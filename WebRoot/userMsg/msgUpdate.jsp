<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>撰写通知</title>
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
       	<script src="../js/jquery-1.7.1.js"></script>
        <script src="../js/html5.js"></script>
		<script src="../js/jquery.formalize.js"></script>
		<script src="../js/loading.js"></script>
		<script src="../js/show.js"></script>
		<script src="js/common.js"></script>
		<script src="js/lenLimit.js"></script>
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
		
		function check(){		
				clearError();	 
			 if($("#title").val()==""||lenStat($("#title"))>50){
			 	showError("#title","请输入通知主题并小于50字！")
			 	alert("请输入通知主题并小于50字！");
			 	return false;
			 }
			  if($("#areaLength").val()>140){
			  	error("content",3); 
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
			 	showError("#rname","请选择收件人！")
			 	alert("请选择收件人！");
			 	return false;
			 }
			 $("#rids").val(r.substring(0,r.length-1));
			 $("#rnames").val(r2.substring(0,r2.length-1));
			 
			 return true;
		}
		
		function send(){
			if(!check()) return false;
			if(confirm("确认发送吗？")){
				$("form").attr("action","msgAdd.action");
				$("form").submit();
			}
		}
		
		function save(){
			clearError();
			if($("#title").val()==""||lenStat($("#title"))>50){
			 	showError("#title","请输入通知主题并小于50字！")
			 	alert("请输入通知主题并小于50字！");
			 	return false;
			 }
			 if($("#areaLength").val()>140){
			 	error("content",3); 
			 	return false;
			 }
			 
			 if(confirm("确认保存吗？")){
				 $("#rid").val("");
				 $("#rname").val("");
				 $("form").attr("action","msgSave.action");
				 $("form").submit();
			 }
		}
		
		function normal(id,times)  
		{  
		    var obj=$("#"+id);  
		    obj.css("background-color","#FFF");  
		    if(times<0)  
		    {  
		        return;  
		    }  
		    times=times-1;  
		    setTimeout("error('"+id+"',"+times+")",150);  
		}  
		function error(id,times)  
		{  
		    var obj=$("#"+id);  
		    obj.css("background-color","#F6CECE");  
		    times=times-1;  
		    setTimeout("normal('"+id+"',"+times+")",150);  
		}  
		
		
        </script>
        <style>
        	.inputarea{border: 1px solid #ccc;border-radius: 3px 3px 3px 3px;box-shadow:1px 1px 4px 0 #DCDCDC inset;margin:7px 0 3px;padding: 5px;position: relative;}
        	.box01-num{ width:100%; float:left; padding-bottom:3px;}
			.box01-num span{ float:left;}
			.box01-num p{ float:right; padding:2px 3px 0 0;}
			.box01-num .num{ font-size:18px; font-style:italic; padding:0 4px 0 2px;}
        </style>
       </head>

<body>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img id="show" onclick="showHide();" src="../css/default/images/sideBar_arrow_right.jpg" width="46" height="30" title="收起"></div>
            <div class="posi fl">
            	<ul>
            		<li><a href="msgReceiveBox.action" target="_self">我的通知</a></li>
                	<li class="fin">撰写通知</li>
                </ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <!--Filter--><!--Filter End-->
        <!--Table-->
        <div class="mb10 pt45">
        <s:form action="msgAdd" method="post"  namespace="/userMsg">
        
       	  <table width="100%"  class="table_1">
           <!--  <thead>
            <th colspan="4" class="t_r"><input type="button" value="发送" onclick="send();"/>
        	      &nbsp;
        	      <input type="button" value="存至草稿箱" onclick="save();"/>
        	      &nbsp;
        	      <input type="button" value="返回" onclick="shut();"/>
        	      &nbsp;
        	</th>
           </thead>-->
           <tbody id="tb">
            <tr>
             <td class="t_r lableTd"><input type="button" value="新增收件人" onclick="addReceiver();">&nbsp;收件人：</td>
             <td colspan="3" id="reTd">
             <input type="hidden" name="msgId" id="msgId" value="<s:property value='#parameters.msgId'/>"/>
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
            	<iframe scrolling="auto" height="150" frameborder="1" border="1" marginheight="0" marginwidth="0" width="100%" id="attachFrame" name="attachFrame" src="/portal/attach/loadFileList.action?fileGroup=attach&fileGroupName=attachGroup&fileGroupId=<s:property value="msgBo.attach"/>&userName=<s:property value="#session.userName"/>&loginName=<s:property value="#session.loginName"/>&procType=edit&targetType=frame&type=1"></iframe>
            	</td>
            </tr>
           <tr>
             <td class="t_r lableTd">内容：</td>
             <td colspan="3">
             <div class="area">
				<div class="box01-num">
					<input type="hidden" name="areaLength" id="areaLength"/>
					<p>你还可以输入<b style="display:inline;" class="num">140</b>字</p>
				</div>
				<div class="inputarea">
					 <textarea class="chackTextarea" id="content" name="content" rows="15" ><s:property value='msgBo.content'/></textarea>
				</div>
			</div>  
             </td>
          </tr>
           </tbody>
            <tr class="tfoot">
              <td colspan="4" class="t_r"><input type="button" value="发送" onclick="send();"/>
        	      &nbsp;
        	      <input type="button" value="存至草稿箱" onclick="save();"/>
        	       &nbsp;
        	      <input type="button" value="返回收件箱" onclick="backReceive();"/>
        	      &nbsp;
        	      <input type="button" value="返回发件箱" onclick="backSend();"/>
        	      &nbsp;
        	      <input type="button" value="返回草稿箱" onclick="backDraft();"/>
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
