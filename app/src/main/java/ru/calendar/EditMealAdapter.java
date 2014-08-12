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

        return r;
    }
}
