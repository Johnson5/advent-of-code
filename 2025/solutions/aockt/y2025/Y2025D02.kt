package aockt.y2025

import io.github.jadarma.aockt.core.Solution
import kotlin.collections.component1
import kotlin.collections.component2

object Y2025D02 : Solution {

    private fun parseInput(input: String): List<Pair<Long, Long>> =
        input.split(',').map {
            val (start, end) = it.split('-').map(String::toLong)
            start to end
        }

    override fun partOne(input: String): Long =
        parseInput(input)
            .flatMap { (start, end) -> (start..end) }
            .filter { number ->
                val asString = number.toString()
                val mid = asString.length / 2
                val firstHalf = asString.take(mid)
                val secondHalf = asString.drop(mid)
                firstHalf == secondHalf
            }
            .sum()

    override fun partTwo(input: String): Long =
        parseInput(input)
            .flatMap { (start, end) -> (start .. end) }
            .filter { number ->
                number.toString().matches(Regex("^(\\d+)\\1+\$"))
            }
            .sum()
}
