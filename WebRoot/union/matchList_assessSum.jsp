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

<script src="<%=path%>/js/jquery-1.7.1.js"></script>
<script src="<%=path%>/deptDoc/js/ztree/jquery.ztree.all-3.4.min.js"></script>
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
				//window.location.href = "list.action?g=${g}";
				$("#form").submit();
				clearInterval(refresh);
			}
		}
		 
        $(document).ready(function () {
        	$("[name=status]").val('${status}');
        });
        
      //跳转到新增页面
        function showApprovePage(matchId){
        	sonWindow = window.open('<%=path%>/unionPrize/prizeApplySum.action?matchId='+matchId);
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
		div.jqi{ width:770px}
		.ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }	
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
                	<li class="fin">竞赛初审</li>
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
      <div style="padding-top: 35px;">
<%--         	<s:form action="list" id="form" method="post"  namespace="/examManage">
        	
      	    </s:form>       --%>
        <!--Filter-->
        <!--Filter-->
        <div class="filter">
            <div class="query">
                <div class="p8 filter_search">
                    <s:form action="list" id="form" method="post"  namespace="/unionMatch">
				<input type="hidden"  value="<s:property value='#request.pageResultSet.pageInfo.currentPage'/>" name="page" id="pageNo"/>
				<input type="hidden"  value="${g}" name="g" id="g"/>

                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td class="t_r" width="20%">专项名称</td>
                                <td width="30%">
                                    <input name="matchName" type="text" value="${matchName}" class="input_xlarge fi">
                                </td>

                                <td class="t_r" width="20%">专项状态</td>
                                <td width="30%">
                                    <select name="status" class="fi">
                                        <option value="">--请选择--</option>
                                        <option value="20">申报中</option>                         
                                        <option value="21">考评小组预审</option>
                                        <option value="22">考评小组领导审核</option>
                                        <option value="23">领导小组审核</option>
                                        <option value="26">通过</option>                                                                                                                                                                            
                                    </select>
                                </td>
                            </tr>

                            <tr>
                                <td colspan="4" class="t_c">
                                    <input type="submit" id="search" value="检 索" />&nbsp;
                                    <input type="button" onclick="clearInput()" value="重 置" /></td>
                            </tr>
                        </table>
                    </s:form>




                </div>
            </div>
            <div class="fn clearfix">
                <h5 class="fl"><a href="#" class="colSelect fl">专项主题列表</a></h5>
            </div>
        </div>

        <!--Filter End-->
        <!--Table-->
        <div class="mb10">
        	<table width="100%"  class="table_1">
                              <tbody>
                              <tr class="tit">
				                    <td class="t_c" width="5%">序号</td>
				                    <td class="t_c" width="5%">类别</td>
				                    <td class="t_c" width="20%">专项名称</td>
				                    <td class="t_c" width="10%">开始日期</td>
				                    <td class="t_c" width="10%">结束日期</td>
				                    <td class="t_c" width="10%">考评部门</td>
				                    <td class="t_c" width="20%">专项状态</td>
				                    <td class="t_c" width="20%">操作</td>
                               </tr>
                              <s:iterator value="#request.pageResultSet.list" id="data" status="s">
                              <tr>
                               	<td class="t_c"><s:property value="(#s.index+1)+(#request.pageResultSet.pageInfo.currentPage-1)*10"/></td>
                                <td class="t_c"><s:property value="#data.MATCH_TYPE"/></td>
                                <td class="t_c"><s:property value="#data.MATCH_NAME"/></td>
                                <td class="t_c"><s:property value="#data.BEGIN_DATE"/></td>
                                <td class="t_c"><s:property value="#data.END_DATE"/></td>
                                <td class="t_c"><s:property value="#data.DEPT_NAME"/></td>
                                <td class="t_c">
                                	<s:if test="#data.STATUS==@com.wonders.stpt.union.entity.bo.UnionMatch@APPLY_STATUS">申报中</s:if>
                                	<s:if test="#data.STATUS==@com.wonders.stpt.union.entity.bo.UnionMatch@ASSESS_SUM_STATUS">考评小组审核</s:if>
                                	<s:if test="#data.STATUS==@com.wonders.stpt.union.entity.bo.UnionMatch@ASSESS_SUM_REVIEW_STATUS">考评小组领导审核</s:if>
                                	<s:if test="#data.STATUS==@com.wonders.stpt.union.entity.bo.UnionMatch@LEAD_SUM_STATUS">领导小组初审</s:if>
                                	<s:if test="#data.STATUS==@com.wonders.stpt.union.entity.bo.UnionMatch@LEAD_SUM_REVIEW_STATUS">领导小组领导审核</s:if>
                                	<s:if test="#data.STATUS==@com.wonders.stpt.union.entity.bo.UnionMatch@LEAD_SUM_APPROVE_STATUS">集团领导审核</s:if>
                                	<s:if test="#data.STATUS==@com.wonders.stpt.union.entity.bo.UnionMatch@PASS_STATUS">审批通过</s:if>
                                </td>
                                <td class="t_c">
                                <s:if test="#data.STATUS!=@com.wonders.stpt.union.entity.bo.UnionMatch@ASSESS_SUM_STATUS"> 
                                	<a class="mr5" href="<%=path%>/unionPrize/prizeApplySum.action?matchId=<s:property value="#data.ID"/>" target="_blank">查看申报情况</a>
                                </s:if>
                                <s:if test="#data.STATUS==@com.wonders.stpt.union.entity.bo.UnionMatch@ASSESS_SUM_STATUS"> 
                                	<a class="mr5" href="javascript:showApprovePage('<s:property value="#data.ID"/>')">审核</a>
                                </s:if>                                
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