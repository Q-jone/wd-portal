<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>重点工作基本信息</title>
    <link rel="stylesheet" href="<s:url value="/css/formalize.css"/>" />
    <link rel="stylesheet" href="<s:url value="/css/page.css"/>" />
    <link rel="stylesheet" href="<s:url value="/css/default/imgs.css"/>" />
    <link rel="stylesheet" href="<s:url value="/css/reset.css"/>" />
    <!--[if IE 6.0]>
    <script src="js/iepng.js" type="text/javascript"></script>
    <script type="text/javascript">
        EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
    </script>
    <![endif]-->
    <script src="<s:url value="/js/html5.js"/>"></script>
    <script src="<s:url value="/js/jquery-1.7.1.js"/>"></script>
    <script src="<s:url value="/js/show.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/js/flick/jquery-ui-1.8.18.custom.min.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/js/flick/jquery.ui.datepicker-zh-CN.js"/>"></script>
    <script type="text/javascript" src="images/seclude.js"></script>
    <script type="text/javascript" src="images/secludeCheck.js"></script>
    <style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
        
        .black_overlay{ 
            display: none; 
            position: absolute; 
            top: 0%; 
            left: 0%; 
            width: 100%; 
            height: 100%; 
            background-color: black; 
            z-index:1001; 
            -moz-opacity: 0.8; 
            opacity:.80; 
            filter: alpha(opacity=88); 
        } 
        .white_content { 
            display: none; 
            position: absolute; 
            top: 25%; 
            left: 25%; 
            width: 55%; 
            height: 55%; 
            padding: 20px; 
            border: 10px solid orange; 
            background-color: white; 
            z-index:1002; 
            overflow: auto; 
        } 
    
    </style>
<script type="text/javascript">
$(function(){
	var confirm=$("#confirm").val();
	if(confirm==1){
		//dsabled后台取不到值
		$("#year").attr({"name":"year"});
		$("#year").hide();
		$("#yearcon").append("<input readonly='readonly' text='text' id='yearconfirm' name='workSeclude.year' value='"+$("#years").val()+"'/>");
		$("#yearconfirm").css({"border-color":"white"});
		$(":text").attr({"readonly":"true"});
		 $(":text").css({"border-color":"white"});
		$("#objective").attr({"readonly":"true"});
		$("#objective").css({"border-color":"white"});
	}
	$("#release").click(function(){
		if(realse()){
		if(window.confirm("发布后计划将无法修改，是否继续操作？")){
			$("#confirm").val("1");
			var path="/portal/project/work/save.action";
			
				$("#wform").attr("action", path);
				$("#wform").submit();
			}
		}
	});
	var years=$("#years").val();
	if(years==""){
	year=0;
	}else{
		$("#year").attr("value",years);
	}
	 $( "#dialog-message" ).dialog({
                autoOpen: false,
                modal: false,
                width:500,
                close:function(){
                    $("#hisTb").html("");
                }
            });
	
});

function history(id,content,time){
	var url="/portal/project/work/work_history_edit.jsp";
	var array=new Array();
	array[0]=id;
	array[1]=content;
	array[2]=time;
	//var returnVal=window.showModalDialog(url ,array,'dialogWidth='+800+'px;dialogHeight='+ 300+'px;resizable=no;help=no;center=yes;status=no;scroll=no;edge=sunken'); 
	$.post("<s:url value="/project/progress/progress.action"/>",{"workProgressId":id,"format":"json"},function(data){
                var html = '';
                $.each(data,function(i,n){
               		 //html+='<input type="hidden" id="workProgressId" name="workProgressId" value="'+n.workProgressId+'"/>';
                   	 html+='<tr><td><input type="hidden" id="workSecludeId" name="workProgress.workSecludeId" value="'+n.workSecludeId+'" /><input type="hidden" id="workProgressId" name="workProgressId" value="'+n.workProgressId+'" /><textarea rows="5" cols="1" id="progress" name="workProgress.progress" >'+n.progress+'</textarea></td></tr>';
                	 html+='<tr><td class="t_c"><input type="button" id="submits" value="保 存" onclick="saveAjaxForm()"/></td></tr>';
                	 $("#workProgressId").val(n.workProgressId);
                });
                $("#hisTb").html(html);
                $( "#dialog-message").dialog( "open" );

            },"json");
	
	$( "#dialog-message").dialog( "open" );
	//window.location.reload();
}
function saveAjaxForm(){
	if($("#progress").val().length<=250&&$("#progress").val().length>0){
		$.ajax({
			url:"/portal/project/progress/save.action",
			data:$("#ajaxForm").serialize(),
			dataType:"text",
			async:false,
			type:"post",
			error:function(data,status){
			alert(status);
			},
			success: function(data){
				$( "#dialog-message").dialog( "close" );
				window.location.reload();
			}
			
		});
	}else{
		alert("推进情况不能为空且长度不能超过250个汉字");
	}
}

function back(){
window.location="/portal/project/work/workSecludes.action";
}
</script>
</head>

<body>
 
