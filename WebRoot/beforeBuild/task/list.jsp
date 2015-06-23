<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String basePath = request.getContextPath();
String oldDeptId = request.getParameter("oldDeptId");
if(oldDeptId==null){
	oldDeptId = "";
}
%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title id="titleZone">任务管理</title>
<link rel="stylesheet" href="<%=basePath %>/css/formalize.css" />
<link rel="stylesheet" href="<%=basePath %>/css/page.css" />
<link rel="stylesheet" href="<%=basePath %>/css/default/imgs.css" />
<link rel="stylesheet" href="<%=basePath %>/css/reset.css" />
<!--[if IE 6.0]>
           <script src="../js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
        <script src="<%=basePath %>/js/html5.js"></script>
        <script src="<%=basePath %>/js/jquery-1.7.1.js"></script>
		<script src="<%=basePath %>/js/jquery.formalize.js"></script>
		<script src="<%=basePath %>/beforeBuild/js/comm.js"></script>
		<link type="text/css" href="<%=basePath %>/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="<%=basePath %>/js/flick/jquery-ui-1.8.18.custom.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>/js/flick/jquery.ui.datepicker-zh-CN.js"></script>
		<style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
		</style>		

	<script type="text/javascript">
		
	 	$(function(){
	 		$("#addButton").click(function(){
	 			window.open("/portal/task/toAdd.action?oldDeptId=<%=oldDeptId%>&monomerPlanId="+$("#id").val());
	 			return false;
	 		});

	 		$("[name=status]").val($("#h_status").val());

	 		$("[id=statusTd]").each(function(){
				$(this).html($(this).text().replace("1","待办").replace("2","在办").replace("3","延误").replace("4","办结").replace("5","失效").replace("6","过期"));
		 	});

	 		findAllPapers();

	 		orderFunc();

		 	if($("[name=type]").val()=="do"){
				$("[part=hiddenPart]").show();
				$("[part=showPart]").hide();
				$("#addButton").hide();
				$("#addTaskButton").hide();
				$("#task_project").hide();
				$("#task2").addClass("selected");
				$("#task1").removeClass("selected");
				$("#titleName").html($("#titleName").html().replace("发布","实施"));
				$("#listName").html($("#listName").html().replace("发布","实施"));
		 	}else{
		 		findProjectAndLine();
		 	}
		});
		
		function toEdit(id,type,monomerPlanName,planStartTime,planFinishTime){
			window.open("/portal/task/toEdit.action?id="+id+"&oldDeptId=<%=oldDeptId%>&type="+type+"&monomerPlanName="+encodeURI(monomerPlanName)+"&planStartTime="+planStartTime+"&planFinishTime="+planFinishTime);
	 	}


	 	function clearInput(){
			$("[name=monomerName]").val("");
			$("[name=paperName]").val("");
			$("[name=status]").val("");
			$("[name=projectName]").val("");
	 	}

	 	function startTask(){
			if($("[name=checkbox]:checked").length==0){
				alert("请勾选任务！");
			}else if($("[name=checkbox]:checked").length>1){
				alert("一次只能选择一个任务！");
			}else{
				var status = $.trim($("[name=checkbox]:checked").parents("tr").find($("[id=statusTd]")).html());
				if(status==""||status=="待办"){
					$.ajax({
						url: "/portal/task/startTask.action",
						type: 'post', 
						data:{
							id:$("[name=checkbox]:checked").val(),
							random:Math.random()
						},
						dataType: 'json', 
						error:function(){
							alert("系统连接失败，请稍后再试！");
						},
						success: function(obj){			
				          alert("操作成功！");
				          $("[name=checkbox]:checked").parents("tr").find($("[id=statusTd]")).html("在办");
				          $("[name=checkbox]:checked").parents("tr").find($("[id=realStartTimeTd]")).html(obj.time);
						}
					});	
				}else{
					alert("当前状态为"+status+"，不能开始任务！");
				}
			}
	 	}

	 	function finishTask(){
			if($("[name=checkbox]:checked").length==0){
				alert("请勾选任务！");
			}else if($("[name=checkbox]:checked").length>1){
				alert("一次只能选择一个任务！");
			}else{
				var status = $.trim($("[name=checkbox]:checked").parents("tr").find($("[id=statusTd]")).html());
				if(status=="在办"||status=="延误"){
					$.ajax({
						url: "/portal/task/finishTask.action",
						type: 'post', 
						data:{
							id:$("[name=checkbox]:checked").val(),
							random:Math.random()
						},
						dataType: 'json', 
						error:function(){
							alert("系统连接失败，请稍后再试！");
						},
						success: function(obj){			
				          alert("操作成功！");
				          $("[name=checkbox]:checked").parents("tr").find($("[id=statusTd]")).html("办结");
				          $("[name=checkbox]:checked").parents("tr").find($("[id=realFinishTimeTd]")).html(obj.time);
						}
					});	
				}else{
					alert("当前状态为"+status+"，不能完成任务！");
				}
			}
	 	}

	 	function selfDeleteFunc(id){
	 		$.ajax({
				url: "/portal/task/ifHaveTaskFollow.action",
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
						var text = "";
						if(obj.if_have=="no"){
							text = "确定删除？";
						}else if(obj.if_have=="yes"){
							text = "此任务包含任务跟踪，若删除此任务，会一并删除相关联的任务跟踪。确定删除？";
						}
						if(confirm(text)){
				 			$.ajax({
								url: "/portal/task/delete.action",
								type: 'post', 
								data:{
									id:id,
									if_have:obj.if_have,
									random:Math.random()
								},
								dataType: 'json', 
								error:function(){
									alert("系统连接失败，请稍后再试！");
								},
								success: function(obj){			
								   $("#form").submit();
								}
							});	
						}
				   }
				}
			});	
 		}

 		function followTask(){
 			if($("[name=checkbox]:checked").length==0){
				alert("请勾选任务！");
			}else if($("[name=checkbox]:checked").length>1){
				alert("一次只能选择一个任务！");
			}else{
				var status = $.trim($("[name=checkbox]:checked").parents("tr").find($("[id=statusTd]")).html());
				if(status=="在办"||status=="延误"){
					window.open("/portal/taskFollow/findTaskFollowByPage.action?oldDeptId=<%=oldDeptId%>&id="+$("[name=checkbox]:checked").val());
				}else{
					alert("当前状态为"+status+"，不能跟踪任务！");
				}
			}
 		}

 		function findProjectAndLine(){
			$.ajax({
				url: "/portal/history/findProjectAndLine.action",
				type: 'post', 
				data:{oldDeptId:"<%=oldDeptId%>",type:"0"},
				dataType: 'json', 
				error:function(){
					alert("系统连接失败，请稍后再试！");
				},
				success: function(obj){			
	              if(obj!=null&&obj.length>0){
					  var addHtml = "<option value=''>---请选择---</option>";
					  for(var i=0;i<obj.length;i++){
						  addHtml += "<option value='"+obj[i][0]+"'>"+obj[i][1]+"</option>";
					  }
					  $("#task_project").html(addHtml);
					  $("#task_project").val($("#projectId").val());
	              }
				}
			});	
		}

		function addTask(obj){
			if($("#task_project").val()==""){
				alert("请选择项目！");
			}else{
				$(obj).attr("disabled",true);
				$.ajax({
					url: "/portal/task/addTask.action",
					type: 'post', 
					data:{
						projectId:$("#task_project").val(),
						oldDeptId:'<%=oldDeptId%>',
						random:Math.random()
					},
					dataType: 'json', 
					error:function(){
						alert("系统连接失败，请稍后再试！");
					},
					success: function(data){			
						if(data!=null&&data.if_success=="yes"){
							$("#form").submit();
						}else{
							alert("提交失败，请联系管理员！");
						}
					}
				});	
			}
		}

		function findAllPapers(){
			$.ajax({
				url: "/portal/linePlan/findAllPapers.action",
				type: 'post', 
				dataType: 'json', 
				error:function(){
					alert("系统连接失败，请稍后再试！");
				},
				success: function(obj){			
	              if(obj!=null&&obj.length>0){
					  var addHtml = "<option value=''>---请选择---</option>";
					  for(var i=0;i<obj.length;i++){
						  addHtml += "<option value='"+obj[i][1]+"' paperId='"+obj[i][0]+"'>"+obj[i][1]+"</option>";
					  }
					  $("[name=paperName]").html(addHtml);
					  $("[name=paperName]").val($("#h_paperName").val());
	              }
				}
			});	
		}
    </script>
