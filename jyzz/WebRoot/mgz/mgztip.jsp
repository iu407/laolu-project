<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
<c:if test="${!goods.issale}">
<a href='${ctx }/mgz/upsale/${goods.goodsid}' onclick="changupdown('mgz_div_${goods.goodsid}',this,event)" id='mgz_${goods.goodsid }'>上架</a>
</c:if>

<c:if test="${goods.issale}">
<a href='${ctx }/mgz/downsale/${goods.goodsid}'onclick="changupdown('mgz_div_${goods.goodsid}',this,event)" id='mgz_${goods.goodsid }'>下架</a>
</c:if>
