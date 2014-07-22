package ru.calendar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import jBerry.MySugar.R;

/**
 * Created by Sindri on 15/07/14.
 */
public class EventListAdapter extends ArrayAdapter<Event> {

    private List<Event> data = null;
    private static LayoutInflater inflater=null;

    public EventListAdapter(Context context, int layoutResourceId, List<Event> data) {
        super(context, layoutResourceId, data);
        this.data = data;

    }

    @SuppressLint("DefaultLocale")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if(convertView == null){
            inflater = LayoutInflater.from(getContext());
            row = inflater.inflate(R.layout.fragment_a, null);
        }

        TextView title = (TextView)row.findViewById(R.id.calendarView1);
        ListView event = (ListView)row.findViewById(R.id.dailyView1);

        Event

        Event e = data.get(e.getDate());
        title.setText(e.getTitle());

        return row;
    }


}