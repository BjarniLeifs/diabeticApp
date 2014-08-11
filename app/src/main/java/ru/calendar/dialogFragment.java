package ru.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.jberry.dto.Meal;

import jBerry.MySugar.R;

public class dialogFragment extends DialogFragment {
    private Button nutritionView, editView, deleteView;
    private Meal nutrition = new Meal();

    public  dialogFragment (){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.calendar_dialog, container,
                false);
        nutrition = (Meal) CalendarAdapter.getNutrition();

        getDialog().setTitle("Og hvað svo?");

        nutritionView = (Button) rootView.findViewById(R.id.nutrition2);
        editView = (Button) rootView.findViewById(R.id.edit2);
        deleteView = (Button) rootView.findViewById(R.id.delete2);

        String pro = Float.toString(nutrition.PróteinAlls);
        String kol = Float.toString(nutrition.KolvetniAlls);
        String fit = Float.toString(nutrition.FitaAlls);

        TextView protein = (TextView) rootView.findViewById(R.id.meal1);
        TextView kolvetni = (TextView) rootView.findViewById(R.id.meal2);
        TextView fita = (TextView) rootView.findViewById(R.id.meal3);

        protein.setText("Prótein: " + pro + "gr");
        kolvetni.setText("Kolvetni: " + kol + "gr");
        fita.setText("Fita: " + fit + "gr");


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


        return rootView;
    }
}

