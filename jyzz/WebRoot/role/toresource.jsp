<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="../common/meta.jsp" %>
</head>
  
<body>
<%@ include file="../common/menu.jsp" %>

<div class="container_12">
<jsp:include page="/user/usermenu"></jsp:include>

<div class="grid_9">
	<h3>${role.rolename }-${role.remark } 权限</h3>
	<h2>已经分配资源</h2>
	<form method="post" id="loginForm" action="${ctx}/role/deleteresource">
	<input type="hidden"" name="roleid" id="roleid" value="${role.roleid }"/>
	<ul>
	<c:forEach items="${role.resources}" var="resource">
		<li id="li_${resource.resourceid }">
		<input type="checkbox"" name="resourceid" id="resourceid" value="${resource.resourceid }"/>
		${resource.resourceid },${resource.type },${resource.value},${resource.resourcename}
		</li>
	</c:forEach>
	</ul>
	<p><input type="submit" value="取消权限" /></p>
	</form>

	<h2 class=''>未分配资源</h2>
	<form method="post" id="loginForm" action="${ctx}/role/addresource">
	<input type="hidden"" name="roleid" id="roleid" value="${role.roleid }"/>
	<ul>
	<c:forEach items="${resources}" var="resource">
		<li id="li_${resource.resourceid }">
		<input type="checkbox"" name="resourceid" id="resourceid" value="${resource.resourceid }"/>
		${resource.resourceid },${resource.type },${resource.value},${resource.resourcename}
		</li>
	</c:forEach>
	</ul>
	<p><input type="submit" value="增加权限" /></p>
	</form>
</div>
</div>
<%@ include file="../common/foot.jsp" %></body>
</html>
