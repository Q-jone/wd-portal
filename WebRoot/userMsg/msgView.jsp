<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>查看通知</title>
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
      <style>
			pre {
				white-space: pre-wrap;       /* css-3 */
				white-space: -moz-pre-wrap;  /* Mozilla, since 1999 */
				white-space: -pre-wrap;      /* Opera 4-6 */
				white-space: -o-pre-wrap;    /* Opera 7 */
				word-wrap: break-word;       /* Internet Explorer 5.5+ */
				word-break:break-all;
				overflow:hidden;
			}
		</style>
       	<script src="../js/jquery-1.7.1.js"></script>
        <script src="../js/html5.js"></script>
		<script src="../js/jquery.formalize.js"></script>
		<script src="../js/loading.js"></script>
		<script src="../js/show.js"></script>
		<script src="js/common.js"></script>
		<script src="js/jquery-ui-1.8.22.custom.min.js"></script>
		<script src="js/jquery.qtip-1.0.0-rc3.js"></script>
		<link type="text/css" href="../css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		<script type="text/javascript">
         $(function(){
         	$(".hideBu").live("click",function(){
         		$("#rname").val($.trim($("#rname").val().replace($(this).prev("input[type=text]").val()+";","")));
         		$(this).parent().remove();			
         	})
			
			$(".odd tr:odd").css("background","#fafafa");	
			loadShow();		
		});
	
		function shut(){
		  window.history.back();
		  //window.location.href="msgReceiveBox.action";
		  //window.opener=null;
		  //window.open("","_self");
		  //window.close();
		}

		function backView(){
			$("#forwardTable").hide();
			$("#replyTable").hide();
			$("#detailTable").show();
		}
		function check(){			 
			 if($("#forwardTable").find("#title").val()==""||lenStat($("#forwardTable").find("#title"))>50){
			 	alert("请输入通知主题并小于50字！");
			 	return false;
			 }
			 $("#rids").val("");$("#rnames").val("");
			 $("#reTd").find("input[type=hidden][name^=rd]").each(function(i,n){
		 		if($(n).val()!=""){
		 			$("#rids").val($("#rids").val()+$(n).val()+",");
		 		}
			 })
			 $("#reTd").find("input[type=hidden][name^=rn]").each(function(i,n){
			 	if($(n).val()!=""){
			 		$("#rnames").val($("#rnames").val()+$(n).val()+",");
			 	}
			 })
			 var r = $("#rids").val();
			 var r2 = $("#rnames").val();
			 if(r==""){
			 	alert("请选择收件人！");
			 	return false;
			 }
			 $("#rids").val(r.substring(0,r.length-1));
			 $("#rnames").val(r2.substring(0,r2.length-1));
			 
			 return true;
		}
		
		function reply(){
			$("#replyTable").show();
			$("#forwardTable").hide();
			$("#detailTable").hide();
			$("#replyTable").find("#title").val("回复："+$("#msgTitle").text());
			//$("#replyTable").find("#content").val("\n\n"+$("#replyTable").find("#content").val());
			$("#replyTable").find("#content").val("//"+$("#replyTable").find("#content").val());
		}
		
		function forward(){
			$("#forwardTable").show();
			$("#replyTable").hide();
			$("#detailTable").hide();
			$("#forwardTable").find("#title").val("转发："+$("#msgTitle").text());
			//$("#forwardTable").find("#content").val("\n"+$("#forwardTable").find("#content").val());
			$("#forwardTable").find("#content").val("//"+$("#forwardTable").find("#content").val());
		}
		
		function replySub(){
			if($("#replyTable").find("#title").val()==""||lenStat($("#replyTable").find("#title"))>50){
			 	alert("请输入通知主题并小于50字！");
			 	return false;
			}
			if(confirm("确认回复吗？")){
				$("#rid").val("");
				$("#rname").val("");
				$("#msgReply").submit();
			}
		}
		
		function forwardSub(){
			if(!check()) return false;
			if(confirm("确认转发吗？")){
				$("#rid").val("");
				$("#rname").val("");
				$("#msgForward").submit();
			}
		}
		
        </script>
       </head>

