package com.luxoft.vmosin.ui;

import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.swt.*;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import com.luxoft.vmosin.actions.ClearAction;
import com.luxoft.vmosin.actions.DeleteAction;
import com.luxoft.vmosin.actions.NewAction;
import com.luxoft.vmosin.actions.SaveFileAction;
import com.luxoft.vmosin.utils.Common;

public class RightFieldPersonData extends Composite {

	private StatusLineManager slm = Common.slm;
	private SaveFileAction sfa = new SaveFileAction(slm);
	private ActionContributionItem aciSfa = new ActionContributionItem(sfa);
	private DeleteAction da = new DeleteAction(slm);
	private ActionContributionItem aciDa = new ActionContributionItem(da);
	private ClearAction clearAction;
	private ActionContributionItem aciClearAction;
	private NewAction newAction;
	private ActionContributionItem aciNewAction;

	private Text fieldName;
	private Text fieldGroup;
	private Button buttonCheck;

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

		buttonCheck = new Button(this, SWT.CHECK);
		GridData gridDataCheck = new GridData(SWT.END, SWT.TOP, true, false);
		buttonCheck.setLayoutData(gridDataCheck);

		Composite buttonComp = new Composite(this, SWT.NONE);
		FillLayout buttLayout = new FillLayout(SWT.HORIZONTAL);
		buttLayout.spacing = 5;
		buttonComp.setLayout(buttLayout);

		GridData gridDataButt = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gridDataButt.horizontalSpan = 4;
		buttonComp.setLayoutData(gridDataButt);

		// button "New"
		newAction = new NewAction(slm, fieldName, fieldGroup, buttonCheck);
		aciNewAction = new ActionContributionItem(newAction);
		aciNewAction.fill(buttonComp);

		// button "Save"
		aciSfa.fill(buttonComp);

		// button "Delete"
		aciDa.fill(buttonComp);

		// button "Clear"
		clearAction = new ClearAction(fieldName, fieldGroup, buttonCheck);
		aciClearAction = new ActionContributionItem(clearAction);
		aciClearAction.fill(buttonComp);
	}
}