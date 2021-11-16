package com.luxoft.vmosin.ui;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.*;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import com.luxoft.vmosin.actions.DeleteAction;
import com.luxoft.vmosin.actions.SaveFileAction;
import com.luxoft.vmosin.eintity.Person;

public class RightFieldPersonData extends Composite {
	
	StatusLineManager slm = new StatusLineManager();
	SaveFileAction sfa = new SaveFileAction(slm);
	ActionContributionItem aciSfa = new ActionContributionItem(sfa);
	DeleteAction da = new DeleteAction(slm);
	ActionContributionItem aciDa = new ActionContributionItem(da);

	private Text fieldName;
	private Text fieldGroup;

	public RightFieldPersonData(Composite parent, int style) {
		super(parent, style);

		GridLayout gridLayoutRight = new GridLayout();
		gridLayoutRight.numColumns = 3;
		gridLayoutRight.makeColumnsEqualWidth = true;
		gridLayoutRight.marginTop = 20;
		this.setLayout(gridLayoutRight);

		Label labelName = new Label(this, SWT.LEFT);
		labelName.setText("Name");
		fieldName = new Text(this, SWT.RIGHT | SWT.BORDER);
		GridData gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gridData.horizontalSpan = 2;
		fieldName.setLayoutData(gridData);

		Label labelGroup = new Label(this, SWT.LEFT);
		labelGroup.setText("Group");
		fieldGroup = new Text(this, SWT.RIGHT | SWT.BORDER);
		fieldGroup.setLayoutData(gridData);
		fieldGroup.addVerifyListener(new VerifyListener() {
			@Override
			public void verifyText(VerifyEvent e) {
				StringBuilder builder = new StringBuilder(fieldGroup.getText());
				builder.insert(fieldGroup.getCaretPosition(), e.text);

				if (!e.text.matches("[0-9]*")) {
					e.doit = false;
				}
			}
		});

		Label labelDone = new Label(this, SWT.NONE);
		labelDone.setText("SWT task done");
		GridData gridDataDone = new GridData(SWT.LEFT, SWT.TOP, true, true);
		gridDataDone.horizontalSpan = 2;
		labelDone.setLayoutData(gridDataDone);

		Button buttonCheck = new Button(this, SWT.CHECK);
		GridData gridDataCheck = new GridData(SWT.END, SWT.TOP, true, false);
		buttonCheck.setLayoutData(gridDataCheck);

		Composite buttonComp = new Composite(this, SWT.NONE);
		FillLayout buttLayout = new FillLayout(SWT.HORIZONTAL);
		buttonComp.setLayout(buttLayout);
		Button button1 = new Button(buttonComp, SWT.PUSH);
		button1.setText("New");
		button1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!fieldName.getText().equals("") && !fieldGroup.getText().equals("")) {
					addNewRecord();
					fieldName.setText("");
					fieldGroup.setText("");
					fieldName.setFocus();
				} else {
					Status status = new Status(IStatus.WARNING, "dd", 0,
							"Fields \"Name\" and \"Group\" are not allowed to be empty", null);
					ErrorDialog.openError(null, "Error Message", "Enter text to \"Name\" or numbers to \"Group\"",
							status);
				}
			}

			private void addNewRecord() {
				Person p = new Person();
				p.setName(fieldName.getText());
				p.setGroup(Integer.parseInt(fieldGroup.getText()));
				p.setDone(buttonCheck.getSelection());
				LeftFieldTablViewer.getInstance().getPersons().add(p);
				LeftFieldTablViewer.getInstance().refresh();
			}
		});
		
		// button "Save"
		aciSfa.fill(buttonComp);
		
		// button "Delete"
		aciDa.fill(buttonComp);
		
//		Button button3 = new Button(buttonComp, SWT.PUSH);
//		button3.setText("Delete");
//		button3.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				TableItem[] tableItems = LeftFieldTablViewer.getInstance().getTable().getSelection();
//				if (tableItems.length > 0 && MessageDialog.openQuestion(new Shell(), "", "Delete selected row(s)?")) {
//					delRows(tableItems);
//				}
//			}
//
//			private void delRows(TableItem[] tableItems) {
//				for (int i = 0; i < tableItems.length; i++) {
//					Person p = (Person) tableItems[i].getData();
//					LeftFieldTablViewer.getInstance().getPersons().remove(p);
//				}
//				LeftFieldTablViewer.getInstance().refresh();
//			}
//		});

		Button button4 = new Button(buttonComp, SWT.PUSH);
		button4.setText("Clear");
		button4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				fieldName.setText("");
				fieldGroup.setText("");
				buttonCheck.setSelection(false);
				fieldName.setFocus();
			}

		});
		GridData gridDataButt = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gridDataButt.horizontalSpan = 4;
		buttonComp.setLayoutData(gridDataButt);
	}

}