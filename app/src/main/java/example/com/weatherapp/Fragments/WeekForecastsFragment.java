package example.com.weatherapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import example.com.weatherapp.Adapters.ForecastAdapter;
import example.com.weatherapp.Connections.ApiUtils;
import example.com.weatherapp.Connections.WeatherAPIInterface;
import example.com.weatherapp.Models.BaseInfo;
import example.com.weatherapp.Models.Day;
import example.com.weatherapp.Models.Forecast;
import example.com.weatherapp.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WeekForecastsFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    Call<Forecast> conn = null;
    WeatherAPIInterface weatherAPIInterface;
    TextView test;
    ListView weeklyList;
    Forecast dataForecast;
    List<Day> dataList;
    ForecastAdapter adapter;

    public WeekForecastsFragment() {
        // Required empty public constructor
    }

    //factory design pattern
    public static WeekForecastsFragment getInstance() {
        return new WeekForecastsFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_week_forecasts, container, false);

        test = (TextView) view.findViewById(R.id.tv);
        weeklyList = (ListView) view.findViewById(R.id.weeklyList);
        dataForecast = new Forecast();
        dataList = new ArrayList<>();
        adapter = new ForecastAdapter(getActivity(), dataList, true);//true mean weekly forecast layout


        weeklyList.setAdapter(adapter);
        HttpRequest();

        return view;
    }

    private void HttpRequest() {
        try {

            weatherAPIInterface = ApiUtils.getWeatherAPI();

            conn = weatherAPIInterface.listForecasts(BaseInfo.getCity(getContext()), 7);
            test.setText(BaseInfo.getCity(getContext()));
            conn.enqueue(new Callback<Forecast>() {
                @Override
                public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                    dataForecast = response.body();
                    dataList.addAll(dataForecast.getList());
                    adapter.updateList(dataList);
                }

                @Override
                public void onFailure(Call<Forecast> call, Throwable t) {
                    Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();

                }
            });
        } catch (Exception ex) {
            Toast.makeText(getContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onWeeklyFragmentInteraction();
    }
}
