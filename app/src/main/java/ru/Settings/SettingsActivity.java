package ru.Settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import jBerry.MySugar.R;

/**
 * Created by Anna on 16.7.2014.
 */
public class SettingsActivity extends ActionBarActivity{
    EditText height, weight, age, sensitivity, ratio;
    CheckBox isMale, isFemale;
    Button sensitivityInfo, ratioInfo, save;
    double genderId, cm, kg, year, rat;
    double  sensi = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        height = (EditText) findViewById(R.id.height);
        weight = (EditText) findViewById(R.id.weight);
        age = (EditText) findViewById(R.id.age);
        sensitivity = (EditText) findViewById(R.id.sensitive);
        isMale = (CheckBox) findViewById(R.id.male);
        isFemale = (CheckBox) findViewById(R.id.female);
        sensitivityInfo = (Button) findViewById(R.id.sensitiveInfoButton);
        ratioInfo = (Button) findViewById(R.id.ratioInfoButton);
        save = (Button) findViewById(R.id.saveButton);


        isMale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                    genderId = 1;
            }
        });
        isFemale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                    genderId = 2;
            }
        });

        final TextWatcher tw = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

                if(weight.getText().toString().length() > 0) {
                    kg = Double.parseDouble(weight.getText().toString());
                    sensi = (1800/kg)/18;

                }


            }

            @Override
            public void afterTextChanged(Editable editable) {
               // sensitivity.setText(Double.toString(sensi));
            }
        };

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    kg = Double.parseDouble(weight.getText().toString());
                    sensi = (1800/kg)/18;
                    cm = Integer.parseInt(height.getText().toString());
                    year = Integer.parseInt(age.getText().toString());
                    rat = Integer.parseInt(ratio.getText().toString());
                   // sensi = Double.parseDouble(sensitivity.getText().toString());


                }
            });
        sensitivityInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, SensitivityInfoActivity.class);
                startActivity(intent);
            }
        });
        ratioInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, RatioInfoActivity.class);
                startActivity(intent);
            }
        });
        sensitivity.addTextChangedListener(tw);
    }

}
