package ru.calendar;

import java.util.Calendar;

/**
 * Created by Sindri on 09/07/14.
 */
class CalendarPagerAdapter  {

    public int day, month, year, hour;
    public String formattedDate;
    public Calendar calendar;

    public CalendarPagerAdapter(){

        calendar = Calendar.getInstance();
       // hour = calendar.get(Calendar.HOUR_OF_DAY);
        day = calendar.get(Calendar.DATE);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
    }

    public String getDate(){
       return formattedDate = month+"/" + day +"/"+ year ;
    }

    public void incDate(){
        calendar.add(Calendar.DATE,  1);
    }

}
