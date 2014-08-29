package ru.checkin;


import android.app.FragmentManager;
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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jberry.dto.Food;
import com.jberry.dto.FoodTO;
import com.jberry.dto.Meal;
import com.jberry.services.food.FoodService;
import com.jberry.services.food.FoodServiceFactory;
import com.jberry.services.insulin.InsulinService;
import com.jberry.services.insulin.InsulinServiceFactory;
import com.jberry.services.meal.MealService;
import com.jberry.services.meal.MealServiceFactory;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import jBerry.MySugar.R;
import ru.Events.Events;
import ru.calendar.CalendarActivity;
import ru.menu.MenuActivity;

/*
 * Created by Anna on 26.6.2014.
 */
public class CheckinActivity extends ActionBarActivity {

    CheckBox Exercise;
    ArrayList<FoodTO> mealList = new ArrayList<FoodTO>();
    FragmentManager fManager;
    EditText bloodSugar, item1, gram1;
    Button checkInBtn, toCalendarBtn;
    String mealName;

    //Fra notanda
    double BL = 0; //Frá checkin
    boolean exercise = false; // Fra checkin
    long timestamp;

    public CheckinActivity() {

    }

    public CheckinActivity(long timestamp, ArrayList<FoodTO> mealList, double BL, boolean exercise) {
        this.timestamp = timestamp;
        this.mealList = mealList;
        this.BL = BL;
        this.exercise = exercise;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            mealName = bundle.getString("mealName");
        }

        new getIngredients().execute(mealName);

        checkInBtn = (Button) findViewById(R.id.checkInButton);
        toCalendarBtn = (Button) findViewById(R.id.toCalanderBtn);
        bloodSugar = (EditText) findViewById(R.id.bloodSugarItem);
        Exercise = (CheckBox) findViewById(R.id.checkBoxItem);
        item1 = (EditText) findViewById(Events.idItems[0]);
        gram1 = (EditText) findViewById(Events.idGrams[0]);


        toCalendarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckinActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });



        final EditText item1 = (EditText) findViewById(Events.idItems[0]);
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

                //if the username or password is missing the log in button is not clickable
                checkInBtn.setEnabled((actv.getText().toString().length() > 0)
                        && gram1.getText().toString().length() > 0
                        && bloodSugar.getText().toString().length() > 0);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

        };
        item1.addTextChangedListener(tw);
        gram1.addTextChangedListener(tw);
        item2.addTextChangedListener(tw);
        item3.addTextChangedListener(tw);
        item4.addTextChangedListener(tw);
        item5.addTextChangedListener(tw);
        bloodSugar.addTextChangedListener(tw);

        checkInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int m = 0; m < Events.idItems.length; m++) {
                    AutoCompleteTextView item = (AutoCompleteTextView) findViewById(Events.idItems[m]);
                    EditText gram = (EditText) findViewById(Events.idGrams[m]);

                    if (item.getText().toString().length() > 0 && gram.getText().length() > 0) {
                        String foody = item.getText().toString();
                        int grams = Integer.parseInt(gram.getText().toString());
                        mealList.add(new FoodTO(foody, grams));
                    }
                }


                BL = Double.parseDouble(bloodSugar.getText().toString());
                timestamp = System.currentTimeMillis() / 1000L;

                MyTaskParams params = new MyTaskParams(mealList, timestamp, BL, exercise);
                CalculateInsulin calculate = new CalculateInsulin();
                calculate.execute(params);


            }
        });

        Exercise.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                exercise = Exercise.isChecked();
            }
        });
    }

    private static class MyTaskParams {
        long timestamp;
        ArrayList<FoodTO> mealList;
        double BloodSugar;
        boolean exercise;


        MyTaskParams(ArrayList<FoodTO> mealList, long timestamp, double BloodSugar, boolean exercise) {


            this.timestamp = timestamp;
            this.mealList = mealList;
            this.BloodSugar = BloodSugar;
            this.exercise = exercise;
        }
    }


    private class CalculateInsulin extends AsyncTask<MyTaskParams, ArrayList<FoodTO>, Integer> {

        @Override
        protected Integer doInBackground(MyTaskParams... params) {

            InsulinService service = InsulinServiceFactory.getInsulinService();
            double i = 0;
            MyTaskParams meal = params[0];

            long timestamp = meal.timestamp;
            ArrayList<FoodTO> food = meal.mealList;
            double BL = meal.BloodSugar;
            boolean bool = meal.exercise;

            try {
                i = service.calculateInsulin(timestamp, food, BL, bool);
            } catch (IOException e) {
                e.printStackTrace();
            }

            int m = (int) i;
            return m;
        }


        protected void onPostExecute(Integer i) {

            if (i > 100) {
                Toast.makeText(getBaseContext(), "Ertu eitthvað snar, þú drepur þig á þessu!", Toast.LENGTH_SHORT).show();

            }
                Bundle args = new Bundle();
                args.putDouble("insulinUnits", i);
                checkinDialog dialog = new checkinDialog();
                dialog.setArguments(args);
                dialog.show(getFragmentManager(), "abc");


        }
    }

    private class MatisConnection extends AsyncTask<String, ArrayList<Food>, ArrayList<Food>> {

        protected ArrayList<Food> doInBackground(String... params) {


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
        protected void onPostExecute(ArrayList<Food> result) {

            if(result.size() > 0) {
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

        public ArrayList<Food> matisCaller(String list) throws Exception {
            ArrayList<Food> anna;
            FoodService service = FoodServiceFactory.getFoodService();
            anna = service.getFoodInformation(list);
            return anna;
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
}