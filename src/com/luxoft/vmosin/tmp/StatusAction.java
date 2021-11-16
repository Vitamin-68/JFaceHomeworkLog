package com.luxoft.vmosin.tmp;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.StatusLineManager;

import com.luxoft.vmosin.ui.LeftFieldTablViewer;

public class StatusAction extends Action {
	StatusLineManager statman;
	public static int count = 0;

	public StatusAction(StatusLineManager sm) {
		statman = sm;
		count++;
	}

	public void run() {
		statman.setMessage("File name: " + LeftFieldTablViewer.getInstance().getFileStore() 
				+ "Status: Count =  " + count);
	}
}