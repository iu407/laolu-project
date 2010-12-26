<%@ page language="java"  pageEncoding="UTF-8" %>
<%@ include file="common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="common/meta.jsp" %>
</head>
<body>
<%@ include file="common/menu.jsp" %>  
<div class="container_12">
<div class="grid_12">
	<form method="post" id="loginForm" action="${ctx}/j_spring_security_check">
	<table>
	<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
		<tr ><td></td><td class='left'><font color="red">登录失败，请重新输入用户名密码</font></td></tr>
	</c:if>
	<tr><td class='tdright'  >用户名：</td> <td><input type="text" name="j_username" id="j_username" /></td></tr>
	<tr><td class='tdright'  >密码：</td> <td><input type="password" name="j_password" id="j_password" /></td></tr>
	<tr><td></td><td ><input type="checkbox" name="_spring_security_remember_me" id='reme'/>
        <label for='reme' id='remelabel'>两周之内自动登陆</label></td></tr>
    <tr><td></td><td><input type="submit" value="登陆" /></td></tr>
    <tr><td></td><td><a href='${ctx}/register.jsp'>注册</a>
						<a href='${ctx}/forgetpword'>忘记密码</a></td></tr>
	</table>
    </form>
	</div>     
</div>   
<%@ include file="common/foot.jsp" %></body>
</html>
