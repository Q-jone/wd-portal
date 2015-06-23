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
//头条新闻
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

//xxgl cms
function getXXGLNews(channelID){
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
			rownum:'6'
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