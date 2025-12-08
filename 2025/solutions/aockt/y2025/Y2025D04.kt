package aockt.y2025

import io.github.jadarma.aockt.core.Solution
import utils.Coordinate2D
import utils.Grid

object Y2025D04 : Solution {

    private fun parseInput(input: String): Grid<Char> =
        Grid(input.lines()) { it }

    override fun partOne(input: String): Int {
        val grid = parseInput(input)
        val o = Coordinate2D(0, 0)
        val surrounding = listOf(
            o.up(),
            o.down(),
            o.left(),
            o.right(),
            o.left() + o.up(),
            o.right() + o.up(),
            o.left() + o.down(),
            o.right() + o.down()
        )
        return grid.indices().count { c ->
            if (grid.at(c) == '.') return@count false
            var count = 0
            for(s in surrounding) {
                if(grid.at(c+s) != null && grid.at(c + s) == '@') {
                    count ++
                }
            }
            return@count count < 4
        }
    }

    override fun partTwo(input: String): Int {
        val grid = parseInput(input)
        val o = Coordinate2D(0, 0)
        val surrounding = listOf(
            o.up(),
            o.down(),
            o.left(),
            o.right(),
            o.left() + o.up(),
            o.right() + o.up(),
            o.left() + o.down(),
            o.right() + o.down()
        )
        var iterate = true
        var total = 0
        while(iterate) {
            val reachable = grid.indices().count { c ->
                if (grid.at(c) == '.') return@count false
                var count = 0
                for(s in surrounding) {
                    if(grid.at(c+s) != null && grid.at(c + s) == '@') {
                        count ++
                    }
                }
                if(count < 4){
                    grid.set(c, '.')
                    total++
                    return@count true
                } else {
                    return@count false
                }
            }
            if(reachable == 0) iterate = false
        }
        return total
    }
}
