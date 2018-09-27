package com.incluit.apinto.cookiescream.activities;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.incluit.apinto.cookiescream.R;

import java.util.ArrayList;
import java.util.List;

public class NotificationsActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();

    private NotificationManager mNotificationManager;
    private Button mCreateNotificationChannelsButton;
    private Button mSendLowNotificationButton;
    private Button mSendMediumNotificationButton;
    private Button mSendHighNotificationButton;
    private static int sNotifyId = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications_activity);
        mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        initViews();
    }

    private void initViews() {
        mCreateNotificationChannelsButton = findViewById(R.id.create_notification_channels);
        mCreateNotificationChannelsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                    String channelId = "low_channel_id";
                    CharSequence channelName = "Low importance channel";
                    int importance = NotificationManager.IMPORTANCE_LOW;

                    NotificationChannel notificationChannel = createNotificationChannel(importance, channelName, channelId);

                    String mediumChannelId = "medium_channel_id";
                    CharSequence mediumName = "Medium importance channel";
                    int mediumImportance = NotificationManager.IMPORTANCE_DEFAULT;

                    NotificationChannel mediumNotificationChannel = createNotificationChannel(mediumImportance, mediumName, mediumChannelId);

                    String highChannelId = "high_channel_id";
                    CharSequence highName = "High importance channel";
                    int highImportance = NotificationManager.IMPORTANCE_HIGH;

                    NotificationChannel highNotificationChannel = createNotificationChannel(highImportance, highName, highChannelId);

                    List<NotificationChannel> notificationChannels = new ArrayList<>();
                    notificationChannels.add(notificationChannel);
                    notificationChannels.add(mediumNotificationChannel);
                    notificationChannels.add(highNotificationChannel);

                    mNotificationManager.createNotificationChannels(notificationChannels);
                }

            }
        });

        mSendLowNotificationButton = findViewById(R.id.send_low_notification);
        mSendLowNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String channelId = "low_channel_id";

                Notification notification = createNotification(channelId, "A message", "This is the body of the notification");

                mNotificationManager.notify(sNotifyId++, notification);
            }
        });

        mSendMediumNotificationButton = findViewById(R.id.send_medium_notification);
        mSendMediumNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String channelId = "medium_channel_id";

                Notification notification = createNotification(channelId,"A medium important message", "This is the body of the notification");

                mNotificationManager.notify(sNotifyId++, notification);
            }
        });

        mSendHighNotificationButton = findViewById(R.id.send_high_notification);
        mSendHighNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String channelId = "high_channel_id";

                Notification notification = createNotification(channelId, "A SUPER important message", "This is the body of the notification");

                mNotificationManager.notify(sNotifyId++, notification);
            }
        });

    }

    private NotificationChannel createNotificationChannel(int importance, CharSequence name, String id) {
        NotificationChannel notificationChannel = new NotificationChannel(id, name, importance);
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.RED);
        notificationChannel.enableVibration(true);
        return notificationChannel;
    }

    private Notification createNotification(String channelId, String title, String message) {

        Intent notifIntent = new Intent(getApplicationContext(), MainActivity.class);
        notifIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        Notification.Builder builder = new Notification.Builder(getApplicationContext())
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(PendingIntent.getActivity(getApplicationContext(), 0, notifIntent, 0));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId(channelId);
        }

        Notification notification = builder.build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        return notification;
    }

}
