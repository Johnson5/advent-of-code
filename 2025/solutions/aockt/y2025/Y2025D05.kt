package aockt.y2025

import io.github.jadarma.aockt.core.Solution

object Y2025D05 : Solution {

    private fun parseInput(input: String): Pair<List<LongRange>, List<Long>> {
        val ranges = input.lines().filter {
            it.contains('-')
        }.map {
            val (start, end) = it.split('-', limit = 2)
            (start.toLong() .. end.toLong())
        }

        val ingredients = input.lines().filter {
            it.isNotBlank() && !it.contains('-')
        }.map {
            it.toLong()
        }
        return Pair(ranges, ingredients)
    }

    override fun partOne(input: String): Int {
        val (ranges, ingredients) = parseInput(input)

        return ingredients
            .count {value -> ranges.any { value in it }}
    }

    override fun partTwo(input: String): Long {
        val ranges = parseInput(input).first.sortedBy { it.first }

        val merged = buildList {
            for (r in ranges) {
                if (isEmpty()) add(r)
                else {
                    val last = last()
                    if(last.last >= r.first) {
                        removeLast()
                        add(last.first .. maxOf(r.last, last.last))
                    } else {
                        add(r)
                    }
                }
            }
        }

        return merged.sumOf { it.last - it.first + 1 }
    }
}
