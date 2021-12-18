fun main() {
    fun part1(input: List<Int>): Int {

        val min = input.minOrNull()!!
        val max = input.maxOrNull()!!

        val cost = (min..max).map { position ->
            input.sumOf { kotlin.math.abs(position - it) }
        }

        return cost.minOrNull()!!
    }

    fun part2(input: List<Int>): Int {

        val min = input.minOrNull()!!
        val max = input.maxOrNull()!!

        val cost = (min..max).map { position ->
            input.sumOf {
                val n = kotlin.math.abs(position - it)
                n * (n + 1) / 2
            }
        }

        return cost.minOrNull()!!
    }

    val input = readInput("Day07").first().split(",").map { it.toInt() }
    println(part1(input))
    println(part2(input))
}

