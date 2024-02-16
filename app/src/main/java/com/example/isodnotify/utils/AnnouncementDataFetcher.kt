package com.example.isodnotify.utils

import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class AnnouncementDataFetcher(override val name: String, override val apiKey: String) : JsonDataFetcher<AnnouncementData> {
    override fun getQueryParameters(): String {
        return "mynewsheaders"
    }

    override fun getJsonArrayName(): String {
        return "items"
    }
    override fun getImportantInformation(item: JSONObject): AnnouncementData {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
        val modifiedDate = dateFormat.parse(item.optString("modifiedDate", null))

        return AnnouncementData(
            item.optString("hash", null),
            item.optString("subject", null),
            item.optString("modifiedBy", null),
            modifiedDate
        )
    }
}