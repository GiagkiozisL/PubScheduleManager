package com.stonesoup.Manager;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.AsyncTask;
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
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class ScheduleActivity extends Activity{

	List<ParseUser> userOb;
	List<ParseObject> ob;
	List<String> n = new ArrayList<String>();
	private Spinner spinner1,spinner2,spinner3;
	private DatePicker datePicker;
	String name,str,finalDay,finalDate;
	Integer day,month,year,finalMonth,finalYear;
	Users users;
	Typeface typeface;
	int I;
	Button submitBtn;

	Spinner spinner;
	String INTTAG;
    
    ProgressDialog mProgressDialog;
    ParseQueryAdapter<ParseObject> adapter;
    private List<WorldPopulation> worldpopulationlist = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.schedule_activity);
		addItemsOnSpinner1();
		addItemsOnSpinner2();
		addItemsOnSpinner3();
		new RemoteDataTask().execute();
		typeface = Typeface.createFromAsset(getAssets(), "Exo-SemiBold.otf");
		Button submitBtn = (Button) findViewById(R.id.btnSubmit);
		submitBtn.setTypeface(typeface);
		submitBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (spinner1.getSelectedItem().equals("--Choose a situation--") || spinner3.getSelectedItem().equals("--Choose a person--") || spinner3.getSelectedItem().equals("--Choose a position--"))
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
					
					finalYear = Integer.valueOf(datePicker.getYear() - 2000);
					if (datePicker.getDayOfMonth() <10)
					{
						finalDay = String.valueOf("0"+datePicker.getDayOfMonth()); 
					}
					else {finalDay = String.valueOf(datePicker.getDayOfMonth());}
					
					finalMonth = Integer.valueOf(datePicker.getMonth()+1);
					
					if (finalMonth <10)
					{
						finalDate = String.valueOf(finalDay+"/"+"0"+finalMonth+"/"+finalYear);
					}
					else
						finalDate = String.valueOf(finalDay+"/"+finalMonth+"/"+finalYear);
					
				createClassWeeklySchedule(); 
				}
			}

			private void createClassWeeklySchedule() {
				
				ParseObject weeklyProgram = new ParseObject("Program");
				weeklyProgram.put("situation", spinner1.getSelectedItem());
				weeklyProgram.put("position", spinner3.getSelectedItem());
				weeklyProgram.put("date",finalDate);
				//weeklyProgram.put("date",datePicker.getDayOfMonth()+"/"+datePicker.getMonth()+"/"+datePicker.getYear());
				weeklyProgram.put("username",spinner2.getSelectedItem());
				weeklyProgram.saveInBackground();
				
			}
		});
	}
	
	private void addItemsOnSpinner2() {

		spinner2 = (Spinner) findViewById(R.id.spinner2);
		
		List<String> userlist = new ArrayList<String>();
		userlist.add("--Choose a person--");
		ParseQuery<ParseUser> query = ParseUser.getQuery();
		//query.orderByAscending("createdAt");
		query.whereNotEqualTo("objectId", "whatever");
		try {
			userOb = query.find();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (ParseUser user : userOb) {
			
			WorldPopulation map = new WorldPopulation();
			map.setUsername((String) user.get("username"));
			Log.i("USER:::", map.getUsername());
			
			userlist.add(map.getUsername());
			Log.i("USER2:::", userlist.toString());
		}
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, userlist);
			dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinner2.setAdapter(dataAdapter);
			spinner2.setPrompt("--Choose a person--");
	}

	private void addItemsOnSpinner3() { 
		spinner3 = (Spinner) findViewById(R.id.spinner3); 
		}

	public void addItemsOnSpinner1() {
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		datePicker = (DatePicker) findViewById(R.id.datePicker1);

	}
	
	// RemoteDataTask AsyncTask
    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(ScheduleActivity.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Parse.com Custom ListView Tutorial");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }
 
        @Override
        protected Void doInBackground(Void... params) {
            // Create the array
            worldpopulationlist = new ArrayList<WorldPopulation>();
            try {
                // Locate the class table named "Country" in Parse.com
                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Program");
                 query.orderByAscending("usename");
                ob = query.find();
                int i = 0;
                
                for (ParseObject country : ob) {
             
                    WorldPopulation map = new WorldPopulation();
                    map.setUsername((String) country.get("date"));
                    worldpopulationlist.add(map);
                    i++;
                    Log.d(INTTAG, country.get("date").toString());
                    Log.d(INTTAG,query.toString());
                    
                }
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
           return null;
        }
 
        @Override
        protected void onPostExecute(Void result) {
        	adapter = new ParseQueryAdapter<ParseObject>();
            mProgressDialog.dismiss();
        }
    }
}