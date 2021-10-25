package com.example.test2.di

import android.content.Context
import com.example.test2.data.HistoryRepositoryImpl
import com.example.test2.domain.HistoryRepository

object HistoryRepositoryProvider {

    private var repository: HistoryRepository? = null

    fun get(context: Context): HistoryRepository {
        return repository ?: HistoryRepositoryImpl(DatabaseProvider.get(context).historyItemDao)
            .also { repository = it }
    }
}