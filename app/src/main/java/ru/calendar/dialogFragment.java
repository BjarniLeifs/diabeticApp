package ru.calendar;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jberry.dto.Meal;
import com.jberry.services.calendar.CalendarService;
import com.jberry.services.calendar.CalendarServiceFactory;
import com.jberry.services.meal.MealService;
import com.jberry.services.meal.MealServiceFactory;

import java.io.IOException;

import jBerry.MySugar.R;
import ru.checkin.CheckinActivity;

/*
    A dialog that gives user the options to choose what he want to do with the row he clicked on in CalendarList.
    He has the option to choose:
        * See nutrition for the meal
        * Chance the meal
        * Delete the meal
        * Checkin the meal
 */
public class dialogFragment extends DialogFragment {
    private Button nutritionView, editView, deleteView, toCheckInBtn;
    private View rootView;

    public dialogFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.calendar_dialog, container,
                false);
        final String mealName = getArguments().getString("mealName");
        final long timeOfMeal = getArguments().getLong("timeOfMeal");
        getDialog().setTitle("Og hvað svo?");

        nutritionView = (Button) rootView.findViewById(R.id.nutrition2);
        editView = (Button) rootView.findViewById(R.id.edit2);
        deleteView = (Button) rootView.findViewById(R.id.delete2);
        toCheckInBtn = (Button) rootView.findViewById(R.id.toCheckInBtn);

        new getFoodInformation().execute(mealName);

        nutritionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), NutritionPerMealActivity.class);
                intent.putExtra("foodName", mealName);
                startActivity(intent);
            }
        });

        editView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), EditMealActivity.class);
                intent.putExtra("mealName", mealName);
                startActivity(intent);
            }
        });

        deleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DeleteMeal().execute(timeOfMeal);
                Intent intent = new Intent(getActivity(), CalendarActivity.class);
                startActivity(intent);
            }
        });

        toCheckInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), CheckinActivity.class);
                intent.putExtra("mealName", mealName);
                startActivity(intent);
            }
        });
        return rootView;
    }

    private class DeleteMeal extends AsyncTask<Long, Boolean, Boolean> {
        protected Boolean doInBackground(Long... params) {

            CalendarService service = CalendarServiceFactory.getCalanderService();

            try {
                return service.deleteFromCalendar(params[0]);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Boolean result) {

            Toast.makeText(getActivity(), "Máltíð hefur verið eytt!", Toast.LENGTH_SHORT).show();
        }
    }

    private class getFoodInformation extends AsyncTask<String, Meal, Meal> {

        Meal meal;

        @Override
        protected Meal doInBackground(String... params) {

            MealService service = MealServiceFactory.getMealService();
            String Time = params[0];
            try {
                meal = service.getMealByName(Time);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return meal;
        }

        @Override
        protected void onPostExecute(Meal result) {

            String pro = Double.toString(Math.round(result.getTotalProtien()));
            String kol = Double.toString(Math.round(result.getTotalFiber()));
            String fit = Double.toString(Math.round(result.getTotalFat()));

            TextView protein = (TextView) rootView.findViewById(R.id.meal1);
            TextView kolvetni = (TextView) rootView.findViewById(R.id.meal2);
            TextView fita = (TextView) rootView.findViewById(R.id.meal3);

            protein.setText("Prótein: " + pro + "gr");
            kolvetni.setText("Kolvetni: " + kol + "gr");
            fita.setText("Fita: " + fit + "gr");

        }
    }
}

