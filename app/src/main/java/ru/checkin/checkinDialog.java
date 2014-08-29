package ru.checkin;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jberry.dto.Diabetic;
import com.jberry.dto.FoodTO;
import com.jberry.dto.Meal;
import com.jberry.services.diabetic.DiabeticService;
import com.jberry.services.diabetic.DiabeticServiceFactory;
import com.jberry.services.meal.MealService;
import com.jberry.services.meal.MealServiceFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import jBerry.MySugar.R;
import ru.Events.Events;
import ru.menu.MenuActivity;

/**
 * Created by Sindri on 15/08/14.
 */
public class checkinDialog extends DialogFragment {

    final Diabetic diabetic = new Diabetic();

    public checkinDialog() {
    }

    Context context;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View rootView = inflater.inflate(R.layout.checkin_dialog, container, false);


        TextView insulinView = (TextView) rootView.findViewById(R.id.insulinView);
        Button calcel = (Button) rootView.findViewById(R.id.canselCheckInInsulinBtn);
        Button confirm = (Button) rootView.findViewById(R.id.confirmCheckInBtn);


        Bundle mArgs = getArguments();
        long myValue = (long) mArgs.getDouble("insulinUnits");
        final String value = String.valueOf(myValue);
        insulinView.setText(value + " einingar");


        Date date = new Date();
        long unixTime = date.getTime() / 1000L;

        diabetic.setLastDoseTime(unixTime);
        diabetic.setLastDoseAmount(myValue);

        getDialog().setTitle("Áætlaður útreikningur");

        calcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CheckinActivity.class);
                startActivity(intent);
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MenuActivity.class);

                new finishCheckin().execute(diabetic);

                startActivity(intent);
            }
        });
        return rootView;
    }


    private class finishCheckin extends AsyncTask<Diabetic, Meal, Boolean> {

        protected Boolean doInBackground(Diabetic... params) {

            DiabeticService service = DiabeticServiceFactory.getDiabeticService();

            Diabetic diabetic = params[0];
            boolean i = false;
            try {
                i = service.finishCheckIn(diabetic);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return i;

        }

        @Override
        protected void onPostExecute(Boolean t) {

        }
    }


}
