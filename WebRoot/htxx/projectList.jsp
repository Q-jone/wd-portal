<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>项目信息统计</title>
<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/default/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
<link type="text/css" href="../css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
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
		<script src="../js/flick/jquery-ui-1.8.18.custom.min.js"></script>
		<script src="../js/flick/jquery.ui.datepicker-zh-CN.js"></script>

		
		
		<style type="text/css">
	        .ui-datepicker-title span {display:inline;}
	        button.ui-datepicker-current { display: none; }
	        a img{margin:0 auto;}
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
        
	      	/*
	    	$(window).resize(function(){
	    		$(".mb10").width($(window).width());
	    		$(".mb10").css( "overflowX", "auto" );
	    	})*/

      })
        
	$(function(){

		$("input[name='stat_date']").datepicker({
			inline: true,
			changeYear:true,
			changeMonth:true,
			showOtherMonths: true,
			selectOtherMonths: true
		});
		
		$("input[name='contractSignedEndDate']").datepicker({
			inline: true,
			changeYear:true,
			changeMonth:true,
			showOtherMonths: true,
			selectOtherMonths: true,
			onSelect: function( selectedDate ) {
				$("input[name='contractSignedDate']").datepicker( "option", "maxDate", selectedDate );
			}
		});
		$("input[name='contractSignedDate']").datepicker({
			inline: true,
			changeYear:true,
			changeMonth:true,
			showOtherMonths: true,
			selectOtherMonths: true,
			onSelect: function( selectedDate ) {
				$("input[name='contractSignedEndDate']").datepicker( "option", "minDate", selectedDate );
			}
		});

		
		$("input[name='projectApproveEnd']").datepicker({
			inline: true,
			changeYear:true,
			changeMonth:true,
			showOtherMonths: true,
			selectOtherMonths: true,
			onSelect: function( selectedDate ) {
				$("input[name='projectApproveStart']").datepicker( "option", "maxDate", selectedDate );
			}
		});
		$("input[name='projectApproveStart']").datepicker({
			inline: true,
			changeYear:true,
			changeMonth:true,
			showOtherMonths: true,
			selectOtherMonths: true,
			onSelect: function( selectedDate ) {
				$("input[name='projectApproveEnd']").datepicker( "option", "minDate", selectedDate );
			}
		});


		//显示搜索或隐藏
		if($("#showOrhide").val()=="hide"){
			$("#queryArea").hide();
		}else{
			$("#queryArea").show();
		}
		
		$("#clearInput").click(function(){
			$("#form").find("tr[id^='search']").find("input").val("");
			$("#form").find("tr[id^='search']").find("select option:first").attr("selected",true);
		});

		if($("input[name=contractType]").val()!=""){
			//搜索条件
			var type1 = $("input[name=contractType]").val().substring(0,1);
			var type2 = $("input[name=contractType]").val().substring(2,3);
			$("select[name=contractType2]").children("option").each(function(){
				if($(this).val()==type2){
					$(this).attr("selected",true);
				}
			});
			//快速搜索
			$("#"+type1+type2+"").addClass("selected");
			$("#00").removeClass("selected");
		}else{
			//快速搜索，没有选择,显示全部
			$("#00").addClass("selected");
		}

		//所有价格取整数
		$("#dataList").find("td[name$='Price']").each(function(){
			if($(this).text()!=null && $(this).text()!=""){
				$(this).text(parseInt($.trim($(this).text())));				
			}
		});
		//计算变更后合同价
		$("#dataList").find("td[name='contractPrice']").each(function(){
			var contactPrice = parseFloat($.trim($(this).text()));
			var changeMoney = parseFloat($.trim($(this).next().next().text()));
			$(this).next().next().next().text(parseInt(contactPrice+changeMoney));			
		});
	});       

    function checkForm(){
    	var type1 = $("select[name=contractType1]").val();
    	var type2 = $("select[name=contractType2]").val();
    	$("#pageNo").val("");
    	//拼接合同分类
    	if(type1!=null && type1!=""){
    		var type =  type1+","+ type2;
    		$("input[name=contractType]").val(type);
    	}else{
    		$("input[name=contractType]").val("");
    	}
    	$("#page").val(1);
		$("#form").submit();
    }

  //显示合同分类
    function showContractType(paramObj){
    	var addHtml;
    	if($(paramObj).val()=="1"){
    		addHtml = "<option value=''>-请选择-</option>"+
    					"<option value='1'>工程类</option>"+
    					"<option value='2'>勘察类</option>"+
    					"<option value='3'>设计类</option>"+
    					"<option value='4'>前期类</option>"+
    					"<option value='5'>其他类</option>"+
    					"<option value='6'>监理类</option>"+
    					"<option value='7'>科研类</option>"+
    					"<option value='8'>咨询类</option>";
    	}else if($(paramObj).val()=="2"){
    		addHtml = "<option value=''>-请选择-</option>"+
    					"<option value='1'>服务</option>"+
    					"<option value='2'>工程</option>"+
    					"<option value='3'>货物</option>";
    	}else if($(paramObj).val()=="3"){
    		addHtml = "<option value='1'>其他类</option>";
    	}else{
    		addHtml = "<option value=''>-请选择-</option>";
    	}
    	$(paramObj).next().html(addHtml);
    }

