package com.luxoft.vmosin.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

public class WorkAreaComposite extends Composite {

	public WorkAreaComposite(Composite parent) {
		super(parent, SWT.NONE);

		FillLayout workAreaLayout = new FillLayout(SWT.HORIZONTAL);
		this.setLayout(workAreaLayout);
		SashForm form = new SashForm(this, SWT.HORIZONTAL);
		form.setSize(parent.getSize().x - 17, parent.getSize().y - 90);

		new LeftFieldTablViewer(form, SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL | SWT.MULTI);
		new RightFieldPersonData(form, SWT.NONE);

		form.setWeights(new int[] { 55, 35 });
	}
}
