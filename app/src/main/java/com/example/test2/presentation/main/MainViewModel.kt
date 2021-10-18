package com.example.test2.presentation.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test2.data.calculateExpression
import com.example.test2.domain.SettingsDao
import com.example.test2.domain.entity.ResultPanelType
import kotlinx.coroutines.launch

class MainViewModel (
    private val settingsDao: SettingsDao
        ): ViewModel() {

    private var expression: String = ""
    private val _expressionState = MutableLiveData<String>()
    val expressionState: LiveData<String> = _expressionState

    private val _resultState = MutableLiveData<String>()
    val resultState: LiveData<String> = _resultState

    private val _resultPanelState = MutableLiveData<ResultPanelType>()
    val resultPanelState: LiveData<ResultPanelType> = _resultPanelState

    private val _calcState = MutableLiveData<String>()

    init {
        viewModelScope.launch {
            _resultPanelState.value = settingsDao.getResultPanelType()
        }
    }

    fun onNumberClick(number: Int) {
        if (expression != "Infinity") {
            expression += number.toString()
            _expressionState.value = expression
        } else {
            expression = number.toString()
            _expressionState.value = expression
            _resultState.value = ""
        }
    }

    fun onResultClick() {
        _calcState.value = calculateExpression(expression)
        expression = _calcState.value.toString()
        _expressionState.value = expression
        _resultState.value = expression
    }

    fun onClearClick() {
        _resultState.value = ""
    }

    fun onOperationClick(operation: String) {
        if (expression.isNotBlank() && (expression.last().isDigit() || expression.endsWith("."))) {
            _calcState.value = calculateExpression(expression)
            _resultState.value = _calcState.value
            expression += operation
            _expressionState.value = expression
        }
    }

    fun onBackClick() {
        expression = expression.dropLast(1)
        _expressionState.value = expression
    }

    fun onPointClick() {
        if (expression.isNotBlank() && expression.last().isDigit()) {
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
}