package com.luxoft.vmosin.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;

import com.luxoft.vmosin.eintity.Person;
import com.luxoft.vmosin.ui.LeftFieldTablViewer;

public class DeleteAction extends Action {
	
	private StatusLineManager statman;
	
	public DeleteAction(StatusLineManager sm) {
		super("&Delete@Ctrl+D", AS_PUSH_BUTTON);
		statman = sm;
	}

	public void run() {
		LeftFieldTablViewer tv = LeftFieldTablViewer.getInstance();
		TableItem[] tableItems = tv.getTable().getSelection();
		if (tableItems.length > 0 && MessageDialog.openQuestion(new Shell(), "", "Delete selected row(s)?")) {
			delRows(tableItems);
			statman.setMessage("File name: " + getNameFromPath(tv.getFileStore()) + "		Status: Not saved.");
		}
	}
	
	private void delRows(TableItem[] tableItems) {
		for (int i = 0; i < tableItems.length; i++) {
			Person p = (Person) tableItems[i].getData();
			LeftFieldTablViewer.getInstance().getPersons().remove(p);
		}
		LeftFieldTablViewer.getInstance().refresh();
	}
	
	private String getNameFromPath(String path) {
		int idx = path.replaceAll("\\\\", "/").lastIndexOf("/");
		return idx >= 0 ? path.substring(idx + 1) : path;
	}
}
