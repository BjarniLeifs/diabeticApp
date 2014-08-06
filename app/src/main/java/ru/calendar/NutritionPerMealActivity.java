package ru.calendar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.jberry.dto.Meal;
import jBerry.MySugar.R;

public class NutritionPerMealActivity extends Activity  {

    private Meal nutrition = new Meal();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutritioninfo);


        nutrition = (Meal) CalendarAdapter.getNutrition();

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
        TextView dvitiamin = (TextView) findViewById(R.id.dvitiamin);
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
        dvitiamin.setText("D vítamín: " + Dvi);
        evitamin.setText("E vítamín: " + Evi) ;
        b1vitamin.setText("B1 vítamín: " + B1v);
        b2vitamin.setText("B2 vítamín: " + B2v);
        b12vitamin.setText("B12 Vítamín: " + B12);
        cvitamin.setText("C vítamín: " + Cvi);


    }

}
