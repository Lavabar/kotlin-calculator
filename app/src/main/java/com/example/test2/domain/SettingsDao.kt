package com.example.test2.domain

import com.example.test2.domain.entity.PrecisionValue
import com.example.test2.domain.entity.ResultPanelType
import com.example.test2.domain.entity.VibrationFeedbackValue

interface SettingsDao {

    /**
     * получает тип отображения панели результата
     */
    suspend fun getResultPanelType(): ResultPanelType

    /**
     * устанавливает тип отображения панели результата
     * */
    suspend fun setResultPanelType(resultPanelType: ResultPanelType)

    /**
     * получает точность вычисления (в знаках после запятой)
     * */
    suspend fun getPrecisionValue(): PrecisionValue

    /**
     * устанавливает точность вычисления (в знаках после запятой)
     * */
    suspend fun setPrecisionValue(precisionValue: PrecisionValue)

    /**
     * получает значение величины виброотклика
     * */
    suspend fun getVibrationFeedback(): VibrationFeedbackValue

    /**
     * устанавливает величину виброотклика
     * */
    suspend fun setVibrationFeedback(vibrationFeedbackValue: VibrationFeedbackValue)

}

