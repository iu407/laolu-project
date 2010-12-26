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

<div class="grid_8" >
<form action="" name="pager" method="post" >
<c:if test="${fn:length(goodsPage.results) > 0  }">
		<c:forEach items="${goodsPage.results}" var="goods">
		<div class="grid_8 mgzdiv list">
		<div class="grid_4 post">
			<c:if test="${fn:length(goods.imgs) > 0  }">
				<c:forEach var="img" items="${goods.imgs}" begin="0" end="0">
				<a href="${ctx }/mgz/show/${goods.goodsid}" class="thumb" >
					<img src="${ctx }/static/${img.url }"   style='max-height: 290px;'/>
				</a>
				</c:forEach>
			</c:if>		
		</div>
		<div class="grid_3">
		<h2><a href="${ctx }/mgz/show/${goods.goodsid}" id="a_${goods.goodsid}">${goods.goodsname}</a></h2>
		说明：${goods.detail}
		<p>久远编号：${goods.jyid}</p>
		<h3>租借价格：<fmt:formatNumber value="${goods.price}" type="currency" pattern=".00元" /></h3>
		<h3>配送时间：<fmt:formatDate value="${peisongTime}"  pattern="yyyy年MM月" /></h3>
		<p>
<%--			<jyzz:auth url="/mgz/edit/" ><a href='${ctx }/mgz/edit/${goods.goodsid}'>编辑</a></jyzz:auth>--%>
<%--			<jyzz:auth url="/mgz/upsale/" ><c:if test="${!goods.issale}"><a href='${ctx }/mgz/upsale/${goods.goodsid}'>上架</a></c:if></jyzz:auth>--%>
<%--			<jyzz:auth url="/mgz/downsale/" ><c:if test="${goods.issale}"><a href='${ctx }/mgz/downsale/${goods.goodsid}'>下架</a></c:if></jyzz:auth>--%>
			<a href='${ctx }/cart/addtocart/${goods.goodsid}' class='tocart'>放入购物车</a>
		</p>
		</div>
		</div>
		</c:forEach>
		<div class="clearfix"></div>
		${goodsPage.pager}
</c:if>
<c:if test="${fn:length(goodsPage.results) <= 0  }">
	<div class="grid_8 mgzdiv list">
	<a href="${ctx }/mgz/list">没有找到相应的杂志，返回杂志列表</a>
	</div>
</c:if>
</form>
</div>

<jsp:include page="/commonservice"></jsp:include>
</div>
<%@ include file="../common/foot.jsp" %></body>
</html>
