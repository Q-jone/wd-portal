<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>

	</title>
<link rel="stylesheet" href="../operation/ui/css/formalize.css" />
<link rel="stylesheet" href="../operation/ui/css/page.css" />
<link rel="stylesheet" href="../operation/ui/css/default/imgs.css" />
<link rel="stylesheet" href="../operation/ui/css/reset.css" />
	<script src="../js/jquery-1.7.1.js"></script>
	<script src="../js/jquery.form.js"></script>
	<script src="../js/jquery.formalize.js"></script>
	<script src="../operation/ui/js/messagebox.js"></script>
	<style>
	.deptTreeZone{display:none;}
	.redMark{color:red;display:inline;}
	.r_bor{border-right:#000 1px solid}
	</style>
	<style type="text/css" media=print>
	.nprint{display:none;}
	</style>
<script>

$(document).ready(function(){
	initMessagebox();
	initMessageboxClose();
});
function toPrintPage(){
	var id = $("#id").val();
	var url = "/portal/opDocSend/view.action?id="+id+"&deal=n&print=1";
	window.showModalDialog(url,window,'dialogWidth:870px;dialogHeight:800px;scroll:auto');
}
function printFunc(){
	pageHeaderFooter();
    window.print();
}

function pageHeaderFooter(){
	var hkey_root,hkey_path,hkey_key;
	hkey_root="HKEY_CURRENT_USER";
	hkey_path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
	try{
       var RegWsh = new ActiveXObject("WScript.Shell");
       hkey_key="header";
       RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"");
       hkey_key="footer";
       RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"");
    }catch(e){}
}
</script>
</head>
<body class="Flow" style="background:none">
<div id="bt"  class=" transparent" style="display:none;"></div>
<s:form action="startFlow" id="form" method="post" namespace="/opDocSend" theme="simple">
	<!-- 待办项时增加此变量 对应  DeptContactOperateVo。taskId = todoid-->
	<input type="hidden" id="id" name="id" value="<s:property value='#request.opDocSend.id'/>"/>
	<input type="hidden" id="mainUnitId" name="sendMainId" value="<s:property value='opDocSend.sendMainId'/>"/>
	<input type="hidden" id="lineUnitId" name="sendLineId" value="<s:property value='opDocSend.sendLineId'/>"/>

	<div class=" transparent" id="maskDiv" style="display:none;" style="filter:alpha(opacity=30);opacity:0.3;"></div>

	<!-- <div class="f_bg_fw"> -->
	<div class="f_bg_fw" style="background:none">
		<s:if test="#request.print!=1">
     	<div class="Divab1">
			<!--1st-->
			<div class="panel_6">
				<div class="divT">
					<div class="mb10 icon icon_1"></div>
					<div class="more_4">
						<a title="更多" href="#">更多</a>
					</div>
				</div>
				<div class="divH">
					<div class="divB">
						<h5 class="clearfix">
							业务办理
						</h5>
						<div class="con">
							<ul class="button clearfix">
								<s:if test="#request.thread!=null&&#request.deal!='n'">
								<li>
									<a id="todo_handle" class="ywbl" href="javascript:void(0);">办理</a>
								</li>
								</s:if>
								<li>
									<a id="todo_print" class="print"  href="javascript:void(0);" onclick="toPrintPage();">打印</a>
								</li>
							</ul>
						</div>
					</div>
					<div class="divF"></div>
				</div>
			</div>
		</div>
		</s:if>
        <div class="gray_bg">
        	<div class="gray_bg2">
            	<div class="w_bg">
                	<div class="Bottom">
                    	<div class="Top_fw">
                        	<h1 class="t_c" id="title1">上海申通地铁集团有限公司<br>运营发文稿纸</h1>
							<div class="mb10">

								<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_2">
									<thead>
									<th colspan="4" class="lableTd6" style="padding-left:10px;padding-right:0;">
									<h5><b style="display:inline">运营发文:</b></h5>
									</th>
									</thead>
								  	<tbody>
								  	<tr class="content7">
	                                    <td class="lableTd t_c" style="width:15%">文件分类</td>
	                            	    <td class="pl18" colspan="3">
												<s:property value='#request.opDocSend.fileTypeDic.name'/>
												<s:property value='#request.opDocSend.fileTypeSubDic.name'/>
		                            	</td>
	                          	    </tr>
								  	<tr class="content7">
	                                    <td class="lableTd t_c" style="width:15%">文件编号</td>
	                            	    <td class="pl18" colspan="3">
	                            	    	<span style="width: 10%; margin-right: 2px;display:inline">
												<s:property value='#request.opDocSend.fileCode'/>
											</span>
		                            	</td>
	                          	    </tr>
	                          	    </tbody>
	                          	  </table>
	                          	  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_2">
	                          	    <tr class="content6">
	                                    <td class="lableTd t_c" style="width:15%">文件名称</td>
	                                    <td colspan="3" class="content6">
	                                    <s:property value='#request.opDocSend.docTitle'/></td>
                                  	</tr>
								  	<tr class="content6">
										<td class="lableTd t_c">发放范围</td>
										<td class="pl18" colspan="3">
											<s:property value='#request.opDocSend.sendMainW'/>
										</td>
								  	</tr>
								  	<tr class="content6">
										<td class="lableTd t_c">外发单位</td>
										<td class="pl18" colspan="3">
											<s:property value='#request.opDocSend.sendOutW'/>
										</td>
								  	</tr>
								  	<tr class="content6">
										<td class="lableTd t_c" style="vertical-align:middle">文件内容<br>涉及线路/<br>OCC/专业<br>单位参考</td>
										<td class="pl18" colspan="3">
											<s:property value='#request.opDocSend.sendLineW'/>
										</td>
								  	</tr>
								  	<tr class="content6">
										<td class="lableTd t_c">备注</td>
										<td class="pl18" colspan="3">
											<s:property value='#request.opDocSend.remark'/>
										</td>
								  	</tr>
								  	<tr class="content6">
										<td class="lableTd t_c">起草人</td>
										<td class="pl18" style="width:35%;border-right:#000 1px solid;">
											<s:property value='#request.startThread.userName'/>
										</td>
										<td class="lableTd t_c" style="width:15%">起草日期</td>
										<td class="pl18">
											<s:property value='#request.opDocSend.applyDate'/>
										</td>
								  	</tr>
								  	<tr class="content6">
										<td class="lableTd t_c">审批意见</td>
										<td class="pl18" colspan="3">
											<table width="100%" border="0">
											<tbody>
											<s:iterator value="#request.approveThreads" id="data" status="s">
												<tr>
												<td style="border-bottom: 0px;">
														<div align="left">
														<s:property value="#data.contents"/><br>
														<div>
														<span style="float:right">
														<s:property value="#data.userName"/>&nbsp;<s:property value="#data.endTime"/>
														</span>
														<s:if test="#data.attachGroup!=null&&#data.attachCnt!=0">
														<a target="_blank" style="float:right" href="/portal/attachOld/loadFileOldList.action?fileGroupId=<s:property value='#data.attachGroup'/>&procType=view&targetType=frame&type=1&attachMemo=OP_DOCSEND">
														<img src="../operation/ui/images/fj.gif" style="cursor:hand" alt="附件">
														</a>
														</s:if>
														</div>
														</div>
												</td>
												</tr>
											</s:iterator>
											</tbody>
											</table>
										</td>
								  	</tr>
								  	<tr class="content6">
										<td class="lableTd t_c">套头人</td>
										<td class="pl18" style="width:35%;border-right:#000 1px solid;">
											<s:property value='#request.endThread.userName'/>
											<s:if test="#request.endThread.attachGroup!=null&&#request.endThread.attachCnt!=0">
												<a target="_blank" style="float:left" href="/portal/attachOld/loadFileOldList.action?fileGroupId=<s:property value='#request.endThread.attachGroup'/>&procType=view&targetType=frame&type=1&attachMemo=OP_DOCSEND">
												<img src="../operation/ui/images/fj.gif" style="cursor:hand" alt="附件">
												</a>
											</s:if>
										</td>
										<td class="lableTd t_c" style="width:15%">套头日期</td>
										<td class="pl18">
											<s:property value='#request.endThread.endTime'/>
										</td>
								  	</tr>
								  	<tr class="content6">
										<td class="lableTd t_c">套头意见</td>
										<td class="pl18" colspan="3">
											<s:property value='#request.endThread.contents'/>
										</td>
								  	</tr>
								  	<tr class="content6">
                                        <td class="lableTd t_c">发布日期</td>
                                        <td class="pl18" style="width:35%;border-right:#000 1px solid;">
                                            <s:property value='#request.opDocSend.pubDate'/>
                                        </td>
										<td class="lableTd t_c" style="width:15%">文件状态</td>
										<td class="pl18">
											<s:property value='#request.opDocSend.isExpired'/>
										</td>
								  	</tr>
								  	<tr class="content6">
                                        <td class="lableTd t_c">有效日期</td>
                                        <td class="pl18" style="width:35%;border-right:#000 1px solid;">
                                            <s:property value='#request.opDocSend.validDate'/>
                                        </td>
										<td class="lableTd t_c" style="width:15%">发文日期</td>
										<td class="pl18">
											<s:property value='#request.opDocSend.passDate'/>
										</td>
								  	</tr>
								  	<tr class="content7">
								  		<td class="lableTd t_c">文件附件</td>
								  		<td colspan="3">
								  			<input type="hidden" name="contentAtt" id='contentAtt' value="<s:property value="#request.opDocSend.contentAtt"/>"/>
