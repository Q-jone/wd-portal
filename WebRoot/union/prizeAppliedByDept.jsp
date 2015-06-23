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
<link rel="stylesheet" href="<%=path%>/union/css/default/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
<link type="text/css" href="<%=path%>/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />

<script src="<%=path%>/js/jquery-1.7.1.js"></script>
<script src="<%=path%>/deptDoc/js/ztree/jquery.ztree.all-3.4.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/flick/jquery-ui-1.8.18.custom.min.js"></script>
<script src="<%=path%>/js/flick/jquery.ui.datepicker-zh-CN.js"></script>
<script src="<%=path%>/union/js/messagebox.js"></script>
	<script type="text/javascript">
	
		//定义子窗口
		var sonWindow = null;
		//每1秒执行一次checkSonWindow()方法
		var refresh = setInterval("checkSonWindow()",1000);
		 //监测子窗口是否关闭
		function checkSonWindow(){
			if(sonWindow!=null && sonWindow.closed==true){
				window.location.href = "prizeApplyList.action?matchId=${match.id}";
				clearInterval(refresh);
			}
		}
		 
        $(document).ready(function () {
        	initMessagebox();
        	initMessageboxClose();
        	
        	$('.collapsible').css('cursor','pointer').click(toggleDetail).find('i').addClass('arrow_1 collapse_1');
        });
        
      //跳转到新增页面
        function showApply(prizeId){
        	sonWindow = window.open('showApply.action?prizeId='+prizeId);
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
       
	function doReject(){
  	     var personalPrizeIds = '';
	     $("input[name='check_personal']:checked").each(function(){
	    	 personalPrizeIds += ','+this.value;
	     });
	     if(personalPrizeIds == ''){
	         alert('没有勾选申报奖项！');
	         return;
	     }
	     
	     personalPrizeIds=personalPrizeIds.substr(1);
	     
	     if(confirm('确定要退回选中的申报奖项吗？')){
	 		$.post(
					'<%=path%>/unionPrize/rejectByIds.action?random='+Math.random(),
					{
						"id": '${match.id}',
						"personalPrizeIds": personalPrizeIds
					},
					function(obj, textStatus, jqXHR){
						if(obj=="1"){
							alert('操作成功。');
							parent.location.reload();
						}else{
							alert("服务器连接失败，请稍后再试!");
						}
						return false;
					},
					"text"
				).error(function() { alert("服务器连接失败，请稍后再试!"); })	    	 
	     }
	}
	
	function allCheck(obj){
		var checked = obj.checked;
		$(obj).closest('tbody').find(':checkbox').attr('checked',checked);
	}
       
   	function closeDialog(){
		$("#applyDepartmentDialog").dialog('close');  
	}       
   	
	function toggleDetail(){
		var target = $(this);
		if(target.find('i').hasClass('expand_1')){
			target.find('i').removeClass('expand_1').addClass('collapse_1');
			$("tbody",target.parent()).hide();
		}else{
			target.find('i').removeClass('collapse_1').addClass('expand_1');
			$("tbody",target.parent()).show();
		}
	}	    	   	
    </script>

	<style>
		div.jqi{ width:770px}
		.ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }	
	</style>

</head>

<body>
<div class="main f_bg">
<%--     <div class="ctrl clearfix">
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
    </div> --%>
