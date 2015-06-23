<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>已办事项查询</title>
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
		<script src="../js/jquery.formalize.js"></script>
		<!--<script src="../js/switchDept.js"></script>-->
		<script src="../js/show.js"></script>
		<link type="text/css" href="../css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		<script src="../js/flick/jquery-ui-1.8.18.custom.min.js"></script>
		<script src="../js/flick/jquery.ui.datepicker-zh-CN.js"></script>
		<script src="/portal/todoItem/js/jobcontact.js"></script>
		<script src="/portal/validFile/js/jquery.blockUI.js"></script>
		<style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
        a img{margin:0 auto;}
        #jobContactInfo {display:none;position:absolute;width:380px;height:auto;}
        #jobContactCuiban {display:none;position:absolute;width:150px;height:auto;}
		.hoverTable {border:0px solid #DADCD3;background:#FBFACC;width:100%;cellpadding:0px; cellspacing:0px;text-align:center;border-collapse:collapse;}
		.hoverTable tr td {width:0%;border:1px solid #DADCD3;text-align:middle;padding:2px;}
		.title{ font-size:12px; color:#931602;  font-weight:bold;text-align:center;display:inline;}
		.summary{display:block;cursor:pointer;}
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
       	$.blockUI({ message: $('#domMessage') });
        }
      

		
		function ifNewJobContact(obj){
			var targetTr = obj.parent("td").parent("tr");
			var summaryDiv = $(targetTr).find(".summary");
			//console.log(summaryDiv);
			if(summaryDiv.length>0){
				return true;
			}
			return false;
		}
		
		function cuibanNew(inci){
			var processname = encodeURI("工作联系单");
			var incident = encodeURI(inci);
			var url = "http://10.1.48.19:8088/sendMsgPerson.action?processName="+processname+"&incident="+incident+""+"&num="+Math.random();
			window.open(url,"c","width=470px,height=325px");	
			return false;
		}
	
		
        $(document).ready(function () {
        	$(":radio[name='days'][value='<s:property value="days"/>']").attr("checked",true);
        	
        		
        	$(":radio[name='days']").click(function(){
        		if($(this).val() == "0"){
        			
        		}else{
        			$("#starttimes").val("");
        			$("#starttimee").val("");
        			$("#endtimes").val("");
        			$("#endimee").val("");
        		}
        		
        		$("#form").submit();
				$.blockUI({ message: $('#domMessage') });
        	});
        	
        	$(":text[id*=time]").change(function(){
        		if($(this).val()!=""){
        			$(":radio[name='days'][value='0']").click();
        		}
        	})
        	
			$("#sb").click(function(){
				$("#form").submit();
				$.blockUI({ message: $('#domMessage') });
			});
        	
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
					
	        var status = "<s:property value='status'/>";
			if(status!=""){
				$("form:first").find("select>option[value='"+status+"']").attr("selected",true);
			}
        
	        $('#starttimes').datepicker({
	    		inline: true,  
                changeYear: true,  
                changeMonth: true , 
                showButtonPanel:true,     
                closeText:'清除',   
                currentText:'ss'//仅作为“清除”按钮的判断条件  
	    	});
	    	$('#starttimee').datepicker({
	    		inline: true,  
                changeYear: true,  
                changeMonth: true  ,  
                showButtonPanel:true,     
                closeText:'清除',
                currentText:'se'//仅作为“清除”按钮的判断条件  
	    	});
	    	$('#endtimes').datepicker({
	    		inline: true,  
                changeYear: true,  
                changeMonth: true   , 
                showButtonPanel:true,     
                closeText:'清除',  
                currentText:'es'//仅作为“清除”按钮的判断条件  
	    	});
	    	$('#endtimee').datepicker({
	    		inline: true,  
                changeYear: true,  
                changeMonth: true   , 
                showButtonPanel:true,     
                closeText:'清除',   
                currentText:'ee'//仅作为“清除”按钮的判断条件  
	    	});
		
			$(".ui-datepicker-close").live("click", function (){              
              if($(this).parent("div").children("button:eq(0)").text()=="ss") $("#starttimes").val("");
              if($(this).parent("div").children("button:eq(0)").text()=="se") $("#starttimee").val("");     
              if($(this).parent("div").children("button:eq(0)").text()=="es") $("#endtimes").val("");
              if($(this).parent("div").children("button:eq(0)").text()=="ee") $("#endtimee").val("");        
              });
		
		
		$(".todoUrl").click(function(){
			var pname = $("#processname",$(this).parents("td")).val();
			var url = "";
			if(pname=="纪委收文流程"){
				url = "http://10.1.48.16:8080/" +
                "workflow/discipline-dcpRecvMain/forward.action?" +
                "pname="+encodeURI($("#processname",$(this).parents("td")).val())+
                "&pincident="+$("#incident",$(this).parents("td")).val()+
                "&cname="+encodeURI($("#cname",$(this).parents("td")).val())+
                "&cincident="+$("#cincident",$(this).parents("td")).val()+
                "&taskid="+$("#groupid",$(this).parents("td")).val()+"&viewType=1";
			}else if(pname=="多级工作联系单"){	
    			url = "http://10.1.48.16:8080/workflow/contact-deptContact/viewForward.action?"
				+"id="+$("#groupid",$(this).parents("td")).val()+"&"
				+"rand="+Math.random();
    		}else if(pname=="新发文流程" || pname=="新党委发文流程"||pname=="新纪委发文流程"){
    			url = "http://10.1.48.16:8080/" +
    			"workflow/send-tDocSend/toFormPage.action?" +
    			"modelName="+encodeURI($("#processname",$(this).parents("td")).val())+
				"&incidentNo="+$("#incident",$(this).parents("td")).val()+
				"&processName="+encodeURI($("#cname",$(this).parents("td")).val())+
				"&pinstanceId="+$("#cincident",$(this).parents("td")).val()+
    			"&taskId="+$("#groupid",$(this).parents("td")).val();
    		}else if(pname=="新收文流程"){
    			url = "http://10.1.48.16:8080/" +
    			"workflow/receive-recvMain/forward.action?" +
    			"pname="+encodeURI($("#processname",$(this).parents("td")).val())+
				"&pincident="+$("#incident",$(this).parents("td")).val()+
				"&cname="+encodeURI($("#cname",$(this).parents("td")).val())+
				"&cincident="+$("#cincident",$(this).parents("td")).val()+
    			"&taskid="+$("#groupid",$(this).parents("td")).val()+"&viewType=1";
    		}else if(pname=="新收呈批件"){
    			url = "http://10.1.48.16:8080/" +
    			"workflow/receive-redvMain/forward.action?" +
    			"pname="+encodeURI($("#processname",$(this).parents("td")).val())+
				"&pincident="+$("#incident",$(this).parents("td")).val()+
				"&cname="+encodeURI($("#cname",$(this).parents("td")).val())+
				"&cincident="+$("#cincident",$(this).parents("td")).val()+
    			"&taskid="+$("#groupid",$(this).parents("td")).val()+"&viewType=1";
    		}else if(pname=="合同后审流程"){
    			url = "http://10.1.48.16:8080/" +
    			"workflow/contract-reviewMain/forward.action?" +
    			"pname="+encodeURI($("#processname",$(this).parents("td")).val())+
				"&pincident="+$("#incident",$(this).parents("td")).val()+
				"&cname="+encodeURI($("#cname",$(this).parents("td")).val())+
				"&cincident="+$("#cincident",$(this).parents("td")).val()+
    			"&taskid="+$("#groupid",$(this).parents("td")).val()+"&viewType=1";
    		}else if(pname=="部门内部审阅"){
    			url = "http://10.1.48.16:8080/" +
    			"workflow/dept-passMain/forward.action?" +
    			"pname="+encodeURI($("#processname",$(this).parents("td")).val())+
				"&pincident="+$("#incident",$(this).parents("td")).val()+
				"&cname="+encodeURI($("#cname",$(this).parents("td")).val())+
				"&cincident="+$("#cincident",$(this).parents("td")).val()+
    			"&taskid="+$("#groupid",$(this).parents("td")).val()+"&viewType=1";
    		}else if(pname=="项目销项流程"){
                url = "http://10.1.48.16:8080/" +
                        "workflow/project-discardMain/forward.action?" +
                        "pname="+encodeURI($("#processname",$(this).parents("td")).val())+
                        "&pincident="+$("#incident",$(this).parents("td")).val()+
                        "&cname="+encodeURI($("#cname",$(this).parents("td")).val())+
                        "&cincident="+$("#cincident",$(this).parents("td")).val()+
                        "&taskid="+$("#groupid",$(this).parents("td")).val()+"&viewType=1";
            }else if(pname=="新合同审批流程"){
                url = "http://10.1.48.16:8080/" +
                        "workflow/contractApproval-contractMain/forward.action?" +
                        "pname="+encodeURI($("#processname",$(this).parents("td")).val())+
                        "&pincident="+$("#incident",$(this).parents("td")).val()+
                        "&cname="+encodeURI($("#cname",$(this).parents("td")).val())+
                        "&cincident="+$("#cincident",$(this).parents("td")).val()+
                        "&taskid="+$("#groupid",$(this).parents("td")).val()+"&viewType=1";
            }else if(pname=="新项目立项流程"){
                url = "http://10.1.14.28:8080/" +
                        "xmlx/projectapp-pclMain/loadXMLX.action?" +
                        "modelName="+encodeURI($("#processname",$(this).parents("td")).val())+
                        "&incident="+$("#incident",$(this).parents("td")).val()+
                        "&taskid="+$("#groupid",$(this).parents("td")).val()+"&viewType=1";
            }else if(pname=="变更事项审批流程"){
                url = "http://10.1.48.16:8080/" +
                        "workflow/contractsxChange-reviewMain/forward.action?" +
                        "pname="+encodeURI($("#processname",$(this).parents("td")).val())+
                        "&pincident="+$("#incident",$(this).parents("td")).val()+
                        "&cname="+encodeURI($("#cname",$(this).parents("td")).val())+
                        "&cincident="+$("#cincident",$(this).parents("td")).val()+
                        "&taskid="+$("#groupid",$(this).parents("td")).val()+"&viewType=1";
            }else if(pname=="变更协议后审核流程"){
                url = "http://10.1.48.16:8080/" +
                        "workflow/contracthsChange-reviewMain/forward.action?" +
                        "pname="+encodeURI($("#processname",$(this).parents("td")).val())+
                        "&pincident="+$("#incident",$(this).parents("td")).val()+
                        "&cname="+encodeURI($("#cname",$(this).parents("td")).val())+
                        "&cincident="+$("#cincident",$(this).parents("td")).val()+
                        "&taskid="+$("#groupid",$(this).parents("td")).val()+"&viewType=1";
            }else if(pname=="变更协议审批流程"){
                url = "http://10.1.48.16:8080/" +
                        "workflow/contractChange-contractMain/forward.action?" +
                        "pname="+encodeURI($("#processname",$(this).parents("td")).val())+
                        "&pincident="+$("#incident",$(this).parents("td")).val()+
                        "&cname="+encodeURI($("#cname",$(this).parents("td")).val())+
                        "&cincident="+$("#cincident",$(this).parents("td")).val()+
                        "&taskid="+$("#groupid",$(this).parents("td")).val()+"&viewType=1";
            }else{
        		var flag = ifNewJobContact($(this));
        		if(flag){
        			var targetTr = $(this).parent("td").parent("tr");
					var summaryDiv = $(targetTr).find(".summary");		
					var groupid = $(summaryDiv).attr("groupid");

					url = "http://10.1.48.19:8088/ToOperateJob.action"
											+ "?groupId="+$("#groupid",$(this).parents("td")).val()
											+ "&modelName=" + encodeURI($("#processname",$(this).parents("td")).val())
											+ "&task_type=" + summaryDiv.attr("ptype")
											+ "&dbxtype=" + summaryDiv.attr("dbxtype");
        			
        		}else{
        			url = "http://10.1.48.19:8088/openflowform.action"
											+ "?task_id="
											+ $("#groupid",$(this).parents("td")).val()
											+ "&task_user_name="
											+ $("#initiator",$(this).parents("td")).val()
											+ "&model_id="
											+ encodeURI($("#processname",$(this).parents("td")).val())
											+ "&instance_id="
											+ $("#incident",$(this).parents("td")).val()
											+ "&step_name=aa"
											+ "&pinstance_id="
											+ $("#incident",$(this).parents("td")).val()
											+ "&processName="
											+ encodeURI($("#processName",$(this).parents("td")).val())
											+ "&task_type=1";
				}
    		}
        		window.open(url);
    		
        	})
        	
        	$(".todoScan").click(function(){
        		var pname = $("#processname",$(this).parents("td")).val();
        		var url = "";
        		if(pname=="多级工作联系单"){	
        			url = "http://10.1.48.16:8080/workflow/contact-ultimus/scanList.action?"+
        					"id="+$("#groupid",$(this).parents("td")).val()+"&"
        					+"rand="+Math.random();
        		}else{
	        		
		        	var flag = ifNewJobContact($(this));
					if(flag){
						var targetTr = $(this).parent("td").parent("tr");
						var summaryDiv = $(targetTr).find(".summary");
						var groupid = $(summaryDiv).attr("groupid");
						url ="http://10.1.48.19:8088/jobContact/newVersion/jobContactScan.jsp?groupId="+groupid;
						
					}else{
						url = "http://10.1.48.17/sLogin/workflow/TaskStatus.aspx"
			 									+ "?TaskID="
			 									+ $("#groupid",$(this).parents("td")).val();
					}
        		}
        			
		 		window.open(url);
        	})
		
			var navtimeoutcuibanid=null;
			
			$(".todoUrge").click(function(){
				var pname = $("#processname",$(this).parents("td")).val();
				if(pname=="多级工作联系单"||pname=="新收文流程"
						 ||pname=="新发文流程"||pname=="新收呈批件"
							 ||pname=="新党委发文流程"||pname=="新纪委发文流程"){	
					
				}else{
					var url = "";
					var flag = ifNewJobContact($(this));
					if(flag){
						var targetTr = $(this).parent("td").parent("tr");
						var summaryDiv = $(targetTr).find(".summary");		
						var groupid = $(summaryDiv).attr("groupid");
						var offset = $(this).offset();
			            $("#jobContactCuiban").css("left", offset.left-130);
			            $("#jobContactCuiban").css("top", offset.top+30);
			            $("#jobContactCuiban").html("<img src='/portal/todoItem/js/throbber.gif'></img>");
			            $("#jobContactCuiban").css('filter','Alpha(Opacity=90)');
					    $("#jobContactCuiban").slideDown(100);
					    jobConatctCuiBanHoverTableAjax($(summaryDiv).attr("groupid"),
						    $(summaryDiv).attr("ptype"),$(summaryDiv).attr("dbxtype"),$("#jobContactCuiban"),
						    "<s:property value='#session.cs_login_name'/>","<s:property value='#session.oldDeptId'/>","<s:property value='#session.userName'/>");
			
						$("#jobContactCuiban").bind({
							mouseenter: function(){
								if(navtimeoutcuibanid) clearTimeout(navtimeoutcuibanid);
							},
							mouseleave: function(){
								navtimeoutcuibanid=setTimeout(function(){
									$("#jobContactCuiban").slideUp(100);
									//$("#jobContactInfo").hide();
								    $("#jobContactCuiban").css('filter','');
								    $("#jobContactCuiban").css("left", "");
					                $("#jobContactCuiban").css("top", "");
								    $("#jobContactCuiban").html("");
							    },500);
							}
						});
						//console.log(offset);
				
						return false;
					}else{
						var status = $("#pstatus",$(this).parents("td")).val()
						if(status == 1){
							url = "http://10.1.48.19:8088/sendMsgPerson.action?"
												+"processName="+encodeURI($("#processname",$(this).parents("td")).val())
												+"&incident="+$("#incident",$(this).parents("td")).val()+""
												+"&num="+Math.random();
							window.open(url,"j","width=470px,height=325px");
						}else{
							alert("该流程已结束！")
						}					
					}
				}
			})
		
			$("#clearInput").click(function(){
	      		$("#processname").val("");
	      		$("#summary").val("");
	      		$("#starttimes").val("");
	      		$("#starttimee").val("");
	      		$("#endtimes").val("");
	      		$("#endtimee").val("");
	      		$("#username").val("");
	      		$("#deptname").val("");
	      		$("#status").val("");
			})
       
       		//jobContactLoad("G00100000161","2116","李名敏","1");
       		//jobContactLoad("G00100000161","2116","<s:property value='#session.userName'/>","1");
			jobContactLoad("<s:property value='#session.cs_login_name'/>","<s:property value='#session.oldDeptId'/>","<s:property value='#session.userName'/>","1");

       
      })
        
       
    </script>



</head>
<div id="domMessage" style="display:none;"> 
    <h1>请稍候</h1> 
</div> 	
<body>
	<div id="jobContactInfo" style="z-index:2;"></div>
	<div id="jobContactCuiban" style="z-index:2;"></div>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
			<div class="fl"><img id="show" onclick="showHide();" src="../css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li><a href="javascript:window.location.href='/portal/center/wdsw/wd_index.jsp'">我的事务</a></li>
                	<li class="fin">已办事项</li>
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
            	<li><a href="/portal/processInfo/findDocReceiveByPage.action" target="_blank"><span>收文查询</span></a></li>
            	<li><a href="/portal/processInfo/findDocSendByPage.action" target="_blank" ><span>发文查询</span></a></li>        			
        		<li><a href="/portal/processInfo/findDocDirectiveByPage.action" target="_blank"><span>呈批件查询</span></a></li>
        		<li><a href="/portal/contractManage/list.action" target="_blank"><span>合同查询</span></a></li>
        		<li><a href="/portal/processInfo/findJobContactByPage.action" target="_blank"><span>工作联系单查询</span></a></li>
        		<li><a href="/portal/processInfo/findDbByPage.action" target="_blank"><span>督办查询</span></a></li>
            </ul>
        </div>
	       <div class="fn clearfix" style="height:5px;background-color:#fff;"></div>
        <!--Filter-->
      <div class="filter">
        	<div class="query">
        	<div class="p8 filter_search">
        	<s:form action="doneItemListThisYear" id="form" method="post"  namespace="/done" theme="simple">
        	
        	<input type="hidden" name="page" id="page" value="<s:property value="page"/>"/>
        	
        	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
        	    <tr>
        	      <td class="t_r">流程名称</td>
        	      <td>
        	      <input type="text" id="processname" name="processname" class="input_large"  value="<s:property value="processname"/>"/>
        	      </td>
        	       <td class="t_r">摘要</td>
        	      <td>
        	      <input type="text" id="summary" name="summary" class="input_large" value="<s:property value="summary"/>"/>
        	      </td>
        	       <td class="t_r">开始时间</td>
        	       <td>
        	  		<input type="text" id="starttimes" name="starttimes" class="input_large date" value="<s:property value='starttimes'/>" readonly="readonly"/>
                    -
                    <input type="text" id="starttimee" name="starttimee" class="input_large date" value="<s:property value='starttimee'/>" readonly="readonly" />
                   </td>
                 </tr>
                 <tr>
                  <td class="t_r">申请人</td>
        	      <td>
        	      <input type="text" id="username" name="username" class="input_large" value="<s:property value="username"/>"/>
        	      </td>
        	       <td class="t_r">申请部门</td>
        	       <td>
        	  		<input type="text" id="deptname" name="deptname" class="input_large" value="<s:property value="deptname"/>"/>
        	  		</td>
        	  		 <td class="t_r">完成时间</td>
        	      <td>
        	      <input type="text" id="endtimes" name="endtimes" class="input_large date" value="<s:property value='endtimes'/>" readonly="readonly"/>
                    -
                    <input type="text" id="endtimee" name="endtimee" class="input_large date" value="<s:property value='endtimee'/>" readonly="readonly" />
                   </td>
        	      
      	        </tr>
      	         <tr>
        	  		 <td class="t_r">完成状态</td>
        	 
                      <td>
                      	<select name="status" id="status" class="input_large">
                              <option value="">---请选择---</option>
                              <option value="on">进行中</option>
                              <option value="off">已完成</option>
                          </select>
                      </td>
        	       
      	        </tr>
        	    <tr>
        	      <td colspan="6" class="t_c">
                  	<input type="button" id="sb" value="检 索" />&nbsp;&nbsp;
                  	<input id="clearInput" type="button" value="重 置"/></td>
				</tr>
      	    </table>
      	    
      	    </div>
        	</div>     
		      <div class="fn clearfix">
		                  <h5 class="fl"><a href="#" class="fl">已办事项信息列表</a></h5>
		             &nbsp;
		             <input type="radio" name="days" id="days" value="3">近3天
		             &nbsp;
		             <input type="radio" name="days" id="days" value="30">近30天
		             &nbsp;
		             <input type="radio" name="days" id="days" value="0">显示全部
		            </div>
		      </div>
</s:form>

      
        <!--Filter End-->
      <!--Table-->
        <div class="mb10">
        	<table width="100%"  class="table_1">
                              <tbody>
                              <tr class="tit">
                                <!-- <td><input type="checkbox" id="test_checkbox_1" name="test_checkbox_1" /></td>-->
                                 <td class="t_c">序号</td>
                                <td class="t_c">类型</td>
                                <td class="t_c">流程名称</td>
                                <td style="width:50%;" class="t_c">摘要</td>
                                <td class="t_c">开始时间</td>
                                <td class="t_c">完成时间</td>
                                <td class="t_c">申请人</td>
                                <td class="t_c">申请部门</td>
                                <td class="t_c">完成状态</td>
                               	<td class="t_c">备注</td>
                               	<td class="t_c">表单</td>
                                <td class="t_c">监控</td>
                                <td class="t_c">催办</td>
                                
                                </tr>
                              <s:iterator value="pageResultSet.list" id="items" status="s">
                              <tr>
                              	<td class="t_c"><s:property value="(#s.index+1)+(pageResultSet.pageInfo.currentPage-1)*10"/></td>
                               	<td class="t_c"><s:property value="#items.priorities" escape="0"/></td>
                               	<td class="t_c"><s:property value="#items.processname" /></td>
                                <td><s:property value="#items.summary" escape="0"/></td>
                                <td class="t_c"><s:property value="#items.starttime" /></td>
                                <td class="t_c"><s:property value="#items.endtime" /></td>
                                <td class="t_c"><s:property value="#items.username" /></td>
                                <td class="t_c"><s:property value="#items.deptname" /></td>
                                <td class="t_c">
                                <s:if test='#items.status=="on"'>进行中</s:if>
                                <s:elseif test='#items.status=="off"'>已完成</s:elseif>
                                <s:else></s:else>
                                </td>
                                <td class="t_c"><s:property value="#items.memo" escape="0"/></td>
                                <td class="t_c nwarp">
                                 
                                <input type="hidden" value="<s:property value='#items.processname'/>" id="processname"/>
                                <input type="hidden" value="<s:property value='#items.incident'/>" id="incident"/>
                                <input type="hidden" value="<s:property value='#items.initiator'/>" id="initiator"/>
                                 <input type="hidden" value="<s:property value='#items.groupid'/>" id="groupid"/>
                                <input type="hidden" value="<s:property value='#items.pstatus'/>" id="pstatus"/>
                                <input type="hidden" value="<s:property value='#items.cname'/>" id="cname"/>
                                <input type="hidden" value="<s:property value='#items.cincident'/>" id="cincident"/>
                                <a href="javascript:void(0);" class="todoUrl"><img src="../css/default/images/p_open.gif"></a>
                                
                                </td>
                                 <td class="t_c nwarp">
                                <input type="hidden" value="<s:property value='#items.processname'/>" id="processname"/>
                                <input type="hidden" value="<s:property value='#items.incident'/>" id="incident"/>
                                <input type="hidden" value="<s:property value='#items.initiator'/>" id="initiator"/>
                                 <input type="hidden" value="<s:property value='#items.groupid'/>" id="groupid"/>
                                <input type="hidden" value="<s:property value='#items.pstatus'/>" id="pstatus"/>
                                <a href="javascript:void(0);" class="todoScan"><img src="../css/default/images/p_but9.gif"></a>
                                </td>
                                 <td class="t_c nwarp">
                                 
                                <input type="hidden" value="<s:property value='#items.processname'/>" id="processname"/>
                                <input type="hidden" value="<s:property value='#items.incident'/>" id="incident"/>
                                <input type="hidden" value="<s:property value='#items.initiator'/>" id="initiator"/>
                                 <input type="hidden" value="<s:property value='#items.groupid'/>" id="groupid"/>
                                <input type="hidden" value="<s:property value='#items.pstatus'/>" id="pstatus"/>
                                <a href="javascript:void(0);" class="todoUrge"><img src="../css/default/images/p_task_exp.gif"></a>
                                
                                </td>
                                </tr>
                                </s:iterator>
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="13"><div class="clearfix"><span class="fl">共<s:property value="pageResultSet.pageInfo.totalRow"/>条记录</span>
                           		<ul class="fr clearfix pager">
		                             <li>Pages:<s:property value="pageResultSet.pageInfo.currentPage"/>/<s:property value="pageResultSet.pageInfo.totalPage"/>
		                             		<input type="hidden" value="<s:property value='pageResultSet.pageInfo.totalPage'/>" id="totalPageCount">
		                             		<input type="hidden" value="<s:property value='pageResultSet.pageInfo.currentPage'/>" id="currentNumber">
			                                  <input type="text" id="number" name="pageNumber" min="0" max="999" step="1" class="input_tiny" value="<s:property value='pageResultSet.pageInfo.currentPage'/>"/>
			                                  <input type="button" name="button" id="button" value="Go" onclick="goPage(0,0)">
		                             </li>
                             
		                             <s:if test="pageResultSet.pageInfo.currentPage==pageResultSet.pageInfo.totalPage">
		                             <li><a href="javascript:void(0)">&gt;&gt;</a></li>
		                             </s:if>
		                             <s:else>
		                              <li><a href="javascript:void(0)" onclick="goPage(<s:property value='pageResultSet.pageInfo.totalPage'/>,3)">&gt;&gt;</a></li>
		                             </s:else>
                              
	                             	<li>
	                             	<s:if test="pageResultSet.pageInfo.currentPage==pageResultSet.pageInfo.totalPage">	
	                             		<a href="javascript:void(0)">下一页</a>
	                             	</s:if>
	                             	<s:else>
	                             		<a href="javascript:void(0)" onclick="goPage(<s:property value='pageResultSet.pageInfo.currentPage'/>,2)">下一页</a>
	                             	</s:else>
	                             	</li>
	                             	<li>
	                             	<s:if test="pageResultSet.pageInfo.currentPage==1">
	                             		<a href="javascript:void(0)">上一页</a>
	                             	</s:if>
	                             	<s:else>
	                             		<a href="javascript:void(0)" onclick="goPage(<s:property value='pageResultSet.pageInfo.currentPage'/>,1)">上一页</a>
	                             	</s:else>
	                             	
	                             	</li> 
	                             
	                             	<s:if test="pageResultSet.pageInfo.currentPage==1">
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
</html>