package com.example.isodnotify.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.isodnotify.R
import com.example.isodnotify.databinding.ActivityLoginScreenBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class LoginScreen : AppCompatActivity() {
    private lateinit var binding: ActivityLoginScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.logInButton.setOnClickListener {
            val username = binding.usernameInputTile.text.toString()
            val apiKey = binding.apiKeyInputTile.text.toString()

            lifecycleScope.launch {
                val result = validateLoginCredentials(username, apiKey)
                if (result) {
                    correctInput(username)
                    //TODO: Navigate to next screen
                    //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
                } else {
                    wrongInput()
                    println("niedziala")
                    //TODO: Show error message
                    //Toast.makeText(context, "Invalid username or password", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private suspend fun validateLoginCredentials(username: String, apiKey: String): Boolean {
        if (username.isEmpty() || apiKey.isEmpty()) {
            return false
        }
        val url = "https://isod.ee.pw.edu.pl/isod-portal/" +
                "wapi?q=mynewsheaders&username=$username&apikey=$apiKey&to=1"
        return withContext(Dispatchers.IO) {
            try {
                URL(url).readText()
                true
            } catch (e: Exception) {
                false
            }
        }
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
    private fun wrongInput() {
        binding.usernameInputTile.setBackgroundResource(R.drawable.lm_log_input_tile_error)
        binding.usernameInputTile.setTextAppearance(R.style.LogInInputTileError)

        binding.apiKeyInputTile.setBackgroundResource(R.drawable.lm_log_input_tile_error)
        binding.apiKeyInputTile.setTextAppearance(R.style.LogInInputTileError)
    }

    private fun correctInput(username: String) {
        val mainScene = Intent(applicationContext, MainScene::class.java)
        mainScene.putExtra("USER_NAME", username)
        startActivity(mainScene)
    }

}