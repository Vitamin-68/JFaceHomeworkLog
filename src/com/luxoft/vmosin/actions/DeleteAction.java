package com.luxoft.vmosin.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;

import com.luxoft.vmosin.eintity.Person;
import com.luxoft.vmosin.repository.DataStoreList;
import com.luxoft.vmosin.ui.LeftFieldTablViewer;
import com.luxoft.vmosin.utils.Common;
import com.luxoft.vmosin.utils.MyUtils;

public class DeleteAction extends Action {

	private StatusLineManager statman;
	private DataStoreList persons;

	public DeleteAction(StatusLineManager sm) {
		super("&Delete@Ctrl+D", AS_PUSH_BUTTON);
		statman = sm;
		this.setToolTipText("Delete selected row(s)");
		persons = Common.persons;
	}

	public void run() {
		LeftFieldTablViewer tv = LeftFieldTablViewer.getInstance();
		TableItem[] tableItems = tv.getTable().getSelection();
		if (tableItems.length > 0 && MessageDialog.openQuestion(new Shell(), "", "Delete selected row(s)?")) {
			delRows(tableItems);
			persons.setStatus(false);
			statman.setMessage(
					"File name: " + MyUtils.getNameFromPath(persons.getFullFileName()) + "		Status: Not saved.");
		}
	}

	private void delRows(TableItem[] tableItems) {
		for (int i = 0; i < tableItems.length; i++) {
			Person p = (Person) tableItems[i].getData();
			persons.getPersons().remove(p);
		}
		LeftFieldTablViewer.getInstance().refresh();
	}
}
