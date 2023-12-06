package com.student.data.service;

import java.util.List;
import java.util.Map;

public interface RegisterService {
	public boolean resgisterPhone(List<Object> params);
	public boolean Login(List<Object> params);
	public boolean resgisterCheck(List<Object> params);
	public Map<String, Object> queryOne(List<Object> params);
	public Map<String, Object> queryId(List<Object> params);
	public List<Map<String, Object>> listUser(List<Object> params);
	public int getItemCount();
	public boolean deleteUser(List<Object> params);
	public boolean updateName(List<Object> params);
	public boolean updatePhone(List<Object> params);
}
