package cn.scau.zzzd.xst.util;

import java.util.regex.Pattern;

public class PatternUtil {
	private static String re_phone = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
	private static String re_username = "\\d{5,20}";
	private static String re_password = "\\w{5,20}";
	
	public static boolean isPhone(String input){
		return Pattern.matches(re_phone, input);
	}

	public static boolean isUsername(String input) {
		// TODO Auto-generated method stub
		return Pattern.matches(re_username, input);
	}

	public static boolean isPassword(String input) {
		// TODO Auto-generated method stub
		return Pattern.matches(re_password, input);
	}
}
