package aockt.y2025

import io.github.jadarma.aockt.core.Solution
import utils.Grid

object Y2025D06 : Solution {

    private fun parseInput(input: String): List<String>{
        return input.lines()
    }

    override fun partOne(input: String): Long {
        val pairs = mutableListOf<Pair<MutableList<Long>,Char>>()
        input.lines().map { line ->
            line.trim().split("\\s+".toRegex()).map{ it.trim() }.mapIndexed { i, str ->
                val pair = pairs.elementAtOrNull(i)
                if(str != "+" && str != "*") {
                    pair?.first?.add(str.toLong()) ?: pairs.add(Pair(mutableListOf(str.toLong()), '_'))
                } else {
                    pairs.set(i, pair!!.copy(
                        second = str.first()
                    ))
                }
            }
        }
        return pairs.sumOf { (values, operator) ->
            (if(operator == '+') {
                values.sum()
            } else {
                values.reduce { acc, i -> acc * i }
            })
        }
    }

    override fun partTwo(input: String): Long {
        val grid = Grid(parseInput(input)) { it }
        var total = 0L
        var runningCount: Long = -1
        var operator: Char? = null
        val maxLength = grid.maxOf { it.size }
        (0 until maxLength).forEach {
            val column = grid.getColumn(it, ' ').joinToString("")

            if(column.isBlank()) {
                total += runningCount
                runningCount = 0
                operator = null
                return@forEach
            }

            val number: Long?
            if(column.last() == '+' || column.last() == '*') {
                operator = column.last()
                runningCount = if (operator == '+') 0L else 1L
                val numberStr = column.dropLast(1).trim()
                number = numberStr.toLong()
            } else {
                number = column.trim().toLong()
            }

            if(operator == '+') {
                runningCount += number
            } else if (operator == '*') {
                runningCount *= number
            }
        }
        total += runningCount
        println("Part 2: $total")
        return total
    }
}
