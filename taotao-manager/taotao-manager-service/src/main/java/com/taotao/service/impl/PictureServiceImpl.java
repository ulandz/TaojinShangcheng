package com.taotao.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.utils.FtpUtil;
import com.taotao.common.utils.IDUtils;
import com.taotao.service.PictureService;

/**
 * 图片上传服务
 * @author zhc
 *
 */

@Service
public class PictureServiceImpl implements PictureService {

	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	@Value("${FTP_PATH}")
	private String FTP_PATH;
	@Value("${FTP_IMAGE_BASE_URL}")
	private String FTP_IMAGE_BASE_URL;
	
	
	@Override
	public Map uploadPicture(MultipartFile uploadFile) {
		HashMap<Object, Object> resultMap = new HashMap<>();
		if(uploadFile == null || uploadFile.isEmpty()) {
			resultMap.put("error", 1);
			resultMap.put("message", "上传图片为空");
			return resultMap;
		}
		//生成新文件名
		String oldName = uploadFile.getOriginalFilename();
		//可以使用UUID生成
		//在这里使用自定义的工具类生成
		//时间+随机数生成文件名
		String newName = IDUtils.genImageName();
		newName = newName + oldName.substring(oldName.lastIndexOf("."));
		DateTime dateTime = new DateTime();
		String filePath = dateTime.toString("yyyy/MM/dd");
		try {
			boolean result = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_PATH, 
					filePath, newName, uploadFile.getInputStream());
			if(!result) {
				resultMap.put("error", 1);
				resultMap.put("message", "上传图片失败");
				return resultMap;
			}
			resultMap.put("error", 0);
			resultMap.put("url", FTP_IMAGE_BASE_URL + filePath + "/" + newName);
			return resultMap;
		} catch (IOException e) {
			e.printStackTrace();
			resultMap.put("error", 1);
			resultMap.put("message", "图片上传发生异常");
			return resultMap;
		}
	}

}
