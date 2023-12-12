<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	List<Map<String, Object>> list = (List<Map<String, Object>>) request.getAttribute("listMessage");
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
   th.action="<%=path%>/servlet/NoticeAction?action_flag=addMessage";
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
				<li><label>发布标题</label><input name="noticeTitle" type="text" class="dfinput" /></li>
				<li><label>医生姓名</label>


					<div class="vocation">
						<select class="select1" name="doctorMsg">


							<%
								if (!list.isEmpty()) {
									/*  for(Map<String,Object> map:list){ */
									for (int i = 0; i < list.size(); i++) {
										Map<String, Object> map = list.get(i);
							%>


							<option value="<%=map.get("did")%>,<%=map.get("dName")%>"><%=map.get("dName")%></option>

							<%
								}
								}
							%>
						</select>
					</div>
				  <li><label>发布内容</label><textarea name="noticeContent" cols="" rows="" class="textinput"></textarea></li>
				<li><label>&nbsp;</label><input name="" type="button"  onclick="javascript:dosubmit();"  class="btn" value="确认保存" /></li>
			</ul>


		</div>
	</form>
</body>
</html>
