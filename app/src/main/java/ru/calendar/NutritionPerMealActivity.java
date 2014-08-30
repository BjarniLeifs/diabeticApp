package ru.calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.jberry.dto.Food;
import com.jberry.services.food.FoodService;
import com.jberry.services.food.FoodServiceFactory;

import java.io.IOException;
import java.util.ArrayList;

import jBerry.MySugar.R;

/*
    Function to see nutrition for each meal.
 */
public class NutritionPerMealActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutritioninfo);

        String foodName = null;
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            foodName = bundle.getString("foodName");
        }

        new getFoodInformationTotal().execute(foodName);
    }


    private class getFoodInformationTotal extends AsyncTask<String, String, ArrayList<Food>> {

        @Override
        protected ArrayList<Food> doInBackground(String... params) {

            FoodService service = FoodServiceFactory.getFoodService();
            ArrayList<Food> foo = null;
            try {
                foo = service.getFoodInformation(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return foo;
        }

        @Override
        protected void onPostExecute(ArrayList<com.jberry.dto.Food> result) {

            if(result.size() == 0){

                Toast.makeText(getBaseContext(), "Aðgerð tókst ekki að svo stöddu...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NutritionPerMealActivity.this, CalendarActivity.class);
                startActivity(intent);

            }else{

                String pro = Double.toString(result.get(0).getProteins());
                String kol = Double.toString(result.get(0).getFiber());
                String fit = Double.toString(result.get(0).getFat());
                String chol = Double.toString(result.get(0).getCholesterol());
                String sug = Double.toString(result.get(0).getSugars());

                TextView protein = (TextView) findViewById(R.id.protein);
                TextView kolvetni = (TextView) findViewById(R.id.kolvetni);
                TextView fita = (TextView) findViewById(R.id.fita);
                TextView kolesterol = (TextView) findViewById(R.id.kolesterol);
                TextView sugars = (TextView) findViewById(R.id.vidbaettursykur);

                protein.setText(pro);
                protein.setText("Prótein: " + pro + "gr");
                kolvetni.setText("Kolvetni: " + kol + "gr");
                fita.setText("Fita: " + fit + "gr");
                kolesterol.setText("Kolesterol: " + chol + "gr");
                sugars.setText("Viðbættur sykur: " + sug + "gr");
            }
        }
    }
}
