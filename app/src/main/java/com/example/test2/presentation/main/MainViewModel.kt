package com.example.test2.presentation.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private var expression: String = ""
    private val _expressionState = MutableLiveData<String>()
    val expressionState: LiveData<String> = _expressionState

    private val _resultState = MutableLiveData<String>()
    val resultState: LiveData<String> = _resultState

    private val _calcState = MutableLiveData<String>()

    private fun calculateExpression(): String {

        var res: Double = 0.0

        return res.toString()
    }

    fun onNumberClick(number: Int) {
        expression += number.toString()
        _expressionState.value = expression
    }

    fun onResultClick() {
        _calcState.value = calculateExpression()
        expression = _calcState.value.toString()
        _expressionState.value = expression
        _resultState.value = expression
    }

    fun onClearClick() {
        _resultState.value = ""
    }

    fun onOperationClick(operation: String) {
        _calcState.value = calculateExpression()
        _resultState.value = _calcState.value
        expression += operation
        _expressionState.value = expression
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("MainViewModel", "onCleared")
    }
}