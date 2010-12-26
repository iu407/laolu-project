<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="../common/meta.jsp" %>
<%@ include file="../common/meta-upload.jsp" %>
<script type="text/javascript">
		var swfu;
		window.onload = function () {
			swfu = new SWFUpload({
				// Backend Settings
				upload_url: ctx+"/uploadcsv",
				// File Upload Settings
				file_size_limit : "5 MB",	// 2MB
				file_types : "*.csv",
				file_types_description : "CSV File",
				file_upload_limit : "0",
				file_queue_limit: "1",
				// Event Handler Settings - these functions as defined in Handlers.js
				//  The handlers are not part of SWFUpload but are part of my website and control how
				//  my website reacts to the SWFUpload events.
				file_queue_error_handler : fileQueueError,
				file_dialog_complete_handler : fileDialogComplete,
				upload_progress_handler : uploadProgress,
				upload_error_handler : uploadError,
				upload_success_handler : uploadSuccess,
				upload_complete_handler : uploadComplete,

				// Button Settings
				button_image_url : '${ctx}/static/images/SmallSpyGlassWithTransperancy_17x18.png" ',
				button_placeholder_id : "spanButtonPlaceholder",
				button_width: 180,
				button_height: 18,
				button_text : '<span class="button">添加图片<span class="buttonSmall">(2 MB Max)</span></span>',
				button_text_style : '.button { font-family: Helvetica, Arial, sans-serif; font-size: 12pt; } .buttonSmall { font-size: 10pt; }',
				button_text_top_padding: 0,
				button_text_left_padding: 18,
				button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
				button_cursor: SWFUpload.CURSOR.HAND,
				
				flash_url : '${ctx}/static/javascripts/swfupload.swf" ',

				custom_settings : {
					upload_target : "divFileProgressContainer"
				},
				debug: false
			});
		};
</script>
</head>
  
  <body>
<%@ include file="../common/menu.jsp" %>
<div class="container_12">
<jsp:include page="/user/usermenu"></jsp:include>
<div class="container_9">
<p style="color: red;">提示：选择商品的csv文件</p>
		<form method="post" id="loginForm"action="${ctx}/csvsave">
		<div style="display: inline; border: solid 1px #7FAAFF; background-color: #C5D9FF; padding: 2px;">
				<span id="spanButtonPlaceholder"></span>
			</div>
		<div id="divFileProgressContainer" style="height: 75px;"></div>
		<p><a href="${ctx }/adm/list">返回商品管理</a></p>
		<div id="thumbnails">
		</div>
		
		</form>
</div></div>
	<%@ include file="../common/foot.jsp" %></body>
</html>
