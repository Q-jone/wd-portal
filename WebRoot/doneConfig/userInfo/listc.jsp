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
    <title>显示信息</title>
    
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
   var i=0;
 $(function(){
 	$("#add").click(function(){
 		window.location="<%=basePath%>doneConfig/userInfo/getType.action";
 	});
 	$("#all").click(function(){//全选
  	i=0;
  		$("[name^=chk]").each(function(i){
  			$(this).attr("checked","checked");
  			i++;
  		});
  	});
  	$("#reverse").click(function(){//反选
  	i=0;
  		$("[name^=chk]").each(function(i){
  			
  			if($(this).attr("checked")){
  				$(this).attr("checked",false);
  			}else{
  				$(this).attr("checked","checked");
  				i++;
  			}
  		});
  	});
  	$("#addRecord").click(function(){//将类型下未添加到当前账号的记录选出来
  		window.location="<%=basePath %>doneConfig/userInfo/addList.action?typeId="+$("#typeId").val();
  		/* var i=0;
		$("[name^=chk]").each(function(i){//判断是否选中值 
  			if($(this).attr("checked")){
  				i++;
  			}
  		});
		if(i>0)
  			
  		else
  			alert("至少选中一条记录!"); */
  	});
  	$("#del").click(function(){
  		var j=0;
		$("[name^=chk]").each(function(i){//判断是否选中值 
  			if($(this).attr("checked")){
  				j++;
  			}
  		});
		if(j>0){
  			return true;
		}else{
			alert("请选择删除的记录!!!");
			return false;
		}			
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
                	<li class="fin">当前用户为：<s:property value="#session.userName"/>
   	当前用户账号为：<s:property value="#session.loginName"/></li>
                </ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <div class="pt45">
        <!--Filter-->
				<div class="filter">
		        	<div class="query">
		        	<div class="p8 filter_search">
		      	    </div>
		        	</div>     
		        </div>
                <div style="background-color: ;">
		                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<h5 class="fl"><a href="#" class="colSelect fl">信息列表</a></h5>
		             &nbsp;<input type="button"  id="add" name="add" value="更新" class="fr">
		           </div>
		      </div>
   <%-- <div style="width:98%;height: 90px;border:1px solid #F00;">
   
   	当前用户为：<s:property value="#session.userName"/>
   	当前用户账号为：<s:property value="#session.loginName"/>
    <br>
    
   	&nbsp;&nbsp;<input type="button"  id="add" name="add" value="更新"/>
   </div> --%>
   <div class="mb10">
   <div style="width:30%;height: 500px;border:1px solid #F00;float: left;margin-top: 5px;">
   <table width="100%"  class="table_1" id="mytable">
   <thead>
   <tr class="tit"><td class="t_c">当前用户所有类型：</td></tr>
   </thead>
   <tbody>
   	<s:if test="listType !=null">
	   <s:iterator value="listType" id="type" var="type" status="types">
	   <tr id="dataTr"><td class="t_c">
	   		<a href="<%=basePath %>doneConfig/userInfo/list.action?typeId=<s:property value='#type[0]'/>" ><s:property value="#type[1]"/></a><br>
	   </td></tr>
	   </s:iterator>
   </s:if>
   <s:if test="listType.size() <=0"><tr id="dataTr"><td class="t_c"><font color="red">当前账号没有选择类型</font> </td></tr></s:if>
   </tbody>
   </table>
   </div>
   <div style="width:48%;height: 500px;border:1px solid #F00;float: left;margin-top: 5px;">
   <form action="<%=basePath %>doneConfig/userInfo/deletes.action" method="post" id="" name="">
   <input type="hidden" id="typeId" name="typeId" value='<s:property value="typeId"/>'/>
   <input type="button"  id="all" name="all" value="全选"/>&nbsp;/&nbsp;<input type="button"  id="reverse" name="reverse" value="反选" />
    &nbsp;/&nbsp;<input type="submit"  id="del" name="del"  value="删除"/>
    &nbsp;/&nbsp;<input type="button"  id="addRecord" name="addRecord" value="添加记录"/>
   <table style="width:100%;"  border="1px" cellspacing="0px">
   <thead>
    		<tr>
    			<th width="40px">序号</th>
    			<th>名称</th>
    			<th width="100px">操作</th>
    		</tr>
    	</thead>
   <s:iterator value="list" var="info" status="l">
   		<tr>
   			<td><input type="checkbox"  name="chk" value="<s:property value='id'/>"/></td>
   			<td><s:property value="recordName"/> </td>
   			<td><a href="<%=basePath %>doneConfig/userInfo/deletes.action?id=<s:property value="id"/>">删除记录</a></td>
   		</tr>
   </s:iterator>
   </table>
   </form>
   </div>
   </div>
   </div>
  </body>
</html>
