package com.stonesoup.Manager;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

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
			
			
			////////herereeeeeeeeeee
			listview.setOnItemLongClickListener(new OnItemLongClickListener() {
	            // setting onItemLongClickListener and passing the position to the function
	                      @Override
	            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
	                    int position, long arg3) {
	                removeItemFromList(position);   
	                
	                return true;
	            }
	        });
	    }
	// method to remove list item
	    protected void removeItemFromList(int position) {
	        final int deletePosition = position;
	        
	        AlertDialog.Builder alert = new AlertDialog.Builder(
	                EventsList.this);
	    
	        alert.setTitle("Delete");
	        alert.setMessage("Do you want delete this item?");
	        alert.setPositiveButton("YES", new OnClickListener() {
	            @Override
	            public void onClick(DialogInterface dialog, int which) {
	                // TOD O Auto-generated method stub
	                    
	                    // main code on after clicking yes
	                    ob.remove(deletePosition);
	                    adapter.notifyDataSetChanged();
	                    adapter.notifyDataSetInvalidated();
	      
	            }
	        });
	        alert.setNegativeButton("CANCEL", new OnClickListener() {
	            @Override
	            public void onClick(DialogInterface dialog, int which) {
	                // TODO Auto-generated method stub
	                dialog.dismiss();
	            }
	        });
	      
	        alert.show();
	      
	    }
			///////tohererereeeeeeeee
		}
	}

