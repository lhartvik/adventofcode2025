import java.io.File

//val filename = "input2_example.txt"
val filename = "input2.txt"

fun main() {
    val lines = File(filename).readLines()
    var score = 0L
    lines[0].split(",").forEach { s ->
        println(s)
        val start = s.split("-").first()
        val end = s.split("-").last()
        for (i in start.toLong()..end.toLong()){
            val istr = i.toString()
            var found = false
            for (j in 1..istr.length/2) {
                if (istr.length % j == 0) {
                    val segments = istr.chunked(j)
                    if (segments.distinct().size == 1) {
                        score += i
                        println("Found $istr")
                        found = true
                        break
                    }
                }
            }
            if (!found) continue
        }
    }
    println("Score: $score")
}