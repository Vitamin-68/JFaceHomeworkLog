package com.luxoft.vmosin.actions;

import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import com.luxoft.vmosin.eintity.Person;
import com.luxoft.vmosin.utils.JsonDataStoreImpl;

public class SaveFileAction extends Action {

	private List<Person> persons;

	public SaveFileAction(List<Person> persons) {
		super("&Save file@Ctrl+S", AS_PUSH_BUTTON);
		this.persons = persons;
	}

	public void run() {
		FileDialog fileDialog = new FileDialog(new Shell(), SWT.SAVE);
		fileDialog.setFileName("Persons.json");
		fileDialog.setFilterExtensions(new String[] { "*.json", "*.*" });
		String fn = fileDialog.open();
		JsonDataStoreImpl dataStore = JsonDataStoreImpl.getInstance();
		if (fn != null) {
			dataStore.saveData(persons, fn);
		}
	}
}
