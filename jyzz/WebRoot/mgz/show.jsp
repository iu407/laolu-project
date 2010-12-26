<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="../common/meta.jsp" %>
<script type="text/javascript">
</script>
</head>
<body>
<%@ include file="../common/menu.jsp" %>
<div class="container_12">

	<div class="grid_8" >
	<div class="grid_4" ><h2>${goods.goodsname}</h2></div>
	<h3>租借价格：<fmt:formatNumber value="${goods.price}" type="currency" pattern=".00元" /></h3>
	<h3>配送时间：<fmt:formatDate value="${peisongTime}"  pattern="yyyy年MM月" /></h3>
	<a href='${ctx }/cart/addtocart/${goods.goodsid}' class='tocart'>放入购物车</a>
	<div class="clearfix"></div>
			<c:if test="${fn:length(goods.imgs) > 0  }">
				<c:forEach var="img" items="${goods.imgs}">
					<img src="${ctx }/static/${img.url }" width="160" height="200" class="content" />
				</c:forEach>
			</c:if>
	<div class="clearfix"></div>
	<a href='${ctx }/mgz/list' >返回杂志列表</a>
	</div>
	

	<jsp:include page="/commonservice"></jsp:include>
</div>
<%@ include file="../common/foot.jsp" %></body>
</html>
