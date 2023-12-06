package com.pony.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class FileUtils {
	public static boolean mkdirsIfNotExit(File f) {
		if (f == null) {
			return false;
		}
		if (!f.exists()) {
			synchronized (FileUtils.class) {
				return f.mkdirs();
			}
		}
		return true;
	}

	public static boolean mkdirsJust(File f) {
		synchronized (FileUtils.class) {
			return f.mkdirs();
		}
	}
	
	public static void recursionDeleteFile(File file){
        if(file.isFile()){
            file.delete();
            return;
        }
        if(file.isDirectory()){
            File[] childFile = file.listFiles();
            if(childFile == null || childFile.length == 0){
                file.delete();
                return;
            }
            for(File f : childFile){
            	recursionDeleteFile(f);
            }
            file.delete();
        }
    }

	public static boolean createFile(File file) {
		boolean createOk = false;
		if (!FileUtils.mkdirsIfNotExit(file.getParentFile())) {
			return false;
		}
		if (!file.exists()) {
			try {
				file.createNewFile();
				createOk = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return createOk;
	}

	/**
	 * 删锟斤拷锟侥硷拷,锟斤拷锟斤拷锟侥硷拷锟斤拷
	 * 
	 * @param file
	 * @return
	 */
	public static boolean clear(File f) {
		if (f == null) {
			return false;
		}
		if (f.exists()) {
			if (!f.isDirectory()) {
				return forceDeleteFile(f);
			} else {
				boolean ret = true;
				try {
					File[] files = f.listFiles();
					for (int i = 0; i < files.length; i++) {
						Thread.sleep(1);
						if (files[i].isDirectory()) {
							if (!clear(files[i])) {
								// 只要失锟杰撅拷return false
								return false;
							}
						} else {
							if (!forceDeleteFile(files[i])) {
								ret = false;
								break;
							}
						}
					}
					final File to = new File(f.getAbsolutePath()
							+ System.currentTimeMillis());
					f.renameTo(to);
					forceDeleteFile(to);// 删锟斤拷锟斤拷锟侥硷拷锟斤拷
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
				return ret;
			}
		} else {
			return true;
		}
	}

	public static boolean forceDeleteFile(File file) {
		if (file.exists()) {
			boolean result = false;
			int tryCount = 0;
			while (!result && tryCount++ < 10) {
				result = file.delete();
				if (!result) {
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
					}
				}
			}
			// Log.v("FileUtil.forceDeleteFile", "tryCount = " + tryCount);
			return result;
		} else {
			return true;
		}
	}
	/** 
	* 锟狡讹拷锟侥硷拷 
	* @param srcFileName 	源锟侥硷拷锟斤拷锟斤拷路锟斤拷
	* @param destDirName 	目锟斤拷目录锟斤拷锟斤拷路锟斤拷
	* @return 锟侥硷拷锟狡讹拷锟缴癸拷锟斤拷锟斤拷true锟斤拷锟斤拷锟津返伙拷false 
	*/  
	public static boolean moveFile(String srcFileName, String destDirName) {
		
		File srcFile = new File(srcFileName);
		if(!srcFile.exists() || !srcFile.isFile()) 
		    return false;
		
		File destDir = new File(destDirName);
		if (!destDir.exists())
			destDir.mkdirs();
		
		return srcFile.renameTo(new File(destDirName + File.separator + srcFile.getName()));
	}
	/** 
	* 锟狡讹拷目录 
	* @param srcDirName 	源目录锟斤拷锟斤拷路锟斤拷
	* @param destDirName 	目锟斤拷目录锟斤拷锟斤拷路锟斤拷
	* @return 目录锟狡讹拷锟缴癸拷锟斤拷锟斤拷true锟斤拷锟斤拷锟津返伙拷false 
	*/  
	public static  boolean moveDirectory(String srcDirName, String destDirName) {
		
		File srcDir = new File(srcDirName);
		if(!srcDir.exists() || !srcDir.isDirectory())  
			return false;  
	   
	   File destDir = new File(destDirName);
	   if(!destDir.exists())
		   destDir.mkdirs();
	   
	   File[] sourceFiles = srcDir.listFiles();
	   for (File sourceFile : sourceFiles) {
		   if (sourceFile.isFile())
			   moveFile(sourceFile.getAbsolutePath(), destDir.getAbsolutePath());
		   else if (sourceFile.isDirectory())
			   moveDirectory(sourceFile.getAbsolutePath(), 
					   destDir.getAbsolutePath() + File.separator + sourceFile.getName());
		   else
			   ;
	   }
	   return srcDir.delete();
	}
	
	public static String showNowTime(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long times = System.currentTimeMillis();
		Date date = new Date(times);
		String tim = sdf.format(date);
		return tim; 
	}
	
	public static String showTime(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		long times = System.currentTimeMillis();
		Date date = new Date(times);
		String tim = sdf.format(date);
		return tim; 
	}
	
	
	
}
