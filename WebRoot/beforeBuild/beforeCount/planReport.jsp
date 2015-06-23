<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String basePath = request.getContextPath();
String oldDeptId = request.getParameter("oldDeptId");
if(oldDeptId==null){
	oldDeptId = "";
}
String role = request.getParameter("role");
if(role==null){
	role = "";
}
if("2".equals(role)||"3".equals(role)){
	oldDeptId = "";
}
%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>统计</title>
<link rel="stylesheet" href="<%=basePath %>/css/formalize.css" />
<link rel="stylesheet" href="<%=basePath %>/css/page.css" />
<link rel="stylesheet" href="<%=basePath %>/css/default/imgs.css" />
<link rel="stylesheet" href="<%=basePath %>/css/reset.css" />
<!--[if IE 6.0]>
           <script src="../js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
        <script src="<%=basePath %>/js/html5.js"></script>
        <script src="<%=basePath %>/js/jquery-1.7.1.js"></script>
		<script src="<%=basePath %>/js/jquery.formalize.js"></script>
		<script src="<%=basePath %>/beforeBuild/js/comm.js"></script>
		<script src="<%=basePath %>/beforeBuild/js/merge.js"></script>
	<style>
	.fontRed {color:red;}
	</style>
	<script type="text/javascript">
		function addSum(){
			var sums = new Array();
			var sums_all = new Array();
			var tdLength = $("#tbody").children("tr:eq(0)").children("td").length;
			for(var i=0;i<tdLength;i++){
				sums[i] = 0;
				sums_all[i] = 0;
			}
			$("#tbody").children("tr").each(function(i){
				$(this).children("td").each(function(j){
					if($(this).html().indexOf("red")>-1){
						$(this).addClass("fontRed");
						$(this).html($(this).html().replace("red",""));
					}
					if($(this).html()=="完成"){
						sums[j] += 1;
						sums_all[j] += 1;
					}
					if($(this).html()==""){
						$(this).html("-");
					}
				});
				if($(this).children("td:eq(0)").html()!=$(this).next().children("td:eq(0)").html()){
					var addHtml = "<tr id='sums'>";
					for(var k=0;k<tdLength;k++){
						addHtml += '<td style="white-space:nowrap;border:1px solid #ccc;" class="t_c">'+sums[k]+'</td>';
						sums[k] = 0;
					}
					addHtml += "</tr>";
					if($(this).next().children("td:eq(0)").html()==null){
						addHtml += "<tr id='sums_all'>";
						for(var k=0;k<tdLength;k++){
							addHtml += '<td style="white-space:nowrap;border:1px solid #ccc;" class="t_c">'+sums_all[k]+'</td>';
						}
						addHtml += "</tr>";
					}
					$(this).after(addHtml);
				}
			});
			$("[id=sums]").each(function(){
				$(this).children("td:eq(0)").attr("colspan","2");
				$(this).children("td:eq(0)").html("小计(完成)：");
				$(this).children("td:eq(0)").attr("class","t_r");
				$(this).children("td:eq(1)").remove();
				$(this).attr("style","background-color:#FFF8DC");
			});
			$("#sums_all").children("td:eq(0)").attr("colspan","2");
			$("#sums_all").children("td:eq(0)").html("总计(完成)：");
			$("#sums_all").children("td:eq(0)").attr("class","t_r");
			$("#sums_all").children("td:eq(1)").remove();
			$("#sums_all").attr("style","background-color:#F5DEB3");
		}

		$(function(){
			findProjectAndLine();
			$("[name=year]").val($("#hidden_year").val());
			addSum();
			$("#tbody").parent("table").rowspan({td:1});
		});

		function findProjectAndLine(){
			$.ajax({
				url: "/portal/history/findProjectAndLine.action",
				type: 'post', 
				data:{oldDeptId:"<%=oldDeptId%>"},
				dataType: 'json', 
				error:function(){
					alert("系统连接失败，请稍后再试！");
				},
				success: function(obj){			
	              if(obj!=null&&obj.length>0){
					  var addHtml = "<option value=''>---请选择---</option>";
					  for(var i=0;i<obj.length;i++){
						  addHtml += "<option value='"+obj[i][0]+"'>"+obj[i][1]+"</option>";
					  }
					  $("[name=projectId]").html(addHtml);
					  $("[name=projectId]").val($("#hidden_projectId").val());
	              }
				}
			});	
		}
    </script>
</head>

