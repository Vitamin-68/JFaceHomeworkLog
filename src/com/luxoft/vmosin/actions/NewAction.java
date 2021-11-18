package com.luxoft.vmosin.actions;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;

import com.luxoft.vmosin.eintity.Person;
import com.luxoft.vmosin.repository.DataStoreList;
import com.luxoft.vmosin.ui.LeftFieldTablViewer;
import com.luxoft.vmosin.utils.Common;
import com.luxoft.vmosin.utils.MyUtils;

public class NewAction extends Action {

	private StatusLineManager statman;
	private Text fieldName;
	private Text fieldGroup;
	private Button buttonCheck;
	private DataStoreList persons;

	public NewAction(StatusLineManager sm, Text fieldName, Text fieldGroup, Button buttonCheck) {
		super("&New@Ctrl+N", AS_PUSH_BUTTON);
		statman = sm;
		this.fieldName = fieldName;
		this.fieldGroup = fieldGroup;
		this.buttonCheck = buttonCheck;
		this.setToolTipText("Add new record");
		persons = Common.persons;
	}

	public void run() {
		if (!fieldName.getText().equals("") && !fieldGroup.getText().equals("")) {
			addNewRecord();
			fieldName.setText("");
			fieldGroup.setText("");
			buttonCheck.setSelection(false);
			fieldName.setFocus();
			statman.setMessage(
					"File name: " + MyUtils.getNameFromPath(persons.getFullFileName()) + "		Status: Not saved.");
			persons.setStatus(false);
		} else {
			Status status = new Status(IStatus.WARNING, "dd", 0,
					"Fields \"Name\" and \"Group\" are not allowed to be empty", null);
			ErrorDialog.openError(null, "Error Message", "Enter text to \"Name\" or numbers to \"Group\"", status);
		}
	}

	private void addNewRecord() {
		Person p = new Person();
		p.setName(fieldName.getText());
		p.setGroup(Integer.parseInt(fieldGroup.getText()));
		p.setDone(buttonCheck.getSelection());
		persons.getPersons().add(p);
		LeftFieldTablViewer.getInstance().refresh();
	}
}
