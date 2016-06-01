package com.tms.util;

import java.sql.Timestamp;

public class FormatUtil {
	public static void main(String[] args){
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		System.out.println(ts.toLocaleString());
	}
}
