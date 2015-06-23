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
    <title>初始化</title>
    
    <link rel="stylesheet" href="<%=basePath %>css/jquery-ui.css">
  <script src="<%=basePath %>js/jquery-1.7.1.js"></script>
  <script src="<%=basePath %>/doneConfig/jquery-ui.min.js"></script>
  <style>
  #sortable1, #sortable2, #sortable3 { list-style-type: none; margin: 0; padding: 0; float: left; margin-right: 10px; background: #eee; padding: 5px; width: 143px;}
  #sortable1 li, #sortable2 li, #sortable3 li { margin: 5px; padding: 5px; font-size: 1.2em; width: 120px; }
  </style>
   <link rel="stylesheet" href="<%=basePath %>css/formalize.css" />
	<link rel="stylesheet" href="<%=basePath %>css/page.css" />
	<link rel="stylesheet" href="<%=basePath %>css/default/imgs.css" />
	<link rel="stylesheet" href="<%=basePath %>css/reset.css" />
	<script src="<%=basePath %>js/html5.js"></script>
	<%-- <script src="<%=basePath %>js/jquery.formalize.js"></script> --%>
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
     // dropOnEmpty: false
    });
 
    $( "#sortable1, #sortable2, #sortable3" ).disableSelection();
 	 $("#submit").click(function(){
    	alert($("#listNum").val()+"sd");
       if($("#listNum").val()==null||$("#listNum").val()==''){
    		alert("请填写初始化账号");
    		return false;
    	} 
    	//return false;
    	$("#nums").val($("#listNum").val());
    	/* var j=0;
    	$("#sortable2 li").each(function(i,n){
    		j++;
    	});
    	if(j>4){
    		alert("最多只能选择四个类型");
    		return false;
    	}if(j<1){
    		alert("请选择初始化类型");
    		return false;
    	} */
    	//alert("as");//$("#form").submit();
    	//alert("ssdsss");
    	//$("#initForm").submit();
    	//return true;
       });
    
  });
  </script>
    
  </head>
  
  <body>
  <div class="main">
  <div class="ctrl clearfix">
			<div class="fl"><img id="show" onclick="showHide();" src="<%=basePath %>css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li class="fin">当前用户：<s:property value="#session.userName"/>
   	<%-- 当前用户账号为：<s:property value="#session.loginName"/> --%></li>
                </ul>
            </div>
   		</div>
   		<div class="pt45">
				<div class="filter">
		        	<div class="query">
		        	<!-- <div class="p8 filter_search">
		      	    </div> -->
		        	</div>     
		        </div>
                <!-- <div style="background-color: ;">
		                  
		           </div> -->
		      </div>
		      <div class="mb10">
    <div><!--  style="margin: 30px" -->
    <!-- <form action="" method="post"> -->
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;初始化账号设置:
   <textarea rows="5" id="listNum" name="listNum" cols="50"></textarea>
   <!-- <input id="nums" type name="nums"> -->
    <!-- <br> -->
    
    <br>
    <div style="width:50%;height: 460px;float: left;overflow-y:scroll; OVERFLOW-x: none;"><!-- border:1px solid #F00; -->
可添加类型:<br>
 <ul id="sortable1" class="droptrue" style="width:98%;height: 94%;background-color: white;">
  <s:iterator value="classic" var="c" status="lc">
  <li class="ui-state-default">
  <input type="hidden" name="chk" value="<s:property value="id"/>"/>
  <s:property value="name"/></li>
  </s:iterator>
</ul>
</div>
<div style="width:49%;height: 460px;float: left;overflow-y:scroll; OVERFLOW-x: none;"><!-- border:1px solid #F00; -->
 已添加类型:<br>
 <form action="<%=basePath%>doneConfig/userInfo/initType.action" method="post" id="initForm" name="initForm">
 <input type="hidden" id="nums" name="nums" value=""/>
<ul id="sortable2" class="dropfalse" style="width:97%;height: 300px;background-color: white;">
	<s:iterator value="reverseclassic" var="rc" status="lrc">
  <li class="ui-state-highlight">
  <input type="hidden" name="chk" value="<s:property value="id"/>"/>
  <s:property value="name"/></li>
  </s:iterator>
</ul>
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="submit" id="submit" name="submit" value="初始化账号" />
</form>
 </div>
    <!-- </form> -->
    </div></div></div>
  </body>
</html>
