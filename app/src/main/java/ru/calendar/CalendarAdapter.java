package ru.calendar;

import android.content.Context;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.widget.TextView;
import com.jberry.dto.CalanderMeal;
import com.jberry.dto.Diabetic;
import com.jberry.dto.Food;
import com.jberry.dto.FoodTO;
import com.jberry.dto.Meal;
import com.jberry.services.calendar.CalendarService;
import com.jberry.services.calendar.CalendarServiceFactory;
import com.jberry.services.diabetic.DiabeticService;
import com.jberry.services.diabetic.DiabeticServiceFactory;
import com.jberry.services.food.FoodService;
import com.jberry.services.food.FoodServiceFactory;
import com.jberry.services.insulin.InsulinService;
import com.jberry.services.insulin.InsulinServiceFactory;
import com.jberry.services.meal.MealService;
import com.jberry.services.meal.MealServiceFactory;
import com.jberry.services.profile.ProfileService;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import jBerry.MySugar.R;
/*
 * Created by Sindri on 15/07/14.
 */
public class CalendarAdapter extends ArrayAdapter<CalanderMeal> {


    private ArrayList<CalanderMeal> list = null;
    private static LayoutInflater inflater=null;


    public CalendarAdapter(Context context, int layoutResourceID,
                           ArrayList<CalanderMeal> calList){

        super(context, layoutResourceID, calList);
        this.list = calList;
    }



    /*
    *   GET
    *   ArrayList getFoodTitle(String foodName)
    *   -Tekur inn nafn á mat
    *   -skilar út Arraylista af þeim nöfnum af mat
     */

     public static ArrayList<String> getFoodTitle(String foodName){
        FoodService foo = FoodServiceFactory.getFoodService();

        return foo.getFoodTitle(foodName);
    }


    /*
    *   GET
    *   double getCarbsFromFood(String foodName)
    *   -Tekur inn Nafn á mat
    *   -skilar út kolvetnismagnið í þeim mat
     */
    public static double getCarbsFromFood(String foodName){
        FoodService cal = FoodServiceFactory.getFoodService();
        double i = 0;

        return i;
    }

      /*
      GET
      ArrayList getFoodInformation(String foodName)
      -Tekur inn Nafn á mat
      -Skilar út öllum upplýsingum um þann mat.
      */
    public static ArrayList<Food> getFoodInformation (String foodName) throws IOException {

        ArrayList<Food> info;
        FoodService calService = FoodServiceFactory.getFoodService();
        info = calService.getFoodInformation(foodName);

        return info;

    }


    /*
    * PUT
    * boolean finishCheckIn(Diabetic DiabeticUsr)
    * -Tekur inn tilvik af Diabetic class-a
    * -Er notaður til að staðfesta intöku á insúlín skammti og vista þá skammtinn og tímann
    * -Vistar: lastDoseTime og lastDoseAmount.
    */
    public static boolean  finishCheckIn(Diabetic diabeticUsr) throws IOException {
        DiabeticService service = DiabeticServiceFactory.getDiabeticService();
        return service.finishCheckIn(diabeticUsr);
    }


    /*
    * GET
    * Diabetic getDiabeticInfo(String userId)
    * -Tekur inn UserId
    * -Skilar öllum Diabeticupplýsingum um notanda í Diabetic class-a
    * -skilar: morningRatio, noonRatio, eveningRatio, insulinSensitivity,  lastDoseTime og lastDoseAmount.*/
    public static Diabetic getDiabeticInfo(String userId) throws IOException {
        Diabetic diabetic;
        DiabeticService dia = DiabeticServiceFactory.getDiabeticService();
        diabetic = dia.getDiabeticInfo(userId);

        return diabetic;
    }

    /*
        Put
        -Tekur inn Streng sem er nafn � meal og Arraylista af ingredients
	    -Ingredients hefur gildin sem eru � FoodTO (Name og grams)
		-skilar til baka True ef m�lt�� n�r a� vistast � gagnagrunnin.
     */
    public static boolean createMeal(String mealName, ArrayList<FoodTO> ingredients) throws IOException {
        MealService service = MealServiceFactory.getMealService();
        return service.createMeal(mealName, ingredients);
    }

