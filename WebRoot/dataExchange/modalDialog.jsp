<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<% 
String par=request.getParameter("p");
String url="";
if(par.equals("bh")){
	url="http://10.1.44.18/fowardContractPage.action";
}if(par.equals("deptLeader")){
	url="http://10.1.44.18/getDeptLeader.jsp";
}
%>
<!DOCTYPE html>
<html>
  <body>
  <form id="page_interface_form" name="page_interface_form" method="post">
     <input type="hidden" value="测试数据11111111" name="data" />
  </form>
    <iframe name="page_interface_frame" width="100%" height="100%" ></iframe>
  </body>
</html>
<script type="text/javascript">
    //嵌入集成页面
    call_page_interface();    
    //在iframe中调用页面集成接口
    function call_page_interface(){
        document.getElementById("page_interface_form").action = "<%=url%>";
        document.getElementById("page_interface_form").target = 'page_interface_frame';
        document.getElementById("page_interface_form").submit();
    }
</script>
