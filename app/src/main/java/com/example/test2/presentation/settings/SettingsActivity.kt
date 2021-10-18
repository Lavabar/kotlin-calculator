package com.example.test2.presentation.settings

import com.example.test2.presentation.common.BaseActivity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
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
            Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show()
        }
    }
}