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


public class CurrentForecastFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public CurrentForecastFragment() {
        // Required empty public constructor
    }

    WeatherAPIInterface weatherAPIInterface;
    Call<Forecast> conn;
    Forecast dataForecast;
    ArrayList<Day> dataList;
    ForecastAdapter adapter;
    TextView test;
    ListView weeklyList;

    public static CurrentForecastFragment newInstance() {
        CurrentForecastFragment fragment = new CurrentForecastFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ForecastAdapter adapter;

        if (getArguments() != null) {
            //
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_current_forecast, container, false);

        test = (TextView) view.findViewById(R.id.tv2);
        weeklyList = (ListView) view.findViewById(R.id.currentList);
        dataForecast = new Forecast();
        dataList = new ArrayList<>();
        adapter = new ForecastAdapter(getActivity(), dataList, false);//false mean current forecast layout


        weeklyList.setAdapter(adapter);

        HttpRequest();

        return view;
    }

    private void HttpRequest() {
        try {

            weatherAPIInterface = ApiUtils.getWeatherAPI();

            conn = weatherAPIInterface.listForecasts(BaseInfo.getCity(getContext()), 2);//get 2 days only today and tomorrow



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
            throw new ClassCastException(context.toString() + " must implement OnArticleSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onCurrentFragmentInteraction();
    }
}
