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
    <title>重点工作推进计划表</title>
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
    <style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
    </style>
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
	   $("#wform").submit();
}

function deletes(workSecludeId){
	var url="/portal/project/work/delete.action?workSecludeId="+workSecludeId;
	if(window.confirm("是否确定删除?"))
		window.location=url;
	
}
</script>


</head>

<body>
<div class="main">
<s:form action="workSecludes" namespace="/project/work" method="post" id="wform">
<input type="hidden" id="pageSize" name="pageSize" value="<s:property value="pageResultSet.pageInfo.pageSize"/>" />
        	<input type="hidden" id="page" name="page" value="<s:property value="pageResultSet.pageInfo.currentPage"/>" />
        	
</s:form>

    <!--Ctrl-->
    <div class="ctrl clearfix">
        <div class="fl"><img id="show" onclick="showHide();" src="<%=basePath %>css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
        <div class="posi fl">
            <ul>
                <li class="fin">重点工作推进计划表</li>
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
        
        <div>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	&nbsp;<input type="button" name="addButton" id="addButton" onclick="location.href='<s:url namespace="/project/work" action="goAdd.action"/>'" value="新 增" class="fr">
        </div>
         
    </div>
    <!--Filter End-->
    <!--Table-->
    <div class="mb10">
        <table width="100%"  class="table_1" id="mytable">
            <thead>
            <tr class="tit">
            	<td class="t_c" width="3%">序号</td>
                <td class="t_c" width="8%">重点工作</td>
                <td class="t_c" width="10%">目标及要求</td>
                <td class="t_c" width="10%">责任单位</td>
                <td class="t_c" width="10%">配合单位</td>
                <td class="t_c" width="15%">推进情况</td>
                <td class="t_c" width="15%">补充说明</td>
                <td class="t_c" width="10%">对应绩效考核项</td>
                <td class="t_c" width="12%">信息中心对口联系人</td>
                <s:if test="operatorAuth==\"1\"">
                    <td class="t_c" width="12%">操作</td>
                </s:if>
            </tr>
            </thead>
            <tbody>
            <s:iterator value="pageResultSet.list" status="s">
                <tr id="dataTr">
                	<td class="t_c"><s:property value="#s.index+1" /></td>
                    <td class="t_c"><s:property value="workName" /></td>
                    <td class="t_c"><s:property value="objective" /></td>
                    <td class="t_c"><s:property value="responsible" /></td>
                    <td class="t_c"><s:property value="cooperate" /></td>
                    <td class="t_c"><s:property value="progress" /></td>
                    <td class="t_c"><s:property value="memo" /></td>
                    <td class="t_c"><s:property value="kpi" /></td>
                    <td class="t_c"><s:property value="contactName"/>联系电话:<s:property value="contactTel"/></td>

                    <s:if test="operatorAuth==\"1\"">
                    <td>
                        <a class="fl mr5" href="<s:url value="/project/work/workSeclude.action"/>?workSecludeId=<s:property value='workSecludeId'/>">修改</a>
                        <a class="fl mr5" href="javascript:void(0)" onclick="deletes('<s:property value='workSecludeId'/>');">删除</a>
                    </td>
                    </tr>
                    </s:if>
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