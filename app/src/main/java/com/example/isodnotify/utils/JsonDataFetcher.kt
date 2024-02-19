package com.example.isodnotify.utils

import org.json.JSONObject
import java.net.URL

interface JsonDataFetcher<T> {
    val name: String
    val apiKey: String
    fun getQueryParameters(): String
    fun getJsonArrayName(): String
    private fun createUrlFromNameAndApi(): String {
        return "https://isod.ee.pw.edu.pl/isod-portal/wapi?q=${getQueryParameters()}&username=$name&apikey=$apiKey"
    }
    private fun getJsonDataFromUrl(url: String): String{
        return URL(url).readText()
    }
    suspend fun getAllData(): List<T>{
        val jsonData = getJsonDataFromUrl(createUrlFromNameAndApi())
        val itemsArray = JSONObject(jsonData).getJSONArray(getJsonArrayName())
        val itemsList = mutableListOf<T>()

        for (i in 0 until itemsArray.length()) {
            val item = itemsArray.getJSONObject(i)
            itemsList.add( getImportantInformation(item) )
        }
        return itemsList
    }
    fun getImportantInformation(item: JSONObject): T

}