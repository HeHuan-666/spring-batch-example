package com.lsd.batch.db2file;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.lsd.model.User;
import com.lsd.utils.Counter;

public class UserWriter implements ItemWriter<User> {

	@Override
	public void write(List<? extends User> items) throws Exception {
		if (items != null && items.size() > 0) {
			System.out.println("==========: " + Thread.currentThread().getName());
			Counter.getInstance().addAndGet(items.size());
			for (User user : items) {
				System.out.println("--------name: " + user.getName());
			}
		}
	}
}
