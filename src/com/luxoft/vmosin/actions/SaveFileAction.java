package com.luxoft.vmosin.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import com.luxoft.vmosin.repository.DataStoreList;
import com.luxoft.vmosin.utils.Common;
import com.luxoft.vmosin.utils.JsonDataStoreImpl;
import com.luxoft.vmosin.utils.MyUtils;

public class SaveFileAction extends Action {

	private StatusLineManager statman;
	private DataStoreList persons;

	public SaveFileAction(StatusLineManager sm) {
		super("&Save@Ctrl+S", AS_PUSH_BUTTON);
		statman = sm;
		this.setToolTipText("Save table");
		persons = Common.persons;
	}

	public void run() {
		if (MessageDialog.openQuestion(new Shell(), "Save", "Wanna save your changes?")) {
			String fn = persons.getFullFileName();
			JsonDataStoreImpl dataStore = JsonDataStoreImpl.getInstance();
			if (fn == null) {
				FileDialog fileDialog = new FileDialog(new Shell(), SWT.SAVE);
				fileDialog.setFilterExtensions(new String[] { "*.json", "*.*" });
				fileDialog.setOverwrite(true);
				fn = fileDialog.open();
			}
			if (fn != null) {
				dataStore.saveData(persons.getPersons(), fn);
				persons.setFullFileName(fn);
				persons.setStatus(true);
				statman.setMessage(String.format("File name: %s		Status: Saved.",
						MyUtils.getNameFromPath(persons.getFullFileName())));
			}
		}
	}
}
