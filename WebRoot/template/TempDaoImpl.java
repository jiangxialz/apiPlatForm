package com.sharehome.platform.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sharehome.platform.dao.${className}Dao;

@Repository
public class ${className}DaoImpl implements ${className}Dao{
	
	private final static Logger log = Logger.getLogger(${className}DaoImpl.class);
	
	@Autowired
	private SqlSession sqlSession;

}
