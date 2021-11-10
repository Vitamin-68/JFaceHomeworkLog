package com.luxoft.vmosin.actions;

import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import com.luxoft.vmosin.eintity.Person;
import com.luxoft.vmosin.ui.LeftFieldTablViewer;
import com.luxoft.vmosin.utils.JsonDataStoreImpl;

public class SaveFileAction extends Action {

	private static SaveFileAction instance;

	private SaveFileAction() {
		super("&Save file@Ctrl+S", AS_PUSH_BUTTON);
	}

	public void run() {
		FileDialog fileDialog = new FileDialog(new Shell(), SWT.SAVE);
		fileDialog.setFileName("Persons.json");
		fileDialog.setFilterExtensions(new String[] { "*.json", "*.*" });
		String fn = fileDialog.open();
		JsonDataStoreImpl dataStore = JsonDataStoreImpl.getInstance();
		if (fn != null) {
			List<Person> persons = LeftFieldTablViewer.getInstance(null, null, AS_CHECK_BOX).getPersons();
			dataStore.saveData(persons, fn);
		}
	}
	
	public static SaveFileAction getInstance() {
		if (instance == null) {
			instance = new SaveFileAction();
		}
		return instance;
	}
}
