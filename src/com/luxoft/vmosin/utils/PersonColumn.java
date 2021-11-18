package com.luxoft.vmosin.utils;

public enum PersonColumn {
	NAME("Name"), GROUP("Group"), IS_DONE("SWT done");

	private String name;

	PersonColumn(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
