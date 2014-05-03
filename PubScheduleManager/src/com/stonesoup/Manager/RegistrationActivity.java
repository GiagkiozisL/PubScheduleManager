package com.stonesoup.Manager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class RegistrationActivity extends Activity{

	private EditText userEditText,passEditText;
	TextView user,pass;
	private Button submit;
	Typeface typeface;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration);
		typeface = Typeface.createFromAsset(getAssets(), "Exo-SemiBold.otf");
		user = (TextView) findViewById(R.id.usernametxt2);
		user.setTypeface(typeface);
		pass = (TextView) findViewById(R.id.password);
		pass.setTypeface(typeface);
		userEditText = (EditText) findViewById(R.id.usernameEditTxt);
		passEditText = (EditText) findViewById(R.id.passwordEditTxt);
		submit = (Button) findViewById(R.id.settingsSubmitBtn);
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
		
				final String username = userEditText.getText().toString();
                String password = passEditText.getText().toString();

                ParseUser.logInInBackground(username, password,
                        new LogInCallback() {
                            public void done(ParseUser user, ParseException e) {
                                if (user != null) {
                                    // If user exist and authenticated, send user to Welcome.class
                                    Intent intent = new Intent(
                                            RegistrationActivity.this,
                                            MainActivity.class);
                                    startActivity(intent);
                                    
                                    Toast.makeText(getApplicationContext(),
                                            "Successfully Logged in",
                                            Toast.LENGTH_LONG).show();
                                    finish();
                                } else {
                                    Toast.makeText(
                                            getApplicationContext(),
                                            "No such user exist, please signup",
                                            Toast.LENGTH_LONG).show();
                                    Toast.makeText(getApplicationContext(), username, Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                
                
                }
		});
		
		
		
	}
	
}
