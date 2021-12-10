fun main() {
    fun part1(input: List<Command>): Int {
        var horizontalPosition = 0
        var depth = 0
        input.forEach {
            when (it.action) {
                "forward" -> horizontalPosition += it.unit
                "down" -> depth += it.unit
                "up" -> depth -= it.unit
            }
        }
        return horizontalPosition * depth
    }

    fun part2(input: List<Command>): Int {
        var horizontalPosition = 0
        var depth = 0
        var aim = 0
        input.forEach {
            when (it.action) {
                "forward" -> {
                    horizontalPosition += it.unit
                    depth += aim * it.unit
                }
                "down" -> aim += it.unit
                "up" -> aim -= it.unit
            }
        }
        return horizontalPosition * depth
    }

    val input = readInput("Day02").map {
        val (action, unit) = it.split(" ")
        Command(action, unit.toInt())
    }
    println(part1(input))
    println(part2(input))
}

class Command(val action: String, val unit: Int)
