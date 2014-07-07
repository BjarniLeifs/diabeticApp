package ru.Client;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Created by Krissi on 1.7.2014.
 */
public class main {

    static HttpClient httpClient; //hafa static eða eh annað? Hægt að sleppa global?
    //Keyrt í upphafi til að initialize-a tengingu.
    //HttpServer???!! Það myndi þá hafa mongodb://localhost:port/jberry urlið?
    public static void connect(String url)throws Exception{

        httpClient = new DefaultHttpClient();
        //senda request til að staðfesta tenginu
        HttpGet httpget = new HttpGet("mongodb://localhost:27017/jBerry"); //ATH Þarf örugglega að vera kall í API-ið
        HttpResponse response;
        //eh til að staðfesta tenginguna
        response = httpClient.execute(httpget);
        HttpEntity entity = response.getEntity();
        System.out.println("Login form get: " + response.getStatusLine());
        if (entity != null) {
            entity.consumeContent();
        }

    }
   /* public static string newUser(int ID)
    {
        HttpPost("http://localhost:3000/api/register");
    }*/
    //Föll sem notuð eru til að framkvæma HTTP köllin
}
