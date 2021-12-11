fun main() {
    fun part1(input: List<CharArray>): Int {
        val cnt = mutableMapOf<Int, Int>()
        val digits = input.first().size

        input.forEach { row ->
            (0 until digits).forEach {
                if (row[it] == '1') cnt[it] = cnt.getOrPut(it) { 0 } + 1
            }
        }

        val gammaRate = (0 until digits).map {
            if (cnt[it]!! > input.size / 2) 1 else 0
        }.joinToString("")

        val epsilonRate = gammaRate.map { if (it == '0') 1 else 0 }.joinToString("")

        return gammaRate.toInt(2) * epsilonRate.toInt(2)
    }

    fun part2(input: List<CharArray>): Int {

        val oxyGenCandidateList = input.filterByColumn('1', '0')
        val co2CandidateList = input.filterByColumn('0', '1')

        val oxygenGeneratorRating = oxyGenCandidateList.first().joinToString("").toInt(2)
        val co2ScrubberRating = co2CandidateList.first().joinToString("").toInt(2)

        return oxygenGeneratorRating * co2ScrubberRating
    }

    val input = readInput("Day03").map { it.toCharArray() }
    println(part1(input))
    println(part2(input))
}

fun List<CharArray>.filterByColumn(desiredCharOnMostFrequency: Char, other: Char): List<CharArray> {
    var candidateList = this
    var digit = 0
    while (candidateList.size > 1) {
        val frequency = candidateList.groupingBy { it[digit] }.eachCount()
        val char =
            if (frequency.getOrDefault('1', 0) >= frequency.getOrDefault('0', 0)) desiredCharOnMostFrequency else other
        candidateList = candidateList.filter { it[digit] == char }
        digit++
    }
    return candidateList
}
