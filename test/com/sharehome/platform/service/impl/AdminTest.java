package com.sharehome.platform.service.impl;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sharehome.platform.common.MultiData;
import com.sharehome.platform.common.Page;
import com.sharehome.platform.junit.BaseJunitCase;
import com.sharehome.platform.service.AdminService;
import com.sharehome.platform.vo.AdminBO;

public class AdminTest extends BaseJunitCase{

	@Autowired
	AdminService adminService;
	
	@Test
	public void testSelectBbsPostList(){
		AdminBO adminBO = new AdminBO();
		Page page = new Page();
		page.setPageNum(1);
		page.setNumPerPage(10);
		MultiData mutiDate = adminService.listAdminByConditions(adminBO, page);
		logger.info("当前页记录数："+ mutiDate.getData().size());
		logger.info("总记录数："+ mutiDate.getPage().getPageCount());
		Assert.assertEquals(1, mutiDate.getPage().getPageCount());
	}
	
}
