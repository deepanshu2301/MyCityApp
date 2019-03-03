package com.dipanshu.mycityapp;

import android.os.AsyncTask;

import org.json.JSONArray;
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

public class polutionData extends AsyncTask<Void,Void,JSONArray> {

    @Override
    protected JSONArray doInBackground(Void...voids) {
        JSONArray polutionData = null;
        HttpURLConnection urlConnection = null;
        String mainurl = "http://api.data.gov.in/resource/3b01bcb8-0b14-4abf-b6f2-c1bfd384ba69?api-key=579b464db66ec23bdd000001258e7305e65b42f9567b051a7aee2fb4&format=json";
        URL url = null;
        try {
            url = new URL(mainurl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
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
            polutionData = jsonObj.getJSONArray("records");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {

            urlConnection.disconnect();
        }

        return polutionData;
    }
}

