package com.example.isodnotify.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.isodnotify.R
import com.example.isodnotify.databinding.ActivityMainSceneBinding
import com.example.isodnotify.tiles.NewsTile
import com.example.isodnotify.tiles.SubjectTile
import com.example.isodnotify.utils.AnnouncementDataFetcher
import com.example.isodnotify.utils.JsonDataFetcher
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

    override fun onResume() {
        super.onResume()

        lifecycleScope.launch {
            val fetcher = TimeTableDataFetcher(userName, apiKey)
            isodInfo = fetcher.getAllData()

            val timeTableData = isodInfo[3]
            val timeTableData2 = isodInfo[4]

            val tile = SubjectTile(this@MainScene, timeTableData)
            val tile2 = SubjectTile(this@MainScene, timeTableData2)

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

            val fetcherAnn = AnnouncementDataFetcher(userName, apiKey)
            val isodAnn = fetcherAnn.getAllData()

            var titlesList : MutableList<String> = mutableListOf()



            titlesList.add(isodAnn[0].subject)
            titlesList.add(isodAnn[1].subject)
            titlesList.add(isodAnn[2].subject)
            titlesList.add(isodAnn[3].subject)
            titlesList.add(isodAnn[4].subject)

            val newsTiles = NewsTile(titlesList)

            binding.viewPager.adapter = ViewPagerAdapter(newsTiles.getTitlesList(), object: ViewPagerAdapter.OnClickListener {
                override fun onClick(view: View, title: String) {
                    println("Kliknięto na kafelek o tytule: $title")
                }
            })

            binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

            val indicator = findViewById<CircleIndicator3>(R.id.indicator)
            indicator.setViewPager(binding.viewPager)
        }


    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainSceneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent.hasExtra("USER_NAME") && intent.hasExtra("API_KEY")) {
            binding.welcomeText.text = "Cześć, " + intent.getStringExtra("USER_NAME")
            userName = intent.getStringExtra("USER_NAME").toString()
            apiKey = intent.getStringExtra("API_KEY").toString()
        }

    }

}

