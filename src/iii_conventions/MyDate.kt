package iii_conventions

fun upperBoundedDaysSinceJesusOurLordAndSaviourWasBord(date: MyDate) : Int {
    return date.dayOfMonth -1 + (date.month -1 ) * 31 + date.year * 365
}

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        return upperBoundedDaysSinceJesusOurLordAndSaviourWasBord(this) - upperBoundedDaysSinceJesusOurLordAndSaviourWasBord(other)
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange {
    return DateRange(start = this, endInclusive = other)
}

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterable<MyDate>{
    operator fun contains(date : MyDate) = start <= date && date <= endInclusive
    override fun iterator(): Iterator<MyDate> = object : Iterator<MyDate> {
        var it : MyDate = start
        override fun hasNext(): Boolean = it <= endInclusive
        override fun next(): MyDate {
            val current = it
            it = it.nextDay()
            return current
        }

    }
}
