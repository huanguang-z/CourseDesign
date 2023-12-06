package com.student.data.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.student.data.service.OrderService;
import com.student.jdbc.JdbcUtils;

public class OrderDao implements OrderService {
	private JdbcUtils jdbcUtils;

	public OrderDao() {
		jdbcUtils = new JdbcUtils();
	}
	public List<Map<String, Object>> listMesageUser(List<Object> params) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from review where  rUserId = ?";
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
	public boolean updateMessage(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "update  review set rReplyContent =? where rid = ?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}
	public boolean updateMoney(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "update  user set uMoney =? where uid = ?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}
	public Map<String, Object> queryUser(List<Object> params) {
		Map<String, Object> map = null;
		String sql = "select * from user where uid=?";
		try {
			jdbcUtils.getConnection();
			map = jdbcUtils.findSimpleResult(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return map;
	}
	
	
	
	public Map<String, Object> queryCourse(List<Object> params) {
		Map<String, Object> map = null;
		String sql = "select * from coursemsg where courseId=?";
		try {
			jdbcUtils.getConnection();
			map = jdbcUtils.findSimpleResult(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return map;
	}
	
	@Override
	public boolean addMessage(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into ordermsg (orderMessageId,orderUserId,orderUserName,orderTime) values  (?,?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}
	
	
	public List<Map<String, Object>> listNoticesMessage(List<Object> params) {
		
		
		List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapResult;
		
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from ordermsg  where  orderUserId = ?";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, params);
			
			
			for (int i = 0; i < list.size(); i++) {
				mapResult = list.get(i);
				Map<String, Object> mapMsg = list.get(i);
				List<Object> paramsCheck = new ArrayList<Object>();
				paramsCheck.clear();
				paramsCheck.add(list.get(i).get("orderMessageId") + "");
				
				Map<String, Object> flagMember = queryCourse(paramsCheck);
				mapResult.put("coursemsg", flagMember);

				
				listResult.add(mapResult);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}
	
	public List<Map<String, Object>> listReviewMessage(List<Object> params) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from review where  rNoticeId = ?";
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
	public List<Map<String, Object>> listMessage() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from review";
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

	@Override
	public boolean deleteMessage(List<Object> params) {
		// TODO Auto-generated method stub
		return false;
	}
}
