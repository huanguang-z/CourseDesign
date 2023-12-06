package com.student.data.service;

import java.util.List;
import java.util.Map;

public interface ReviewService {

	public boolean addMessage(List<Object> params);

	public List<Map<String, Object>> listMessage();

	public List<Map<String, Object>> listMessageTeacher(List<Object> params);
	
	public boolean deleteMessage(List<Object> params);
}
