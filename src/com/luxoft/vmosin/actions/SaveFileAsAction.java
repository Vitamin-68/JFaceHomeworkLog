package com.luxoft.vmosin.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import com.luxoft.vmosin.ui.LeftFieldTablViewer;
import com.luxoft.vmosin.utils.JsonDataStoreImpl;

public class SaveFileAsAction extends Action {
	
	private StatusLineManager statman;

	public SaveFileAsAction(StatusLineManager sm) {
		super("Save &As ...@Ctrl+Shift+S", AS_PUSH_BUTTON);
		statman = sm;
	}

	public void run() {
		LeftFieldTablViewer tv = LeftFieldTablViewer.getInstance();
		String fn = tv.getFileStore();
		JsonDataStoreImpl dataStore = JsonDataStoreImpl.getInstance();
		FileDialog fileDialog = new FileDialog(new Shell(), SWT.SAVE);
		fileDialog.setFileName(getNameFromPath(fn));
		fileDialog.setFilterExtensions(new String[] { "*.json", "*.*" });
		fileDialog.setOverwrite(true);
		fn = fileDialog.open();
		if (fn != null) {
			dataStore.saveData(tv.getPersons(), fn);
			tv.setFileStore(fn);
			statman.setMessage("File name: " + getNameFromPath(LeftFieldTablViewer.getInstance().getFileStore()) 
			+ "		Status: Saved.");
		}
	}
	
	private String getNameFromPath(String path) {
		int idx = path.replaceAll("\\\\", "/").lastIndexOf("/");
		return idx >= 0 ? path.substring(idx + 1) : path;
	}
}
