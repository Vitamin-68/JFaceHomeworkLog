package com.luxoft.vmosin.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.StatusLineManager;

import com.luxoft.vmosin.ui.LeftFieldTablViewer;

public class StatusAction extends Action {
	StatusLineManager statman;

	public StatusAction(StatusLineManager sm) {
//		super("&Trigger @CTRL+T", AS_PUSH_BUTTON);
		statman = sm;
//		setToolTipText("Trigger the Action");
//		setImageDescriptor(ImageDescriptor.createFromFile(this.getClass(), "eclipse.gif"));
	}

	public void run() {
		statman.setMessage("File name: " + LeftFieldTablViewer.getInstance().getFileStore() 
				+ "Status: ");
	}
}