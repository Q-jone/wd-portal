<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String basePath = request.getContextPath();
String oldDeptId = request.getParameter("oldDeptId");
if(oldDeptId==null){
	oldDeptId = "";
}
String paper = request.getParameter("paper");
String routeId = request.getParameter("routeId");
String typeId = request.getParameter("typeId");
String monomerName = request.getParameter("monomerName");
String status = request.getParameter("status");
%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title><s:if test="#request.oldDeptId!=''">历史数据维护</s:if><s:else>前期办证完成情况</s:else></title>
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
	 		$("#addButton").click(function(){//2130
	 			window.open("/portal/beforeBuild/history/add.jsp?oldDeptId=<%=oldDeptId%>");
	 			return false;
	 		});
	 		
	 		$('#realFinishTime_start').datepicker({
				//inline: true								
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',	
				"currentText":'realFinishTime_start'//仅作为“清除”按钮的判断条件						
			});
	 		$('#realFinishTime_end').datepicker({
				//inline: true								
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',	
				"currentText":'realFinishTime_end'//仅作为“清除”按钮的判断条件						
			});
	 		
	       //datepicker的“清除”功能
	         $(".ui-datepicker-close").live("click", function (){              
	           if($(this).parent("div").children("button:eq(0)").text()=="realFinishTime_start") $("#realFinishTime_start").val("");
	           if($(this).parent("div").children("button:eq(0)").text()=="realFinishTime_end") $("#realFinishTime_end").val("");
	         });
	       
	       init();
		});
		
		function clearInput(){
			$("[name=projectName]").val("");
			$("[name=routeId]").val("");
			$("[name=monomerName]").val("");
			$("[name=paper]").val("");
			$("[name=realFinishTime_start]").val("");
			$("[name=realFinishTime_end]").val("");
			$("[name=typeId]").val("");
			$("[name=status]").val("");
	 	}

	 	function edit(id){
			window.open("/portal/beforeBuild/history/add.jsp?id="+id+"&oldDeptId=<%=oldDeptId%>");
 		}
	 	
	 	function view(id){
			window.open("/portal/history/detail.action?id="+id+"&oldDeptId=<%=oldDeptId%>");
 		}

	 	function init(){
	 		findAllTypes();
	 		findAllLines();
	 		findAllPapers();
	 		$("[name=status]").val("<%=status%>");
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
						  addHtml += "<option value='"+obj[i][0]+"' typeId='"+obj[i][0]+"'>"+obj[i][1]+"</option>";
					  }
					  $("[name=typeId]").html(addHtml);
					  $("[name=typeId]").val("<%=typeId%>");
					  findMonomerByRouteAndType();
	              }
				}
			});	
		}
		
		function findAllLines(){
			$.ajax({
				url: "/portal/history/findAllLines.action",
				type: 'post', 
				dataType: 'json', 
				error:function(){
					alert("系统连接失败，请稍后再试！");
				},
				success: function(obj){			
	              if(obj!=null&&obj.length>0){
					  var addHtml = "<option value=''>---请选择---</option>";
					  for(var i=0;i<obj.length;i++){
						  addHtml += "<option value='"+obj[i][0]+"' routeId='"+obj[i][0]+"'>"+obj[i][1]+"</option>";
					  }
					  $("[name=routeId]").html(addHtml);
					  $("[name=routeId]").val("<%=routeId%>");
					  findMonomerByRouteAndType();
	              }
				}
			});	
		}
		
		function findAllPapers(){
			$.ajax({
				url: "/portal/history/findAllPapers.action",
				type: 'post', 
				dataType: 'json', 
				error:function(){
					alert("系统连接失败，请稍后再试！");
				},
				success: function(obj){			
	              if(obj!=null&&obj.length>0){
					  var addHtml = "<option value=''>---请选择---</option>";
					  for(var i=0;i<obj.length;i++){
						  addHtml += "<option value='"+obj[i][1]+"' monoId='"+obj[i][0]+"'>"+obj[i][1]+"</option>";
					  }
					  /* addHtml += "<option value='拆迁许可' monoId=''>拆迁许可</option>";
					  addHtml += "<option value='交警审核' monoId=''>交警审核</option>"; */
					  $("[name=paper]").html(addHtml);
					  $("[name=paper]").val("<%=paper%>");
	              }
				}
			});	
		}
		
		function findMonomerByRouteAndType(){
			var routeId = $("[name=routeId]").children("option:selected").attr('routeId');
			var typeName = $("[name=typeId]").children("option:selected").text();
			
			if(!routeId && !typeName){
				return;
			}
			
			if(!routeId && typeName == '车站'){
				return;
			}
			
			$.ajax({
				url: "/portal/history/findMonomerByRouteAndType.action",
				type: 'post', 
				data:{
					routeId : routeId,
					typeName : typeName,
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
		           $("[name=monomerName]").val("<%=monomerName%>");
				}
			});	
		}
		
		function selectType(obj){
			var option = $(obj).children("option:selected");
			$("[name=typeId]").val(option.attr("typeId"));
			findMonomerByRouteAndType();
		}
		
		function selectLine(obj){
			var option = $(obj).children("option:selected");
			$("[name=routeId]").val(option.attr("routeId"));
			findMonomerByRouteAndType();
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
                	<li class="fin"><s:if test="#request.oldDeptId!=''">历史数据维护</s:if><s:else>前期办证完成情况</s:else></li>
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
        	<s:form action="findHistoryByPage" id="form" method="post"  namespace="/history">
        	<input type="hidden" name="oldDeptId" value="<s:property value='#request.oldDeptId'/>"/>
        	<input type="hidden" name="page" id="page" value="<s:property value='#request.page'/>"/>
        	 <table width="100%" border="0" cellspacing="0" cellpadding="0">
        	    <tr>
        	     <td class="t_r" width="10%">项目</td>
        	     <td width="20%">
        	     <input type="text" name="projectName" class="input_xlarge" value="<s:property value='#request.projectName'/>"/>
        	     </td>
        	     <td class="t_r">证件</td>
        	     <td>
	        	     <select name="paper"></select>
        	     </td>
        	     <td class="t_r">状态</td>
        	     <td>
<%--         	     <input type="text" id="realFinishTime_start" name="realFinishTime_start" style="width:117px"  value="<s:property value='#request.realFinishTime_start'/>" readonly="readonly"/>至
        	      <input type="text" id="realFinishTime_end" name="realFinishTime_end" style="width:117px"  value="<s:property value='#request.realFinishTime_end'/>" readonly="readonly"/> --%>
        	      <select name="status">
<!--         	      	<option value="1">未开始</option>
        	      	<option value="2">进行中</option>
        	      	<option value="3">延误</option> -->
        	      	<option value=''>---请选择---</option>
        	      	<option value="4">办结</option>
        	      	<option value="5">失效</option>
        	      	<option value="6">过期</option>
        	      </select>
        	     </td>        	     
      	      	</tr> 
      	      	 <tr>
        	     <td class="t_r">线路</td>
        	     <td>
        	     	<select name="routeId" onchange="selectLine(this);"></select>
        	      </td>
        	     <td class="t_r">单体类型</td>
        	     <td>
        	       <select name="typeId" onchange="selectType(this);"></select>
        	      </td>        	      
        	     <td class="t_r">单体</td>
        	     <td>
        	     	<select name="monomerName" ></select>
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
		                  <h5 class="fl"><a href="#" class="colSelect fl"><s:if test="#request.oldDeptId!=''">历史数据维护</s:if><s:else>前期办证完成情况</s:else></a></h5>
		                  <s:if test="#request.oldDeptId!=''">
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
                              <tr class="tit">
                                <!-- <td><input type="checkbox" id="test_checkbox_1" name="test_checkbox_1" /></td>-->
                                <th class="t_c">序号</th>
                                <th class="t_c">项目</th>
                                 <th class="t_c">线路</th>
                                 <th class="t_c">单体类型</th>
                                 <th class="t_c">单体</th>
                             	<th class="t_c">证件</th>
                             	<th class="t_c">实际开始时间</th>
                                 <th class="t_c">实际完成时间</th>
                                 <th class="t_c">状态</th>
                                <th class="t_c">操作</th>
                                </tr>
                              
                              </thead>
                              <tbody>
	                             <s:iterator value="#r.list" id="data" status="s">
	                             <tr>
	                             	<td class="t_c"><s:property value="#s.index+1+(#r.pageInfo.currentPage-1)*10" /></td>
	                             	<td class="t_c"><s:property value="#data.PROJECT_NAME" /></td>
	                             	<td class="t_c"><s:property value="#data.ROUTE_NAME" /></td>
	                             	<td class="t_c"><s:property value="#data.TYPE_NAME" /></td>
	                             	<td class="t_c"><s:property value="#data.MONOMER_NAME" /></td>
	                             	<td class="t_c"><s:property value="#data.PAPER" /></td>
	                             	<td class="t_c"><s:property value="#data.REAL_START_TIME" /></td>
	                             	<td class="t_c"><s:property value="#data.REAL_FINISH_TIME" /></td>
	                             	<td class="t_c"><s:if test="#data.STATUS ==null || #data.STATUS==4">办结</s:if>
	                             	<s:if test="#data.STATUS==5">失效</s:if>
	                             	<s:if test="#data.STATUS==6">过期</s:if>
	                             	</td>
	                             	<td class="t_c">
	                             		<a class="editA" href="javascript:void(0);" onclick="view('<s:property value="#data.ID" />');">查看</a>&nbsp;&nbsp;
	                             		<s:if test="#request.oldDeptId!='' && #data.SOURCE!=2 && #data.SOURCE!=3">
	                             		<a class="editA" href="javascript:void(0);" onclick="edit('<s:property value="#data.ID" />');">编辑</a>&nbsp;&nbsp;
	                             		<a href="javascript:void(0);" onclick="deleteFunc('<s:property value="#data.ID" />','history');">删除</a>
	                             		</s:if>
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