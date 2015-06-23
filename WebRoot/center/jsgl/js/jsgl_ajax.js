//replaceall方法
	String.prototype.replaceAll  = function(s1,s2){  
	  return this.replace(new RegExp(s1,"gm"),s2);   
	};
	//String date=new Date().getFullYear()+"/"+new Date().+
function showTable1(obj3){
	$.ajax({
		type : 'post',
		url : '/portal/jsgl/findProjects.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		error:function(){
			//alert("系统连接失败，请稍后再试！")
		},
		success:function(obj2){
			if(obj2==null){
				//alert("无相关数据！");
			}else{
				$.ajax({
					type : 'post',
					url : '/portal/jsgl/findPaperDatas.action?random='+Math.random(),
					dataType:'json',
					cache : false,
					error:function(){
						//alert("系统连接失败，请稍后再试！")
					},
					success:function(object){
						if(object==null){
							//alert("无相关数据！");
						}else{
							var addHtml = "<tr class='table_tr' style='background:#fff;line-height:36px;'><td class='t_c table_td' style='font-weight: bold;'>项目名称</td><td class='t_c table_td' style='font-weight: bold;'>施工证数</td><td class='t_c table_td' style='font-weight: bold;'>完成数</td><td class='t_c table_td' style='font-weight: bold;'>完成比(%)</td></tr>";
							var persent = 0;
							var sum_1 = 0;
							var sum_2 = 0;
							for(var i=0;i<obj2.length;i++){
								if(object[0][i]==0){
									persent = 0;
								}else{
									persent = (object[1][i]*100/object[0][i]).toFixed(2);
								}
								sum_1 += object[0][i];
								sum_2 += object[1][i];
								addHtml += "<tr class='table_tr'><td class='t_l table_td'>"+obj2[i]+"</td><td class='t_r table_td'>"+object[0][i]+"</td><td class='t_r table_td'>"+object[1][i]+"</td><td class='t_r table_td'>"+persent+"%</td></tr>";
							}
							var sum_persent = (sum_2*100/sum_1).toFixed(2);
							addHtml += "<tr class='table_tr' id='grayTr'><td class='t_l table_td'>合计</td><td class='t_r table_td'>"+sum_1+"</td><td class='t_r table_td'>"+sum_2+"</td><td class='t_r table_td'>"+sum_persent+"%</td></tr>";
							$("#table_1").find("table").html(addHtml);
							
							if(obj3!=null){
								$(obj3).parents("ul").find("a").attr("class","");
								$(obj3).attr("class","Lb_08");
							}
							$("#table_1").children("div").attr("style","");
							$("#h3").html("各项目“施工许可证”办理情况");
							
							var $tblAlterRow1 = $("#table_1 table tbody tr:odd");
				            if ($tblAlterRow1.length > 0)
				                $tblAlterRow1.css("background","#f8f8f8"); 
				            $("[id=grayTr]").attr("style","background:#bababa;line-height:42px; ");
							showPic1(object,obj2);
						}
					}
				});
			}
		}
	});
}

