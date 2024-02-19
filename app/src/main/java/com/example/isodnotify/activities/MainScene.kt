package com.example.isodnotify.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.isodnotify.R
import com.example.isodnotify.databinding.ActivityMainSceneBinding
import com.example.isodnotify.tiles.NewsTile
import me.relex.circleindicator.CircleIndicator3

class MainScene : AppCompatActivity() {
    private lateinit var binding: ActivityMainSceneBinding
//    private var titlesList = mutableListOf<String>()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainSceneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent.hasExtra("USER_NAME")) {
            binding.welcomeText.text = "Cześć, " + intent.getStringExtra("USER_NAME")
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

        val tileSubject = LayoutInflater.from(this).inflate(R.layout.tile_subject, null)
        val tileSubject2 = LayoutInflater.from(this).inflate(R.layout.tile_subject, null)

        val subjectLayout = findViewById<LinearLayout>(R.id.subjectTable)

        val tileParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
        )
        tileParams.setMargins(0,0,0,20)

        subjectLayout.addView(tileSubject, tileParams)

        val tileParams2 = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
        )
        tileParams2.setMargins(0,20,0,0)

        subjectLayout.addView(tileSubject2, tileParams2)


    }
}

