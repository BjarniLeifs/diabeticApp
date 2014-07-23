package ru.calendar;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.jberry.dto.CalanderMeal;
import com.jberry.services.calendar.CalendarService;
import com.jberry.services.calendar.CalendarServiceFactory;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Sindri on 15/07/14.
 */
public class CalendarAdapter extends ArrayAdapter<CalanderMeal> {


    private ArrayList<CalendarMeal> mCalList;

    private CalendarService calService;


    public CalendarAdapter(Context context, int layoutResourceID, ArrayList<CalanderMeal> calList){
       super(context, layoutResourceID, calList);
        this.mCalList = calList;

    }

    public Object getDayItems(){

        List<CalanderMeal> calList;
        calService = CalendarServiceFactory.getCalanderService();
        calList = calService.getMealsByDay();

        return calList;
    }


    @Override
    public int getCount() {
        return  mCalList.size();
    }



    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

}