<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	List<Map<String, Object>> list = (List<Map<String, Object>>) request.getAttribute("listMessage");
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head >
<title>doacter</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery.js"></script>

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
					<th>评论用户</th>
					<th>评论内容</th>
					<th>评论时间</th>
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
					<td><%=map.get("reviewUserName")%></td>
					<td><%=map.get("reviewContent")%></td>
					
					<td><%=map.get("reviewTime")%></td>
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
