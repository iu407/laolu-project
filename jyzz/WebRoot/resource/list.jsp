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

<div class="grid_9">
<a href='${ctx }/resource/new'>增加资源</a>
<table>
	<caption>资源列表</caption>
	<thead>
	<tr>
		<th>资源</th> 
		<th>资源类型</th> 
		<th>名称</th> 
		<th>操作</th>
	</tr>
	</thead>
	<c:forEach items="${resources}" var="resource">
	<tr id="li_${resource.resourceid }">
		<td>${resource.value }</td>
		<td>${resource.type }</td>
		<td>${resource.resourcename }</td>
		<td>
			<a href='<c:url value="/resource/edit/${resource.resourceid}"></c:url>' >编辑</a>
			&nbsp;
			<a href='<c:url value="/resource/delete/${resource.resourceid }"></c:url>' >删除</a>
		</td>
	</tr>
	</c:forEach>
</table>
<a href='${ctx }/resource/new'>增加资源</a>
</div>
</div>
<%@ include file="../common/foot.jsp" %></body>
</html>
