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
import com.parse.ParseQuery;
import com.parse.ParseUser;



public class UserList extends Activity {
	
	//String classNames;
	ListView listview;
	List<ParseUser> ob;
	ProgressDialog mProgressDialog;
	UserListViewAdapter userAdapter;
	private WorldPopulation worldPop;
	private List<WorldPopulation> worldpopulationlist = null;
	
	String classname = "User";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.userlistview_main);
		worldPop = new WorldPopulation();
		new RemoteDataTask().execute();
		
		
	}
	
	private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Create a progressdialog
			mProgressDialog = new ProgressDialog(UserList.this);
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
				//ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(classname);
				ParseQuery<ParseUser> query = ParseUser.getQuery();
				//query.orderByAscending("createdAt");
				query.whereNotEqualTo("objectId", "whatever");
				ob = query.find();
				for (ParseUser user : ob) {
					
					WorldPopulation map = new WorldPopulation();
					map.setUsername((String) user.get("username"));
					Log.i("USER:::", map.getUsername());
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
			listview = (ListView) findViewById(R.id.userlistview);
			// Pass the results into ListViewAdapter.java
			userAdapter = new UserListViewAdapter(UserList.this,
					worldpopulationlist);
			// Binds the Adapter to the ListView
			listview.setAdapter(userAdapter);
			// Close the progressdialog
			mProgressDialog.dismiss();
			////HEREEEEEEEEEEEEEEEEEEEEE
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
	                UserList.this);
	    
	        alert.setTitle("Delete");
	        alert.setMessage("Do you want delete this item?");
	        alert.setPositiveButton("YES", new OnClickListener() {
	            @Override
	            public void onClick(DialogInterface dialog, int which) {	              
             	               
//	            	ParseUser user = ParseUser.delet
//	            	user.deleteInBackground();
	                    // main code on after clicking yes
	                    ob.remove(deletePosition);
	                    userAdapter.notifyDataSetChanged();
	                    userAdapter.notifyDataSetInvalidated();
	      
	            }
	        });
	        alert.setNegativeButton("CANCEL", new OnClickListener() {
	            @Override
	            public void onClick(DialogInterface dialog, int which) {
	                dialog.dismiss();
	            }
	        });
	      
	        alert.show();
	      
	    }
			/////TO THERE
			
		}
	}
	