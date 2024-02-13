package com.example.isodnotify.utils

import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.Locale

interface JsonDataFetcher<T> {
    fun createUrlFromNameAndApi(name: String, apiKey: String): String {
        return "https://isod.ee.pw.edu.pl/isod-portal/wapi?q=mynewsheaders&username=$name&apikey=$apiKey&to=1"
    }
    fun getJsonDataFromUrl(url: String): String{
        return URL(url).readText()
    }
    fun getImportantInformation(jsonString: String): T
}