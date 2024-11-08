package ru.mrfix1033.gridandtablelayout

import net.objecthunter.exp4j.ExpressionBuilder

class Utils {
    companion object {
        fun executeExpression(expression: String) = run {
            try {
                ExpressionBuilder(expression).build().evaluate()
            } catch (e: Exception) {
                null
            }
        }
    }
}