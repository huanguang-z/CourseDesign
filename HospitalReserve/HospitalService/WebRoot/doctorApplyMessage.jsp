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
					<th>主治类别</th>
					<th>作息时间</th>
					<th>医生简历</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>




				<%
					int houseNumber = 0;
											if (!list.isEmpty()) {
												/*  for(Map<String,Object> map:list){ */
												for (int i = 1; i < list.size(); i++) {
													houseNumber = i + 1;
													Map<String, Object> map = list.get(i);
				%>


				<tr>
					<td ><%=houseNumber%></td>
					<td><%=map.get("dName")%></td>
					<td><%=map.get("dType")%></td>
					<td><%=map.get("dTime")%></td>
					<td><%=map.get("dContent")%></td>
					<td>
					<%
					if(map.get("dState").equals("1")){
					
					%>
					<a href="<%=path%>/servlet/DoctorAction?action_flag=updateOk&did=<%=map.get("did")%>">审核通过</a>&nbsp;&nbsp;<a href="<%=path%>/servlet/DoctorAction?action_flag=updateNo&did=<%=map.get("did")%>">不予通过</a>
					<%
					}else if(map.get("dState").equals("2")){
					 %>
					 已审核通过
					 	<%
					}else if(map.get("dState").equals("3")){
					 %>
					未审核通过
					<%
					}
					 %>
					 
					</td>
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
