package com.lsd.utils;

import java.util.regex.Pattern;

/**
 * @Description: 字符串工具类
 * @Author: lishengdong
 * @DateTime: 2015年11月28日 下午9:50:22
 * @Version: 1.0.0
 * @Copyright:
 * @Company:
 */
public class StringUtil {
	// 测试用例
	public static void main(String[] args) {
		System.out.println(StringUtil.isNumber("-1"));
		System.out.println(StringUtil.isNumber("0"));
		System.out.println(StringUtil.isNumber("1"));
		System.out.println(StringUtil.isNumber("10"));
		System.out.println(StringUtil.isNumber("100"));
		System.out.println(StringUtil.isNumber("111111"));
	}
	
	/**
	 * @Description: 是否为大于或等于0的数字
	 * @param str
	 * @return boolean
	 */
	public static boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");  
	    return pattern.matcher(str).matches();
	}
}