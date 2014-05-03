package com.stonesoup.Manager;

import java.util.List;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.stonesoup.Manager.AddMeetingEventDialog.EditMeetingDialogListener;
import com.stonesoup.Manager.AddUserDialog.EditNameDialogListener;

public class MainActivity extends FragmentActivity implements EditNameDialogListener,EditMeetingDialogListener{

	private String userName,passWord,dateStr,timeStr;
	private Button scheduleBtn,meetingBtn,userlistBtn,eventlistBtn,calendarBtn;
	Typeface typeface;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//track statistics around application opens
		ParseAnalytics.trackAppOpened(getIntent());
		setContentView(R.layout.activity_main);
		typeface = Typeface.createFromAsset(getAssets(), "Exo-SemiBold.otf");
		scheduleBtn = (Button) findViewById(R.id.scheduleBtn);
		scheduleBtn.setTypeface(typeface);
		scheduleBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent schedule = new Intent(MainActivity.this,ScheduleActivity.class);
				startActivity(schedule);
			}
		});
		
		userlistBtn = (Button) findViewById(R.id.userlistBtn);
		userlistBtn.setTypeface(typeface);
		userlistBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent userlist = new Intent(MainActivity.this,UserList.class);
				startActivity(userlist);
				
			}
		});
		
		eventlistBtn = (Button) findViewById(R.id.evenlistBtn);
		eventlistBtn.setTypeface(typeface);
		eventlistBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent eventlist = new Intent(MainActivity.this,EventsList.class);
				startActivity(eventlist);
				
			}
		});
		
		meetingBtn = (Button) findViewById(R.id.meetingBtn);
		meetingBtn.setTypeface(typeface);
		meetingBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			showMeetingDialog();
			}

			private void showMeetingDialog() {
				FragmentManager fm = getFragmentManager();
		        FragmentTransaction ft = fm.beginTransaction();
		        ft.add(new AddMeetingEventDialog(), "dlg_edit_meeting");
		        ft.addToBackStack(null);
		        ft.commit();
				
			}
		});
		
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

	@Override
	public void onFinishEditMeetingDialog(String date, String time) {
		FragmentManager fm = getFragmentManager();
        fm.popBackStack();
        Toast.makeText(this, "New meeting added succesfully", Toast.LENGTH_SHORT).show();
        dateStr = date.toString();
        timeStr = time.toString();
	
		ParseObject meeting = new ParseObject("Meetings");
		meeting.put("date", dateStr);
		meeting.put("time", timeStr);
		meeting.saveInBackground();
		
	}
}
