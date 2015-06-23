<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>工作联系单查询</title>
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
	 	$(function(){
	 		$("[id=status_show]").each(function(){
					$(this).html($(this).html().replace("0","进行中").replace("1","预归档"));
			});
		})
			
		function clearInput(){
          $("#status").children("option:eq(0)").attr("selected",true);
          //$("#serial").val("");
          $("#theme").val("");  
          $("#main_unit").val("");
          $("#copy_unit").val("");
        }
        
       function openForm2(task_user_name,model_id,instance_id,processName,pinstance_id,task_id){
			var url =''; 
			url += stptPath+'/openflowform.action';
			url +="?task_id="+encodeURI(task_id);
			url +="&task_user_name="+ encodeURI(task_user_name);
			if (model_id == ''){
				url +="&model_id=" + encodeURI(processName);
			}else{
				url +="&model_id=" + encodeURI(model_id);
			}
	
			if (instance_id == ''){
				url +="&instance_id="+ encodeURI(pinstance_id);
			}else{
				url +="&instance_id="+ encodeURI(instance_id);
			}
			url +="&step_name=Begin";
			url +="&pinstance_id=" + encodeURI(pinstance_id);
			url +="&processName=" + encodeURI(processName);
			url +="&task_type=1" ;
	
			window.open(url);
			
			return false
		}
		
		function openScan2(processName,pinstance_id,task_id){
			var url1 = "";
			url1 = ultimusPath;
			url1 +='/sLogin/workflow/TaskStatus.aspx?TaskID=' + encodeURI(task_id);
			window.open(url1);
			return false; 
		}
		
		function openJobcontactForm(id){
			var url = workflowPath+"/contact-deptContact/viewForward.action?id="+id;
			window.open(url);
		}
		function openJobcontactScan(id){
			var url = workflowPath+"/contact-ultimus/scanList.action?id="+id;
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
                	<li class="fin">工作联系单查询</li>
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
           		<s:if test="#request.processType=='old'">
	           		<li><a href="/portal/processInfo/findJobContactByPage.action"><span>工作联系单查询</span></a></li>
	           		<li class="selected"><a href="/portal/processInfo/findJobContactByPage.action?processType=old"><span>历史数据查询</span></a></li> 
           		</s:if>
           		<s:else>
           			<li class="selected"><a href="/portal/processInfo/findJobContactByPage.action"><span>工作联系单查询</span></a></li>
	           		<li><a href="/portal/processInfo/findJobContactByPage.action?processType=old"><span>历史数据查询</span></a></li>
           		</s:else>
            </ul>
        </div>
        <!--Filter-->
      <div class="filter">
        	<div class="query">
        	<div class="p8 filter_search">
        	<s:form action="findJobContactByPage" id="form" method="post"  namespace="/processInfo" theme="simple">
        	
        	<input type="hidden" name="page" id="page" value="<s:property value="page"/>"/>
        	<input type="hidden" name="processType" id="processType" value="<s:property value="#request.processType"/>"/>
        	
        	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
        	    <tr>
        	     <td class="t_r">主题</td>
        	      <td>
        	      <input type="text" id="theme" name="theme" class="input_xlarge" value="<s:property value='#request.theme'/>"/>
        	     </td>
        	     <!-- 
        	     <td class="t_r">编号</td>
        	      <td>
        	      <input type="text" id="serial" name="serial" class="input_large"  value="<s:property value='#request.serial'/>"/>
        	     </td>
        	      -->
        	     <td class="t_r">完成状态</td>
        	      <td>
        	      	<input type="hidden" id="h_status" value="<s:property value='#request.flag'/>"/>
        	      <select style="width:117px" id="status" name="flag" >
        	      	<option value="">---请选择---</option>
        	      	<option value="0">进行中</option>
        	      	<option value="1">预归档</option>
        	      </select>
        	     </td>
      	      </tr> 
      	      <tr>
      	      <td class="t_r">主送单位</td>
        	      <td>
        	      <input type="text" id="main_unit" name="main_unit" class="input_xlarge" value="<s:property value='#request.main_unit'/>"/>
        	     </td>
        	     <td class="t_r">抄送单位</td>
        	      <td>
        	      <input type="text" id="copy_unit" name="copy_unit" class="input_large"  value="<s:property value='#request.copy_unit'/>"/>
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
		                  <h5 class="fl"><a href="#" class="colSelect fl">工作联系单列表</a></h5>
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
                                <s:if test="#request.processType=='old'">
                                <td class="t_c" width="15%">编号</td>
                                </s:if>
                                <td class="t_c" width="31%">主题</td>
                                <td class="t_c" width="15%">主送单位</td>
                                <td class="t_c" width="15%">抄送单位</td>
                                <td class="t_c" width="10%">联系时间</td>
                                <td class="t_c" width="5%">完成状态</td>
                                <td class="t_c" width="3%">查看</td>
                                <td class="t_c" width="3%">监控</td>
                                </tr>
                              <s:iterator value="#r.list" id="items" status="s">
                              <tr>
                              
                              	<td class="t_c"><s:property value="#s.index+1+(#r.pageInfo.currentPage-1)*10"/></td>
                              	<s:if test="#request.processType=='old'">
                                <td class="t_c"><s:property value="#items[6]"/></td>
                                </s:if>
                              	<td class="t_c"><s:property value="#items[0]"/></td>
                               	<td class="t_l"><s:property value="#items[1]" /></td>
                               	<td class="t_l"><s:property value="#items[2]" /></td>
                                <td class="t_l"><s:property value="#items[3]"/></td>
                                <td class="t_c" id="status_show"><s:property value="#items[4]" /></td>
                                <s:if test="#request.processType=='old'">
                                <td class="t_c">
                                	<center>
                                		<a href="javascript:void(0)" onclick="openForm2('','工作联系单','<s:property value="#items[5]" />','工作联系单','<s:property value="#items[5]" />','<s:property value="#items[7]" />');"><img src="../css/default/images/p_find.gif"/></a>
                                	</center>
                                </td>
                                <td class="t_c">
                                	<center>
                                		<a href="javascript:void(0)" onclick="openScan2('<s:property value="#items[5]" />','工作联系单','<s:property value="#items[7]" />');"><img src="../css/default/images/p_but9.gif"/></a>
                                	</center>
                                </td>
                                </s:if>
                                <s:else>
                                <td class="t_c">
                                	<center>
                                		<a href="javascript:void(0)" onclick="openJobcontactForm('<s:property value="#items[5]" />');"><img src="../css/default/images/p_find.gif"/></a>
                                	</center>
                                </td>
                                <td class="t_c">
                                	<center>
                                		<a href="javascript:void(0)" onclick="openJobcontactScan('<s:property value="#items[5]" />');"><img src="../css/default/images/p_but9.gif"/></a>
                                	</center>
                                </td>
                                </s:else>
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