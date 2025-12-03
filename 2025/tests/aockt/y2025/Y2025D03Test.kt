package aockt.y2025

import io.github.jadarma.aockt.core.Solution
import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec

@AdventDay(2025, 3, "Lobby")
class Y2025D03Test : Y2025D03Spec<Y2025D03>()

@AdventDay(2025, 3, "Lobby", "With sequence")
class Y2025D03BTest : Y2025D03Spec<Y2025D03B>()

    abstract class Y2025D03Spec<T : Solution> : AdventSpec<T>({
    partOne {
        "987654321111111\n" +
                "811111111111119\n" +
                "234234234234278\n" +
                "818181911112111" shouldOutput 357
    }

    partTwo {
        "987654321111111\n" +
                "811111111111119\n" +
                "234234234234278\n" +
                "818181911112111" shouldOutput 3121910778619
    }
})