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


import com.jberry.dto.Meal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import jBerry.MySugar.R;
import ru.Events.Events;
import ru.calendar.EditMealAdapter;

/*
 * Created by Anna on 26.6.2014.
 */
public class CheckinActivity extends ActionBarActivity {
    CheckBox Exercise;
    private Meal mealList;

    EditText bloodSugar;
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


        mealList = EditMealAdapter.getMealById();
        final ArrayList<String> listIngrdients = new ArrayList<String>(mealList.Ingredients.keySet());
        final ArrayList<Integer> gramIngrdients = new ArrayList<Integer>(mealList.Ingredients.values());
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, listIngrdients);


        for(int i = 0; i < Events.idItems.length; i++){
            AutoCompleteTextView actv = (AutoCompleteTextView)findViewById(Events.idItems[i]);
            actv.setThreshold(1);
            actv.setAdapter(adapter);
        }

        bloodSugar = (EditText) findViewById(R.id.bloodSugarItem);
        Exercise = (CheckBox) findViewById(R.id.checkBoxItem);

        for (int i=0; i < Events.idButton.length; i++){
            Button btn = (Button) findViewById(Events.idButton[i]);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(CheckinActivity.this, NutritionActivity.class);
                    startActivity(intent);
                }
            });
        }


        checkIn = (Button) findViewById(R.id.checkInButton);
        checkIn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int m = 0; m < Events.idItems.length; m++){
                    AutoCompleteTextView item= (AutoCompleteTextView) findViewById(Events.idItems[m]);
                    EditText gram = (EditText) findViewById(Events.idGrams[m]);

                    if(item.getText().toString().length() > 0 &&  gram.getText().length() > 0){
                        data.put(item.getText().toString(), Integer.parseInt(gram.getText().toString()));
                    }

                    else if (m == 1){
                        checkIn.setEnabled((bloodSugar.getText().length() > 0)
                             && (item.getText().toString().length() > 0)
                             && (bloodSugar.getText().length() > 0));
                        }
                }

                final EditText infoText = (EditText) findViewById(R.id.noteInfo);

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
                int i = MealAdapter.setMeal(ratio, data, BL, exercise/*, infoText*/);

                Toast.makeText(getBaseContext(), "You need " + i + " insulin units", Toast.LENGTH_SHORT).show();


            }
        });

        Exercise.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                exercise = Exercise.isChecked();
            }
        });

    }
}