function showTable1(obj3,paperId){
	var year=$("#year").val();
	if(null==year||''==year){
		year='all';
	}
	$.ajax({
		type : 'post',
		url : '/portal/jsgl/findProjects.action?random='+Math.random(),
		data:{paperId:paperId},
		dataType:'json',
		cache : false,
		error:function(){
			//alert("系统连接失败，请稍后再试！")
		},
		success:function(obj2){
			if(obj2==null){
				//alert("无相关数据！");
			}else{
				$.ajax({
					type : 'post',
					url : '/portal/jsgl/findPaperDatas.action?random='+Math.random(),
					data:{paperId:paperId,year:year},
					dataType:'json',
					cache : false,
					error:function(){
						//alert("系统连接失败，请稍后再试！")
					},
					success:function(object){
						if(object==null){
							//alert("无相关数据！");
						}else{
							var addHtml = "<tr class='table_tr' style='background:#fff;line-height:36px;'><td class='t_c table_td' style='font-weight: bold;'>项目名称</td><td class='t_c table_td' style='font-weight: bold;'>施工证数</td><td class='t_c table_td' style='font-weight: bold;'>完成数</td><td class='t_c table_td' style='font-weight: bold;'>完成比(%)</td></tr>";
							var persent = 0;
							var sum_1 = 0;
							var sum_2 = 0;
							for(var i=0;i<obj2.length;i++){
								if(object[0][i]==0){
									persent = 0;
								}else{
									persent = (object[1][i]*100/object[0][i]).toFixed(2);
								}
								sum_1 += object[0][i];
								sum_2 += object[1][i];
								addHtml += "<tr class='table_tr'><td class='t_l table_td'>"+obj2[i]+"</td><td class='t_r table_td'>"+object[0][i]+"</td><td class='t_r table_td'>"+object[1][i]+"</td><td class='t_r table_td'>"+persent+"%</td></tr>";
							}
							var sum_persent = (sum_2*100/sum_1).toFixed(2);
							addHtml += "<tr class='table_tr' id='grayTr'><td class='t_l table_td'>合计</td><td class='t_r table_td'>"+sum_1+"</td><td class='t_r table_td'>"+sum_2+"</td><td class='t_r table_td'>"+sum_persent+"%</td></tr>";
							$("#table_1").find("table").html(addHtml);
							
							if(obj3!=null){
								$(obj3).parents("ul").find("a").attr("class","");
								$(obj3).attr("class","Lb_08");
							}
							$("#table_1").children("div").attr("style","");
							//alert("paperId:"+paperId);
							//String date="1";//=new SimpleDateFormat("yyyy/MM/dd").format(new Date());
							//String date=new Date().g;
							var date = new Date().getFullYear()+"/"+(new Date().getMonth()+1)+"/"+new Date().getDate();   // HH:mm:ss
							if('20'==paperId){
								if('all'==year){
									$("#h3").html("各项目“施工许可证”办理情况(所有办证计划与当前完成情况)");
								}else if('now'==year){//+"/"+new Date().getUTCDay()
									$("#h3").html("各项目“施工许可证”办理情况  (截止当天办证计划与当前完成情况)");//+new Date().getFullYear()+"/"+new Date().getMonth()+1+"/"+new Date().getTime()
								}else{
									$("#h3").html("各项目“施工许可证”办理情况  ("+year+"年度的办证计划与当前完成情)");
								}
							}
							if('3'==paperId){
								if('all'==year){
									$("#h3").html("各项目“规划用地”办理情况(所有办证计划与当前完成情况)");
								}else if('now'==year){
									$("#h3").html("各项目“规划用地”办理情况(截止当天办证计划与当前完成情况)");
								}else{
									$("#h3").html("各项目“规划用地”办理情况("+year+"年度的办证计划与当前完成情)");
								}
							}
							if('2'==paperId){
								if('all'==year){
									$("#h3").html("各项目“规划方案”办理情况(所有办证计划与当前完成情况)");
								}else if('now'==year){
									$("#h3").html("各项目“规划方案”办理情况(截止当天办证计划与当前完成情况)");
								}else{
									$("#h3").html("各项目“规划方案”办理情况("+year+"年度的办证计划与当前完成情)");
								}
							}
							
							$("#paperId").val(paperId);
							var $tblAlterRow1 = $("#table_1 table tbody tr:odd");
				            if ($tblAlterRow1.length > 0)
				                $tblAlterRow1.css("background","#f8f8f8"); 
				            $("[id=grayTr]").attr("style","background:#bababa;line-height:42px; ");
							showPic1(object,obj2);
						}
					}
				});
			}
		}
	});
}

