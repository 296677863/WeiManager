package com.weiweb.common.utils;

import java.util.UUID;

public class UUIDHelper {

	public static String gen(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
