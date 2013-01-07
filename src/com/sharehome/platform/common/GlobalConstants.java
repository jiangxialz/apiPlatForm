package com.sharehome.platform.common;


public class GlobalConstants {
	
	/**
	 * 异步操作成功代码
	 */
	public static final String AJAX_SUCCESS = "200";
	
	/**
	 * 异步操作失败代码
	 */
	public static final String AJAX_FAIL = "300";
	
	/**
	 * 异步操作超时代码
	 */
	public static final String AJAX_OUT_OF_TIME = "301";
	
	/**
	 * 异步回调方法:关闭当前窗口
	 */
	public static final String AJAX_CALLBACK_CLOSE_CURRENT = "closeCurrent";
	/**
	 * 异步回调方法:重新加载页面
	 */
	public static final String AJAX_CALLBACK_RELOAD = "reload";
	
	/**
	 * 异步回调跳转
	 */
	public static final String AJAX_CALLBACK_FORWARD = "forward";
	
	public static final String SUCCESS_OPERATE = "操作成功";

	public static final String FAIL_OPERATE = "操作失败";
	
	public static final String TIMEOUT_OPERATE = "操作超时";
	
	/**
	 * 异步回调带确认询问跳转
	 */
	public static final String AJAX_CALLBACK_FORWARD_CONFIRM = "forwardConfirm";

}
