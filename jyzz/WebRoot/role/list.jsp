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

<div class='grid_9'>
<a href='${ctx }/role/new'>增加角色</a>
<table>
	<caption>商品列表</caption>
	<thead>
	<tr>
		<th>角色id</th> 
		<th>角色名</th> 
		<th>名称</th> 
		<th>操作</th>
	</tr>
	</thead>
	<c:forEach items="${roles}" var="role">
	<tr id="li_${role.roleid }">
		<td>${role.roleid }</td>
		<td>${role.rolename }</td>
		<td>${role.remark }</td>
		<td>
			<a href='<c:url value="/role/delete/${role.roleid }"></c:url>' >删除</a>
			&nbsp;
			<a href='<c:url value="/role/toresource/${role.roleid }"></c:url>' >分配资源</a>
		</td>
	</tr>
	</c:forEach>
</table>
<%--<ul>--%>
<%--<c:forEach items="${roles}" var="role">--%>
<%--	<li id="li_${role.roleid }">${role.roleid },${role.rolename },${role.remark }--%>
<%--	<a href='<c:url value="/role/edit/${role.roleid }"></c:url>' >编辑</a>--%>
<%--	&nbsp;&nbsp;<a href='<c:url value="/role/delete/${role.roleid }"></c:url>' >删除</a>--%>
<%--	&nbsp;&nbsp;<a href='<c:url value="/role/toresource/${role.roleid }"></c:url>' >分配资源</a>--%>
<%--	</li>--%>
<%--</c:forEach>--%>
<%--</ul>--%>
</div>
	<div class='grid_2'>
		<a href='${ctx }/role/new'>增加角色</a>
	</div>
</div>
<%@ include file="../common/foot.jsp" %></body>
</html>
