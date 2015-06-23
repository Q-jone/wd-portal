<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>流程延时申请历史信息</title>
<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/default/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
<!--[if IE 6.0]>
           <script src="../js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
        <script src="../js/html5.js"></script>
        <script src="../js/jquery-1.7.1.js"></script>
		<script src="../js/jquery.formalize.js"></script>
		<!--<script src="../js/switchDept.js"></script>-->
		<script src="../js/show.js"></script>
		<link type="text/css" href="../css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="../js/flick/jquery-ui-1.8.18.custom.min.js"></script>
		<script type="text/javascript" src="../js/flick/jquery.ui.datepicker-zh-CN.js"></script>
		<script src="../processInfo/js/contextPath.js"></script>
        <style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
		</style>		
	<script type="text/javascript">
	$(function(){
		$('#starttime1').datepicker({
			//inline: true								
			"changeYear":true,
			"showButtonPanel":true,	
			"closeText":'清除',	
			"currentText":'starttime1'//仅作为“清除”按钮的判断条件						
		});
					
		//$("#indicatorDate").datepicker('option', $.datepicker.regional['zh-CN']); 
		$('#starttime2').datepicker({
			//inline: true
			"changeYear":true,
			"showButtonPanel":true,	
			"closeText":'清除',	
			"currentText":'starttime2'//仅作为“清除”按钮的判断条件
		});	

		$('#applytime1').datepicker({
			//inline: true								
			"changeYear":true,
			"showButtonPanel":true,	
			"closeText":'清除',	
			"currentText":'applytime1'//仅作为“清除”按钮的判断条件						
		});
					
		//$("#indicatorDate").datepicker('option', $.datepicker.regional['zh-CN']); 
		$('#applytime2').datepicker({
			//inline: true
			"changeYear":true,
			"showButtonPanel":true,	
			"closeText":'清除',	
			"currentText":'applytime2'//仅作为“清除”按钮的判断条件
		});	
		//datepicker的“清除”功能
        $(".ui-datepicker-close").live("click", function (){              
          if($(this).parent("div").children("button:eq(0)").text()=="starttime1") $("#starttime1").val("");
          if($(this).parent("div").children("button:eq(0)").text()=="starttime2") $("#starttime2").val("");   
          if($(this).parent("div").children("button:eq(0)").text()=="applytime1") $("#applytime1").val("");
          if($(this).parent("div").children("button:eq(0)").text()=="applytime2") $("#applytime2").val("");   
        });
	});	
	
	$(function(){
		$("[id=form_a]").each(function(){
			var pname = $(this).parents("tr").find("#pname").html();
			var pincident = $(this).parents("tr").find("#pincident").val();
			var cname = $(this).parents("tr").find("#cname").val();
			var cincident = $(this).parents("tr").find("#cincident").val();
			var taskid = $(this).parents("tr").find("#taskid").val();
			var a = "";
			if(pname=="新收文流程"){
				a = workflowPath+"/receive-recvMain/forward.action?pincident="+pincident+"&pname="+encodeURI(pname)+"&cname="+encodeURI(cname)+"&cincident="+cincident+"&taskid="+taskid+"&viewType=1";
			}
			if(pname=="新收呈批件"){
				a = workflowPath+"/receive-redvMain/forward.action?pincident="+pincident+"&pname="+encodeURI(pname)+"&cname="+encodeURI(cname)+"&cincident="+cincident+"&taskid="+taskid+"&viewType=1";
			}
			if(pname=="收文流程"||pname=="收呈批件"){
				a = openForm1('',pname,pincident,pname,pincident,taskid);
			}
			$(this).attr("href",a);

			$("[id=status]").each(function(){
				$(this).html($(this).html().replace('1','进行中').replace('2','已完成').replace('33','已取消'));
			});

			$("[name=status]").val($("#h_status").val());
		});
	})
	
	function openForm1(task_user_name,model_id,instance_id,processName,pinstance_id,task_id){
				var url =stptPath; 
				url += '/openflowform.action';
				url +="?task_id="+encodeURI(task_id);
				url +="&task_user_name="+ encodeURI('ST/G00100000161');
				if (model_id == ''){
					url +="&model_id=" + encodeURI(processName);
				}else{
					url +="&model_id=" + encodeURI(model_id);
				}
		
				if (instance_id == ''){
					url +="&instance_id="+ encodeURI(pinstance_id);
				}else{
					url +="&instance_id="+ encodeURI(instance_id);
				}
				url +="&step_name=aa";
				url +="&pinstance_id=" + encodeURI(pinstance_id);
				url +="&processName=" + encodeURI(processName);
				url +="&task_type=1" ;
				
				return url;
			}

	function openScan(taskid){
		var url = ultimusPath+"/sLogin/workflow/TaskStatus.aspx?TaskID="+taskid;
		window.open(url);
	}

	//跳转到制定页
    function goPage(pageNo,type){
		//type=0,直接跳转到制定页
       if(type=="0"){
       		
       		var pageCount = $("#totalPageCount").val();
       		var number = $("#number").val();
       		if(!number.match(/^[0-9]*$/)){
       			alert("请输入数字");
       			$("#number").val($("#currentNumber").val());
       			$("#number").focus();
       			return ;
       		}
       		if(parseInt(number)>parseInt(pageCount)){
       			$("#number").val(pageCount);
       			$("#page").val(pageCount);
       		}else{
       			$("#page").val(number);
       		}
       }
		//type=1,跳转到上一页	       
       if(type=="1"){
       		$("#page").val(parseInt($("#number").val())-1);
       }
		//type=2,跳转到下一页	       
       if(type=="2"){
            //alert($("#number").val());	       		
       		$("#page").val(parseInt($("#number").val())+1);
       		//alert($("#pageNo").val());
       }
       
       //type=3,跳转到最后一页,或第一页
       if(type=="3"){
   	    	$("#page").val(pageNo);
       }
   	   $("#form").submit();

    }
    </script>



