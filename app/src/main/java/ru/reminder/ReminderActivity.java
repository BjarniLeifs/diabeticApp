package ru.reminder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;



import jBerry.MySugar.R;

/**
 * Created by Anna on 7.8.2014.
 */
public class ReminderActivity extends ActionBarActivity {
    Button noti;
    int notiValue;
    Intent intent = new Intent();
    TextView numValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent.setClass(this, ReminderIntentService.class);
        numValue = (TextView) findViewById(R.id.time1);
        setContentView(R.layout.activity_reminder);

        noti = (Button) findViewById(R.id.NotificationButton);
        final NumberPicker mNumberPicker = (NumberPicker) findViewById(R.id.numberPicker);
        mNumberPicker.setMinValue(1);
        mNumberPicker.setMaxValue(24);
        mNumberPicker.setWrapSelectorWheel(true);

        noti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notiValue = mNumberPicker.getValue();
                intent.putExtra("HourValue", notiValue);
                startService(intent);
                Toast.makeText(getBaseContext(), "Þú færð áminningu eftir" + notiValue + "klukkutíma", Toast.LENGTH_SHORT).show();
            }
        });
    }
}





