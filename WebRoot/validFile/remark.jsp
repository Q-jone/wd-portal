<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<style>
#remarkNow td{
    border:1px solid #000;
}
</style>
<div id="remarkZone" title="备注">
<table id="remarkNow" style="width:100%;">
</table>
<br>
   <form id="handleForm">
	<input type="hidden" name="mainId" id="mainId"/>
	<input type="hidden" name="operator" id="operator" value="<s:property value='#session.userName'/>"/>
	<fieldset>
		<label>备注（控制在200字内）</label>
		<textarea id="remark" name="remark" rows="7" ></textarea>
		<br><br>
		<label>如有附件，请上传附件（若单一附件大于20M，请使用分卷压缩控制每个附件在20M内）</label>
		<input type="hidden" name="attach" id="attach" value=""/>
        <iframe scrolling="auto" height="150" frameborder="1" border="1" marginheight="0" marginwidth="0" width="100%" id="attachFrame" name="attachFrame" src="/portal/attachOld/loadFileOldList.action?fileGroup=attach&fileGroupName=attachGroup&fileGroupId=&userName=<s:property value="#session.userName"/>&loginName=<s:property value="#session.loginName"/>&procType=edit&targetType=frame&type=1"></iframe>
    </fieldset>
	</form>
	
</div>

    