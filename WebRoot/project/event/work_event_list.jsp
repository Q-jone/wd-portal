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
    <title>信息安全事件 </title>
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
      function deletes(id){
    	  
    	 var url= "/portal/project/event/deletes.action?id="+id+"&page="+$("#page").val();
    	 
    	 if(window.confirm("确定删除记录？"))
    	 		window.location=url;
    	 //alert("此计划未发布，暂时不能删除！！！");
      }
	function add(){
		
		window.location="/portal/project/event/goAdd.action";
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
                <li class="fin">信息安全事件 </li>
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
                    <header>
                        <div class="tit">
                            <div class="bg clearfix">
                                <h5 class="fl stats">信息安全事件</h5>

                            </div>
                        </div>
                    </header>
                    <div><h1 style="color: black;padding: 5px;">信息安全事件 </h1></div>
                    <div id="select">
                    	<div style="float: left;margin-left: 10px;"><input type="button"  value="新增" onclick="add();"/></div>
                        <s:form action="events" id="form" method="post" namespace="/project/event">
								<input type="hidden" id="page" name="page" value="<s:property value="pageResultSet.pageInfo.currentPage"/>" />
                           <%-- <s:select name="year" list="#{'2014':'2014年度','2015':'2015年度','2016':'2016年度'}"></s:select>
							 --%>
                        </s:form>
                    </div>
                    <div style="width:100%;">
                        <table width="100%"  class="table_1" id="mytable">
                            <thead>
                            <tr class="tit">
                                <td class="t_c" width="2%">序号</td>
                                <td class="t_c" width="8%">单位名称</td>
                                <td class="t_c" width="6%">报告时间</td>
                                <td class="t_c" width="10%">事件发生时间</td>
                                <td class="t_c" width="5%">信息来源</td>
                                <td class="t_c" width="5%">业务分类</td>
                                <td class="t_c" width="5%">事件类型</td>
                                <td class="t_c" width="5%">涉及网络或系统</td>
                                <td class="t_c" width="7%">事件描述</td>
                                <td class="t_c" width="5%">事件等级</td>
                                <td class="t_c" width="5%">事件原因分析</td>
                                <td class="t_c" width="5%">处理过程</td>
                                <td class="t_c" width="5%">处理结果</td>
                                <td class="t_c" width="5%">事件报告人</td>
                                <td class="t_c" width="5%">联系方式</td>
                                <td class="t_c" width="10%">备注</td>
                                <td class="t_c" width="8%">操作</td>
                            </tr>
                            </thead>
                            <tbody>

                            <s:iterator value="pageResultSet.list" status="s">
                            	 <tr id="dataTr">
                                    <td class="t_c"><s:property value="#s.index+1"/></td>
                                    <td class="t_c" id="pname"><s:property value="companyName"/></td>
                                    <td class="t_l"><s:date name="telTime" format="yyyy/MM/dd"/></td>
                                    <td class="t_c"><s:date name="beginTime" format="yyyy/MM/dd"/></td>
                                    <td class="t_c"><s:property value="messageSource" /></td>
                                    <td class="t_c"><s:property value="classification" /></td>
                                     <td class="t_c"><s:property value="eventType" /></td>                                    
                                    <td class="t_c"><s:property value="networkSystem" />  </td>
                                    <td class="t_c"><s:property value="descriptions" /></td>
                                    <td class="t_c"><s:property value="ranks"/></td>
                                    <td class="t_c"><s:property value="reasons"/></td>
                                    <td class="t_c"><s:property value="process" /></td>
                                    <td class="t_c"><s:property value="results" /></td>
                                    <td class="t_c"><s:property value="reporter"/></td>
                                    <td class="t_c"><s:property value="telphone" /></td>
                                    <td class="t_c"><s:property value="memo"/></td>
                                    <td class="t_c">
                                    	<a href="#" onclick="deletes('<s:property value='id' />')" class="fl mr5">删除</a>
                                    	<a href="/portal/project/event/event.action?id=<s:property value='id' />" class="fl mr5">修改</a>
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