package com.example.hellocalendar;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CalendarsRetriever extends Activity {
	
	private Cursor mCursor =  null ;
	private final static String[] COLUMNS = new String[] 
			{CalendarContract.Calendars.CALENDAR_DISPLAY_NAME} ;
	private final static String HELLOCAL = "hello_calendar" ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendars_retriever);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.calendars_retriever, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void onButtonClick(View view){
    	mCursor = getContentResolver().query(CalendarContract.Calendars.CONTENT_URI, 
    			COLUMNS, null, null, null) ;
    	mCursor.moveToFirst() ;
    	TextView tv = (TextView)findViewById(R.id.message) ;
    	tv.setText (Integer.toString(mCursor.getCount())) ;
    	String output = "" ;
    		while(mCursor.moveToNext()){
    			output = output.concat(mCursor.getString(0) + "; \n") ;
    		}
    		tv.setText(output) ;
    		
    	
    }
}
