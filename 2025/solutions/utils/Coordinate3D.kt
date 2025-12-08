package utils

import kotlin.math.sqrt

data class Coordinate3D(val x: Int, val y: Int, val z: Int) {

    companion object {
        fun origin(): Coordinate3D = Coordinate3D(0, 0, 0)
    }

    override fun toString(): String {
        return "($x, $y, $z)"
    }

    fun up(): Coordinate3D = Coordinate3D(x, y - 1, z)
    fun down(): Coordinate3D = Coordinate3D(x, y + 1, z)

    fun left(): Coordinate3D = Coordinate3D(x - 1, y, z)
    fun right(): Coordinate3D = Coordinate3D(x + 1, y, z)

    fun forward(): Coordinate3D = Coordinate3D(x, y, z + 1)
    fun backward(): Coordinate3D = Coordinate3D(x, y, z - 1)

    operator fun plus(other: Coordinate3D): Coordinate3D {
        return Coordinate3D(
            x + other.x,
            y + other.y,
            z + other.z
        )
    }

    operator fun minus(other: Coordinate3D): Coordinate3D {
        return Coordinate3D(
            x - other.x,
            y - other.y,
            z - other.z
        )
    }

    fun distanceTo(other: Coordinate3D): Double {
        val dx = (other.x - x).toDouble()
        val dy = (other.y - y).toDouble()
        val dz = (other.z - z).toDouble()
        return sqrt(dx * dx + dy * dy + dz * dz)
    }
}
