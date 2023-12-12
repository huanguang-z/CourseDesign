package com.student.data.service;

import java.util.List;
import java.util.Map;

public interface CommunityService {

	public boolean courseAdd(List<Object> params);

	public List<Map<String, Object>> listCourseMessage();

	public boolean deleteMessage(List<Object> params);
}
