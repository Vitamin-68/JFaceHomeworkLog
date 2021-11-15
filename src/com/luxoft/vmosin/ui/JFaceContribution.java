package com.luxoft.vmosin.ui;

import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.luxoft.vmosin.actions.ExitAction;
import com.luxoft.vmosin.actions.AboutAction;
import com.luxoft.vmosin.actions.DeleteAction;
import com.luxoft.vmosin.actions.StatusAction;
import com.luxoft.vmosin.actions.OpenFileAction;
import com.luxoft.vmosin.actions.SaveFileAction;
import com.luxoft.vmosin.actions.SaveFileAsAction;

public class JFaceContribution extends ApplicationWindow {
	public StatusLineManager slm = new StatusLineManager();
	StatusAction status_action = new StatusAction(slm);
//	ActionContributionItem aci = new ActionContributionItem(status_action);

	public JFaceContribution() {
		super(null);
		addMenuBar();
		addStatusLine();
//		slm.BEGIN_GROUP;
	}

	protected Control createContents(Composite parent) {
		
		getShell().setText("JFace homework log");
		parent.setSize(700, 300);
		
		new WorkAreaComposite(parent);
		
//		aci.fill(parent);
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
		menuFile.add(new ExitAction(this));
		menuEdit.add(new DeleteAction());
		menuHelp.add(new AboutAction());
		

		return mainMenu;
	}

	protected StatusLineManager createStatusLineManager() {
		return slm;
	}
	
//	protected ToolBarManager createToolBarManager(int style) {
//		ToolBarManager tool_bar_manager = new ToolBarManager(style);
//		tool_bar_manager.add(status_action);
//		return tool_bar_manager;
//	}

}
