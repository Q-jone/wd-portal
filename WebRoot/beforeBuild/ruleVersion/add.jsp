<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String basePath = request.getContextPath();
String oldDeptId = request.getParameter("oldDeptId");
if(oldDeptId==null){
	oldDeptId = "";
}
String id = request.getParameter("id");
if(id==null){
	id = "";
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>规则版本</title>
<link rel="stylesheet" href="<%=basePath %>/css/formalize.css" />
<link rel="stylesheet" href="<%=basePath %>/css/page.css" />
<link rel="stylesheet" href="<%=basePath %>/css/default/imgs.css" />
<link rel="stylesheet" href="<%=basePath %>/css/reset.css" />
<!--[if IE 6.0]>
           <script src="js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
        <script src="<%=basePath %>/js/html5.js"></script>
        <script src="<%=basePath %>/js/jquery-1.7.1.js"></script>
        <script src="<%=basePath %>/js/jquery.formalize.js"></script>
        <script src="<%=basePath %>/beforeBuild/js/jquery.form.js"></script>
        <script src="<%=basePath %>/beforeBuild/js/comm.js"></script>
        <link type="text/css" href="<%=basePath %>/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="<%=basePath %>/js/flick/jquery-ui-1.8.18.custom.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>/js/flick/jquery.ui.datepicker-zh-CN.js"></script>
		<style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
		</style>	
	
		
		<script type="text/javascript">
	
		
         $(function(){ 
			$(".odd tr:odd").css("background","#fafafa");	

			if("<%=id%>"!=""){
				$("#addButton").val("保  存");
				$("#titleName").html($("#titleName").html().replace("新增","修改"));
			}else{
				showNextVersion();
			}
		});
		
		function shut(){
		  window.opener=null;
		  window.open("","_self");
		  window.close();
		}

		function save(){
			if(check()){
				var formOptions = {
					cache:false,
					type:'post',
					callback:null,
					dataType :'json',
					url:"/portal/ruleVersion/save.action",
				    success:function(data){
						if(data!=null&&data.if_success=="yes"){
							alert("提交成功！");
							if("<%=id%>"!=""){
								window.close();
							}else{
								window.location.href = "toAdd.action?oldDeptId=<%=oldDeptId%>";
							}
						}else{
							alert("提交失败，请联系管理员！");
						}
						return false;
				    }
				};
				$("#form").ajaxSubmit(formOptions);  
			}
		}

		function check(){
			if($("[name=versionNum]").val()==""){
				alert("请填写版本号！");
				$("[name=versionNum]").focus();
				return false;
			}

			if($("[name=versionName]").val()==""){
				alert("请填写版本名称！");
				$("[name=versionName]").focus();
				return false;
			}

			if($("[name=remark]").val().length>2000){
				alert("备注最多输入2000字！");
				return false;
			}

			return true;
		}

		function showNextVersion(){
			$.ajax({
				url: "/portal/ruleVersion/getNextVersionOrder.action",
				type: 'post', 
				dataType: 'json', 
				data:{
					random:Math.random()
				},
				error:function(){
					alert("系统连接失败，请稍后再试！");
				},
				success: function(obj){			
	              if(obj!=null&&obj.length>0){
					  $("[name=versionOrder]").val(obj);
	              }
	              $("[name=versionNum]").val("V"+obj);
	              $("[name=versionName]").val("前期办证[V"+obj+"]");
				}
			});	
		}
        </script>
       </head>

<body>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img id="show" onclick="showHide();" src="<%=basePath %>/css/default/images/sideBar_arrow_right.jpg" width="46" height="30" title="收起"></div>
            <div class="posi fl">
            	<ul>
            		<li class="fin" id="titleName">规则版本新增</li>
                </ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <!--Filter--><!--Filter End-->
        <!--Table-->
        <div class="mb10 pt45">
         <form action="<%=basePath %>/ruleVersion/save.action" id="form" name="form" method="post">
         	<input type="hidden" name="id" value="<%=id %>">
	        <input type="hidden" name="createTime" value="<s:property value='ruleVersion.createTime'/>">
	        <input type="hidden" name="createUser" value="<s:property value='ruleVersion.createUser'/>">
	        <input type="hidden" name="removed" value="0">
	        <input type="hidden" name="oldDeptId" value="<%=oldDeptId %>">
	        <input type="hidden" name="versionOrder" value="<s:property value='ruleVersion.versionOrder'/>">
          <table width="100%"  class="table_1">
           <thead>
            <th colspan="4" class="t_r">
       	      &nbsp;</th>
                                  </thead>
                              <tbody>
                               <tr>
                                <td style="width:20%;" class="t_r lableTd">版本号：</td>
                                <td style="width:30%;">
                                <input type="text" name="versionNum" class="input_xxlarge" maxlength="50" value="<s:property value='ruleVersion.versionNum'/>" style="background-color:#EAEAEA;" readonly="readonly"/>
                                </td>
                                <td style="width:20%;" class="t_r lableTd">版本名称：</td>
                                <td style="width:30%;">
                                <input type="text" name="versionName" class="input_xxlarge" maxlength="50" value="<s:property value='ruleVersion.versionName'/>" style="background-color:#EAEAEA;" readonly="readonly"/>
                                </td>
                                </tr>
                                
                                
                                <tr>
                                <td class="t_r lableTd">备注：</td>
                                <td colspan="3">
                                <textarea cols="10" rows="5" name="remark"><s:property value='ruleVersion.remark'/></textarea>
                                </td>
                                 
                                </tr>
                                
                                
                                
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="4" class="t_r"><input id="addButton" type="button" value="添  加" onclick="save();"/>&nbsp;
                                <input type="button" value="关 闭" onclick="shut();"/></td>
                              </tr>
                             
                              
                            </table>
             </form>
      </div>
        <!--Table End-->
</div>
</body>
</html>
