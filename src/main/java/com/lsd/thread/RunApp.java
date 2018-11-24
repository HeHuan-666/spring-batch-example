package com.lsd.thread;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lsd.service.IUserService;

/**
 * @Description: 创建用户数据类
 * @Author: lishengdong
 * @DateTime: 2015年11月30日 上午10:47:22
 * @Version: 1.0.0
 * @Copyright: 
 * @Company:
 */
public class RunApp {
	private static ApplicationContext context;
	
	//测试用例
	public static void main(String[] args) {
		context = new ClassPathXmlApplicationContext("resources/applicationContext.xml");
		IUserService userServiceImpl = (IUserService) context.getBean("UserServiceImpl");
		UserCreateData userCreateData = new UserCreateData(userServiceImpl, 5, 100000, 100);
		userCreateData.create();
		userCreateData.printInfo();
	}
}
