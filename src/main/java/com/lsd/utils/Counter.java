package com.lsd.utils;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description: 计数器
 * @Author: lishengdong
 * @DateTime: 2015年12月2日 下午4:28:29
 * @Version: 1.0.0
 * @Copyright:
 * @Company:
 */
public class Counter {
	private static Counter counter = new Counter();

	private static AtomicLong total = new AtomicLong();

	private Counter() {
	}

	/***
	 * @Description: 增量
	 * @return
	 */
	public long incrementAndGet() {
		return total.incrementAndGet();
	}

	/***
	 * @Description: 添加并获取指定增量
	 * @param delta
	 * @return
	 */
	public long addAndGet(long delta) {
		return total.addAndGet(delta);
	}

	public static Counter getInstance() {
		return counter;
	}

	/**
	 * @Description: 得到当前总数
	 * @return
	 */
	public long getTotal() {
		return total.get();
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			//Counter.getInstance().incrementAndGet();
			Counter.getInstance().addAndGet(2L);
		}

		System.out.println(Counter.getInstance().getTotal());
	}
}
