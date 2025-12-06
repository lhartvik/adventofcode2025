import java.io.File

fun main() {
    calculateFile("input_example.txt")
}

fun calculateFile(filename: String) {
    val lines = File(filename).readLines()

    var posisjon = 50
    var poeng = 0

    println(posisjon)
    for (line in lines) {
        val operation = parse(line)
        val start = posisjon
        when (operation.first) {
            Direction.L -> {
                posisjon -= operation.second % 100
                poeng += operation.second / 100
            }
            Direction.R -> {
                posisjon += operation.second % 100
                poeng += operation.second / 100
            }
        }

        if (posisjon == 0) poeng++

        if (posisjon < 0) {
            posisjon += 100
            if (start != 0) poeng++
        }

        if (posisjon >= 100) {
            posisjon -= 100
            poeng++
        }


        println("${operation.second} ${operation.first} : $posisjon, $poeng" )

    }
    println("Final position for $filename: $posisjon")
    println(poeng)

}
fun parse(line: String): Pair<Direction, Int> {
    val direction = Direction.valueOf(line.first().toString())
    val amount = line.drop(1).toIntOrNull() ?: error("Invalid input2.txt: $line")
    return Pair(direction, amount)
}

enum class Direction {
    L, R
}