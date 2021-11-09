package com.luxoft.vmosin.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

public class AboutAction extends Action {

	public AboutAction() {
		super("&About@Ctrl+H", AS_PUSH_BUTTON);
	}

	public void run() {
		MessageDialog.openInformation(new Shell(), "About",
				"LUXOFT internship project\n\nCreated by Vitalii Mosin\n\n2021");
	}
}
