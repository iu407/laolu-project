<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>

<div class="grid_3" id='usermenu'>
<div id='head_img' class="grid_3 ">
<c:if test="${fn:length(imgs) > 0  }">
				<c:forEach var="img" items="${imgs}">
				<c:if test="${img.dftimg }">
				<div class="grid_2 ">
				<h2>我的头像</h2>
				</div>
				<img src="${ctx }/static/${img.url }" width="80" height="100"  />
				</c:if>
				</c:forEach>
</c:if>
</div>
<h3><a href='${ctx }/user/myorder'>我的订单</a></h3>
<h3><a href='${ctx }/user/myspace'>我的空间</a></h3>
<ul>
	<li><a href='${ctx }/user/myinfo'>修改信息</a></li>
</ul>
<sec:authorize ifAnyGranted="ROLE_admin" >
<h3>网站管理</h3>
<ul>
	<li><a href='${ctx }/adm/list'>商品管理</a></li>
	<jyzz:auth url="/category/list" ><li><a href='${ctx }/category/list'>商品分类</a></li></jyzz:auth>
	<sec:authorize ifAnyGranted="ROLE_admin" ><li><a href='${ctx }/user/list'>用户管理</a></li></sec:authorize>
	<jyzz:auth url="/role/list" ><li><a href='${ctx }/role/list'>角色管理</a></li></jyzz:auth>
	<sec:authorize ifAnyGranted="ROLE_admin" ><li><a href='${ctx }/resource/list' title="资源管理">资源管理</a></li></sec:authorize>
	<li><a href='#'>订单管理</a></li>
	<li><a href='#'>销售统计</a></li>
	<li><a href='#'>图片管理</a></li>

<%--	<li><a href='${ctx }/importprdcsv' title="导入商品csv文件">导入商品csv文件</a></li>--%>
</ul>
</sec:authorize>
<ul>
	<c:if test="${not empty SPRING_SECURITY_CONTEXT}">
		<li><a href='${ctx }/consignee/list'>收货人管理</a></li>
	</c:if>
</ul>
</div>