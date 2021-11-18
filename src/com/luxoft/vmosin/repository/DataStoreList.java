package com.luxoft.vmosin.repository;

import java.util.ArrayList;
import java.util.List;

import com.luxoft.vmosin.eintity.Person;

public final class DataStoreList {

	private static DataStoreList instance;
	private final List<Person> persons;
	private boolean status;
	private String fullStoreFileName;

	private DataStoreList() {
		persons = new ArrayList<>();
		status = false;
	}

	public static DataStoreList getInstance() {
		if (instance == null) {
			instance = new DataStoreList();
		}
		return instance;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getFullFileName() {
		return fullStoreFileName;
	}

	public void setFullFileName(String fullFileName) {
		this.fullStoreFileName = fullFileName;
	}
}
