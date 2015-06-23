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
function getDJGTPicNews(cmsUrl,channelID){
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
					rownum:'1'
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
							playList += "<li><a target='_blank' title='"+setShortTitle(obj[i].shortTitle,obj[i].title)+"' href='"+"../../jeecms/contentDetail.jsp?content="+obj[i].contentId+"'>" +
									"<img src='"+cmsUrl+obj[i].titleImg+"' title='"+obj[i].title+"'></img></a></li>";
							playInfo += "<li><a target='_blank' title='"+setShortTitle(obj[i].shortTitle,obj[i].title)+"' href='"+"../../jeecms/contentDetail.jsp?content="+obj[i].contentId+"'>"
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
					
					$("#newsCenter hgroup a").attr("href",basePath+"/jeecms/findStfbNewsByPage.action?channelId="+channelID);
					$("#newsCenter section a").attr("href","../../jeecms/contentDetail.jsp?content="+obj[i].contentId);
					$("#newsCenter section a h5").html(obj[i].title);
					//$("#newsCenter section a h5").attr("title",obj[i].copyTitle);
					$("#newsCenter section a p").html(obj[i].txt);//obj[i].txt
					//$("#newsCenter section a p").attr("title",obj[i].copyMemo);
					$("#newsCenter section a").attr("title",obj[i].description);
				}
				
			}
		}	  
	});	
		}
		
//党纪工团新闻
function getDJGTNews(channelID){
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

//最新消息
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
					newsLi += "  href='../../jeecms/contentDetail.jsp?content="+obj[i].contentId+"'>"+obj[i].title+"</a><span>"+obj[i].releaseDate.substring(5)+"</span></li>";
					newsP =basePath+ "/jeecms/findStfbNewsByPage.action?channelId="+obj[i].channelId;
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

//侧边新闻
function getLatestNewsAside(channelID,pos){
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
			var newsP = "javascript:void(0)";
			if(obj){
				for(var i=0;i<obj.length;i++){
					newsLi += "<li><a target='_blank' title='"+setShortTitle(obj[i].shortTitle,obj[i].title)+"'";
					newsLi += " href='../../jeecms/contentDetail.jsp?content="+obj[i].contentId+"'>"+obj[i].title+"</a></li>";
					newsP =basePath+ "/jeecms/findStfbNewsByPage.action?channelId="+obj[i].channelId;
				}
				
				$(".asideUl:eq("+pos+")").html(newsLi);
				$(".asideH:eq("+pos+") a").attr("href",newsP);
				$(".asideH:eq("+pos+") a").attr("target","_blank");
				if(obj.length==0){
					$(".asideUl:eq("+pos+")").html("&nbsp;&nbsp;无相关信息。");
					$(".asideH:eq("+pos+") a").hide();
				}
			}
		}	  
	});	
}

function getFGFW(channelID,pos){
	$.ajax({
		url:basePath+"/jeecms/getJeecmsInfo.action?random="+Math.random(),
			type:"post",
			dataType:'json',
			cache : false,
			data:{
				method:"JEECMS.SEARCH_CONTENT_LIST",
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
						if(obj[i].isrecommend=="1"){
							newsLi += "  href='../../jeecms/contentDetail.jsp?content="+obj[i].contentId+"'><font style='float: left;color: red;'>[置顶]</font>"+obj[i].title+"</a><span>"+obj[i].releaseDate.substring(5)+"</span></li>";
						}else{
							newsLi += "  href='../../jeecms/contentDetail.jsp?content="+obj[i].contentId+"'>"+obj[i].title+"</a><span>"+obj[i].releaseDate.substring(5)+"</span></li>";
						}
						
						newsP = basePath+"/jeecms/findStfbNewsByPage.action?channelId="+channelID;
						
					}
					
					$(".reportDiv:eq("+pos+") ul").html(newsLi);
					$(".reportDiv:eq("+pos+") p").html("<a target='_blank' class='more_3' href='"+newsP+"'>更多</a>");
					if(obj.length==0){
						$(".reportDiv:eq("+pos+") ul").html("&nbsp;&nbsp;无相关信息。");
						$(".reportDiv:eq("+pos+") p a").hide();
					}
				}
			}	  
		});	
	}


function drawChart(deptId,role){
	$.ajax({
		type : 'post',
		url : '/portal/pxzx/findTrainDeptDatas.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		error:function(){
			//alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object==null){
				//alert("无相关数据！");
			}else {
				newChartPie(object,"piePic");
			}
		}
	});
	
	$.ajax({
		type : 'post',
		url : '/portal/pxzx/findTrainPeopleDatas.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		error:function(){
			//alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object==null){
				//alert("无相关数据！");
			}else{
				newChartColumn(object[2],object[1],object[0],'blockPic1',deptId,role,true,null);
			}
		}
	});
	
	$.ajax({
		type : 'post',
		url : '/portal/pxzx/findTrainLessonDatas.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		error:function(){
			//alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object==null){
				//alert("无相关数据！");
			}else{
				newChartColumn(object[2],object[1],object[0],'blockPic2',deptId,role,false,null);//null表示百分比只比最大的多不超过20
			}
		}
	});
	
	$.ajax({
		type : 'post',
		url : '/portal/pxzx/findTrainDatas.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		error:function(){
			//alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object==null){
				//alert("无相关数据！");
			}else{
				$("#value1").html(object[0]);
				$("#value2").html(object[1]);
				$("#value3").html(object[2]);
				$("#value4").html(object[3]);
				
				$("ul.zyl").find("li div.colorbg").each(function() {
					alert("背景色："+parseFloat($(this).prev("div.textcontainer").find("p.ui-li-aside").text()));
					if(parseFloat($(this).prev("div.textcontainer").find("p.ui-li-aside").text())<=parseFloat('100%'))
						$(this).css("width", $(this).prev("div.textcontainer").find("p.ui-li-aside").text());
					else 
						$(this).css("width", '100%');
				});
			}
		}
	});
	
	$.ajax({
		type : 'post',
		url : '/portal/pxzx/findEndMonth.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		error:function(){
			//alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object==null){
				//alert("无相关数据！");
			}else{
				$("#h5_title").html("培训业务进展概况与指标(所有统计截止日期到"+object+")");
			}
		}
	});
}


