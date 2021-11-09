package com.luxoft.vmosin.actions;

import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;


import com.luxoft.vmosin.eintity.Person;
import com.luxoft.vmosin.utils.JsonDataStoreImpl;

public class OpenFileAction extends Action {

	private static OpenFileAction instance;
	public List<Person> persons;

	private OpenFileAction(List<Person> persons) {
		super("&Open file...@Ctrl+O", AS_PUSH_BUTTON);
		this.persons = persons;
		ImageDescriptor.createFromURL(getClass().getResource("/checked.gif")).createImage();
	}

	public void run() {
		FileDialog fileDialog = new FileDialog(new Shell(), SWT.OPEN);
		fileDialog.setFileName("Persons.json");
		fileDialog.setFilterExtensions(new String[] {"*.json", "*.*"});
		String fn = fileDialog.open();
		JsonDataStoreImpl dataStore = JsonDataStoreImpl.getInstance();
        if (fn != null) {
        	persons = dataStore.loadData(fn);
        	
//        	System.out.println(persons.toString());
//        	System.out.println(persons.get(0).toString());
        }
	}
	
	public static OpenFileAction getInstance(List<Person> persons) {
		if (instance == null) {
			instance = new OpenFileAction(persons);
		}
		return instance;
	}
}
