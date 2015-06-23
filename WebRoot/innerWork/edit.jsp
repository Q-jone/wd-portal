<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>部门工作修改</title>
<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/default/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
<!--[if IE 6.0]>
           <script src="js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
        <script src="../js/html5.js"></script>
        <script src="../js/jquery-1.7.1.js"></script>
        <script src="js/jquery.form.js"></script>
		<script src="../js/jquery.formalize.js"></script>
		<!--<script src="../js/switchDept.js"></script>-->
		<script src="../js/show.js"></script>
		<script src="../js/loading.js"></script>
		<link type="text/css" href="../css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="../js/flick/jquery-ui-1.8.18.custom.min.js"></script>
		<script type="text/javascript" src="../js/flick/jquery.ui.datepicker-zh-CN.js"></script>
        <style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
		</style>
		
		<script type="text/javascript">
		var updateOptions = {
				cache:false,
				type:'post',
				callback:null,
				url:'/portal/innerWork/update.action',
				success:function(data){
					if(data !=null && data.length > 0){
						alert("修改成功");
						window.location.href = window.location.href+"&rand="+Math.random();
					}else{
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
        	 $('#pfTime').datepicker({
 				//inline: true								
 				"changeYear":true,
 				"showButtonPanel":true,	
 				"closeText":'清除',	
 				"currentText":'pfTime'//仅作为“清除”按钮的判断条件						
 			});
        	 $('#bTime').datepicker({
  				//inline: true								
  				"changeYear":true,
  				"showButtonPanel":true,	
  				"closeText":'清除',	
  				"currentText":'bTime'//仅作为“清除”按钮的判断条件						
  			});
 			//datepicker的“清除”功能
             $(".ui-datepicker-close").live("click", function (){              
               if($(this).parent("div").children("button:eq(0)").text()=="pfTime") $("#pfTime").val("");
               if($(this).parent("div").children("button:eq(0)").text()=="bTime") $("#bTime").val("");
                              
             });
			
 			$("#status").val("<s:property value='bo.status' escape='0'/>");
 			$("#pFlag").val("<s:property value='bo.pFlag' escape='0'/>");
			loadShow();		
		});
		
		function shut(){
		  window.opener=null;
		  window.open("","_self");
		  window.close();
		}
		
		function check(){
		
		
		showLoading();
		}
		
		
        </script>
       </head>

<body>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img id="show" onclick="showHide();" src="../css/default/images/sideBar_arrow_right.jpg" width="46" height="30" title="收起"></div>
            <div class="posi fl">
            	<ul>
            		<li class="fin">部门工作修改</li>
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
                                <td class="t_r lableTd">督办工作：</td>
                                <td colspan="3">
                                <input value="<s:property value='bo.jobName'/>" type="text" id="jobName" name="jobName" class="input_xxlarge"/>
                                </td>
                                </tr>
                                
                                 <tr>
                                <td class="t_r lableTd">工作要求：</td>
                                <td colspan="3">
                                <textarea cols="10" rows="5" id="jobDemand" name="jobDemand"><s:property value="bo.jobDemand" escape="0"/></textarea>
                                </td>
                                </tr>
                                
                                
                              <tr>
                                <td class="t_r lableTd">开始时间：</td>
                                <td>
                                <input value="<s:property value='bo.bTime'/>" readonly="readonly" type="date" id="bTime" name="bTime"/>
                                </td>
                                <td class="t_r lableTd">要求完成时间：</td>
                                <td>
                                <input value="<s:property value='bo.pfTime'/>" readonly="readonly" type="date" id="pfTime" name="pfTime"/>
                                </td>
                                </tr>
                             
                              <tr>
                                <td class="t_r lableTd">最新状态：</td>
                                <td>
                                <input value="<s:property value='bo.process'/>" type="text" id="process" name="process" class="input_xxlarge"/>
                                </td>
                                <td class="t_r lableTd">进展标志：</td>
                                <td>
                                <select name="pFlag" id="pFlag" class="input_large" > 
                                	<option value="正常">正常</option>
                                	<option value="延迟">延迟</option>
                                </select></td>
                                </tr>
                                
                                 <tr>
                                <td class="t_r lableTd">延迟原因分类：</td>
                                <td>
                                <input value="<s:property value='bo.delayType'/>" type="text" id="delayType" name="delayType" class="input_xxlarge"/>
                                </td>
                                <td class="t_r lableTd">延迟原因描述：</td>
                                <td>
                                 <input value="<s:property value='bo.delayDetail'/>" type="text" id="delayDetail" name="delayDetail" class="input_xxlarge"/>
                                </td>
                                </tr>
                                
                                
                              <tr>
                                <td class="t_r lableTd">完成标志：</td>
                                <td>
                               <input value="<s:property value='bo.fFlag'/>" type="text" id="fFlag" name="fFlag" class="input_xxlarge"/>
                                </td>
                                <td class="t_r lableTd">完成状态：</td>
                                 <td>
                                <select name="status" id="status" class="input_large" > 
                                	<option value="进行中">进行中</option>
                                	<option value="未完成">未完成</option>
                                	<option value="已完成">已完成</option>
                                </select>
                                </td>
                                </tr>
                              <tr>
                                <td class="t_r lableTd">责任人：</td>
                               <td colspan="3">
                               <input value="<s:property value='bo.rPeople'/>" type="text" id="rPeople" name="rPeople" class="input_xxlarge"/>
                               </td>
                               </tr>    
                             <tr>
                                <td class="t_r lableTd">分管领导：</td>
                               <td colspan="3">
                               <input value="<s:property value='bo.rLeader'/>" type="text" id="rLeader" name="rLeader" class="input_xxlarge"/>
                               </td>
                             </tr>  
                                            
                             <tr>
				            	<td class="lableTd t_r">附件内容：</td>
				            	<td colspan="3">
				            	<input type="hidden" name="attach" id='attach' value="<s:property value="bo.attach"/>"/>
				            	<iframe scrolling="auto" height="150" frameborder="1" border="1" marginheight="0" marginwidth="0" width="100%" id="attachFrame" name="attachFrame" src="/portal/attach/loadFileList.action?fileGroup=attach&fileGroupName=attachGroup&fileGroupId=<s:property value="bo.attach"/>&userName=<s:property value="#session.userName"/>&loginName=<s:property value="#session.loginName"/>&procType=edit&targetType=frame&type=1"></iframe>
				            	</td>
				            </tr>
                               <tr>
        	      				<td class="t_r lableTd">填报人：</td>
                               <td>
                               <s:property value="bo.operatorName"/>
                               <input type="hidden" id="operator" name="operator" value="<s:property value='bo.operator'/>"/>
        	      				<input type="hidden" id="operatorName" name="operatorName" value="<s:property value='bo.operatorName'/>"/>
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
