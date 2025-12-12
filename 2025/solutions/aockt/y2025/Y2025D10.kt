package aockt.y2025

import io.github.jadarma.aockt.core.Solution
import kotlin.math.min

object Y2025D10 : Solution {
    private fun parseInput(input: String): List<Triple<Int, List<String>, List<Int>>> =
        input
            .lines()
            .map { line ->
                val split = line.split(" ")
                val goal = split.first()
                    .removeSurrounding("[", "]")
                    .map { if (it=='#') '1' else '0'}
                    .joinToString("")
                    .toInt(2)
                val buttons = split.drop(1).dropLast(1)
                    .map {
                        val base = MutableList<Char>(split.first().length - 2) { '0' }
                        it.removeSurrounding("(", ")")
                            .split(",")
                            .map(String::toInt)
                            .forEach { index ->
                                base[index] = '1'
                            }
                        base.joinToString("")
                    }
                val requirements = split.last()
                    .removeSurrounding("{", "}")
                    .split(",")
                    .map(String::toInt)
                Triple(goal, buttons, requirements)
            }


    override fun partOne(input: String): Int {
        return parseInput(input).sumOf { (goal, buttons, _) ->
            val minPresses = mutableMapOf(Pair(0, 0))
            val queue = ArrayDeque(listOf(0))
            while(queue.isNotEmpty()){
                val state = queue.removeFirst()
                if (state == goal) break

                for(button in buttons) {
                    val newState = state xor button.toInt(2)
                    val presses = minPresses.getValue(state) + 1
                    if(presses < minPresses.getOrDefault(newState, Int.MAX_VALUE)) {
                        minPresses[newState] = presses
                        queue.add(newState)
                    }
                }
            }
            minPresses.getValue(goal)
        }
    }

    override fun partTwo(input: String): Int {
        return parseInput(input).sumOf { (_, buttons, requirements) ->
            val initial = List(requirements.size) { 0 }
            val minPresses = mutableMapOf(Pair(requirements, 0))
            val queue = ArrayDeque(listOf(requirements))
            while (queue.isNotEmpty()) {
                val state: List<Int> = queue.removeFirst()
                if (state == initial) break
                for (button in buttons) {
                    val newState = state.mapIndexed { index, num ->
                        if (button[index] == '1') num -1 else num
                    }
                    if(newState.any { it < 0}) continue
                    val presses = minPresses.getValue(state) + 1
                    if (presses < minPresses.getOrDefault(newState, Int.MAX_VALUE)) {
                        minPresses[newState] = presses
                        queue.add(newState)
                    }
                }
            }
            minPresses.getValue(initial)
        }
    }
}
