var index = 0;		//图片轮播的序号

//1.显示党组织建设
function showParty(sj_id,part_id,size){	
	$.ajax({
		type: 'POST',
		//url: '/portal/infoSearch/findStfbNewsLatestEvents.action?random='+Math.random(),		//不带首页图片
		url: '/portal/infoSearch/findStfbPicNewsLatestEvents.action?random='+Math.random(),		//带首页图片
		data:{
			"sj_id" : sj_id,
			"part_id":part_id,
			"pic_needed":"1",
			"size":size
		},
		dataType:'json',
		cache : false,
		error:function(){/**alert('系统连接失败，请稍后再试！')*/},
		success: function(obj){
			if(obj!=null && obj.length>0){
				var addHtml ="";
				var imgHtml ="";
				for(var i=0; i<obj.length; i++){
					if(i<=3){
						imgHtml += "<li><a href='javascript:void(0);' name='http://10.1.44.18/stfb"+obj[i].url+".htm'>" +
								"<img src='http://10.1.44.18/stfb"+obj[i].picUrl+"' title="+obj[i].title+">" +
										"<i></i></a></li>";
					}
					
					if(i==0){
						$("#partyHeadNews a").attr("href","http://10.1.44.18/stfb"+obj[i].url+".htm");
						$("#partyHeadNews h4").html(obj[i].title);	//党组织建设头条标题
						if(obj[i].memo!=null && obj[i].memo!="" && obj[i].memo.length>55){
							$("#partyHeadNews p").html(obj[i].memo.substr(0,40)+"...");	//党组织建设头条标题
						}else{
							$("#partyHeadNews p").html(obj[i].memo);	//党组织建设头条标题
						}
						
					}else{
						addHtml += "<li class='clearfix'><a href='http://10.1.44.18/stfb"+obj[i].url+".htm' class='fl' target='_blank'>"+obj[i].title+"</a><span class='fr date'>"+obj[i].createTime.substring(obj[i].createTime.length-5,obj[i].createTime.length)+"</span></li>"
					}
				}
				$("#partyList").html(addHtml );
				$("#headImagesList").html(imgHtml);
				
				showPatyPic();			//轮播党组织图片		
			}
			
		}
	});
}


//3.显示共青团工作
function showGroup(sj_id,part_id){	
	$.ajax({
		type: 'POST',
		url: '/portal/infoSearch/findStfbNewsLatestEvents.action?random='+Math.random(),
		data:{
			"sj_id" : sj_id,
			"part_id":part_id
		},
		dataType:'json',
		cache : false,
		error:function(){/**alert('系统连接失败，请稍后再试！')*/},
		success: function(obj){
			if(obj!=null && obj.length>0){
				var addHtml ="";							
				for(var i=0; i<obj.length; i++){
					addHtml += "<li class='clearfix'><span class='fl'></span>" +
							"<a class='fl con' href='http://10.1.44.18/stfb"+obj[i].url+".htm' target='_blank'>"+obj[i].title+"</a></li>";
				}
				$("#groupList").html(addHtml);								
			}
		}
	});
}

//6.人物情怀
function showPersonEmotion(sj_id,part_id,size){
	$.ajax({
		type: 'POST',
		//url: '/portal/infoSearch/findStfbNewsLatestEvents.action?random='+Math.random(),		//不带首页图片
		url: '/portal/infoSearch/findStfbPicNewsLatestEvents.action?random='+Math.random(),		//带首页图片
		data:{
			"sj_id" : sj_id,
			"part_id":part_id,
			"pic_needed":"1",
			"size":size
		},
		dataType:'json',
		cache : false,
		error:function(){/**alert('系统连接失败，请稍后再试！')*/},
		success: function(obj){
			if(obj!=null && obj.length>0){
				var visibleLength = 7;
				var addHtml ="";				
				for(var i=0; i<obj.length; i++){
					addHtml += "<li><img src='http://10.1.44.18/stfb"+obj[i].picUrl+"' title="+obj[i].title+" onclick='window.open(\"http://10.1.44.18/stfb"+obj[i].url+".htm\")'></li>";
					/*if(i>=7){
						addHtml += "<li style='display:none'><img src='http://10.1.44.18/stfb"+obj[i].picUrl+"' title="+obj[i].title+" onclick='window.open(http://10.1.44.18/stfb"+obj[i].url+".htm)'></li>";
					}else{
						addHtml += "<li><img src='http://10.1.44.18/stfb"+obj[i].picUrl+"' title="+obj[i].title+" onclick='window.open('http://10.1.44.18/stfb"+obj[i].url+".htm')'></li>";
					}*/
				}
				if(obj.length<visibleLength){
					visibleLength = obj.length;
				}
				$("#personEmotion").html(addHtml);
				$("#scrollDiv").jCarouselLite({
				    auto: 5000,
				    speed: 2000,
				    visible: visibleLength
				});
			}
		}
	});
}




//显示栏目信息，通用方法
function showCommonList(sj_id,part_id,index,size){	
	$.ajax({
		type: 'POST',
		url: '/portal/infoSearch/findStfbNewsLatestEvents.action?random='+Math.random(),
		data:{
			"sj_id" : sj_id,
			"part_id":part_id,
			"size":size
		},
		dataType:'json',
		cache : false,
		error:function(){/**alert('系统连接失败，请稍后再试！')*/},
		success: function(obj){
			if(obj!=null && obj.length>0){
				var addHtml ="";							
				for(var i=0; i<obj.length; i++){
					addHtml += "<li><a href='http://10.1.44.18/stfb"+obj[i].url+".htm' target='_blank'><font color='black'>"+obj[i].title+"</font></a></li>";
				}
				$(index).html(addHtml );								
			}
		}
	});
}

//党组织建设图片轮播
function showPatyPic(){
	var $firstLi =  $("#headImagesList li:first");
	$("#headImage").attr("src",$firstLi.find("img").attr("src"));
	$("#headImage").parent().attr("href",$firstLi.find("a").attr("name"));
	//$("#headImageInfo span").html($firstLi.find("img").attr("title"));
	
	$("#headImagesList li").click(function(){
		index = $("#headImagesList li").index($(this));
		$("#headImage").attr("src",$(this).find("img").attr("src"));
		$("#headImage").parent().attr("href",$firstLi.find("a").attr("name"));
		//$("#headImageInfo span").html($(this).find("img").attr("title"));
//		$("#headImageInfo a").attr("href",$(this).find("a").attr("name"));
	});
	setInterval("autoShowPartyPic()", 5000);
	
}
function autoShowPartyPic(){
	index = index >= ($("#headImagesList li").length -1) ? 0 : ++index;
	$("#headImagesList li").eq(index).trigger('click');
}










