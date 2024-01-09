package com.example.gafitorsatpam.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.gafitorsatpam.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFireBaseMessagingService : FirebaseMessagingService() {

    companion object {
        private fun initNotificationChannel(notificationManager: NotificationManager) {
            if (Build.VERSION_CODES.O <= Build.VERSION.SDK_INT) {
                notificationManager.createNotificationChannelIfNOtExists(
                    channelId = "1",
                    channelName = "Default"
                )
            }
        }


        private const val TAG = "FCM Notification"
        const val DEFAULT_NOTIFICATION_ID = 0
    }

    override fun onNewToken(token: String) {
        Log.i(TAG, "new FCM token created: $token")
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        initNotificationChannel(notificationManager)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val title = remoteMessage.notification?.title
        val body = remoteMessage.notification?.body

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        var notificationBuilder = if (Build.VERSION_CODES.O <= Build.VERSION.SDK_INT) {
            NotificationCompat.Builder(applicationContext, "1")
        } else {
            NotificationCompat.Builder(applicationContext)
        }
        notificationBuilder = notificationBuilder
            .setSmallIcon(R.drawable.gafito)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
        initNotificationChannel(notificationManager)
        notificationManager.notify(DEFAULT_NOTIFICATION_ID, notificationBuilder.build())
    }
    fun sendNotification(title: String, message: String) {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        initNotificationChannel(notificationManager)

        val notificationBuilder = NotificationCompat.Builder(applicationContext, "1")
            .setSmallIcon(R.drawable.gafito)
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(true)

        notificationManager.notify(DEFAULT_NOTIFICATION_ID, notificationBuilder.build())
    }

    private fun initNotificationChannel(notificationManager: NotificationManager) {
        if (Build.VERSION_CODES.O <= Build.VERSION.SDK_INT) {
            notificationManager.createNotificationChannelIfNOtExists(
                channelId = "1",
                channelName = "Default"
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun NotificationManager.createNotificationChannelIfNOtExists(
    channelId: String,
    channelName: String,
    importance: Int = NotificationManager.IMPORTANCE_DEFAULT
) {
    var channel = this.getNotificationChannel(channelId)

    if (channel == null) {
        channel = NotificationChannel(
            channelId,
            channelName,
            importance
        )
        this.createNotificationChannel(channel)
    }
}
