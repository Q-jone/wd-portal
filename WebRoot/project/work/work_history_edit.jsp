<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>历史计划修改
</title>
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
        <script src="<%=basePath %>js/html5.js"></script>
        <script src="<%=basePath %>js/jquery-1.7.1.js"></script>
        <script src="<%=basePath %>js/jquery.form.js"></script>
		<script src="<%=basePath %>js/jquery.formalize.js"></script>
		<!--<script src="../js/switchDept.js"></script>-->
		<script src="<%=basePath %>js/show.js"></script>
		<script src="<%=basePath %>js/loading.js"></script>
		<link type="text/css" href="<%=basePath %>css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="<%=basePath %>js/flick/jquery-ui-1.8.18.custom.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>js/flick/jquery.ui.datepicker-zh-CN.js"></script>
        <style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
		</style>
		
		<script type="text/javascript">
         $(function(){
        	 $("#workProgressId").val(window.dialogArguments[0]);
        	 $("#progress").val(window.dialogArguments[1]);
        	 $("#workSecludeId").val(window.dialogArguments[2]);
			$(".odd tr:odd").css("background","#fafafa");	
			$('#updateTime').datepicker({
				//inline: true
				"changeYear":true,
				"showButtonPanel":true,
				"closeText":'清除',
				"currentText":'updateTime'//仅作为“清除”按钮的判断条件
			});
			//datepicker的“清除”功能
            $(".ui-datepicker-close").live("click", function (){              
              if($(this).parent("div").children("button:eq(0)").text()=="pfTime") $("#updateTime").val("");                   
            });
			$("#submit").click(function(){
				
				$("#edit").submit();
				
			});
					
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
        	<div class="fl"><img id="show" onclick="showHide();" src="<%=basePath %>css/default/images/sideBar_arrow_right.jpg" width="46" height="30" title="收起"></div>
            <div class="posi fl">
            	<ul>
            		<li class="fin">历史计划修改</li>
				</ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <!--Filter--><!--Filter End-->
        <!--Table-->
        
        <div class="mb10 pt45">
        <form id="edit" action="/portal/project/progress/save.action" method="post">
        <input  type="hidden" id="workProgressId" name="workProgressId" value="<s:property value='workProgress.workProgressId'/>"/>
          <input type="hidden" id="workSecludeId" name="workProgress.workSecludeId" value="<s:property value='workProgress.workSecludeId'/>" />
          <table width="100%"  class="table_1">
           <thead>
            <th colspan="4" class="t_r">
       	      &nbsp;</th>
                                  </thead>
                              <tbody>
                               <tr>
                                <td class="t_r lableTd">推进情况：</td>
                                <td>
                                <input type="text" id="progress" name="workProgress.progress" class="input_xxlarge" value="<s:property value="progress" />"/>
                                </td>
                                </tr>
                                
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="4" class="t_r"><input id="submit" type="submit" value="修改"/>&nbsp;
                                <input type="button" value="关 闭" onclick="shut();"/>&nbsp;
                                <input type="reset" value="重 置" />&nbsp;</td>
                              </tr>
                             
                              
                            </table>
             </form>
      </div>
        <!--Table End-->
</div>
</body>
</html>
