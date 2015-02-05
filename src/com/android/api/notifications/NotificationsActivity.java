package com.android.api.notifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.api.ui.R;

public class NotificationsActivity extends ActionBarActivity {
    public static int id=1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notifications);
	}
    public void notifyHandler(View view){
    	NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
    	notificationBuilder.setSmallIcon(R.drawable.ic_launcher);
    	notificationBuilder.setContentText("This is content text");
    	notificationBuilder.setContentTitle("Content Title");
    	
    	Intent intent = new Intent(this,NotifyHandlerActivity.class);
    	PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent,PendingIntent.FLAG_ONE_SHOT);
    	
    	notificationBuilder.setContentIntent(pendingIntent);
    	
    	NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    	manager.notify(id, notificationBuilder.build());
    	id++;
    }
    public void OpenSecond(View view){
    	Intent intent = new Intent(this,NotifyHandlerActivity.class);
    	startActivity(intent);
    
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.notifications, menu);
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
