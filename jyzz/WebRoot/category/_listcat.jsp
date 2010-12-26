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
<ul>
<c:forEach items="${categorys}" var="category">
	<li id="li_${category.categoryid }">ID:${category.categoryid },类型:${category.cattype },类型名称:${category.catname },父类型:${category.fatherid }
<%--	&nbsp;<a href='<c:url value="/category/edit/${category.categoryid}"></c:url>' >编辑</a>--%>
<%--	&nbsp;<a href='<c:url value="/category/delete/${category.categoryid }"></c:url>' >删除</a>--%>
	</li>
</c:forEach>
</ul>
</div>
	<div class='grid_2'>
		<a href='${ctx }/category/new' title="添加分类">添加分类</a>
		删除修改没有做
	</div>
</div>
<%@ include file="../common/foot.jsp" %></body>
</html>
