<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>待办事项查询</title>
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
	<script type="text/javascript">
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
      
		function ifNewJobContact(obj){
			var targetTr = obj.parent("td").parent("tr");
			var summaryDiv = $(targetTr).find(".summary");
			//console.log(summaryDiv);
			if(summaryDiv.length>0){
				return true;
			}
			return false;
		}
		
		
        $(document).ready(function () {
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
        
        	$(".todoUrl").click(function(){
        		var flag = ifNewJobContact($(this));
        		var url = "";
        		if(flag){
        			var targetTr = $(this).parent("td").parent("tr");
					var summaryDiv = $(targetTr).find(".summary");		
					var groupid = $(summaryDiv).attr("groupid");

					url = "http://10.1.44.18/ToOperateJob.action"
											+ "?groupId="+$("#task_id",$(this).parents("td")).val()
											+ "&modelName=" + encodeURI($("#model_id",$(this).parents("td")).val())
											+ "&task_type=" + summaryDiv.attr("ptype")
											+ "&dbxtype=" + summaryDiv.attr("dbxtype");
        			
        		}else{
        			url = "http://10.1.44.18/openflowform.action"
											+ "?task_id="
											+ $("#task_id",$(this).parents("td")).val()
											+ "&task_user_name="
											+ $("#task_user_name",$(this).parents("td")).val()
											+ "&model_id="
											+ encodeURI($("#model_id",$(this).parents("td")).val())
											+ "&instance_id="
											+ $("#instance_id",$(this).parents("td")).val()
											+ "&step_name="
											+ encodeURI($("#step_name",$(this).parents("td")).val())
											+ "&pinstance_id="
											+ $("#pinstance_id",$(this).parents("td")).val()
											+ "&processName="
											+ encodeURI($("#processName",$(this).parents("td")).val())
											+ "&task_type="
											+ $("#task_type",$(this).parents("td")).val();
				}
        		window.open(url);
        	})
        	
        	$(".todoScan").click(function(){
        		var url = "";
	        	var flag = ifNewJobContact($(this));
				if(flag){
					var targetTr = $(this).parent("td").parent("tr");
					var summaryDiv = $(targetTr).find(".summary");
					var groupid = $(summaryDiv).attr("groupid");
					url ="http://10.1.44.18/jobContact/newVersion/jobContactScan.jsp?groupId="+groupid+"&flag=1";
					
				}else{
					url = "http://10.1.44.17/sLogin/workflow/TaskStatus.aspx"
		 									+ "?TaskID="
		 									+ $("#task_id",$(this).parents("td")).val();
				}
        			
		 		window.open(url);
        	})
		
       
      })
        
       
    </script>



</head>

<body>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
			<div class="fl"><img id="show" onclick="showHide();" src="../css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li><a href="javascript:window.location.href='/portal/center/wdsw/wd_index.jsp'">我的事务</a></li>
                	<li class="fin">待办事项</li>
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
        	<s:form action="todoItemListNew" id="form" method="post"  namespace="/todo" theme="simple">
        	
        	<input type="hidden" name="page" id="page" value="<s:property value="page"/>"/>
        	
        	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
        	    <tr>
        	       <td class="t_r">流程名称</td>
        	      <td>
        	      <input type="text" id="typename" name="typename" class="input_large"  value="<s:property value="typename"/>"/>
        	      </td>
        	       <td class="t_r">摘要</td>
        	      <td>
        	      <input type="text" id="title" name="title" class="input_large" value="<s:property value="title"/>"/>
        	      </td>
        	      
      	        </tr>
      	        <tr>
        	       <td class="t_r">申请部门</td>
        	       <td>
        	  		<input type="text" id="applydept" name="applydept" class="input_large" value="<s:property value="applydept"/>"/>
        	  	</td>
      	        </tr>
        	    <tr>
        	      <td colspan="6" class="t_c">
                  	<input type="submit" value="检 索" />&nbsp;&nbsp;
                  	<input type="reset" value="重 置"/></td>
				</tr>
      	    </table>
      	    </s:form>
      	    </div>
        	</div>     
		      <div class="fn clearfix">
		                  <h5 class="fl"><a href="#" class="colSelect fl">待办事项信息列表</a></h5>
		             <!-- <input type="submit" name="button2" id="button2" value="新 增" class="fr"> --> 
		            </div>
		      </div>


      
        <!--Filter End-->
        <!--Table-->
         <s:set name="r" value="#request.result"></s:set>  
        <div class="mb10">
        	<table width="100%"  class="table_1">
                              <tbody>
                              <tr class="tit">
                                <!-- <td><input type="checkbox" id="test_checkbox_1" name="test_checkbox_1" /></td>-->
                                <td class="t_c">序号</td>
                                <td class="t_c">类别</td>
                                <td class="t_c">流程名称</td>
                                <td class="t_c">摘要</td>
                                <td class="t_c">步骤</td>
                                <td class="t_c">完成时限</td>
                                <td class="t_c">申请人</td>
                                <td class="t_c">申请部门</td>
                                <td class="t_c">操作</td>
                                </tr>
                              <s:iterator value="#r.list" id="items" status="s">
                              <tr>
                              
                              	<td class="t_c"><s:property value="#s.index+1"/></td>
                              	<td class="t_c"><s:property value="#items.priorities_show" escape="0"/></td>
                               	<td class="t_c"><s:property value="#items.pname" /></td>
                                <td class="t_c"><s:property value="#items.summary" escape="0"/></td>
                                <td class="t_c"><s:property value="#items.steplabel" /></td>
                                <td class="t_c"><s:property value="#items.overduetime" /></td>
                                <td class="t_c"><s:property value="#items.apply_username" /></td>
                                <td class="t_c"><s:property value="#items.deptname" /></td>
                                <td class="t_c">
                                <input type="hidden" value="<s:property value='#items.taskid'/>" id="task_id"/>
                                <input type="hidden" value="<s:property value='#items.UserName'/>" id="task_user_name"/>
                                <input type="hidden" value="<s:property value='#items.pname'/>" id="model_id"/>
                                <input type="hidden" value="<s:property value='#items.pincident'/>" id="instance_id"/>
                                <input type="hidden" value="<s:property value='#items.steplabel'/>" id="step_name"/>
                                <input type="hidden" value="<s:property value='#items.incident'/>" id="pinstance_id"/>
                                <input type="hidden" value="<s:property value='#items.processname'/>" id="processName"/>
                                <input type="hidden" value="<s:property value='#items.task_type'/>" id="task_type"/>
                                <input class="todoUrl" type="button" value="表单"/><input class="todoScan" type="button" value="监控"/></td>
                                </tr>
                                </s:iterator>
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="10"><div class="clearfix"><span class="fl"><s:property value="#r.pageInfo.totalRow"/>条记录</span>
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
        <!--Table End-->
</div>
</div>
</body>
</html>