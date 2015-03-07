package com.sql;

//import com.sql.SQLiteExample;

import com.sql.SimpleGestureFilter.SimpleGestureListener; 

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.Toast;

public class myswipewriteActivity extends Activity  implements SimpleGestureListener{
	 private static final String GESTURE = null;
	private static final String PUBLIC_STATIC_STRING_IDENTIFIER = null;
	 private SimpleGestureFilter detector; 
	// private SQLiteExample sqlgps;
	// private static TextToSpeech myTts;
	 public String data[] = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T", "U","V", "W","X","Y","Z"," "} ;
	 //public char data[] = {'a', 'b', 'c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
     public static int i=-1;
   //  public static int j=-1;
     public char name[];
     public String s1 = "";
    // private         View    mTheView; 
	    /** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	     super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);
	         
	        detector = new SimpleGestureFilter(this,this);

	        
	       // myTts.speak("write menu swipe right for next alphabet swipe left for previous character double tap to confirm character swipe up to read entered word" , 0, null);
	        //speakSMS("write menu swipe right for next alphabet ");//swipe left for previous character double tap to confirm character swipe up to read entered word " );
	    }
	   
	  @Override 
	 public boolean dispatchTouchEvent(MotionEvent me){ 
	   this.detector.onTouchEvent(me);
	  return super.dispatchTouchEvent(me); 
	 }

	  public void onSwipe(int direction) {
	  String str = "";
	  
	  switch (direction) {
	  
	  case SimpleGestureFilter.SWIPE_RIGHT : if(i==26){ i=-1;} 
		  i++;
		  if(i==27){ i=0;}
		  str =  data [i];
	 
	  
	                                           break;
	  case SimpleGestureFilter.SWIPE_LEFT : if(i==-1){ i=26;}
		  i--;
		  if(i==-1){ i=26;}
	  str =   data [i];
		 
	                                                 break;
	  case SimpleGestureFilter.SWIPE_DOWN : if(s1.length()!= 0){
		  int len = s1.length();
		/*  char c = s1.charAt(len);
		  String s = Character.toString(c);*/
		  str =  s1.charAt(len-1) +" is deleted" ;
		  s1  = s1.substring(0,len-1 );
		  
		  
		  }
	  else
		  str = "complete word deleted "  ;
	  //speakSMS("character " + data[i] +" is deleted from word" );
	                                                 break;
	  case SimpleGestureFilter.SWIPE_UP :   
		  str = "Entered word  "+ s1;
		/*  Intent intent = this.getIntent();
	        intent.putExtra(PUBLIC_STATIC_STRING_IDENTIFIER, s1);
	        this.setResult(RESULT_OK, intent);
	        finish();*/
	                                                 break;
	                                           
	  } 
	  String str1= str;
	   if (str.equalsIgnoreCase(" "))
	   {
		   str1 = "SPACE";
	   }
	   SQLiteExample.speakSMS(str1);
	   Toast.makeText(this, str1, Toast.LENGTH_SHORT).show();

	 }

	 public void onDoubleTap() {
	   
	   /* j++;
	    if(j!=-1){
	     name[j] =  data [i]; }*/
	    if(i!=-1){
	    	 Toast.makeText(this,  data [i], Toast.LENGTH_SHORT).show(); 
	    s1 = s1 + data [i];
	    SQLiteExample.speakSMS( data[i] + " Entered ");}
	    
	    }
	 
	 
	

@Override
public void onStart() {
    super.onStart();
    
   
}

@Override
public void onPause() {
    super.onPause();
}

	public boolean onTouchEvent(MotionEvent e) {

	        int pointerCount = e.getPointerCount();
	        if(pointerCount == 2) {
	     /*   if(pointerCount != 2) {
	            Log.d(GESTURE, "not pinching - exactly 2 fingers are needed but have " + pointerCount);
	            clearPinch();
	            return false;
	        }
*/
	        int firstIndex = e.getX(0) < e.getX(1) ? 0: 1;
	        int secondIndex = e.getX(0) < e.getX(1) ? 1: 0;

	        Finger currentLeftFinger = new Finger(e.getX(firstIndex), e.getY(firstIndex));
	        Finger currentRightFinger = new Finger(e.getX(secondIndex), e.getY(secondIndex));

	        float yDifference = Math.abs(currentLeftFinger.getY() - currentRightFinger.getY());
	        if(yDifference > 80) {
	            Log.d(GESTURE, "not pinching - fingers too vertically-oriented");
	            clearPinch();
	            return false;
	        }

	        if(initialLeftFinger == null) {
	            initialLeftFinger = currentLeftFinger;
	            initialRightFinger = currentRightFinger;
	            Log.d(GESTURE, "not pinching, but might be starting a pinch...");
	            return false;
	        }

	        float leftFingerDistance = initialLeftFinger.getX() - currentLeftFinger.getX();
	        float rightFingerDistance = currentRightFinger.getX() - initialRightFinger.getX();

	        float xDistanceBetweenFingers = Math.abs(currentLeftFinger.getX() - currentRightFinger.getX());
	        if(xDistanceBetweenFingers < minimumDistanceBetweenFingers) {
	            Log.d(GESTURE, "pinching, but fingers are not far enough apart...");
	            return true;
	        }

	        if(leftFingerDistance < minimumDistanceForEachFinger) {
	            Log.d(GESTURE, "pinching, but left finger has not moved enough...");
	            return true;
	        }
	        if(rightFingerDistance < minimumDistanceForEachFinger) {
	            Log.d(GESTURE, "pinching, but right finger has not moved enough...");
	            return true;
	        }

	        pinchCompleted();
	        return true;
	    }
	        return false;
	}

	    private void pinchCompleted() {
	        Log.d(GESTURE, "pinch completed location saved");
	        SQLiteExample.speakSMS("pinch completed location saved");
	        
	        if(pinchListener != null) pinchListener.onPinch();
	        clearPinch();
	        /*Intent resultIntent = null;// = new Intent(null);
	        resultIntent.putExtra(PUBLIC_STATIC_STRING_IDENTIFIER, s1);
	        setResult(Activity.RESULT_OK, resultIntent);*/
	        Intent intent = this.getIntent();
	        intent.putExtra(PUBLIC_STATIC_STRING_IDENTIFIER, s1);
	        this.setResult(RESULT_OK, intent);
	        finish();
	      
	        
	    }

	    public static interface OnPinchListener {
	        void onPinch();
	    }

	    private void clearPinch() {
	        initialLeftFinger = null;
	        initialRightFinger = null;
	    }

	    public void setPinchListener(OnPinchListener pinchListener) {
	        this.pinchListener = pinchListener;
	    }

	    private static class Finger {

	        private Finger(float x, float y) {
	            this.x = x;
	            this.y = y;
	        }

	        public float getX() {
	            return x;
	        }

	        public float getY() {
	            return y;
	        }

	        private float x;
	        private float y;
	    }

	    private Finger initialLeftFinger;
	    private Finger initialRightFinger;
	    private OnPinchListener pinchListener;
	    private static final float minimumDistanceForEachFinger = 30;
	    private static final float minimumDistanceBetweenFingers = 50;


		 
		}