package ru.checkin;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jberry.dto.Food;

import java.io.IOException;
import java.util.ArrayList;

import jBerry.MySugar.R;
import ru.calendar.CalendarAdapter;

public class foodDialogFragment extends DialogFragment {
    private ArrayList<Food> nutrition = new ArrayList<Food>();

    public foodDialogFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.checkin_food_dialog, container, false);

        Bundle mArgs = getArguments();
        String itemName = mArgs.getString("itemName");


        try {
            nutrition =  CalendarAdapter.getFoodInformation(itemName);
        } catch (IOException e) {
            e.printStackTrace();
        }


        getDialog().setTitle(nutrition.get(1).getNameIce());

        // TODO Passa að það taki ekki alltaf bara úr núlta stakinu.
        String pro = Double.toString(nutrition.get(0).getProteins());
        String kol = Double.toString(nutrition.get(0).getTotalCarbohydrates());
        String fit = Double.toString(nutrition.get(0).getFat());
        String syk = Double.toString(nutrition.get(0).getSugars());
        String chol = Double.toString(nutrition.get(0).getCholesterol());
        String fib = Double.toString(nutrition.get(0).getFiber());

        TextView protein = (TextView) rootView.findViewById(R.id.proteinDialog);
        TextView kolvetni = (TextView) rootView.findViewById(R.id.kolvetniDialog);
        TextView fita = (TextView) rootView.findViewById(R.id.fitaDialog);
        TextView sykur = (TextView) rootView.findViewById(R.id.satFatDialog);
        TextView cholestrol = (TextView) rootView.findViewById(R.id.CholestrolDialog);
        TextView fiber = (TextView) rootView.findViewById(R.id.fiberDialog);

        protein.setText("Prótein: " + pro);
        kolvetni.setText("Kolvetni: " + kol);
        fita.setText("Fita: " + fit);
        sykur.setText("Sykur: " + syk);
        cholestrol.setText("Kólesteról: " + chol);
        fiber.setText("Trefjar: " + fib);


        return rootView;

    }

}
