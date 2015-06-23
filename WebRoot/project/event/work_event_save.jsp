<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>信息安全事件保存
</title>
<link rel="stylesheet" href="<%=basePath %>css/formalize.css" />
<link rel="stylesheet" href="<%=basePath %>css/page.css" />
<link rel="stylesheet" href="<%=basePath %>css/default/imgs.css" />
<link rel="stylesheet" href="<%=basePath %>css/reset.css" />
<!--[if IE 6.0]>
           <script src="js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
        <script src="<%=basePath %>js/html5.js"></script>
        <script src="<%=basePath %>js/jquery-1.7.1.js"></script>
        <script src="<%=basePath %>project/sysinfo/js/jquery.form.js"></script>
		<script src="<%=basePath %>js/jquery.formalize.js"></script>
		<!--<script src="../js/switchDept.js"></script>-->
		<script src="<%=basePath %>js/show.js"></script>
		<script src="<%=basePath %>js/loading.js"></script>
		<link type="text/css" href="<%=basePath %>css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="<%=basePath %>js/flick/jquery-ui-1.8.18.custom.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>js/flick/jquery.ui.datepicker-zh-CN.js"></script>
        <style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
		</style>
		<%-- <script type="text/javascript" src="js/inspect.js"></script> --%>
		<script type="text/javascript">
		var addOptions = {
				cache:false,
				type:'post',
				callback:null,
				url:'/portal/project/security/save.action',
			    success:function(data){
			    	
						if(data !=null && data.length > 0){
							alert(data);
							if(data=="添加成功！！！"){
								window.location.href = "/portal/project/sysinfo/project_add.jsp";
							}else if(data=="修改成功！！！"){
								window.location.href = "/portal/project/sysinfo/list.action";
							}
						}else{
							alert("添加失败！");
							window.location.href = "/portal/project/sysinfo/project_add.jsp";
						}
					return false;
			    }
			};
		
		
         $(function(){
        	 $("#submit").click(function(){
        		 	$("#add").ajaxSubmit(addOptions);
        	 });
			$(".odd tr:odd").css("background","#fafafa");	
			$('#telTime').datepicker({
				//inline: true
				"changeYear":true,
				"showButtonPanel":true,
				"closeText":'清除',
				"currentText":'telTime'//仅作为“清除”按钮的判断条件
			});
			$('#beginTime').datepicker({
				//inline: true
				"changeYear":true,
				"showButtonPanel":true,
				"closeText":'清除',
				"currentText":'beginTime'//仅作为“清除”按钮的判断条件
			});
			//datepicker的“清除”功能
            $(".ui-datepicker-close").live("click", function (){              
              if($(this).parent("div").children("button:eq(0)").text()=="telTime") $("#telTime").val(""); 
              if($(this).parent("div").children("button:eq(0)").text()=="beginTime") $("#beginTime").val(""); 
            });
			
			loadShow();
			
			
			$("#release").click(function(){
				if(window.confirm("发布后计划将无法修改，是否继续操作？")){
					$("#confirm").val("1");
					var path="/portal/project/security/confirm.action";
					$("#add").attr("action", path).submit();
					//$("#wform").submit(); 
					//$("#confirm").val(1);
					//$("#wform").submit();
					//return true;
				}else{
					$("#confirm").val("0");
				}
			});
			
			if($("#confirm").val()==1){
				$("#sysName").attr("readonly","readonly");
				$("#sysName").css("border-color","white");
			}
			
			//信息来源
			var  mlength=$("#messageSource").children("option").length;
			for(var i=0;i<mlength;i++){
				if($("#messageSource").children("option:eq("+i+")").val()==$("[name=messageSources]").val())
					$("#messageSource").children("option:eq("+i+")").attr("selected",true);
			}
			//业务类型
			var  clength=$("#classification").children("option").length;
			for(var i=0;i<clength;i++){
				if($("#classification").children("option:eq("+i+")").val()==$("[name=classifications]").val())
					$("#classification").children("option:eq("+i+")").attr("selected",true);
			}
			//事件类型
			var  elength=$("#eventType").children("option").length;
			for(var i=0;i<elength;i++){
				if($("#eventType").children("option:eq("+i+")").val()==$("[name=eventTypes]").val())
					$("#eventType").children("option:eq("+i+")").attr("selected",true);
			}
			//事件等级
			var  rlength=$("#ranks").children("option").length;
			for(var i=0;i<rlength;i++){
				if($("#ranks").children("option:eq("+i+")").val()==$("[name=rankss]").val())
					$("#ranks").children("option:eq("+i+")").attr("selected",true);
			}
		});
		
		function shut(){
		  window.opener=null;
		  window.open("","_self");
		  window.close();
		}
		
		function checkout(){
			//alert("校验");
			return true;
		}
		
        </script>
       </head>

