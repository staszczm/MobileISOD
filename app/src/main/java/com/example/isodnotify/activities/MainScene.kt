package com.example.isodnotify.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.isodnotify.R
import com.example.isodnotify.databinding.ActivityMainSceneBinding
import me.relex.circleindicator.CircleIndicator2
import me.relex.circleindicator.CircleIndicator3

class MainScene : AppCompatActivity() {
    private lateinit var binding: ActivityMainSceneBinding
    private var titlesList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainSceneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.welcomeText.text = "Cześć, " + intent.getStringExtra("USER_NAME")

        postToList()
        binding.viewPager.adapter = ViewPagerAdapter(titlesList)
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val indicator = findViewById<CircleIndicator3>(R.id.indicator)
        indicator.setViewPager(binding.viewPager)

    }

    private fun addToList(title: String) {
        titlesList.add(title)
    }

    private fun postToList() {
        for(i in 1..5) {
            addToList("Title $i")
        }
    }
}

