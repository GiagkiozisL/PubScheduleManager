package com.stonesoup;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.stonesoup.AddUserDialog.EditNameDialogListener;

public class MainActivity extends FragmentActivity implements EditNameDialogListener{

	private String userName,passWord;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//track statistics around application opens
		ParseAnalytics.trackAppOpened(getIntent());
		setContentView(R.layout.activity_main);
		
	}
	
	private void showEditDialog() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(new AddUserDialog(), "dlg_edit_name");
        ft.addToBackStack(null);
        ft.commit();
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
			showEditDialog();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onFinishEditDialog(String inputText,String inputPass) {
		FragmentManager fm = getFragmentManager();
        fm.popBackStack();
        Toast.makeText(this, "New account added succesfully", Toast.LENGTH_SHORT).show();
		userName = inputText.toString();
		passWord = inputPass.toString();
		
		ParseUser user = new ParseUser();
		user.setUsername(userName);
		user.setPassword(passWord);
		user.signUpInBackground(new SignUpCallback() {
			  public void done(ParseException e) {
			    if (e == null) {
			      // Hooray! Let them use the app now.
			    } else {
			      // Sign up didn't succeed. Look at the ParseException
			      // to figure out what went wrong
			    }
			  }
			});
	}
}
