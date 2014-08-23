package ru.calendar;

import android.app.Activity;
import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.view.View.OnClickListener;
import android.widget.Toast;


import com.jberry.dto.CalanderMeal;
import com.jberry.dto.FoodTO;
import com.jberry.services.calendar.CalendarService;
import com.jberry.services.calendar.CalendarServiceFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import jBerry.MySugar.R;
import ru.Events.Events;

public class AddMealToActivity extends Activity {

    private Button btnSave;
    long timestamp;
    GregorianCalendar cal = new GregorianCalendar(TimeZone.getTimeZone("US/Central"));
    final Map<String, Integer> data = new HashMap<String, Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);


        final CalendarView calendarView = (CalendarView) findViewById(R.id.addCalendarView);
        final TimePicker timePicker = (TimePicker) findViewById(R.id.timePickerView);
        cal.setTimeInMillis(calendarView.getDate());

        btnSave = (Button) findViewById(R.id.saveAddBtn);
        btnSave.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {


                EditText Name = (EditText) findViewById(R.id.nameid);
                String mealName = Name.getText().toString();

                cal.setTimeInMillis(calendarView.getDate());
                Date date = new Date(calendarView.getDate() / 1000L);
                long hour = timePicker.getCurrentHour();
                long min = timePicker.getCurrentMinute();

                hour = TimeUnit.HOURS.toMillis(hour);
                min = TimeUnit.MINUTES.toMillis(min);

                // Fæ ranga niðurstöðu en Date gefur rétt frá sér.
                timestamp = date.getTime()+hour+min;

                for (int i = 0; i < Events.idItems.length; i++) {
                    AutoCompleteTextView item = (AutoCompleteTextView) findViewById(Events.idItems[i]);
                    EditText gram = (EditText) findViewById(Events.idGrams[i]);

                    if (item.getText().toString().trim().length() > 0) {
                        data.put(item.toString(), Integer.parseInt(gram.getText().toString()));
                    }
                }

                MyTaskParams params = new MyTaskParams(mealName, timestamp);
                saveMealToCalander saveIt = new saveMealToCalander();
                saveIt.execute(params);

                //AddToCalendarAdapter.addMeal(data, timestamp, hour, min);
                Intent intent = new Intent(AddMealToActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });
    }

    private static class MyTaskParams {


        String mealName;
        long timeStamp;

        MyTaskParams(String mealName, long timeStamp) {


            this.timeStamp = timeStamp;
            this.mealName = mealName;
        }
    }

    private class saveMealToCalander extends AsyncTask<MyTaskParams, Boolean, Boolean> {

        @Override
        protected Boolean doInBackground(MyTaskParams... params) {

            MyTaskParams meal = params[0];
            String mealName = meal.mealName;
            long TimeStamp = meal.timeStamp;

            CalendarService service = CalendarServiceFactory.getCalanderService();
            try {
                return service.saveMealToCalander(mealName, TimeStamp);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Boolean i) {
            Toast.makeText(getBaseContext(), "Bætt við í dagatal!", Toast.LENGTH_SHORT).show();

        }
    }
}

