<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>项目基本信息列表</title>
<link rel="stylesheet" href="<%=basePath %>css/formalize.css" />
<link rel="stylesheet" href="<%=basePath %>css/page.css" />
<link rel="stylesheet" href="<%=basePath %>css/default/imgs.css" />
<link rel="stylesheet" href="<%=basePath %>css/reset.css" />
<!--[if IE 6.0]>
           <script src="../js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
        <script src="<%=basePath %>js/html5.js"></script>
        <script src="<%=basePath %>js/jquery-1.7.1.js"></script>
		<script src="<%=basePath %>js/jquery.formalize.js"></script>
		<!--<script src="../js/switchDept.js"></script>-->
		<script src="<%=basePath %>js/show.js"></script>
		<link type="text/css" href="<%=basePath %>css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="<%=basePath %>js/flick/jquery-ui-1.8.18.custom.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>js/flick/jquery.ui.datepicker-zh-CN.js"></script>
		<script src="<%=basePath %>processInfo/js/contextPath.js"></script>
		<script type="text/javascript" src="js/checkout.js"></script>
        <style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
		</style>
		<!--<script type="text/javascript" src="js/checkout.js"></script>-->
	<script type="text/javascript">
	
	
	//跳转到制定页
        function goPage(pageNo,type){
			//type=0,直接跳转到指定页
			
	       if(type=="0"){
	       		
	       		var totalPage = $("#totalPageCount").val();//总页数
	       		var pageNumber = $("#pageNumber").val();//当前页码
	       		if(!pageNumber.match(/^[0-9]*$/)){//输入不是数字时提示
	       			alert("请输入数字");
	       			$("#pageNumber").val("");
	       			$("#pageNumber").focus();
	       			return ;
	       		}
	       		if(parseInt(pageNumber)>parseInt(totalPage)){
	       			$("#pageNumber").val(totalPage);
	       			$("#page").val(pageCount);
	       		}else{
	       			$("#page").val(pageNumber);
	       		}
	       }
			//type=1,跳转到上一页	       
	       if(type=="1"){
	       		$("#page").val(parseInt($("#page").val())-1);
	       }
			//type=2,跳转到下一页	       
	       if(type=="2"){
	       		$("#page").val(parseInt($("#page").val())+1);
	       		//alert($("#pageNo").val());
	       }
	       
	       //type=3,跳转到最后一页,或第一页
	       if(type=="3"){
	   	    	$("#page").val(pageNo);
	       }
	      if(check()){
	    	  $("#form").submit();
	      }
       	   
        }
	
	
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
		//datepicker的“清除”功能
        $(".ui-datepicker-close").live("click", function (){              
          if($(this).parent("div").children("button:eq(0)").text()=="starttime1") $("#starttime1").val("");
          if($(this).parent("div").children("button:eq(0)").text()=="starttime2") $("#starttime2").val("");     
        });
	});	
	
	
	//添加跳转
	function add(){
		window.open("project_add.jsp");
	}
	//查看项目基本信息
	function view(projectId){
		newWindow=window.open("view.action?projectId="+projectId);
	}
	//编辑跳转
	function editData(id){
          newWindow=window.open("project.action?projectId="+id);
    }
	//计划管理
	function plan(projectId){
		location.href="/portal/projectPlan/sysinfo/getProjectPlanList.action?projectId="+projectId;
	}
	
	//年度推进目标
	function Goal(projectId){
		window.location.href="/portal/projectForwardGoal/sysinfo/forwardGoals.action?projectId="+projectId;
	}
	
	$(function(){
		
		$("a[id^=deletes]").click(function(){//获取主键值
			var ids=$(this).attr("id");
			var projectId=$(this).attr("title");
			
			/**var removed="1";
			var projectNumber=$("#projectNumber").val();
			var projectName=$("#projectName").val();
			var projectType=$("#projectType").val();
			var pageSize=$("#pageSize").val();
			var page=$("#page").val();
			var param="?page="+page+"&pageSize="+pageSize+"&project.projectNumber="+projectNumber+"&project.projectName="
					+"&project.projectType="+projectType+"&project.removed="+removed+"&project.projectId="+projectId;*/
			var param="?projectId="+projectId+"&page="+$("#page").val();
			if(window.confirm("是否确认删除？"))
			window.location="deletes.action"+param;
		});
		$("a[id^=finish]").click(function(){//finish获取主键值
			var ids=$(this).attr("id");
			var projectId=$(this).attr("title");//主键
			/**var finish="1";
			var projectNumber=$("#projectNumber").val();
			var projectName=$("#projectName").val();
			var projectType=$("#projectType").val();
			var pageSize=$("#pageSize").val();
			var page=$("#page").val();
			var param="?page="+page+"&pageSize="+pageSize+"&project.projectNumber="+projectNumber+"&project.projectName="
					+"&project.projectType="+projectType+"&project.finish="+finish+"&project.projectId="+projectId;*/
			var param="?projectId="+projectId+"&page="+$("#page").val();
			if(window.confirm("是否确认完成？"))
			window.location="finished.action"+param;
		});
	});
	
    </script>



