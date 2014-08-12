package ru.calendar;


import android.app.DialogFragment;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import jBerry.MySugar.R;


/**
 * A simple {@link //fragment} subclass.
 *
 */
public class FragmentB extends DialogFragment{
/*
    Button btnCalendar, btnTimePicker, btnSave;
    EditText txtDate, txtTime;
    protected Calendar dateTime;
    private SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a", Locale.US);

    private int mYear, mMonth, mDay, mHour, mMinute;


    public FragmentB() {

    }



    public void setEventDate(Calendar eventDate) {
        this.dateTime = eventDate;
    }


*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_b, container, false);
  /*      btnCalendar = (Button) v.findViewById(R.id.btnCalendar);
        btnTimePicker = (Button) v.findViewById(R.id.btnTimePicker);
        btnSave = (Button) v.findViewById(R.id.btnSave);
        txtDate = (EditText) v.findViewById(R.id.txtDate);
        txtTime = (EditText) v.findViewById(R.id.txtTime);
        btnTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTimeClick(v);
            }

        });

        btnSave.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                CalendarAdapter adapter = new CalendarAdapter(getActivity());
                adapter.setTime(list....)
            }
        });
*/
        return v;
    }
/*
    public void onTimeClick(View v){
        DateFormat.is24HourFormat(getActivity());

        final Calendar calendar = Calendar.getInstance();
        mHour = calendar.get(Calendar.HOUR_OF_DAY);
        mMinute = calendar.get(Calendar.MINUTE);

        TimePickerDialog tpd = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                dateTime.set(Calendar.MINUTE, minute);
                btnTimePicker.setText(timeFormat.format(dateTime.getTime()));
            }
        },
                dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE),
                DateFormat.is24HourFormat(getActivity())
        );
        tpd.show();


    }

*/
    public static FragmentB newInstance(String text){
        FragmentB f = new FragmentB();

        return f;
    }

}
