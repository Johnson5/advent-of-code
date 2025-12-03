package aockt.y2025

import io.github.jadarma.aockt.core.Solution

object Y2025D01 : Solution {

    private fun parseInput(input: String): List<String> =
        input.lines()

    override fun partOne(input: String): Int {
        val moves = parseInput(input)
        var answer = 0
        var value = 50
        for (move in moves) {
            val amount = move.substring(1).toInt()
            value = if(move.startsWith('L')) {
                Math.floorMod(value - amount, 100)
            } else {
                Math.floorMod(value + amount, 100)
            }
            if (value == 0) {
                answer++
            }
        }
        return answer
    }

    override fun partTwo(input: String): Int {
        val moves = parseInput(input)
        var value = 50
        var answer = 0

        for (move in moves) {
            val dir = if (move.startsWith('L')) -1 else 1
            val amount = move.substring(1).toInt()

            val fullCycles = amount / 100
            val remainder = amount % 100

            answer += fullCycles

            val newValue = Math.floorMod(value + dir * remainder, 100)

            if (remainder > 0 && value != 0 && newValue != 0) {
                if ((dir == 1 && newValue < value) || (dir == -1 && newValue > value)) {
                    answer += 1
                }
            }
            if (remainder != 0 && newValue == 0) {
                answer += 1
            }
            value = newValue
        }

        return answer
    }
}
