<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
<c:set var="title" value="分类"></c:set>
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
<table>
	<caption>分类</caption>
	<thead>
	<tr>
		<th>分类ID</th> 
		<th>类型</th> 
		<th>名称</th> 
		<th>父ID</th>
	</tr>
	</thead>
	<c:forEach items="${categorys}" var="category">
	<tr id="li_${category.categoryid }">
		<td>${category.categoryid }</td>
		<td>${category.cattype }</td>
		<td>${category.catname }</td>
		<td>${category.fatherid }</td>
	</tr>
	</c:forEach>
</table>
</div>
	<div class='grid_2'>
		<a href='${ctx }/category/new' title="添加分类">添加分类</a>
		删除修改没有做
	</div>
</div>
<%@ include file="../common/foot.jsp" %></body>
</html>
