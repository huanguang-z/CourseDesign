<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	   String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()

            + path;
	List<Map<String, Object>> list = (List<Map<String, Object>>) request.getAttribute("listMessage");
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head href="<%=basePath%>">
<title>学生管理</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$(".click").click(function() {
			$(".tip").fadeIn(200);
		});

		$(".tiptop a").click(function() {
			$(".tip").fadeOut(200);
		});

		$(".sure").click(function() {
			$(".tip").fadeOut(100);
		});

		$(".cancel").click(function() {
			$(".tip").fadeOut(100);
		});

	});
</script>


</head>


<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">数据表</a></li>
			<li><a href="#">基本内容</a></li>
		</ul>
	</div>

	<div class="rightinfo">


		<table class="tablelist">
			<thead>
				<tr>
					<th>序号</th>
					<th>医生姓名</th>
					<th>身份证号</th>
					<th>医生级别</th>
					<th>挂号费用</th>
					<th>所属科室</th>
					<th>预约信息</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>




				<%
					int houseNumber = 0;
											if (!list.isEmpty()) {
												/*  for(Map<String,Object> map:list){ */
												for (int i = 0; i < list.size(); i++) {
													houseNumber = i + 1;
													Map<String, Object> map = list.get(i);
				%>


				<tr>
					<td ><%=houseNumber%></td>
					<td>
					
					<%=map.get("doctorName")%>
									
					</td>
					<td><%=map.get("doctorCard")%></td>
					<td><%=map.get("doctorLevel")%></td>
					<td><%=map.get("doctorMoney")%>元/位</td>
					<td><%=map.get("departmentName")%></td>
						<td>
					
					<a href="<%=path%>/servlet/HospitalAction?action_flag=listAppluUserPhoneNews&doctorId=<%=map.get("doctorId")%>" class="tablelink"> 查看预约患者</a>
					&nbsp&nbsp
					<a href="<%=path%>/servlet/HospitalAction?action_flag=listDoctorReviewAdminMessage&doctorId=<%=map.get("doctorId")%>" class="tablelink"> 查看用户反馈</a>
									
					</td>
					<td>  <a href="<%=path%>/servlet/HospitalAction?action_flag=deleteDoctorMessage&doctorId=<%=map.get("doctorId")%>" class="tablelink"> 删除</a></td>
				</tr>

				<%
					}
											}
				%>
			</tbody>
		</table>







	</div>

	<script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>
</html>
