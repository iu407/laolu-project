<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="taglib.jsp" %>
<%
//String ctx = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort()+  request.getContextPath();
//pageContext.setAttribute("ctx",ctx);
//session.setAttribute("ctx",ctx);
//application.setAttribute("ctx",ctx);
//application.setAttribute("ctx","http://192.168.1.100");
%>
<script type="text/javascript">
$(document).ready(function(){
	document.onclick = dd_close;
	$('#dd > li').bind('mouseover', dd_open);
	$('#dd > li').bind('mouseout',  dd_timer);
});
</script>
<div class="container_12" id='header'>
<div id="head" class="grid_3">
	<h1>
		杂志阅读
	</h1>
	<h2>
		新体验
	</h2>
</div>
	<div id="nav"  >
		<ul id="dd" class="dd">
<%--			<sec:authorize ifAnyGranted="ROLE_admin" ><li><a href='${ctx }/adm/admin'>网站管理</a></li></sec:authorize>--%>
			<li><a href='${ctx }/' id='home'>首页</a></li>
			<li><a href='${ctx }/mgz/list' id="listmgz">浏览杂志</a></li>
			<li><a href='${ctx }/cart/mycart' id='mycart'>我的购物车<span class='nes'>(${fn:length( cart.items) })</span></a></li>
			<c:if test="${empty SPRING_SECURITY_CONTEXT }"><li><a href='${ctx }/login.jsp' id='mylogin' >登陆</a></li>
			</c:if>
			<c:if test="${empty SPRING_SECURITY_CONTEXT }"><li><a href='${ctx }/register.jsp'>注册</a></li></c:if>
			<c:if test="${not empty SPRING_SECURITY_CONTEXT}">
			<li class="parent "><a href='${ctx }/user/myspace' id='myhome' >我的空间</a>
				<ul>
					<li><a href='${ctx }/user/myorder'>我的订单</a></li>
					<li><a href='${ctx }/user/myinfo'>修改信息</a></li>
				<sec:authorize ifAnyGranted="ROLE_admin" >
					<li><a href='${ctx }/adm/list'>商品管理</a></li>
					<jyzz:auth url="/category/list" ><li><a href='${ctx }/category/list'>商品分类</a></li></jyzz:auth>
					<sec:authorize ifAnyGranted="ROLE_admin" ><li><a href='${ctx }/user/list'>用户管理</a></li></sec:authorize>
					<jyzz:auth url="/role/list" ><li><a href='${ctx }/role/list'>角色管理</a></li></jyzz:auth>
					<sec:authorize ifAnyGranted="ROLE_admin" ><li><a href='${ctx }/resource/list' title="资源管理">资源管理</a></li></sec:authorize>
					<li><a href='#'>订单管理</a></li>
					<li><a href='#'>销售统计</a></li>
					<li><a href='#'>图片管理</a></li>
				</sec:authorize>
					<c:if test="${not empty SPRING_SECURITY_CONTEXT}">
						<li><a href='${ctx }/consignee/list'>收货人管理</a></li>
					</c:if>
				</ul>
			</li>
			
			<li><a href='${ctx }/j_spring_security_logout' id="logout">退出</a></li>
			<li><a href='${ctx }/login' style="color: red;">欢迎：<sec:authentication property="name" /></a></li>
			</c:if>
		</ul>
		
	</div>
	<div class="search">
			    <form method="get" id="searchform" action="${ctx }/search">
			        <fieldset>
			            <input name="s" type="text" onfocus="if(this.value=='书名/久远ID') this.value='';" onblur="if(this.value=='') this.value='书名/久远ID';" value='${empty s ?  "书名/久远ID": s}' />
			        	<button type="submit"></button>
			        </fieldset>
			    </form>
	</div>		
</div>
<%
	String msg = request.getParameter("msg");
	if(msg != null){%>
	<div class="container_12" >
		<div id="msg" class='grid_12'><%=msg %></div>
	</div>
<%	}
%>
