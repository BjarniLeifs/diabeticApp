package ru.calendar;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;

import jBerry.MySugar.R;

public class EditMealActivity extends ActionBarActivity {


    private Button btn;
    final Map<String, Integer> data = new HashMap<String, Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_meal);


        // getMealById returns: mango, apple, banana
        //mealList = CalendarAdapter.getMealById();
      //  final ArrayList<String> listIngrdients = new ArrayList<String>(mealList.Ingredients.keySet());
       // ArrayList<Integer> gramIngrdients = new ArrayList<Integer>(mealList.Ingredients.values());


        //final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, listIngrdients);

        /*for(int i = 0; i < listIngrdients.size(); i++){
            AutoCompleteTextView item = (AutoCompleteTextView) findViewById(Events.idItems[i]);
            EditText gram = (EditText) findViewById(Events.idGrams[i]);
            item.setText(listIngrdients.get(i));
            item.setThreshold(1);
            item.setAdapter(adapter);

            gram.setText("" + gramIngrdients.get(i));
        }*/

        btn = (Button) findViewById(R.id.editSaveBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*for(int i=0; i<listIngrdients.size(); i++){
                    AutoCompleteTextView item = (AutoCompleteTextView)findViewById(Events.idItems[i]);
                    EditText gram = (EditText) findViewById(Events.idGrams[i]);
                    if(item.getText().toString().trim().length() > 0){
                        data.put(Events.idItems.toString(), Integer.parseInt(gram.getText().toString()));
                    }
                }*/

                //CalendarAdapter.setEditMeal(data);
            }
        });
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