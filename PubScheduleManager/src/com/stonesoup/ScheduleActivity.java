package com.stonesoup;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class ScheduleActivity extends Activity{

	List<ParseObject> ob;
	List<String> n = new ArrayList<String>();
	private Spinner spinner1,spinner2,spinner3;
	private DatePicker datePicker;
	String name,str;
	Integer day,month,year;
	Users users;
	Typeface typeface;
	int I;
	Button submitBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.schedule_activity);
		addItemsOnSpinner2();
		addItemOnSpinner3();
		
		typeface = Typeface.createFromAsset(getAssets(), "Exo-SemiBold.otf");
		Button submitBtn = (Button) findViewById(R.id.btnSubmit);
		submitBtn.setTypeface(typeface);
		submitBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (spinner1.getSelectedItem().equals("--Choose a situation--") || spinner3.getSelectedItem().equals("--Choose a position--"))
				{ 
					Toast.makeText(ScheduleActivity.this, "You haven't choose one or more fields!\n Please,try again", Toast.LENGTH_SHORT).show();
				
				}
				else {	
					Toast.makeText(ScheduleActivity.this,
						"OnClickListener : " + 
				                "\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem()) + 
				                "\nSpinner 2 : "+ String.valueOf(spinner2.getSelectedItem()) + 
				                "\nSpinner 3 : "+ String.valueOf(spinner3.getSelectedItem()) + 
				                "\nDate : "+ String.valueOf(datePicker.getYear()+"-"+datePicker.getMonth()+"-"+datePicker.getDayOfMonth()),
							Toast.LENGTH_SHORT).show();	
				createClassWeeklySchedule(); 
				}
			}

			private void createClassWeeklySchedule() {
				ParseObject weeklyProgram = new ParseObject("Program");
				weeklyProgram.put("situation", spinner1.getSelectedItem());
		//		weeklyProgram.put("name", spinner2.getSelectedItem());
				weeklyProgram.put("position", spinner3.getSelectedItem());
				weeklyProgram.put("date",datePicker.getYear()+"-"+datePicker.getMonth()+"-"+datePicker.getDayOfMonth());
				weeklyProgram.saveInBackground();
				
			}
		});
	}
	
	private void addItemOnSpinner3() {
		//we can also add drop down list items dynamically as in addItemsOnSpinner2
	}

	public void addItemsOnSpinner2() {

		ParseQuery<ParseObject> query  = ParseQuery.getQuery("User");
		query.whereNotEqualTo("username", "whatever");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {
                if (e==null){

                    Log.d("THE OBJECT", "" +parseObjects.size());


                    name =  parseObjects.toString();
                    Log.d("THE QUERY ", "" + name);

                } else {
                    Log.d("ERROR:", "" + e.getMessage());
                }
            }
        });
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner3 = (Spinner) findViewById(R.id.spinner3);
		datePicker = (DatePicker) findViewById(R.id.datePicker1);
		 
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		List<String> list = new ArrayList<String>();
		 
		list.add(name);
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner2.setAdapter(dataAdapter);
	}
}