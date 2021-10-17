package com.example.test2.presentation.settings

import com.example.test2.presentation.common.BaseActivity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.example.test2.R


class SettingsActivity : BaseActivity() {

    companion object {
        const val SETTINGS_RESULT_KEY = "SETTINGS_RESULT_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data = intent.getIntExtra(SETTINGS_RESULT_KEY, -1)
        Toast.makeText(this, "data is $data", Toast.LENGTH_SHORT).show()
        setContentView(R.layout.settings_activity)


        val back = findViewById<ImageView>(R.id.settings_back)
        back.setOnClickListener {
            setResult(RESULT_OK, Intent().putExtra(SETTINGS_RESULT_KEY,"Hello!"))
            finish()
        }
    }
}