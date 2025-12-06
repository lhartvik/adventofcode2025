import java.io.File

fun main() {
    readFile("example6.txt")
    //readFile("input6.txt")
}

fun readFile(fileName: String) {
    val rows = File(fileName).readLines().map { line ->
        line.toCharArray()
    }

    var currentOperator = ' '
    var answer = 0L
    val numbers = mutableListOf<Long>()
    for ((x, col) in rows[0].withIndex()) {
        val operator = rows.last()[x]
        if (operator != ' ') {
            currentOperator = operator
        }
        var number = 0
        if (rows[0][x] != ' ' || rows[1][x] != ' ' || rows[2][x] != ' ' || rows[3][x] != ' ') {
            for ((y, row) in rows.subList(0, rows.size - 1).withIndex()) {
                if (rows[y][x] != ' ') {
                    number = number * 10 + rows[y][x].code - '0'.code
                }
            }
            numbers += number.toLong()
            println("$number skal $currentOperator")
        } else {
            println("$numbers skal $currentOperator")
            when (currentOperator) {
                '+' -> {
                    val sum = numbers.sum()
                    println("Sum: $sum")
                    answer += sum
                }
                '*' -> {
                    val product = numbers.fold (1, Long::times)
                    println("Produkt: $product")
                    answer += product
                }
                else -> error("Ukjent operator: $operator")
            }
            numbers.clear()
        }
    }
    println("Answer: $answer")
}


