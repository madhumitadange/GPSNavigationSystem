package com.sql;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class SPEAKActivity extends Activity {
	  private static final String TAG = null;

	public void onCreate(Bundle savedInstanceState) {
		     super.onCreate(savedInstanceState);
		        //setContentView(R.layout.main);
		    // if(intent.getData() != null)
				//	Log.v(TAG, intent.getData().toString());
		     SQLiteExample info = new SQLiteExample();
		     
		    
		   /*  Cornte entry = new Cornte(this);
				entry.open();
			   String  s1=entry.check(currentlocation1);
			    entry.close();*/
		     //Bundle extras = intent.getExtras();
		    String s1 =info.s1;// extras.getString("mesaages");
		     SQLiteExample.speakSMS(s1);
		     /*Intent i =new Intent("com.sql.SMSActivity");
		     i.putExtra("hi", s1);
				startActivity(i); */
		     finish();
		     

		        
		       // myTts.speak("write menu swipe right for next alphabet swipe left for previous character double tap to confirm character swipe up to read entered word" , 0, null);
		        //speakSMS("write menu swipe right for next alphabet ");//swipe left for previous character double tap to confirm character swipe up to read entered word " );
		    }
		   
	

}
