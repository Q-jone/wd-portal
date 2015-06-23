<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>事件查看</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/formalize.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/page.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/default/imgs.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/reset.css" />
<!--[if IE 6.0]>
           <script src="js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
         <script src="<%=request.getContextPath() %>/js/html5.js"></script>
        <script src="<%=request.getContextPath() %>/applyProject/js/jquery-1.10.2.min.js"></script>
        <script src="<%=request.getContextPath() %>/applyProject/js/jquery-migrate-1.2.1.min.js"></script>
        <script src="<%=request.getContextPath() %>/js/show.js"></script>
        <script src="<%=request.getContextPath() %>/applyProject/js/jquery.blockUI.js"></script>
        <script src="<%=request.getContextPath() %>/applyProject/js/jquery.form.js"></script>
		<script src="<%=request.getContextPath() %>/js/jquery.formalize.js"></script>
		<link type="text/css" href="<%=request.getContextPath() %>/applyProject/css/flick/jquery-ui-1.10.3.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="<%=request.getContextPath() %>/applyProject/js/jquery-ui-1.10.3.custom.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/flick/jquery.ui.datepicker-zh-CN.js"></script>
		
		<script type="text/javascript">
		
		
         $(function(){
			$(".odd tr:odd").css("background","#fafafa");				
		});
		
		function shut(){
		  window.opener=null;
		  window.open("","_self");
		  window.close();
		}
		
        </script>
       </head>

<body>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img id="show" onclick="showHide();" src="<%=request.getContextPath() %>/css/default/images/sideBar_arrow_right.jpg" width="46" height="30" title="收起"></div>
            <div class="posi fl">
            	<ul>
            		<li class="fin">事件查看</li>
                </ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <!--Filter--><!--Filter End-->
        <!--Table-->
        <div class="mb10 pt45">
        <form id="update">
         <table width="100%"  class="table_1">
           <thead>
            <th colspan="4" class="t_r">&nbsp;</th>
                                  </thead>
                               <tbody>
                               <tr>
                                <td class="t_r lableTd">事件内容：</td>
                                <td colspan="3">
                                <pre style="width: 100%; white-space: pre-wrap !important; word-wrap: break-word;"><s:property escape="0" value="bo.accidentContent"/></pre>
								</td>
                                </tr>
                            
                               <tr>
        	      				<td class="t_r lableTd">填报人：</td>
                               <td>
                               <s:property value="bo.operatePerson"/>
                               </td>
        	      				<td>
        	      				</td>
                             </tr>
                             
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="4" class="t_r">
                                <input type="button" value="关 闭" onclick="shut();"/>&nbsp;
                               </tr>
                             
                              
                            </table>
             </form>
      </div>
        <!--Table End-->
</div>
</body>
</html>