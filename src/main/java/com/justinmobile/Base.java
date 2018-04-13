package com.justinmobile;

import java.util.HashMap;
import java.util.Map;

public class Base extends Thread {
	public static Map<Class<? extends Base>, Class<? extends Object>> map = new HashMap<Class<? extends Base>, Class<? extends Object>>();
	private String name = "base";

	public String getName2() {
		this.getName();
		return name;
	}

}
