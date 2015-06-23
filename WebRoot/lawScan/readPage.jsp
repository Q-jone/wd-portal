<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URLDecoder" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String pname = URLDecoder.decode(request.getParameter("pname"));
String pid = request.getParameter("pid");
String cname = URLDecoder.decode(request.getParameter("cname"));
String cid = request.getParameter("cid");
String stptPath = request.getParameter("stptPath");
String url = stptPath+"/stoa/publicConn.jsp?urlPath=/htsp/detail.do?b_query=true&type=2&pinstanceid="+URLEncoder.encode(pid,"utf-8")+"&modelId="+URLEncoder.encode(pname,"utf-8");
%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>合同查询</title>

<!--[if IE 6.0]>
           <script src="../js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
        <script src="../js/html5.js"></script>
        <script src="../js/jquery-1.7.1.js"></script>
		<script src="../js/jquery.formalize.js"></script>
		<!--<script src="../js/switchDept.js"></script>-->
		
		<link type="text/css" href="../css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="../js/flick/jquery-ui-1.8.18.custom.min.js"></script>
		<script type="text/javascript" src="../js/flick/jquery.ui.datepicker-zh-CN.js"></script>
       	
	<script type="text/javascript">
	 	function read(obj){
			$(obj).attr("disabled",true);
	 		$.ajax({
				type: 'POST',
				url: '/portal/lawScan/read.action?random='+Math.random(),
				dataType:'json',
				data:{		
					cname:"<%=cname%>",
					cid:"<%=cid%>",
					pname:"<%=pname%>",
					pid:"<%=pid%>"
				},
				cache : false,
				error:function(){alert('系统连接失败，请稍后再试！')},
				success: function(){
					alert("设置成功！");
					window.close();
				}
			});
	 	}

	 
	        
    </script>



</head>

<body>
	<div>
		<div>
			<iframe  src="<%=url %>" frameBorder=0 scrolling="auto" marginheight="0" marginwidth="0" 
			style="WIDTH:100%; height:1000px;" >
			</iframe>
		</div>
		<div align="center">
			<input type="button" class="btn" value=" 已读 " onclick="read(this);"/>
			<input type="button" class="btn" value=" 取消 " onclick="javascript:window.close();" />
		</div>
	</div>
</body>
</html>