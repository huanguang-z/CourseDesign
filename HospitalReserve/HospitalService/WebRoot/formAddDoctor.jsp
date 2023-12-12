<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	Map<String, Object> mapmsg = (Map<String, Object>)request.getAttribute("mapmsg");
%>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>无标题文档</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<link href="../css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="../js/select-ui.min.js"></script>
<script type="text/javascript" src="../editor/kindeditor.js"></script>


<script type="text/javascript">
	$(document).ready(function(e) {
		$(".select1").uedSelect({
			width : 345
		});
		$(".select2").uedSelect({
			width : 167
		});
		$(".select3").uedSelect({
			width : 100
		});
	});
</script>
<script type="text/javascript">
 function dosubmit(){
   var th = document.form2;
   th.action="<%=path%>/servlet/HospitalAction?action_flag=addDoctor&departmentId=<%=mapmsg.get("departmentId")%>";
		th.submit();
	}
</script>
</head>

<body>
	<form name="form2" action="" method="post" >
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
			<li ><label>所属科室：    </label><input onfocus=this.blur() name="departmentName" type="text" class="dfinput" value="<%=mapmsg.get("departmentName")%>" /></li>
				<li><label>医生姓名</label><input name="doctorName" type="text" class="dfinput" /></li>
				<li><label>挂号费用</label><input name="doctorMoney" type="text" class="dfinput" /></li>
					<li><label>医生级别</label>
					<div class="vocation">
						<select class="select1" name="doctorLevel">
							<option value="专家">专家</option>
							<option value="普通">普通</option>
						</select>
					</div></li>
				<li><label>身份证号</label><input name="doctorYear" type="text" class="dfinput" /></li>
				 <li><label>医生简介</label><textarea name="doctorMessage" cols="" rows="" class="textinput"></textarea></li>
				<li><label>&nbsp;</label><input name="" type="button" class="btn" onclick="javascript:dosubmit();"  value="确认添加" /></li>
			</ul>

		</div>
	</form>
</body>
</html>
