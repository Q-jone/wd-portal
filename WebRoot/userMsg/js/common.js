function backReceive(){
	window.location.href = "msgReceiveBox.action"
}

function backSend(){
	window.location.href = "msgSendBox.action"
}

function backDraft(){
	window.location.href = "msgDraftBox.action"
}

function delBatch(userType){
		var delIds = "";
		$("input[name='delId']:checked").each(function(i,n){
			delIds += $(n).val()+",";
		})
		if(delIds==""){
			alert("请选择需要删除的通知！");
			return false;
		}
		delIds = delIds.substring(0,delIds.length-1);
		if(confirm("确认删除吗？")){
			$.post(
					'/portal/userMsg/msgDelBatch.action?random='+Math.random(),
					{
						userType: 	userType,
						delIds: 	delIds
					},
					function(data, textStatus, jqXHR){
						if(data=="1"){
							alert("删除成功！");
							$("form").submit();
						}
					},
					"json"
				)
		}
		
}

function goPage(page){
			$("#page").val(page);
			$("form").submit();
		}
		
function goPageRedirect(){
	if(!$("#redirectPage").val().match(/^[0-9]+$/)){
		alert("请输入数字");
		$("#redirectPage").val("");
		$("#redirectPage").focus();
		return ;
	}
   		
	$("#page").val($("#redirectPage").val());
	$("form").submit();
}

var cache = {};
var lastXhr;
var cacheData;
reCount = 0;
function addReceiver(){
	reCount++;
	var template = $("#hideRe").find("div");
	var emptyRow = template.clone(true);
	emptyRow.find("input[type=text]").each(function(i,n){
		$(n).attr("name","rname"+reCount);
		$(n).attr("id","rname"+reCount);
	});
	emptyRow.find("input[type=hidden]").each(function(i,n){
		var tmp = $(n).attr("name");
		$(n).attr("name",tmp+reCount);
		$(n).attr("id",tmp+reCount);
	});
	emptyRow.appendTo($('#reTd'));
	$("#rname"+reCount).autocomplete({
		source: function( request, response ) {
				var term = request.term;
				if ( term in cache ) {
					response( cache[ term ] );
					return;
				}
				lastXhr = $.post(
						'/portal/userMsg/getUser.action?random='+Math.random(),
						{
							maxRows: 12,
							name_startsWith: request.term//.replace(/(^\s*)|(\s*$)/g,'') 去除开头结尾的空格
						},
						function(data, textStatus, jqXHR){
							 cacheData = $.map( data, function( item ) {
									return {
										label: 		item.name+"("+item.dept+")",
										value: 		item.name+"("+item.dept+")",
										rid:		item.cid,
										rname:		item.name
									};
								});
							cache[ term ] = cacheData;
							if ( jqXHR === lastXhr ) {
								response(cacheData);
							}
						},
						"json"
					)
		},
		minLength: 1,
		select: function( event, ui ) {
			$("#rname").val($("#rname").val()+ui.item.label+"; ");
			$(this).prev("input[type=hidden]").val(ui.item.rid);
			$(this).prev().prev("input[type=hidden]").val(ui.item.rname);
		}
	
});
}

function isChinese(str){  //判断是不是中文
    var reCh=/[u00-uff]/;
    return !reCh.test(str);
}
function lenStat(target){
    var strlen=0; //初始定义长度为0
	var txtval = $.trim(target.val());
	for(var i=0;i<txtval.length;i++){
	 if(isChinese(txtval.charAt(i))==true){
	  strlen=strlen+2;//中文为2个字符
	 }else{
	  strlen=strlen+1;//英文一个字符
	 }
	}
	strlen=Math.ceil(strlen/2);//中英文相加除2取整数
	return strlen;
}

var errorTargets = new Array();
function showError(target,error){
	if($(target).length>0){
		errorTargets.push(target);
		if($(target).hasClass("date")){
			$(target).addClass("fieldErrorDate");
		}else{
			$(target).addClass("fieldError");
		}
		
		/**/
		$(target).qtip({
			content:error,
			position: {
                corner: {
                   tooltip: "topLeft", 
                   target: "bottomLeft" 
                }
             },
             show: {
                //when: true, 
                //ready: true 
             },
             //hide: false, 
             style: {
                border: {
                   width: 2,
                   radius: 3
                },
                padding: 10, 
                textAlign: 'center',
                tip: true, 
                name: 'red' 
             }
		});
		
	}
}

function clearError(){
	if(errorTargets!=null&&errorTargets.length>0){
		
		//console.log(errorTargets);
		
		$(errorTargets).each(function(i,n){
			if($(n).length>0){
				$(n).qtip('destroy');
				
				if($(n).hasClass("date")){
					$(n).removeClass("fieldErrorDate");
				}else{
					$(n).removeClass("fieldError");
				}
			}
		});
	}
	errorTargets = new Array();
}
