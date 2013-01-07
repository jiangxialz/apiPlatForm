package com.sharehome.platform.service.impl;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharehome.platform.service.${className}Service;
import com.sharehome.platform.dao.${className}Dao;

@Service
public class ${className}ServiceImpl implements ${className}Service{
	
	private final static Logger log = Logger.getLogger(${className}ServiceImpl.class);
	
	@Autowired
    private ${className}Dao dao;

}
