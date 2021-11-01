package com.example.test2.data

import com.fathzer.soft.javaluator.DoubleEvaluator
import java.math.BigDecimal
import java.math.MathContext

class CalculateExpression {

    private val mc = MathContext(5)
    private val evaluator = DoubleEvaluator(DoubleEvaluator.getDefaultParameters(), true)

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
        val result = BigDecimal(evaluator.evaluate(preparedExpression), mc)

        return result.toString();
    }
}
