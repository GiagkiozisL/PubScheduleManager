package com.stonesoup.Manager;

import android.app.DialogFragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;



public class AddMeetingEventDialog extends DialogFragment implements OnEditorActionListener{
	
	public interface EditMeetingDialogListener {
		void onFinishEditMeetingDialog(String date,String time);
	}
	
	Button createBtn,cancelBtn;
	String date,time;
	static Typeface typeface;
	private DatePicker datePicker;
	private TimePicker timePicker;
	private EditText year_display,month_display,date_display,hour_display,min_display;
	
	//constructor
		public AddMeetingEventDialog() { }
		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.event_dialog, container);
		getDialog().setTitle("Define time of meeting");
		
		typeface = Typeface.createFromAsset(getActivity().getAssets(), "Exo-SemiBold.otf");
		year_display = (EditText) view.findViewById(R.id.year_display);
		year_display.setTypeface(typeface);
		month_display = (EditText) view.findViewById(R.id.month_display);
		month_display.setTypeface(typeface);
		date_display = (EditText) view.findViewById(R.id.date_display);
		date_display.setTypeface(typeface);
		hour_display = (EditText) view.findViewById(R.id.hour_display);
		hour_display.setTypeface(typeface);
		min_display = (EditText) view.findViewById(R.id.min_display);
		min_display.setTypeface(typeface);
		Log.d("say wwhat>??" , "yeardisplay: "+month_display+"hour_display: "+min_display.getText().toString());
		min_display.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if ((event.getAction() == KeyEvent.ACTION_DOWN)
                        && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
					EditMeetingDialogListener activity = (EditMeetingDialogListener) getActivity();
                    activity.onFinishEditMeetingDialog(datePicker.year_watcher+"-"+datePicker.months+"-"+datePicker.date_watcher.toString(), timePicker.hour_watcher+":"+timePicker.min_watcher.toString());
                    return true;
                }
				return false;
			}
		});
		
		timePicker = (TimePicker) view.findViewById(R.id.timeEvent);
		  datePicker = (DatePicker) view.findViewById(R.id.dateEvent);
		
		  createBtn = (Button) view.findViewById(R.id.createBtn);
		createBtn.setTypeface(typeface);
		createBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				date = date_display.toString();
				time = hour_display.getText().toString();
				Toast.makeText(getActivity(), "You did not enter a username", Toast.LENGTH_SHORT).show();
				AddMeetingEventDialog.this.getDialog().cancel();
				
			}
		});
		cancelBtn = (Button) view.findViewById(R.id.cancel2Btn);
		cancelBtn.setTypeface(typeface);
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
	            activity.onFinishEditMeetingDialog(date.toString(),time.toString());
	            this.dismiss();
	            return true;
	        }
	        return false;
	}

}
