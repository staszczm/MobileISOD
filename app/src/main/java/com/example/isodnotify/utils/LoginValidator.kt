package com.example.isodnotify.utils

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class LoginValidator {
    fun validate(username: String, apiKey: String): Boolean {
        val url = "https://isod.ee.pw.edu.pl/isod-portal/wapi?q=mynewsheaders&username=$username&apikey=$apiKey&to=1"
        println(url)
        try{
            val jsonString = URL(url).readText()
            println(username)
            println(apiKey)
            println(jsonString)
        } catch (e: Exception) {
            Log.e("LoginValidator", "Error: $e")
            return false
        }
        return true
    }

}