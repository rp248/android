package com.android.api.v4.support;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.api.ui.MenuActivity;
import com.android.api.ui.R;

public class V4SupportActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_v4_support);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
	}
	
	public void startActivitiesHnadler(View view){
		Intent v1 = new Intent(this,V4SupportActivity_1.class);
		Intent v2 = new Intent(this,V4SupportActivity_2.class);
		Intent v3 = new Intent(this,V4SupportActivity_3.class);
		if(Build.VERSION.SDK_INT>Build.VERSION_CODES.HONEYCOMB)
		{
	        android.app.TaskStackBuilder tsb = android.app.TaskStackBuilder.create(this);
	        tsb.addParentStack(V4SupportActivity.class);
			tsb.addNextIntentWithParentStack(v1);
			//tsb.addNextIntentWithParentStack(v2);
			//tsb.addNextIntentWithParentStack(v3);
			
			tsb.startActivities();
		    //startActivities(new Intent[]{v1,v2,v3});
		}
		else
		{
			TaskStackBuilder tsb = TaskStackBuilder.create(this);
			tsb.addParentStack(V4SupportActivity.class);
			tsb.addNextIntentWithParentStack(v1);
			tsb.addNextIntentWithParentStack(v2);
			tsb.addNextIntentWithParentStack(v3);
			tsb.startActivities();
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.v4_support, menu);
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
}
