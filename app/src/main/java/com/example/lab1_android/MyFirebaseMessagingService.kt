package com.example.lab1_android

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.example.lab1_android.ui.main.view.OneCharacterActivity


class MyFirebaseMessagingService : FirebaseMessagingService() {
    companion object {
        private val TAG = MyFirebaseMessagingService::class.java.simpleName
    }

    private fun sendNotification(messageBody: String?) {
        val channelId = getString(R.string.default_notification_channel_id)
        val channelName = getString(R.string.default_notification_channel_name)

        val pendingIntent = Intent(this, OneCharacterActivity::class.java)
        pendingIntent.putExtra("CHARACTER_ID", messageBody.toString().toInt())
        val resultPendingIntent = PendingIntent.getActivity(this, 0, pendingIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_stat_name)
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(resultPendingIntent)

        val mNotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            notificationBuilder.setChannelId(channelId)
            mNotificationManager.createNotificationChannel(channel)
        }

        val notification = notificationBuilder.build()
        mNotificationManager.notify(0, notification)
    }

    override fun onNewToken(s: String) {
        super.onNewToken(s)
        Log.d(TAG, "Refreshed token: $s")
        sendRegistrationToServer(s)
    }
    private fun sendRegistrationToServer(token: String) {
        val db: FirebaseDatabase = FirebaseDatabase.getInstance()
        var ref = db.getReference("server/saving-data/IDs")
        ref.push().setValue(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        remoteMessage.notification?.let {
            sendNotification(it.body)
        }
    }
}