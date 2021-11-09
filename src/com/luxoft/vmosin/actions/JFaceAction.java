package com.luxoft.vmosin.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.resource.ImageDescriptor;

public class JFaceAction extends Action {
	StatusLineManager statman;
	short triggercount = 0;

	public JFaceAction(StatusLineManager sm) {
		super("&Trigger @CTRL+T", AS_PUSH_BUTTON);
		statman = sm;
		setToolTipText("Trigger the Action");
//		setImageDescriptor(ImageDescriptor.createFromFile(this.getClass(), "eclipse.gif"));
	}

	public void run() {
		triggercount++;
		statman.setMessage("The status action has fired. Count: " + triggercount);
	}
}