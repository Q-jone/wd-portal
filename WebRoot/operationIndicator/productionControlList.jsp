<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>管控查看</title>
<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/default/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
<!--[if IE 6.0]>
           <script src="js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
<script src="../js/html5.js"></script>
<script src="../js/jquery-1.7.1.js"></script>
<script src="../js/jquery.formalize.js"></script>
<script type="text/javascript">
$(function(){
	$(".odd tr:odd").css("background","#fafafa");
	$("#tbody").children("tr").each(function(index,trObj){
		$(trObj).children("td").each(function(index,tdObj){
			$(tdObj).css("text-align","center");	
		});
	});
	
	var hiddenDate = $("#hiddenDate").val();
	$("select[name=date]").children().each(function(){
		if($(this).html()==hiddenDate) $(this).attr("selected",true); 
	});
});

var sonWindow = null;

//跳转到添加页面
function showAddPage(){
	sonWindow = window.open('showProductionControlAddPage.action');
}
//跳转到编辑页面	
function showEditPage(id){
	sonWindow = window.open("showProductionControlEditPage.action?id="+id);
}
//每1秒执行一次checkSonWindow()方法
var refresh = setInterval("checkSonWindow()",1000);
 //监测子窗口是否关闭
function checkSonWindow(){
	if(sonWindow!=null && sonWindow.closed==true){
		//$("#sForm").submit();
		window.location.href = "showProductionControlPage.action";
		clearInterval(refresh);
	}
}

//删除
function deleteById(id){
	if(confirm("是否删除？")){
		$.ajax({
			type : 'post',
			url : 'deleteProductionControl.action?random='+Math.random(),
			dataType:'json',
			cache : false,
			data:{
				id:id
			},
			error:function(){
				alert("系统连接失败，请稍后再试！")
			},
			success:function(object){
				$("#form").submit();
			}
		});
	}
}

//跳转到指定页
function goPage(pageNo,type){
    if(type=="0"){
    	var pageCount = $("#totalPageCount").val();
       	var number = $("#number").val();
       	if(!number.match(/^[0-9]*$/)){
  			alert("请输入数字");
  			$("#number").val("");
  			$("#number").focus();
  			return ;
      	}
    	if(parseInt(number)>parseInt(pageCount)){
    		$("#number").val(pageCount);
    		$("#pageNo").val(pageCount);
    	}else{
    		$("#pageNo").val(number);
    	}
	}else if(type=="1"){	//type=1,跳转到上一页
    	$("#pageNo").val(parseInt($("#number").val())-1);
    }else if(type=="2"){	//type=2,跳转到下一页
    	$("#pageNo").val(parseInt($("#number").val())+1);
    }else if(type=="3"){	//type=3,跳转到最后一页,或第一页
   		$("#pageNo").val(pageNo);
    }
    $("#startDateHide").val($("#datepicker1").val());
    $("#endDateHide").val($("#datepicker2").val());
    $("#form").submit();
}

function clearForm(){
	$("select").each(function(){
		$(this).find("option:first").attr("selected",true);
	});
}
</script>
</head>

<body>
<div class="main"><!--Ctrl-->
<div class="ctrl clearfix">
<div class="fl"><img src="../css/default/images/sideBar_arrow_right.jpg"
	width="46" height="30" alt="收起"></div>
<div class="posi fl">
<ul>
	<li><a href="#">运营管理</a></li>
   	<li><a href="#">运营指标</a></li>
   	<li class="fin">运营指标管控设置</li>
</ul>
</div>
<div class="fr lit_nav">
<ul>
	<li class="selected"><a class="print" href="#">打印</a></li>
	<li><a class="storage" href="#">网络硬盘</a></li>
	<li><a class="rss" href="#">订阅</a></li>
	<li><a class="chat" href="#">聊天</a></li>
	<li><a class="query" href="#">查询</a></li>
</ul>
</div>
</div>
<div class="pt45"><!--
 <div class="tabs_2 nwarp">
	<ul>
    	<li class="selected" id="tab1"><a href="#"><span>运营生产指标管控</span></a></li>
		 <li id="tab2"><a href="showQualityControlPage.action"><span>运营质量安全指标管控</span></a></li> 
    </ul>
