package utils

class Grid<T> : Iterable<List<T>> {
    private val grid: MutableList<MutableList<T>>
    val width: Int get() = grid.firstOrNull()?.size ?: 0
    val height: Int get() = grid.size

    constructor(x: Int, y: Int, initialValue: T) {
        grid = MutableList(y) { MutableList(x) { initialValue } }
    }

    constructor(lines: List<String>, converter: (Char) -> T) {
        grid = lines.map { row -> row.map(converter).toMutableList() }.toMutableList()
    }

    private constructor(existing: MutableList<MutableList<T>>) {
        grid = existing
    }

    override fun toString() = grid.joinToString("\n") { it.joinToString("") }

    override fun iterator(): Iterator<List<T>> = grid.iterator()

    fun toPrettyString(
        columnSeparator: String = " ",
        rowSeparator: String? = null
    ): String {
        if (height == 0 || width == 0) return ""

        val columnWidths = (0 until width).map { col ->
            grid.maxOf { row -> row[col].toString().length }
        }

        fun formatRow(row: List<T>): String =
            row.mapIndexed { col, value ->
                value.toString().padStart(columnWidths[col])
            }.joinToString(columnSeparator)

        val formattedRows = grid.map(::formatRow)

        if (rowSeparator == null) return formattedRows.joinToString("\n")

        val divider = columnWidths.joinToString(columnSeparator) { width ->
            rowSeparator.repeat(width)
        }

        return formattedRows.joinToString("\n$divider\n")
    }

    fun padded(border: Int = 1, with: T): Grid<T> {
        val newHeight = height + border * 2
        val newWidth = width + border * 2

        val newGrid = MutableList(newHeight) { y ->
            MutableList(newWidth) { x ->
                val origX = x - border
                val origY = y - border
                grid.getOrNull(origY)?.getOrNull(origX) ?: with
            }
        }

        return Grid(newGrid)
    }


    fun set(coordinate: Coordinate2D, value: T) {
        if (isInBounds(coordinate)) {
            grid[coordinate.y][coordinate.x] = value
        } else {
            throw IndexOutOfBoundsException("Coordinate is not in bounds of this grid: $coordinate")
        }
    }

    fun at(x: Int, y: Int): T? = grid.getOrNull(y)?.getOrNull(x)

    fun at(c: Coordinate2D): T? = at(c.x, c.y)

    fun atOrDefault(c: Coordinate2D, default: T): T = at(c.x, c.y) ?: default


    fun coordinatesWhere(predicate: (T) -> Boolean): List<Coordinate2D> {
        return grid.flatMapIndexed { y, row ->
            row.mapIndexedNotNull { x, i ->
                if (predicate(i)) Coordinate2D(
                    x,
                    y
                ) else null
            }
        }
    }

    fun getRow(rowIndex: Int): List<T> = grid[rowIndex]

    fun getColumn(columnIndex: Int): List<T> =
        grid.map { row -> row[columnIndex] }

    fun getColumn(columnIndex: Int, paddedWith: T): List<T> =
        grid.map { row -> row.elementAtOrElse(columnIndex) { paddedWith } }

    fun isInBounds(coordinate: Coordinate2D): Boolean =
        grid.getOrNull(coordinate.y)?.getOrNull(coordinate.x) != null

    fun isInBounds(x: Int, y: Int): Boolean =
        grid.getOrNull(y)?.getOrNull(x) != null

    fun indices(): List<Coordinate2D> =
        grid.flatMapIndexed { y, row ->
            row.indices.map { x -> Coordinate2D(x, y) }
        }

}