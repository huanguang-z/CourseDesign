package com.student.data.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.student.data.service.HospitalService;
import com.student.data.service.ReviewService;
import com.student.jdbc.JdbcUtils;

public class HospitalDao implements HospitalService {
	private JdbcUtils jdbcUtils;

	public HospitalDao() {
		jdbcUtils = new JdbcUtils();
	}
	
	
	
	public boolean updateDepartment(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "update  departmentmsg set departmentName =?,departmentInfor =? where departmentId = ?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	
	public List<Map<String, Object>> listMesageAllNews() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from newsmessage  ";
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
	public boolean UpatdeDoctorInfor(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "update  doctormsg set doctorName =?,doctorMoney = ?, doctorMessage = ? where doctorId = ?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
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

	
	public List<Map<String, Object>> listMesageNewsDoc(List<Object> params) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from newsmessage where newsUserId = ?";
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
	
	public boolean updateNewsState(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "update  newsmessage set newsState =? where newsId = ?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}
	
	public boolean deleteDepartMessage(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "delete from departmentmsg where departmentId=?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}
	public List<Map<String, Object>> listSearchDepartJibingMessage(String searchInfor) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from departmentmsg where  departmentInfor like '%" + searchInfor + "%'";
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
	
	
	public List<Map<String, Object>> listSearchDepartKeShiMessage(String searchInfor) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from departmentmsg where  departmentName like '%" + searchInfor + "%'";
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
	
	
	public Map<String, Object> queryChuFang(List<Object> params) {
		Map<String, Object> map = null;
		String sql = "select * from diagnosistb where diagnosisApplyId=?";
		try {
			jdbcUtils.getConnection();
			map = jdbcUtils.findSimpleResult(sql, params);
			
			
			List<Object> paramsCheck = new ArrayList<Object>();
			paramsCheck.add(map.get("diagnosisDoctorId") + "");

			Map<String, Object> doctorMsg = queryDoctor(paramsCheck);
			map.put("doctorMsg", doctorMsg);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return map;
	}
	
	public List<Map<String, Object>> listUserPhoneApplyAll(List<Object> params) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from applymsg where applyState = ? and  applyMessageId= ? ";
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
	
	public boolean updateChuFang(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "update  applymsg set applyState =? where applyId = ?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}
	
	
	public boolean addChuFang(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into diagnosistb (diagnosisApplyId,diagnosisDoctorId,diagnosisInfor,diagnosisMethods,diagnosisDrug,diagnosisTime) values  (?,?,?,?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}
	

	public boolean deleteReview(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "delete from reviewtb where reviewId=?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public List<Map<String, Object>> listDoctorDepartMessage(List<Object> params) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from doctormsg where departmentId = ? ";
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


	public boolean addCZ(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into rechargetb (rechargeMoney,rechargeUserId,rechargeUserName,rechargeTime) values  (?,?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}
	
	public boolean updateCZ(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "update  user set umoney =? where uid = ?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}
	public List<Map<String, Object>> listCZPhone(String rechargeUserId) {

		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from rechargetb  where rechargeUserId = " + rechargeUserId + "  order by rechargeId desc ";
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
	
	public List<Map<String, Object>> listJiJianPCMessage(List<Object> params) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from jijiantb where jijianUserId = ?  ";
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

	public boolean addJiJian(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into jijiantb (jijianName,jijianAddresse,jijianPhone,jijianCard,jijianUserId) values  (?,?,?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}
	public List<Map<String, Object>> reviewListMessage(List<Object> params) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select reviewtb.*,user.userImage,user.uname from reviewtb  LEFT JOIN `user` on reviewtb.reviewUserId = `user`.uid where reviewMessageId = ? order by reviewId desc ";
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

	/**
	 * Ìí¼ÓÆÀÂÛ
	 * 
	 * @param params
	 * @return
	 */
	public boolean addReview(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into reviewtb (reviewMessageId,reviewContent,reviewUserId,reviewUserName,reviewTime) values  (?,?,?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}
	
	
	public List<Map<String, Object>> listMesageAllPhoneDepartment() {

		List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapResult;

		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from departmentmsg";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, null);

			for (int i = 0; i < list.size(); i++) {
				mapResult = list.get(i);

				List<Object> params = new ArrayList<Object>();
				params.add(list.get(i).get("departmentId").toString() + "");

				List<Map<String, Object>> listInfor = ListDoctorListsPCMessage(params);
				mapResult.put("doctorList", listInfor);

			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public List<Map<String, Object>> ListDoctorListsPCMessage(List<Object> params) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from doctormsg where departmentId = ? ";
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

	public List<Map<String, Object>> ListBannerPCMessage() {
		List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapResult;
		
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from bannertb";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, null);
			
			for (int i = 0; i < list.size(); i++) {
				mapResult = list.get(i);
				Map<String, Object> mapMsg = list.get(i);
				List<Object> paramsCheck = new ArrayList<Object>();
				paramsCheck.clear();
				paramsCheck.add(list.get(i).get("bannerDoctorId") + "");

				Map<String, Object> doctorMsg = queryDoctor(paramsCheck);
				mapResult.put("doctorMsg", doctorMsg);
				listResult.add(mapResult);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public boolean addBanner(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into bannertb (bannerDoctorId,bannerDoctorName,bannerImg) values  (?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public boolean deleteNews(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "delete from newsmessage where newsId=?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public boolean addNews(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into newsmessage (newsTitle,newsContent,newsTime,newsState,newsUserId,newsUserName) values  (?,?,?,?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public List<Map<String, Object>> listMesageNews() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from newsmessage where newsState= 2 ";
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

	public List<Map<String, Object>> listAppluUserPhoneNews(List<Object> params) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from applymsg where applyMessageId= ? ";
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

	public List<Map<String, Object>> listMesagePhoneNews(List<Object> params) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from newsmsg where newsUserId = ?";
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

	public boolean deleteApplyMessage(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "delete from applymsg where applyId=?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public List<Map<String, Object>> listMesagePhoneDoctor(List<Object> params) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from doctormsg where departmentId = ?";
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

	public List<Map<String, Object>> listMesagePhoneDepartment() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from departmentmsg";
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

	public boolean deleteDoctorMessage(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "delete from doctormsg where doctorId=?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public List<Map<String, Object>> listMesageDoctor() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from doctormsg";
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

	public boolean addDoctor(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into doctormsg (departmentName,doctorName,doctorMoney,doctorLevel,doctorCard"
					+ ",doctorMessage,departmentId,doctorPswd) values  (?,?,?,?,?,?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public Map<String, Object> queryDepart(List<Object> params) {
		Map<String, Object> map = null;
		String sql = "select * from departmentmsg where departmentId=?";
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

	public List<Map<String, Object>> listMesageDepartment() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from departmentmsg";
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

	public boolean addDepartment(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into departmentmsg (departmentName,departmentInfor) values  (?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public List<Map<String, Object>> listMesageHospital() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from hospitalmsg";
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

	public boolean addHospital(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into hospitalmsg (hospitalName,hospitalAddress,hospitalMessage,hospitalTime,hospitalImage) values  (?,?,?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public boolean updateScoreMessage(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "update  applymsg set applyScore =? where applyId = ?";
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
		String sql = "select * from applymsg where applyId=?";
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

	public List<Map<String, Object>> listMesagePcLoss() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from lossmsg";
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

	public List<Map<String, Object>> listMesagePcBorrow() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from applymsg";
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

	public boolean addLoss(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into lossmsg (lossUserId,lossUserName,lossTime) values  (?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public List<Map<String, Object>> listMesageBorrow(List<Object> params) {

		List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapResult;

		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from applymsg where  applyUserId = ?";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, params);

			for (int i = 0; i < list.size(); i++) {
				mapResult = list.get(i);
				Map<String, Object> mapMsg = list.get(i);
				List<Object> paramsCheck = new ArrayList<Object>();
				paramsCheck.clear();
				paramsCheck.add(list.get(i).get("applyMessageId") + "");

				Map<String, Object> doctorMsg = queryDoctor(paramsCheck);
				mapResult.put("doctorMsg", doctorMsg);
				listResult.add(mapResult);
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public Map<String, Object> queryDoctor(List<Object> params) {
		Map<String, Object> map = null;
		String sql = "select * from doctormsg where doctorId=?";
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

	public List<Map<String, Object>> listMesageBook() {

		List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapResult;

		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from experimentmsg";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, null);

			// for (int i = 0; i < list.size(); i++) {
			// mapResult = list.get(i);
			// Map<String, Object> mapMsg = list.get(i);
			// List<Object> paramsCheck = new ArrayList<Object>();
			// paramsCheck.clear();
			// paramsCheck.add(userId);
			// paramsCheck.add(list.get(i).get("bookId") + "");
			//
			// boolean flagMember = memberState(paramsCheck);
			// mapResult.put("borrowState", flagMember);
			//
			// listResult.add(mapResult);
			// }

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public boolean memberState(List<Object> params) {
		boolean flag = false;
		String sql = "select * from borrowmsg where borrowUserId=? and borrowBookId=?";
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

	public boolean addBorrow(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into applymsg (applyUserId,applyUserName,applyMessageId,applyMessageName,applyChoiceTime,applyTime,applyState) values  (?,?,?,?,?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public List<Map<String, Object>> listSearchMessage(String proname) {

		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from applymsg where (1=1) ";
		// limit ?,?
		StringBuffer buffer = new StringBuffer(sql);
		List<Object> params = new ArrayList<Object>();
		if (proname != null) {
			buffer.append(" and applyUserName like ? ");
			params.add("%" + proname + "%");
		}
		try {
			jdbcUtils.getConnection();

			System.out.println(buffer.toString());
			list = jdbcUtils.findMoreResult(buffer.toString(), params);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public boolean deleteCourseMessage(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "delete from experimentmsg where experimentId=?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public List<Map<String, Object>> listMesageCourse() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from experimentmsg";
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

	@Override
	public boolean addMessage(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into newsmsg (newsName,newsMessage,newsUserId,newsTime) values  (?,?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public List<Map<String, Object>> listNoticesMessage(List<Object> params) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from review  where  rCategoryId = ? order by rId desc ";
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

	public List<Map<String, Object>> listReviewMessage(List<Object> params) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from reviewtb where  reviewMessageId = ?";
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
