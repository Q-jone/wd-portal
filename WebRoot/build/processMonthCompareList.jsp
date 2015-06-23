<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>工程建设月度进度对比表</title>
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
		<script src="js/merge.js"></script>
		<script src="js/select.js"></script>
		<link type="text/css" href="../css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="../js/flick/jquery-ui-1.8.18.custom.min.js"></script>
		<script type="text/javascript" src="../js/flick/jquery.ui.datepicker-zh-CN.js"></script>
        <style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
		</style>		
	<script type="text/javascript">

	$(function(){
		countSum();

		$("#mytable").rowspan({td:2});
		$("#mytable").rowspan({td:1});

		getDistinctValue("stat_year","dw_build_plantask_month");
		getDistinctValue("project_name","dw_build_plantask_month");
		getDistinctValue("type_name","dw_build_plantask_month");

		$("[name=stat_month]").val($("#stat_month").val());
	});


	function clearInput(){
		$("[name=stat_month]").val("");
		$("[name=type_name]").val("");
		$("[name=project_name]").val("");
	}

	function countSum(){
		var plan_done = "";
		var real_done = "";
		var plan_done_all = "";
		var real_done_all = "";
		$("[id=dataTr]").each(function(i){
			var addHtml = "";
			if(plan_done!=""&&$.trim($(this).children("td:eq(3)").html())!=""){
				plan_done += ",";
			}
			if(plan_done_all!=""&&$.trim($(this).children("td:eq(3)").html())!=""){
				plan_done_all += ",";
			}
			
			plan_done += $(this).children("td:eq(3)").html();
			plan_done_all += $(this).children("td:eq(3)").html();
			if(real_done!=""&&$.trim($(this).children("td:eq(4)").html())!=""){
				real_done += ",";
			}
			if(real_done_all!=""&&$.trim($(this).children("td:eq(4)").html())!=""){
				real_done_all += ",";
			}
			real_done += $(this).children("td:eq(4)").html();
			real_done_all += $(this).children("td:eq(4)").html();
			if(i==($("[id=dataTr]").length-1)||($(this).children("td:eq(0)").html()!=$(this).next().children("td:eq(0)").html()||$(this).children("td:eq(1)").html()!=$(this).next().children("td:eq(1)").html())){
				var plan_done_sum = 0;
				var real_done_sum = 0;
				if(plan_done!=""){
					var plan_done_split = plan_done.split(",");
					plan_done_sum = plan_done_split.length;
				}
				if(real_done!=""){
					var real_done_split = real_done.split(",");
					real_done_sum = real_done_split.length;
				}
				addHtml += "<tr id='countSum' style='background-color:#FFF8DC;'><td>merge</td><td>merge</td><td class='t_c'>小计</td><td class='t_c'>"+plan_done_sum+"</td><td class='t_c'>"+real_done_sum+"</td></tr>";
				plan_done = "";
				real_done = "";
			}
			if(i==($("[id=dataTr]").length-1)||$(this).children("td:eq(0)").html()!=$(this).next().children("td:eq(0)").html()){
				var plan_done_sum = 0;
				var real_done_sum = 0;
				if(plan_done_all!=""){
					var plan_done_split = plan_done_all.split(",");
					plan_done_sum = plan_done_split.length;
				}
				if(real_done_all!=""){
					var real_done_split = real_done_all.split(",");
					real_done_sum = real_done_split.length;
				}
				addHtml += "<tr id='countAllSum' style='background-color:#FFE7BA;'><td>merge</td><td class='t_c'>合计</td><td class='t_c'></td><td class='t_c'>"+plan_done_sum+"</td><td class='t_c'>"+real_done_sum+"</td></tr>";
				plan_done_all = "";
				real_done_all = "";
			}
			$(this).after(addHtml);
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
                	<li class="fin">工程建设月度进度对比表</li>
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
        	<div class="p8 filter_search">
        	<s:form action="getProcessMonthCompareList" id="form" method="post"  namespace="/build">
        		<input type="hidden" id="stat_year" value="<s:property value='#request.year'/>">
        		<input type="hidden" id="stat_month" value="<s:property value='#request.stat_month'/>">
        		<input type="hidden" id="project_name" value="<s:property value='#request.project_name'/>">
        		<input type="hidden" id="type_name" value="<s:property value='#request.type_name'/>">
        	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
        	    <tr>
        	     <td class="t_r">年度</td>
        	      <td>
        	      <select name="stat_year" class="input_large">
        	      	
        	      </select>
        	     </td>
        	     <td class="t_r">月度</td>
        	      <td>
        	      <select name="stat_month" class="input_large">
        	      	<option value="">---请选择---</option>
        	      	<option value="1">1</option>
        	      	<option value="2">2</option>
        	      	<option value="3">3</option>
        	      	<option value="4">4</option>
        	      	<option value="5">5</option>
        	      	<option value="6">6</option>
        	      	<option value="7">7</option>
        	      	<option value="8">8</option>
        	      	<option value="9">9</option>
        	      	<option value="10">10</option>
        	      	<option value="11">11</option>
        	      	<option value="12">12</option>
        	      </select>
        	     </td>
        	     
        	      <td class="t_r">类型</td>
        	      <td>
        	      <select name="type_name" class="input_large">
        	      	
        	      </select>
        	     </td>
      	      </tr> 
      	      <tr>
      	       <td class="t_r">项目</td>
        	      <td>
        	      <select name="project_name" class="input_large">
        	      	
        	      </select>
        	     </td>
      	      </tr>
      	       <tr>
        	      <td colspan="6" class="t_c">
                  	<input type="submit" value="检 索" />&nbsp;&nbsp;
                  	<input type="button" value="重 置" onclick="clearInput();"/></td>
				</tr>
      	    </table>
      	    </s:form>
      	    </div>
        	</div>   
      
        <!--Filter End-->
        <!--Table-->
         <s:set name="r" value="#request.list"></s:set>  
        <div class="mb10">
        	<table width="100%"  class="table_1"  id="mytable">
                              <thead>
                              <th colspan="30" style="font-size:15px;">上海轨道交通<s:property value="#request.year"/>年度各类型月度进度对比表</th>
                              
                                </thead>
                               <tbody> 
                               <tr class="tit">
                                <td class="t_c" width="10%">月度</td>
                                <td class="t_c" width="15%">类型</td>
                                <td class="t_c" width="25%">项目</td>
                                <td class="t_c" width="25%">计划完成任务</td>
                                <td class="t_c" width="25%">实际完成任务</td>
                                </tr>
                              <s:iterator value="#r" id="items" status="s">
                              <tr id="dataTr">
                               	<td class="t_c"><s:property value="#items[10]" /></td>
                               	<td class="t_c"><s:property value="#items[4]" /></td>
                               	<td class="t_c"><s:property value="#items[2]" /></td>
                               	<td class="t_c" style="background-color:#7AC5CD;"><s:property value="#items[6]" /></td>
                               	<td class="t_c" style="background-color:#9BCD9B;"><s:property value="#items[8]" /></td>
                                </tr>
                                </s:iterator>
                              </tbody>
                            </table>

      </div>
      
      
</div>
</div>
</body>
</html>