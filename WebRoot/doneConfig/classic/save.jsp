<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <base href="<%=basePath%>">
    
    <title>项目类型添加</title>
    
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
  var flag=false;
  	$(function(){
  		$("[name=goback]").click(function(){
  			window.location="<%=basePath %>doneConfig/classic/list.action";
  		});
  		$("#name").keyup(function(){//验证是否有重复的名称
  			$.ajax({
  				type: "post",
  				url: "<%=basePath %>doneConfig/classic/checkName.action",
  				data:{name:$("#name").val(),id:$("#id").val()},
  				success: function(data, textStatus){
  				 if(data==0){
  					flag=true;
  				 }else{
  					flag=false;
  				 }
  				},
  				complete: function(XMLHttpRequest, textStatus){
  				//HideLoading();
  				},
  				error: function(){
  				//请求出错处理
  				}
  		});
  		});
  		$("[name=submit]").click(function(){
  			if(flag){
  				return true;
  			}
  			alert("有重名的记录，请重新输入名字");
  			return	false;
  		});
  		
  	});
  	function shut(){
		  window.opener=null;
		  window.open("","_self");
		  window.close();
		}
  </script>
  <body>
    <%-- <form action="<%=basePath%>doneConfig/classic/save.action" method="post">
    <input type="hidden" id="id" name="doneConfigClassic.id" value="<s:property value="doneConfigClassic.id"/>"/>
    	流程类型：<input type="text" id="name" name="doneConfigClassic.name" value="<s:property value="doneConfigClassic.name"/>"/>&nbsp;&nbsp;&nbsp;&nbsp;
    	<input type="submit" name="submit" value="提交"  />&nbsp;&nbsp;&nbsp;
    	<input type="button" name="goback" value="返回"  />
    </form> --%>
    <div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img id="show" onclick="showHide();" src="<%=basePath %>css/default/images/sideBar_arrow_right.jpg" width="46" height="30" title="收起"></div>
            <div class="posi fl">
            	<ul>
            		<li class="fin">项目类型添加</li>
				</ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <!--Filter--><!--Filter End-->
        <!--Table-->        
        <div class="mb10 pt45">
        <form action="<%=basePath%>doneConfig/classic/save.action" method="post">
        <input type="hidden" id="id" name="doneConfigClassic.id" value="<s:property value="doneConfigClassic.id"/>"/>
    	<table width="100%"  class="table_1">
           <thead>
            <th colspan="4" class="t_r">
       	      &nbsp;</th>
                                  </thead>
                              <tbody>
                               <tr>
                                <td class="t_r lableTd">流程类型：</td>
                                <td>
                                <input type="text" id="name" name="doneConfigClassic.name" value="<s:property value="doneConfigClassic.name"/>"/></td>
                                </tr>
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="4" class="t_r"><input type="submit" name="submit" value="提交"  />&nbsp;
                                <!-- <input type="button" name="goback" value="返回"  />&nbsp;
                                <input type="button" value="关 闭" onclick="shut();"/>&nbsp;
                                <input type="reset" value="重 置" />&nbsp; --></td>
                              </tr>
                             
                            </table>
             </form>
      </div>
        <!--Table End-->
</div>
  </body>
</html>
