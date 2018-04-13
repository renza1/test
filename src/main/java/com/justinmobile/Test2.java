package com.justinmobile;

import java.text.DecimalFormat;

public class Test2 {

	public static void main(String[] args) {
		Integer intHao = Integer.parseInt("1");
		intHao++;
		DecimalFormat df = new DecimalFormat("0000000000");
		System.out.println(df.format(intHao));
		String[] a = new String[2];
		a[0] = "a";
		a[1] = "ab";
		System.out.println(a[0]);
	}
}
