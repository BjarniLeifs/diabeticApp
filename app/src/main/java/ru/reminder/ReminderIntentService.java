package ru.reminder;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import jBerry.MySugar.R;
import ru.checkin.CheckinActivity;

/**
 * Created by Anna on 7.8.2014.
 */
public  class ReminderIntentService extends IntentService {
    String message = "Ekki gleyma að mæla þig";
    Object value;

    public ReminderIntentService() {
        super("ReminderIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //Sleep for the amount of hours the user picks
        Bundle extras = intent.getExtras();
        value = extras.get("HourValue");
        int i = (Integer) value;
        //The alert goes on when the chosen amount of hours has passed
        long alertTime = System.currentTimeMillis() + (i * 1000);//3600000);
        while (System.currentTimeMillis() < alertTime) {
            synchronized (this) {
                try {
                    wait(alertTime - System.currentTimeMillis());
                }
                catch (Exception e) {
                }
            }
        }
        generateNotification(getBaseContext(), message);

    }
    private static void generateNotification(Context context, String message) {
        int icon = R.drawable.diabetes;
        long when = System.currentTimeMillis();
        String NotificationTitle = context.getString(R.string.app_name);
        //set intent
        Intent notificationIntent = new Intent(context, CheckinActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent intent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
        //Make notification manager
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification(icon, message, when);
        // notification sound
        notification.defaults |= Notification.DEFAULT_SOUND;

        notification.setLatestEventInfo(context, NotificationTitle, message, intent);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        // Vibration
        notification.defaults |= Notification.DEFAULT_VIBRATE;
        mNotificationManager.notify(0, notification);

    }
}
