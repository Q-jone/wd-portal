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
<link rel="stylesheet" href="<%=path%>/union/css/default/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
<link type="text/css" href="<%=path%>/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />

<script src="<%=path%>/js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="<%=path%>/js/flick/jquery-ui-1.8.18.custom.min.js"></script>
<script src="<%=path%>/js/jquery.form.js"></script>
<script src="<%=path%>/js/jquery.formalize.js"></script>
<script src="<%=path%>/union/js/messagebox.js"></script>	
	<script type="text/javascript">
	
		 
        $(document).ready(function () {
         	$(".tabs_2 li:eq(0)").addClass("selected");
            $(".tabs_2").find("a").click(function(){
                var index = $(this).parent().index();
                $(this).parents("ul").find(".selected").removeClass("selected");
                $(this).parent().addClass("selected");
                loadContent('/portal/unionPrize/prizeAppliedByDept.action?matchId=${match.id}&deptId='+$(this).attr('deptId'));
            })
            $(".tabs_2 a:eq(0)").trigger('click');
            
        	initMessagebox();
        	initMessageboxClose();            
        	
        	$('.collapsible').css('cursor','pointer').click(toggleDetail).find('i').addClass('arrow_1 collapse_1');
        });
        
  		function shut(){
  		  window.opener=null;
  		  window.open("","_self");
  		  window.close();
  		} 
       
       function clearInput(){
    	   $('.fi').val('');
       }
   	
    function loadContent(url){
    	$('#iframe').attr('src',url);
/*         $("#content").html("");
        $("#content").load(url,function(){
            $("#delForm").remove();
        }); */
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
    
	function doReject(){
		if(confirm('确定要把该专项退回到考评部门吗？')){
			$.post(
					'<%=path%>/unionMatch/rejectById.action?random='+Math.random(),
					{
						"id": '${match.id}'
					},
					function(obj, textStatus, jqXHR){
						if(obj=="1"){
							alert('操作成功。');
							parent.location.href=parent.location.href;
						}else{
							alert("服务器连接失败，请稍后再试!");
						}
						return false;
					},
					"text"
				).error(function() { alert("服务器连接失败，请稍后再试!"); })    			
		}
	}
	
	function showView(prizeId){
    	window.open('showView.action?id='+prizeId);
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

</head>

<body>
<form action="" id="form" method="post">
	<input type="hidden" name="id" value="${match.id}"/>
    <input type="hidden" name="status" value="${match.status}"/>
    <input type="hidden" name="checkRole" value="${checkRole}"/>
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

    <div>
        <table width="100%"  class="table_1">
            <thead class="collapsible">
            <th colspan="7" style="text-align:center;"><strong>奖项列表</strong><i> </i></th>
            </thead>        
            <tbody style="display:none;">
            <tr class="tit">
                <td class="t_c" width="30">序号</td>
                <td class="t_c">类别</td>
                <td class="t_c">奖项名称</td>
                <td class="t_c">名额</td>
                <td class="t_c">奖金</td>
                <td class="t_c">参赛单位</td>
                <td class="t_c" width="250">操作</td>
            </tr>

			<s:iterator value="#request.prizeList" id="data" status="s">
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
                <td class="t_c"><a class="mr5" style="display: inline;cursor:pointer;" onclick="modifyQuantity('${data.id}','${data.quantity}','false')">查看参赛单位</a></td>
                <td class="t_c">
                    <a class="mr5" style="display: inline;cursor:pointer;" onclick="showView('${data.id}')" name="applicant">查看</a>
                </td>
            </tr>
			</s:iterator>
            </tbody>
        </table>

    </div>    
    <s:if test="#request.match.status==10">
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
								<s:property value="#data.checkUser"/>&nbsp;<s:property value="#data.checkTime"/>
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
    </s:if>     
    
    <div class="filter">
        <div class="fn clearfix">
            <h5 class="fl"><a href="#" class="colSelect fl">各单位申报情况</a></h5>
            	<s:if test="#request.theme.status==@com.wonders.stpt.union.entity.bo.UnionTheme@SUM_STATUS">
                <div class="fr">
                    <input type="button" id="todo_handle" value="退 回" id="addButton">
                </div>            
                </s:if>            
        </div>
    </div>    

    <div class="mb10">
        <div class="tabs_2">
            <ul>
            <s:iterator value="#request.appliedDepts" id="data" status="s">
                <li><a deptId="${data.deptId}"
                        href="javascript:void(0);"><span>${data.deptName}</span></a>
                </li>
			</s:iterator>
            </ul>
        </div>
        <div id="content">
        <iframe id="iframe" frameborder="0" width="100%;" height="520px"></iframe>
        </div>
    </div>
             
    <div>
        <table width="100%"  class="table_1">
            <thead>
            <th colspan="4" style="text-align:center;"><strong>专项审核信息</strong></th>
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
	<jsp:include page="flow/matchReject.jsp"/>
</form>
<div style="display:none;" id="applyDepartmentDialog" title="分配名额">
</div>
</body>
</html>