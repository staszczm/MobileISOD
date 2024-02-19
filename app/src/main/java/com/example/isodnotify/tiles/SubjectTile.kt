package com.example.isodnotify.tiles

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.example.isodnotify.R
import com.example.isodnotify.utils.TimeTableData

class SubjectTile @JvmOverloads constructor(context: Context, private val subjectInfo: TimeTableData) : LinearLayout(context){
    private var startTime: TextView
    private var endTime: TextView
    private var subjectName: TextView
    private var buildingName: TextView

    init {
        LayoutInflater.from(context).inflate(R.layout.tile_subject, null)
        startTime = findViewById(R.id.startTime)
        endTime = findViewById(R.id.endTime)
        subjectName = findViewById(R.id.subjectName)
        buildingName = findViewById(R.id.buildingName)
    }

}