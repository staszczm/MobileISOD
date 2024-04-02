package com.example.isodnotify.utils

import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId

class TimeTableDataSorter {
    fun sortTimeTableData(timeTableData: List<TimeTableData>): HashMap<DayOfWeek, List<TimeTableData>> {
        val sortedData = HashMap<DayOfWeek, List<TimeTableData>>()

        for (day in DayOfWeek.values()) {
            val intValue = day.ordinal + 1
            val dayData = timeTableData.filter { it.dayOfWeek == intValue}
            sortedData[day] = dayData.sortedBy { it.startTime }
        }

        return sortedData
    }


    fun printTimeTableData(timeTableData: HashMap<DayOfWeek, List<TimeTableData>>) {
        for ((day, data) in timeTableData) {
            println("$day: $data")
        }
    }

    fun getNextTwoClasses(sortedData: HashMap<DayOfWeek, List<TimeTableData>>): List<TimeTableData> {
        val now = LocalDateTime.now()
        println("Now: $now")
        val currentDay = DayOfWeek.values().getOrNull(now.dayOfWeek.value-1) ?: return emptyList()
        print("Current day: $currentDay")
        val currentTime = now.toLocalTime()
        println("Current time: $currentTime")

        println("Sorted data: $sortedData[$currentDay]")

        return sortedData[currentDay]
            ?.filter { it.startTime?.toInstant()?.atZone(ZoneId.systemDefault())?.toLocalTime()?.isAfter(currentTime) == true }
            ?.sortedBy { it.startTime }
            ?.take(2)
            ?: emptyList()
    }

    fun printNextTwoClasses(sortedData: HashMap<DayOfWeek, List<TimeTableData>>) {
        val nextTwoClasses = getNextTwoClasses(sortedData)
        println(nextTwoClasses)
    }
}