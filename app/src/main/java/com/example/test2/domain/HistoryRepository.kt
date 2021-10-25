package com.example.test2.domain

import com.example.test2.domain.entity.HistoryItem

interface HistoryRepository {

    suspend fun add(historyItem: HistoryItem)

    suspend fun getAll(): List<HistoryItem>
}