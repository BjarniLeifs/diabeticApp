package ru.Events;

import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import jBerry.MySugar.R;

/*
 * Created by Sindri on 12/08/14.
 */
public class Events {

    public static
    int[] idItems = new int[]{
            R.id.item1,
            R.id.item2,
            R.id.item3,
            R.id.item4,
            R.id.item5
    };

    public static
    int[] idGrams = new int[]{
            R.id.grams1,
            R.id.grams2,
            R.id.grams3,
            R.id.grams4,
            R.id.grams5
    };

    public static
    int[] idButton = new int[]{
            R.id.nutritionButton,
            R.id.nutritionButton2,
            R.id.nutritionButton3,
            R.id.nutritionButton4,
            R.id.nutritionButton5
    };



    public static
    int[] idnute = new int[]{
            R.id.protein,
            R.id.kolvetni,
            R.id.fita,
            R.id.mettadarfitusyrur,
            R.id.kolesterol,
            R.id.kalk,
            R.id.vidbaettursykur,
            R.id.trefjaefni,
            R.id.alkahol,
            R.id.steinefni,
            R.id.avitamin,
            R.id.dvitiamin,
            R.id.evitamin,
            R.id.b1vitamin,
            R.id.b2vitamin,
            R.id.b12vitamin,
            R.id.cvitamin
    };

    private TextView protein,
            kolvetni,
            fita,
            mettadarfitusyrur,
            kolesterol,
            kalk,
            vidbaettursykur,
            trefjaefn,
            alkahol,
            steinefni,
            avitamin,
            dvitamin,
            evitamin,
            b1vitamin,
            b2vitamin,
            b12vitamin,
            cvitamin;

    TextView[] textViews = new TextView[]{
            protein,
            kolvetni,
            fita,
            mettadarfitusyrur,
            kolesterol,
            kalk,
            vidbaettursykur,
            trefjaefn,
            alkahol,
            steinefni,
            avitamin,
            dvitamin,
            evitamin,
            b1vitamin,
            b2vitamin,
            b12vitamin,
            cvitamin
    };
}
