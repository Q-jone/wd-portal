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

<script src="<%=path%>/js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="<%=path%>/js/flick/jquery-ui-1.8.18.custom.min.js"></script>
<script src="<%=path%>/js/flick/jquery.ui.datepicker-zh-CN.js"></script>
	<script type="text/javascript">
	
		//定义子窗口
		var sonWindow = null;
		//每1秒执行一次checkSonWindow()方法
		var refresh = setInterval("checkSonWindow()",1000);
		 //监测子窗口是否关闭
		function checkSonWindow(){
			if(sonWindow!=null && sonWindow.closed==true){
				$('#form').submit();
				clearInterval(refresh);
			}
		}
		 
        $(document).ready(function () {

        });
        
      //跳转到新增页面
        function showAddPage(){
        	sonWindow = window.open('showAdd.action');
        }
      
        function showEdit(id){
        	sonWindow = window.open('showEdit.action?id='+id);
        }      
        
        function showView(id){
        	sonWindow = window.open('showView.action?id='+id);
        }        
        
        function doDel(id){
        	var url = '<%=path%>/unionAccountApply/doDel.action';
        	if(confirm('您确定要删除该专项吗！')){
    	   		 $.ajax({
    	   		        type: "post",
    	   		        url: url,
    	   		        dataType: "json",
    	   		        data:"id="+id+"&random="+Math.random(),
    	   		        success: function (data) {
    	   		        	alert("删除成功");
    	   		        	$('#pageNo').val('1');
    	   		        	$("#form").submit();
    	   		        },
    	   		        error: function (XMLHttpRequest, textStatus, errorThrown) {
    	   		                alert("删除失败");
    	   		        }
    	   		});	 
        	}
    	}       
        
        function flagDone(id){
        	var url = '<%=path%>/unionAccountApply/changeStatus.action';
        	if(confirm('确定该条申请已处理完成吗！')){
    	   		 $.ajax({
    	   		        type: "post",
    	   		        url: url,
    	   		        dataType: "json",
    	   		        data:"id="+id+"&random="+Math.random(),
    	   		        success: function (data) {
    	   		        	alert("操作成功");
    	   		        	$('#pageNo').val('1');
    	   		        	$("#form").submit();
    	   		        },
    	   		        error: function (XMLHttpRequest, textStatus, errorThrown) {
    	   		                alert("删除失败");
    	   		        }
    	   		});	 
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
       
    </script>

	<style>
	</style>

</head>

<body>
<div id="domMessage" style="display:none;"> 
    <h1>请稍后</h1> 
</div> 	
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
			<div class="fl"><img id="show" onclick="showHide();" src="../css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li class="fin">申报人帐号申请</li>
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
      <div style="padding-top: 17px;">
<%--         	<s:form action="list" id="form" method="post"  namespace="/examManage">
        	
      	    </s:form>       --%>
        <!--Filter-->
      <div class="filter">
	        	<div class="query">
	        	<div class="p8 filter_search">
      	    	<form action="<%=path%>/unionAccountApply/list.action" id="form" method="post">
	        	<input type="hidden"  value="" name="page" id="pageNo"/>
	        	<input type="hidden"  value="${g}" name="g"/>

					</form>	        	
	      	    </div>
	        	</div>        
       				<div class="fn clearfix">
		                <h5 class="fl"><a href="#" class="colSelect fl">申请列表</a></h5>
			            <div class="fn clearfix">
			                <div class="fr">
			                    <input type="button" id="addToolBarBtn" value="新 增" onclick="showAddPage();" style="margin-left: 5px"/>
			                </div>
			            </div>		                  
		            </div>
		</div>

        <!--Filter End-->
        <!--Table-->
        <div class="mb10">
        	<table width="100%"  class="table_1">
                              <tbody>
                              <tr class="tit">
                                <!-- <td><input type="checkbox" id="test_checkbox_1" name="test_checkbox_1" /></td>-->
                                <td class="t_c" width="3%">序</td>
                                <td class="t_c" width="20%">姓名</td>
                                <td class="t_c" width="20%">工号</td>
                                <td class="t_c" width="20%">部门</td>
                                <td class="t_c" width="20%">状态</td>
                                <td class="t_c" width="17%">操作</td>
                                </tr>
                              <s:iterator value="#request.pageResultSet.list" id="data" status="s">
                              <tr>
                               	<td class="t_c"><s:property value="(#s.index+1)+(#request.pageResultSet.pageInfo.currentPage-1)*10"/></td>
                                <td class="t_c"><s:property value="#data.NAME"/></td>
                                <td class="t_c"><s:property value="#data.LOGIN_NAME"/></td>
                                <td class="t_c"><s:property value="#data.DEPT_NAME"/></td>
                                <td class="t_c">
                                	<s:if test="#data.STATUS==0">待处理</s:if>
                                	<s:if test="#data.STATUS==1">已处理</s:if>
                                </td>                                
                                <td class="t_c"> 
                                	<s:if test="#data.STATUS==0">
	                                	<s:if test="#request.g=='admin'">
		                                	<a class="mr5" style="display: inline;cursor:pointer;" href="javascript:flagDone('<s:property value="#data.ID"/>');">处理完毕</a>     
		                                </s:if>
		                                <s:else>
		                                	<a class="mr5" style="display: inline;cursor:pointer;" href="javascript:showEdit('<s:property value="#data.ID"/>');">修改</a>     
		                                	<a class="mr5" style="display: inline;cursor:pointer;" onclick="doDel('${data.ID}')">删除</a>		                                
		                                </s:else>                                	
	                                </s:if>
	                                <a class="mr5" style="display: inline;cursor:pointer;" href="javascript:showView('<s:property value="#data.ID"/>');">查看</a>
                                </td>
                                </tr>
                                </s:iterator>
                              </tbody>
                              <tr class="tfoot">
                              	<s:set name="start" value="(#request.pageResultSet.pageInfo.currentPage-1)*#request.pageResultSet.pageInfo.pageSize + 1"/>
                                <td colspan="10"><div class="clearfix"><span class="fl"><s:property value="#request.pageResultSet.pageInfo.totalRow"/>条记录<s:if test="#request.pageResultSet.pageInfo.totalRow!=0">，当前显示<s:property value="#start"/>-<s:property value="#start+#request.pageResultSet.list.size-1"/>条</s:if></span>
                           		<ul class="fr clearfix pager">
		                             <li>Pages:<s:property value="#request.pageResultSet.pageInfo.currentPage"/>/<s:property value="#request.pageResultSet.pageInfo.totalPage"/>
		                             			<input type="hidden" value="<s:property value='#request.pageResultSet.pageInfo.totalPage'/>" id="totalPageCount">
			                                  <input type="text" id="number" name="pageNumber" min="0" max="999" step="1" class="input_tiny" value="<s:property value='#request.pageResultSet.pageInfo.currentPage'/>"/>
			                                  <input type="button" name="button" id="button" value="Go" onclick="goPage(0,0)">
		                             </li>
                             
		                             <s:if test="#request.pageResultSet.pageInfo.currentPage==#request.pageResultSet.pageInfo.totalPage">
		                             <li><a href="javascript:void(0)">&gt;&gt;</a></li>
		                             </s:if>
		                             <s:else>
		                              <li><a href="javascript:void(0)" onclick="goPage(<s:property value='#request.pageResultSet.pageInfo.totalPage'/>,3)">&gt;&gt;</a></li>
		                             </s:else>
                              
	                             	<li>
	                             	<s:if test="#request.pageResultSet.pageInfo.currentPage==#request.pageResultSet.pageInfo.totalPage">	
	                             		<a href="javascript:void(0)">下一页</a>
	                             	</s:if>
	                             	<s:else>
	                             		<a href="javascript:void(0)" onclick="goPage(<s:property value='#request.pageResultSet.pageInfo.currentPage'/>,2)">下一页</a>
	                             	</s:else>
	                             	</li>
	                             	<li>
	                             	<s:if test="#request.pageResultSet.pageInfo.currentPage==1">
	                             		<a href="javascript:void(0)">上一页</a>
	                             	</s:if>
	                             	<s:else>
	                             		<a href="javascript:void(0)" onclick="goPage(<s:property value='#request.pageResultSet.pageInfo.currentPage'/>,1)">上一页</a>
	                             	</s:else>
	                             	
	                             	</li> 
	                             
	                             	<s:if test="#request.pageResultSet.pageInfo.currentPage==1">
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
        <!--Table End-->
</div>
</div>
</body>
</html>