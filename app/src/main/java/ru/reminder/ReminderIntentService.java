package ru.reminder;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.NumberPicker;

import jBerry.MySugar.R;
import ru.checkin.CheckinActivity;
import ru.menu.StartActivity;

/**
 * Created by Anna on 7.8.2014.
 */
public class ReminderIntentService extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        NumberPicker mNumberPicker = (NumberPicker)findViewById(R.id.numberPicker);
        mNumberPicker.setMinValue(1);
        mNumberPicker.setMaxValue(24);
        mNumberPicker.setWrapSelectorWheel(true);






    }

}
