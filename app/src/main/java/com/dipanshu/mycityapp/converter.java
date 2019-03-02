package com.dipanshu.mycityapp;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class converter extends AsyncTask<Object,String,Double> {
    Double resultValue=0.0;

    @Override
    protected Double doInBackground(Object... objects) {
        String fromSpinner = (String) objects[0];
        String toSpinner = (String) objects[1];
        String moneyText = (String) objects[2];

        HttpURLConnection urlConnection = null;
        String mainurl = "http://data.fixer.io/api/latest?access_key=52afe14cf5a36c4555a95f88932adce0&symbols";
        String modifiedurl = mainurl + fromSpinner + "," + toSpinner;
        URL url = null;
        try {
            url = new URL(modifiedurl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection urlConnection1 = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader inReader = new BufferedReader(new InputStreamReader(in));
            String inputLine = "";
            String fullStr = "";
            while ((inputLine = inReader.readLine()) != null) {
                fullStr += inputLine;
            }

            JSONObject jsonObj = new JSONObject(fullStr);
            JSONObject result = jsonObj.getJSONObject("rates");
            Double moneyValue = Double.valueOf(moneyText);
            if (fromSpinner.equals(toSpinner)) {
                resultValue = moneyValue;
            } else {
                Double fromFactor = result.getDouble(fromSpinner);
                Double toFactor = result.getDouble(toSpinner);
                Double rateValue = toFactor / fromFactor;
                resultValue = moneyValue * rateValue;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }finally {

            urlConnection.disconnect();
        }

        return resultValue;
    }

}

