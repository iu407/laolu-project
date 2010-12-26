<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
<c:if test="${fn:length(consignees) <= 0}">
<input type="hidden" name="consigneeflag" id="consigneeflag" value="1"/>
</c:if>
<div class="grid_9">
<table>
	<caption>收货人信息</caption>
	<thead>
	<tr id='tr_consignee'>
		<th>收货人</th> 
		<th>省份</th> 
		<th>市</th> 
		<th>街道</th> 
		<th>邮编</th>
		<th>电话</th>
<%--		<th>操作</th>--%>
	</tr>
	</thead>
		<c:forEach items="${consignees}" var="consignee">
			<tr id='tr_consignee_${consignee.consigneeid }'>
				<td><input type="radio" name='dft' value='${consignee.consigneeid}' ${consignee.dft ? "checked=checked" :""} onclick="setDefaultConsignee('${consignee.consigneeid }',event)"/> ${consignee.consigneename }</td>
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
		</c:forEach>
	
</table>
<div class="grid_9">
<a href='${ctx }/consignee/new' onclick="addConfignee('div_consignee',this,event)">增加收货人</a>
<a href='${ctx }/consignee/list'>收货人管理</a>
</div>
<div class="grid_8 consignee" id='div_consignee'>
	<form method="post" id="consigneeForm"  action="${ctx }/consignee/save" >
				<div class="grid_8">
					<div class="grid_1 left">收货人</div>
					<div class="grid_6">
					<input type="text" name="consigneename" id="consigneename" class='required'  maxlength="255" />
					</div>
				</div>
				<div class="grid_8">
					<div class="grid_8">
					<select name="provinceid">
						<c:forEach items="${provinces}" var="province">
						<option value="${province.areaid }">${province.name }</option>
						</c:forEach>
					</select>省
					<select name="cityid">
						<c:forEach items="${citys}" var="city">
						<option value="${city.areaid }">${city.name }</option>
						</c:forEach>
					</select>市
					<input type="text" name="street" id="street" class='required'  maxlength="255" />街道
					</div>
				</div>
				<div class="grid_8">
					<div class="grid_1">邮编</div><input type="text" name="postcode" id="postcode" class='required number'  maxlength="6" />
				</div>
				<div class="grid_8">
					<div class="grid_1">电话</div><input type="text" name="tele" id="tele" class='required'  maxlength="20"/>
				</div>
				<div class="grid_8">
				<input type="button" value="完成" onclick="submitform('consigneeForm','tr_consignee',event)"/>
				</div>
				<div class="grid_8">
				<a href='${ctx }' onclick="hideConfignee('div_consignee',this,event)">收起</a>
				</div>
	</form>
</div>
</div>
