package com.example.test2.di

import android.content.Context
import com.example.test2.data.SettingsDaoImpl
import com.example.test2.domain.SettingsDao

object SettingsDaoProvider {

    private var dao: SettingsDao? = null
    fun getDao(context: Context): SettingsDao {
        return dao ?: SettingsDaoImpl(
            context.getSharedPreferences(
                "settings",
                Context.MODE_PRIVATE
            )
        ).also {
            dao = it
        }
    }
}