package com.example.test2.presentation.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test2.presentation.common.SingleLiveEvent

class SettingsViewModel: ViewModel() {

    private val _resultPanelState = MutableLiveData<ResultPanelType>(ResultPanelType.RIGHT)
    val resultPanelState: LiveData<ResultPanelType> = _resultPanelState

    private val _openResultPanelAction = SingleLiveEvent<ResultPanelType>()
    val openResultPaneAction: LiveData<ResultPanelType> = _openResultPanelAction

    fun onResultPanelTypeChanged(resultPanelType: ResultPanelType) {
        _resultPanelState.value = resultPanelType
    }

    fun onResultPanelClick() {
        _openResultPanelAction.value = _resultPanelState.value
    }


}