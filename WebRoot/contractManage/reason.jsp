<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div id="reasonZone" title="合同流程终止原因">
	<input type="hidden" name="process" id="process"/>
	<input type="hidden" name="incident" id="incident"/>
	<input type="hidden" name="mainId" id="mainId"/>
	<input type="hidden" name="operator" id="operator" value="<s:property value='#session.t_login_name'/>"/>
	 <table style="border-collapse:collapse;" id="reasonTable" width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td style="border:1px solid black;" width="50%">终止原因</td>
			<td style="border:1px solid black;" width="10%">附件</td>
			<td style="border:1px solid black;" width="20%">终止人</td>
			<td style="border:1px solid black;" width="20%">终止时间</td>
		</tr>
	</table>
</div>

    