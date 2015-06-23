 
function ifNewJobContact(obj){
	var targetTr = obj.parent("td").parent("tr");
	var summaryDiv = $(targetTr).find(".summary");
	//console.log(summaryDiv);
	if(summaryDiv.length>0){
		return true;
	}
	return false;
}
		

//工作联系单初始化
function jobContactLoad(loginname,deptid,name,type){
	var groupids = "";
	var ptypes = "";
	var dbxtypes = "";
	$(".summary").each(function(i,n){
		groupids = groupids + "," + $(n).attr("groupid");
		ptypes = ptypes + "," + $(n).attr("ptype");
		dbxtypes = dbxtypes + "," + $(n).attr("dbxtype");
		$("div[groupid="+$(n).attr("groupid")+"]").each(function(i,n){
			$(n).html("<img src='/portal/todoItem/js/throbber.gif'></img>");
		});
	});
	
	$(".summary[dbxtype=dyx]").each(function(i,n){
		var target = $(n).parent("td").parent("tr");
		$(target).find("img").each(function(j,m){
			if($(m).attr("src")=="../css/default/images/p_open.gif")
			$(m).attr("src","../css/default/images/p_find.gif");
			$(m).attr("title","待查阅");
		});
	});
	
	if (groupids.length==0) return false;
	if (groupids.length>0&&groupids.startWith(",")) groupids = groupids.substring(1,groupids.length);
	if (ptypes.length>0&&ptypes.startWith(",")) ptypes = ptypes.substring(1,ptypes.length);
	if (dbxtypes.length>0&&dbxtypes.startWith(",")) dbxtypes = dbxtypes.substring(1,dbxtypes.length);
	
	jobConatctAjax(groupids,ptypes,dbxtypes,loginname,deptid,name,type);
}
var navtimeoutid=null;
function jobConatctAjax(groupids,ptypes,dbxtypes,loginname,deptid,name,type){
	var url = "/portal/jobContact/listJobInfo.action";
	$.ajax({
		url: url,
		type: 'POST',
		data:{
			"groupids" : groupids,
			"ptypes" : ptypes,
			//"name" : "<%//name%>",
			"dbxtypes" : dbxtypes,
			"loginName" : "ST/"+loginname,
			"deptId" : deptid
		},
		dataType: 'text',
		timeout: 50000,
		error: function(){
			//alert('系统错误,请与管理员联系!');
		},
		success: function(data){
			//alert(data);
			if($.trim(data).indexOf("你还没有登录或登录已超时")>0) return false;
			if($.trim(data).length>0){
				var items = $.trim(data).split("&&")
				$.each(items, function(){
					var values = this.split("##"); 
					var groupid = "";			
					if(values[0]!=null&&values[0].length>0) groupid=$.trim(values[0]);
					if(values[1]!=null){
						var num = parseInt($.trim(values[1]));
						$(".summary[groupid="+groupid+"]").attr("count",$.trim(values[1]));
						
						/**if(num>0){
							$(".summary[groupid="+groupid+"]").bind({
								mouseenter: function(){
									if(navtimeoutid) clearTimeout(navtimeoutid);
									var offset = $(this).offset();
					                $("#jobContactInfo").css("left", offset.left+100);
					                $("#jobContactInfo").css("top", offset.top+25);
					                $("#jobContactInfo").html("<img src='/portal/todoItem/js/throbber.gif'></img>");
					                $("#jobContactInfo").css('filter','Alpha(Opacity=90)');
								    //$("#jobContactInfo").show();
								    $("#jobContactInfo").slideDown(100);
								   // jobConatctHoverTableAjax($(this).attr("groupid"),$(this).attr("ptype"),$(this).attr("dbxtype"),$("#jobContactInfo"),loginname,deptid,name);
								}, 
								mouseleave: function(){
									if(navtimeoutid){
										
										navtimeoutid=setTimeout(function(){
							       		$("#jobContactInfo").slideUp(100);
							       		//$("#jobContactInfo").hide();
									    $("#jobContactInfo").css('filter','');
									    $("#jobContactInfo").css("left", "");
						                $("#jobContactInfo").css("top", "");
									    $("#jobContactInfo").html("");
							    		},1000);
							    	}
									//$("#jobContactInfo").show();
								}
							});
							
							$("#jobContactInfo").bind({
								mouseenter: function(){
									//$("#jobContactInfo").show();
									if(navtimeoutid) clearTimeout(navtimeoutid);
								}, 
								mouseleave: function(){
									navtimeoutid=setTimeout(function(){
								    $("#jobContactInfo").slideUp(100);
									//$("#jobContactInfo").slideUp();
									//$("#jobContactInfo").hide();
								    $("#jobContactInfo").css('filter','');
								    $("#jobContactInfo").css("left", "");
					                $("#jobContactInfo").css("top", "");
								    $("#jobContactInfo").html("");
								    },500);
									
								}
							});
						}*/
					}
					if(type=="0"){
						if(values[5]!=null) $(".memo[groupid='"+groupid+"'][dbxtype='"+$.trim(values[5])+"']").html($.trim(values[4]));
						if(values[2]!=null) $(".summary[groupid='"+groupid+"'][dbxtype='"+$.trim(values[5])+"']").html($.trim(values[2]));
						if(values[3]!=null) $(".steplabel[groupid='"+groupid+"'][dbxtype='"+$.trim(values[5])+"']").html($.trim(values[3]));
						if(values[4]!=null) $(".memo[groupid='"+groupid+"'][dbxtype='"+$.trim(values[5])+"']").html($.trim(values[4]));
					}else{
						if(values[2]!=null) $(".summary[groupid="+groupid+"]").html($.trim(values[2]));
						if(values[3]!=null) $(".steplabel[groupid="+groupid+"]").html($.trim(values[3]));
						if(values[4]!=null) $(".memo[groupid="+groupid+"]").html($.trim(values[4]));
					}
					
				});
			
			}
			
		}
	});
}

