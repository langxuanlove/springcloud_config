package com.key.api.util;

import java.util.UUID;

public class UUIDUtil {

	public UUIDUtil() {
	}

	public static String getUUID() {
		String s = UUID.randomUUID().toString();
		return (new StringBuilder(String.valueOf(s.substring(0, 8))))
				.append(s.substring(9, 13)).append(s.substring(14, 18))
				.append(s.substring(19, 23)).append(s.substring(24)).toString();
	}

	public static String[] getUUID(int piNumber) {
		if (piNumber < 1)
			return null;
		String ss[] = new String[piNumber];
		for (int i = 0; i < piNumber; i++)
			ss[i] = getUUID();

		return ss;
	}

//	public static void main(String[] args) {
//		System.out.println(UUIDUtil.getUUID());
//		System.out.println(UUIDUtil.getUUID().length());
//	}
}
