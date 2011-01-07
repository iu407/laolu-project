<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="../common/meta.jsp" %>
	<script language="javascript">
	    /* This function is invoked by the activity */
		function wave() {
		    alert("1");
			document.getElementById("droid").src="android_waving.png";
			alert("2");
		}
		var abc = ctx;
	</script>

</head>
<body>

<a onClick="window.demo.clickOnAndroid(abc)" >
	<div style="width:80px;
			margin:0px auto;
			padding:10px;
			text-align:center;
			border:2px solid #202020;" >
				<img id="droid" src="android_normal.png"/><br />
				我是卢卫华
	</div>
	</a>

</body>
</html>
