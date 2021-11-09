package com.luxoft.vmosin.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

public class DeleteAction extends Action {
	
	public DeleteAction() {
		super("&Delete@Ctrl+D", AS_PUSH_BUTTON);
	}

	public void run() {
		MessageDialog.openInformation(new Shell(), "Warning",
				"Not working yet");
	}
}
