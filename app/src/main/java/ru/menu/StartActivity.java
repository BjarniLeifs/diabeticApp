package ru.menu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.jberry.services.checkin.CheckInService;
import com.jberry.services.checkin.CheckInServiceFactory;

import java.util.ArrayList;

import jBerry.MySugar.R;
import ru.Settings.SettingsActivity;
import ru.backStore.CustomArrayAdapter;
import ru.backStore.CustomClass;
import ru.checkin.CheckinActivity;
import ru.signup.LoginActivity;
import ru.signup.SignupActivity;


public class StartActivity extends Activity{

    ArrayList<CustomClass> list=new ArrayList<CustomClass>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        //get username
        Intent username = getIntent();
        username.getStringExtra("username");

        GridView gridView=(GridView)findViewById(R.id.gridView1);

        list.add(new CustomClass(BitmapFactory.decodeResource(getResources(), R.drawable.diabetes),"Calendar"));
        list.add(new CustomClass(BitmapFactory.decodeResource(getResources(), R.drawable.diabetes),"Check-In"));
        list.add(new CustomClass(BitmapFactory.decodeResource(getResources(), R.drawable.diabetes),"Settings"));
        list.add(new CustomClass(BitmapFactory.decodeResource(getResources(), R.drawable.diabetes),"Stuff"));

        final CustomArrayAdapter adapter;
        adapter = new CustomArrayAdapter(getApplicationContext(), list);

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos,
                                    long id){
                Intent intent;
                if(id == 0){
                    intent = new Intent(StartActivity.this, LoginActivity.class);

                }
                else if(id == 1){
                    intent = new Intent(StartActivity.this, CheckinActivity.class);

                }
                else if(id == 2){
                    intent = new Intent(StartActivity.this, SettingsActivity.class);

                }
                else{
                    intent = new Intent(StartActivity.this, SignupActivity.class);

                }
                startActivity(intent);

                TextView view2=(TextView) ((ViewGroup) view).getChildAt(1);
                String s= null;
                if (view2 != null) {
                    s = view2.getText().toString();
                }
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();

            }
        });

    }

}