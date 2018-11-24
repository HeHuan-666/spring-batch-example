package com.lsd.batch.db2file;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.lsd.model.User;
import com.lsd.utils.DateTimeUtil;

/**
 * @Description: 用户表映射类
 * @Author: lishengdong
 * @DateTime: 2015年12月2日 上午11:23:03
 * @Version: 1.0.0
 * @Copyright: 
 * @Company:
 */
public class UserMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setAge(rs.getInt("age"));
		user.setSex(rs.getString("sex"));
		user.setPhone(rs.getString("phone"));
		user.setCreateTime(DateTimeUtil.getDateFromString("yyyy-MM-dd HH:mm:ss", rs.getString("create_time")));
		
		return user;
	}
}
