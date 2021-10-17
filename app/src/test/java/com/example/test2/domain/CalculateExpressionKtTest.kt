package com.example.test2.domain

import com.example.test2.data.calculateExpression
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

class CalculateExpressionKtTest {


    @Test
    fun testPlus() {
        val expression = "2+2"
        val result = "4"

        Assert.assertEquals(result, calculateExpression(expression))
    }

    @Test
    fun testSubtraction() {
        val expression = "4-2"
        val result = "2"

        Assert.assertEquals(result, calculateExpression(expression))
    }

    @Test
    fun testExpression() {
        val expression = "4-2*2"
        val result = "0"

        Assert.assertEquals(result, calculateExpression(expression))
    }

    @Test
    fun testDoubleError() {
        val expression = "4.0000000000000001 + 1"
        val result = "5"

        Assert.assertEquals(result, calculateExpression(expression))
    }

    @Test
    fun testDoubleError2() {
        val expression = "5 / 2"
        val result = "2.5"

        Assert.assertEquals(result, calculateExpression(expression))
    }

    @Test
    fun testInput() {
        testCalculation("", "")
        testCalculation("1+", "2")
        testCalculation("1-", "0")
        testCalculation("3*", "9")
        testCalculation("7/", "1")
    }

    private fun testCalculation(expression: String, result: String) {
        Assert.assertEquals(result, calculateExpression(expression))
    }

    @Test
    fun testDivZero() {
        val expression = "1 / 0"
        val result = "Infinity"

        Assert.assertEquals(result, calculateExpression(expression))
    }

    @Test
    fun testBigNumber() {
        val expression = "999999999*999999999"
        val result = "1e18"

        Assert.assertEquals(result, calculateExpression(expression))
    }
}