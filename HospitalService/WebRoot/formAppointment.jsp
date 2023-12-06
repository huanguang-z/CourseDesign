<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
%>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="../js/select-ui.min.js"></script>
<script type="text/javascript" src="../editor/kindeditor.js"></script>


<script type="text/javascript">


	<%String AppointmentId = new String(request
					.getParameter("AppointmentId").getBytes("ISO-8859-1"), "UTF-8");
			out.print(AppointmentId);%>
			
	<%String AppointmentUserId = new String(request
					.getParameter("AppointmentUserId").getBytes("ISO-8859-1"), "UTF-8");
			out.print(AppointmentUserId);%>
	
	<%String AppointmentDoctorId = new String(request
					.getParameter("AppointmentDoctorId").getBytes("ISO-8859-1"), "UTF-8");
			out.print(AppointmentDoctorId);%>
	
		<%String AppointmentDoctor = new String(request.getParameter(
					"AppointmentDoctor").getBytes("ISO-8859-1"), "UTF-8");%>
	
	
	
 function dosubmit(){
   var th = document.form2;
   th.action="<%=path%>/servlet/AppointmentAction?action_flag=addAtMessage&AppointmentId=<%=AppointmentId %>&AppointmentUserId=<%=AppointmentUserId %>&AppointmentDoctorId=<%=AppointmentDoctorId %>&AppointmentDoctor=<%=AppointmentDoctor %>";
		th.submit();
	}
</script>
</head>

<body>
	<form name="form2" action="" method="post">
		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li><a href="#">首页</a></li>
				<li><a href="#">表单</a></li>
			</ul>
		</div>

		<div class="formbody">

			<div class="formtitle">
				<span>基本信息</span>
			</div>

			<ul class="forminfo">
				<li><label>病历标题</label><input name="atTitle" type="text" class="dfinput" /></li>
				<li><label>病历内容</label>
				<textarea name="atBingLi" cols="" rows="" class="textinput"></textarea></li>
				<li><label>处方内容</label>
				<textarea name="atContent" cols="" rows="" class="textinput"></textarea></li>
				<li><label>&nbsp;</label><input name="" type="button" onclick="javascript:dosubmit();" class="btn" value="确认保存" /></li>
			</ul>


		</div>
	</form>
</body>
</html>
