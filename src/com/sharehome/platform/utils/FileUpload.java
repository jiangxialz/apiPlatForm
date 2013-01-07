package com.sharehome.platform.utils;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.sharehome.platform.global.Constants;

public class FileUpload {
	
	private final static Logger log= Logger.getLogger(FileUpload.class);
	
	private static ConfManager cm = ConfManager.getInstance();
	
	private static final String SAVEPATH = cm.getPropValue(Constants.SAVE_PATH);
	
	/**
	 * 文件上传
	 * @param file
	 * @param request
	 */
	public static String uploadSolver(MultipartFile file, HttpServletRequest request){
		String filePath = "";
		if(null == file || file.isEmpty()){
			log.info("文件未上传");
        }else{  
//            System.out.println("文件长度: " + file.getSize());  
//            System.out.println("文件类型: " + file.getContentType());  
//            System.out.println("文件名称: " + file.getName());  
//            System.out.println("文件原名: " + file.getOriginalFilename());  
//            System.out.println("========================================");  
            String fileName = file.getOriginalFilename();
            log.info("上传的文件原始名称fileName："+fileName);
            fileName = String.valueOf(Calendar.getInstance().getTimeInMillis()) + Constants.FD + fileName;
            filePath = SAVEPATH + fileName;
            log.info("上传路径filePath:"+filePath);
            try {
				FileUtils.copyInputStreamToFile(file.getInputStream(), new File(SAVEPATH, fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
		return filePath;
	}
	
}
