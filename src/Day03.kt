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

        var oxyGenCandidateList = input
        var oxyDigit = 0

        while (oxyGenCandidateList.size > 1) {

            val frequency = oxyGenCandidateList.groupingBy { it[oxyDigit] }.eachCount()
            val mostFrequencyChar = if (frequency.getOrDefault('1', 0) >= frequency.getOrDefault('0', 0)) '1' else '0'

            oxyGenCandidateList = oxyGenCandidateList.filter { it[oxyDigit] == mostFrequencyChar }

            oxyDigit++
        }

        var co2CandidateList = input
        var co2Digit = 0

        while (co2CandidateList.size > 1) {

            val frequency = co2CandidateList.groupingBy { it[co2Digit] }.eachCount()
            val mostFrequencyChar = if (frequency.getOrDefault('1', 0) >= frequency.getOrDefault('0', 0)) '0' else '1'

            co2CandidateList = co2CandidateList.filter { it[co2Digit] == mostFrequencyChar }

            co2Digit++
        }

        val oxygenGeneratorRating = oxyGenCandidateList.first().joinToString("").toInt(2)
        val co2ScrubberRating = co2CandidateList.first().joinToString("").toInt(2)

        return oxygenGeneratorRating * co2ScrubberRating
    }

    val input = readInput("Day03").map { it.toCharArray() }
    println(part1(input))
    println(part2(input))
}
