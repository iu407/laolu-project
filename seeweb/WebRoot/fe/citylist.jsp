<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="../common/adrmeta.jsp" %>

</head>
<body>

<c:if test="${fn:length(citys) > 0  }">
<ul class="tracks">
		<c:forEach items="${citys}" var="city" varStatus="sqe">
			<li class="licity">
	<script language="javascript">
		var city_${city.id} = '${ctx }/adr/s/${city.id}';
	</script>			
				<a onClick="window.demo.clickOnAndroid(city_${city.id})">
					${city.name}
				</a>
			</li>
		</c:forEach>
</ul>		
</c:if>
<c:if test="${fn:length(citys) <= 0  }">
	<div class="grid_8 mgzdiv list">
	<a href="${ctx }/mgz/list">没有找到相应的杂志，返回杂志列表</a>
	</div>
</c:if>



</body>
</html>
