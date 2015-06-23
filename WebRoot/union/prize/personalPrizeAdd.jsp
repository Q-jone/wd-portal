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
        	
        	$(".datepicker").datepicker({
        		inline: true,  
        		closeText: '确定',
        		changeMonth: true,
        		changeYear: true,
        		showButtonPanel: true,
        		onChangeMonthYear: function(year, month, inst) {
        		   var month = $("#ui-datepicker-div .ui-datepicker-month option:selected").val();//得到选中的月份值
        		   var year = $("#ui-datepicker-div .ui-datepicker-year option:selected").val();//得到选中的年份值
        		   $(inst.input[0]).val(year+'年'+(parseInt(month)+1)+'月');//给input赋值，其中要对月值加1才是实际的月份
        		},
        		onClose: function(dateText, inst) {
         		   var month = $("#ui-datepicker-div .ui-datepicker-month option:selected").val();//得到选中的月份值
         		   var year = $("#ui-datepicker-div .ui-datepicker-year option:selected").val();//得到选中的年份值
         		   $(inst.input[0]).val(year+'年'+(parseInt(month)+1)+'月');//给input赋值，其中要对月值加1才是实际的月份
         		},
                yearRange: '1900:+1'
        	});
        	
        	$('[name=gender]').val('${personalPrize.gender}');
        	$('[name=political]').val('${personalPrize.political}');
        	
    		var timer = setInterval(function() {
    			checkInputWord(400);
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
    			url:'<%=path%>/unionPersonalPrize/save.action',
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
					return '请输入姓名！';
				}
				
				if($('[name=gender]').val()==''){
					$('[name=gender]').focus();
					return '请选择性别！';
				}			
				
				if($('input[name=brithday]').val()==''){
					$('input[name=brithday]').focus();
					return '请输入生日！';
				}
				
				if($('[name=political]').val()==''){
					$('[name=political]').focus();
					return '请选择政治面貌！';
				}				

				if($('input[name=position]').val()==''){
					$('input[name=position]').focus();
					return '请输入职位！';
				}

				if($("[name=prizeInfo]").val()==''){
					$("[name=prizeInfo]").focus();
					return("请输入曾获荣誉！");
				}				
				
				if($("[name=introduct]").val()==''){
					$("[name=introduct]").focus();
					return("请输入主要事迹！");
				}
				
				if($("[name=prizeInfo]").val().length>1000){
					$("[name=prizeInfo]").focus();
					return("曾获荣誉只限输入1000字！");
				}				
				
				if($("[name=introduct]").val().length>400){
					$("[name=introduct]").focus();
					return("主要事迹只限输入400字！");
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
	.ui-datepicker-calendar {
    	display: none;
    }
    .ui-datepicker-current {
    	display: none;
    }
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
                <li class="fin">个人奖项申报资料</li>
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
        	<s:if test="#request.personalPrize==null">
        		<input type="hidden" name="prizeId" value="${prize.id}"/>
        		<input type="hidden" name="applyId" value="${applyId}"/>
        	</s:if>
        	<s:else>
        		<input type="hidden" name="prizeId" value="${personalPrize.prizeId}"/>
        		<input type="hidden" name="applyId" value="${personalPrize.applyId}"/>
        	</s:else>        
			<input type="hidden" name="rejected" value="0"/>            
			<input type="hidden" name="modified" value="${param.modified==1?1:0}"/>
            <input type="hidden" name="id" value="${personalPrize.id}"/>
            <input type="hidden" name="cUser" value="${personalPrize.cUser}"/>
            <input type="hidden" name="cUserName" value="${personalPrize.cUserName}"/>
            <input type="hidden" name="cTime" value="${personalPrize.cTime}"/>
			<input type="hidden" name="deptId" value="${personalPrize.deptId}"/>
            <input type="hidden" name="deptName" value="${personalPrize.deptName}"/>
            <table width="100%"  class="table_1">
                <thead>
                <th colspan="4" class="t_r">
                    &nbsp;</th>
                </thead>
                <tbody>
                <tr>
                    <td class="t_r lableTd" width="20%"><font class="redMark">*&nbsp;</font>姓名：</td>
                    <td width="30%">
                        <input class="input_xxlarge" type="text" name="name" maxlength="200" value="${personalPrize.name}">
                    </td>
                    <td class="t_r lableTd" width="20%"><font class="redMark">*&nbsp;</font>性别：</td>
                    <td width="30%">
                        <select id="gender" name="gender">
                            <option value="">--请选择--</option>
                            <option value="男">男</option>
                            <option value="女">女</option>
                        </select>                    
                    </td>
                </tr>

                <tr>
                    <td class="t_r lableTd"><font class="redMark">*&nbsp;</font>出生年月：</td>
                    <td>
                        <input class="input_xxlarge datepicker" type="text" name="brithday" readOnly
                               value="${personalPrize.brithday}">
                    </td>

                    <td class="t_r lableTd"><font class="redMark">*&nbsp;</font>政治面貌：</td>
                    <td>
                        <select id="political" name="political">
                            <option value="">--请选择--</option>
                            <option value="中共党员">中共党员</option>
                            <option value="中共预备党员">中共预备党员</option>
                            <option value="民主党派">民主党派</option>
                            <option value="共青团员">共青团员</option>
                            <option value="群众">群众</option>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td class="t_r lableTd"><font class="redMark">*&nbsp;</font>职位：</td>
                    <td colspan="3">
                        <input class="input_xxlarge" type="text" name="position" maxlength="100" value="${personalPrize.position}">
                    </td>
                </tr>

                <tr>
                    <td class="t_r lableTd"><font class="redMark">*&nbsp;</font>曾获荣誉：</td>
                    <td colspan="3">
                        <textarea     rows="10"  name="prizeInfo">${personalPrize.prizeInfo}</textarea >
                    </td>
                </tr>

                <tr>
                    <td class="t_r lableTd"><font class="redMark">*&nbsp;</font>主要事迹（限定400字以内）：</td>
                    <td colspan="3">
                        <textarea rows="10" name="introduct" id="txtContent">${personalPrize.introduct}</textarea >
                        <p id="pLetterCount">还可输入<span>400</span>字</p>
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