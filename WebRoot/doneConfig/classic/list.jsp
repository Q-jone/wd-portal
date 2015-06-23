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
    
    <title>类型显示</title>
    
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
        <%-- <script src="<%=basePath %>project/sysinfo/js/jquery.form.js"></script> --%>
        <script src="<%=basePath %>js/html5.js"></script>
    <script src="<%=basePath %>js/jquery-1.7.1.js"></script>
    
		<script src="<%=basePath %>js/jquery.formalize.js"></script>
		<script src="<%=basePath %>js/show.js"></script>
		<%-- <script src="<%=basePath %>js/loading.js"></script> --%>
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
  			//window.location="<%=basePath%>doneConfig/classic/dosave.action";
  			window.open("<%=basePath%>doneConfig/classic/dosave.action");
  		});
  		$("a[id=deletes]").click(function(){//doneConfig/classic/deleteType.action?id=<s:property value="id"/>
  			var id=$(this).attr("title");
  			if(window.confirm("是否确认完成？"))
  			window.location="<%=basePath%>doneConfig/classic/deleteType.action?id="+id;
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
                	<li class="fin">类型列表</li>
                </ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <div class="pt45">
        <div class="filter">
        	<div class="query">
        	<div class="p8 filter_search">
         搜索区域：<br/>
  <form action="<%=basePath%>doneConfig/classic/list.action" method="post">
   名称：<input type="text" id="name" name="doneConfigClassic.name" value='<s:property value='doneConfigClassic.name'/>'/>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   <input id="submit" type="submit" name="submit" value="搜索" />
   </form>
        
        </div></div></div>
      
   <div style="background-color: ;">
		                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<h5 class="fl">类型列表</h5>
		             &nbsp;<input  type="button" id="add" name="add" value="添加类型" class="fr">
		           <!--  <input type="button" name="excelButton" id="excelButton" value="导 出当页至 EXCEL" class="fl">
		              &nbsp;<input type="button" name="excelAllButton" id="excelAllButton" value="导 出全部至 EXCEL" class="fl">
		            --> </div>
		      </div>
    
    <div class="mb10">
    <table style="width:100%;border-collapse:;"class="table_1" id="mytable">  <!-- border="1px" cellspacing="0px"   -->
    	<thead>
    		<tr class="tit">
    			<th width="40px" class="t_c">序号</th>
    			<th class="t_c">名称</th>
    			<th class="t_c"></th>
    			<th class="t_c"></th>
    			<th class="t_c"></th>
    			<th class="t_c"></th>
    			<th width="330px" class="t_c">操作</th>
    		</tr>
    	</thead>
    	<tbody>
    		<s:iterator value="pageResultSet.list" id="p" status="doneConfigClassic">
    			<tr id="dataTr">
    				<td class="t_c"><s:property value="#doneConfigClassic.index+1"/> </td>
    				<td class="t_c"><s:property value="name"/></td>
    				<td class="t_c"></td>
    				<td class="t_c"></td>
    				<td class="t_c"></td>
    				<td class="t_c"></td>
    				<td class="t_c">
    				<a href="<%=basePath%>doneConfig/classic/addList.action?id=<s:property value="id"/>" class="fl mr5">添加记录</a>&nbsp;&nbsp;&nbsp;&nbsp;
    				<a href="<%=basePath%>doneConfig/classic/showList.action?id=<s:property value="id"/>" class="fl mr5">显示记录</a> 
    				&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" id="deletes" title="<s:property value="id"/>" class="fl mr5">删除类型</a> 
    				<!-- &nbsp;&nbsp;&nbsp;&nbsp;<a href="<%=basePath%>doneConfig/classic/findById.action?id=<s:property value="id"/>" class="fl mr5">编辑类型</a> 
    				 <a class="fl mr5" href="javascript:void(0)" onclick="editData('<s:property value='projectId'/>')">修改</a>-->
    				</td>
    			</tr>
    		</s:iterator>
    	</tbody>
    
    </table>
 </div>
 </div>
 
  </body>
</html>
