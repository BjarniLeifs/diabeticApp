package ru.calendar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jberry.dto.Food;
import com.jberry.dto.FoodTO;
import com.jberry.dto.Meal;

import com.jberry.services.meal.MealService;
import com.jberry.services.meal.MealServiceFactory;

import java.io.IOException;
import java.util.ArrayList;
import jBerry.MySugar.R;
import ru.Events.Events;

public class EditMealActivity extends ActionBarActivity {


    private Button btn;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_meal);


        String mealName = null;
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            mealName = bundle.getString("mealName");
        }
        name = mealName;

        new getIngredients().execute(mealName);





        // getMealById returns: mango, apple, banana
        //mealList = CalendarAdapter.getMealById();
        // ArrayList<Integer> gramIngrdients = new ArrayList<Integer>(mealList.Ingredients.values());


        // final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, listIngrdients);

        /*for(int i = 0; i < listIngrdients.size(); i++){
            AutoCompleteTextView item = (AutoCompleteTextView) findViewById(Events.idItems[i]);
            EditText gram = (EditText) findViewById(Events.idGrams[i]);
            item.setText(listIngrdients.get(i));
            item.setThreshold(1);
            item.setAdapter(adapter);

            gram.setText("" + gramIngrdients.get(i));
        }*/

        btn = (Button) findViewById(R.id.editSaveBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



               /* MyTaskParams params = new MyTaskParams();

                EditFood editFood = new EditFood();
                editFood.execute(params);
*/
                ArrayList<FoodTO> list = new ArrayList<FoodTO>();

                for(int i=0; i<5; i++){
                    AutoCompleteTextView item = (AutoCompleteTextView)findViewById(Events.idItems[i]);
                    EditText gram = (EditText) findViewById(Events.idGrams[i]);


                    if(item.getText().toString().length() > 0){
                        String foody = item.getText().toString();
                        int grams = Integer.parseInt(gram.getText().toString());
                        list.add(new FoodTO(foody, grams));
                    }
                }

                MyTaskParams params = new MyTaskParams(list, name);
                EditFood editIt = new EditFood();
                editIt.execute(params);


            }
        });
    }




    private class getIngredients extends AsyncTask<String, String, Meal> {

        @Override
        protected Meal doInBackground(String... params) {

            MealService service = MealServiceFactory.getMealService();
            String name = params[0];
            Meal meal = null;
            try {
                meal = service.getMealByName(name);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return meal;
        }
        @Override
        protected void onPostExecute(Meal result){


            ArrayList<FoodTO> ingrdients = new ArrayList<FoodTO>(result.getIngredients());

            //final ArrayAdapter<FoodTO> adapter = new ArrayAdapter<FoodTO>(this, android.R.layout.select_dialog_item, ingrdients);

            for(int i = 0; i < ingrdients.size(); i++){
                String name = ingrdients.get(i).getFoodName();
                String gramValue = String.valueOf(ingrdients.get(i).getGrams());

                AutoCompleteTextView item = (AutoCompleteTextView) findViewById(Events.idItems[i]);
                EditText gram = (EditText) findViewById(Events.idGrams[i]);
                //item.setThreshold(1);
                //item.setAdapter(adapter);

                item.setText("" + name);
                gram.setText("" + gramValue);
            }


        }

    }


    private static class MyTaskParams {
        String mealName;
        ArrayList<FoodTO> ingredients;


        MyTaskParams(ArrayList<FoodTO> ingredients, String mealName) {




            this.mealName = mealName;
            this.ingredients = ingredients;

        }

    }

    private class EditFood extends AsyncTask<MyTaskParams, Boolean, Boolean> {

        @Override
        protected Boolean doInBackground(MyTaskParams... params) {


            MealService service = MealServiceFactory.getMealService();
            String mealName = params[0].mealName;
            ArrayList<FoodTO> ingredients = params[0].ingredients;
            try {
                return service.editMeal(mealName, ingredients);
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        //  @Override
        protected void onPostExecute(Boolean result){

            Intent intent = new Intent(EditMealActivity.this, CalendarActivity.class);
            startActivity(intent);
          /*  if(result){
                Toast.makeText(getBaseContext(), "Máltíð hefur verið breytt", Toast.LENGTH_SHORT).show();
            }else{

                Toast.makeText(getBaseContext(), "Máltíð var ekki breytt", Toast.LENGTH_SHORT).show();

            }
*/
        }


    }

}