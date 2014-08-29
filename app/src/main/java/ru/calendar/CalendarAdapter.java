package ru.calendar;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jberry.dto.*;
import com.jberry.dto.Food;
import com.jberry.services.food.FoodService;
import com.jberry.services.food.FoodServiceFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

import jBerry.MySugar.R;
import ru.Events.Events;

/*
 * Created by Sindri on 15/07/14.
 */
public class CalendarAdapter extends ArrayAdapter<CalanderMeal>{



    ArrayList<CalanderMeal> mealsList = new ArrayList<CalanderMeal>();
   // private  ArrayList<Meal> nutrition = new  ArrayList<Meal>();
    private static LayoutInflater inflater=null;

    public CalendarAdapter(Context context, int layoutResourceID, ArrayList<CalanderMeal> mealsList){/*, ArrayList<Meal> nutrition*/
        super(context, layoutResourceID);


        this.mealsList = mealsList;
        inflater = LayoutInflater.from(context);

    }

    public int getCount (){
        return mealsList.size();
    }




        public static long setDeleteRow(long date){

        return date;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View row = convertView;
        if(convertView == null)
        {
            inflater = LayoutInflater.from(getContext());
            row = inflater.inflate(R.layout.notification_list_item, null);
        }

        TextView mealName = (TextView)row.findViewById(R.id.notificationTitle1);
        //TextView nuteInfo = (TextView)row.findViewById(R.id.info1);
        TextView time = (TextView)row.findViewById(R.id.timeOfMeal1);

           CalanderMeal c = mealsList.get(position);

        Date date = new Date(c.getTimeOfMeal()*1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm"); // the format of your date
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-0"));

        String timeOfMeal = sdf.format(date);

     /*   String p = Float.toString(nutrition.PróteinAlls);
        String k = Float.toString(nutrition.KolvetniAlls);
        String f = Float.toString(nutrition.FitaAlls);
*/
        mealName.setText(c.getMealName());
      //  nuteInfo.setText("Protein: " + p + " Kolvetni: " + k + " Fita" + f);
        time.setText(timeOfMeal);


        return row;
    }


}
