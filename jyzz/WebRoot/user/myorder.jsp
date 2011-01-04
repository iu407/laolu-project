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
<form action="" name="pager" method="post" >
<table>
	<caption>我的所有订单</caption>
	<thead>
	<tr>
		<th>订单编号</th> 
		<th>时间</th>
		<th>价格</th>
		<th>操作</th>
	</tr>
	</thead>
	<tbody>
	<tr id="tableb">
	</tr>
	<c:forEach items="${orderpage.results}" var="order">
	<tr id='tr_${order.orderid}'>
		<td><a href='${ctx }/order/show/${order.orderid}' title="${order.ordersn} 详细信息">${order.ordersn}</a></td>
		<td><fmt:formatDate value="${order.createtime}"  pattern="yyyy-MM-dd hh/mm" /></td>
		<td><fmt:formatNumber value="${order.totalPrice}" type="currency" pattern=".00元" /></td>
		<td>
<%--			<a href='${ctx }/mgz/edit/${order.orderid}'>编辑</a>--%>
<%--			<span id='mgz_div_${order.orderid}'>--%>
<%--			<c:if test="${!order.issale}"><a href='${ctx }/mgz/upsale/${order.orderid}' onclick="changupdown('mgz_div_${order.orderid}',this,event)" id='mgz_${order.orderid }'>上架</a></c:if>--%>
<%--			<c:if test="${order.issale}"><a href='${ctx }/mgz/downsale/${order.orderid}'onclick="changupdown('mgz_div_${order.orderid}',this,event)" id='mgz_${order.orderid }'>下架</a></c:if>--%>
<%--			</span>--%>
		</td>
	</tr>
	<tr>
		<td colspan="6">
		
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
			</div>		
		
		</td>
	</tr>
	</c:forEach>
	${orderpage.pager}
	</tbody>
</table>

</form>
</div>


</div>
<%@ include file="../common/foot.jsp" %></body>
</html>
