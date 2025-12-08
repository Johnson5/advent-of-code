package aockt.y2025

import io.github.jadarma.aockt.core.Solution
import utils.Coordinate3D
import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.collections.component3

object Y2025D08 : Solution {
    private fun parseInput(input: String): List<Coordinate3D> = input.lines().map {
        val (x, y, z) = it.split(',').map {s -> s.trim().toInt() }
        Coordinate3D(x, y, z)
    }

    override fun partOne(input: String): Int {
        val points = parseInput(input)
        val dict = mutableMapOf<Pair<Coordinate3D, Coordinate3D>, Double>()

        for (point in points) {
            for (other in points) {
                if (point != other) {
                    val (first, second) = listOf(
                        point, other
                    ).sortedWith(compareBy<Coordinate3D> { it.x }.thenBy { it.y }.thenBy { it.z })
                    val pair = Pair(first,second)
                    dict.getOrPut(pair) { first.distanceTo(second) }
                }
            }
        }

        val sorted = dict.entries
            .sortedBy { it.value }
            .map { it.key }
            .take(1000)

        val circuits = mutableMapOf<Int, MutableSet<Coordinate3D>>()
        var nextId = 0
        for((first, second) in sorted) {
            var firstId = -1
            var secondId = -1
            for (c in circuits) {
                if (c.value.contains(first)) {
                    firstId = c.key
                }
                if(c.value.contains(second)) {
                    secondId = c.key
                }
            }
            if(firstId > -1 && secondId == -1) {
                circuits[firstId]?.add(second)
            } else if (firstId == -1 && secondId > -1) {
                circuits[secondId]?.add(first)
            } else if (firstId > -1 && secondId > -1 && firstId != secondId) {
                circuits[firstId]?.addAll(circuits[secondId]!!)
                circuits[secondId] = mutableSetOf()
            } else if (firstId == -1 && secondId == -1) {
                circuits[nextId] = mutableSetOf(first, second)
                nextId ++
            }
        }
        return circuits
            .values
            .map { it.size }
            .sortedDescending()
            .take(3)
            .reduce { acc, i -> acc * i }
    }

    override fun partTwo(input: String): Int {
        val points = parseInput(input)
        val dict = mutableMapOf<Pair<Coordinate3D, Coordinate3D>, Double>()

        for (point in points) {
            for (other in points) {
                if (point != other) {
                    val (first, second) = listOf(
                        point, other
                    ).sortedWith(compareBy<Coordinate3D> { it.x }.thenBy { it.y }.thenBy { it.z })
                    val pair = Pair(first,second)
                    dict.getOrPut(pair) { first.distanceTo(second) }
                }
            }
        }

        val sorted = dict.entries
            .sortedBy { it.value }
            .map { it.key }

        val circuits = mutableMapOf<Int, MutableSet<Coordinate3D>>()
        var nextId = 0
        var lastTwo = Pair(Coordinate3D(-1, -1, -1), Coordinate3D(0, 0, 0))
        for((first, second) in sorted) {
            var firstId = -1
            var secondId = -1
            for (c in circuits) {
                if (c.value.contains(first)) {
                    firstId = c.key
                }
                if(c.value.contains(second)) {
                    secondId = c.key
                }
            }
            if(firstId > -1 && secondId == -1) {
                circuits[firstId]?.add(second)
            } else if (firstId == -1 && secondId > -1) {
                circuits[secondId]?.add(first)
            } else if (firstId > -1 && secondId > -1 && firstId != secondId) {
                circuits[firstId]?.addAll(circuits[secondId]!!)
                circuits[secondId] = mutableSetOf()
            } else if (firstId == -1 && secondId == -1) {
                circuits[nextId] = mutableSetOf(first, second)
                nextId ++
            }
            lastTwo = Pair(first,second)
            if(circuits.values.maxOf {it.size} == points.size) {
                break
            }
        }

        return lastTwo.first.x * lastTwo.second.x
    }
}
