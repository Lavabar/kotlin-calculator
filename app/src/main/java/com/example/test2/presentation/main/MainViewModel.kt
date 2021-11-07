package com.example.test2.presentation.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test2.domain.CalculateExpression
import com.example.test2.domain.HistoryRepository
import com.example.test2.domain.SettingsDao
import com.example.test2.domain.entity.HistoryItem
import com.example.test2.domain.entity.ResultPanelType
import kotlinx.coroutines.launch

class MainViewModel (
    private val settingsDao: SettingsDao,
    private val historyRepository: HistoryRepository
        ): ViewModel() {

    private var expression: String = ""
    private var inputNumber: String = ""
    private val _expressionState = MutableLiveData<String>()
    val expressionState: LiveData<String> = _expressionState

    private val _resultState = MutableLiveData<String>()
    val resultState: LiveData<String> = _resultState

    private val _resultPanelState = MutableLiveData<ResultPanelType>()
    val resultPanelState: LiveData<ResultPanelType> = _resultPanelState

    private val calculator = CalculateExpression()

    init {
        viewModelScope.launch {
            _resultPanelState.value = settingsDao.getResultPanelType()
        }
    }

    fun onNumberClicked(number: Int) {
        if (expression != "Infinity") {
            if (number.toDouble() ==
                calculator.fastEvaluateCheck(inputNumber + number.toString())) {
                    if (inputNumber.isNotBlank())
                        expression = expression.dropLast(1)
                    inputNumber = number.toString()
            } else {
                inputNumber += number.toString()
            }
            expression += number.toString()
        } else {
            inputNumber = number.toString()
            expression = number.toString()
            _resultState.value = ""
        }
        _expressionState.value = expression
    }

    fun onResultClicked() {
        val result = calculator.calculateExpression(expression)
        if (("" != expression || "0" != expression) && "0" != result) {
            viewModelScope.launch {
                historyRepository.add(HistoryItem(expression, result))
            }
        }
        expression = result
        _expressionState.value = expression
        _resultState.value = expression
        inputNumber = ""
    }

    fun onAllClearClicked() {
        _resultState.value = ""
        expression = ""
        _expressionState.value = expression
        inputNumber = ""
    }

    fun onOperationClicked(operation: String) {
        if (expression.isNotBlank() && (expression.last().isDigit() || expression.endsWith("."))) {
            _resultState.value = calculator.calculateExpression(expression)
            expression += operation
            _expressionState.value = expression
        }
        inputNumber = ""
    }

    fun onClearClicked() {
        _resultState.value = ""
        expression = calculator.zeroLastOperand(expression)
        _expressionState.value = expression
        inputNumber = "0"
    }

    fun onPointClicked() {
        if (expression.isNotBlank() && expression.last().isDigit()) {
            inputNumber += "."
            expression += "."
            _expressionState.value = expression
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("MainViewModel", "onCleared")
    }

    fun onStart() {
        viewModelScope.launch {
            _resultPanelState.value = settingsDao.getResultPanelType()
        }
    }

    fun onHistoryResult(item: HistoryItem?) {
        if (item != null) {
            expression = item.expression
            _expressionState.value = expression
            _resultState.value = item.result
        }
    }
}