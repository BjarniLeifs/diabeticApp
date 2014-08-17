package ru.education;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;
import jBerry.MySugar.R;
import ru.Events.Events;

/*
 * Created by Anna on 13.8.2014.
 */
public class EducationActivity extends ActionBarActivity{

    TextView myUrl;
    private static final String HTTPS = "https://";
    private static final String HTTP = "http://";
    private String urlString;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);

         for(int i = 0; i < Events.idLink.length; i++) {

            myUrl = (TextView) findViewById(Events.idLink[i]);
            myUrl.setMovementMethod(LinkMovementMethod.getInstance());

        }
    }
}
