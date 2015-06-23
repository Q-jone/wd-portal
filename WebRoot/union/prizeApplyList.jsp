<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.wonders.stpt.union.entity.bo.UnionApplyMatch"%>
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

<script src="<%=path%>/js/jquery-1.7.1.js"></script>
<script src="<%=path%>/deptDoc/js/ztree/jquery.ztree.all-3.4.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/flick/jquery-ui-1.8.18.custom.min.js"></script>
<script src="<%=path%>/js/flick/jquery.ui.datepicker-zh-CN.js"></script>
<script src="<%=path%>/js/jquery.form.js"></script>
<script src="<%=path%>/js/jquery.formalize.js"></script>
<script src="<%=path%>/union/js/messagebox.js"></script>
	<script type="text/javascript">
	
		//定义子窗口
		var sonWindow = null;
		//每1秒执行一次checkSonWindow()方法
		var refresh = setInterval("checkSonWindow()",1000);
		 //监测子窗口是否关闭
		function checkSonWindow(){
			if(sonWindow!=null && sonWindow.closed==true){
				window.location.href = "prizeApplyList.action?applyMatchId=${applyMatch.id}";
				clearInterval(refresh);
			}
		}
		 
        $(document).ready(function () {
        	initMessagebox();
        	initMessageboxClose();
        });
        
      //跳转到新增页面
        function showApply(prizeId){
			$.post(
					'<%=path%>/unionPrize/hasQuantityLeft.action?random='+Math.random(),
					{
						"prizeId": prizeId,
						"applyId": '${applyMatch.id}'
					},
					function(obj, textStatus, jqXHR){
						if(obj=="1"){
							sonWindow = window.open('showApply.action?prizeId='+prizeId+'&applyId=${applyMatch.id}');
						}else{
							alert("您的申报名额已满！");
							return false;
						}
					},
					"text"
				).error(function() { alert("服务器连接失败，请稍后再试!"); })    	  
        }
      
        function showApplyEdit(id,type){
        	var extraParam = '';
        	if('${applyMatch.status}'=='<%=UnionApplyMatch.MODIFY_STATUS%>'){
        		extraParam += '&modified=1';
        	}
        	if(type=='1'){
        		sonWindow = window.open('<%=path%>/unionPersonalPrize/showEdit.action?id='+id+extraParam);
        	}else if(type=='2'){
        		sonWindow = window.open('<%=path%>/unionTeamPrize/showEdit.action?id='+id+extraParam);
        	}else if(type=='3'){
        		sonWindow = window.open('<%=path%>/unionProjectPrize/showEdit.action?id='+id+extraParam);
        	}else if(type=='4'){
        		sonWindow = window.open('<%=path%>/unionAchivementPrize/showEdit.action?id='+id+extraParam);
        	}
        	
        }      
        
        function showApplyView(id,type){
        	if(type=='1'){
        		window.open('<%=path%>/unionPersonalPrize/showView.action?id='+id);
        	}else if(type=='2'){
        		window.open('<%=path%>/unionTeamPrize/showView.action?id='+id);
        	}else if(type=='3'){
        		window.open('<%=path%>/unionProjectPrize/showView.action?id='+id);
        	}else if(type=='4'){
        		window.open('<%=path%>/unionAchivementPrize/showView.action?id='+id);
        	}
        }           
        
        function doDel(id,type){
        	var url = '';
        	if(type=='1'){
        		url = '<%=path%>/unionPersonalPrize/doDel.action';
        	}else if(type=='2'){
        		url = '<%=path%>/unionTeamPrize/doDel.action';
        	}else if(type=='3'){
        		url = '<%=path%>/unionProjectPrize/doDel.action';
        	}else if(type=='4'){
        		url = '<%=path%>/unionAchivementPrize/doDel.action';
        	}
        	if(confirm('您确定要删除该条申请吗！')){
		   		 $.ajax({
		   		        type: "post",
		   		        url: url,
		   		        dataType: "json",
		   		        data:"id="+id+"&random="+Math.random(),
		   		        success: function (data) {
		   		        	alert("删除成功");
		   		        	window.location.href = "prizeApplyList.action?matchId=${match.id}&applyMatchId=${applyMatch.id}";
		   		        },
		   		        error: function (XMLHttpRequest, textStatus, errorThrown) {
		   		                alert("删除失败");
		   		        }
		   		});	 
        	}
   	}
        
        function showPrint(){
        	window.open('<%=path%>/unionPrize/prizeApplyList.action?op=print&applyMatchId=${applyMatch.id}');
        }     		        
      
        function showImport(prizeId){
        	sonWindow = window.open('<%=path%>/unionPrize/showBatchUpload.action?applyId=${applyMatch.id}&prizeId='+prizeId);
        }
        
       //跳转到制定页
       function goPage(pageNo,type){
       
       		//type=0,直接跳转到制定页
	       if(type=="0"){
	   	    	var pageCount = $("#totalPageCount").val();
	       		var number = $("#number").val();
	       		if(!number.match(/^[0-9]+$/)){
	       			alert("请输入数字");
	       			$("#number").val("");
	       			$("#number").focus();
	       			return ;
	       		}
	       		if(parseInt(number)>parseInt(pageCount)){
	       			$("#number").val(pageCount);
	       			$("#pageNo").val(pageCount);
	       		}else{
	       			$("#pageNo").val(number);
	       		}
	       }
			//type=1,跳转到上一页	       
	       if(type=="1"){
	       		$("#pageNo").val(parseInt($("#number").val())-1);
	       }
			//type=2,跳转到下一页	       
	       if(type=="2"){
	       		$("#pageNo").val(parseInt($("#number").val())+1);
	       }
	       
	       //type=3,跳转到最后一页,或第一页
	       if(type=="3"){
	   	    	$("#pageNo").val(pageNo);
	       }
       	   $("#form").submit();
       }
       
       function clearInput(){
    	   $('.fi').val('');
       }
       
       function modifyQuantity(prizeId,quantity){
         	 $("#applyDepartmentDialog").dialog({
         		title: '参赛单位',
   				modal: true,
   				autoOpen: false,
   				width: 360,
   				height: 450,
   				zIndex: 9999,
   				buttons: [
   					{
   						text: "关闭",
   						click: function() {
   							$( this ).dialog( "close" );
   						}
   					}
   				]});    	  
       	  var surl = "<%=path%>/unionPrize/listApplyDepartments.action?prizeId="+prizeId+"&quantity="+quantity;
       	  var frame = '<iframe frameBorder="0" scrolling="yes" src="'+surl+'" style="HEIGHT:350px; VISIBILITY: inherit; WIDTH:320px; Z-INDEX:999"></iframe>'
       	  $('#applyDepartmentDialog').html(frame);
       	  $("#applyDepartmentDialog").dialog('open');
         }
       
     	var handleOptions = {
    			cache:false,
    			type:'post',
    			callback:null,
    			url:'<%=path%>/unionApplyMatch/flowDeal.action',
    		    success:function(data){
    				if(data=="1"){
    					alert("操作成功!");
						shut();
    				}else if(data=="2"){
    					alert("请将所有奖项名额申报后再提交!");
    				}
    				return false;
    		    },error: function() { alert("服务器连接失败，请稍后再试!"); }	
    		};
          
  		function shut(){
  		  window.opener=null;
  		  window.open("","_self");
  		  window.close();
  		}        
       
   	function closeDialog(){
		$("#applyDepartmentDialog").dialog('close');  
	}       
    </script>

	<style>
		div.jqi{ width:770px}
		.ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }	
	</style>

