$(document).ready(function(){
	$("#selectDept").click(function(){
		selectDept("deptTreeZone");
	})
	
	/*$("input[type=text][name=loginName]").live("keydown",function(event){
		if(event.keyCode == 32 ) return false;
	})*/
	
	$("input[type=text][name=loginName]").live("blur",function(){
		$(this).val(trimm($(this).val()));
	})
	$("input[type=text][name=userName]").live("blur",function(){
		$(this).val(trimm($(this).val()));
	})
	$("input[type=text][name=group]").live("blur",function(){
		$(this).val(trimm($(this).val()));
	})
	
	$("#fileupload").uploadify({ 
        /*注意前面需要书写path的代码*/ 
		'scriptData':{ 'test':'11111111111111111111' },
        'folder'    	 : '../image/'   ,     //要上传到的服务器路径，默认‘/’ 
        'uploader'       : 'uploadify/uploadify.swf', 
        'script'         : 'addExcel.action', 
        'cancelImg'      : 'uploadify/cancel.png', 
        'queueID'        : 'fileQueue', //和存放队列的DIV的id一致 
        'fileDataName'   : 'fileupload', //和以下input的name属性一致 
        'auto'           : false, //是否自动开始 
        'multi'          : true, //是否支持多文件上传 
        'buttonText'     : 'browse', //按钮上的文字 
        'simUploadLimit' : 3, //一次同步上传的文件数目 
        'sizeLimit'      : 1024000000, //设置单个文件大小限制  单位byte
        'queueSizeLimit' : 2, //队列中同时存在的文件个数限制 
        'fileDesc'       : '支持格式:*.', //如果配置了以下的'fileExt'属性，那么这个属性是必须的 
        'fileExt'        : '*.*',//允许的格式    '*.jpg;*.bmp;*.png;*.gif'
        onComplete: function (event, queueID, fileObj, response, data) {
			$('<li></li>').appendTo('.files').text(response); 
			var f=  encodeURI(fileObj.name);
			$("#download").append("<a href='"+"/portal/cfconsole/excelDownload.action?saveName="+response+"&fileName="+f+"'>"+fileObj.name+"</a>"+"<br>");
			$("#download").show(); 	
			}, 
		onAllComplete:function(event,data) {  
		 },
		onError: function(event, queueID, fileObj) { 
			     alert("文件:" + fileObj.name + "上传失败"); 
			  }, 
		onCancel: function(event, queueID, fileObj){ 
			       alert("取消了" + fileObj.name); 
			  } 
			 });
	
})

function uploadifyUpload(){ 
		   $('#fileupload').uploadifyUpload(); 
		} 

function check(){
	
	if($("#deptId").val()==""||$("#orders").val()==""||$("#loginName").val()==""||$("#userName").val()==""){
		alert("!!!!!!!!!!!!!!");
		return false;
	}
	
	 showLoading();
}

function selectDept(zoneId){
	var url = "/portal/cfconsole/deptTree.jsp?zoneId="+zoneId+"&random="+Math.random();
	window.open(url);
}

var userCount = 0;
function addUserRow(){
	var template = $("#zs").find("tr[name=userTr]");
	var emptyRow = template.clone(true);
	//emptyRow.find("input[type=text]").each(function(i,n){
		//$(n).attr("name",$(n).attr("name")+userCount);
		//$(n).attr("id",$(n).attr("id")+userCount);
	//});
	emptyRow.find("input[type=hidden]").each(function(i,n){
		$(n).attr("value",userCount);
	
	});
	emptyRow.appendTo($('#userTable'));
	userCount++;
}

function trimm(str,is_global)
{
	 var result;  
	 result = str.replace(/(^\s+)|(\s+$)/g,"");
	 //if(is_global.toLowerCase()=="g")
	 result = result.replace(/\s/g,"");
	 return result;
}
