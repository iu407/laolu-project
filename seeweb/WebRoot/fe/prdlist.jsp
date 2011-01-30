<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="../common/adrmeta.jsp" %>

</head>
<body>
<div id='content'>
<c:if test="${fn:length(productData.citys) > 0  }">
		<c:forEach items="${productData.citys}" var="city" varStatus="sqe">
			<div >
				<a  href='${ctx }/fe/scp/${city.id}'   class="licity">
					${city.name}
				</a>
			</div>
		</c:forEach>
</c:if>
<c:if test="${fn:length(productData.citys) <= 0  }">
	<div class="grid_8 mgzdiv list">
		<a href="${ctx }/mgz/list">没有找到相应的杂志，返回杂志列表</a>
	</div>
</c:if>
</div>


</body>
</html>