<body>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img id="show" onclick="showHide();" src="../css/default/images/sideBar_arrow_right.jpg" width="46" height="30" title="收起"></div>
            <div class="posi fl">
            	<ul>
            		<li><a href="msgReceiveBox.action" target="_self">我的通知</a></li>
                	<li class="fin">查看通知</li>
                </ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <!--Filter--><!--Filter End-->
        <!--Table-->
        <div class="mb10 pt45">
       
        
       	  <table width="100%"  id="detailTable" class="table_1">
           <!--  <thead>
            <th colspan="4" class="t_r">
            	<s:if test='msgBo.sendMode=="1"'>	
            	<input type="button" value="回复" onclick="reply();"/>
        	      &nbsp;
        	    </s:if>
        	      <input type="button" value="转发" onclick="forward();"/>
        	      &nbsp;
        	      <input type="button" value="返回" onclick="shut();"/>
        	      &nbsp;
        	</th>
           </thead>-->
           <tbody id="tb">
            <tr>
             <td class="t_r lableTd">发件人：</td>
             <td colspan="3">
             <input type="hidden" name="sd" id="sd" value="<s:property value='msgBo.sid'/>"/>
             <s:property value='msgBo.sname'/></td>
            </tr>
            <tr>
             <td class="t_r lableTd">主题：</td>
             <td colspan="3" id="msgTitle"><s:property value='msgBo.title'/></td>
            </tr>
            <tr>
            	<td class="lableTd t_r">附件内容：</td>
            	<td colspan="3">
            	<input type="hidden" name="attach" id='attach' value="<s:property value="msgBo.attach"/>"/>
            	<iframe scrolling="auto" height="150" frameborder="1" border="1" marginheight="0" marginwidth="0" width="100%" id="attachFrame" name="attachFrame" src="/portal/attach/loadFileList.action?fileGroup=attach&fileGroupName=attachGroup&fileGroupId=<s:property value="msgBo.attach"/>&userName=<s:property value="#session.userName"/>&loginName=<s:property value="#session.loginName"/>&procType=view&targetType=frame&type=1"></iframe>
            	</td>
            </tr>
           <tr>
             <td class="t_r lableTd">内容：</td>
             <td colspan="3" id="msgContent"><pre><s:property value="msgBo.content" escape="0"/></pre></td>
          </tr>
           </tbody>
            <tr class="tfoot">
              <td colspan="4" class="t_r">
              <s:if test='msgBo.sendMode=="1"'>	
            	<input type="button" value="回复" onclick="reply();"/>
        	      &nbsp;
        	    </s:if>
        	      <input type="button" value="转发" onclick="forward();"/>
        	      &nbsp;
        	      <input type="button" value="返回收件箱" onclick="backReceive();"/>
        	      &nbsp;
        	      <input type="button" value="返回发件箱" onclick="backSend();"/>
        	      &nbsp;
        	      <input type="button" value="返回草稿箱" onclick="backDraft();"/></td>
            </tr>
                             
                              
           </table>
           <hr>
            <s:form id="msgForward" action="msgForward" method="post"  namespace="/userMsg">
           <table style="display:none;" id="forwardTable" width="100%"  class="table_1">
           <tbody id="tb">
            <tr>
             <td class="t_r lableTd"><input type="button" value="新增收件人" onclick="addReceiver();">&nbsp;收件人：</td>
             <td colspan="3" id="reTd">
             <input type="hidden" name="forwardId" id="forwardId" value="<s:property value='#parameters.msgId'/>"/>
             <input type="hidden" name="rids" id="rids" value=""/>
             <input type="hidden" name="rnames" id="rnames" value=""/>
             <input type="hidden" name="sid" id="sid" value="<s:property value='#session.userId'/>"/>
             <input type="hidden" name="sname" id="sname" value="<s:property value='#session.userName'/>"/>
             <input type="text" readonly="readonly" id="rname" name="rname" class="input_full" value=""/></td>
            </tr>
            <tr>
             <td class="t_r lableTd">主题：</td>
             <td colspan="3"><input type="text" id="title" name="title" class="input_full" value="<s:property value='msgBo.title'/>"/></td>
            </tr>
            <tr>
            	<td class="lableTd t_r">附件内容：</td>
            	<td colspan="3">
            	<input type="hidden" name="attach" id='attach' value="<s:property value="msgBo.attach"/>"/>
            	<iframe scrolling="auto" height="150" frameborder="1" border="1" marginheight="0" marginwidth="0" width="100%" id="attachFrame" name="attachFrame" src="/portal/attach/loadFileList.action?fileGroup=attach&fileGroupName=attachGroup&fileGroupId=<s:property value="msgBo.attach"/>&userName=<s:property value="#session.userName"/>&loginName=<s:property value="#session.loginName"/>&procType=view&targetType=frame&type=1"></iframe>
            	</td>
            </tr>
           <tr>
             <td class="t_r lableTd">内容：</td>
             <td colspan="3">
              <textarea id="content" name="content" rows="15"><s:property value='msgBo.content'/></textarea></td>
          </tr>
           </tbody>    
           <tr class="tfoot">
              <td colspan="4" class="t_r"><input type="button" value="确认转发" onclick="forwardSub();"/>
        	      &nbsp;
        	      <input type="button" value="返回" onclick="backView();"/>
        	  </td>
            </tr>    
           </table>
           </s:form>
           
            <s:form id="msgReply" action="msgReply" method="post"  namespace="/userMsg">
           <table style="display:none;" id="replyTable" width="100%"  class="table_1">
           <tbody id="tb">
           <tr>
             <td class="t_r lableTd">主题：</td>
             <td colspan="3"><input type="text" id="title" name="title" class="input_full" value="<s:property value='msgBo.title'/>"/></td>
          </tr>
           <tr>
            	<td class="lableTd t_r">附件内容：</td>
            	<td colspan="3">
            	<input type="hidden" name="attachReply" id="attachReply" value=""/>
            	<iframe scrolling="auto" height="150" frameborder="1" border="1" marginheight="0" marginwidth="0" width="100%" id="attachReplyFrame" name="attachReplyFrame" src="/portal/attach/loadFileList.action?fileGroup=attachReply&fileGroupName=attachReplyGroup&fileGroupId=&userName=<s:property value="#session.userName"/>&loginName=<s:property value="#session.loginName"/>&procType=edit&targetType=frame&type=1"></iframe>
            	</td>
            </tr>
           <tr>
             <td class="t_r lableTd">内容：</td>
           <td colspan="3">
             <input type="hidden" name="replyId" id="replyId" value="<s:property value='#parameters.msgId'/>"/>
             <input type="hidden" name="replyRid" id="replyRid" value="<s:property value='msgBo.sid'/>"/>
             <input type="hidden" name="replyRname" id="replyRname" value="<s:property value='msgBo.sname'/>"/>
             <input type="hidden" name="sid" id="sid" value="<s:property value='#session.userId'/>"/>
             <input type="hidden" name="sname" id="sname" value="<s:property value='#session.userName'/>"/>
             <textarea id="content" name="content" rows="15"><s:property value='msgBo.content'/></textarea>
          </td>
          </tr>
           </tbody>         
           <tr class="tfoot">
              <td colspan="4" class="t_r"><input type="button" value="确认回复" onclick="replySub();"/>
        	      &nbsp;
        	      <input type="button" value="返回" onclick="backView();"/>
        	  </td>
            </tr>               
         </table>
           </s:form>
      </div>
        <!--Table End-->
</div>

<div id="hideRe" style="display:none"><div>
<input type="hidden" name="rn" id="rn"/><input type="hidden" name="rd" id="rd"/>
<input type="text" id="rname" name="rname" class="input_xlarge"/>&nbsp;<input type="button" value="删除" class="hideBu"></div></div>
</body>
</html>
