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
	
//	ListView listview;
	Spinner spinner;
	String INTTAG;
    
    ProgressDialog mProgressDialog;
    ParseQueryAdapter<ParseObject> adapter;
    private List<WorldPopulation> worldpopulationlist = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//		final ParseQuery<ParseUser> query = ParseUser.getQuery();
//		query.orderByAscending("username");
//		query.findInBackground(new FindCallback<ParseUser>() {
//		  public void done(List<ParseUser> objects, ParseException e) {
//		    if (e == null) {
//		    	
//		    	ParseQuery fewWins = new ParseQuery("User");
//	            fewWins.addAscendingOrder("username");
//		    	
//		    	query.addAscendingOrder("username");
//		    	Log.d("TAG", query.toString());
//		    	
//		    	Log.d("TAG2", fewWins.toString());
//		        // The query was successful.
//		    } else {
//		        // Something went wrong.
//		    }
//		  }
//		});
		
		setContentView(R.layout.schedule_activity);
		addItemsOnSpinner2();
		addItemOnSpinner3();
		new RemoteDataTask().execute();
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
				weeklyProgram.put("date",datePicker.getDayOfMonth()+"/"+datePicker.getMonth()+"/"+datePicker.getYear());
				weeklyProgram.saveInBackground();
				
			}
		});
	}
	
	private void addItemOnSpinner3() {
		//we can also add drop down list items dynamically as in addItemsOnSpinner2
	}

	public void addItemsOnSpinner2() {

		
//		ParseQuery<ParseObject> query  = ParseQuery.getQuery("User");
//		query.whereNotEqualTo("username", "whatever");
//        query.findInBackground(new FindCallback<ParseObject>() {
//            @Override
//            public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {
//                if (e==null){
//
//                    Log.d("THE OBJECT", "" +parseObjects.size());
//
//
//                    name =  parseObjects.toString();
//                    Log.d("THE QUERY ", "" + name);
//
//                } else {
//                    Log.d("ERROR:", "" + e.getMessage());
//                }
//            }
//        });
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
            // Locate the listview in listview_main.xml
            spinner = (Spinner) findViewById(R.id.spinner2);
            // Pass the results into ListViewAdapter.java
            adapter = new ParseQueryAdapter<ParseObject>();
//            		ListViewAdapter(ScheduleActivity.this,
//                    worldpopulationlist);
            // Binds the Adapter to the ListView
            spinner.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();
        }
    }
}