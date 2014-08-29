package ru.calendar;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jberry.dto.CalanderMeal;
import com.jberry.dto.Food;
import com.jberry.dto.Meal;
import com.jberry.services.calendar.CalendarService;
import com.jberry.services.calendar.CalendarServiceFactory;
import com.jberry.services.food.FoodService;
import com.jberry.services.food.FoodServiceFactory;
import com.jberry.services.meal.MealService;
import com.jberry.services.meal.MealServiceFactory;

import java.io.IOException;
import java.util.ArrayList;

import jBerry.MySugar.R;
import ru.checkin.CheckinActivity;

public class dialogFragment extends DialogFragment {
    private Button nutritionView, editView, deleteView, toCheckInBtn;
    private View rootView;

    public  dialogFragment (){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.calendar_dialog, container,
                false);
        final String mealName = getArguments().getString("mealName");
        final long timeOfMeal = getArguments().getLong("timeOfMeal");


        new getFoodInformation().execute(mealName);


        getDialog().setTitle("Og hvað svo?");

        nutritionView = (Button) rootView.findViewById(R.id.nutrition2);
        editView = (Button) rootView.findViewById(R.id.edit2);
        deleteView = (Button) rootView.findViewById(R.id.delete2);
        toCheckInBtn = (Button) rootView.findViewById(R.id.toCheckInBtn);

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

                Intent intent = new Intent(getActivity(), NutritionPerMealActivity.class);
                startActivity(intent);
            }
        });

        toCheckInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), CheckinActivity.class);
                //intent.putExtra("mealId", nutrition.Meald);
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
        protected void onPostExecute(Boolean result){

            if(result){
                Toast.makeText(getActivity(), "Þessu hefur verið hent!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getActivity(), "Ekki tókst að henda að svo stöddu!", Toast.LENGTH_SHORT).show();
            }
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
        protected void onPostExecute(Meal result){

            String pro = Double.toString(result.getTotalProtien());
            String kol = Double.toString(result.getTotalFiber());
            String fit = Double.toString(result.getTotalFat());

            TextView protein = (TextView) rootView.findViewById(R.id.meal1);
            TextView kolvetni = (TextView) rootView.findViewById(R.id.meal2);
            TextView fita = (TextView) rootView.findViewById(R.id.meal3);

            protein.setText("Prótein: " + pro + "gr");
            kolvetni.setText("Kolvetni: " + kol + "gr");
            fita.setText("Fita: " + fit + "gr");

        }
    }
}

