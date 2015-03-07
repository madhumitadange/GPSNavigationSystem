package com.sql;

import com.sql.SimpleGestureFilter.SimpleGestureListener;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.widget.Toast;

public class SMSActivity  extends Activity  implements SimpleGestureListener {
	//  private static final String TAG = null;
	 private SimpleGestureFilter detectorS; 
	@Override
    public void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
         
        detectorS = new SimpleGestureFilter(this,this);

        
       // myTts.speak("write menu swipe right for next alphabet swipe left for previous character double tap to confirm character swipe up to read entered word" , 0, null);
        //speakSMS("write menu swipe right for next alphabet ");//swipe left for previous character double tap to confirm character swipe up to read entered word " );
    }
		     
		     
		     SQLiteExample info = new SQLiteExample();
		     public void onDoubleTap() {
		  	   
		  	   /* j++;
		  	    if(j!=-1){
		  	     name[j] =  data [i]; }*/
		  	   
		  	   // SQLiteExample.speakSMS(  " Entered ");
		  	    
		  	    
		    
		   /*  Cornte entry = new Cornte(this);
				entry.open();
			   String  s1=entry.check(currentlocation1);
			    entry.close();*/
		    	
		     Bundle extras = getIntent().getExtras();
		    		  String s = extras.getString("hi")  ;
		    if(s.equals("Not at defined Location")){
		    	SQLiteExample.speakSMS("no SMS  sent not defined location");
		    	//finish(); 
		    	}
		    // extras.getString("mesaages");
		   //  SQLiteExample.speakSMS(s1 + " no SMS sent");
		    else {
		    String sms1 =  "Message: " + s ;
		    String number = "9960362688";
		    SmsManager sm = SmsManager.getDefault(); 
		    sm.sendTextMessage(number, null, "sms I am at " + sms1, null, null);
		    SQLiteExample.speakSMS(sms1+" SMS sent");
		    } 
		    // finish();
		     

		        
		       // myTts.speak("write menu swipe right for next alphabet swipe left for previous character double tap to confirm character swipe up to read entered word" , 0, null);
		        //speakSMS("write menu swipe right for next alphabet ");//swipe left for previous character double tap to confirm character swipe up to read entered word " );
		    
		   
}
			public void onSwipe(int direction) {
				// TODO Auto-generated method stub
			// case SimpleGestureFilter.SWIPE_LEFT : 
			 
		                                           //      break;
				
			}

}
