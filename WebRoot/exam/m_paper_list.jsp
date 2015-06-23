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
<title>试卷管理</title>
<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/default/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
<link rel="stylesheet" href="../exam/ui/js/prompt/impromptu.css"/>    
<!--[if IE 6.0]>
           <script src="../js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
       	<script src="../js/jquery-1.7.1.js"></script>
	<script type="text/javascript" src="../exam/ui/js/prompt/impromptu.js"></script>
	<script type="text/javascript">
        $(document).ready(function () {
        	$("#button2").click(function(){
        		window.location.href = "add.action";
        	})
        	$(".t_c a").css("display","inline");
            var $tbInfo = $(".filter .query input:text");
            $tbInfo.each(function () {
                $(this).focus(function () {
                    $(this).attr("placeholder", "");
                });
            });
			
			var $tblAlterRow = $(".table_1 tbody tr:even");
			if ($tblAlterRow.length > 0)
				$tblAlterRow.css("background","#fafafa");	
			
        });
        
        
       
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
       
       function showGroup(mainId){
			var surl = "showGroup.action?mainId=" + mainId;
			$.prompt('<iframe frameBorder="0" id="xwind" name="xwind" scrolling="yes" src="'+surl+'" style="HEIGHT:350px; VISIBILITY: inherit; WIDTH:750px; Z-INDEX:999"></iframe>');
		}
       
       function edit(id){
    	   window.location.href = 'add.action?id='+id;
       }
       
       function del(id){
    	   if(window.confirm('确定要删除吗？\n这将影响到相关的答卷，且不可恢复。\n请谨慎操作。')){
    		   window.location.href = 'delete.action?id='+id;    		   
    	   }    	   
       }       
       
       function clearInput(){
    	   $('#title').val('');
       }
    </script>

	<style>
		div.jqi{ width:770px}
	</style>

</head>

<body>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
			<div class="fl"><img id="show" onclick="showHide();" src="../css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li><a href="#">试卷管理</a></li>
                	<li class="fin">试卷管理</li>
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
      <div class="filter">
	        	<div class="query">
	        	<div class="p8 filter_search">
	        	<s:form action="list.action" id="form" method="post"  namespace="/examManage">
	        	<input type="hidden"  value="" name="page" id="pageNo"/>
	        	 <table width="100%" border="0" cellspacing="0" cellpadding="0">
	        	    <tr>
	        	      <td colspan="6" class="t_r">
	        	      	试卷名称 : <input type="text" value="<s:property value="#request.title"/>" name="title" id="title"/>
	                  	<input type="submit" value="检 索" />&nbsp;&nbsp;
	                  	<input type="button" value="重 置" onclick="clearInput()"/></td>
					</tr>
	      	    </table>
	      	    </s:form>
	      	    </div>
	        	</div>        
       				<div class="fn clearfix">
		                  <h5 class="fl"><a href="#" class="colSelect fl">试卷列表</a></h5>
		             <input type="button" name="button2" id="button2" value="创建试卷" class="fr">
		            </div>
		</div>

        <!--Filter End-->
        <!--Table-->
        <div class="mb10">
        	<table width="100%"  class="table_1">
                              <tbody>
                              <tr class="tit">
                                <!-- <td><input type="checkbox" id="test_checkbox_1" name="test_checkbox_1" /></td>-->
                                <td class="t_c" width="35%">试卷名称</td>
                                <td class="t_c" width="10%">创建人</td>
                                <td class="t_c" width="15%">创建时间</td>
                                <td class="t_c" width="10%">是否开放</td>
                                <td class="t_c" width="10%">试题数量</td>
                                <td class="t_c" width="20%">操作</td>
                                </tr>
                              <s:iterator value="#request.pageResultSet.list" id="data">
                              <tr>
                               	<td class="t_c"><s:property value="#data[1]"/></td>
                                <td class="t_c"><s:property value="#data[2]"/></td>
                                <td class="t_c"><s:property value="#data[3]"/></td>
                                <td class="t_c"><s:property value="#data[4]==0?'开放':'不开放'"/></td>
                                <td class="t_c"><s:property value="#data[5]"/></td>
                                <td class="t_c"> 
                                <a class="mr5" href="javascript:showGroup('<s:property value="#data[0]"/>')">分组设置</a>
                                <a class="mr5" href="listQ.action?pid=<s:property value="#data[0]"/>">试题设置</a>
                                <a class="mr5" href="javascript:edit('<s:property value="#data[0]"/>')">修改</a>
                                <a class="mr5" href="javascript:del('<s:property value="#data[0]"/>')">删除</a>
                                </td>
                                </tr>
                                </s:iterator>
                              </tbody>
                              <tr class="tfoot">
                              	<s:set name="start" value="(#request.pageResultSet.pageInfo.currentPage-1)*#request.pageResultSet.pageInfo.pageSize + 1"/>
                                <td colspan="10"><div class="clearfix"><span class="fl"><s:property value="#request.pageResultSet.pageInfo.totalRow"/>条记录，当前显示<s:property value="#start"/>-<s:property value="#start+#request.pageResultSet.list.size-1"/>条</span>
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