package aockt.y2025

import io.github.jadarma.aockt.core.Solution
import utils.Coordinate
import utils.Grid

object Y2025D07 : Solution {

    private fun parseInput(input: String): List<String> {
        return input.lines().filter { it.contains('^') }
    }

    override fun partOne(input:String): Int {
        val splitter = '^'
        var beams = setOf(input.lines()[0].indexOf('S'))
        var count = 0

        for (row in parseInput(input)) {
            val nextBeams = mutableSetOf<Int>()
            for (beam in beams) {
                if(row[beam] == splitter) {
                    nextBeams.add(beam + 1)
                    nextBeams.add(beam - 1)
                    count ++
                } else {
                    nextBeams.add(beam)
                }
            }
            beams = nextBeams
        }
        return count
    }

    override fun partTwo(input: String): Long {
        val splitter = -1L
        val grid = Grid(parseInput(input)) { if(it == '.') 0L else -1L }
        val start = Coordinate(grid.first().indexOf(-1L), 0)
        grid.set(start.left(), 1L)
        grid.set(start.right(), 1L)
        var beams = setOf(start.left(), start.right())

        for (rowIndex in 0 until grid.height - 1) {
            val nextBeams = mutableSetOf<Coordinate>()
            for(beam in beams) {
                if(grid.at(beam.down()) == splitter) {
                    val leftDown = beam.down().left()
                    val leftDownValue = grid.at(beam.down().left())!!
                    grid.set(leftDown, leftDownValue + grid.at(beam)!!)

                    val rightDown = beam.down().right()
                    val rightDownValue = grid.at(beam.down().right())!!
                    grid.set(rightDown, rightDownValue + grid.at(beam)!!)

                    nextBeams.add(leftDown)
                    nextBeams.add(rightDown)
                } else {
                    grid.set(beam.down(), grid.at(beam.down())!! + grid.at(beam)!!)
                    nextBeams.add(beam.down())
                }
            }
            beams = nextBeams
        }

        return grid.last().filter { it > 0 }.sum().also{ println(it) }
    }
}
