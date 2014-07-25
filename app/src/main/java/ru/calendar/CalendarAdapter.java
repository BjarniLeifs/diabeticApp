package ru.calendar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.jberry.dto.CalanderMeal;
import com.jberry.services.calendar.CalendarService;
import com.jberry.services.calendar.CalendarServiceFactory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import jBerry.MySugar.R;
/**
 * Created by Sindri on 15/07/14.
 */public class CalendarAdapter extends ArrayAdapter<CalanderMeal> {


    private ArrayList<CalanderMeal> list = null;
    private static LayoutInflater inflater=null;



    public CalendarAdapter(Context context, int layoutResourceID,
                           ArrayList<CalanderMeal> calList){
        super(context, layoutResourceID, calList);
        this.list = calList;
    }

    public static List<CalanderMeal> getDayItems(long unixTime){

        List<CalanderMeal> calList;
        CalendarService calService = CalendarServiceFactory.getCalanderService();
        calList = calService.getMealsByDay(unixTime);

        return calList;
    }

    @Override
    public int getCount() {
        return  list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        if(convertView == null)
        {
            inflater = LayoutInflater.from(getContext());
            row = inflater.inflate(R.layout.notification_list_item, null);
        }

        TextView mealName = (TextView)row.findViewById(R.id.notificationTitle);
        //TextView timeOfMeal = (TextView)row.findViewById(R.id.timeOfMeal);

        CalanderMeal c = list.get(position);
        //mealName.setText(c.mealName);




        return row;
    }

}