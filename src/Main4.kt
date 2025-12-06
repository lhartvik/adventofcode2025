import java.io.File

fun main() {
    val floor: List<List<Int>> = File("example4.txt").readLines()
        .map { it.toList().map { if (it == '@') 1 else 0 }}

    floor.forEach { println(it) }
    val scorefloor: List<MutableList<Int>> = List(floor.size) { MutableList(floor[0].size) { 0 } }
    for ((index, x) in floor.withIndex()) {
        for ((jndex) in x.withIndex()) {
            val sum = filter(index, jndex, floor)
            scorefloor[index][jndex] = sum
        }
    }
    println()
    scorefloor.forEach { println(it) }
var totalscore = 0;
    val hitfloor = List(floor.size) { MutableList(floor[0].size) { '?' } }
    for ((index, x) in floor.withIndex()) {
        for ((jndex) in x.withIndex()) {
            if (scorefloor[index][jndex] < 4 && floor[index][jndex] == 1) totalscore++
        }
    }
    print(recursiveRemovePaper(0, floor))
}

fun recursiveRemovePaper(scoreSoFar: Long, floor: List<List<Int>>): Long {
    var nextScore = scoreSoFar
    val scorefloor: List<MutableList<Int>> = List(floor.size) { MutableList(floor[0].size) { 0 } }
    for ((index, x) in floor.withIndex()) {
        for ((jndex) in x.withIndex()) {
            val sum = filter(index, jndex, floor)
            scorefloor[index][jndex] = sum
        }
    }
    val showFloor: List<MutableList<Char>> = List(floor.size) { MutableList(floor[0].size) { '?' } }
    val nextFloor: List<MutableList<Int>> = List(floor.size) { MutableList(floor[0].size) { 0 } }
    for ((index, x) in floor.withIndex()) {
        for ((jndex) in x.withIndex()) {
            if (scorefloor[index][jndex] < 4 && floor[index][jndex] == 1) {
                showFloor[index][jndex] = 'x'
                nextFloor[index][jndex] = 0
                nextScore++
            }
            else {
                showFloor[index][jndex] = if(floor[index][jndex]==1) '@' else '.'
                nextFloor[index][jndex] = floor[index][jndex]
            }
        }
    }
    printFloor(showFloor)
    println(nextScore)
    if (nextScore > scoreSoFar) return recursiveRemovePaper(nextScore, nextFloor)
    else return 0
}

fun filter(x: Int, y:Int, floor:List<List<Int>>):Int {
    var sum = 0;
    if (x < floor.size-1) sum += floor[x+1][y]
    if (x > 0) sum +=  floor[x-1][y]
    if (y < floor[0].size-1) sum += floor[x][y+1]
    if (y > 0) sum += floor[x][y-1]

    if (x < floor.size-1 && y < floor[0].size-1) sum += floor[x+1][y+1]
    if (x > 0 && y < floor[0].size-1) sum += floor[x-1][y+1]
    if (x < floor.size-1 && y > 0) sum += floor[x+1][y-1]
    if (x > 0 && y > 0) sum += floor[x-1][y-1]

    return sum
}

fun <T>printFloor(floor: List<List<T>>) {
    for (line in floor) {
        for (cell in line) {
            print(cell)
        }
        println()
    }
}

fun <T, U>printFloor(hitfloor: List<List<T>>, floor: List<List<U>>) {
    for ((index, line) in hitfloor.withIndex()) {
        for ((jndex, cell) in line.withIndex()) {
            if (cell == 'x') print('x') else
                if ( floor[index][jndex] == 1) print('@') else print('.')
        }
        println()
    }
}