package com.luxoft.vmosin.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import com.luxoft.vmosin.ui.LeftFieldTablViewer;
import com.luxoft.vmosin.utils.JsonDataStoreImpl;

public class OpenFileAction extends Action {
	
	private StatusLineManager statman;

	public OpenFileAction(StatusLineManager sm) {
		super("&Open file...@Ctrl+O", AS_PUSH_BUTTON);
		statman = sm;
	}

	public void run() {
		LeftFieldTablViewer tv = LeftFieldTablViewer.getInstance();
		FileDialog fileDialog = new FileDialog(new Shell(), SWT.OPEN);
		fileDialog.setFileName(tv.getFileStore());
		fileDialog.setFilterExtensions(new String[] { "*.json", "*.*" });
		String fn = fileDialog.open();
		JsonDataStoreImpl dataStore = JsonDataStoreImpl.getInstance();
		if (fn != null) {
			tv.setFileStore(dataStore.loadData(fn, tv.getPersons()));
			tv.refresh();
			statman.setMessage("File name: " + getNameFromPath(LeftFieldTablViewer.getInstance().getFileStore()) 
					+ "		Status: Saved.");
		}
	}
	
	private String getNameFromPath(String path) {
		int idx = path.replaceAll("\\\\", "/").lastIndexOf("/");
		return idx >= 0 ? path.substring(idx + 1) : path;
	}
}
