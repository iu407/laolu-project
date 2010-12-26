<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="../common/meta.jsp" %>
<script type="text/javascript" src="${ctx }/static/javascripts/editTable.js"       language="JavaScript"></script>
</head>
  
<body>
<%@ include file="../common/menu.jsp" %>
<div class='container_12'>
	<div class="grid_12">
	<jsp:include page="/commonservice"></jsp:include>
	
		<div class='grid_8 '>
		<h3>预计配送时间：<fmt:formatDate value="${peisongTime}"  pattern="yyyy年MM月" /></h3>
		<form method="post" id="cartform" action="${ctx}/order/check" >
		<table>
			<caption>我的购物车</caption>
			<thead>
			<tr>
				<th style="width: 100px"></th> 
				<th style="width: 150px">名称</th> 
				<th style="width: 60px">单价</th>
				<th style="width: 60px">数量</th>
				<th style="width: 150px">时间(月)</th>
				<th style="width: 150px">合计(元)</th>
				<th style="width: 180px"></th>
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
					<td><a href="${ctx }/cart/delete/${item.goods.goodsid}" id='a_cart_${item.goods.goodsid}' onclick="dltgoodsformcart('tr_${item.goods.goodsid}',this,event)">取消</a></td>
				</tr>
				</c:forEach>
				<tr id='cart_total'>
					<td colspan="6">
					<input type="button" value="结 &nbsp;&nbsp;算" name="btn_sureorder"  class="btn_sureorder" onclick="checkorder('cartform');"  <c:if test="${empty cart.items   }">disabled=disabled</c:if>	 />
					</td>
					<td >合计:
					<fmt:formatNumber value="${cart.totalPrice}" type="currency" pattern=".00元" />
					</td>
				</tr>
				<tr>
					<td colspan="6"><a href='${ctx }/mgz/list'>继续选择杂志</a></td>
				</tr>
				</c:if>
				
				<c:if test="${empty cart.items }">
				<tr>
					<td colspan="6">购物车中没有物品，请<a href='${ctx }/mgz/list'>选择杂志</a></td>
				</tr>
				</c:if>
			</tbody>
		</table>
			
		</form>
		</div>
	</div>
</div>
	<%@ include file="../common/foot.jsp" %></body>
</html>
