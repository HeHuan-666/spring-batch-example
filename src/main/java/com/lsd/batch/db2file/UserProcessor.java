package com.lsd.batch.db2file;

import org.springframework.batch.item.ItemProcessor;

import com.lsd.model.User;
import com.lsd.utils.Counter;

public class UserProcessor implements ItemProcessor<User, User> {

	@Override
	public User process(User item) throws Exception {
		long num = Counter.getInstance().incrementAndGet();
		/* 年龄加2 */
        //item.setAge(item.getAge() + 2);
		
		if (num % 10 == 0) {
			System.out.println();
		} else {
			System.out.print("*"+num + " ");
		}
        
		return item;
	}
}
