<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>流程中止</title>
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
        <script src="js/jquery.form.js"></script>
		<script src="../js/jquery.formalize.js"></script>
		<!--<script src="../js/switchDept.js"></script>-->
		<script src="../js/show.js"></script>
		<script src="js/common.js"></script>
		<link type="text/css" href="../css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="../js/flick/jquery-ui-1.8.18.custom.min.js"></script>
		<script type="text/javascript" src="../js/flick/jquery.ui.datepicker-zh-CN.js"></script>
        <style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
		</style>		
	<script type="text/javascript">
	
	
		
		
    </script>



</head>

<body>
<%-- 操作页面--%>
		<jsp:include page="handle.jsp"></jsp:include>
		<jsp:include page="reason.jsp"></jsp:include>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
			<div class="fl"><img id="show" onclick="showHide();" src="../css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li><a href="javascript:window.location.href='/portal/center/wdsw/wd_index.jsp'">我的事务</a></li>
                	<li class="fin">流程中止</li>
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
           		<li><a href="javascript:void(0);"><span>行政收文</span></a></li>
           		<li><a href="javascript:void(0);"><span>行政发文</span></a></li> 
           		<li><a href="javascript:void(0);"><span>行政呈批件</span></a></li>
           	</ul>
        </div>
        <!--Filter-->
      <div class="filter">
        	<div class="query">
        	<div class="p8 filter_search">
        	<s:form action="list" id="form" method="post"  namespace="/processStop">
        	<input type="hidden" name="type" id="type" value="<s:property value='#request.type'/>"/>
        	
        	<input type="hidden" name="page" id="page" value="<s:property value="page"/>"/>
        	<input type="hidden" name="table" id="table" value="<s:property value='#request.table'/>"/>
        	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
        	    <tr>
        	     <td class="t_r">名称</td>
        	      <td>
        	      <input type="text" id="title" name="title" class="input_xlarge" value="<s:property value='#request.title'/>"/>
        	     </td>
        	      <td class="t_r">状态</td>
        	      <td>
        	      	<input type="hidden" id="h_status" value="<s:property value='#request.status'/>"/>
        	      <select style="width:117px" id="status" name="status" >
        	      	<option value="">---请选择---</option>
        	      	<option value="0">过程中</option>
        	      	<option value="1">预归档</option>
        	      	<option value="2">已归档</option>
        	      	<option value="99">已终止</option>
        	      </select>
        	     </td>
        	     
        	     <td class="t_r">更新时间</td>
        	      <td>
        	      <input type="text" id="starttime" name="starttime" style="width:117px"  value="<s:property value='#request.starttime'/>"/>至
        	      <input type="text" id="endtime" name="endtime" style="width:117px"  value="<s:property value='#request.endtime'/>"/>
        	     </td>
      	      </tr> 
      	    
      	      <tr>
      	    
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
		                  <h5 class="fl"><a href="#" class="colSelect fl">合同列表</a></h5>
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
                                <td class="t_c">流程类型</td>
                                <td class="t_c">流程实例号</td>
                                <td class="t_c">流程名称</td>
                                <td class="t_c">当前状态</td>
                                <td class="t_c">更新时间</td>
                                <td class="t_c">表单</td>
                                <td class="t_c">终止</td>
                                </tr>
                              <s:iterator value="#r.list" id="items" status="s">
                              <tr>
                              
                              	<td class="t_c"><s:property value="#s.index+1+(#r.pageInfo.currentPage-1)*10"/></td>
                              	<td class="t_c"><s:property value="#items[0]"/></td>
                               	<td class="t_c"><s:property value="#items[1]" /></td>
                               	<td class="t_c"><s:property value="#items[2]"  escape="0" /></td>
                                <td id="status_show" class="t_l"><s:property value="#items[3]"/></td>
                                <td class="t_c"><s:property value="#items[4]" /></td>
                               
                                <td>
									<center>
										<a href="javascript:void(0)" onclick="toDetail('<s:property value="#items[0]" />','<s:property value="#items[1]" />');"><img src="../css/default/images/p_open.gif"/></a>
									</center>
								</td>
								<td>
									<input type="hidden" value="<s:property value='#items[1]'/>" id="pinstance_id"/>
                                	<input type="hidden" value="<s:property value='#items[0]'/>" id="processName"/>
                                	<input type="hidden" value="<s:property value='#items[5]'/>" id="mainId"/>
									<center>
										<s:if test='#items[3]=="99"'>
										<a href="javascript:void(0);" class="showReason"><img src="../css/default/images/p_find.gif"/></a>											
										</s:if>
										<s:else>
										<a href="javascript:void(0);" class="stop"><img src="../css/default/images/delete.gif"/></a>
										</s:else>
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