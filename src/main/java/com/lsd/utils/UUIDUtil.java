package com.lsd.utils;

import java.util.UUID;

/**
 * @Description: UUID工具类
 * @Author: lishengdong
 * @DateTime: 2015年11月27日 上午11:45:44
 * @Version: 1.0.0
 * @Copyright: 
 * @Company:
 */
public class UUIDUtil {
	/**
	 * 取UUID(去横杠)
	 * 
	 * @return String
	 */
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "");
	}
}
