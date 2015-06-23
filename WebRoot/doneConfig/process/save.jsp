<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'save.jsp' starting page</title>
    
    <link rel="stylesheet" href="<%=basePath %>css/formalize.css" />
<link rel="stylesheet" href="<%=basePath %>css/page.css" />
<link rel="stylesheet" href="<%=basePath %>css/default/imgs.css" />
<link rel="stylesheet" href="<%=basePath %>css/reset.css" />

		<script src="<%=basePath %>js/jquery.formalize.js"></script>
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
  		$("[name=button]").click(function(){
  			window.location="<%=basePath %>doneConfig/process/list.action";
  		});
  	});
  	var flag=false;
  	$(function(){
  		$("[name=goback]").click(function(){
  			window.location="<%=basePath %>doneConfig/classic/list.action";
  		});
  	
  		$("#name").keyup(function(){//验证是否有重复的名称
  			$.ajax({
  				type: "post",
  				url: "<%=basePath %>doneConfig/process/checkName.action",
  				data:{name:$("#name").val(),id:$("#id").val()},
  				success: function(data, textStatus){
  				 //alert(data);
  				 if(data==0){
  					 flag=true;
  					 $("span").html("sdsd"+data);
  				 }
  				 else{
  					flag=false;
  					$("span").html("不能"+data);
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
    <%-- <form action="<%=basePath%>doneConfig/process/save.action" method="post"></form> --%>
    <div class="mb10 pt45">
        <form action="<%=basePath%>doneConfig/process/save.action" method="post">
        <input type="hidden" id="id" name="process.id" value="<s:property value="process.id"/>"/>
    	<table width="100%"  class="table_1">
           <thead>
            <th colspan="4" class="t_r">
       	      &nbsp;</th>
                                  </thead>
                              <tbody>
                               <tr>
                                <td class="t_r lableTd">名称：</td>
                                <td>
                                <input type="text" id="name" name="process.name" value="<s:property value="process.name"/>"/>&nbsp;&nbsp;&nbsp;&nbsp;<span></span></td>
                                </tr>
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="4" class="t_r"><input type="submit" name="submit" value="提交"/>&nbsp;
                                <!-- <input type="button" name="button" value="返回"/>
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
