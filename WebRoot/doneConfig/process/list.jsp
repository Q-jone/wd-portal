<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'list.jsp' starting page</title>
    
    <link rel="stylesheet" href="<%=basePath %>css/formalize.css" />
<link rel="stylesheet" href="<%=basePath %>css/page.css" />
<link rel="stylesheet" href="<%=basePath %>css/default/imgs.css" />
<link rel="stylesheet" href="<%=basePath %>css/reset.css" />
<script src="<%=basePath %>js/html5.js"></script>
    <script src="<%=basePath %>js/jquery-1.7.1.js"></script>
    
		<script src="<%=basePath %>js/jquery.formalize.js"></script>
		<script src="<%=basePath %>js/show.js"></script>
		<link type="text/css" href="<%=basePath %>css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="<%=basePath %>js/flick/jquery-ui-1.8.18.custom.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>js/flick/jquery.ui.datepicker-zh-CN.js"></script>
        <style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
		</style>
    
    
  </head>
  <script type="text/javascript">
  	$(function(){
  		$("#add").click(function(){
  			//window.location="<%=basePath %>doneConfig/process/toAdd.action";
  			window.open("<%=basePath %>doneConfig/process/toAdd.action");
  		});
  		$("a[id=deletes]").click(function(){//doneConfig/process/delete.action?id=<s:property value="id"/>
  			var id=$(this).attr("title");
  			if(window.confirm("是否确认完成？"))
  			window.location="<%=basePath%>doneConfig/process/delete.action?id="+id;
  		});
  	});
  </script>
  <body>
   <div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
			<div class="fl"><img id="show" onclick="showHide();" src="<%=basePath %>css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li class="fin">信息列表</li>
                </ul>
            </div>
            
   		</div>
    <div class="pt45">
    <div class="filter">
        	<div class="query">
        	<div class="p8 filter_search">
        	  搜索区域：<br/>
  <form action="<%=basePath %>doneConfig/process/list.action" method="post">
   名称：<input type="text" id="name" name="process.name" value='<s:property value='process.name'/>'/>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   <input id="submit" type="submit" name="submit" value="搜索" />
   </form>
        	</div></div></div>
    <div style="height: 14px;">
		                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<h5 class="fl">信息列表</h5>
		             &nbsp;<input id="add" type="button" name="add" value="新增" class="fr">
   <!--  <hr style="border: 1px solid; color: gray"> -->
		         </div>
		      </div>
    <div class="mb10">
    <table style="width:100%;"  border="1px" cellspacing="0px" class="table_1" id="mytable">
    	<thead>
    		<tr class="tit">
    			<th width="5%" class="t_c">序号</th>
    			<th width="45%" class="t_c">名称</th>
    			<th class="t_c" width="15%"></th>
    			<th class="t_c" width="15%"></th>
    			<th width="20%" class="t_c">操作</th>
    		</tr>
    	</thead>
    	<tbody>
    		<s:iterator value="pageResultSet.list" id="p" status="process">
    			<tr id="dataTr">
    				<td class="t_c"><s:property value="#process.index+1"/> </td>
    				<td class="t_c"><s:property value="name"/>  </td>
    				<td class="t_c"></td><td class="t_c"></td>
    				<td class="t_c">
    				&nbsp;&nbsp;&nbsp;&nbsp;
    				<a class="fl mr5" href="javascript:void(0)" id="deletes" title="<s:property value="id"/>">删除</a>
    				&nbsp;&nbsp;&nbsp;&nbsp;
    				<a class="fl mr5" href="<%=basePath %>doneConfig/process/update.action?id=<s:property value="id"/>" target="_blank">更新</a>
    				</td>
    			</tr>
    		</s:iterator>
    	</tbody>
    
    </table>
 </div></div>
  </body>
</html>
