package example.com.weatherapp.Connections;

import example.com.weatherapp.Models.BaseInfo;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by MrHacker on 6/22/2017.
 */


//singleton design pattern
public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit()
    {
        if(retrofit==null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BaseInfo.baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit ;
    }
}
