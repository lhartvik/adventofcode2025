import java.io.File
import kotlin.math.max

fun main() {
    val banks = File("example3.txt").readLines()
    var sum = 0L
    var n = 0
    for (bank in banks) {
        val numbers = bank.toList().map { it.code - '0'.code }
        val svar = recursiveCalculate(0, numbers, 12)
        sum+=svar
        println("$svar, L ${++n}")
    }
    println("Sum: $sum")
}

fun recursiveCalculate(total: Long, rest: List<Int>, numbersLeftToPick: Int): Long {
    if (numbersLeftToPick == 0) {
        return total
    }
    if (numbersLeftToPick > rest.size || rest.isEmpty()) {
        return 0
    }
    val nextNumberToPick = findIndexOfFirstHighestNumberWithAtLeastNNumbersAfterIt(rest, numbersLeftToPick)
    val a = recursiveCalculate(total*10 + rest[nextNumberToPick], rest.subList(nextNumberToPick+1, rest.size), numbersLeftToPick-1)
    val b = recursiveCalculate(total, rest.subList(max(nextNumberToPick,1), rest.size), numbersLeftToPick)
    return max(a,b)
}

fun findIndexOfFirstHighestNumberWithAtLeastNNumbersAfterIt(numbers: List<Int>, numbersToPick: Int): Int {
    if (numbers.size <= numbersToPick) {
        return 0
    }
    val usableNumbers = numbers.reversed().drop(numbersToPick-1)
    val highest = usableNumbers.max()
    val i = numbers.indexOf(highest)
    return i
}
