<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String oldDeptId = request.getParameter("oldDeptId");
if(oldDeptId==null){
	oldDeptId = "";
}
String showMessage = request.getParameter("showMessage");
if(showMessage==null){
	showMessage = "";
}
String role = request.getParameter("role");
if(role==null){
	role = "";
}
String userName = (String)session.getAttribute("userName");
%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title></title>

	<script type="text/javascript">
		
	$(function(){
		if("yes"=="<%=showMessage%>"){
			showMessage();
		}
	})

 	function divPosition(div){
		var top = ($(document).height() - $(div).height())/2;
		var left = ($(document).width() - $(div).width())/2;
		
		$(div).css("left",left+"px");
		$(div).css("top",top<0?0:top + "px");
	}
	
	function divMaskPosition(mask){
		pageWidth = ($.browser.version=="6.0")?$(document).width()-21:$(document).width();
		pageHeight = $(document).height();
		$(mask).css("height",pageHeight).css("width",pageWidth);
	}
	
	function MessageBox(divId,maskId){
		pageWidth = ($.browser.version=="6.0")?$(document).width()-21:$(document).width();
		pageHeight = $(document).height();
		
		var div=$("#"+divId);
		var mask = $("#"+maskId);
		
		$(mask).animate({
				height :pageHeight,
				width :pageWidth,
				opacity :'show'
			},{
				duration :500,
				complete : function() {
					$(mask).css("filter","alpha(opacity=30)");
					divPosition(div);
					$(div).animate({
							opacity :'show'
						},{
							duration :500,
							complete : function() {
								divMaskPosition(mask);
								var offsetHeight = (document.body.clientHeight - $(div).height()) / 2
								if (offsetHeight < 0) offsetHeight = 0;
								ScrollTop = $(div).offset().top	- offsetHeight;
								$(document).scrollTop(ScrollTop);
								
							}
						}
					);
					/**/
				}
			}
		);
	}
	
	function closeMessageBox(divId,maskId){
		$("#"+divId).animate({
			  opacity:'hide'
		},{duration:1000,complete:function(){}});
		
		$("#"+maskId).animate({
			  opacity:'hide'
		},{duration:1000,complete:function(){}});
	}
	
	function closeDiv(){
		closeMessageBox("handle_zone","maskDiv");
	}
	
	function read(obj){
		var tbody = $(obj).parents("tbody");
		$(obj).parents("tr").remove();
		if(tbody.find("tr").length==0){
			closeDiv();
		}
	}

	function showMessage(){
		$.ajax({
			url: "/portal/report/showMessage.action",
			type: 'post', 
			data:{
				oldDeptId:"<%=oldDeptId%>",
				role:"<%=role%>"
			},
			dataType: 'json', 
			error:function(){
				alert("系统连接失败，请稍后再试！");
			},
			success: function(obj){			
              if(obj!=null&&obj.length>0){
            	  MessageBox("handle_zone","maskDiv");
				  var addHtml = "";
				  for(var i=0;i<obj.length;i++){
					  addHtml += "<tr><td class='t_c'>"+(i+1)+"</td><td class='t_c'>"+obj[i][1]+"</td><td class='t_l'>"+obj[i][2]+"</td><td class='t_c'>";
					  addHtml += "<a href='javascript:void(0)' onclick='read(this);readMessage(\""+obj[i][0]+"\");'>已阅</a>";
					  if(obj[i][2]!=null&&obj[i][2].indexOf("【延误】")>-1){
						  addHtml += "<a href='javascript:void(0)' onclick='showPushMessage(\""+obj[i][3]+"\",this);'>督办</a>";
					  }
					  addHtml += "</td></tr>";
				  }
				  $("#messageTbody").append(addHtml);
              }
			}
		});
	}

	function readMessage(id){
		$.ajax({
			url: "/portal/report/readMessage.action",
			type: 'post', 
			data:{
				id:id,
				random:Math.random()
			},
			dataType: 'json', 
			error:function(){
				alert("系统连接失败，请稍后再试！");
			},
			success: function(obj){	
						
			}
		});
	}

	function pushMessage(id,obj2){
		$.ajax({
			url: "/portal/report/pushMessage.action",
			type: 'post', 
			data:{
				id:id,
				content:$(obj2).parents("tr").find("textarea").val(),
				random:Math.random()
			},
			dataType: 'json', 
			error:function(){
				alert("系统连接失败，请稍后再试！");
			},
			success: function(obj){	
				if(obj.if_success=='yes'){
					alert("已发送催办短信！");
					$(obj2).parents("tr").remove();
				}else{
					alert("发送催办短信失败，请联系管理员！");
				}
			}
		});
	}

	function showPushMessage(id,obj){
		if($(obj).parents("tr").next().find('textarea').length > 0){
			return;
		}
		var addHtml = "<tr><td colspan='2'>督办信息内容：</td><td><textarea>"+
			$(obj).parents("tr").children("td:eq(2)").html().replace("【延误】","【催办】").replace("请关注！","请尽快办理！ 催办人：<%=userName%>")+
			"</textarea></td><td><a href='javascript:void(0)' onclick='pushMessage(\""+id+"\",this);'>发送</a></td></tr>";
		$(obj).parents("tr").after(addHtml);
	}
    </script>
</head>

<body>	
	<div id="handle_zone" class="f_window" style="display:none;">
	<h3 class="clearfix mb10">
			<span class="fl">
			业务提醒
			</span>
			<div class="fr close" id="handleDivClose" onclick="closeDiv()">关闭窗口</div>
		</h3>
	<div class="mb10" id="listDiv" style="height:450px;overflow: auto;">
        	<table width="100%"  class="table_1" id="mytable">
        				<thead>
                              <tr class="tit">
                               <th class="t_c" style="width:8%;">序号</th>
								<th class="t_c" style="width:15%;">类别</th>
                                 <th class="t_c">消息内容</th>
                                 <th class="t_c" style="width:8%;">操作</th>
                             
                                </tr>
                              
                              </thead>
                              <tbody id="messageTbody">
                              </tbody>
                              
                            </table>

      </div>
		
	</div>
	<div class="transparent" id="maskDiv" style="display: none;" style="filter:alpha(opacity=30);opacity:0.3;"></div>
</body>
</html>