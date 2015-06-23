<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.wonders.stpt.deptDoc.util.*"%>
<%@ page import = "com.wonders.stpt.deptDoc.constants.*"%>
<%@ page import = "com.wonders.stpt.util.StringUtil"%>
<%
	String path = request.getContextPath();
	String operator = StringUtil.getNotNullValueString(request.getSession().getAttribute("loginName"));
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>部门文件柜-呈批件</title>
<link rel="stylesheet" href="<%=path %>/css/formalize.css" />
<link rel="stylesheet" href="<%=path %>/css/page.css" />
<link rel="stylesheet" href="<%=path %>/css/default/imgs.css" />
<link rel="stylesheet" href="<%=path %>/css/reset.css" />
<!--[if IE 6.0]>
           <script src="../js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
        <script src="<%=path %>/js/html5.js"></script>
        <script src="../js/jquery-1.10.2.min.js"></script>
        <script src="../js/jquery-migrate-1.2.1.min.js"></script>
        <script src="../js/jquery.blockUI.js"></script>
        <script src="../js/jquery.form.js"></script>
		<script src="<%=path %>/js/jquery.formalize.js"></script>
		<!--<script src="../js/switchDept.js"></script>-->
		<script src="<%=path %>/js/show.js"></script>
		<link type="text/css" href="../css/flick/jquery-ui-1.10.3.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="../js/jquery-ui-1.10.3.custom.min.js"></script>
		<script type="text/javascript" src="<%=path %>/js/flick/jquery.ui.datepicker-zh-CN.js"></script>
        <script type="text/javascript" src="../js/page.js"></script>
        <script type="text/javascript" src="../js/print.js"></script>
        <style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
		</style>		
	<script type="text/javascript">
		var listCallback = function(data){
		$("#checkboxAll").prop("checked",false);
    	var p = data.pageInfo;
		var temp = "";
		if(data!=null && data.list.length >0){
			var l = data.list;
			
			for(var i=0;i<l.length;i++){
				temp += "<tr>";
				temp += "<td class='t_c'><input class='clk' type='checkbox' value='"+$.notNull(l[i].ID)+"'></td>";
				temp += "<td class='t_c'>"+(i+1) +"</td>";
				temp += "<td class='t_c'>"+$.notNull(l[i].ADDDEPT) +"</td>";
				temp += "<td class='t_c'>"+$.notNull(l[i].ADDDATE) +"</td>";
				temp += "<td class='t_l'>"+$.notNull(l[i].DEPTCODE) +"</td>";
				temp += "<td class='t_c'>"+$.notNull(l[i].TITLE) +"</td>";
				temp += "<td class='t_c'>"+$.notNull(l[i].ADDPERSON) +"</td>";
				temp += "<td class='t_c'>";
				if($.notNull(l[i].URL) == ""){
                    temp += " </td>";
                }else{
                    temp += "<a class='view' href='"+$.notNull(l[i].URL)+"' target='_blank'>查看</a></td>";
                }
                
				temp += "</tr>";
			}
            $("#totalPage_out").val(p.totalPage);
            $("#totalPage").html("当前显示"+(((p.currentPage-1)*p.pageSize)+1)+"-"+(((p.currentPage-1)*p.pageSize)+l.length)+"条记录，"+"总记录："+p.totalRow+"条");
            var totalOption = "";
            $("#pageInfo").html(p.currentPage+"/"+p.totalPage);
            $("#page_out").val(p.currentPage);
            $("#current").val(p.currentPage);
            $("#pageSize_out option:last").val(p.totalRow);
        }else{
            $("#current").val("0");
            $("#pageInfo").html("0/0");
            $("#totalPage_out").val(0);
            $("#totalPage").html("当前显示0条记录，总记录：0条");
            $("#page_out").val("0");
        }
		
		$("#mytable>tbody").eq(0).html(temp);
		//var t = $("#show>tbody").eq(0).find("tr:first").html();
		//console.log($("#show>tbody").eq(0).html());
		//alert(index);
		
		$.unblockUI();
		return false;
	}
	
		
		var index = 0;
		var listOptions = {
			cache:false,
			dataType:'json',
			type:'post',
			callback:null,
			url:'/portal/deptDoc/process/redvList.action?random='+Math.random(),
			error: function(XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
            },
		    success:listCallback
		};
		
	
	$(function(){
		$('#addDate_startDate').datepicker({
			//inline: true								
			"changeYear":true,
			"showButtonPanel":true,	
			"closeText":'清除',	
			"currentText":'addDate_startDate'//仅作为“清除”按钮的判断条件						
		});
 		$('#addDate_endDate').datepicker({
			//inline: true								
			"changeYear":true,
			"showButtonPanel":true,	
			"closeText":'清除',	
			"currentText":'addDate_endDate'//仅作为“清除”按钮的判断条件						
		});	
 		
 		 //datepicker的“清除”功能
        $(document).on("click", ".ui-datepicker-close",function (){              
           if($(this).parent("div").children("button:eq(0)").text()=="addDate_startDate") $("#addDate_startDate").val("");
           if($(this).parent("div").children("button:eq(0)").text()=="addDate_endDate") $("#addDate_endDate").val("");     
          });	 		
	})
    </script>

</head>

<body>
<div id="domMessage" style="display:none;"> 
    <h1>请稍后</h1> 
