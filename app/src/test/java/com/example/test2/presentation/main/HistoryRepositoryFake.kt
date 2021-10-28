package com.example.test2.presentation.main

import com.example.test2.domain.HistoryRepository
import com.example.test2.domain.entity.HistoryItem

class HistoryRepositoryFake: HistoryRepository {

    override suspend fun add(historyItem: HistoryItem) {

    }

    override suspend fun getAll(): List<HistoryItem> {
        return emptyList()
    }

}