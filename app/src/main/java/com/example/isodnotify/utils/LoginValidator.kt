package com.example.isodnotify.utils

import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class LoginValidator {
    fun validate(username: String, apiKey: String): Boolean {
        val url = "https://isod.ee.pw.edu.pl/isod-portal/wapi?q=mynewsheaders&username=$username&apikey=$apiKey&to=1"
        try{
            val jsonString = URL(url).readText()
        } catch (e: Exception) {
            return false
        }
        return true
    }

}