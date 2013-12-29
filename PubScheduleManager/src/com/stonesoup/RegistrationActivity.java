package com.stonesoup;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegistrationActivity extends Activity{

	private EditText username,passEditText;
	TextView user,pass;
	private Button submit;
	Typeface typeface;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration);
		typeface = Typeface.createFromAsset(getAssets(), "Exo-SemiBold.otf");
		user = (TextView) findViewById(R.id.username);
		user.setTypeface(typeface);
		pass = (TextView) findViewById(R.id.password);
		pass.setTypeface(typeface);
		username = (EditText) findViewById(R.id.usernameEditTxt);
		passEditText = (EditText) findViewById(R.id.passwordEditTxt);
		submit = (Button) findViewById(R.id.settingsSubmitBtn);
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				//just for naw , move on and take me to main
				Intent main  = new Intent(RegistrationActivity.this, MainActivity.class);
				startActivity(main);
				
			}
		});
		
	}
	
}
