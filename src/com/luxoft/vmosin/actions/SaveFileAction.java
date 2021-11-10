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
		FileDialog fileDialog = new FileDialog(new Shell(), SWT.SAVE);
		String fn = "Persons.json";
		fileDialog.setFileName(fn);
		fileDialog.setFilterExtensions(new String[] { "*.json", "*.*" });
		JsonDataStoreImpl dataStore = JsonDataStoreImpl.getInstance();
		boolean isSaved = false;
		while (!isSaved && !(fn == null && fileDialog.getFileName().equals(""))) {
			fn = fileDialog.open();
			if (fn != null) {
				File inputfile = new File(fn);
				if (!inputfile.exists() || MessageDialog.openQuestion(new Shell(), "Confirm Save file",
						getFileName(fn) + " already exist!\nDo you want to replace it?")) {
					List<Person> persons = LeftFieldTablViewer.getInstance(null, SWT.NONE).getPersons();
					dataStore.saveData(persons, fn);
					isSaved = true;
				}
				else {
					fileDialog.setFileName("");
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
