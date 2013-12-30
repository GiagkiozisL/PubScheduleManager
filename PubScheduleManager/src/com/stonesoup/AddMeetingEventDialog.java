package com.stonesoup;

import android.app.DialogFragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.Toast;

import com.stonesoup.DatePicker;
import com.stonesoup.TimePicker;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;



public class AddMeetingEventDialog extends DialogFragment implements OnEditorActionListener{
	
	public interface EditMeetingDialogListener {
		void onFinishEditMeetingDialog(String date,String time);
	}
	
	Button createBtn,cancelBtn;
	String date,time;
	static Typeface typeface;
	private DatePicker datePicker;
	private TimePicker timePicker;
	
	//constructor
		public AddMeetingEventDialog() { }
		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.event_dialog, container);
		getDialog().setTitle("Define time of meeting");
		
		  timePicker = (TimePicker) view.findViewById(R.id.timeEvent);
		  datePicker = (DatePicker) view.findViewById(R.id.dateEvent);
		createBtn = (Button) view.findViewById(R.id.createBtn);
		cancelBtn = (Button) view.findViewById(R.id.cancel2Btn);
		typeface = Typeface.createFromAsset(getActivity().getAssets(), "Exo-SemiBold.otf");
		createBtn.setTypeface(typeface);
		cancelBtn.setTypeface(typeface);
		
		
		createBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				date = datePicker.year_watcher+"-"+datePicker.months+"-"+datePicker.date_watcher;
				time = timePicker.hour_watcher+":"+timePicker.min_watcher;
			//	Toast.makeText(getActivity(), "You did not enter a username", Toast.LENGTH_SHORT).show();
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

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		 if (EditorInfo.IME_ACTION_DONE == actionId) {
	            // Return input text to activity
	            EditMeetingDialogListener activity = (EditMeetingDialogListener) getActivity();
	            activity.onFinishEditMeetingDialog(date,time);
	            this.dismiss();
	            return true;
	        }
	        return false;
	}

}
