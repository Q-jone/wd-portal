var LOAD_STR = '<div style="display:none;" id="dialog" title="最新通知">'+
	'<iframe frameborder="0" style="WIDTH:100%; height:100%;" src="http://10.1.44.18/stfb/node210/201302/con1035233.htm" '+
	' frameBorder=0 scrolling="auto" marginheight="0" marginwidth="0"></iframe>'+
	'</div>';

document.write(LOAD_STR);

//去除空格
function strTrim(str){
	return str.replace(/(^\s*)|(\s*$)/g,"");
}

//读取cookie中的值
function readCookie(name) {
	var searchName = name + "=";
	var cookies = document.cookie.split(';');
	for(var i=0; i < cookies.length; i++) {
		var c = cookies[i];
		while (c.charAt(0) == ' ')
		  c = c.substring(1, c.length);
		if (c.indexOf(searchName) == 0)
		  return c.substring(searchName.length, c.length);
	}
	return "";
}

//存放cookie
function writeCookie(name, value, days) {
	var expires = "";
	if (days) {
		var date = new Date();
		date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
		expires = "; expires=" + date.toGMTString();
	}
	document.cookie = name + "=" + value + expires + "; path=/";
}

function showNotice(){
	if(readCookie("noticeDialog")=="1"){
		//$( "#dialog" ).dialog( "open" );
	}else{
		writeCookie("noticeDialog","1",1);
		$( "#dialog" ).dialog( "open" );
	}
}

$(function(){
	$( "#dialog" ).dialog({
		modal: true,
		autoOpen: false,
		width: 630,
		height: 510,
		zIndex: 9999
//		buttons: [
//					{
//						text: "确认",
//						click: function() {
//							$( this ).dialog( "close" );
//						}
//					},
//					{
//						text: "取消",
//						click: function() {
//							$( this ).dialog( "close" );
//						}
//					}
//				]
	});
	
	showNotice();
})

