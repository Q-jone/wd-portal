<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>工程建设进度计划对比表</title>
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
		<link type="text/css" href="../css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="../js/flick/jquery-ui-1.8.18.custom.min.js"></script>
		<script type="text/javascript" src="../js/flick/jquery.ui.datepicker-zh-CN.js"></script>
        <style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
		</style>		
	<script type="text/javascript">
	function getAllProjects(){
		$.ajax({
			type : 'post',
			url : 'build/getListBySql.action?random='+Math.random(),
			dataType:'json',
			data:{
				sql:"select project_id,project_name from greata_project order by project_name"
			},
			cache : false,
			error:function(){
				alert("系统连接失败，请稍后再试！")
			},
			success:function(object){
				var addHtml = "<option value=''>---请选择---</option>";
				for(var i=0;i<object.length;i++){
					addHtml += "<option value='"+object[i][0]+"'>"+object[i][1]+"</option>";
				}
				$("#projectId").html(addHtml);
				$("#projectId").val($("#hiddenProjectId").val());
			}
		});
	}

	function addNbsp(lev){
		var addHtml = "";
		var nbsp = "&nbsp;&nbsp;&nbsp;&nbsp;";
		for(var i=1;i<lev;i++){
			addHtml += nbsp;
		}
		return addHtml;
	}

	$(function(){
		getAllProjects();
		$("#dateType").val($("#hiddenDateType").val());

		$("[id=dataTr]").each(function(i){
			$(this).children("td:eq(0)").html(addNbsp($(this).children("td:eq(0)").attr("lev"))+$(this).children("td:eq(0)").html());
			//if($(this).children("td:eq(0)").attr("lev")=='2'){
			//	$(this).attr("style","background-color:gray;");
			//}
			if(i!=$("[id=dataTr]").length-1&&$(this).children("td:eq(0)").attr("lev")<$(this).next().children("td:eq(0)").attr("lev")){
				$(this).children("td:eq(0)").attr("style","border:#d0d0d3 1px solid;font-weight:bold;");
			}
			if(parseInt($(this).children("td:eq(1)").html())>0){
				$(this).children("td:eq(1)").attr("style","border:#d0d0d3 1px solid;color:red;");
			}
			if(parseFloat($(this).children("td:eq(5)").html().replace('%',''))<0){
				$(this).children("td:eq(5)").attr("style","border:#d0d0d3 1px solid;color:red;");
			}
			if(parseFloat($(this).children("td:eq(9)").html().replace('%',''))<0||parseFloat($(this).children("td:eq(9)").html().replace('%',''))>parseFloat($(this).children("td:eq(8)").html().replace('%',''))){
				$(this).children("td:eq(9)").attr("style","border:#d0d0d3 1px solid;color:red;");
			}
		});
	})
    </script>



</head>

<body>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
			<div class="fl"><img id="show" onclick="showHide();" src="../css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li class="fin">工程建设进度计划对比表</li>
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
		<div class="fn">
	        <s:form action="findProcessPlanComparision" id="form" name="build" namespace="/build">        	
	            <table width="100%">
		            <tr>
			            <td>
			            	项目：<select id="projectId" name="projectId"></select>
			            	<select id="dateType" name="dateType">
			            		<option value="all">全部</option>
			            		<option value="year">本年</option>
			            		<option value="month">本月</option>
			            	</select>
			            	<input type="hidden" id="hiddenProjectId" value="<s:property value='#request.projectId'/>">
			            	<input type="hidden" id="hiddenDateType" value="<s:property value='#request.dateType'/>">
			              <input type="submit" value="统 计" class="new" >
			            </td>
		            </tr>
	            </table>
            </s:form>
        </div>
      
        <!--Filter End-->
        <!--Table-->
         <s:set name="r" value="#request.list"></s:set>  
        <div class="mb10">
        	<table width="100%"  class="table_1"  id="mytable">
                              <thead>
                              <tr class="tit">
                                <td class="t_c" width="30%">任务名称</td>
                                <td class="t_c" width="15%">开始延误(天)</td>
                                <td class="t_c" width="15%">实际完成日期</td>
                                <td class="t_c" width="15%">计划工期(天)</td>
                                <td class="t_c" width="15%">剩余工期(天)</td>
                                <td class="t_c" width="10%">工期比</td>
                                <td class="t_c" width="5%" style="display:none;">计划量</td>
                                <td class="t_c" width="10%" style="display:none;">累计完成量</td>
                                <td class="t_c" width="5%" style="display:none;">进度比</td>
                                <td class="t_c" width="10%" style="display:none;">进度工期对比</td>
                                </tr>
                                </thead>
                               <tbody> 
                              <s:iterator value="#r" id="items" status="s">
                              <tr id="dataTr">
                              	<td class="t_l" style="border:#d0d0d3 1px solid;" lev="<s:property value="#items[10]"/>"><s:property value="#items[0]"/></td>
                               	<td class="t_r" style="border:#d0d0d3 1px solid;"><s:property value="#items[1]" /></td>
                               	<td class="t_c" style="border:#d0d0d3 1px solid;"><s:property value="#items[2]" /></td>
                               	<td class="t_r" style="border:#d0d0d3 1px solid;"><s:property value="#items[3]" /></td>
                               	<td class="t_r" style="border:#d0d0d3 1px solid;"><s:property value="#items[4]" /></td>
                               	<td class="t_r" style="border:#d0d0d3 1px solid;"><s:property value="#items[5]" /></td>
                               	<td class="t_r" style="border:#d0d0d3 1px solid;display:none;"><s:property value="#items[6]" /></td>
                               	<td class="t_r" style="border:#d0d0d3 1px solid;display:none;"><s:property value="#items[7]" /></td>
                               	<td class="t_r" style="border:#d0d0d3 1px solid;display:none;"><s:property value="#items[8]" /></td>
                               	<td class="t_r" style="border:#d0d0d3 1px solid;display:none;"><s:property value="#items[9]" /></td>
                                </tr>
                                </s:iterator>
                              </tbody>
                            </table>

      </div>
      
      
</div>
</div>
</body>
</html>