<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>Untitled Document</title>
<link rel="stylesheet" href="../css/formalize.css" />

<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/default/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
<!--[if IE 6.0]>
           <script src="js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
        <script src="../js/html5.js"></script>
        <script src="../js/jquery-1.7.1.js"></script>
		<script src="../js/jquery.formalize.js"></script>
		<script src="../js/show.js"></script>
		<!--<script src="../js/switchDept.js"></script>-->
		<script type="text/javascript">
		$(document).ready(function(){
			$("#subList").height($("#newList").height() - $("#relativeSub").height() - 4);
			loadShow();
		});
		
		$(window).resize(function(){
		 	$("#subList").height($("#newList").height() - $("#relativeSub").height() - 4);
		});
		
		function goPage(page){
			$("#page").val(page);
			$("#form").submit();
		}
		
		function goPageRedirect(){
			if(!$("#redirectPage").val().match(/^[0-9]+$/)){
       			alert("请输入数字");
       			$("#redirectPage").val("");
       			$("#redirectPage").focus();
       			return ;
       		}
	       		
			$("#page").val($("#redirectPage").val());
			$("#form").submit();
		}
		
		$(function(){
			if($("#asideInfo").text()=="无相关信息。"){
				$("#aside").hide();
				$("#newList").attr("style","width:99%;");
			}
		})
		</script>

</head>

<body>

	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img id="show" onclick="showHide();" src="../css/default/images/sideBar_arrow_right.jpg" width="46" height="30" title="收起"></div>
            <div class="posi fl">
            	<ul>
                	<!-- <li><a href="javascript:window.location.href='/portal/center/yygl/yg_index.jsp'">信息发布</a></li> -->
                	<li class="fin">信息发布</li>
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
        <div class="clearfix pt45">
        	<!--News_list-->
            <div id="newList" class="mb10 right_main News_list">
            	<header class="clearfix mb10">
                	<h4 class="fl">
                	<s:if test='infoSearchVo.sj_id.indexOf(",")>0'>党纪工团</s:if>
                	<s:elseif test="pageResultSet.list.get(0).category!=null">
                	<s:property value="pageResultSet.list.get(0).category" />
                	</s:elseif>
                	<s:else>
                	无相关信息。
                	</s:else>
                	</h4>
                	<form action="/portal/infoSearch/findStfbNewsByPage.action" id="form" method="post">
                    <div class="fr p10">
                      <input type="text" name="searchWord" id="searchWord" value="<s:property value="infoSearchVo.searchWord"/>" />
                      <input type="submit" value="检索" />
                      	<input type="hidden" name="page" id="page" value="<s:property value="infoSearchVo.page"/>"/>
						<input type="hidden" name="sj_id" id="sj_id" value="<s:property value="infoSearchVo.sj_id"/>"/>
                    </div>
                    </form>
                </header>          
                <div class="list">
                    <ul>
                    <s:iterator value="pageResultSet.list" id="news">
                    	 <li>
                            <dl>
                                <dt class="clearfix">
                                    <span class="type">[<s:property value="#news.category" />]</span> <a target="_blank" href="http://10.1.44.18/stfb<s:property value="#news.url" />/con<s:property value="#news.identifiedNo" />.htm"><s:property escape="0" value="#news.title" /></a>
                                    <span><s:property value="#news.createTime" /></span>
                                </dt>
                                <dd><s:property escape="0" value="#news.content" /></dd>
                            </dl>
                        </li>
                      </s:iterator>
                    </ul>
                </div>
                <div class="pager mb10 clearfix">
                	<span class="fl">记录总数：<s:property value="pageResultSet.pageInfo.totalRow"/></span>
                    <div class="fr mr5"><input value="<s:property value="pageResultSet.pageInfo.currentPage"/>" type="number" id="redirectPage" name="redirectPage" class="input_tiny" />/<s:property value="pageResultSet.pageInfo.totalPage"/>
                <a style="display:inline;" href="javascript:goPageRedirect();">转到</a></div>
                    <ul class="fr clearfix mr5">
                    	<li><a href="javascript:goPage('1');">首页</a></li>
                    	<li><a href="javascript:goPage('<s:property value="pageResultSet.pageInfo.currentPage-1"/>');">上一页</a></li>
                    	<li><a href="javascript:goPage('<s:property value="pageResultSet.pageInfo.currentPage+1"/>');">下一页</a></li>
                    	<li><a href="javascript:goPage('<s:property value="pageResultSet.pageInfo.totalPage"/>');">尾页</a></li>
                    </ul>
                </div>
            </div>
            <!--News_list End-->
            <!--News_list right-->
            <aside class="mb10 related panel_5" id="aside">
            	<div id="relativeSub" class="tit"><h4>相关栏目</h4></div>
                <div id="subList" class="con">
                	<ul class="list">
                    	<li><a href="#" id="asideInfo">无相关信息。</a></li>
                    </ul>
                </div>
            </aside>
            <!--News_list right End-->
        </div>
	</div>

</body>
</html>
