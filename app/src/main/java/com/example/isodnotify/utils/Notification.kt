package com.example.isodnotify.utils

import android.content.Context
import android.app.Notification
import android.app.NotificationManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class Notification {
    fun showNotification(context: Context, title: String, message: String){
        val channelId = "channel_id"

        var builder = NotificationCompat.Builder(context, channelId)
            //.setSmallIcon(R.drawable.notification_icon) // Replace with your own notification icon
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
    }
}
