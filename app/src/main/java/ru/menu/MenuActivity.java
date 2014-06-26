package ru.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import jBerry.MySugar.R;


public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


    }

    public void menuListener(View view){

        switch (view.getId()){
            case R.id.btn1:
                //Intent intent = new Intent(this, SignupActivity.class);
                //startActivity(intent);
                break;
            case R.id.btn2:

                break;
            case R.id.btn3:

                break;

            case R.id.btn4:
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}
