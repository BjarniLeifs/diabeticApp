package ru.Settings;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.jberry.dto.Diabetic;
import com.jberry.dto.Profile;
import com.jberry.services.diabetic.DiabeticService;
import com.jberry.services.diabetic.DiabeticServiceFactory;
import com.jberry.services.profile.ProfileService;
import com.jberry.services.profile.ProfileServiceFactory;

import java.io.IOException;

import jBerry.MySugar.R;

/**
 * Created by Anna on 16.7.2014.
 */
public class SettingsActivity extends ActionBarActivity {
    EditText height, weight, date, mRatio, nRatio, eRatio;
    CheckBox isMale, isFemale;
    Button save;
    String gender, birthday;
    double cm, kg, mRat, nRat, eRat;
    long mBirthday;

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

        birthday = date.getText().toString();
        kg = Double.parseDouble(weight.getText().toString());
        cm = Double.parseDouble(height.getText().toString());
        mRat = Double.parseDouble(mRatio.getText().toString());
        nRat = Double.parseDouble(nRatio.getText().toString());
        eRat = Double.parseDouble(eRatio.getText().toString());

        //Diabetic res = getDiabeticInfo();
       // double Rat1 = res.getMorningRatio();
        //double Rat2 = res.getNoonRatio();
        //double Rat3 = res.getEveningRatio();
       // mRatio.setText((int) Rat1);
       // nRatio.setText((int) Rat2);
        //eRatio.setText((int) Rat3);

        CompoundButton.OnCheckedChangeListener genderListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    switch (buttonView.getId()) {
                        case R.id.male:
                            isMale.setChecked(true);
                            isFemale.setChecked(false);
                            gender = "KK";
                            break;
                        case R.id.female:
                            isFemale.setChecked(true);
                            isMale.setChecked(false);
                            gender = "KVK";
                            break;
                    }
                }
            }
        };

        /*save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBirthday = sendDate(birthday);

                try {
                    sendDiabeticInfo(mRat, nRat, eRat);
                    sendProfileInfo(cm, kg, mBirthday, gender);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // Toast.makeText(getBaseContext(), "Þú átt afmæli" + year, Toast.LENGTH_SHORT).show();
            }
        });
*/
        isFemale.setOnCheckedChangeListener(genderListener);
        isMale.setOnCheckedChangeListener(genderListener);

    }
    //Þessi tvö eru að sett-a gildi sem notandi slær inn
    //það þarf að implimenta í menue activity kannski að þegar kallað er á profile
    //þá eru sóttar upplýsingar sem eru þegar til staðar í database
    private class ProfileTask extends AsyncTask<Object, Boolean, Boolean>{

        @Override
        protected Boolean doInBackground(Object... params) {
            Profile pUser = (Profile)params[0];//Fyrri params er Profile Object
            Diabetic dUser = (Diabetic)params[1];//Seinni er diabetic Object
            boolean checker1 = false;
            boolean checker2 = false;

            ProfileService pServ = ProfileServiceFactory.getProfileService();
            DiabeticService dServ = DiabeticServiceFactory.getDiabeticService();


            try {
                checker1 = pServ.postUpdatedProfile(pUser);
                checker2 = dServ.postDiabeticInfo(dUser);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(checker1 == true && checker2 == true)
            {
                return true;
            }

            return false;
        }
        protected void onPostExecute(Boolean i){
            //Það sem gerist eeftir að búið er að update-a profile.

        }
    }

}

