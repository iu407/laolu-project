<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="../common/adrmeta.jsp" %>
<style type="text/css">
* {
	font-size: 16px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
}

body {
	background: #FFFFFF;
	margin: 0px;
	padding: 0px;
}

a {
	color: #000000;
	text-decoration: none;
}

a:hover {
	color: #CC0000;
	text-decoration: underline;
}

ul {
	list-style:none;
	margin-left: 1em;
	padding-left: 0;
}

li {
	width:100%;
	display:block;
	float: left;
	margin: 0;
	padding: 0;
	border-bottom: 1px #333 solid;
	text-align: center;
	padding-bottom: 10px;
}
li img{
	float: left;
}
li a{
display: block;
width: 100%;
}
</style>

	<script language="javascript">
	</script>

</head>
<body>

<c:if test="${fn:length(productData.citys) > 0  }">
<ul class="tracks">
		<c:forEach items="${productData.citys}" var="city" varStatus="sqe">
			<li>
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
<c:if test="${fn:length(productData.citys) <= 0  }">
	<div class="grid_8 mgzdiv list">
	<a href="${ctx }/mgz/list">没有找到相应的杂志，返回杂志列表</a>
	</div>
</c:if>



</body>
</html>
