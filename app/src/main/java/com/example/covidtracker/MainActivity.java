package com.example.covidtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView tvCases, tvRecovered, tvCritical, tvActive, tvTodayCases, tvTotalDeaths, tvTodayDeaths, tvTodayRecovered, tvCriticalInEstonia, tvActiveInEstonia,
    tvCasesInEstonia, tvRecoveredInEstonia, tvDeathsInEstonia, tvCasesInEstoniaToday, tvRecoveredInEstoniaToday, tvDeathsInEstoniaToday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvCases = findViewById(R.id.tvCases);
        tvRecovered = findViewById(R.id.tvRecovered);
        tvTotalDeaths = findViewById(R.id.tvDeaths);
        tvTodayCases = findViewById(R.id.tvTodayCases);
        tvTodayDeaths = findViewById(R.id.tvTodayDeaths);
        tvTodayRecovered = findViewById(R.id.tvTodayRecovered);
        tvCritical = findViewById(R.id.tvCriticalCases);
        tvActive = findViewById(R.id.tvActiveCases);
        tvCriticalInEstonia = findViewById(R.id.tvCriticalCasesinEstonia);
        tvActiveInEstonia = findViewById(R.id.tvNumberofActiveCasesinEstonia);
        tvCasesInEstonia = findViewById(R.id.tvCasesEstonia);
        tvRecoveredInEstonia = findViewById(R.id.tvRecoveredInEstonia);
        tvDeathsInEstonia = findViewById(R.id.tvNumberofDeathsinEstonia);
        tvCasesInEstoniaToday = findViewById(R.id.tvCasesTodayinEstonia);
        tvRecoveredInEstoniaToday = findViewById(R.id.tvRecoveredinEstoniaToday);
        tvDeathsInEstoniaToday = findViewById(R.id.tvDeathsinEstoniaToday);


        fetchdata();
        dataOfEstonia();

    }
    private void fetchdata() {
        String url = "https://disease.sh/v3/covid-19/all";

        StringRequest request
                = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener() {

                    @Override
                    public void onResponse(Object response) {

                        try {

                            JSONObject jsonObject
                                    = new JSONObject(
                                    response.toString());


                            tvCases.setText(jsonObject.getString("cases"));

                            tvRecovered.setText(jsonObject.getString("recovered"));

                            tvTotalDeaths.setText(jsonObject.getString("deaths"));

                            tvTodayCases.setText(jsonObject.getString("todayCases"));

                            tvTodayDeaths.setText(jsonObject.getString("todayDeaths"));

                            tvTodayRecovered.setText(jsonObject.getString("todayRecovered"));

                            tvActive.setText(jsonObject.getString("active"));

                            tvCritical.setText(jsonObject.getString("critical"));


                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(
                            VolleyError error)
                    {
                        Toast.makeText(MainActivity.this,
                                error.getMessage(),
                                Toast.LENGTH_SHORT)
                                .show();
                    }
                });

        RequestQueue requestQueue
                = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void dataOfEstonia() {
        String url = "https://disease.sh/v3/covid-19/countries/estonia";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());

                    tvActiveInEstonia.setText(jsonObject.getString("active"));

                    tvCriticalInEstonia.setText(jsonObject.getString("critical"));

                    tvCasesInEstonia.setText(jsonObject.getString("cases"));

                    tvRecoveredInEstonia.setText(jsonObject.getString("recovered"));

                    tvDeathsInEstonia.setText(jsonObject.getString("deaths"));

                    tvCasesInEstoniaToday.setText(jsonObject.getString("todayCases"));

                    tvDeathsInEstoniaToday.setText(jsonObject.getString("todayDeaths"));

                    tvRecoveredInEstoniaToday.setText(jsonObject.getString("todayRecovered"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }




}