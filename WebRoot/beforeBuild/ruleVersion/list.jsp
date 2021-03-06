﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
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
<title>规则版本维护</title>
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
	 			window.open("/portal/ruleVersion/toAdd.action?oldDeptId=<%=oldDeptId%>");
	 			return false;
	 		})

	 		orderFunc();
		})	
		
		function toEdit(id){
			window.open("/portal/ruleVersion/toEdit.action?id="+id+"&oldDeptId=<%=oldDeptId%>");
	 	}

	 	function clearInput(){
			$("[name=versionNum]").val("");
			$("[name=versionName]").val("");
	 	}

	 	function selfDeleteFunc(id){
	 		$.ajax({
				url: "/portal/ruleVersion/ifHaveRuleDetail.action",
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
							text = "此规则版本包含规则详情，若删除此规则版本，会一并删除相关联的规则详情。确定删除？";
						}
						if(confirm(text)){
				 			$.ajax({
								url: "/portal/ruleVersion/delete.action",
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
                	<li class="fin">规则版本维护</li>
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
				<li class="selected"><a href="/portal/ruleVersion/findRuleVersionByPage.action?oldDeptId=<%=oldDeptId %>"><span>规则版本</span></a></li>
            </ul>
        </div>
        <!--Filter-->
      <div class="filter">
        	<div class="query">
        	<div class="p8 filter_search">
        	<s:form action="findRuleVersionByPage" id="form" method="post"  namespace="/ruleVersion">
        	<input type="hidden" name="page" id="page" value="<s:property value='#request.page'/>"/>
        	<input type="hidden" name="oldDeptId" value="<s:property value='#request.oldDeptId'/>"/>
        	<input type="hidden" name="orderParam" value="<s:property value='#request.orderParam'/>">
        	<input type="hidden" name="orderValue" value="<s:property value='#request.orderValue'/>">
        	 <table width="100%" border="0" cellspacing="0" cellpadding="0">
        	    <tr>
        	     <td class="t_r">版本号</td>
        	     <td>
        	     <input type="text" name="versionNum" class="input_xlarge" value="<s:property value='#request.versionNum'/>"/>
        	     </td>
        	     <td class="t_r">版本名称</td>
        	     <td>
        	   	<input type="text" name="versionName" class="input_xlarge" value="<s:property value='#request.versionName'/>"/>
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
		                  <h5 class="fl"><a href="#" class="colSelect fl">规则版本维护</a></h5>
		             <input type="button" name="addButton" id="addButton" value="新 增" class="fr">
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
                                <th class="t_c" orderParam="version_num" style="cursor:pointer;">版本号</th>
                                 <th class="t_c" orderParam="version_name" style="cursor:pointer;">版本名称</th>
                                 <th class="t_c">备注</th>
                             
                                <th class="t_c">操作</th>
                               
                                </tr>
                              
                              </thead>
                              <tbody>
	                             <s:iterator value="#r.list" id="data" status="s">
	                             <tr>
	                             	<td class="t_c"><s:property value="#s.index+1+(#r.pageInfo.currentPage-1)*10" /></td>
	                             	<td class="t_c"><s:property value="#data.VERSION_NUM" /></td>
	                             	<td class="t_c"><s:property value="#data.VERSION_NAME" /></td>
	                             	<td class="t_c" width="500px"><div style="width:500px; overflow:hidden; white-space:nowrap; text-overflow:ellipsis"><s:property value="#data.REMARK" /></div></td>
	                             	
	                             	<td class="t_c">
	                             		<a href="javascript:void(0);" onclick="toEdit('<s:property value="#data.ID" />');">编辑</a>
                             			<a href="javascript:void(0);" onclick="selfDeleteFunc('<s:property value="#data.ID" />');">删除</a>
                             			<a href="/portal/ruleDetail/findRuleDetailByPage.action?id=<s:property value="#data.ID" />&oldDeptId=<%=oldDeptId%>" target="_blank">规则详情</a>
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