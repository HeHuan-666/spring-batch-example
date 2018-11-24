package com.lsd.base.service.impl;

import javax.annotation.Resource;

import com.lsd.base.dao.IBaseDao;
import com.lsd.base.service.IBaseService;

public class BaseServiceImpl implements IBaseService {
    @Resource(name = "BaseDaoImpl")
	protected IBaseDao baseDao;
}
