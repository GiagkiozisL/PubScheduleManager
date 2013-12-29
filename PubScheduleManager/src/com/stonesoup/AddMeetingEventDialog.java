package com.stonesoup;

import android.app.DialogFragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class AddMeetingEventDialog extends DialogFragment {
	
	Button createBtn,cancelBtn;
	static Typeface typeface;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.event_dialog, container);
		getDialog().setTitle("Define time of meeting");
		createBtn = (Button) view.findViewById(R.id.createBtn);
		cancelBtn = (Button) view.findViewById(R.id.cancel2Btn);
		typeface = Typeface.createFromAsset(getActivity().getAssets(), "Exo-SemiBold.otf");
		createBtn.setTypeface(typeface);
		cancelBtn.setTypeface(typeface);
		
		createBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AddMeetingEventDialog.this.getDialog().cancel();
				
			}
		});
		cancelBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AddMeetingEventDialog.this.getDialog().cancel();
				
			}
		});
		return view;
	}

}
