<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
<%@ include file="../common/meta-upload.jsp" %>
<script type="text/javascript">
		var swfu;
		window.onload = function () {
			swfu = new SWFUpload({
				// Backend Settings
				upload_url: ctx+"/uploadimg/1",

				// File Upload Settings
				file_size_limit : "2 MB",	// 2MB
				file_types : "*.jpg",
				file_types_description : "JPG Images",
				file_upload_limit : "0",

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
				button_image_url : '${ctx}/static/images/SmallSpyGlassWithTransperancy_17x18.png ',
				button_placeholder_id : "spanButtonPlaceholder",
				button_width: 180,
				button_height: 18,
				button_text : '<span class="button">添加头像<span class="buttonSmall">(2 MB Max)</span></span>',
				button_text_style : '.button { font-family: Helvetica, Arial, sans-serif; font-size: 12pt; } .buttonSmall { font-size: 10pt; }',
				button_text_top_padding: 0,
				button_text_left_padding: 18,
				button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
				button_cursor: SWFUpload.CURSOR.HAND,
				
				// Flash Settings
				flash_url : '${ctx}/static/javascripts/swfupload.swf" ',

				custom_settings : {
					upload_target : "divFileProgressContainer"
				},
				
				// Debug Settings
				debug: false
			});
		};
</script>
<div class="grid_9">
<h3>修改你的个人信息</h3>
<form action="${ctx }/user/savemyinfo" method="post">
<div class="imgdiv grid_9">
<c:if test="${fn:length(imgs) > 0  }">
				<c:forEach var="img" items="${imgs}">
				<div class='imgdiv1' id='img_div_${img.imgid}'>
					<span id='img_${img.imgid}'><img src="${ctx }/static/${img.url }" width="80" height="100" class="content" /></span>
					<a href="${ctx }/deleteimg/${img.imgid}" onclick="deletImg('img_div_${img.imgid}',this,event)" class="del_img" id='a_del_${img.imgid}'>删除图片</a>
					<a href="${ctx }/dftimg/${img.imgid}"    onclick="setdftImg('img_${img.imgid}',this,event)" class="def_img"  id='a_dft_${img.imgid}'>默认图片</a>
				</div>
				</c:forEach>
</c:if>	
</div>
<div class="clearfix"></div>
	<table>
	<tr><td class='tdright' >用户名：</td><td>${user.username }</td></tr>
	<tr><td class='tdright' >真实姓名：</td> <td><input type="text" name="realname" id="realname" value="${user.realname }"/></td></tr>
	<tr><td class='tdright' >EMAIL：</td> <td><input type="text" name="email" id="email" value="${user.email }"/></td></tr>
	<tr>
		<td class='tdright' >我的头像：</td> 
		<td>
			<div style="display: inline; border: solid 1px #7FAAFF; background-color: #C5D9FF; padding: 2px;">
						<span id="spanButtonPlaceholder"></span>
			</div>
		</td>
	</tr>
	<tr>
		<td class='tdright'  ></td> 
		<td><input type="submit" class="button" value="修改完成" /></td>
	</tr>
	</table>
<div id="divFileProgressContainer" style="height: 75px;"></div>
		<div id="thumbnails">
</div>
<input type="hidden" class="text" name="userid" value="${user.userid }"/>
</form>

</div>
