<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.*"%>
<%@ page import="com.wonders.stpt.union.entity.bo.UnionMatch"%>
<%@ page import="com.wonders.stpt.union.entity.bo.prize.UnionPersonalPrize"%>
<%@ page import="com.wonders.stpt.union.entity.bo.prize.UnionTeamPrize"%>
<%@ page import="com.wonders.stpt.union.entity.bo.UnionPrize"%>

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
        });
        
      //跳转到新增页面
        function showApply(prizeId){
        	sonWindow = window.open('showApply.action?prizeId='+prizeId);
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
          
        function doSubmit(choice){
    		$(":hidden[name=choice]").val(choice);
        	if(confirm('确认要提交吗？')){$('#form').ajaxSubmit(handleOptions);}
          }
          
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
	<input type="hidden" name="id" value="${theme.id}"/>
    <input type="hidden" name="status" value="${theme.status}"/>
    <input type="hidden" name="checkRole" value="${checkRole}"/>
    <input type="hidden" name="choice" value="0"/>
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
                <tr>
                    <td colspan="4" style="text-align:center">
								<textarea rows="4" id="remark" name="remark" style="width:98%;overflow:visible"></textarea>
					</td>
                </tr>				
							<tr class="tfoot">
                                <td style="text-align:center" colspan="4">
                                <input type="button" onclick="doSubmit('0');" value="提 交" id="addButton">&nbsp;
                                <input type="button" onclick="doSubmit('1');" value="退 回" id="addButton">&nbsp;
                                <input type="button" onclick="shut();" value="关 闭">
                                </td>
                              </tr>   		  	
            </tbody>
        </table>

    </div>        
    
    <div class="filter">
        <div class="fn clearfix">
            <h5 class="fl"><a href="#" class="colSelect fl">各专项申报情况</a></h5>
        </div>
    </div>    

	<%List<UnionMatch> matchList = (List<UnionMatch>)request.getAttribute("matchList"); 
		for(int i = 0;i<matchList.size();i++){
			UnionMatch match = matchList.get(i);
		
	%>
    <div>

        <table width="100%"  class="table_1">
            <thead>
            <th colspan="4" style="text-align:center;"><strong>专项主题信息</strong></th>
            </thead>
            <tbody>
            <tr>
                <td class="t_r lableTd" width="15%">类别:</td>
                <td width="35%"><%=match.getMatchType()%></td>
                <td class="t_r lableTd" width="15%">专项主题名:</td>
                <td width="35%"><%=match.getMatchName()%></td>
            </tr>
            <tr>
                <td class="t_r lableTd">开始日期:</td>
                <td><%=match.getBeginDate()%></td>
                <td class="t_r lableTd">结束日期:</td>
                <td><%=match.getEndDate()%></td>
            </tr>
            <tr>
                <td class="t_r lableTd">附件:</td>
                <td colspan="3">

                </td>
            </tr>     
            </tbody>
        </table>

    </div>

    <div class="mb10">

        <table width="100%"  class="table_1">
            <thead>
            <th colspan="7" style="text-align:center;"><strong>先进个人</strong></th>
            </thead>
            <tbody>
            <tr class="tit">
                <td class="t_c" width="30">序号</td>
                <td class="t_c">奖项名称</td>
                <td class="t_c">姓名</td>
                <td class="t_c">性别</td>
                <td class="t_c">出生年月</td>
                <td class="t_c">工作单位</td>
                <td class="t_c" width="250">操作</td>
            </tr>            
               <%Map<String,Map<String,List>> prizeMap = (Map<String,Map<String,List>>)request.getAttribute("prizeMap"); 
    Map<String,List> matchPrizeMap = prizeMap.get(match.getId());
    List<UnionPersonalPrize> personalPrizes = matchPrizeMap.get("personal");
	for(int j = 0;j<personalPrizes.size();j++){
		UnionPersonalPrize personalPrize = personalPrizes.get(j);
    %>
            <tr>
                <td class="t_c" width="30"><s:property value="#s.index+1"/></td>
                <td class="t_c"><%=personalPrize.getPrize().getPrizeName()%></td>
                <td class="t_c"><%=personalPrize.getName()%></td>
                <td class="t_c">性别</td>
                <td class="t_c"><%=personalPrize.getBrithday()%></td>
                <td class="t_c"><%=personalPrize.getUnitName()%></td>
                <td class="t_c" width="250">
                 	<a class="mr5" style="display: inline;cursor:pointer;" onclick="">查看</a>
                </td>
            </tr>
            <% }%>
            </tbody>
        </table>

    </div>
    
     <div class="mb10">

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
                <td class="t_c" style="width:100px">操作</td>
            </tr>            
               <%
    List<UnionTeamPrize> teamPrizes = matchPrizeMap.get("team");
	for(int j = 0;j<teamPrizes.size();j++){
		UnionTeamPrize teamPrize = teamPrizes.get(j);
    %>            
            <tr>
                <td class="t_c" width="30"><s:property value="#s.index+1"/></td>
                <td class="t_c"><%=teamPrize.getPrize().getPrizeName()%></td>
                <td class="t_c"><%=teamPrize.getName()%></td>
                <td class="t_c"><%=teamPrize.getPersons()%></td>
                <td class="t_c"><%=teamPrize.getDeptName()%></td>
                <td class="t_c" width="250">
                 	<a class="mr5" style="display: inline;cursor:pointer;" onclick="">查看</a>
                </td>
            </tr>
            <% }%>
            </tbody>
        </table>

    </div>  
    <% }%>

</div>
</form>
<div style="display:none;" id="applyDepartmentDialog" title="分配名额">
</div>
</body>
</html>