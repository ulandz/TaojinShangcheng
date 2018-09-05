package com.taotao.contoller;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

public class FTPTest {

	@Test
	public void testFtpClient() throws Exception{
		//创建一个FtpClient对象
		FTPClient ftpClient = new FTPClient();
		//创建ftp连接，默认21端口
		ftpClient.connect("192.168.117.132", 21);
		//登陆ftp服务器，使用用户名和密码
		ftpClient.login("ftpuser", "ftpuser");
		//上传文件
		//读取本地文件
		FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\Administrator\\Pictures\\6e1de60ce43d4bf4b9671d7661024e7a.jpg"));
		//设置上传模式为被动模式
		ftpClient.enterLocalPassiveMode();
		//设置上传的路径
		ftpClient.changeWorkingDirectory("/home/ftpuser/image");
		//设置文件上传格式
		ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE);
		//第一个参数：服务端文件名称
		//第二个参数：文件的io流
		ftpClient.storeFile("2.jpg", inputStream);
		//关闭连接
		ftpClient.logout();
	}
}
