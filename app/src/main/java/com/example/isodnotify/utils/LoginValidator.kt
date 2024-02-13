package com.example.isodnotify.utils

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

class LoginValidator {
    suspend fun validate(username: String, apiKey: String): Boolean {
        val url = "https://isod.ee.pw.edu.pl/isod-portal/wapi?q=mynewsheaders&username=$username&apikey=$apiKey&to=1"
        try{
            val jsonString = URL(url).readText()
        } catch (e: Exception) {
            return false
        }
        return true
    }

}