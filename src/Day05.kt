fun main() {
    fun part1(input: List<String>): Int {
        var max = 0
        val vents: List<LineOfVents> = input.map { line ->
            val xy = line.split(" -> ")
            val (x1, y1) = xy[0].split(",").map { it.toInt() }
            val (x2, y2) = xy[1].split(",").map { it.toInt() }
            max = maxOf(x1, y1, x2, y2, max)
            LineOfVents(Source(x1, y1), Destination(x2, y2))
        }
        val diagram = Array(max + 1) { Array(max + 1) { 0 } }

        vents
            .filter { lineOfVents -> lineOfVents.isHorizontal() || lineOfVents.isVertical() }
            .forEach { lineOfVents ->
                val (x1, y1) = lineOfVents.source
                val (x2, y2) = lineOfVents.destination

                if (x1 == x2) {
                    if (y1 > y2) {
                        (y2..y1).forEach {
                            diagram[x1][it]++
                        }
                    } else {
                        (y1..y2).forEach {
                            diagram[x1][it]++
                        }
                    }
                }

                if (y1 == y2) {
                    if (x1 > x2) {
                        (x2..x1).forEach {
                            diagram[it][y1]++
                        }
                    } else {
                        (x1..x2).forEach {
                            diagram[it][y1]++
                        }
                    }
                }
            }

        return diagram.sumOf { row -> row.count { it > 1 } }
    }

    fun part2(input: List<String>): Int {
        var max = 0
        val vents: List<LineOfVents> = input.map { line ->
            val xy = line.split(" -> ")
            val (x1, y1) = xy[0].split(",").map { it.toInt() }
            val (x2, y2) = xy[1].split(",").map { it.toInt() }
            max = maxOf(x1, y1, x2, y2, max)
            LineOfVents(Source(x1, y1), Destination(x2, y2))
        }
        val diagram = Array(max + 1) { Array(max + 1) { 0 } }

        vents
            .forEach { lineOfVents ->
                val (x1, y1) = lineOfVents.source
                val (x2, y2) = lineOfVents.destination

                if (lineOfVents.isVertical()) { // vertical
                    if (y1 > y2) {
                        (y2..y1).forEach {
                            diagram[x1][it]++
                        }
                    } else {
                        (y1..y2).forEach {
                            diagram[x1][it]++
                        }
                    }
                } else if (lineOfVents.isHorizontal()) { // horizontal
                    if (x1 > x2) {
                        (x2..x1).forEach {
                            diagram[it][y1]++
                        }
                    } else {
                        (x1..x2).forEach {
                            diagram[it][y1]++
                        }
                    }
                } else { // diagonal
                    if (x2 > x1 && y2 > y1) {
                        (0..x2 - x1).forEach {
                            diagram[x1 + it][y1 + it]++
                        }
                    }
                    if (x1 > x2 && y1 > y2) {
                        (0..x1 - x2).forEach {
                            diagram[x2 + it][y2 + it]++
                        }
                    }
                    if (x2 > x1 && y1 > y2) {
                        (0..x2 - x1).forEach {
                            diagram[x1 + it][y1 - it]++
                        }
                    }
                    if (x1 > x2 && y2 > y1) {
                        (0..x1 - x2).forEach {
                            diagram[x1 - it][y1 + it]++
                        }
                    }
                }
            }

        return diagram.sumOf { row -> row.count { it > 1 } }
    }

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}

data class Source(val x: Int, val y: Int)

data class Destination(val x: Int, val y: Int)

data class LineOfVents(val source: Source, val destination: Destination) {

    fun isVertical(): Boolean {
        return source.x == destination.x
    }

    fun isHorizontal(): Boolean {
        return source.y == destination.y
    }
}

