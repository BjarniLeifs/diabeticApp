package ru.checkin;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import com.jberry.dto.Meal;
import com.jberry.services.checkin.CheckInService;
import com.jberry.services.checkin.CheckInServiceFactory;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Anna on 24.7.2014.
 */

public class MealAdapter extends ArrayAdapter<Meal> {
    private ArrayList<Meal> list = null;

    private static LayoutInflater inflater = null;
    private String _food;
    private Integer _grams;

    public void Meal (String food, Integer grams) {
        _food = food;
        _grams = grams;
    }

    public MealAdapter(Context context, int layoutResourceID, ArrayList<Meal> foodList) {
        super(context, layoutResourceID, foodList);
        this.list = foodList;
    }
    public static  int setMeal(double ratio, Map data, double bloodSugar, boolean exercise) {


        CheckInService checker = CheckInServiceFactory.getCheckInService();
        return checker.calculateInsulin(ratio, data, bloodSugar, exercise);
    }


}
