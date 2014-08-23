package ru.calendar;

import android.support.v7.app.ActionBarActivity;

public class CalendarActivity extends ActionBarActivity {
/*
  //  private ArrayList<CalanderMeal> eventList = new ArrayList<CalanderMeal>();
   // private Meal nutrition = new Meal();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.US);
    GregorianCalendar cal = new GregorianCalendar(TimeZone.getTimeZone("US/Central"));
*/
   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar2);
        //new FoodTester().execute("Apple");
    }*/
/*
        final CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView1);
        cal.setTimeInMillis(calendarView.getDate());

        final Button addBtn = (Button) findViewById(R.id.saveAddBtn2);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this, AddMealToActivity.class);
                startActivity(intent);
            }
        });



       // eventList = (ArrayList<CalanderMeal>) CalendarAdapter.getMealsByDay(Long.parseLong(sdf.format(cal.getTime())));
        //nutrition = CalendarAdapter.getMealById();

        ListAdapter adapter = new CalendarAdapter(getApplicationContext(), R.layout.notification_list_item, eventList, nutrition);
        ListView listview = (ListView) findViewById(R.id.eventList);
        listview.setAdapter(adapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager fm = getSupportFragmentManager();

                dialogFragment dialog = new dialogFragment();

                dialog.show(fm, "abc");
            }
        });


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                cal.setTimeInMillis(calendarView.getDate());
                eventList = (ArrayList<CalanderMeal>) CalendarAdapter.getMealsByDay(Long.parseLong(sdf.format(cal.getTime())));
                nutrition =  CalendarAdapter.getMealById();

                ListAdapter adapter = new CalendarAdapter(getApplicationContext(), R.layout.notification_list_item, eventList, nutrition);
                ListView listview = (ListView) findViewById(R.id.eventList);
                listview.setAdapter(adapter);
            }
        });

    }
*//*
private class FoodTester extends AsyncTask<String, ArrayList, ArrayList<Food>> {

    // private String[] strings;

    @Override
    protected ArrayList<Food> doInBackground(String... strings) {
        FoodService getter = FoodServiceFactory.getFoodService();

        try {
            return tester(strings[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    protected void onPostExecute(ArrayList<Food> result){
        Toast.makeText(getBaseContext(), "Got: " + result.toString(), Toast.LENGTH_LONG).show();

    }
    public ArrayList<Food> tester(String foodName) throws IOException {
        ToolService toolService = new ToolService();
        foodName = foodName.replace(" ","%20"); //because fuck jBerry
        String url = "http://" + toolService.url() + ":3000/api/food/getByName/" + foodName;

        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);
        request.setHeader("Authorization", "Basic " + toolService.getB64Auth());

        HttpResponse response = client.execute(request);
        BufferedReader br = new BufferedReader(
                new InputStreamReader((response.getEntity().getContent())));

        StringBuilder builder = new StringBuilder();
        String output;
        while ((output = br.readLine()) != null) {
            builder.append(output);
        }
        output = builder.toString();

        Gson jesus = new Gson();
        Food[] fud = jesus.fromJson(output ,Food[].class);

        return new ArrayList<Food>(Arrays.asList(fud));

    }
}*/
}


