package com.example.test2.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import androidx.activity.result.launch
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.test2.R
import com.example.test2.databinding.MainActivityBinding
import com.example.test2.di.HistoryRepositoryProvider
import com.example.test2.di.SettingsDaoProvider
import com.example.test2.domain.entity.ResultPanelType
import com.example.test2.presentation.common.BaseActivity
import com.example.test2.presentation.history.HistoryResult
import com.example.test2.presentation.settings.SettingsActivity

class MainActivity : BaseActivity() {

    private val viewBinding by viewBinding(MainActivityBinding::bind)
    private val viewModel by viewModels<MainViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(SettingsDaoProvider.getDao(this@MainActivity),
                HistoryRepositoryProvider.get(this@MainActivity)) as T
            }
        }
    }

    private val resultLauncher = registerForActivityResult(HistoryResult()) { item ->
        viewModel.onHistoryResult(item)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        viewBinding.mainActivitySettings.setOnClickListener {
            openSettings()
        }

        viewBinding.mainActivityHistory.setOnClickListener {
            openHistory()
        }

        viewBinding.mainEquals.setOnClickListener {
            viewModel.onResultClicked()
        }

        listOf(
            viewBinding.mainPlus,
            viewBinding.mainMinus,
            viewBinding.mainMultiply,
            viewBinding.mainDivide
        ).forEach { operationView ->
            operationView.setOnClickListener { viewModel.onOperationClicked(operationView.contentDescription.toString()) }
        }

        viewBinding.mainClear.setOnClickListener {
            viewModel.onClearClicked()
        }

        listOf(
            viewBinding.mainZero,
            viewBinding.mainOne,
            viewBinding.mainTwo,
            viewBinding.mainThree,
            viewBinding.mainFour,
            viewBinding.mainFive,
            viewBinding.mainSix,
            viewBinding.mainSeven,
            viewBinding.mainEight,
            viewBinding.mainNine
        ).forEachIndexed { index, textView ->
            textView.setOnClickListener { viewModel.onNumberClicked(index) }
        }

        viewBinding.mainBack.setOnClickListener {
            viewModel.onBackClicked()
        }

        viewBinding.mainPoint.setOnClickListener {
            viewModel.onPointClicked()
        }

        viewModel.expressionState.observe(this) { state ->
            viewBinding.inputEdit.text = state
        }
        viewModel.resultState.observe(this) { state ->
            viewBinding.outputTextView.text = state
        }

        viewModel.resultPanelState.observe(this) {
            with(viewBinding.outputTextView) {
                gravity = when (it) {
                    ResultPanelType.LEFT -> Gravity.START.or(Gravity.CENTER_VERTICAL)
                    ResultPanelType.RIGHT -> Gravity.END.or(Gravity.CENTER_VERTICAL)
                    ResultPanelType.HIDE -> gravity
                }
                isVisible = it != ResultPanelType.HIDE
            }
        }

    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }
    private fun openHistory() {
        resultLauncher.launch()
    }
    private fun openSettings() {
        startActivity(Intent(this, SettingsActivity::class.java))
    }
}

