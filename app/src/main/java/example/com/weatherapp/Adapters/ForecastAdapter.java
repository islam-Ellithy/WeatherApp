package example.com.weatherapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import example.com.weatherapp.Models.BaseInfo;
import example.com.weatherapp.Models.Day;
import example.com.weatherapp.R;

/**
 * Created by MrHacker on 6/23/2017.
 */

public class ForecastAdapter extends BaseAdapter {

    private Activity activity = null;
    private List<Day> weekForecast = null;
    private LayoutInflater inflater = null;
    private boolean isWeekly = true;

    public ForecastAdapter(Activity activity, List<Day> weekForecast, boolean isWeekly) {
        this.weekForecast = new ArrayList<>();
        this.weekForecast.addAll(weekForecast);
        this.activity = activity;
        this.isWeekly = isWeekly;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return weekForecast.size();
    }


    @Override
    public Object getItem(int position) {
        return weekForecast.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        private TextView tv;//date
        private TextView ftv;//forecast
        private ImageView iv;//icon forecast
        private View view;

        ViewHolder(View view) {
            this.view = view;
            tv = (TextView) view.findViewById(R.id.date);
            ftv = (TextView) view.findViewById(R.id.forecast);
            iv = (ImageView) view.findViewById(R.id.icon);
        }

        public ImageView getIv() {
            return iv;
        }

        public void setIv(ImageView iv) {
            this.iv = iv;
        }

        public TextView getTv() {
            return tv;
        }

        public void setTv(TextView tv) {
            this.tv = tv;
        }

        public TextView getFtv() {
            return ftv;
        }

        public void setFtv(TextView ftv) {
            this.ftv = ftv;
        }
    }

    public void updateList(List<Day> newlist) {
        weekForecast.clear();
        weekForecast.addAll(newlist);
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View view = convertView;
        if (view == null) {
            if (isWeekly)
                view = inflater.inflate(R.layout.day_forecast_item, null);
            else
                view = inflater.inflate(R.layout.current_forecast_item, null);

        }
        TextView day;
        ViewHolder vh = new ViewHolder(view);

        Day dy = weekForecast.get(position);
        if (dy != null) {

            String maxDegree = String.valueOf((int) Math.round(dy.getTemp().getMax()));
            String minDegree = String.valueOf((int) Math.round(dy.getTemp().getMin()));

            if (!isWeekly) {
                day = (TextView) view.findViewById(R.id.day);

                if (position == 0) {
                    day.setText("Today");
                } else if (position == 1) {
                    day.setText("Tomorrow");
                }
                vh.getTv().setText(BaseInfo.formatDate(weekForecast.get(position).getDt()));

                vh.getFtv().setText(maxDegree + "/" + minDegree);

            } else {
                vh.getTv().setText(BaseInfo.formatDate(weekForecast.get(position).getDt()) + " - "
                        + maxDegree + "/" + minDegree
                );
            }
            Picasso.with(activity)
                    .load(BaseInfo.urlImage + dy.getWeather().get(0).getIcon() + ".png")
                    .into(vh.getIv());
        }

        return view;
    }
}
