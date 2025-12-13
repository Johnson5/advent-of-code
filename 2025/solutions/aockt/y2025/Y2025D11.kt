package aockt.y2025

import io.github.jadarma.aockt.core.Solution
import kotlin.math.min

object Y2025D11 : Solution {
    private fun parseInput(input: String): List<Pair<String, List<String>>> =
        input
            .lines()
            .map {
                it.split(" ")
            }
            .map {
                Pair(it.first().dropLast(1), it.drop(1))
            }

    override fun partOne(input: String): Int {
        val map = parseInput(input).associate { it }
        val goal = "out"
        fun searchTree(root: String): Int {
            val children = map[root]
            if(root == goal) return 1
            val found = children?.sumOf { searchTree(it) } ?: 0
            return found
        }
        val result = searchTree("you")
        return result
    }

    override fun partTwo(input: String): Long {
        val map = parseInput(input).associate { it }
        val goal = "out"
        val memo = mutableMapOf<Triple<String,Boolean,Boolean>, Long>()
        fun searchTree(root: String, seenDac: Boolean, seenFft: Boolean): Long {
            val state = Triple(root, seenDac, seenFft)
            val knownAnswer = memo[state]
            if(knownAnswer != null) return knownAnswer
            val nowSeenDac = seenDac || root == "dac"
            val nowSeenFft = seenFft || root == "fft"
            if(root == goal) {
                val ans = if (nowSeenDac && nowSeenFft) 1L else 0L
                memo[state] = ans
                return ans
            }
            val children = map[root]
            val found = children?.sumOf { searchTree(it, nowSeenDac, nowSeenFft) } ?: 0L
            memo[state] = found
            return found
        }
        val result = searchTree("svr", false, false)
        return result
    }
}
