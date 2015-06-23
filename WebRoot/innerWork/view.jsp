<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>部门工作查看</title>
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
		
		
         $(function(){
			$(".odd tr:odd").css("background","#fafafa");			
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
         <table width="100%"  class="table_1">
           <thead>
            <th colspan="4" class="t_r">&nbsp;</th>
                                  </thead>
                               <tbody>
                               <tr>
                                <td class="t_r lableTd">督办工作：</td>
                                <td colspan="3">
                                <s:property value='bo.jobName'/>
                                </td>
                                </tr>
                                
                                 <tr>
                                <td class="t_r lableTd">工作要求：</td>
                                <td colspan="3">
                               <pre style="width: 100%; white-space: pre-wrap !important; word-wrap: break-word;"><s:property escape="0" value="bo.jobDemand"/></pre>
								</td>
                                </tr>
                                
                              <tr>
                                <td class="t_r lableTd">开始时间：</td>
                                <td>
                               <s:property value='bo.bTime'/>
                                </td>
                                <td class="t_r lableTd">要求完成时间：</td>
                                <td>
                                <s:property value='bo.pfTime'/>
                                </td>
                                </tr>
                              <tr>
                                <td class="t_r lableTd">最新状态：</td>
                                <td>
                               <s:property value='bo.process'/>
                                </td>
                                <td class="t_r lableTd">进展标志：</td>
                                <td>
                                <s:property value='bo.pFlag'/>
                                </td>
                                </tr>
                                
                                   <tr>
                                <td class="t_r lableTd">延迟原因分类：</td>
                                <td>
                                <s:property value='bo.delayType'/>
                                </td>
                                <td class="t_r lableTd">延迟原因描述：</td>
                                <td>
                                 <s:property value='bo.delayDetail'/>
                                </td>
                                </tr>
                                
                              <tr>
                                <td class="t_r lableTd">完成标志：</td>
                                <td>
                               <s:property value='bo.fFlag'/>
                                </td>
                                <td class="t_r lableTd">完成状态：</td>
                                 <td>
                                <s:property value='bo.status' escape='0'/>
                                </td>
                                </tr>
                              <tr>
                                <td class="t_r lableTd">责任人：</td>
                               <td colspan="3">
                               <s:property value='bo.rPeople'/>
                               </td>
                               </tr>    
                             <tr>
                                <td class="t_r lableTd">分管领导：</td>
                               <td colspan="3">
                               <s:property value='bo.rLeader'/>
                               </td>
                             </tr>                 
                              <tr>
				            	<td class="lableTd t_r">附件内容：</td>
				            	<td colspan="3">
				            	<input type="hidden" name="attach" id='attach' value="<s:property value="bo.attach"/>"/>
				            	<iframe scrolling="auto" height="150" frameborder="1" border="1" marginheight="0" marginwidth="0" width="100%" id="attachFrame" name="attachFrame" src="/portal/attach/loadFileList.action?fileGroup=attach&fileGroupName=attachGroup&fileGroupId=<s:property value="bo.attach"/>&userName=<s:property value="#session.userName"/>&loginName=<s:property value="#session.loginName"/>&procType=view&targetType=frame&type=1"></iframe>
				            	</td>
				            </tr>
                               <tr>
        	      				<td class="t_r lableTd">填报人：</td>
                               <td>
                               <s:property value="bo.operatorName"/>
                               <input type="hidden" id="operator" name="operator" value="<s:property value='#session.loginName'/>"/>
        	      				<input type="hidden" id="operatorName" name="operatorName" value="<s:property value='#session.userName'/>"/>
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