<!--     <div class="filter">
        <div class="fn clearfix">
            <h5 class="fl"><a href="#" class="colSelect fl">可申报奖项</a></h5>
        </div>
    </div> -->
    <div>
        <table width="100%"  class="table_1">
            <thead class="collapsible">
            <th colspan="5" style="text-align:center;"><strong>可申报奖项</strong><i> </i></th>
            </thead>        
            <tbody style="display:none;">
            <tr class="tit">
                <td class="t_c" width="30">序号</td>
                <td class="t_c">类别</td>
                <td class="t_c">奖项名称</td>
                <td class="t_c">名额</td>
                <td class="t_c">奖金</td>
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
            </tr>
			</s:iterator>
            </tbody>
        </table>

    </div>
    
    <div class="filter">
        <div class="fn clearfix">
            <h5 class="fl"><a href="#" class="colSelect fl">已申报奖项</a></h5>
            	<s:if test="#request.match.operatorId.substring(0,12)==#session.t_login_name">
            	<s:if test="#request.match.status==@com.wonders.stpt.union.entity.bo.UnionMatch@ASSESS_SUM_STATUS || #request.match.status==@com.wonders.stpt.union.entity.bo.UnionMatch@APPLY_STATUS">
                <div class="fr">
                	<input type="button" id="todo_handle" value="退 回 修 改" id="addButton">
                    <!-- <input type="button" id="addToolBarBtn"  value="退 回" onclick="doReject();" style="margin-left: 5px"/> -->
                </div>            
                </s:if>
                </s:if>
        </div>
    </div>    

	<s:if test="null!=#request.appliedTeamPrizes&&!#request.appliedTeamPrizes.isEmpty()"> 
    <div>

        <table width="100%"  class="table_1">
            <thead>
            <th colspan="8" style="text-align:center;"><strong>先进集体</strong></th>
            </thead>
            <tbody>
            <tr class="tit">
            	<td class="t_c" width="5%"><input type="checkbox" onclick="allCheck(this);"></td>
                <td class="t_c" style="width:40px">序号</td>
                <td class="t_c">奖项名称</td>
                <td class="t_c">集体全称</td>
                <td class="t_c">人数</td>
                <td class="t_c">所属单位</td>
                <td class="t_c">申报人</td>
                <td class="t_c" style="width:100px">操作</td>
            </tr>            
            <s:iterator value="#request.appliedTeamPrizes" id="data" status="s">
            <tr <s:if test="#data.modified==1 && #request.match.status<@com.wonders.stpt.union.entity.bo.UnionMatch@LEAD_SUM_STATUS">style="background-color:#FFFF00"</s:if>>
            	<td class="t_c"><input type="checkbox" name="check_team" value="${data.id}"></td>
                <td class="t_c" width="30"><s:property value="#s.index+1"/></td>
                <td class="t_c">${data.prize.prizeName}</td>
                <td class="t_c">${data.name}</td>
                <td class="t_c">${data.persons}</td>
                <td class="t_c">${data.deptName}</td>
                <td class="t_c">${data.cUserName}</td>
                <td class="t_c" width="250">
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
            <th colspan="9" style="text-align:center;"><strong>先进个人</strong></th>
            </thead>
            <tbody>
            <tr class="tit">
            	<td class="t_c" width="5%"><input type="checkbox" onclick="allCheck(this);"></td>
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
            <tr <s:if test="#data.modified==1 && #request.match.status<@com.wonders.stpt.union.entity.bo.UnionMatch@LEAD_SUM_STATUS">style="background-color:#FFFF00"</s:if>>
            	<td class="t_c"><input type="checkbox" name="check_personal" value="${data.id}"></td>
                <td class="t_c" width="30"><s:property value="#s.index+1"/></td>
                <td class="t_c">${data.prize.prizeName}</td>
                <td class="t_c">${data.name}</td>
                <td class="t_c">${data.gender}</td>
                <td class="t_c">${data.brithday}</td>
                <td class="t_c">${data.deptName}</td>
                <td class="t_c">${data.cUserName}</td>
                <td class="t_c" width="250">
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
            <th colspan="8" style="text-align:center;"><strong>优秀创新项目</strong></th>
            </thead>
            <tbody>
            <tr class="tit">
            	<td class="t_c" width="5%"><input type="checkbox" onclick="allCheck(this);"></td>
                <td class="t_c" width="30">序号</td>
                <td class="t_c">奖项名称</td>
                <td class="t_c">项目名称</td>
                <td class="t_c">承担单位</td>
                <td class="t_c">负责人</td>
                <td class="t_c">申报人</td>
                <td class="t_c" width="250">操作</td>
            </tr>            
            <s:iterator value="#request.appliedProjectPrizes" id="data" status="s">
            <tr <s:if test="#data.modified==1 && #request.match.status<@com.wonders.stpt.union.entity.bo.UnionMatch@LEAD_SUM_STATUS">style="background-color:#FFFF00"</s:if>>
            	<td class="t_c"><input type="checkbox" name="check_project" value="${data.id}"></td>
                <td class="t_c" width="30"><s:property value="#s.index+1"/></td>
                <td class="t_c">${data.prize.prizeName}</td>
                <td class="t_c">${data.prjectName}</td>
                <td class="t_c">${data.deptName}</td>
                <td class="t_c">${data.responsibilePerson}</td>
                <td class="t_c">${data.cUserName}</td>
                <td class="t_c" width="250">
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
            <th colspan="9" style="text-align:center;"><strong>品牌成果</strong></th>
            </thead>
            <tbody>
            <tr class="tit">
            	<td class="t_c" width="5%"><input type="checkbox" onclick="allCheck(this);"></td>
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
            <tr <s:if test="#data.modified==1 && #request.match.status<@com.wonders.stpt.union.entity.bo.UnionMatch@LEAD_SUM_STATUS">style="background-color:#FFFF00"</s:if>>
            	<td class="t_c"><input type="checkbox" name="check_achivement" value="${data.id}"></td>
                <td class="t_c" width="30"><s:property value="#s.index+1"/></td>
                <td class="t_c">${data.prize.prizeName}</td>
                <td class="t_c">${data.prjectName}</td>
                <td class="t_c">${data.module}</td>
                <td class="t_c">${data.groupName}</td>
                <td class="t_c">${data.responsibilePerson}</td>
                <td class="t_c">${data.cUserName}</td>
                <td class="t_c" width="250">
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
	<jsp:include page="flow/prizeReject.jsp"/>
</body>
</html>