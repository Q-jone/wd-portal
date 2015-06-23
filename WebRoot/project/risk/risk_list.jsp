<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta charset="utf-8"/>
    <title>安全风险管理列表</title>
    <link rel="stylesheet" href="<%=basePath %>css/page.css"/>
    <link rel="stylesheet" href="<%=basePath %>css/default/imgs.css"/>
    <link rel="stylesheet" href="<%=basePath %>css/reset.css"/>
    <!--[if IE 6.0]>
    <script src="../js/iepng.js" type="text/javascript"></script>
    <script type="text/javascript">
        EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
    </script>
    <![endif]-->
    <script src="<%=basePath %>js/html5.js"></script>
    <script src="<%=basePath %>js/jquery-1.7.1.js"></script>
    <!--<script src="../js/switchDept.js"></script>-->
    <script src="<%=basePath %>js/show.js"></script>
    <style type="text/css">
        #select {

            width: 100%;
            float: right;
            margin: 5px;
            text-align: right;
        }

        body {
            width: 100%;
            text-align: center;
        }

        li {
            background-image: url(<s:url value="/project/sysinfo/images/icon.gif"/>);
            background-repeat: no-repeat;
            padding-left: 15px;
            background-position: center;
        }

        li.tip2 {
            background-position: 0px 0px;
        }

        li.tip1 {
            background-position: 0 -28px;
        }

        li.tip0{
            background-position: 0 -56px;
        }
        .tip li.tipA {
            background-position: 0px 0px
        }

        .tip li.tipB {
            background-position: 0 -28px
        }

        .tip li.tipC {
            background-position: 0 -56px
        }
    </style>
    <script>

        $(function () {


            $("select").change(function () {
                $("#form").submit();
            });
        });
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
       	   $("#form").submit();
        }
      function deletes(riskManageId){
    	  
    	 var url= "/portal/project/risk/delete.action?riskManageId="+riskManageId+"&page="+$("#page").val();
    	 if(window.confirm("确定删除记录吗？"))
    	 	window.location=url;
    	 /**if(confirm==1){
    		 window.location=url;
    		 //window.location
    		 return ;
    	 }
    	 alert("此计划未发布，暂时不能删除！！！");*/
      }
	function add(){
		
		window.location="/portal/project/risk/goSave.action";
	}
	
    </script>

</head>
<body>
<div class="main">
    <!--Ctrl-->
    <div class="ctrl clearfix">
        <div class="fl"><img id="show" onclick="showHide();"
                             src="<%=basePath %>css/default/images/sideBar_arrow_right.jpg" width="46" height="30"
                             alt="收起"></div>
        <div class="posi fl">
            <ul>
                <li class="fin">安全风险管理列表</li>
               
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

    <div class="con clearfix">
        <div class="clearfix pt45">
            <div class="mb10">

                <!--Panel_8-->
                <div class="panel_4 mb10 panel_8" style="background-color: white;">
                   
                    <div><h1 style="color: black;padding: 5px;">安全风险管理列表</h1></div>
                    <div id="select">
                    	<div style="float: right;margin-right: 10px;"><input type="button"  value="新增" onclick="add();"/></div>
                        <s:form action="riskManages.action" id="form" method="post" namespace="/project/risk">
								<input type="hidden" id="page" name="page" value="<s:property value="pageResultSet.pageInfo.currentPage"/>" />
                            <!--<s:select name="year" list="#{'2014':'2014年度','2015':'2015年度','2016':'2016年度'}"></s:select>-->

                        </s:form>
                    </div>
                    <div style="width:100%;">
                        <table width="100%"  class="table_1" id="mytable">
                            <thead>
                            <tr class="tit">
                                <td class="t_c" width="2%">序号</td>
                                <td class="t_c" width="8%">单位</td>
                                <td class="t_c" width="10%">风险来源</td>
                                <td class="t_c" width="8%">发现时间</td>
                                <td class="t_c" width="8%">风险描述</td>
                                <td class="t_c" width="10%">类别</td>
                                <td class="t_c" width="5%">风险等级</td>
                                <td class="t_c" width="8%">风险处置方案</td>
                                <td class="t_c" width="8%">风险处置时限</td>
                                <td class="t_c" width="20%">处置跟踪情况</td>
                                <td class="t_c" width="8%">跟踪日期</td>
                                <td class="t_c" width="5%">操作</td>
                            </tr>
                            </thead>
                            <tbody>

                            <s:iterator value="pageResultSet.list" status="s">
                            	 <tr id="dataTr">
                                    <td class="t_c"><s:property value="#s.index+1"/></td>
                                    <td class="t_c" id="pname"><s:property value="department"/></td>
                                    <td class="t_l"><s:property value="riskFrom" /></td>
                                    <td class="t_c"><s:date name="discovery" format="yyyy-MM-dd"/></td>
                                    <td class="t_c"><s:property value="riskInfo" /></td>
                                    <td class="t_c"><s:property value="category" /></td>
                                     <td class="t_c"><s:property value="riskLevel" /></td>
                                    
                                    <td class="t_c"><s:property value="plan"/> </td>
                                    <td class="t_c"><s:property value="dateLimit" />  </td>
                                    <td class="t_c"><s:property value="trackInfo" /></td>
                                    <td class="t_c">
                                    	<s:date name="trackDate" format="yyyy-MM-dd"/>
                                    </td>
                                    <td class="t_c">
                                    	<a href="#" onclick="deletes('<s:property value='riskManageId' />')" class="fl mr5">删除</a>
                                    	<a href="/portal/project/risk/riskManage.action?riskManageId=<s:property value='riskManageId' />" class="fl mr5">修改</a>
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
						<input type="text" id="pageNumber" style="width:30px;" name="pageNumber" min="0" max="999" step="1" class="input_tiny" value="<s:property value='pageResultSet.pageInfo.currentPage'/>"/>
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
        </div>
    </div>
    <!--Filter End-->


</div>
</div>
</body>
</html>