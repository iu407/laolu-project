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
		<form method="post" id="loginForm"action="${ctx}/user/save">
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
</div>
	<%@ include file="../common/foot.jsp" %></body>
</html>
