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
		<script src="<%=basePath %>/beforeBuild/js/highcharts.js"></script>

	<script type="text/javascript">
	$(function(){
		var listType = $("[name=listType]").val();
		if(listType=='project'){
			listAllPapers();
			$("#projectLi").addClass("selected");
			$("#projectTr").show();
		}else if(listType=='monomer'){
			listAllPapers();
			$("#monomerLi").addClass("selected");
			$("#monomerTr").show();
		}else if(listType=='dept'){
			listAllDepts();
			$("#deptLi").addClass("selected");
		}else{
			listAllProjects();
			$("#paperLi").addClass("selected");
			$("#paperTr").show();
		}
		findProject();
		findAllTypes();
		findAllPapers();
	});

	function listAllPapers(){
		$.ajax({
			url: "/portal/beforeCount/findAllPapers.action",
			type: 'post', 
			dataType: 'json', 
			error:function(){
				alert("系统连接失败，请稍后再试！");
			},
			success: function(obj){			
              if(obj!=null&&obj.length>0){
				  var addHtml = "<th class='t_c'>证件名称</th>";
                  for(var i=0;i<obj.length;i++){
                	  addHtml += "<th class='t_c'>"+obj[i]+"</th>";
                  }
                  $("thead").children("tr").append(addHtml);
                  listCountData(obj);
              }
			}
		});	
	}

	function listAllDepts(){
		$.ajax({
			url: "/portal/beforeCount/findAllDepts.action",
			type: 'post', 
			dataType: 'json', 
			error:function(){
				alert("系统连接失败，请稍后再试！");
			},
			success: function(obj){			
              if(obj!=null&&obj.length>0){
				  var addHtml = "<th class='t_c'>审批部门</th>";
                  for(var i=0;i<obj.length;i++){
                	  addHtml += "<th class='t_c'>"+obj[i]+"</th>";
                  }
                  $("thead").children("tr").append(addHtml);
                  listCountData(obj);
              }
			}
		});	
	}

	function listAllProjects(){
		$.ajax({
			url: "/portal/beforeCount/findAllProjects.action",
			type: 'post', 
			dataType: 'json', 
			error:function(){
				alert("系统连接失败，请稍后再试！");
			},
			success: function(obj){			
              if(obj!=null&&obj.length>0){
				  var addHtml = "<th class='t_c'>项目名称</th>";
                  for(var i=0;i<obj.length;i++){
                	  addHtml += "<th class='t_c'>"+obj[i]+"</th>";
                  }
                  $("thead").children("tr").append(addHtml);
                  listCountData(obj);
              }
			}
		});	
	}

	function listCountData(obj2){
		$.ajax({
			url: "/portal/beforeCount/countDatas.action",
			type: 'post', 
			data:{
				listType:$("[name=listType]").val(),
				project_id:$("#h_project_id").val(),
				monomer_id:$("#h_monomerId").val(),
				paper_id:$("#h_paper_id").val(),
				oldDeptId:'<%=oldDeptId %>',
				random:Math.random()
			},
			dataType: 'json', 
			error:function(){
				alert("系统连接失败，请稍后再试！");
			},
			success: function(obj){			
              if(obj!=null&&obj.length>0){
				  var addHtml = "";
				  for(var i=0;i<obj.length;i++){
					  addHtml += "<tr>";
					  if(i==0){
						  addHtml += "<td class='t_c'>待办</td>";
					  }else if(i==1){
						  addHtml += "<td class='t_c'>在办</td>";
					  }else if(i==2){
						  addHtml += "<td class='t_c'>延误</td>";
					  }else if(i==3){
						  addHtml += "<td class='t_c'>办结</td>";
					  }else if(i==4){
						  addHtml += "<td class='t_c'>失效</td>";
					  }else if(i==5){
						  addHtml += "<td class='t_c'>过期</td>";
					  }
					  for(var j=0;j<obj[i].length;j++){
							addHtml += "<td class='t_c'>"+obj[i][j]+"</td>";
					  }
					  addHtml += "</tr>";
				  }
				  $("#dataTbody").append(addHtml);
				  showColumn(obj,obj2);
              }
			}
		});	
	}

	function showColumn(obj,obj2){
        $('#chart').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: ''
            },
            subtitle: {
                text: ''
            },
            xAxis: {
                categories: obj2
            },
            yAxis: {
                min: 0,
                title: {
                    text: ''
                },
                allowDecimals:false
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>{point.y} 个</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            credits:{
				enabled:false
            },
            series: [{
                name: '待办',
                data: obj[0]
    
            }, {
                name: '在办',
                data: obj[1]
    
            }, {
                name: '延误',
                data: obj[2]
    
            }, {
                name: '办结',
                data: obj[3]
    
            }, {
                name: '失效',
                data: obj[4]
    
            }, {
                name: '过期',
                data: obj[5]
    
            }]
        });
	}

	function findProject(){
		$.ajax({
			url: "/portal/history/findProjectAndLine.action",
			type: 'post', 
			data:{
				oldDeptId:'<%=oldDeptId%>',
				random:Math.random()
			},
			dataType: 'json', 
			error:function(){
				alert("系统连接失败，请稍后再试！");
			},
			success: function(obj){			
              if(obj!=null&&obj.length>0){
				  var addHtml = "<option value=''>---请选择---</option>";
				  for(var i=0;i<obj.length;i++){
					  addHtml += "<option value='"+obj[i][0]+"' routeId='"+obj[i][2]+"'>"+obj[i][1]+"</option>";
				  }
				  $("[name=project_id]").html(addHtml);
				  $("[name=project_id]").val($("#h_project_id").val());
				  $("[name=project_id_2]").html(addHtml);
				  $("[name=project_id_2]").val($("#h_project_id_2").val());
              }
			}
		});	
	}

	function selectProject(obj){
		$("#form").submit();
	}

	function selectProject2(obj){
		var option = $(obj).children("option:selected");
		$("[name=routeId]").val(option.attr("routeId"));
		$("[name=monomer_type]").val("");
		$("[name=monomerId]").val("");
	}

	function findAllTypes(){
		$.ajax({
			url: "/portal/history/findAllTypes.action",
			type: 'post', 
			dataType: 'json', 
			error:function(){
				alert("系统连接失败，请稍后再试！");
			},
			success: function(obj){			
              if(obj!=null&&obj.length>0){
				  var addHtml = "<option value=''>---请选择---</option>";
				  for(var i=0;i<obj.length;i++){
					  addHtml += "<option value='"+obj[i][1]+"'>"+obj[i][1]+"</option>";
				  }
				  $("[name=monomer_type]").html(addHtml);
				  $("[name=monomer_type]").val($("#h_monomer_type").val());
				  findMonomerByRouteAndType();
              }
			}
		});	
	}

	function findMonomerByRouteAndType(){
		$.ajax({
			url: "/portal/history/findMonomerByRouteAndType.action",
			type: 'post', 
			data:{
				routeId:$("[name=routeId]").val(),
				typeName:$("[name=monomer_type]").val(),
				random:Math.random()
			},
			dataType: 'json', 
			error:function(){
				alert("系统连接失败，请稍后再试！");
			},
			success: function(obj){			
			   var addHtml = "<option value=''>---请选择---</option>";
	           if(obj!=null&&obj.length>0){
				  for(var i=0;i<obj.length;i++){
					  addHtml += "<option value='"+obj[i][0]+"'>"+obj[i][1]+"</option>";
				  }
               }
	           $("[name=monomerId]").html(addHtml);
			   $("[name=monomerId]").val($("#h_monomerId").val());
			}
		});	
	}

	function selectMonomer(obj){
		$("#form").submit();
	}

	function findAllPapers(){
		$.ajax({
			url: "/portal/linePlan/findAllPapers.action",
			type: 'post', 
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
				  $("[name=paper_id]").html(addHtml);
				   $("[name=paper_id]").val($("#h_paper_id").val());
              }
			}
		});	
	}

	function selectPaper(obj){
		$("#form").submit();
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
        		<li id="paperLi"><a href="/portal/beforeCount/toCountList.action?listType=paper&oldDeptId=<%=oldDeptId %>&role=<%=role %>"><span>按证件名称统计</span></a></li>
				<li id="projectLi"><a href="/portal/beforeCount/toCountList.action?listType=project&oldDeptId=<%=oldDeptId %>&role=<%=role %>"><span>按项目统计</span></a></li>
				<li id="monomerLi"><a href="/portal/beforeCount/toCountList.action?listType=monomer&oldDeptId=<%=oldDeptId %>&role=<%=role %>"><span>按单体统计</span></a></li>
				<li id="deptLi"><a href="/portal/beforeCount/toCountList.action?listType=dept&oldDeptId=<%=oldDeptId %>&role=<%=role %>"><span>按审批部门统计</span></a></li>
				<li><a href="/portal/report/findReportByPage.action?oldDeptId=<%=oldDeptId %>&role=<%=role %>"><span>业务提醒查询</span></a></li>
				<li><a href="/portal/knowledge/findKnowledgeByPage.action?oldDeptId=<%=oldDeptId %>&role=<%=role %>&showType=view"><span>知识库</span></a></li>
				<li><a href="/portal/beforeCount/showPlanReport.action?oldDeptId=<%=oldDeptId %>&role=<%=role %>"><span>前期办证计划简报表</span></a></li>
            </ul>
        </div>
        <!--Filter-->
      <div class="filter">
        	<div class="query">
        	<div class="p8 filter_search">
        	<s:form action="toCountList" id="form" method="post"  namespace="/beforeCount">
        	<input type="hidden" name="oldDeptId" value="<%=oldDeptId %>"/>
        	<input type="hidden" name="listType" value="<s:property value='#request.listType'/>"/>
        	 <table width="100%" border="0" cellspacing="0" cellpadding="0">
      	      	<tr id="paperTr" style="display:none;">
      	      	<td class="t_r">证件名称</td>
        	     <td>
        	   	<select name="paper_id" onchange="selectPaper(this);"></select>
                 <input type="hidden" id="h_paper_id" value="<s:property value='#request.paper_id'/>">
        	      </td>
      	      	</tr>
      	      	<tr id="projectTr" style="display:none;">
      	      	<td class="t_r">项目名称</td>
        	     <td>
        	   	<select name="project_id" onchange="selectProject(this);"></select>
                 <input type="hidden" id="h_project_id" value="<s:property value='#request.project_id'/>">
        	      </td>
      	      	</tr>
      	      	<tr id="monomerTr" style="display:none;">
      	      	<td class="t_r">项目名称</td>
        	     <td>
        	   	<select name="project_id_2" onchange="selectProject2(this);"></select>
                 <input type="hidden" id="h_project_id_2" value="<s:property value='#request.project_id_2'/>">
                  <input type="hidden" name="routeId" value="<s:property value='#request.routeId'/>">
        	      </td>
        	      <td class="t_r">单体类型</td>
        	     <td>
        	   	<select name="monomer_type" onchange="findMonomerByRouteAndType();"></select>
                 <input type="hidden" id="h_monomer_type" value="<s:property value='#request.monomer_type'/>">
        	      </td>
        	      <td class="t_r">单体名称</td>
        	     <td>
        	   	<select name="monomerId" onchange="selectMonomer(this);"></select>
                 <input type="hidden" id="h_monomerId" value="<s:property value='#request.monomerId'/>">
        	      </td>
      	      	</tr>
        	    <tr style="display:none;">
        	      <td colspan="6" class="t_c">
                  	<input type="submit" value="检 索" />&nbsp;&nbsp;
				</tr>
				<tr>
        	      <td colspan="6" class="t_r">
                  	<input type="button" value="打 印" onclick="window.print();"/>&nbsp;&nbsp;
				</tr>
      	    </table>
      	    </s:form>
      	    </div>
        	</div>     
		            
		       <div id="chart"></div>    
		      </div>


      
        <!--Filter End-->
        <!--Table-->
        <div class="mb10" id="listDiv">
        	<table width="100%"  class="table_1" id="mytable">
        				<thead style="background:#c9d4f3">
                              <tr class="tit">
                                </tr>
                              
                              </thead>
                              <tbody id="dataTbody">
	                             
                              </tbody>
                            </table>

      </div>
        <!--Table End-->
</div>
</div>
</body>
</html>