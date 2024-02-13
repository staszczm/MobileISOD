package com.example.isodnotify.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.isodnotify.R
import com.example.isodnotify.databinding.ActivityMainSceneBinding

class MainScene : AppCompatActivity() {
    private lateinit var binding: ActivityMainSceneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainSceneBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main_scene)


    }


}