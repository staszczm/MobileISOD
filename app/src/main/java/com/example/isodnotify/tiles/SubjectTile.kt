package com.example.isodnotify.tiles

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.example.isodnotify.R
import com.example.isodnotify.utils.TimeTableData
import java.text.SimpleDateFormat
import java.util.*

class SubjectTile @JvmOverloads constructor(context: Context, subjectInfo: TimeTableData) : LinearLayout(context){

    private var startTime: TextView
    private var endTime: TextView
    private var subjectName: TextView
    private var buildingName: TextView

    init {
        LayoutInflater.from(context).inflate(R.layout.tile_subject, this, true)

        startTime = findViewById(R.id.startTime)
        endTime = findViewById(R.id.endTime)
        subjectName = findViewById(R.id.subjectName)
        buildingName = findViewById(R.id.buildingName)

        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

        startTime.text = dateFormat.format(subjectInfo.startTime)
        endTime.text = dateFormat.format(subjectInfo.endTime)
        subjectName.text = subjectInfo.courseName
        buildingName.text = subjectInfo.building
    }
}