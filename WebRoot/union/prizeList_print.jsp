<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.wonders.stpt.union.entity.bo.UnionMatch"%>
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
	<script type="text/javascript">
	
		//定义子窗口
		var sonWindow = null;
		//每1秒执行一次checkSonWindow()方法
		var refresh = setInterval("checkSonWindow()",1000);
		 //监测子窗口是否关闭
		function checkSonWindow(){
			if(sonWindow!=null && sonWindow.closed==true){
				window.location.href = "list.action?matchId=${match.id}";
				clearInterval(refresh);
			}
		}
		 
        $(document).ready(function () {

        });
        
      //跳转到新增页面
        function showAddPage(){
        	sonWindow = window.open('showAdd.action?matchId=${match.id}');
        }

        function showEdit(prizeId){
        	sonWindow = window.open('showEdit.action?id='+prizeId);
        }      
        
        function showView(prizeId){
        	window.open('showView.action?id='+prizeId);
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
       
       function modifyQuantity(prizeId,quantity,edit){
         	 $("#applyDepartmentDialog").dialog({
         		title: '参赛单位',
   				modal: true,
   				autoOpen: false,
   				width: 380,
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
       	  var surl = "<%=path%>/unionPrize/listApplyDepartments.action?prizeId="+prizeId+"&quantity="+quantity+"&edit="+edit;
       	  var frame = '<iframe frameBorder="0" scrolling="yes" src="'+surl+'" style="HEIGHT:350px; VISIBILITY: inherit; WIDTH:340px; Z-INDEX:999"></iframe>'
       	  $('#applyDepartmentDialog').html(frame);
       	  $("#applyDepartmentDialog").dialog('open');
         }
       
   	function closeDialog(){
		$("#applyDepartmentDialog").dialog('close');  
	}       
   	
  	var handleOptions = {
			cache:false,
			type:'post',
			callback:null,
			url:'<%=path%>/unionMatch/flowDeal.action',
		    success:function(data){
				if(data=="1"){
					alert("操作成功");
					shut();
				}else if(data=="2"){
					if('${match.status}'=='2'){
						alert("请在下方新增奖项后再提交！");						
					}					
					if('${match.status}'=='4'){
						alert("请将所有名额分配完毕后再提交！");						
					}
				}
				return false;
		    },error: function() { alert("服务器连接失败，请稍后再试!"); }	
		};
      
    function doDel(id,type){
    	var url = '<%=path%>/unionPrize/doDel.action';
    	if(confirm('您确定要删除该奖项吗！')){
	   		 $.ajax({
	   		        type: "post",
	   		        url: url,
	   		        dataType: "json",
	   		        data:"id="+id+"&random="+Math.random(),
	   		        success: function (data) {
	   		        	alert("删除成功");
	   		        	window.location.href = "list.action?matchId=${match.id}";
	   		        },
	   		        error: function (XMLHttpRequest, textStatus, errorThrown) {
	   		                alert("删除失败");
	   		        }
	   		});	 
    	}
	}    
    
	function shut(){
  		window.opener=null;
  		window.open("","_self");
  		window.close();
  	}   	
    </script>

	<style>
		div.jqi{ width:770px}
		.ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }	
	</style>
	<style type="text/css" media=print>
	.nprint{display:none;}
	</style>
</head>

<body>
        <form action="" id="form" method="post">
        	<input type="hidden" name="id" value="${match.id}"/>
            <input type="hidden" name="status" value="${match.status}"/>
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
            </tbody>
        </table>

    </div>
    <s:if test="!#request.applyDepts.isEmpty()"> 
    <div class="filter">
        <div class="fn clearfix">
            <h5 class="fl"><a href="#" class="colSelect fl">参赛单位经办人设置</a></h5>
	            <div class="fr">
	            </div>
        </div>
    </div>    
	<div>
        <table width="100%"  class="table_1">
            <tbody>
            <tr class="tit">
                <td class="t_c" width="10%">序号</td>
                <td class="t_c" width="60%">单位名称</td>
                <td class="t_c" width="30%">申报人</td>
            </tr>

			<s:iterator value="#request.applyDepts" id="data" status="s">
            <tr class="odd">
                <td class=" first t_c"><input type="hidden" value="">
                	<s:property value="#s.index+1"/>
                </td>
                <td class="t_c">
					${data.deptName}                                               
				</td>
                <td class="t_c">
                	<table style="width:100%;border:0px;">
					<s:iterator value="#request.applyMatchesMap[#data.deptId]" id="applyMatch" status="s">
						<tr>
							<td width="60%" class="t_c" style="border:0px;">${applyMatch.userName}</td>
						</tr>
					</s:iterator>
					</table>
                </td>
            </tr>
			</s:iterator>
            </tbody>         
        </table>
    </div>      
    </s:if>    
    <div class="filter">
        <div class="fn clearfix">
            <h5 class="fl"><a href="#" class="colSelect fl">奖项列表</a></h5>
	            <div class="fr">
	            </div>
        </div>
    </div>
    <div>
        <table width="100%"  class="table_1">
            <tbody>
            <tr class="tit">
                <td class="t_c" width="30">序号</td>
                <td class="t_c" width="15%">类别</td>
                <td class="t_c" width="35%">奖项名称</td>
                <td class="t_c" width="10%">数量</td>
                <td class="t_c" width="15%">奖金</td>
                <!-- <td class="t_c">参赛单位</td> -->
                <td class="t_c" width="250">参赛单位</td>
            </tr>

			<s:iterator value="#request.prizes" id="data" status="s">
            <tr class="odd">
                <td class=" first t_c"><input type="hidden" value="">
                <s:property value="#s.index+1"/>
                </td>
                <td class="t_c">
                    <s:if test="#data.prizeType == 1">
                        	个人类
                    </s:if>
                    <s:if test="#data.prizeType == 2">
                        	集体类
                    </s:if>
                    <s:if test="#data.prizeType == 3">
                        	 项目类
                    </s:if>
                    <s:if test="#data.prizeType == 4">
                        	项目成果类
                    </s:if>                                                            
				</td>
                <td class="t_c"> ${data.prizeName}</td>
                <td class="t_c"> ${data.quantity}</td>
                <td class="t_c"> ${data.bonus}</td>
<%--                 <td class="t_c"> 
                ${data.APPLY_DEPT}
                </td> --%>
                <td class="t_c">
                	<table style="width:100%;border:0px;">
					<s:iterator value="#request.applyDeptMap[#data.id]" id="dept" status="s">
						<tr>
							<td width="60%" class="t_r" style="border:0px;">${dept.deptName}：</td><td style="border:0px;">${dept.quantity}</td>
						</tr>
					</s:iterator>
					</table>
                </td>
            </tr>
			</s:iterator>
            <tr>
                <td class="t_c" colspan="4"></td>
				<td class="t_c">总奖金：${totalBonus}</td>
				<td class="t_c"></td>
            </tr>			
            </tbody>
                <tr class="tfoot nprint">
                    <td colspan="6" style="text-align:center"><input type="button" onclick="window.print();" value="打 印"/>&nbsp;
                        <input type="button" onclick="shut();" value="关 闭"/>&nbsp;</td>
                </tr>               
        </table>

    </div>
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
        </form>
<div style="display:none;" id="applyDepartmentDialog" title="分配名额">
</div>
</body>
</html>