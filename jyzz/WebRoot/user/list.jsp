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

<div class="container_9">
<form action="" name="pager" method="post" >
<a href='<c:url value="/user/new"></c:url>' >增加用户</a>
<table>
	<caption>商品列表</caption>
	<thead>
	<tr>
		<th>id</th> 
		<th>用户名</th> 
		<th>真实姓名</th> 
		<th>EMAIL</th>
		<th>操作</th>
	</tr>
	<tr id="tableb">
		<th></th> 
		<th><input name="demand[username]" type="text" value="${userspage.pager.demand['username'] }"/></th>
		<th><input name="demand[realname]" type="text" value="${userspage.pager.demand['realname'] }"/></th>
		<th><input name="demand[email]" type="text" value="${userspage.pager.demand['email'] }"/></th> 
		<th>
			<button type="submit" >查询</button><button type="button" onclick="clearform('tableb');">清空</button>
		</th>
	</tr>
	</thead>
		<c:forEach items="${userspage.results}" var="user">
	<tr id='tr_${user.userid }'>
		<td>${user.userid }</td>
		<td>${user.username }</td>
		<td>${user.realname }</td>
		<td>${user.email }</td>
		<td>
			<jyzz:auth url="/user/delete/" ><a href='<c:url value="/user/delete/${user.userid }"></c:url>' id='a_${user.userid }' onclick="ajaxdelete('tr_${user.userid }',this,event)">取消</a></jyzz:auth>
			&nbsp;
			<jyzz:auth url="/user/torole/" ><a href='<c:url value="/user/torole/${user.userid }"></c:url>' >分配角色</a></jyzz:auth>
		</td>
	</tr>
</c:forEach>
</table>
</form>
</div>
${userspage.pager}
<a href='<c:url value="/user/new"></c:url>' >增加用户</a>
</div>
	<%@ include file="../common/foot.jsp" %></body>
</html>
