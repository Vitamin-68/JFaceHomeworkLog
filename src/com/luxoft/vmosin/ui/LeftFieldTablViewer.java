package com.luxoft.vmosin.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import com.luxoft.vmosin.utils.ColumnViewerComparator;
import com.luxoft.vmosin.eintity.Person;
import com.luxoft.vmosin.utils.PersonColumn;

public class LeftFieldTablViewer extends TableViewer {

	private static class OptimizedIndexSearcher {
		private int lastIndex = 0;

		public boolean isEven(TableItem item) {
			TableItem[] items = item.getParent().getItems();

			// 1. Search the next ten items
			for (int i = lastIndex; i < items.length && lastIndex + 10 > i; i++) {
				if (items[i] == item) {
					lastIndex = i;
					return lastIndex % 2 == 0;
				}
			}

			// 2. Search the previous ten items
			for (int i = lastIndex; i < items.length && lastIndex - 10 > i; i--) {
				if (items[i] == item) {
					lastIndex = i;
					return lastIndex % 2 == 0;
				}
			}

			// 3. Start from the beginning
			for (int i = 0; i < items.length; i++) {
				if (items[i] == item) {
					lastIndex = i;
					return lastIndex % 2 == 0;
				}
			}

			return false;
		}
	}

	final private OptimizedIndexSearcher searcher = new OptimizedIndexSearcher();

	private static LeftFieldTablViewer instance;
	private String fileStore;
	private List<Person> persons = new ArrayList<>();

	public LeftFieldTablViewer(Composite parent, int style) {
		super(parent, style);
		this.setContentProvider(ArrayContentProvider.getInstance());

		Table table = this.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		createTableColumn(this, SWT.NONE, PersonColumn.NAME);
		createTableColumn(this, SWT.RIGHT, PersonColumn.GROUP);
		createTableColumn(this, SWT.CENTER, PersonColumn.IS_DONE);
		
//		TableViewerColumn column1 = new TableViewerColumn(this, SWT.NONE);
//		column1.getColumn().setWidth(138);
//		column1.getColumn().setText("Name");
//		column1.setLabelProvider(createLabelProvider(this, PersonColumn.NAME));
//			@Override
//			public String getText(Object element) {
//				Person p = (Person) element;
//				return p.getName();
//			}
//		});
//		column1.getColumn().addSelectionListener(new SelectionAdapter() {
//		      public void widgetSelected(SelectionEvent event) {
//		        ((PlayerViewerSorter) tablView.getSorter())
//		            .doSort(TabColumnConst.COLUMN_DONE);
//		        tablView.refresh();
//		      }
//		    });

		this.setInput(persons);
		
		if (instance == null) {
			instance = this;
		}

	}
	private TableViewerColumn createTableColumn(TableViewer viewer, int style, PersonColumn column) {
		TableViewerColumn tColumn = new TableViewerColumn(viewer, style);
		tColumn.getColumn().setWidth(138);
		tColumn.getColumn().setText(column.getName());
		tColumn.setLabelProvider(createLabelProvider(viewer, column));
		ColumnViewerComparator cSorter = new ColumnViewerComparator(viewer, tColumn) {

			@Override
			protected int doCompare(Viewer viewer, Object e1, Object e2) {
				Person p1 = (Person) e1;
				Person p2 = (Person) e2;
				String p1Field, p2Field;
				switch (column) {
				case NAME:
					p1Field = p1.getName();
					p2Field = p2.getName();
					break;
				case GROUP:
					if (p1.getGroup() > p2.getGroup()) {
						return 1;
					}
					else if (p1.getGroup() < p2.getGroup()) {
						return -1;
					}
					return 0;
				case IS_DONE:
					p1Field = String.valueOf(p1.isDone());
					p2Field = String.valueOf(p2.isDone());
					break;
				default:
					p1Field = ""; 
					p2Field = "";
					break;
				}
				return p1Field.compareToIgnoreCase(p2Field);
			}

		};
		cSorter.setSorter(cSorter, ColumnViewerComparator.ASC);
		return tColumn;
	}

	private ColumnLabelProvider createLabelProvider(TableViewer tv, PersonColumn column) {
		return new ColumnLabelProvider() {
			boolean isEvenIdx = true;

			@Override
			public Color getBackground(Object element) {
				Color grayColor = tv.getTable().getDisplay().getSystemColor(SWT.COLOR_GRAY);

				return (isEvenIdx ? null : grayColor);
			}

			@Override
			public void update(ViewerCell cell) {
				isEvenIdx = searcher.isEven((TableItem) cell.getItem());
				super.update(cell);
			}

			@Override
			public String getText(Object element) {
				Person p = (Person) element;
				switch (column) {
				case NAME:
					return p.getName();
				case GROUP:
					return String.valueOf(p.getGroup());
				case IS_DONE:
					return null;
				default:
					break;
				}
				return null;
			}

			@Override
			public Image getImage(Object element) {
				Person p = (Person) element;
				if (column == PersonColumn.IS_DONE) {
					return ImageDescriptor
							.createFromURL(getClass().getResource(p.isDone() ? "/checked.gif" : "/unchecked.gif"))
							.createImage();
				} else {
					return null;
				}
			}

			@Override
			public void dispose() {
				super.dispose();
			}
		};
	}

	public static LeftFieldTablViewer getInstance() {
		return instance;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public String getFileStore() {
		return fileStore;
	}

	public void setFileStore(String fileStore) {
		this.fileStore = fileStore;
	}

}
