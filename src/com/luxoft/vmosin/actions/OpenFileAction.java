package com.luxoft.vmosin.actions;

import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;


import com.luxoft.vmosin.eintity.Person;
import com.luxoft.vmosin.ui.LeftFieldTablViewer;
import com.luxoft.vmosin.utils.JsonDataStoreImpl;

public class OpenFileAction extends Action {

	private static OpenFileAction instance;

	private OpenFileAction() {
		super("&Open file...@Ctrl+O", AS_PUSH_BUTTON);
	}

	public void run() {
		FileDialog fileDialog = new FileDialog(new Shell(), SWT.OPEN);
		fileDialog.setFileName("Persons.json");
		fileDialog.setFilterExtensions(new String[] {"*.json", "*.*"});
		String fn = fileDialog.open();
		JsonDataStoreImpl dataStore = JsonDataStoreImpl.getInstance();
        if (fn != null) {
        	System.out.println("Open");
        	List<Person> persons = dataStore.loadData(fn);

        	LeftFieldTablViewer tv = LeftFieldTablViewer.getInstance(null, null, SWT.NONE);
        	tv.setPersons(persons);
        	tv.setInput(tv.getPersons());

        	//this doesn't work 
//        	tv.getTable().redraw();
//        	tv.refresh();
        }
	}
	
	public static OpenFileAction getInstance() {
		if (instance == null) {
			instance = new OpenFileAction();
		}
		return instance;
	}
}
