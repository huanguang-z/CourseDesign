package com.student.data.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.student.data.service.CommunityService;
import com.student.jdbc.JdbcUtils;

public class TypeDao implements CommunityService {
	private JdbcUtils jdbcUtils;

	public TypeDao() {
		jdbcUtils = new JdbcUtils();
	}

	@Override
	public boolean courseAdd(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into typemsg (typeName,typeTeaId,typeTeaName,typeCreatetime) values  (?,?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	@Override
	public List<Map<String, Object>> listCourseMessage() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from typemsg order by typeId desc";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, null);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	
	public List<Map<String, Object>> listTypeMessage(List<Object> params) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from typemsg where typeTeaId = ?";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}
	
	
	public List<Map<String, Object>> listTeacherToCourse(List<Object> params) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from typemsg where cTeacherId = ?";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}
	
	

	@Override
	public boolean deleteMessage(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "delete from typemsg where typeId=?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	
	public List<Map<String, Object>> listScoreMessageTeacher() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from teacher order by tid desc";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, null);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}
	
}
