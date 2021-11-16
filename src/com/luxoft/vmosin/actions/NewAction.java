package com.luxoft.vmosin.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

public class NewAction extends Action {

	public NewAction() {
		super("&New@Ctrl+N", AS_PUSH_BUTTON);
	}

	public void run() {
		MessageDialog.openInformation(new Shell(), "About",
				"LUXOFT internship project\n\nCreated by Vitalii Mosin\n\n2021");
	}
}