<body>	
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
			<div class="fl"><img id="show" onclick="showHide();" src="<%=basePath %>/css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li class="fin">统计</li>
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
        		<li><a href="/portal/beforeCount/toInfoList.action?oldDeptId=<%=oldDeptId %>&role=<%=role %>"><span>前期办证信息查询</span></a></li>
        		<li id="paperLi"><a href="/portal/beforeCount/toCountList.action?listType=paper&oldDeptId=<%=oldDeptId %>&role=<%=role %>"><span>按证件类型统计</span></a></li>
				<li id="projectLi"><a href="/portal/beforeCount/toCountList.action?listType=project&oldDeptId=<%=oldDeptId %>&role=<%=role %>"><span>按项目统计</span></a></li>
				<li id="monomerLi"><a href="/portal/beforeCount/toCountList.action?listType=monomer&oldDeptId=<%=oldDeptId %>&role=<%=role %>"><span>按单体统计</span></a></li>
				<li id="deptLi"><a href="/portal/beforeCount/toCountList.action?listType=dept&oldDeptId=<%=oldDeptId %>&role=<%=role %>"><span>按审批部门统计</span></a></li>
				<li><a href="/portal/report/findReportByPage.action?oldDeptId=<%=oldDeptId %>&role=<%=role %>"><span>业务提醒查询</span></a></li>
				<li><a href="/portal/knowledge/findKnowledgeByPage.action?oldDeptId=<%=oldDeptId %>&role=<%=role %>&showType=view"><span>知识库</span></a></li>
				<li class="selected"><a href="/portal/beforeCount/showPlanReport.action?oldDeptId=<%=oldDeptId %>&role=<%=role %>"><span>前期办证计划简报表</span></a></li>
            </ul>
        </div>
        <!--Filter-->
      <div class="filter">
        	<div class="query">
        	<div class="p8 filter_search">
        	<s:form action="showPlanReport" id="form" method="post"  namespace="/beforeCount">
        	<input type="hidden" name="oldDeptId" value="<%=oldDeptId%>">
        	 <table width="100%" border="0" cellspacing="0" cellpadding="0">
        	    <tr>
        	    	<td class="t_r">项目</td>
	        	     <td style="width:300px;">
	        	      <select name="projectId"></select>
	        	      <input type="hidden" id="hidden_projectId" value="<s:property value='#request.projectId'/>">
	        	     </td>
	        	     <td class="t_r">年度</td>
	        	     <td>
	        	       <select name="year">
	        	       	<option value="2014">2014</option>
	        	       	<option value="2015">2015</option>
	        	       	<option value="2016">2016</option>
	        	       	<option value="2017">2017</option>
	        	       	<option value="2018">2018</option>
	        	       </select>
	        	       <input type="hidden" id="hidden_year" value="<s:property value='#request.year'/>">
	        	      </td>
        	    </tr>
        	   
        	    <tr>
        	      <td colspan="6" class="t_c">
                  	<input type="submit" value="统 计" />&nbsp;&nbsp;
                  	<input type="button" value="打 印" onclick="window.print();"/></td>
				</tr>
      	    </table>
      	    </s:form>
      	    </div>
        	</div>     
		      </div>


      
        <!--Filter End-->
        <!--Table-->
        <div class="mb10" id="listDiv">
        	<table width="100%;"  class="table_1" id="mytable">
        				<thead>
                              <tr class="tit">
                                <th class="t_c" style="white-space:nowrap;">项目</th>
                                 <th class="t_c" style="white-space:nowrap;">单体</th>
                                 <s:iterator value="#request.paperList" id="data1" status="b">
                                 	<th class="t_c" style="white-space:nowrap;"><s:property value="#request.paperList.get(#b.index)[1]" /></th>
                                 </s:iterator>
                                </tr>
                              
                              </thead>
                              <tbody id="tbody">
	                             <s:iterator value="#request.showList" id="data" status="b">
	                             <tr>
	                             	<td style="white-space:nowrap;border:1px solid #ccc;" class="t_c"><s:property value="#request.monomerList.get(#b.index)[0]" /></td>
	                             	<td style="white-space:nowrap;border:1px solid #ccc;" class="t_c"><s:property value="#request.monomerList.get(#b.index)[2]" /></td>
									  <s:iterator value="#data" status="a">
									 	<td style="white-space:nowrap;border:1px solid #ccc;" class="t_c"><s:property value="#data[#a.index]" /></td>
									  </s:iterator>
								  </tr>
								</s:iterator>
                              </tbody>
                              <tr class="tfoot">
                              	<td colspan="50">&nbsp;</td>
                              </tr>
                            </table>

      </div>
        <!--Table End-->
</div>
</div>
</body>
</html>