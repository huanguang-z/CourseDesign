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
					<th>名称</th>
					<th>类型</th>
					<th>单价金额</th>
					<th>产地</th>
					<th>添加时间 </th>
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
					<td><%=map.get("shopName")%></td>
					<td><%=map.get("shopTypeName")%></td>
					<td><%=map.get("shopMoney")%>元</td>
					<td><%=map.get("shopCity")%></td>
					<td><%=map.get("shopCreatime")%></td>
					<td>
				<%
						if(map.get("shopState").equals("1")){
						%>
					 <a href="<%=path%>/servlet/MessageAction?action_flag=updateState&shopState=2&shopId=<%=map.get("shopId")%>" class="tablelink"> 批准</a>
					 &nbsp
					 &nbsp
						<a href="<%=path%>/servlet/MessageAction?action_flag=updateState&shopState=3&shopId=<%=map.get("shopId")%>" class="tablelink"> 拒绝</a>
					  &nbsp
						<%
						}else if(map.get("shopState").equals("2")){
						out.print("已批准");
						}else if(map.get("shopState").equals("3")){
						out.print("已拒绝");
						}
						%> <a href="<%=path%>/servlet/MessageAction?action_flag=deleteShop&shopId=<%=map.get("shopId")%>" class="tablelink"> 删除</a>
					
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
