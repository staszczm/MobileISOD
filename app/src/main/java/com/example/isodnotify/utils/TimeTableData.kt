package com.example.isodnotify.utils

import java.util.Date

data class TimeTableData (
    val courseName: String,
    val startTime: Date,
    val endTime: Date,
    val dayOfWeek: Int,
    val building: String,
    val typeOfClasses: String,
    val cycleShort: String, //may not be used
)