function showOrhide(){
	var $target = $("#showOrHide");
	if($target.val()=="hide"){
		$target.val("show");
		$("#queryArea").slideDown();
	}else{
		$target.val("hide");
		$("#queryArea").slideUp();
	}
}

$(function(){
	$("tr[name='dataListArea']").each(function(){
		var a1 = parseFloat($(this).find("td[name='PriceContractTotal']").html());
		var a2 = parseFloat($(this).find("td[name='PriceChangeTotal']").html());
		var result = a1 + a2;
		var result2 = 0;		//变更比例 
		if(a2!=0){
			result2 = a2 / a1 * 100;
			result2 = result2.toFixed(2)+"%";
		}
		if((result+"").indexOf(".")!=-1){	//有小数点
			$(this).find("td[name='PriceAfterChange']").html(result.toFixed(4));
		}else{
			$(this).find("td[name='PriceAfterChange']").html(result.toFixed(0));
		}
		$(this).find("td[name='PriceChangePercent']").html(result2);


		var contractPrice = $(this).find("td[name='PriceContractTotal']").html();
		var planPayTotal = $(this).find("td[name='PricePlanPayTotal']").html();
		var actualPayTotal = $(this).find("td[name='PriceActualPayTotal']").html();

		var v1 = parseFloat(planPayTotal) / parseFloat(contractPrice) * 100;
		if(v1!=0){ v1 = v1.toFixed(4)}; 
		var v2 = parseFloat(actualPayTotal) / parseFloat(contractPrice) * 100;
		if(v2!=0){ v2 = v2.toFixed(4)}; 
		$(this).find("td[name='PricePlanPayPercent']").html(v1+"%");
		$(this).find("td[name='PriceActualPayPercent']").html(v2+"%");
	});
});

