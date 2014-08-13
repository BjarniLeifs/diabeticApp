package ru.checkin;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jberry.dto.Food;
import jBerry.MySugar.R;

public class foodDialogFragment extends DialogFragment {
    private Food nutrition = new Food();

    public foodDialogFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.checkin_dialog, container, false);
        nutrition = (Food) CheckInAdapter.getFoodNutrition();

        getDialog().setTitle(nutrition.Nafn);


        String pro = Float.toString(nutrition.Prótein);
        String kol = Float.toString(nutrition.Kolvetni);
        String fit = Float.toString(nutrition.Fita);
        String satFat = Float.toString(nutrition.Sykrur);
        String chol = Float.toString(nutrition.Kólesteról);
        String addSug = Float.toString(nutrition.Viðbættursykur);
        String fib = Float.toString(nutrition.Trefjaefni);
        String alco = Float.toString(nutrition.Alkohól);
        String min = Float.toString(nutrition.Steinefnialls);
        String vitA = Float.toString(nutrition.Avítamín);
        String vitD = Float.toString(nutrition.Dvítamín);
        String vitE = Float.toString(nutrition.Evítamín);
        String vitC = Float.toString(nutrition.Cvítamín);
        String vitB1 = Float.toString(nutrition.B1vítamín);
        String vitB2 = Float.toString(nutrition.B2vítamín);
        String vitB12 = Float.toString(nutrition.B2vítamín);
        String calc = Float.toString(nutrition.Kalk);

        TextView protein = (TextView) rootView.findViewById(R.id.proteinDialog);
        TextView kolvetni = (TextView) rootView.findViewById(R.id.kolvetniDialog);
        TextView fita = (TextView) rootView.findViewById(R.id.fitaDialog);
        TextView saturatedFat = (TextView) rootView.findViewById(R.id.satFatDialog);
        TextView cholestrol = (TextView) rootView.findViewById(R.id.CholestrolDialog);
        TextView addedSugar = (TextView) rootView.findViewById(R.id.addSugarDialog);
        TextView fiber = (TextView) rootView.findViewById(R.id.fiberDialog);
        TextView alcohol = (TextView) rootView.findViewById(R.id.alcoholDialog);
        TextView minerals = (TextView) rootView.findViewById(R.id.MineralsDialog);
        TextView vitaminA = (TextView) rootView.findViewById(R.id.vitaminADialog);
        TextView vitaminD = (TextView) rootView.findViewById(R.id.vitaminDDialog);
        TextView vitaminE = (TextView) rootView.findViewById(R.id.vitaminEDialog);
        TextView vitaminC = (TextView) rootView.findViewById(R.id.vitaminCDialog);
        TextView vitaminB1 = (TextView) rootView.findViewById(R.id.vitaminB1Dialog);
        TextView vitaminB2 = (TextView) rootView.findViewById(R.id.vitaminB2Dialog);
        TextView vitaminB12 = (TextView) rootView.findViewById(R.id.vitaminB12Dialog);
        TextView calcium = (TextView) rootView.findViewById(R.id.calciumDialog);

        protein.setText("Prótein: " + pro);
        kolvetni.setText("Kolvetni: " + kol);
        fita.setText("Fita: " + fit);
        saturatedFat.setText("Sykrur: " + satFat);
        cholestrol.setText("Kólesteról: " + chol);
        addedSugar.setText("Viðbættur sykur: " + addSug);
        fiber.setText("Trefjar: " + fib);
        alcohol.setText("Áfengi: " + alco);
        minerals.setText("Steinefni: " + min);
        vitaminA.setText("A vítamín: " + vitA);
        vitaminD.setText("D vítamín: " + vitD);
        vitaminE.setText("E vítamín: " + vitE);
        vitaminC.setText("C vítamín: " + vitC);
        vitaminB1.setText("B1 vítamín: " + vitB1);
        vitaminB2.setText("B2 vítamín: " + vitB2);
        vitaminB12.setText("B12 vítamín: " + vitB12);
        calcium.setText("Kalk: " + calc);


        return rootView;

    }

}
