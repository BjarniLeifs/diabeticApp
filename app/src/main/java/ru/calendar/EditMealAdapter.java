package ru.calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.jberry.dto.Meal;
import com.jberry.services.meal.MealService;
import com.jberry.services.meal.MealServiceFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jBerry.MySugar.R;

/*
 * Created by Sindri on 06/08/14.
 */
public class EditMealAdapter extends ArrayAdapter<String>{

    private ArrayList<String> list = null;
    private Context Context = null;
    private LayoutInflater Inflater;
    private Meal mealList;



    public EditMealAdapter(Context _context, int _id, ArrayList _list, Meal _mealList) {
        super(_context, _id);

        this.Inflater = LayoutInflater.from(_context);
        this.Context = _context;
        this.mealList = _mealList;
        this.list = _list;
    }


    public static Meal getMealById(){
        Meal mealList;
        MealService mealService = MealServiceFactory.getMealService();
        mealList = mealService.getMealByName();
        return mealList;
    }

    public static HashMap setEditMeal(Map mealList){

        MealService service = MealServiceFactory.getMealService();
      //  return service.setEditMeal(mealList);
        return null;
    }


    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    // GETVIEW IS NEVER CALLED??????? ************************
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View r = convertView;
        if(convertView == null)
        {
            r = Inflater.inflate(R.layout.activity_edit_meal, parent,false);
            convertView.setTag(R.id.textView, convertView.findViewById(R.id.textView));
        }

        // Should this maybe be: AutoCompleteTextView. User is to be able to write ap and apple comes up
        //foodItem1 = (AutoCompleteTextView) convertView.getTag(R.id.item1);
        //String textItem = (String) getItem(position);

        //foodItem1.setText(list.toString().indexOf(0));
        //gram1.setText(list.Meald);

        return r;
    }
}

        /*
        foodItem2 = (EditText) findViewById(R.id.item2);
        foodItem3 = (EditText) findViewById(R.id.item3);
        foodItem4 = (EditText) findViewById(R.id.item4);
        foodItem5 = (EditText) findViewById(R.id.item5);
        gram1 = (EditText) findViewById(R.id.grams1);
        gram2 = (EditText) findViewById(R.id.grams2);
        gram3 = (EditText) findViewById(R.id.grams3);
        gram4 = (EditText) findViewById(R.id.grams4);
        gram5 = (EditText) findViewById(R.id.grams5);
        checkIn = (Button) findViewById(R.id.checkInButton);
        nutrition1 = (Button) findViewById(R.id.nutritionButton);
        nutrition2 = (Button) findViewById(R.id.nutritionButton2);
        nutrition3 = (Button) findViewById(R.id.nutritionButton3);
        nutrition4 = (Button) findViewById(R.id.nutritionButton4);
        nutrition5 = (Button) findViewById(R.id.nutritionButton5);*/