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
<title>项目简介</title>
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
        <script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript">
         $(function(){
			$(".odd tr:odd").css("background","#fafafa");			
		});
		function shut(){
		  window.opener=null;
		  window.open("","_self");
		  window.close();
		}
		 
        </script></head>

<body>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img id="show" src="<%=basePath %>/css/default/images/sideBar_arrow_right.jpg" width="46" height="30" title="收起"></div>
            <div class="posi fl">
            	<ul>
            		<li class="fin" id="titleName">项目介绍</li>
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
                                <td class="t_r lableTd" style="width:20%">项目编号</td>
                                
                                <td style="width:30%">
                                
                                <s:property value='#request.go.projectNum'/></td>
                                
                                <td class="t_r lableTd" style="width:20%">项目状态</td>
                                <td style="width:30%">
                                <s:if test="%{#request.go.projectStatus ==1 }">
                                		待建
                                </s:if>
                                <s:if test="%{#request.go.projectStatus ==2 }">
                                		在建
                                </s:if>
                                <s:if test="%{#request.go.projectStatus ==3 }">
                                		竣工
                                </s:if>
                                <s:property value='#request.go.projectStatus'/>
                                
                                </td>
                                
                              <tr>
                                <td class="t_r lableTd">项目名称</td>
                                <td colspan="3">
                                <s:property value="#request.go.projectName"/></td>
                                
                                </tr>
                              <tr>
                                <td class="t_r lableTd">项目简称</td>
                                <td><s:property value="#request.go.projectShortName"/></td>
                                <td class="t_r lableTd">项目造价</td>
                                <td><s:property value="#request.go.projectCost"/></td>
                                </tr>
                              <tr>
                                <td class="t_r lableTd">计划开工时间</td>
                                <td><s:property value="#request.go.planBeginTime"/></td>
                                <td class="t_r lableTd">计划竣工时间</td>
                                <td><s:property value="#request.go.planFinishTime"/></td>
                                </tr>
                                <tr>
                                <td class="t_r lableTd">实际开始日期</td>
                                <td><s:property value="#request.go.realBeginTime"/></td>
                                <td class="t_r lableTd">实际竣工时间</td>
                                <td><s:property value="#request.go.realFinishTime"/></td>
                                </tr>
                                <tr>
                                <td class="t_r lableTd">项目公司</td>
                                <td><s:property value="#request.co.projectCompanyName"/></td>
                                <td class="t_r lableTd">所属线路</td>
                                <td>
                                <s:property value="#request.go.lineId"/></td>
                                </tr>
                                <tr>
                                <td class="t_r lableTd" >项目简介</td>
                                <td colspan="3" style="text-align: left"><s:property value="#request.go.projectProfile" /></td>
                               </tr>
                               
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="4" class="t_r">
                                <input type="button" value="关 闭" onclick="shut();"/></td>
                              </tr>
                             
                              
                            </table>
      </div>
        <!--Table End-->
</div>
</body>
</html>
