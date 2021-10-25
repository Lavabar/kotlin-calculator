package com.example.test2.di

import android.content.Context
import com.example.test2.data.db.MainDatabase

object DatabaseProvider {

    private var db: MainDatabase? = null

    fun get(context: Context): MainDatabase {
        return db ?: MainDatabase.create(context).also { db = it }
    }
}

