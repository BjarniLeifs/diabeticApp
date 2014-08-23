package ru.calendar;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
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
import java.util.ArrayList;
import java.util.Arrays;

/*
 * Created by Sindri on 15/07/14.
 */
public class CalendarAdapter extends ArrayAdapter<CalanderMeal>{


    private ArrayList<CalanderMeal> mealsList;
    private Meal nutrition = null;
    private static LayoutInflater inflater=null;

    public CalendarAdapter(Context context, int layoutResourceID, ArrayList<CalanderMeal> mealsList){
        super(context, layoutResourceID, mealsList);

    }

  /*  public static List<CalanderMeal> getMealsByDay(long unixTime){

        List<CalanderMeal> calList;
        CalendarService calService = CalendarServiceFactory.getCalanderService();
        calList = calService.getMealsByDay(unixTime);
        return calList;
    }*/


    public static  ArrayList<Food> getFoodInformation(String foodName){
       //new MatisConnection().execute(foodName);
        return null;
    }
/*
    public static Object getNutrition(){

        Object _mealByName;
        MealService calService = MealServiceFactory.getMealService();
        _mealByName = calService.getMealByName();
        return _mealByName;
    }
*/
    /*public static Meal getMealById(){
        Meal mealList;
        MealService mealService = MealServiceFactory.getMealService();
        mealList = mealService.getMealByName();

        return mealList;
    }*/

    /*public static HashMap addMeal(Map items, long timestamp, int hour, int min){
        CalendarService service = CalendarServiceFactory.getCalanderService();
        //service.addMealsByDay(items, timestamp, hour, min);
        return null;
    }*/

    /*public static HashMap setEditMeal(Map mealList){

        MealService service = MealServiceFactory.getMealService();
        //  return service.setEditMeal(mealList);
        return null;
    }*/

    public static long setDeleteRow(long date){

        return date;
    }
/*
    @Override
    public int getCount() {
        return  list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
*/
/*
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        if(convertView == null)
        {
            inflater = LayoutInflater.from(getContext());
            row = inflater.inflate(R.layout.notification_list_item, null);
        }

        TextView mealName = (TextView)row.findViewById(R.id.notificationTitle);
        TextView nuteInfo = (TextView)row.findViewById(R.id.info);
        TextView time = (TextView)row.findViewById(R.id.timeOfMeal);

     //   CalanderMeal c = list.get(position);

       /* Date date = new Date(c.timeOfMeal*1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm"); // the format of your date
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-0"));

        String timeOfMeal = sdf.format(date);

        String p = Float.toString(nutrition.PróteinAlls);
        String k = Float.toString(nutrition.KolvetniAlls);
        String f = Float.toString(nutrition.FitaAlls);

        mealName.setText(c.mealName);
        nuteInfo.setText("Protein: " + p + " Kolvetni: " + k + " Fita" + f);
        time.setText(timeOfMeal);


        return row;
    }
*/
/*
    private static class MatisConnection extends AsyncTask<String, ArrayList<Food>, ArrayList<Food>> {

        public MatisConnection(){
        }

        @Override
        protected ArrayList<Food> doInBackground(String... strings) {

            try {
                return tester(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(ArrayList<Food> result){



        }

        public ArrayList<Food> tester(String foodName) throws IOException {
            ToolService toolService = new ToolService();
            foodName = foodName.replace(" ","%20"); //because fuck jBerry
            String url = "http://" + toolService.url() + ":3000/api/food/getByName/" + foodName;

            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(url);
            request.setHeader("Authorization", ToolService.getB64Auth());
            HttpResponse response = client.execute(request);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));
            StringBuilder builder = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                builder.append(output);
            }
            output = builder.toString();
            Gson jesus = new Gson();
            com.jberry.dto.Food[] fud = jesus.fromJson(output , com.jberry.dto.Food[].class);

            return new ArrayList<Food>(Arrays.asList(fud));
        }
    }*/
}
