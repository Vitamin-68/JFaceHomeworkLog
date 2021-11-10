package com.luxoft.vmosin.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.luxoft.vmosin.actions.ExitAction;
import com.luxoft.vmosin.actions.AboutAction;
import com.luxoft.vmosin.actions.DeleteAction;
import com.luxoft.vmosin.actions.JFaceAction;
import com.luxoft.vmosin.actions.OpenFileAction;
import com.luxoft.vmosin.actions.SaveFileAction;
import com.luxoft.vmosin.eintity.Person;

public class JFaceContribution extends ApplicationWindow {
	StatusLineManager slm = new StatusLineManager();
	JFaceAction status_action = new JFaceAction(slm);
	ActionContributionItem aci = new ActionContributionItem(status_action);
//	private JsonDataStoreImpl dataStore = new JsonDataStoreImpl();
	public final List<Person> persons = new ArrayList<>();

	public JFaceContribution() {
		super(null);
		addStatusLine();
		addMenuBar();
	}

	protected Control createContents(Composite parent) {
		
		getShell().setText("JFace homework log");
		parent.setSize(700, 300);
		
		persons.add(new Person("Ivan", 1, true));
		persons.add(new Person("Piter", 1, false));
		persons.add(new Person("John", 2, false));
		
		new WorkAreaComposite(parent, persons);
		
//		aci.fill(parent);
		return parent;
	}

	protected MenuManager createMenuManager() {
		MenuManager mainMenu = new MenuManager(null);
		MenuManager menuFile = new MenuManager("&File");
		MenuManager menuEdit = new MenuManager("&Edit");
		MenuManager menuHelp = new MenuManager("&Help");
//		MenuManager subMenuFileOpen = new MenuManager("&Open file...     @CTRL+O");
//		MenuManager subMenuFileSave = new MenuManager("&Save file       @CTRL+S");
//		MenuManager subMenuFileSaveAs = new MenuManager("Save file &As ... @CTRL+SHIFT+S");
//		MenuManager subMenuFileExit = new MenuManager("&Exit        @CTRL+Q");
//		MenuManager subMenuEditDelete = new MenuManager("&Delete      @CTRL+D");
//		MenuManager subMenuHelpAbout = new MenuManager("&About       @CTRL+H");

		mainMenu.add(menuFile);
		mainMenu.add(menuEdit);
		mainMenu.add(menuHelp);

		menuFile.add(OpenFileAction.getInstance());
		menuFile.add(SaveFileAction.getInstance());
//		menuFile.add(subMenuFileSaveAs);
		menuFile.add(new ExitAction(this));

		menuEdit.add(new DeleteAction());

		menuHelp.add(new AboutAction());
		
//		subMenuHelpAbout.add(status_action);

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
