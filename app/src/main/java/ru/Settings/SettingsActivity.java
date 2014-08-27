package ru.Settings;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jberry.dto.Diabetic;
import com.jberry.dto.Profile;
import com.jberry.dto.User;
import com.jberry.services.diabetic.DiabeticService;
import com.jberry.services.diabetic.DiabeticServiceFactory;
import com.jberry.services.profile.ProfileService;
import com.jberry.services.profile.ProfileServiceFactory;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jBerry.MySugar.R;

/**
 * Created by Anna on 16.7.2014.
 */
public class SettingsActivity extends ActionBarActivity {
    EditText height, weight, date, mRatio, nRatio, eRatio;
    CheckBox isMale, isFemale;
    Button save;
    String sex, birthday, set3;
    double cm, kg, mRat, nRat, eRat, rat1, rat2, rat3, set1, set2;
    long mBirthday, set4;
    Diabetic diaInfo;
    Profile proInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        height = (EditText) findViewById(R.id.height);
        weight = (EditText) findViewById(R.id.weight);
        date = (EditText) findViewById(R.id.birthday);
        isMale = (CheckBox) findViewById(R.id.male);
        isFemale = (CheckBox) findViewById(R.id.female);
        save = (Button) findViewById(R.id.saveButton);
        mRatio = (EditText) findViewById(R.id.morningRatio);
        nRatio = (EditText) findViewById(R.id.noonRatio);
        eRatio = (EditText) findViewById(R.id.eveningRatio);

        /*
        Get all the existing settings information from the database
         */
        new getDiabeticInfoTask().execute();
        new getProfileInfoTask().execute();

