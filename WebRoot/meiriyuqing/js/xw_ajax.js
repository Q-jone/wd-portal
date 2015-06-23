var basePath="";
function setbasePath(path){
	basePath=path;
}


//cms
function getStfbLatestNews(sj_id,pos,div){
	var html = "";
	var rownum = "";
	var width = "95%";
	if(div=="reportDivTop"){
		rownum = "6";
	}else{
		rownum = "12";
		width = "47%";
	}			
			$.ajax({
				type: 'POST',
				url: '/portal/infoSearch/findStfbNewsLatestEvents.action?random='+Math.random(),
				data:{
					"sj_id" : sj_id,
					"size":"12"
				},
				dataType:'json',
				cache : false,
				error:function(){/**alert('系统连接失败，请稍后再试！')*/},
				success: function(obj){			
					var newsLi = "";
					var newsP = "javascript:void(0)";
					if(obj){
						for(var i=0;i<obj.length;i++){
							newsLi += "<li class='clearfix' style='width:"+width+"'><a target='_blank' title='"+obj[i].copyTitle+"'";
							newsLi += " href='http://10.1.44.18/stfb"+obj[i].url+".htm'>"+obj[i].title+"</a><span>"+obj[i].createTime.substring(5)+"</span></li>";
							newsP = "/portal/infoSearch/findStfbNewsByPage.action?sj_id="+obj[i].SJ_ID;
						}
						$("."+div+":eq("+pos+") ul").html(newsLi);
						$("."+div+":eq("+pos+") p").html("<a target='_self' class='more_3' href='"+newsP+"'>更多</a>");
						if(obj.length==0){
							$("."+div+":eq("+pos+") ul").html("&nbsp;&nbsp;无相关信息。");
							$("."+div+":eq("+pos+") p a").hide();
						}
					}
				}	  
			});	
			
		}




//最新消息
function getLatestNews(channelID,pos,div){
var html = "";
var rownum = "";
var width = "95%";
if(div=="reportDivTop"){
	rownum = "6";
}else{
	rownum = "12";
	width = "47%";
}
	
	$.ajax({
		url:basePath+"/jeecms/getJeecmsInfo.action?random="+Math.random(),
		type:"post",
		dataType:'json',
		data:{
			method:"JEECMS.GET_CONTENT_LIST",
			channelId:channelID,
			hasTitleImg:"0,1",
			typeId:'1,2,3,4',
			rownum:rownum
		},
		error:function(){
			//alert("系统连接失败，请稍后再试！");
		},
		success: function(obj){			
			var newsLi = "";
			var newsP = "javascript:void(0)";
			if(obj){
				for(var i=0;i<obj.length;i++){
					newsLi += "<li class='clearfix' style='width:"+width+"'><a target='_blank' title='"+setShortTitle(obj[i].shortTitle,obj[i].title)+"'";
					html = obj[i].title;
					if(html.indexOf("[正面新闻]")>-1){
						html = "<img style='display:inline;' src='/portal/meiriyuqing/images/zhengmian.jpg'/>"+html.replace("[正面新闻]","");
					}else if(html.indexOf("[常规新闻]")>-1){
						html = "<img style='display:inline;' src='/portal/meiriyuqing/images/changgui.jpg'/>"+html.replace("[常规新闻]","");
					}else if(html.indexOf("[负面新闻]")>-1){
						html = "<img style='display:inline;' src='/portal/meiriyuqing/images/fumian.jpg'/>"+html.replace("[负面新闻]","");
					} 
					newsLi += " href='"+basePath+"/jeecms/contentDetail.jsp?content="+obj[i].contentId+"'>"+html+"</a><span>"+obj[i].releaseDate.substring(5)+"</span></li>";
					newsP =basePath+ "/jeecms/findStfbNewsByPage.action?channelId="+obj[i].channelId;
				}
			
				$("."+div+":eq("+pos+") ul").html(newsLi);
				$("."+div+":eq("+pos+") p").html("<a target='_self' class='more_3' href='"+newsP+"'>更多</a>");
				if(obj.length==0){
					$("."+div+":eq("+pos+") ul").html("&nbsp;&nbsp;无相关信息。");
					$("."+div+":eq("+pos+") p a").hide();
				}
			}
		}	  
	});	
}


function setShortTitle(shortTitle,Title){
	if(shortTitle=="null"){
		return shortTitle;
	}else{
		return Title;
	}
}