</head>

<body>

	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
			<div class="fl"><img id="show" onclick="showHide();" src="<%=basePath %>css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li class="fin">项目基本信息列表</li>
                </ul>
            </div>
            
   		</div>
        <!--Ctrl End-->
      <div class="pt45">
        <!--Filter-->
<div class="filter">
        	<div class="query">
        	<div class="p8 filter_search">
        	<s:form action="list" id="form" method="post"  namespace="/project/sysinfo">
        	
        	<input type="hidden" id="pageSize" name="pageSize" value="<s:property value="pageResultSet.pageInfo.pageSize"/>" />
        	<input type="hidden" id="page" name="page" value="<s:property value="pageResultSet.pageInfo.currentPage"/>" />
        	
        	 <table width="100%" border="0" cellspacing="0" cellpadding="0">
      	      	 <tr>
        	     <td class="t_r">项目编号</td>
        	     <td>
        	       <input type="text" id="projectNumber" name="project.projectNumber" class="input_xlarge" value="<s:property value='project.projectNumber'/>"/>
        	      </td>
        	     <td class="t_r">项目名称</td>
        	     <td>
        	       <input type="text" id="projectName" name="project.projectName" class="input_xlarge" value="<s:property value='project.projectName'/>"/>
        	      </td>
        	       <td class="t_r">项目类别</td>
        	     <td>
        	       <input type="text" id="projectType" name="project.projectType" class="input_xlarge" value="<s:property value='project.projectType'/>"/>
        	      </td>
        	      <td><input type="submit" value="检索" onclick="return check();" style="width:50px;"> </td>
        	      </tr>
        	      
      	     	<!-- 
        	    <tr>
        	      <td colspan="6" class="t_c">
                  	<input type="submit" value="检 索" /></td>
				</tr> -->
      	    </table>
      	    </s:form>
      	    </div>
        	</div>     
		      </div>
      <div style="background-color: ;">
		                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<h5 class="fl"><a href="#" class="colSelect fl">项目基本信息列表</a></h5>
		             &nbsp;<input type="button" name="addButton" id="addButton" onclick="add();" value="新 增" class="fr">
		           <!--  <input type="button" name="excelButton" id="excelButton" value="导 出当页至 EXCEL" class="fl">
		              &nbsp;<input type="button" name="excelAllButton" id="excelAllButton" value="导 出全部至 EXCEL" class="fl">
		            --> </div>
		      </div>
        <!--Filter End-->
        <!--Table-->
        <!--  <s:set name="r" value="#request.pageResultSet.list"></s:set> -->
        <div class="mb10">
        	<table width="100%"  class="table_1" id="mytable">
                              <thead>
                              <tr class="tit">
                                <td class="t_c" width="3%">序号</td>
                                <td class="t_c" width="10%">项目类别</td>
                                <td class="t_c" width="6%">项目编号</td>
                                <td class="t_c" width="10%">项目名称</td>
                                <td class="t_c" width="7%">责任人</td>
                                <td class="t_c" width="10%">责任单位</td>
                                <td class="t_c" width="10%">状态</td>
                                <td class="t_c" width="15%">备注</td>
                                <!-- <td class="t_c" width="10%">年度推进目标</td> -->
                                <td class="t_c" width="17%">操作</td>
                                </tr>
                                </thead>
                               <tbody>
                               <s:set name="r" value="pageResultSet.list"></s:set>
                               
                              <s:iterator value="r" id="items" status="s">  
                              <tr id="dataTr">
                              	<td class="t_c"><s:property value="#s.index+1"/></td>
                               	<td class="t_c" id="pname"><s:property value="projectType"/></td>
                               	<td class="t_l"><s:property value="projectNumber" /></td>
                               	<td class="t_c"><s:property value="projectName" /></td>
                               	<td class="t_c"><s:property value="responsible" /></td>
                               	<td class="t_c"><s:property value="department" /></td>
                               	<td class="t_c"><s:property value="projectStatus" /></td>
                               	<td class="t_c"><s:property value="memo" /></td>
                               	<!-- <td class="t_c"><s:property value="projectForwardGoals" /></td> -->
                               	<td>
                               		<a class="fl mr5" href="javascript:void(0)" onclick="plan('<s:property value='projectId'/>')"  >计划管理</a>
                               		<a class="fl mr5" href="javascript:void(0)" onclick="view('<s:property value='projectId'/>')"  >查看</a>
                               		<a class="fl mr5" href="javascript:void(0)" onclick="editData('<s:property value='projectId'/>')">修改</a>
		  	                        <a class="fl mr5" href="javascript:void(0)" id="deletes<s:property value='#s.index'/>" title="<s:property value='projectId'/>" >删除</a> <!-- onclick="deleteData('<s:property value='projectId'/>')" -->
		  	                        <a class="fl mr5" href="javascript:void(0)" id="finish<s:property value='#s.index'/>" title="<s:property value='projectId'/>">完成</a><!-- onclick="finished'<s:property value='projectId'/>')" -->
                               		<a class="fl mr5" href="javascript:void(0)" onclick="Goal('<s:property value='projectId'/>');">年度推进目标</a>
                               	</td>
                               	</tr>
                                </s:iterator>
                              </tbody>
                              <s:if test="pageResultSet.pageInfo.totalRow!=0">
                              <tr class="tfoot">
        	      <td colspan="30"><div class="clearfix"><span class="fl">共<s:property value="pageResultSet.pageInfo.totalRow"/>条记录，当前显示<s:property value="pageResultSet.pageInfo.startRow"/>-
        	      <s:if test="pageResultSet.pageInfo.totalRow<(pageResultSet.pageInfo.startRow+pageResultSet.pageInfo.pageSize-1)">
        	        <s:property value="pageResultSet.pageInfo.totalRow"/>
        	      </s:if>
        	      <s:else>
        	        <s:property value="pageResultSet.pageInfo.startRow+pageResultSet.pageInfo.pageSize-1"/>
        	      </s:else>
        	      条</span>
        	      
        	        <ul class="fr clearfix pager">
        	          <li>Pages:<s:property value="pageResultSet.pageInfo.currentPage"/>/<s:property value="pageResultSet.pageInfo.totalPage"/>
        	          	<input type="hidden" value="<s:property value='pageResultSet.pageInfo.totalPage'/>" id="totalPageCount">
						<input type="text" id="pageNumber" name="pageNumber" min="0" max="999" step="1" class="input_tiny" value="<s:property value='pageResultSet.pageInfo.currentPage'/>"/>
        	            <input type="button" name="button" id="button" value="Go" onclick="goPage(0,0)">
      	            </li>
        	          
                       <s:if test="pageResultSet.pageInfo.currentPage==pageResultSet.pageInfo.totalPage">
                       	 <li>&gt;&gt;</li>
                       </s:if>
                       <s:else>
                        <li><a href="javascript:void(0)" onclick="goPage(<s:property value='pageResultSet.pageInfo.totalPage'/>,3)">&gt;&gt;</a></li>
                       </s:else> 
                      <li>
                      	<s:if test="pageResultSet.pageInfo.currentPage==pageResultSet.pageInfo.totalPage">	
                      		下一页
                      	</s:if>
                      	<s:else>
                      		<a href="javascript:void(0)" onclick="goPage(<s:property value='pageResultSet.pageInfo.currentPage'/>,2)">下一页</a>
                      	</s:else>
                      </li>
                      <li>
                      	<s:if test="pageResultSet.pageInfo.currentPage==1">
                      		上一页
                      	</s:if>
                      	<s:else>
                      		<a href="javascript:void(0)" onclick="goPage(<s:property value='pageResultSet.pageInfo.currentPage'/>,1)">上一页</a>
                      	</s:else>
                      </li>
                      <s:if test="pageResultSet.pageInfo.currentPage==1">
                      	<li>&lt;&lt;</li>
                      </s:if>
                      <s:else>
                      	<li><a href="javascript:void(0)" onclick="goPage(1,3)">&lt;&lt;</a></li>
                      </s:else>
      	          </ul>
      	        </div></td>
      	      </tr></s:if><s:else>
      	      <tr class="tfoot"><td colspan="30"><div class="clearfix">无相关数据</div></td>
   	          </tr>
      	      </s:else>
                            </table>

      </div>
      
      
</div>
</div>
</body>
</html>