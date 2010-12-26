<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="common/meta.jsp" %>
<script type="text/javascript">
$().ready(function() {
	$("#registerform").validate({
		rules: {
			username: "required",
			realname: "required",
			username: {
				required: true,
				minlength: 2
			},
			password: {
				required: true,
				minlength: 5
			},
			repassword: {
				required: true,
				minlength: 5,
				equalTo: "#password"
			},
			email: {
				required: true,
				email: true
			}
		},
		messages: {
			username: {
				required: "请输入用户名",
				minlength: "用户名最少2个字符"
			},
			password: {
				required: "请输入密码",
				minlength: "密码至少5个字符"
			},
			confirm_password: {
				required: "请输入密码",
				minlength: "密码至少5个字符",
				equalTo: "请确认输入相同的密码"
			},
			email: "请输入email"
		}
	});
});
</script>
</head>
  
<body>
<%@ include file="common/menu.jsp" %>
<div class="container_12">
<form method="post" id="registerform" action="${ctx}/user/register">
	<table>
	<tr><td class='tdright' style="width: 100px;text-align: tdright;" >用户名     <span class='nes'>*</span></td> <td  style="width: 520px"><input type="text" name="username" id="username" value=""/></td></tr>
	<tr><td class='tdright' style="width: 100px;text-align: tdright;">真实姓名<span class='nes'>*</span></td> <td  style="width: 520px"><input type="text" name="realname" id="realname" value=""/></td></tr>
	<tr><td class='tdright' style="width: 100px;text-align: tdright;">密码          <span class='nes'>*</span></td> <td  style="width: 520px"><input type="password" name="password" id="password" value=""/></td></tr>
	<tr><td class='tdright' style="width: 100px;text-align: tdright;">重复密码<span class='nes'>*</span></td> <td  style="width: 520px"><input type="password" name="repassword" id="repassword" value=""/></td></tr>
	<tr><td class='tdright' style="width: 100px;text-align: tdright;">邮箱          <span class='nes'>*</span></td> <td  style="width: 520px"><input type="text" name="email" id="email" value=""/></td></tr>
	<tr><td></td><td ><input type="submit" value="提交注册" /></td></tr>
	<tr><td></td><td ><span class='nes'>*</span>为必填字段！</td></tr>
	</table>
</form>
</div>
<%@ include file="common/foot.jsp" %></body>
</html>
