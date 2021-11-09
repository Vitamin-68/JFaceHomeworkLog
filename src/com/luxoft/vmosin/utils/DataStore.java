package com.luxoft.vmosin.utils;

import java.util.List;

public interface DataStore<T> {
	
	public void saveData(List<T> list, String fileName);
	public List<T> loadData(String file);

}
