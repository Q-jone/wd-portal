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
<title>年度推进计划目标保存
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
		<script type="text/javascript" src="<%=basePath %>project/sysinfo/js/forwardGoal.js"></script>
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
        	//新增一行表单便于添加记录
        	
        	 $("#addline").click(function(){
        		 count=$("#mytable tr").length;//计算当前表格的总行数
        		 count=count;
        		 var trs="<tr id='dataTr'><td class='t_c'>"+count+"</td>"
        		 +"<td><input type='text' id='forwardGoals"+(count-1)+"' name='forwardGoals["+(count-1)+"].forwardGoal' style='width:100%;' /></td>"
        		 +"<td><select id='year<s:property value='#p.index'/>' name='year[<s:property value='#p.index'/>]''><option value='0'>--请选择年度--</option>"
        		 +"<option value='2013'>--2013年度--</option><option value='2014'>--2014年度--</option><option value='2015'>--2015年度--</option>"
				 +"<option value='2016'>--2016年度--</option></select></td>";
        	 	 $("tbody").append(trs);
        	 	 sh(count);
        	 });
        	 
				
				$("a[id^=select]").click(function(){
					var id=$(this).attr("id");
					
					//alert(id.substr(7));
					var removed="select"+id.substr(6);
					$("#"+removed).val(id.substr(6));
					alert($("#"+removed).val());
				});
				
				var beginDate="";
				var ids="";
				var sid="";
				$("input[id^=beginDate]").each(function(i,n){
					ids=$(this).attr("id");//遍历每一个存放时间的控件得到控件id值
					sid="year"+ids.substr(9);
					for(var i=1;i<$("#"+sid).children("option").length;i++){
						if($("#"+sid).children("option:eq("+i+")").val()==$(this).val().substr(0,4))
							$("#"+sid).children("option:eq("+i+")").attr("selected",true);
					}
					beginDate+=$(this).val();
				});
				
			});
         function backs(){
        	history.go(-1);
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
            		<li class="fin">年度推进计划目标保存</li>
				</ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <!--Filter--><!--Filter End-->
        <!--Table-->
       
        <div class="mb10 pt45">
        
        <div style="background-color: ;">
		                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<h5 class="fl"><a href="#" class="colSelect fl">年度推进计划目标保存</a></h5>
		             &nbsp;<input type="button" name="addline" id="addline"  value="新 增" class="fr">
		           <!--  <input type="button" name="excelButton" id="excelButton" value="导 出当页至 EXCEL" class="fl">
		              &nbsp;<input type="button" name="excelAllButton" id="excelAllButton" value="导 出全部至 EXCEL" class="fl">
		            --> </div>
		            <s:form action="saveForwards.action" id="add" method="post" namespace="/projectForwardGoal/sysinfo">
        <!-- <form id="add" method="POST" action="projectPlan/saves.action" > -->
        <input  type="hidden" id="projectId" name="projectId" value="<s:property value='project.projectId'/>"/>
        <input type="hidden" id="select" name="select" value="" />
        <table width="100%"  class="table_1" id="mytable">
        	<thead>
        		<tr>
	        		<td style="width:3%;" class="t_c">序号</td>
	        		<td style="width:82%;" class="t_c">年度推进计划目标</td>
	        		<td style="width:15%;" class="t_c">选择年度</td>
	        	</tr>
        	</thead>
        	<tbody>
        	<s:set name="plan" value="forwardGoals"></s:set>
        		<s:iterator value="plan" id="pp" status="p">
        			<tr id="dataTr" class="td<s:property value='#p.index'/>">
        		<!-- 记录自己的主键 -->
        				<td class="t_c"><s:property value='#p.index+1'/></td>
        				<td class="t_c">
        				<input type="hidden" id="forwardGoalId<s:property value='#p.index'/>" name="forwardGoals[<s:property value='#p.index'/>].forwardGoalId" value="<s:property value='forwardGoalId'/>"/>
        				<input type="text" id="forwardGoal<s:property value='#p.index'/>" name="forwardGoals[<s:property value='#p.index'/>].forwardGoal" value="<s:property value='forwardGoal'/>" style="width:100%;">
        				</td>
        				<td>
        				<input type="hidden" id="beginDate<s:property value='#p.index'/>" name="beginDate<s:property value='#p.index'/>" value="<s:date name="beginDate" format="yyyy-MM-dd"/>"/>
        					<select id="year<s:property value='#p.index'/>" name="year[<s:property value='#p.index'/>]">
        						<option value="0">--请选择年度--</option>
        						<option value="2013">--2013年度--</option>
        						<option value="2014">--2014年度--</option>
        						<option value="2015">--2015年度--</option>
        						<option value="2016">--2016年度--</option>
        					</select>
        				</td>
        			</tr>
        		</s:iterator>
        	</tbody>
        			<input type="submit" id="save" name="save" onclick="return checkout();" value="保存" />
        			<input type="button" id="back" name="back" onclick="backs();" value="后退" />
        		
        	</table>
       </s:form>
      </div>
        <!--Table End-->
</div>
</body>
</html>
