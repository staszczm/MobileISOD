package com.example.isodnotify.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.isodnotify.R
import com.example.isodnotify.databinding.ActivityLoginScreenBinding

class LoginScreen : AppCompatActivity() {
    private lateinit var binding: ActivityLoginScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }






//  *** Funkcja do otwierania strony gdzie jest API key ***

    fun whereToApiTextOnClick(view: View) {
        gotoURL()
    }
    private fun gotoURL() {
        val url = "https://isod.ee.pw.edu.pl/isod-stud/person"
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = uri
        val chooserIntent = Intent.createChooser(intent, "Wybierz aplikację")
        startActivity(chooserIntent)
    }

// ********************************************************
}