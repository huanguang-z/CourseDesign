package com.student.data.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.student.data.service.CommunityService;
import com.student.jdbc.JdbcUtils;

public class CommunityDao implements CommunityService {
	private JdbcUtils jdbcUtils;

	public CommunityDao() {
		jdbcUtils = new JdbcUtils();
	}
	
	public List<Map<String, Object>> listSearchCommunity(String proname,String uid) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapResult;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from category where (1=1) ";
		// limit ?,?
		StringBuffer buffer = new StringBuffer(sql);
		List<Object> params = new ArrayList<Object>();
		if (proname != null) {
			buffer.append(" and cName like ? ");
			params.add("%" + proname + "%");
		}
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(buffer.toString(), params);
			
			
			for (int i = 0; i < list.size(); i++) {
				mapResult = list.get(i);
				Map<String, Object> mapMsg = list.get(i);
				List<Object> paramsCheck = new ArrayList<Object>();
				paramsCheck.clear();
				paramsCheck.add(uid);
				paramsCheck.add(list.get(i).get("cId") + "");
				boolean flagcollect = collectState(paramsCheck);
				mapResult.put("flagcollect", flagcollect);

				
				boolean flagMember = memberState(paramsCheck);
				mapResult.put("flagMember", flagMember);

				
				
				List<Object> paramsMember = new ArrayList<Object>();
				paramsMember.clear();
				paramsMember.add(list.get(i).get("cId") + "");
				
				List<Map<String, Object>> communityMember = listMember(paramsMember);
				mapResult.put("communityMember", communityMember);
				
				
				listResult.add(mapResult);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return listResult;
	}
	public boolean updateCommunity(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "update  category set cUserName =?, cMessage =? where cId = ?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}
	
	
	public boolean deleteCollect(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "delete from collect where cCategoryId=? and cUserId = ?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}
	
	public boolean addCollect(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into collect (cUserId,cUserName,cCategoryId,cCategoryName) values  (?,?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}
	public boolean addCommunityCategory(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into CommunityCategory (ccName,ccCreateTime) values  (?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}
	public boolean addCategory(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into category (cName,cMessage,cCreateTime,cUserId,cUserName,cCCId) values  (?,?,?,?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}
	
	
	public boolean updateNumber(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "update  scoopBottleNumber set sbNumber =?,sbCreatime = ? where sbId = ?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	
	public boolean addNumber(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into scoopBottleNumber (sbUserId,sbUserName,sbNumber,sbCreatime) values  (?,?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}
	
	public boolean addCheck(List<Object> params) {
		boolean flag = false;
		String sql = "select * from scoopBottleNumber where sbUserId=?";
		try {
			jdbcUtils.getConnection();
			Map<String, Object> map = jdbcUtils.findSimpleResult(sql, params);
			flag = !map.isEmpty() ? true : false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public Map<String, Object> queryCheck(List<Object> params) {
		Map<String, Object> map = null;
		String sql = "select * from scoopBottleNumber where sbUserId=?";
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
	public boolean courseAdd(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into bottle (bContent,bUserId,bUserName,bType,bCreatime) values  (?,?,?,?,?)";
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
		String sql = "select * from course order by cid desc";
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
	
	public List<Map<String, Object>> listMesageUser(List<Object> params) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from bottle where bUserId = ?";
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
		String sql = "select * from bottle where bUserId != ?";
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
	public boolean deleteCategoryComunity(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "delete from CommunityCategory where ccId=?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}
	public boolean deleteCategory(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "delete from category where cId=?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	@Override
	public boolean deleteMessage(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "delete from course where cid=?";
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
	
	public List<Map<String, Object>> listMessageCollect(List<Object> params) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from collect  where cUserId = ? order by cId desc";
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
	
	public List<Map<String, Object>> listMessageMyNotice(List<Object> params) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from notice where nUserId = ? order by nId desc";
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
	public List<Map<String, Object>> listMessageNotice(List<Object> params) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from notice where nCategoryId = ? order by nId desc";
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
	public List<Map<String, Object>> listMessageMyCommunity(List<Object> params) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from category where cUserId = ? order by cId desc";
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
	
	
	
	public boolean memberState(List<Object> params) {
		boolean flag = false;
		String sql = "select * from members where mUserId=? and mCategoryId=?";
		try {
			jdbcUtils.getConnection();
			Map<String, Object> map = jdbcUtils.findSimpleResult(sql, params);
			flag = !map.isEmpty() ? true : false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}
	
	
	public boolean collectState(List<Object> params) {
		boolean flag = false;
		String sql = "select * from collect where cUserId=? and cCategoryId=?";
		try {
			jdbcUtils.getConnection();
			Map<String, Object> map = jdbcUtils.findSimpleResult(sql, params);
			flag = !map.isEmpty() ? true : false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}
	
	public List<Map<String, Object>> listMessageCategory(String uid) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapResult;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from category order by cId desc";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, null);
			
			for (int i = 0; i < list.size(); i++) {
				mapResult = list.get(i);
				Map<String, Object> mapMsg = list.get(i);
				List<Object> paramsCheck = new ArrayList<Object>();
				paramsCheck.clear();
				paramsCheck.add(uid);
				paramsCheck.add(list.get(i).get("cId") + "");
				boolean flagcollect = collectState(paramsCheck);
				mapResult.put("flagcollect", flagcollect);

				boolean flagMember = memberState(paramsCheck);
				mapResult.put("flagMember", flagMember);

				
				
				List<Object> paramsMember = new ArrayList<Object>();
				paramsMember.clear();
				paramsMember.add(list.get(i).get("cId") + "");
				
				List<Map<String, Object>> communityMember = listMember(paramsMember);
				mapResult.put("communityMember", communityMember);
				listResult.add(mapResult);
			}

			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}
	
	
	public List<Map<String, Object>> listMessageQueryCategoryCommunity(List<Object> params ,String uid) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapResult;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from category where cCCId = ?";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, params);
			
			for (int i = 0; i < list.size(); i++) {
				mapResult = list.get(i);
				Map<String, Object> mapMsg = list.get(i);
				List<Object> paramsCheck = new ArrayList<Object>();
				paramsCheck.clear();
				paramsCheck.add(uid);
				paramsCheck.add(list.get(i).get("cId") + "");
				boolean flagcollect = collectState(paramsCheck);
				mapResult.put("flagcollect", flagcollect);

				boolean flagMember = memberState(paramsCheck);
				mapResult.put("flagMember", flagMember);

				
				
				List<Object> paramsMember = new ArrayList<Object>();
				paramsMember.clear();
				paramsMember.add(list.get(i).get("cId") + "");
				
				List<Map<String, Object>> communityMember = listMember(paramsMember);
				mapResult.put("communityMember", communityMember);
				listResult.add(mapResult);
			}

			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return listResult;
	}
	
	
	public List<Map<String, Object>> listMessageCategoryCommunityPc() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapResult;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from CommunityCategory order by ccId desc";
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
	public List<Map<String, Object>> listMessageCategoryCommunityPcPhone() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapResult;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from CommunityCategory";
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
	public List<Map<String, Object>> listMessageCategoryPc() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapResult;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from category order by cId desc";
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
	
	public List<Map<String, Object>> listMember(List<Object> params) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from members where mCategoryId = ?";
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
}
