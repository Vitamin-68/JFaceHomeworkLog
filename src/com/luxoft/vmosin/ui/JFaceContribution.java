package com.luxoft.vmosin.ui;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.luxoft.vmosin.actions.ExitAction;
import com.luxoft.vmosin.actions.AboutAction;
import com.luxoft.vmosin.actions.DeleteAction;
import com.luxoft.vmosin.actions.OpenFileAction;
import com.luxoft.vmosin.actions.SaveFileAction;
import com.luxoft.vmosin.actions.SaveFileAsAction;
import com.luxoft.vmosin.utils.Common;

public class JFaceContribution extends ApplicationWindow {

	private final StatusLineManager slm = Common.slm;

	public JFaceContribution() {
		super(null);
		addMenuBar();
		addStatusLine();
	}

	protected Control createContents(Composite parent) {

		getShell().setText("JFace homework log");
		parent.setSize(700, 300);
		new WorkAreaComposite(parent);
		return parent;
	}

	protected MenuManager createMenuManager() {
		MenuManager mainMenu = new MenuManager(null);
		MenuManager menuFile = new MenuManager("&File");
		MenuManager menuEdit = new MenuManager("&Edit");
		MenuManager menuHelp = new MenuManager("&Help");

		mainMenu.add(menuFile);
		mainMenu.add(menuEdit);
		mainMenu.add(menuHelp);

		menuFile.add(new OpenFileAction(slm));
		menuFile.add(new SaveFileAction(slm));
		menuFile.add(new SaveFileAsAction(slm));
		menuFile.add(new ExitAction(this, slm));
		menuEdit.add(new DeleteAction(slm));
		menuHelp.add(new AboutAction());

		return mainMenu;
	}

	protected StatusLineManager createStatusLineManager() {
		return slm;
	}
}
