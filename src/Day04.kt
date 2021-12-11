fun main() {
    fun part1(input: List<String>): Int {
        val callNumbers = input[0].split(",").map { it.toInt() }
        val inputBoards = input
            .drop(1)
            .chunked(6)
            .map { board ->
                board.filter { line -> line.isNotBlank() }
                    .map { line ->
                        line.split(" ")
                            .filter { num -> num.isNotBlank() }
                            .map { num -> num.toInt() }
                    }
            }

        val boards: List<Board> = inputBoards.map { Board.from(it) }

        for (called in callNumbers) {
            boards.forEach { board ->
                board.mark(called)
                if (board.isBingo()) return called * board.remainTotal()
            }
        }

        return 1
    }

    fun part2(input: List<String>): Int {
        val callNumbers = input[0].split(",").map { it.toInt() }
        val inputBoards = input
            .drop(1)
            .chunked(6)
            .map { board ->
                board.filter { line -> line.isNotBlank() }
                    .map { line ->
                        line.split(" ")
                            .filter { num -> num.isNotBlank() }
                            .map { num -> num.toInt() }
                    }
            }

        var boards: List<Board> = inputBoards.map { Board.from(it) }

        for (called in callNumbers) {
            boards.forEach { board ->
                board.mark(called)
                if (board.isBingo()) {
                    if (boards.size == 1) {
                        return called * board.remainTotal()
                    } else {
                        boards = boards - board
                    }
                }
            }
        }

        return 1
    }

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}

class Board(private val rows: List<MutableList<Field>>) {

    companion object {
        fun from(input: List<List<Int>>): Board {
            return Board(input.map { row -> row.map { num -> Field(num, false) }.toMutableList() })
        }
    }

    fun remainTotal(): Int {
        return rows.sumOf { row -> row.filterNot { it.marked }.sumOf { it.value } }
    }

    fun mark(calledNumber: Int) {
        rows.forEach { row ->
            row.replaceAll { field ->
                if (field.value == calledNumber) field.copy(marked = true) else field
            }
        }
    }

    fun isBingo(): Boolean = hasBingoRow() || hasBingoColumn()

    private fun hasBingoRow(): Boolean {
        return rows.any { row ->
            row.all { field -> field.marked }
        }
    }

    private fun hasBingoColumn(): Boolean {
        for (columnIdx in rows.first().indices) {
            var isBingo = true
            for (rowIdx in rows.indices) {
                if (!rows[rowIdx][columnIdx].marked) {
                    isBingo = false
                }
            }
            if (isBingo) return true
        }
        return false
    }
}

data class Field(val value: Int, val marked: Boolean)



