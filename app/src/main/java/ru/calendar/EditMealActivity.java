package ru.calendar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jberry.dto.Food;
import com.jberry.dto.FoodTO;
import com.jberry.dto.Meal;
import com.jberry.services.food.FoodService;
import com.jberry.services.food.FoodServiceFactory;
import com.jberry.services.meal.MealService;
import com.jberry.services.meal.MealServiceFactory;

import java.io.IOException;
import java.util.ArrayList;

import jBerry.MySugar.R;
import ru.Events.Events;

public class EditMealActivity extends ActionBarActivity {


    String name;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_meal);


        String mealName = null;
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            mealName = bundle.getString("mealName");
        }
        name = mealName;

        new getIngredients().execute(mealName);


        EditText item1 = (EditText) findViewById(Events.idItems[0]);
        EditText item2 = (EditText) findViewById(Events.idItems[1]);
        EditText item3 = (EditText) findViewById(Events.idItems[2]);
        EditText item4 = (EditText) findViewById(Events.idItems[3]);
        EditText item5 = (EditText) findViewById(Events.idItems[4]);
        TextWatcher tw = new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {


                AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.item1);
                String s = actv.getText().toString();
                new MatisConnection().execute(s);

                AutoCompleteTextView actv2 = (AutoCompleteTextView) findViewById(R.id.item2);
                String s2 = actv2.getText().toString();
                new MatisConnection().execute(s2);

                AutoCompleteTextView actv3 = (AutoCompleteTextView) findViewById(R.id.item3);
                String s3 = actv3.getText().toString();
                new MatisConnection().execute(s3);

                AutoCompleteTextView actv4 = (AutoCompleteTextView) findViewById(R.id.item4);
                String s4 = actv4.getText().toString();
                new MatisConnection().execute(s4);

                AutoCompleteTextView actv5 = (AutoCompleteTextView) findViewById(R.id.item5);
                String s5 = actv5.getText().toString();
                new MatisConnection().execute(s5);
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {


            }
        };
        item1.addTextChangedListener(tw);
        item2.addTextChangedListener(tw);
        item3.addTextChangedListener(tw);
        item4.addTextChangedListener(tw);
        item5.addTextChangedListener(tw);


        btn = (Button) findViewById(R.id.editSaveBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getBaseContext(), "Dokaðu...", Toast.LENGTH_SHORT).show();

                ArrayList<FoodTO> list = new ArrayList<FoodTO>();

                for (int i = 0; i < 5; i++) {
                    AutoCompleteTextView item = (AutoCompleteTextView) findViewById(Events.idItems[i]);
                    EditText gram = (EditText) findViewById(Events.idGrams[i]);


                    if (item.getText().toString().length() > 0) {
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

    private static class MyTaskParams {
        String mealName;
        ArrayList<FoodTO> ingredients;


        MyTaskParams(ArrayList<FoodTO> ingredients, String mealName) {


            this.mealName = mealName;
            this.ingredients = ingredients;

        }

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
        protected void onPostExecute(Meal result) {


            ArrayList<FoodTO> ingrdients = new ArrayList<FoodTO>(result.getIngredients());

            //final ArrayAdapter<FoodTO> adapter = new ArrayAdapter<FoodTO>(this, android.R.layout.select_dialog_item, ingrdients);

            for (int i = 0; i < ingrdients.size(); i++) {
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

    private class EditFood extends AsyncTask<MyTaskParams, Boolean, Boolean> {

        @Override
        protected Boolean doInBackground(MyTaskParams... params) {


            MealService service = MealServiceFactory.getMealService();
            String mealName = params[0].mealName;
            ArrayList<FoodTO> ingredients = params[0].ingredients;
            boolean i = false;
            try {
                i = service.editMeal(mealName, ingredients);
            } catch (IOException e) {
                e.printStackTrace();
            }


            return i;
        }

        //  @Override
        protected void onPostExecute(Boolean result) {

            Toast.makeText(getBaseContext(), "Máltíð hefur verið breytt", Toast.LENGTH_SHORT).show();


            Intent intent = new Intent(EditMealActivity.this, CalendarActivity.class);
            startActivity(intent);


        }


    }

    private class MatisConnection extends AsyncTask<String, ArrayList<com.jberry.dto.Food>, ArrayList<com.jberry.dto.Food>> {

        protected ArrayList<com.jberry.dto.Food> doInBackground(String... params) {

            ArrayList<Food> food = new ArrayList<Food>();
            if (params[0].equals("")) {
                return food;
            } else {
                try {
                    food = matisCaller(params[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            return food;
        }

        @Override
        protected void onPostExecute(ArrayList<com.jberry.dto.Food> result) {

            if (result.size() > 0) {
                String[] strings = new String[10];
                for (int i = 0; i < strings.length; i++) {
                    strings[i] = result.get(0).getNameEng();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.select_dialog_item, strings);

                AutoCompleteTextView actv1 = (AutoCompleteTextView) findViewById(R.id.item1);
                actv1.setThreshold(4);
                actv1.setAdapter(adapter);

                AutoCompleteTextView actv2 = (AutoCompleteTextView) findViewById(R.id.item2);
                actv2.setThreshold(4);
                actv2.setAdapter(adapter);

                AutoCompleteTextView actv3 = (AutoCompleteTextView) findViewById(R.id.item3);
                actv3.setThreshold(4);
                actv3.setAdapter(adapter);

                AutoCompleteTextView actv4 = (AutoCompleteTextView) findViewById(R.id.item4);
                actv4.setThreshold(4);
                actv4.setAdapter(adapter);

                AutoCompleteTextView actv5 = (AutoCompleteTextView) findViewById(R.id.item5);
                actv5.setThreshold(4);
                actv5.setAdapter(adapter);

            }

        }

        public ArrayList<com.jberry.dto.Food> matisCaller(String list) throws Exception {
            ArrayList<Food> anna;
            FoodService service = FoodServiceFactory.getFoodService();
            anna = service.getFoodInformation(list);
            return anna;
        }
    }


}