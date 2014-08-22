package ru.menu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import java.util.ArrayList;

import jBerry.MySugar.R;
import ru.History.HistoryActivity;
import ru.Settings.SettingsActivity;
import ru.backStore.CustomArrayAdapter;
import ru.backStore.CustomClass;
import ru.calendar.CalendarActivity;
import ru.education.EducationActivity;
import ru.reminder.ReminderActivity;

//import ru.checkin.CheckinActivity;


public class MenuActivity extends Activity{


    ArrayList<CustomClass> list=new ArrayList<CustomClass>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_grid);

        list.add(new CustomClass(BitmapFactory.decodeResource(getResources(), R.drawable.calendar),"Dagatal"));
        list.add(new CustomClass(BitmapFactory.decodeResource(getResources(), R.drawable.list),"Check-In"));
        list.add(new CustomClass(BitmapFactory.decodeResource(getResources(), R.drawable.settings),"Stillingar"));
        list.add(new CustomClass(BitmapFactory.decodeResource(getResources(), R.drawable.clock),"Áminning"));
        list.add(new CustomClass(BitmapFactory.decodeResource(getResources(), R.drawable.graph1),"Sagan mín"));
        list.add(new CustomClass(BitmapFactory.decodeResource(getResources(), R.drawable.student),"Fræðsla"));


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
                /*else if(id == 1){
                    //intent = new Intent(MenuActivity.this, CheckinActivity.class);
                }*/
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
                    intent = new Intent(MenuActivity.this, EducationActivity.class);
                }
                startActivity(intent);
            }
        });

    }

}