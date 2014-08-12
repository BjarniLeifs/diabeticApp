package ru.calendar;

import android.annotation.TargetApi;


import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.jberry.dto.CalanderMeal;
import com.jberry.dto.Meal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import jBerry.MySugar.R;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class FragmentA extends Fragment {

    private ArrayList<CalanderMeal> eventList = new ArrayList<CalanderMeal>();
    private Meal nutrition = new Meal();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.US);
    GregorianCalendar cal = new GregorianCalendar(TimeZone.getTimeZone("US/Central"));
    private Button nutritionView, editView, deleteView;

    private FragmentManager fManager;

    public static FragmentA newInstance(String text){
        FragmentA f = new FragmentA();
        return f;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View parentView = inflater.inflate(R.layout.fragment_a, container, false);
        final CalendarView calendarView = (CalendarView) parentView.findViewById(R.id.calendarView1);
        cal.setTimeInMillis(calendarView.getDate());

        eventList = (ArrayList<CalanderMeal>) CalendarAdapter.getDayItems(Long.parseLong(sdf.format(cal.getTime())));
        nutrition = (Meal) CalendarAdapter.getNutrition();

        ListAdapter adapter = new CalendarAdapter(getActivity(), R.layout.notification_list_item, eventList, nutrition);
        ListView listview = (ListView) parentView.findViewById(R.id.eventList);
        listview.setAdapter(adapter);


        listview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fManager = getFragmentManager();

                dialogFragment dialog = new dialogFragment();

                dialog.show(fManager, "stuff");
            }
        });


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                cal.setTimeInMillis(calendarView.getDate());
                eventList = (ArrayList<CalanderMeal>) CalendarAdapter.getDayItems(Long.parseLong(sdf.format(cal.getTime())));
                nutrition = (Meal) CalendarAdapter.getNutrition();

                ListAdapter adapter = new CalendarAdapter(getActivity(), R.layout.notification_list_item, eventList, nutrition);
                ListView listview = (ListView) parentView.findViewById(R.id.eventList);
                listview.setAdapter(adapter);
            }
        });

        return parentView;
    }



}
