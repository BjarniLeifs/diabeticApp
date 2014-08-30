package ru.calendar;

import com.jberry.dto.User;


public class ToolService {

    public static String getB64Auth() {
        String user = User.getTheUser().getUserName();
        String pass = User.getTheUser().getPassword();
        String usr = "sindri@sindri.is" + ":" + "sindri";
        String ret = "Basic " + android.util.Base64.encodeToString(usr.getBytes(), android.util.Base64.URL_SAFE | android.util.Base64.NO_WRAP);
        return ret;
    }

    public String url() {
        return "jberry.noip.me";
    }
}
