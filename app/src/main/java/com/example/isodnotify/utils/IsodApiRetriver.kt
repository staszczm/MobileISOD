package com.example.isodnotify.utils

import android.os.AsyncTask
import android.util.Log
import java.net.URL

class IsodApiRetriver(private val username: String, private val apiKey: String): AsyncTask<String, Void, String>(){
    private val jsonDataFetcher = JsonDataFetcher()
    override fun doInBackground(vararg params: String?): String {
        val url = jsonDataFetcher.createUrlFromNameAndApi(username, apiKey)
        return getJsonDataFromUrl(url)
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        Log.d("IsodApiRetriver", "Result: $result")
    }

    fun getJsonDataFromUrl(url: String): String{
        return URL(url).readText()
    }
}