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
<title>安全风险管理保存
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
		<script type="text/javascript" src="js/risk.js"></script>
		<script type="text/javascript">
		var addOptions = {
				cache:false,
				type:'post',
				callback:null,
				url:'/portal/project/security/save.action',
			    success:function(data){
			    	
						if(data !=null && data.length > 0){
							alert(data);
							if(data=="添加成功！！！"){
								window.location.href = "/portal/project/sysinfo/project_add.jsp";
							}else if(data=="修改成功！！！"){
								window.location.href = "/portal/project/sysinfo/list.action";
							}
						}else{
							alert("添加失败！");
							window.location.href = "/portal/project/sysinfo/project_add.jsp";
						}
					return false;
			    }
			};
		
		
         $(function(){
        	 $("#submit").click(function(){
        		 	$("#add").ajaxSubmit(addOptions);
        	 });
      
        	 
			$(".odd tr:odd").css("background","#fafafa");	
			$('#trackDate').datepicker({
				//inline: true
				"changeYear":true,
				"showButtonPanel":true,
				"closeText":'清除',
				"currentText":'trackDate'//仅作为“清除”按钮的判断条件
			});
			
			
			$('#discovery').datepicker({
				//inline: true								
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',
				
				"currentText":'discovery'//仅作为“清除”按钮的判断条件						
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
              if($(this).parent("div").children("button:eq(0)").text()=="trackDate") $("#trackDate").val("");
              if($(this).parent("div").children("button:eq(0)").text()=="discovery") $("#discovery").val("");   
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
				$("#planBeginDate").attr("disabled","disabled");
				$("#planBeginDate").css("border-color","white");
				$("#planEndDate").attr("disabled","disabled");
				$("#planEndDate").css("border-color","white");
			}
			
			
			//trackInfo   处置跟踪情况
			var trackInfo=$("[name=trackInfo]").val();
			var tlength=$("#trackInfo").children("option").length;
			for(var i=0;i<tlength;i++){
				if($("#trackInfo").children("option:eq("+i+")").val()==trackInfo)
					$("#trackInfo").children("option:eq("+i+")").attr("selected",true);
			}
			
			//riskLevel    风险等级
			var riskLevel=$("[name=riskLevel]").val();
			var rlength=$("#riskLevel").children("option").length;
			for(var i=0;i<rlength;i++){
				if($("#riskLevel").children("option:eq("+i+")").val()==riskLevel)
					$("#riskLevel").children("option:eq("+i+")").attr("selected",true);
			}
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
            		<li class="fin">安全风险管理</li>
				</ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <!--Filter--><!--Filter End-->
        <!--Table-->
        
        <div class="mb10 pt45">
        <form id="add" method="post" action="/portal/project/risk/save.action" >
        <input type="hidden" id="riskManageId" name="riskManageId" value="<s:property value='risk.riskManageId'/>" />
        
        <table width="100%"  class="table_1">
           <thead>
            <th colspan="4" class="t_r">
       	      &nbsp;</th>
                                  </thead>
                              <tbody>
                               <tr>
                                <td class="t_r lableTd">单位：</td>
                                <td>
                                <input type="text" id="department" name="risk.department" class="input_xxlarge" value="<s:property value='risk.department'/>"/>
                                </td>
                                <td class="t_r lableTd">风险来源：</td>
                                <td>
                                <input type="text" id="riskFrom" name="risk.riskFrom" class="input_xxlarge" value="<s:property value='risk.riskFrom'/>"/>
                                </td>
                                </tr>
                                <tr>
                                <td class="t_r lableTd">发现时间：</td>
                                <td>
                                <input readonly="readonly" type="text" id="discovery" name="risk.discovery" class="input_xxlarge" value="<s:date name='risk.discovery' format='yyyy-MM-dd'/>"/>
                                </td>
                                <td class="t_r lableTd">类别：</td>
                                <td>
                                <input type="text" id="category" name="risk.category" class="input_xxlarge" value="<s:property value='risk.category'/>"/>
                                </td>
                                </tr><tr>
                                <td class="t_r lableTd">风险等级：</td>
                                <td>
                                <select id="riskLevel" name="risk.riskLevel" class="input_xxlarge">
                                	<option value="">--请选择--</option> 
                                	<option value="高">--高--</option> 
                                	<option value="中">--中--</option> 
                                	<option value="低">--低--</option>                                 
                                </select>
                                <input type="hidden" id="riskLevels" name="riskLevel" class="input_xxlarge" value="<s:property value='risk.riskLevel'/>"/>
                                </td>
                                <td class="t_r lableTd">风险处置时限：</td>
                                <td>
                                <input type="text" id="dateLimit" name="risk.dateLimit" class="input_xxlarge" value="<s:property value='risk.dateLimit'/>"/>
                                </td>
                                
                                <tr>
                                <td class="t_r lableTd">跟踪日期：</td>
                                <td>
                                <input readonly="readonly" type="text" id="trackDate" name="risk.trackDate" class="input_xxlarge" value="<s:date name='risk.trackDate' format='yyyy-MM-dd'/>"/>
                                </td>
                                <td class="t_r lableTd">处置跟踪情况：</td>
                                <td colspan="3">
                                	<input type="hidden" id="trackInfos" name="trackInfo" value="<s:property value='risk.trackInfo'/>"/>
                                <select id="trackInfo" name="risk.trackInfo" class="input_xxlarge">
                                	<option value="">--请选择--</option>
                                	<option value="未完成">--未完成--</option>
                                	<option value="已验证">--已验证--</option>
                                </select>
                                </td>
                                </tr>
                               
                                <tr>
                                <td class="t_r lableTd">风险描述：</td>
                                <td colspan="3">
                                <textarea cols="10" rows="5" id="riskInfo" name="risk.riskInfo" value=""><s:property value='risk.riskInfo'/></textarea>
                                </td>
                                </tr>
                                <tr>
                                <td class="t_r lableTd">风险处置方案：</td>
                                <td colspan="3">
                                <textarea cols="10" rows="5" id="plan" name="risk.plan" value=""><s:property value='risk.plan'/></textarea>
                                </td>
                                </tr>
                                <!-- <tr>
                                <td class="t_r lableTd">处置跟踪情况：</td>
                                <td colspan="3">
                                <textarea cols="10" rows="5" id="trackInfo" name="risk.trackInfo" value=""><s:property value='risk.trackInfo'/></textarea>
                                </td>
                                </tr>
                                 -->
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="4" class="t_r"><input id="saves" onclick="return checkout();" type="submit" value="保存"/>&nbsp;
                               <!--  <s:if test="workSecurity==null||workSecurity.confirm==0">
                                <input id="release" type="button" value="发布"/>&nbsp;
                                </s:if> -->
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
