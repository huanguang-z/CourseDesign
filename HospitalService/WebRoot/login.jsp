<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>欢迎登录后台管理系统--我爱模板网 www.5imoban.net</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>
<script src="js/cloud.js" type="text/javascript"></script>

<script language="javascript">
	$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    })  
});  
</script>
<script type="text/javascript">

 function login(){
   var th = document.formLogin;
   if(th.name.value==""){
      alert("用户名不能为空！！");
      th.username.focus();
      return ;
   }
   if(th.pswd.value==""){
      alert("密码不能为空！！");
      th.pswd.focus();
      return ;
   }
   th.action="<%=path%>/servlet/RegisterAction?action_flag=loginPc";
			th.submit();

	}
</script>

</head>

<body
	style="background-color:#1c77ac; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">


	<div id="mainBody">
		<div id="cloud1" class="cloud"></div>
		<div id="cloud2" class="cloud"></div>
	</div>


	<div class="logintop">
		<span>欢迎登录后台管理界面平台</span>
		<ul>
			<li><a href="#">回首页</a></li>
			<li><a href="#">帮助</a></li>
			<li><a href="#">关于</a></li>
		</ul>
	</div>

	<div class="loginbody">

		<span class="systemlogo"></span>

		<div class="loginbox">
			<FORM name="formLogin" action="<%=path%>/servlet/RegisterAction?action_flag=loginPc" method="post">
			<ul>
				<li><input name="loginName" type="text" class="loginuser" value="" onclick="JavaScript:this.value=''" /></li>
				<li><input name="loginPassword" type="password" class="loginpwd" value="" onclick="JavaScript:this.value=''" /></li>
				<li><label>用户类型&nbsp;&nbsp;</label><cite><input name="loginType" type="radio" value="1" checked="checked" />管理员&nbsp;&nbsp;&nbsp;&nbsp;<input name="loginType" type="radio" value="2" />医生</cite></li>
				<li><input name="" type="submit" class="loginbtn" value="登录"  /></li>
			</ul>

	</FORM>
		</div>

	</div>



	<div class="loginbm"></div>
</body>
</html>
