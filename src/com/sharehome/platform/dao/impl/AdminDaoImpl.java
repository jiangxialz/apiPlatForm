package com.sharehome.platform.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sharehome.platform.common.Page;
import com.sharehome.platform.dao.AdminDao;
import com.sharehome.platform.global.Constants;
import com.sharehome.platform.model.Admin;

@Repository
public class AdminDaoImpl implements AdminDao{
	
	private final static Logger log = Logger.getLogger(AdminDaoImpl.class);
	
	@Autowired
	private SqlSession sqlSession;

	// 命名空间
	private final String nameSpace = "com.sharehome.platform.mapper.AdminMapper";
	
	@Override
	public List<Admin> listAdminByConditions(Admin admin,Page page) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("admin", admin);
		params.put("page", page);
		log.info("==========" + nameSpace + Constants.DOT + "listAdminByConditionsLimit");
		return sqlSession.selectList(nameSpace + Constants.DOT + "listAdminByConditionsLimit", params);
	}
	
	public Page listAdminByConditionsCount(Admin admin,Page page) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("admin", admin);
		page.setPageCount((Integer)sqlSession.selectOne(nameSpace + Constants.DOT + "listAdminByConditionsCount", params));
		return page;
	}
	
	@Override
	public Admin getAdminByLoginName(Admin admin) {
		return sqlSession.selectOne("com.sharehome.platform.mapper.AdminMapper.getAdminByLoginName", admin);
	}
}
