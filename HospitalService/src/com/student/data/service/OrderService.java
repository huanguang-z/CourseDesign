package com.student.data.service;

import java.util.List;
import java.util.Map;

public interface OrderService {

	public boolean addMessage(List<Object> params);

	public List<Map<String, Object>> listMessage();

	
	public boolean deleteMessage(List<Object> params);
}
