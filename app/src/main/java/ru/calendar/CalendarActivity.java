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

import com.jberry.dto.*;
import com.jberry.services.calendar.CalendarService;
import com.jberry.services.calendar.CalendarServiceFactory;
import com.jberry.services.meal.MealService;
import com.jberry.services.meal.MealServiceFactory;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import jBerry.MySugar.R;


public class CalendarActivity extends ActionBarActivity {

    ArrayList<CalanderMeal> mealsList = new ArrayList<CalanderMeal>();
    ArrayList<Meal> meal = new ArrayList<Meal>();

    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.US);
    GregorianCalendar cal = new GregorianCalendar(TimeZone.getTimeZone("US/Central"));

    public CalendarActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar2);


        final CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView1);
        final Button addBtn = (Button) findViewById(R.id.saveAddBtn2);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this, AddMealToActivity.class);
                startActivity(intent);
            }
        });


        long unixTime = calendarView.getDate() / 1000L;

        // Þetta kall virkar en skilar tómum lista
        new GetMealsByDayConnection().execute(unixTime);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                long unixTime = calendarView.getDate() / 1000L;
                new GetMealsByDayConnection().execute(unixTime);
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
                mealsList =  service.getMealsByDay(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e){
                e.getMessage();
            }
            return mealsList;
        }

        @Override
        protected void onPostExecute(ArrayList<CalanderMeal> result){


            ListAdapter adapter = new CalendarAdapter(getApplicationContext(), R.layout.notification_list_item, result/*, list*/);
            ListView listview = (ListView) findViewById(R.id.eventList);
            listview.setAdapter(adapter);
            /*
            Meal meal = null;

            MyTaskParams params = new MyTaskParams(result, meal);
            params.arrayList = result;
            GetMealsByDayNutritionConnection calculate = new GetMealsByDayNutritionConnection();
            calculate.execute(params);

*/

        }
    }


    private class GetMealsByDayNutritionConnection extends AsyncTask<MyTaskParams, Meal, test> {

        protected test doInBackground(MyTaskParams... params) {

            MealService service = MealServiceFactory.getMealService();
            ArrayList<Meal> newMeal = new ArrayList<Meal>();

            Meal meal = null;
            try {
                for(int i = 0; i < params[0].arrayList.size(); i++){
                    String mealName = params[0].arrayList.get(i).getMealName();

                    meal =  service.getMealByName(mealName);
                    newMeal.add(meal);

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e){
                e.getMessage();
            }

            test t = new test();
            t.arrayList = params[0].arrayList;
            t.meal = newMeal;
            return t;
        }

        @Override
        protected void onPostExecute(test t){


               // ArrayList<CalanderMeal> mealName = t.arrayList;
               // ArrayList<Meal> list = t.meal;







        }
    }
    private static class MyTaskParams {

        ArrayList<CalanderMeal> arrayList;
        Meal meal;

        MyTaskParams(ArrayList<CalanderMeal> arrayList, Meal meal) {

            this.arrayList = arrayList;
            this.meal = meal;

        }


    }
    public class test{
        ArrayList<Meal> meal;

        ArrayList<CalanderMeal> arrayList;


    }

}


