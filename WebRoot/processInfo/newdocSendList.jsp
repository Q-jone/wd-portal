<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>行文查询</title>
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
			<script src="js/contextPath.js"></script>
		<script src="js/processComm.js"></script>
	
		<link type="text/css" href="../css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="../js/flick/jquery-ui-1.8.18.custom.min.js"></script>
		<script type="text/javascript" src="../js/flick/jquery.ui.datepicker-zh-CN.js"></script>
        <style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
		</style>		
	<script type="text/javascript">
	
			function clearInput(){
          $("#status").children("option:eq(0)").attr("selected",true);
          $("#doc_title").val("");
          $("#send_id").val("");  
          
          $("#doc_count").val("");
          $("#content").val("");
          $("#name").val("");
          $("#operator").val("");
          $("#date_start").val("");  
          
          $("#date_end").val("");          
                                           
        }

        function openNewDocSendForm(processname,incident,taskid){
        	var url = workflowPath+"/send-tDocSend/toFormPage.action?incidentNo="+incident+"&modelName="+encodeURI(processname)+"&taskId="+taskid;
        	window.open(url);
        }
       
    </script>



</head>

<body>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
			<div class="fl"><img id="show" onclick="showHide();" src="../css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li><a href="javascript:window.location.href='/portal/center/yygl/yg_index.jsp'">我的事务</a></li>
                	<li class="fin">行文查询</li>
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
            	<li><a href="/portal/processInfo/findDocReceiveByPage.action"><span>收文统计</span></a></li>
            	<li><a href="/portal/processInfo/findDocSendByPage.action"><span>行政发文统计</span></a></li>        			
        		<li><a href="/portal/processInfo/findDocDirectiveByPage.action"><span>呈批件统计</span></a></li>
        		<li class="selected"><a href="/portal/processInfo/findNewDocSendByPage.action"><span>行政发文统计（新）</span></a></li>
            </ul>
        </div>
        <!--Filter-->
      <div class="filter">
        	<div class="query">
        	<div class="p8 filter_search">
        	<s:form action="findNewDocSendByPage" id="form" method="post"  namespace="/processInfo">
        	
        	<input type="hidden" name="page" id="page" value="<s:property value="page"/>"/>
        	
        	 <table width="100%" border="0" cellspacing="0" cellpadding="0">
        	    <tr>
        	     <td class="t_r">文件题名</td>
        	      <td>
        	      <input type="text" id="doc_title" name="doc_title" class="input_xlarge" value="<s:property value='#request.doc_title'/>"/>
        	     </td>
        	     <td class="t_r">文件字号</td>
        	      <td>
        	      <input type="text" id="send_id" name="send_id" class="input_large"  value="<s:property value='#request.send_id'/>"/>
        	     </td>
        	     
        	     
        	     <td class="t_r">页数</td>
        	      <td>
        	      <input type="text" id="doc_count" name="doc_count" class="input_large"  value="<s:property value='#request.doc_count'/>"/>
        	     </td>
      	      </tr> 
      	      
      	      <tr>
        	     <td class="t_r">主抄送机关</td>
        	      <td>
        	      <input type="text" id="content" name="content" class="input_xlarge"  value="<s:property value='#request.content'/>"/>
        	     </td>
        	     <td class="t_r">签发</td>
        	      <td>
        	      <input type="text" id="name" name="name" class="input_large"  value="<s:property value='#request.name'/>"/>
        	     </td>
        	     <td class="t_r">经办</td>
        	      <td>
        	      	<input type="text" id="operator" name="operator" class="input_large"  value="<s:property value='#request.operator'/>"/>
        	     </td>
        	     
      	      </tr> 
      	       
      	        <tr>
        	     <td class="t_r">签发日期</td>
        	      <td>
        	      <input type="text" id="date_start" name="send_date_start" style="width:117px"  value="<s:property value='#request.send_date_start'/>"/>至
        	      <input type="text" id="date_end" name="send_date_end" style="width:117px"  value="<s:property value='#request.send_date_end'/>"/>
        	     </td>
        	     <td class="t_r">完成状态</td>
        	      <td>
        	      	<input type="hidden" id="h_status" value="<s:property value='#request.pstatus'/>"/>
        	      <select class="input_large" id="status" name="pstatus" >
        	      	<option value="">---请选择---</option>
        	      	<option value="1">进行中</option>
        	      	<option value="2">预归档</option>
        	      	<option value="3">已归档</option>
        	      </select>
        	     </td>
      	      </tr> 
        	    <tr>
        	      <td colspan="6" class="t_c">
                  	<input type="submit" value="检 索" />&nbsp;&nbsp;
                  	<input type="button" value="重 置" onclick="clearInput();"/></td>
				</tr>
      	    </table>
      	    </s:form>
      	    </div>
        	</div>     
		      <div class="fn clearfix">
		                  <h5 class="fl"><a href="#" class="colSelect fl">行政发文统计（新）</a></h5>
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
                                <td class="t_c" width="3%">序号</td>
                                <td class="t_c" width="7%">发文日期（签发日期）</td>
                                <td class="t_c" width="8%">文件字号</td>
                                <td class="t_c" width="22%">文件题名</td>
                                <td class="t_c" width="3%">页数</td>
                                <td class="t_c" width="10%">签发</td>
                                <td class="t_c" width="5%">经办</td>
                                <td class="t_c" width="28%">主抄送机关</td>
                                <td class="t_c" width="3%">备注</td>
                                <td class="t_c" width="5%">完成状态</td>
                                <td class="t_c" width="3%">表单</td>
                                <td class="t_c" width="3%">监控</td>
                                </tr>
                              <s:iterator value="#r.list" id="items" status="s">
                              <tr>
                              
                              	<td class="t_c"><s:property value="#s.index+1+(#r.pageInfo.currentPage-1)*10"/></td>
                              	<td class="t_c"><s:property value="#items[1]"/></td>
                               	<td class="t_c"><s:property value="#items[5]" /></td>
                                <td class="t_l"><s:property value="#items[6]"/></td>
                                <td class="t_c"><s:property value="#items[7]" /></td>
                                <td class="t_c"><s:property value="#items[10]" /></td>
                                <td class="t_c"><s:property value="#items[11]" /></td>
                                <td class="t_l"><s:property value="#items[12]" /></td>
                                <td class="t_c"></td>
                                <td class="t_c" id="status_td"><s:property value="#items[16]" /></td>
                                <td>
									<center>
										<a href="javascript:void(0)" onclick="openNewDocSendForm('<s:property value="#items[13]" />','<s:property value="#items[14]" />','<s:property value="#items[18]" />')"><img src="../css/default/images/p_open.gif"/></a>
									</center>
								</td>
								<td>
									<center>
										<a href="javascript:void(0)" onclick="openScan1('<s:property value="#items[13]" />','<s:property value="#items[14]" />','<s:property value="#items[18]" />')"><img src="../css/default/images/p_but9.gif"/></a>
									</center>
								</td>
                                </tr>
                                </s:iterator>
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="15"><div class="clearfix"><span class="fl"><s:property value="#r.pageInfo.totalRow"/>条记录</span>
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