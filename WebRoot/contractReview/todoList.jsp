<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>合同后审流程待办</title>
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
<script src="js/jquery-1.10.2.min.js"></script>
<script src="js/jquery-migrate-1.2.1.min.js"></script>
<script src="js/jquery.blockUI.js"></script>
<script src="../js/jquery.formalize.js"></script>
<script src="../js/show.js"></script>
<script src="js/common.js"></script>
<link type="text/css" href="../css/flick/jquery-ui-1.8.18.custom.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="../js/flick/jquery-ui-1.8.18.custom.min.js"></script>
<script type="text/javascript"
	src="../js/flick/jquery.ui.datepicker-zh-CN.js"></script>
<style type="text/css">
.ui-datepicker-title span {
	display: inline;
}

button.ui-datepicker-current {
	display: none;
}
</style>
<script type="text/javascript">
	
	 	$(function(){
	 		loadReviewByCode("mtype",$("#contract_money_type_id"));
			loadReviewByCode("purchase",$("#purchase_type_id"));
			loadReviewByCode("group",$("#contract_group_id"));
			loadReviewByCode("type1",$("#contract_type1_id"));
			loadKPI();
			
			$(document).on("click","#chkAll",function(){
				//alert($(this).prop("checked"));
				$("#data-table").find("input[type=checkbox]").prop("checked",$(this).prop("checked"));
			});
			
			$(document).on("click",".chk",function(){
				 if($(".chk:checked").length != $(".chk").length){
					 $("#chkAll").prop("checked",false);
				 }else{
					 $("#chkAll").prop("checked",true);
				 }
			});
			
			$("#form").on("change","select",function(){
				if($(this).attr("id")=="contract_type1_id" && $(this).val() != ""){
					loadReviewByPid($(this).val(),$("#contract_type2_id"));
				}
			});
			
			$("#form").on("click",":checkbox",function(){
				if($(this).index() == 0 && $(this).prop("checked")){
					$(":checkbox:gt(0)").prop("checked",false);
				}else if($(this).index() > 0 && $(this).prop("checked")){
					$(":checkbox:first").prop("checked",false);
				}
			})
			
			$("#data-table").find("tr[name='data_tr']").find("td:not(:first,:last)").each(function(i,n){
				var text = $(n).html();
				if(text.length>limit){
					//console.log($(n));
					$(n).html(text.substring(0,limit)+"...");
					$(n).attr("title",text);
				}
			});
			
			$("#addButton").click(function(){
				var ids = "";
				var operators = "";
				var steps = "";
				$(".chk:checked").each(function(){
					ids += $(this).attr("idValue")+",";
					operators += $(this).attr("operatorValue")+",";
					steps += $(this).attr("stepValue")+",";
				})
				if(ids == ""){
					alert("请选择需要入库的后审流程！");
				}else{
					ids = ids.substr(0,(ids.length)-1);
					operators = operators.substr(0,(operators.length)-1);
					steps = steps.substr(0,(steps.length)-1);
					$.blockUI({ message: $('#domMessage') }); 
					$.post(
							'/portal/contractReview/batchFinish.action?random='+Math.random(),
							{
								"ids": 	ids,
								"operators": 	operators,
								"steps": 	steps
							},
							function(obj, textStatus, jqXHR){
								if(obj=="1"){
									$.unblockUI();
									alert("操作成功!");
									$("#form").submit();
								}else{
									alert("操作失败，请稍后再试！");
									$.unblockUI();
								}
							},
							"text"
						).error(function() { alert("服务器连接失败，请稍后再试!"); })
				}
			});
			
		})
		
		function loadKPI(){
		$.post(
				'<%=path%>/contractReview/getKPI.action?random='+Math.random(),
				{},
				function(obj, textStatus, jqXHR){
					var temp = "";
					var kpiValue = '${requestScope.kpi_control}';
					if(obj !=null && obj.length>0){
						for(var i=0;i<obj.length;i++){
							temp += '<input value="'+obj[i].ID+
							'" type="checkbox" id="kpi_control" name="kpi_control"';
							if(kpiValue != null  && kpiValue.length > 0 && kpiValue.indexOf(obj[i].ID) >=0){
								temp += " checked ";
							}
							temp += '/>'+obj[i].NAME+'&nbsp;';
						}
						
					}
					
					$("#kpiTd").html(temp);
					
				},
				"json"
			).error(function() { alert("服务器连接失败，请稍后再试!"); });
		}
	 	
		function loadReviewByPid(pid,element){
		$.post(
				'<%=path%>/contractReview/getTypeByPid.action?random='+Math.random(),
				{
					"pid": 	pid
				},
				function(obj, textStatus, jqXHR){
					var temp = "<option value=''>请选择</option>";
					if(obj !=null && obj.length>0){
						for(var i=0;i<obj.length;i++){
							temp +="<option value='"+obj[i].ID+"'>"+obj[i].NAME+"</option>";
						}
						
					}
					
					element.html(temp);
					if(codeFlag==5){
						$("#contract_type2_id").val('${requestScope.contract_type2_id}');
					}
					codeFlag ++ ;
				},
				"json"
			).error(function() { alert("服务器连接失败，请稍后再试!"); });
		}
	
		var codeFlag = 1;
		function loadReviewByCode(code,element){
			$.post(
					'<%=path%>/contractReview/getTypeByCode.action?random='+Math.random(),
					{
						"code": 	code
					},
					function(obj, textStatus, jqXHR){
						var temp = "<option value=''>请选择</option>";
						if(obj !=null && obj.length>0){
							for(var i=0;i<obj.length;i++){
								temp +="<option value='"+obj[i].ID+"'>"+obj[i].NAME+"</option>";
							}
							
						}
						element.html(temp);
						codeFlag++;
						//alert(codeFlag)
						if(codeFlag == 5){
							$("#contract_money_type_id").val('${requestScope.contract_money_type_id}');
							$("#purchase_type_id").val('${requestScope.purchase_type_id}');
							$("#contract_group_id").val('${requestScope.contract_group_id}');
							$("#contract_type1_id").val('${requestScope.contract_type1_id}');
							loadReviewByPid($("#contract_type1_id").val(),$("#contract_type2_id"));
						}
					},
					"json"
				).error(function() { alert("服务器连接失败，请稍后再试!"); });
		}
	
		function clearInput(){
			var form = $("form:first");
	        $(form).find("input:text").val("");
	        $(form).find("select>option:first").prop("selected",true);
	        $(form).find(":checkbox").prop("checked",false);
	    }
        
       function toDetail(process_name,instant_id,taskUserName,steplabel,taskid){
    	  var url = "http://10.1.48.16:8080/workflow/contract-reviewMain/forward.action?"
			+"pname="+encodeURI(process_name)+"&pincident="+instant_id
			+"&cname="+encodeURI(process_name)+"&cincident="+instant_id
			+"&steplabel="+encodeURI(steplabel)+"&"
			+"taskid="+taskid+"&"
			+"taskuser="+taskUserName+"&"
			+"rand="+Math.random();
			window.open(url);
			return false;
		}
		
		function toSee(taskid){
			var url = "http://10.1.48.17/sLogin/workflow/TaskStatus.aspx?TaskID="+taskid;
			//window.location.href=url;
			window.open(url);
			return false;
		}
		
    </script>



