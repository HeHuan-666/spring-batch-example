package com.lsd.service;

import java.util.List;
import java.util.Map;

import com.lsd.model.User;

public interface IUserService {
	/**
	 * @Description: 批量添加
	 * @param user
	 * @return 添加条数
	 */
	public int batchInsert(List<User> userList) throws Exception;

	/**
	 * @Description: 根据条件、分页参数(pageNum, pageSize)查询列表数据
	 * @param paraMap
	 * @return 数据列表
	 */
	public List<User> selectList(Map<String, Object> paraMap) throws Exception;
}
