package aockt.y2025

import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec

@AdventDay(2025, 6, "Trash Compactor")
class Y2025D06Test : AdventSpec<Y2025D06>({
    partOne {
        """
        123 328  51 64 
         45 64  387 23 
          6 98  215 314
        *   +   *   +  
    """.trimIndent() shouldOutput 4277556
    }

    partTwo {
        """
        123 328  51 64
         45 64  387 23
          6 98  215 314
        *   +   *   +
    """.trimIndent() shouldOutput 3263827

        """
        1 2 3 4
        + * + *
    """.trimIndent() shouldOutput 10

        """
         1   4
         2 8 1
         3 9 77
         + * +
    """.trimIndent() shouldOutput 636
    }

//    debug {
//        solution.partTwo(input =
//            "123 328  51 64 \n" +
//            " 45 64  387 23 \n" +
//            "  6 98  215 314\n" +
//            "*   +   *   +  "
//        )
//    }
})
