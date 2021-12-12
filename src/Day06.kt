fun main() {
    fun part1(input: List<String>): Int {
        val fishList = input[0]
            .split(",")
            .map { LanternFish(it.toInt()) }
            .toMutableList()

        repeat(80) {
            val newFishList = mutableListOf<LanternFish>()
            fishList.forEach { fish ->
                if (fish.canCreateFish()) {
                    val newFish = fish.createFish()
                    newFishList.add(newFish)
                } else {
                    fish.countDown()
                }
            }
            fishList += newFishList
        }

        return fishList.size
    }

    fun part2(input: List<String>): Long {

        // part1と同じ方法でやるとOOMが発生するので、key: timer, value: lanternFishの数(Long) を持ったMapを作成
        var fishMap = input[0]
            .split(",")
            .groupingBy { it.toInt() }
            .eachCount()
            .map { it.key to it.value.toLong() }
            .toMap()

        repeat(256) {
            val newFishMap = mutableMapOf<Int, Long>()
            for (i in 0..8) {
                when (i) {
                    0 -> {
                        newFishMap[8] = fishMap.getOrDefault(i, 0)
                        newFishMap[6] = fishMap.getOrDefault(i, 0)
                    }
                    else -> {
                        newFishMap[i - 1] = newFishMap.getOrDefault(i - 1, 0) + fishMap.getOrDefault(i, 0)
                    }
                }
            }
            fishMap = newFishMap
        }

        return fishMap.map { it.value }.sum()
    }

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}

class LanternFish(var timer: Int) {

    fun countDown() {
        require(timer != 0)
        timer--
    }

    fun canCreateFish(): Boolean {
        return timer == 0
    }

    fun createFish(): LanternFish {
        require(timer == 0)
        timer = 6
        return LanternFish(8)
    }
}
