package com.example.isodnotify.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.appcompat.app.AppCompatActivity
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

//        Log.e("SHAREDPREFERENCES", "clearing shared preferences")
//        clearSharedPreferences()

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

    private fun correctInput(username: String, apiKey: String) {
        val editor = sharedpreferences.edit()
        editor.putString(USERNAME_KEY, username)
        editor.putString(API_KEY, apiKey)
        editor.apply()

        val mainScene = Intent(applicationContext, MainScene::class.java)
        mainScene.putExtra("USER_NAME", username)
        startActivity(mainScene)
    }

    private fun clearSharedPreferences() {
        val editor = sharedpreferences.edit()
        editor.clear()
        editor.apply()
    }

    private fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

}