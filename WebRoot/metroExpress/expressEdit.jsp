<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.wonders.stpt.metroExpress.constant.ExpressConstants"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>运营速报修改</title>
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
		<script src="../js/My97DatePicker/WdatePicker.js"></script>
		<script src="../js/loading.js"></script>
		<script src="../js/show.js"></script>
		<script type="text/javascript">
         $(function(){
			$(".odd tr:odd").css("background","#fafafa");	
			
			$.ajax({
			type: 'POST',
			url: 'findMetroLineConfig.action?random='+Math.random(),
			dataType:'json',
			cache : false,
			error:function(){alert('系统连接失败，请稍后再试！')},
			success: function(obj){			
				var metroLine = "";
				var lineId = "";
				var lineName = "";
				for(var i=0;i<obj.length;i++){
					lineId = obj[i].split(":")[0];
					lineName = obj[i].split(":")[1];
					if($("#accidentLineHidden").val()==lineId){
						metroLine +="<option value='"+lineId+"' selected >"+lineName+"</option>";
					}else{
						metroLine +="<option value='"+lineId+"'>"+lineName+"</option>";
					}
				}
				$("#accidentLine").html(metroLine);
			}	  
		});	
		
		$.ajax({
			type: 'POST',
			url: 'findMetroExpressCode.action?random='+Math.random(),
			data:{
					"codeType_code" : "<%=ExpressConstants.EXPRESS_CODE%>",
					"codeInfo_code" : "<%=ExpressConstants.EMERGENCY_CODE%>"
				},
			dataType:'json',
			cache : false,
			error:function(){alert('系统连接失败，请稍后再试！')},
			success: function(obj){			
				var metroEmergency = "<option value=''></option>";
				for(var i=0;i<obj.length;i++){
					if($("#accidentEmergencyHidden").val()==obj[i]){
						metroEmergency +="<option value='"+obj[i]+"' selected >"+obj[i]+"</option>";
					}else{
						metroEmergency +="<option value='"+obj[i]+"'>"+obj[i]+"</option>";
					}
				}
				$("#accidentEmergency").html(metroEmergency);
			}	  
		});	
			
		var active = "";
		if($("#accidentActiveHidden").val()=="0"){
			active += "<option value='0' selected >是</option>"+
			"<option value='1' >否</option> "
		}else{
			active += "<option value='0'  >是</option>"+
			"<option value='1' selected >否</option> "
		}
		$("#accidentActive").html(active);	
		
		loadShow();
			
		});
		
		
		function shut(){
		  window.opener=null;
		  window.open("","_self");
		  window.close();
		}
		
		function check(){
		if($("#accidentTitle").val()==""){
		 	alert("请输入事件标题！")
		 	$("#accidentTitle").focus();
		 	return false;
		 }
		 if($("#accidentType").val()==""){
		 	alert("请输入事件类别！")
		 	$("#accidentType").focus();
		 	return false;
		 }
		 if($("#accidentSource").val()==""){
		 	alert("请输入信息来源！")
		 	$("#accidentSource").focus();
		 	return false;
		 }
		 if($("#accidentDate").val()==""){
		 	alert("请输入事件发生日期！")
		 	$("#accidentDate").focus();
		 	return false;
		 }
		 if($("#accidentTime").val()==""){
		 	alert("请输入事件发生时间！")
		 	$("#accidentTime").focus();
		 	return false;
		 }
		 if($("#accidentLine").val()==""){
		 	alert("请输入事件所在线路！")
		 	$("#accidentLine").focus();
		 	return false;
		 }
		
		  if($("#accidentLocation").val()==""){
		 	alert("请输入事件地点！")
		 	$("#accidentLocation").focus();
		 	return false;
		 }
		 if($("#accidentDetail").val()==""){
		 	alert("请输入事件概述！")
		 	$("#accidentDetail").focus();
		 	return false;
		 }
		 
		  //把所有续报拼接起来
		 var param = "";
		 var status = true;
		 $("textarea[id=accidentRemarkParam]").each(function(){
		 	if(!$(this).val().replace(/(^\s*)|(\s*$)/g, "")==""){
		 		/*alert("续报不能为空！如不需要该续报，请删除！");
		 		$(this).focus();
		 		status = false;
		 		return false;
		 		*/
		 		var temp = $(this).val().replace(/(^\s*)|(\s*$)/g, "");
		 		temp = temp.replace(/——/g,'--'); 
		 		param += temp +"——";
		 	}/*else{
		 		var temp = $(this).val().replace(/(^\s*)|(\s*$)/g, "");
		 		temp = temp.replace(/——/g,'--'); 
		 		param += temp +"——";
		 	}*/
		 });
		 if(!status){
		 	return false;
		 }
		 $("#hideRemarkParam").val(param);
		 	showLoading();
		 }
		
		//添加续报
		function addReport(){
			var addHtml = "<tr>"+
							"<td class='t_r lableTd'><input type='checkbox'> &nbsp;续报：</td>"+
                            "<td colspan='3'><textarea id='accidentRemarkParam' rows='4'></textarea></td>"+
                          "</tr>";
			
			$("#reportArea").parent("tbody").append(addHtml);
		}
		
		//删除续报
		function delReport(){
			$("input:checked").each(function(){
				$(this).parent("td").parent("tr").remove();
			});
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
            		<li><a href="javascript:window.location.href='/portal/center/yygl/yg_index.jsp'">运营管理</a></li>
                	<li class="fin">运营速报修改</li>
                </ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <!--Filter--><!--Filter End-->
        <!--Table-->
        <div class="mb10 pt45">
        <s:form action="metroExpressUpdate" method="post"  namespace="/metroExpress">
        	<input type="hidden" name="hideRemarkParam" id="hideRemarkParam">
        	<input type="hidden" id="accidentActiveHidden" value="<s:property value='metroExpress.accidentActive'/>">
        	<input type="hidden" id="accidentLineHidden" value="<s:property value='metroExpress.accidentLine'/>">
        	<input type="hidden" id="accidentEmergencyHidden" value="<s:property value='metroExpress.accidentEmergency'/>">
        	 <input type="hidden" name="metroExpressId" id="metroExpressId" value="<s:property value='metroExpress.id'/>">
        	 <input type="hidden" name="id" id="id" value="<s:property value='metroExpress.id'/>">
       	  <table width="100%"  class="table_1">
           <thead>
            <th colspan="4" class="t_r"><input type="submit" value="修 改" onclick="return check();"/>
        	      &nbsp;
        	      <input type="button" value="关 闭" onclick="shut();"/>
        	      &nbsp;
        	      </th>
                                  </thead>
                              <tbody>
                                <tr>
                                <td class="t_r lableTd">事件标题：</td>
                                <td colspan="3"><input value="<s:property value='metroExpress.accidentTitle'/>" type="text" id="accidentTitle" name="accidentTitle" style="width:490px;"/></td>
                               
                                </tr>
                              <tr>
                                 <td class="t_r lableTd">事件类别：</td>
                                <td>
                                <select name="accidentType" id="accidentType" class="input_large" > 
                                	<s:if test="metroExpress.accidentType==行车组织">
                                		<option value="行车组织" selected="selected">行车组织</option>
                                	</s:if>
                                	<s:else>
                                		<option value="行车组织">行车组织</option>
                                	</s:else>
                                	<s:if test="metroExpress.accidentType==运营安全">
                                		<option value="运营安全" selected="selected">运营安全</option>
                                	</s:if>
                                	<s:else>
                                		<option value="运营安全">运营安全</option>
                                	</s:else>
                                	<s:if test="metroExpress.accidentType==票务管理">
                                		<option value="票务管理" selected="selected">票务管理</option>
                                	</s:if>
                                	<s:else>
                                		<option value="票务管理">票务管理</option>
                                	</s:else>
                                	<s:if test="metroExpress.accidentType==设施设备">
                                		<option value="设施设备" selected="selected">设施设备</option>
                                	</s:if>
                                	<s:else>
                                		<option value="设施设备">设施设备</option>
                                	</s:else>
                                </select>
                                </td>
                                <td class="t_r lableTd">信息来源：</td>
                                <td><input value="<s:property value='metroExpress.accidentSource'/>" type="text" id="accidentSource" name="accidentSource" value="运管中心" class="input_large"/></td>
                                
                                </tr>
                              <tr>
                                <td class="t_r lableTd">发生日期：</td>
                                <td><input value="<s:property value='metroExpress.accidentDate'/>" type="text" id="accidentDate" name="accidentDate" class="input_large date"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"/></td>
                                <td class="t_r lableTd">发生时间：</td>
                                <td><input value="<s:property value='metroExpress.accidentTime'/>" type="text" id="accidentTime" name="accidentTime" class="input_large date"  onClick="WdatePicker({dateFmt:'HH:mm'})"/></td>
                                
                                </tr>
                              <tr>
                                <td class="t_r lableTd">所在线路：</td>
                                <td>
                                <select name="accidentLine" id="accidentLine" class="input_large" > 
                                </select>
                                </td>
                               <td class="t_r lableTd">紧急程度：</td>
                                <td>
                                	<select name="accidentEmergency" id="accidentEmergency" class="input_large" > 
                                </select>
                                </td>
                                </tr>
                                
                              <tr>
                                <td class="t_r lableTd">事件地点：</td>
                                <td>
                                	<input value="<s:property value='metroExpress.accidentLocation'/>" type="text" id="accidentLocation" name="accidentLocation" class="input_large" />
                                </td>
                                <td class="t_r lableTd">填报人：</td>
                                <td>
                                	<s:property value='metroExpress.operatePerson'/>
                                	<input value="<s:property value='metroExpress.operatePerson'/>" type="hidden" id="operatePerson" name="operatePerson" class="input_large" />
                                </td>
                             </tr>
                                                          
                              <tr>
                                <td class="t_r lableTd">是否有效：</td>
                                <td colspan="3">
                                	<select name="accidentActive" id="accidentActive" class="input_large" > 
                               		</select>
                                </td>
                               
                             </tr>
                                        
                                        
                              <tr>
                                <td class="t_r lableTd">事件概述：</td>
                                <td colspan="3">
                                <textarea id="accidentDetail" name="accidentDetail" rows="4"><s:property value='metroExpress.accidentDetail'/></textarea>
                                </td>
                              </tr>
                              
                              <tr id="reportArea">
                              	 <td class="t_r lableTd">事件续报</td>
                              	 <td colspan="3">
                              	 	<input type="button" value="添加续报" onclick="addReport();"/>
                              	 	 <input type="button" value="删除续报" onclick="delReport();"/>
                              	 </td>
                              </tr>
                             
                             
                             <s:iterator value="#request.resultMap" id="map">
                             	<tr>
	                                <td class="t_r lableTd"><input type="checkbox"> &nbsp;续报：</td>
	                                <td colspan="3">
	                                <textarea id="accidentRemarkParam" rows="4"><s:property value='#map.value'/></textarea>
	                                </td>
                              	</tr>
                             </s:iterator>
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="4" class="t_r"><input type="submit" value="修 改" onclick="return check();"/>&nbsp;
                                <input type="button" value="关 闭" onclick="shut();"/>&nbsp;
                                </td>
                              </tr>
                            </table>
             </s:form>
      </div>
        <!--Table End-->
</div>
</body>
</html>
