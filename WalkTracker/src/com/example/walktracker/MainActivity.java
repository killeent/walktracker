package com.example.walktracker;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	  private LocationManager locationManager;
	  private String provider;
	  private TextView latitudeField;
	  private TextView longitudeField;
	  private LocationListener locationListener;
	  //private int counter = 0;
	  
	  private double totalDistance;
	  
	  private double lastLat;
	  private double lastLong;
	  
	  private double currentLat;
	  private double currentLong;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        totalDistance = 0;
        latitudeField = (TextView) findViewById(R.id.latitude);
        longitudeField = (TextView) findViewById(R.id.longitude);
        
        locationListener = new MyLocationListener();

        // Get the location manager
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        
        // Define the criteria how to select the locatioin provider -> use default
        Criteria criteria = new Criteria();
        provider = LocationManager.GPS_PROVIDER;	// TODO change hardcode
        		//locationManager.getBestProvider(criteria, false);
        locationManager.requestLocationUpdates(provider, 400, 0, locationListener);
        
        Location initialLocation = locationManager.getLastKnownLocation(provider);


        // Initialize the location fields
        if (initialLocation != null) {
    	    lastLat = initialLocation.getLatitude();	// initialize lastLat
    	    lastLong = initialLocation.getLongitude();	// initialize lastLong
    	    latitudeField.setText(String.valueOf(lastLat));
    	    longitudeField.setText(String.valueOf(lastLong));
        } else {
          latitudeField.setText("Location not available");
          longitudeField.setText("Location not available");
        }
    }
    
    /*
     * 
     */
    public void start(View view) {
    	TextView tv = (TextView) view;
    	if (tv.getText().equals(getString(R.string.button_start))) {
    		tv.setText(getString(R.string.button_finished));
    	} else {
    		tv.setText(getString(R.string.button_start));
    	}
    }
    
    /*
     * 
     */
    public class MyLocationListener implements LocationListener {

  	  @Override
  	  public void onLocationChanged(Location location) {
  		//counter++;  
  		//textD.setText(String.valueOf(counter));
  		  
  		TextView textDistanceView = (TextView) findViewById(R.id.textDistance);
  		  
  		float[] distanceMeters = new float[4];
  		  
  	    currentLat = location.getLatitude();
  	    currentLong = location.getLongitude();
  	    
  	    Location.distanceBetween(lastLat, lastLong, currentLat, currentLong, distanceMeters);
  	    totalDistance += distanceMeters[0];
  	    
  	    lastLat = currentLat;
  	    lastLong = currentLong;
  	    
  	    textDistanceView.setText("Current Location: " + String.valueOf(totalDistance));
  	    
  	    latitudeField.setText(String.valueOf(currentLat));
  	    longitudeField.setText(String.valueOf(currentLong));
  	  }

  	  @Override
  	  public void onStatusChanged(String provider, int status, Bundle extras) {
  	    // TODO Auto-generated method stub

  	  }

  	  @Override
  	  public void onProviderEnabled(String provider) {
  	    Toast.makeText((Context) locationListener, "Enabled new provider " + provider,
  	        Toast.LENGTH_SHORT).show();

  	  }

  	  @Override
  	  public void onProviderDisabled(String provider) {
  	    Toast.makeText((Context) locationListener, "Disabled provider " + provider,
  	        Toast.LENGTH_SHORT).show();
  	  }

  }
   
    // Request updates at startup 
    @Override
    protected void onResume() {
      super.onResume();
      locationManager.requestLocationUpdates(provider, 400, 0, locationListener);
    }

    // Remove the locationlistener updates when Activity is paused 
    @Override
    protected void onPause() {
      super.onPause();
      locationManager.requestLocationUpdates(provider, 400, 0, locationListener);
    }
    

    
}
