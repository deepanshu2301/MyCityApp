package com.dipanshu.mycityapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.dipanshu.mycityapp.GetNearbyPlacesData;
import com.dipanshu.mycityapp.R;
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

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener{


    private GoogleMap mMap;
    private GoogleApiClient client;
    private LocationRequest locationRequest;
    private Location lastlocation;
    private Marker currentLocationmMarker;
    public static final int REQUEST_LOCATION_CODE = 99;
    int PROXIMITY_RADIUS = 5000;
    double latitude,longitude;
    private int supported_types;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);




        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            checkLocationPermission();

        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Spinner spinner = (Spinner)findViewById(R.id.typeSpinner);
        ArrayAdapter<CharSequence> stateAdapter = ArrayAdapter.createFromResource(this,R.array.supported_types,android.R.layout.simple_selectable_list_item);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(stateAdapter);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode)
        {
            case REQUEST_LOCATION_CODE:
                if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) !=  PackageManager.PERMISSION_GRANTED)
                    {
                        if(client == null)
                        {
                            bulidGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                }
                else
                {
                    Toast.makeText(this,"Permission Denied" , Toast.LENGTH_LONG).show();
                }
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            bulidGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }
    }


    protected synchronized void bulidGoogleApiClient() {
        client = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
        client.connect();

    }

    @Override
    public void onLocationChanged(Location location) {

        latitude = location.getLatitude();
        longitude = location.getLongitude();
        lastlocation = location;
        if(currentLocationmMarker != null)
        {
            currentLocationmMarker.remove();

        }
        Log.d("lat = ",""+latitude);
        LatLng latLng = new LatLng(location.getLatitude() , location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        currentLocationmMarker = mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(15));


        if(client != null)
        {
            LocationServices.FusedLocationApi.removeLocationUpdates(client,this);
        }
    }


    public void onClick(View v)
    {
        Object dataTransfer[] = new Object[2];
        GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
        EditText tf_location =  findViewById(R.id.TF_location);
        SeekBar seekBar= findViewById(R.id.seekbar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                PROXIMITY_RADIUS=progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        switch(v.getId())
        {
            case R.id.B_search:
                String location = tf_location.getText().toString();
                List<Address> addressList;
//                Toast.makeText(getBaseContext(),""+location,Toast.LENGTH_SHORT).show();
                String location1=location;
                location1.toLowerCase();
                if(location1.equals("dustbin") ||location1.equals("dustbins")  ){
                    ///////////
//                    Toast.makeText(getBaseContext(),"dustbins..",Toast.LENGTH_SHORT).show();
                    location="";
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



                }
                String location2=location;
                location2.toLowerCase();
                if(location2.equals("wifi") ||location2.equals("wifis")  ){
///////////
                    LatLng dustbin1 = new LatLng(latitude+0.0008,longitude+0.0013);
                    MarkerOptions dustbin1marker1 = new MarkerOptions();
                    dustbin1marker1.position(dustbin1);
                    dustbin1marker1.title("Wifi1");
                    dustbin1marker1.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
                    mMap.addMarker(dustbin1marker1);

                    ///////////
                    LatLng dustbin2 = new LatLng(latitude+0.0014,longitude+0.0019);
                    MarkerOptions dustbin1marker2 = new MarkerOptions();
                    dustbin1marker2.position(dustbin2);
                    dustbin1marker2.title("Wifi2");
                    dustbin1marker2.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
                    mMap.addMarker(dustbin1marker2);

///////////
                    LatLng dustbin3 = new LatLng(latitude+0.0019,longitude+0.0015);
                    MarkerOptions dustbin1marker3 = new MarkerOptions();
                    dustbin1marker3.position(dustbin3);
                    dustbin1marker3.title("Wifi3");
                    dustbin1marker3.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
                    mMap.addMarker(dustbin1marker3);

///////////
                    LatLng dustbin4 = new LatLng(latitude+0.0005,longitude+0.0013);
                    MarkerOptions dustbin1marker4 = new MarkerOptions();
                    dustbin1marker4.position(dustbin4);
                    dustbin1marker4.title("Wifi4");
                    dustbin1marker4.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
                    mMap.addMarker(dustbin1marker4);

///////////
                    LatLng dustbin5 = new LatLng(latitude+0.0017,longitude+0.0003);
                    MarkerOptions dustbin1marker5 = new MarkerOptions();
                    dustbin1marker5.position(dustbin5);
                    dustbin1marker5.title("Wifi5");
                    dustbin1marker5.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
                    mMap.addMarker(dustbin1marker5);

///////////
                    LatLng dustbin6 = new LatLng(latitude+0.0003,longitude+0.0012);
                    MarkerOptions dustbin1marker6 = new MarkerOptions();
                    dustbin1marker6.position(dustbin6);
                    dustbin1marker6.title("Wifi6");
                    dustbin1marker6.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
                    mMap.addMarker(dustbin1marker6);

///////////
                    LatLng dustbin7 = new LatLng(latitude+0.0007,longitude+0.0009);
                    MarkerOptions dustbin1marker7 = new MarkerOptions();
                    dustbin1marker7.position(dustbin7);
                    dustbin1marker7.title("Wifi7");
                    dustbin1marker7.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
                    mMap.addMarker(dustbin1marker7);

///////////
                    LatLng dustbin8 = new LatLng(latitude+0.0015,longitude+0.0006);
                    MarkerOptions dustbin1marker8 = new MarkerOptions();
                    dustbin1marker8.position(dustbin8);
                    dustbin1marker8.title("Wifi8");
                    dustbin1marker8.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
                    mMap.addMarker(dustbin1marker8);

///////////
                    LatLng dustbin9 = new LatLng(latitude+0.0009,longitude+0.0018);
                    MarkerOptions dustbin1marker9 = new MarkerOptions();
                    dustbin1marker9.position(dustbin9);
                    dustbin1marker9.title("Wifi9");
                    dustbin1marker9.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
                    mMap.addMarker(dustbin1marker9);



                }
                else if(!location.equals(""))
                {
                    String work="not done";
                    Geocoder geocoder = new Geocoder(this);
                    String supported_types[]= getResources().getStringArray(R.array.supported_types);
                    for(int i=0;i<supported_types.length;i++){
                        if(location.equals(supported_types[i])){
                            mMap.clear();
                            String url = getUrl(latitude, longitude, supported_types[i]);
                            dataTransfer[0] = mMap;
                            dataTransfer[1] = url;
                            getNearbyPlacesData.execute(dataTransfer);
                            Toast.makeText(MapsActivity.this, "Showing Nearby "+supported_types[i], Toast.LENGTH_SHORT).show();
                            work="done";
                            break;
                        }
                    }
                    if(work.equals("not done"))
                    {
                        mMap.clear();
                        try {
                            addressList = geocoder.getFromLocationName(location, 25);

                            if(addressList != null)
                            {
                                for(int i = 0;i<addressList.size();i++)
                                {
                                    LatLng latLng = new LatLng(addressList.get(i).getLatitude() , addressList.get(i).getLongitude());
                                    MarkerOptions markerOptions = new MarkerOptions();
                                    markerOptions.position(latLng);
                                    markerOptions.title(location);
                                    mMap.addMarker(markerOptions);
                                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                                    mMap.animateCamera(CameraUpdateFactory.zoomTo(15));

                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                break;
        }
    }

    private String getUrl(double latitude , double longitude , String nearbyPlace)
    {

        StringBuilder googlePlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlaceUrl.append("location="+latitude+","+longitude);
        googlePlaceUrl.append("&radius="+PROXIMITY_RADIUS);
        googlePlaceUrl.append("&type="+nearbyPlace);
        googlePlaceUrl.append("&sensor=true");
        googlePlaceUrl.append("&key="+"AIzaSyDdb9ZxodTnFy7MdLa8BUrXZVD8msLgoME");

        Log.d("MapsActivity", "url = "+googlePlaceUrl.toString());

        return googlePlaceUrl.toString();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        locationRequest = new LocationRequest();
        locationRequest.setInterval(100);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);


        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED)
        {
            LocationServices.FusedLocationApi.requestLocationUpdates(client, locationRequest, this);
        }
    }


    public boolean checkLocationPermission()
    {
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)  != PackageManager.PERMISSION_GRANTED )
        {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION))
            {
                ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION },REQUEST_LOCATION_CODE);
            }
            else
            {
                ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION },REQUEST_LOCATION_CODE);
            }
            return false;

        }
        else
            return true;
    }


    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }
}
