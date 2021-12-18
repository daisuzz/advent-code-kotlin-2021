fun main() {
    fun part1(input: List<List<String>>): Int {
        val entries: List<Entry> = input.map { line ->
            val (signals, outputs) = line
            Entry(signals.split(" "), outputs.split(" "))
        }

        val uniqueSevenDigits = listOf(
            SevenDigit.ONE.segment.size,
            SevenDigit.SEVEN.segment.size,
            SevenDigit.FOUR.segment.size,
            SevenDigit.EIGHT.segment.size
        )

        return entries.sumOf { entry ->
            entry.outputs.count { output ->
                uniqueSevenDigits.contains(output.length)
            }
        }
    }

    fun part2(input: List<List<String>>): Int {
        val entries: List<Entry> = input.map { line ->
            val (signals, outputs) = line
            Entry(signals.split(" "), outputs.split(" "))
        }

        val segmentMap = mapOf(
            setOf('a', 'b', 'c', 'e', 'f', 'g') to 0,
            setOf('c', 'f') to 1,
            setOf('a', 'c', 'd', 'e', 'g') to 2,
            setOf('a', 'c', 'd', 'f', 'g') to 3,
            setOf('b', 'c', 'd', 'f') to 4,
            setOf('a', 'b', 'd', 'f', 'g') to 5,
            setOf('a', 'b', 'd', 'e', 'f', 'g') to 6,
            setOf('a', 'c', 'f') to 7,
            setOf('a', 'b', 'c', 'd', 'e', 'f', 'g') to 8,
            setOf('a', 'b', 'c', 'd', 'f', 'g') to 9,
        )

        return entries.sumOf { entry ->
            val one = entry.signals.find { signal -> signal.length == SevenDigit.ONE.segment.size }!!
            val four = entry.signals.find { signal -> signal.length == SevenDigit.FOUR.segment.size }!!
            val seven = entry.signals.find { signal -> signal.length == SevenDigit.SEVEN.segment.size }!!
            val eight = entry.signals.find { signal -> signal.length == SevenDigit.EIGHT.segment.size }!!

            val three = entry.signals.find { signal ->
                signal.length == SevenDigit.THREE.segment.size
                        && signal.toSet().containsAll(one.toSet())
            }!!

            val nine = entry.signals.find { signal ->
                signal.length == SevenDigit.NINE.segment.size
                        && signal.toSet().containsAll(three.toSet())
            }!!

            val zero = entry.signals.find { signal ->
                signal.length == SevenDigit.ZERO.segment.size
                        && signal.toSet().containsAll(seven.toSet())
                        && signal != nine
            }!!

            val six = entry.signals.find { signal ->
                signal.length == SevenDigit.SIX.segment.size
                        && signal != zero
                        && signal != nine
            }!!

            val five = entry.signals.find { signal ->
                signal.length == SevenDigit.FIVE.segment.size
                        && six.toSet().containsAll(signal.toSet())
            }!!

            val two = entry.signals.find { signal ->
                signal.length == SevenDigit.TWO.segment.size
                        && signal != three
                        && signal != five
            }!!

            val a = seven.toSet() - one.toSet()
            val bd = four.toSet() - one.toSet()
            val g = nine.toSet() - four.toSet() - a
            val d = three.toSet() - one.toSet() - a - g
            val b = bd - d
            val c = eight.toSet() - six.toSet()
            val f = one.toSet() - c
            val e = eight.toSet() - nine.toSet()

            val charMap = mapOf(
                a.first() to 'a',
                b.first() to 'b',
                c.first() to 'c',
                d.first() to 'd',
                e.first() to 'e',
                f.first() to 'f',
                g.first() to 'g'
            )

            var digit = 1000
            var sum = 0
            entry.outputs.forEach { output ->
                val translated = output.map { it -> charMap[it]!! }.toSet()
                sum += segmentMap[translated]!! * digit
                digit /= 10
            }
            sum
        }
    }

    val input = readInput("Day08").map { it.split(" | ") }
    println(part1(input))
    println(part2(input))
}

data class Entry(val signals: List<String>, val outputs: List<String>)

enum class SevenDigit(val segment: List<Char>) {
    ZERO(listOf('a', 'b', 'c', 'e', 'f', 'g')),
    ONE(listOf('c', 'f')),
    TWO(listOf('a', 'c', 'd', 'e', 'g')),
    THREE(listOf('a', 'c', 'd', 'f', 'g')),
    FOUR(listOf('b', 'c', 'd', 'f')),
    FIVE(listOf('a', 'b', 'd', 'f', 'g')),
    SIX(listOf('a', 'b', 'd', 'e', 'f', 'g')),
    SEVEN(listOf('a', 'c', 'd')),
    EIGHT(listOf('a', 'b', 'c', 'd', 'e', 'f', 'g')),
    NINE(listOf('a', 'b', 'c', 'd', 'f', 'g'))
}


