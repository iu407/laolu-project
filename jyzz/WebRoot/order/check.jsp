<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="../common/meta.jsp" %>
<script type="text/javascript">
$(document).ready(function() {
});
</script>
</head>
<body>
<%@ include file="../common/menu.jsp" %>
<div class="container_12">
<div class='grid_12'>
<jsp:include page="/consignee/consigneelist"></jsp:include>
</div>
<div class='grid_12'>
付款方式：目前只支持货到付款！如有疑问请拨打电话：400-499-400。QQ:********
</div>

<div class="grid_12">
		<table>
			<caption>我的订单</caption>
			<thead>
			<tr>
				<th style="width: 100px"></th> 
				<th style="width: 150px">商品名称</th> 
				<th style="width: 60px">单价</th>
				<th style="width: 60px">数量</th>
				<th style="width: 150px">时间(月)</th>
				<th style="width: 150px">合计(元)</th>
			</tr>
			</thead>
			<tbody>
				<c:if test="${not empty cart.items }">
				<c:forEach var="item" items="${cart.items}">
				<tr id='tr_${item.goods.goodsid}'>
					<td>
					<c:if test="${fn:length(item.goods.imgs) > 0  }">
						<c:forEach var="img" items="${item.goods.imgs}" begin="0" end="0">
						<a href="${ctx }/mgz/show/${item.goods.goodsid}"  >
							<img src="${ctx }/static/${img.url }"   width="80" height="100" />
						</a>
						</c:forEach>
					</c:if>
					</td>
					<td><a href="${ctx }/mgz/show/${item.goods.goodsid}">${item.goods.goodsname}</a></td>
					<td>${item.goods.price}</td>
					<td class="editable">${item.quantity}</td>
					<td>${item.monthnum}(月)</td>
					<td>${item.sum}</td>
				</tr>
				</c:forEach>
				<tr id='cart_total'>
					<td colspan="5">
					
					</td>
					<td >合计:
					<fmt:formatNumber value="${cart.totalPrice}" type="currency" pattern=".00元" />
					</td>
				</tr>
				</c:if>
			</tbody>
		</table>
		
		<table>
			<caption>您的详细配送表</caption>
			<thead>
			<tr>
				<th style="width: 100px">配送时间</th> 
				<th style="width: 150px">杂志名称</th> 
			</tr>
			</thead>
			<tbody>
				<c:forEach var="dispatchItem" items="${dispatch.dispatchItemList}">
				<tr id='tr_${dispatchItem.itemNum}'>
					<td><fmt:formatDate value="${dispatchItem.dispatchDate}"  pattern="yyyy年MM月" /></td>
					<td>
					<ul>
						<c:forEach var="goods" items="${dispatchItem.goodsList}">
						<li class='grid_6'>
							<span class='grid_1'>${goods.goodsid}</span>
							<span class='grid_2'>${goods.goodsname}</span>
							<span class='grid_1'>${goods.price}</span>
						</li>
						</c:forEach>
					</ul>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>		
</div>
<div class="grid_12" id='tipinfo' style="display: none; color: red;font: 2em;">
提交之前请确认收货人！
</div>
<div class="grid_12">
提交订单之前检查您的订单
<input type="button" value="确认订单" name="btn_sureorder"  class="btn_sureorder" onclick="submitorder('cartform');"  <c:if test="${empty cart.items   }">disabled=disabled</c:if>	 />
</div>		

<div class="grid_12">
		<a href='${ctx }/cart/mycart'>返回购物车</a>
</div>	
</div>
<div class="container_12">
	<div class='grid_2'>
	</div>
</div>
<div class="container_12">
	<div class='grid_2'>
	</div>
</div>
<%@ include file="../common/foot.jsp" %></body>
</html>
