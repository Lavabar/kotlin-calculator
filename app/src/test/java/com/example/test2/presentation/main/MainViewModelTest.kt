package com.example.test2.presentation.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import java.lang.AssertionError

class MainViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun testPlus() {
        val viewModel = MainViewModel()

        viewModel.onNumberClick(2)
        viewModel.onOperationClick("+")
        viewModel.onNumberClick(2)
        viewModel.onResultClick()

        Assert.assertEquals("4", viewModel.resultState.value)
    }

    @Test
    fun testPoint() {
        val viewModel = MainViewModel()

        viewModel.onPointClick()

        Assert.assertEquals(null, viewModel.resultState.value)
    }
}