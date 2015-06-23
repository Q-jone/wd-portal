<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div id="receiptZone" title="发文回执" style="font-size:15px;display:none;">
<input type="text" style="width:0px;height:0px;"/>
	<table style="border-collapse:collapse;" id="titleTable" width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td style="border:1px solid black;font-weight:bold;" width="10%">发文编号</td>
			<td style="border:1px solid black;font-weight:bold;" id="title_sendid"></td>
		</tr>
		<tr>
			<td style="border:1px solid black;font-weight:bold;" width="10%">发文日期</td>
			<td style="border:1px solid black;font-weight:bold;" id="title_senddate"></td>
		</tr>
		<tr>
			<td style="border:1px solid black;font-weight:bold;" width="10%">标题</td>
			<td style="border:1px solid black;font-weight:bold;" id="title_title"></td>
		</tr>
	</table>
	<br>
	
	<table style="border-collapse:collapse;" id="receiptTable" width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td style="border:1px solid black;font-weight:bold;" width="10%">分发号</td>
			<td style="border:1px solid black;font-weight:bold;" width="20%">签收部门</td>
			<td style="border:1px solid black;font-weight:bold;" width="10%">签收人</td>
			<td style="border:1px solid black;font-weight:bold;" width="20%">状态</td>
			<td style="border:1px solid black;font-weight:bold;" width="30%">阅读时间</td>
		</tr>
		<tbody id="receiptBody">
		</tbody>
	</table>
</div>

    