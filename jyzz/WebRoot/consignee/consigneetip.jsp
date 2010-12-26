<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
<c:if test="${flag == 0}">
<tr id='tr_consignee_${consignee.consigneeid }'>
				<td><input type="radio" name='dft' value='T' ${consignee.dft ? "checked=checked" :""} onclick="setDefaultConsignee('${consignee.consigneeid }',event)"/> ${consignee.consigneename }</td>
				<td>${consignee.province.name }</td>
				<td>${consignee.city.name }</td>
				<td>${consignee.street }</td>
				<td>${consignee.postcode }</td>
				<td>${consignee.tele }</td>
<%--				<td>--%>
<%--				<c:if test="${! consignee.dft}">--%>
<%--				<a href='${ctx }/consignee/delete/${consignee.consigneeid }'id='a_${consignee.consigneeid }' class='deleteconsignee' onclick="ajaxdelete('tr_consignee_${consignee.consigneeid }',this,event)">删除</a>--%>
<%--				</c:if>--%>
<%--				</td>--%>
</tr>

</c:if>

