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
<p style="color: red;">提示：*；表示匹配所有字符。</p>
<p>使用这样的路径/user/list</p>
		<form method="post" id="loginForm"action="${ctx}/resource/save">
			<p>路径：<input type="text" name="value" id="value"       value="${resource.value }"/>
			</p>
			<p>名称：<input type="text" name="resourcename" id="resourcename" value="${resource.resourcename}"/>
			</p>
			<p>类型：<input type="text" name="type" id="type" value="URL"/>
			</p>
			<p><input type="submit" value="完成" /></p>
		</form>
</div>
	<%@ include file="../common/foot.jsp" %></body>
</html>
