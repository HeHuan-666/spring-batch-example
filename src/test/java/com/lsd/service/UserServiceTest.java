package com.lsd.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageInfo;
import com.lsd.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:resources/applicationContext.xml")
public class UserServiceTest {
	
	private final static Logger logger = Logger.getLogger(UserServiceTest.class);
	
	@Resource(name = "UserServiceImpl")
	private IUserService userServiceImpl;
	
	@Before
	public void before() {
		System.out.println("================= Before =================");
	}
	
	/** 查询列表（条件与分页） */
	@Test
	public void testSelectList() {
		System.out.println("开始");
		long begin = System.currentTimeMillis();

		try {
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("pageNum", 1);
			paraMap.put("pageSize", 10);
			paraMap.put("name", "张A");
			List<User> userList = userServiceImpl.selectList(paraMap);
			
			System.out.println("共查出数据： " + userList.size());
			if (userList != null && userList.size() > 0) {
				for (User student : userList) {
					System.out.println(student.getName());
				}
			}
			
			PageInfo<User> pageInfo = new PageInfo<User>(userList);
			logger.info(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();
		long usedTime = end - begin;
		System.out.println("总时间：[" + usedTime / 1000 + "]秒, [" + usedTime + "]毫秒");
		System.out.println("结束");
	}
	
	@After
	public void after() {
		System.out.println("================= After =================");
	}
}
