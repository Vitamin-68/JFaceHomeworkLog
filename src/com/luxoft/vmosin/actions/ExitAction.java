package com.luxoft.vmosin.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.ApplicationWindow;

public class ExitAction extends Action {
	ApplicationWindow win;
    public ExitAction(ApplicationWindow aWin) {
        super("E&xit@Ctrl+Q", AS_PUSH_BUTTON);
        this.win = aWin;
    }

    public void run() {
        this.win.close();
    }
}
