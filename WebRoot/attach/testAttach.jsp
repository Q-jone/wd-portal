<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" import="java.io.*,java.util.*" %>
<%
String userName = "tt";
String loginName = "tt";

 %>
<script type="text/javascript">
	function loadFiles(){
		testHiddenVal = document.getElementById("testHidden").value
		url = "<%=request.getContextPath()%>/attach/loadFileList.action?fileGroup=testHidden&fileGroupName=upFiles&userName=attila&loginName=luyj&fileGroupId="+testHiddenVal;
		//alert(url);
		window.open(url);
	}
	

</script>

<script src="../js/html5.js"></script>
<script src="../js/jquery-1.7.1.js"></script>
<script src="../js/jquery.formalize.js"></script>
<script src="../js/Player.js"></script>
<script type="text/javascript" src="../js/organforFlowInterface.js"></script>
<script type="text/javascript" src="../js/UltimasDeptAndPersonSel.js"></script>
<script src="../ProjectApproval/js/jquery_expand.js"></script>
<script type="text/javascript" src="../js/attach.js"></script> 
<input type="text" id="testHidden" name="testHidden" value=""/>
<a href="#" onclick="javascript:loadFiles()" >test</a>
<br/>
 <input type="text" id="projectPlanAttachId"  name="projectPlanAttachId"/>
 <a style="display: inline;margin-left: 600px;cursor: hand;" target="#" onclick="drawAttach(480, 280,'projectPlanAttachId','docReYjFileGroup4',document.all.projectPlanAttachId.value,document.all.projectPlanAttachId.value,'','frame','fjcount4','<%=userName %>','<%=loginName %>')">相关附件(<span style="display:inline;" id="fjcount4">0</span>)</a>
 <iframe scrolling="auto" height="100%" frameborder="0" marginheight="0" marginwidth="0" width="100%" id="projectAttachIdFrame" name="projectAttachIdFrame" src="<%=request.getContextPath()%>/attach/loadFileList.action?fileGroup=projectAttachId&fileGroupName=projectAttachIdGroup&userName=<%=userName%>&loginName=<%=loginName%>&fileGroupId={58A0C328-1567-35C4-22C4-5C48BB25503A}&procType=view&targetType=frame"></iframe>  
                