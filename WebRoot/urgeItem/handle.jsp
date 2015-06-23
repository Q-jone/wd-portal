<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div id="handleZone" title="超时流程延期申请">
   <p class="validateTips">延期申请：</p>

	<form id="handleForm">
	<input type="hidden" name="process" id="process"/>
	<input type="hidden" name="incident" id="incident"/>
	<input type="hidden" name="delayPerson" id="delayPerson" value="<s:property value='#session.t_login_name'/>"/>
	<fieldset>
		<label>延期天数（输入0 取消延期）</label>
		<input type="text" name="delayDay" id="delayDay"/>
	</fieldset>
	</form>
</div>

    