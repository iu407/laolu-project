<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="../common/meta.jsp" %>
<script type="text/javascript">
$(document).ready(function() {
	$('#test').click(function(){
		jQuery.ajax({
	        url: ctx+'/mgz/getPerson',
	        contentType: "application/json",//application/xml
	        processData: true,//contentType为xml时，些值为false
	        dataType: "html",//json--返回json数据类型；xml--返回xml
	        data: {},
	        success: function(data) {
	        	alert(data);
	        	//var dataObj = eval("("+data+")");
	        	//alert(dataObj.name);
	        	//$('#test').append(data);
	        	//$('#test').append('   name is '+ dataObj.name);
	        },             
	        error:function(e){
	        	alert(e);
	        }
		});
	});
	$('#goods').click(function(){
		jQuery.ajax({
	        url: ctx+'/mgz/getGoods',
	        contentType: "application/json",//application/xml
	        processData: true,//contentType为xml时，些值为false
	        dataType: "html",//json--返回json数据类型；xml--返回xml
	        data: {},
	        success: function(data) {
	        	var dataObj = eval("("+data+")");
	        	//$('#goods').append('   name is '+ dataObj.name);
	        },             
	        error:function(e){
	        	alert(e);
	        }
		});
	});

});
</script>
</head>
<body>
<%@ include file="../common/menu.jsp" %>
<div class="container_12">
<%--	<div id='test'>测试pojo</div>--%>
<%--	<div id='goods'>测试goods</div>--%>
<c:forEach items="${goodses}" var="goods">
<ul id='ul_list'>
	<li><div>久远编号：${goods.jyid}</div><div>${goods.goodsname}</div><div>${goods.issale}</div>
		<div><fmt:formatNumber value="${goods.price}" type="number" pattern="###.##" /></div>
	<a href='${ctx }/mgz/edit/${goods.goodsid}'>编辑</a>
	<a href='${ctx }/mgz/upsale/${goods.goodsid}'>上架</a>
	<a href='${ctx }/mgz/downsale/${goods.goodsid}'>下架</a>
	<a href='${ctx }/cart/addtocart/${goods.goodsid}'>加入购物车</a>
	<c:if test="${fn:length(goods.imgs) > 0  }">
		<c:forEach var="img" items="${goods.imgs}">
			<img src="${ctx }/static/${img.url }" width="80" height="100"/>
		</c:forEach>
	</c:if>
	
	</li>
</ul>
</c:forEach>					
</div>
<div class="container_12">
	<div class='grid_2'>
	<jyzz:auth url="/mgz/new" ><a href='${ctx }/mgz/new'>添加杂志</a></jyzz:auth>
	</div>
</div>
<div class="container_12">
	<div class='grid_2'>
<%--		<a href='${ctx }/mgz/getPerson'>测试json</a>--%>
	</div>
</div>
<%@ include file="../common/foot.jsp" %></body>
</html>
