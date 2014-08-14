package ru.checkin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
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
import ru.calendar.CalendarActivity;
import ru.calendar.EditMealAdapter;

/*
 * Created by Anna on 26.6.2014.
 */
public class CheckinActivity extends ActionBarActivity {

    CheckBox Exercise;
    private Meal mealList;
    FragmentManager fManager;
    EditText bloodSugar, item1, gram1;
    Button checkIn, toCalendarBtn;
    int mealId;
    Meal meal;

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

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            mealId = bundle.getInt("mealId");
        }

        // NEED INT
        meal = EditMealAdapter.getMealById();



        final Map<String, Integer> data = new HashMap<String, Integer>();

        mealList = EditMealAdapter.getMealById();
        final ArrayList<String> listIngrdients = new ArrayList<String>(mealList.Ingredients.keySet());
        final ArrayList<Integer> gramIngrdients = new ArrayList<Integer>(mealList.Ingredients.values());
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, listIngrdients);
        checkIn = (Button) findViewById(R.id.checkInButton);
        toCalendarBtn = (Button) findViewById(R.id.toCalanderBtn);
        item1 = (EditText) findViewById(Events.idItems[0]);
        gram1 = (EditText) findViewById(Events.idGrams[0]);

       // EditText text = (EditText) findViewById(Events.idItems[0]);

        if(bundle != null){
            for(int i = 0; i < meal.Ingredients.size(); i++){
                AutoCompleteTextView item = (AutoCompleteTextView) findViewById(Events.idItems[i]);
                EditText gram = (EditText) findViewById(Events.idGrams[i]);
                item.setText(listIngrdients.get(i));
                item.setThreshold(1);
                item.setAdapter(adapter);

                gram.setText("" + gramIngrdients.get(i));
            }
        }

        toCalendarBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckinActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

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
                   fManager = getSupportFragmentManager();
                   foodDialogFragment dialog = new foodDialogFragment();
                   dialog.show(fManager, "stuff");
                }
            });
        }
        TextWatcher tw = new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                //if the username or password is missing the log in button is not clickable
                checkIn.setEnabled((item1.getText().toString().length() > 0)
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
        bloodSugar.addTextChangedListener(tw);

        checkIn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int m = 0; m < Events.idItems.length; m++){
                    AutoCompleteTextView item= (AutoCompleteTextView) findViewById(Events.idItems[m]);
                    EditText gram = (EditText) findViewById(Events.idGrams[m]);

                    if(item.getText().toString().length() > 0 &&  gram.getText().length() > 0){
                        data.put(item.getText().toString(), Integer.parseInt(gram.getText().toString()));
                    }
                }
                  //final EditText infoText = (EditText) findViewById(R.id.noteInfo);

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
                int i = CheckInAdapter.setMeal(ratio, data, BL, exercise/*, infoText*/);

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


