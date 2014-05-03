package com.stonesoup.Manager;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UserListViewAdapter extends BaseAdapter{

	Context context;
	LayoutInflater inflater;
	private List<WorldPopulation> worldpopulationlist = null;
	private ArrayList<WorldPopulation> arraylist;
	LinearLayout linear;
	ImageView imageworker;

	public UserListViewAdapter(Context context,
			List<WorldPopulation> worldpopulationlist) {
		this.context = context;
		this.worldpopulationlist = worldpopulationlist;
		inflater = LayoutInflater.from(context);
		this.arraylist = new ArrayList<WorldPopulation>();
		this.arraylist.addAll(worldpopulationlist);
	}

	public class ViewHolder {
	//	TextView date;
		TextView username;
	//	TextView job;
	}
	
	@Override
	public int getCount() {
		return worldpopulationlist.size();
	}

	@Override
	public Object getItem(int position) {
		return worldpopulationlist.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		final ViewHolder holder;
	
		if (view == null) {
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.userlistview_item, null);
			// Locate the TextViews in listview_item.xml
			holder.username = (TextView) view.findViewById(R.id.usernametxt2);
		
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		linear = (LinearLayout) view.findViewById(R.id.userlinearr);
		imageworker = (ImageView) view.findViewById(R.id.imageworker);
		// Set the results into TextViews
		holder.username.setText(worldpopulationlist.get(position).getUsername());

		return view;

	}

}