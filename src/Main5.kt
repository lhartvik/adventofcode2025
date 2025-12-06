import java.io.File

fun main(){
    val lines = File("example5.txt").readLines()

    val freshranges = lines.filter { it.contains("-")}.map { Range (it) }
    val products = lines.filter { !it.contains("-") && it.isNotBlank()}.map { it.toLong() }
    val freshproducts = products.filter{ p -> freshranges.any { r -> r.contains(p) } }

    val mergedRanges = mergeRanges(freshranges)


    println("Fresh products: ${freshproducts.size}")

    val totalFreshCount = mergedRanges.sumOf { it.count() }

    println("Total fresh count: $totalFreshCount")
}

fun mergeRanges(ranges: List<Range>): List<Range> {
    if (ranges.isEmpty()) return emptyList()
    val sortedRanges = ranges.sortedBy { it.start }
    val mergedRanges = mutableListOf<Range>()
    var currentRange = sortedRanges[0]

    for (i in 1 until sortedRanges.size) {
        val nextRange = sortedRanges[i]
        if (currentRange.overlaps(nextRange)) {
            currentRange = Range(
                start = minOf(currentRange.start, nextRange.start),
                end = maxOf(currentRange.end, nextRange.end)
            )
        } else {
            mergedRanges.add(currentRange)
            currentRange = nextRange
        }
    }
    mergedRanges.add(currentRange)
    return mergedRanges
}

class Range(val start: Long, val end: Long) {
    constructor(line : String) : this(
        line.split("-")[0].toLong(),
        line.split("-")[1].toLong()
    ) {
        if (start > end) error("Invalid range: $line")
    }

    fun contains(number: Long): Boolean {
        return number in start..end
    }

    fun overlaps(range: Range): Boolean {
        return !(range.end < this.start || range.start > this.end)
    }

    fun count(): Long {
        return end - start + 1
    }

    override fun toString(): String {
        return "[$start-$end]"
    }
}