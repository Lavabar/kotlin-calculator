package com.example.test2.presentation.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    override fun onCleared() {
        super.onCleared()
        Log.d("MainViewModel", "onCleared")
    }

    private var expression: String = ""
    private val _expressionState = MutableLiveData<String>()
    val expressionState: LiveData<String> = _expressionState

    fun onNumberClick(number: Int) {
        expression += number.toString()
        _expressionState.value = expression
    }


    private val _resultState = MutableLiveData<String>()
    val resultState: LiveData<String> = _resultState

    fun onResultClick() {
        _resultState.value = expression
    }

    fun onClearClick() {
        _resultState.value = ""
    }
}