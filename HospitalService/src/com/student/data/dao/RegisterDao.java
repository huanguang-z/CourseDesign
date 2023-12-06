package com.student.data.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.student.data.service.RegisterService;
import com.student.jdbc.JdbcUtils;

public class RegisterDao implements RegisterService {
	private JdbcUtils jdbcUtils;

	public RegisterDao() {
		jdbcUtils = new JdbcUtils();
	}
	
	
	

	public Map<String, Object> queryDoctorInfor(List<Object> params) {
		Map<String, Object> map = null;
		String sql = "select * from doctormsg where doctorId=? ";
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
	
	
	public boolean updateDocPswdPC(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "update  doctormsg set doctorPswd =? where doctorId = ?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}
	
	public Map<String, Object> queryUserInfor(List<Object> params) {
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
	
	public Map<String, Object> queryDoctor(List<Object> params) {
		Map<String, Object> map = null;
		String sql = "select * from doctormsg where doctorName=? and doctorPswd = ?";
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
	
	
	public boolean loginDoctorPcCheck(List<Object> params) {
		boolean flag = false;
		String sql = "select * from doctormsg where doctorName=? and doctorPswd = ?";
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
	
	
	public Map<String, Object> queryUset(List<Object> params) {
		Map<String, Object> map = null;
		String sql = "select * from user where uname=? and upswd = ?";
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
	
	
	public boolean loginPcCheck(List<Object> params) {
		boolean flag = false;
		String sql = "select * from user where uname=? and upswd = ?";
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
	

	public boolean updateUserInfor(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "update  user set uname = ?,uphone=?, uSex = ?,userImage=? where uid = ?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}
	
	
	public boolean updatePswd(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "update  user set upswd =? where uid = ?";
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
	
	public boolean LoginCheckPc(List<Object> params) {
		boolean flag = false;
		String sql = "select * from doctor where dPhone=? and dPswd = ?";
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

	public Map<String, Object> LoginMessage(List<Object> params) {
		Map<String, Object> map = null;
		String sql = "select * from doctor where dPhone=? and dPswd = ?";
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

	public boolean updateName(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "update  user set uname =? where uid = ?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public boolean updatePhone(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "update  user set uphone =? where uid = ?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public boolean deleteUser(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "delete from user where uid=?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public boolean resgisterPhone(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into user (uname,uphone,uSex,uCard,upswd,utime,umoney) values  (?,?,?,?,?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	@Override
	public boolean resgisterCheck(List<Object> params) {
		boolean flag = false;
		String sql = "select * from user where uname=? and upswd=?";
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

	@Override
	public boolean Login(List<Object> params) {
		boolean flag = false;
		String sql = "select * from user where uphone=? and upswd=?";
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

	@Override
	public Map<String, Object> queryOne(List<Object> params) {
		Map<String, Object> map = null;
		String sql = "select * from user where uphone=? and upswd=?";
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
	public Map<String, Object> queryId(List<Object> params) {
		Map<String, Object> map = null;
		String sql = "select uid from user where uname=? and upswd=?";
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

	
	public List<Map<String, Object>> listUserMessage() {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from user";
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
	public List<Map<String, Object>> listUser(List<Object> params) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from user";
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

	// public List<Map<String, Object>> listUser(String proname, int start, int
	// end) {
	// // TODO Auto-generated method stub
	// List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	// String sql = "select * from user where (1=1) ";
	// // limit ?,?
	// StringBuffer buffer = new StringBuffer(sql);
	// List<Object> params = new ArrayList<Object>();
	// if (proname != null) {
	// buffer.append(" and uname like ? ");
	// params.add("%" + proname + "%");
	// }
	// buffer.append("limit ?,? ");
	// params.add(start);
	// params.add(end);
	// try {
	// jdbcUtils.getConnection();
	// list = jdbcUtils.findMoreResult(buffer.toString(), params);
	// } catch (Exception e) {
	// // TODO: handle exception
	// } finally {
	// jdbcUtils.releaseConn();
	// }
	// return list;
	// }

	public int getItemCount() {
		int result = 0;
		Map<String, Object> map = null;
		String sql = " select count(*) mycount from user ";
		try {
			jdbcUtils.getConnection();
			map = jdbcUtils.findSimpleResult(sql, null);
			result = Integer.parseInt(map.get("mycount").toString());
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		// TODO Auto-generated method stub
		return result;
	}

}
