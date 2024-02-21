package com.example.isodnotify.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.isodnotify.R
import com.example.isodnotify.databinding.ActivityMainSceneBinding
import com.example.isodnotify.tiles.NewsTile
import com.example.isodnotify.tiles.SubjectTile
import com.example.isodnotify.utils.TimeTableData
import com.example.isodnotify.utils.TimeTableDataFetcher
import kotlinx.coroutines.launch
import me.relex.circleindicator.CircleIndicator3
import java.util.*

class MainScene : AppCompatActivity() {
    private lateinit var binding: ActivityMainSceneBinding
    private lateinit var userName: String
    private lateinit var apiKey: String
    private lateinit var isodInfo: List<TimeTableData>

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainSceneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent.hasExtra("USER_NAME") && intent.hasExtra("API_KEY")) {
            binding.welcomeText.text = "Cześć, " + intent.getStringExtra("USER_NAME")
            userName = intent.getStringExtra("USER_NAME").toString()
            apiKey = intent.getStringExtra("API_KEY").toString()

            Log.d("INFO", "$userName $apiKey")

        }

        var titlesList : MutableList<String> = mutableListOf()

        titlesList.add("Pierwszy news")
        titlesList.add("Drugi news")

        val newsTiles = NewsTile(titlesList)

        binding.viewPager.adapter = ViewPagerAdapter(newsTiles.getTitlesList(), object: ViewPagerAdapter.OnClickListener {
            override fun onClick(view: View, title: String) {
                println("Kliknięto na kafelek o tytule: $title")
            }
        })

        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val indicator = findViewById<CircleIndicator3>(R.id.indicator)
        indicator.setViewPager(binding.viewPager)

        val startTime = Calendar.getInstance()
        startTime.set(2024, Calendar.FEBRUARY, 20, 8, 0)

        val endTime = Calendar.getInstance()
        endTime.set(2024, Calendar.FEBRUARY, 20, 10, 0)


        val startTime2 = Calendar.getInstance()
        startTime2.set(2024, Calendar.FEBRUARY, 20, 12, 0)

        val endTime2 = Calendar.getInstance()
        endTime2.set(2024, Calendar.FEBRUARY, 20, 16, 0)


        val timeTableData = TimeTableData("Wychowanie fizyczne 3", startTime.time, endTime.time, 1, "Budynek YOLO", "Zajęcia", "siema")
        val timeTableData2 = TimeTableData("Angielski 2", startTime2.time, endTime2.time, 1, "Budynek DUPCIA", "Zajęcia", "siema")

        val tile = SubjectTile(this, timeTableData)
        val tile2 = SubjectTile(this, timeTableData2)

        val subjectLayout = findViewById<LinearLayout>(R.id.subjectTable)

        val tileParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
        )
        tileParams.setMargins(0,0,0,20)

        subjectLayout.addView(tile, tileParams)

        val tileParams2 = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
        )
        tileParams2.setMargins(0,20,0,0)

        subjectLayout.addView(tile2, tileParams2)


    }
}

