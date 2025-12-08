package utils

import kotlin.math.sqrt

data class Coordinate2D(val x: Int, val y: Int) {
    companion object {
        fun origin(): Coordinate2D {
            return Coordinate2D(0, 0)
        }
    }

    override fun toString(): String {
        return "($x, $y)"
    }

    fun up(): Coordinate2D {
        return Coordinate2D(x, y - 1)
    }

    fun down(): Coordinate2D {
        return Coordinate2D(x, y + 1)
    }

    fun left(): Coordinate2D {
        return Coordinate2D(x - 1, y)
    }

    fun right(): Coordinate2D {
        return Coordinate2D(x + 1, y)
    }

    operator fun plus(other: Coordinate2D): Coordinate2D {
        return Coordinate2D(this.x + other.x, this.y + other.y)
    }

    operator fun minus(other: Coordinate2D): Coordinate2D {
        return Coordinate2D(this.x - other.x, this.y - other.y)
    }

    fun distanceTo(other: Coordinate2D): Double {
        val dx = (other.x - x).toDouble()
        val dy = (other.y - y).toDouble()
        return sqrt(dx * dx + dy * dy)
    }
}