package com.example.test2.presentation.main

import com.example.test2.presentation.common.BaseActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.test2.R
import com.example.test2.presentation.settings.TransmissionResult
import com.example.test2.databinding.MainActivityBinding

class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val viewBinding by viewBinding(MainActivityBinding::bind)

    private val getTransmissionResult : ActivityResultLauncher<Int> =
        registerForActivityResult(TransmissionResult()) { result ->
            Toast.makeText(this, "res is $result", Toast.LENGTH_SHORT).show()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        viewBinding.inputEdit.apply {
            showSoftInputOnFocus = false
            isCursorVisible = false
        }
        viewBinding.mainActivitySettings.setOnClickListener {
            openSettings()
        }

        viewBinding.mainEquals.setOnClickListener {
            viewModel.onResultClick()
        }

        listOf(
            viewBinding.mainPlus,
            viewBinding.mainMinus,
            viewBinding.mainMultiply,
            viewBinding.mainDivide
        ).forEach { operationView ->
            operationView.setOnClickListener { viewModel.onOperationClick(operationView.contentDescription.toString()) }
        }

        viewBinding.mainClear.setOnClickListener {
            viewModel.onClearClick()
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
            textView.setOnClickListener { viewModel.onNumberClick(index) }
        }

        viewModel.expressionState.observe(this) { state ->
            viewBinding.inputEdit.setText(state)
        }
        viewModel.resultState.observe(this) { state ->
            viewBinding.outputTextView.text = state
        }

    }
    private fun openSettings() {
        getTransmissionResult.launch(100)
    }
}

