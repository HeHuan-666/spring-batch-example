package com.lsd.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @Description: 属性文件工具类
 * @Author: lishengdong
 * @DateTime: 2015年11月27日 上午11:45:20
 * @Version: 1.0.0
 * @Copyright:
 * @Company:
 */
public class PropertiesUtil {
	// 测试用例
	public static void main(String[] args) {
		PropertiesUtil p = new PropertiesUtil("/resources/redis.properties");
		System.out.println(p.getStringValByKey("minevictableidletimemillis"));
	}

	private final static Logger logger = Logger.getLogger(PropertiesUtil.class);

	private static Properties pros = null; // 静态的属性变量

	/**
	 * 加载属性文件
	 */
	public PropertiesUtil(String path) {
		try {
			pros = new Properties(); // 静态的属性变量
			BufferedReader bfReader = getBufferedReader(path);
			pros.load(bfReader);
		} catch (IOException e) {
			logger.debug("系统属性文件初始化失败!");
			e.printStackTrace();
		}
	}

	/**
	 * 加载文件
	 * 
	 * @param filePath
	 * @return
	 */
	private BufferedReader getBufferedReader(String filePath) {
		InputStream inStream = PropertiesUtil.class.getResourceAsStream(filePath);
		BufferedReader bfReader = new BufferedReader(new InputStreamReader(inStream));

		return bfReader;
	}

	/**
	 * 根据key取value
	 * 
	 * @param key
	 * @return String
	 */
	public String getStringValByKey(String key) {
		if (pros == null || pros.isEmpty()) {
			return null;
		}

		return pros.getProperty(key, "");
	}

	/**
	 * 根据key取value
	 * 
	 * @param key
	 * @return int
	 */
	public int getIntValByKey(String key) {
		if (pros == null || pros.isEmpty()) {
			return 0;
		}
		int val = Integer.parseInt(pros.getProperty(key));

		return val;
	}

	public long getLongValByKey(String key) {
		if (pros == null || pros.isEmpty()) {
			return 0;
		}
		long val = Long.parseLong(pros.getProperty(key));

		return val;
	}
}