</div>

--><div class="filter">
<input type="hidden" value="<s:property value='#request.date'/>" id="hiddenDate">
	<div class="query p8">
        		<s:form action="showProductionControlPage" name="MetroProductionControl" namespace="/indicatorControl" id="form" method="post">
        		<input type="hidden" name="pageNo" id="pageNo"/>
	        	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
	        	   <tr>
	        	    <td class="t_r">线路</td>
	        	     <td>
	        	      	<select name="line" id="select3" class="input_small">
							<option value="-1" id="-1op">---请选择---</option>	        	      		
	        	      		<s:iterator value="#request.lineMap" id="lineMap">
	        	      			<s:if test="lineMap.key=='0'">
	        	      				<s:if test="#request.line==#lineMap.key"><option value="<s:property value='#lineMap.key'/>" selected="selected">全网</option></s:if>
	        	      				<s:else><option value="<s:property value='#lineMap.key'/>">全网</option></s:else>
	        	      			</s:if>
	        	      			<s:else>
	        	      				<s:if test="#request.line==#lineMap.key">
	        	      					<option value="<s:property value='#lineMap.key'/>" selected="selected"><s:property value="#lineMap.value"/></option>
	        	      				</s:if>
	        	      				<s:else>
										<option value="<s:property value='#lineMap.key'/>"><s:property value="#lineMap.value"/></option>	        	      				
	        	      				</s:else>
	        	      			</s:else>
	        	      		</s:iterator>
	      	        	</select>
	      	          </td>
	        	      <td class="t_r">年份</td>
	        	      <td>
	        	      	<select name="date">
	        	      		<option value="-1">---请选择---</option>
				    		<option>2000</option>
				    		<option>2001</option>
				    		<option>2002</option>
				    		<option>2003</option>
				    		<option>2004</option>
				    		<option>2005</option>
				    		<option>2006</option>
				    		<option>2007</option>
				    		<option>2008</option>
				    		<option>2009</option>
				    		<option>2010</option>
				    		<option>2011</option>
				    		<option>2012</option>
				    		<option>2013</option>
				    		<option>2014</option>
				    		<option>2015</option>
				    		<option>2016</option>
				    		<option>2017</option>
				    		<option>2018</option>
				    		<option>2019</option>
				    		<option>2020</option>
				    	</select>
	        	      </td>
	      	        </tr>
	        	    <tr>
	        	      <td colspan="6" class="t_c">
	                  	<input type="submit" value="检 索" />&nbsp;&nbsp;
						<input type="button" value="重 置" onclick="clearForm();"/></td>
	       	        </tr>
	      	    </table>
      	    </s:form>
        	</div>
	<div class="fn">
		<input type="button"" name="button2" id="button2" value="新增" class="new" onclick="showAddPage();">
	</div>
</div>

