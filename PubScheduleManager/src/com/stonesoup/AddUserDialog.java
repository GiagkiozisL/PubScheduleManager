package com.stonesoup;

import android.app.DialogFragment;
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


public class AddUserDialog extends DialogFragment implements OnEditorActionListener{

	public interface EditNameDialogListener {
		void onFinishEditDialog(String inputText,String inputPass);
	}
	private EditText usernameEdit,passwordEdit;
	private Button addBtn,cancelBtn;
	
	//constructor
	public AddUserDialog() { }
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_user, container);
        usernameEdit = (EditText) view.findViewById(R.id.addUsername);
        passwordEdit = (EditText) view.findViewById(R.id.addPassword);
       passwordEdit.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if ((event.getAction() == KeyEvent.ACTION_DOWN)
                        && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    EditNameDialogListener activity = (EditNameDialogListener) getActivity();
                    activity.onFinishEditDialog(usernameEdit.getText().toString(), passwordEdit.getText().toString());
                    // EditNameDialog.this.dismiss();
                    return true;
                }
                return false;
            }
		});
       
        addBtn = (Button) view.findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AddUserDialog.this.getDialog().cancel();
			}
		}); 
       
        cancelBtn = (Button) view.findViewById(R.id.cancelBtn);
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