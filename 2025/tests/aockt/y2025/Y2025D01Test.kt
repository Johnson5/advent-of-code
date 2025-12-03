package aockt.y2025

import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec

@AdventDay(2025, 1, "Magic Numbers")
class Y2025D01Test : AdventSpec<Y2025D01>({
    partOne {
        "L68\n" +
                "L30\n" +
                "R48\n" +
                "L5\n" +
                "R60\n" +
                "L55\n" +
                "L1\n" +
                "L99\n" +
                "R14\n" +
                "L82" shouldOutput 3
    }

    partTwo {
        "L50\nR200" shouldOutput 3

        "L68\n" +
                "L30\n" +
                "R48\n" +
                "L5\n" +
                "R60\n" +
                "L55\n" +
                "L1\n" +
                "L99\n" +
                "R14\n" +
                "L82" shouldOutput 6

    }
})