function showTable1_2(obj3){
	var year=$("#year").val();
	if(null==year||''==year){
		year='all';
	}
	$.ajax({
		type : 'post',
		url : '/portal/jsgl/findPapers.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		error:function(){
			//alert("系统连接失败，请稍后再试！")
		},
		success:function(obj2){
			if(obj2==null){
				//alert("无相关数据！");
			}else{
				$.ajax({
					type : 'post',
					url : '/portal/jsgl/findDatasByPaper.action?random='+Math.random(),
					data:{year:year},
					dataType:'json',
					cache : false,
					error:function(){
						//alert("系统连接失败，请稍后再试！")
					},
					success:function(object){
						if(object==null){
							//alert("无相关数据！");
						}else{
							var addHtml = "<tr class='table_tr' style='background:#fff;line-height:36px;'><td class='t_c table_td' style='font-weight: bold;'>证件名称</td><td class='t_c table_td' style='font-weight: bold;'>计划数</td><td class='t_c table_td' style='font-weight: bold;'>完成数</td><td class='t_c table_td' style='font-weight: bold;'>完成比(%)</td></tr>";
							var persent = 0;
							var sum_1 = 0;
							var sum_2 = 0;
							for(var i=0;i<obj2.length;i++){
								if(object[0][i]==0){
									persent = 0;
								}else{
									persent = (object[1][i]*100/object[0][i]).toFixed(2);
								}
								sum_1 += object[0][i];
								sum_2 += object[1][i];
								addHtml += "<tr class='table_tr'><td class='t_l table_td'>"+obj2[i]+"</td><td class='t_r table_td'>"+object[0][i]+"</td><td class='t_r table_td'>"+object[1][i]+"</td><td class='t_r table_td'>"+persent+"%</td></tr>";
							}
							var sum_persent = (sum_2*100/sum_1).toFixed(2);
							addHtml += "<tr class='table_tr' id='grayTr'><td class='t_l table_td'>合计</td><td class='t_r table_td'>"+sum_1+"</td><td class='t_r table_td'>"+sum_2+"</td><td class='t_r table_td'>"+sum_persent+"%</td></tr>";
							$("#table_1").find("table").html(addHtml);
							
							if(obj3!=null){
								$(obj3).parents("ul").find("a").attr("class","");
								$(obj3).attr("class","Lb_08");
							}
							$("#table_1").children("div").attr("style","height:310px;overflow:auto;");
							var date = new Date().getFullYear()+"/"+(new Date().getMonth()+1)+"/"+new Date().getDate();
							if('all'==year){
								$("#h3").html("各证件办理情况(所有办证计划与当前完成情况)");
							}else if('now'==year){
								$("#h3").html("各证件办理情况(截止当天办证计划与当前完成情况)");
							}else{
								$("#h3").html("各证件办理情况("+year+"年度的办证计划与当前完成情)");
							}
							$("#paperId").val("证件维度");
							var $tblAlterRow1 = $("#table_1 table tbody tr:odd");
				            if ($tblAlterRow1.length > 0)
				                $tblAlterRow1.css("background","#f8f8f8"); 
				            $("[id=grayTr]").attr("style","background:#bababa;line-height:42px; ");
							showPic1(object,obj2);
						}
					}
				});
			}
		}
	});
}

function showTable1_3(obj3){
	var year=$("#year").val();
	if(null==year||''==year){
		year='all';
	}
	$.ajax({
		type : 'post',
		url : '/portal/jsgl/findDepts.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		error:function(){
			//alert("系统连接失败，请稍后再试！")
		},
		success:function(obj2){
			if(obj2==null){
				//alert("无相关数据！");
			}else{
				$.ajax({
					type : 'post',
					url : '/portal/jsgl/findDatasByDept.action?random='+Math.random(),
					data:{year:year},
					dataType:'json',
					cache : false,
					error:function(){
						//alert("系统连接失败，请稍后再试！")
					},
					success:function(object){
						if(object==null){
							//alert("无相关数据！");
						}else{
							var addHtml = "<tr class='table_tr' style='background:#fff;line-height:36px;'><td class='t_c table_td' style='font-weight: bold;'>审批部门名称</td><td class='t_c table_td' style='font-weight: bold;'>计划数</td><td class='t_c table_td' style='font-weight: bold;'>完成数</td><td class='t_c table_td' style='font-weight: bold;'>完成比(%)</td></tr>";
							var persent = 0;
							var sum_1 = 0;
							var sum_2 = 0;
							for(var i=0;i<obj2.length;i++){
								if(object[0][i]==0){
									persent = 0;
								}else{
									persent = (object[1][i]*100/object[0][i]).toFixed(2);
								}
								sum_1 += object[0][i];
								sum_2 += object[1][i];
								addHtml += "<tr class='table_tr'><td class='t_l table_td'>"+obj2[i]+"</td><td class='t_r table_td'>"+object[0][i]+"</td><td class='t_r table_td'>"+object[1][i]+"</td><td class='t_r table_td'>"+persent+"%</td></tr>";
							}
							var sum_persent = (sum_2*100/sum_1).toFixed(2);
							addHtml += "<tr class='table_tr' id='grayTr'><td class='t_l table_td'>合计</td><td class='t_r table_td'>"+sum_1+"</td><td class='t_r table_td'>"+sum_2+"</td><td class='t_r table_td'>"+sum_persent+"%</td></tr>";
							$("#table_1").find("table").html(addHtml);
							
							if(obj3!=null){
								$(obj3).parents("ul").find("a").attr("class","");
								$(obj3).attr("class","Lb_08");
							}
							$("#table_1").children("div").attr("style","height:310px;overflow:auto;");
							var date = new Date().getFullYear()+"/"+(new Date().getMonth()+1)+"/"+new Date().getDate();
							if('all'==year){
								$("#h3").html("各审批部门办理情况(所有办证计划与当前完成情况)");
							}else if('now'==year){
								$("#h3").html("各审批部门办理情况(截止当天办证计划与当前完成情况)");
							}else{
								$("#h3").html("各审批部门办理情况("+year+"年度的办证计划与当前完成情)");
							}
							$("#paperId").val("审批部门维度");
							
							var $tblAlterRow1 = $("#table_1 table tbody tr:odd");
				            if ($tblAlterRow1.length > 0)
				                $tblAlterRow1.css("background","#f8f8f8"); 
				            $("[id=grayTr]").attr("style","background:#bababa;line-height:42px; ");
							showPic1(object,obj2);
						}
					}
				});
			}
		}
	});
}
	
