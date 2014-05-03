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

public class ListViewAdapter extends BaseAdapter{

	Context context;
	LayoutInflater inflater;
	private List<WorldPopulation> worldpopulationlist = null;
	private ArrayList<WorldPopulation> arraylist;
	LinearLayout linear;
	ImageView imageworker;

	public ListViewAdapter(Context context,
			List<WorldPopulation> worldpopulationlist) {
		this.context = context;
		this.worldpopulationlist = worldpopulationlist;
		inflater = LayoutInflater.from(context);
		this.arraylist = new ArrayList<WorldPopulation>();
		this.arraylist.addAll(worldpopulationlist);
	}

	public class ViewHolder {
		TextView date;
		TextView username;
		TextView job;
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
			view = inflater.inflate(R.layout.eventstview_item, null);
			// Locate the TextViews in listview_item.xml
			holder.date = (TextView) view.findViewById(R.id.datetxt);
			holder.username = (TextView) view.findViewById(R.id.usernametxt);
			holder.job = (TextView) view.findViewById(R.id.jobtxt);
			// Locate the ImageView in listview_item.xml
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		linear = (LinearLayout) view.findViewById(R.id.linearr);
		imageworker = (ImageView) view.findViewById(R.id.imageworker);
		// Set the results into TextViews
		holder.date.setText(worldpopulationlist.get(position).getDate());
		holder.username.setText(worldpopulationlist.get(position).getUsername());
		holder.job.setText(worldpopulationlist.get(position).getJob());
		
		if (worldpopulationlist.get(position).getSituation().equalsIgnoreCase("Day")) {
			linear.setBackgroundResource(R.drawable.day7);
		} else {
			linear.setBackgroundResource(R.drawable.night7);
		}
		if (worldpopulationlist.get(position).getJob().equalsIgnoreCase("Service")) {
			imageworker.setImageResource(R.drawable.wairtress);
		} else if (worldpopulationlist.get(position).getJob().equalsIgnoreCase("Service 2nd")) {
			imageworker.setImageResource(R.drawable.wairtress2nd);
		} else if (worldpopulationlist.get(position).getJob().equalsIgnoreCase("Dj")) {
			imageworker.setImageResource(R.drawable.turntable_manager);
		} else if (worldpopulationlist.get(position).getJob().equalsIgnoreCase("Bar")) {
			imageworker.setImageResource(R.drawable.bartender);
		} else if (worldpopulationlist.get(position).getJob().equalsIgnoreCase("Chef")) {
			imageworker.setImageResource(R.drawable.chef_manager);
		}
		
		return view;

	}

}