function jobConatctHoverTableAjax(groupId,ptype,dbxtype,div,loginname,deptid,name){

	var url = "/portal/jobContact/listHoverJobInfo.action";
	$.ajax({
		url: url,
		type: 'POST',
		data:{
			"groupId" : groupId,
			"ptype" : ptype,
			"dbxtype" : dbxtype,
			"name" : name,
			"loginName" : "ST/"+loginname,
			"deptId" : deptid
		},
		dataType: 'text',
		timeout: 50000,
		error: function(){
			//alert('系统错误,请与管理员联系!');
		},
		success: function(data){
			//console.log($.trim(data));
			if($.trim(data).indexOf("你还没有登录或登录已超时")>0) return "";
			
			if($.trim(data).length>0){
				$(div).html($.trim(data))
			}
			
		}
	});
	
}

function jobConatctCuiBanHoverTableAjax(groupId,ptype,dbxtype,div,loginname,deptid,name){
	//alert(1);
	var url = "/portal/jobContact/listHoverCuiBanJobInfo.action";
	$.ajax({
		url: url,
		type: 'POST',
		data:{
			"groupId" : groupId,
				"ptype" : ptype,
				"dbxtype" :dbxtype,
				"name" : name,
				"loginName" : "ST/"+loginname,
				"deptId" : deptid
		},
		dataType: 'text',
		timeout: 50000,
		error: function(){
			alert('系统错误,请与管理员联系!');
		},
		success: function(data){
			//console.log($.trim(data));
			if($.trim(data).indexOf("你还没有登录或登录已超时")>0) return "";
			
			if($.trim(data).length>0){
				$(div).html($.trim(data))
			}
			
		}
	});
	
}

String.prototype.startWith=function(str){
	if(str==null||str==""||this.length==0||str.length>this.length)
	  return false;
	if(this.substr(0,str.length)==str)
	  return true;
	else
	  return false;
	return true;
}