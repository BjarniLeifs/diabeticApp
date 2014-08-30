package ru.calendar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.jberry.dto.CalanderMeal;
import com.jberry.services.calendar.CalendarService;
import com.jberry.services.calendar.CalendarServiceFactory;

import java.io.IOException;
import java.util.ArrayList;

import jBerry.MySugar.R;

/*
    Class that holds the calendarView and a list of items for specific day choose by user.
 */
public class CalendarActivity extends ActionBarActivity {

    ArrayList<CalanderMeal> mealsList = new ArrayList<CalanderMeal>();

    public CalendarActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar2);

        final CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView1);
        final Button addBtn = (Button) findViewById(R.id.saveAddBtn2);
        long unixTime = calendarView.getDate() / 1000L;

        new GetMealsByDayConnection().execute(unixTime);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                long unixTime = calendarView.getDate() / 1000L;
                new GetMealsByDayConnection().execute(unixTime);
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this, AddMealToActivity.class);
                startActivity(intent);
            }
        });

        ListAdapter adapter = new CalendarAdapter(getApplicationContext(), R.layout.notification_list_item, mealsList);
        final ListView listview = (ListView) findViewById(R.id.eventList);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String foodName = mealsList.get(position).getMealName();
                long timeOfMeal = mealsList.get(position).getTimeOfMeal();

                Bundle args = new Bundle();
                args.putString("mealName", foodName);
                args.putLong("timeOfMeal", timeOfMeal);
                FragmentManager fm = getSupportFragmentManager();
                dialogFragment dialog = new dialogFragment();

                dialog.setArguments(args);
                dialog.show(fm, "abc");
            }
        });
    }

    private class GetMealsByDayConnection extends AsyncTask<Long, ArrayList<CalanderMeal>, ArrayList<CalanderMeal>> {
        protected ArrayList<CalanderMeal> doInBackground(Long... params) {

            CalendarService service = CalendarServiceFactory.getCalanderService();

            try {
                mealsList = service.getMealsByDay(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.getMessage();
            }
            return mealsList;
        }

        @Override
        protected void onPostExecute(ArrayList<CalanderMeal> result) {

            ListAdapter adapter = new CalendarAdapter(getApplicationContext(), R.layout.notification_list_item, result/*, list*/);
            ListView listview = (ListView) findViewById(R.id.eventList);
            listview.setAdapter(adapter);
        }
    }
}


