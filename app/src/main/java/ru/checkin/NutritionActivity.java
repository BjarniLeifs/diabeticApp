package ru.checkin;

import android.os.Bundle;
import android.widget.TextView;

import jBerry.MySugar.R;

/**
 * Created by Anna on 22.7.2014.
 */
public class NutritionActivity extends CheckinActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);

        TextView t1 = (TextView) findViewById(R.id.textViewProtein);
        t1.setText("Hallo heimur");
        TextView t2 = (TextView) findViewById(R.id.textViewFat);
        t2.setText("Hallo heimur");
        TextView t3 = (TextView) findViewById(R.id.textViewSatFat);
        t3.setText("Hallo heimur");
        TextView t4 = (TextView) findViewById(R.id.textViewCholesterol);
        t4.setText("Hallo heimur");
        TextView t5 = (TextView) findViewById(R.id.textViewCarb);
        t5.setText("Hallo heimur");
        TextView t6 = (TextView) findViewById(R.id.textViewAddedSugar);
        t6.setText("Hallo heimur");
        TextView t7 = (TextView) findViewById(R.id.textViewFiber);
        t7.setText("Hallo heimur");
        TextView t8 = (TextView) findViewById(R.id.textViewAlcohol);
        t8.setText("Hallo heimur");
        TextView t9 = (TextView) findViewById(R.id.textViewMinerals);
        t9.setText("Hallo heimur");
        TextView t10 = (TextView) findViewById(R.id.textViewVitaminA);
        t10.setText("Hallo heimur");
        TextView t11 = (TextView) findViewById(R.id.textViewVitaminD);
        t11.setText("Hallo heimur");
        TextView t12 = (TextView) findViewById(R.id.textViewVitaminE);
        t12.setText("Hallo heimur");
        TextView t13 = (TextView) findViewById(R.id.textViewVitaminB1);
        t13.setText("Hallo heimur");
        TextView t14 = (TextView) findViewById(R.id.textViewVitaminB2);
        t14.setText("Hallo heimur");
        TextView t15 = (TextView) findViewById(R.id.textViewVitaminB12);
        t15.setText("Hallo heimur");
        TextView t16 = (TextView) findViewById(R.id.textViewVitaminC);
        t16.setText("Hallo heimur");
        TextView t17 = (TextView) findViewById(R.id.textViewCalcium);
        t17.setText("Hallo heimur");

    }


}
