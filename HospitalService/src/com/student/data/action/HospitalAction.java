package com.student.data.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.student.config.Consts;
import com.student.data.dao.HospitalDao;
import com.student.util.PingYinUtil;

public class HospitalAction extends HttpServlet {

	private HospitalDao membersDao;

	public HospitalAction() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path = request.getContextPath();
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		String action_flag = request.getParameter("action_flag");
		if (action_flag.equals("addMessage")) {
			addMessage(request, response);
		} else if (action_flag.equals("listMessage")) {
			listMessage(request, response);
		} else if (action_flag.equals("listNoticesMessage")) {
			listNoticesMessage(request, response);
		} else if (action_flag.equals("updateReview")) {
			updateReview(request, response);
		} else if (action_flag.equals("deleteMessage")) {
			deleteMessage(request, response);
		} else if (action_flag.equals("listSearchMessage")) {
			listSearchMessage(request, response);
		} else if (action_flag.equals("addBorrow")) {
			addBorrow(request, response);
		} else if (action_flag.equals("listMesageBook")) {
			listBookMessage(request, response);
		} else if (action_flag.equals("listMesageBorrow")) {
			listMesageBorrow(request, response);
		} else if (action_flag.equals("addLoss")) {
			addLoss(request, response);
		} else if (action_flag.equals("listPcBorrowMessage")) {
			listPcBorrowMessage(request, response);
		} else if (action_flag.equals("listPcLossMessage")) {
			listPcLossMessage(request, response);
		} else if (action_flag.equals("queryScore")) {
			queryScore(request, response);
		} else if (action_flag.equals("addScore")) {
			addScore(request, response);
		} else if (action_flag.equals("addHospital")) {
			addHospital(request, response);
		} else if (action_flag.equals("listHopitalMessage")) {
			listHopitalMessage(request, response);
		} else if (action_flag.equals("listChoiceHopital")) {
			listChoiceHopital(request, response);
		} else if (action_flag.equals("addDepartment")) {
			addDepartment(request, response);
		} else if (action_flag.equals("listDepartmentMessage")) {
			listDepartmentMessage(request, response);
		} else if (action_flag.equals("queryDepart")) {
			queryDepart(request, response);
		} else if (action_flag.equals("addDoctor")) {
			addDoctor(request, response);
		} else if (action_flag.equals("listDoctorMessage")) {
			listDoctorMessage(request, response);
		} else if (action_flag.equals("deleteDoctorMessage")) {
			deleteDoctorMessage(request, response);

		}

