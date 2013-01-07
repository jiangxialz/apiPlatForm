package com.sharehome.platform.dao;

import java.util.List;

import com.sharehome.platform.common.Page;
import com.sharehome.platform.model.Admin;

public interface AdminDao {

	public List<Admin> listAdminByConditions(Admin admin,Page page);
	
	public Page listAdminByConditionsCount(Admin amdin,Page page);
	
	public Admin getAdminByLoginName(Admin admin);
}
