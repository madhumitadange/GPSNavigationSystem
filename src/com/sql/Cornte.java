package com.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import android.widget.Toast;

public class Cornte{
	String locationName="Not at defined Location";
	double minvalue=0.02;
	public  final String KEY_ROWID="_id"; // all static finals
	public  final String KEY_LOCATION="location_name";
	public  final String KEY_LONGI= "longi" ;
	public  final String KEY_LATI="lati";
	private  final String DATABASE_NAME="COR";
	private  final String DATABASE_TABLE="Table1";
	private  final int DATABASE_VERSION=1;
	private dbhelper ourhelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	public  class dbhelper extends SQLiteOpenHelper//private static before
	{

		public dbhelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("create table " + DATABASE_TABLE + " (" +
			        KEY_ROWID + " INTEGER PRIMARY KEY," +
					KEY_LOCATION + " TEXT NOT NULL," +
					KEY_LONGI + " TEXT NOT NULL," + KEY_LATI + " TEXT NOT NULL);"
			);

			
			
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			/*db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE );
			onCreate(db);*/
			if (newVersion > oldVersion) {
	            Log.w("MyAppTag","Updating database from version " + oldVersion + " to "
	                    + newVersion + " .Existing data will be lost.");
	            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
	            //db.execSQL("DROP TABLE IF EXISTS " + MY_TABLE2);
	            onCreate(db);
	        }
		}
	}
	public Cornte(Context c){
		ourContext=c;
	}
	public Cornte open() throws SQLException
	{
		ourhelper = new dbhelper(ourContext); 
		ourDatabase = ourhelper.getWritableDatabase();
		return this;
	}
	public void close()
	{
		ourhelper.close();
	}
	public long createEntry(String location, double longi,double lati) {
		
		
		// TODO Auto-generated method stub
		
		ContentValues cv =new ContentValues();
		cv.put(KEY_LOCATION, location);
		cv.put(KEY_LONGI, longi);
		//cv.put(KEY_LONGI,  longi);
		cv.put(KEY_LATI , lati);
		return ourDatabase.insert(DATABASE_TABLE,null,cv);
		
	}	
	
	public String getData() {
		// TODO Auto-generated method stub
		String[] columns =new String[]{ KEY_ROWID,KEY_LOCATION, KEY_LONGI,KEY_LATI};
		Cursor c=ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		c.moveToFirst();
		String result="";
		int irow=c.getColumnIndex(KEY_ROWID);
		int ilocation=c.getColumnIndex(KEY_LOCATION);
		int ilongi=c.getColumnIndex(KEY_LONGI);
		int ilati=c.getColumnIndex(KEY_LATI);
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			result=result+ c.getString(irow) +  "  " +c.getString(ilocation) + c.getDouble(ilongi) + c.getDouble(ilati) +"\n";
		
		}
		c.close();
		return result;
	}
	public String check(Location pointLocation) {
		
		String[] columns2 =new String[]{ KEY_ROWID,KEY_LOCATION, KEY_LONGI,KEY_LATI};
		Cursor c2=ourDatabase.query(DATABASE_TABLE, columns2, null, null, null, null, null);
		c2.moveToFirst();
		int ilocation2=c2.getColumnIndex(KEY_LOCATION);
		int ilongi2=c2.getColumnIndex(KEY_LONGI);
		int ilati2=c2.getColumnIndex(KEY_LATI);
		Location loc = new Location("POINT_LOCATION");
		loc.setLatitude(10);
		loc.setLongitude(10);
			    
		for (c2.moveToFirst(); !c2.isAfterLast(); c2.moveToNext()){
			double l2=c2.getDouble(ilati2);
			double l1=c2.getDouble(ilongi2);
			/*double longiDouble = Double.parseDouble(l1);
			double latiDouble = Double.parseDouble(l2);
			//double longi = Double.parseDouble(longi2);*/
			
			loc.setLatitude(l2);
			loc.setLongitude(l1);
			float distance = loc.distanceTo(pointLocation);
		//if((Math.abs(longi2-l1)<minvalue) && (Math.abs(lati2-l2)<minvalue)){
			if(distance<200)	{
			locationName=c2.getString(ilocation2) + " at distance " +(int)distance +" meters";
			 //SQLiteExample.speakSMS("you are at "+locationName);

			break;}
			else
			locationName="Not at defined Location";
			
		}
		
		c2.close();
		return locationName;
	}
	/*
	public String addAlert(double latitude, double longitude) {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID, KEY_LATI, KEY_LONGI, KEY_LOCATION};
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
        String result = "";
        
        int iRow = c.getColumnIndex(KEY_ROWID);
        int iLat = c.getColumnIndex(KEY_LATI);
        int iLon = c.getColumnIndex(KEY_LONGI);
        int iplace = c.getColumnIndex(KEY_LOCATION);
        
        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
    		if((Math.abs(longitude-iLon)<minvalue) && (Math.abs(latitude-iLat)<minvalue)){

        	//if(((Math.sqrt(Math.pow(latitude-c.getFloat(iLat),2))+(Math.pow(longitude- c.getFloat(iLon))))<minvalue)){
        		result = c.getString(iplace);
        	}
        	else
        		result = "Not added as proximity alert";
        }
        //if(c.isLast()){
        	//result = c.getString(iLat) + " " + c.getString(iLon);
        //}
        c.close();
		return result;
	}
	*/}
	
		


