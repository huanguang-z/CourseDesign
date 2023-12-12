package com.pony.db;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.pony.app.MyApplication;

public class MemberManager {

	// Context Yoga.getInstance();

	static MemberManager manager;

	private MemberManager() {
	}

	public static MemberManager getInstance() {

		if (manager == null) {
			manager = new MemberManager();
		}

		return manager;
	}

	public boolean isLogin() {
		String sid = getUid();
		Log.i("pony_log", sid);
		if (!TextUtils.isEmpty(sid)) {
			return true;
		}
		return false;
	}

	public String getUid() {
		try {
			SharedPreferences preferences = MyApplication.getInstance().getSharedPreferences("MemberManager", 0);
			return preferences.getString("uid", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public void setUid(String uid) {
		SharedPreferences preferences = MyApplication.getInstance().getSharedPreferences("MemberManager", 0);
		preferences.edit().putString("uid", uid).commit();
	}

	
	public int getTrueScore() {
		try {
			SharedPreferences preferences = MyApplication.getInstance().getSharedPreferences("MemberManager", 0);
			return preferences.getInt("true", 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void setTrueScore(int score) {
		SharedPreferences preferences = MyApplication.getInstance().getSharedPreferences("MemberManager", 0);
		preferences.edit().putInt("true", score).commit();
	}

	public int getFalseScore() {
		try {
			SharedPreferences preferences = MyApplication.getInstance().getSharedPreferences("MemberManager", 0);
			return preferences.getInt("false", 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void setFalseScore(int score) {
		SharedPreferences preferences = MyApplication.getInstance().getSharedPreferences("MemberManager", 0);
		preferences.edit().putInt("false", score).commit();
	}

}
