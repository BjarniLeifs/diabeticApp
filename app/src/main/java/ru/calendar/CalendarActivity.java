package ru.calendar;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.jberry.services.calander.CalanderService;
import com.jberry.services.calander.CalanderServiceFactory;

import jBerry.MySugar.R;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class CalendarActivity extends FragmentActivity implements TabListener {

    CalanderService cale = CalanderServiceFactory.getCalanderService();


    ActionBar actionBar;
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_calendar);

        viewPager = (ViewPager) findViewById(R.id.calendarContainer);
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                actionBar.setSelectedNavigationItem(arg0);
                // Intent intent = new Intent(this, CalendarActivity.class);
                // startActivity(intent);
                Log.d("DpoiNT", "onPageSelected at " + " position " + arg0);

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                //    Log.d("DpoiNT", "onPageScrolled at "+" position " +arg0+" from " +arg1+" with number of pixels "+arg2);

            }


            @Override
            public void onPageScrollStateChanged(int arg0) {
                if(arg0== ViewPager.SCROLL_STATE_IDLE){
                    Log.d("DpoiNT", "onPageScrollStateChanged Idle");
                }
                if(arg0== ViewPager.SCROLL_STATE_DRAGGING){
                    Log.d("DpoiNT", "onPageScrollStateChanged Dragging");
                }
                if(arg0== ViewPager.SCROLL_STATE_SETTLING){
                    Log.d("DpoiNT", "onPageScrollStateChanged Settling");
                }

            }
        });

        actionBar = getActionBar();
        assert actionBar != null;
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab tab1=actionBar.newTab();
        tab1.setText("Tab 1");
        tab1.setTabListener(this);

        ActionBar.Tab tab2=actionBar.newTab();
        tab2.setText("Tab 2");
        tab2.setTabListener(this);

        ActionBar.Tab tab3=actionBar.newTab();
        tab3.setText("Tab 3");
        tab3.setTabListener(this);

        actionBar.addTab(tab1);
        actionBar.addTab(tab2);
        actionBar.addTab(tab3);
    }


    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // Log.d("DpoiNT", "onTabSelected at "+" position " +tab.getPosition()+" name "+tab.getText());
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // Log.d("DpoiNT", "onTabUnselected at "+" position " +tab.getPosition()+" name "+tab.getText());

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // Log.d("DpoiNT", "onTabReselected at "+" position "+tab.getPosition()+" name "+tab.getText());

    }
}


class MyAdapter extends FragmentPagerAdapter {


    public MyAdapter(FragmentManager fm) {
        super(fm);
    }



    @Override
    public Fragment getItem(int arg0) {

        switch(arg0){
            case 0: return FragmentA.newInstance("FragmentA, Instance 1");
            case 1: return FragmentB.newInstance("FragmentB, Instance 2");
            case 2: return FragmentC.newInstance("FragmentC, Instance 3");
            default: return FragmentA.newInstance("FragmentA, Default");
        }
    }

    @Override
    public int getCount() {
        return 3;
    }


}