<body>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img id="show" onclick="showHide();" src="<%=basePath %>css/default/images/sideBar_arrow_right.jpg" width="46" height="30" title="收起"></div>
            <div class="posi fl">
            	<ul>
            		<li class="fin">信息安全事件保存</li>
				</ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <!--Filter--><!--Filter End-->
        <!--Table-->
        
        <div class="mb10 pt45">
        <form id="add" method="post" action="/portal/project/event/save.action" >
        <input  type="hidden" id="id" name="id" value="<s:property value='event.id'/>"/>
        
          <table width="100%"  class="table_1">
           <thead>
            <th colspan="4" class="t_r">
       	      &nbsp;</th>
                                  </thead>
                              <tbody>
                               <tr>
                                <td class="t_r lableTd">单位名称：</td>
                                <td>
                                <input type="text" id="companyName" name="event.companyName" class="input_xxlarge" value="<s:property value='event.companyName'/>"/>
                                </td>
                                <td class="t_r lableTd">报告时间：</td>
                                <td>
                                <input type="text" id="telTime" name="event.telTime" class="input_xxlarge" value="<s:date name='event.telTime' format='yyyy-MM-dd'/>"/>
                                </td>
                                </tr>
                                <tr>
                                <td class="t_r lableTd">事件发生时间：</td>
                                <td>
                                <input type="text" id="beginTime" name="event.beginTime" class="input_xxlarge" value="<s:date name='event.beginTime' format='yyyy-MM-dd'/>"/>
                                
                                </td>
                                <td class="t_r lableTd">信息来源：</td>
                                <td>
                                <input type="hidden" id="messageSources" name="messageSources" class="input_xxlarge" value="<s:property value='event.messageSource'/>"/>
                                <select id="messageSource" name="event.messageSource" class="input_xxlarge">
                                <option value="">--请选择--</option>
                                <option value="内部">--内部--</option>
                                <option value="外部">--外部--</option>
                                </select>
                                </td>
                                </tr><tr>
                                <td class="t_r lableTd">业务类型：</td>
                                <td>
                                <input type="hidden" id="classifications" name="classifications" class="input_xxlarge" value="<s:property value='event.classification'/>"/>
                                <select id="classification" name="event.classification" class="input_xxlarge">
                                <option value="">--请选择--</option>
                                <option value="企业">--企业--</option>
                                <option value="生产">--生产--</option>
                                <option value="企业/生产">--企业/生产--</option>
                                </select>
                                </td>
                                <td class="t_r lableTd">事件类型：</td>
                                <td>
                                <input type="hidden" id="eventTypes" name="eventTypes" class="input_xxlarge" value="<s:property value='event.eventType'/>"/>
                                <select id="eventType" name="event.eventType" class="input_xxlarge">
                                <option value="">--请选择--</option>
                                <option value="有害程序事件">--有害程序事件--</option>
                                <option value="网络攻击事件">--网络攻击事件--</option>
                                <option value="信息破坏事件">--信息破坏事件--</option>
                                <option value="工业控制系统攻击事件">--工业控制系统攻击事件--</option>
                                <option value="设备设施故障">--设备设施故障--</option>
                                <option value="灾害性事件">--灾害性事件--</option>
                                </select>
                                </td>
                                </tr>
                                <tr>
                                <td class="t_r lableTd">涉及网络或系统：</td>
                                <td>
                                <input type="text" id="networkSystem"  name="event.networkSystem" class="input_xxlarge" value="<s:property value="event.networkSystem"/>"/>
                                </td>
                                <td class="t_r lableTd">事件描述：</td>
                                <td>
                                <input type="text" id="descriptions" name="event.descriptions" class="input_xxlarge" value="<s:property value="event.descriptions"/>"/> 
                                </td>
                                </tr>
                                <tr>
                                <td class="t_r lableTd">事件报告人：</td>
                                <td>
                                <input type="text" id="reporter"  name="event.reporter" class="input_xxlarge" value="<s:property value="event.reporter"/>"/>
                                </td>
                                <td class="t_r lableTd">联系方式：</td>
                                <td>
                                <input type="text" id="telphone" name="event.telphone" class="input_xxlarge" value="<s:property value="event.telphone"/>"/>
                                </td>
                                </tr>
                                <tr>
                                <td class="t_r lableTd">事件等级：</td>
                                <td>
                                <input type="hidden" id="rankss" name="rankss" class="input_xxlarge" value="<s:property value='event.ranks'/>"/>
                                <select id="ranks" name="event.ranks" class="input_xxlarge">
                                <option value="">--请选择--</option>
                                <option value="特别重大事件（Ⅰ级）">--特别重大事件（Ⅰ级）--</option>
                                <option value="重大事件（II级）">--重大事件（II级）--</option>
                                <option value="较大事件（Ⅲ级）">--较大事件（Ⅲ级）--</option>
                                <option value="一般事件（Ⅳ级）">--一般事件（Ⅳ级）--</option>
                                <option value="一般以下事件（V级）">--一般以下事件（V级）--</option>
                                </select>
                                </td>
                                </tr>
                                
                                <tr>
                                <td class="t_r lableTd">事件原因分析：</td>
                                <td colspan="3">
                                <textarea cols="10" rows="5" id="reasons" name="event.reasons" value=""><s:property value='event.reasons'/></textarea>
                                </td>
                                </tr>
                                <tr>
                                <td class="t_r lableTd">处理过程：</td>
                                <td colspan="3">
                                <textarea cols="10" rows="5" id="process" name="event.process" value=""><s:property value='event.process'/></textarea>
                                </td>
                                </tr>
                                <tr>
                                <td class="t_r lableTd">处理结果：</td>
                                <td colspan="3">
                                <textarea cols="10" rows="5" id="results" name="event.results" value=""><s:property value='event.results'/></textarea>
                                </td>
                                </tr>
                               <tr>
                                <td class="t_r lableTd">备注：</td>
                                <td colspan="3">
                                <textarea cols="10" rows="5" id="memo" name="event.memo" value=""><s:property value='event.memo'/></textarea>
                                </td>
                                </tr>
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="4" class="t_r"><input id="saves" onclick="return checkout();" type="submit" value="保存"/>&nbsp;
                                
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
