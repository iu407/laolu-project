<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
<%@ include file="../common/meta.jsp" %>
  </head>
  
  <body>
<%@ include file="../common/menu.jsp" %>
<div>
<ul>
<c:forEach items="${users}" var="user">
	<li id="li_${user.userid }">${user.userid },${user.username },${user.realname },${user.email }
	<a href='<c:url value="/user/delete/${user.userid }"></c:url>' >删除</a>
	<button onclick="deleteUser(${user.userid });">隐藏</button>
	</li>
</c:forEach>
</ul>
</div>
		<form method="post" id="loginForm"action="${pageContext.request.contextPath}/user/save">
			<p>USER NAME：<input type="text" name="username" id="username" />
			</p>
			<p>REAL NAME：<input type="text" name="realname" id="realname" />
			</p>
			<p>PASSWORD：<input type="password" name="password" id="password" />
			</p>
			<p>EMAIL：<input type="text" name="email" id="email" />
			</p>
			<p><input type="submit" value="submit" /></p>
		</form>
	<%@ include file="../common/foot.jsp" %></body>
</html>
