package aockt.y2025

import io.github.jadarma.aockt.core.Solution
import utils.Grid
import kotlin.math.min

object Y2025D12 : Solution {
    private fun parseInput(input: String): Pair<List<Grid<Boolean>>, List<Region>> {
        val splitOnEmptyLine = input.split("\n\n")
        val shapes = splitOnEmptyLine.dropLast(1)
            .map {
                Grid(it.lines()) { char -> char == '#' }
            }
        val regions = splitOnEmptyLine.last().lines()
            .map {
                val split = it.split(" ")
                val (width, height) = split.first().dropLast(1).split("x").map(String::toInt)
                val requirements = split.drop(1).map(String::toInt)
                Region(width, height, requirements)
            }
        return Pair(shapes, regions)
    }

    override fun partOne(input: String): Int {
        val (shapes, regions) = parseInput(input)

        return regions.count { region ->
            val area = region.width * region.height
            val required = region.requirements.mapIndexed { index, i ->
                shapes[index].sumOf { it } * i
            }.sum()
            area >= required
        }
    }

    override fun partTwo(input: String): Long {
        return 3L
    }

    data class Region(val width: Int, val height: Int, val requirements: List<Int>)
}
