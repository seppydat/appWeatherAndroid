package com.example.myweather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myweather.Adapter.WeatherCityAdapter;
import com.example.myweather.Model.City;
import com.example.myweather.Model.Weather;
import com.example.myweather.Model.Wind;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText search;
    private Button btnSearch;
    private ListView result;
    private static final String API_KEY = "c701cfb1d005e7c9cb2b368352f16bce";

    private void initUI() {
        this.search = findViewById(R.id.edt_main_city);
        this.btnSearch = findViewById(R.id.btn_main_search);
        this.result = findViewById(R.id.lv_main_result);
    }

    private String createUrl(String city) {
        return String.format("https://api.openweathermap.org/data/2.5/find?q=%s&appid=%s&lang=vi&units=metric", city, API_KEY);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initUI();

        this.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = search.getText().toString();

                if (input.isEmpty()) {
                    search.setError("Please Enter City");
                    return;
                }

                try {
                    search(input);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Query not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void search (String query) {
        StringRequest weatherRequest = new StringRequest(Request.Method.GET, createUrl(query),
                response -> {
                    try{
                        List<City> listCity = new ArrayList<>();

                        //Create a JSON object containing information from the API.
                        JSONObject weatherRequestObject = new JSONObject(response);
                        JSONArray listCityWeathers = weatherRequestObject.getJSONArray("list");

                        for (int i = 0; i < listCityWeathers.length() ; i++) {
                            JSONObject cityWeather = (JSONObject) listCityWeathers.get(i);

                            Wind wind = new Wind(cityWeather.getJSONObject("wind").getDouble("speed"), cityWeather.getJSONObject("wind").getDouble("deg"));
                            List<Weather> weatherCitys = new ArrayList<>();

                            for (int j = 0; j < cityWeather.getJSONArray("weather").length(); j++) {
                                JSONObject weatherOfCity = (JSONObject) cityWeather.getJSONArray("weather").get(j);
                                Weather weather = new Weather(weatherOfCity.getString("main"), weatherOfCity.getString("description"));
                                weatherCitys.add(weather);
                            }

                            City city = new City();
                            city.setCountry(cityWeather.getJSONObject("sys").getString("country"));
                            city.setName(cityWeather.getString("name"));
                            city.setWeather(weatherCitys);
                            city.setClouds(cityWeather.getJSONObject("clouds").getDouble("all"));
                            city.setHumidity(cityWeather.getJSONObject("main").getDouble("humidity"));
                            city.setPressure(cityWeather.getJSONObject("main").getDouble("pressure"));
                            city.setTemp(cityWeather.getJSONObject("main").getDouble("temp"));
                            city.setWind(wind);

                            listCity.add(city);
                        }

                        if (listCity.isEmpty()) {
                            Toast.makeText(MainActivity.this, "This city not found data weather", Toast.LENGTH_SHORT).show();
                        } else {
                            WeatherCityAdapter weatherCityAdapter = new WeatherCityAdapter(MainActivity.this, listCity);
                            result.setAdapter(weatherCityAdapter);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                volleyError -> Toast.makeText(MainActivity.this, volleyError.getMessage(), Toast.LENGTH_SHORT).show()
        );

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(weatherRequest);
    }
}