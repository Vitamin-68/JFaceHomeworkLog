package com.luxoft.vmosin;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.widgets.Display;

import com.luxoft.vmosin.ui.JFaceContribution;

public class JFaceMain extends ApplicationWindow {

	public JFaceMain() {
		super(null);
	}

	public static void main(String[] args) {
		JFaceContribution swin = new JFaceContribution();
		swin.setBlockOnOpen(true);
		swin.open();
		Display.getCurrent().dispose();
	}

}
