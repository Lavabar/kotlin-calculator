package com.example.test2.data

import android.icu.number.Notation
import com.fathzer.soft.javaluator.DoubleEvaluator
import java.lang.Math.floor
import kotlin.math.roundToInt

/**
 * Рассчитывает значение выражения [expression]
 */

fun calculateExpression(expression: String): String {

    var preparedExpression: String = expression

    if (expression.isBlank()) return ""

    when {
        expression.endsWith("+") -> preparedExpression = "(" + expression.dropLast(1) + ")*2"
        expression.endsWith("-") -> preparedExpression = "0"
        expression.endsWith("*") -> preparedExpression = "(" + expression.dropLast(1) + ")^2"
        expression.endsWith("/") -> preparedExpression = "1"
    }

    val result = DoubleEvaluator().evaluate(preparedExpression)

    return if (result.roundToInt().toDouble() == result) {
        result.toInt().toString()
    } else {
        result.toString()
    }
}