</head>

<body>
<form action="" id="form" method="post">
	<input type="hidden" name="applyId" value="${applyMatch.id}"/>
	<input type="hidden" name="id" value="${match.id}"/>
    <input type="hidden" name="status" value="${applyMatch.status}"/>
    <input type="hidden" name="checkRole" value="${checkRole}"/>
<div class="main f_bg">
    <div class="ctrl clearfix">
        <div class="fl"><img id="show" onclick="showHide();" src="<%=path%>/css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
        <div class="posi fl">
            <ul>
                <li><a href="<%=path%>/match/matches">专项主题</a></li>
                <li class="fin">专项主题明细</li>
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
    <div class="pt45">

        <table width="100%"  class="table_1">
            <thead>
            <th colspan="4" style="text-align:center;"><strong>竞赛主题信息</strong></th>
            </thead>
            <tbody>
            <tr>
                <td class="t_r lableTd" width="15%">年度:</td>
                <td width="35%">
                ${theme.year}
                </td>
                <td class="t_r lableTd" width="15%">竞赛名称:</td>
                <td width="35%">
                ${theme.themeName}
                </td>
            </tr>       
            </tbody>
        </table>

    </div>    
    <div>

        <table width="100%"  class="table_1">
            <thead>
            <th colspan="4" style="text-align:center;"><strong>专项主题信息</strong></th>
            </thead>
            <tbody>
            <tr>
                <td class="t_r lableTd" width="15%">类别:</td>
                <td width="35%">${match.matchType}</td>
                <td class="t_r lableTd" width="15%">专项主题名:</td>
                <td width="35%">${match.matchName}</td>
            </tr>
            <tr>
                <td class="t_r lableTd">开始日期:</td>
                <td>${match.beginDate}</td>
                <td class="t_r lableTd">结束日期:</td>
                <td>${match.endDate}</td>
            </tr>
            <tr>
                <td class="t_r lableTd">附件:</td>
                <td colspan="3">
					<iframe scrolling="auto" height="80" frameborder="1" border="1" marginheight="0" marginwidth="0" width="100%" id="attachFrame" name="attachFrame" src="/portal/attach/loadFileList.action?fileGroup=attach&fileGroupName=attachGroup&fileGroupId=${match.attach}&userName=<s:property value="#session.userName"/>&loginName=<s:property value="#session.loginName"/>&procType=view&targetType=frame&type=1"></iframe>
                </td>
            </tr>     
                <tr class="tfoot nprint">
                    <td colspan="4" style="text-align:center">
	                    <s:if test="#request.applyMatch.handlerId.substring(0,12)==#session.t_login_name">
	                    	<s:set name="dealBtnName" value="'审 批'"/>
                            <s:if test="#request.applyMatch.status==@com.wonders.stpt.union.entity.bo.UnionApplyMatch@APPLY_STATUS || #request.applyMatch.status==@com.wonders.stpt.union.entity.bo.UnionApplyMatch@MODIFY_STATUS">
                            	<s:set name="dealBtnName" value="'提 交'"/>
                            </s:if>	                    	
	                        <input type="button" id="todo_handle" value="<s:property value="#dealBtnName"/>">&nbsp;
	                    </s:if>
                    	<input type="button" onclick="showPrint();" value="打 印"/>&nbsp;
                        <input type="button" onclick="shut();" value="关 闭"/>&nbsp;
                    </td>
                </tr>                 
            </tbody>
        </table>

    </div>
    
        
    <div class="filter">
        <div class="fn clearfix">
            <h5 class="fl"><a href="#" class="colSelect fl">可申报奖项列表</a></h5>
        </div>
    </div>
    <div>
        <table width="100%"  class="table_1">
            <tbody>
            <tr class="tit">
                <td class="t_c" width="30">序号</td>
                <td class="t_c">类别</td>
                <td class="t_c">奖项名称</td>
                <td class="t_c">名额</td>
                <td class="t_c">奖金</td>
                <td class="t_c" width="250">操作</td>
            </tr>

			<s:iterator value="#request.deptPrizeList" id="data" status="s">
            <tr class="odd">
                <td class=" first t_c"><input type="hidden" value="">
                <s:property value="#s.index+1"/>
                </td>
                <td class="t_c">
                    <s:if test="#data.prize.prizeType == 1">
                        	个人类
                    </s:if>
                    <s:if test="#data.prize.prizeType == 2">
                        	集体类
                    </s:if>
                    <s:if test="#data.prize.prizeType == 3">
                        	 项目类
                    </s:if>
                    <s:if test="#data.prize.prizeType == 4">
                        	项目成果类
                    </s:if>                                                            
				</td>
                <td class="t_c"> ${data.prize.prizeName}</td>
                <td class="t_c"> ${data.quantity}</td>
                <td class="t_c"> ${data.prize.bonus}</td>
                <td class="t_c">
                	<s:if test="#request.applyMatch.handlerId.substring(0,12)==#session.t_login_name">
                	<s:if test="#request.applyMatch.status==@com.wonders.stpt.union.entity.bo.UnionApplyMatch@APPLY_STATUS || #request.applyMatch.status==@com.wonders.stpt.union.entity.bo.UnionApplyMatch@MODIFY_STATUS">
                    	<a class="mr5" style="display: inline;cursor:pointer;" onclick="showApply('${data.prize.id}')">填报</a>
                    	<a class="mr5" style="display: inline;cursor:pointer;" onclick="showImport('${data.prize.id}')">导入</a>
                    </s:if>
                    </s:if>
                </td>
            </tr>
			</s:iterator>
            </tbody>
        </table>

    </div>
    
    <div class="filter">
        <div class="fn clearfix">
            <h5 class="fl"><a href="#" class="colSelect fl">已申报奖项
            <%-- <s:if test="#request.applyMatch.status==@com.wonders.stpt.union.entity.bo.UnionApplyMatch@MODIFY_STATUS && #request.applyMatch.handlerId.substring(0,12)==#session.t_login_name">（被考评部门退回的以高亮显示）</s:if> --%>
            </a></h5>
        </div>
    </div>    
    <s:if test="null!=#request.appliedTeamPrizes&&!#request.appliedTeamPrizes.isEmpty()"> 
    <div>

        <table width="100%"  class="table_1">
            <thead>
            <th colspan="7" style="text-align:center;"><strong>先进集体</strong></th>
            </thead>
            <tbody>
            <tr class="tit">
                <td class="t_c" style="width:40px">序号</td>
                <td class="t_c">奖项名称</td>
                <td class="t_c">集体全称</td>
                <td class="t_c">人数</td>
                <td class="t_c">所属单位</td>
                <td class="t_c">申报人</td>
                <td class="t_c" style="width:100px">操作</td>
            </tr>            
            <s:iterator value="#request.appliedTeamPrizes" id="data" status="s">
            <tr <s:if test="(#data.rejected==1 && #data.cUser.substring(0,12)==#session.t_login_name)
            				||(#data.modified==1 && #request.applyMatch.status!=@com.wonders.stpt.union.entity.bo.UnionApplyMatch@MODIFY_STATUS)">style="background-color:#FFFF00"</s:if>>
                <td class="t_c" width="30"><s:property value="#s.index+1"/></td>
                <td class="t_c">${data.prize.prizeName}</td>
                <td class="t_c">${data.name}</td>
                <td class="t_c">${data.persons}</td>
                <td class="t_c">${data.deptName}</td>
                <td class="t_c">${data.cUserName}</td>
                <td class="t_c" width="250">
                <s:if test="#request.applyMatch.status==@com.wonders.stpt.union.entity.bo.UnionApplyMatch@APPLY_STATUS || #request.applyMatch.status==@com.wonders.stpt.union.entity.bo.UnionApplyMatch@MODIFY_STATUS">
                <s:if test="#data.cUser.substring(0,12)==#session.t_login_name">
                 	<a class="mr5" style="display: inline;cursor:pointer;" onclick="showApplyEdit('${data.id}','${data.prize.prizeType}')">修改</a>
					<a class="mr5" style="display: inline;cursor:pointer;" onclick="doDel('${data.id}','${data.prize.prizeType}')">删除</a>
				</s:if>
				</s:if>
					<a class="mr5" style="display: inline;cursor:pointer;" onclick="showApplyView('${data.id}','${data.prize.prizeType}')">查看</a>
                </td>
            </tr>
            </s:iterator>
            </tbody>
        </table>

    </div>        
	</s:if>
	<s:if test="null!=#request.appliedPersonalPrizes&&!#request.appliedPersonalPrizes.isEmpty()"> 
    <div>

        <table width="100%"  class="table_1">
            <thead>
            <th colspan="8" style="text-align:center;"><strong>先进个人</strong></th>
            </thead>
            <tbody>
            <tr class="tit">
                <td class="t_c" width="30">序号</td>
                <td class="t_c">奖项名称</td>
                <td class="t_c">姓名</td>
                <td class="t_c">性别</td>
                <td class="t_c">出生年月</td>
                <td class="t_c">所属单位</td>
                <td class="t_c">申报人</td>
                <td class="t_c" width="250">操作</td>
            </tr>            
            <s:iterator value="#request.appliedPersonalPrizes" id="data" status="s">
            <tr <s:if test="(#data.rejected==1 && #data.cUser.substring(0,12)==#session.t_login_name)
            				||(#data.modified==1 && #request.applyMatch.status!=@com.wonders.stpt.union.entity.bo.UnionApplyMatch@MODIFY_STATUS)">style="background-color:#FFFF00"</s:if>>
                <td class="t_c" width="30"><s:property value="#s.index+1"/></td>
                <td class="t_c">${data.prize.prizeName}</td>
                <td class="t_c">${data.name}</td>
                <td class="t_c">${data.gender}</td>
                <td class="t_c">${data.brithday}</td>
                <td class="t_c">${data.deptName}</td>
                <td class="t_c">${data.cUserName}</td>
                <td class="t_c" width="250">
                <s:if test="#request.applyMatch.status==@com.wonders.stpt.union.entity.bo.UnionApplyMatch@APPLY_STATUS || #request.applyMatch.status==@com.wonders.stpt.union.entity.bo.UnionApplyMatch@MODIFY_STATUS">
                <s:if test="#data.cUser.substring(0,12)==#session.t_login_name">
                 	<a class="mr5" style="display: inline;cursor:pointer;" onclick="showApplyEdit('${data.id}','${data.prize.prizeType}')">修改</a>
					<a class="mr5" style="display: inline;cursor:pointer;" onclick="doDel('${data.id}','${data.prize.prizeType}')">删除</a>
				</s:if>
				</s:if>
					<a class="mr5" style="display: inline;cursor:pointer;" onclick="showApplyView('${data.id}','${data.prize.prizeType}')">查看</a>
                </td>
            </tr>
            </s:iterator>
            </tbody>
        </table>
        
    </div>
    </s:if>
    <s:if test="null!=#request.appliedProjectPrizes&&!#request.appliedProjectPrizes.isEmpty()"> 
    <div>

        <table width="100%"  class="table_1">
            <thead>
            <th colspan="7" style="text-align:center;"><strong>优秀创新项目</strong></th>
            </thead>
            <tbody>
            <tr class="tit">
                <td class="t_c" width="30">序号</td>
                <td class="t_c">奖项名称</td>
                <td class="t_c">项目名称</td>
                <td class="t_c">承担单位</td>
                <td class="t_c">负责人</td>
                <td class="t_c">申报人</td>
                <td class="t_c" width="250">操作</td>
            </tr>            
            <s:iterator value="#request.appliedProjectPrizes" id="data" status="s">
            <tr <s:if test="(#data.rejected==1 && #data.cUser.substring(0,12)==#session.t_login_name)
            				||(#data.modified==1 && #request.applyMatch.status!=@com.wonders.stpt.union.entity.bo.UnionApplyMatch@MODIFY_STATUS)">style="background-color:#FFFF00"</s:if>>
                <td class="t_c" width="30"><s:property value="#s.index+1"/></td>
                <td class="t_c">${data.prize.prizeName}</td>
                <td class="t_c">${data.prjectName}</td>
                <td class="t_c">${data.deptName}</td>
                <td class="t_c">${data.responsibilePerson}</td>
                <td class="t_c">${data.cUserName}</td>
                <td class="t_c" width="250">
                <s:if test="#request.applyMatch.status==@com.wonders.stpt.union.entity.bo.UnionApplyMatch@APPLY_STATUS || #request.applyMatch.status==@com.wonders.stpt.union.entity.bo.UnionApplyMatch@MODIFY_STATUS">
                <s:if test="#data.cUser.substring(0,12)==#session.t_login_name">
                 	<a class="mr5" style="display: inline;cursor:pointer;" onclick="showApplyEdit('${data.id}','${data.prize.prizeType}')">修改</a>
					<a class="mr5" style="display: inline;cursor:pointer;" onclick="doDel('${data.id}','${data.prize.prizeType}')">删除</a>
				</s:if>
				</s:if>
					<a class="mr5" style="display: inline;cursor:pointer;" onclick="showApplyView('${data.id}','${data.prize.prizeType}')">查看</a>
                </td>
            </tr>
            </s:iterator>
            </tbody>
        </table>
        
    </div>    
    </s:if>
    <s:if test="null!=#request.appliedAchivementPrizes&&!#request.appliedAchivementPrizes.isEmpty()"> 
    <div>

        <table width="100%"  class="table_1">
            <thead>
            <th colspan="8" style="text-align:center;"><strong>品牌成果</strong></th>
            </thead>
            <tbody>
            <tr class="tit">
                <td class="t_c" width="30">序号</td>
                <td class="t_c">奖项名称</td>
                <td class="t_c">项目名称</td>
                <td class="t_c">参赛板块</td>
                <td class="t_c">申报集体全称</td>
                <td class="t_c">负责人</td>
                <td class="t_c">申报人</td>
                <td class="t_c" width="250">操作</td>
            </tr>            
            <s:iterator value="#request.appliedAchivementPrizes" id="data" status="s">
            <tr <s:if test="(#data.rejected==1 && #data.cUser.substring(0,12)==#session.t_login_name)
            				||(#data.modified==1 && #request.applyMatch.status!=@com.wonders.stpt.union.entity.bo.UnionApplyMatch@MODIFY_STATUS)">style="background-color:#FFFF00"</s:if>>
                <td class="t_c" width="30"><s:property value="#s.index+1"/></td>
                <td class="t_c">${data.prize.prizeName}</td>
                <td class="t_c">${data.prjectName}</td>
                <td class="t_c">${data.module}</td>
                <td class="t_c">${data.groupName}</td>
                <td class="t_c">${data.responsibilePerson}</td>
                <td class="t_c">${data.cUserName}</td>
                <td class="t_c" width="250">
                <s:if test="#request.applyMatch.status==@com.wonders.stpt.union.entity.bo.UnionApplyMatch@APPLY_STATUS || #request.applyMatch.status==@com.wonders.stpt.union.entity.bo.UnionApplyMatch@MODIFY_STATUS">
                <s:if test="#data.cUser.substring(0,12)==#session.t_login_name">
                 	<a class="mr5" style="display: inline;cursor:pointer;" onclick="showApplyEdit('${data.id}','${data.prize.prizeType}')">修改</a>
					<a class="mr5" style="display: inline;cursor:pointer;" onclick="doDel('${data.id}','${data.prize.prizeType}')">删除</a>
				</s:if>
				</s:if>
					<a class="mr5" style="display: inline;cursor:pointer;" onclick="showApplyView('${data.id}','${data.prize.prizeType}')">查看</a>
                </td>
            </tr>
            </s:iterator>
            </tbody>
        </table>
        
    </div>        
    </s:if>
    
    <div>

        <table width="100%"  class="table_1">
            <thead>
            <th colspan="4" style="text-align:center;"><strong>审核信息</strong></th>
            </thead>
            <tbody>
		  	<tr class="content6">
				<td class="pl18" colspan="4">
					<table width="100%" border="0">
					<tbody>
					<s:iterator value="#request.approvalInfos" id="data" status="s">
						<tr>
						<td style="border-bottom: 0px;">
								<div align="left">
								<s:property value="#data.remark"/><br>
								<div>
								<span style="float:right">
								<s:property value="#data.nodeName"/>&nbsp;&nbsp;<s:property value="#data.checkUser"/>&nbsp;<s:property value="#data.checkTime"/>
								</span>
								</div>
								</div>
						</td>
						</tr>
					</s:iterator>
					</tbody>
					</table>
				</td>
		  	</tr>            			
            </tbody>
        </table>

    </div>       
</div>
	<div class="transparent" id="maskDiv" style="display: none;z-index:99;" style="filter:alpha(opacity=30);opacity:0.3;"></div>
	<s:if test="#request.applyMatch.status==@com.wonders.stpt.union.entity.bo.UnionApplyMatch@APPLY_STATUS">
		<jsp:include page="flow/prizeApply.jsp"/>
	</s:if>
	<s:if test="#request.applyMatch.status==@com.wonders.stpt.union.entity.bo.UnionApplyMatch@DEPT_REVIEW_STATUS">
		<jsp:include page="flow/prizeApply_deptReview.jsp"/>
	</s:if>	
	<s:if test="#request.applyMatch.status==@com.wonders.stpt.union.entity.bo.UnionApplyMatch@MODIFY_STATUS">
		<jsp:include page="flow/prizeApply_modify.jsp"/>
	</s:if>	
</form>
</body>
</html>