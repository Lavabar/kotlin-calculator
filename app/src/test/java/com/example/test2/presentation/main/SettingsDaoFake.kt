package com.example.test2.presentation.main

import com.example.test2.domain.SettingsDao
import com.example.test2.domain.entity.ResultPanelType

class SettingsDaoFake: SettingsDao {

    var resultPanelType: ResultPanelType = ResultPanelType.LEFT
    override suspend fun setResultPanelType(resultPanelType: ResultPanelType) {
        this.resultPanelType = resultPanelType
    }
    override suspend fun getResultPanelType(): ResultPanelType {
        return resultPanelType
    }
}

