package com.example.test2.presentation.history

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.test2.R
import com.example.test2.databinding.HistoryActivityBinding
import com.example.test2.di.SettingsDaoProvider
import com.example.test2.presentation.common.BaseActivity
import com.example.test2.presentation.main.MainViewModel

class HistoryActivity: BaseActivity() {

    private val viewBinding by viewBinding(HistoryActivityBinding::bind)
    private val viewModel by viewModels<HistoryViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.history_activity)

        val historyAdapter = HistoryAdapter(viewModel::onItemClicked)

        with(viewBinding.historyList) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = historyAdapter
        }

        viewModel.historyItemsState.observe(this) { state ->
            historyAdapter.setData(state)
        }
        viewBinding.historyBack.setOnClickListener {
            finish()
        }

        viewModel.showToastAction.observe(this) { state ->
            Toast.makeText(this, "Нажатие ${state.expression} ${state.result}", Toast.LENGTH_SHORT).show()
        }
    }
}