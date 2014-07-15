package ru.calendar;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ListView;

import java.util.ArrayList;

import jBerry.MySugar.R;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class FragmentA extends Fragment {

    protected CalendarView calendar;
    protected ListView listview;



    public FragmentA() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_a, container, false);


        assert view != null;
        calendar = (CalendarView) view.findViewById(R.id.calendarView1);
        listview = (ListView) view.findViewById(R.id.dailyView1);


        return view;
    }

    public static FragmentA newInstance(String text){
        FragmentA f = new FragmentA();
        Bundle b = new Bundle();
        b.putString("msg", text);
        f.setArguments(b);

        return f;
    }
}
