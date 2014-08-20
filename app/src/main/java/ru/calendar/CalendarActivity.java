package ru.calendar;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.jberry.dto.CalanderMeal;
import com.jberry.dto.Food;
import com.jberry.dto.FoodTO;
import com.jberry.dto.Meal;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import jBerry.MySugar.R;
import ru.Events.Events;

import static ru.calendar.CalendarAdapter.getMealsByDay;

public class CalendarActivity extends ActionBarActivity {

    private ArrayList<CalanderMeal> mealsList;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.US);
    GregorianCalendar cal = new GregorianCalendar(TimeZone.getTimeZone("US/Central"));

    public CalendarActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar2);


        final CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView1);
        cal.setTimeInMillis(calendarView.getDate());
        final Button addBtn = (Button) findViewById(R.id.saveAddBtn2);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this, AddMealToActivity.class);
                startActivity(intent);
            }
        });
        long unixTime; //System.currentTimeMillis() / 1000L;

        unixTime = 1408492800;
        try {
            mealsList = CalendarAdapter.getMealsByDay(unixTime);
        } catch (IOException e) {
            e.printStackTrace();
        }


        ListAdapter adapter = new CalendarAdapter(getApplicationContext(), R.layout.notification_list_item, mealsList);
        final ListView listview = (ListView) findViewById(R.id.eventList);
        listview.setAdapter(adapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String fakeName = "disIsFake";


                Bundle args = new Bundle();
                args.putString("fakeName", fakeName);
                FragmentManager fm = getSupportFragmentManager();
                dialogFragment dialog = new dialogFragment();

                dialog.setArguments(args);
                dialog.show(fm, "abc");


            }
        });


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                cal.setTimeInMillis(calendarView.getDate());

                try {
                    mealsList = getMealsByDay(Long.parseLong(sdf.format(cal.getTime())));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //  nutrition =  CalendarAdapter.getMealById();

                ListAdapter adapter = new CalendarAdapter(getApplicationContext(), R.layout.notification_list_item, mealsList);
                ListView listview = (ListView) findViewById(R.id.eventList);
                listview.setAdapter(adapter);
            }
        });

    }

}


