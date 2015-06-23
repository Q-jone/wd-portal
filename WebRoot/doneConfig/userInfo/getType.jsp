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
  		alert($("#count").val());
  		return false;
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
          当前用户为：<s:property value="#session.userName"/>
   	当前用户账号为：<s:property value="#session.loginName"/>
   	<br>
  	
    <hr style="border: 1px solid; color: gray">
    <form action="<%=basePath%>doneConfig/userInfo/addinfo.action" method="post" id="" name="">
    <input type="hidden" id="id" name="id" value="<s:property value="doneConfigClassic.id"/>"/>
    <input type="button"  id="all" name="all" value="全选"/>&nbsp;/&nbsp;<input type="button"  id="reverse" name="reverse" value="反选" />
    &nbsp;/&nbsp;<input type="submit"  id="add" name="add" value="添加"/>
    <input type="hidden" id="count" name="count" value="<s:property value='count'/>"/>
    <table style="width:100%;"  border="1px" cellspacing="0px">
    	<thead>
    		<tr>
    			<th style="width: 40px;">选择</th>
    			<th width="40px">序号</th>
    			
    			<th>名称</th>
    			<!--<th width="100px">操作</th> -->
    		</tr>
    	</thead>
    	<tbody>
    	
    		<s:iterator value="classic" id="p" status="classic">
    			<tr>
    				<td><input type="checkbox"  name="chk" value="<s:property value='id'/>"   /> </td>
    				<td><s:property value="#doneConfigClassic.index+1"/> </td>
    				<td><s:property value="name"/></td>
    				<!-- <td><a href="<%=basePath%>doneConfig/classic/addList.action?id=<s:property value="id"/>">添加纪录</a></td>
    				 -->
    			</tr>
    		</s:iterator>
    	</tbody>
    
    </table>
    </form>
  </body>
</html>
