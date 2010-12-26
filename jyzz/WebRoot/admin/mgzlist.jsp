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

<jsp:include page="/user/usermenu" flush="true"></jsp:include>
<div class="grid_9">
<form action="" name="pager" method="post" >
<a href='${ctx }/mgz/new'>增加商品</a>		|
<a href='${ctx }/mgz/edit/${goods.goodsid}'>商品分类</a>
<table>
	<caption>商品列表</caption>
	<thead>
	<tr>
		<th>久远编号</th> 
		<th>名称</th> 
		<th>类型</th> 
		<th>价格</th>
		<th>操作</th>
	</tr>
	</thead>
	<tbody>
	<tr id="tableb">
		<th><input name="demand[jyid]" type="text" value="${goodsPage.pager.demand['jyid'] }"/></th> 
		<th><input name="demand[goodsname]" type="text" value="${goodsPage.pager.demand['goodsname'] }"/></th> 
		<th></th> 
		<th>
		<input name="demand[priceMin]" type="text" value="${goodsPage.pager.demand['priceMin'] }" style="width: 50px;"/>
		-
		<input name="demand[priceMax]" type="text" value="${goodsPage.pager.demand['priceMax'] }" style="width: 50px;"/>
		</th>
		<th>
			<button type="submit" >查询</button>
			<button type="button" onclick="clearform('tableb');">清空</button>
		</th>
	</tr>
	<c:forEach items="${goodsPage.results}" var="goods">
	<tr id='tr_${goods.goodsid}'>
		<td>${goods.jyid}</td>
		<td>${goods.goodsname}</td>
		<td>${goods.category.catname}</td>
		<td><fmt:formatNumber value="${goods.price}" type="currency" pattern=".00元" /></td>
		<td>
			<a href='${ctx }/mgz/edit/${goods.goodsid}'>编辑</a>
			<span id='mgz_div_${goods.goodsid}'>
			<c:if test="${!goods.issale}"><a href='${ctx }/mgz/upsale/${goods.goodsid}' onclick="changupdown('mgz_div_${goods.goodsid}',this,event)" id='mgz_${goods.goodsid }'>上架</a></c:if>
			<c:if test="${goods.issale}"><a href='${ctx }/mgz/downsale/${goods.goodsid}'onclick="changupdown('mgz_div_${goods.goodsid}',this,event)" id='mgz_${goods.goodsid }'>下架</a></c:if>
			</span>
		</td>
	</tr>	
	</c:forEach>
	${goodsPage.pager}
	</tbody>
</table>
<a href='${ctx }/mgz/new'>增加商品</a>	
</form>
</div>
</div>
<%@ include file="../common/foot.jsp" %></body>
</html>
