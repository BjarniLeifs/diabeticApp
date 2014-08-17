package ru.calendar;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.jberry.dto.CalanderMeal;
import com.jberry.dto.Meal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import jBerry.MySugar.R;

public class CalendarActivity extends ActionBarActivity {

    private ArrayList<CalanderMeal> eventList = new ArrayList<CalanderMeal>();
    private Meal nutrition = new Meal();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.US);
    GregorianCalendar cal = new GregorianCalendar(TimeZone.getTimeZone("US/Central"));

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



        eventList = (ArrayList<CalanderMeal>) CalendarAdapter.getMealsByDay(Long.parseLong(sdf.format(cal.getTime())));
        nutrition = CalendarAdapter.getMealById();

        ListAdapter adapter = new CalendarAdapter(getApplicationContext(), R.layout.notification_list_item, eventList, nutrition);
        ListView listview = (ListView) findViewById(R.id.eventList);
        listview.setAdapter(adapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager fm = getSupportFragmentManager();

                dialogFragment dialog = new dialogFragment();

                dialog.show(fm, "abc");
            }
        });


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                cal.setTimeInMillis(calendarView.getDate());
                eventList = (ArrayList<CalanderMeal>) CalendarAdapter.getMealsByDay(Long.parseLong(sdf.format(cal.getTime())));
                nutrition =  CalendarAdapter.getMealById();

                ListAdapter adapter = new CalendarAdapter(getApplicationContext(), R.layout.notification_list_item, eventList, nutrition);
                ListView listview = (ListView) findViewById(R.id.eventList);
                listview.setAdapter(adapter);
            }
        });

    }

}


