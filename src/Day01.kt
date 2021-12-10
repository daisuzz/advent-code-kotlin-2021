fun main() {
    fun part1(input: List<String>): Int {
        var cnt = 0
        (1 until input.size).forEach {
            if (input[it].toInt() - input[it - 1].toInt() > 0) cnt++
        }
        return cnt
    }

    fun part2(input: List<String>): Int {
        var cnt = 0
        (3 until input.size).forEach {
            if (input[it].toInt() > input[it - 3].toInt()) cnt++
        }
        return cnt
    }

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
