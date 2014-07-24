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
import java.util.List;

import jBerry.MySugar.R;
/**
 * Created by Sindri on 15/07/14.
 */public class CalendarAdapter extends ArrayAdapter<CalanderMeal> {


    private ArrayList<CalanderMeal> mCalList = null;
    private static LayoutInflater inflater=null;

    public CalendarAdapter(Context context, int layoutResourceID,
                           ArrayList<CalanderMeal> calList){
        super(context, layoutResourceID, calList);
        this.mCalList = calList;
    }

    public static List<CalanderMeal> getDayItems(long unixTime){

        List<CalanderMeal> calList;
        CalendarService calService = CalendarServiceFactory.getCalanderService();
        calList = calService.getMealsByDay(unixTime);

        return calList;
    }

    @Override
    public int getCount() {
        return  mCalList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private static class ViewHolder {
        TextView mealName;
        TextView timeOfMeal;
        TextView userId;
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
        TextView timeOfMeal = (TextView)row.findViewById(R.id.notificationDescription);

        CalanderMeal c = mCalList.get(position);
        mealName.setText(c.mealName);
        timeOfMeal.setText(c.timeOfMeal);

        return row;

/*        ViewHolder viewHolder;
        CalanderMeal calendarMeal = getItem(position);


        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.fragment_a, null);
            viewHolder.mealName = (TextView) convertView.findViewById(R.id.eventList);
            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.mealName.setText(calendarMeal.mealName);

        return convertView;
*/
    }

}