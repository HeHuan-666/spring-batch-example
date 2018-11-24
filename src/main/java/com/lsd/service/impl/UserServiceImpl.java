package com.lsd.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.lsd.base.service.impl.BaseServiceImpl;
import com.lsd.model.User;
import com.lsd.service.IUserService;
import com.lsd.utils.StringUtil;

@Service("UserServiceImpl")
public class UserServiceImpl extends BaseServiceImpl implements IUserService {
	/**
	 * @Description: 批量添加
	 * @param user
	 * @return 添加条数
	 */
	@Override
	public int batchInsert(List<User> userList) throws Exception {
		return baseDao.getSqlSessionTemplateReuse().insert("UserMapper.batchInsert", userList);
	}

	/**
	 * @Description: 根据条件、分页参数(pageNum, pageSize)查询列表数据
	 * @param paraMap
	 * @return 数据列表
	 */
	@Override
	public List<User> selectList(Map<String, Object> paraMap) throws Exception {
		if (paraMap == null) {
			throw new Exception("参数不能为空!");
		}
		if (paraMap.get("pageNum") != null && paraMap.get("pageSize") != null) {
			if (StringUtil.isNumber(paraMap.get("pageNum").toString())
					&& StringUtil.isNumber(paraMap.get("pageSize").toString())) {
				PageHelper.startPage(Integer.parseInt(paraMap.get("pageNum").toString()),
						Integer.parseInt(paraMap.get("pageSize").toString()));
			} else {
				throw new Exception("分页参数不是正整数!");
			}
		}
		return baseDao.getSqlSessionTemplateReuse().selectList("UserMapper.selectList", paraMap);
	}
}