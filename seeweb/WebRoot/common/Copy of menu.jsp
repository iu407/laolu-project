<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="taglib.jsp" %>
<%
String ctx = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort()+  request.getContextPath();
pageContext.setAttribute("ctx",ctx);
%>
<div id="upbg"></div>

<div id="outer">
	<div id="header">
		<div id="headercontent">
			<h1>杂志吧<sup>借阅杂志新体验</sup></h1>
			<h2>老卢设计</h2>
		</div>
	</div>


	<form method="post" action="">
		<div id="search">
			<input type="text" class="text" maxlength="64" name="keywords" value="ISBN/杂志名"/>
			<input type="submit" class="submit" value="Search" />
		</div>
	</form>
	<div id="headerpic"></div>
	
	<div id="menu">
		<ul>
			<li><a href='${ctx }/'>首页</a></li>
			<li><a href='${ctx }/mgz/list' id="listmgz">浏览杂志</a></li>
<%--			<li><a href='${ctx }/mgz/list' onclick="ajaxcall(this)" id="listmgz">浏览杂志</a></li>--%>
			<li><a href='${ctx }/cart/mycart'>我的购物车</a></li>
			<jyzz:auth url="/category/list" ><li><a href='${ctx }/category/list'>分类</a></li></jyzz:auth>
			<sec:authorize ifAnyGranted="ROLE_admin" ><li><a href='${ctx }/user/list'>用户</a></li></sec:authorize>
			<jyzz:auth url="/role/list" ><li><a href='${ctx }/role/list'>角色</a></li></jyzz:auth>
			<sec:authorize ifAnyGranted="ROLE_admin" ><li><a href='${ctx }/resource/list'>资源</a></li></sec:authorize>
			<c:if test="${empty SPRING_SECURITY_CONTEXT }"><li><a href='${ctx }/login.jsp'>登陆</a></li></c:if>
			<c:if test="${empty SPRING_SECURITY_CONTEXT }"><li><a href='${ctx }/register.jsp'>注册</a></li></c:if>
			<c:if test="${not empty SPRING_SECURITY_CONTEXT}"><li><a href='${ctx }/j_spring_security_logout'>退出</a></li>
			<li><sec:authentication property="name" ></sec:authentication></li>
			</c:if>
		</ul>
	</div>
	<div id="menubottom"></div>
	<c:if test="${!empty msg}"><div id="msg">${msg }</div></c:if>

