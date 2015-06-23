<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
	<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>
<meta charset="utf-8" />
<meta http-equiv="x-ua-compatible" content="IE=9">
<title>工会立功竞赛</title>
<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/default/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
<link type="text/css" href="<%=path%>/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
<link rel="stylesheet" href="<%=path%>/union/css/tree/style.css" type="text/css">

<script src="<%=path%>/js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="<%=path%>/union/js/tree/jquery.tree.core.min.js"></script>
<script type="text/javascript" src="<%=path%>/union/js/tree/jquery.tree.excheck.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/flick/jquery-ui-1.8.18.custom.min.js"></script>
<script src="../js/flick/jquery.ui.datepicker-zh-CN.js"></script>
<script src="<%=path%>/js/jquery.form.js"></script>
<script src="<%=path%>/js/jquery.formalize.js"></script>
<script src="<%=path%>/union/js/custom_widgets.js"></script>

	<script type="text/javascript">
        $(document).ready(function () {
        	$.initContextPath("<%=path%>");
        	
        	$(".datepicker").datepicker();
            
    		var timer = setInterval(function() {
    			checkInputWord(350);
    		}, 100);
        });
        
        function checkInputWord(allowLength){
    		var num = $("#txtContent").attr("value").length;
    		$("#pLetterCount span:eq(0)").text(allowLength - num);
    		
    		if (num > allowLength) {
    			//去掉该汉字
    			$("#txtContent").attr("value", $.trim($("#txtContent").attr("value")).substring(0, allowLength));
    			if ($("#pLetterCount span:eq(0)").length > 0)
    				$("#pLetterCount span:eq(0)").text(0);
	    			//对象失去焦点，同时弹出(setTimeout是兼容IE检查超出规定字数粘贴的时候焦点blur不了的bug)
	    			setTimeout(function() {
	    				$("#txtContent").blur();
	    				//再去掉一次，防止输入太快前面去的不干净
	    				$("#txtContent").attr("value", $.trim($("#txtContent").attr("value")).substring(0, allowLength));
	    			}, 0);
    		}    		
        }
        
      	var handleOptions = {
    			cache:false,
    			type:'post',
    			callback:null,
    			beforeSubmit:function(){$('#saveBtn').attr('disabled','true');},
    			url:'<%=path%>/unionTeamPrize/save.action',
    		    success:function(data){
    				if(data=="1"){
    					alert("保存成功");
						shut();
    				}else{
    					alert("服务器连接失败，请稍后再试!");
    				}
    				$('#saveBtn').removeAttr('disabled');
    				return false;
    		    },error: function() { alert("服务器连接失败，请稍后再试!"); $('#saveBtn').removeAttr('disabled');}	
    		};
          
        function doSubmit(){
      		var checkResult = checkForm();
    		if(checkResult){
    			alert(checkResult);
    			return false;
    		}        	  
        	if(confirm('确认要提交吗？')){$('#form').ajaxSubmit(handleOptions);}
          }
          
          function checkForm(){
				if($('input[name=name]').val()==''){
					$('input[name=name]').focus();
					return '请输入集体名称！';
				}
				
				if($('input[name=persons]').val()==''){
					$('input[name=persons]').focus();
					return '请输入人数！';
				}				
				
				var r = /^\d+$/;
				if(!r.test($('input[name=persons]').val())){
					$('input[name=persons]').focus();
					return '人数请输入整数！';
				}				
				
				if($('input[name=responsibilePerson]').val()==''){
					$('input[name=responsibilePerson]').focus();
					return '请输入责任人！';
				}
				
				if($('input[name=telephone]').val()==''){
					$('input[name=telephone]').focus();
					return '请输入联系电话！';
				}						
				
				if($('[name=introduct]').val()==''){
					$('[name=introduct]').focus();
					return '请输入简要事迹！';
				}			
				
				if($("[name=introduct]").val().length>350){
					$("[name=introduct]").focus();
					return("简要事迹只限输入350字！");
				}				
          }  
          
  		function shut(){
  		  window.opener=null;
  		  window.open("","_self");
  		  window.close();
  		}          
    </script>

	<style>
	span{display: inline;}
	.redMark{color:red;display:inline;}
	</style>

