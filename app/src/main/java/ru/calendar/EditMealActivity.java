package ru.calendar;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import com.jberry.dto.Meal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import jBerry.MySugar.R;
import ru.Events.Events;

public class EditMealActivity extends ActionBarActivity {

    private Meal mealList;
    private Button btn;
    final Map<String, Integer> data = new HashMap<String, Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_meal);


        // getMealById returns: mango, apple, banana
        mealList = EditMealAdapter.getMealById();
        final ArrayList<String> listIngrdients = new ArrayList<String>(mealList.Ingredients.keySet());
        ArrayList<Integer> gramIngrdients = new ArrayList<Integer>(mealList.Ingredients.values());


        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, listIngrdients);

        for(int i = 0; i < listIngrdients.size(); i++){
            AutoCompleteTextView item = (AutoCompleteTextView) findViewById(Events.idItems[i]);
            EditText gram = (EditText) findViewById(Events.idGrams[i]);
            item.setText(listIngrdients.get(i));
            item.setThreshold(1);
            item.setAdapter(adapter);

            gram.setText("" + gramIngrdients.get(i));
        }

        btn = (Button) findViewById(R.id.editSaveBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i=0; i<listIngrdients.size(); i++){
                    AutoCompleteTextView item = (AutoCompleteTextView)findViewById(Events.idItems[i]);
                    EditText gram = (EditText) findViewById(Events.idGrams[i]);
                    if(item.getText().toString().trim().length() > 0){
                        data.put(Events.idItems.toString(), Integer.parseInt(gram.getText().toString()));
                    }
                }

                EditMealAdapter.setEditMeal(data);
            }
        });




/*



        Resources res = getResources(); //if you are in an activity
        for(int i = 0; i < mealList.Ingredients.size(); i++){
            EditText t = (EditText) findViewById(idItems[i]);
            values.add(t.getText().toString());

        }
*/






    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit_meal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
    /*   //final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, food);
       // AutoCompleteTextView actv = (AutoCompleteTextView)findViewById(R.id.item1);
       // actv.setThreshold(1);
     //   actv.setAdapter(adapter);
        AutoCompleteTextView actv2 = (AutoCompleteTextView)findViewById(R.id.item2);
        actv2.setThreshold(1);
      //  actv2.setAdapter(adapter);
        AutoCompleteTextView actv3 = (AutoCompleteTextView)findViewById(R.id.item3);
        actv3.setThreshold(1);
      //  actv3.setAdapter(adapter);
        AutoCompleteTextView actv4 = (AutoCompleteTextView)findViewById(R.id.item4);
        actv4.setThreshold(1);
       // actv4.setAdapter(adapter);
        AutoCompleteTextView actv5 = (AutoCompleteTextView)findViewById(R.id.item5);
        actv5.setThreshold(1);
       // actv5.setAdapter(adapter);
*/
