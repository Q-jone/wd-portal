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
				window.location.href = "prizeApplyList.action?matchId=${match.id}";
				clearInterval(refresh);
			}
		}
		 
        $(document).ready(function () {
        	$(".tabs_2 li:eq(0)").addClass("selected");
            $(".tabs_2").find("a").click(function(){
                var index = $(this).parent().index();
                $(this).parents("ul").find(".selected").removeClass("selected");
                $(this).parent().addClass("selected");
                loadContent('/portal/unionPrize/prizeApplySum.action?g=leader&matchId='+$(this).attr('matchId'));
            })
            $(".tabs_2 a:eq(0)").trigger('click');
            
        	initMessagebox();
        	initMessageboxClose();
        });
        
      //跳转到新增页面
        function showApply(prizeId){
        	sonWindow = window.open('showApply.action?prizeId='+prizeId);
        }
      
        function showPrint(){
        	window.open('<%=path%>/unionPrize/prizeApplyPrintOfTheme.action?themeId=${theme.id}');
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
    			url:'<%=path%>/unionTheme/flowDeal.action',
    		    success:function(data){
    				if(data=="1"){
    					alert("操作成功!");
						shut();
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
   	
    function loadContent(url){
    	$('#iframe').attr('src',url);
/*      $("#content").html("");
        $("#content").load(url,function(){
            $("#delForm").remove();
        }); */
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
	<input type="hidden" name="themeId" value="${theme.id}"/>
    <input type="hidden" name="status" value="${theme.status}"/>
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
                <tr class="tfoot nprint">
                    <td colspan="4" style="text-align:center">
                    <s:if test="#request.theme.status>=@com.wonders.stpt.union.entity.bo.UnionTheme@SUM_STATUS && #request.theme.handlerId.substring(0,12)==#session.t_login_name">
                        <input type="button" id="todo_handle" value="审 批" id="addButton">&nbsp;
                    </s:if>
                    	<input type="button" onclick="showPrint();" value="打 印 汇 总 表"/>&nbsp;
                        <input type="button" onclick="shut();" value="关 闭"/>&nbsp;
                    </td>
                </tr>             
            </tbody>
        </table>

    </div>    
    
    <s:if test="#request.approvalInfos!=null && !#request.approvalInfos.isEmpty()">
    <div>

        <table width="100%"  class="table_1">
            <thead>
            <th colspan="4" style="text-align:center;"><strong>竞赛审核信息</strong></th>
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
    </s:if>
    <div class="filter">
        <div class="fn clearfix">
            <h5 class="fl"><a href="#" class="colSelect fl">各专项申报情况</a></h5>
        </div>
    </div>    

    <div class="mb10">
        <div class="tabs_2">
            <ul>
            <s:iterator value="#request.matchList" id="data" status="s">
                <li><a matchId="${data.id}"
                        href="javascript:void(0);"><span>${data.matchName}</span></a>
                </li>
			</s:iterator>
            </ul>
        </div>
        <div id="content">
        <iframe id="iframe" frameborder="0" width="100%;" height="520px"></iframe>
        </div>
    </div>
    
</div>
	<div class="transparent" id="maskDiv" style="display: none;z-index:99;" style="filter:alpha(opacity=30);opacity:0.3;"></div>
	<s:if test="#request.theme.status==@com.wonders.stpt.union.entity.bo.UnionTheme@SUM_STATUS">
		<jsp:include page="flow/themeApply_firstReview.jsp"/>
	</s:if>
	<s:if test="#request.theme.status==@com.wonders.stpt.union.entity.bo.UnionTheme@DEPT_LEADER_APPROVE_STATUS">
		<jsp:include page="flow/themeApply_deptReview.jsp"/>
	</s:if>
	<s:if test="#request.theme.status==@com.wonders.stpt.union.entity.bo.UnionTheme@GROUP_LEADER_APPROVE_STATUS">
		<jsp:include page="flow/themeApply_groupReview.jsp"/>
	</s:if>		
</form>
<div style="display:none;" id="applyDepartmentDialog" title="分配名额">
</div>
</body>
</html>