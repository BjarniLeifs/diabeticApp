package ru.menu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import jBerry.MySugar.R;

import static jBerry.MySugar.R.id.btn1;

public class MenuActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*
        A placeholder fragment containing a some view
     */

    public static class PlaceholderFragment extends Fragment implements View.OnClickListener{
        View rootView;

        public PlaceholderFragment(){

        }

        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle saveInstanceState){
            rootView = inflater.inflate(R.layout.activity_menu, container, false);

            Button btn = (Button)rootView.findViewById(btn1);
            btn.setOnClickListener(this);
            return rootView;
        }

        @Override
        public void onClick(View view) {

            //Change views..

            switch (view.getId())
            {
                case btn1:
                    ((TextView)rootView.findViewById(R.id.myTextView)).setText("You clicked me!");
                    break;
            }
        }
    }
}
