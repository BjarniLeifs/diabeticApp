package ru.checkin;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jberry.dto.Diabetic;
import com.jberry.dto.Meal;

import java.io.IOException;

import jBerry.MySugar.R;
import ru.calendar.CalendarAdapter;
import ru.menu.MenuActivity;

/**
 * Created by Sindri on 15/08/14.
 */
public class checkinDialog extends DialogFragment {

    public checkinDialog(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View rootView = inflater.inflate(R.layout.checkin_dialog, container, false);



        TextView insulinView = (TextView) rootView.findViewById(R.id.insulinView);
        Button cancel = (Button) rootView.findViewById(R.id.canselCheckInInsulinBtn);
        Button confirm = (Button) rootView.findViewById(R.id.confirmCheckInBtn);


        Bundle mArgs = getArguments();
        final int myValue = mArgs.getInt("insulinUnits");
        final String value = String.valueOf(myValue);
        insulinView.setText(value + " einingar");

        getDialog().setTitle("Áætlaður útreikningur");

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CheckinActivity.class);
                startActivity(intent);
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double i = Double.valueOf(myValue);
                long unixTime = System.currentTimeMillis() / 1000L;

                Diabetic diabetic = new Diabetic();
                diabetic.setLastDoseAmount(i);
                diabetic.setLastDoseTime(unixTime);

                try {
                    CalendarAdapter.finishCheckIn(diabetic);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

         return rootView;
    }
}
