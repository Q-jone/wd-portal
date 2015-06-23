<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>招标计划管理</title>
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
         <script src="js/jquery.blockUI.js"></script>
        <script src="js/jquery.form.js"></script>
		<script src="../js/jquery.formalize.js"></script>
		<!--<script src="../js/switchDept.js"></script>-->
		<script src="../js/show.js"></script>
		<link type="text/css" href="../css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="../js/flick/jquery-ui-1.8.18.custom.min.js"></script>
		<script type="text/javascript" src="../js/flick/jquery.ui.datepicker-zh-CN.js"></script>
        <style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
		</style>		
	<script type="text/javascript">
		var listOptions = {
			cache:false,
			dataType:'json',
			type:'post',
			callback:null,
			url:'/portal/bidList/list.action?random='+Math.random(),
		    success:function(data){
			    	var p = data.pageInfo;
					var temp = "";
					if(data!=null && data.list.length >0){
						var l = data.list;
						
						for(var i=0;i<l.length;i++){
							temp += "<tr>";
							temp += "<td class='t_c'>"+(i+1) +"</td>";
							temp += "<td class='t_c'>"+l[i].PROJECTCOMPANY +"</td>";
							temp += "<td class='t_c'>"+l[i].LINE +"</td>";
							temp += "<td class='t_c'>"+l[i].CATALOGNAME +"</td>";
							temp += "<td class='t_c'>"+l[i].TYPENAME +"</td>";
							temp += "<td class='t_c'>"+l[i].MAJORNAME +"</td>";
							temp += "<td class='t_c'>"+l[i].BIDNUM +"</td>";
							temp += "<td class='t_c'>"+l[i].PROJECTNAME +"</td>";
							temp += "<td class='t_c'>"+l[i].BIDPLANDATE +"</td>";
							temp += "<td class='t_c'>"+l[i].BIDAMOUNT +"</td>";
							temp += "<td class='t_c'>"+l[i].BIDINFODATE +"</td>";
							temp += "<td class='t_c'>"+l[i].STATUS +"</td>";
							temp += "<td class='t_c'><a target='_blank' href='/portal/bid/edit.action?id="+l[i].ID+"'>编辑</a></td>";
							temp += "</tr>";
						}
						$("#totalPage_out").val(p.totalPage);
						$("#totalPage").html("当前显示"+(((p.currentPage-1)*p.pageSize)+1)+"-"+(((p.currentPage-1)*p.pageSize)+l.length)+"条记录，"+"总记录："+p.totalRow+"条");	
						var totalOption = "";
						for(var i=1;i<=p.totalPage;i++){
							totalOption += "<option value='"+i+"'>"+i+"</option>";						
							$("#page_out").html(totalOption);
						}
						$("#page_out").val(p.currentPage);
					}else{
						$("#totalPage_out").val(0);
						$("#totalPage").html("当前显示0条记录，总记录：0条");
						$("#page_out").html("<option value='0'>0</option>");
					}
					
					var t = $("#show>tbody").eq(0).find("tr:first").html();
					//console.log($("#show>tbody").eq(0).html());
					$("#show>tbody").eq(0).html("<tr class='tit'>"+t+"</tr>"+temp);
					$.unblockUI();
				return false;
		    }
		};
		
		function list(){
			$.blockUI({ message: $('#domMessage') }); 
			$("#list").ajaxSubmit(listOptions);
		}
		
		var k =0;
		function getBidType(flag,element){
			$.ajax({
				type: 'POST',
				url: 'getBidType.action?random='+Math.random(),
				data : {
							"flag" : flag
						},
				dataType:'json',
				cache : false,
				error:function(){alert('系统连接失败，请稍后再试！')},
				success: function(obj){			
					if(obj !=null && obj.length >0){
						var option = "<option value=''>全部</option>";
						for(var i=0;i<obj.length;i++){
							option +="<option value='"+obj[i].TYPEID+"'>"+obj[i].TYPENAME+"</option>";
						}
						$(element).html(option);
						k++;
						if(k==6){
							list();
						}
					}
				}	  
			});	
		}
		
	 	$(function(){
	 		$("#prePage").click(function(){
	 			if($("#page_out").val() == 1 || $("#page_out").val() == 0){}
	 			else{
	 				$("#page_out").val(parseInt($("#page_out").val()-1));
	 				$("#page").val($("#page_out").val());
	 				list();
	 			}
	 			
	 		})
	 		
	 		$("#nextPage").click(function(){
	 			if(($("#page_out").val() == $("#totalPage_out").val()) || $("#page_out").val() ==0){}
	 			else{
	 				$("#page_out").val(parseInt($("#page_out").val())+1);
	 				$("#page").val($("#page_out").val());
	 				list();
	 			}
	 			
	 		})
	 	
	 	$(document).on("click","#excelButton",function(){
        	window.open("/portal/bidList/excel.action?"+$("#list").formSerialize());
        })
        
        $(document).on("click","#excelAllButton",function(){
        	window.open("/portal/bidList/excelAll.action?"+$("#list").formSerialize());
        })
	 		
	 	$("#submit").click(function(){
	 		list();
       	 })
       	 $("#list select").each(function(i,n){
        		getBidType(i,n);
        	})
	 	
        $("#page_out").on("change",function(){
        	$("#page").val($(this).val());
        	list();
        })
        
         $("#pageSize_out").on("change",function(){
        	if($("#page_out").val()!=0){
	        	$("#pageSize").val($(this).val());
	        	list();
         	}
        })
        	
       	$('#bidPlanDate_startDate').datepicker({
			//inline: true								
			"changeYear":true,
			"showButtonPanel":true,	
			"closeText":'清除',	
			"currentText":'bidPlanDate_startDate'//仅作为“清除”按钮的判断条件						
		});
 		$('#bidPlanDate_endDate').datepicker({
			//inline: true								
			"changeYear":true,
			"showButtonPanel":true,	
			"closeText":'清除',	
			"currentText":'bidPlanDate_endDate'//仅作为“清除”按钮的判断条件						
		});
		$('#bidInfoDate_startDate').datepicker({
			//inline: true
			"changeYear":true,
			"showButtonPanel":true,	
			"closeText":'清除',	
			"currentText":'bidInfoDate_startDate'//仅作为“清除”按钮的判断条件
		});	
		$('#bidInfoDate_endDate').datepicker({
			//inline: true
			"changeYear":true,
			"showButtonPanel":true,	
			"closeText":'清除',	
			"currentText":'bidInfoDate_endDate'//仅作为“清除”按钮的判断条件
		});	
		//datepicker的“清除”功能
           $(".ui-datepicker-close").live("click", function (){              
             if($(this).parent("div").children("button:eq(0)").text()=="bidPlanDate_startDate") $("#bidPlanDate_startDate").val("");
             if($(this).parent("div").children("button:eq(0)").text()=="bidInfoDate_startDate") $("#bidInfoDate_startDate").val("");     
             if($(this).parent("div").children("button:eq(0)").text()=="bidPlanDate_endDate") $("#bidPlanDate_endDate").val("");
             if($(this).parent("div").children("button:eq(0)").text()=="bidInfoDate_endDate") $("#bidInfoDate_endDate").val("");     
                             
           });
		
       	list();
		})
		
		
    
	
		
		
    </script>



