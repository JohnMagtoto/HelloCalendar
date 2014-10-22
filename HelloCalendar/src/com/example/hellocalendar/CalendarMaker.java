package com.example.hellocalendar;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract.Calendars;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class CalendarMaker extends Activity {
	private static final String TAG = "CalendarMaker" ;
	private Cursor mCursor =  null ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendar_maker);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.calendar_maker, menu);
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
	
	public void onTapMe(View view) {
		Uri uri = _asSyncAdapter(Calendars.CONTENT_URI, "crashdummy.enceladus@gmail.com", "com.google") ;
		ContentValues values = new ContentValues() ;
		values.put(Calendars.CALENDAR_DISPLAY_NAME, "Test") ;
		getContentResolver().insert(uri, values) ;
		getContentResolver().requestSync(account, authority, extras) ;
		Log.d(TAG, "Inserted calendar to CrashDummy") ;
	}
	
	/*
	 * Helper stub for use to append to URI the required parameters for use in SyncAdapter class
	 */
	protected static Uri _asSyncAdapter(Uri uri, String account, String accountType) {
	    return uri.buildUpon()
	        .appendQueryParameter(android.provider.CalendarContract.CALLER_IS_SYNCADAPTER,"true")
	        .appendQueryParameter(Calendars.ACCOUNT_NAME, account)
	        .appendQueryParameter(Calendars.ACCOUNT_TYPE, accountType).build();
	 }
}
