package com.example.test2.domain

import com.fathzer.soft.javaluator.DoubleEvaluator
import java.math.BigDecimal
import java.math.MathContext

class CalculateExpression {

    private val mc = MathContext(5)
    private val evaluator = DoubleEvaluator(DoubleEvaluator.getDefaultParameters(), true)
    private val operandRegex = """[0-9]+(?:\.[0-9]*)?(?:E[+-][0-9]+)?""".toRegex()
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

    fun zeroLastOperand(expression: String): String {
        when {
            expression.endsWith("+")
                    || expression.endsWith("-")
                    || expression.endsWith("*")
                    || expression.endsWith("/")
                    || expression.endsWith("^") -> return expression.plus("0")
            expression.last().isDigit() -> {
                return expression.replaceRange(getLastOperandRange(expression), "0")
            }
        }
        return expression
    }

    private fun getLastOperandRange(expression: String): IntRange {
        return operandRegex.findAll(expression).last().range
    }

    fun fastEvaluateCheck(operand: String): Double {
        return evaluator.evaluate(operand)
    }
}
