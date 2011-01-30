<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="../common/adrmeta.jsp" %>


</head>
<body>

<c:if test="${fn:length(productData.citys) > 0  }">
<ul class="tracks">
	<c:forEach items="${productData.citys}" var="city" varStatus="sqe">
		
		<c:if test="${city.id == id  }">	
			<c:forEach items="${city.products}" var="product" varStatus="pqe">
			<li>
			<script language="javascript">
				var product_${product.id} = '${ctx }/fe/sp/${product.id}';
			</script>
				<div >
					<img src="${product.small_image}"/>		
					<a onClick="window.demo.clickOnAndroid(product_${product.id})">
						${product.name}
					</a>
				</div>
				<div>结束时间：${product.end_date}</div>
				<div>${product.quantity_sold}人已购买</div>
				<div>${product.vendor_name}</div>
				<div >
			<script language="javascript">
				var tel_${product.id} = '${product.vendor_phone}';
			</script>
				<a onClick="window.demo.calltel(tel_${product.id})">
						联系电话：<span style="font: 25px green normal">${product.vendor_phone}</span>
				</a>
				</div>
			</li>
			</c:forEach>
		</c:if>			
			
	</c:forEach>
</ul>		
</c:if>
<c:if test="${fn:length(productData.citys) <= 0  }">
<jsp:include page="/nodata"></jsp:include>
</c:if>



</body>
</html>
