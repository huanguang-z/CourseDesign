package com.pony.config;

import android.os.Environment;

/**
 * 
 *
 */
public class Consts {

	/**
	 * 请求超时
	 */
	public static int TIME_OUT = 30000;


	public static String[] typeArr = new String[]{"全部","专家","普通"};
	public static String[] timeArr = new String[]{"星期一","星期二","星期三","星期四","星期五","星期六","星期日"};

	public static String[] timeChoiceArr = new String[]{"08:30-09:30","08:90-10:30","10:30-11:30","14:00-15:00","15:00-16:00","16:00-17:00"};

	/**
	 * 访问地址(wxh)
	 */
	public final static String URL = "http://100.65.101.28:8080/HospitalService/";

	public final static String URL_IMAGE = "http://100.65.101.28:8080/HospitalService/upload/";
	


	public static class APP {

		/**
		 * 登录
		 */
		public static final String HospitalAction = "servlet/HospitalAction";
		public static final String RegisterAction = "servlet/RegisterAction";
		public static final String JobAction = "servlet/JobAction";

	}

	public static class actionId {
		/**
		 * 更多 帮助
		 */
		public static final int resultFlag = 1;
		public static final int resultCode = 2;
		public static final int resultMsg = 3;
		public static final int resultState = 4;
		public static final int resultFive = 5;
		public static final int resultSix = 6;
	}
}
