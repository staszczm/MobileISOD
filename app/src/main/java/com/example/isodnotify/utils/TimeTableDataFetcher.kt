package com.example.isodnotify.utils

import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class TimeTableDataFetcher(override val name: String, override val apiKey: String) : JsonDataFetcher<TimeTableData>{
    override fun getQueryParameters(): String {
        return "myplan"
    }

    override fun getJsonArrayName(): String {
        return "planItems"
    }

    private fun convertTo24HourFormat(time: String): String {
        val inputFormat = SimpleDateFormat("hh:mm:ss a", Locale.getDefault())
        val outputFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        val date = inputFormat.parse(time)
        return outputFormat.format(date)
    }

    override fun getImportantInformation(item: JSONObject): TimeTableData {
        val startTime24 = convertTo24HourFormat(item.optString("startTime", null))
        val endTime24 = convertTo24HourFormat(item.optString("endTime", null))

        val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        val startTime = dateFormat.parse(startTime24)
        val endTime = dateFormat.parse(endTime24)

        return TimeTableData(
            item.optString("courseName", null),
            startTime,
            endTime,
            item.optInt("dayOfWeek", 0),
            item.optString("building", null),
            item.optString("typeOfClasses", null),
            item.optString("cycleShort", null)
        )
    }
}
