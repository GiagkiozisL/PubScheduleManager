package com.stonesoup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.PushService;


public class StartupActivity extends Activity{

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		Parse.initialize(this, "ph2pneKQy22P3wQMnVeufRZfPzf69ZajhyAUH6e1", "5mIvqvTCez5LrynMYzg25A1XXwOXT9YDJUrZ216A");
	   setContentView(R.layout.registration);
	// Save the current Installation to Parse.
	   ParseInstallation.getCurrentInstallation().saveInBackground();
	   PushService.setDefaultPushCallback(this, MainActivity.class);
	
	Intent registrationActivity = new Intent(getApplicationContext(),RegistrationActivity.class);
	startActivity(registrationActivity);
	finish();
	}
}
