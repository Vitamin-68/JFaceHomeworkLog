package com.luxoft.vmosin.eintity;

public class Person {

	private String name;
	private int group;
	private boolean isDone;
	
	public Person() {};

	public Person(String name, int group, boolean isDone) {
		super();
		this.name = name;
		this.group = group;
		this.isDone = isDone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", group=" + group + ", isDone=" + isDone + "]";
	}
}
