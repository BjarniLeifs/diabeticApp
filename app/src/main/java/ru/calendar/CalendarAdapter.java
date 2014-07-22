package ru.calendar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jberry.dto.CalanderMeal;
import com.jberry.services.calander.CalanderService;
import com.jberry.services.calander.CalanderServiceFactory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import jBerry.MySugar.R;


/**
 * Created by Sindri on 15/07/14.
 */
public class CalendarAdapter extends BaseAdapter {


    private ArrayList<CalanderMeal> mCalList;

    private CalanderService calService;


    public CalendarAdapter(ArrayList<CalanderMeal> calList){
        this.mCalList = calList;
    }

    public Object getDayItems(){

        ArrayList<CalanderMeal> calList;
        calService = CalanderServiceFactory.getCalanderService();
        calList = calService.getMealsByDay();

        return calList;
    }


    @Override
    public int getCount() {
        return  mCalList.size();
    }

    @Override
    public Object getItem(int position) {
        return mCalList.get(position);
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