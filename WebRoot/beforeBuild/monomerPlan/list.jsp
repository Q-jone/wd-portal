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
<title>单体办证计划</title>
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
		var allCheck = false;
	 	$(function(){
	 		$("#addButton").click(function(){
	 			window.open("/portal/monomerPlan/toAdd.action?oldDeptId=<%=oldDeptId%>&linePlanId="+$("#id").val()+"&selected_route_name="+encodeURI($("#selected_route_name").val())+"&selected_route_id="+$("#selected_route_id").val()+"&linePlanName="+encodeURI($("[name=linePlanId]").val()));
	 			return false;
	 		})

	 		findAllPapers();
	 		$("[name=ifNode]").val($("#h_ifNode").val());
	 		$("[name=ifMilestone]").val($("#h_ifMilestone").val());
	 		$("[name=status]").val($("#h_status").val());
	 		$("[name=checkStatus]").val($("#h_checkStatus").val());
	 		$("[id=checkStatusTd]").each(function(){
				if($(this).html()=="1"&&$("[name=checkRole]").val()=="1"){
					$(this).attr("style","color:red;");				
				}else if($(this).html()=="2"&&$("[name=checkRole]").val()=="2"){
					$(this).attr("style","color:red;");				
				}
				$(this).html($(this).html().replace("1","未审核").replace("2","已初审").replace("3","已终审").replace("4","返回修改"));
		 	});

	 		orderFunc();

	 		if($("[name=checkRole]").val()!='0'){
				$("[part=hiddenPart]").show();
	 		}
		})	
		
		function toEdit(id,linePlanName){
			window.open("/portal/monomerPlan/toEdit.action?id="+id+"&oldDeptId=<%=oldDeptId%>&linePlanName="+encodeURI(linePlanName));
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

	 	function clearInput(){
			$("[name=planName]").val("");
			$("[name=monomerName]").val("");
			$("[name=linePlanId]").val("");
			$("[name=ruleVersionId]").val("");
			$("[name=paperName]").val("");
			$("[name=ifNode]").val("");
			$("[name=ifMilestone]").val("");
			$("[name=status]").val("");
			$("[name=checkStatus]").val("");
	 	}

	 	function selfDeleteFunc(id){
	 		$.ajax({
				url: "/portal/monomerPlan/ifHaveTask.action",
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
							text = "此单体计划包含任务，若删除此单体计划，会一并删除相关联的任务。确定删除？";
						}
						if(confirm(text)){
				 			$.ajax({
								url: "/portal/monomerPlan/delete.action",
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

 		function allCheckFunc(){
			var all_monomerId = "";
			if($("[name=checkbox]:checked").length>0){
				$("[name=checkbox]:checked").each(function(i){
					if(i>0){
						all_monomerId += ",";
					}
					all_monomerId += $(this).val();
				});
				MessageBox('handle_zone','maskDiv',all_monomerId,null);
			}else{
				alert("请先勾选单体计划！");
			}
			
 		}

 		function checkAll(){
 			allCheck = !allCheck;
 			$("[name=checkbox]").attr("checked",allCheck);
 		}

 		function cancelCheck(id){
			if(confirm("作废后将重新进行审核并删除已发布的任务，确认要作废吗？")){
				$.ajax({
					url: "/portal/monomerPlan/cancelCheck.action",
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
					   $("#form").submit();
					}
				});	
			}
 		}
    </script>
</head>

<body>	
	<jsp:include page="/beforeBuild/monomerPlan/checkBox.jsp"/>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
			<div class="fl"><img id="show" onclick="showHide();" src="<%=basePath %>/css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li class="fin">
                	<s:if test="#request.checkRole==0">单体办证计划列表</s:if>
                	<s:elseif test="#request.checkRole==1">单体办证计划初审</s:elseif>
                	<s:elseif test="#request.checkRole==2">单体办证计划终审</s:elseif>
                	</li>
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
	           	<li class="selected"><a href="/portal/monomerPlan/findMonomerPlanByPage.action?oldDeptId=<%=oldDeptId %>"><span>单体办证计划</span></a></li> 
            </ul>
        </div>
        <!--Filter-->
      <div class="filter">
        	<div class="query">
        	<div class="p8 filter_search">
        	<s:form action="findMonomerPlanByPage" id="form" method="post"  namespace="/monomerPlan">
        	<input type="hidden" name="page" id="page" value="<s:property value='#request.page'/>"/>
        	<input type="hidden" name="oldDeptId" value="<s:property value='#request.oldDeptId'/>"/>
        	<input type="hidden" id="id" name="id" value="<s:property value='#request.id'/>">
        	<input type="hidden" id="selected_route_name" value="<s:property value='#request.selected_route_name'/>">
        	<input type="hidden" id="selected_route_id" value="<s:property value='#request.selected_route_id'/>">
        	<input type="hidden" name="linePlanId" class="input_xlarge" value="<s:property value='#request.linePlanId'/>"/>
        	<input type="hidden" name="checkRole" value="<s:property value='#request.checkRole'/>"/>
        	<input type="hidden" name="orderParam" value="<s:property value='#request.orderParam'/>">
        	<input type="hidden" name="orderValue" value="<s:property value='#request.orderValue'/>">
        	 <table width="100%" border="0" cellspacing="0" cellpadding="0">
        	 <%--
        	    <tr>
        	     <td class="t_r">计划名称</td>
        	     <td>
        	    <input type="text" name="planName" class="input_xlarge" value="<s:property value='#request.planName'/>"/>
        	      </td>
        	      <td class="t_r">线路计划名称</td>
        	     <td>
        	       <input type="text" name="linePlanId" class="input_xlarge" value="<s:property value='#request.linePlanId'/>"/>
        	      </td>
        	      <td class="t_r">规则版本名称</td>
        	     <td>
        	       <input type="text" name="ruleVersionId" class="input_xlarge" value="<s:property value='#request.ruleVersionId'/>"/>
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
        	      <td class="t_r">审核状态</td>
        	     <td>
        	      <select name="checkStatus">
        	      	<option value="">---请选择---</option>
        	      	<option value="1">未审核</option>
        	      	<option value="2">已初审</option>
        	      	<option value="3">已终审</option>
        	      	<option value="4">返回修改</option>
        	      </select>
                   <input type="hidden" id="h_checkStatus" value="<s:property value='#request.checkStatus'/>">
        	      </td>
      	      	</tr>
      	      	<%--
      	      	<tr>
        	     <td class="t_r">是否节点</td>
        	     <td>
        	     	 <select name="ifNode">
                     	<option value="">---请选择---</option>
                     	<option value="1">是</option>
                     	<option value="0">否</option>
                     </select>
                     <input type="hidden" id="h_ifNode" value="<s:property value='#request.ifNode'/>">
        	     </td>
        	     <td class="t_r">是否里程碑</td>
        	     <td>
        	       <select name="ifMilestone">
                   	<option value="">---请选择---</option>
                   	<option value="1">是</option>
                   	<option value="0">否</option>
                   </select>
                   <input type="hidden" id="h_ifMilestone" value="<s:property value='#request.ifMilestone'/>">
        	      </td>
        	                 
      	      	</tr> 
      	    	 --%>
      	     	
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
		                  <h5 class="fl"><a href="#" class="colSelect fl">单体办证计划列表</a></h5>
		                  <s:if test="#request.checkRole==0">
		             <input type="button" name="addButton" id="addButton" value="新 增" class="fr">
		             </s:if>
		             <s:else>
		             	<input type="button" value="批量审核" class="fr" onclick="allCheckFunc();">
		             </s:else>
		            </div>
		      </div>


      
        <!--Filter End-->
        <!--Table-->
        <s:set name="r" value="#request.pageResultSet"></s:set>  
        <div class="mb10" id="listDiv">
        	<table width="100%"  class="table_1" id="mytable">
        				<thead>
                              <tr class="tit" order="yes">
                              	<th class="t_c" style="display:none;" part="hiddenPart">
                              		<input type="checkbox" onclick="checkAll();">
                              	</th>
                                <th class="t_c">序号</th>
                                 <th class="t_c" orderParam="plan_name" style="cursor:pointer;">计划名称</th>
                                 <th class="t_c" orderParam="monomer_name" style="cursor:pointer;">单体</th>
                                <th class="t_c" orderParam="paper_name" style="cursor:pointer;">证件名称</th>
                                 <th class="t_c" orderParam="plan_start_time" style="cursor:pointer;">计划开始时间</th>
                                <th class="t_c" orderParam="plan_finish_time" style="cursor:pointer;">计划完成时间</th>
                                <th class="t_c" orderParam="main_person" style="cursor:pointer;">经办人</th>
                                <th class="t_c" style="display:none;">线路计划名称</th>
                                <th class="t_c" style="display:none;">规则版本名称</th>
                                <th class="t_c" orderParam="check_status" style="cursor:pointer;">审核状态</th>
                                <th class="t_c">操作</th>
                                </tr>
                              </thead>
                              <tbody>
                              <s:iterator value="#r.list" id="data" status="s">
                             <tr>
                             	<td class="t_c" style="display:none;" part="hiddenPart">
	                             	<s:if test="(#request.checkRole==1&&#data.CHECK_STATUS==1)||(#request.checkRole==2&&#data.CHECK_STATUS==2)">
	                             		<input type="checkbox" name="checkbox" value="<s:property value="#data.ID"/>">
	                             	</s:if>
                             	</td>
                             	<td class="t_c"><s:property value="#s.index+1+(#r.pageInfo.currentPage-1)*10" /></td>
                             	<td class="t_c"><s:property value="#data.PLAN_NAME" /></td>
                             	<td class="t_c"><s:property value="#data.MONOMER_NAME" /></td>
                             	<td class="t_c"><s:property value="#data.PAPER_NAME" /></td>
                             	<td class="t_c"><s:property value="#data.PLAN_START_TIME" /></td>
                             	<td class="t_c"><s:property value="#data.PLAN_FINISH_TIME" /></td>
                             	<td class="t_c"><s:property value="#data.MAIN_PERSON" /></td>
                             	<td class="t_c" style="display:none;"><s:property value="#data.LINE_PLAN_NAME" /></td>
                             	<td class="t_c" style="display:none;"><s:property value="#data.RULE_VERSION_NAME" /></td>
                             	<td class="t_c" id="checkStatusTd"><s:property value="#data.CHECK_STATUS" /></td>
                             	<td class="t_c">
                             		<s:if test="#request.checkRole==0">
	                             		<s:if test="#data.CHECK_STATUS==3">
	                             			<a href="/portal/task/findTaskByPage.action?id=<s:property value="#data.ID" />&oldDeptId=<%=oldDeptId%>" target="_blank">任务布置</a>
	                             		</s:if>
	                             		<s:elseif test="#data.CHECK_STATUS==1||#data.CHECK_STATUS==4">
	                             			<a href="javascript:void(0);" onclick="toEdit('<s:property value="#data.ID" />','<s:property value="#data.LINE_PLAN_NAME" />');">编辑</a>
	                             			<a href="javascript:void(0);" onclick="selfDeleteFunc('<s:property value="#data.ID" />');">删除</a>
	                             		</s:elseif>
	                             		<s:if test="#data.CHECK_STATUS==2||#data.CHECK_STATUS==3">
	                             			<a href="javascript:void(0);" onclick="cancelCheck('<s:property value="#data.ID" />');">作废</a>
	                             		</s:if>
                             		</s:if>
                             		<s:elseif test="(#request.checkRole==1&&#data.CHECK_STATUS==1)||(#request.checkRole==2&&#data.CHECK_STATUS==2)">
                             			<a href="javascript:void(0);" onclick="MessageBox('handle_zone','maskDiv','<s:property value="#data.ID" />',this)">审核</a>
                             		</s:elseif>
                             		<a href="<%=basePath %>/monomerPlan/view.action?id=<s:property value="#data.ID" />" target="_blank">查看</a>
                             	</td>
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