<iframe scrolling="auto" height="150" frameborder="1" border="1" marginheight="0" marginwidth="0" width="100%" id="attachFrame" name="attachFrame" src="/portal/attachOld/loadFileOldList.action?fileGroup=contentAtt&fileGroupName=attachGroupOp&fileGroupId=<s:property value='#request.opDocSend.contentAtt'/>&userName=<s:property value="#session.userName"/>&loginName=<s:property value="#session.loginName"/>&procType=view&targetType=frame&type=1&attachMemo=OP_DOCSEND"></iframe>
								  		</td>
								  	</tr>
								  	<tr class="content7">
								  		<td class="lableTd t_c">正式文件</td>
								  		<td colspan="3">
								  		<s:if test="#request.thread.nodeName=='运营发文套头'">
								  		<input type="hidden" name="contentAttMain" id='contentAttMain' value="<s:property value="#request.opDocSend.contentAttMain"/>"/>
								  		<input type="hidden" id='contentAttMainCnt' value=""/>
<iframe scrolling="auto" height="150" frameborder="1" border="1" marginheight="0" marginwidth="0" width="100%" id="attachMainFrame" name="attachMainFrame" src="/portal/attachOld/loadFileOldList.action?fileGroup=contentAttMain&fileGroupName=attachGroupOpMain&fileGroupId=<s:property value='#request.opDocSend.contentAttMain'/>&userName=<s:property value="#session.userName"/>&loginName=<s:property value="#session.loginName"/>&procType=edit&targetType=frame&type=1&attachMemo=OP_DOCSEND&fileCntObjId=contentAttMainCnt"></iframe>
										</s:if>
										<s:else>
