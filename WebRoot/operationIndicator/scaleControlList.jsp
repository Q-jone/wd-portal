<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>管控查看</title>
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
<script src="../js/jquery.formalize.js"></script>
<script type="text/javascript">
         $(function(){
			$(".odd tr:odd").css("background","#fafafa");			
		});
</script>
</head>

<body>
<div class="main"><!--Ctrl-->
<div class="ctrl clearfix">
<div class="fl"><img src="../css/default/images/sideBar_arrow_right.jpg"
	width="46" height="30" alt="收起"></div>
<div class="posi fl">
<ul>
	<li><a href="#">运营管理</a></li>
   	<li><a href="#">运营指标</a></li>
   	<li class="fin">运营指标管控设置</li>
</ul>
</div>
<div class="fr lit_nav">
<ul>
	<li class="selected"><a class="print" href="#">打印</a></li>
	<li><a class="storage" href="#">网络硬盘</a></li>
	<li><a class="rss" href="#">订阅</a></li>
	<li><a class="chat" href="#">聊天</a></li>
	<li><a class="query" href="#">查询</a></li>
</ul>
</div>
</div>
<div class="pt45">
 <div class="tabs_2 nwarp">
	<ul>
    	<li id="tab1"><a href="showProductionControlPage.action"><span>运营生产指标管控</span></a></li>
		<li id="tab2"><a href="showQualityControlPage.action"><span>运营质量安全指标管控</span></a></li>
		<li id="tab3" class="selected"><a href="#"><span>运营规模指标管控</span></a></li>
    </ul>
</div>

<div class="filter">
	<div class="fn">
		<input type="button"" name="button2" id="button2" value="新增" class="new" onclick="showAddPage();">
	</div>
</div>

<div class="mb10">
	<div class="mb10">
		<table width="100%" class="table_1">
			<thead>
				<th colspan="16"></th>
			</thead>
			<tbody>
			
			<tr class="tit">
				<td>线路</td>
				<td>开行列次</td>
				<td>运营里程&nbsp;(单位：列次)</td>
				<td>客流量&nbsp;(单位：列次)</td>
				<td>换乘人次&nbsp;(单位：列次)</td>
				<td>客运收入&nbsp;(单位：列次)</td>
				<td></td>
				<td colspan="2">操作</td>
			</tr>
			<s:iterator value="#request.pcList" id="pcList" >
				<tr>
					<td id="td_1">
						<s:property value="#pcList.indicatorLine"/>号线
					</td>
					<td id="td_2"><s:property value="#pm.indicatorDate"/></td>
					<td id="td_3"><s:property value="#pm.metroColumnDaily"/></td>
					<td id="td_3"><s:property value="#pm.lastyearMetroProduction.metroColumnDaily"/></td>
					<td id="td_4"><s:property value="#pm.metroColumnMonth"/></td>
					<td id="td_5"><s:property value="#pm.metroColumnYear"/></td>
					<td id="td_5">
						<a href="3">查看</a>
					</td>
					<td id="td_5">
						<a href="3">编辑</a>
					</td>
					<td id="td_5">
						<a href="3">删除</a>
					</td>
					
				</tr>
			</s:iterator>
				
			</tbody>
			<tr class="tfoot">
	      		<td colspan="11">
		      		<div class="clearfix">
		      			<span class="fl">
		      				<s:if test="#request.pcList==null">
		      					无相关数据
		      				</s:if>
		      			</span>
			       	</div>
		       </td>
     		</tr>
		</table>
	</div>
</div>
<!--Table End--></div>
</div>
</body>
</html>