</head>

<body>	
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
			<div class="fl"><img id="show" onclick="showHide();" src="<%=basePath %>/css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li class="fin" id="titleName">任务发布列表</li>
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
		<div class="tabs_2">
        	<ul>
           		<li part="showPart" id="task1" class="selected"><a href="/portal/task/findTaskByPage.action?oldDeptId=<%=oldDeptId %>"><span>任务发布</span></a></li>
           		<li part="hiddenPart" style="display:none;" id="task2"><a href="/portal/task/findTaskByPage.action?type=do&oldDeptId=<%=oldDeptId %>"><span>任务实施</span></a></li>
				<%--<li part="hiddenPart" style="display:none;"><a href="/portal/taskFollow/findTaskFollowByPage.action?oldDeptId=<%=oldDeptId %>"><span>任务跟踪</span></a></li> --%>
            </ul>
        </div>
        <!--Filter-->
      <div class="filter">
        	<div class="query">
        	<div class="p8 filter_search">
        	<s:form action="findTaskByPage" id="form" method="post"  namespace="/task">
        	<input type="hidden" name="page" id="page" value="<s:property value='#request.page'/>"/>
        	<input type="hidden" name="oldDeptId" value="<s:property value='#request.oldDeptId'/>"/>
        	<input type="hidden" id="id" name="id" value="<s:property value='#request.id'/>">
        	<input type="hidden" name="type" value="<s:property value='#request.type'/>"/>
        	<input type="hidden" name="orderParam" value="<s:property value='#request.orderParam'/>">
        	<input type="hidden" name="orderValue" value="<s:property value='#request.orderValue'/>">
        	<input type="hidden" id="projectId" value="<s:property value='#request.projectId'/>">
        	 <table width="100%" border="0" cellspacing="0" cellpadding="0">
        	 <%--
        	    <tr>
        	     <td class="t_r">任务编号</td>
        	     <td>
        	     <input type="text" name="taskNum" class="input_xlarge" value="<s:property value='#request.taskNum'/>"/>
        	     </td>
        	     <td class="t_r">任务名称</td>
        	     <td>
        	    <input type="text" name="taskName" class="input_xlarge" value="<s:property value='#request.taskName'/>"/>
        	      </td>
        	     <td class="t_r">单体计划名称</td>
        	     <td>
        	       <input type="text" name="planName" class="input_xlarge" value="<s:property value='#request.planName'/>"/>
        	      </td>
      	      	</tr> 
      	      	
      	      	 <tr>
        	      <td class="t_r">责任人</td>
        	     <td>
        	       <input type="text" name="mainPerson" class="input_xlarge" value="<s:property value='#request.mainPerson'/>"/>
        	      </td>
        	      
      	      	</tr>
      	      	 --%>
      	      	<tr>
        	     <td class="t_r">单体</td>
        	     <td>
        	       <input type="text" name="monomerName" class="input_xlarge" value="<s:property value='#request.monomerName'/>"/>
        	      </td>
        	     <td class="t_r">证件名称</td>
        	     <td>
        	      <select name="paperName"></select>
                   <input type="hidden" id="h_paperName" value="<s:property value='#request.paperName'/>">
        	      </td>
        	      <td class="t_r">当前状态</td>
        	     <td>
        	     	<select name="status">
                    	<option value="">---请选择---</option>
                    	<option value="1">待办</option>
                    	<option value="2">在办</option>
                    	<option value="3">延误</option>
                    	<option value="4">办结</option>
                    	<option value="5">失效</option>
                    	<option value="6">过期</option>
                    </select>
                    <input type="hidden" id="h_status" value="<s:property value='#request.status'/>">
        	      </td>  
      	      	</tr>
      	      	<tr>
        	     <td class="t_r">项目</td>
        	     <td>
        	       <input type="text" name="projectName" class="input_xlarge" value="<s:property value='#request.projectName'/>"/>
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
		      <div class="fn clearfix">
		                  <h5 class="fl"><a href="#" class="colSelect fl" id="listName">任务发布列表</a></h5>
		             <!-- <input type="button" name="addButton" id="addButton" value="新 增" class="fr"> -->
		             <input type="button" id="addTaskButton" class="fr" value="自动发布任务" onclick="addTask(this);">
		             <select class="fr" id="task_project"></select>
		             <!-- <input type="button" part="hiddenPart" value="完成任务" style="display:none;" class="fr" onclick="finishTask();">		             
		             <input type="button" part="hiddenPart" value="跟踪任务" style="display:none;" class="fr" onclick="followTask()"> 
		             <input type="button" part="hiddenPart" value="开始任务" style="display:none;" class="fr" onclick="startTask();">-->
		             
		            </div>
		      </div>


      
        <!--Filter End-->
        <!--Table-->
        <s:set name="r" value="#request.pageResultSet"></s:set>  
        <div class="mb10" id="listDiv">
        	<table width="100%"  class="table_1" id="mytable">
        				<thead>
                              <tr class="tit" order="yes">
                                <!-- <th class="t_c" style="display:none;" part="hiddenPart"></th> -->
                                <th class="t_c">序号</th>
                                 <th class="t_c" orderParam="plan_name" style="cursor:pointer;">计划名称</th>
                                 <th class="t_c" orderParam="monomer_name" style="cursor:pointer;">单体</th>
                                <th class="t_c" orderParam="paper_name" style="cursor:pointer;">证件名称</th>
                                 <th class="t_c" orderParam="plan_start_time" style="cursor:pointer;">计划开始时间</th>
                                <th class="t_c" orderParam="plan_finish_time" style="cursor:pointer;">计划完成时间</th>
                                <th class="t_c" orderParam="real_start_time" style="display:none;cursor:pointer;" part="hiddenPart">实际开始时间</th>
                                <th class="t_c" orderParam="real_finish_time" style="display:none;cursor:pointer;" part="hiddenPart">实际完成时间</th>
                                <th class="t_c" orderParam="main_person" style="cursor:pointer;">经办人</th>
                                <th class="t_c" style="display:none;">线路计划名称</th>
                                <th class="t_c" style="display:none;">规则版本名称</th>
                                <th class="t_c" orderParam="task_status" style="cursor:pointer;">任务状态</th>
                                <!-- 
                                <th class="t_c" part="showPart">操作</th>
                                 -->
                                <th class="t_c" style="display:none;" part="hiddenPart">填报</th>
                                <!-- <th class="t_c" style="display:none;" part="hiddenPart">相关知识</th> -->
                                </tr>
                              </thead>
                              <tbody>
                              <s:iterator value="#r.list" id="data" status="s">
                             <tr>
                             	<%--<td class="t_c" style="display:none;" part="hiddenPart"><input type="checkbox" name="checkbox" value="<s:property value="#data.TASK_ID"/>"></td> --%>
                             	<td class="t_c"><s:property value="#s.index+1+(#r.pageInfo.currentPage-1)*10" /></td>
                             	<td class="t_c"><s:property value="#data.PLAN_NAME" /></td>
                             	<td class="t_c"><s:property value="#data.MONOMER_NAME" /></td>
                             	<td class="t_c"><s:property value="#data.PAPER_NAME" /></td>
                             	<td class="t_c"><s:property value="#data.PLAN_START_TIME" /></td>
                             	<td class="t_c"><s:property value="#data.PLAN_FINISH_TIME" /></td>
                             	<td class="t_c" style="display:none;" id="realStartTimeTd" part="hiddenPart"><s:property value="#data.REAL_START_TIME" /></td>
                             	<td class="t_c" style="display:none;" id="realFinishTimeTd" part="hiddenPart"><s:property value="#data.REAL_FINISH_TIME" /></td>
                             	<td class="t_c"><s:property value="#data.MAIN_PERSON" /></td>
                             	<td class="t_c" style="display:none;"><s:property value="#data.LINE_PLAN_NAME" /></td>
                             	<td class="t_c" style="display:none;"><s:property value="#data.RULE_VERSION_NAME" /></td>
                             	<td class="t_c" id="statusTd"><s:property value="#data.TASK_STATUS" /></td>
                             	<%-- 
                             	<td class="t_c" part="showPart">
                             		<a href="javascript:void(0);" onclick="toEdit('<s:property value="#data.ID" />','');">编辑</a>
                             		<a href="javascript:void(0);" onclick="selfDeleteFunc('<s:property value="#data.ID" />');">删除</a>
                             	</td>
                             	 --%>
                             	<td class="t_c" style="display:none;" part="hiddenPart">
                             		<a href="javascript:void(0);" onclick="toEdit('<s:property value="#data.TASK_ID" />','do','<s:property value="#data.PLAN_NAME" />','<s:property value="#data.PLAN_START_TIME" />','<s:property value="#data.PLAN_FINISH_TIME" />');">填报</a>
                             	</td>
                             	<%--
                             	<td class="t_c" style="display:none;" part="hiddenPart">
                             		<a href="/portal/knowledge/findKnowledgeByPage.action?part=edit&taskId=<s:property value="#data.ID" />" target="_blank">知识库</a>
                             	</td>
                             	 --%>
                             	</tr>
                             	</s:iterator>
                              </tbody>
                             <tr class="tfoot">
                                <td colspan="15"><div class="clearfix"><span class="fl"><s:property value="#r.pageInfo.totalRow"/>条记录</span>
                           		<ul class="fr clearfix pager">
		                             <li>Pages:<s:property value="#r.pageInfo.currentPage"/>/<s:property value="#r.pageInfo.totalPage"/>
		                             		<input type="hidden" value="<s:property value='#r.pageInfo.totalPage'/>" id="totalPageCount">
		                             		<input type="hidden" value="<s:property value='#r.pageInfo.currentPage'/>" id="currentNumber">
			                                  <input type="text" id="number" name="pageNumber" min="0" max="999" step="1" class="input_tiny" value="<s:property value='#r.pageInfo.currentPage'/>"/>
			                                  <input type="button" name="button" id="button" value="Go" onclick="goPage(0,0)">
		                             </li>
                             
		                             <s:if test="#r.pageInfo.currentPage==#r.pageInfo.totalPage">
		                             <li><a href="javascript:void(0)">&gt;&gt;</a></li>
		                             </s:if>
		                             <s:else>
		                              <li><a href="javascript:void(0)" onclick="goPage(<s:property value='#r.pageInfo.totalPage'/>,3)">&gt;&gt;</a></li>
		                             </s:else>
                              
	                             	<li>
	                             	<s:if test="#r.pageInfo.currentPage==#r.pageInfo.totalPage">	
	                             		<a href="javascript:void(0)">下一页</a>
	                             	</s:if>
	                             	<s:else>
	                             		<a href="javascript:void(0)" onclick="goPage(<s:property value='#r.pageInfo.currentPage'/>,2)">下一页</a>
	                             	</s:else>
	                             	</li>
	                             	<li>
	                             	<s:if test="#r.pageInfo.currentPage==1">
	                             		<a href="javascript:void(0)">上一页</a>
	                             	</s:if>
	                             	<s:else>
	                             		<a href="javascript:void(0)" onclick="goPage(<s:property value='#r.pageInfo.currentPage'/>,1)">上一页</a>
	                             	</s:else>
	                             	
	                             	</li> 
	                             
	                             	<s:if test="#r.pageInfo.currentPage==1">
	                             	<li><a href="javascript:void(0)">&lt;&lt;</a></li>
	                             	</s:if>
	                             	<s:else>
	                             		<li><a href="javascript:void(0)" onclick="goPage(1,3)">&lt;&lt;</a></li>
	                             	</s:else>
	                             
                                
                            </ul>
                        </div>
                                </td>
                              </tr>
                            </table>

      </div>
        <!--Table End-->
</div>
</div>
</body>
</html>