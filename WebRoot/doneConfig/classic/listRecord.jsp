<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'list.jsp' starting page</title>
    
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
        <script src="<%=basePath %>project/sysinfo/js/jquery.form.js"></script>
		<script src="<%=basePath %>js/jquery.formalize.js"></script>
		<script src="<%=basePath %>js/loading.js"></script>
		<link type="text/css" href="<%=basePath %>css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="<%=basePath %>js/flick/jquery-ui-1.8.18.custom.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>js/flick/jquery.ui.datepicker-zh-CN.js"></script>
        <style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
		</style>
    
    <script src="<%=basePath %>js/html5.js"></script>
    <script src="<%=basePath %>js/jquery-1.7.1.js"></script>
    <script src="<%=basePath %>js/show.js"></script>

  </head>
  <script type="text/javascript">
  	$(function(){
  		$("[name=goback]").click(function(){
  			window.location="<%=basePath %>doneConfig/classic/list.action";
  		});
  	});
  </script>
  <body>
  <!--  搜索区域：<br/>
  <form action="" method="post">
   名称：<input type="text" id="name" name="process.name" value=""/>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   <input id="submit" type="submit" name="submit" value="搜索" />
   </form> -->
   <div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
			<div class="fl"><img id="show" onclick="showHide();" src="<%=basePath %>css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li class="fin">显示类型下面的记录</li>
                </ul>
            </div>
            
   		</div>
   <div class="pt45">
   <div style="background-color: ;">
		                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<h5 class="fl">显示类型下面的记录</h5>
		             &nbsp;<input type="button" name="goback" value="返回类型" class="fr">
		           <!--  <input type="button" name="excelButton" id="excelButton" value="导 出当页至 EXCEL" class="fl">
		              &nbsp;<input type="button" name="excelAllButton" id="excelAllButton" value="导 出全部至 EXCEL" class="fl">
		            --> </div>
		      </div>
    <hr style="border: 1px solid; color: gray">
    <div class="mb10">
    <table style="width:100%;"  border="1px" cellspacing="0px" class="table_1">
    	<thead>
    		<tr>
    			<th style="width:3%;text-align: left;" class="t_r">序号</th>
    			<th class="t_r" style="width:85%;text-align: left;"> 名称</th>
    			<th style="width:12%;text-align: left;" class="t_r">操作</th>
    		</tr>
    	</thead>
    	<tbody>
    		<s:iterator value="listRecorder" id="p" status="process">
    			<tr>
    				<td style="width:3%;text-align: left;" class="t_r lableTd"><s:property value="#process.index+1"/> </td>
    				<td style="width:85%;text-align: left;" class="t_r lableTd"><s:property value="name"/>  </td>
    				<td style="width:12%;text-align: left;" class="t_r lableTd"><a href="<%=basePath %>doneConfig/classic/deleteRecord.action?id=<s:property value="id"/>">删除</a></td>
    			</tr>
    		</s:iterator>
    	</tbody>
    </table>
    </div>
  </body>
</html>
