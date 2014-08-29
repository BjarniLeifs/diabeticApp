package ru.calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import com.jberry.dto.Food;
import com.jberry.services.food.FoodService;
import com.jberry.services.food.FoodServiceFactory;
import java.io.IOException;
import java.util.ArrayList;
import jBerry.MySugar.R;

public class NutritionPerMealActivity extends Activity  {

    ArrayList<Food> foodArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutritioninfo);

        String foodName = null;
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            foodName = bundle.getString("foodName");
        }

        new getFoodInformationTotal().execute(foodName);

        //nutrition =  CalendarAdapter.getMealById();
/*
        String pro = Float.toString(nutrition.PróteinAlls);
        String kol = Float.toString(nutrition.KolvetniAlls);
        String fit = Float.toString(nutrition.FitaAlls);
        String met = Float.toString(nutrition.MettaðarfitusýrurAlls);
        String kole = Float.toString(nutrition.KólesterólAlls);
        String kal = Float.toString(nutrition.KalkAlls);
        String syk = Float.toString(nutrition.ViðbætturSykurAlls);
        String tre = Float.toString(nutrition.TrefjaefniAlls);
        String alk = Float.toString(nutrition.AlkóhólAlls);
        String ste = Float.toString(nutrition.SteinefniAlls);
        String Avi = Float.toString(nutrition.AvítamínAlls);
        String Dvi = Float.toString(nutrition.DVítamínAlls);
        String Evi = Float.toString(nutrition.EVítamínAlls);
        String B1v = Float.toString(nutrition.B1vítamínAlls);
        String B2v = Float.toString(nutrition.B2vítaminAlls);
        String B12 = Float.toString(nutrition.B12vítamínAlls);
        String Cvi = Float.toString(nutrition.CvítamínAlls);


        TextView protein = (TextView) findViewById(R.id.protein);
        TextView kolvetni = (TextView) findViewById(R.id.kolvetni);
        TextView fita = (TextView) findViewById(R.id.fita);
        TextView mettadarfitusyrur = (TextView) findViewById(R.id.mettadarfitusyrur);
        TextView kolesterol = (TextView) findViewById(R.id.kolesterol);
        TextView kalk = (TextView) findViewById(R.id.kalk);
        TextView vidbaettursykur = (TextView) findViewById(R.id.vidbaettursykur);
        TextView trefjaefni = (TextView) findViewById(R.id.trefjaefni);
        TextView alkahol = (TextView) findViewById(R.id.alkahol);
        TextView steinefni = (TextView) findViewById(R.id.steinefni);
        TextView avitamin = (TextView) findViewById(R.id.avitamin);
        TextView dvitamin = (TextView) findViewById(R.id.dvitiamin);
        TextView evitamin = (TextView) findViewById(R.id.evitamin);
        TextView b1vitamin = (TextView) findViewById(R.id.b1vitamin);
        TextView b2vitamin = (TextView) findViewById(R.id.b2vitamin);
        TextView b12vitamin = (TextView) findViewById(R.id.b12vitamin);
        TextView cvitamin = (TextView) findViewById(R.id.cvitamin);


        protein.setText("Prótein: " + pro);
        kolvetni.setText("Kolvetni: " + kol);
        fita.setText("Fita: " + fit);
        mettadarfitusyrur.setText("Mettaðar fitusýrur: " + met);
        kolesterol.setText("Kólesterol: " + kole);
        vidbaettursykur.setText("Viðbættur sykur: " + syk);
        kalk.setText("Kalk: " + kal);
        trefjaefni.setText("Trefjaefni: " + tre);
        alkahol.setText("Alkahól: " + alk);
        steinefni.setText("Steinefni: " + ste);
        avitamin.setText("A vítamín: " + Avi);
        dvitamin.setText("D vítamín: " + Dvi);
        evitamin.setText("E vítamín: " + Evi) ;
        b1vitamin.setText("B1 vítamín: " + B1v);
        b2vitamin.setText("B2 vítamín: " + B2v);
        b12vitamin.setText("B12 Vítamín: " + B12);
        cvitamin.setText("C vítamín: " + Cvi);

*/
    }


    private class getFoodInformationTotal extends AsyncTask<String, String, ArrayList<Food>> {

        @Override
        protected ArrayList<Food> doInBackground(String... params) {

            FoodService service = FoodServiceFactory.getFoodService();
            ArrayList<Food> foo = null;
            try {
                foo = service.getFoodInformation(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return foo;
        }

        @Override
        protected void onPostExecute(ArrayList<com.jberry.dto.Food> result){

            String pro = Double.toString(result.get(0).getProteins());
            String kol = Double.toString(result.get(0).getFiber());
            String fit = Double.toString(result.get(0).getFat());
            String chol = Double.toString(result.get(0).getCholesterol());
            String sug = Double.toString(result.get(0).getSugars());


            TextView protein = (TextView) findViewById(R.id.protein);
            TextView kolvetni = (TextView) findViewById(R.id.kolvetni);
            TextView fita = (TextView) findViewById(R.id.fita);
            TextView kolesterol = (TextView) findViewById(R.id.kolesterol);
            TextView sugars = (TextView) findViewById(R.id.vidbaettursykur);


            protein.setText(pro);
            protein.setText("Prótein: " + pro + "gr");
            kolvetni.setText("Kolvetni: " + kol + "gr");
            fita.setText("Fita: " + fit + "gr");
            kolesterol.setText("Kolesterol: " + chol + "gr");
            sugars.setText("Viðbættur sykur: " + sug + "gr");


        }
    }
}
