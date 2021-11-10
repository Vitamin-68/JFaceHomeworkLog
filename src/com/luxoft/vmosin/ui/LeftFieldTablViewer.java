package com.luxoft.vmosin.ui;

import java.util.List;


import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import com.luxoft.vmosin.eintity.Person;

public class LeftFieldTablViewer extends TableViewer {

	private static LeftFieldTablViewer instance;
	private List<Person> persons;

	private LeftFieldTablViewer(Composite parent, int style) {
		super(parent, style);
		this.setContentProvider(ArrayContentProvider.getInstance());
//		this.persons = persons;

		Table table = this.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableViewerColumn column1 = new TableViewerColumn(this, SWT.NONE);
		column1.getColumn().setWidth(138);
		column1.getColumn().setText("Name");
		column1.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Person p = (Person) element;
				return p.getName();
			}
		});
//		column1.getColumn().addSelectionListener(new SelectionAdapter() {
//		      public void widgetSelected(SelectionEvent event) {
//		        ((PlayerViewerSorter) tablView.getSorter())
//		            .doSort(TabColumnConst.COLUMN_DONE);
//		        tablView.refresh();
//		      }
//		    });

		TableViewerColumn column2 = new TableViewerColumn(this, SWT.RIGHT);
		column2.getColumn().setWidth(138);
		column2.getColumn().setText("Class");
		column2.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Person p = (Person) element;
				return String.valueOf(p.getGroup());
			}
		});

		TableViewerColumn column3 = new TableViewerColumn(this, SWT.CENTER);
		column3.getColumn().setWidth(138);
		column3.getColumn().setText("SWT Done");
		column3.setLabelProvider(new ColumnLabelProvider() {

		    @Override
		    public String getText(Object element) {
		        return null;  // no string representation, we only want to display the image
		    }

		    @Override
		    public Image getImage(Object element) {
		    	Person p = (Person) element;
		        return ImageDescriptor.createFromURL(getClass().getResource(p.isDone() ?
		        		"/checked.gif" : "/unchecked.gif")).createImage();
		    }

		    @Override
		    public void dispose() {
		        super.dispose();
		    }
		});	
		
//			@Override
//			public String getText(Object element) {
//				Person p = (Person) element;
//				return String.valueOf(p.isDone());
//			}
//		});
		this.setInput(persons);
		this.refresh();
	}
	
	public static LeftFieldTablViewer getInstance(Composite parent, int style) {
		if (instance == null) {
			instance = new LeftFieldTablViewer(parent, style);
		}
		return instance;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
	
}