function showPic1(obj,obj2){
    $('#pic1').highcharts({
    	chart: {
    		renderTo: 'gray_border',//
	        width:$(".gray_border").width()-16,
	        height:300
	    },
        title: {
            text: ''
        },
        exporting : {
			enabled : false
		},
        credits: {
			enabled:false//商标
		},
        xAxis: {
            categories: obj2,//标题
            
            labels:{
            	rotation: 45
            	//staggerLines:2//上下两层
            }
		 /*labels: { 
             
             style: { 
                 fontSize: '13px', 
                 fontFamily: 'Verdana, sans-serif',
                 writingMode:'tb-rl'    //文字竖排样式
             }
         }*/
        },
        yAxis: {
            min: 0,
            title: {
                text: '数量(个)'
            }
        },
        plotOptions: {
            column: {
                stacking: 'normal'//上下叠在一起
            }
        },
        series: [{
            type: 'column',
            name: '剩余数',
            data: obj[2],
            color:'#B22222'
        }, {
            type: 'column',
            name: '完成数',
            data: obj[1],
            color:'#4876FF'
        }, {
            type: 'line',
            name: '计划数',
            data: obj[0],
            color:'#7CCD7C'
        }],
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle'
        }
        

    });
}
	
function showTable2(){
	$.ajax({
		type : 'post',
		url : '/portal/jsgl/findInvestDatas.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		error:function(){
			//alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object==null){
				//alert("无相关数据！");
			}else{
				for(var i=0;i<object.length;i++){
					for(var j=0;j<object[i].length;j++){
						$("#td_"+i+"_"+j).html(object[i][j]);
					}
				}
				showPic2();
			}
		}
	});
}

function showPic2(){
	var lineList = new Array();
	var dataList1 = new Array();
	var dataList2 = new Array();
	var dataList3 = new Array();
	
	$("#table_2").find("tr").each(function(i){
		if(i!=0&&i!=14){
			lineList.push($(this).children("td:eq(0)").html().replace("GM7","7号线").replace("GM11南","11号线南").replace("GM12","12号线").replace("GM13","13号线").replace("GM11北二","11号线北").replace("GM5南","5号线南").replace("GM9","9号线").replace("GM10(二）","10号线二").replace("GM17","17号线").replace("L18","18号线").replace("GM14","14号线").replace("GM8(三)","8号线三").replace("GM15","15号线"));
			dataList1.push(parseInt($(this).children("td:eq(1)").html().replaceAll(",","")));
			dataList2.push(parseInt($(this).children("td:eq(2)").html().replaceAll(",",""))+parseInt($(this).children("td:eq(3)").html().replaceAll(",","")));
			dataList3.push(parseInt($(this).children("td:eq(5)").html().replaceAll(",","")));
		}
	});
	$('#pic2').highcharts({
        chart: {
            type: 'column',
            height: 300,
            width:$(".gray_border").width()-16
        },
        title: {
            text: ''
        },
        credits: {
			enabled:false
		},
        xAxis: {
            categories: lineList
        },
        yAxis: {
        	//tickInterval:5000,
            min: 0,
            title: {
                text: '金额 (万元)'
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.2f} 万元</b></td></tr>',//显示小数位数
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
            name: '概算',
            data: dataList1

        }, {
            name: '已发生成本',
            data: dataList2

        }, {
            name: '累计支付',
            data: dataList3

        }]
    });
}

