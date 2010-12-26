var userAgent = navigator.userAgent.toLowerCase();
var is_opera = userAgent.indexOf('opera') != -1 && opera.version();
var is_moz = (navigator.product == 'Gecko') && userAgent.substr(userAgent.indexOf('firefox') + 8, 3);
var is_ie = (userAgent.indexOf('msie') != -1 && !is_opera) && userAgent.substr(userAgent.indexOf('msie') + 5, 3);
var is_mac = userAgent.indexOf('mac') != -1;



function deleteUser(id){
	var url = ctx+'/user/delete/'+id;
//	$.ajax({
//		 type: "GET",
//		 url: url,
//		 success: function(data) {
//			alert(data);
//			$('.result').html(data);
//		 }
//	});
	$(".result").load(url,
			function (responseText, textStatus, XMLHttpRequest){
				$('.result').html(responseText);
				$('#li_'+id+'').hide();
	});
}


function ajaxcall(d){
	var href = d.href;
	//d.stopPropagation();
}


function changupdown(targetid,link,event){
	var href = link.href;
	$('#'+targetid).load(href,
			function (responseText, textStatus, XMLHttpRequest){
				$(targetid).html(responseText);
			}
	);
	doane(event);
}
function deletImg(targetid,link,event) {
	var href = link.href;
//	alert(href);
	$.get(href);
	$('#'+targetid).hide('slow');
	doane(event);
}

function setDefaultConsignee(consigneeid,event) {
	var href = ctx+'/consignee/dft/'+consigneeid;
	$('.deleteconsignee').show();
	hidediv('a_'+consigneeid);
	$.get(href);
//	doane(event);
}
function ajaxdelete(targetid,link,event) {
	doane(event);
	if( confirm("确认删除") ){
		alert('ddd');
		var href = link.href;
		$.get(href);
		$('#'+targetid).hide('slow');
		
	}
	
	return false;
	
}

function dltgoodsformcart(targetid,link,event){
	var href = link.href;
	$('#'+targetid).hide('slow');
	$.get(href,function(data){$('#cart_total').html(data);});
	
	doane(event);
}
		
function setdftImg(imgdiv,link,event) {
	var h = $('#'+imgdiv).html();
	$('#head_img').html(h);
	var href = link.href;
//	alert(href);
	$.get(href);
//	$('#'+targetid).hide('slow');
	doane(event);
}

function clearform(ele) {
    $('#'+ele).find(':input').each(function() {
        switch(this.type) {
            case 'password':
            case 'select-multiple':
            case 'select-one':
            case 'text':
            case 'textarea':
                $(this).val('');
                break;
            case 'checkbox':
            case 'radio':
                this.checked = false;
        }
    });
}


function submitform(form,targetid,event) {
	var my_element = $("#consigneeflag");
	deleteElement(my_element);
	
	var f = $('#'+form);
	doane(event);
	if(! f.valid()){
		return false;
	}
	var queryString = f.formSerialize();
	var action = getFormAction(form);
	
	$.ajax({
		   type: "POST",
		   url: action,
		   data: queryString,
		   success: function(msg){
				$('#'+targetid).after(msg);
				clearform(form);
		   }
		 });
	
}


function submitorder(form){
	var my_element = $("#consigneeflag");
	if( isempty(my_element)){
		var consigneeid = $("input[name='dft']:checked").val();
		window.location= ctx+"/order/submit/"+consigneeid+"";
	}else{
		$("#tipinfo").show();
	}
	return false;
}

function checkorder(form){
	var action = getFormAction(form);
	window.location= action;
	return false;
}

function deleteElement(element){
	if(! isempty(element)){
		element.remove();
	}
}

function isempty(element){
	if(element.length > 0 ){//说明不是空
		return false;
	}else{
		return true;
	}
}
/**
 * 得到form的action值
 * @param form
 * @return
 */
function getFormAction(form){
	var f = $('#'+form);
	var action = f.attr('action');
	return action;
}


function addConfignee(targetid,link,event){
	doane(event);
	showdiv(targetid);
}
function hideConfignee(targetid,link,event){
	doane(event);
	hidediv(targetid);
}

function showdiv(div){
	var t = $('#'+div);
	t.show('slow');
}
function hidediv(div){
	var t = $('#'+div);
	t.hide('slow');
}

function doane(event) {
	e = event ? event : window.event;
	if (is_ie) {
		e.returnValue = false;
		e.cancelBubble = true;
	} else if (e) {
		e.stopPropagation();
		e.preventDefault();
	}
};



/*** Dropdown menu ***/

var timeout    = 200;
var closetimer = 0;
var ddmenuitem = 0;

function dd_open() {
    dd_canceltimer();
    dd_close();
    var liwidth = $(this).width();
    ddmenuitem = $(this).find('ul').css({'visibility': 'visible', 'width': liwidth});
    ddmenuitem.prev().addClass('dd_hover').parent().addClass('dd_hover');
}

function dd_close() {
    if(ddmenuitem) ddmenuitem.css('visibility', 'hidden').prev().removeClass('dd_hover').parent().removeClass('dd_hover');
}

function dd_timer() {closetimer = window.setTimeout(dd_close, timeout);
}

function dd_canceltimer() {
    if (closetimer) {
        window.clearTimeout(closetimer);
        closetimer = null;
    }
}
/*
(function($) {
    $(function() {
    	document.onclick = dd_close;
    	$('#dd > li').bind('mouseover', dd_open);
    	$('#dd > li').bind('mouseout',  dd_timer);
    })
})(jQuery)
*/
