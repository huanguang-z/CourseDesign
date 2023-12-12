package com.pony.model;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Instructor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String instructor_id = "";//
	private String title = "";//
	private String logo = "";//
	private String instructor_url = "";//
	

	public String getInstructor_url() {
		return instructor_url;
	}

	public void setInstructor_url(String instructor_url) {
		this.instructor_url = instructor_url;
	}

	public String getInstructor_id() {
		return instructor_id;
	}

	public void setInstructor_id(String instructor_id) {
		this.instructor_id = instructor_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	/**
	 * 
	 * @param obj
	 * @return
	 * @throws JSONException
	 */
	public static ArrayList<Instructor> parseHotTopicDatas(Object obj)
			throws JSONException {
		ArrayList<Instructor> datas = new ArrayList<Instructor>();
		if (obj instanceof JSONArray) {
			JSONArray msgList = (JSONArray) obj;
			for (int i = 0; i < msgList.length(); i++) {
				JSONObject value = msgList.getJSONObject(i);
				Instructor bean = parseHotTopicInfo(value);
				if (bean != null) {
					datas.add(bean);
				}
			}
		} else if (obj instanceof JSONObject) {
			JSONObject json = (JSONObject) obj;
			Instructor bean = parseHotTopicInfo(json);
			if (bean != null) {
				datas.add(bean);
			}
		}
		return datas;
	}

	public static Instructor parseHotTopicInfo(JSONObject value)
			throws JSONException {
		Instructor boutique = new Instructor();
//
//		String boutiqueCid = value.optString(ConstServer.INSTRUCTOR_ID);
//		boutique.setInstructor_id(boutiqueCid);
//
//		String boutiqueLogo = value.optString(ConstServer.BLOGO);
//		boutique.setLogo(boutiqueLogo);
//
//		String boutiqueTile = value.optString(ConstServer.BTITLE);
//		boutique.setTitle(boutiqueTile);
//		
//		String instructor_url = value.optString(ConstServer.INSTRUCTOR_URL);
//		boutique.setInstructor_url(instructor_url);

		return boutique;
	}
}
