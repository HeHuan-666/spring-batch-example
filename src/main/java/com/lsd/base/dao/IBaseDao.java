package com.lsd.base.dao;

import org.mybatis.spring.SqlSessionTemplate;

public interface IBaseDao {
	/**
	 * @Description: MyBatis数据操作模板(重用prepared statements的执行器)
	 * @return SqlSessionTemplate
	 */
	public SqlSessionTemplate getSqlSessionTemplateReuse();

	/**
	 * @Description: MyBatis数据操作模板(重用statements并且进行批量更新的执行器)
	 * @return SqlSessionTemplate
	 */
	public SqlSessionTemplate getSqlSessionTemplateBatch();
}
