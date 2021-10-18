package com.example.test2.domain

import com.example.test2.domain.entity.ResultPanelType

interface SettingsDao {

    /**
     * устанавливает тип отображения панели результата
     * */
    suspend fun getResultPanelType(): ResultPanelType

    /**
     * получает тип отображения панели результата
     */
    suspend fun setResultPanelType(resultPanelType: ResultPanelType)

}

