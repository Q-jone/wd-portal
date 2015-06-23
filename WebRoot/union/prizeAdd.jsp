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
	var rangeSelectorHtml = '<select name="personRange" onchange="setRange()">'+
        		'<option value="">请选择</option>'+
        		'<option value="1">单位</option>'+
        		'<option value="2">集体人数<=20人</option>'+
        		'<option value="3">20人< 集体人数 < 50人</option>'+
        		'<option value="4">集体人数 >= 50人</option>'+
    			'</select>';
        $(document).ready(function () {
        	$.initContextPath("<%=path%>");
        	
        	$('[name=prizeType]').val('${prize.prizeType}');
        	$('[name=prizeSubType]').val('${prize.prizeSubType}');
        	$('[name=personRange]').val('${prize.personRange}');

        });
        
        function addApplicantDept(applicantDept){
            var info = "" ;
            $("#applicatDept,#applicantDeptDiv").html("");
            $.each(applicantDept,function(i,n){
                info = info+n.departmentName+";"

                $("#applicantDeptDiv").append("<input type='hidden' name='applyDepartmentList["+i+"].prizeId' value='"+ n.prizeId+"'/><input type='hidden' name='applyDepartmentList["+i+"].deptName' value='"+ n.departmentName+"'/><input type='hidden' name='applyDepartmentList["+i+"].deptId' value='"+ n.departmentId+"'/>")
            })
            $("#applicatDept").text(info);
        }
        
      	var handleOptions = {
    			cache:false,
    			type:'post',
    			callback:null,
    			beforeSubmit:function(){$('#saveBtn').attr('disabled','true');},
    			url:'<%=path%>/unionPrize/save.action',
    		    success:function(data){
    				if(data=="1"){
    					alert("操作成功");
						shut();
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
				if($('[name=prizeType]').val()==''){
					$('[name=prizeType]').focus();
					return '请选择奖项类型！';
				}
				if($('[name=prizeSubType]').val()==''){
					$('[name=prizeSubType]').focus();
					return '请选择奖项类型！';
				}
				if($('[name=prizeType]').val()!='1' && $('[name=personRange]').val()==''){
					$('[name=personRange]').focus();
					return '请选择参与人数！';
				}				
				if($('[name=prizeName]').val()==''){
					$('[name=prizeName]').focus();
					return '请输入奖项名称！';
				}
				if($('[name=bonus]').val()==''){
					$('[name=bonus]').focus();
					return '请输入奖金！';
				}		
				if($('[name=quantity]').val()==''){
					$('[name=quantity]').focus();
					return '请输入数量！';
				}					
				
				if(!$.isNumeric($('input[name=bonus]').val())){
					$('input[name=bonus]').focus();
					return '奖金请输入数字！';
				}				
				
				var r = /^\d+$/;
				if(!r.test($('input[name=quantity]').val())){
					$('input[name=quantity]').focus();
					return '数量请输入整数！';
				}
				
				var prizeSubType = $('[name=prizeSubType]').val();
				var prizeType = $('[name=prizeType]').val();
				var personRange = $('[name=personRange]').val();
				var bonus = $('input[name=bonus]').val()*1;
				if(prizeSubType=='1'){
					if(prizeType=='1'){
						if(bonus < 500 || bonus > 800){
							return "奖金不符合规则！";
						}
					}else{
						if(personRange == '1'){
							if(bonus < 8000 || bonus > 12000){
								return "奖金不符合规则！";
							}
						}else if(personRange == '2'){
							if(bonus < 2000 || bonus > 4000){
								return "奖金不符合规则！";
							}							
						}else if(personRange == '3'){
							if(bonus < 4000 || bonus > 6000){
								return "奖金不符合规则！";
							}							
						}else if(personRange == '4'){
							if(bonus < 6000 || bonus > 8000){
								return "奖金不符合规则！";
							}							
						}
					}					
				}else if(prizeSubType=='2'){
					if(prizeType=='1'){
						if(bonus != 600){
							return "奖金不符合规则！";
						}
					}else{
						if(personRange == '1'){
							if(bonus != 10000){
								return "奖金不符合规则！";
							}
						}else if(personRange == '2'){
							if(bonus != 3000){
								return "奖金不符合规则！";
							}
						}else if(personRange == '3'){
							if(bonus != 5000){
								return "奖金不符合规则！";
							}
						}else if(personRange == '4'){
							if(bonus != 7000){
								return "奖金不符合规则！";
							}
						}
					}										
				}

          }  
          
  		function shut(){
  		  window.opener=null;
  		  window.open("","_self");
  		  window.close();
  		}          
  		
  		function setRange(){
  			$('[name=bonus]').attr("readonly",false).val("").removeAttr("placeholder");
  			
  			var prizeType = $('select[name=prizeType]').val();
  			var prizeSubType = $('select[name=prizeSubType]').val();
  			var personRange = $('select[name=personRange]').val();
  			if(prizeType && prizeSubType && (personRange || prizeType=='1') ){
  				var range = '';
				if(prizeSubType=='1'){
					if(prizeType=='1'){
						range = '500 ~ 800';
					}else{
						if(personRange == '1'){
							range = '8000 ~ 12000';
						}else if(personRange == '2'){
							range = '2000 ~ 4000';			
						}else if(personRange == '3'){
							range = '4000 ~ 6000';			
						}else if(personRange == '4'){
							range = '6000 ~ 8000';			
						}
					}				
					$('[name=bonus]').attr("readonly",false).attr("placeholder",range).placeholder();
				}else if(prizeSubType=='2'){
					if(prizeType=='1'){
						range = '600';
					}else{
						if(personRange == '1'){
							range = '10000';
						}else if(personRange == '2'){
							range = '3000';
						}else if(personRange == '3'){
							range = '5000';
						}else if(personRange == '4'){
							range = '7000';
						}
					}						
					$('[name=bonus]').attr("readonly",true).val(range).css("color","#000");
				}
  			}
  		}
  		
  		function onTypeChange(obj){
  			if(obj.value=='1'){
  				$('#personRange_setting').html('');
  			}else{
  				$('#personRange_setting').html(rangeSelectorHtml);  				
  			}
  			setRange();
  		}
    </script>

	<style>
	span{display: inline;}
	.phcolor{color:#FF0000;}
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
                <li class="fin">竞赛奖项表单</li>
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
        <form id="form" action="" method="post">
            <div id="applicantDeptDiv"></div>
        	<s:if test="#request.prize==null">
        		<input type="hidden" name="matchId" value="${param.matchId}"/>
        	</s:if>
        	<s:else>
        		<input type="hidden" name="matchId" value="${prize.matchId}"/>
        	</s:else>     
        	            
            <input type="hidden" name="id" value="${prize.id}"/>
            <table width="100%"  class="table_1">
                <thead>
                <th colspan="4" class="t_r">
                    &nbsp;</th>
                </thead>
                <tbody>
                <tr>
                    <td class="t_r lableTd"><font class="redMark">*&nbsp;</font>奖项类型：</td>
                    <td>
                        <select name="prizeType" onchange="onTypeChange(this)">
                            <option value="">请选择</option>
                            <option value="1">个人类</option>
                            <option value="2">集体类</option>
                            <option value="3">项目类</option>
                            <option value="4">项目成果类</option>
                        </select><select name="prizeSubType" style="margin-left: 10px;" onchange="setRange()">
                        <option value="">请选择</option>
                        <option value="1">分等次竞赛</option>
                        <option value="2">不分等次竞赛</option>
                    </select>
                    </td>
                    <td class="t_r lableTd">参与人数：</td>
                    <td id="personRange_setting">
                    	<s:if test="#request.prize.prizeType!=1">
                        <select name="personRange" onchange="setRange()">
                            <option value="">请选择</option>
                            <option value="1">单位</option>
                            <option value="2">集体人数<=20人</option>
                            <option value="3">20人< 集体人数 < 50人</option>
                            <option value="4">集体人数 >= 50人</option>
                        </select>
                        </s:if>
                    </td>                    
                </tr>

                <tr>
                    <td class="t_r lableTd"><font class="redMark">*&nbsp;</font>奖项名称：</td>
                    <td>
                        <input class="input_xxlarge" type="text" name="prizeName"
                               value="${prize.prizeName}">
                    </td>

                    <td class="t_r lableTd"><font class="redMark">*&nbsp;</font>奖金：</td>
                    <td>
                        <input class="input_xxlarge" type="text" name="bonus"
                               value="${prize.bonus}">
                    </td>
                </tr>

                <tr>

                    <td class="t_r lableTd"><font class="redMark">*&nbsp;</font>数量：</td>
                    <td colspan="3">
                        <input class="input_xxlarge" type="text" name="quantity"
                               value="${prize.quantity}">

                    </td>
                </tr>
<!--                 <tr>
                    <td class="t_r lableTd">参赛单位：</td>
                    <td colspan="3">
                        <textarea class="form-field small" rows="5" readonly name="applicatDept"  id="applicatDept"></textarea><input type="button" id="selDepartment" value="选择参赛单位"/>
                    </td>
                </tr> -->
                
                <tr>
                    <td class="t_r lableTd">奖金设置规则：</td>
                    <td colspan="3">
                        <img src="<%=path%>/union/images/prize_rule.png"/>注：项目类和成果类奖金参考集体类奖金校验规则
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
<div style="display:none;" id="departmentDialog" title="选择考评部门">
    <ul id="departmentTree" class="ztree"></ul>
</div>
</body>
</html>