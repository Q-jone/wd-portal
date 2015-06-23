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
<title>线路计划</title>
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
	 			window.open("/portal/linePlan/toAdd.action?oldDeptId=<%=oldDeptId%>");
	 			return false;
	 		})

	 		findAllPlanTypes();
	 		findAllPapers();
	 		$("[name=ifNode]").val($("#h_ifNode").val());
	 		$("[name=ifMilestone]").val($("#h_ifMilestone").val());
	 		$("[name=status]").val($("#h_status").val());

	 		$("[id=statusTd]").each(function(){
				$(this).html($(this).text().replace("1","待办").replace("2","在办").replace("3","延误").replace("4","已办"));
		 	});

	 		orderFunc();
		})	
		
		function toEdit(id){
			window.open("/portal/linePlan/toEdit.action?id="+id+"&oldDeptId=<%=oldDeptId%>");
	 	}

	 	function findAllPlanTypes(){
			$.ajax({
				url: "/portal/linePlan/findAllPlanTypes.action",
				type: 'post', 
				dataType: 'json', 
				error:function(){
					alert("系统连接失败，请稍后再试！");
				},
				success: function(obj){			
	              if(obj!=null&&obj.length>0){
					  var addHtml = "<option value=''>---请选择---</option>";
					  for(var i=0;i<obj.length;i++){
						  addHtml += "<option value='"+obj[i][1]+"' planTypeId='"+obj[i][0]+"'>"+obj[i][1]+"</option>";
					  }
					  $("[name=planTypeName]").html(addHtml);
					  $("[name=planTypeName]").val($("#h_planTypeName").val());
	              }
				}
			});	
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
			$("[name=planNum]").val("");
			$("[name=planName]").val("");
			$("[name=projectName]").val("");
			$("[name=routeName]").val("");
			$("[name=planTypeName]").val("");
			$("[name=paperName]").val("");
			$("[name=ifNode]").val("");
			$("[name=ifMilestone]").val("");
			$("[name=status]").val("");
	 	}

	 	function selfDeleteFunc(id){
	 		$.ajax({
				url: "/portal/linePlan/ifHaveMonomerPlan.action",
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
							text = "此线路计划包含单体办证计划，若删除此线路计划，会一并删除相关联的单体办证计划。确定删除？";
						}
						if(confirm(text)){
				 			$.ajax({
								url: "/portal/linePlan/delete.action",
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


    </script>
</head>

<body>	
	
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
			<div class="fl"><img id="show" onclick="showHide();" src="<%=basePath %>/css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li class="fin">线路计划列表</li>
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
           		<li class="selected"><a href="/portal/linePlan/findLinePlanByPage.action?oldDeptId=<%=oldDeptId %>"><span>线路计划</span></a></li>
            </ul>
        </div>
        <!--Filter-->
      <div class="filter">
        	<div class="query">
        	<div class="p8 filter_search">
        	<s:form action="findLinePlanByPage" id="form" method="post"  namespace="/linePlan">
        	<input type="hidden" name="page" id="page" value="<s:property value='#request.page'/>"/>
        	<input type="hidden" name="oldDeptId" value="<s:property value='#request.oldDeptId'/>"/>
        	<input type="hidden" name="checkRole" value="<s:property value='#request.checkRole'/>"/>
        	<input type="hidden" name="orderParam" value="<s:property value='#request.orderParam'/>">
        	<input type="hidden" name="orderValue" value="<s:property value='#request.orderValue'/>">
        	 <table width="100%" border="0" cellspacing="0" cellpadding="0">
        	    <tr>
        	     <%--
        	     <td class="t_r">计划编号</td>
        	     <td>
        	     <input type="text" name="planNum" class="input_xlarge" value="<s:property value='#request.planNum'/>"/>
        	     </td>
        	     
        	     <td class="t_r">计划名称</td>
        	     <td>
        	    <input type="text" name="planName" class="input_xlarge" value="<s:property value='#request.planName'/>"/>
        	      </td>
        	      <td class="t_r">线路</td>
        	     <td>
        	     <input type="text" name="routeName" class="input_xlarge" value="<s:property value='#request.routeName'/>"/>
        	     </td>
        	       --%>
        	     <td class="t_r">项目</td>
        	     <td>
        	       <input type="text" name="projectName" class="input_xlarge" value="<s:property value='#request.projectName'/>"/>
        	      </td>
      	      	</tr> 
      	      	<%--
      	      	 <tr>
        	     <td class="t_r">计划类型</td>
        	     <td>
        	    <select name="planTypeName"></select>
                 <input type="hidden" id="h_planTypeName" value="<s:property value='#request.planTypeName'/>">
        	      </td>
        	     <td class="t_r">证件名称</td>
        	     <td>
        	      <select name="paperName"></select>
                   <input type="hidden" id="h_paperName" value="<s:property value='#request.paperName'/>">
        	      </td>
      	      	</tr>
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
        	      <td class="t_r">当前状态</td>
        	     <td>
        	     	<select name="status">
                    	<option value="">---请选择---</option>
                    	<option value="1">待办</option>
                    	<option value="2">在办</option>
                    	<option value="3">延误</option>
                    	<option value="4">已办</option>
                    </select>
                    <input type="hidden" id="h_status" value="<s:property value='#request.status'/>">
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
		                  <h5 class="fl"><a href="#" class="colSelect fl">线路计划列表</a></h5>
		                  <s:if test="#request.checkRole==0">
		             <input type="button" name="addButton" id="addButton" value="新 增" class="fr">
		             </s:if>
		            </div>
		      </div>


      
        <!--Filter End-->
        <!--Table-->
        <s:set name="r" value="#request.pageResultSet"></s:set>  
        <div class="mb10" id="listDiv">
        	<table width="100%"  class="table_1" id="mytable">
        				<thead>
                              <tr class="tit" order="yes">
                                <th class="t_c">序号</th>
                                 <th class="t_c" orderParam="plan_name" style="cursor:pointer;">计划名称</th>
                                 <th class="t_c" orderParam="project_name" style="cursor:pointer;">项目</th>
                                 <!-- 
                                <th class="t_c">线路</th>
                                 <th class="t_c">计划类型</th>
                                <th class="t_c">证件名称</th>
                                <th class="t_c">工期(天)</th>
                                <th class="t_c">工作量</th>
                                <th class="t_c">计量单位</th>
                              -->
                                <th class="t_c" orderParam="plan_start_time" style="cursor:pointer;">计划开始时间</th>
                                <th class="t_c" orderParam="plan_finish_time" style="cursor:pointer;">计划完成时间</th>
                                <th class="t_c">操作</th>
                                </tr>
                              
                              </thead>
                              <tbody>
                              <s:iterator value="#r.list" id="data" status="s">
                             <tr>
                             	<td class="t_c"><s:property value="#s.index+1+(#r.pageInfo.currentPage-1)*10" /></td>
                             	<td class="t_c"><s:property value="#data.PLAN_NAME" /></td>
                             	<td class="t_c"><s:property value="#data.PROJECT_NAME" /></td>
                             	<%--
                             	<td class="t_c"><s:property value="#data.ROUTE_NAME" /></td>
                             	<td class="t_c"><s:property value="#data.PLAN_TYPE_NAME" /></td>
                             	<td class="t_c"><s:property value="#data.PAPER_NAME" /></td>
                             	<td class="t_c"><s:property value="#data.TIME_LIMIT" /></td>
                             	<td class="t_c"><s:property value="#data.WORKLOAD" /></td>
                             	<td class="t_c"><s:property value="#data.MEASURE" /></td>
                             	 --%>
                             	 <td class="t_c"><s:property value="#data.PLAN_START_TIME" /></td>
                             	 <td class="t_c"><s:property value="#data.PLAN_FINISH_TIME" /></td>
                             	<td class="t_c">
                             		<s:if test="#request.checkRole==0">
                             		<a href="javascript:void(0);" onclick="toEdit('<s:property value="#data.ID" />');">编辑</a>
                             		<a href="javascript:void(0);" onclick="selfDeleteFunc('<s:property value="#data.ID" />');">删除</a>
                             		</s:if>
                             		<a href="/portal/monomerPlan/findMonomerPlanByPage.action?id=<s:property value="#data.ID" />&oldDeptId=<%=oldDeptId%>&checkRole=<s:property value="#request.checkRole" />" target="_blank">单体办证计划</a>
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