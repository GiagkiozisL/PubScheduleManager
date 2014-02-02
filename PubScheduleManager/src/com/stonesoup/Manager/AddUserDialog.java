package com.stonesoup.Manager;

import android.app.DialogFragment;
import android.graphics.Typeface;
import android.os.Bundle;
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


public class AddUserDialog extends DialogFragment implements OnEditorActionListener{

	public interface EditNameDialogListener {
		void onFinishEditDialog(String inputText,String inputPass);
	}
	private EditText usernameEdit,passwordEdit;
	private Button addBtn,cancelBtn;
	static Typeface typeface;

	//constructor
	public AddUserDialog() { }
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_user_dialog, container);
        typeface = Typeface.createFromAsset(getActivity().getAssets(), "Exo-SemiBold.otf");
        getDialog().setTitle("Create a new user");
        usernameEdit = (EditText) view.findViewById(R.id.addUsername);
        usernameEdit.setTypeface(typeface);
        passwordEdit = (EditText) view.findViewById(R.id.addPassword);
        passwordEdit.setTypeface(typeface);
       passwordEdit.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if ((event.getAction() == KeyEvent.ACTION_DOWN)
                        && (keyCode == KeyEvent.KEYCODE_ENTER)) {
				
                   
					EditNameDialogListener activity = (EditNameDialogListener) getActivity();
                    activity.onFinishEditDialog(usernameEdit.getText().toString(), passwordEdit.getText().toString());
                   return true;
                }
                return false;
            }

		});
       
        addBtn = (Button) view.findViewById(R.id.createBtn);
        addBtn.setTypeface(typeface);
        addBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
		String user = usernameEdit.getText().toString();
		String pass = passwordEdit.getText().toString();
				if (user != null || pass != null)
					Toast.makeText(getActivity(), "You did not enter a username", Toast.LENGTH_SHORT).show();
				AddUserDialog.this.getDialog().cancel();
			}
		}); 
       
        cancelBtn = (Button) view.findViewById(R.id.cancel2Btn);
        cancelBtn.setTypeface(typeface);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AddUserDialog.this.getDialog().cancel();
				
			}
		});
        return view;
    }
 
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (EditorInfo.IME_ACTION_DONE == actionId) {
            // Return input text to activity
            EditNameDialogListener activity = (EditNameDialogListener) getActivity();
            activity.onFinishEditDialog(usernameEdit.getText().toString(),passwordEdit.getText().toString());
            this.dismiss();
            return true;
        }
        return false;
    }
}