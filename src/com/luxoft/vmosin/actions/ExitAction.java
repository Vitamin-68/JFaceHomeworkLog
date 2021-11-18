package com.luxoft.vmosin.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.window.ApplicationWindow;

import com.luxoft.vmosin.utils.Common;

public class ExitAction extends Action {

	private StatusLineManager statman;
	private ApplicationWindow win;

	public ExitAction(ApplicationWindow aWin, StatusLineManager sm) {
		super("E&xit@Ctrl+Q", AS_PUSH_BUTTON);
		this.win = aWin;
		statman = sm;
	}

	public void run() {
		if (!Common.persons.isStatus()
				&& (Common.persons.getPersons().size() > 0 || Common.persons.getFullFileName() != null)) {
			new SaveFileAction(statman).run();
		}
		this.win.close();
	}
}
