package ru.History;

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

/**
 * Created by Sindri on 12/08/14.
 */
public class HistoryAdapter extends ArrayAdapter<CalanderMeal> {
    private ArrayList<CalanderMeal> historyList = null;
    private static LayoutInflater inflater=null;


    public HistoryAdapter(Context context, int resource, ArrayList<CalanderMeal> historyList) {
        super(context, resource, historyList);
            this.historyList = historyList;


    }

    @Override
    public int getCount() {
        return  historyList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        if(convertView == null)
        {
            inflater = LayoutInflater.from(getContext());
            row = inflater.inflate(R.layout.history_list_layout, null);
        }


        TextView historyDate = (TextView) row.findViewById(R.id.historyDate);
        TextView historyTime = (TextView) row.findViewById(R.id.historyTime);
        TextView historyBloodSugerLevel = (TextView) row.findViewById(R.id.historyBloodSugerLevel);
        TextView historyNote = (TextView) row.findViewById(R.id.historyNotes);


        CalanderMeal c = historyList.get(position);

        Date date = new Date(c.timeOfMeal*1000L);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // the format of your date
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-0"));
        String dateit = sdf.format(date);

        SimpleDateFormat sdfit = new SimpleDateFormat("HH:mm"); // the format of your date
        sdfit.setTimeZone(TimeZone.getTimeZone("GMT-0"));
        String timeit = sdf.format(date);

        historyDate.setText(dateit);
        historyTime.setText(timeit);
        historyBloodSugerLevel.setText(c.mealName);
        historyNote.setText(c.mealName + c.mealName + c.mealName);

        return row;
    }


}
