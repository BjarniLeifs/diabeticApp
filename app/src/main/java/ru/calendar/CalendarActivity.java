package ru.calendar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.jberry.dto.*;
import com.jberry.services.calendar.CalendarService;
import com.jberry.services.calendar.CalendarServiceFactory;
import com.jberry.services.food.FoodService;
import com.jberry.services.food.FoodServiceFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import jBerry.MySugar.R;


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
        long unixTime = System.currentTimeMillis() / 1000L;

       // Þetta kall virkar en skilar tómum lista
       // new getMealsByDayConnection().execute(unixTime);


     //   ListAdapter adapter = new CalendarAdapter(getApplicationContext(), R.layout.notification_list_item, mealsList);
      //  final ListView listview = (ListView) findViewById(R.id.eventList);
      //  listview.setAdapter(adapter);

/*
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
*/

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                cal.setTimeInMillis(calendarView.getDate());
                new getMealsByDayConnection().execute(calendarView.getDate());

                ListAdapter adapter = new CalendarAdapter(getApplicationContext(), R.layout.notification_list_item, mealsList);
                ListView listview = (ListView) findViewById(R.id.eventList);
                listview.setAdapter(adapter);
            }
        });

    }

    private class getMealsByDayConnection extends AsyncTask<Long, ArrayList<CalanderMeal>, ArrayList<CalanderMeal>> {

        protected ArrayList<CalanderMeal> doInBackground(Long... params) {

            CalendarService service = CalendarServiceFactory.getCalanderService();
            try {
               return service.getMealsByDay(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        return null;
        }

        @Override
        protected void onPostExecute(ArrayList<CalanderMeal> result){
            CalendarAdapter adapter = null;
            adapter.notifyDataSetChanged();

        }
    }

}


