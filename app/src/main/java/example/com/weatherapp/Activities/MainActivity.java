package example.com.weatherapp.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import example.com.weatherapp.Adapters.ForecastAdapter;
import example.com.weatherapp.Connections.WeatherAPIInterface;
import example.com.weatherapp.Fragments.CurrentForecastFragment;
import example.com.weatherapp.Fragments.EditLocationFragment;
import example.com.weatherapp.Fragments.WeekForecastsFragment;
import example.com.weatherapp.Models.BaseInfo;
import example.com.weatherapp.Models.Day;
import example.com.weatherapp.Models.Forecast;
import example.com.weatherapp.R;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity implements EditLocationFragment.OnFragmentInteractionListener, WeekForecastsFragment.OnFragmentInteractionListener ,CurrentForecastFragment.OnFragmentInteractionListener{

    Call<Forecast> conn = null;
    WeatherAPIInterface weatherAPIInterface;
    Button get;
    TextView test;
    ListView weeklyList;
    Forecast dataForecast;
    List<Day> dataList;
    ForecastAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button currentForecast = (Button)findViewById(R.id.currentForecast);
        Button weekForecast = (Button)findViewById(R.id.weekForecast);

        currentForecast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCurrentForecastFragment();
            }
        });

        weekForecast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToWeekForecastFragment();
            }
        });
        goToWeekForecastFragment();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public void goToWeekForecastFragment()
    {

        WeekForecastsFragment weekForecastsFragment = WeekForecastsFragment.getInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_layout,weekForecastsFragment)
                .commit();
    }
    public void goToCurrentForecastFragment()
    {
        CurrentForecastFragment currentForecastFragment = CurrentForecastFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_layout,currentForecastFragment)
                .commit();
    }

    public void goToChangeLocationFragment()
    {
        EditLocationFragment editLocationFragment = EditLocationFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_layout,editLocationFragment)
                .commit();
    }


    public  void changeCity()
    {

        Toast.makeText(getApplicationContext(),BaseInfo.getCity(getBaseContext()),Toast.LENGTH_SHORT).show();

        goToWeekForecastFragment();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.edit_city) {
            goToChangeLocationFragment();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onEditFragmentInteraction() {
        changeCity();
    }

    @Override
    public void onWeeklyFragmentInteraction() {

    }

    @Override
    public void onCurrentFragmentInteraction() {

    }
}
