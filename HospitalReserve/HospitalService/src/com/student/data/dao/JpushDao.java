package com.student.data.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.student.data.service.JpushService;
import com.student.jdbc.JdbcUtils;

public class JpushDao implements JpushService {
	private JdbcUtils jdbcUtils;

	public JpushDao() {
		jdbcUtils = new JdbcUtils();
	}

	public boolean deleteMessage(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "delete from jpushmsg where jpushId=?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}
	
	public boolean jpushAdd(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into jpushmsg (jpushName,jpushMessage,jpushTime) values  (?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	

	public List<Map<String, Object>> listMessage() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from jpushmsg order by jpushId desc";
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
