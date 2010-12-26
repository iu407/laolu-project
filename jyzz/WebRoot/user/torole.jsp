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
	<div class=''>已经分配的角色</div>
	<form method="post" id="loginForm" action="${ctx}/user/deleterole">
	<input type="hidden"" name="userid" id="userid" value="${userid }"/>
	<ul>
	<c:forEach items="${user.roles}" var="role">
		<li id="li_${role.roleid }">
		<input type="checkbox"" name="roleid" id="roleid" value="${role.roleid }"/>
		${role.roleid },${role.rolename },${role.remark }
		</li>
	</c:forEach>
	</ul>
	<p><input type="submit" value="取消角色" /></p>
	</form>

</div>
<div class="grid_9">
	<div class=''>未分配角色</div>
	<form method="post" id="loginForm" action="${ctx}/user/addrole">
	<input type="hidden" name="userid" id="userid" value="${userid }"/>
	<ul>
	<c:forEach items="${roles}" var="role">
		<li id="li_${role.roleid }">
		<input type="checkbox"" name="roleid" id="roleid" value="${role.roleid }"/>
		${role.roleid },${role.rolename },${role.remark }
		</li>
	</c:forEach>
	</ul>
	<p><input type="submit" value="增加角色" /></p>
	</form>
</div>
<div class="grid_8">
<a href="${ctx }/user/list">返回用户管理</a>
</div>
</div>
<%@ include file="../common/foot.jsp" %></body>
</html>
