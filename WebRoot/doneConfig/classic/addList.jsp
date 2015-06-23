<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>将记录归于用户下的类型中</title>
    
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
  	$("#all").click(function(){//全选
  		$("[name^=chk]").each(function(i){
  			$(this).attr("checked","checked");
  		});
  	});
  	$("#reverse").click(function(){//反选
  		$("[name^=chk]").each(function(i){
  			
  			if($(this).attr("checked")){
  				$(this).attr("checked",false);
  			}else{
  				$(this).attr("checked","checked");
  			}
  		});
  	});
  	$("#add").click(function(){
  		var j=0;
  		$("[name^=chk]").each(function(i){
  			if($(this).attr("checked")){
  				j=j+1;
  			}
  		});
  		if(j>0){
  			return true;
  		}
  		alert(j+"至少选择一条记录");
  		return false;
  	});
  });
  
  </script>
  <body>
  <!-- <div style="width:98%;float: auto;margin: auto;margin-top: 10px;"> -->
  
  	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
			<div class="fl"><img id="show" onclick="showHide();" src="<%=basePath %>css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li class="fin">项目基本信息列表</li>
                </ul>
            </div>
            
   		</div>
        <!--Ctrl End-->
    <hr style="border: 1px solid; color: gray">
     
        <!--Filter End-->
        <!--Table-->
        <div class="mb10">
    <form action="<%=basePath%>doneConfig/classic/addRecord.action" method="post">
    <div class="pt45">
    <div style="background-color: ;">
		                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<h5 class="fl"> 类型名称：<label><s:property value="doneConfigClassic.name"/></label></h5>
		             &nbsp;
		           <input type="button"  id="all" name="all" value="全选"/>&nbsp;/&nbsp;<input type="button"  id="reverse" name="reverse" value="反选" />
    &nbsp;/&nbsp;<input type="submit"  id="add" name="add" value="添加" onclick="return adds();"/>
		           </div>
		      </div>
    <input type="hidden" id="id" name="id" value="<s:property value="doneConfigClassic.id"/>" />
    
    
    
    <table style="width:100%;"  border="1px" cellspacing="0px" class="table_1">
    	<thead>
    		<tr>
    			<th style="width: 3%;text-align: left;" class="t_r" >选择</th>
    			<th style="width: 3%;text-align: left;" class="t_r">序号</th>
    			
    			<th class="t_r" style="width: 94%;text-align: left;">名称</th>
    			<!--<th width="100px">操作</th> -->
    		</tr>
    	</thead>
    	<tbody>
    	
    		<s:iterator value="listRecorder" id="p" status="doneConfigClassic">
    			<tr>
    				<td class="t_r lableTd" style="width:3%;text-align: left;"><input type="checkbox"  name="chk" value="<s:property value='id'/>" /> </td>
    				<td class="t_r lableTd" style="width:3%;text-align: left;"><s:property value="#doneConfigClassic.index+1"/> </td>
    				<td class="t_r lableTd" style="width:94%;text-align: left;"><s:property value="name"/></td>
    				<!-- <td><a href="<%=basePath%>doneConfig/classic/addList.action?id=<s:property value="id"/>">添加纪录</a></td>
    				 -->
    			</tr>
    		</s:iterator>
    	</tbody>
    
    </table>
    </form>
    </div>
    </div>
    <!-- </div> -->
  </body>
</html>
