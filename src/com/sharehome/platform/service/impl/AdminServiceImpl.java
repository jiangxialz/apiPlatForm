package com.sharehome.platform.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharehome.platform.common.MultiData;
import com.sharehome.platform.common.Page;
import com.sharehome.platform.dao.AdminDao;
import com.sharehome.platform.model.Admin;
import com.sharehome.platform.service.AdminService;
import com.sharehome.platform.utils.MD5Util;

@Service
public class AdminServiceImpl implements AdminService{
	
	private final static Logger log = Logger.getLogger(AdminServiceImpl.class);
	
	@Autowired
	private AdminDao adminDao;
	
	@Override
	public MultiData listAdminByConditions(Admin admin,Page page) {
		MultiData multiData = new MultiData();
		List<Admin> admins = adminDao.listAdminByConditions(admin,page);
		page = adminDao.listAdminByConditionsCount(admin, page);
		log.info("总记录数："+ page.getPageCount());
		multiData.setData(admins);
		multiData.setPage(page);
		return multiData;
	}

	
	@Override
	public Admin login(Admin admin) {
		Admin currrent_admin = adminDao.getAdminByLoginName(admin);
		if(null != currrent_admin){
			String password = admin.getPassword();
			password = MD5Util.make(password);
			if(password.equals(currrent_admin.getPassword())){
				return currrent_admin;
			}
		}
		return null;
	}

}
