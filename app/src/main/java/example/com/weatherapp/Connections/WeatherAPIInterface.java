package example.com.weatherapp.Connections;

import example.com.weatherapp.Models.Forecast;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by MrHacker on 6/22/2017.
 */

public interface WeatherAPIInterface {
    String key = "c550788d001ff159854a8faa1a4066b7";
    @GET("daily?appid=c550788d001ff159854a8faa1a4066b7&mode=json&units=metric")
    Call<Forecast> listForecasts(@Query("q") String cityName,@Query("cnt")int cnt);
}
