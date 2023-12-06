package com.pony.db;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

/**
 * 
 * @author WangXuan
 * 
 *         2016-11-7
 */


public class MemberUserUtils {

	private static final String NAME = "orangeShare";

	public static boolean isLogin(Context context) {
		String sid = getUid(context);
		Log.i("pony_log", sid);
		if (!TextUtils.isEmpty(sid)) {
			return true;
		}
		return false;
	}

	
	public static String isTime(Context context) {
		String sid = getTime(context);
		Log.i("pony_log", sid);
		if (!TextUtils.isEmpty(sid)) {
			return sid;
		}
		return "";
	}
	
	
	public static SharedPreferences getSharedPreferences(Context context) {
		return context.getSharedPreferences(NAME, Context.MODE_APPEND);
	}

	
	public static String getTime(Context context) {
		try {
			SharedPreferences preferences = context.getSharedPreferences("MemberManager", 0);
			return preferences.getString("time", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static void setTime(Context context, String uid) {
		SharedPreferences preferences = context.getSharedPreferences("MemberManager", 0);
		preferences.edit().putString("time", uid).commit();
	}
	
	
	public static String getCreatMoney(Context context) {
		try {
			SharedPreferences preferences = context.getSharedPreferences("MemberManager", 0);
			return preferences.getString("Creat", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static void setCreatMoney(Context context, String name) {
		SharedPreferences preferences = context.getSharedPreferences("MemberManager", 0);
		preferences.edit().putString("Creat", name).commit();
	}
	
	public static String getIncomeMoney(Context context) {
		try {
			SharedPreferences preferences = context.getSharedPreferences("MemberManager", 0);
			return preferences.getString("Income", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static void setIncomeMoney(Context context, String name) {
		SharedPreferences preferences = context.getSharedPreferences("MemberManager", 0);
		preferences.edit().putString("Income", name).commit();
	}
	
	
	public static String getUserId(Context context) {
		try {
			SharedPreferences preferences = context.getSharedPreferences("MemberManager", 0);
			return preferences.getString("userid", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static void setUserId(Context context, String name) {
		SharedPreferences preferences = context.getSharedPreferences("MemberManager", 0);
		preferences.edit().putString("userid", name).commit();
	}
	
	public static String getName(Context context) {
		try {
			SharedPreferences preferences = context.getSharedPreferences("MemberManager", 0);
			return preferences.getString("name", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static void setName(Context context, String name) {
		SharedPreferences preferences = context.getSharedPreferences("MemberManager", 0);
		preferences.edit().putString("name", name).commit();
	}
	
	
	public static String getPswd(Context context) {
		try {
			SharedPreferences preferences = context.getSharedPreferences("MemberManager", 0);
			return preferences.getString("Pswd", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static void setPswd(Context context, String name) {
		SharedPreferences preferences = context.getSharedPreferences("MemberManager", 0);
		preferences.edit().putString("Pswd", name).commit();
	}
	
	
	
	public static String getCostMoney(Context context) {
		try {
			SharedPreferences preferences = context.getSharedPreferences("MemberManager", 0);
			return preferences.getString("cost", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static void setCostMoney(Context context, String name) {
		SharedPreferences preferences = context.getSharedPreferences("MemberManager", 0);
		preferences.edit().putString("cost", name).commit();
	}
	
	
	
	public static String getMoney(Context context) {
		try {
			SharedPreferences preferences = context.getSharedPreferences("MemberManager", 0);
			return preferences.getString("money", "0");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static void setMoney(Context context, String name) {
		SharedPreferences preferences = context.getSharedPreferences("MemberManager", 0);
		preferences.edit().putString("money", name).commit();
	}
	
	public static String getUid(Context context) {
		try {
			SharedPreferences preferences = context.getSharedPreferences("MemberManager", 0);
			return preferences.getString("uid", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static void setUid(Context context, String uid) {
		SharedPreferences preferences = context.getSharedPreferences("MemberManager", 0);
		preferences.edit().putString("uid", uid).commit();
	}

	
	public static int getTrueScore(Context context) {
		try {
			SharedPreferences preferences = context.getSharedPreferences("MemberManagerscoreTrue", 0);
			return preferences.getInt("scoreTrue", 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static void setTrueScore(Context context,int score) {
		Log.e("pony_log", score+"");
		SharedPreferences preferences = context.getSharedPreferences("MemberManagerscoreTrue", 0);
		preferences.edit().putInt("scoreTrue", score).commit();
	}

	public static int getFalseScore(Context context) {
		try {
			SharedPreferences preferences = context.getSharedPreferences("MemberManagerscoreFalse", 0);
			return preferences.getInt("scorefalse", 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public  static void setFalseScore(Context context,int score) {
		SharedPreferences preferences = context.getSharedPreferences("MemberManagerscoreFalse", 0);
		preferences.edit().putInt("scorefalse", score).commit();
	}

	
	public static void putBean(Context context, String key, Object obj) {
		if (obj instanceof Serializable) {
			try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(baos);
				oos.writeObject(obj);
				String string64 = new String(Base64.encode(baos.toByteArray(), 0));
				Editor editor = getSharedPreferences(context).edit();
				editor.putString(key, string64);
				editor.commit();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			throw new IllegalArgumentException("the obj must implement Serializble");
		}

	}

	public static Object getBean(Context context, String key) {
		Object obj = null;
		try {
			String base64 = getSharedPreferences(context).getString(key, "");
			if (base64.equals("")) {
				return null;
			}
			byte[] base64Bytes = Base64.decode(base64.getBytes(), 1);
			ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			obj = ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}


}
