<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	List<Map<String, Object>> list = (List<Map<String, Object>>) request.getAttribute("listMessage");
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
	<%String rNoticeId = new String(request
					.getParameter("rNoticeId").getBytes("ISO-8859-1"), "UTF-8");
			out.print(rNoticeId);%>

	<%String rid = new String(request
					.getParameter("rid").getBytes("ISO-8859-1"), "UTF-8");
			out.print(rid);%>
			
		<%String rReviewContent = new String(request
					.getParameter("rReviewContent").getBytes("ISO-8859-1"), "UTF-8");
			out.print(rReviewContent);%>	
 function dosubmit(){
   var th = document.form2;
   th.action="<%=path%>/servlet/ReviewAction?action_flag=updateReview&rid=<%=rid%>&rNoticeId=<%=rNoticeId%>";
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
				<li>评论内容:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=rReviewContent %></li>
				<li><label>回复内容</label><textarea name="replyContent" cols="" rows="" class="textinput"></textarea></li>
				<li><label>&nbsp;</label><input name="" type="button"  onclick="javascript:dosubmit();"  class="btn" value="确认保存" /></li>
			</ul>


		</div>
	</form>
</body>
</html>
