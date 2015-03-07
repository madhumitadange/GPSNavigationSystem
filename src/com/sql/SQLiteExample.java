package com.sql;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;



import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;
import android.util.Log;
import android.view.View.OnClickListener;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;


public class SQLiteExample extends Activity {
    private static final int STATIC_INTEGER_VALUE = 0;
	private static final String PUBLIC_STATIC_STRING_IDENTIFIER = null;
	private static final String TAG = null;
	/** Called when the activity is first created. */
	public TextView name;
    public Button sqlUpdate,sqlView,check;
    EditText sqllocation,sqllongi,sqllati;
    private static TextToSpeech myTts;
    double lati1,longi1;
    private final long MINIMUM_DISTANCECHANGE_FOR_UPDATE = 200; // in Meters all atatic finals
	private  final long MINIMUM_TIME_BETWEEN_UPDATE = 1000; // in Milliseconds
	private  final long POINT_RADIUS = 1000; // in Meters
    private  final long PROX_ALERT_EXPIRATION = -1;
	 
	private  final String POINT_LATITUDE_KEY = "POINT_LATITUDE_KEY";
    private  final String POINT_LONGITUDE_KEY = "POINT_LONGITUDE_KEY";	     
    private  final String PROX_ALERT_INTENT =
	         "com.sql"; 
    public static String s1="hi";
    private  final NumberFormat nf = new DecimalFormat("##.########");
   public String location1 = "Not at defined Location";
    private LocationManager locationManager;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.sqliteexample);
       // sqlUpdate = (Button)findViewById(R.id.bsqlupdate);
        //sqlUpdate.setOnClickListener((android.view.View.OnClickListener) this);
       // sqlView = (Button)findViewById(R.id.bsqlopenview);
        //check = (Button)findViewById(R.id.check1);
        //check.setOnClickListener(this);
      // sqlView.setOnClickListener(this);
       //name = (TextView)findViewById(R.id.name);
        //	    savePointButton = (Button) findViewById(R.id.save_point_button);

        
        
       // sqllocation = (EditText)findViewById(R.id.location1);
        
       //sqlView.setOnClickListener((android.view.View.OnClickListener) this);

        
        myTts = new TextToSpeech(getApplicationContext(), ttsInitListener);
        
        //myTts.speak("hi", 0, null);
       // myTts = new TextToSpeech(this, ttsInitListener);


        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        LocationListener mlocListener = new MylocListener();
        locationManager.requestLocationUpdates( LocationManager.GPS_PROVIDER,MINIMUM_TIME_BETWEEN_UPDATE,MINIMUM_DISTANCECHANGE_FOR_UPDATE, mlocListener);
       // locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
       // LocationListener myloclist = new MylocListener();
       /* locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                MINIMUM_TIME_BETWEEN_UPDATE pre 0,
                    MINIMUM_DISTANCECHANGE_FOR_UPDATE 0before,myloclist
                );*/
        
	//}
	

	//public void onClick(View arg0) {
		// TODO Auto-generated method stub
		//switch (arg0.getId())
		//{
		//case R.id.check1:
		/*	Cornte ck = new Cornte(this);
			//String longi2 = Double.toString(longi1);
			//String lati2 = Double.toString(lati1);
			Location currentlocation =
				    locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			ck.open();
			String s=ck.check(currentlocation);
			ck.close();
			name.setText("You are at " + s);
			break;*/
			
		//case R.id.bsqlupdate:
			boolean wrk=true;
			try{
			//String location1 = sqllocation.getText().toString();
			//String longi = Double.toString(longi1);
			//String lati = Double.toString(lati1);
		   // saveProximityAlertPoint();
			Location currentlocation1 =
		    locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
	        
			Cornte entry = new Cornte(this);
			entry.open();
		     s1=entry.check(currentlocation1);
		    entry.close();
			if(s1.equals("Not at defined Location")){
			// entry.createEntry(location , longi, lati);
				Intent intent = new Intent(SQLiteExample.this, myswipewriteActivity.class);

				startActivityForResult(intent, STATIC_INTEGER_VALUE);
			//saveProximityAlertPoint(location1,longi1, lati1);
			}
		else{
			Intent i =new Intent("com.sql.SPEAKActivity");
					startActivity(i);
					 
			}
				//myTts.speak("you are at "+s1,0,null);
				
				//entry.check(longi, lati);
				//entry.close();
				//name.setText("You are at defined position at :" + s1);
			
			}catch (Exception e){
				wrk= false;
				String error=e.toString();
				Dialog d=new Dialog(this);
				d.setTitle("NO!!!");
				TextView tv =new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show(); 
				
			}finally{
				if(wrk)
				{
					/*Dialog d=new Dialog(this);
					d.setTitle("YUP!!!");
					TextView tv =new TextView(this);
					tv.setText("SUCCESS");
					d.setContentView(tv);
					d.show();*/
					 try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					//finish();
				}
			}
		//	break;
		//case R.id.bsqlopenview:
		//	Intent i =new Intent("com.sql.SQLVIEW");
			//startActivity(i);
			
			//break;
			//finish();
		}
	
	

	



	/* TextToSpeech.OnInitListener ttsInitListener = new TextToSpeech.OnInitListener() {
	        public void onInit(int version) {
	          myTts.speak("tts initial", 0, null);
	        }
	    };*/
	
	   
	/*    private void saveProximityAlertPoint(  String location1, double longi12, double lati12) {
			// TODO Auto-generated method stub
	    	Location location =
		            locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
	        if (location==null) {
	            Toast.makeText(this, "No last known location. Aborting...",
	                Toast.LENGTH_LONG).show();
		            return;
	        }
	        
	        Cornte en = new Cornte(this);
	        en.open();
	        //String longi1 = Double.toString();
			//String lati1 = Double.toString();
	        en.createEntry(location1 ,location.getLongitude(),location.getLatitude());
	        en.close();
			//addProximityAlert(location.getLatitude(),location.getLongitude());
		}
*/
/*
	    private void addProximityAlert(double latitude, double longitude) {
			// TOSDO Auto-generated method stub
			Cornte proxm = new Cornte(SQLiteExample.this);
			proxm.open();
			String info = proxm.addAlert(latitude, longitude);
			proxm.close();
			
			Intent intent = new Intent(PROX_ALERT_INTENT);
			intent.putExtra("message", info) ;
					
	        PendingIntent proximityIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
			
	        locationManager.addProximityAlert(
	                latitude, // the latitude of the central point of the alert region
	                longitude, // the longitude of the central point of the alert region
	                POINT_RADIUS, // the radius of the central point of the alert region, in meters
	                PROX_ALERT_EXPIRATION, // time for this proximity alert, in milliseconds, or -1 to indicate no expiration
	                proximityIntent // will be used to generate an Intent to fire when entry to or exit from the alert region is detected
	           ); 
	        
	        IntentFilter filter = new IntentFilter(PROX_ALERT_INTENT); 
	    //   if( ! isRegistered(ProximityIntentReceiver))
	        registerReceiver(new ProximityIntentReceiver(), filter);
	        Toast.makeText(this, "Proximity alert added", Toast.LENGTH_LONG).show();
		}
	 
	    *//*   @Override
	    protected void onStop()
	    {
	        unregisterReceiver(ProximityIntentReceiver);
	        
	        super.onStop();
	    }*/
	    TextToSpeech.OnInitListener ttsInitListener = new TextToSpeech.OnInitListener() {
  	        public void onInit(int version) {
  	          //myTts.speak("tts initial", 0, null);
  	        	//myTts.setOnUtteranceCompletedListener(this);
  	        }
  	    };
	    public static void speakSMS(String text)
	    {
	    	//myTts.setOnUtteranceCompletedListener();
	         myTts.speak(text, 0, null);
	         
	         //myTts.synthesizeToFile(sms,null, "/sdcard/myappsounds/mysms.wav");
	    }
	    
	    @Override 
	    public void onActivityResult(int requestCode, int resultCode, Intent data) {     
	      super.onActivityResult(requestCode, resultCode, data); 
	     // String tabIndex = "no string";
	      switch(requestCode) { 
	        case (STATIC_INTEGER_VALUE) : { 
	          if (resultCode == Activity.RESULT_OK) { 
	          s1 = data.getStringExtra(PUBLIC_STATIC_STRING_IDENTIFIER);
	          // TODO Switch tabs using the index.
	          Location location =
			            locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		        if (location==null) {
		            Toast.makeText(this, "No last known location. Aborting...",
		                Toast.LENGTH_LONG).show();
			            return;
		        }
	          Cornte en = new Cornte(this);
		        en.open();
		        //String longi1 = Double.toString();
				//String lati1 = Double.toString();
		        en.createEntry(s1 ,location.getLongitude(),location.getLatitude());
		        en.close();
	        //  SQLiteExample.speakSMS(s1);
	         // Intent i =new Intent("com.sql.SQLVIEW");
			 // startActivity(i);
	          finish();
	          } 
	          break; 
	          
	        } 
	      } 
	    }







		
		
	    

}