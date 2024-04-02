package com.example.isodnotify.activities

import android.content.Context
import android.text.Editable
import android.content.Intent
import android.content.SharedPreferences
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
    companion object {
        const val SHARED_PREFS = "shared_prefs"
        const val USERNAME_KEY = "email_key"
        const val API_KEY = "password_key"
    }

    private lateinit var sharedpreferences: SharedPreferences
    private var username: String? = null
    private var apiKey: String? = null

    private lateinit var binding: ActivityLoginScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        binding.usernameInputTile.text = sharedpreferences.getString(USERNAME_KEY, "")!!.toEditable()
        binding.apiKeyInputTile.text = sharedpreferences.getString(API_KEY, "")!!.toEditable()

        binding.logInButton.setOnClickListener {
            lifecycleScope.launch {
                username = binding.usernameInputTile.text.toString()
                apiKey = binding.apiKeyInputTile.text.toString()
                val result = validateLoginCredentials(username!!, apiKey!!)
                if (result) {
                    correctInput(username!!, apiKey!!)
                } else {
                    showInputError()
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
    private fun showInputError() {
        binding.usernameInputTile.setBackgroundResource(R.drawable.lm_log_input_tile_error)
        binding.usernameInputTile.setTextAppearance(R.style.LogInInputTileError)
        binding.apiKeyInputTile.setBackgroundResource(R.drawable.lm_log_input_tile_error)
        binding.apiKeyInputTile.setTextAppearance(R.style.LogInInputTileError)
    }

    private fun correctInput(username: String, apiKey: String) {
        saveCredentials(username, apiKey)
        val mainScene = Intent(applicationContext, MainScene::class.java)
        mainScene.putExtra("USER_NAME", username)
        mainScene.putExtra("API_KEY", apiKey)
        startActivity(mainScene)
    }

    private fun saveCredentials(username: String, apiKey: String) {
        sharedpreferences.edit().apply() {
            putString(USERNAME_KEY, username)
            putString(API_KEY, apiKey)
            apply()
        }
    }
    private fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)
}
