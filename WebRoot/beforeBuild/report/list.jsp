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
%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>业务提醒</title>
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
        <script src="<%=basePath %>/beforeBuild/js/jquery.form.js"></script>
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
	 		$("[name=status]").val($("#h_status").val());
	 		$("[id=statusTd]").each(function(){
				$(this).html($(this).text().replace("1","已阅").replace("0","未阅"));
		 	});
	 		findAllPlanTypes();

	 		if($("[name=role]").val()=="1"){
				$("[part=hiddenPart]").show();
				$("[part=showPart]").hide();
				$("#report2").addClass("selected");
				$("#report1").removeClass("selected");
		 	}

	 		orderFunc();
		})	
		
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
					  $("[name=type]").html(addHtml);
					  $("[name=type]").val($("#h_type").val());
	              }
				}
			});	
		}

		function clearInput(){
			$("[name=type]").val("");
			$("[name=status]").val("");
			$("[name=content]").val("");
	 	}

	 	function sendMessage(){
	 		$.ajax({
				url: "/portal/report/sendMessage.action",
				type: 'post', 
				error:function(){
					alert("系统连接失败，请稍后再试！");
				},
				success: function(obj){			
					$.ajax({
						url: "/portal/report/sendMessage2.action",
						type: 'post', 
						error:function(){
							alert("系统连接失败，请稍后再试！");
						},
						success: function(obj){			
							alert("手动推送成功！");
						}
					});	
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
                	<li class="fin">业务提醒</li>
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
        		<li><a href="/portal/beforeCount/toInfoList.action?oldDeptId=<%=oldDeptId %>&role=<%=role %>"><span>前期办证信息查询</span></a></li>
        		<li id="paperLi"><a href="/portal/beforeCount/toCountList.action?listType=paper&oldDeptId=<%=oldDeptId %>&role=<%=role %>"><span>按证件类型统计</span></a></li>
				<li id="projectLi"><a href="/portal/beforeCount/toCountList.action?listType=project&oldDeptId=<%=oldDeptId %>&role=<%=role %>"><span>按项目统计</span></a></li>
				<li id="monomerLi"><a href="/portal/beforeCount/toCountList.action?listType=monomer&oldDeptId=<%=oldDeptId %>&role=<%=role %>"><span>按单体统计</span></a></li>
				<li id="deptLi"><a href="/portal/beforeCount/toCountList.action?listType=dept&oldDeptId=<%=oldDeptId %>&role=<%=role %>"><span>按审批部门统计</span></a></li>
				<li class="selected"><a href="/portal/report/findReportByPage.action?oldDeptId=<%=oldDeptId %>&role=<%=role %>"><span>业务提醒查询</span></a></li>
				<li><a href="/portal/knowledge/findKnowledgeByPage.action?oldDeptId=<%=oldDeptId %>&role=<%=role %>&showType=view"><span>知识库</span></a></li>
				<li><a href="/portal/beforeCount/showPlanReport.action?oldDeptId=<%=oldDeptId %>&role=<%=role %>"><span>前期办证计划简报表</span></a></li>
            </ul>
        </div>
        <!--Filter-->
      <div class="filter">
        	<div class="query">
        	<div class="p8 filter_search">
        	<s:form action="findReportByPage" id="form" method="post"  namespace="/report">
        	<input type="hidden" name="page" id="page" value="<s:property value='#request.page'/>"/>
        	<input type="hidden" name="oldDeptId" value="<s:property value='#request.oldDeptId'/>"/>
        	<input type="hidden" name="role" value="<s:property value='#request.role'/>"/>
        	<input type="hidden" name="orderParam" value="<s:property value='#request.orderParam'/>">
        	<input type="hidden" name="orderValue" value="<s:property value='#request.orderValue'/>">
        	 <table width="100%" border="0" cellspacing="0" cellpadding="0">
        	    <tr>
        	     <td class="t_r">类别</td>
        	     <td>
        	     	 <select name="type"></select>
                 	<input type="hidden" id="h_type" value="<s:property value='#request.type'/>">
        	     </td>
        	     <td class="t_r">状态</td>
        	     <td>
        	     	 <select name="status">
                    	<option value="">---请选择---</option>
                    	<option value="0">未阅</option>
                    	<option value="1">已阅</option>
                    </select>
                    <input type="hidden" id="h_status" value="<s:property value='#request.status'/>">
        	     </td>
        	     <td class="t_r">消息内容</td>
        	     <td>
        	       <input type="text" name="content" class="input_xlarge" value="<s:property value='#request.content'/>"/>
        	      </td>
        	      
      	      	</tr> 
        	    <tr>
        	      <td colspan="6" class="t_c">
                  	<input type="submit" value="检 索" />&nbsp;&nbsp;
                  	<input type="button" value="重 置" onclick="clearInput();"/>
                  	<%if(!"10.1.48.30".equals(request.getLocalAddr())&&!"10.1.48.39".equals(request.getLocalAddr())){%>
                  	&nbsp;&nbsp;<input type="button" value="手动推动" onclick="sendMessage();"/>
					<%}%>
                  	</td>
				</tr>
      	    </table>
      	    </s:form>
      	    </div>
        	</div>     
		      <div class="fn clearfix">
		                  <h5 class="fl"><a href="#" class="colSelect fl">业务提醒</a></h5>
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
                                <th class="t_c">类别</th>
                                 <th class="t_c" orderParam="receive_time" style="cursor:pointer;">接收时间</th>
                                 <th class="t_c" orderParam="read_time" style="cursor:pointer;">阅读时间</th>
                                 <th class="t_c">消息内容</th>
                                <th class="t_c" orderParam="status" style="cursor:pointer;">状态</th>
                                </tr>
                              
                              </thead>
                              <tbody>
                             <s:iterator value="#r.list" id="data" status="s">
	                             <tr>
	                             	<td class="t_c"><s:property value="#s.index+1+(#r.pageInfo.currentPage-1)*10" /></td>
	                             	<td class="t_c"><s:property value="#data.TYPE" /></td>
	                             	<td class="t_c"><s:property value="#data.RECEIVE_TIME" /></td>
	                             	<td class="t_c"><s:property value="#data.READ_TIME" /></td>
	                             	<td class="t_c"><s:property value="#data.CONTENT" /></td>
	                             	<td class="t_c" id="statusTd"><s:property value="#data.STATUS" /></td>
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