package com.example.test2

import BaseActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher

class MainActivity : BaseActivity() {

    private val getTransmissionResult : ActivityResultLauncher<Int> =
        registerForActivityResult(TransmissionResult()) { result ->
            Toast.makeText(this, "res is $result", Toast.LENGTH_SHORT).show()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val button: Button = findViewById(R.id.main_activity_settings)
        button.setOnClickListener {
            openSettings()
        }
    }
    private fun openSettings() {
        getTransmissionResult.launch(100)
    }
}

