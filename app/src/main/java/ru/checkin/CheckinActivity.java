package ru.checkin;


import android.app.Dialog;
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


import com.jberry.dto.Diabetic;
import com.jberry.dto.Food;
import com.jberry.dto.FoodTO;
import com.jberry.dto.Meal;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import jBerry.MySugar.R;
import ru.Events.Events;
import ru.calendar.CalendarActivity;
import ru.calendar.CalendarAdapter;
import ru.menu.MenuActivity;

/*
 * Created by Anna on 26.6.2014.
 */
public class CheckinActivity extends ActionBarActivity {

    CheckBox Exercise;
    //private Meal oneMeal;
    private ArrayList<Food> oneMeal = new ArrayList<Food>();
    private ArrayList<String> matisList = new ArrayList<String>();
    private ArrayList<FoodTO> addFood;

    Diabetic diabetic;

    FragmentManager fManager;
    EditText bloodSugar, item1, gram1;
    Button checkIn, toCalendarBtn;
    int mealId;
    Meal meal;
    foodDialogFragment dialog;
/*
    //Drasl sem kemur ur database
    double ratio = 10; //Frá settings
    double morningRatio = 12;//frá settings
    double noonRatio = 15;//fra settings
    double eveningRatio = 17; //fra settings
*/
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
        //oneMeal = CalendarAdapter.getMealById(mealId);

        final Map<String, Integer> data = new HashMap<String, Integer>();


        // Ef notandi skrifar ep þá koma upp allar fæðutegundir sem byrja á ep t.d. epli
        for(int i = 0; i < Events.idItems.length; i++){
            AutoCompleteTextView actv = (AutoCompleteTextView)findViewById(Events.idItems[i]);
            actv.setThreshold(1);
            String item = actv.getText().toString();

            matisList = CalendarAdapter.getFoodTitle(item);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, matisList);
            actv.setAdapter(adapter);
        }

       // final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, );
        checkIn = (Button) findViewById(R.id.checkInButton);
        toCalendarBtn = (Button) findViewById(R.id.toCalanderBtn);
        item1 = (EditText) findViewById(Events.idItems[0]);
        gram1 = (EditText) findViewById(Events.idGrams[0]);

       // EditText text = (EditText) findViewById(Events.idItems[0]);

       // Need to get mealById

     /*   try {
            oneMeal = CalendarAdapter.getFoodInformation(da);

        } catch (IOException e) {
            e.printStackTrace();
        }
*/

      /*  if(bundle != null){
            for(int i = 0; i < 5; i++){
                AutoCompleteTextView item = (AutoCompleteTextView) findViewById(Events.idItems[i]);
                EditText gram = (EditText) findViewById(Events.idGrams[i]);
                item.setText(oneMeal.getMealName());
                item.setThreshold(1);
                item.setAdapter(adapter);

                gram.setText("" + oneMeal.getMealName());
                oneMeal.
            }
        }
*/

        toCalendarBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckinActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });


        bloodSugar = (EditText) findViewById(R.id.bloodSugarItem);
        Exercise = (CheckBox) findViewById(R.id.checkBoxItem);

        for (int i=0; i < Events.idButton.length; i++){
            Button btn = (Button) findViewById(Events.idButton[i]);
            AutoCompleteTextView item = (AutoCompleteTextView) findViewById(Events.idItems[i]);
            String food = item.getText().toString();
            Bundle args = new Bundle();
            args.putString("itemName", food);
            fManager = getSupportFragmentManager();
            dialog = new foodDialogFragment();
            dialog.setArguments(args);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

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

                //Get bloodsugar input
                BL = Double.parseDouble(bloodSugar.getText().toString());

                long unixTime = System.currentTimeMillis() / 1000L;

                double i = 0;
                try {
                    i = CalendarAdapter.calculateInsulin(unixTime, addFood, BL, exercise);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if(i > 100){
                    Toast.makeText(getBaseContext(), "Ertu eitthvað snar, þú drepur þig á þessu!", Toast.LENGTH_SHORT).show();

                    try{
                        TimeUnit.SECONDS.wait(1);
                    }catch (InterruptedException e){

                    }

                    Intent intent = new Intent(CheckinActivity.this, MenuActivity.class);
                    startActivity(intent);
                }
                else if(i == 0){
                    return;
                }else{
                    Bundle args = new Bundle();
                    args.putInt("insulinUnits", (int) i);
                    checkinDialog dialog = new checkinDialog();
                    dialog.setArguments(args);
                    dialog.show(getFragmentManager(), "abc");
                }
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



