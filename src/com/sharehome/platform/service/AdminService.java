package com.sharehome.platform.service;

import com.sharehome.platform.common.MultiData;
import com.sharehome.platform.common.Page;
import com.sharehome.platform.model.Admin;

public interface AdminService {
	
	public MultiData listAdminByConditions(Admin admin,Page page);
	
	public Admin login(Admin admin);
}
