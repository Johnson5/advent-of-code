package aockt.y2025

import io.github.jadarma.aockt.core.Solution
import utils.Grid
import kotlin.collections.component1
import kotlin.collections.component2

object Y2025D04 : Solution {

    private fun parseInput(input: String): Grid<Char> =
        Grid(input.lines()) { it }

    override fun partOne(input: String): Long {
        return 3L
    }

    override fun partTwo(input: String): Long =
        TODO()
}
