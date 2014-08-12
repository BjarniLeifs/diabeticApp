package ru.checkin;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import com.jberry.dto.Food;
import com.jberry.services.checkin.CheckInService;
import com.jberry.services.checkin.CheckInServiceFactory;
import com.jberry.services.food.FoodService;
import com.jberry.services.food.FoodServiceFactory;
import java.util.Map;

/**
 * Created by Anna on 24.7.2014.
 */

public class MealAdapter extends ArrayAdapter<Food> {

    private static LayoutInflater inflater = null;
    private String _food;
    private Integer _grams;
    private Food nutrition = null;

    public void Meal (String food, Integer grams) {
        _food = food;
        _grams = grams;

    }

    public MealAdapter(Context context, int layoutResourceID, Food _nutrition) {
        super(context, layoutResourceID, (java.util.List<Food>) _nutrition);
        this.nutrition = _nutrition;
    }
    public static  int setMeal(double ratio, Map data, double bloodSugar, boolean exercise) {


        CheckInService checker = CheckInServiceFactory.getCheckInService();
        return checker.calculateInsulin(ratio, data, bloodSugar, exercise);
    }
    public static Object getFoodNutrition(){
        Food _foodByName;
        FoodService calService = FoodServiceFactory.getFoodService();
        _foodByName = calService.getFoodInformation();
        return _foodByName;
    }


}
