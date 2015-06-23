//head cms
function getHeadNews(sj_id){
	$.ajax({
		type: 'POST',
		url: '/portal/infoSearch/findStfbHeadNewsLatestEvents.action?random='+Math.random(),
		data:{
			"sj_id" : sj_id
		},
		dataType:'json',
		cache : false,
		error:function(){/**alert('系统连接失败，请稍后再试！')*/},
		success: function(obj){			
			if(obj){
				for(var i=0;i<obj.length;i++){
					$("#newsCenter hgroup a").attr("href","/portal/infoSearch/findStfbNewsByPage.action?sj_id="+obj[i].SJ_ID);
					$("#newsCenter section a").attr("href","http://10.1.44.18/stfb"+obj[i].url+".htm");
					$("#newsCenter section a h5").html(obj[i].title);
					//$("#newsCenter section a h5").attr("title",obj[i].copyTitle);
					$("#newsCenter section a p").html(obj[i].memo);
					//$("#newsCenter section a p").attr("title",obj[i].copyMemo);
					$("#newsCenter section a").attr("title",obj[i].copyMemo);
				}
				
			}
		}	  
	});	
}

//xwtt cms
function getXWTTNews(sj_id){
	$.ajax({
		type: 'POST',
		url: '/portal/infoSearch/findStfbPicNewsLatestEvents.action?random='+Math.random(),
		data:{
			"sj_id" : sj_id,
			"pic_needed" : "0",
			"size":"6"
		},
		dataType:'json',
		cache : false,
		error:function(){/**alert('系统连接失败，请稍后再试！')*/},
		success: function(obj){			
			var newsLi = "";
			if(obj){
				for(var i=0;i<obj.length;i++){
					newsLi += "<li><a target='_blank' title='"+obj[i].copyTitle+"' href='"+"http://10.1.44.18/stfb"+obj[i].url+".htm"+"'>"+obj[i].title+"</a><span>"+obj[i].createTime+"</span></li>";
				}
				$("#newsCenter ul").html(newsLi);
			}
		}	  
	});	
}

function getXWTTPicNews(sj_id){
	$.ajax({
		type: 'POST',
		url: '/portal/infoSearch/findStfbPicNewsLatestEvents.action?random='+Math.random(),
		data:{
			"sj_id" : sj_id,
			"pic_needed" : "1"
		},
		dataType:'json',
		cache : false,
		error:function(){/**alert('系统连接失败，请稍后再试！')*/},
		success: function(obj){			
			var playList = "";
			var playText = "";
			var playInfo = "";
			if(obj){
				for(var i=0;i<obj.length;i++){
					playList += "<li><a target='_blank' title='"+obj[i].copyTitle+"' href='"+"http://10.1.44.18/stfb"+obj[i].url+".htm"+"'>" +
							"<img src='http://10.1.44.18/stfb"+obj[i].picUrl+"' title='"+obj[i].title+"'></img></a></li>";
					playInfo += "<li><a target='_blank' title='"+obj[i].copyTitle+"' href='"+"http://10.1.44.18/stfb"+obj[i].url+".htm"+"'>"
					+ obj[i].title+"</a></li>";

					playText += "<li>"+(i+1)+"</li>";
				}
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
function getLatestNews(sj_id,pos){
			
			$.ajax({
				type: 'POST',
				url: '/portal/infoSearch/findStfbNewsLatestEvents.action?random='+Math.random(),
				data:{
					"sj_id" : sj_id
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
							newsP = "/portal/infoSearch/findStfbNewsByPage.action?sj_id="+obj[i].SJ_ID;
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


//cms dtb
function getLatestDTB(pos){
			
			$.ajax({
				type: 'POST',
				url: '/portal/infoSearch/ajax_dtb.jsp?random='+Math.random(),
				cache : false,
				error:function(){/**alert('系统连接失败，请稍后再试！')*/},
				success: function(obj){
					var dtb = "";
					if(obj){
						dtb = obj+ 
						"<p class='fr'><a target='_blank' class='more_3' href='http://10.1.44.17:88/content/content/contentListAction2.do?doType=0&status=Y'>更多</a></p>";
						$(".reportDiv:eq("+pos+")").html(dtb);	
					}
					
					
				}	  
			});	
			
		}
		
//aside cms
function getLatestNewsAside(sj_id,pos){
			
			$.ajax({
				type: 'POST',
				url: '/portal/infoSearch/findStfbNewsLatestEvents.action?random='+Math.random(),
				data:{
					"sj_id" : sj_id,
					"size"	:	"4"
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
							newsP = "/portal/infoSearch/findStfbNewsByPage.action?sj_id="+obj[i].SJ_ID;
						}
						$(".asideUl:eq("+pos+")").html(newsLi);
						$(".asideH:eq("+pos+") a").attr("href",newsP);
						if(sj_id=="974"){
								$(".asideH:eq("+pos+") a").attr("href","/ConstructionNotice/TConstructionNotice/findTConstructionNoticeOnly.action");
						}
						if(obj.length==0){
							$(".asideUl:eq("+pos+")").html("&nbsp;&nbsp;无相关信息。");
							$(".asideH:eq("+pos+") a").hide();
						}
					}
				}	  
			});	
			
		}