        /*
        Listener for the gender check box
         */
        CompoundButton.OnCheckedChangeListener genderListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    switch (buttonView.getId()) {
                        case R.id.male:
                            isMale.setChecked(true);
                            isFemale.setChecked(false);
                            sex = "KK";
                            break;
                        case R.id.female:
                            isFemale.setChecked(true);
                            isMale.setChecked(false);
                            sex = "KVK";
                            break;
                    }
                }
            }
        };
        isFemale.setOnCheckedChangeListener(genderListener);
        isMale.setOnCheckedChangeListener(genderListener);

        /*
        The save button that executes both the Diabetic AsyncTask and the Profile AsyncTask functions
         */
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                kg = Double.parseDouble(weight.getText().toString());
                cm = Double.parseDouble(height.getText().toString());
                mRat = Double.parseDouble(mRatio.getText().toString());
                nRat = Double.parseDouble(nRatio.getText().toString());
                eRat = Double.parseDouble(eRatio.getText().toString());
                birthday = date.getText().toString();
                mBirthday = sendDate(birthday);
                ProfileParams params = new ProfileParams(cm, kg, mBirthday, sex);
                ProfileTask setProfile = new ProfileTask();
                setProfile.execute(params);
                DiabeticParams diaparams = new DiabeticParams(mRat, nRat, eRat);
                DiabeticTask setDiabetic = new DiabeticTask();
                setDiabetic.execute(diaparams);
            }
        });
    }

    /*
    Contructor for profile task, makes it easier to use Async with multiple parameters
     */
    private static class ProfileParams {
        double cm, kg;
        long mBirthday;
        String gender;

        ProfileParams(double cm, double kg, long mBirthday, String gender) {

            this.cm = cm;
            this.kg = kg;
            this.mBirthday = mBirthday;
            this.gender = gender;
        }
    }

    /*
    The ProfileTask Async function injects all the profile info into the database
    It takes height, weight, birthday and gender
     */
    private class ProfileTask extends AsyncTask<ProfileParams, Boolean, Boolean> {

        @Override
        protected Boolean doInBackground(ProfileParams... params) {

            ProfileParams profile = params[0];
            double cm = profile.cm;
            double kg = profile.kg;
            long mBirthday = profile.mBirthday;
            String gender = profile.gender;
            ProfileService service = ProfileServiceFactory.getProfileService();
            Profile mPro = new Profile();
            mPro.setHeight(cm);
            mPro.setWeight(kg);
            mPro.setBirthDay(mBirthday);
            mPro.setSex(gender);

            try {
                boolean u = service.postUpdatedProfile(mPro);
                return u;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
        protected void onPostExecute(Boolean result) {
            if(result == true) {
                Toast.makeText(getBaseContext(), "Update completed", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getBaseContext(), "Update failed", Toast.LENGTH_SHORT).show();
            }
        }

    }

    /*
    constructor for diabetic task
     */
    private static class DiabeticParams {
        double mRat, nRat, eRat;


        DiabeticParams(double mRat, double nRat, double eRat) {

            this.mRat = mRat;
            this.nRat = nRat;
            this.eRat = eRat;
        }
    }
    /*
        The DiabeticInfoTask Async function injects all the diabetic info into the database
        That is, morning ratio, noon ratio and evening ratio.
    */
    private class DiabeticTask extends AsyncTask<DiabeticParams, Boolean, Boolean> {

        @Override
        protected Boolean doInBackground(DiabeticParams... params) {

            DiabeticService service = DiabeticServiceFactory.getDiabeticService();
            Diabetic mDia = new Diabetic();
            mDia.setMorningRatio(mRat);
            mDia.setNoonRatio(nRat);
            mDia.setEveningRatio(eRat);

            try {
                return service.postDiabeticInfo(mDia);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Boolean result) {
            if(result == true) {
                Toast.makeText(getBaseContext(), "Diabetic Update completed", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getBaseContext(), "Diabetic Update failed", Toast.LENGTH_SHORT).show();
            }
        }

    }

    /*
    The getDiabeticInfoTask Async function gets all the diabetic information from the database and displays in in the view
     */
    private class getDiabeticInfoTask extends AsyncTask<Void, Boolean, Diabetic> {

        @Override
        protected Diabetic doInBackground(Void... params) {

            User mUser = User.getTheUser();
            String mUserId = mUser.getId();
            DiabeticService dService = DiabeticServiceFactory.getDiabeticService();

            try {
                diaInfo = dService.getDiabeticInfo(mUserId);
                //proInfo = pService.getUserProfile(mUserId);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return diaInfo;
        }
        protected void onPostExecute(Diabetic result) {
            if(result == diaInfo) {
                rat1 = diaInfo.getMorningRatio();
                rat2 = diaInfo.getNoonRatio();
                rat3 = diaInfo.getEveningRatio();
                String r1 = Double.toString(rat1);
                mRatio.setText(r1);
                String r2 = Double.toString(rat2);
                nRatio.setText(r2);
                String r3 = Double.toString(rat3);
                eRatio.setText(r3);
            }
            else{
                Toast.makeText(getBaseContext(), "Getting diabetes info failed", Toast.LENGTH_SHORT).show();
            }
        }

    }

    /*
    The getProfileInfoTask Async function gets all the profile information from the database and displays it in the view
     */
    private class getProfileInfoTask extends AsyncTask<Void, Boolean, Profile> {

        @Override
        protected Profile doInBackground(Void... params) {

            User mUser = User.getTheUser();
            String mUserId = mUser.getId();
            ProfileService pService = ProfileServiceFactory.getProfileService();

            try {
                proInfo = pService.getUserProfile(mUserId);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return proInfo;
        }
        protected void onPostExecute(Profile result) {
            if(result == proInfo) {
                set1 = proInfo.getHeight();
                set2 = proInfo.getWeight();
                set3 = proInfo.getSex();
                set4 = proInfo.getBirthDay();
                String s1 = Double.toString(set1);
                height.setText(s1);
                String s2 = Double.toString(set2);
                weight.setText(s2);
                String s3 = set3;
                if(s3.equals("KK")){
                    isMale.setChecked(true);
                }
                else if(s3.equals("KVK")){
                    isFemale.setChecked(true);
                }
                //Formats the unix timestamp into a date form
                DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                String s4 = formatter.format(set4);
                date.setText(s4);
            }
            else{
                Toast.makeText(getBaseContext(), "Getting profile info failed", Toast.LENGTH_SHORT).show();
            }
        }
    }
    /*
    The sendDate function formats birthday into a unix timestamp
     */
    public long sendDate(String birthday) {
        DateFormat formatter;
        Date date;
        long mDate = 0;
        formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            date = formatter.parse(birthday);
            mDate = date.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mDate;
    }
}
