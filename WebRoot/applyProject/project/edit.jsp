<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>项目编辑</title>
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
		var updateOptions = {
				cache:false,
				type:'post',
				callback:null,
				url:'/portal/applyProject/projectUpdate.action',
				success:function(data){
					if(data !=null && data.length > 0){
						$.unblockUI();
						alert("修改成功");
						window.location.href = window.location.href+"&rand="+Math.random();
					}else{
						$.unblockUI();
						alert("修改失败！");
					}
				return false;
		    }
			};
		
		
         $(function(){
        	 $("#submit").click(function(){
        		 $("#update").ajaxSubmit(updateOptions);
        	 })    	 
			$(".odd tr:odd").css("background","#fafafa");
        	 
        	 $("#projectStatus").val("<s:property value='bo.projectStatus' escape='0'/>");
			});
		
		function shut(){
		  window.opener=null;
		  window.open("","_self");
		  window.close();
		}
		
		function check(){
			$.blockUI({ message: $('#domMessage') }); 
		}
		
		
        </script>
       </head>

<body>
<div id="domMessage" style="display:none;"> 
    <h1>请稍后</h1> 
</div> 
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img id="show" onclick="showHide();" src="<%=request.getContextPath() %>/css/default/images/sideBar_arrow_right.jpg" width="46" height="30" title="收起"></div>
            <div class="posi fl">
            	<ul>
            		<li class="fin">项目修改</li>
                </ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <!--Filter--><!--Filter End-->
        <!--Table-->
        <div class="mb10 pt45">
        <form id="update">
        <input type="hidden" name="id" value='<s:property value='bo.id'/>'>
          <table width="100%"  class="table_1">
           <thead>
            <th colspan="4" class="t_r">&nbsp;</th>
                 </thead>
              <tbody>
              <tr>
             <td class="t_r lableTd">项目名称：</td>
             <td colspan="3">
             <input value="<s:property value='bo.projectName'/>" type="text" id="projectName" name="projectName" class="input_xxlarge"/>
             </td>
             </tr>
              <tr>
             <td class="t_r lableTd">当前状态：</td>
             <td colspan="3">
              <select name="projectStatus" id="projectStatus" class="input_large" > 
                 	<option value="初审">初审</option>
                 	<option value="会审">会审</option>
                 	<option value="领导汇报">领导汇报</option>
              </select>
             </td>
             </tr>
             <tr>
             <td class="t_r lableTd">已有成果：</td>
             <td colspan="3">
             <textarea id="projectAchievement" name="projectAchievement" rows="10"><s:property value="bo.projectAchievement" escape="0"/></textarea>
             </td>
             </tr>  
            <tr>
				<td class="lableTd t_r">已有成果（附件）：</td>
				<td colspan="3">
				<input value="<s:property value='bo.projectAchievementAttach'/>"  type="hidden" name="projectAchievementAttach" id='projectAchievementAttach'/>
				<iframe scrolling="auto" height="150" frameborder="1" border="1" marginheight="0" marginwidth="0" width="100%" id="attachFrame" name="attachFrame" src="/portal/attach/loadFileList.action?fileGroup=projectAchievementAttach&fileGroupName=projectAchievementAttachGroup&fileGroupId=<s:property value="bo.projectAchievementAttach"/>&userName=<s:property value="#session.userName"/>&loginName=<s:property value="#session.loginName"/>&procType=edit&targetType=frame&type=1"></iframe>
				</td>
			</tr>
             <tr>
             <td class="t_r lableTd">当前进展：</td>
             <td colspan="3">
             <textarea id="projectProgress" name="projectProgress" rows="10"><s:property value="bo.projectProgress" escape="0"/></textarea>
             </td>
             </tr>  
             
              <tr>
             <td class="t_r lableTd">备注：</td>
             <td colspan="3">
             <textarea id="projectRemark" name="projectRemark" rows="10"><s:property value="bo.projectRemark" escape="0"/></textarea>
             </td>
             </tr> 
             
              <tr>
			<td class="t_r lableTd">填报人：</td>
              <td>
              <s:property value="bo.operatePerson"/>
              <input type="hidden" id="operatePerson" name="operatePerson" value="<s:property value='#session.userName'/>"/>
				<input type="hidden" id="type" name="type" value="<s:property value='bo.type'/>"/>
				</td>
				<td>
				</td>
            </tr>
            
             </tbody>
             <tr class="tfoot">
               <td colspan="4" class="t_r"><input type="button" value="修 改" id="submit"/>&nbsp;
               <input type="button" value="关 闭" onclick="shut();"/>&nbsp;
              </tr>
            
                              
                            </table>
             </form>
      </div>
        <!--Table End-->
</div>
</body>
</html>