function showTable3(){
	$.ajax({
		type : 'post',
		url : '/portal/jsgl/findPlatformDatas.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		error:function(){
			//alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object==null){
				//alert("无相关数据！");
			}else{
				for(var i=0;i<object.length;i++){
					for(var j=0;j<object[i].length;j++){
						$("#td3_"+i+"_"+j).html(object[i][j]);
					}
				}
				showPic3();
			}
		}
	});
}

function showPic3(){
	var lineList = new Array();
	var dataList1 = new Array();
	var dataList2 = new Array();
	var dataList3 = new Array();
	var dataList4 = new Array();
	
	
	lineList.push("数据量");
	lineList.push("流程交易量");
	lineList.push("平台登录数");
	
	dataList1.push(parseInt($("#table_3").find("[id=tr3]:eq(0)").children("td:eq(2)").html().replaceAll(",","")));
	dataList1.push(parseInt($("#table_3").find("[id=tr3]:eq(0)").children("td:eq(4)").html().replaceAll(",","")));
	dataList1.push(parseInt($("#table_3").find("[id=tr3]:eq(0)").children("td:eq(6)").html().replaceAll(",","")));
	
	dataList2.push(parseInt($("#table_3").find("[id=tr3]:eq(1)").children("td:eq(2)").html().replaceAll(",","")));
	dataList2.push(parseInt($("#table_3").find("[id=tr3]:eq(1)").children("td:eq(4)").html().replaceAll(",","")));
	dataList2.push(parseInt($("#table_3").find("[id=tr3]:eq(1)").children("td:eq(6)").html().replaceAll(",","")));
	
	dataList3.push(parseInt($("#table_3").find("[id=tr3]:eq(2)").children("td:eq(2)").html().replaceAll(",","")));
	dataList3.push(parseInt($("#table_3").find("[id=tr3]:eq(2)").children("td:eq(4)").html().replaceAll(",","")));
	dataList3.push(parseInt($("#table_3").find("[id=tr3]:eq(2)").children("td:eq(6)").html().replaceAll(",","")));
	
	dataList4.push(parseInt($("#table_3").find("[id=tr3]:eq(3)").children("td:eq(2)").html().replaceAll(",","")));
	dataList4.push(parseInt($("#table_3").find("[id=tr3]:eq(3)").children("td:eq(4)").html().replaceAll(",","")));
	dataList4.push(parseInt($("#table_3").find("[id=tr3]:eq(3)").children("td:eq(6)").html().replaceAll(",","")));
	$('#pic3').highcharts({
        chart: {
            type: 'column',
            height:240,
            width:$(".gray_border").width()-16
        },
        title: {
            text: ''
        },
        credits: {
			enabled:false
		},
        xAxis: {
            categories: lineList
        },
        yAxis: {
            min: 0,
            title: {
                text: '数量(个)'
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.1f} </b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
            name: '13号线',
            data: dataList1

        }, {
            name: '12号线',
            data: dataList2

        }, {
            name: '11号线南',
            data: dataList3

        }, {
            name: '7号线',
            data: dataList4

        }]
    });
}


//aside cms
function getLatestNewsAside(sj_id,part_id,pos){
			
			$.ajax({
				type: 'POST',
				url: '/portal/infoSearch/findStfbNewsLatestEvents.action?random='+Math.random(),
				data:{
					"sj_id" : sj_id,
					"part_id":part_id,
					"size"	:	"5"
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


function getZttp(pos){
			
			$.ajax({
				type: 'POST',
				url : '/portal/jsgl/findZttp.action?random='+Math.random(),
				dataType:'json',
				cache : false,
				error:function(){/**alert('系统连接失败，请稍后再试！')*/},
				success: function(obj){			
					var newsLi = "";
					var newsP = "javascript:void(0)";
					if(obj){
						for(var i=0;i<obj.length;i++){
							newsLi += "<li><a target='_blank' title='"+obj[i][1]+"'";
							newsLi += " href='http://10.1.44.18/stfb/frame/pic_content_jsgl.jsp?id="+obj[i][0]+"'>"+obj[i][1]+"</a></li>";
							newsP = "http://10.1.44.18/stfb/frame/jsgl/detail_jsgl.jsp?part=czyj";
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