</div> 	
<%-- 操作页面--%>
		<jsp:include page="personSelect.jsp"></jsp:include>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
			<div class="fl"><img id="show" onclick="showHide();" src="<%=path %>/css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li class="fin">行政公文</li>
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
                <li><a href="<%=path%>/deptDoc/process/recvListr.jsp"><span>行政收文</span></a></li>
               <li  class="selected"><a href="<%=path%>/deptDoc/process/redvListr.jsp"><span>呈批件</span></a></li>
               <li><a href="<%=path%>/deptDoc/process/sendListr.jsp"><span>行政发文</span></a></li> 
               <li><a href="<%=path%>/deptDoc/process/normativeListr.jsp"><span>规范性文件</span></a></li>  
               <li><a href="<%=path%>/deptDoc/process/partysendListr.jsp"><span>党委发文</span></a></li>  
               <li><a href="<%=path%>/deptDoc/process/dcpsendListr.jsp"><span>纪委发文</span></a></li>  
            </ul>
        </div>
        <div class="clearfix"></div>
        <!--Filter-->
      <div class="filter">
        	<div class="query">
        	<div class="p8 filter_search">
        	<form id="list">
        	<input type="hidden" name="pageSize" id="pageSize" value=""/>
        	
        	<input type="hidden" name="page" id="page" value=""/>
        	 <table width="100%" border="0" cellspacing="0" cellpadding="0">
        	    <tr>
        	      <td class="t_r">部门字号</td>
        	     <td>
        	      <input type="text" id="deptCode_like" name="deptCode_like" class="input_xlarge"/>
        	      </td>
        	     <td class="t_r">文件标题</td>
        	     <td>
        	      <input type="text" id="title_like" name="title_like" class="input_xlarge"/>
        	     </td>
        	     <td class="t_r">呈报部门</td>
        	     <td>
        	      <input type="text" id="addDept_like" name="addDept_like" class="input_xlarge"/>
        	     </td>
      	      	</tr> 
      	      	<tr>
      	      	 <td class="t_r">呈报日期</td>
        	     <td>
        	     <input readonly="readonly" type="date" id="addDate_startDate" name="addDate_startDate" value=""/>
        	      	至
        	     <input readonly="readonly" type="date" id="addDate_endDate" name="addDate_endDate" value=""/>
        	      
        	      </td>
        	    <td><input type="hidden" name="order" id="order" value="adddate desc"></td>
        	    <td></td>
        	     <td></td>
        	    <td></td>
      	      	</tr> 
      	     	
        	    <tr>
        	      <td colspan="6" class="t_c">
                  	<input id="submit" type="button" value="检 索" />&nbsp;&nbsp;
                  	<input type="reset" value="重 置"/></td>
				</tr>
      	    </table>
      	    </form>
      	    </div>
        	</div>     
		      <div class="fn clearfix">
		                  <h5 class="fl"><a href="#" class="colSelect fl">行政公文</a></h5>
		                     <% if(DeptDocProcessUtil.existInGroup(DeptDocConstants.CODE_PROCESS_DEPT_DOC_LEADER, operator)){ %>                 
              <input type="hidden" name="authorityFlag" id="authorityFlag"/>
              <input style="margin-left:5px;" type="button" name="cancelButton" id="cancelButton" value="阅读文件权限撤销（请在文件列表中勾选）" class="fr">
              <input style="margin-left:5px;" type="button" name="authorityButton" id="authorityButton" value="阅读文件权限设置（请在文件列表中勾选）" class="fr">
             <%} %>
		      <input type="button" name="printButton" id="printButton" value="打印本页" class="fl">
		      <input type="text" name="printTitle" id="printTitle" value="" class="fl">
		           
		      </div>
		      </div>


      
        <!--Filter End-->
        <!--Table-->
        <div class="mb10" id="listDiv">
        	<table width="100%"  class="table_1" id="mytable">
        				<thead>
                              <tr class="tit">
                                <th class="t_c"><input type="checkbox" id="checkboxAll" name="checkboxAll" /></th>
                                <th style="white-space:nowrap;" class="t_c">序号</th>
                                <th style="white-space:nowrap;" class="t_c">呈报部门</th>
                                 <th style="white-space:nowrap;" class="t_c">呈报日期</th>
                                 <th style="white-space:nowrap;" class="t_c">部门字号</th>
                                 <th style="white-space:nowrap;" class="t_c">文件标题</th>
                                <th style="white-space:nowrap;" class="t_c">经办人</th>
                                 <th style="white-space:nowrap;" colspan="3" class="t_c">操作</th>
                                </tr>
                              
                              </thead>
                              <tbody>
                             
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="12"><div class="clearfix">
                                <input type="hidden" id="totalPage_out">
                                <span class="fl">&nbsp;每页显示条数：  
                                   <select id="pageSize_out">
                                    <option value="10">10</option>
                                    <option value="15">15</option>
                                    <option value="20">20</option>
                                    <option value="30">30</option>
                                    <option value="-1">显示全部</option>
                                   </select>
                                </span>
                                <span class="fl">
                                    &nbsp; Pages：<span style="display:inline;" id="pageInfo">0/0</span>&nbsp;
                                    <input id="current" type="hidden"/>
                                   <input id="page_out" type="text" class="input_tiny"/>
                                   <input id="redirect" type="button" value="Go" class="btn"/>
                                 </span>
                                <span id="totalPage" class="fr">当前显示1-10条记录，总记录：100</span>
                                
                                <span class="fr" style="margin-right:10px;">
                                <ul class="clearfix pager">
                                    <li id="prePage" style="float:left;"><a href="javascript:void(0)">上一页</a> </li>
                                     <li id="nextPage" style="float:left;"><a href="javascript:void(0)">下一页</a></li>    
                                </ul>
                                </span> 
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
