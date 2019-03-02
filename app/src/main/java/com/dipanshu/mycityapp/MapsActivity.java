package com.dipanshu.mycityapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, AdapterView.OnItemSelectedListener {

    private GoogleMap mMap;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Location lastLocation;
    private Marker currentUserLocationMarker;
    private static final int Request_User_Location_Code = 99;
    private double latitude, longitude;
    private int ProximityRadius = 10000;


    private Spinner spinner;
    private static final String[] paths = {"Hospitals", "Schools", "Restaurants","Police Stations","Banks","Shopping Malls","Stores","Night Clubs","Bars","Dustbins"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            checkUserLocationPermission();
        }
        spinner = (Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MapsActivity.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    private String getUrl(double latitude, double longitude, String nearbyPlace)
    {
        StringBuilder googleURL = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googleURL.append("location=" + latitude + "," + longitude);
        googleURL.append("&radius=" + ProximityRadius);
        googleURL.append("&type=" + nearbyPlace);
        googleURL.append("&sensor=true");
        googleURL.append("&key=" + "AIzaSyDdb9ZxodTnFy7MdLa8BUrXZVD8msLgoME");

        Log.d("GoogleMapsActivity", "url = " + googleURL.toString());

        return googleURL.toString();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            buildGoogleApiClient();

            mMap.setMyLocationEnabled(true);
        }

    }



    public boolean checkUserLocationPermission()
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION))
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_User_Location_Code);
            }
            else
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_User_Location_Code);
            }
            return false;
        }
        else
        {
            return true;
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        switch (requestCode)
        {
            case Request_User_Location_Code:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                    {
                        if (googleApiClient == null)
                        {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                }
                else
                {
                    Toast.makeText(this, "Permission Denied...", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }




    protected synchronized void buildGoogleApiClient()
    {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        googleApiClient.connect();
    }


    @Override
    public void onLocationChanged(Location location)
    {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
//        latitude = 28.6259370;
//        longitude = 77.3575840;

        lastLocation = location;

        if (currentUserLocationMarker != null)
        {
            currentUserLocationMarker.remove();
        }

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
//        LatLng latLng = new LatLng(28.6259370,77.3575840);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("user Current Location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

        currentUserLocationMarker = mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(12));

        if (googleApiClient != null)
        {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
        }
    }


    @Override
    public void onConnected(@Nullable Bundle bundle)
    {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1100);
        locationRequest.setFastestInterval(1100);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
        }


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


        Object transferData[] = new Object[2];
        GetNearbyPlaces getNearbyPlaces = new GetNearbyPlaces();

        switch (i){
            case 0:
                mMap.clear();
                String url = getUrl(latitude, longitude, "hospital");
                transferData[0] = mMap;
                transferData[1] = url;

                getNearbyPlaces.execute(transferData);
                Toast.makeText(this, "Searching for Nearby Hospitals...", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Showing Nearby Hospitals...", Toast.LENGTH_SHORT).show();
                break;

            case 1:
                mMap.clear();
                url = getUrl(latitude, longitude, "school");
                transferData[0] = mMap;
                transferData[1] = url;

                getNearbyPlaces.execute(transferData);
                Toast.makeText(this, "Searching for Nearby Schools...", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Showing Nearby Schools...", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                mMap.clear();
                url = getUrl(latitude, longitude, "restaurant");
                transferData[0] = mMap;
                transferData[1] = url;

                getNearbyPlaces.execute(transferData);
                Toast.makeText(this, "Searching for Nearby Restaurants...", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Showing Nearby Restaurants...", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                mMap.clear();
                url = getUrl(latitude, longitude, "police");
                transferData[0] = mMap;
                transferData[1] = url;

                getNearbyPlaces.execute(transferData);
                Toast.makeText(this, "Searching for Nearby police stations...", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Showing Nearby police stations...", Toast.LENGTH_SHORT).show();
                break;

            case 4:

                mMap.clear();
                url = getUrl(latitude, longitude, "bank");
                transferData[0] = mMap;
                transferData[1] = url;

                getNearbyPlaces.execute(transferData);
                Toast.makeText(this, "Searching for Nearby Banks...", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Showing Nearby Banks...", Toast.LENGTH_SHORT).show();
                break;
            case 5:

                mMap.clear();
                url = getUrl(latitude, longitude, "shopping_mall");
                transferData[0] = mMap;
                transferData[1] = url;

                getNearbyPlaces.execute(transferData);
                Toast.makeText(this, "Searching for Shopping Malls...", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Showing Shopping Malls...", Toast.LENGTH_SHORT).show();
                break;

            case 6:

                mMap.clear();
                url = getUrl(latitude, longitude, "store");
                transferData[0] = mMap;
                transferData[1] = url;

                getNearbyPlaces.execute(transferData);
                Toast.makeText(this, "Searching for Store...", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Showing Store...", Toast.LENGTH_SHORT).show();
                break;

            case 7:

                mMap.clear();
                url = getUrl(latitude, longitude, "night_club");
                transferData[0] = mMap;
                transferData[1] = url;

                getNearbyPlaces.execute(transferData);
                Toast.makeText(this, "Searching for night_club...", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Showing night_club...", Toast.LENGTH_SHORT).show();
                break;
            case 8:

                mMap.clear();
                url = getUrl(latitude, longitude, "bar");
                transferData[0] = mMap;
                transferData[1] = url;

                getNearbyPlaces.execute(transferData);
                Toast.makeText(this, "Searching for Bars...", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Showing Bars...", Toast.LENGTH_SHORT).show();
                break;

            case 9:///////////

                mMap.clear();
                LatLng dustbin1 = new LatLng(latitude+0.0005,longitude+0.0013);
                MarkerOptions dustbin1marker1 = new MarkerOptions();
                dustbin1marker1.position(dustbin1);
                dustbin1marker1.title("Dustbin1");
                dustbin1marker1.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                mMap.addMarker(dustbin1marker1);

                ///////////
                LatLng dustbin2 = new LatLng(latitude+0.0004,longitude+0.0019);
                MarkerOptions dustbin1marker2 = new MarkerOptions();
                dustbin1marker2.position(dustbin2);
                dustbin1marker2.title("Dustbin2");
                dustbin1marker2.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                mMap.addMarker(dustbin1marker2);

///////////
                LatLng dustbin3 = new LatLng(latitude+0.0009,longitude+0.0005);
                MarkerOptions dustbin1marker3 = new MarkerOptions();
                dustbin1marker3.position(dustbin3);
                dustbin1marker3.title("Dustbin3");
                dustbin1marker3.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                mMap.addMarker(dustbin1marker3);

///////////
                LatLng dustbin4 = new LatLng(latitude+0.0011,longitude+0.0003);
                MarkerOptions dustbin1marker4 = new MarkerOptions();
                dustbin1marker4.position(dustbin4);
                dustbin1marker4.title("Dustbin4");
                dustbin1marker4.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                mMap.addMarker(dustbin1marker4);

///////////
                LatLng dustbin5 = new LatLng(latitude+0.0007,longitude+0.0007);
                MarkerOptions dustbin1marker5 = new MarkerOptions();
                dustbin1marker5.position(dustbin5);
                dustbin1marker5.title("Dustbin5");
                dustbin1marker5.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                mMap.addMarker(dustbin1marker5);

///////////
                LatLng dustbin6 = new LatLng(latitude+0.0015,longitude+0.0012);
                MarkerOptions dustbin1marker6 = new MarkerOptions();
                dustbin1marker6.position(dustbin6);
                dustbin1marker6.title("Dustbin6");
                dustbin1marker6.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                mMap.addMarker(dustbin1marker6);

///////////
                LatLng dustbin7 = new LatLng(latitude+0.0017,longitude+0.0009);
                MarkerOptions dustbin1marker7 = new MarkerOptions();
                dustbin1marker7.position(dustbin7);
                dustbin1marker7.title("Dustbin7");
                dustbin1marker7.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                mMap.addMarker(dustbin1marker7);

///////////
                LatLng dustbin8 = new LatLng(latitude+0.0009,longitude+0.0006);
                MarkerOptions dustbin1marker8 = new MarkerOptions();
                dustbin1marker8.position(dustbin8);
                dustbin1marker8.title("Dustbin8");
                dustbin1marker8.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                mMap.addMarker(dustbin1marker8);

///////////
                LatLng dustbin9 = new LatLng(latitude+0.0001,longitude+0.0018);
                MarkerOptions dustbin1marker9 = new MarkerOptions();
                dustbin1marker9.position(dustbin9);
                dustbin1marker9.title("Dustbin9");
                dustbin1marker9.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                mMap.addMarker(dustbin1marker9);


                mMap.moveCamera(CameraUpdateFactory.newLatLng(dustbin9));
                mMap.animateCamera(CameraUpdateFactory.zoomBy(12));


                break;




        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}