</head>

<body>
<div id="domMessage" style="display:none;"> 
    <h1>请稍后</h1> 
</div>
	<div class="main">
		<!--Ctrl-->
		<div class="ctrl clearfix">
			<div class="fl">
				<img id="show" onclick="showHide();"
					src="../css/default/images/sideBar_arrow_right.jpg" width="46"
					height="30" alt="收起">
			</div>
			<div class="posi fl">
				<ul>
					<li><a
						href="javascript:window.location.href='/portal/center/wdsw/wd_index2.jsp'">我的事务</a></li>
					<li class="fin">合同后审待办</li>
				</ul>
			</div>
			<div style="display: none;" class="fr lit_nav nwarp">
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
						<form action="todoList.action" id="form" method="post"
							namespace="/contractReview">

							<input type="hidden" name="page" id="page"
								value="${page}"/>

							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td class="t_r">合同编号 </td>
									<td><input type="text" id="contract_identifier"
										value="${requestScope.contract_identifier}" name="contract_identifier" class="input_xlarge" /></td>
									<td class="t_r">合同名称</td>
									<td><input type="text" id="contract_name"
										value="${requestScope.contract_name}" name="contract_name" class="input_xlarge" /></td>
									<td class="t_r">合同金额分类</td>
									<td><select id="contract_money_type_id"
										name="contract_money_type_id">

									</select></td>

								</tr>

								<tr>
									<td class="t_r">合同分类</td>
									<td><select id="contract_type1_id"
										name="contract_type1_id">

									</select> &nbsp; <select id="contract_type2_id" name="contract_type2_id">

									</select></td>
									<td class="t_r">负责单位/部门</td>
									<td><input type="text" id="project_charge_dept"
										value="${requestScope.project_charge_dept}" name="project_charge_dept" class="input_xlarge" /></td>
									<td class="t_r">合同金额（万元）</td>
									<td><input type="number" id="contract_moneyLt"
										value="${requestScope.contract_moneyLt}" name="contract_moneyLt" class="input_medium" /> - <input
										type="number" id="contract_moneyGt" 
										value="${requestScope.contract_moneyGt}"
										name="contract_moneyGt"
										class="input_medium" /></td>

								</tr>


								<tr>
									<td class="t_r">合同分组</td>
									<td><select id="contract_group_id"
										name="contract_group_id">

									</select></td>
									<td class="t_r">采购方式</td>
									<td><select id="purchase_type_id" name="purchase_type_id">

									</select></td>
									<td></td>
								</tr>
								<tr>
									<td class="t_r">KPI提示</td>
									<td colspan="5" id="kpiTd"></td>
								</tr>

								<tr>
									<td colspan="6" class="t_c"><input type="submit"
										value="检 索" />&nbsp;&nbsp; <input type="button" value="重 置"
										onclick="clearInput();" /></td>
								</tr>
							</table>
						</form>
					</div>
				</div>
				<div class="fn clearfix">
					<h5 class="fl">
						<a href="#" class="colSelect fl">合同列表</a>
					</h5>
					 &nbsp;<input type="button" name="addButton" id="addButton" value="入 库" class="fl">
				</div>
			</div>



			<!--Filter End-->
			<!--Table-->
			<c:set value="${requestScope.result}" var="r" scope="page"/>  
			<div class="mb10">
				<table width="100%" class="table_1" id="data-table">
					<tbody>
						<tr class="tit">
							<td class="t_c" width="1%"><input type="checkbox" id="chkAll" name="chkAll" /></td>
							<td class="t_c" width="3%">序号</td>
							<td class="t_c" width="8%">合同编号</td>
							<td class="t_c" width="27%">合同名称</td>
							<td class="t_c" width="5%">合同金额分类</td>
							<td class="t_c" width="7%">合同分类</td>
							<td class="t_c" width="10%">合同分组</td>
							<td class="t_c" width="10%">负责单位/部门</td>
							<td class="t_c" width="10%">采购方式</td>
							<td class="t_c" width="10%">合同金额（万元）</td>
							<td class="t_c" width="3%">表单</td>
							<td class="t_c" width="3%">监控</td>
						</tr>
						<c:forEach items="${r.list}" var="items" varStatus="s">
						<tr>
								<td class="t_c">
									<input class="chk" type="checkbox" 
									idValue="${items[20]}"
									operatorValue="${items[17]}"
									stepValue="${items[18]}" />
								</td>
								<td class="t_c">${s.index+1+(r.pageInfo.currentPage-1)*10}</td>
								<td class="t_l">${items[1]}</td>
								<td class="t_l">${items[0]}</td>
								<td class="t_c">${items[3]}</td>
								<td class="t_l">${items[6]}-${items[7]}</td>
								<td class="t_c">${items[12]}</td>
								<td class="t_c">${items[8]}</td>
								<td class="t_c">${items[13]}</td>
								<td class="t_c">${items[9]}</td>
								<td>
									<center>
										<a href="javascript:void(0)"
											onclick="toDetail('${items[15]}',
											'${items[16]}',
											'${items[17]}',
											'${items[18]}',
											'${items[19]}');"><img
											src="../css/default/images/p_open.gif" /></a>
									</center>
								</td>
								<td>
									<center>
										<a href="javascript:void(0)"
											onclick="toSee('${items[19]}');"><img
											src="../css/default/images/p_but9.gif" /></a>
									</center>
								</td>

							</tr>
						</c:forEach>
					</tbody>
					<tr class="tfoot">
						<td colspan="15"><div class="clearfix">
								<span class="fl">${r.pageInfo.totalRow}条记录</span>
								<ul class="fr clearfix pager">
									<li>Pages:${r.pageInfo.currentPage}/${r.pageInfo.totalPage}<input type="hidden"
										value="${r.pageInfo.totalPage}"
										id="totalPageCount"> <input type="hidden"
										value="${r.pageInfo.currentPage}"
										id="currentNumber"> <input type="text" id="number"
										name="pageNumber" min="0" max="999" step="1"
										class="input_tiny"
										value="${r.pageInfo.currentPage}" /> <input
										type="button" name="button" id="button" value="Go"
										onclick="goPage(0,0)">
									</li>

									<c:choose>
									<c:when test="${r.pageInfo.currentPage==r.pageInfo.totalPage}">
										<li><a href="javascript:void(0)">&gt;&gt;</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="javascript:void(0)"
											onclick="goPage(${r.pageInfo.totalPage},3)">&gt;&gt;</a></li>
									</c:otherwise>
									</c:choose>
									<li>
									<c:choose>
									<c:when
											test="${r.pageInfo.currentPage==r.pageInfo.totalPage}">
											<a href="javascript:void(0)">下一页</a>
										</c:when> 
										<c:otherwise>
											<a href="javascript:void(0)"
												onclick="goPage(${r.pageInfo.currentPage},2)">下一页</a>
										</c:otherwise>
										</c:choose>
										</li>
									
									<li>
									<c:choose>
									<c:when test="${r.pageInfo.currentPage==1}">
											<a href="javascript:void(0)">上一页</a>
										</c:when> <c:otherwise>
											<a href="javascript:void(0)"
												onclick="goPage(${r.pageInfo.currentPage},1)">上一页</a>
										</c:otherwise>
										</c:choose>
									</li>
									<li>
									<c:choose>
									<c:when test="${r.pageInfo.currentPage==1}">
										<li><a href="javascript:void(0)">&lt;&lt;</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="javascript:void(0)" onclick="goPage(1,3)">&lt;&lt;</a></li>
								</c:otherwise>
									</c:choose>


								</ul>
							</div></td>
					</tr>
				</table>

			</div>
			<!--Table End-->
		</div>
	</div>
</body>
</html>