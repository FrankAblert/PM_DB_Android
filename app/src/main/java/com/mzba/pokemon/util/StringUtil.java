package com.mzba.pokemon.util;
/**
 * 
 * @author 06peng
 *
 */
public class StringUtil {

	/**
	 * 把字符串数组用逗号隔开
	 * @param arrays
	 * @return
	 */
	public static String ArraysToString(String[] arrays) {
		String result = "";
		if (arrays != null && arrays.length > 0) {
			for (String value : arrays) {
				result += value + ",";
			}
			result = result.substring(0, result.length() - 1);
		}
		return result;
	}
	
	public static String intArraysToString(int[] arrays) {
		String result = "";
		if (arrays != null && arrays.length > 0) {
			for (int value : arrays) {
				result += value + " ";
			}
			result = result.substring(0, result.length() - 1);
		}
		return result;
	}
	
	public static boolean isBlank(String str) {
		return str == null || str.equals("");
	}
	
	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}
}
