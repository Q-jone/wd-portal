<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>工程建设进度对比表</title>
<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/default/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
<!--[if IE 6.0]>
           <script src="../js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
        <script src="../js/html5.js"></script>
        <script src="../js/jquery-1.7.1.js"></script>
		<script src="../js/jquery.formalize.js"></script>
		<!--<script src="../js/switchDept.js"></script>-->
		<script src="../js/show.js"></script>
		<link type="text/css" href="../css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="../js/flick/jquery-ui-1.8.18.custom.min.js"></script>
		<script type="text/javascript" src="../js/flick/jquery.ui.datepicker-zh-CN.js"></script>
        <style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
		</style>		
	<script type="text/javascript">
	function showDifferenceAndPersent(){
		var planQuality = 0;
		var realQuality = 0;
		var difference = 0;
		var persent = 0;
		$("[id=dataTr]").each(function(){
			planQuality = parseFloat($.trim($(this).find("#planQuality").text())).toFixed(2);
			realQuality = parseFloat($.trim($(this).find("#realQuality").text())).toFixed(2);
			difference = (planQuality - realQuality).toFixed(2);
			persent = (realQuality*100/planQuality).toFixed(2);
			$(this).find("#difference").html(difference);
			$(this).find("#persent").html(persent);
		});
	}

	$(function(){
		showDifferenceAndPersent();
	});
    </script>



</head>

<body>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
			<div class="fl"><img id="show" onclick="showHide();" src="../css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li class="fin">工程建设进度对比表</li>
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

      
        <!--Filter End-->
        <!--Table-->
         <s:set name="r" value="#request.list"></s:set>  
        <div class="mb10">
        	<table width="100%"  class="table_1"  id="mytable">
                              <thead>
                              <tr class="tit">
                                <td class="t_c" width="5%">序号</td>
                                <td class="t_c" width="10%">年度</td>
                                <td class="t_c" width="25%">项目</td>
                                <td class="t_c" width="20%">类型</td>
                                <td class="t_c" width="10%">年度计划量</td>
                                <td class="t_c" width="10%">实际完成量</td>
                                <td class="t_c" width="10%">差额</td>
                                <td class="t_c" width="10%">完成比(%)</td>
                                </tr>
                                </thead>
                               <tbody> 
                              <s:iterator value="#r" id="items" status="s">
                              <tr id="dataTr">
                              	<td class="t_c"><s:property value="#s.index+1"/></td>
                               	<td class="t_c"><s:property value="#items[5]" /></td>
                               	<td class="t_c"><s:property value="#items[2]" /></td>
                               	<td class="t_c"><s:property value="#items[4]" /></td>
                               	<td class="t_c" id="planQuality"><s:property value="#items[6]" /></td>
                               	<td class="t_c" id="realQuality"><s:property value="#items[7]" /></td>
                               	<td class="t_c" id="difference"></td>
                               	<td class="t_c" id="persent"></td>
                                </tr>
                                </s:iterator>
                              </tbody>
                            </table>

      </div>
      
      
</div>
</div>
</body>
</html>