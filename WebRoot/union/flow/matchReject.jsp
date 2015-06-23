<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
%>
<script>
function handleFunc(obj,choice){
     var remark = $('[name=remark]').val();
     
	if(confirm("确定要退回该专项吗？")){
 		$.post(
				'<%=path%>/unionMatch/rejectById.action?random='+Math.random(),
				{
					"id": '${match.id}',
					"remark": remark,
					"choice": '1'
				},
				function(obj, textStatus, jqXHR){
					if(obj=="1"){
						alert('操作成功。');
						parent.location.href=parent.location.href;
					}else{
						alert("服务器连接失败，请稍后再试!");
					}
					$("#handle_zone_loading").hide();
					closeMessageBox("handle_zone","maskDiv");
					return false;
				},
				"text"
			).error(function() { alert("服务器连接失败，请稍后再试!"); })	    
		
		$('#attachCnt').val($('#fjcount_1').text());
/* 		$(obj).attr("disabled",true);
		$("#handleClose").attr("disabled",true); */
		$("#handle_zone_loading").show();
	}
}

function initSuggestAttach(){
	$("a.suggest_attach").each(function(i,n){
		var obj = $(this);
		$(obj).click(function(){
			var attachId = $(obj).find("[name='attachId']");
			var attachCount = $(obj).find(".fjcount");
			var groupId = $("#"+$(attachId).attr("id")).val();
			drawAttach($(attachId).attr("id"), "", groupId, "", "", "open", $(attachCount).attr("id"));
		});
	});
}
function drawAttach(fileGroup, fileGroupName, fileGroupId, newGroupId, procType, targetType, fileCntObjId) {
	var url = '/portal/attachOld/loadFileOldList.action?fileGroup='
			+ fileGroup + '&fileGroupName=' + fileGroupName + '&fileGroupId='
			+ fileGroupId + '&newGroupId=' + newGroupId + '&procType='
			+ procType + '&targetType=' + targetType + '&fileCntObjId='
			+ fileCntObjId + '&rand='+Math.random()+ '&approve=1&attachMemo=OP_DOCSEND';
	openwindow(url,'',600,200);
}
var newwindow;
function openwindow(url,name,iWidth,iHeight)  
{  
// url 转向网页的地址  
// name 网页名称，可为空  
// iWidth 弹出窗口的宽度  
// iHeight 弹出窗口的高度  
//window.screen.height获得屏幕的高，window.screen.width获得屏幕的宽  
var iTop = (window.screen.height-30-iHeight)/2; //获得窗口的垂直位置;  
var iLeft = (window.screen.width-10-iWidth)/2; //获得窗口的水平位置;  
newwindow = window.open(url,'t','height='+iHeight+',width='+iWidth+',top='+iTop+',left='+iLeft);  
}  
$(function(){
	initSuggestAttach();
});
</script>
<div id="handle_zone" class="f_window" style="display:none;z-index:999;">
	
	<h3 class="clearfix mb10">
		<span class="fl">退回专项</span>
		<div class="fr close" id="handleDivClose">关闭窗口</div>
	</h3>
	<div class="con">
     	<input type="hidden" name="threadId" id="threadId" value="<s:property value='#request.thread.id'/>"/>
     	<input type="hidden" name="handlerName" id="handlerName" value=""/>
		<table id="choiceZone1" width="100%" border="0" cellspacing="0" cellpadding="0">				
	  		<tr height="30">
				<td class="td_1">
					
				</td>
				<td>
					<span class="fl" >意见：</span>
<%-- 	  				<span class="fr" style="display:inline;" id="fontId_1">
	                	<a name="suggest_attach" class="suggest_attach" target="#" >
						<input type="hidden" name="attachId" id="attachId_1" value=""/>
						<input type="hidden" name="attachCnt" id="attachCnt" value=""/>
	                	上传意见附件(<span style="display:inline;" id="fjcount_1" class="fjcount">0</span>)</a>
	                	<!--<a class="previewSuggest">备注预览</a>-->
                	</span> --%>
	  				<br/>
					<textarea rows="3" name="remark">请按要求修改</textarea>
				</td>
			</tr>
			<tr><td colspan="2">
			<div class="button t_c">
				<input id="handleSubmit" type="button" value="确 认" onclick="handleFunc(this,'');">
				<input id="handleClose" type="button" value="取 消">
		
				<div id="handle_zone_loading" style="width:100%;align:center;line-height:20px;display:none;" class="">
			      <p style="width:auto;display:inline;"><img src="<%=path %>/operation/ui/images/loading.gif" style="display:inline;"/>
			      <b style="color:green;display:inline;">&nbsp;正在提交</b></p>
			    </div>
	    	</div>
			</td>
			</tr>
		</table>
	</div>
</div>
