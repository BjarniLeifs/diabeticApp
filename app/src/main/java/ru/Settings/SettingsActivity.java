package ru.Settings;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import jBerry.MySugar.R;

/**
 * Created by Anna on 16.7.2014.
 */
public class SettingsActivity extends ActionBarActivity {
    EditText height, weight, date, mRatio, nRatio, eRatio;
    CheckBox isMale, isFemale;
    Button save;
    double genderId;
    double cm;
    double kg;
    String birthday;
    double mRat;
    double nRat;
    double eRat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        height = (EditText) findViewById(R.id.height);
        weight = (EditText) findViewById(R.id.weight);
        date = (EditText) findViewById(R.id.birthday);
        isMale = (CheckBox) findViewById(R.id.male);
        isFemale = (CheckBox) findViewById(R.id.female);
        save = (Button) findViewById(R.id.saveButton);
        mRatio = (EditText) findViewById(R.id.morningRatio);
        nRatio = (EditText) findViewById(R.id.noonRatio);
        eRatio = (EditText) findViewById(R.id.eveningRatio);

        CompoundButton.OnCheckedChangeListener genderListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    switch (buttonView.getId()) {
                        case R.id.male:
                            isMale.setChecked(true);
                            isFemale.setChecked(false);
                            genderId = 1;
                            break;
                        case R.id.female:
                            isFemale.setChecked(true);
                            isMale.setChecked(false);
                            genderId = 2;
                            break;
                    }
                }
            }
        };


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //    kg = Double.parseDouble(weight.getText().toString());
            //    cm = Double.parseDouble(height.getText().toString());
                birthday = date.getText().toString();
                sendDate(birthday);
               // mRat = Double.parseDouble(mRatio.getText().toString());
               // nRat = Double.parseDouble(nRatio.getText().toString());
               // eRat = Double.parseDouble(eRatio.getText().toString());
              //  Toast.makeText(getBaseContext(), "Þú átt afmæli" + year, Toast.LENGTH_SHORT).show();

            }
        });





        isFemale.setOnCheckedChangeListener(genderListener);
        isMale.setOnCheckedChangeListener(genderListener);

    }
    public void sendDate(String birthday) {
        DateFormat formatter ;
        Date date ;
        formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            date = formatter.parse(birthday);
            Toast.makeText(getBaseContext(), "Today is " + date.getTime(), Toast.LENGTH_SHORT).show();
          //  System.out.println("Today is " + date.getTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


}

