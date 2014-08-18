package ru.education;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import jBerry.MySugar.R;
import ru.Events.Events;

/*
 * Created by Anna on 13.8.2014.
 */
public class EducationActivity extends ActionBarActivity{

    TextView myUrl;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);

         for(int i = 0; i < Events.idLink.length; i++) {

            myUrl = (TextView) findViewById(Events.idLink[i]);
            myUrl.setMovementMethod(LinkMovementMethod.getInstance());

        }
    }
}
