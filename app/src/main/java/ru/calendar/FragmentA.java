package ru.calendar;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ListView;

import com.jberry.dto.CalanderMeal;

import java.util.ArrayList;

import jBerry.MySugar.R;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class FragmentA extends Fragment {

    private ArrayList<CalanderMeal> calMeals = new ArrayList<CalanderMeal>();
    private ListView listView;
    private CalendarView calendarView;
    private CalendarAdapter adapter;
    private View parentView;
    private int yearSelected; //year selected by user
    private int monthSelected; //month selected by user
    private int dayOfMonthSelected; //day of month selected by user



    public static FragmentA newInstance(String text){
        FragmentA f = new FragmentA();

        return f;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        parentView = inflater.inflate(R.layout.fragment_a, container, false);
        calendarView = (CalendarView) parentView.findViewById(R.id.calendarView1);
        listView = (ListView) parentView.findViewById(R.id.dailyView1);

        adapter = new CalendarAdapter(getActivity(), R.layout.fragment_a, calMeals);
        ListView listview = (ListView)parentView.findViewById(R.id.dailyView1);
        listview.setAdapter(adapter);


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                yearSelected = year;
                monthSelected = month;
                dayOfMonthSelected = dayOfMonth;

                adapter = new CalendarAdapter(getActivity(), R.layout.fragment_a, calMeals);
                ListView listview = (ListView)parentView.findViewById(R.id.dailyView1);
                listview.setAdapter(adapter);

            }
        });

        return parentView;
    }
}
