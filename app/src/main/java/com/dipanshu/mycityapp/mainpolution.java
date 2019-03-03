package com.dipanshu.mycityapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class mainpolution extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pollutionactivity);

        final Spinner stateSpinner = (Spinner)findViewById(R.id.stateSpinner);
        final Spinner citySpinner = (Spinner)findViewById(R.id.citySpinner);
        final Button fetch = (Button)findViewById(R.id.button);
        final TextView textView= (TextView)findViewById(R.id.resultView);
        final String citySelected[] = {""};
        ArrayAdapter<CharSequence> adapterState=ArrayAdapter.createFromResource(this,R.array.state,android.R.layout.simple_spinner_item);
        adapterState.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateSpinner.setAdapter(adapterState);
        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 switch (position){
                    case 0: Toast.makeText(mainpolution.this, "Select State", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:ArrayAdapter<CharSequence> adapterCityAP=ArrayAdapter.createFromResource(mainpolution.this,R.array.cityAP,android.R.layout.simple_spinner_item);
                        adapterCityAP.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        citySpinner.setAdapter(adapterCityAP);
                        Toast.makeText(mainpolution.this, "Select City/Station", Toast.LENGTH_SHORT).show();
                        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                citySelected[0] = (String) parent.getItemAtPosition(position);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        break;
                    case 2:ArrayAdapter<CharSequence> adapterCityA=ArrayAdapter.createFromResource(mainpolution.this,R.array.cityA,android.R.layout.simple_spinner_item);
                        adapterCityA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        citySpinner.setAdapter(adapterCityA);
                        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                citySelected[0] = (String) parent.getItemAtPosition(position);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        break;
                    case 3:ArrayAdapter<CharSequence> adapterCityB=ArrayAdapter.createFromResource(mainpolution.this,R.array.cityB,android.R.layout.simple_spinner_item);
                        adapterCityB.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        citySpinner.setAdapter(adapterCityB);
                        Toast.makeText(mainpolution.this, "Select City/Station", Toast.LENGTH_SHORT).show();
                        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                citySelected[0] = (String) parent.getItemAtPosition(position);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        break;
                    case 4:ArrayAdapter<CharSequence> adapterCityD=ArrayAdapter.createFromResource(mainpolution.this,R.array.cityD,android.R.layout.simple_spinner_item);
                        adapterCityD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        citySpinner.setAdapter(adapterCityD);
                        Toast.makeText(mainpolution.this, "Select City/Station", Toast.LENGTH_SHORT).show();
                        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                citySelected[0] = (String) parent.getItemAtPosition(position);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        try {
            fetch.setOnClickListener(new View.OnClickListener() {
                JSONArray polutionJSON;
                String jsonINITIAL ="[]";
                JSONArray recordJSON= new JSONArray(jsonINITIAL);
                JSONObject temp;
                String polutionResult="";
                String polutionStation="";
                @Override
                public void onClick(View v) {
                    if (recordJSON.length()==0) {
                        try {
                            recordJSON = new polutionData().execute().get();
                            Toast.makeText(mainpolution.this, "Got the data", Toast.LENGTH_SHORT).show();
                            } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }temp = new JSONObject();
                    if(recordJSON.length()!=0){
                        try {
                            polutionStation="Data provided by data.gov.in\nRecords last updated:"+recordJSON.getJSONObject(0).getString("last_update");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        for(int i=0;i<recordJSON.length();i++){
                            try {
                                temp = recordJSON.getJSONObject(i);
                                if(temp.getString("station").equals(citySelected[0]))
                                    polutionStation +="\nPolutant : "+temp.getString("pollutant_id")
                                            +"\nMin:"+temp.getString("pollutant_min")
                                            +"\tAvg:"+temp.getString("pollutant_avg")
                                            +"\tMax:"+temp.getString("pollutant_max");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        textView.setText(polutionStation);
                        polutionStation="";
                    }

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
        WebView webView=(WebView)findViewById(R.id.livemap);
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDisplayZoomControls(false);
        webView.loadUrl("https://aqicn.org/map/india/m");
    }
}
