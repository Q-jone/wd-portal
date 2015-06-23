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
<title>知识库</title>
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

			findAllPapers();
			if("<%=id%>"!=""){
				$("[name=type]").val($("#h_type").val());
				$("#addButton").val("保  存");
				$("#titleName").html($("#titleName").html().replace("新增","修改"));
			}
		});
		
		function shut(){
		  window.opener=null;
		  window.open("","_self");
		  window.close();
		}
		
		function findAllPapers(){
			$.ajax({
				url: "/portal/linePlan/findAllPapers.action",
				type: 'post', 
				dataType: 'json', 
				error:function(){
					alert("系统连接失败，请稍后再试！");
				},
				success: function(obj){			
	              if(obj!=null&&obj.length>0){
					  var addHtml = "<option value=''>---请选择---</option>";
					  for(var i=0;i<obj.length;i++){
						  addHtml += "<option value='"+obj[i][1]+"' paperId='"+obj[i][0]+"'>"+obj[i][1]+"</option>";
					  }
					  $("[name=paperName]").html(addHtml);
					  if("<%=id%>"!=""){
							$("[name=paperName]").val($("#h_paperName").val());
						}
	              }
				}
			});	
		}

		function selectPaper(obj){
			var option = $(obj).children("option:selected");
			$("[name=paperId]").val(option.attr("paperId"));
		}

		function check(){
			if($("[name=paperName]").val()==""){
				alert("请选择证件！");
				$("[name=paperName]").focus();
				return false;
			}

			if($("[name=type]").val()==""){
				alert("请选择知识类型！");
				$("[name=type]").focus();
				return false;
			}
			return true;
		}

		function save(){
			if(check()){
				var formOptions = {
					cache:false,
					type:'post',
					callback:null,
					dataType :'json',
					url:"/portal/knowledge/save.action",
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
        </script>
       </head>

<body>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img id="show" onclick="showHide();" src="<%=basePath %>/css/default/images/sideBar_arrow_right.jpg" width="46" height="30" title="收起"></div>
            <div class="posi fl">
            	<ul>
            		<li class="fin" id="titleName">知识库新增</li>
                </ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <!--Filter--><!--Filter End-->
        <!--Table-->
        <div class="mb10 pt45">
        <form action="<%=basePath %>/knowledge/save.action" id="form" name="form" method="post">
          <input type="hidden" name="id" value="<%=id %>">
	        <input type="hidden" name="createTime" value="<s:property value='knowledge.createTime'/>">
	        <input type="hidden" name="createUser" value="<s:property value='knowledge.createUser'/>">
	        <input type="hidden" name="oldDeptId" value="<%=oldDeptId %>">
	        <input type="hidden" name="removed" value="0">
          <table width="100%"  class="table_1">
           <thead>
            <th colspan="4" class="t_r">
       	      &nbsp;</th>
                                  </thead>
                              <tbody>
                               <tr>
                                <td style="width:20%;" class="t_r lableTd">证件名称：</td>
                                <td style="width:30%;">
                                <select name="paperName" onchange="selectPaper(this);"></select>
                                <span style="color:red;display:inline;">&nbsp;*</span>
                               <input type="hidden" name="paperId" value="<s:property value='knowledge.paperId'/>">
                               <input type="hidden" id="h_paperName" value="<s:property value='knowledge.paperName'/>">
                                </td>
                                <td style="width:20%;" class="t_r lableTd">知识类型：</td>
                                <td style="width:30%;">
                                <select name="type">
                                	<option value="">---请选择---</option>
                                	<option value="1">办证指导</option>
                                	<option value="2">填报表格</option>
                                	<option value="3">注意事项</option>
									<option value="4">规章制度</option>
                                </select>
                                <span style="color:red;display:inline;">&nbsp;*</span>
                                <input type="hidden" id="h_type" value="<s:property value='knowledge.type'/>">
                                </td>
                                </tr>
                                
                                
                                <tr>
	                                <td class="t_r lableTd">附件：</td>
	                                <td colspan="3">
		                                <input type="hidden" name="attach" id="attach" value="<s:property value='knowledge.attach'/>"/>
		        						<iframe scrolling="auto" height="150" frameborder="1" border="1" marginheight="0" marginwidth="0" width="100%" id="attachFrame" name="attachFrame" src="/portal/attach/loadFileList.action?fileGroup=attach&fileGroupName=attachGroup&fileGroupId=<s:property value='knowledge.attach'/>&userName=<s:property value="#session.userName"/>&loginName=<s:property value="#session.loginName"/>&procType=edit&targetType=frame"></iframe>
	                                </td>
                                </tr>
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="4" class="t_r"><input id="addButton" type="button" value="添  加" onclick="save();" title="将数据添加到数据库，并可继续添加新信息"/>&nbsp;
                                <input type="button" value="关 闭" onclick="shut();"/></td>
                              </tr>
                             
                              
                            </table>
             </form>
      </div>
        <!--Table End-->
</div>
</body>
</html>
