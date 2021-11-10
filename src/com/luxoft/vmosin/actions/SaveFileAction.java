package com.luxoft.vmosin.actions;

import java.io.File;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
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
		Shell fds = new Shell();
		FileDialog fileDialog = new FileDialog(fds, SWT.SAVE);
		fileDialog.setFileName("Persons.json");
		fileDialog.setFilterExtensions(new String[] { "*.json", "*.*" });
		JsonDataStoreImpl dataStore = JsonDataStoreImpl.getInstance();
		boolean isSaved = false;
		while (!isSaved) {
			String fn = fileDialog.open();
			File inputfile = new File(fn);
			if (fn != null) {
				if (!inputfile.exists() || MessageDialog.openQuestion(fds, "Confirm Save file",
						getFileName(fn) + " already exist!\nDo you want to replace it?")) {
					List<Person> persons = LeftFieldTablViewer.getInstance(null, null, AS_CHECK_BOX).getPersons();
					dataStore.saveData(persons, fn);
					isSaved = true;
				}
			}
		}
	}

	public static SaveFileAction getInstance() {
		if (instance == null) {
			instance = new SaveFileAction();
		}
		return instance;
	}

	private String getFileName(String filePath) {
		int idx = filePath.replaceAll("\\\\", "/").lastIndexOf("/");
		return idx >= 0 ? filePath.substring(idx + 1) : filePath;
	}
}
