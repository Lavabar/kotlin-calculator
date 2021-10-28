package com.example.test2.presentation.history

import org.junit.Assert.*
import org.junit.Test
import java.time.LocalDateTime

class HistoryAdapterKtTest {
    @Test
    fun testFormatter() {
        val date = LocalDateTime.of(2021, 10, 25, 10, 2, 3)
        val result = date.formatForHistory()

        assertEquals("10:02:03 25.10.2021", result)
    }
}