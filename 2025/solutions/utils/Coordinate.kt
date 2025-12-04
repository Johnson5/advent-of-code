package utils

data class Coordinate(val x: Int, val y: Int) {
    companion object {
        fun origin(): Coordinate {
            return Coordinate(0, 0)
        }
    }

    // Override the toString() method to return the format (x, y)
    override fun toString(): String {
        return "($x, $y)"
    }

    fun up(): Coordinate {
        return Coordinate(x, y - 1)
    }

    fun down(): Coordinate {
        return Coordinate(x, y + 1)
    }

    fun left(): Coordinate {
        return Coordinate(x - 1, y)
    }

    fun right(): Coordinate {
        return Coordinate(x + 1, y)
    }

    // Plus operator to add two Coordinates
    operator fun plus(other: Coordinate): Coordinate {
        return Coordinate(this.x + other.x, this.y + other.y)
    }

    // Minus operator to subtract two Coordinates
    operator fun minus(other: Coordinate): Coordinate {
        return Coordinate(this.x - other.x, this.y - other.y)
    }
}