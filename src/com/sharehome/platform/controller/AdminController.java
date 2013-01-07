package com.sharehome.platform.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sharehome.platform.common.MultiData;
import com.sharehome.platform.common.Page;
import com.sharehome.platform.model.Admin;
import com.sharehome.platform.service.AdminService;

@Controller
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value="login")
	public String login(HttpServletRequest request,Admin admin,ModelMap modelMap){
		
		admin = adminService.login(admin);
		if(null != admin){
			HttpSession httpSession = request.getSession();
			// 设置session失效时间,1800秒意思就是失效时间为30分钟
			httpSession.setMaxInactiveInterval(1800);
			httpSession.setAttribute("auth", admin);
			return "/center";
		}
		return "redirect:/index.html";
	}
	
	@RequestMapping("admin/listInfo")
	public String listAdmin(Admin admin,Page page,ModelMap modelMap){
		MultiData multiData = adminService.listAdminByConditions(admin, page);
		modelMap.addAttribute("multiData", multiData);
		return "admin/list";
	}
	
	@RequestMapping(value="logout")
	public String logout(ModelMap modelMap,HttpServletRequest request,HttpServletResponse response){
		
		HttpSession session = request.getSession();
		session.setAttribute("auth", null);
		String contextPath= request.getContextPath();
		modelMap.put("contextPath", contextPath);
		return "logout";
	}
	
	
}