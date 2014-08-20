package ru.calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jberry.dto.Food;
import com.jberry.dto.Meal;

import java.io.IOException;
import java.util.ArrayList;

import jBerry.MySugar.R;

public class NutritionPerMealActivity extends Activity  {

    private ArrayList<Food> nutrition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutritioninfo);
        String mealName = null;

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            mealName = bundle.getString("mealName");
        }


        try {
            nutrition = CalendarAdapter.getFoodInformation(mealName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // TODO Passa að það taki ekki alltaf bara úr núlta stakinu.
        String pro = Double.toString(nutrition.get(0).getProteins());
        String kol = Double.toString(nutrition.get(0).getTotalCarbohydrates());
        String fit = Double.toString(nutrition.get(0).getFat());
        String satFat = Double.toString(nutrition.get(0).getSugars());
        String chol = Double.toString(nutrition.get(0).getCholesterol());
        String fib = Double.toString(nutrition.get(0).getFiber());

        TextView protein = (TextView) findViewById(R.id.proteinDialog);
        TextView kolvetni = (TextView) findViewById(R.id.kolvetniDialog);
        TextView fita = (TextView) findViewById(R.id.fitaDialog);
        TextView saturatedFat = (TextView) findViewById(R.id.satFatDialog);
        TextView cholestrol = (TextView) findViewById(R.id.CholestrolDialog);
        TextView fiber = (TextView) findViewById(R.id.fiberDialog);

        protein.setText("Prótein: " + pro);
        kolvetni.setText("Kolvetni: " + kol);
        fita.setText("Fita: " + fit);
        saturatedFat.setText("Sykrur: " + satFat);
        cholestrol.setText("Kólesteról: " + chol);
        fiber.setText("Trefjar: " + fib);



    }

}
