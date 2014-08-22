package ru.calendar;

import org.apache.commons.codec.binary.Base64;

public class ToolService {
    public String userEncoded(){


        Base64 b64 = new Base64();
        String usr = "kalli@kalli.com" + ":" + "kalli";
        return b64.encodeAsString(usr.getBytes());
    }
    public String url(){
        return "jberry.noip.me";
    }
}
