<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>招标计划添加</title>
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
        <script src="js/jquery.form.js"></script>
		<script src="../js/jquery.formalize.js"></script>
		<!--<script src="../js/switchDept.js"></script>-->
		<script src="../js/show.js"></script>
		<script src="../js/loading.js"></script>
		<link type="text/css" href="../css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="../js/flick/jquery-ui-1.8.18.custom.min.js"></script>
		<script type="text/javascript" src="../js/flick/jquery.ui.datepicker-zh-CN.js"></script>
        <style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
		</style>
		
		<script type="text/javascript">
		var handleOptions = {
				cache:false,
				type:'post',
				callback:null,
				url:'/portal/bid/addBidType.action',
			    success:function(data){
						if(data=="1"){
							alert("添加成功");
							$('#handleZone').dialog("close");
							getBidType(this.flag,$("#"+this.elementId));
						}else{
							alert("添加失败！");
							$('#handleZone').dialog("close");
						}
					return false;
			    }
			};
		
		var addOptions = {
				cache:false,
				type:'post',
				callback:null,
				url:'/portal/bid/add.action',
			    success:function(data){
						if(data !=null && data.length > 0){
							alert("添加成功");
							window.location.href = "/portal/bid/add.jsp";
						}else{
							alert("添加失败！");
							window.location.href = "/portal/bid/add.jsp";
						}
					return false;
			    }
			};
		
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
						var option = "";
						for(var i=0;i<obj.length;i++){
							option +="<option value='"+obj[i].TYPEID+"'>"+obj[i].TYPENAME+"</option>";
						}
						$(element).html(option);
					}
				}	  
			});	
		}
		
		
         $(function(){
        	 $("#submit").click(function(){
        		 $("#add").ajaxSubmit(addOptions);
        	 })
        	 
        	 $("#handleZone").dialog({
 				modal: true,
 				autoOpen: false,
 				width: 330,
 				height: 210,
 				zIndex: 9999,
 				buttons: [
 					{
 						text: "确认",
 						click: function() {
 							if($("#typeName").val()!=""){
 								$("#handleForm").ajaxSubmit(handleOptions);
 							}else{
 								alert("请输入类别名称!");
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
 				//close: function(event, ui) {$("#form").submit();}
 		});
        	 
        	$("select").each(function(i,n){
        		getBidType(i,n);
        		$(n).next("input[type=button]").bind("click",function(){
        			$( "#handleZone" ).dialog( "open" );
        			$("#typeName").val("")
            		$("#handleZone").find("#flag").val(i);
            		handleOptions.flag = i;
            		handleOptions.elementId = $(n).attr("id");
        		})
        	})
        	 
			$(".odd tr:odd").css("background","#fafafa");	
			$('#bidPlanDate').datepicker({
				//inline: true								
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',	
				"currentText":'bidPlanDate'//仅作为“清除”按钮的判断条件						
			});
			$('#bidInfoDate').datepicker({
				//inline: true
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',	
				"currentText":'bidInfoDate'//仅作为“清除”按钮的判断条件
			});	
			//datepicker的“清除”功能
            $(".ui-datepicker-close").live("click", function (){              
              if($(this).parent("div").children("button:eq(0)").text()=="bidPlanDate") $("#bidPlanDate").val("");
              if($(this).parent("div").children("button:eq(0)").text()=="bidInfoDate") $("#bidInfoDate").val("");     
                              
            });
			
			loadShow();		
		});
		
		function shut(){
		  window.opener=null;
		  window.open("","_self");
		  window.close();
		}
		
		function check(){
		
		
		showLoading();
		}
		
		
        </script>
       </head>

<body>
<jsp:include page="handle.jsp"></jsp:include>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img id="show" onclick="showHide();" src="../css/default/images/sideBar_arrow_right.jpg" width="46" height="30" title="收起"></div>
            <div class="posi fl">
            	<ul>
            		<li class="fin">招标计划添加</li>
                </ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <!--Filter--><!--Filter End-->
        <!--Table-->
        <div class="mb10 pt45">
        <form id="add">
          <table width="100%"  class="table_1">
           <thead>
            <th colspan="4" class="t_r">
       	      &nbsp;</th>
                                  </thead>
                              <tbody>
                               <tr>
                                <td class="t_r lableTd">项目公司：</td>
                                <td colspan="3">
                                <select name="projectCompany" id="projectCompany" class="input_xxlarge" > 
                                </select>
                                <input type="button" value="新增"/>
                                </td>
                                </tr>
                              <tr>
                                <td class="t_r lableTd">线路：</td>
                                <td>
                                <select name="line" id="line" class="input_large" > 
                                </select>
                                <input type="button" value="新增"/>
                                </td>
                                <td class="t_r lableTd">子目：</td>
                                <td>
                                <select name="catalogId" id="catalogId" class="input_large" > 
                                </select>
                                <input type="button" value="新增"/>
                                </td>
                                </tr>
                              <tr>
                                <td class="t_r lableTd">类别：</td>
                                <td>
                                <select name="typeId" id="typeId" class="input_large" > 
                                </select>
                                <input type="button" value="新增"/>
                                </td>
                                <td class="t_r lableTd">专业：</td>
                                 <td>
                                <select name="majorId" id="majorId" class="input_large" > 
                                </select>
                                <input type="button" value="新增"/>
                                </td>
                                </tr>
                              <tr>
                                <td class="t_r lableTd">标段号：</td>
                               <td colspan="3"><input type="text" id="bidNum" name="bidNum" class="input_xxlarge"/></td>
                               </tr>    
                             <tr>
                                <td class="t_r lableTd">工程名称：</td>
                               <td colspan="3"><input type="text" id="projectName" name="projectName" class="input_xxlarge"/></td>
                             </tr>                 
                             
                              <tr>
                                <td class="t_r lableTd">招标计划：</td>
                                <td>
                                <input readonly="readonly" type="date" id="bidPlanDate" name="bidPlanDate" value=""/>
        	      				</td>
                                <td class="t_r lableTd">中标金额：</td>
                               	<td><input type="text" id="bidAmount" name="bidAmount"/></td>
                               
                               </tr>
                               
                               <tr>
                                <td class="t_r lableTd">中标通知书发出日期：</td>
                               <td>
                               <input readonly="readonly" type="date" id="bidInfoDate" name="bidInfoDate" value=""/>
        	      				</td>
        	      				<td class="t_r lableTd">填报人：</td>
                               <td>
                               <s:property value="#session.userName"/>
                               <input type="hidden" id="operator" name="operator" value="<s:property value='#session.loginName'/>"/>
        	      				<input type="hidden" id="operatorName" name="operatorName" value="<s:property value='#session.userName'/>"/>
        	      				</td>
                             </tr>
                              <tr>
                                <td class="t_r lableTd">状态：</td>
                                <td>
                                <select name="status" id="status" class="input_large" > 
                                </select>
                                <input type="button" value="新增"/>
                                </td>
                                <td class="t_r lableTd"></td>
                                 <td></td>
                                </tr>
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="4" class="t_r"><input id="submit" type="button" value="添 加"/>&nbsp;
                                <input type="button" value="关 闭" onclick="shut();"/>&nbsp;
                                <input type="reset" value="重 置" />&nbsp;</td>
                              </tr>
                             
                              
                            </table>
             </form>
      </div>
        <!--Table End-->
</div>
</body>
</html>
