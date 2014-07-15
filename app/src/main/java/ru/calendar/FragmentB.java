package ru.calendar;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jBerry.MySugar.R;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class FragmentB extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_b, container, false);

        //TextView tv = (TextView) v.findViewById(R.id.tvFragFirst);
        //tv.setText(getArguments().getString("msg"));

        return v;
    }

    public static FragmentB newInstance(String text){
        FragmentB f = new FragmentB();
        Bundle b = new Bundle();
        b.putString("msg", text);
        f.setArguments(b);

        return f;
    }


}
