package ru.calendar;

import android.app.Activity;
import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.view.View.OnClickListener;
import android.widget.Toast;


import com.jberry.dto.*;
import com.jberry.dto.Food;
import com.jberry.services.calendar.CalendarService;
import com.jberry.services.calendar.CalendarServiceFactory;
import com.jberry.services.food.FoodService;
import com.jberry.services.food.FoodServiceFactory;
import com.jberry.services.meal.MealService;
import com.jberry.services.meal.MealServiceFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import jBerry.MySugar.R;
import ru.Events.Events;

public class AddMealToActivity extends Activity {

    private Button btnSave;
    long timestamp;

    String mealName, foody;
    int grams;
    GregorianCalendar cal = new GregorianCalendar(TimeZone.getTimeZone("US/Central"));
    private ArrayList<FoodTO> mealList = new ArrayList<FoodTO>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);


        final CalendarView calendarView = (CalendarView) findViewById(R.id.addCalendarView);
        final TimePicker timePicker = (TimePicker) findViewById(R.id.timePickerView);
        timePicker.setIs24HourView(true);

        EditText item1 = (EditText) findViewById(Events.idItems[0]);
        EditText item2 = (EditText) findViewById(Events.idItems[1]);
        EditText item3 = (EditText) findViewById(Events.idItems[2]);
        EditText item4 = (EditText) findViewById(Events.idItems[3]);
        EditText item5 = (EditText) findViewById(Events.idItems[4]);
        TextWatcher tw = new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {


                AutoCompleteTextView actv = (AutoCompleteTextView)findViewById(R.id.item1);
                String s = actv.getText().toString();
                new MatisConnection().execute(s);

                AutoCompleteTextView actv2 = (AutoCompleteTextView)findViewById(R.id.item2);
                String s2 = actv2.getText().toString();
                new MatisConnection().execute(s2);

                AutoCompleteTextView actv3 = (AutoCompleteTextView)findViewById(R.id.item3);
                String s3 = actv3.getText().toString();
                new MatisConnection().execute(s3);

                AutoCompleteTextView actv4 = (AutoCompleteTextView)findViewById(R.id.item4);
                String s4 = actv4.getText().toString();
                new MatisConnection().execute(s4);

                AutoCompleteTextView actv5 = (AutoCompleteTextView)findViewById(R.id.item5);
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


        btnSave = (Button) findViewById(R.id.saveAddBtn);
        btnSave.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {


                EditText Name = (EditText) findViewById(R.id.nameid);
                mealName = Name.getText().toString();

                Date date = new Date(calendarView.getDate());
                int hour = timePicker.getCurrentHour();
                int min = timePicker.getCurrentMinute();
                date.setHours(hour);
                date.setMinutes(min);
                timestamp = date.getTime() / 1000L;


                for (int i = 0; i < Events.idItems.length; i++) {
                    AutoCompleteTextView item = (AutoCompleteTextView) findViewById(Events.idItems[i]);
                    EditText gram = (EditText) findViewById(Events.idGrams[i]);

                    if (item.getText().toString().length() > 0 && gram.getText().length() > 0) {
                        foody = item.getText().toString();
                        grams = Integer.parseInt(gram.getText().toString());
                        mealList.add(new FoodTO(foody, grams));

                    }
                }



                MyParams myParams = new MyParams(mealName, mealList);
                CreateMeal addIt = new CreateMeal();
                addIt.execute(myParams);


            }
        });
    }

    private static class MyTaskParams {


        String mealName;
        long timeStamp;

        MyTaskParams(String mealName, long timeStamp) {


            this.timeStamp = timeStamp;
            this.mealName = mealName;
        }
    }

    private class saveMealToCalander extends AsyncTask<MyTaskParams, Boolean, Boolean> {

        @Override
        protected Boolean doInBackground(MyTaskParams... params) {

            MyTaskParams meal = params[0];
            String mealName = meal.mealName;

            long TimeStamp = meal.timeStamp;
            boolean i = false;
            CalendarService service = CalendarServiceFactory.getCalanderService();
            try {
                i = service.saveMealToCalander(mealName, TimeStamp);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return i;
        }

        @Override
        protected void onPostExecute(Boolean i) {
            Toast.makeText(getBaseContext(), "Máltíð bætt við", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(AddMealToActivity.this, CalendarActivity.class);
            startActivity(intent);
/*
            if(i){
                Toast.makeText(getBaseContext(), "Máltíð bætt við", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getBaseContext(), "Máltíð ekki bætt við...", Toast.LENGTH_SHORT).show();

            }*/
        }
    }




    private static class MyParams {


        String mealName;
        ArrayList<FoodTO> ingredients;

        MyParams(String mealName, ArrayList<FoodTO> ingredients) {


            this.ingredients = ingredients;
            this.mealName = mealName;

        }
    }

    private class CreateMeal extends AsyncTask<MyParams, Boolean , Boolean> {

        @Override
        protected Boolean doInBackground(MyParams... params) {
            boolean i = false;
            MealService service = MealServiceFactory.getMealService();

            MyParams meal = params[0];

            String mealName = meal.mealName;
            ArrayList<FoodTO> ingredients = meal.ingredients;
            foody = mealName;
            try {
                i =  service.createMeal(mealName, ingredients);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return i;

        }

        @Override
        protected void onPostExecute(Boolean i) {
            Boolean m = i;
            MyTaskParams params = new MyTaskParams(mealName, timestamp);
            saveMealToCalander saveIt = new saveMealToCalander();
            saveIt.execute(params);

/*
            if(m){
                Toast.makeText(getBaseContext(), "Máltíð bætt við", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getBaseContext(), "Máltíð ekki bætt við...", Toast.LENGTH_SHORT).show();

            }
*/


        }
    }


    private class MatisConnection extends AsyncTask<String, ArrayList<com.jberry.dto.Food>, ArrayList<com.jberry.dto.Food>> {

        protected ArrayList<com.jberry.dto.Food> doInBackground(String... params) {

            ArrayList<Food> food = new ArrayList<Food>();
            if(params[0].equals("")){
                return food;
            }else{
                try {
                    food = matisCaller(params[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return food;
        }

        @Override
        protected void onPostExecute(ArrayList<com.jberry.dto.Food> result){

            if(result.size() > 0){
                String[] strings = new String[10];
                for(int i = 0 ; i < strings.length; i++){
                    strings[i] = result.get(0).getNameEng();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.select_dialog_item, strings);

                AutoCompleteTextView actv1 = (AutoCompleteTextView)findViewById(R.id.item1);
                actv1.setThreshold(4);
                actv1.setAdapter(adapter);

                AutoCompleteTextView actv2 = (AutoCompleteTextView)findViewById(R.id.item2);
                actv2.setThreshold(4);
                actv2.setAdapter(adapter);

                AutoCompleteTextView actv3 = (AutoCompleteTextView)findViewById(R.id.item3);
                actv3.setThreshold(4);
                actv3.setAdapter(adapter);

                AutoCompleteTextView actv4 = (AutoCompleteTextView)findViewById(R.id.item4);
                actv4.setThreshold(4);
                actv4.setAdapter(adapter);

                AutoCompleteTextView actv5 = (AutoCompleteTextView)findViewById(R.id.item5);
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

