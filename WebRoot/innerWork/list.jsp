<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>部门重点工作跟踪</title>
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
        <script src="js/jquery.tablesorter.js"></script>
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
	jQuery.notNull = function(options){
			if (typeof(options) == "undefined" || options== null){
				return "";
			}else {
				return options;
			}
		}
	

		var listOptions = {
			cache:false,
			dataType:'json',
			type:'post',
			callback:null,
			url:'/portal/innerWorkList/list.action?random='+Math.random(),
		    success:function(data){
			    	var p = data.pageInfo;
					var temp = "";
					if(data!=null && data.list.length >0){
						var l = data.list;
						
						for(var i=0;i<l.length;i++){
							temp += "<tr>";
							temp += "<td class='t_c'>"+(i+1) +"</td>";
							//截取督办工作名称
							var jobName="";
							if($.notNull(l[i].JOBNAME)!="" && $.notNull(l[i].JOBNAME).length>15){
							   jobName=$.notNull(l[i].JOBNAME).substring(0,15)+"..."; 
							}else{
							   jobName=$.notNull(l[i].JOBNAME);
							}
							temp += "<td class='t_l'>"+jobName +"</td>";
							temp += "<td class='t_l'>"+$.notNull(l[i].PROCESS) +"</td>";
							temp += "<td class='t_c'>"+$.notNull(l[i].BTIME) +"</td>";
							temp += "<td class='t_c'>"+$.notNull(l[i].PFTIME) +"</td>";
							temp += "<td class='t_c'>"+$.notNull(l[i].PFLAG) +"</td>";
							temp += "<td class='t_c'>"+$.notNull(l[i].STATUS) +"</td>";
							var rPeople="";
							if($.notNull(l[i].RPEOPLE)!="" && $.notNull(l[i].RPEOPLE).length>15){
							     rPeople=$.notNull(l[i].RPEOPLE).substring(0,15)+"...";
							}else{
							     rPeople=$.notNull(l[i].RPEOPLE);
							}
							temp += "<td class='t_c'>"+rPeople +"</td>";
							var rLeader="";
							if($.notNull(l[i].RLEADER)!="" && $.notNull(l[i].RLEADER).length>15){
							    rLeader=$.notNull(l[i].RLEADER).substring(0,15)+"...";
							}else{
							    rLeader=$.notNull(l[i].RLEADER);
							}
							temp += "<td class='t_c'>"+rLeader +"</td>";
							 <s:if test='#session.userName=="孔琰" 
	                                || #session.userName=="李名敏" 
	                                || #session.userName=="金涛"
	                                || #session.userName=="黄天印"'>
							temp += "<td class='t_c'><a class='editA' href='javascript:void(0);' jobId='"+l[i].ID+"'>编辑</a></td>";
							</s:if>
							temp += "<td class='t_c'><a target='_blank' href='/portal/innerWork/view.action?id="+l[i].ID+"'>查看</a></td>";
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
						$("#pageSize_out option:last").val(p.totalRow);
					}else{
						$("#totalPage_out").val(0);
						$("#totalPage").html("当前显示0条记录，总记录：0条");
						$("#page_out").html("<option value='0'>0</option>");
					}
					
					$("#mytable>tbody").eq(0).html(temp);
					//var t = $("#show>tbody").eq(0).find("tr:first").html();
					//console.log($("#show>tbody").eq(0).html());
					//alert(index);
					if(index == 0){
						$('#mytable').tablesorter();
						index ++;
					}else{
						$('#mytable').trigger("update"); 
					}
					$.unblockUI();
					$('#mytable').find('td:contains("延迟")').parent("tr").css("backgroundColor","#FFDAB9");
				return false;
		    }
		};
		
		
		function list(){
			$.blockUI({ message: $('#domMessage') }); 
			 $("#list").ajaxSubmit(listOptions);
		}
		
		var intHandn=null;
		var rtnn=null;	
		function checkWinn(){
				if(rtnn!=null && rtnn.closed){
					clearInterval(intHandn);
					intHandn=null;
					rtnn=null;
					list()
				}
		}
		function openWinn(url,i){
			rtnn = window.open(url,i);
			intHandn=setInterval("checkWinn()",3000);
			return false;
		}
		
		var index = 0;
		
	 	$(function(){
	 		$("#addButton").click(function(){
	 			openWinn("/portal/innerWork/add.jsp",new Date().getTime());
	 		})
	 		
	 		$(document).on("click",".editA",function(){
	 			openWinn("/portal/innerWork/edit.action?id="+$(this).attr("jobId"),$(this).attr("jobId"));
	 			return false;
	 		})
	 		
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
	 	
	 		
	 	$("#submit").click(function(){
       		 list();
       	 })
       	
        $(document).on("change","#page_out",function(){
        	$("#page").val($(this).val());
        	list();
        })
        
        $(document).on("change","#pageSize_out",function(){
        	if($("#page_out").val()!=0){
	        	$("#pageSize").val($(this).val());
	        	list();
         	}
        })
        	
        $(document).on("click","#excelButton",function(){
        	window.open("/portal/innerWorkList/excel.action?"+$("#list").formSerialize());
        })
        
        $(document).on("click","#excelAllButton",function(){
        	window.open("/portal/innerWorkList/excelAll.action?"+$("#list").formSerialize());
        })
        
        
       	$('#pfTime_startDate').datepicker({
			//inline: true								
			"changeYear":true,
			"showButtonPanel":true,	
			"closeText":'清除',	
			"currentText":'pfTime_startDate'//仅作为“清除”按钮的判断条件						
		});
 		$('#pfTime_endDate').datepicker({
			//inline: true								
			"changeYear":true,
			"showButtonPanel":true,	
			"closeText":'清除',	
			"currentText":'pfTime_endDate'//仅作为“清除”按钮的判断条件						
		});
 		
 		$('#bTime_startDate').datepicker({
			//inline: true								
			"changeYear":true,
			"showButtonPanel":true,	
			"closeText":'清除',	
			"currentText":'bTime_startDate'//仅作为“清除”按钮的判断条件						
		});
 		$('#bTime_endDate').datepicker({
			//inline: true								
			"changeYear":true,
			"showButtonPanel":true,	
			"closeText":'清除',	
			"currentText":'bTime_endDate'//仅作为“清除”按钮的判断条件						
		});	
		//datepicker的“清除”功能
          $(document).on("click", ".ui-datepicker-close",function (){              
             if($(this).parent("div").children("button:eq(0)").text()=="pfTime_startDate") $("#pfTime_startDate").val("");
             if($(this).parent("div").children("button:eq(0)").text()=="pfTime_endDate") $("#pfTime_endDate").val("");     
             if($(this).parent("div").children("button:eq(0)").text()=="bTime_startDate") $("#bTime_startDate").val("");
             if($(this).parent("div").children("button:eq(0)").text()=="bTime_endDate") $("#bTime_endDate").val("");     
                           
           });
		
           list();
           
           
           
		});	
		
		//调整完成状态不为“已完成”，且进展标志为“延迟”的记录，以不同底色高亮显示，并排在所有数据的前面
// 		function improvePosition(){
// 		       var trs=$("#mytable>tbody tr");
// 		       alert(trs.length);   
//  		}
    </script>

	<style>
	.headerSortDown {
	    background: url("js/desc.gif") no-repeat scroll center right transparent;
	    cursor: pointer;
	}

	.headerSortUp {
	    background: url("js/asc.gif") no-repeat scroll center right transparent;
	    cursor: pointer;
	}
	
	</style>
</head>

<body>
<div id="domMessage" style="display:none;"> 
    <h1>请稍后</h1> 
</div> 	
<%-- 操作页面--%>
		
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
			<div class="fl"><img id="show" onclick="showHide();" src="../css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li class="fin">部门重点工作跟踪</li>
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
<!--         <div class="tabs_2" style="height:55px;"> -->
<!--         	<table width="100%" border="0" cellspacing="0" cellpadding="0"> -->
<!--         		<tr class="t_r"><td style="font-size:20px;padding:3px 5px 3px 25px;">延迟工作数：X个</td></tr> -->
<!--         	</table> -->
<!--         </div> -->
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
        	     <td class="t_r">督办工作</td>
        	     <td>
        	     <input type="text" id="jobName_like" name="jobName_like" class="input_xxlarge"/>
        	     </td>
        	     <td class="t_r">进展标志</td>
        	     <td>
        	     <select name="pFlag_equal" id="pFlag_equal" class="input_large" > 
                    <option value="">全部</option>
                    <option value="正常">正常</option>
                    <option value="延迟">延迟</option>
                    </select>
        	      </td>
        	     <td class="t_r">完成状态</td>
        	     <td>
        	       <select name="status_equal" id="status_equal" class="input_large" > 
                    <option value="">全部</option>
                    <option value="进行中">进行中</option>
                    <option value="未完成">未完成</option>
                    <option value="已完成">已完成</option>
                    </select>	
        	      </td>
      	      	</tr> 
      	      	<tr>
        	     <td class="t_r">开始时间</td>
        	     <td>
        	     <input readonly="readonly" type="date" id="bTime_startDate" name="bTime_startDate" value=""/>
        	      	至
        	      	<input readonly="readonly" type="date" id="bTime_endDate" name="bTime_endDate" value=""/>
        	      
        	      </td>
        	     <td class="t_r">要求完成时间</td>
        	     <td>
        	       <input readonly="readonly" type="date" id="pfTime_startDate" name="pfTime_startDate" value=""/>
        	      	至
        	      	<input readonly="readonly" type="date" id="pfTime_endDate" name="pfTime_endDate" value=""/>
        	      
        	      </td>
        	      <td class="t_r"></td>
        	     <td>
        	      </td>             
      	      	</tr> 
      	    	<tr>
        	     <td class="t_r">责任人</td>
        	     <td>
        	     <input type="text" id="rPeople_like" name="rPeople_like" class="input_xxlarge"/>
        	     </td>
        	     <td class="t_r">分管领导</td>
        	     <td>
        	     <input type="text" id="rLeader_like" name="rLeader_like" class="input_xxlarge"/>
        	      </td>
        	     <td class="t_r"><input type="hidden" id="order" name="order" value="case when pflag ='延迟' then 1 else 0 end desc,t.status,t.pfTime"/></td>
        	     <td>
        	      </td>             
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
		                  <h5 class="fl"><a href="#" class="colSelect fl">部门工作列表</a></h5>
		             &nbsp;<input type="button" name="addButton" id="addButton" value="新 增" class="fr">
		            <input type="button" name="excelButton" id="excelButton" value="导 出当页至 EXCEL" class="fl">
		              &nbsp;<input type="button" name="excelAllButton" id="excelAllButton" value="导 出全部至 EXCEL" class="fl">
		            </div>
		      </div>


      
        <!--Filter End-->
        <!--Table-->
        <div class="mb10" id="listDiv">
        	<table width="100%"  class="table_1" id="mytable">
        				<thead>
                              <tr class="tit">
                                <!-- <td><input type="checkbox" id="test_checkbox_1" name="test_checkbox_1" /></td>-->
                                <th class="t_c">序号</th>
                                <th class="t_c">督办工作</th>
                                 <th class="t_c">最新状态</th>
                                 <th class="t_c">开始时间</th>
                                <th class="t_c">要求完成时间</th>
                                 <th class="t_c">进展标志</th>
                                <th class="t_c">完成状态</th>
                                <th class="t_c">责任人</th>
                                <th class="t_c">分管领导</th>
                                <s:if test='#session.userName=="孔琰" 
                                || #session.userName=="李名敏" 
                                || #session.userName=="金涛"
                                || #session.userName=="黄天印"'>
                                <th class="t_c">编辑</th>
                                </s:if>
                                <th class="t_c">查看</th>
                                </tr>
                              
                              </thead>
                              <tbody>
                             
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="11"><div class="clearfix">
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
                                <option value="-1">显示全部</option>
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