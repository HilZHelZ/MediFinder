package com.example.hospitalfindertest;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMessagingService";
    private static final String CHANNEL_ID = "default_channel_id";

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.d(TAG, "New token: " + token);

        // You can send the token to your server if needed for managing subscriptions.
        sendTokenToServer(token);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d(TAG, "Message received from: " + remoteMessage.getFrom());

        // Check if the message contains a notification payload
        if (remoteMessage.getNotification() != null) {
            String title = remoteMessage.getNotification().getTitle();
            String body = remoteMessage.getNotification().getBody();
            Log.d(TAG, "Notification Title: " + title);
            Log.d(TAG, "Notification Body: " + body);

            // Display the notification
            sendNotification(title, body);
        }

        // Check if the message contains data payload
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

            // You can process custom data here, if needed
        }
    }

    private void sendNotification(String title, String messageBody) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Create a notification channel for Android 8.0+ (Oreo)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Default Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription("Default notification channel for the app");
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }

        // Intent to open the app when the notification is clicked
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE
        );

        // Build the notification
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_notification) // Use a custom notification icon
                        .setContentTitle(title)
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Show the notification
        if (notificationManager != null) {
            notificationManager.notify(0, notificationBuilder.build());
        }
    }

    private void sendTokenToServer(String token) {
        // Implement this method if you need to send the token to your app server
        Log.d(TAG, "Sending token to server: " + token);
        // Example: make a network call to your backend to save the token
    }
}
