<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'saveType.jsp' starting page</title>
 <%--  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css"> --%>
  <link rel="stylesheet" href="<%=basePath %>css/jquery-ui.css">
  <script src="<%=basePath %>js/jquery-1.11.1.js"></script>
  <script src="<%=basePath %>js/ui/jquery-ui.js"></script>
  <style>
  #sortable1, #sortable2, #sortable3 { list-style-type: none; margin: 0; padding: 0; float: left; margin-right: 10px; background: #eee; padding: 5px; width: 143px;}
  #sortable1 li, #sortable2 li, #sortable3 li { margin: 5px; padding: 5px; font-size: 1.2em; width: 120px; }
  </style>
  <link rel="stylesheet" href="<%=basePath %>css/formalize.css" />
	<link rel="stylesheet" href="<%=basePath %>css/page.css" />
	<link rel="stylesheet" href="<%=basePath %>css/default/imgs.css" />
	<link rel="stylesheet" href="<%=basePath %>css/reset.css" />
	<script src="<%=basePath %>js/html5.js"></script>
		<script src="<%=basePath %>js/jquery.formalize.js"></script>
		<script src="<%=basePath %>js/show.js"></script>
	<link type="text/css" href="<%=basePath %>css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
    <style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
	</style>
  <script>
  $(function() {
    $( "ul.droptrue" ).sortable({
      connectWith: "ul",
    });
 
    $( "ul.dropfalse" ).sortable({
      connectWith: "ul",
     // dropOnEmpty: false//为空也能拖动
    });
 
    $( "#sortable1, #sortable2, #sortable3" ).disableSelection();
    $("#submit").click(function(){
    	
    	var j=0;
    	$("#sortable2 li").each(function(i,n){
    		j++;
    	});
    	if(j>4){
    		alert("最多只能选择四个类型");
    		return false;
    	}
    	return true;
    });
   
  });
  </script>
</head>
<body>
 <div class="main">
  <!--Ctrl-->
		<div class="ctrl clearfix">
			<div class="fl"><img id="show" onclick="showHide();" src="<%=basePath %>css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li class="fin">当前用户：<s:property value="#session.userName"/>
   	<%-- 当前用户账号为：<s:property value="#session.loginName"/> --%></li>
                </ul>
            </div>
   		</div>
<%-- 当前用户为1：<s:property value="#session.userName"/>
   	当前用户账号为：<s:property value="#session.loginName"/>
   	<br> --%>
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
		                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<h5 class="fl"><a href="#" class="colSelect fl"><s:property value="#session.userName"/>的类型变更</a></h5>
		             &nbsp;<!-- <input type="button"  id="add" name="add" value="更新" class="fr"> -->
		           </div>
		      </div>
		      <div class="mb10">
		      <table style="width:100%;">
		      <tbody>
		      <tr>
		      <td style="width:50%;"><div style="width:100%;height: 500px;float: left;overflow-y:scroll; OVERFLOW-x: none;"><!-- border:1px solid #F00; -->
可添加类型:<br>
 <ul id="sortable1" class="droptrue" style="width:98%;height: 94%;background-color: white;">
  <s:iterator value="classic" var="c" status="lc">
  <li class="ui-state-default">
  <input type="hidden" name="chk" value="<s:property value="id"/>"/>
  <s:property value="name"/></li>
  </s:iterator>
</ul>
</div></td>
		      <td style="width:50%;"><div style="width:100%;height: 500px;float: left;"><!-- border:1px solid #F00; -->
 已添加类型:<br>
 <form action="<%=basePath%>doneConfig/userInfo/saveType.action" method="post">
 
<ul id="sortable2" class="dropfalse" style="width:100%;height: 100%;background-color: white;">
	<s:iterator value="reverseclassic" var="rc" status="lrc">
  <li class="ui-state-highlight">
  <input type="hidden" name="chk" value="<s:property value="id"/>"/>
  <s:property value="name"/></li>
  </s:iterator>
</ul>
<br><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" id="submit" name="submit" onclick="return check();" value="保存" />
</form>
 </div></td>
		      </tr></tbody>
		      </table>


<br style="clear:both">
</div></div>
  </body>
</html>
