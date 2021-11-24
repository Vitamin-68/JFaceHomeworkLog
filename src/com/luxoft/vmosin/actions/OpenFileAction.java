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

public class OpenFileAction extends Action {

	private StatusLineManager statman;
	private DataStoreList persons;

	public OpenFileAction(StatusLineManager sm) {
		super("&Open file...@Ctrl+O", AS_PUSH_BUTTON);
		statman = sm;
		persons = Common.persons;
	}

	public void run() {
		LeftFieldTablViewer tv = LeftFieldTablViewer.getInstance();
		FileDialog fileDialog = new FileDialog(new Shell(), SWT.OPEN);
		fileDialog.setFileName(tv.getFileStore());
		fileDialog.setFilterExtensions(new String[] { "*.json", "*.*" });
		String fn = fileDialog.open();
		JsonDataStoreImpl dataStore = JsonDataStoreImpl.getInstance();
		if (fn != null) {
			persons.setFullFileName(dataStore.loadData(fn, persons.getPersons()));
			persons.setStatus(true);
			tv.refresh();
			statman.setMessage(String.format("File name: %s		Status: Loaded.",
					MyUtils.getNameFromPath(persons.getFullFileName())));
		}
	}
}
