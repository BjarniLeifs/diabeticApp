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
import com.jberry.dto.Meal;
import com.jberry.services.calendar.CalendarService;
import com.jberry.services.calendar.CalendarServiceFactory;
import com.jberry.services.meal.MealService;
import com.jberry.services.meal.MealServiceFactory;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import jBerry.MySugar.R;
/*
 * Created by Sindri on 15/07/14.
 */public class CalendarAdapter extends ArrayAdapter<CalanderMeal> {


    private ArrayList<CalanderMeal> list = null;
    private Meal nutrition = null;
    private static LayoutInflater inflater=null;





    public CalendarAdapter(Context context, int layoutResourceID,
                           ArrayList<CalanderMeal> calList, Meal _nutrition){

        super(context, layoutResourceID, calList);
        this.list = calList;
        this.nutrition = _nutrition;
    }

    public static List<CalanderMeal> getDayItems(long unixTime){

        List<CalanderMeal> calList;
        CalendarService calService = CalendarServiceFactory.getCalanderService();
        calList = calService.getMealsByDay(unixTime);


        return calList;
    }

    public static Object getNutrition(){

        Object _mealByName;
        MealService calService = MealServiceFactory.getMealService();
        _mealByName = calService.getMealByName();
        return _mealByName;
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
        TextView nuteInfo = (TextView)row.findViewById(R.id.description);
        TextView time = (TextView)row.findViewById(R.id.timeOfMeal);

        CalanderMeal c = list.get(position);

        Date date = new Date(c.timeOfMeal*1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm"); // the format of your date
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-0"));




        String timeOfMeal = sdf.format(date);


        String s = Float.toString(nutrition.PróteinAlls);
        float kolvetni = nutrition.KolvetniAlls;
        float fita = nutrition.FitaAlls;

        mealName.setText(c.mealName);
        nuteInfo.setText(s);
        time.setText(timeOfMeal);



        return row;
    }

}