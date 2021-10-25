package com.example.test2.presentation.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test2.domain.entity.HistoryItem
import com.example.test2.presentation.common.SingleLiveEvent

class HistoryViewModel : ViewModel() {

    private val historyItems: List<HistoryItem> = listOf(
        HistoryItem("204+32*4", "2343"),
        HistoryItem("204-22/4", "243"),
        HistoryItem("204+32*4", "2343"),
        HistoryItem("204+32*4", "232243"),
        HistoryItem("2104+432*4", "23413"),
        HistoryItem("204+324*4", "23543"),
        HistoryItem("2054+32*4", "231343"),
        HistoryItem("204+32*42", "23413"),
        HistoryItem("2014+32*4", "23432")
    )

    private val _historyItemsState = MutableLiveData<List<HistoryItem>>()
    val historyItemsState: LiveData<List<HistoryItem>> = _historyItemsState

    private val _showToastAction = SingleLiveEvent<HistoryItem>()
    val showToastAction: LiveData<HistoryItem> = _showToastAction

    init {
        _historyItemsState.value = historyItems
    }

    fun onItemClicked(historyItem: HistoryItem) {
        _showToastAction.value = historyItem
    }

}

