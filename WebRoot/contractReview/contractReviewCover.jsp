<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>合同后审封面</title>
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
		<script src="js/contextPath.js"></script>
		<script src="js/processComm.js"></script>
		
		<link type="text/css" href="../css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="../js/flick/jquery-ui-1.8.18.custom.min.js"></script>
		<script type="text/javascript" src="../js/flick/jquery.ui.datepicker-zh-CN.js"></script>
        <style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
		</style>		
	<script type="text/javascript">
    </script>



</head>

<body>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
			<div class="fl"><img id="show" onclick="showHide();" src="../css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li class="fin">合同后审</li>
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
      <div class="tabs_2">
        	<ul>

	           		<li <s:if test="#request.type==1">class="selected"</s:if>><a href="/portal/contractReview/findContractReviewByType.action?type=1"><span>建设类合同备案情况</span></a></li>
	           		<li <s:if test="#request.type==2">class="selected"</s:if>><a href="/portal/contractReview/findContractReviewByType.action?type=2"><span>运维类合同备案情况</span></a></li>


           		<input type="button" onclick="location.href='/portal/contractReview/list.action'" value="详情" style="float:right;margin-right:20px;"/>
           		
            </ul>
        </div>


      
        <!--Table-->
         <s:set name="r" value="#request.result"></s:set>  
        <div class="mb10">
        	<table width="100%"  class="table_1">
               <tbody>
               <tr class="tit">
                   <br/>
                 <!-- <td><input type="checkbox" id="test_checkbox_1" name="test_checkbox_1" /></td>-->
                 <td class="t_c" width="25%">负责单位</td>
                 <s:if test="#request.type==1">
                 	<td class="t_c" width="20%">线路</td>
                 </s:if>
                 <td class="t_c" width="10%">进行中(个数)</td>
                 <td class="t_c" width="10%">已完成(个数)</td>
                 <td class="t_c" width="12%">其中:批量入库(个数)</td>
                 <td class="t_c" width="10%">逐一审核(个数)</td>
                 <td class="t_c" width="10%">合计(个数)</td>
                 <td class="t_c" width="10%">退回个数</td>
                 </tr>
               <s:iterator value="#r" id="items" status="s">
                   <s:if test="#items.name != null && #items.name != ''">
                <tr>
                	<td class="t_l"><s:property value="#items.name"/></td>
	                 <s:if test="#request.type==1">
	                  	<td class="t_l"><s:property value="#items['line']"/></td>
	                 </s:if>
                  	<td class="t_c"><s:property value="#items['working']"/></td>
                	<td class="t_c"><s:property value="#items['completed']"/></td>
                 	<td class="t_c"><s:property value="#items['direct']" /></td>
                 	<td class="t_c"><s:property value="#items['step']" /></td>
                  <td class="t_c"><s:property value="#items['total']"/></td>
                  <td class="t_c"><s:property value="#items['return']"/></td>
                </tr>
                   </s:if>
                 </s:iterator>
               </tbody>
             </table>

      </div>
        <!--Table End-->
</div>
</div>
</body>
</html>