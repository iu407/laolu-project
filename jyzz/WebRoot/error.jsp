<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
<%@ include file="common/meta.jsp" %>  
	</head>
<body>

<%@ include file="common/menu.jsp" %>
<div class="container_12">
您访问的页面不存在!
<div class="clearfix"></div>
<jsp:include page="/commongoods"></jsp:include>

<a href='${ctx }' >返回首页</a>
</div>
<%@ include file="common/foot.jsp" %></body>
</html>
