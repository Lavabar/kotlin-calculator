package com.example.test2.presentation.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.test2.domain.SettingsDao
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private val settingsDao: SettingsDao = SettingsDaoFake()
    private val historyRepository = HistoryRepositoryFake()

    @Test
    fun testPlus() {
        val viewModel = MainViewModel(settingsDao, historyRepository)

        viewModel.onNumberClicked(2)
        viewModel.onOperationClicked("+")
        viewModel.onNumberClicked(2)
        viewModel.onResultClicked()

        Assert.assertEquals("2+2", viewModel.expressionState.value)
        Assert.assertEquals("4", viewModel.resultState.value)
    }

    @Test
    fun testPoint() {
        val viewModel = MainViewModel(settingsDao, historyRepository)

        viewModel.onPointClicked()

        Assert.assertEquals(null, viewModel.resultState.value)
    }
}