<div class="main">
    <!--Ctrl-->
    <div class="ctrl clearfix">
        <div class="fl"><img id="show" onclick="showHide();" src="<s:url value="/css/default/images/sideBar_arrow_right.jpg"/>" width="46" height="30" title="收起"></div>
        <div class="posi fl">
            <ul>
                <li class="fin">重点工作基本信息</li>
            </ul>
        </div>
    </div>
    <!--Ctrl End-->
    <!--Filter--><!--Filter End-->
    <!--Table-->

    <div class="mb10 pt45">
        <s:form action="save" namespace="/project/work" method="post" id="wform"> 
            <input  type="hidden" id="workSecludeId" name="workSecludeId" value="<s:property value='workSeclude.workSecludeId'/>"/>
			<input  type="hidden" id="confirm" name="workSeclude.confirm" value="<s:property value='workSeclude.confirm'/>"/>
            <table width="100%"  class="table_1">
                <thead>
                <th colspan="4" class="t_r">
                    &nbsp;</th>
                </thead>
                <tbody>
                <tr>
                	<td class="t_r lableTd">选择年份：</td>
                	<td id="yearcon">
                	<s:select name="workSeclude.year" id="year"
                           list="#{'2014':'2014年度','2015':'2015年度','2016':'2016年度','0':'--请选择年份--'}" value="0"></s:select>
                	</td>
                	<input type="hidden" id="years" value="<s:property value="workSeclude.year"/>" />
                </tr>
                <tr>
                    <td class="t_r lableTd">重点工作：</td>
                    <td >
                        <s:textfield name="workSeclude.workName" cssClass="input_xxlarge" id="workName"/>
                    </td>
                    <td class="t_r lableTd">联系人：</td>
                    <td>
                    	
                        <s:property value="#session.user.name"/>
                    </td>
                </tr>
                <tr>
                    <td class="t_r lableTd">联系人电话：</td>
                    <td>
                       <s:textfield name="workSeclude.contactTel" cssClass="input_xxlarge" id="contactTel"/> 
                    </td>
                    <td class="t_r lableTd">对应绩效考核项：</td>
                    <td>
                       <s:textfield name="workSeclude.kpi" cssClass="input_xxlarge" id="kpi"/> 
                    </td>
                </tr>

                <tr>
                    <td class="t_r lableTd">责任单位：</td>
                    <td>
                          <s:textfield name="workSeclude.responsible" cssClass="input_xxlarge" id="responsible"/>
                    </td>
                    <td class="t_r lableTd">配合单位：</td>
                    <td>
                      <s:textfield name="workSeclude.cooperate" cssClass="input_xxlarge" id="cooperate"/>
                    </td>
                </tr>

                <tr>
                    <td class="t_r lableTd">目标及要求：</td>
                    <td colspan="3">
                        <s:textarea cssStyle="width:100%;height:50px;" name="workSeclude.objective" cssClass="input_xxlarge" id="objective"/>
                    </td>
                </tr>
                
                
                <tr>
                    <td class="t_r lableTd">推进情况：</td>
                    <td colspan="3">
                        <s:textarea cssStyle="width:100%;height:50px;" id="progress" name="workSeclude.progress" cssClass="input_xxlarge"/>
                    </td>
                </tr>
                <tr>
                    <td class="t_r lableTd">补充说明：</td>
                    <td colspan="3">
                        <s:textarea cssStyle="width:100%;height:50px;" id="memo" name="workSeclude.memo" cssClass="input_xxlarge"/>
                    	
                    </td>
                </tr>
                <s:if test="workSeclude.confirm==1">
                <tr>
                    <td colspan="4" style="height: 300px;">
                        <!--加载页面work_history_progress.jsp-->
                        <iframe frameborder="0" width="100%" style="height: 300px;" src="<s:url namespace="/project/progress" action="workProgresses"/>"></iframe>
                    </td>
                </tr>
                </s:if>
                <tr class="tfoot">
                    <td colspan="4" class="t_r">
                        <input type="submit" id="submits" value="保 存" onclick="return checkout();" />&nbsp;
                        
                       
                       <s:if test='workSeclude==null||workSeclude.confirm==0'>
                        <input type="button" value="确认发布" id="release" />&nbsp;<!-- onclick="location.href='<s:url namespace="/project/work" action="confirm.action"/>'" -->
                       	</s:if>
                        <input type="button" onclick="back();" value="后 退" onclick=""/>&nbsp;
                        <input type="reset" value="重 置" />&nbsp;</td>
                </tr>

                </tbody>

            </table>
        </s:form>
    </div>
    <!--Table End-->
</div>

<div id="dialog-message" title="历史推进情况" style="border-color: navy;border: 2px;width:1200px;background-color: yellow;" >
	<form action="/portal/project/progress/save.action"style="width: 100%;" method="post" id="ajaxForm">
    	<table width="100%" class="table_1">
        <thead>
        <tr class="tit">
            <td class="t_c" width="200">推进情况</td>
        </tr>
        </thead>
        <tbody id="hisTb"> 
        	
			
        </tbody>
    </table>
    </form>
</div>


</body>
</html>
