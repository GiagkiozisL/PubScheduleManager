package com.stonesoup;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class ScheduleActivity extends Activity{

	List<ParseObject> ob;
	private Spinner spinner2;
	Users users;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.schedule_activity);
		addItemsOnSpinner2();
	}
	
	public void addItemsOnSpinner2() {

		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("AnemosTheBar");
		query.getInBackground("", new GetCallback<ParseObject>() {
			  public void done(ParseObject object, ParseException e) {
			    if (e == null) {
			      // object will be your game score
			    } else {
			      // something went wrong
			    }
			  }
			});
		
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		List<String> list = new ArrayList<String>();
		list.add(query.toString());
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner2.setAdapter(dataAdapter);
	}
}