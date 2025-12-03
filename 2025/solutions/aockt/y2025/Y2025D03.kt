package aockt.y2025

import io.github.jadarma.aockt.core.Solution

object Y2025D03 : Solution {
    override fun partOne(input: String): Int =
        input.lines().sumOf { bank ->
            val firstDigit = bank.dropLast(1).max()
            val secondDigit = bank.drop(bank.indexOf(firstDigit) + 1).max()
            firstDigit.digitToInt() * 10 + secondDigit.digitToInt()
        }

    override fun partTwo(input: String): Long =
        input.lines().sumOf { bank ->
            var str = ""
            var start = 0
            var end = bank.length - 12
            while(str.length < 12) {
                val substring = bank.substring(start, end+ 1)
                val maxInSubstring = substring.max()
                start+= substring.indexOf(maxInSubstring) + 1
                str += maxInSubstring
                end = bank.length - (12 - str.length)
            }
            str.toLong()
    }

}

object Y2025D03B : Solution {
    override fun partTwo(input: String): Long =
        input.lines().sumOf { bank ->
            generateSequence(
                Triple(0, bank.length - 12, "")
            ) { (start, end, result) ->
                if (result.length == 12) return@generateSequence null

                val substring = bank.substring(start .. end)
                val maxChar = substring.max()
                val newStart = start + substring.indexOf(maxChar) + 1
                val newEnd = bank.length - (12 - result.length) + 1

                Triple(newStart, newEnd, result + maxChar)
            }
                .last()
                .third
                .toLong()
        }
}