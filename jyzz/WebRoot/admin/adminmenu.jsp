<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>

<div id="right">
<h3>网站管理</h3>
<ul>
	<li><a href='${ctx }/adm/list'>商品管理</a></li>
	<jyzz:auth url="/category/list" ><li><a href='${ctx }/category/list'>商品分类</a></li></jyzz:auth>
	<sec:authorize ifAnyGranted="ROLE_admin" ><li><a href='${ctx }/user/list'>用户管理</a></li></sec:authorize>
	<jyzz:auth url="/role/list" ><li><a href='${ctx }/role/list'>角色管理</a></li></jyzz:auth>
	<sec:authorize ifAnyGranted="ROLE_admin" ><li><a href='${ctx }/resource/list' title="资源管理">资源管理</a></li></sec:authorize>
</ul>
</div>
