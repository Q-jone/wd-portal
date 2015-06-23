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
<title>集团信息安全等保测评工作计划安排
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
        <script src="<%=basePath %>project/sysinfo/js/jquery.form.js"></script>
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
		<script type="text/javascript" src="js/security_add.js"></script>
		<script type="text/javascript">
		
		
         $(function(){
        	 
        	 if($("#confirm").val()==1){
 				$("#sysName").attr("readonly","readonly");
 				$("#sysName").css("border-color","white");
 				$("#category").attr("readonly","readonly");
 				$("#category").css("border-color","white");
 				$("#department").attr("readonly","readonly");
 				$("#department").css("border-color","white");
 				$("#important").attr("readonly","readonly");
 				$("#important").css("border-color","white");
 				$("#sysLevel").attr("readonly","readonly");
 				$("#sysLevel").css("border-color","white");
 				$("#excuteQuantity").attr("readonly","readonly");
 				$("#excuteQuantity").css("border-color","white");
 				//dsabled后台取不到值
 				$("#planBeginDate").attr("id","planBeginDateconfirm");
 				$("#planBeginDateconfirm").css("border-color","white");
 				$("#planEndDate").attr("id","planEndDateconfirm");
 				$("#planEndDateconfirm").css("border-color","white");
 			}
        	 
			$(".odd tr:odd").css("background","#fafafa");	
			$('#planBeginDate').datepicker({
				//inline: true
				"changeYear":true,
				"showButtonPanel":true,
				"closeText":'清除',
				"currentText":'planBeginDate'//仅作为“清除”按钮的判断条件
			});
			$('#planEndDate').datepicker({
				//inline: true
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',
				"currentText":'planEndDate'//仅作为“清除”按钮的判断条件						
			});
			
			$('#RealBeginDate').datepicker({
				//inline: true								
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',
				
				"currentText":'RealBeginDate'//仅作为“清除”按钮的判断条件						
			});
			$('#RealEndDate').datepicker({
				//inline: true								
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',
				
				"currentText":'RealEndDate'//仅作为“清除”按钮的判断条件						
			});
			//datepicker的“清除”功能
            $(".ui-datepicker-close").live("click", function (){              
              if($(this).parent("div").children("button:eq(0)").text()=="planBeginDate") $("#planBeginDate").val("");
              if($(this).parent("div").children("button:eq(0)").text()=="planEndDate") $("#planEndDate").val("");
              if($(this).parent("div").children("button:eq(0)").text()=="RealBeginDate") $("#RealBeginDate").val("");   
              if($(this).parent("div").children("button:eq(0)").text()=="RealEndDate") $("#RealEndDate").val("");   
            });
			
			loadShow();
			
			
			$("#release").click(function(){
				if(window.confirm("发布后计划将无法修改，是否继续操作？")){
					$("#confirm").val("1");
					var path="/portal/project/security/confirm.action";
					$("#add").attr("action", path).submit();
					//$("#wform").submit(); 
					//$("#confirm").val(1);
					//$("#wform").submit();
					//return true;
				}else{
					$("#confirm").val("0");
				}
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
            		<li class="fin">集团信息安全等保测评工作计划安排</li>
				</ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <!--Filter--><!--Filter End-->
        <!--Table-->
        
        <div class="mb10 pt45">
        <form id="add" method="post" action="/portal/project/security/save.action" >
        <input  type="hidden" id="workSecurityId" name="workSecurityId" value="<s:property value='workSecurity.workSecurityId'/>"/>
        <input type="hidden" id="confirm" name="workSecurity.confirm" value="<s:property value='workSecurity.confirm'/>"  />
          <table width="100%"  class="table_1">
           <thead>
            <th colspan="4" class="t_r">
       	      &nbsp;</th>
                                  </thead>
                              <tbody>
                               <tr>
                                <td class="t_r lableTd">系统名称：</td>
                                <td>
                                <input type="text" id="sysName" name="workSecurity.sysName" class="input_xxlarge" value="<s:property value='workSecurity.sysName'/>"/>
                                </td>
                                <td class="t_r lableTd">类别：</td>
                                <td>
                                <input type="text" id="category" name="workSecurity.category" class="input_xxlarge" value="<s:property value='workSecurity.category'/>"/>
                                </td>
                                </tr>
                                <tr>
                                <td class="t_r lableTd">归属单位：</td>
                                <td>
                                <input type="text" id="department" name="workSecurity.department" class="input_xxlarge" value="<s:property value='workSecurity.department'/>"/>
                                </td>
                                <td class="t_r lableTd">系统重要性：</td>
                                <td>
                                <input type="text" id="important" name="workSecurity.important" class="input_xxlarge" value="<s:property value='workSecurity.important'/>"/>
                                </td>
                                </tr><tr>
                                <td class="t_r lableTd">系统定级：</td>
                                <td>
                                <input type="text" id="sysLevel" name="workSecurity.sysLevel" class="input_xxlarge" value="<s:property value='workSecurity.sysLevel'/>"/>
                                </td>
                                <td class="t_r lableTd">实施系统数量：</td>
                                <td>
                                <input type="text" id="excuteQuantity" name="workSecurity.excuteQuantity" class="input_xxlarge" value="<s:property value='workSecurity.excuteQuantity'/>"/>
                                </td>
                                
                                <tr>
                                <td class="t_r lableTd">统筹计划开始时间：</td>
                                <td>
                                <input readonly="readonly" type="text" id="planBeginDate" name="workSecurity.planBeginDate" class="input_xxlarge" value="<s:date name='workSecurity.planBeginDate' format='yyyy-MM-dd'/>"/>
                                </td>
                                <td class="t_r lableTd">统筹计划结束时间：</td>
                                <td>
                                <input readonly="readonly" type="text" id="planEndDate" name="workSecurity.planEndDate" class="input_xxlarge" value="<s:date name='workSecurity.planEndDate' format='yyyy-MM-dd'/>"/> 
                                </td>
                                </tr>
                                <tr>
                                <td class="t_r lableTd">实际计划开始时间：</td>
                                <td>
                                <input type="text" id="RealBeginDate" name="workSecurity.RealBeginDate" class="input_xxlarge" value="<s:date name='workSecurity.RealBeginDate' format='yyyy-MM-dd'/>"/>
                                </td>
                                <td class="t_r lableTd">实际计划完成时间：</td>
                                <td>
                                <input readonly="readonly" type="text" id="RealEndDate" name="workSecurity.RealEndDate" class="input_xxlarge" value="<s:date name='workSecurity.RealEndDate' format='yyyy-MM-dd'/>"/> 
                                </td>
                                </tr>
                                <tr>
                                <td class="t_r lableTd">备注：</td>
                                <td colspan="3">
                                <textarea cols="10" rows="5" id="memo" name="workSecurity.memo" value=""><s:property value='workSecurity.memo'/></textarea>
                                </td>
                                </tr>
                                
                               
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="4" class="t_r">
                                <input id="saves" type="submit" onclick="return checkout();" value="保存"/>&nbsp;
                                <s:if test="workSecurity==null||workSecurity.confirm==0">
                                <input id="release" type="button" value="发布"/>&nbsp;
                                </s:if>
                                <input type="button" value="后 退" onclick="history.go(-1);"/>&nbsp;
                                <input type="reset" value="重 置" />&nbsp;</td>
                              </tr>
                             
                            </table>
             </form>
      </div>
        <!--Table End-->
</div>
</body>
</html>
