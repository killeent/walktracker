package com.example.walktracker;

import java.util.Timer;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    private static boolean tracking = true;
    private static double distance = 0.0;
    
    public void start(View view) {
    	TextView tv = (TextView) view;
    	if (tv.getText().equals(getString(R.string.button_start))) {
    		tv.setText(getString(R.string.button_finished));
    		startTracking();
    	} else {
    		tv.setText(getString(R.string.button_start));
    		stopTracking();
    	}
    }
    
    private void startTracking() {
    	Timer tracker = new Timer();
    }
    
    private void getLocationInfo() {
    	
    }
    
    private void stopTracking() {
    	return;
    }
    
}
