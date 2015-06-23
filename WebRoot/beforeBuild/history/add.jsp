<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String basePath = request.getContextPath();
String oldDeptId = request.getParameter("oldDeptId");
if(oldDeptId==null){
	oldDeptId = "";
}
String id = request.getParameter("id");
if(id==null){
	id = "";
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>历史数据填报</title>
<link rel="stylesheet" href="<%=basePath %>/css/formalize.css" />
<link rel="stylesheet" href="<%=basePath %>/css/page.css" />
<link rel="stylesheet" href="<%=basePath %>/css/default/imgs.css" />
<link rel="stylesheet" href="<%=basePath %>/css/reset.css" />
<!--[if IE 6.0]>
           <script src="js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
        <script src="<%=basePath %>/js/html5.js"></script>
        <script src="<%=basePath %>/js/jquery-1.7.1.js"></script>
        <script src="<%=basePath %>/js/jquery.formalize.js"></script>
        <script src="<%=basePath %>/beforeBuild/js/jquery.form.js"></script>
        <link type="text/css" href="<%=basePath %>/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="<%=basePath %>/js/flick/jquery-ui-1.8.18.custom.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>/js/flick/jquery.ui.datepicker-zh-CN.js"></script>
        <style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
		</style>		
		
		<script type="text/javascript">
		var projectNameValue = "";
		var projectIdValue = "";
		var routeNameValue = "";
		var routeIdValue = "";
		var typeNameValue = "";
		var typeIdValue = "";
		var monomerNameValue = "";
		var monomerIdValue = "";
		
         $(function(){ 
			$(".odd tr:odd").css("background","#fafafa");	

			$('#realFinishTime').datepicker({
				//inline: true								
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',	
				"currentText":'realFinishTime'//仅作为“清除”按钮的判断条件						
			});
			
			$('#realStartTime').datepicker({
				//inline: true								
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',	
				"currentText":'realStartTime'//仅作为“清除”按钮的判断条件						
			});
	 		
	       //datepicker的“清除”功能
	         $(".ui-datepicker-close").live("click", function (){
	           if($(this).parent("div").children("button:eq(0)").text()=="realFinishTime") $("#realFinishTime").val("");
	           if($(this).parent("div").children("button:eq(0)").text()=="realStartTime") $("#realStartTime").val("");
	         });
			
		});

         
		
		function shut(){
		  window.opener=null;
		  window.open("","_self");
		  window.close();
		}
		
		$(function(){
			load();
		});
		
		function findProjectAndLine(){
			$.ajax({
				url: "/portal/history/findProjectAndLine.action",
				type: 'post', 
				data:{oldDeptId:"<%=oldDeptId%>"},
				dataType: 'json', 
				error:function(){
					alert("系统连接失败，请稍后再试！");
				},
				success: function(obj){			
	              if(obj!=null&&obj.length>0){
					  var addHtml = "<option value=''>---请选择---</option>";
					  for(var i=0;i<obj.length;i++){
						  addHtml += "<option value='"+obj[i][1]+"' projectId='"+obj[i][0]+"' routeId='"+obj[i][2]+"' routeName='"+obj[i][3]+"'>"+obj[i][1]+"</option>";
					  }
					  $("[name=projectName]").html(addHtml);
					  if("<%=id%>"!=""){
							$("[name=projectName]").val(projectNameValue);
							$("[name=projectId]").val(projectIdValue);
							$("[name=routeName]").val(routeNameValue);
							$("[name=routeId]").val(routeIdValue);
						}
	              }
				}
			});	
		}
		
		function selectProject(obj){
			var option = $(obj).children("option:selected");
			$("[name=projectId]").val(option.attr("projectId"));
			$("[name=routeId]").val(option.attr("routeId"));
			$("[name=routeName]").val(option.attr("routeName"));
			$("[name=typeName]").val("");
			$("[name=typeId]").val("");
			$("[name=monomerName]").val("");
			$("[name=monomerId]").val("");
		}

		function findAllAreas(){
			$.ajax({
				url: "/portal/history/findAllAreas.action",
				type: 'post', 
				dataType: 'json', 
				error:function(){
					alert("系统连接失败，请稍后再试！");
				},
				success: function(obj){			
	              if(obj!=null&&obj.length>0){
					  var addHtml = "<option value=''>---请选择---</option>";
					  for(var i=0;i<obj.length;i++){
						  addHtml += "<option value='"+obj[i][1]+"' areaId='"+obj[i][0]+"'>"+obj[i][1]+"</option>";
					  }
					  $("[name=areaName]").html(addHtml);
	              }
				}
			});	
		}

		function selectArea(obj){
			var option = $(obj).children("option:selected");
			$("[name=areaId]").val(option.attr("areaId"));
		}

		function findAllTypes(){
			$.ajax({
				url: "/portal/history/findAllTypes.action",
				type: 'post', 
				dataType: 'json', 
				error:function(){
					alert("系统连接失败，请稍后再试！");
				},
				success: function(obj){			
	              if(obj!=null&&obj.length>0){
					  var addHtml = "<option value=''>---请选择---</option>";
					  for(var i=0;i<obj.length;i++){
						  addHtml += "<option value='"+obj[i][1]+"' typeId='"+obj[i][0]+"'>"+obj[i][1]+"</option>";
					  }
					  $("[name=typeName]").html(addHtml);
					  if("<%=id%>"!=""){
							$("[name=typeName]").val(typeNameValue);
							$("[name=typeId]").val(typeIdValue);
							findMonomerByRouteAndType();
						}
	              }
				}
			});	
		}

		function selectType(obj){
			var option = $(obj).children("option:selected");
			$("[name=typeId]").val(option.attr("typeId"));
			findMonomerByRouteAndType();
		}

		function findMonomerByRouteAndType(){
			$.ajax({
				url: "/portal/history/findMonomerByRouteAndType.action",
				type: 'post', 
				data:{
					routeId:$("[name=routeId]").val(),
					typeName:$("[name=typeName]").val(),
					projectId:$("[name=projectId]").val(),
					typeId:$("[name=typeId]").val(),
					random:Math.random()
				},
				dataType: 'json', 
				error:function(){
					alert("系统连接失败，请稍后再试！");
				},
				success: function(obj){			
				   var addHtml = "<option value=''>---请选择---</option>";
		           if(obj!=null&&obj.length>0){
					  for(var i=0;i<obj.length;i++){
						  addHtml += "<option value='"+obj[i][1]+"' monomerId='"+obj[i][0]+"'>"+obj[i][1]+"</option>";
					  }
	               }
		           $("[name=monomerName]").html(addHtml);
		           if("<%=id%>"!=""){
						$("[name=monomerName]").val(monomerNameValue);
						$("[name=monomerId]").val(monomerIdValue);
					}
				}
			});	
		}

		function selectMonomer(obj){
			var option = $(obj).children("option:selected");
			$("[name=monomerId]").val(option.attr("monomerId"));
		}

		function save(){
			if(check()){
				var formOptions = {
					cache:false,
					type:'post',
					callback:null,
					dataType :'json',
					url:"/portal/history/save.action",
				    success:function(data){
						if(data!=null&&data.if_success=="yes"){
							alert("提交成功！");
							if("<%=id%>"!=""){
								window.close();
							}else{
								$("[name=paper]").val("");
								$("[name=realFinishTime]").val("");
								$("[name=realStartTime]").val("");
								$("[name=result]").val("");
								$("#attachFrame").attr("src",$("#attachFrame").attr("src"));
							}
						}else if(data!=null&&data.if_success=="no"){
							alert("该证件已添加，请勿重复添加！");
						}else{
							alert("提交失败，请联系管理员！");
						}
						return false;
				    }
				};
				
				$("#form").ajaxSubmit(formOptions);  
			}
		}

		function check(){
			if($("[name=projectName]").val()==""){
				alert("请选择项目！");
				$("[name=projectName]").focus();
				return false;
			}
			if($("[name=monomerName]").val()==null||$("[name=monomerName]").val()==""){
				alert("请选择类型和单体名称！");
				$("[name=monomerName]").focus();
				return false;
			}
			if($("[name=paper]").val()==""){
				alert("请选择证件！");
				$("[name=paper]").focus();
				return false;
			}
			if($("[name=realStartTime]").val()!=""&&$("[name=realFinishTime]").val()!=""&&$("[name=realStartTime]").val()>=$("[name=realFinishTime]").val()){
				alert("实际结束时间必须大于实际开始时间！");
				return false;
			}
			
			/* if($("[name=paperId]").val().length<=0||$("[name=paperId]").val()==""){
				alert("sdfasdf");
				return false;
			} *///else{//为paperId赋值
				alert($("[name=paper]").val());
				if($("[name=paper]").val()=="规划选址"){
					$("[name=paperId]").val("1");
				}
				if($("[name=paper]").val()=="规划方案"){
					$("[name=paperId]").val("2");
				}
				if($("[name=paper]").val()=="规划用地"){
					$("[name=paperId]").val("3");
				}
				if($("[name=paper]").val()=="勘测报告"){
					$("[name=paperId]").val("4");
				}
				if($("[name=paper]").val()=="用地征询单"){
					$("[name=paperId]").val("5");
				}
				if($("[name=paper]").val()=="用地批文"){
					$("[name=paperId]").val("6");
				}
				if($("[name=paper]").val()=="拆迁许可"){
					$("[name=paperId]").val("34");
				}
				if($("[name=paper]").val()=="划拨决定书"){
					$("[name=paperId]").val("8");
				}
				if($("[name=paper]").val()=="用地批准书"){
					$("[name=paperId]").val("9");
				}
				if($("[name=paper]").val()=="卫生预评价"){
					$("[name=paperId]").val("10");
				}
				if($("[name=paper]").val()=="卫生初设"){
					$("[name=paperId]").val("11");
				}
				if($("[name=paper]").val()=="卫生施工图"){
					$("[name=paperId]").val("12");
				}
				if($("[name=paper]").val()=="消防初设"){
					$("[name=paperId]").val("13");
				}
				if($("[name=paper]").val()=="消防施工图"){
					$("[name=paperId]").val("14");
				}
				if($("[name=paper]").val()=="人防初设"){
					$("[name=paperId]").val("15");
				}
				if($("[name=paper]").val()=="人防施工图"){
					$("[name=paperId]").val("16");
				}
				if($("[name=paper]").val()=="交警审核"){//??
						alert("警");
					$("[name=paperId]").val("35");
				}
				if($("[name=paper]").val()=="绿化搬迁"){
					$("[name=paperId]").val("18");
				}
				if($("[name=paper]").val()=="规划许可"){
					$("[name=paperId]").val("19");
				}
				if($("[name=paper]").val()=="施工许可"){
					$("[name=paperId]").val("20");
				}
				if($("[name=paperId]").val().length<=0||$("[name=paperId]").val()==""){
					alert("sdfasdf");
					return false;
				}
				alert("yes");
				return true;
			//}
			
		}

		function load(){
			var id = "<%=id%>";
			if(id!=""){
				$("#addButton").val("保  存");
				$("#titleName").html($("#titleName").html().replace("新增","修改"));
				$.ajax({
					url: "/portal/history/load.action",
					type: 'post', 
					data:{
						id:id,
						random:Math.random()
					},
					dataType: 'json', 
					error:function(){
						alert("系统连接失败，请稍后再试！");
					},
					success: function(obj){			
			           if(obj!=null){
			       			projectNameValue = obj.projectName;
			       			projectIdValue = obj.projectId;
			       			routeNameValue = obj.routeName;
			       			routeIdValue = obj.routeId;
			       			typeNameValue = obj.typeName;
			       			typeIdValue = obj.typeId;
			       			monomerNameValue = obj.monomerName;
			       			monomerIdValue = obj.monomerId;
			       			$("[name=paper]").val(obj.paper);
			       			$("[name=realFinishTime]").val(obj.realFinishTime);
			       			$("[name=realStartTime]").val(obj.realStartTime);
			       			$("[name=result]").val(obj.result);
			       			$("[name=createTime]").val(obj.createTime);
			       			$("[name=createUser]").val(obj.createUser);
			       			$("#attachFrame").attr("src",$("#attachFrame").attr("src").replace("fileGroupId=","fileGroupId="+$("[name=result]").val()));
			       			
			        	    findProjectAndLine();
				   		    //findAllAreas();
				   		    findAllTypes();
		               }
					}
				});	
			}else{
				findProjectAndLine();
		   		//findAllAreas();
		   		findAllTypes();
			}
		}
        </script>
       </head>
<body>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img id="show" onclick="showHide();" src="<%=basePath %>/css/default/images/sideBar_arrow_right.jpg" width="46" height="30" title="收起"></div>
            <div class="posi fl">
            	<ul>
            		<li class="fin" id="titleName">历史数据新增</li>
                </ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <!--Filter--><!--Filter End-->
        <!--Table-->
        <div class="mb10 pt45">
        <form action="<%=basePath %>/history/save.action" id="form" name="form" method="post">
        <input type="hidden" name="id" value="<%=id %>">
        <input type="hidden" name="paperId">
        <input type="hidden" name="createTime">
        <input type="hidden" name="createUser">
        <input type="hidden" name="removed" value="0">
        <input type="hidden" name="status" value="4">
        <input type="hidden" name="source" value="1">
          <table width="100%"  class="table_1">
           <thead>
            <th colspan="4" class="t_r">
       	      &nbsp;</th>
                                  </thead>
                              <tbody>
                               <tr>
                                <td style="width:20%;" class="t_r lableTd">项目名称：</td>
                                <td style="width:30%;">
                                <select name="projectName" onchange="selectProject(this);"></select>
                                <input type="hidden" name="projectId">
                                </td>
                                <td style="width:20%;" class="t_r lableTd">所属线路：</td>
                                <td style="width:30%;">
                                <input type="text" name="routeName" class="input_large" readonly="readonly"/>
                                <input type="hidden" name="routeId">
                                </td>
                                </tr>
                                
                                <tr>
                                <td class="t_r lableTd">单体：</td>
                                <td>
                                	<!-- 行政区划：<select name="areaName" onchange="selectArea(this);"></select>&nbsp;&nbsp;<input type="text" name="areaId"> -->
                                	单体类型：<select name="typeName" onchange="selectType(this);"></select>&nbsp;&nbsp;
                                	单体名称：<select name="monomerName" onchange="selectMonomer(this);"></select>
                                	<input type="hidden" name="typeId">
                                	<input type="hidden" name="monomerId">
                                </td>
                                <td class="t_r lableTd">证件：</td>
                                <td>
                                	<select name="paper">
                                		<option value="">---请选择---</option>
                                		<option value="规划选址">规划选址</option>
                                		<option value="规划方案">规划方案</option>
                                		<option value="规划用地">规划用地</option>
                                		<option value="勘测报告">勘测报告</option>
                                		<option value="用地征询单">用地征询单</option>
                                		<option value="用地批文">用地批文</option>
                                		<option value="拆迁许可">拆迁许可</option>
                                		<option value="划拨决定书">划拨决定书</option>
                                		<option value="用地批准书">用地批准书</option>
                                		<option value="卫生预评价">卫生预评价</option>
                                		<option value="卫生初设">卫生初设</option>
                                		<option value="卫生施工图">卫生施工图</option>
                                		<option value="消防初设">消防初设</option>
                                		<option value="消防施工图">消防施工图</option>
                                		<option value="人防初设">人防初设</option>
                                		<option value="人防施工图">人防施工图</option>
                                		<option value="交警审核">交警审核</option>
                                		<option value="绿化搬迁">绿化搬迁</option>
                                		<option value="规划许可">规划许可</option>
                                		<option value="施工许可">施工许可</option>
                                	</select>
                                </td>                                
                                <tr>
                                <td class="t_r lableTd">实际开始时间：</td>
                                <td>
                                	<input type="text" name="realStartTime" id="realStartTime" readonly="readonly" class="input_large">
                                </td>
                                <td class="t_r lableTd">实际完成时间：</td>
                                <td>
                                	<input type="text" name="realFinishTime" id="realFinishTime" readonly="readonly" class="input_large">
                                </td>
                                </tr>
                                <tr>
	                                <td class="t_r lableTd">成果：</td>
	                                <td colspan="3">
		                                <input type="hidden" name="result" id="attach" value=""/>
		        						<iframe scrolling="auto" height="150" frameborder="1" border="1" marginheight="0" marginwidth="0" width="100%" id="attachFrame" name="attachFrame" src="/portal/attach/loadFileList.action?fileGroup=attach&fileGroupName=attachGroup&fileGroupId=&userName=<s:property value="#session.userName"/>&loginName=<s:property value="#session.loginName"/>&procType=edit&targetType=frame"></iframe>
	                                </td>
                                </tr>
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="4" class="t_r"><input id="addButton" type="button" value="添  加" onclick="save();"/>&nbsp;
                                <input type="button" value="关 闭" onclick="shut();"/></td>
                              </tr>
                             
                              
                            </table>
             </form>
      </div>
        <!--Table End-->
</div>
</body>
</html>
