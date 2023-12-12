<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
%>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="js/select-ui.min.js"></script>
<script type="text/javascript" src="editor/kindeditor.js"></script>
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
   th.action="<%=path%>/servlet/RegisterAction?action_flag=updateDocPswdPC&userId=<%=session.getAttribute("userId") %>";
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
		
				<li><label>原始密码</label><input name="oldpswd" type="text" class="dfinput" /></li>
				<li><label>新密码</label><input name="newpswd" type="text" class="dfinput" /></li>
				<li><label>确认密码</label><input name="twppswd" type="text" class="dfinput" /></li>
				<li><label>&nbsp;</label><input name="" type="button"  onclick="javascript:dosubmit();"  class="btn" value="确认修改" /></li>
			</ul>


		</div>
	</form>
</body>
</html>
