<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>工程建设进度跟踪</title>
<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/default/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
<!--[if IE 6.0]>
           <script src="../js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
        <script src="../js/html5.js"></script>
        <script src="../js/jquery-1.7.1.js"></script>
		<script src="../js/jquery.formalize.js"></script>
		<!--<script src="../js/switchDept.js"></script>-->
		<script src="../js/show.js"></script>
		<script src="js/jquery.tablesorter.js"></script>
		<link type="text/css" href="../css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="../js/flick/jquery-ui-1.8.18.custom.min.js"></script>
		<script type="text/javascript" src="../js/flick/jquery.ui.datepicker-zh-CN.js"></script>
        <style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
        .red1 {color:#FBF5EF;font-weight:bold;}
        .red2 {color:#F6E3CE;font-weight:bold;}
        .red3 {color:#F7BE81;font-weight:bold;}
        .red4 {color:#FE9A2E;font-weight:bold;}
        .red5 {color:#DF7401;font-weight:bold;}
        .back1 {background-color:#FFEC8B;}
        .back2 {background-color:#FFDAB9;}
        .back3 {background-color:#7AC5CD;}
		</style>		
	<script type="text/javascript">
	var project_id = "";

	function showDetailData(obj){
		project_id = $(obj).find("#project_id").val();
		$("[name=detailType][value=1]").attr("checked",true);
		$("[id=baseTr]").attr("style","background-color:0");
		$(obj).parents("tr").attr("style","background-color:rgb(226, 231, 243);");
		showDetailDataByType();
	}
	
	function showDetailDataByType(){
		var detailType = $("[name=detailType]:checked").val();
		var url = "build/getProcessDetailList";
		if(detailType==1){
			$("#detailTypeName").html("计划类型");
			$("#tr1").show();
			$("#tr2").hide();
		}else if(detailType==2){
			$("#detailTypeName").html("单体");
			$("#tr1").show();
			$("#tr2").hide();
		}else if(detailType==3){
			url = "build/getMonthDetailData";
			$("#tr2").show();
			$("#tr1").hide();
		}
		$.ajax({
			type : 'post',
			url : url+'.action?random='+Math.random(),
			dataType:'json',
			data:{
				processType:$("[name=processType]:checked").val(),
				project_id:project_id,
				detailType:detailType
			},
			cache : false,
			error:function(){
				alert("系统连接失败，请稍后再试！")
			},
			success:function(object){
				$("[id^=addTr]").remove();
				if(object!=null&&object.length>0){
					var addHtml = "";
					for(var i=0;i<object.length;i++){
						addHtml += "<tr id='addTr'><td class='t_c'>"+(i+1)+"</td>";
						for(var j=0;j<(object[i].length-1);j++){
							addHtml += "<td class='t_c'>"+object[i][j]+"</td>";
						}
						addHtml += "<td class='t_c' style='display:none;'>"+object[i][object[i].length-1]+"</td>";
						addHtml += "</tr>";
					}
					$("#detailData").append(addHtml);
					
					if(detailType==3){
						monthSum();
					}
					setColor();
					if($("[name=detailType]:checked").val()!='3'){
						taskClickFunc();
					}
				}
			}
		});
	}

	function chooseType(obj){
		$("#form").submit();
	}

	$(function(){
		var processType_value = $("#hidden_processType").val();
		if(processType_value=='2'){
			$("[name=processType]:eq(1)").attr("checked",true);
			$("#typeName").html("单位名称");
		}else{
			$("[name=processType]:eq(0)").attr("checked",true);
			$("#typeName").html("项目名称");
		}
		setColor();

		$('#mytable').tablesorter({ sortList:[[2,1]] });
	});

	function monthSum(){
		var begin_plan_sum = 0;
		var begin_real_sum = 0;
		var begin_delay_sum = 0;
		var begin_persent = 0;
		var finish_plan_sum = 0;
		var finish_real_sum = 0;
		var finish_delay_sum = 0;
		var finish_persent = 0;

		var begin_plan_sum_year = 0;
		var begin_real_sum_year = 0;
		var begin_delay_sum_year = 0;
		var begin_persent_year = 0;
		var finish_plan_sum_year = 0;
		var finish_real_sum_year = 0;
		var finish_delay_sum_year = 0;
		var finish_persent_year = 0;

		$("tr[id=addTr]").each(function(i){
			begin_plan_sum += parseInt($(this).children("td:eq(4)").html());
			begin_real_sum += parseInt($(this).children("td:eq(5)").html());
			begin_delay_sum += parseInt($(this).children("td:eq(6)").html());
			
			finish_plan_sum += parseInt($(this).children("td:eq(8)").html());
			finish_real_sum += parseInt($(this).children("td:eq(9)").html());
			finish_delay_sum += parseInt($(this).children("td:eq(10)").html());

			if(i==($("tr[id=addTr]").length-1)||$(this).children("td:eq(1)").html()!=$(this).next().children("td:eq(1)").html()){
				if(begin_plan_sum>0){
					begin_persent = parseInt(begin_delay_sum*100/begin_plan_sum);
				}
				if(finish_plan_sum>0){
					finish_persent = parseInt(finish_delay_sum*100/finish_plan_sum);
				}
				
				$(this).after("<tr id='addTrMonth' style='background-color:rgb(226, 231, 243);'><td colspan=4 class='t_c'>小计</td><td class='t_c'>"+begin_plan_sum+"</td><td class='t_c'>"+begin_real_sum+"</td><td class='t_c'>"+begin_delay_sum+"</td><td class='t_c'>"+begin_persent+"%</td><td class='t_c'>"+finish_plan_sum+"</td><td class='t_c'>"+finish_real_sum+"</td><td class='t_c'>"+finish_delay_sum+"</td><td class='t_c'>"+finish_persent+"%</td></tr>");
				begin_plan_sum_year += begin_plan_sum;
				begin_real_sum_year += begin_real_sum;
				begin_delay_sum_year += begin_delay_sum;
				finish_plan_sum_year += finish_plan_sum;
				finish_real_sum_year += finish_real_sum;
				finish_delay_sum_year += finish_delay_sum;

				begin_plan_sum = 0;
				begin_real_sum = 0;
				begin_delay_sum = 0;
				begin_persent = 0;
				finish_plan_sum = 0;
				finish_real_sum = 0;
				finish_delay_sum = 0;
				finish_persent = 0;
			}

			if(i==($("tr[id=addTr]").length-1)){
				if(begin_plan_sum_year>0){
					begin_persent_year = parseInt(begin_delay_sum_year*100/begin_plan_sum_year);
				}
				if(finish_plan_sum_year>0){
					finish_persent_year = parseInt(finish_delay_sum_year*100/finish_plan_sum_year);
				}
				$(this).next().after("<tr id='addTrYear' style='background-color:rgb(226, 231, 243);'><td colspan=4 class='t_c'>总计</td><td class='t_c'>"+begin_plan_sum_year+"</td><td class='t_c'>"+begin_real_sum_year+"</td><td class='t_c'>"+begin_delay_sum_year+"</td><td class='t_c'>"+begin_persent_year+"%</td><td class='t_c'>"+finish_plan_sum_year+"</td><td class='t_c'>"+finish_real_sum_year+"</td><td class='t_c'>"+finish_delay_sum_year+"</td><td class='t_c'>"+finish_persent_year+"%</td></tr>");
				
			}
		});
	}
	
	function setColor(){
		var persent = 0;
		$("td").each(function(){
			if($(this).html().indexOf("%")>-1){
				persent = parseInt($(this).text().replace("%",""));
				if(persent>0&&persent<10){
					$(this).addClass("red5");
				}else if(persent>=10&&persent<20){
					$(this).addClass("red5");
				}else if(persent>=20&&persent<30){
					$(this).addClass("red5");
				}else if(persent>=30&&persent<50){
					$(this).addClass("red5");
				}else if(persent>=50){
					$(this).addClass("red5");
				}
			}	
		});

		if($("[name=detailType]:checked").val()!='3'){
			$("[id=addTr]").each(function(){
				$(this).children("td:eq(3)").addClass("back1");
				$(this).children("td:eq(4)").addClass("back1");
				$(this).children("td:eq(5)").addClass("back2");
				$(this).children("td:eq(6)").addClass("back2");
				$(this).children("td:eq(7)").addClass("back3");
				$(this).children("td:eq(8)").addClass("back3");
				$(this).children("td:eq(9)").addClass("back3");
			});
		}else{
			$("[id=addTr]").each(function(){
				$(this).children("td:eq(4)").addClass("back1");
				$(this).children("td:eq(5)").addClass("back1");
				$(this).children("td:eq(6)").addClass("back1");
				$(this).children("td:eq(7)").addClass("back1");
				$(this).children("td:eq(8)").addClass("back2");
				$(this).children("td:eq(9)").addClass("back2");
				$(this).children("td:eq(10)").addClass("back2");
				$(this).children("td:eq(11)").addClass("back2");
			});
		}
	}

	function taskClickFunc(){
		var plan_id = "";
		var text = "";
		var text1 = "";
		var text2 = "";
		var url = "";
		var processType = $("[name=processType]:checked").val();
		var detailType = $("[name=detailType]:checked").val();
		$("[id=addTr]").each(function(){
			plan_id = $.trim($(this).find("td:hidden").text());
			text = $.trim($(this).children("td:eq(2)").text());
			text1 = $.trim($(this).children("td:eq(3)").text());
			text2 = $.trim($(this).children("td:eq(5)").text());
			url = "getTasksList.action?project_id="+project_id+"&plan_id="+plan_id+"&detailType="+detailType+"&processType="+processType;
			$(this).children("td:eq(2)").html("<a href='"+url+"' target='_blank'>"+text+"</a>");
			$(this).children("td:eq(3)").html("<a href='"+url+"&type=startDelay' target='_blank'>"+text1+"</a>");
			$(this).children("td:eq(5)").html("<a href='"+url+"&type=finishDelay' target='_blank'>"+text2+"</a>");
		});
	}
    </script>



</head>

<body>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
			<div class="fl"><img id="show" onclick="showHide();" src="../css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li class="fin">工程建设进度跟踪</li>
                </ul>
            </div>
            <div style="display:none;" class="fr lit_nav nwarp">
            	<ul>
                    <li class="selected"><a class="print" href="#">打印</a></li>
                    <li><a class="express" href="#">导出数据</a></li>
                    <li class="selected"><a class="table" href="#">表格模式</a></li>
                    <li><a class="treeOpen" href="#">打开树</a></li>
                    <li><a class="filterClose" href="#">关闭过滤</a></li>
                </ul>
            </div>
   		</div>
        <!--Ctrl End-->
      <div class="pt45">
        <!--Filter-->
      <div class="filter">
        	<div class="query">
        	<div class="p8">
        	<s:form action="getProcessList" id="form" method="post"  namespace="/build">
        	  <input type="hidden" id="hidden_processType" value="<s:property value='#request.processType'/>">
        	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
        	    <tr>
        	     <td class="t_l">
					<input type="radio" value="1" name="processType" onclick="chooseType();">按项目线路
					<input type="radio" value="2" name="processType" onclick="chooseType();">按承建集团单位
				</td>
      	      </tr> 
      	      
      	      
      	    </table>
      	    </s:form>
      	    </div>
        	</div>     
</div>

      
        <!--Filter End-->
        <!--Table-->
         <s:set name="r" value="#request.processList"></s:set>  
        <div class="mb10">
        	<table width="100%"  class="table_1"  id="mytable">
                              <thead>
                              <tr class="tit">
                                <td class="t_c" width="5%" style="height:0"></td>
                                <td class="t_c" width="55%" id="typeName" style="height:0"></td>
                                <td class="t_c" width="20%" style="height:0">里程碑任务</td>
                                <td class="t_c" width="20%" style="height:0">所有任务</td>
                                </tr>
                                </thead>
                               <tbody> 
                              <s:iterator value="#r" id="items" status="s">
                              <tr id="baseTr">
                              	<td class="t_c"><!--<s:property value="#s.index+1"/>--></td>
                              	<td class="t_l">
                              		<a href="javascript:void(0)" onclick="showDetailData(this)">
                              			<s:property value="#items[1]"/>
                              			<input type="hidden" id="project_id" value="<s:property value='#items[0]'/>">
                              		</a>
                              	</td>
                               	<td class="t_c"><s:property value="#items[2]" /></td>
                               	<td class="t_c"><s:property value="#items[3]" /></td>
                                </tr>
                                </s:iterator>
                              </tbody>
                            </table>

      </div>
      
      
         <div class="filter">
        	<div class="query">
        	<div class="p8 ">
        	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
        	    <tr>
        	     <td class="t_l">
					<input type="radio" value="1" name="detailType" onclick="showDetailDataByType();">按计划类型
					<input type="radio" value="2" name="detailType" onclick="showDetailDataByType();">按单体
					<!-- <input type="radio" value="3" name="detailType" onclick="showDetailDataByType();">按月度进度  -->
				</td>
      	      </tr> 
      	      
      	      
      	    </table>
      	    </div>
        	</div>     
</div>

      <div class="mb10">
        	<table width="100%"  class="table_1">
                              <tbody id="detailData">
                              <tr class="tit" id="tr1">
                                <td class="t_c" width="3%">序号</td>
                                <td class="t_c" width="15%" id="detailTypeName"></td>
                                <td class="t_c" width="12%">计划任务(项)</td>
                                <td class="t_c" width="10%">开工延误</td>
                                <td class="t_c" width="10%">延误率(%)</td>
                                <td class="t_c" width="10%">竣工延误</td>
                                <td class="t_c" width="10%">延误率(%)</td>
                                <td class="t_c" width="10%">待开工</td>
                                <td class="t_c" width="10%">正常实施</td>
                                <td class="t_c" width="10%">已完成</td>
                              </tr>
                              <tr class="tit" id="tr2" style="display:none;">
                                <td class="t_c" width="3%">序号</td>
                                <td class="t_c" width="7%">年份</td>
                                <td class="t_c" width="5%">月份</td>
                                <td class="t_c" width="5%">状态</td>
                                <td class="t_c" width="10%">开始任务(计划)</td>
                                <td class="t_c" width="10%">开始任务(实际)</td>
                                <td class="t_c" width="10%">开始任务(延误)</td>
                                <td class="t_c" width="10%">开始任务(延误率(%))</td>
                                <td class="t_c" width="10%">完成任务(计划)</td>
                                <td class="t_c" width="10%">完成任务(实际)</td>
                                <td class="t_c" width="10%">完成任务(延误)</td>
                                <td class="t_c" width="10%">完成任务(延误率(%))</td>
                              </tr>
                              </tbody>
                            </table>

      </div>
        <!--Table End-->
</div>
</div>
</body>
</html>