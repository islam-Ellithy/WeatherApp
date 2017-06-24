package example.com.weatherapp.Connections;

/**
 * Created by MrHacker on 6/22/2017.
 */

public class ApiUtils {
    public static WeatherAPIInterface getWeatherAPI()
    {
        return RetrofitClient.getRetrofit().create(WeatherAPIInterface.class);
    }
}
