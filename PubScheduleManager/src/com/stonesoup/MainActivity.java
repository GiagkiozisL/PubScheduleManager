package com.stonesoup;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.ParseAnalytics;

public class MainActivity extends FragmentActivity {

	private Menu optionsMenu;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//track statistics around application opens
		ParseAnalytics.trackAppOpened(getIntent());
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_activity_actions, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.addUser:
			showAddUserDialog();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void showAddUserDialog(){
		FragmentManager st =getFragmentManager();
		AddUserDialog addUserDialog = new  AddUserDialog();
		addUserDialog.show(st, "setting_dialog");
	}

}
