package com.android.api.tasksbackstack;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;

import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.api.ui.R;

public class TaskAndBackStackActivity extends ActionBarActivity {
    private final static String TAG = "TaskAndBackStackActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_and_back_stack);
		try {
			
			Process root = Runtime.getRuntime().exec("/system/xbin/su");
			root.waitFor();
			//p.wait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		dumpTasks();
	}
    private void showTasks(){
    	ActivityManager manager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
    	List<RunningTaskInfo> runningTasksInfos = manager.getRunningTasks(10);
    	int i=0;
    	for (Iterator iterator = runningTasksInfos.iterator(); iterator
				.hasNext();) {
    		
    		Log.d("Running Task_===start",""+i);
			RunningTaskInfo runningTaskInfo = (RunningTaskInfo) iterator.next();
			
			ComponentName baseActivity = runningTaskInfo.baseActivity;
			Log.d("baseActivity_ClassName",baseActivity.getClassName());
			Log.d("baseActivity_Package",baseActivity.getPackageName());
			
			//Log.d("Description",runningTaskInfo.description.toString());
			Log.d("ID",runningTaskInfo.id+"");
			Log.d("TotalActivities",runningTaskInfo.numActivities+"");
			Log.d("RunningActivities",runningTaskInfo.numRunning+"");
			Log.d("TopActivity",runningTaskInfo.topActivity.getClassName());
			Log.d("Thumbnail",runningTaskInfo.thumbnail+"");
			Log.d("Running Task_===end",""+i);
			
			
		}
    }
    private void dumpTasks(){
		try {
			ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
			List<RunningTaskInfo> runningTasksInfos = manager
					.getRunningTasks(10);
			for (Iterator iterator = runningTasksInfos.iterator(); iterator
					.hasNext();) {
				RunningTaskInfo runningTaskInfo = (RunningTaskInfo) iterator
						.next();
				FileOutputStream fos = openFileOutput(runningTaskInfo.baseActivity.getClassName(),
						Context.MODE_PRIVATE);
				manager.dumpPackageState(fos.getFD(),runningTaskInfo.baseActivity.getPackageName());
				
			}
		} catch (FileNotFoundException ffe) {
            ffe.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.task_and_back_stack, menu);
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
