package com.example.myweather.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myweather.Model.City;
import com.example.myweather.Model.Weather;
import com.example.myweather.R;

import java.util.List;

public class WeatherCityAdapter extends BaseAdapter {

    private Context context;
    private List<City> data;

    public WeatherCityAdapter(Context context, List<City> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position +1;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.weather_city, parent, false);
        }

        TextView cityWeatherName = convertView.findViewById(R.id.tv_weather_name);
        TextView cityWeatherTemp = convertView.findViewById(R.id.tv_weather_temp);
        TextView cityWeatherHumidity = convertView.findViewById(R.id.tv_weather_humidity);
        TextView cityWeatherPressure = convertView.findViewById(R.id.tv_weather_pressure);
        TextView cityWeatherClouds = convertView.findViewById(R.id.tv_weather_clouds);
        TextView cityWeatherWind = convertView.findViewById(R.id.tv_weather_wind);
        TextView cityWeatherWeather = convertView.findViewById(R.id.tv_weather_weather);

        City city = data.get(position);

        StringBuilder weatherText = new StringBuilder("Weather: ");

        for(Weather weather : city.getWeather()) {
            String weatherCity = String.format("\n\t Main: %s \n\t Description: %s", weather.getMain(), weather.getDescription());
            weatherText.append(weatherCity).append("\n\t-----------------\n");
        }

        cityWeatherName.setText(String.format("%s - %s", city.getName(), city.getCountry()));
        cityWeatherTemp.setText(String.format("Temp: %.2f%%", city.getTemp()));
        cityWeatherHumidity.setText(String.format("Humidity: %.2f%%", city.getHumidity()));
        cityWeatherPressure.setText(String.format("Pressure: %.2f \"Hg", city.getPressure()));
        cityWeatherClouds.setText(String.format("Clouds: %.1f%%", city.getClouds()));
        cityWeatherWind.setText(String.format("Wind: \n\t Speed: %.1f \n\t Deg: %.1f", city.getWind().getSpeed(), city.getWind().getDeg()));
        cityWeatherWeather.setText(weatherText);

        return convertView;
    }
}
