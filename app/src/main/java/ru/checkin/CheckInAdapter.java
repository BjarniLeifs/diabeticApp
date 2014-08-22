package ru.checkin;

import android.view.LayoutInflater;

/**
 * Created by Anna on 24.7.2014.
 */

public class CheckInAdapter /*extends ArrayAdapter<>*/ {


    private static LayoutInflater inflater = null;
    private String _food;
    private Integer _grams;
    //private Food nutrition = null;

    /*public void Meal (String food, Integer grams) {
        _food = food;
        _grams = grams;

    }*/

    /*public CheckInAdapter(Context context, int layoutResourceID, Food _nutrition) {
        super(context, layoutResourceID, (java.util.List<Food>) _nutrition);
        this.nutrition = _nutrition;
    }
    public static  int setMeal(double ratio, Map data, double bloodSugar, boolean exercise) {
        InsulinService insulin = InsulinServiceFactory.getInsulinService();


        return insulin.calculateInsulin(ratio, data, bloodSugar, exercise);
    }

    public static Object getFoodNutrition(){
        Food _foodByName;
        FoodService calService = FoodServiceFactory.getFoodService();
        _foodByName = calService.getFoodInformation();
        return _foodByName;
    }
*/

}
