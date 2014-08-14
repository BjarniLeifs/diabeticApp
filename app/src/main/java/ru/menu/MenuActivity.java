package ru.menu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import jBerry.MySugar.R;
import ru.History.HistoryActivity;
import ru.Settings.SettingsActivity;
import ru.backStore.CustomArrayAdapter;
import ru.backStore.CustomClass;
import ru.calendar.CalendarActivity;
import ru.checkin.CheckinActivity;
import ru.reminder.ReminderActivity;
import ru.signup.SignupActivity;


public class MenuActivity extends Activity{

    ArrayList<CustomClass> list=new ArrayList<CustomClass>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        list.add(new CustomClass(BitmapFactory.decodeResource(getResources(), R.drawable.diabetes),"Dagatal"));
        list.add(new CustomClass(BitmapFactory.decodeResource(getResources(), R.drawable.diabetes),"Check-In"));
        list.add(new CustomClass(BitmapFactory.decodeResource(getResources(), R.drawable.diabetes),"Stillingar"));
        list.add(new CustomClass(BitmapFactory.decodeResource(getResources(), R.drawable.diabetes),"Áminning"));
        list.add(new CustomClass(BitmapFactory.decodeResource(getResources(), R.drawable.diabetes),"Sagan mín"));
        list.add(new CustomClass(BitmapFactory.decodeResource(getResources(), R.drawable.diabetes),"Fræðsla"));

        final CustomArrayAdapter adapter=new CustomArrayAdapter(getApplicationContext(), list);
        GridView gridView=(GridView)findViewById(R.id.gridView1);
        gridView.setAdapter(adapter);


        gridView.setOnItemClickListener(new OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos,
                                    long id){
                Intent intent;
                if(id == 0){
                    intent = new Intent(MenuActivity.this, CalendarActivity.class);
                }
                else if(id == 1){
                    intent = new Intent(MenuActivity.this, CheckinActivity.class);
                }
                else if(id == 2){
                    intent = new Intent(MenuActivity.this, SettingsActivity.class);

                }
                else if(id == 3){
                    intent = new Intent(MenuActivity.this, ReminderActivity.class);

                }
                else if (id == 4){
                    intent = new Intent(MenuActivity.this, HistoryActivity.class);

                }
                else{

                    intent = new Intent(MenuActivity.this, ReminderActivity.class);
                }
                startActivity(intent);
            }
        });

    }

}