<div class="mb10">
	<div class="mb10">
		<table width="100%" class="table_1">
			<thead>
				<th colspan="16"></th>
			</thead>
			<tbody id="tbody">
			
			<tr class="tit">
				<td>线路</td>
				<td>日期</td>
				<td>开行列次&nbsp;(单位：列次)</td>
				<td>运营里程&nbsp;(单位：万车公里)</td>
				<td>客流量&nbsp;(单位：万人)</td>
				<td>换乘人次&nbsp;(单位：万人)</td>
				<td>客运收入&nbsp;(单位：万元)</td>
				<td></td>
				<td colspan="2">操作</td>
			</tr>
			<s:iterator value="#request.page.result" id="voList" >
				<tr>
					<td id="td_1">
						<s:if test="#voList.indicatorLine==0">全网</s:if>
						<s:else><s:property value="#voList.indicatorLine"/>号线</s:else>
					</td>
					<td id="td_3">
						<s:if test="#voList.ext1!=null">
							<s:property value="#voList.ext1"/>年
						</s:if>
						<s:if test="#voList.ext2==0">
							全年
						</s:if>
						<s:else>
							<s:property value="#voList.ext2"/>月						
						</s:else>
					</td>
					<td id="td_3"><s:property value="#voList.metroColumnDaily"/></td>
					<td id="td_3"><s:property value="#voList.metroDistanceDaily"/></td>
					<td id="td_4"><s:property value="#voList.passengerCapacityDaily"/></td>
					<td id="td_5"><s:property value="#voList.passengerTransferDaily"/></td>
					<td id="td_5"><s:property value="#voList.ticketIncomeDaily"/></td>
					<td id="td_5">
						<a href="showProductionControlDetailPage.action?id=<s:property value='#voList.id'/>" target="_blank">查看</a>
					</td>
					<td id="td_5">
						<!-- <a href="showProductionControlEditPage.action?id=<s:property value='#voList.id'/>" target="_blank">编辑</a> -->
						<a href="javascript:void(0);" onclick="showEditPage('<s:property value='#voList.id'/>');">编辑</a>
					</td>
					<td id="td_5">
						<a href="javascript:viod(0);" onclick="deleteById('<s:property value='#voList.id'/>');">删除</a>
					</td>
					
				</tr>
			</s:iterator>
			</tbody>
			
			<tr class="tfoot">
			      <td colspan="11"><div class="clearfix">
			      <s:if test="#request.page.totalSize==0"><span>无相关数据</span></s:if>
			      <s:else>
			      	<span class="fl">
				      	<s:property value="#request.page.totalSize"/>条记录，当前显示<s:property value="#request.page.start"/> -
					    <s:if test="#request.page.currentPageNo==#request.page.totalPageCount">
					      	<s:property value="#request.page.totalSize"/>条
					    </s:if>
					    <s:else>
					    	<s:property value="#request.page.start+#request.page.pageSize-1"/>条
						</s:else>
				    </span>
			        <ul class="fr clearfix pager">
			          <li>Pages:<s:property value="#request.page.currentPageNo"/>/<s:property value="#request.page.totalPageCount"/>
			          	<input type="hidden" value="<s:property value='#request.page.totalPageCount'/>" id="totalPageCount">
			            <input type="text"" id="number" name="pageNumber" min="0" max="999" step="1" class="input_tiny" value="<s:property value='#request.page.currentPageNo'/>" />
			            <input type="button"" name="button" id="button" value="Go" onclick="goPage(0,0)">
		           	  </li>
		           	  
		               <s:if test="#request.page.currentPageNo==#request.page.totalPageCount">
		               	 <li><a href="javascript:void(0)">&gt;&gt;</a></li>
		               </s:if>
		               <s:else>
		                <li><a href="javascript:void(0)" onclick="goPage(<s:property value='#request.page.totalPageCount'/>,3)">&gt;&gt;</a></li>
		               </s:else>
		                
		              <li>
		              	<s:if test="#request.page.currentPageNo==#request.page.totalPageCount">	
		              		<a href="javascript:void(0)">下一页</a>
		              	</s:if>
		              	<s:else>
		              		<a href="javascript:void(0)" onclick="goPage(<s:property value='#request.page.currentPageNo'/>,2)">下一页</a>
		              	</s:else>
		              </li>
		              
		              <li>
		              	<s:if test="#request.page.currentPageNo==1">
		              		<a href="javascript:void(0)">上一页</a>
		              	</s:if>
		              	<s:else>
		              		<a href="javascript:void(0)" onclick="goPage(<s:property value='#request.page.currentPageNo'/>,1)">上一页</a>
		              	</s:else>
		              </li>
		              
		              <s:if test="#request.page.currentPageNo==1">
		              	<li><a href="javascript:void(0)">&lt;&lt;</a></li>
		              </s:if>
		              <s:else>
		              	<li><a href="javascript:void(0)" onclick="goPage(1,3)">&lt;&lt;</a></li>
		              </s:else>
		         </ul>
		       </s:else>
		       </div></td>
		     </tr>
		</table>
		
	</div>
</div>
<!--Table End--></div>
</div>
</body>
</html>
