<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>有效文件管理</title>
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
		<link type="text/css" href="css/flick/jquery-ui-1.10.3.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="js/jquery-ui-1.10.3.custom.min.js"></script>
		<script type="text/javascript" src="../js/flick/jquery.ui.datepicker-zh-CN.js"></script>
        <script type="text/javascript" src="js/page.js"></script>
        <script type="text/javascript" src="js/print.js"></script>
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
				temp += "<td class='t_c'>"+$.notNull(l[i].SENDCODE) +"</td>";
				temp += "<td class='t_c'>"+$.notNull(l[i].SENDDATE) +"</td>";
				temp += "<td class='t_l'>"+$.notNull(l[i].TITLE) +"</td>";
				temp += "<td class='t_c'>"+$.notNull(l[i].ADDPERSON) +"</td>";
				temp += "<td class='t_c'>"+$.notNull(l[i].SENDDEPT) +"</td>";
				temp += "<td class='t_c'>"+$.notNull(l[i].STATUS) +"</td>";
				temp += "<td class='t_l'>"+($.notNull(l[i].REMARK).length>0?"<img alt='附件' src='<%=path%>/operation/ui/images/fj.gif'/>":"") +"</td>";
				<s:if test='#session.userName=="蔡伟东" || 
		        	#session.userName=="朱英" ||
		        	#session.userName=="陈建东" ||
		        	#session.userName=="忻然"'>
		    	
				temp += "<td class='t_c'>"+
				"<a class='showForm' href='javascript:void(0);' " +
				"mainId='"+$.notNull(l[i].MAINID)+"'>表单</a></td>";
				temp += "<td class='t_c'>" +
				"<a class='showReceipt' href='javascript:void(0);' " +
				"mainId='"+$.notNull(l[i].MAINID)+"'>回执</a></td>";
				temp += "<td class='t_c'>"+
				"<a class='showRemark' href='javascript:void(0);' " +
				"mainId='"+$.notNull(l[i].ID)+"'>备注</a></td>";

		    	</s:if>
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
		if(index == 0){
			$('#mytable').tablesorter({headers:{ 0: { sorter: false}}});
			index ++;
		}else{
			$('#mytable').trigger("update"); 
		}
		$.unblockUI();
		return false;
	}
	
		
		var index = 0;
		var listOptions = {
			cache:false,
			dataType:'json',
			type:'post',
			callback:null,
			url:'/portal/validFileList/list.action?random='+Math.random(),
			error: function(XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
            },
		    success:listCallback
		};
		
		
	var handleOptions = {
			cache:false,
			type:'post',
			callback:null,
			url:'/portal/validFile/addRemark.action',
		    success:function(data){
					if(data=="1"){
						alert("添加成功");
						$("#handleForm").find("#remark").val("");
                        $("#handleForm").find("#attach").val("");
                        $("#handleForm").find("#attachFrame").attr("src",
                        '/portal/attachOld/loadFileOldList.action?fileGroup=attach&fileGroupName=attachGroup&fileGroupId=&userName=<s:property value="#session.userName"  escape="0"/>&loginName=<s:property value="#session.loginName"/>&procType=edit&targetType=frame&type=1');
						$('#remarkZone').dialog("close");
						list();
					}else{
						alert("添加失败！");
						$('#remarkZone').dialog("close");
					}
				return false;
		    }
		};
	
	$(function(){
		$("#invalidButton").click(function(){
			var chks = "";
			var status = $("#normativeStatus").val();
			$(".clk:checked").each(function(){
				chks += "'"+$(this).val()+"',";
			})
			if(chks == ""){
				alert("请选择文件！");
			}else{
				chks = chks.substr(0,(chks.length)-1);
				$.post(
						'/portal/validFile/setInvalid.action?random='+Math.random(),
						{
							"mainId": 	chks,
							 "status": status
						},
						function(obj, textStatus, jqXHR){
							if(obj=="1"){
								alert("操作成功!");
								list();
							}
						},
						"text"
					).error(function() { alert("服务器连接失败，请稍后再试!"); })
			}
		});
		
		$(document).on("click","#checkboxAll",function(){
			//alert($(this).prop("checked"));
			$("tbody").find("input[type=checkbox]").prop("checked",$(this).prop("checked"));
		});
		
		$(document).on("click",".clk",function(){
			 if($(".clk:checked").length != $(".clk").length){
				 $("#checkboxAll").prop("checked",false);
			 }else{
				 $("#checkboxAll").prop("checked",true);
			 }
		});
		
		$(document).on("click",".showForm",function(){
			window.open("http://10.1.48.16:8080/workflow/send-tDocSend/validFile.action?id="+$(this).attr("mainId"));
		});
		
		$(document).on("click",".showReceipt",function(){	
    		var mainId = $(this).attr("mainId");
    		var temp = "";
    		$.post(
					'/portal/validFile/getReceipt.action?random='+Math.random(),
					{
						"mainId": 	mainId
					},
					function(obj, textStatus, jqXHR){
						if(obj !=null && obj.length>0){
							for(var i=0;i<obj.length;i++){
								temp +="<tr><td style='border:1px solid black;'>"+$.notNull(obj[i].CODE)+"</td>"+
									"<td style='border:1px solid black;'>"+$.notNull(obj[i].DEPT)+"</td>"+
									"<td style='border:1px solid black;'>"+$.notNull(obj[i].PERSON)+"</td>"+
									"<td style='border:1px solid black;'>"+$.notNull(obj[i].STATUS)+"</td>"+
									"<td style='border:1px solid black;'>"+$.notNull(obj[i].READTIME)+"</td></tr>";
							}
							$("#title_sendid").html($.notNull(obj[0].SENDID));
							$("#title_senddate").html($.notNull(obj[0].SENDDATE));
							$("#title_title").html($.notNull(obj[0].TITLE));
							$("#receiptBody").html(temp);
						}
						$( "#receiptZone" ).dialog( "open" );
					},
					"json"
				).error(function() { alert("服务器连接失败，请稍后再试!"); })
    		});
    	
	    	$("#receiptZone").dialog({
				modal: true,
				autoOpen: false,
				width: 800,
				height: 500,
				zIndex: 9999,
				buttons: [
					{
						text: "打印",
						click: function() {
							window.open("printReceipt.jsp");
						}
					},
					{
						text: "取消",
						click: function() {
							//$("#form").submit();
							$( this ).dialog( "close" );
						}
					}
				],
				close: function(event, ui) {}
			});
	    	
	    	$(document).on("click",".attachTd",function(){ 
	    		window.open("/portal/attachOld/loadFileOldList.action?fileGroup=attach&fileGroupName=attachGroup&fileGroupId="+$(this).attr("groupId")+"&userName=&loginName=&procType=view&targetType=frame&type=1");
	    	})
	    	
	    	$(document).on("click",".showRemark",function(){	
	    		var mainId = $(this).attr("mainId");
	    		$.post(
						'/portal/validFile/getRemark.action?random='+Math.random(),
						{
							"mainId": 	mainId
						},
						function(obj, textStatus, jqXHR){
							if(obj !=null && obj.length>0){
							 var rows = "";
							 rows +="<tr>";
                             rows += "<td style=\"width:50%;\">备注内容</td>";
                             rows += "<td style=\"width:10%;\">备注用户</td>";
                             rows += "<td style=\"width:15%;\">备注时间</td>";
                             rows += "<td style=\"width:10%;\">附件</td>";
                             rows += "</tr>";
								for(var i =0;i<obj.length;i++){
									rows += "<tr>";
									rows += "<td>"+$.notNull(obj[i].REMARK)+"</td>";
									rows += "<td>"+$.notNull(obj[i].PERSON)+"</td>";
									rows += "<td>"+$.notNull(obj[i].TIMES)+"</td>";
									var attachTemp = $.notNull(obj[i].ATTACH);
									if(attachTemp.length > 0){
										rows += "<td style=\"cursor:pointer;\" class=\"attachTd\" groupId=\""+$.notNull(obj[i].ATTACH)+"\"><img alt='附件' src='<%=path%>/operation/ui/images/fj.gif'/></td>";
									}else{
										rows += "<td></td>";
									}
									rows += "</tr>";
									
								}
								$("#remarkNow").html(rows);
							}
							$("#handleForm").find("#mainId").val(mainId);
							$( "#remarkZone" ).dialog( "open" );
						},
						"json"
					).error(function() { alert("服务器连接失败，请稍后再试!"); })
	    		});
	    	
	    	$("#remarkZone").dialog({
				modal: true,
				autoOpen: false,
				width: 630,
				height: 510,
				zIndex: 9999,
				buttons: [
					{
						text: "确认",
						click: function() {
							if($("#remark").val()!=""){
								$("#handleForm").ajaxSubmit(handleOptions);
							}else{
								alert("请输入备注!");
							}
							
						}
					},
					{
						text: "取消",
						click: function() {
							$( this ).dialog( "close" );
						}
					}
				],
				close: function(event, ui) {}
			});
	    	
	    	
	 		
	})
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
		<jsp:include page="remark.jsp"></jsp:include>
		<jsp:include page="receipt.jsp"></jsp:include>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
			<div class="fl"><img id="show" onclick="showHide();" src="../css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li class="fin">文件列表</li>
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
        	     <td class="t_r">文件字号</td>
        	     <td>
        	     <input type="text" id="sendcode_like" name="sendcode_like" class="input_xlarge"/>
        	     </td>
        	     <td class="t_r">文件标题</td>
        	     <td>
        	      <input type="text" id="title_like" name="title_like" class="input_xlarge"/>
        	      </td>
        	     <td class="t_r">文件状态</td>
        	     <td>
        	       <select name="status_in" id="status_in" class="input_large" > 
                    <option value="">全部</option>
                    <option value="有效,部分有效"  selected="selected">有效+部分有效</option>
                    <option value="有效">有效</option>
                    <option value="部分有效">部分有效</option>
                    <option value="失效">失效</option>
                    <option value="废止">废止</option>
                    </select>	
        	      </td>
      	      	</tr> 
      	      	<tr>
        	     <td class="t_r">发文日期</td>
        	     <td>
        	     <input readonly="readonly" type="date" id="senddate_startDate" name="senddate_startDate" value=""/>
        	      	至
        	     <input readonly="readonly" type="date" id="senddate_endDate" name="senddate_endDate" value=""/>
        	      
        	      </td>
        	     <td class="t_r">制定部门/人</td>
        	     <td>
        	      <input type="text" id="addperson_like" name="addperson_like" class="input_xlarge"/>
        	     </td>
        	      <td class="t_r">发送部门</td>
        	     <td>
        	     <input type="text" id="senddpet_like" name="senddept_like" class="input_xlarge"/>
        	     </td>             
      	      	</tr> 
      	    	<tr>
        	     <td class="t_r">备注</td>
        	     <td>
        	     <input type="text" id="remark_like" name="remark_like" class="input_xxlarge"/>
        	     </td>
        	     <td class="t_r">字号类别</td>
        	     <td>
        	        <select name="code2_equal" id="code2_equal" class="input_middle" >
                        <option value="">全部</option>
                        <option value="2009">2009</option>
                        <option value="2010">2010</option>
                        <option value="2011">2011</option>
                        <option value="2012">2012</option>
                        <option value="2013">2013</option>
                        <option value="2014">2014</option>
                        <option value="2015">2015</option>
                        <option value="2016">2016</option>
                    </select>	
        	     	<select name="code1_equal" id="code1_equal" class="input_middle" > 
                        <option value="">全部</option>
                        <option value="沪地铁">沪地铁</option>
                        <option value="沪地铁办发">沪地铁办发</option>
                        <option value="会议">会议</option>
                    </select>	
        	    </td>
        	     <td class="t_r">
        	     	排序
        	     <td>
        	     		按时间降序<input checked="checked" type="radio" value="senddate desc" name = "order" id="order">
        	     	按文号降序<input type="radio" value="code3 asc" name = "order" id="order">
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
		                  <h5 class="fl"><a href="#" class="colSelect fl">文件列表</a></h5>
		                   <s:if test='#session.userName=="蔡伟东" || 
                                	#session.userName=="朱英" ||
                                	#session.userName=="陈建东" ||
                                	#session.userName=="忻然"'>                 	
		      <input style="margin-left:5px;" type="button" name="invalidButton" id="invalidButton" value="设置" class="fr">
			   
			    <select id = "normativeStatus" class="fr">
			      <option value="1">有效</option>
			      <option value="2" selected>部分有效</option>
			      <option value="3">失效</option>
			      <option value="4">废止</option>
			    </select>
		      </s:if>
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
                                <th style="white-space:nowrap;" class="t_c">文件字号</th>
                                 <th style="white-space:nowrap;" class="t_c">发文日期</th>
                                 <th style="white-space:nowrap;" class="t_c">文件标题</th>
                                <th style="white-space:nowrap;" class="t_c">制定部门/人</th>
                                 <th style="white-space:nowrap;" class="t_c">发送部门</th>
                                <th style="white-space:nowrap;" class="t_c">文件状态</th>
                                <th style="white-space:nowrap;" class="t_c">备注</th>
                                <s:if test='#session.userName=="蔡伟东" || 
                                	#session.userName=="朱英" ||
                                	#session.userName=="陈建东" ||
                                	#session.userName=="忻然"'>
                                <th style="white-space:nowrap;" colspan="3" class="t_c">操作</th>
                                </s:if>
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