/**
 * 画一个饼状图
 * @param valueList	饼图的值
 * @param renderTo	要显示位置的id
 */
function newChartPie(valueList,renderTo){
	if(valueList==null || valueList==""){
		valueList = new Array();
		valueList.push(0);
		valueList.push(0);
		valueList.push(0);
		valueList.push(0);
		valueList.push(0);
	}
	var  chartOption ={
		chart: {
			renderTo: renderTo,
			plotBackgroundColor: null,
			plotBorderWidth: null,
			plotShadow: false,
	        borderWidth:0,
	        height: 250
		},
		title: {
			text: ''
		},
		credits: {
			enabled:false
		},
		tooltip: {
             formatter: function() {
               return this.point.name + '<br>值:'+ this.y +' 人';
            },
            style: {
                padding: '10px',
                fontWeight: 'bold',
                fontSize:'12px'
            }
        },
		plotOptions: {
			pie: {
       	 	size:'100%',
				allowPointSelect: true,
				cursor: 'pointer',
				dataLabels: {
					enabled: true,			
					color: 'black',			
					connectorColor: '#333',
					distance: 10,
					style: {		
                   		fontSize:'12px'
                	},
					formatter: function() {
						return this.percentage.toFixed(2)+'%';	//this.percentage 百分比
					}
				},
				showInLegend: true
			}
		},
		legend:{
			align:'right',
			layout:'vertical',
			verticalAlign: 'middle',
			x:-50,
			itemMarginBottom:10
		},
		series: [{
			type: 'pie',
			name: '值',
			data: [
			    {	name :'初级',
					y : valueList[0],
					color : '#8B1A1A'
				},
				{	name :'中级',
					y : valueList[1],
					color : '#79BB25'
				},
				{	name :'高级',
					y : valueList[2],
					color : '#FFC002'
				},
				{	name :'技师',
					y : valueList[3],
					color : '#4572A7'
				},
				{	name :'高技',
					y : valueList[4],
					color : '#FE5917'
				}
			]
		}]
	};
	new Highcharts.Chart(chartOption); 
}

function newChartColumn(valueList,deptIdList,dateList,renderTo,deptId,role,ifClick,maxLength){
	var seriesList = "";
	seriesList = "[";
	if(valueList!=null&&valueList.length>0){
		for(var i = 0;i<valueList.length;i++){
			if(i>0){
				seriesList += ",";
			}
			seriesList += "{name:'值',y:"+valueList[i]+",url:'/portal/center/pxzx/page2.jsp?deptId="+deptIdList[i]+"&deptName="+encodeURI(dateList[i])+"&role="+role+"'}";
		}
	}
	seriesList += "]";
	seriesList =  eval("(" + seriesList + ")");
 	var  chartOption ={
 		chart: {
 		    renderTo: renderTo,
 		    type: 'bar',
 		    height: 350,
 		    borderWidth:0
 		},
 		credits:{					
 			enabled:false
 		},
 		legend: {					
 					enabled: false
 				},
 		title: {
 			text:null
 		},
 		tooltip: {
              formatter: function() {
                 return this.x + '<br>值:'+ this.y +'%';
             },
             style: {
                 padding: '10px',
                 fontWeight: 'bold',
                 fontSize:'12px'
             }
         },
 		xAxis: {
 			categories:dateList,
 			minPadding: 0.05,
 			maxPadding: 0.05,
 			labels: {
                 style: {
                     fontSize:'9px'
                 }
             }
 		},
 		yAxis:{
 			title: {
 		    	text: null
 			}, 
 			labels: {
 				align: 'right',
                 style: {
                     fontSize:'9px'
                 },
                 format:'{value}%'
             },
             max:maxLength
 		},
 		plotOptions: {
 		    series: {
 		        cursor: 'pointer',
 		        events: {
 		            click: function(e) {
 		                //window.open("/portal/center/pxzx/page2.jsp?deptId="+deptId);
				 		if(ifClick){			
				 			if(role=="dept"){
	 							if(e.point.url.indexOf(deptId)>-1){
	 								window.open(e.point.url);
	 							}else{
	 								window.open("");
	 							}
	 						}else{
	 							window.open(e.point.url);
	 						}
				 		}
	 			    }
	 			}
 		    }
 		}
 	};
 	
	chartOption.series = [{
		name:'值',
		data:seriesList
	}]
 	  		
 	new Highcharts.Chart(chartOption); 
 }