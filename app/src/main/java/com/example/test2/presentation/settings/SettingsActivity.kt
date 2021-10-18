package com.example.test2.presentation.settings

import android.app.AlertDialog
import com.example.test2.presentation.common.BaseActivity
import android.os.Bundle
import androidx.activity.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.test2.R
import com.example.test2.databinding.SettingsActivityBinding


class SettingsActivity : BaseActivity() {

    private val viewBinding by viewBinding(SettingsActivityBinding::bind)
    private val viewModel: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.settings_activity)

        viewBinding.settingsBack.setOnClickListener {
            finish()
        }

        viewBinding.resultPanel.setOnClickListener {
            //viewModel.onResultPanelClick()
            //Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show()
            viewModel.onResultPanelClick()
        }
        viewModel.resultPanelState.observe(this) { state ->

            viewBinding.resultPanelDescription.text =
                resources.getStringArray(R.array.result_panel_types)[state.ordinal]
        }
        viewModel.openResultPaneAction.observe(this) { type ->
            showDialog(type)
        }
    }

    private fun showDialog(type: ResultPanelType) {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.result_panel_dialog_title))
            .setSingleChoiceItems(R.array.result_panel_types, type.ordinal){ dialog, id ->
                viewModel.onResultPanelTypeChanged(ResultPanelType.values()[id])
            }
            .show()
    }
}