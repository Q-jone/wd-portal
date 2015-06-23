<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String basePath = request.getContextPath();

%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>单体办证计划</title>
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

			
		});
		
		
        </script>
       </head>

<body>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img id="show" onclick="showHide();" src="<%=basePath %>/css/default/images/sideBar_arrow_right.jpg" width="46" height="30" title="收起"></div>
            <div class="posi fl">
            	<ul>
            		<li class="fin" id="titleName">单体办证计划查看</li>
                </ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <!--Filter--><!--Filter End-->
        <!--Table-->
        <div class="mb10 pt45">
          <table width="100%"  class="table_1">
           <thead>
            <th colspan="4" class="t_r">
       	      &nbsp;</th>
                                  </thead>
                              <tbody>
                              <tr>
                                <td class="t_r lableTd">单体：</td>
                                <td colspan="3">
                                	<s:property value='monomerPlan.monomerName'/>
                                </td>
                                </tr>
                              <tr>
                                 <td style="width:20%;" class="t_r lableTd">计划名称：</td>
                                <td style="width:30%;">
                                <s:property value='monomerPlan.planName'/>
                                </td>
                                <td class="t_r lableTd" style="width:20%;">证件名称：</td>
                                <td style="width:30%;">
                               <s:property value='monomerPlan.paperName'/>
							   <tr>
                                <td class="t_r lableTd">计划开始时间：</td>
                                <td>
                                <s:property value='monomerPlan.planStartTime'/>
                                </td>
                                <td class="t_r lableTd">计划完成时间：</td>
                                <td>
                                <s:property value='monomerPlan.planFinishTime'/>
                                </td>
                                </tr>
								  <tr>
                                <td class="t_r lableTd">经办人：</td>
                                <td>
                                <s:property value='monomerPlan.mainPerson'/>
                                </td>
                                <td class="t_r lableTd">经办人手机：</td>
                                <td>
                                <s:property value='monomerPlan.phone'/>
                                </td>
                                </tr>
								<tr>
                                <td class="t_r lableTd">提前提醒天数：</td>
                                <td colspan="3">
                                <s:property value='monomerPlan.warnDays'/>
                                </td>
                                </tr>
                                <tr>
                                <td class="t_r lableTd">备注：</td>
                                <td colspan="3">
                                <s:property value='monomerPlan.remark'/>
                                </td>
                                 
                                </tr>
	                                <tr>
		                                <td class="t_r lableTd">审核意见：</td>
		                                <td colspan="3">
			                                <s:iterator value="#request.checkInfo" id="data" status="s">
				                                <p>【<s:property value='#data[3]'/>】<s:property value='#data[0]'/></p>
				                                <p>
				                                <s:if test="#data[3]=='作废'">
				                               		操作人：
				                                </s:if>
				                                <s:else>
				                                	 审核人：
				                                </s:else>
				                                <s:property value='#data[1]'/>&nbsp;&nbsp;&nbsp;&nbsp;
				                                <s:if test="#data[3]=='作废'">
				                               		操作时间：
				                                </s:if>
				                                <s:else>
				                                	 审核时间：
				                                </s:else>
				                                <s:property value='#data[2]'/></p>
				                                <br>
			                                </s:iterator>
		                                </td>
	                                </tr>

                              </tbody>
                              <tr class="tfoot">
                                <td colspan="4" class="t_r">&nbsp;</td>
                              </tr>
                             
                              
                            </table>
      </div>
        <!--Table End-->
</div>
</body>
</html>
