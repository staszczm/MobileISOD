package com.example.isodnotify.utils

import org.json.JSONObject
import java.net.URL

class JsonDataFetcher {
    fun createUrlFromNameAndApi(name: String, apiKey: String): String {
        return "https://isod.ee.pw.edu.pl/isod-portal/wapi?q=mynewsheaders&username=$name&apikey=$apiKey&to=1"
    }
    fun getJsonDataFromUrl(url: String) =
        URL(url).readText()
    fun getImportantInformation(jsonString: String): NotifyInformations {
        val firstItem = JSONObject(jsonString).getJSONArray("items").getJSONObject(0)
        return NotifyInformations(
            firstItem.optString("hash", null),
            firstItem.optString("subject", null),
            firstItem.optString("modifiedBy", null)
        )
    }
}