</head>

<body>
<%-- 操作页面--%>
<div id="domMessage" style="display:none;"> 
    <h1>请稍后</h1> 
</div> 	
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
			<div class="fl"><img id="show" onclick="showHide();" src="../css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li class="fin">招标计划管理</li>
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
        	
        </div>
        <!--Filter-->
      <div class="filter">
        	<div class="query">
        	<div class="p8 filter_search">
        	<form id="list">
        	<input type="hidden" name="pageSize" id="pageSize" value=""/>
        	
        	<input type="hidden" name="page" id="page" value=""/>
        	 <table width="100%" border="0" cellspacing="0" cellpadding="0">
        	    <tr>
        	     <td class="t_r">项目公司</td>
        	     <td>
        	     <select name="projectCompanyId_equal" id="projectCompanyId_equal" class="input_xxlarge" > 
                 </select>
        	     </td>
        	     <td class="t_r">线路</td>
        	     <td>
        	      <select name="lineId_equal" id="lineId_equal" class="input_large" > 
                  </select>	
        	      </td>
        	     <td class="t_r">子目</td>
        	     <td>
        	       <select name="catalogId_equal" id="catalogId_equal" class="input_large" > 
                                </select>	
        	      </td>
      	      	</tr> 
      	    	<tr>
        	     <td class="t_r">类别</td>
        	     <td>
        	      <select name="typeId_equal" id="typeId_equal" class="input_large" > 
                                </select>
        	     </td>
        	     <td class="t_r">专业</td>
        	     <td>
        	      <select name="majorId_equal" id="majorId_equal" class="input_large" > 
                                </select>	
        	      </td>
        	     <td class="t_r">标段号</td>
        	     <td>
        	      <input type="text" id="bidNum_like" name="bidNum_like" class="input_xxlarge"/>
        	      </td>             
      	      	</tr> 
      	     	<tr>
        	     <td class="t_r">工程名称</td>
        	     <td>
        	     <input type="text" id="projectName_like" name="projectName_like" class="input_xxlarge"/>
        	     </td>
        	     <td class="t_r">招标计划</td>
        	     <td>
        	      	<input readonly="readonly" type="date" id="bidPlanDate_startDate" name="bidPlanDate_startDate" value=""/>
        	      	至
        	      	<input readonly="readonly" type="date" id="bidPlanDate_endDate" name="bidPlanDate_endDate" value=""/>
        	      </td>
        	     <td class="t_r">中标金额</td>
        	     <td>
        	      	<input type="text" id="bidAmount_like" name="bidAmount_like"/>
        	      </td>
      	      	</tr> 
      	      	<tr>
        	     <td class="t_r">中标通知书发出日期</td>
        	     <td>
        	     <input readonly="readonly" type="date" id="bidInfoDate_startDate" name="bidInfoDate_startDate" value=""/>
					 至
        	     <input readonly="readonly" type="date" id="bidInfoDate_endDate" name="bidInfoDate_endDate" value=""/>
        	     </td>
        	     <td class="t_r">状态</td>
        	     <td><select name="statusId_equal" id="statusId_equal" class="input_large" > 
                                </select>	</td>
        	     <td class="t_r"></td>
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
		                  <h5 class="fl"><a href="#" class="colSelect fl">招标计划列表</a></h5>
		             <input type="button" onclick="window.open('/portal/bid/add.jsp');" name="addButton" id="addButton" value="新 增" class="fr">
		            <input type="button" name="excelButton" id="excelButton" value="导 出当页至 EXCEL" class="fl">
		              &nbsp;<input type="button" name="excelAllButton" id="excelAllButton" value="导 出全部至 EXCEL" class="fl">
		            </div>
		      </div>


      
        <!--Filter End-->
        <!--Table-->
        <div class="mb10">
        	<table width="100%"  class="table_1" id="show">
                              <tbody>
                              <tr class="tit">
                                <!-- <td><input type="checkbox" id="test_checkbox_1" name="test_checkbox_1" /></td>-->
                                <td class="t_c">序号</td>
                                <td class="t_c">项目公司</td>
                                <td class="t_c">线路</td>
                                <td class="t_c">子目</td>
                                <td class="t_c">类别</td>
                                <td class="t_c">专业</td>
                                <td class="t_c">标段号</td>
                                <td class="t_c">工程名称</td>
                                <td class="t_c">招标计划</td>
                                <td class="t_c">中标金额</td>
                                <td class="t_c">中标通知书发出日期</td>
                                <td class="t_c">状态</td>
                                <td class="t_c">编辑</td>
                                </tr>
                              
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="13"><div class="clearfix">
                                <input type="hidden" id="totalPage_out">
                                <span id="totalPage" class="fr">当前显示1-10条记录，总记录：100</span>
                           		<ul class="fl clearfix pager">
		                             <li class="fl" id="nextPage"><a href="javascript:void(0)">下一页</a></li>
		                             <li class="fl" id="prePage"><a href="javascript:void(0)">上一页</a> </li>
		                        </ul>
		                            <span class="fl">
									&nbsp; 跳转至：
	                             	<select id="page_out"></select>
	                             	
	                             	</span>
	                             	<span class="fl">&nbsp;每页显示条数：	</span>
	                             	<select id="pageSize_out">
                                <option value="10">10</option>
                                <option value="15">15</option>
                                <option value="20">20</option>
                                <option value="30">30</option>
                                </select>
                            
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