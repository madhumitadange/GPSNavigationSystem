package com.sql;


import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

public class MylocListener implements LocationListener {

	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
	//	Location pointLocation = retrievelocationFromPreferences();
   //     float distance = location.distanceTo(pointLocation);
  //      Toast.makeText(MylocListener.this,
          //      "Distance from Point:"+distance, Toast.LENGTH_LONG).show();

	}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

}
