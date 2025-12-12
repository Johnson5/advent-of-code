package aockt.y2025

import io.github.jadarma.aockt.core.Solution
import utils.Coordinate2D
import utils.Grid
import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.math.abs
import kotlin.math.max

object Y2025D09 : Solution {
    private fun parseInput(input: String): List<Coordinate2D> = input.lines().map {
        val (x, y) = it.split(',').map(String::toInt)
        Coordinate2D(x, y)
    }

    override fun partOne(input: String): Long {
        val tiles = parseInput(input)
        val maxX = tiles.maxOf { it.x }
        val maxY = tiles.maxOf { it.y }
        val gridTopLeft = Coordinate2D.origin()
        val gridBottomLeft = Coordinate2D(0, maxY)
        val gridTopRight = Coordinate2D(maxX, 0)
        val gridBottomRight = Coordinate2D(maxX, maxY)
        var br = gridTopLeft
        var bl = gridTopRight
        var tr = gridBottomLeft
        var tl = gridBottomRight

        for (tile in tiles) {
            if (tile.manhattanDistanceTo(gridTopLeft) < tl.manhattanDistanceTo(gridTopLeft)) tl = tile
            if (tile.manhattanDistanceTo(gridTopRight) < tr.manhattanDistanceTo(gridTopRight)) tr = tile
            if (tile.manhattanDistanceTo(gridBottomLeft) < bl.manhattanDistanceTo(gridBottomLeft)) bl = tile
            if (tile.manhattanDistanceTo(gridBottomRight) < br.manhattanDistanceTo(gridBottomRight)) br = tile
        }
        val brtl = (br.x - tl.x + 1).toLong() * (br.y - tl.y + 1).toLong()
        val bltr = (tr.x - bl.x + 1).toLong() * (bl.y - tr.y + 1).toLong()

        return max(brtl, bltr).also { println(it) }
    }

    fun List<Int>.compressed(): Map<Int, Int> =
        distinct().sorted().withIndex().associate { it.value to it.index + 1 }

    fun List<Int>.compressedReverse(): Map<Int, Int> =
        distinct().sorted().withIndex().associate { it.index + 1 to it.value }

    override fun partTwo(input: String): Int {
        val tiles = parseInput(input)
        val xToCompressedX = tiles.map { it.x }.compressed()
        val yToCompressedY = tiles.map { it.y }.compressed()
        val compressedYtoY = tiles.map { it.y }.compressedReverse()
        val compressedXtoX = tiles.map { it.x }.compressedReverse()

        val cTiles = tiles.map { Coordinate2D(xToCompressedX.getValue(it.x), yToCompressedY.getValue(it.y)) }

        val cGrid = Grid(xToCompressedX.maxOf { it.value }, yToCompressedY.maxOf { it.value }, ".")
            .padded(1, ".")
        cTiles.forEachIndexed { index, tile ->
            cGrid.set(tile, "R")
        }

        for (i in cTiles.indices) {
            val tile = cTiles[i]
            val prev = if(i == 0) cTiles.last() else cTiles[i-1]
            val coordsToSet = if(tile.x == prev.x) {
                // vertical
                val ys = listOf(tile.y, prev.y).sorted()
                (ys.first() + 1 until ys.last()).map {
                    Coordinate2D(tile.x, it)
                }
            } else {
                // horizontal
                val xs = listOf(tile.x, prev.x).sorted()
                (xs.first() + 1 until xs.last()).map {
                    Coordinate2D(it, tile.y)
                }
            }
            for (c in coordsToSet) {
                cGrid.set(c, "G")
            }
        }

        // Flood fill outside
        val stack = ArrayDeque<Coordinate2D>(listOf(Coordinate2D.origin()))
        cGrid.set(0, 0, " ")
        while (stack.isNotEmpty()) {
            val c = stack.removeFirst()
            for(n in listOf(c.down(), c.up(), c.left(), c.right())) {
                val nValue = cGrid.at(n)
                if (nValue != null && !"GR ".contains(nValue)) {
                    cGrid.set(n, " ")
                    stack.addLast(n)
                }
            }
        }

        // Check all possible areas
        var maxArea = -1
        for(i in cTiles.indices) {
            areaCheck@ for(j in i + 1 until cTiles.size) {
                val tile = cTiles[i]
                val other = cTiles[j]
                val area = (abs(compressedXtoX.getValue(tile.x) - compressedXtoX.getValue(other.x)) + 1) * (abs(compressedYtoY.getValue(tile.y) - compressedYtoY.getValue(other.y)) + 1)
                if(area > maxArea) {
                    // Check if all green
                    val (minX, maxX) = listOf(tile.x, other.x).sorted()
                    val (minY, maxY) = listOf(tile.y, other.y).sorted()
                    for(x in minX .. maxX)
                        for(y in minY .. maxY) {
                            if(cGrid.at(x, y) == " ")
                            // Continue to next area check if there is a " " tile
                                continue@areaCheck
                        }

                    maxArea = area
                }
            }
        }
        println("ANSWER: $maxArea")
        return maxArea
    }
}
