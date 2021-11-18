package com.luxoft.vmosin.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import com.luxoft.vmosin.repository.DataStoreList;
import com.luxoft.vmosin.ui.LeftFieldTablViewer;
import com.luxoft.vmosin.utils.Common;
import com.luxoft.vmosin.utils.JsonDataStoreImpl;
import com.luxoft.vmosin.utils.MyUtils;

public class SaveFileAsAction extends Action {

	private StatusLineManager statman;
	private DataStoreList persons;

	public SaveFileAsAction(StatusLineManager sm) {
		super("Save &As ...@Ctrl+Shift+S", AS_PUSH_BUTTON);
		statman = sm;
		persons = Common.persons;
	}

	public void run() {
		LeftFieldTablViewer tv = LeftFieldTablViewer.getInstance();
		String fn = tv.getFileStore();
		JsonDataStoreImpl dataStore = JsonDataStoreImpl.getInstance();
		FileDialog fileDialog = new FileDialog(new Shell(), SWT.SAVE);
		if (fn != null) {
			fileDialog.setFileName(MyUtils.getNameFromPath(fn));
		}
		fileDialog.setFilterExtensions(new String[] { "*.json", "*.*" });
		fileDialog.setOverwrite(true);
		fn = fileDialog.open();
		if (fn != null) {
			dataStore.saveData(persons.getPersons(), fn);
			persons.setFullFileName(fn);
			persons.setStatus(true);
			statman.setMessage("File name: " + MyUtils.getNameFromPath(LeftFieldTablViewer.getInstance().getFileStore())
					+ "		Status: Saved.");
		}
	}
}
