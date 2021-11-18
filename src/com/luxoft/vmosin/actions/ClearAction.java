package com.luxoft.vmosin.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;

public class ClearAction extends Action {

	private Text fieldName;
	private Text fieldGroup;
	private Button buttonCheck;

	public ClearAction(Text fieldName, Text fieldGroup, Button buttonCheck) {
		super("&Clear@Ctrl+Shift+C", AS_PUSH_BUTTON);
		this.fieldName = fieldName;
		this.fieldGroup = fieldGroup;
		this.buttonCheck = buttonCheck;
		this.setToolTipText("Clear input fields");
	}

	public void run() {
		fieldName.setText("");
		fieldGroup.setText("");
		buttonCheck.setSelection(false);
		fieldName.setFocus();
	}
}
