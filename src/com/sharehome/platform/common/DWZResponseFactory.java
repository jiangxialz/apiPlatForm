package com.sharehome.platform.common;

import com.sharehome.platform.common.DWZResponser;

public class DWZResponseFactory {
	
	private DWZResponseFactory(){};
	
	public static DWZResponser create(){
		return new DWZResponser();
	}
	
	public static DWZResponser create(String statusCode,String callbackType,String message){
		return new DWZResponser(statusCode,callbackType,message);
	}
	
}
