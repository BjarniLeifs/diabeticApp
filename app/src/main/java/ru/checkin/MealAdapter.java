package ru.checkin;

import android.content.Context;
import android.widget.Adapter;
import android.widget.SimpleAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Anna on 24.7.2014.
 */

public class MealAdapter extends SimpleAdapter {
    private String _food;
    private Integer _grams;
    Adapter adapters;
    private Context mContext;

    public void Meal (String food, Integer grams) {
        _food = food;
        _grams = grams;
    }

    public String getFood() {
        return _food;
    }

    public Integer getGrams() {
        return _grams;
    }

    public MealAdapter(Context context, List<? extends Map<String, Integer>> data, int resources, String[] from, int[] to) {
        super(context, data, resources, from, to);
        mContext = context;
        Map<String, Integer> addFood = new HashMap<String, Integer>();
        addFood.put(_food, _grams);
    }
    //private void addMeal(String food, String grams) {
      //  Meals.add(new Meal(food, grams));
    //}

}
