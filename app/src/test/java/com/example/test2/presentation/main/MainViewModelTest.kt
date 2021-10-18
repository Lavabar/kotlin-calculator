package com.example.test2.presentation.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.test2.domain.SettingsDao
import com.example.test2.domain.entity.ResultPanelType
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import java.lang.AssertionError

class MainViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private val settingsDao: SettingsDao = SettingsDaoFake()

    @Test
    fun testPlus() {
        val viewModel = MainViewModel(settingsDao)

        viewModel.onNumberClick(2)
        viewModel.onOperationClick("+")
        viewModel.onNumberClick(2)
        viewModel.onResultClick()

        Assert.assertEquals("4", viewModel.resultState.value)
    }

    @Test
    fun testPoint() {
        val viewModel = MainViewModel(settingsDao)

        viewModel.onPointClick()

        Assert.assertEquals(null, viewModel.resultState.value)
    }
}

class SettingsDaoFake: SettingsDao {

    var resultPanelType: ResultPanelType = ResultPanelType.LEFT
    override suspend fun setResultPanelType(resultPanelType: ResultPanelType) {
        this.resultPanelType = resultPanelType
    }
    override suspend fun getResultPanelType(): ResultPanelType {
        return resultPanelType
    }
}