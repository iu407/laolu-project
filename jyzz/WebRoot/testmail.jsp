<%@ page language="java"   contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="common/taglib.jsp" %>
<%@ page language="java"   import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="common/meta.jsp" %>
</head>
<body>
<%@ include file="common/menu.jsp" %>
<%
	Map<String,String> emailModel = (Map<String,String> )request.getAttribute("emailModel");
	System.out.println(emailModel);
%>
<div class="container_12">
	
	<div class="grid_12">
	<div class="grid_12">${message}</div>
	发送邮件地址是：${emailModel["mail_receive"] }
	</div>
	<div class="grid_12"><a href='${ctx }/'>返回主页</a></div>
</div>
<%@ include file="common/foot.jsp" %></body>
</html>
