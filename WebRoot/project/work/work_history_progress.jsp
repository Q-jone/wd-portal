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
    <title>历史推进情况</title>
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
    <style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
    </style>

</head>

<body>
<div class="main">
    <!--Ctrl-->
    <!--Ctrl End-->
    <div>
        <!--Filter-->
        <div class="filter">
            <div class="query">
                <div class="p8 filter_search">
                </div>
            </div>
        </div>
        <div>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<h5 class="fl"><a href="#" class="colSelect fl">历史推进情况</a></h5>
        </div>
    </div>
    <!--Filter End-->
    <!--Table-->
    <div class="mb10">
    <s:form action="workProgresses" namespace="/project/progress" method="post" id="wform">
<input type="hidden" id="pageSize" name="pageSize" value="<s:property value="pageResultSet.pageInfo.pageSize"/>" />
        	<input type="hidden" id="page" name="page" value="<s:property value="pageResultSet.pageInfo.currentPage"/>" />
        	
</s:form>
        <table width="100%"  class="table_1" id="mytable">
            <thead>
            <tr class="tit">
                <td class="t_c" width="3%">序号</td>
                <td class="t_c" width="70%">推进情况</td>
                <td class="t_c" width="17%">时间</td>
                <td class="t_c" width="10%">操作</td>
            </tr>
            </thead>
            <tbody>
            <s:iterator value="pageResultSet.list" status="s">
            
                <tr id="dataTr">
                    <td class="t_c"><s:property value="#s.index+1"/></td>
                    <td class="t_c"  style="text-align: left;text-indent: 2"><s:property value="progress" /></td>
                    <td class="t_c"><s:date name="updateTime"format="yyyy-MM-dd"/>  </td>
                    <td>
                        <a class="fl mr5" title="<s:property value='progress' />" href="#" id="edit<s:property value='workSecludeId'/>" name="<s:property value="workProgressId" />">修改</a>
                        <a class="fl mr5" href="javascript:void(0)" onclick="deletes('<s:property value='workProgressId'/>','<s:property value='workSecludeId'/>')">删除</a>
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
<script>
function deletes(workProgressId,workSecludeId){
	var url="/portal/project/progress/delete.action?workProgressId="+workProgressId+"&workSecludeId="+workSecludeId;
	if(window.confirm("是否确定删除？"))
		window.location=url;
}


    $(function() {
    	$("a[id^=edit]").click(function(){
    		//$(this).dialog();
    		//alert($(this).attr("name"));
    		var aId=$(this).attr("id");//链接的id
    		var id=$(this).attr("name");//id值
    		var content=$(this).attr("title");//内容
    		var time=aId.substr(4);//sid
    		window.parent.history(id,content,time);
    		
    	});
		
    });
    
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
</script>
</html>