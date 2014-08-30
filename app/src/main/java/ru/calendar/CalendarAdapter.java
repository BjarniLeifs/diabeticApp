package ru.calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jberry.dto.CalanderMeal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import jBerry.MySugar.R;

/*
  Adapter thats renders data to the view, calendarView.
 */
public class CalendarAdapter extends ArrayAdapter<CalanderMeal> {


    private static LayoutInflater inflater = null;
    ArrayList<CalanderMeal> mealsList = new ArrayList<CalanderMeal>();

    public CalendarAdapter(Context context, int layoutResourceID, ArrayList<CalanderMeal> mealsList) {/*, ArrayList<Meal> nutrition*/
        super(context, layoutResourceID);
        inflater = LayoutInflater.from(context);
        this.mealsList = mealsList;

    }

    public int getCount() {
        return mealsList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        if (convertView == null) {
            inflater = LayoutInflater.from(getContext());
            row = inflater.inflate(R.layout.notification_list_item, null);
        }

        TextView mealName = (TextView) row.findViewById(R.id.notificationTitle1);
        TextView time = (TextView) row.findViewById(R.id.timeOfMeal1);
        CalanderMeal c = mealsList.get(position);

        Date date = new Date(c.getTimeOfMeal() * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-0"));

        String timeOfMeal = sdf.format(date);
        mealName.setText(c.getMealName());
        time.setText(timeOfMeal);

        return row;
    }
}