<iframe scrolling="auto" height="150" frameborder="1" border="1" marginheight="0" marginwidth="0" width="100%" id="attachMainFrame" name="attachMainFrame" src="/portal/attachOld/loadFileOldList.action?fileGroup=contentAttMain&fileGroupName=attachGroupOpMain&fileGroupId=<s:property value='#request.opDocSend.contentAttMain'/>&userName=<s:property value="#session.userName"/>&loginName=<s:property value="#session.loginName"/>&procType=view&targetType=frame&type=1&attachMemo=OP_DOCSEND"></iframe>
										</s:else>
								  		</td>
								  	</tr>
                          	  	</table>
                        	</div>
                        	<s:if test="#request.print==1">
                                <s:if test='#request.opDocSend.flowWorkProcess.state==4 || #request.opDocSend.isLeader=="1"'>
                                    <div class="t_r">
                                        经分管领导审核同意 __________________________
                                    </div>
                                </s:if>
                        	</s:if>
							<div class="mb10 t_c nprint">
								<s:if test="#request.print==1">
								<input type="button"  value="打印" onclick="printFunc();"/>&nbsp;
								</s:if>
								<input type="button" id="closeBtn" value="关闭" onclick="window.close();"/>&nbsp;
								<div id="formUpdate_loading" style="width:100%;align:center;line-height:20px;display:none;" class="">
							      <p style="width:auto;display:inline;"><img src="<%=path%>/operation/ui/images/loading.gif" style="display:inline;"/>
							      <b style="color:green;display:inline;">&nbsp;正在提交</b></p>
							    </div>
							</div>
							<div class="footer"></div>
						</div>
                    </div>
                </div>
            </div>
        </div>
 	</div>

 	<div class="transparent" id="maskDiv" style="display: none;" style="filter:alpha(opacity=30);opacity:0.3;"></div>
	<s:if test="#request.thread.nodeName=='运营发文审批'">
		<jsp:include page="verifyDraft.jsp"/>
	</s:if>
	<s:if test="#request.thread.nodeName=='运营发文套头'">
		<jsp:include page="formalize.jsp"/>
	</s:if>
	</s:form>

</body>
</html>