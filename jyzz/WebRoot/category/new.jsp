<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="../common/meta.jsp" %>
<script type="text/javascript"language="JavaScript">
$(document).ready(function() {
	$("#show").click(function(){$("#clist").show();});
	$("#hide").click(function(){$("#clist").hide();});
});
</script>
</head>

  <body>
<%@ include file="../common/menu.jsp" %>
<div class="container_12">
<div class="grid_1 button" id='show' >显示分类</div>
<div class="grid_1 button" id='hide' >隐藏分类</div>
</div>

<div class="container_12">
<ul id='clist'>
<c:forEach items="${categorys}" var="category">
	<li id="li_${category.categoryid }">id:${category.categoryid },catetype:${category.cattype },catname:${category.catname },fatherid:${category.fatherid }
	&nbsp;<a href='<c:url value="/category/edit/${category.categoryid}"></c:url>' >编辑</a>
	&nbsp;<a href='<c:url value="/category/delete/${category.categoryid }"></c:url>' >删除</a>
	</li>
</c:forEach>
</ul>
</div>
<div class="container_12">
<p style="color: red;">您可以在这里添加分类</p>
		<form method="post" id="loginForm"action="${ctx}/category/save">
			<p><label>类型：</label><input type="text" name="cattype" id="cattype"       value="${category.cattype }"/>
			</p>
			<p><label>名称：</label><input type="text" name="catname" id="catname" value="${category.catname}"/>
			</p>
			<p><label>父类型：</label><input type="text" name="fatherid" id="fatherid"   value="${category.fatherid}"/>
			</p>
			<p><input type="submit" value="submit" /></p>
		</form>
</div>
	<%@ include file="../common/foot.jsp" %></body>
</html>
