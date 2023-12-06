package com.student.data.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.student.config.Consts;
import com.student.data.dao.RegisterDao;

public class RegisterAction extends HttpServlet {

	private RegisterDao registerDao;
	private static final long serialVersionUID = 1L;
	private ServletFileUpload upload;
	private final long MAXSize = 4194304 * 2L;// 4*2MB
	private String filedir = null;

	public RegisterAction() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		String action_flag = request.getParameter("action_flag");

		System.out.println(action_flag);
		if (action_flag.equals("add")) {
			registerMessage(request, response);
		} else if (action_flag.equals("listUser")) {
			listUser(request, response);
		} else if (action_flag.equals("login")) {
			loginMessage(request, response);
		} else if (action_flag.equals("deleteUser")) {
			deleteUserPc(request, response);
		} else if (action_flag.equals("updateName")) {
			updateName(request, response);
		} else if (action_flag.equals("updatePhone")) {
			updatePhone(request, response);
		} else if (action_flag.equals("exit")) {
			exit(request, response);
		} else if (action_flag.equals("updateMoneyMessage")) {
			updateMoney(request, response);
		} else if (action_flag.equals("updatePswd")) {
			updatePswd(request, response);
		} else if (action_flag.equals("updateUserInfor")) {
			updateUserInfor(request, response);
		} else if (action_flag.equals("updateUserImage")) {
			updateUserImage(request, response);
		} else if (action_flag.equals("loginPc")) {
			loginPc(request, response);
		} else if (action_flag.equals("updatePswdPC")) {
			updatePswdPC(request, response);
		} else if (action_flag.equals("updateCardPswdPC")) {
			updateCardPswdPC(request, response);
		}else if (action_flag.equals("updateDocPswdPC")) {
			updateDocPswdPC(request, response);
		} else if (action_flag.equals("updateDocCardPswdPC")) {
			updateDocCardPswdPC(request, response);
		}

	}

	public void init(ServletConfig config) throws ServletException {
		FileItemFactory factory = new DiskFileItemFactory();// Create a factory
		this.upload = new ServletFileUpload(factory);// Create a new file upload
		this.upload.setSizeMax(this.MAXSize);// Set overall request size
		filedir = config.getServletContext().getRealPath("upload");
		System.out.println("filedir=" + filedir);
		registerDao = new RegisterDao();
	}
	
	
	private void updateDocCardPswdPC(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uCard = request.getParameter("uCard");
		String userId = request.getParameter("userId");

		PrintWriter out = response.getWriter(); // 初始化out对象

		List<Object> params = new ArrayList<Object>();
		params.add(userId);
		Map<String, Object> map = registerDao.queryDoctorInfor(params);

		if (!uCard.equals(map.get("doctorCard"))) {
			out.print("<script language='javascript'>alert('你输入的身份证号码和原始的不匹配!');window.location.href='../formPswd.jsp';</script>");
		}else{
				 List<Object> paramsUpdate = new ArrayList<Object>();
				 paramsUpdate.add(uCard.substring(uCard.length()-6,uCard.length()));
				 paramsUpdate.add(userId);
				boolean flag = registerDao.updateDocPswdPC(paramsUpdate);

				if (flag) {
//					JSONObject jsonmsg = new JSONObject();
//					jsonmsg.put("repMsg", "重置成功");
//					System.out.println(jsonmsg);
//					response.getWriter().print(jsonmsg);// 将路径返回给客户端
//					
					
					
					out.print("<script language='javascript'>alert('重置成功,请使用密码后六位进行登录!');window.location.href='../formPswd.jsp';</script>");

				} else {
					JSONObject jsonmsg = new JSONObject();
					jsonmsg.put("repMsg", "更新失败");
					jsonmsg.put("repCode", "111");
					System.out.println(jsonmsg);
				}
		}

	}
	
	private void updateDocPswdPC(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String oldpswd = request.getParameter("oldpswd");
		String newpswd = request.getParameter("newpswd");
		String twppswd = request.getParameter("twppswd");
		String userId = request.getParameter("userId");

		PrintWriter out = response.getWriter(); // 初始化out对象

		List<Object> params = new ArrayList<Object>();
		params.add(userId);
		Map<String, Object> map = registerDao.queryDoctorInfor(params);

		if (!oldpswd.equals(map.get("doctorPswd"))) {
			out.print("<script language='javascript'>alert('旧密码输入错误!');window.location.href='../formDocPswd.jsp';</script>");
		}else{
			if (!newpswd.equals(twppswd)) {
				out.print("<script language='javascript'>alert('新密码和确认密码不一致!');window.location.href='../formDocPswd.jsp';</script>");
			}else{
				 List<Object> paramsUpdate = new ArrayList<Object>();
				 paramsUpdate.add(twppswd);
				 paramsUpdate.add(userId);
				boolean flag = registerDao.updateDocPswdPC(paramsUpdate);

				if (flag) {
//					JSONObject jsonmsg = new JSONObject();
//					jsonmsg.put("repMsg", "修改成功");
//					System.out.println(jsonmsg);
//					response.getWriter().print(jsonmsg);// 将路径返回给客户端
//					
//					
					out.print("<script language='javascript'>alert('修改成功,请牢记新密码哦!');window.location.href='../formDocPswd.jsp';</script>");

				} else {
					JSONObject jsonmsg = new JSONObject();
					jsonmsg.put("repMsg", "更新失败");
					jsonmsg.put("repCode", "111");
					System.out.println(jsonmsg);
				}
			}
		}

	}
	
	private void updateCardPswdPC(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uCard = request.getParameter("uCard");
		String userId = request.getParameter("userId");

		PrintWriter out = response.getWriter(); // 初始化out对象

		List<Object> params = new ArrayList<Object>();
		params.add(userId);
		Map<String, Object> map = registerDao.queryUserInfor(params);

		if (!uCard.equals(map.get("uCard"))) {
			out.print("<script language='javascript'>alert('你输入的身份证号码和原始的不匹配!');window.location.href='../formPswd.jsp';</script>");
		}else{
				 List<Object> paramsUpdate = new ArrayList<Object>();
				 paramsUpdate.add(uCard.substring(uCard.length()-6,uCard.length()));
				 paramsUpdate.add(userId);
				boolean flag = registerDao.updatePswd(paramsUpdate);

				if (flag) {
//					JSONObject jsonmsg = new JSONObject();
//					jsonmsg.put("repMsg", "重置成功");
//					System.out.println(jsonmsg);
//					response.getWriter().print(jsonmsg);// 将路径返回给客户端
					
					
					out.print("<script language='javascript'>alert('重置成功,请使用密码后六位进行登录!');window.location.href='../formPswd.jsp';</script>");

				} else {
					JSONObject jsonmsg = new JSONObject();
					jsonmsg.put("repMsg", "更新失败");
					jsonmsg.put("repCode", "111");
					System.out.println(jsonmsg);
				}
		}

	}
	
	private void updatePswdPC(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String oldpswd = request.getParameter("oldpswd");
		String newpswd = request.getParameter("newpswd");
		String twppswd = request.getParameter("twppswd");
		String userId = request.getParameter("userId");

		PrintWriter out = response.getWriter(); // 初始化out对象

		List<Object> params = new ArrayList<Object>();
		params.add(userId);
		Map<String, Object> map = registerDao.queryUserInfor(params);

		if (!oldpswd.equals(map.get("upswd"))) {
			out.print("<script language='javascript'>alert('旧密码输入错误!');window.location.href='../formPswd.jsp';</script>");
		}else{
			if (!newpswd.equals(twppswd)) {
				out.print("<script language='javascript'>alert('新密码和确认密码不一致!');window.location.href='../formPswd.jsp';</script>");
			}else{
				 List<Object> paramsUpdate = new ArrayList<Object>();
				 paramsUpdate.add(twppswd);
				 paramsUpdate.add(userId);
				boolean flag = registerDao.updatePswd(paramsUpdate);

				if (flag) {
//					JSONObject jsonmsg = new JSONObject();
//					jsonmsg.put("repMsg", "修改成功");
//					System.out.println(jsonmsg);
//					response.getWriter().print(jsonmsg);// 将路径返回给客户端
					
					out.print("<script language='javascript'>alert('修改成功,请牢记新密码哦!');window.location.href='../formPswd.jsp';</script>");

				} else {
					JSONObject jsonmsg = new JSONObject();
					jsonmsg.put("repMsg", "更新失败");
					jsonmsg.put("repCode", "111");
					System.out.println(jsonmsg);
				}
			}
		}

	}

	private void updateUserImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String imagePath = null;
		try {
			List<FileItem> items = this.upload.parseRequest(request);
			if (items != null && !items.isEmpty()) {
				for (FileItem fileItem : items) {
					String filename = fileItem.getName();

					File real_path = new File(Consts.imgPath + "/" + filename);
					InputStream inputSteam = fileItem.getInputStream();
					BufferedInputStream fis = new BufferedInputStream(inputSteam);
					FileOutputStream fos = new FileOutputStream(real_path);
					int f;
					while ((f = fis.read()) != -1) {
						fos.write(f);
					}
					fos.flush();
					fos.close();
					fis.close();
					inputSteam.close();
					System.out.println("文件：" + filename + "上传成功!");
					imagePath = filename;
				}
			}

		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}

	private void updateUserInfor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uname = request.getParameter("uname");
		String uphone = request.getParameter("uphone");
		String uSex = request.getParameter("uSex");
		String uImg = request.getParameter("uImg");
		String uid = request.getParameter("uid");

		List<Object> params = new ArrayList<Object>();

		params.add(uname);
		params.add(uphone);
		params.add(uSex);
		params.add(uImg);
		params.add(uid);
		boolean flag = registerDao.updateUserInfor(params);

		if (flag) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "更新成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端

		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "更新失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
		}

	}

	private void updatePswd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String pswd = request.getParameter("pswd");
		String userId = request.getParameter("userId");
		List<Object> params = new ArrayList<Object>();
		params.add(pswd);
		params.add(userId);
		boolean flag = registerDao.updatePswd(params);

		if (flag) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "更新成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
			// response.sendRedirect(path +
			// "/servlet/NoticeAction?action_flag=listMessage");

		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "更新失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			// response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void updateMoney(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String uMoney = request.getParameter("uMoney");
		String uid = request.getParameter("uid");

		System.out.println(uMoney);
		List<Object> paramsQuery = new ArrayList<Object>();
		paramsQuery.add(uid);
		int moneyMessage = Integer.valueOf(registerDao.queryUser(paramsQuery).get("uMoney").toString());

		int totalMoney = Integer.valueOf(uMoney) + moneyMessage;
		List<Object> params = new ArrayList<Object>();
		params.add(totalMoney + "");
		params.add(uid);
		boolean flag = registerDao.updateMoney(params);
		if (flag) {

			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "充值成功");
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

	private void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect(path + "/login.jsp");

	}

	private void loginPc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String loginName = request.getParameter("loginName");
		String loginPassword = request.getParameter("loginPassword");
		String loginType = request.getParameter("loginType");

		System.out.println("-------loginType----" + loginType);
		System.out.println("-------loginName----" + loginName);
		System.out.println("-------loginPassword----" + loginPassword);

		if (loginType.equals("1")) {
			List<Object> params = new ArrayList<Object>();
			params.add(loginName);
			params.add(loginPassword);
			boolean flagCheck = registerDao.loginPcCheck(params);
			if (flagCheck) {
				Map<String, Object> map = registerDao.queryUset(params);
				HttpSession session = request.getSession();
				session.setAttribute("loginState", "loginOk");
				session.setAttribute("username", loginName);
				session.setAttribute("userId", map.get("uid"));
				response.sendRedirect(request.getContextPath() + "/main.jsp");
			} else {
				PrintWriter out = response.getWriter(); // 初始化out对象
				out.print("<script language='javascript'>alert('用户名或密码不正确!');window.location.href='../login.jsp';</script>");

			}
		} else {
			List<Object> params = new ArrayList<Object>();
			params.add(loginName);
			params.add(loginPassword);
			boolean flagCheck = registerDao.loginDoctorPcCheck(params);
			if (flagCheck) {

				Map<String, Object> map = registerDao.queryDoctor(params);
				HttpSession session = request.getSession();
				session.setAttribute("loginState", "loginOk");
				session.setAttribute("username", loginName);
				session.setAttribute("userId", map.get("doctorId"));
				response.sendRedirect(request.getContextPath() + "/mainDoc.jsp");
			} else {
				PrintWriter out = response.getWriter(); // 初始化out对象
				out.print("<script language='javascript'>alert('用户名或密码不正确!');window.location.href='../login.jsp';</script>");

			}
		}

	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String name = request.getParameter("name");
		String pswd = request.getParameter("pswd");
		System.out.println("-------name----" + name);
		System.out.println("-------pswd----" + pswd);
		// List<Object> params = new ArrayList<Object>();
		// params.add(name);
		// params.add(pswd);
		// boolean flag = registerDao.Login(params);
		if (name.equals("admin") && pswd.equals("123456")) {
			response.sendRedirect(path + "/main.jsp");
		} else {
			request.setAttribute("message", "用户名或密码错误");
		}
	}

	private void updateName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String uname = request.getParameter("uname");
		String uid = request.getParameter("uid");
		List<Object> params = new ArrayList<Object>();
		params.add(uname);
		params.add(uid);
		boolean flag = registerDao.updateName(params);
		if (flag) {

			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "更新成功");
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

	private void updatePhone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String uphone = request.getParameter("uphone");
		String uid = request.getParameter("uid");
		List<Object> params = new ArrayList<Object>();
		params.add(uphone);
		params.add(uid);
		boolean flag = registerDao.updatePhone(params);
		if (flag) {

			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "更新成功");
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

	private void deleteUserPc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String uid = request.getParameter("uid");
		List<Object> params = new ArrayList<Object>();
		params.add(uid);
		boolean flag = registerDao.deleteUser(params);
		if (flag) {
			System.out.println("成功了");
			response.sendRedirect(path + "/servlet/RegisterAction?action_flag=listUser&uid=" + uid);
		} else {
			System.out.println("失败了");
		}

	}

	/**
	 * 注册
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */

	private void registerMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uname = request.getParameter("uname");
		String uphone = request.getParameter("uphone");
		String upswd = request.getParameter("upswd");
		String choiceType = request.getParameter("choiceType");
		String uAge = request.getParameter("uAge");

		System.out.println(uname);
		System.out.println(uphone);
		System.out.println(upswd);

		List<Object> params_check_login = new ArrayList<Object>();
		params_check_login.add(uname);
		params_check_login.add(upswd);

		boolean flag = registerDao.resgisterCheck(params_check_login);
		if (flag == true) {
			Map<String, Object> user_model = registerDao.queryOne(params_check_login);

			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "此用户已经注册");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		} else {
			List<Object> params = new ArrayList<Object>();
			params.add(uname);
			params.add(uphone);
			params.add(choiceType + "");
			params.add(uAge + "");
			params.add(upswd);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
			params.add(df.format(new Date()));
			params.add("0");
			JSONObject jsonmsg = new JSONObject();
			// 数据的注册
			boolean flag_zhuce = registerDao.resgisterPhone(params);

			// 注册成功处理
			if (flag_zhuce) {
				jsonmsg.put("repMsg", "注册成功");
				jsonmsg.put("repCode", "666");
				response.getWriter().print(jsonmsg);// 将路径返回给客户端
				System.out.println(jsonmsg);
			} else {
				jsonmsg.put("repMsg", "注册失败");
				jsonmsg.put("repCode", "111");
				response.getWriter().print(jsonmsg);// 将路径返回给客户端
				System.out.println(jsonmsg);
			}

			System.out.println(flag_zhuce);

		}

	}

	/**
	 * 获取用户信息
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 已经进行分页之后的数据集合
		List<Map<String, Object>> list = registerDao.listUserMessage();
		request.setAttribute("listMessage", list);
		request.getRequestDispatcher("../userMessage.jsp").forward(request, response);
	}

	private void loginMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String user_phone = request.getParameter("uphone");
		String user_pswd = request.getParameter("pswd");

		List<Object> params_check_login = new ArrayList<Object>();
		params_check_login.add(user_phone);
		params_check_login.add(user_pswd);
		boolean flag = registerDao.Login(params_check_login);
		if (flag) {
			Map<String, Object> map = registerDao.queryOne(params_check_login);

			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "登录成功");
			jsonmsg.put("repCode", "666");
			jsonmsg.put("data", map);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
			System.out.println(jsonmsg);

		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "用户名或密码不正确");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}
	}

}
