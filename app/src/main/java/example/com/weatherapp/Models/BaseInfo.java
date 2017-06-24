package example.com.weatherapp.Models;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;


public class BaseInfo {
    public final static String  baseUrl = "http://api.openweathermap.org/data/2.5/forecast/";
    private final static String  MY_PREFS_NAME = "Forecast_Pref";
    public final static String  urlImage = "http://openweathermap.org/img/w/";
    private static SharedPreferences pref = null ;

    private static String city ;

    public static String getCity(Context context) {
        pref = context.getSharedPreferences(MY_PREFS_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        city = pref.getString("city","Cairo");
        editor.apply();

        return city;
    }

    public static void setCity(String newCity,Context context) {
        pref = context.getSharedPreferences(MY_PREFS_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("city",newCity);
        editor.apply();
    }


    public static String formatDate(Integer dt)
    {
        //convert to milliseconds as it timestamp unix in seconds
        Date date = new Date((long)dt*1000);
        String dateFormat = new SimpleDateFormat("EEE MMM dd").format(date);
        return  dateFormat;
    }

}