</head>

<body>
<jsp:include page="/delayWarn/checkBox.jsp"/>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
			<div class="fl"><img id="show" onclick="showHide();" src="../css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li class="fin">流程延时申请历史信息</li>
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
      <div class="pt45">
        <!--Filter-->
<div class="filter">
        	<div class="query">
        	<div class="p8 filter_search">
        	<s:form action="showApplyHistoryList" id="form" method="post"  namespace="/delayWarn">
        	<input type="hidden" name="page" id="page" value="<s:property value='#request.page'/>"/>
        	 <table width="100%" border="0" cellspacing="0" cellpadding="0">
      	      	 <tr>
        	     <td class="t_r">流程名称</td>
        	     <td>
        	       <input type="text" name="pname" class="input_xlarge" value="<s:property value='#request.pname'/>"/>
        	      </td>
        	     <td class="t_r">摘要</td>
        	     <td>
        	       <input type="text" name="title" class="input_xlarge" value="<s:property value='#request.title'/>"/>
        	      </td>
        	       <td class="t_r">开始时间</td>
        	     <td>
        	       <input readonly="readonly" name="starttime1" id="starttime1" type="text" value="<s:property value='#request.starttime1'/>" class="input_large"/>至
        	       <input readonly="readonly" name="starttime2" id="starttime2" type="text" value="<s:property value='#request.starttime2'/>" class="input_large"/>
        	      </td>
        	      </tr>
      	     	<tr>
        	     <td class="t_r">申请部门</td>
        	     <td>
        	       <input type="text" name="dept" class="input_xlarge" value="<s:property value='#request.dept'/>"/>
        	      </td>
        	     <td class="t_r">状态</td>
        	     <td>
        	      <select name="status">
        	      	<option value="">全部</option>
        	      	<option value="1">进行中</option>
        	      	<option value="2">已完成</option>
        	      </select> 
        	      <input type="hidden" id="h_status" value="<s:property value='#request.status'/>">
        	      </td>
        	       <td class="t_r">申请时间</td>
        	     <td>
        	       <input readonly="readonly" name="applytime1" id="applytime1" type="text" value="<s:property value='#request.applytime1'/>" class="input_large"/>至
        	       <input readonly="readonly" name="applytime2" id="applytime2" type="text" value="<s:property value='#request.applytime2'/>" class="input_large"/>
        	      </td>
        	      </tr>
        	    <tr>
        	      <td colspan="6" class="t_c">
                  	<input type="submit" value="检 索" /></td>
				</tr>
      	    </table>
      	    </s:form>
      	    </div>
        	</div>     
		      </div>
      
        <!--Filter End-->
        <!--Table-->
         <s:set name="r" value="#request.pageResultSet"></s:set>  
        <div class="mb10">
        	<table width="100%"  class="table_1"  id="mytable">
                              <thead>
                              <tr class="tit">
                                <td class="t_c" width="3%">序号</td>
                                <td class="t_c" width="10%">流程名称</td>
                                <td class="t_c" width="32%">摘要</td>
                                <td class="t_c" width="8%">开始时间</td>
                                <td class="t_c" width="8%">结束时间</td>
                                <td class="t_c" width="5%">状态</td>
                                <td class="t_c" width="10%">申请部门</td>
                                <td class="t_c" width="15%">申请时间</td>
                                <td class="t_c" width="3%">超时</td>
                                <td class="t_c" width="3%">表单</td>
                                <td class="t_c" width="3%">监控</td>
                                </tr>
                                </thead>
                               <tbody> 
                              <s:iterator value="#r.list" id="items" status="s">
                              <tr id="dataTr">
                              	<td class="t_c"><s:property value="#s.index+1"/></td>
                               	<td class="t_c" id="pname"><s:property value="#items.PNAME" /></td>
                               	<td class="t_l"><s:property value="#items.TITLE" /></td>
                               	<td class="t_c"><s:property value="#items.OVER_DATE" /></td>
                               	<td class="t_c"><s:property value="#items.ENDTIME" /></td>
                               	<td class="t_c" id="status"><s:property value="#items.STATUS" /></td>
                               	<td class="t_c"><s:property value="#items.DEPT" /></td>
                               	<td class="t_c"><s:property value="#items.UPDATE_TIME" /></td>
                               	<s:if test="#items.STATUS==1">
                               	<td class="t_c"><s:property value="#items.EXE" /></td>
                               	</s:if>
                               	<s:else>
                               	<td class="t_c">0</td>
                               	</s:else>
                               	<td class="t_c"><center><a id="form_a" href="#" target="_blank">
                               		<img src="/portal/css/default/images/p_open.gif"/>
                               		<input type="hidden" id="pincident" value="<s:property value="#items.PINCIDENT" />">
                               		<input type="hidden" id="cname" value="<s:property value="#items.CNAME" />">
                               		<input type="hidden" id="cincident" value="<s:property value="#items.CINCIDENT" />">
                               		<input type="hidden" id="taskid" value="<s:property value="#items.TASKID" />">
                               		</center></a></td>
                               	<td class="t_c"><center><a href="javascript:void(0);" onclick="openScan('<s:property value="#items.TASKID" />');"><img src="/portal/css/default/images/p_but9.gif"/></a></center></td>
                                </tr>
                                </s:iterator>
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="15"><div class="clearfix"><span class="fl"><s:property value="#r.pageInfo.totalRow"/>条记录</span>
                           		<ul class="fr clearfix pager">
		                             <li>Pages:<s:property value="#r.pageInfo.currentPage"/>/<s:property value="#r.pageInfo.totalPage"/>
		                             		<input type="hidden" value="<s:property value='#r.pageInfo.totalPage'/>" id="totalPageCount">
		                             		<input type="hidden" value="<s:property value='#r.pageInfo.currentPage'/>" id="currentNumber">
			                                  <input type="text" id="number" name="pageNumber" min="0" max="999" step="1" class="input_tiny" value="<s:property value='#r.pageInfo.currentPage'/>"/>
			                                  <input type="button" name="button" id="button" value="Go" onclick="goPage(0,0)">
		                             </li>
                             
		                             <s:if test="#r.pageInfo.currentPage==#r.pageInfo.totalPage">
		                             <li><a href="javascript:void(0)">&gt;&gt;</a></li>
		                             </s:if>
		                             <s:else>
		                              <li><a href="javascript:void(0)" onclick="goPage(<s:property value='#r.pageInfo.totalPage'/>,3)">&gt;&gt;</a></li>
		                             </s:else>
                              
	                             	<li>
	                             	<s:if test="#r.pageInfo.currentPage==#r.pageInfo.totalPage">	
	                             		<a href="javascript:void(0)">下一页</a>
	                             	</s:if>
	                             	<s:else>
	                             		<a href="javascript:void(0)" onclick="goPage(<s:property value='#r.pageInfo.currentPage'/>,2)">下一页</a>
	                             	</s:else>
	                             	</li>
	                             	<li>
	                             	<s:if test="#r.pageInfo.currentPage==1">
	                             		<a href="javascript:void(0)">上一页</a>
	                             	</s:if>
	                             	<s:else>
	                             		<a href="javascript:void(0)" onclick="goPage(<s:property value='#r.pageInfo.currentPage'/>,1)">上一页</a>
	                             	</s:else>
	                             	
	                             	</li> 
	                             
	                             	<s:if test="#r.pageInfo.currentPage==1">
	                             	<li><a href="javascript:void(0)">&lt;&lt;</a></li>
	                             	</s:if>
	                             	<s:else>
	                             		<li><a href="javascript:void(0)" onclick="goPage(1,3)">&lt;&lt;</a></li>
	                             	</s:else>
	                             
                                
                            </ul>
                        </div>
                                </td>
                              </tr>
                            </table>

      </div>
      
      
</div>
</div>
</body>
</html>