package com.example.test2.presentation.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test2.domain.SettingsDao
import com.example.test2.domain.entity.ResultPanelType
import com.example.test2.presentation.common.SingleLiveEvent
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val settingsDao: SettingsDao
): ViewModel() {

    private val _resultPanelState = MutableLiveData<ResultPanelType>()
    val resultPanelState: LiveData<ResultPanelType> = _resultPanelState

    private val _openResultPanelAction = SingleLiveEvent<ResultPanelType>()
    val openResultPaneAction: LiveData<ResultPanelType> = _openResultPanelAction

    init {
        viewModelScope.launch {
            _resultPanelState.value = settingsDao.getResultPanelType()
        }
    }
    fun onResultPanelTypeChanged(resultPanelType: ResultPanelType) {
        _resultPanelState.value = resultPanelType
        viewModelScope.launch {
            settingsDao.setResultPanelType(resultPanelType)
        }

    }

    fun onResultPanelClick() {
        _openResultPanelAction.value = _resultPanelState.value
    }


}