package ru.calendar;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TimePicker;
import android.view.View.OnClickListener;


import com.jberry.dto.Food;
import com.jberry.dto.FoodTO;

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
import ru.checkin.foodDialogFragment;

public class AddMealToActivity extends Activity {

    private Button btnSave;
    private FoodTO foodlist;
    long timestamp;
    String mealName;
    Bundle bundle;
    GregorianCalendar cal = new GregorianCalendar(TimeZone.getTimeZone("US/Central"));
    final Map<String, Integer> data = new HashMap<String, Integer>();
    private ArrayList<String> matisList = new ArrayList<String>();

   public AddMealToActivity(String mealName){
        this.mealName = mealName;
   }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);

        final CalendarView calendarView = (CalendarView) findViewById(R.id.addCalendarView);
        final TimePicker timePicker = (TimePicker) findViewById(R.id.timePickerView);
        cal.setTimeInMillis(calendarView.getDate());

        // Ef notandi skrifar ep þá koma upp allar fæðutegundir sem byrja á ep t.d. epli
        for(int i = 0; i < Events.idItems.length; i++){
            AutoCompleteTextView actv = (AutoCompleteTextView)findViewById(Events.idItems[i]);
            actv.setThreshold(1);
            String item = actv.getText().toString();

            matisList = CalendarAdapter.getFoodTitle(item);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, matisList);
            actv.setAdapter(adapter);
        }

        for(int i = 0; i < Events.idButton.length; i ++){
            AutoCompleteTextView item = (AutoCompleteTextView) findViewById(Events.idItems[i]);
            mealName = item.getText().toString();

            Button btn = (Button) findViewById(Events.idButton[i]);
            btn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(AddMealToActivity.this, NutritionPerMealActivity.class);
                    intent.putExtra("mealName", mealName);
                    startActivity(intent);

                }
            });
        }


        btnSave = (Button) findViewById(R.id.saveAddBtn);
        btnSave.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                cal.setTimeInMillis(calendarView.getDate());
                Date date = new Date(calendarView.getDate() / 1000L);

                long hour = timePicker.getCurrentHour();
                long min = timePicker.getCurrentMinute();

                hour = TimeUnit.HOURS.toMillis(hour);
                min = TimeUnit.MINUTES.toMillis(min);

                timestamp = date.getTime()+hour+min;

                for (int i = 0; i < Events.idItems.length; i++) {
                    AutoCompleteTextView item = (AutoCompleteTextView) findViewById(Events.idItems[i]);
                    EditText gram = (EditText) findViewById(Events.idGrams[i]);

                    if (item.getText().toString().trim().length() > 0) {
                        data.put(item.toString(), Integer.parseInt(gram.getText().toString()));
                    }
                }

                //AddToCalendarAdapter.addMeal(data, timestamp, hour, min);
                Intent intent = new Intent(AddMealToActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });
    }


}
