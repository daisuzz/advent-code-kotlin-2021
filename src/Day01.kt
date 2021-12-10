fun main() {
    fun part1(input: List<Int>): Int {
        return input
            .windowed(2)
            .count { (a, b) -> b > a }
    }

    fun part2(input: List<Int>): Int {
        return input
            .windowed(3) { it.sum() }
            .windowed(2)
            .count { (a, b) -> b > a }
    }

    val input = readInput("Day01").map { it.toInt() }
    println(part1(input))
    println(part2(input))
}
