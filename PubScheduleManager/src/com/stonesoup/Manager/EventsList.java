package com.stonesoup.Manager;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class EventsList extends Activity {
	
	ListView listview;
	List<ParseObject> ob;
	ProgressDialog mProgressDialog;
	ListViewAdapter adapter;
	private WorldPopulation worldPop;
	private List<WorldPopulation> worldpopulationlist = null;
	
	String classname = "Program";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.eventstview_main);
		worldPop = new WorldPopulation();
		new RemoteDataTask().execute();
	}
	
	private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Create a progressdialog
			mProgressDialog = new ProgressDialog(EventsList.this);
			// Set progressdialog title
			mProgressDialog.setTitle("Retrieving data from database");
			// Set progressdialog message
			mProgressDialog.setMessage("Loading...");
			mProgressDialog.setIndeterminate(false);
			// Show progressdialog
			mProgressDialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			
			worldpopulationlist = new ArrayList<WorldPopulation>();
			try {
				String classname = "Program";
				ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(classname);
				query.orderByAscending("createdAt");
				ob = query.find();
				for (ParseObject country : ob) {
					
					WorldPopulation map = new WorldPopulation();
					map.setDate((String) country.get("date"));
					map.setUsername((String) country.get("username"));
					map.setJob((String) country.get("position"));
					map.setSituation((String) country.get("situation"));
					worldpopulationlist.add(map);
					
						
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
			listview = (ListView) findViewById(R.id.eventlistview);
			// Pass the results into ListViewAdapter.java
			adapter = new ListViewAdapter(EventsList.this,
					worldpopulationlist);
			// Binds the Adapter to the ListView
			listview.setAdapter(adapter);
			// Close the progressdialog
			mProgressDialog.dismiss();
		}
	}
}
