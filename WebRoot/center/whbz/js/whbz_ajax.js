//head cms
function getWHBZHeadNews(sWhere){
	$.ajax({
		type: 'POST',
		url: '/portal/infoSearch/findZTTPNewsLatestEvents.action?random='+Math.random(),
		data:{
			"sWhere" : sWhere,
			"size":"6"
		},
		dataType:'json',
		cache : false,
		error:function(){/**alert('系统连接失败，请稍后再试！')*/},
		success: function(obj){
			var newsLi = "";
			var playList = "";
			var playText = "";
			var playInfo = "";
			if(obj){
				for(var i=0;i<obj.length;i++){
					if(i==0){
						$("#newsCenter hgroup a").attr("href","http://10.1.44.18/stfb/frame/zytp_title.jsp?part=wbzx");
						$("#newsCenter section a").attr("href","http://10.1.44.18/stfb/frame/pic_content_title.jsp?id="+obj[i].id);
						$("#newsCenter section a h5").html(obj[i].name);
						//$("#newsCenter section a h5").attr("title",obj[i].copyTitle);
						$("#newsCenter section a p").html(obj[i].abstractContent);
						//$("#newsCenter section a p").attr("title",obj[i].copyMemo);
						$("#newsCenter section a").attr("title",obj[i].content);
					}
					if(i<=5){
						playList += "<li><a target='_blank' title='"+obj[i].name+"' href='"+"http://10.1.44.18/stfb/frame/pic_content_title.jsp?id="+obj[i].id+"'>" +
								"<img src='http://10.1.44.18/stfb"+obj[i].pic+"' title='"+obj[i].name+"'></img></a></li>";
						playInfo += "<li><a target='_blank' title='"+obj[i].copyname+"' href='"+"http://10.1.44.18/stfb/frame/pic_content_title.jsp?id="+obj[i].id+"'>"
						+ obj[i].name+"</a></li>";
			
						playText += "<li>"+(i+1)+"</li>";
					}
					newsLi += "<li><a target='_blank' title='"+obj[i].copyname+"' href='"+"http://10.1.44.18/stfb/frame/pic_content_title.jsp?id="+obj[i].id+"'>"+obj[i].name+"</a><span>"+obj[i].create_time+"</span></li>";
					
				}
				$("#newsCenter ul").html(newsLi);
				$("#play_list").html(playList);
				$("#play_info").html(playInfo);
				$("#play_text").html(playText);
				
				$("#play_list li").hide();
				$("#play_info li").hide();
				//$("#play_text li").hide();
				$("#play_list li").eq(0).show();
				$("#play_info li").eq(0).show();
				$("#play_text li").eq(0).addClass("current");
			}
		}	  
	});	
}


//cms
function getLatestNews(sj_id,part_id,pos){		
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
			var newsLi = "";
			var newsP = "javascript:void(0)";
			if(obj){
				for(var i=0;i<obj.length;i++){
					newsLi += "<li class='clearfix'><a target='_blank' title='"+obj[i].copyTitle+"'";
					newsLi += " href='http://10.1.44.18/stfb"+obj[i].url+".htm'>"+obj[i].title+"</a><span>"+obj[i].createTime.substring(5)+"</span></li>";
					newsP = "/portal/infoSearch/findStfbNewsByPage.action?sj_id="+obj[i].SJ_ID+"&part_id="+obj[i].partId;
				}
				
				$(".reportDiv:eq("+pos+") ul").html(newsLi);
				$(".reportDiv:eq("+pos+") p").html("<a target='_self' class='more_3' href='"+newsP+"'>更多</a>");
				if(obj.length==0){
					$(".reportDiv:eq("+pos+") ul").html("&nbsp;&nbsp;无相关信息。");
					$(".reportDiv:eq("+pos+") p a").hide();
				}
			}
		}	  
	});	
	
}


//aside cms
function getLatestNewsAside(sj_id,part_id,pos,size){
			
			$.ajax({
				type: 'POST',
				url: '/portal/infoSearch/findStfbNewsLatestEvents.action?random='+Math.random(),
				data:{
					"sj_id" : sj_id,
					"part_id":part_id,
					"size"	:	size
				},
				dataType:'json',
				cache : false,
				error:function(){/**alert('系统连接失败，请稍后再试！')*/},
				success: function(obj){			
					var newsLi = "";
					var newsP = "javascript:void(0)";
					if(obj){
						for(var i=0;i<obj.length;i++){
							newsLi += "<li><a target='_blank' title='"+obj[i].copyTitle+"'";
							newsLi += " href='http://10.1.44.18/stfb"+obj[i].url+".htm'>"+obj[i].title+"</a></li>";
							newsP = "/portal/infoSearch/findStfbNewsByPage.action?sj_id="+obj[i].SJ_ID+"&part_id="+obj[i].partId;
						}
						$(".asideUl:eq("+pos+")").html(newsLi);
						$(".asideH:eq("+pos+") a").attr("href",newsP);
						if(obj.length==0){
							$(".asideUl:eq("+pos+")").html("&nbsp;&nbsp;无相关信息。");
							$(".asideH:eq("+pos+") a").hide();
						}
					}
				}	  
			});	
			
		}

function getLatestItemAside(sj_id,part_id,pos){
	
	$.ajax({
		type: 'POST',
		url: '/portal/infoSearch/findStfbNewsLatestEvents.action?random='+Math.random(),
		data:{
			"sj_id" : sj_id,
			"part_id":part_id,
			"size"	:	"1"
		},
		dataType:'json',
		cache : false,
		error:function(){/**alert('系统连接失败，请稍后再试！')*/},
		success: function(obj){			
			var link = "";
			if(obj){
				for(var i=0;i<obj.length;i++){
					link = "http://10.1.44.18/stfb"+obj[i].url+".htm";
					$(".item td:eq("+pos+") a").attr("href",link);
					$(".item td:eq("+pos+") a").attr("target","_blank");
				}
				if(obj.length==0){
					$(".item td:eq("+pos+") a").attr("href","javascript:void(0);");
				}
			}	
		}	  
	});	
	
}