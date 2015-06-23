<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>我的通知-草稿箱</title>
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
<script src="../js/jquery-1.7.1.js"></script>
<script src="../js/html5.js"></script>
<script src="../js/jquery.formalize.js"></script>
<script src="../js/show.js"></script>
<script src="../js/loading.js"></script>
<script src="js/common.js"></script>
<link type="text/css" href="../css/flick/jquery-ui-1.8.18.custom.css"
	rel="stylesheet" />
<script src="../js/flick/jquery-ui-1.8.18.custom.min.js"></script>
<script src="../js/flick/jquery.ui.datepicker-zh-CN.js"></script>
<style type="text/css">
/*demo page css*/
.demoHeaders {
	margin-top: 2em;
}

#dialog_link {
	padding: .4em 1em .4em 20px;
	text-decoration: none;
	position: relative;
}

#dialog_link span.ui-icon {
	margin: 0 5px 0 0;
	position: absolute;
	left: .2em;
	top: 50%;
	margin-top: -8px;
}

ul#icons {
	margin: 0;
	padding: 0;
}

ul#icons li {
	margin: 2px;
	position: relative;
	padding: 4px 0;
	cursor: pointer;
	float: left;
	list-style: none;
}

ul#icons span.ui-icon {
	float: left;
	margin: 0 4px;
}

.ui-datepicker-title span {
	display: inline;
}
</style>
<script type="text/javascript">
		$(document).ready( function() {
			$("#add").click(function(){
				window.location.href = "msgUpdate.jsp";
			})	
			
			$("#delIds").click(function(){
				$("input[type=checkBox][name=delId]").each(function(i,n){
					$(n).attr("checked",!$(n).attr("checked"));
				})
			})
			
			$("#rbox").click(function(){	
				window.location.href = "msgReceiveBox.action";
			})
			$("#sbox").click(function(){
				window.location.href = "msgSendBox.action";
			})
			$("#dbox").click(function(){
				window.location.href = "msgDraftBox.action";
			})
			
			$(".t_c a").css("display", "inline");
			var $tbInfo = $(".filter .query input:text");
			$tbInfo.each( function() {
				$(this).focus( function() {
					$(this).attr("placeholder", "");
				});
			});
		
			var $tblAlterRow = $(".table_1 tbody tr:even");
			if ($tblAlterRow.length > 0)
				$tblAlterRow.css("background", "#fafafa");
			loadShow();
			
	});
	
	function clearInput(){
		$("#title").val("");
		$("#rname").val("");
	}
	
		</script>
</head>

<body>
<div class="main"><!--Ctrl-->
<div class="ctrl clearfix">
<div class="fl"><img id="show" onclick="showHide();"
	src="../css/default/images/sideBar_arrow_right.jpg" width="46"
	height="30" alt="收起"></div>
<div class="posi fl">
<ul>
	<li><a href="msgReceiveBox.action" target="_self">我的通知</a></li>
	<li class="fin">草稿箱</li>
</ul>
</div>
<div style="display: none;" class="fr lit_nav nwarp">
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
<div class="pt45"><!--Tabs_2-->
<!--Tabs_2 End-->
<div class="tabs_2 nwarp">
<ul id="msgTab" class="nwarp">
	<li id="rbox"><a href="javascript:void(0);"><span>收件箱</span></a></li>
	<li id="sbox"><a href="javascript:void(0);"><span>发件箱</span></a></li>
	<li class="selected"  id="dbox"><a href="javascript:void(0);"><span>草稿箱</span></a></li>
</ul>
</div>
 <!--Filter-->
<div class="filter">
<div class="query">
<div class="p8 filter_search"><s:form action="msgDraftBox"
	method="post" namespace="/userMsg">
	<input type="hidden" name="pagesize" id="pagesize" value="10" />
	<input type="hidden" name="sid" id="sid" value="<s:property value="#session.userId"/>" />
	<input type="hidden" name="page" id="page"
		value="<s:property value="pageResultSet.pageInfo.currentPage"/>" />
	<input type="hidden" name="sendState" id="sendState"
		value="0" />
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td colspan="2" class="t_r">主题：</td>
			<td colspan="2"><input value="<s:property value="msgBo.title"/>"
				type="text" id="title" name="title" class="input_xxlarge" /></td>
		</tr>
		
		<tr>
			<td colspan="4" class="t_c"><input type="submit" value="检 索" />&nbsp;&nbsp;
			<input type="button" value="重 置" onclick="clearInput()" /></td>
		</tr>
	</table>
</s:form></div>
</div>

<div class="fn clearfix">
<h5 class="fl"><a href="#" class="colSelect fl">草稿箱</a></h5>&nbsp;
<!-- <input type="button" onclick="delBatch('receive');" value="批量删除">-->
<!-- <input type="button" onclick="window.location.href='msgLitterBox.action';" value="垃圾箱" class="fr mr5">
<input type="button" onclick="window.location.href='msgDraftBox.action';" value="草稿箱" class="fr mr5">
<input type="button" onclick="window.location.href='msgSendBox.action';" value="发件箱" class="fr mr5">
<input type="button" onclick="window.location.href='msgReceiveBoxUnread.action';" value="收件箱" class="fr mr5">-->
<input type="button" onclick="delBatch('send');" value="批量删除" class="fr mr5">
<input type="button" name="add" id="add" value="新  建" class="fr mr5">
</div>
</div>

<!--Filter End--> <!--Table-->
<div class="mb10">
<table width="100%" class="table_1">
	<tbody id="pageTable">
		<tr class="tit">
			<td class="t_c"><input type="checkbox" id="delIds" name="delIds" /></td>
			<!--<td class="t_c">状态</td> -->
			<td class="t_c">主题</td>
			<td class="t_c">操作</td>
		</tr>
		<s:iterator value="pageResultSet.list" id="msg">
			<tr>
				<!--<td class="t_c">状态</td> -->
				<td class="t_c"><input type="checkbox" id="delId" name="delId" value="<s:property value='#msg.id'/>"/></td>
				<td class="t_c"><s:property value="#msg.title" /></td>
				<td class="t_c"><a class="mr5" href="msgUpdate.action?msgId=<s:property value="#msg.id"/>" target="_self">查看</a></td>
			</tr>
		</s:iterator>
	</tbody>
	<tr class="tfoot">
		<td style="border-top: #5a84a7 2px solid;" colspan="3">
		<div class="pager mb10 clearfix"><span class="fl">记录总数：<s:property
			value="pageResultSet.pageInfo.totalRow" /></span>
		<div class="fr mr5"><input
			value="<s:property value="pageResultSet.pageInfo.currentPage"/>"
			type="number" id="redirectPage" name="redirectPage"
			class="input_tiny" />/<s:property
			value="pageResultSet.pageInfo.totalPage" /> <input
			onclick="javascript:goPageRedirect();" type="button" value="Go" /></div>
		<ul class="fr clearfix mr5">
			<li><a
				href="javascript:goPage('<s:property value="pageResultSet.pageInfo.totalPage"/>');">尾页</a></li>
			<li><a
				href="javascript:goPage('<s:property value="pageResultSet.pageInfo.currentPage+1"/>');">下一页</a></li>
			<li><a
				href="javascript:goPage('<s:property value="pageResultSet.pageInfo.currentPage-1"/>');">上一页</a></li>
			<li><a href="javascript:goPage('1');">首页</a></li>
		</ul>
		</div>
		</td>
	</tr>
</table>

</div>
<!--Table End--></div>
</div>
</body>
</html>