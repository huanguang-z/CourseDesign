package com.pony.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FTPUtil {

	private String serverIp = "113.140.71.254";
	private int ftp_port = 21;
	private String ftp_user = "ftpuser";
	private String ftp_password = "ASDFqwer1234";

	private FTPUtil() {
		// TODO Auto-generated constructor stub
	}

	private static FTPUtil ftp;

	public static FTPUtil getInstance() {
		if (null == ftp)
			ftp = new FTPUtil();
		return ftp;
	}

	// 设置上传目录
	// ftpClient.changeWorkingDirectory("/admin/pic" );
	// ftpClient.setBufferSize(1024);
	// ftpClient.setControlEncoding("GBK" );
	// FTPUtil.getInstance().ftpUpload(DTRMApplication.IMAGEPATH, "//");

	public boolean ftpUpload(String localPath, String pathname) throws Exception {
		boolean success = false;
		FTPClient ftp = new FTPClient();

		try {
			ftp.connect(serverIp, ftp_port);// 连接FTP服务器
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			boolean loginResult = ftp.login(ftp_user, ftp_password);// 登录
			int reply = ftp.getReplyCode();
			if (loginResult && FTPReply.isPositiveCompletion(reply)) // 如果登陆成功
			{
				ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
				ftp.makeDirectory(pathname);
				boolean change = ftp.changeWorkingDirectory(pathname);// 设置上传目录
				if (change) {
					File file = new File(localPath);
					InputStream input = new FileInputStream(file);;
					ftp.storeFile(file.getName(), input);
					input.close();
//					File dir = new File(localPath);
//					File files[] = dir.listFiles();
//					InputStream input;
//					if (dir.isDirectory()) {
//						for (int i = 0; i < files.length; i++) {
//							try {
//								input = new FileInputStream(files[i]);
//								ftp.storeFile(imgName, input);
//							} catch (FileNotFoundException e) {
//								e.printStackTrace();
//							}
//						}
//					}
					//
					// for (File f : file.listFiles()) {
					// InputStream input = new FileInputStream(f);
					// ftp.storeFile(f.getName(), input);
					// input.close();
					// f.delete();
					// }

				}
				ftp.logout();
				success = true;
			} else {
				success = false;
			}
		} finally {
			if (ftp.isConnected()) {
				ftp.disconnect();
			}
		}
		return success;
	}

	public static final String DEFAULT_CONTROL_ENCODING = "ISO-8859-1";

	public boolean ftpUpload1(String fileNamePath, String remotePath) throws Exception {
		FTPClient ftpClient = new FTPClient();
		FileInputStream fis = null;

		try {
			ftpClient.connect(serverIp, ftp_port);
			boolean loginResult = ftpClient.login(ftp_user, ftp_password);
			int returnCode = ftpClient.getReplyCode();
			if (loginResult && FTPReply.isPositiveCompletion(returnCode)) // 如果登陆成功
			{

				ftpClient.makeDirectory(remotePath);
				boolean change = ftpClient.changeWorkingDirectory(remotePath);// 设置上传目录
				if (change) {
					ftpClient.setBufferSize(1024);
					ftpClient.setControlEncoding("UTF-8");
					ftpClient.enterRemotePassiveMode();
					ftpClient.enterLocalPassiveMode();
					// 获取所有的错误日志进行上传，以防有的文件第一次没有上传成功
					File folder = new File(fileNamePath);
					String[] fileNames = folder.list();
					for (int i = 0; i < fileNames.length; i++) {
						File file = new File(fileNamePath + fileNames[i]);
						if (file.exists()) {
							fis = new FileInputStream(file);
							boolean result = ftpClient.storeFile(new String(fileNames[i].getBytes("UTF-8"), "iso-8859-1"), fis);
							if (result) // 上传成功,删除手机本地的文件
							{
								// file.delete();
							}
						}
					}
				}
			}
		} finally {

			ftpClient.disconnect();

		}
		return true;
	}
}
