<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>

<div class='div_middle'>
<c:forEach items="${listWithPager.results}" var="goods">
	<div class='grid_4'>
	<c:if test="${fn:length(goods.imgs) > 0  }">
					<c:forEach var="img" items="${goods.imgs}" begin="0" end="0">
					<a href="${ctx }/mgz/show/${goods.goodsid}" >
						<img src="${ctx }/static/${img.url }"   width="160" height="200" class="content" />
					</a>
					</c:forEach>
	</c:if>
		<div class='grid_3'>
		${goods.goodsname}
		</div>
	</div>
</c:forEach>
</div>