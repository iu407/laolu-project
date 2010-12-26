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

<div class="grid_8" id='mgzlist'>

<form action="" name="pager" method="post" >
		<c:forEach items="${goodsPage.results}" var="goods">
		<div class="grid_8 mgzdiv">
		<a href="http://sight.wpshower.com/?p=102" class="thumb"><img width="290" height="290" src="http://sight.wpshower.com/wp-content/uploads/2010/11/marc_swanson_glitter-290x290.jpg" class="attachment-thumbnail wp-post-image" alt="Lorem ipsum dolor sit amet, consectetur elit" title="Lorem ipsum dolor sit amet, consectetur elit" /></a>
		<h2>${goods.goodsname}</h2>
		<a href="#">
			<c:if test="${fn:length(goods.imgs) > 0  }">
				<c:forEach var="img" items="${goods.imgs}">
					<img src="${ctx }/static/${img.url }" width="80" height="100" class="content" />
				</c:forEach>
			</c:if>		
		</a>
		说明：${goods.detail}
		<p>久远编号：${goods.jyid}</p>
		<h3>租借价格：<fmt:formatNumber value="${goods.price}" type="currency" pattern=".00元" /></h3>
		<h3>配送时间：<fmt:formatDate value="${peisongTime}"  pattern="yyyy年MM月" /></h3>
		<p>
			<jyzz:auth url="/mgz/edit/" ><a href='${ctx }/mgz/edit/${goods.goodsid}'>编辑</a></jyzz:auth>
<%--			<jyzz:auth url="/mgz/upsale/" ><c:if test="${!goods.issale}"><a href='${ctx }/mgz/upsale/${goods.goodsid}'>上架</a></c:if></jyzz:auth>--%>
<%--			<jyzz:auth url="/mgz/downsale/" ><c:if test="${goods.issale}"><a href='${ctx }/mgz/downsale/${goods.goodsid}'>下架</a></c:if></jyzz:auth>--%>
			<a href='${ctx }/cart/addtocart/${goods.goodsid}'>放入购物车</a>
		</p>
		</div>
		</c:forEach>
		<div class="clearfix"></div>
		${goodsPage.pager}
</form>
</div>

<jsp:include page="/commonservice"></jsp:include>
</div>
<%@ include file="../common/foot.jsp" %></body>
</html>
