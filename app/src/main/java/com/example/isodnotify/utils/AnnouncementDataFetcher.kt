package com.example.isodnotify.utils

import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class AnnouncementDataFetcher : JsonDataFetcher<AnnouncementData> {
    override fun getImportantInformation(jsonString: String): AnnouncementData {
        val firstItem = JSONObject(jsonString).getJSONArray("items").getJSONObject(0)

        val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
        val modifiedDate = dateFormat.parse(firstItem.optString("modifiedDate", null))

        return AnnouncementData(
            firstItem.optString("hash", null),
            firstItem.optString("subject", null),
            firstItem.optString("modifiedBy", null),
            modifiedDate
        )
    }
}