<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
%>
<script>
function handleFunc(obj,choice){
	if($("[name=contentAttMain]").val()==""||$("#contentAttMainCnt").val()=="0"){
		alert("请上传正式文件！");
		return false;
	}
	if(choice){
		$("[name=choice]").val(choice);
	}
	if(confirm("确定处理完成？")){
		var formOptions1 = {
			cache:false,
			type:'post',
			callback:null,
			dataType :'json',
			url: "dealFlow.action",
		    success:function(data){
				alert("提交成功!");
				window.location.href = "view.action?id="+$('#id').val();
				return false;
		    },error:function() { alert("服务器连接失败，请联系管理员!"); }
		};
		$('#attachCnt').val($('#fjcount_1').text());
/* 		$(obj).attr("disabled",true);
		$("#handleClose").attr("disabled",true); */
		$("#handle_zone_loading").show();

		$("#form").ajaxSubmit(formOptions1);  
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
<div id="handle_zone" class="f_window" style="display:none;">
	
	<h3 class="clearfix mb10">
		<span class="fl">套头</span>
		<div class="fr close" id="handleDivClose">关闭窗口</div>
	</h3>
	<div class="con">
     	<s:form action="dealFlow" id="form1" method="post" namespace="/opDocSend" theme="simple">
     	<input type="hidden" name="threadId" id="threadId" value="<s:property value='#request.thread.id'/>"/>
		<table id="choiceZone2" width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr height="30">
				<td class="td_1">
					<input type="radio" name="choice"  checked="checked" value="0"/>
				</td>
				<td>确认发文</td>
			</tr>

			<tr height="30">
				<td class="td_1">
					
				</td>
				<td>
					备注：
					<span class="fr" style="display:inline;">
	                	<a name="suggest_attach" class="suggest_attach" target="#">
						<input type="hidden" name="attachId" id="attachId_1" value=""/>
						<input type="hidden" name="attachCnt" id="attachCnt" value=""/>
	                	上传意见附件(<span style="display:inline;" id="fjcount_1" class="fjcount">0</span>)</a>
	               	</span>
					<br/>
					<textarea rows="3" name="suggest" id="suggest"></textarea>
				</td>
			</tr>
			
			<tr><td colspan="2">
				<div class="button t_c">
					<input id="handleSubmit" type="button" value="确 认" onclick="handleFunc(this);">
					<input id="handleClose" type="button" value="取 消">
					
					<div id="handle_zone_loading" style="width:100%;align:center;line-height:20px;display:none;" class="">
				      <p style="width:auto;display:inline;"><img src="<%=path %>/operation/ui/images/loading.gif" style="display:inline;"/>
				      <b style="color:green;display:inline;">&nbsp;正在提交</b></p>
				    </div>
				</div>			
			</td></tr>
		</table>
		</s:form>
		
	</div>
<%-- 	<div class="button t_c">
		<input id="handleSubmit" type="button" value="确 认" onclick="handleFunc(this);">
		<input id="handleClose" type="button" value="取 消">
		
		<div id="handle_zone_loading" style="width:100%;align:center;line-height:20px;display:none;" class="">
	      <p style="width:auto;display:inline;"><img src="<%=path %>/operation/ui/images/loading.gif" style="display:inline;"/>
	      <b style="color:green;display:inline;">&nbsp;正在提交</b></p>
	    </div>
	</div> --%>
</div>

