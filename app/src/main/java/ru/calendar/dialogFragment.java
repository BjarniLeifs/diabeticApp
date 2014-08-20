package ru.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.jberry.dto.Food;
import com.jberry.dto.Meal;

import java.io.IOException;
import java.util.ArrayList;

import jBerry.MySugar.R;
import ru.checkin.CheckinActivity;

public class dialogFragment extends DialogFragment {
    private Button nutritionView, editView, deleteView, toCheckInBtn;
    private Meal  meal = new Meal();
    private ArrayList<Food> nutrition = new ArrayList<Food>();


    public  dialogFragment (){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.calendar_dialog, container,
                false);

        Bundle mArgs = getArguments();
        String itemName = mArgs.getString("fakeName");

        try {
            nutrition =  CalendarAdapter.getFoodInformation(itemName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        getDialog().setTitle("Og hvað svo?");

        nutritionView = (Button) rootView.findViewById(R.id.nutrition2);
        editView = (Button) rootView.findViewById(R.id.edit2);
        deleteView = (Button) rootView.findViewById(R.id.delete2);
        toCheckInBtn = (Button) rootView.findViewById(R.id.toCheckInBtn);

        String pro = Double.toString(nutrition.get(0).getProteins());
        String kol = Double.toString(nutrition.get(0).getTotalCarbohydrates());
        String fit = Double.toString(nutrition.get(0).getFat());

        TextView protein = (TextView) rootView.findViewById(R.id.meal1);
        TextView kolvetni = (TextView) rootView.findViewById(R.id.meal2);
        TextView fita = (TextView) rootView.findViewById(R.id.meal3);

/*      protein.setText("Prótein: " + pro + "gr");
        kolvetni.setText("Kolvetni: " + kol + "gr");
        fita.setText("Fita: " + fit + "gr");
*/

        nutritionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), NutritionPerMealActivity.class);
                startActivity(intent);
            }
        });

        editView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), EditMealActivity.class);
                startActivity(intent);
            }
        });

        deleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
}