    /*
        Get
        -Tekur inn streng sem er nafn � meal
            -Skilar til baka Meal class-a sem hefur sama nafn.
            -Meal class inniheldur eftirfarandi:
                String userId
                String mealName
                ArrayList<FoodTO> ingredients **************
                double totalFat
                double totalCarbs
                double totalFiber
                double totalProtein
                double totalCholesterol
            -Notkunargildi:
                -Til a� checka inn m�lt��, �� er h�gt a� nota �etta fall
                 og me� tilvikinu �� geti�i bara t.d. gert:
                    Meal daMeal = Mealservice.getmealbyName("dickbutt");
                    Arraylist<FoodTO> checkinstuff = daMeal.getIngredients();

                -Og �� hend inn checkinstuff sem param � calculateInsulin.
                -Einnig �� geti�i nota� �etta til a� birta uppl�singar um m�lt��.

     */
    public static Meal getMealByName(String mealName) throws IOException {
        MealService service = MealServiceFactory.getMealService();
        return service.getMealByName(mealName);
    }

    /*
    DELETE
    -tekur inn streng sem er nafn � meal sem � a� delete-a
	-Skilar true ef delete t�kst �r database.
	-ATH �etta skal ekki nota � calander nema �i� vilji� ey�a m�lt��inni ALVEG
		 Calander hefur s�r delete a�ger� til a� taka m�lt�� �r calanderTilviki.
     */
    public static boolean deleteMeal(String mealName) throws IOException {
        MealService service = MealServiceFactory.getMealService();
        return service.deleteMeal(mealName);
    }

    /*
        EDIT
        -tekur inn nafn � m�lt�� sem streng og arraylista af breyttum/sendum ingredients
		-skilar true ef n�� var a� breyta m�lt��inni.
     */
    public static boolean editMeal(String mealName, ArrayList<FoodTO> newIngredients) throws IOException {
        MealService service = MealServiceFactory.getMealService();
        return service.editMeal(mealName, newIngredients);
    }

    /*
        PUT
        -Tekur inn nafn � meal og timestamp
		-Timestamp er n�kv�mlega dagur og t�minn.
		-Skilar true ef n�� var a� vista tilvik � database.
	*/
    public static boolean saveMealToCalander(String mealName, long timestamp) throws IOException {
        CalendarService service = CalendarServiceFactory.getCalanderService();
        return service.saveMealToCalander(mealName, timestamp);
    }

    /*
        GET
        -Tekur inn timestamp � v�ldum degi
		-T�minn(hours, min, sec) � timestampnum skiptir ekki m�li �ar sem serverinn checkar a�eins
		 daginn sem er � �essum timestamp
     */
    public static  ArrayList<CalanderMeal> getMealsByDay(long timeStamp) throws IOException {
        CalendarService calService = CalendarServiceFactory.getCalanderService();
        return calService.getMealsByDay(timeStamp);

    }

    /*
        DELETE
        -Tekur inn nafn � meal og t�manum sem h�n er skr�� �
		-ey�ir �v� meal tilviki sem er skr�� � vi�komandi t�ma
		-S.s. ef �� ert me� bananasalat kl 12 og kl 13, ey�ir kl 13
		 �� ertu bara me� bananasalat kl 12 � database.
     */
    public static boolean deleteFromCalenedar(long timeOfMeal) throws IOException {
        CalendarService service = CalendarServiceFactory.getCalanderService();
        return service.deleteFromCalendar(timeOfMeal);
    }

    /*
        PUT
        -Tekur inn tilvik af Diabetic class-a
		-er nota�ur til a� breyta f�stu sykurs�kisuppl�singum um notandann � settings
		-�eas: morningRatio, noonRatio, eveningRatio og insulinSensitivity.
		-Skilar true ef �a� n�r a� vista breytingarnar.
     */
    public static boolean postDiabeticInfo(Diabetic diabeticUsr) throws IOException {
        DiabeticService service = DiabeticServiceFactory.getDiabeticService();
        return service.postDiabeticInfo(diabeticUsr);
    }


    /*
    * POST	double calculateInsulin(long timeStamp, ArrayList food, double bloodSugar, boolean exercise)
    * -Tekur inn: Timestamp, ArrayList-a af mat með því að notast við FoodTo classan Blóðsykur og excersize.
    *
   */
    public static  double calculateInsulin(long timestamp,  ArrayList<FoodTO> food, double bloodSugar, boolean exercise) throws IOException {
        InsulinService checker = InsulinServiceFactory.getInsulinService();
        double i = checker.calculateInsulin(timestamp, food, bloodSugar, exercise);
        return i;
    }


    @Override
    public int getCount() {
        return  list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


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

        CalanderMeal c = list.get(position);

        Date date = new Date(c.getTimeOfMeal() * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm"); // the format of your date
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-0"));

        String dateNow = sdf.format(date);


/*
        String p = Float.toString();
        String k = Float.toString(nutrition.KolvetniAlls);
        String f = Float.toString(nutrition.FitaAlls);
*/
        mealName.setText(c.getMealName());
     //   nuteInfo.setText("Protein: " + p + " Kolvetni: " + k + " Fita" + f);
        time.setText(dateNow);

        return row;
    }

}