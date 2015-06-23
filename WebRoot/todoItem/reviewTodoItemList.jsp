<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>合同后审待办事项</title>
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
		<link type="text/css" href="../css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		<script src="../js/flick/jquery-ui-1.8.18.custom.min.js"></script>
		<script src="../js/flick/jquery.ui.datepicker-zh-CN.js"></script>
		<script src="/portal/todoItem/js/jobcontact.js"></script>
		<style type="text/css">
	        .ui-datepicker-title span {display:inline;}
	        button.ui-datepicker-current { display: none; }
	        a img{margin:0 auto;}
			#jobContactInfo {display:none;position:absolute;width:380px;height:auto;}
			.hoverTable {border:0px solid #DADCD3;background:#FBFACC;width:100%;cellpadding:0px; cellspacing:0px;text-align:center;border-collapse:collapse;}
			.hoverTable tr td {width:0%;border:1px solid #DADCD3;text-align:middle;padding:2px;}
			.title{ font-size:12px; color:#931602;  font-weight:bold;text-align:center;display:inline;}
			.summary{display:block;cursor:pointer;}
			.table_1 font,.table_1 b {display:inline;}
		</style>
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
     
     var intHand=null;
		var rtn=null;	
		function checkWin(){
			if(rtn!=null && rtn.closed){
				clearInterval(intHand);
				intHand=null;
				rtn=null;
				$("form").submit();
			}
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
        
	         $('#occurtimes').datepicker({
		    		inline: true,  
	                changeYear: true,  
	                changeMonth: true , 
	                showButtonPanel:true,     
	                closeText:'清除',   
	                currentText:'occurtimes'//仅作为“清除”按钮的判断条件					
		    	});
		    $('#occurtimee').datepicker({
		    		inline: true,  
	                changeYear: true,  
	                changeMonth: true , 
	                showButtonPanel:true,     
	                closeText:'清除',   
	               currentText:'occurtimee'//仅作为“清除”按钮的判断条件					
		    	});
	    	
	    	 $(document).on("click", ".ui-datepicker-close",function (){              
             if($(this).parent("div").children("button:eq(0)").text()=="occurtimes") $("#occurtimes").val("");
             if($(this).parent("div").children("button:eq(0)").text()=="occurtimee") $("#occurtimee").val("");     
             
           });
           
        	$(".todoUrl").click(function(){
				var url = $("#url",$(this).parents("td")).val();
        		
        		rtn = window.open(url,"workflowtodo");
				intHand = setInterval("checkWin()",3000);
					return false;
        	})
        	
        	$(".todoScan").click(function(){
        		var pname = $("#pname",$(this).parents("td")).val();
        		if(pname=="多级工作联系单"){
        			var url = "";
        			url = "http://10.1.48.101:8080/workflow/contact-ultimus/scanList.action?"+
        					"id="+ $("#taskid",$(this).parents("td")).val();
        		}else if(pname=="工作联系单"){
        			url ="http://10.1.44.18/jobContact/newVersion/jobContactScan.jsp?"+
        					"groupId="+$("#taskid",$(this).parents("td")).val()+"&flag=1";
        		}else{
        			var url = "";
        			url = "http://10.1.44.17/sLogin/workflow/TaskStatus.aspx"
							+ "?TaskID="
							+ $("#taskid",$(this).parents("td")).val();
        		}
		 		window.open(url);
		 			return false;
        	})
        	
        	$("#clearInput").click(function(){
        		$("#typename").val("");
	      		$("#title").val("");
	      		$("#occurtimes").val("");
	      		$("#occurtimee").val("");
			})
		
			//jobContactLoad("G01006000513","2111","忻然","0");
			//jobContactLoad("G00100000161","2116","<s:property value='#session.userName'/>","0");
			//jobContactLoad("<s:property value='#session.cs_login_name'/>","<s:property value='#session.oldDeptId'/>","<s:property value='#session.userName'/>","0");

      })
        
       
    </script>



</head>

<body>
	<div id="jobContactInfo" style="z-index:2;"></div>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
			<div class="fl"><img id="show" onclick="showHide();" src="../css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li><a href="javascript:window.location.href='/portal/center/wdsw/wd_index.jsp'">我的事务</a></li>
                	<li class="fin">合同后审待办事项</li>
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
      <div class="tabs_2">
        	<ul>
            	<li><a href="/portal/processInfo/findDocReceiveByPage.action" target="_blank"><span>收文查询</span></a></li>
            	<li><a href="/portal/processInfo/findDocSendByPage.action" target="_blank" ><span>发文查询</span></a></li>        			
        		<li><a href="/portal/processInfo/findDocDirectiveByPage.action" target="_blank"><span>呈批件查询</span></a></li>
        		<li><a href="/portal/contractManage/list.action" target="_blank"><span>合同查询</span></a></li>
        		<li><a href="/portal/processInfo/findJobContactByPage.action" target="_blank"><span>工作联系单查询</span></a></li>
        		<li><a href="/portal/processInfo/findDbByPage.action" target="_blank"><span>督办查询</span></a></li>
            </ul>
        </div>
	       <div class="fn clearfix" style="height:5px;background-color:#fff;"></div>
        <!--Filter-->
      <div class="filter">
        	<div class="query">
        	<div class="p8 filter_search">
        	<s:form action="reviewTodoItemList" id="form" method="post"  namespace="/todo" theme="simple">
        	
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
        	      <td class="t_r">接收时间</td>
        	      <td>
        	      <input type="text" id="occurtimes" name="occurtimes" class="input_large date" value="<s:property value='occurtimes'/>" readonly="readonly"/>
                    -
                  <input type="text" id="occurtimee" name="occurtimee" class="input_large date" value="<s:property value='occurtimee'/>" readonly="readonly" />
        	      </td>
        	      
      	        </tr>
      	        
      	        
        	    <tr>
        	      <td colspan="6" class="t_c">
                  	<input type="submit" value="检 索" />&nbsp;&nbsp;
                  	<input id="clearInput" type="button" value="重 置"/></td>
				</tr>
      	    </table>
      	    </s:form>
      	    </div>
        	</div>     
		      <div class="fn clearfix">
		                  <h5 class="fl"><a href="#" class="fl">待办事项信息列表</a></h5>
		             <!-- <input type="submit" name="button2" id="button2" value="新 增" class="fr">
		             <h5 class="fr"><font class="L_08">提示：待办事项35分钟更新一次。</font></h5>--> 
		            </div>
		      </div>


      
        <!--Filter End-->
        <!--Table-->
         <s:set name="r" value="pageResultSet"></s:set>  
        <div class="mb10">
        	<table width="100%"  class="table_1">
                             <tbody>
                              <tr class="tit">
                                <!-- <td><input type="checkbox" id="test_checkbox_1" name="test_checkbox_1" /></td>-->
                             	<td class="t_c">序号</td>
                                <td class="t_c">类型</td>
                                <td class="t_c">流程名称</td>
                                <td style="width:50%;" class="t_c">摘要</td>
                                <td class="t_c">步骤</td>
                                <td class="t_c">接收时间</td>
                                <td class="t_c">备注</td>
                                <td class="t_c">表单</td>
                                <td class="t_c">监控</td>
                                </tr>
                              <s:iterator value="#r.list" id="items" status="s">
                              <tr>
                              
                              	<td class="t_c"><s:property value="(#s.index+1)+(#r.pageInfo.currentPage-1)*10"/></td>
                              	<td class="t_c">&nbsp;</td>
                               	<td class="t_c"><s:property value="#items.pname" /></td>
                                <td><s:property value="#items.title" escape="0"/></td>
                                <td class="t_c"><s:property value="#items.stepname" escape="0"/></td>
                                <td class="t_c"><s:property value="#items.occurtime" /></td>
                                <td class="t_c">&nbsp;</td>
                                <td class="t_c nwarp">
                                <input type="hidden" value="<s:property value='#items.url'/>" id="url"/>
                                <input type="hidden" value="<s:property value='#items.taskid'/>" id="taskid"/>
                                
                                <a href="javascript:void(0);" class="todoUrl"><img src="../css/default/images/p_open.gif"></a></td>
                                 <td class="t_c nwarp">
                                <input type="hidden" value="<s:property value='#items.taskid'/>" id="taskid"/>
                                <input type="hidden" value="<s:property value='#items.pname'/>" id="pname"/>
                                
                                <a href="javascript:void(0);" class="todoScan"><img src="../css/default/images/p_but9.gif"></a></td>
                                </tr>
                                </s:iterator>
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="11"><div class="clearfix"><span class="fl">共<s:property value="#r.pageInfo.totalRow"/>条记录</span>
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