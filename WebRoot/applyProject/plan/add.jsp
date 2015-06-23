<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>计划添加</title>
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
        <script src="../../js/html5.js"></script>
        <script src="../js/jquery-1.10.2.min.js"></script>
        <script src="../js/jquery-migrate-1.2.1.min.js"></script>
        <script src="../../js/show.js"></script>
        <script src="../js/jquery.blockUI.js"></script>
        <script src="../js/jquery.form.js"></script>
		<script src="../../js/jquery.formalize.js"></script>
		<link type="text/css" href="../css/flick/jquery-ui-1.10.3.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="../js/jquery-ui-1.10.3.custom.min.js"></script>
		<script type="text/javascript" src="../../js/flick/jquery.ui.datepicker-zh-CN.js"></script>
       
		<script type="text/javascript">
		var addOptions = {
				cache:false,
				type:'post',
				callback:null,
				url:'/portal/applyProject/planAdd.action',
			    success:function(data){
						if(data !=null && data.length > 0){
							$.unblockUI();
							alert("添加成功");
							window.location.href = "/portal/applyProject/plan/add.jsp";
						}else{
							$.unblockUI();
							alert("添加失败！");
							window.location.href = "/portal/applyProject/plan/add.jsp";
						}
					return false;
			    }
			};
		
		
         $(function(){
        	 $("#submit").click(function(){
        		 $("#add").ajaxSubmit(addOptions);
        	 })
        	 
			$(".odd tr:odd").css("background","#fafafa");	
				
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
        	<div class="fl"><img id="show" onclick="showHide();" src="../../css/default/images/sideBar_arrow_right.jpg" width="46" height="30" title="收起"></div>
            <div class="posi fl">
            	<ul>
            		<li class="fin">计划添加</li>
                </ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <!--Filter--><!--Filter End-->
        <!--Table-->
        <div class="mb10 pt45">
        <form id="add">
          <table width="100%"  class="table_1">
           <thead>
            <th colspan="4" class="t_r">
       	      &nbsp;</th>
           </thead>
           <tbody>
            <tr>
             <td class="t_r lableTd">计划名称：</td>
             <td colspan="3">
             <input type="text" id="planName" name="planName" class="input_xxlarge"/>
             </td>
             </tr>
            
            <tr>
			<td class="lableTd t_r">计划附件：</td>
			<td colspan="3">
			<input type="hidden" name="planAttach" id='planAttach'/>
			<iframe scrolling="auto" height="150" frameborder="1" border="1" marginheight="0" marginwidth="0" width="100%" id="attachFrame" name="attachFrame" src="/portal/attach/loadFileList.action?fileGroup=planAttach&fileGroupName=planAttachGroup&fileGroupId=&userName=<s:property value="#session.userName"/>&loginName=<s:property value="#session.loginName"/>&procType=edit&targetType=frame&type=1"></iframe>
				</td>
			</tr>
             
               <tr>
			<td class="t_r lableTd">填报人：</td>
               <td>
               <s:property value="#session.userName"/>
            <input type="hidden" id="operatePerson" name="operatePerson" value="<s:property value='#session.userName'/>"/>
			<input type="hidden" id="type" name="type" value="1"/>
			
			</td>
			<td>
			</td>
          </tr>
          
           </tbody>
           <tr class="tfoot">
             <td colspan="4" class="t_r"><input id="submit" type="button" value="添 加"/>&nbsp;
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
