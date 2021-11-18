package com.luxoft.vmosin.utils;

import org.eclipse.jface.action.StatusLineManager;

import com.luxoft.vmosin.repository.DataStoreList;

public class Common {

	public static final StatusLineManager slm = new StatusLineManager();
	public static final DataStoreList persons = DataStoreList.getInstance();
	public static final int tableWidthPercent = 60;
}
