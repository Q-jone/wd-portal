<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>历史计划信息
</title>
<link rel="stylesheet" href="<%=basePath %>css/formalize.css" />
<link rel="stylesheet" href="<%=basePath %>css/page.css" />
<link rel="stylesheet" href="<%=basePath %>css/default/imgs.css" />
<link rel="stylesheet" href="<%=basePath %>css/reset.css" />
<!--[if IE 6.0]>
           <script src="js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
        <script src="<%=basePath %>js/html5.js"></script>
        <script src="<%=basePath %>js/jquery-1.7.1.js"></script>
        <script src="<%=basePath %>project/project/js/jquery.form.js"></script>
		<script src="<%=basePath %>js/jquery.formalize.js"></script>
		<!--<script src="../js/switchDept.js"></script>-->
		<script src="<%=basePath %>js/show.js"></script>
		<script src="<%=basePath %>js/loading.js"></script>
		<link type="text/css" href="<%=basePath %>css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="<%=basePath %>js/flick/jquery-ui-1.8.18.custom.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>js/flick/jquery.ui.datepicker-zh-CN.js"></script>
        <style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
		</style>
		
		<script type="text/javascript">
		
		var count=0;
         $(function(){
        	 
      		 
        	 count=$("#mytable tr").length;
        	 if(count<=1){
        		 $("input[id=save]").hide();
        		 $("input[id=back]").hide();
        	 }
        	 //将保存按钮动态显示        	 
        	 function sh(count){
        		 if(count>=1){
        			 $("input[id=save]").show();
            		 $("input[id=back]").show();
        		 }
        	 }
        	
				$("select[id^=planName]").click(function(){//subPlanName
	               	 //alert("aaa");
	               	 var id=$(this).attr("id");
	               	cascade(id);
                });
				
				
			});
         function shut(){
   		  window.opener=null;
   		  window.open("","_self");
   		  window.close();
   		}
		</script>
       </head>

<body>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img id="show" onclick="showHide();" src="<%=basePath %>css/default/images/sideBar_arrow_right.jpg" width="46" height="30" title="收起"></div>
            <div class="posi fl">
            	<ul>
            		<li class="fin">历史计划xinx</li>
				</ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <!--Filter--><!--Filter End-->
        <!--Table-->
        
        <div class="mb10 pt45">
        <div style="background-color: ;">
		                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<h5 class="fl"><a href="#" class="colSelect fl">历史计划信息</a></h5>
		              <!--  &nbsp;<input type="button" name="addline" id="addline"  value="新 增" class="fr">
		          <input type="button" name="excelButton" id="excelButton" value="导 出当页至 EXCEL" class="fl">
		              &nbsp;<input type="button" name="excelAllButton" id="excelAllButton" value="导 出全部至 EXCEL" class="fl">
		            --> </div>
		            <s:form action="saves" id="add" method="post" namespace="/projectPlan">
        <!-- <form id="add" method="POST" action="projectPlan/saves.action" > -->
        <input  type="hidden" id="projectId" name="projectId" value="<s:property value='project.projectId'/>"/>
        <table width="100%"  class="table_1" id="mytable">
        	<thead>
        		<tr>
	        		<td style="width:3%;" class="t_c">序号</td>
	        		<td style="width:8%;" class="t_c">阶段</td>
	        		<td style="width:8%;" class="t_c">计划</td>
	        		<td style="width:10%;" class="t_c">计划开始时间</td>
	        		<td style="width:10%;" class="t_c">计划结束时间</td>
	        		<td style="width:10%;" class="t_c">实际开始时间</td>
	        		<td style="width:10%;" class="t_c">实际结束时间</td>
	        		<td style="width:21%;" colspan="2" class="t_c">说明</td>
	        		<td style="width:10%;" class="t_c">修改时间</td>
	        	</tr>
        	</thead>
        	<tbody>
        	<s:set name="plan" value="projectPlanHistorys"></s:set>
        		<s:iterator value="plan" id="pp" status="p">
        			<tr id="dataTr">
        				<td class="t_c"><s:property value='#p.index+1'/></td>
        				<td class="t_c">
        				<input type="hidden" id="plan<s:property value='#p.index'/>" value="<s:property value='planName'/>" />
        					<s:property value="planName" />
        				<td class="t_c">
        				<input type="hidden" id="sub<s:property value='#p.index'/>" value="<s:property value='subPlanName'/>" />
        					<s:property value="subPlanName"/>
        				</td>
        				<td class="t_c">
        					<s:date name='planBeginDate' format='yyyy-MM-dd'/></td>
        				<td class="t_c">
        					<s:date name='planEndDate' format='yyyy-MM-dd'/></td>
        				<td class="t_c">
        					<s:date name='realBeginDate' format='yyyy-MM-dd'/></td>
        				<td class="t_c">
        					<s:date name='realEndDate' format='yyyy-MM-dd'/></td>
        				<td class="t_c" colspan="2"><s:property value='memo'/></td>
        				<td class="t_c">
        					<s:date name='updateTime' format='yyyy-MM-dd'/>
        				</td>
        			</tr>
        		</s:iterator>
        	</tbody>
        			<input type="submit" id="save" name="save" onclick="shut();" value="关闭" />
        	</table>
       </s:form>
      </div>
        <!--Table End-->
</div>
</body>
</html>
