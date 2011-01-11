<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="../common/adrmeta.jsp"%>
		<style type="text/css">
* {
	font-size: 16px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
}

body {
	background: #FFFFFF;
	margin: 0px;
	padding: 0px;
}

a {
	color: #000000;
	text-decoration: none;
}

a:hover {
	color: #CC0000;
	text-decoration: underline;
}

ul {
	list-style: none;
	margin-left: 1em;
	padding-left: 0;
}

li {
	width: 100%;
	display: block;
	float: left;
	margin: 0;
	padding: 0;
	border-bottom: 1px #333 solid;
	text-align: center;
	padding-bottom: 10px;
}

li img {
	float: left;
}

li a {
	display: block;
	width: 100%;
}
</style>
	</head>
	<body>
		<div class="grid_4">
			<h2>
				${goods.goodsname}
			</h2>
		</div>



	</body>
</html>