		else if (action_flag.equals("listHopitalPhoenMessage")) {
			listHopitalPhoenMessage(request, response);
		} else if (action_flag.equals("listMesagePhoneDepartment")) {
			listMesagePhoneDepartment(request, response);
		} else if (action_flag.equals("listMesagePhoneDoctor")) {
			listMesagePhoneDoctor(request, response);
		} else if (action_flag.equals("deleteApplayMessage")) {
			deleteApplayMessage(request, response);
		} else if (action_flag.equals("listMessageNews")) {
			listMessageNews(request, response);
		} else if (action_flag.equals("listMessagePhoneNews")) {
			listMessagePhoneNews(request, response);
		} else if (action_flag.equals("listAppluUserPhoneNews")) {
			listAppluUserPhoneNews(request, response);
		} else if (action_flag.equals("addNotice")) {
			addNotice(request, response);
		} else if (action_flag.equals("deleteNews")) {
			deleteNews(request, response);
		} else if (action_flag.equals("listChoiceDoctorMessage")) {
			listChoiceDoctorMessage(request, response);
		} else if (action_flag.equals("addBannerPC")) {
			addBannerPC(request, response);
		} else if (action_flag.equals("ListBannerPCMessage")) {
			ListBannerPCMessage(request, response);
		} else if (action_flag.equals("listMesageAllPhoneDepartment")) {
			listMesageAllPhoneDepartment(request, response);
		} else if (action_flag.equals("addJiJian")) {
			addJiJian(request, response);
		} else if (action_flag.equals("listJiJianPCMessage")) {
			listJiJianPCMessage(request, response);
		} else if (action_flag.equals("addReview")) {
			addReview(request, response);
		} else if (action_flag.equals("reviewListMessage")) {
			reviewListMessage(request, response);
		} else if (action_flag.equals("listCZPhone")) {
			listCZPhone(request, response);
		} else if (action_flag.equals("updateCZ")) {
			updateCZ(request, response);
		} else if (action_flag.equals("updatePayCZ")) {
			updatePayCZ(request, response);
		} else if (action_flag.equals("listDoctorDepartMessage")) {
			listDoctorDepartMessage(request, response);
		} else if (action_flag.equals("listDoctorReviewMessage")) {
			listDoctorReviewMessage(request, response);
		} else if (action_flag.equals("listDoctorReviewAdminMessage")) {
			listDoctorReviewAdminMessage(request, response);
		} else if (action_flag.equals("deleteReview")) {
			deleteReview(request, response);
		} else if (action_flag.equals("listAppluUserDoctorPhone")) {
			listAppluUserDoctorPhone(request, response);
		} else if (action_flag.equals("addChuFang")) {
			addChuFang(request, response);
		} else if (action_flag.equals("queryChuFang")) {
			queryChuFang(request, response);
		} else if (action_flag.equals("deleteDepartMessage")) {
			deleteDepartMessage(request, response);

		} else if (action_flag.equals("listMessageAdminNews")) {
			listMessageAdminNews(request, response);
		} else if (action_flag.equals("updateNewsState")) {
			updateNewsState(request, response);
		} else if (action_flag.equals("queryDoctorInfor")) {
			queryDoctorInfor(request, response);
		}else if (action_flag.equals("UpatdeDoctorInfor")) {
			UpatdeDoctorInfor(request, response);
		}else if (action_flag.equals("queryChuFangPhone")) {
			queryChuFangPhone(request, response);
		}  else if (action_flag.equals("updateDepartment")) {
			updateDepartment(request, response);
		} 

	}

	public void init() throws ServletException {

		membersDao = new HospitalDao();
	}
	private void updateDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		String departmentName = request.getParameter("departmentName");
		String departmentInfor = request.getParameter("departmentInfor");
		String departmentId = request.getParameter("departmentId");
		
		List<Object> params = new ArrayList<Object>();
		params.add(departmentName);
		params.add(departmentInfor);
		params.add(departmentId);
		boolean flag = membersDao.updateDepartment(params);
		if (flag) {
			listDepartmentMessage(request, response);
		}

	}
	
	private void queryChuFangPhone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String applyId = request.getParameter("applyId");
		List<Object> params = new ArrayList<Object>();
		params.add(applyId);
		Map<String, Object> list = membersDao.queryChuFang(params);
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}
	private void UpatdeDoctorInfor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userId = request.getParameter("userId");
		String doctorName = request.getParameter("doctorName");
		String doctorMoney = request.getParameter("doctorMoney");
		String doctorMessage = request.getParameter("doctorMessage");

		List<Object> params = new ArrayList<Object>();
		params.add(doctorName);
		params.add(doctorMoney);
		params.add(doctorMessage);
		params.add(userId);
		boolean flag = membersDao.UpatdeDoctorInfor(params);
		System.out.println(flag);
		if (flag) {
			PrintWriter out = response.getWriter(); // 初始化out对象
			out.print("<script language='javascript'>alert('履历修改成功');window.location.href='../formPswd.jsp';</script>");
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "修改失败");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void queryDoctorInfor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String userId = request.getParameter("userId");
		List<Object> params = new ArrayList<Object>();
		params.add(userId);

		Map<String, Object> list = membersDao.queryDoctorInfor(params);
		request.setAttribute("mapmsg", list);
		request.getRequestDispatcher("../updateDoctor.jsp").forward(request, response);

	}

	private void updateNewsState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String newsId = request.getParameter("newsId");
		String newsState = request.getParameter("newsState");

		List<Object> params = new ArrayList<Object>();
		params.add(newsState);
		params.add(newsId);
		boolean flag = membersDao.updateNewsState(params);
		if (flag) {
			listMessageAdminNews(request, response);
		}

	}

	private void listMessageAdminNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		List<Map<String, Object>> list = membersDao.listMesageAllNews();
		// studentDao.listMessageTeacher(list);
		request.setAttribute("listMessage", list);
		request.getRequestDispatcher("../newsAdminMessage.jsp").forward(request, response);

	}

	private void deleteDepartMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String departmentId = request.getParameter("departmentId");
		List<Object> params = new ArrayList<Object>();
		params.add(departmentId);
		boolean flag = membersDao.deleteDepartMessage(params);
		if (flag) {
			listDepartmentMessage(request, response);
		} else {
			System.out.println("失败了");
		}

	}

	private void queryChuFang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String applyId = request.getParameter("applyId");
		List<Object> params = new ArrayList<Object>();
		params.add(applyId);
		Map<String, Object> list = membersDao.queryChuFang(params);
		request.setAttribute("mapmsg", list);
		request.getRequestDispatcher("../chufangInfor.jsp").forward(request, response);

	}

	private void addChuFang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String diagnosisApplyId = request.getParameter("diagnosisApplyId");
		String diagnosisDoctorId = request.getParameter("diagnosisDoctorId");
		String diagnosisInfor = request.getParameter("diagnosisInfor");
		String diagnosisMethods = request.getParameter("diagnosisMethods");
		String diagnosisDrug = request.getParameter("diagnosisDrug");

		List<Object> params = new ArrayList<Object>();
		params.add(diagnosisApplyId);
		params.add(diagnosisDoctorId);
		params.add(diagnosisInfor);
		params.add(diagnosisMethods);
		params.add(diagnosisDrug);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
		params.add(df.format(new Date()));
		boolean flag = membersDao.addChuFang(params);
		if (flag) {

			List<Object> paramsUpdate = new ArrayList<Object>();
			paramsUpdate.add("2");
			paramsUpdate.add(diagnosisApplyId);
			boolean flagUpdate = membersDao.updateChuFang(paramsUpdate);
			if (flagUpdate) {
				List<Object> paramsLook = new ArrayList<Object>();
				params.add("1");
				paramsLook.add(diagnosisDoctorId);
				List<Map<String, Object>> list = membersDao.listUserPhoneApplyAll(paramsLook);
				request.setAttribute("mapmsg", list);
				request.getRequestDispatcher("../appointmentDocMessage.jsp").forward(request, response);

			}

		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "添加失败");
			jsonmsg.put("repCode", "111");
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
			System.out.println(jsonmsg);
		}

	}

	private void listAppluUserDoctorPhone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String doctorId = request.getParameter("doctorId");
		String applyState = request.getParameter("applyState");

		System.out.println(doctorId);
		List<Object> params = new ArrayList<Object>();
		params.add(applyState);
		params.add(doctorId);
		List<Map<String, Object>> list = membersDao.listUserPhoneApplyAll(params);
		request.setAttribute("mapmsg", list);

		if (applyState.equals("1")) {
			request.getRequestDispatcher("../appointmentDocMessage.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("../appointmentDocOKMessage.jsp").forward(request, response);
		}

	}

	private void deleteReview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String reviewId = request.getParameter("reviewId");
		String reviewMessageId = request.getParameter("reviewMessageId");
		List<Object> params = new ArrayList<Object>();
		params.add(reviewId);
		boolean flag = membersDao.deleteReview(params);
		if (flag) {

			List<Object> paramsReview = new ArrayList<Object>();
			paramsReview.add(reviewMessageId);
			// 已经进行分页之后的数据集合
			List<Map<String, Object>> list = membersDao.listReviewMessage(paramsReview);
			// studentDao.listMessageTeacher(list);
			request.setAttribute("listMessage", list);
			request.getRequestDispatcher("../reviewAdminMessage.jsp").forward(request, response);

		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "删除失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void listDoctorReviewAdminMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String doctorId = request.getParameter("doctorId");

		List<Object> paramsReview = new ArrayList<Object>();
		paramsReview.add(doctorId);
		// 已经进行分页之后的数据集合
		List<Map<String, Object>> list = membersDao.listReviewMessage(paramsReview);
		// studentDao.listMessageTeacher(list);
		request.setAttribute("listMessage", list);
		request.getRequestDispatcher("../reviewAdminMessage.jsp").forward(request, response);

	}

	private void listDoctorReviewMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String doctorId = request.getParameter("doctorId");

		List<Object> paramsReview = new ArrayList<Object>();
		paramsReview.add(doctorId);
		// 已经进行分页之后的数据集合
		List<Map<String, Object>> list = membersDao.listReviewMessage(paramsReview);
		// studentDao.listMessageTeacher(list);
		request.setAttribute("listMessage", list);
		request.getRequestDispatcher("../reviewMessage.jsp").forward(request, response);

	}

	private void listDoctorDepartMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String departmentId = request.getParameter("departmentId");
		List<Object> params = new ArrayList<Object>();
		params.add(departmentId);

		List<Map<String, Object>> list = membersDao.listDoctorDepartMessage(params);
		request.setAttribute("listMessage", list);
		request.getRequestDispatcher("../doctorMessage.jsp").forward(request, response);

	}

	private void updatePayCZ(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String umoney = request.getParameter("umoney");
		String userId = request.getParameter("userId");

		List<Object> params = new ArrayList<Object>();
		params.add(umoney);
		params.add(userId);
		boolean flag = membersDao.updateCZ(params);

		if (flag) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "更新失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void updateCZ(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String umoney = request.getParameter("umoney");
		String userMoney = request.getParameter("userMoney");

		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");

		List<Object> params = new ArrayList<Object>();
		params.add(userMoney);
		params.add(userId);
		boolean flag = membersDao.updateCZ(params);

		if (flag) {
			List<Object> paramsAdd = new ArrayList<Object>();
			paramsAdd.add(umoney);
			paramsAdd.add(userId);
			paramsAdd.add(userName);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
			paramsAdd.add(df.format(new Date()));
			boolean isAdd = membersDao.addCZ(paramsAdd);

			if (isAdd) {
				JSONObject jsonmsg = new JSONObject();
				jsonmsg.put("repMsg", "充值成功");
				jsonmsg.put("repCode", "666");
				System.out.println(jsonmsg);
				response.getWriter().print(jsonmsg);// 将路径返回给客户端
			} else {
				JSONObject jsonmsg = new JSONObject();
				jsonmsg.put("repMsg", "更新11失败");
				jsonmsg.put("repCode", "111");
				System.out.println(jsonmsg);
				response.getWriter().print(jsonmsg);// 将路径返回给客户端
			}

		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "更新22失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void listCZPhone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String rechargeUserId = request.getParameter("rechargeUserId");
		List<Map<String, Object>> list_tea = membersDao.listCZPhone(rechargeUserId);
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "签到成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list_tea);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void reviewListMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String reviewMessageId = request.getParameter("reviewMessageId");

		System.out.println(reviewMessageId);

		List<Object> params = new ArrayList<Object>();
		params.add(reviewMessageId);
		List<Map<String, Object>> list = membersDao.reviewListMessage(params);
		// 生成json字符串
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void addReview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String reviewMessageId = request.getParameter("reviewMessageId");
		String reviewContent = request.getParameter("reviewContent");
		String reviewUserId = request.getParameter("reviewUserId");
		String reviewUserName = request.getParameter("reviewUserName");

		List<Object> params = new ArrayList<Object>();
		params.add(reviewMessageId);
		params.add(reviewContent);
		params.add(reviewUserId);
		params.add(reviewUserName);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
		params.add(df.format(new Date()));
		boolean flag = membersDao.addReview(params);
		if (flag) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "评价成功");
			jsonmsg.put("repCode", "666");
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
			System.out.println(jsonmsg);
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "添加失败");
			jsonmsg.put("repCode", "111");
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
			System.out.println(jsonmsg);
		}

	}

	private void listJiJianPCMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String jijianUserId = request.getParameter("jijianUserId");
		List<Object> params = new ArrayList<Object>();
		params.add(jijianUserId);
		List<Map<String, Object>> flagFood = membersDao.listJiJianPCMessage(params);

		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", flagFood);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void addJiJian(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String jijianName = request.getParameter("jijianName");
		String jijianAddresse = request.getParameter("jijianAddresse");
		String jijianPhone = request.getParameter("jijianPhone");
		String jijianCard = request.getParameter("jijianCard");
		String jijianUserId = request.getParameter("jijianUserId");

		System.out.println(jijianName);
		System.out.println(jijianAddresse);
		System.out.println(jijianPhone);
		System.out.println(jijianUserId);

		List<Object> params = new ArrayList<Object>();
		params.add(jijianName);
		params.add(jijianAddresse);
		params.add(jijianPhone);
		params.add(jijianCard);
		params.add(jijianUserId);

		boolean flag = membersDao.addJiJian(params);

		if (flag) {

			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "提交成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端

		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "签到失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void listMesageAllPhoneDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Map<String, Object>> list = membersDao.listMesageAllPhoneDepartment();
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void listMessagePhoneNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Map<String, Object>> listNews = membersDao.listMesageNews();
		List<Map<String, Object>> listbanner = membersDao.ListBannerPCMessage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("listNews", listNews);
		map.put("listBanner", listbanner);

		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", map);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void ListBannerPCMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		// 已经进行分页之后的数据集合
		List<Map<String, Object>> list = membersDao.ListBannerPCMessage();
		request.setAttribute("listMessage", list);
		request.getRequestDispatcher("../bannerMessage.jsp").forward(request, response);

	}

	private void addBannerPC(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final long MAX_SIZE = 2048 * 1024 * 1024;// 设置上传文件最大值为2G，可以改为更大
		// 表单含有文件要提交
		String path = request.getContextPath();
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		// 构建一个文件上传类
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		// servletFileUpload.setFileSizeMax(3 * 1024 * 1024);
		servletFileUpload.setSizeMax(MAX_SIZE);// 上传文件总大小
		List<FileItem> list = null;
		List<Object> params = new ArrayList<Object>();
		try {
			// 解析request的请求
			list = servletFileUpload.parseRequest(request);
			// 取出所有表单的值:判断非文本字段和文本字段
			for (FileItem fileItem : list) {
				if (fileItem.isFormField()) {

					if (fileItem.getFieldName().equals("bookType")) {

						params.add(fileItem.getString("utf-8").split(",")[0]);
						params.add(fileItem.getString("utf-8").split(",")[1]);

					}

					if (fileItem.getFieldName().equals("shopMessage")) {

						params.add(fileItem.getString("utf-8"));
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
						params.add(df.format(new Date()));
						System.out.println(df.format(new Date()));

					}

				} else {
					try {

						String image = fileItem.getName();
						String imageload = PingYinUtil.getPingYin(image);
						System.out.println("image111--->>" + imageload);
						params.add(imageload);
						String upload_path = request.getRealPath("/upload");
						System.out.println("--->>" + upload_path);
						String imgPath = Consts.imgPath;
						File real_path = new File(imgPath + "/" + imageload);
						fileItem.write(real_path);

						// 把数据插入到数据库中
					} catch (Exception e) {
						e.printStackTrace();
					}

					boolean flag = membersDao.addBanner(params);

					if (flag) {
						ListBannerPCMessage(request, response);
					} else {
						System.out.println("flag:no");
					}

				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void listChoiceDoctorMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		List<Map<String, Object>> list = membersDao.listMesageDoctor();
		System.out.println(list.size());
		request.setAttribute("listMessage", list);
		request.getRequestDispatcher("../formBanner.jsp").forward(request, response);

	}

	private void deleteNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String newsId = request.getParameter("newsId");
		List<Object> params = new ArrayList<Object>();
		params.add(newsId);
		boolean flag = membersDao.deleteNews(params);
		if (flag) {
			listMessageNews(request, response);
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "删除失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void addNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String userId = request.getParameter("userId");
		String username = request.getParameter("username");

		String newsTitle = request.getParameter("newsTitle");
		String newsContent = request.getParameter("newsContent");
		SimpleDateFormat dfNo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		List<Object> params = new ArrayList<Object>();
		params.add(newsTitle);
		params.add(newsContent);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
		params.add(df.format(new Date()));
		params.add("1");
		params.add(userId);
		params.add(username);
		boolean flag = membersDao.addNews(params);
		if (flag) {
			listMessageNews(request, response);
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "提交失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void listAppluUserPhoneNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String doctorId = request.getParameter("doctorId");

		System.out.println(doctorId);
		List<Object> params = new ArrayList<Object>();
		params.add(doctorId);
		List<Map<String, Object>> list = membersDao.listAppluUserPhoneNews(params);
		request.setAttribute("mapmsg", list);
		request.getRequestDispatcher("../appointmentMessage.jsp").forward(request, response);

	}

	private void listMessageNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String userId = request.getParameter("userId");
		List<Object> params = new ArrayList<Object>();
		params.add(userId);
		List<Map<String, Object>> list = membersDao.listMesageNewsDoc(params);
		request.setAttribute("listMessage", list);
		request.getRequestDispatcher("../newsMessage.jsp").forward(request, response);

	}

	private void deleteApplayMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String applyId = request.getParameter("applyId");
		List<Object> params = new ArrayList<Object>();
		params.add(applyId);

		System.out.println(applyId);
		boolean flag = membersDao.deleteApplyMessage(params);
		if (flag) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "取消成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "申请失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void listMesagePhoneDoctor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String departmentId = request.getParameter("departmentId");
		List<Object> params = new ArrayList<Object>();
		params.add(departmentId);
		List<Map<String, Object>> list = membersDao.listMesagePhoneDoctor(params);
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void listMesagePhoneDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Map<String, Object>> list = membersDao.listMesagePhoneDepartment();
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void listHopitalPhoenMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Map<String, Object>> list = membersDao.listMesageHospital();
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void deleteDoctorMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String doctorId = request.getParameter("doctorId");
		List<Object> params = new ArrayList<Object>();
		params.add(doctorId);
		boolean flag = membersDao.deleteDoctorMessage(params);
		if (flag) {
			listDoctorMessage(request, response);
		} else {
			System.out.println("失败了");
		}

	}

	private void listDoctorMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		List<Map<String, Object>> list = membersDao.listMesageDoctor();
		// studentDao.listMessageTeacher(list);
		System.out.println(list.size());
		request.setAttribute("listMessage", list);
		request.getRequestDispatcher("../doctorMessage.jsp").forward(request, response);

	}

	private void addDoctor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String departmentName = request.getParameter("departmentName");
		String doctorName = request.getParameter("doctorName");

		String doctorMoney = request.getParameter("doctorMoney");
		String doctorLevel = request.getParameter("doctorLevel");

		String doctorYear = request.getParameter("doctorYear");
		String doctorMessage = request.getParameter("doctorMessage");
		String departmentId = request.getParameter("departmentId");

		List<Object> params = new ArrayList<Object>();
		params.add(departmentName);
		params.add(doctorName);
		params.add(doctorMoney);
		params.add(doctorLevel);
		params.add(doctorYear);
		params.add(doctorMessage);
		params.add(departmentId);
		params.add(doctorYear.substring(doctorYear.length()-6,doctorYear.length()));
		
		boolean flag = membersDao.addDoctor(params);
		System.out.println(flag);
		if (flag) {
			listDoctorMessage(request, response);
		} else {
			System.out.println("flag:no");
		}

	}

	private void queryDepart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String departmentId = request.getParameter("departmentId");
		List<Object> params = new ArrayList<Object>();
		params.add(departmentId);

		Map<String, Object> list = membersDao.queryDepart(params);
		request.setAttribute("mapmsg", list);
		request.getRequestDispatcher("../formAddDoctor.jsp").forward(request, response);

	}

	private void listDepartmentMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		List<Map<String, Object>> list = membersDao.listMesageDepartment();
		// studentDao.listMessageTeacher(list);
		request.setAttribute("listMessage", list);
		request.getRequestDispatcher("../departmentMessage.jsp").forward(request, response);

	}

	private void addDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String departmentName = request.getParameter("departmentName");
		String departmentInfor = request.getParameter("departmentInfor");
		List<Object> params = new ArrayList<Object>();
		params.add(departmentName);
		params.add(departmentInfor);
		System.out.println(departmentName);
		//
		boolean flag = membersDao.addDepartment(params);
		//
		if (flag) {
			listDepartmentMessage(request, response);
		} else {
			System.out.println("flag:no");
		}

	}

	private void listChoiceHopital(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		List<Map<String, Object>> list = membersDao.listMesageHospital();
		// studentDao.listMessageTeacher(list);
		request.setAttribute("listMessage", list);
		request.getRequestDispatcher("../formDepartment.jsp").forward(request, response);

	}

	private void listHopitalMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		List<Map<String, Object>> list = membersDao.listMesageHospital();
		// studentDao.listMessageTeacher(list);
		request.setAttribute("listMessage", list);
		request.getRequestDispatcher("../hospitalMessage.jsp").forward(request, response);

	}

	private void addHospital(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final long MAX_SIZE = 2048 * 1024 * 1024;// 设置上传文件最大值为2G，可以改为更大
		// 表单含有文件要提交
		String path = request.getContextPath();
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		// 构建一个文件上传类
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		// servletFileUpload.setFileSizeMax(3 * 1024 * 1024);
		servletFileUpload.setSizeMax(MAX_SIZE);// 上传文件总大小
		List<FileItem> list = null;
		List<Object> params = new ArrayList<Object>();
		try {
			// 解析request的请求
			list = servletFileUpload.parseRequest(request);
			// 取出所有表单的值:判断非文本字段和文本字段
			for (FileItem fileItem : list) {
				if (fileItem.isFormField()) {
					if (fileItem.getFieldName().equals("hospitalName")) {

						params.add(fileItem.getString("utf-8"));
						System.out.println(fileItem.getString("utf-8"));

					}

					if (fileItem.getFieldName().equals("hospitalAddress")) {

						params.add(fileItem.getString("utf-8"));
						System.out.println(fileItem.getString("utf-8"));

					}

					if (fileItem.getFieldName().equals("hospitalMessage")) {

						params.add(fileItem.getString("utf-8"));
						System.out.println(fileItem.getString("utf-8"));

						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
						params.add(df.format(new Date()));
						System.out.println(df.format(new Date()));

					}

				} else {
					try {
						String image = fileItem.getName();
						System.out.println("image111--->>" + image);
						params.add(image);
						String upload_path = request.getRealPath("/upload");
						System.out.println("--->>" + upload_path);

						String imgPath = "e:\\2017-2018code\\A953Hospital\\HospitalService\\WebRoot\\upload";
						File real_path = new File(imgPath + "/" + image);
						fileItem.write(real_path);

						// 把数据插入到数据库中
					} catch (Exception e) {
						e.printStackTrace();
					}

					boolean flag = membersDao.addHospital(params);
					System.out.println(flag);
					if (flag) {
						listHopitalMessage(request, response);
					} else {
						System.out.println("flag:no");
					}

				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void addScore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String applyScore = request.getParameter("applyScore");
		String applyId = request.getParameter("applyId");

		List<Object> params = new ArrayList<Object>();
		params.add(applyScore);
		params.add(applyId);
		boolean flag = membersDao.updateScoreMessage(params);

		if (flag) {

			listPcBorrowMessage(request, response);
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "申请失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
		}

	}

	private void queryScore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String applyId = request.getParameter("applyId");
		List<Object> params = new ArrayList<Object>();
		params.add(applyId);

		Map<String, Object> list = membersDao.queryUser(params);
		// studentDao.listMessageTeacher(list);
		request.setAttribute("mapmsg", list);
		request.getRequestDispatcher("../formAddScore.jsp").forward(request, response);

	}

	private void listPcLossMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		List<Map<String, Object>> list = membersDao.listMesagePcLoss();
		// studentDao.listMessageTeacher(list);
		request.setAttribute("listMessage", list);
		request.getRequestDispatcher("../lossMessage.jsp").forward(request, response);

	}

	private void listPcBorrowMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		List<Map<String, Object>> list = membersDao.listMesagePcBorrow();
		// studentDao.listMessageTeacher(list);
		request.setAttribute("listMessage", list);
		request.getRequestDispatcher("../borrowMessage.jsp").forward(request, response);

	}

	private void addLoss(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String lossUserId = request.getParameter("lossUserId");
		String lossUserName = request.getParameter("lossUserName");

		List<Object> params = new ArrayList<Object>();
		params.add(lossUserId);
		params.add(lossUserName);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
		params.add(df.format(new Date()));
		boolean flag = membersDao.addLoss(params);

		if (flag) {

			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "借阅成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "申请失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
		}

	}

	private void listMesageBorrow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		// 已经进行分页之后的数据集合
		String applyUserId = request.getParameter("applyUserId");
		List<Object> params = new ArrayList<Object>();
		params.add(applyUserId);
		System.out.println(applyUserId);
		List<Map<String, Object>> list_tea = membersDao.listMesageBorrow(params);

		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "签到成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list_tea);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void listBookMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userId = request.getParameter("userId");
		List<Map<String, Object>> list = membersDao.listMesageBook();
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void addBorrow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String applyUserId = request.getParameter("applyUserId");
		String applyUserName = request.getParameter("applyUserName");
		String applyMessageId = request.getParameter("applyMessageId");
		String applyMessageName = request.getParameter("applyMessageName");
		String applyChoiceTime = request.getParameter("applyChoiceTime");

		List<Object> params = new ArrayList<Object>();
		params.add(applyUserId);
		params.add(applyUserName);
		params.add(applyMessageId);
		params.add(applyMessageName);
		params.add(applyChoiceTime);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
		params.add(df.format(new Date()));
		params.add("1");

		boolean flag = membersDao.addBorrow(params);

		if (flag) {

			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "参与成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "申请失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
		}

	}

	private void listSearchMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String searchMessage = request.getParameter("searchMessage");
		String typeSearch = request.getParameter("typeSearch");
		System.out.println(searchMessage);
		System.out.println(typeSearch);
		if (typeSearch.equals("1")) {
			List<Map<String, Object>> list = membersDao.listSearchDepartKeShiMessage(searchMessage);
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "请求成功");
			jsonmsg.put("repCode", "666");
			jsonmsg.put("data", list);
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		} else {
			List<Map<String, Object>> list = membersDao.listSearchDepartJibingMessage(searchMessage);
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "请求成功");
			jsonmsg.put("repCode", "666");
			jsonmsg.put("data", list);
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void deleteMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String experimentId = request.getParameter("experimentId");
		List<Object> params = new ArrayList<Object>();
		params.add(experimentId);
		boolean flag = membersDao.deleteCourseMessage(params);
		if (flag) {
			listMessage(request, response);
		} else {
			System.out.println("失败了");
		}

	}

	private void updateReview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String rid = request.getParameter("rid");
		String replyContent = request.getParameter("replyContent");
		String rNoticeId = request.getParameter("rNoticeId");

		List<Object> params = new ArrayList<Object>();
		params.add(replyContent);
		params.add(rid);
		boolean flag = membersDao.updateMessage(params);
		if (flag) {
			List<Object> paramsReview = new ArrayList<Object>();
			paramsReview.add(rNoticeId);
			// 已经进行分页之后的数据集合
			List<Map<String, Object>> list = membersDao.listReviewMessage(paramsReview);
			// studentDao.listMessageTeacher(list);
			request.setAttribute("listMessage", list);
			request.getRequestDispatcher("../reviewMessage.jsp").forward(request, response);
		}

	}

	private void listNoticesMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Map<String, Object>> list = membersDao.listMesageCourse();
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void listMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		List<Map<String, Object>> list = membersDao.listMesageCourse();
		// studentDao.listMessageTeacher(list);
		request.setAttribute("listMessage", list);
		request.getRequestDispatcher("../epxMessage.jsp").forward(request, response);

	}

	private void addMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String newsName = request.getParameter("newsName");
		String newsMessage = request.getParameter("newsMessage");
		String newsUserId = request.getParameter("newsUserId");

		System.out.println(newsName);
		System.out.println(newsMessage);

		List<Object> params = new ArrayList<Object>();
		params.add(newsName);
		params.add(newsMessage);
		params.add(newsUserId);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
		params.add(df.format(new Date()));
		boolean flag = membersDao.addMessage(params);

		if (flag) {

			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "添加成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端

		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "申请失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
		}

	}

}
