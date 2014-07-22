package ru.calendar;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;

import com.jberry.dto.CalanderMeal;

import java.util.ArrayList;

import jBerry.MySugar.R;
import ru.calendar.databaseObjects.Event;

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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        parentView = inflater.inflate(R.layout.fragment_a, container, false);
        listView = (ListView) parentView.findViewById(R.id.dailyView1);
        calendarView = (CalendarView) parentView.findViewById(R.id.calendarView1);


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                yearSelected = year;
                monthSelected = month;
                dayOfMonthSelected = dayOfMonth;

            }
        });


        initView();
        return parentView;




/*
        cal.setTimeInMillis(calendarView1.getDate());
        eventList = CalendarActivity.datasource.getEvent(Integer.parseInt(sdf.format(cal.getTime())));

        ListAdapter adapter = new EventListAdapter(getActivity(), R.layout.fragment_a, eventList);

        ListView listview = (ListView)root.findViewById(R.id.dailyView1);
        listView.setAdapter(new ArrayAdapter<Event>(this,
                android.R.layout.simple_list_item_1, eventToView));
*/

/*
        listview.setAdapter(adapter);

        calendarView1.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,int dayOfMonth) {
               ListAdapter adapter = new EventListAdapter(getActivity(), R.layout.fragment_a, eventList);
                ListView listview = (ListView)root.findViewById(R.id.dailyView1);
                listview.setAdapter(adapter);
            }
        });

*/


    }

    private void initView(){



        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                getCalendarData());
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), "Clicked item!", Toast.LENGTH_LONG).show();
            }
        });




    }
/*
    private ArrayList<String> getCalendarData(){
        ArrayList<String> calendarList = new ArrayList<String>();
        calendarList.add("New Year's Day");
        calendarList.add("St. Valentine's Day");
        calendarList.add("Easter Day");
        calendarList.add("April Fool's Day");
        calendarList.add("Mother's Day");
        calendarList.add("Memorial Day");
        calendarList.add("National Flag Day");
        calendarList.add("Father's Day");
        calendarList.add("Independence Day");
        calendarList.add("Labor Day");
        calendarList.add("Columbus Day");
        calendarList.add("Halloween");
        calendarList.add("All Soul's Day");
        calendarList.add("Veterans Day");
        calendarList.add("Thanksgiving Day");
        calendarList.add("Election Day");
        calendarList.add("Forefather's Day");
        calendarList.add("Christmas Day");
        return calendarList;
    }*/


}
