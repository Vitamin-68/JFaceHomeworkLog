package com.luxoft.vmosin.utils;

public class MyUtils {

	private MyUtils() {
	}

	public static String getNameFromPath(String path) {
		if (path == null) {
			path = "---";
		}
		int idx = path.replaceAll("\\\\", "/").lastIndexOf("/");
		return idx >= 0 ? path.substring(idx + 1) : path;
	}
}
