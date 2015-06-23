<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
	<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>
<meta charset="utf-8" />
<title>运营发文查询</title>
<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/default/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
<link type="text/css" href="../validFile/css/flick/jquery-ui-1.10.3.custom.css" rel="stylesheet" />
<!--[if IE 6.0]>
           <script src="../js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
        <script src="../js/html5.js"></script>
        <script src="../validFile/js/jquery-1.10.2.min.js"></script>
        <script src="../validFile/js/jquery-migrate-1.2.1.min.js"></script>
        <script src="../validFile/js/jquery.tablesorter.js"></script>
        <script src="../validFile/js/jquery.form.js"></script>
		<script src="../js/jquery.formalize.js"></script>
		<!--<script src="../js/switchDept.js"></script>-->
		<script src="../js/show.js"></script>
		<link type="text/css" href="../validFile/css/flick/jquery-ui-1.10.3.custom.css" rel="stylesheet" />
		<script type="text/javascript" src="../validFile/js/jquery-ui-1.10.3.custom.min.js"></script>
		<script type="text/javascript" src="../js/flick/jquery.ui.datepicker-zh-CN.js"></script>
        <script type="text/javascript" src="../validFile/js/print.js"></script>
	<script type="text/javascript">
        $(document).ready(function () {
        	$("#fileType").change(function(){
        		var fileType = $(this).val();
        		loadSubFileType(fileType);
        	}).val($('#_fileType_').val());
        	
        	$("#fileTypeSub").val($('#_fileTypeSub_').val());

            $("#sendStatus").val($('#_sendStatus_').val());
        	
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
			
	        $('#startDate').datepicker({
	    		inline: true,  
                changeYear: true,  
                changeMonth: true , 
                showButtonPanel:true,     
                closeText:'清除',   
                onSelect:
                function(selectedDate){
					$("#endDate").datepicker("option","minDate",selectedDate);
				},
                currentText:'ds'//仅作为“清除”按钮的判断条件  
	    	});
	    	$('#endDate').datepicker({
	    		inline: true,  
                changeYear: true,  
                changeMonth: true  ,  
                showButtonPanel:true,     
                closeText:'清除',
                onSelect:  
                function(selectedDate){
					$("#startDate").datepicker("option","maxDate",selectedDate);
				}, 
                currentText:'de'//仅作为“清除”按钮的判断条件  
	    	});
	        $('#startVDate').datepicker({
	    		inline: true,  
                changeYear: true,  
                changeMonth: true , 
                showButtonPanel:true,     
                closeText:'清除',   
                onSelect:
                function(selectedDate){
					$("#endVDate").datepicker("option","minDate",selectedDate);
				},
                currentText:'vds'//仅作为“清除”按钮的判断条件  
	    	});
	    	$('#endVDate').datepicker({
	    		inline: true,  
                changeYear: true,  
                changeMonth: true  ,  
                showButtonPanel:true,     
                closeText:'清除',
                onSelect:  
                function(selectedDate){
					$("#startVDate").datepicker("option","maxDate",selectedDate);
				}, 
                currentText:'vde'//仅作为“清除”按钮的判断条件  
	    	});
	    	$(".ui-datepicker-close").live("click", function (){
	            if($(this).parent("div").children("button:eq(0)").text()=="ds") $("#startDate").val("");
	            if($(this).parent("div").children("button:eq(0)").text()=="de") $("#endDate").val("");
	            if($(this).parent("div").children("button:eq(0)").text()=="vds") $("#startVDate").val("");
	            if($(this).parent("div").children("button:eq(0)").text()=="vde") $("#endVDate").val("");	            
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
							window.open("../operation/docSend/printReceipt.jsp");
						}
					}, 
					{
						text: "关闭",
						click: function() {
							//$("#form").submit();
							$( this ).dialog( "close" );
						}
					}
				],
				close: function(event, ui) {}
			});
        });
        
        function showReceipt(mainId){	
       		var temp = "";
       		$.post(
    					'/portal/opDocSend/getReceipt.action?random='+Math.random(),
    					{
    						"id": 	mainId
    					},
    					function(obj, textStatus, jqXHR){
    						if(obj !=null){
    							var results = obj.results;
    							if(results){
        							for(var i = 0; i<results.length; i++){
        								temp +="<tr><td style='border:1px solid black;text-align:center;'>"+(i+1)+"</td>"+
    									"<td style='border:1px solid black;'>"+results[i].dept+"</td>"+
    									"<td style='border:1px solid black;'>"+results[i].receiver+"</td>"+
    									"<td style='border:1px solid black;'>"+results[i].isread+"</td>"+
    									"<td style='border:1px solid black;'>"+results[i].readtime+"</td></tr>";
        							}    								
    							}
     							$("#title_sendid").html(obj.fileCode);
    							$("#title_senddate").html(obj.cTime);
    							$("#title_title").html(obj.docTitle);
    							$("#receiptBody").html(temp);
    						}
    						$( "#receiptZone" ).dialog( "open" );
    					},
    					"json"
    				).error(function() { alert("服务器连接失败，请稍后再试!"); })
       	};
        
        function loadSubFileType(pid){
        	$("#sub_filetype").empty();
        	$('.fileTypeSub').remove();
        	if(!pid){
        		return;
        	}
        	$.ajax({
        		type : 'post',
        		url : 'subFileTypeList.action?random='+Math.random(),
        		dataType:'json',
        		cache : false,
        		data:{
        			pid : pid
        		},
        		error:function(){
        			alert("系统连接失败，请稍后再试！")
        		},
        		success:function(data){
        			var list = $(data);
        			if(list.length > 0){
        				var html = '— ';
        				html += '<select name="fileTypeSub" class="validate[required] fi" id="fileTypeSub"><option value="">--请选择文件分类--</option>';
        				list.each(function(){
        					html += '<option value="'+this.id+'">' + this.name + '</option>';
        				});
        				html += '</select>';
        				$("#sub_filetype").html(html);				
        			}
        		}
        	});
        }
       
       //跳转到制定页
       function goPage(pageNo,type){
       
       		//type=0,直接跳转到制定页
	       if(type=="0"){
	   	    	var pageCount = $("#totalPageCount").val();
	       		var number = $("#number").val();
	       		if(!number.match(/^[0-9]+$/)){
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
	       }
			//type=1,跳转到上一页	       
	       if(type=="1"){
	       		$("#pageNo").val(parseInt($("#number").val())-1);
	       }
			//type=2,跳转到下一页	       
	       if(type=="2"){
	       		$("#pageNo").val(parseInt($("#number").val())+1);
	       }
	       
	       //type=3,跳转到最后一页,或第一页
	       if(type=="3"){
	   	    	$("#pageNo").val(pageNo);
	       }
       	   $("#form").submit();
       }
       
       function clearInput(){
    	   $('.fi').val('');
       }
       
		var listCallback = function(data){
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
       
		function getContent(){
			var htmlContent = $("#mytable thead").html();
			$(".clk:checked").parent("td").parent("tr").each(function(i,obj){
				htmlContent += '<tr  class="list-td1" height="22" >';
				htmlContent += $(obj).html();
				htmlContent += "</tr>";
			})
			//alert(htmlContent);
			return htmlContent;
		}
		function getTitle(){
			return $("#printTitle").val();
		}

		function getReceiptContent(){
			var htmlContent = $("#receiptTable").html();
			return htmlContent;
		}
		function getReceiptTitle(){
			var htmlContent = $("#titleTable").html();
			return htmlContent;
		}
    </script>

	<style>
		div.jqi{ width:770px}
		.ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }	
	</style>

</head>

<body>
<div id="domMessage" style="display:none;"> 
    <h1>请稍后</h1> 
</div> 	
	<jsp:include page="receipt.jsp"></jsp:include>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
			<div class="fl"><img id="show" onclick="showHide();" src="../css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li><a href="#">运营管理</a></li>
                	<li class="fin">运营发文查询</li>
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
      <div style="padding-top: 35px;">
<%--         	<s:form action="list" id="form" method="post"  namespace="/examManage">
        	
      	    </s:form>       --%>
        <!--Filter-->
      <div class="filter">
	        	<div class="query">
	        	<div class="p8 filter_search">
      	    <s:form action="list" id="form" method="post"  namespace="/opDocSend" theme="simple">
	        	<input type="hidden"  value="" name="page" id="pageNo"/>
	        	<input type="hidden"  value="<s:property value="#request.g"/>" name="g"/>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr id="search1">
								<td class="t_r" style="white-space:nowrap" width="10%">文件编号</td>
								<td style="white-space:nowrap" width="20%">
									<input type="text" name="fileCode"  class="input_xlarge fi" value="<s:property value='#request.fileCode'/>">
								</td>
								<td class="t_r" style="white-space:nowrap">文件分类</td>
								<td style="white-space:nowrap" colspan="3">
	                            	    	<span style="width: 10%; margin-right: 2px;display:inline">
							    			<select name="fileType" class="validate[required] fi" id="fileType">
							    				<option value="">--请选择文件分类--</option>
												<s:iterator value="#request.fileTypes">
													<option value="<s:property value='id'/>"><s:property value='name'/></option>
												</s:iterator>							    				
											</select>
											</span>
											<span style="width: 55%; margin-right: 2px; display:inline" id="sub_filetype">
												<s:if test="#request.fileSubTypes!=null">
								    			— <select name="fileTypeSub" class="validate[required] fi" id="fileTypeSub">
								    				<option value="">--请选择文件分类--</option>
													<s:iterator value="#request.fileSubTypes">
														<option value="<s:property value='id'/>"><s:property value='name'/></option>
													</s:iterator>							    				
												</select>		
												</s:if>									
											</span>
								</td>

							</tr>
							<tr id="search2">
								<td class="t_r" style="white-space:nowrap">文件名称</td>
								<td style="white-space:nowrap">
									<input type="text" name="docTitle"  class="input_xlarge fi" value="<s:property value='#request.docTitle'/>" />
								</td>
								<td class="t_r" style="white-space:nowrap">申请部门</td>
								<td style="white-space:nowrap">
									<input type="text" name="deptName"  class="input_xlarge fi" value="<s:property value='#request.deptName'/>" >
								</td>
								<td class="t_r" style="white-space:nowrap">有效日期</td>
								<td style="white-space:nowrap">
									<input type="text" id="startVDate" name="startVDate" class="input_large date fi" value="<s:property value='#request.startVDate'/>" readonly="readonly"/> -
									<input type="text" id="endVDate" name="endVDate" class="input_large date fi" value="<s:property value='#request.endVDate'/>" readonly="readonly"/>
								</td>								
							</tr>
							<tr id="search2">
								<td class="t_r" style="white-space:nowrap">申请日期</td>
								<td style="white-space:nowrap">
									<input type="text" id="startDate" name="startDate" class="input_large date fi" value="<s:property value='#request.startDate'/>" readonly="readonly"/> -
									<input type="text" id="endDate" name="endDate" class="input_large date fi" value="<s:property value='#request.endDate'/>" readonly="readonly"/>
								</td>
                                <td  style="white-space:nowrap" class="t_r"  >文件状态</td>
                                <td style="white-space:nowrap">
                                    <select name="sendStatus" id="sendStatus">
                                        <option value="">--请选择文件状态--</option>
                                        <option value="0">过期</option>
                                        <option value="1">使用</option>
                                    </select>
                                </td>
							</tr>							
							<tr>
								<td colspan="6" class="t_c">
				                  	<input type="submit" value="检 索" />&nbsp;&nbsp;
				                  	<input type="button" value="重 置" onclick="clearInput()"/></td>
								</td>
							</tr>
						</table>
					</s:form>	        	
<%-- 	        	<s:form action="list.action" id="form" method="post"  namespace="/examManage">
	        	<input type="hidden"  value="" name="page" id="pageNo"/>
	        	<input type="hidden"  value="<s:property value="#request.g"/>" name="g"/>
	        	 <table width="100%" border="0" cellspacing="0" cellpadding="0">
	        	    <tr>
	        	      <td colspan="6" class="t_r">
	        	      	文件名称 : <input type="text" value="<s:property value="#request.docTitle"/>" name="docTitle" id="docTitle"/>
	                  	<input type="submit" value="检 索" />&nbsp;&nbsp;
	                  	<input type="button" value="重 置" onclick="clearInput()"/></td>
					</tr>
	      	    </table>
	      	    </s:form> --%>
	      	    </div>
	        	</div>        
       				<div class="fn clearfix">
		                  <h5 class="fl"><a href="#" class="colSelect fl">运营发文查询</a></h5>
		            </div>
		</div>

        <!--Filter End-->
        <!--Table-->
        <div class="mb10">
        	<table width="100%"  class="table_1">
                              <tbody>
                              <tr class="tit">
                                <!-- <td><input type="checkbox" id="test_checkbox_1" name="test_checkbox_1" /></td>-->
                                <td class="t_c" width="3%">序</td>
                                <td class="t_c" width="7%">发文日期</td>
                                <td class="t_c" width="10%">文件编号</td>
                                <td class="t_c" width="7%">发布日期</td>
                                <td class="t_c" width="7%">有效日期</td>
                                <td class="t_c" width="20%">发放范围</td>
                                <td class="t_c" width="20%">文件名称</td>
                                <td class="t_c" width="10%">备注</td>
                                <td class="t_c" width="5%">状态</td>
                                <td class="t_c" width="5%">签收</td>
                                <td class="t_c">表单</td>
                                </tr>
                              <s:iterator value="#request.pageResultSet.list" id="data" status="s">
                              <tr>
                               	<td class="t_c"><s:property value="#s.index+1"/></td>
                                <td class="t_c"><s:property value="#data[1]"/></td>
                                <td class="t_c"><s:property value="#data[2]"/></td>
                                <td class="t_c"><s:property value="#data[3]"/></td>
                                <td class="t_c"><s:property value="#data[4]"/></td>
                                <td class="t_c"><s:property value="#data[6]"/><%-- <s:if test="#data[7]!=null">,<s:property value="#data[7]"/></s:if> --%></td>
                                <td class="t_c"><s:property value="#data[5]"/></td>
                                <td class="t_c"><div style="width:120px; overflow:hidden; white-space:nowrap; text-overflow:ellipsis"><s:property value="#data[8]"/></div></td>
                                <td class="t_c"><s:property value="#data[11]==1?'使用':'过期'"/></td>
                                <td class="t_c">
                                	<s:if test="#data[10]!=-1">
                                	<a class="mr5" href="javascript:showReceipt('<s:property value="#data[0]"/>')"><s:property value="#data[10]"/>/<s:property value="#data[9]"/></a>
                                	</s:if>
                                </td>
                                <td class="t_c"> 
                                <a class="mr5" href="view.action?id=<s:property value="#data[0]"/>&deal=n" target="_blank">查看</a>
                                </td>
                                </tr>
                                </s:iterator>
                              </tbody>
                              <tr class="tfoot">
                              	<s:set name="start" value="(#request.pageResultSet.pageInfo.currentPage-1)*#request.pageResultSet.pageInfo.pageSize + 1"/>
                                <td colspan="10"><div class="clearfix"><span class="fl"><s:property value="#request.pageResultSet.pageInfo.totalRow"/>条记录，当前显示<s:property value="#start"/>-<s:property value="#start+#request.pageResultSet.list.size-1"/>条</span>
                           		<ul class="fr clearfix pager">
		                             <li>Pages:<s:property value="#request.pageResultSet.pageInfo.currentPage"/>/<s:property value="#request.pageResultSet.pageInfo.totalPage"/>
		                             			<input type="hidden" value="<s:property value='#request.pageResultSet.pageInfo.totalPage'/>" id="totalPageCount">
			                                  <input type="text" id="number" name="pageNumber" min="0" max="999" step="1" class="input_tiny" value="<s:property value='#request.pageResultSet.pageInfo.currentPage'/>"/>
			                                  <input type="button" name="button" id="button" value="Go" onclick="goPage(0,0)">
		                             </li>
                             
		                             <s:if test="#request.pageResultSet.pageInfo.currentPage==#request.pageResultSet.pageInfo.totalPage">
		                             <li><a href="javascript:void(0)">&gt;&gt;</a></li>
		                             </s:if>
		                             <s:else>
		                              <li><a href="javascript:void(0)" onclick="goPage(<s:property value='#request.pageResultSet.pageInfo.totalPage'/>,3)">&gt;&gt;</a></li>
		                             </s:else>
                              
	                             	<li>
	                             	<s:if test="#request.pageResultSet.pageInfo.currentPage==#request.pageResultSet.pageInfo.totalPage">	
	                             		<a href="javascript:void(0)">下一页</a>
	                             	</s:if>
	                             	<s:else>
	                             		<a href="javascript:void(0)" onclick="goPage(<s:property value='#request.pageResultSet.pageInfo.currentPage'/>,2)">下一页</a>
	                             	</s:else>
	                             	</li>
	                             	<li>
	                             	<s:if test="#request.pageResultSet.pageInfo.currentPage==1">
	                             		<a href="javascript:void(0)">上一页</a>
	                             	</s:if>
	                             	<s:else>
	                             		<a href="javascript:void(0)" onclick="goPage(<s:property value='#request.pageResultSet.pageInfo.currentPage'/>,1)">上一页</a>
	                             	</s:else>
	                             	
	                             	</li> 
	                             
	                             	<s:if test="#request.pageResultSet.pageInfo.currentPage==1">
	                             	<li><a href="javascript:void(0)">&lt;&lt;</a></li>
	                             	</s:if>
	                             	<s:else>
	                             		<li><a href="javascript:void(0)" onclick="goPage(1,3)">&lt;&lt;</a></li>
	                             	</s:else>
	                             
                                
                            </ul>
                        </div>
                        							<input type="hidden" id="_fileType_" value="<s:property value='#request.fileType'/>"/>
							<input type="hidden" id="_fileTypeSub_" value="<s:property value='#request.fileTypeSub'/>"/>
                                    <input type="hidden" id="_sendStatus_" value="<s:property value='#request.sendStatus'/>"/>
                                </td>
                              </tr>
                            </table>

      </div>
        <!--Table End-->
</div>
</div>
</body>
</html>