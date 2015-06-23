<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String basePath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>总体计划展示</title>
<link rel="stylesheet" href="<%=basePath %>/css/formalize.css" />
<link rel="stylesheet" href="<%=basePath %>/css/page.css" />
<link rel="stylesheet" href="<%=basePath %>/css/default/imgs.css" />
<link rel="stylesheet" href="<%=basePath %>/css/reset.css" />
<!--[if IE 6.0]>
           <script src="../js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
        <script src="<%=basePath %>/js/html5.js"></script>
        <script src="<%=basePath %>/js/jquery-1.7.1.js"></script>
		<script src="<%=basePath %>/js/jquery.formalize.js"></script>
		<link type="text/css" href="<%=basePath %>/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="<%=basePath %>/js/flick/jquery-ui-1.8.18.custom.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>/js/flick/jquery.ui.datepicker-zh-CN.js"></script>
        <style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
		</style>	

	<script type="text/javascript">
		
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
			<div class="fl"><img id="show" src="<%=basePath %>/css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li class="fin">总体计划展示</li>
                </ul>
            </div>
            <div style="display:none;" class="fr lit_nav nwarp">
            	<ul>
                    <li class="selected"><a class="print" href="#">打印</a></li>
                    <li><a class="express" href="#">导出数据</a></li>
                    <li class="selected"><a class="table" href="#">表格模式</a></li>
                    <li><a class="treeOpen" href="#">打开树</a></li>
                    <li><a class="filterClose" href="#">关闭过滤</a></li>
                </ul>
            </div>
   		</div>
        <!--Ctrl End-->
      <div class="pt45">
        <!--Filter-->
      <div class="filter">
        	    
		      <div class="fn clearfix">
		                  <h5 class="fl"><a href="#" class="colSelect fl">总体计划展示</a></h5>
		            </div>
		      </div>


      
        <!--Filter End-->
        <!--Table-->
        <s:set name="r" value="#request.pageResultSet"></s:set>  
        <div class="mb10" id="listDiv">
        	<table width="100%"  class="table_1" id="mytable">
        				<thead>
                              <tr class="tit">
                                <!-- <td><input type="checkbox" id="test_checkbox_1" name="test_checkbox_1" /></td>-->
                                <th class="t_c">序号</th>
                                <th class="t_c">任务名称</th>
                                 <th class="t_c">计划开始时间</th>
                                <th class="t_c">计划完成时间</th>
                                <th class="t_c">实际开始时间</th>
                                <th class="t_c">实际完成时间</th>
                                
                                </tr>
                               
                              
                              </thead>
                              <tbody>
	                             <s:iterator value="#request.list" id="data" status="s">
	                             <tr>
	                             	<td class="t_c"><s:property value="#s.index+1" /></td>
	                             	<td class="t_c"><s:property value="#data[0]" /></td>
	                             	<td class="t_c"><s:property value="#data[1]" /></td>
	                             	<td class="t_c"><s:property value="#data[2]" /></td>
	                             	<td class="t_c"><s:property value="#data[3]" /></td>
	                             	<td class="t_c"><s:property value="#data[4]" /></td>
	                             	
	                             </tr>
	                             </s:iterator>
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="6" class="t_r">
                                <input type="button" value="关 闭" onclick="shut();"/></td>
                              </tr>
                             </table>
		</div>
        <!--Table End-->
</div>
</div>
</body>
</html>