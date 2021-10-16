package com.example.test2

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class TransmissionResult : ActivityResultContract<Int, String?>() {
    override fun createIntent(context: Context, input: Int?) : Intent {
        var intent = Intent(context, SettingsActivity::class.java)
        intent.putExtra(SettingsActivity.SETTINGS_RESULT_KEY, input)

        return intent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        return intent?.getStringExtra(SettingsActivity.SETTINGS_RESULT_KEY)
    }

}
