package com.lsd.base.dao.impl;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.lsd.base.dao.IBaseDao;

@Repository("BaseDaoImpl")
public class BaseDaoImpl implements IBaseDao {
	@Resource(name = "sqlSessionTemplateReuse")
	protected SqlSessionTemplate sqlSessionTemplateReuse;

	@Resource(name = "sqlSessionTemplateBatch")
	protected SqlSessionTemplate sqlSessionTemplateBatch;

	/**
	 * @Description: MyBatis数据操作模板(重用prepared statements的执行器)
	 * @return SqlSessionTemplate
	 */
	public SqlSessionTemplate getSqlSessionTemplateReuse() {
		return sqlSessionTemplateReuse;
	}

	/**
	 * @Description: MyBatis数据操作模板(重用statements并且进行批量更新的执行器)
	 * @return SqlSessionTemplate
	 */
	public SqlSessionTemplate getSqlSessionTemplateBatch() {
		return sqlSessionTemplateBatch;
	}
}
