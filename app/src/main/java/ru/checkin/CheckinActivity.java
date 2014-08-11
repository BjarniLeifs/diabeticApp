package ru.checkin;

import android.content.Intent;
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
import android.view.View.OnClickListener;
import android.widget.Toast;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import jBerry.MySugar.R;

/*
 * Created by Anna on 26.6.2014.
 */
public class CheckinActivity extends ActionBarActivity {
    EditText bloodSugar, foodItem1, foodItem2, foodItem3, foodItem4, foodItem5, gram1, gram2, gram3, gram4, gram5;
    CheckBox Exercise;
    Button checkIn, nutrition1, nutrition2, nutrition3, nutrition4, nutrition5;
    String[] food ={"epli", "bananabrauð", "banani", "appelsina", "mango"};

    //Drasl sem kemur ur database
    double ratio = 10; //Frá settings
    double morningRatio = 12;//frá settings
    double noonRatio = 15;//fra settings
    double eveningRatio = 17; //fra settings
    //Fra notanda
    double BL = 0; //Frá checkin
    boolean exercise = false; // Fra checkin
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin);

        final Map<String, Integer> data = new HashMap<String, Integer>();

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, food);

        AutoCompleteTextView actv = (AutoCompleteTextView)findViewById(R.id.item1);
        actv.setThreshold(1);
        actv.setAdapter(adapter);

        AutoCompleteTextView actv2 = (AutoCompleteTextView)findViewById(R.id.item2);
        actv2.setThreshold(1);
        actv2.setAdapter(adapter);
        AutoCompleteTextView actv3 = (AutoCompleteTextView)findViewById(R.id.item3);
        actv3.setThreshold(1);
        actv3.setAdapter(adapter);
        AutoCompleteTextView actv4 = (AutoCompleteTextView)findViewById(R.id.item4);
        actv4.setThreshold(1);
        actv4.setAdapter(adapter);
        AutoCompleteTextView actv5 = (AutoCompleteTextView)findViewById(R.id.item5);
        actv5.setThreshold(1);
        actv5.setAdapter(adapter);

        bloodSugar = (EditText) findViewById(R.id.bloodSugarItem);
        Exercise = (CheckBox) findViewById(R.id.checkBoxItem);
        foodItem1 = (EditText) findViewById(R.id.item1);
        foodItem2 = (EditText) findViewById(R.id.item2);
        foodItem3 = (EditText) findViewById(R.id.item3);
        foodItem4 = (EditText) findViewById(R.id.item4);
        foodItem5 = (EditText) findViewById(R.id.item5);
        gram1 = (EditText) findViewById(R.id.grams1);
        gram2 = (EditText) findViewById(R.id.grams2);
        gram3 = (EditText) findViewById(R.id.grams3);
        gram4 = (EditText) findViewById(R.id.grams4);
        gram5 = (EditText) findViewById(R.id.grams5);
        checkIn = (Button) findViewById(R.id.checkInButton);
        nutrition1 = (Button) findViewById(R.id.nutritionButton);
        nutrition2 = (Button) findViewById(R.id.nutritionButton2);
        nutrition3 = (Button) findViewById(R.id.nutritionButton3);
        nutrition4 = (Button) findViewById(R.id.nutritionButton4);
        nutrition5 = (Button) findViewById(R.id.nutritionButton5);



        final TextWatcher tw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

               // adapter = new MealAdapter(getApplicationContext(), food, R.layout.activity_checkin, new String[])
                if(foodItem1.getText().toString().length() > 0 &&
                        gram1.getText().toString().length() > 0) {
                        data.put(foodItem1.getText().toString(), Integer.parseInt(gram1.getText().toString()));
                }
                if (foodItem2.getText().toString().length() > 0 && gram2.getText().toString().length() > 0){
                    foodItem3.setVisibility(View.VISIBLE);
                    gram3.setVisibility(View.VISIBLE);
                    nutrition3.setVisibility(View.VISIBLE);
                    data.put(foodItem2.getText().toString(), Integer.parseInt(gram2.getText().toString()));

                    }
                if (foodItem3.getText().toString().length() > 0 && gram3.getText().toString().length() > 0){
                    foodItem4.setVisibility(View.VISIBLE);
                    gram4.setVisibility(View.VISIBLE);
                    nutrition4.setVisibility(View.VISIBLE);
                    data.put(foodItem3.getText().toString(), Integer.parseInt(gram3.getText().toString()));
                }
                if (foodItem4.getText().toString().length() > 0 && gram4.getText().toString().length() > 0){
                    foodItem5.setVisibility(View.VISIBLE);
                    gram5.setVisibility(View.VISIBLE);
                    nutrition5.setVisibility(View.VISIBLE);
                    data.put(foodItem4.getText().toString(), Integer.parseInt(gram4.getText().toString()));
                }

                //Enable checkin button
                checkIn.setEnabled((bloodSugar.getText().length() > 0)
                        && (foodItem1.getText().toString().length() > 0)
                        && (bloodSugar.getText().length() > 0));


            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        };

        nutrition1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckinActivity.this, NutritionActivity.class);
                startActivity(intent);
            }
        });
        Exercise.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                exercise = Exercise.isChecked();
            }
        });
        checkIn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                /*Calculation */

                //Get the hour of the day to set the ratio
                Calendar calendar = new GregorianCalendar();
                Date trialTime = new Date();
                calendar.setTime(trialTime);
                int hours = calendar.get(Calendar.HOUR_OF_DAY);
                if(hours < 11)
                    ratio = morningRatio;
                else if(hours >= 11 && hours < 17)
                    ratio = noonRatio;
                else if(hours >= 17 && hours < 23)
                    ratio = eveningRatio;

                //Get bloodsugar input
                BL = Double.parseDouble(bloodSugar.getText().toString());

                //Send checkin info to the checkinserver
                int i = MealAdapter.setMeal(ratio, data, BL, exercise);

                Toast.makeText(getBaseContext(), "You need " + i + " insulin units", Toast.LENGTH_SHORT).show();
            }
        });

        foodItem1.addTextChangedListener(tw);
        foodItem2.addTextChangedListener(tw);
        foodItem3.addTextChangedListener(tw);
        foodItem4.addTextChangedListener(tw);
        foodItem5.addTextChangedListener(tw);
        checkIn.addTextChangedListener(tw);
        bloodSugar.addTextChangedListener(tw);

    }
}