</head>

<body>
<div class="main">
    <!--Ctrl-->
    <div class="ctrl clearfix">
        <div class="fl"><img id="show" onclick="showHide();" src="<%=path%>/css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
        <div class="posi fl">
            <ul>
                <li class="fin">集体奖项申报资料</li>
            </ul>
        </div>
        <div style="display:none;" class="fr lit_nav nwarp">
            <ul>
                <li class="selected"><a class="print" href="#">打印</a></li>
                <li><a class="express" href="#">导出数据</a></li>
                <li class="selected"><a class="table" href="#">表格模式</a></li>
                <li><a class="treeOpen" href="#">打开树</a></li>
                <li><a class="filterClose" href="#">关闭过滤</a></li>
            </ul>
        </div>
    </div>
    <!--Ctrl End-->
    <!--Filter--><!--Filter End-->
    <!--Table-->

    <div class="mb10 pt45">
        <form id="form" method="post">
        	<s:if test="#request.teamPrize==null">
        		<input type="hidden" name="prizeId" value="${prize.id}"/>
        		<input type="hidden" name="applyId" value="${applyId}"/>
        	</s:if>
        	<s:else>
        		<input type="hidden" name="prizeId" value="${teamPrize.prizeId}"/>
        		<input type="hidden" name="applyId" value="${teamPrize.applyId}"/>
        	</s:else>
        	<input type="hidden" name="rejected" value="0"/>           
        	<input type="hidden" name="modified" value="${param.modified==1?1:0}"/>
            <input type="hidden" name="id" value="${teamPrize.id}"/>
            <input type="hidden" name="cUser" value="${teamPrize.cUser}"/>
            <input type="hidden" name="cUserName" value="${teamPrize.cUserName}"/>
            <input type="hidden" name="cTime" value="${teamPrize.cTime}"/>           
			<input type="hidden" name="deptId" value="${teamPrize.deptId}"/>
            <input type="hidden" name="deptName" value="${teamPrize.deptName}"/>             
            <table width="100%"  class="table_1">
                <thead>
                <th colspan="4" class="t_r">
                    &nbsp;</th>
                </thead>
                <tbody>
                <tr>
                    <td class="t_r lableTd" width="20%"><font class="redMark">*&nbsp;</font>集体名称(单位名称+集体名称)：</td>
                    <td width="30%">
                        <input class="input_xxlarge" type="text" name="name" maxlength="200" value="${teamPrize.name}">
                    </td>
                    <td class="t_r lableTd" width="20%"><font class="redMark">*&nbsp;</font>人数：</td>
                    <td width="30%">
                        <input type="text" class="input_xxlarge" maxlength="9" name="persons" value="${teamPrize.persons}"/>
                    </td>
                </tr>

                <tr>
                    <td class="t_r lableTd"><font class="redMark">*&nbsp;</font>责任人：</td>
                    <td>
                        <input class="input_xxlarge" type="text" name="responsibilePerson" maxlength="200"
                               value="${teamPrize.responsibilePerson}">
                    </td>

                    <td class="t_r lableTd"><font class="redMark">*&nbsp;</font>联系电话：</td>
                    <td>

                        <input type="text" class="input_xxlarge" name="telephone" maxlength="50" value="${teamPrize.telephone}"/>
                    </td>
                </tr>

                <tr>
                    <td class="t_r lableTd"><font class="redMark">*&nbsp;</font>简要事迹（限定350字以内）：</td>
                    <td colspan="3">
                        <textarea  rows="10" name="introduct" id="txtContent">${teamPrize.introduct}</textarea >
						<p id="pLetterCount">还可输入<span>350</span>字</p>
                    </td>
                </tr>
                
                </tbody>
                <tr class="tfoot">
                    <td colspan="4" class="t_r"><input type="button" id="saveBtn" onclick="doSubmit();" value="保 存"/>&nbsp;
                        <input type="reset" value="重 置" />&nbsp;</td>
                </tr>

            </table>
        </form>
    </div>
    <!--Table End-->
</div>
</body>
</html>