function showContract(target){
	$("#form").attr("action","list.action");
	$("#form").attr("target","_blank");
	$("#projectNo").val($.trim($(target).html()));
	
	$("#form").submit();
	
	$("#projectNo").val("");
	$("#form").removeAttr("target");
	$("#form").attr("action","projectList.action");

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
                	<li><a href="javascript:void(0);">合同管理</a></li>
                	<li class="fin">项目统计信息</li>
                </ul>
            </div>
            <div style="display:block;" class="fr lit_nav nwarp">
            	<ul>
                    <li><a class="query" href="javascript:void(0);" onclick="showOrhide();">关闭过滤</a></li>
                </ul>
            </div>
   		</div>
        <!--Ctrl End-->
      <div class="pt45">
        <!--Filter-->
      <div class="filter" id="queryArea">
        	<div class="query">
        	<div class="p8 filter_search">
        	
      	    <s:form action="projectList" id="form" method="post"  namespace="/htxx" theme="simple">
      	    	<input type="hidden" name="showOrHide" id="showOrHide" value="<s:property value="#request.showOrhide"/>"/>
      	    	<input type="hidden" name="page" id="page" value="<s:property value="page"/>"/>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr id="search1">
								<td class="t_r" style="white-space:nowrap">项目编号</td>
								<td style="white-space:nowrap">
									<input type="text" name="projectNo" id="projectNo" class="input_xlarge" value="<s:property value='#request.projectNo'/>">
								</td>
								<td class="t_r" style="white-space:nowrap">项目名称</td>
								<td style="white-space:nowrap">
									<input type="text" name="projectName"  class="input_xlarge" value="<s:property value='#request.projectName'/>">
								</td>
								<td class="t_r" style="white-space:nowrap">甲方(执行)</td>
								<td style="white-space:nowrap">
									<input type="text" name="contract_owner_execute_name"  class="input_large" value="<s:property value='#request.contract_owner_execute_name'/>" >
								</td>
							</tr>
							<tr id="search2">
								<td class="t_r" style="white-space:nowrap">合同编号</td>
								<td style="white-space:nowrap">
									<input type="text" name="contractNo" id="contractNo"  class="input_xlarge" value="<s:property value='#request.contractNo'/>" />
								</td>
								<td class="t_r" style="white-space:nowrap">合同名称</td>
								<td style="white-space:nowrap">
									<input type="text" name="contractName"  class="input_xlarge" value="<s:property value='#request.contractName'/>" >
								</td>
								<td class="t_r" style="white-space:nowrap">合同分类</td>
								<td style="white-space:nowrap">
									<input name="contractType" type="hidden" value="<s:property value='#request.contractType'/>" />
									<s:if test="#request.contractType==null || #request.contractType==''">
										<input type="hidden" id="hide_contractType2">
										<input type="hidden" id="hide_contractType1">
										<select name="contractType1" style="width:78px" onchange="showContractType(this);">
											<option value="">-请选择-</option>
											<option value="1">建设类</option>
											<option value="2">运维类</option>
											<option value="3">其他类</option>
										</select>
										<select name="contractType2" style="width:78px">
											<option value="">-请选择-</option>
										</select>
									</s:if>
									<s:else>
										<input type="hidden" value="<s:property value='#request.contractType.substring(2,3)'/>" id="hide_contractType2">
										<input type="hidden" value="<s:property value='#request.contractType.substring(0,1)'/>" id="hide_contractType1">
										<s:if test="#request.contractType.substring(0,1)==1">
											<select name="contractType1" style="width:78px" onchange="showContractType(this);">
												<option value="">-请选择-</option>
												<option value="1" selected="selected">建设类</option>
												<option value="2">运维类</option>
												<option value="3">其他类</option>
											</select>
											<select name="contractType2" style="width:78px">
												<option value="">-请选择-</option>
												<option value='1'>工程类</option>
												<option value='2'>勘察类</option>
												<option value='3'>设计类</option>
												<option value='4'>前期类</option>
												<option value='5'>其他类</option>
												<option value='6'>监理类</option>
												<option value='7'>科研类</option>
												<option value='8'>咨询类</option>
											</select>						
										</s:if>
										<s:elseif test="#request.contractType.substring(0,1)==2">
											<select name="contractType1" style="width:88px" onchange="showContractType(this);">
												<option value="">-请选择-</option>
												<option value="1">建设类</option>
												<option value="2" selected="selected">运维类</option>
												<option value="3">其他类</option>
											</select>
											<select name="contractType2" style="width:88px">
												<option value="">-请选择-</option>
												<option value='1'>服务</option>
												<option value='2'>工程</option>
												<option value='3'>货物</option>
											</select>
										</s:elseif>
										<s:else>
											<select name="contractType1" style="width:122px" onchange="showContractType(this);">
												<option value="">-请选择-</option>
												<option value="1">建设类</option>
												<option value="2">运维类</option>
												<option value="3" selected="selected">其他类</option>
											</select>
											<select name="contractType2" style="width:122px">
												<option value="3">其他类</option>
											</select>
										</s:else>
									</s:else>
								</td>
							</tr>
							<tr id="search3">
								<td class="t_r" style="white-space:nowrap">项目批文时间</td>
								<td style="white-space:nowrap">
									<input type="text" name="projectApproveStart"  style="width: 118px;" value="<s:property value='#request.projectApproveStart'/>" readonly="readonly">~
									<input type="text" name="projectApproveEnd"  style="width: 118px;" value="<s:property value='#request.projectApproveEnd'/>" readonly="readonly">
								</td>
							
								<td class="t_r" style="white-space:nowrap">合同签订时间</td>
								<td style="white-space:nowrap">
									<input type="text" name="contractSignedDate"  style="width: 118px;" value="<s:property value='#request.contractSignedDate'/>" readonly="readonly">~
									<input type="text" name="contractSignedEndDate"  style="width: 118px;" value="<s:property value='#request.contractSignedEndDate'/>" readonly="readonly">
								</td>
								<!-- 
								<td class="t_r" style="white-space:nowrap">统计时间</td>
								<td style="white-space:nowrap">
									<input type="text" name="stat_date"  style="width: 118px;" value="<s:property value='#request.stat_date'/>" readonly="readonly">
								</td>
								<td class="t_r" style="white-space:nowrap">统计情况</td>
								<td style="white-space:nowrap">
									<select	name="stat_status">
										<s:if test="#request.stat_status==null || #request.stat_status==''">
											<option value="">-请选择-</option>
											<option value="1">有</option>
											<option value="2">无</option>
										</s:if>
										<s:elseif test="#request.stat_status==1">
											<option value="">-请选择-</option>
											<option value="1" selected="selected">有</option>
											<option value="2">无</option>
										</s:elseif>
										<s:else>
											<option value="">-请选择-</option>
											<option value="1">有</option>
											<option value="2" selected="selected">无</option>
										</s:else>
									</select>
								</td>
								-->
								</td>
								<td class="t_r" style="white-space:nowrap">统计情况/统计时间</td>
								<td style="white-space:nowrap">
									<select	name="stat_status" style="width:88px">
										<s:if test="#request.stat_status==null || #request.stat_status==''">
											<option value="">-请选择-</option>
											<option value="1">有</option>
											<option value="2">无</option>
										</s:if>
										<s:elseif test="#request.stat_status==1">
											<option value="">-请选择-</option>
											<option value="1" selected="selected">有</option>
											<option value="2">无</option>
										</s:elseif>
										<s:else>
											<option value="">-请选择-</option>
											<option value="1">有</option>
											<option value="2" selected="selected">无</option>
										</s:else>
									</select>
									<input type="text" name="stat_date"  style="width: 118px;" value="<s:property value='#request.stat_date'/>" readonly="readonly">
								</td>
							</tr>
							<tr>
								<td colspan="6" class="t_c">
									<input type="button" value="检 索" onclick="checkForm();"/>&nbsp;&nbsp;
                  					<input id="clearInput" type="button" value="重 置"/>&nbsp;&nbsp;
                  					<input type="button" value="明 细" onclick="window.open('list.action')"/>
								</td>
							</tr>
						</table>
					</s:form>
      	    
      	    
      	    </div>
        	</div>     
		      <div class="fn clearfix">
		                  <h5 class="fl"><a href="#" class="fl">合同统计信息查询</a></h5>
		            </div>
		      </div>


      
        <!--Filter End-->
        <!--Table-->
         <s:set name="r" value="pageResultSet"></s:set>  
        <div class="mb10">
        	<table width="100%"  class="table_1" id="dataList">
                              <tbody>
                              <tr class="tit">
                             	<td class="t_c">序号</td>
                             	<td class="t_c">项目类型</td>
                                <td class="t_c">项目编号</td>
                                <td class="t_c">项目概算(万元)</td>
                                <td class="t_c">项目名称</td>
                                <td class="t_c">资金渠道</td>
                               	<td class="t_c">项目公司</td>
                                <td class="t_c">合同总价（万元）</td>
                                <td class="t_c">合同变更总次数</td>
                                <td class="t_r">合同变更总金额</td>
                                <td class="t_r">变更后合同总金额</td>
                                <td class="t_r">总变更比例</td>
                                <td class="t_r">总决算价(万元)</td>
                                <td class="t_r">开累计划支付金额</td>
                                <td class="t_r">开累实际支付金额</td>
                                <td class="t_r">开累计划支付比例</td>
                                <td class="t_r">开累实际支付比例</td>
                                <!-- 
                                <td class="t_r">开累计划支付比例</td>
                                <td class="t_r">开累实际支付比例</td>
                                 -->
                                </tr>
                              <s:iterator value="#r.list" id="items" status="s">
                              <tr name="dataListArea">
                              	<td class="t_c"><s:property value="(#s.index+1)+(#r.pageInfo.currentPage-1)*10"/></td>
                              	<td class="t_c" style="white-space: nowrap;"><s:property value="#items.project_type"/></td>
                              	<td class="t_l" style="white-space: nowrap;"><a href="javascript:void(0);" onclick="showContract(this);"><s:property value="#items.project_no"/></a></td>
                              	<td class="t_r" name="Price"><s:property value="#items.project_budget_all"/></td>
                              	<td class="t_l" style="white-space: nowrap;"><s:property value="#items.project_name"/></td>
                              	<td class="t_c" style="white-space: nowrap;"><s:property value="#items.money_source_type"/></td>
                                <td class="t_l" style="white-space: nowrap;"><s:property value="#items.project_company"/></td>
                                <td class="t_r" name="PriceContractTotal"><s:property value="#items.contract_price_total"/></td>
                                <td class="t_r" ><s:property value="#items.change_total_count"/></td>
                                <td class="t_r" name="PriceChangeTotal"><s:property value="#items.change_total_price"/></td>
                                <td class="t_r" name="PriceAfterChange"></td>
                                <td class="t_r" name="PriceChangePercent"></td>
                                <td class="t_r" name="Price"><s:property value="#items.final_price_total"/></td>
                                <td class="t_r" name="PricePlanPayTotal"><s:property value="#items.plan_pay_total"/></td>
                                <td class="t_r" name="PriceActualPayTotal"><s:property value="#items.actual_pay_total"/></td>
                                <td class="t_r" name="PricePlanPayPercent"></td>
                                <td class="t_r" name="PriceActualPayPercent"></td>
                               
                                </s:iterator>
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="35"><div class="clearfix"><span class="fl">共<s:property value="#r.pageInfo.totalRow"/>条记录</span>
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
<script>
/*
$(".mb10").width($(window).width());
$(".mb10").css( "overflowX", "auto" );
*/
</script>
</html>