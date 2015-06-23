	//显示头版头条新闻图片
var basePath="";
function setbasePath(path){
	basePath=path;
}
function setShortTitle(shortTitle,Title){
	if(shortTitle=="null"){
		return shortTitle;
	}else{
		return Title;
	}
}
//head cms
function getHeadNews(channelID){

	$.ajax({
		url:basePath+"/jeecms/getJeecmsInfo.action?random="+Math.random(),
		type:"post",
		dataType:'json',
		cache : false,
		data:{
			method:"JEECMS.GET_CONTENT_LIST",
			channelId:channelID,
			hasTitleImg:"0,1",
			typeId:'1,2,3,4',
			rownum:'5'
		},
		error:function(){
			alert("系统连接失败，请稍后再试！");
		},
		success: function(obj){	
		
			if(obj){
				for(var i=0;i<obj.length;i++){
					
					//$("#newsCenter hgroup a").attr("href",basePath+"/jeecms/findByPage.action?channelId="+channelID);
					$("#newsCenter section a").attr("href","../contentDetail.jsp?content="+obj[i].contentId);
					$("#newsCenter section a h5").html(obj[i].title);
					//$("#newsCenter section a h5").attr("title",obj[i].copyTitle);
					$("#newsCenter section a p").html(obj[i].description);
					//$("#newsCenter section a p").attr("title",obj[i].copyMemo);
					$("#newsCenter section a").attr("title",obj[i].description);
				}
				
			}
		}	  
	});	
}

//xwtt cms
function getXWTTNews(channelID){
	$.ajax({
		url:basePath+"/jeecms/getJeecmsInfo.action?random="+Math.random(),
		type:"post",
		dataType:'json',
		cache : false,
		data:{
			method:"JEECMS.GET_CONTENT_LIST",
			channelId:channelID,
			hasTitleImg:"0,1",
			typeId:'1,2,3,4',
			rownum:'5'
		},
		error:function(){
			alert("系统连接失败，请稍后再试！");
		},
		success: function(obj){			
			var newsLi = "";
			if(obj){
				for(var i=0;i<obj.length;i++){
					newsLi += "<li><a target='_blank' title='"+setShortTitle(obj[i].shortTitle,obj[i].title)+"' href='../contentDetail.jsp?content="+obj[i].contentId+"'>"+obj[i].title+"</a><span>"+obj[i].releaseDate+"</span></li>";
				}
				$("#newsCenter ul").html(newsLi);
			}
		}	 	  
	});	
}

function getXWTTPicNews(cmsUrl,channelID){
	$.ajax({
				url:basePath+"/jeecms/getJeecmsInfo.action?random="+Math.random(),
				type:"post",
				dataType:'json',
				cache : false,
				data:{
					method:"JEECMS.GET_CONTENT_LIST",
					channelId:channelID,
					hasTitleImg:"1",
					typeId:'3',
					rownum:'5'
				},
				error:function(){
					alert("系统连接失败，请稍后再试!");
				},
				success:function(obj){
					var playList = "";
					var playText = "";
					var playInfo = "";
					if(obj!=null && obj!="undefined"){
						for(var i=0;i<obj.length;i++){
							playList += "<li><a target='_blank' title='"+setShortTitle(obj[i].shortTitle,obj[i].title)+"' href='"+"../contentDetail.jsp?content="+obj[i].contentId+"'>" +
									"<img src='"+cmsUrl+obj[i].titleImg.replace("/jeecms/u/","/u/")+"' title='"+obj[i].title+"'></img></a></li>";
							playInfo += "<li><a target='_blank' title='"+setShortTitle(obj[i].shortTitle,obj[i].title)+"' href='"+"../contentDetail.jsp?content="+obj[i].contentId+"'>"
							+ obj[i].title+"</a></li>";
		
							playText += "<li>"+(i+1)+"</li>";
						}
						
						$("#play_list").html(playList);
						$("#play_info").html(playInfo);
						$("#play_text").html(playText);
						$("#play_list li").hide();
						$("#play_info li").hide();
						$("#play_list li").eq(0).show();
						$("#play_info li").eq(0).show();
						$("#play_text li").eq(0).addClass("current");
					}
				}
			});
}



//cms
function getLatestNews(channelID,pos){
			
			$.ajax({
		url:basePath+"/jeecms/getJeecmsInfo.action?random="+Math.random(),
		type:"post",
		dataType:'json',
		cache : false,
		data:{
			method:"JEECMS.GET_CONTENT_LIST",
			channelId:channelID,
			hasTitleImg:"0,1",
			typeId:'1,2,3,4',
			rownum:'8'
		},
		error:function(){
			alert("系统连接失败，请稍后再试！");
		},
		success: function(obj){			
			var newsLi = "";
			var newsP = "javascript:void(0)";
			if(obj){
				for(var i=0;i<obj.length;i++){
					newsLi += "<li class='clearfix'><a target='_blank' title='"+setShortTitle(obj[i].shortTitle,obj[i].title)+"'";
					newsLi += "  href='../contentDetail.jsp?content="+obj[i].contentId+"'>"+obj[i].title+"</a><span>"+obj[i].releaseDate+"</span></li>";
					newsP =basePath+ "/jeecms/findByPage.action?channelId="+obj[i].channelId;
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

