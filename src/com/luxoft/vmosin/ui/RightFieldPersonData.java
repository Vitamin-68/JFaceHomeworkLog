package com.luxoft.vmosin.ui;

import java.util.List;

import org.eclipse.swt.*;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import com.luxoft.vmosin.eintity.Person;

public class RightFieldPersonData extends Composite {
	
	private final List<Person> persons;

	public RightFieldPersonData(Composite parent, List<Person> persons, int style) {
		super(parent, style);
		this.persons = persons;
		
		GridLayout gridLayoutRight = new GridLayout();
		gridLayoutRight.numColumns = 3;
		gridLayoutRight.makeColumnsEqualWidth = true;
		gridLayoutRight.marginTop = 20;
		this.setLayout(gridLayoutRight);
		
		Label labelName = new Label(this, SWT.LEFT);
		labelName.setText("Name");
		Text fieldName = new Text(this, SWT.RIGHT | SWT.BORDER);
		GridData gridData = new GridData(SWT.FILL, SWT.CENTER , true, false);
	    gridData.horizontalSpan = 2;
	    fieldName.setLayoutData(gridData);
		
		Label labelGroup = new Label(this, SWT.LEFT);
		labelGroup.setText("Group");
		Text fieldGroup = new Text(this, SWT.RIGHT | SWT.BORDER);
		fieldGroup.setLayoutData(gridData);
		
		Label labelDone = new Label(this, SWT.NONE);
		labelDone.setText("SWT task done");
		GridData gridDataDone = new GridData(SWT.LEFT, SWT.TOP , true, true);
		gridDataDone.horizontalSpan = 2;
		labelDone.setLayoutData(gridDataDone);
		
		Button buttonCheck = new Button(this, SWT.CHECK);
		GridData gridDataCheck = new GridData(SWT.END, SWT.TOP , true, false);
		buttonCheck.setLayoutData(gridDataCheck);
		
		Composite buttonComp = new Composite(this, SWT.NONE);
		FillLayout buttLayout = new FillLayout(SWT.HORIZONTAL);
		buttonComp.setLayout(buttLayout);
		Button button1 = new Button(buttonComp, SWT.PUSH);
		button1.setText("New");
		Button button2 = new Button(buttonComp, SWT.PUSH);
		button2.setText("Save");
		Button button3 = new Button(buttonComp, SWT.PUSH);
		button3.setText("Delete");
		Button button4 = new Button(buttonComp, SWT.PUSH);
		button4.setText("Cancel");
		GridData gridDataButt = new GridData(SWT.FILL, SWT.CENTER , true, false);
		gridDataButt.horizontalSpan = 4;
		buttonComp.setLayoutData(gridDataButt);
	}
	
	
}