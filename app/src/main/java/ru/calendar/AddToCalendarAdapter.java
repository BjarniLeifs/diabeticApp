package ru.calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import com.jberry.dto.CalanderMeal;
import com.jberry.services.calendar.CalendarService;
import com.jberry.services.calendar.CalendarServiceFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
 * Created by Sindri on 01/08/14.
 */
public class AddToCalendarAdapter extends ArrayAdapter<CalanderMeal>{

    public AddToCalendarAdapter(Context context, int layoutResourceID, ArrayList<CalanderMeal> list){
        super(context, layoutResourceID, list);

    }

    public static HashMap addMeal(Map items, long timestamp, int hour, int min){
        CalendarService service = CalendarServiceFactory.getCalanderService();
        //service.addMealsByDay(items, timestamp, hour, min);
        return null;
    }

}
