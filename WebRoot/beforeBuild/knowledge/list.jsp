<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String basePath = request.getContextPath();
String oldDeptId = request.getParameter("oldDeptId");
if(oldDeptId==null){
	oldDeptId = "";
}
String role = request.getParameter("role");
if(role==null){
	role = "";
}
if("2".equals(role)||"3".equals(role)){
	oldDeptId = "";
}
String part = request.getParameter("part");
if(part==null){
	part = "";
}
%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>知识库维护</title>
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

	<script type="text/javascript">
		
	 	$(function(){
	 		$("#addButton").click(function(){
	 			window.open("/portal/knowledge/toAdd.action?oldDeptId=<%=oldDeptId%>");
	 			return false;
	 		})

	 		findAllPapers();
	 		$("[name=type]").val($("#h_type").val());
	 		$("[id=typeTd]").each(function(){
				$(this).html($(this).text().replace("1","办证指导").replace("2","填报表格").replace("3","注意事项").replace("4","规章制度"));
		 	});

		 	if("<%=part%>"=="edit"){
				$("[part=view]").hide();
		 	}else{
		 		$("[part=edit]").hide();
		 	}

		 	orderFunc();
		})	
		
		function toEdit(id){
			window.open("/portal/knowledge/toEdit.action?id="+id+"&oldDeptId=<%=oldDeptId%>");
	 	}

	 	function clearInput(){
			$("[name=type]").val("");
			$("[name=paperName]").val("");
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
                	<li class="fin">
                	<s:if test="#request.showType=='view'">
                	知识库查看
                	</s:if>
                	<s:else>
                	知识库维护
                	</s:else>
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
      <div class="tabs_2" part="view">
        	<ul>
        		<li><a href="/portal/beforeCount/toInfoList.action?oldDeptId=<%=oldDeptId %>&role=<%=role %>"><span>前期办证信息查询</span></a></li>
        		<li id="paperLi"><a href="/portal/beforeCount/toCountList.action?listType=paper&oldDeptId=<%=oldDeptId %>&role=<%=role %>"><span>按证件类型统计</span></a></li>
				<li id="projectLi"><a href="/portal/beforeCount/toCountList.action?listType=project&oldDeptId=<%=oldDeptId %>&role=<%=role %>"><span>按项目统计</span></a></li>
				<li id="monomerLi"><a href="/portal/beforeCount/toCountList.action?listType=monomer&oldDeptId=<%=oldDeptId %>&role=<%=role %>"><span>按单体统计</span></a></li>
				<li id="deptLi"><a href="/portal/beforeCount/toCountList.action?listType=dept&oldDeptId=<%=oldDeptId %>&role=<%=role %>"><span>按审批部门统计</span></a></li>
				<li><a href="/portal/report/findReportByPage.action?oldDeptId=<%=oldDeptId %>&role=<%=role %>"><span>业务提醒查询</span></a></li>
				<li class="selected"><a href="/portal/knowledge/findKnowledgeByPage.action?oldDeptId=<%=oldDeptId %>&role=<%=role %>&showType=view"><span>知识库</span></a></li>
				<li><a href="/portal/beforeCount/showPlanReport.action?oldDeptId=<%=oldDeptId %>&role=<%=role %>"><span>前期办证计划简报表</span></a></li>
            </ul>
        </div>
        <!--Filter-->
      <div class="filter">
        	<div class="query">
        	<div class="p8 filter_search">
        	<s:form action="findKnowledgeByPage" id="form" method="post"  namespace="/knowledge">
        	<input type="hidden" name="page" id="page" value="<s:property value='#request.page'/>"/>
        	<input type="hidden" name="oldDeptId" value="<s:property value='#request.oldDeptId'/>"/>
        	<input type="hidden" name="orderParam" value="<s:property value='#request.orderParam'/>">
        	<input type="hidden" name="orderValue" value="<s:property value='#request.orderValue'/>">
        	<input type="hidden" name="showType" value="<s:property value='#request.showType'/>">
        	<input type="hidden" name="part" value="<%=part %>"/>
        	 <table width="100%" border="0" cellspacing="0" cellpadding="0">
        	    <tr>
        	     <td class="t_r">证件类型</td>
        	     <td>
        	     <select name="paperName"></select>
                   <input type="hidden" id="h_paperName" value="<s:property value='#request.paperName'/>">
        	     </td>
        	     <td class="t_r">知识类型</td>
        	     <td>
        	   		<select name="type">
                    	<option value="">---请选择---</option>
                    	<option value="1">办证指导</option>
                    	<option value="2">填报表格</option>
                    	<option value="3">注意事项</option>
                    	<option value="4">规章制度</option>
                    </select>
                    <input type="hidden" id="h_type" value="<s:property value='#request.type'/>">
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
		                  <h5 class="fl"><a href="#" class="colSelect fl">
		                  <s:if test="#request.showType=='view'">
		                  	知识库查看
		                  	</s:if>
		                  	<s:else>
		                  	知识库维护
		                  	</s:else>
		                  </a></h5>
		             <input type="button" name="addButton" id="addButton" part="edit" value="新 增" class="fr">
		            </div>
		      </div>


      
        <!--Filter End-->
        <!--Table-->
        <s:set name="r" value="#request.pageResultSet"></s:set>  
        <div class="mb10" id="listDiv">
        	<table width="100%"  class="table_1" id="mytable">
        				<thead>
                              <tr class="tit" order="yes">
                                <!-- <td><input type="checkbox" id="test_checkbox_1" name="test_checkbox_1" /></td>-->
                                <th class="t_c">序号</th>
                                <th class="t_c" orderParam="paper_name" style="cursor:pointer;">证件名称</th>
                                 <th class="t_c" orderParam="type" style="cursor:pointer;">知识类型</th>
                                <th class="t_c">操作</th>
                                </tr>
                              
                              </thead>
                              <tbody>
	                             <s:iterator value="#r.list" id="data" status="s">
	                             <tr>
	                             	<td class="t_c"><s:property value="#s.index+1+(#r.pageInfo.currentPage-1)*10" /></td>
	                             	<td class="t_c"><s:property value="#data.PAPER_NAME" /></td>
	                             	<td class="t_c" id="typeTd"><s:property value="#data.TYPE" /></td>
	                             	<td class="t_c">
	                             		<s:if test="#request.showType=='view'">
	                             		<a href="/portal/knowledge/toView.action?id=<s:property value="#data.ID" />" target="_blank">查看</a>
                             			</s:if>
                             			<s:else>
                             			<a href="javascript:void(0);" onclick="toEdit('<s:property value="#data.ID" />');">编辑</a>
                             			<a href="javascript:void(0);" onclick="deleteFunc('<s:property value="#data.ID" />','knowledge');">删除</a>
                             			</s:else>
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