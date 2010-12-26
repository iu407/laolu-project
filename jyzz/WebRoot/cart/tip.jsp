<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
<c:if test="${flag == 0}">
<td colspan="6">
					<input type="button" value="确认订单" name="btn_sureorder"  class="btn_sureorder" onclick="sureorder('cartform');"  <c:if test="${empty cart.items   }">disabled=disabled</c:if>	 />
					</td>
					<td >合计:
					<fmt:formatNumber value="${cart.totalPrice}" type="currency" pattern=".00元" />
</td>
</c:if>

