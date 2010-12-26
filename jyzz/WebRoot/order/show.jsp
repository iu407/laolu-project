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
<div class="grid_9">
订单编号：${order.ordersn}
创建时间：<fmt:formatDate value="${order.createtime}"  pattern="yyyy年MM月dd日" />
总价格：<fmt:formatNumber value="${order.totalPrice}" type="currency" pattern=".00元" />

</div>
<div class="grid_9">
<c:forEach items="${order.orderItems}" var="item">
<c:set var="goods" value="${item.goods}"></c:set>
<div class="grid_4" style="border: 1px solid #eee;">

<c:if test="${fn:length(goods.imgs) > 0  }">
				<c:forEach var="img" items="${goods.imgs}" begin="0" end="0">
				<a href="${ctx }/mgz/show/${goods.goodsid}" class="thumb" >
					<img src="${ctx }/static/${img.url }"  width="80" height="100" class="content"/>
				</a>
				</c:forEach>
</c:if>

		<div class="grid_2">
		<h2><a href="${ctx }/mgz/show/${goods.goodsid}" id="a_${goods.goodsid}">${goods.goodsname}</a></h2>
		<div class="grid_3">单价${item.price}</div>
		<div class="grid_3">数量${item.quantity}</div>
		</div>

</div>
</c:forEach>
<table>
<caption>详细配送单</caption>
	<c:forEach items="${dispatch.dispatchItemList}" var="dispatchItem">
	<tr>
		<td><fmt:formatDate value="${dispatchItem.dispatchDate}"  pattern="yyyy年MM月" /></td>
		<td>${dispatchItem.sequence}</td>
		<td>${dispatchItem.itemNum}</td>
		<td>${dispatchItem.username}</td>
		<td>
		<c:forEach items="${dispatchItem.dispatchItemGoodsList}" var="itemGoods">
		${itemGoods.price}
		${itemGoods.num}
		</c:forEach>
		</td>
	</tr>
	</c:forEach>
</table>
</div>

<div class='grid_2' ><a href="${ctx }/user/myorder">返回我的订单</a></div>

</div>


</div>
<%@ include file="../common/foot.jsp" %></body>
</html>
