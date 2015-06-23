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
<title>内控检查问题整改跟踪保存
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
		<script type="text/javascript" src="js/inspect.js"></script>
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
			$('#planBeginDate').datepicker({
				//inline: true
				"changeYear":true,
				"showButtonPanel":true,
				"closeText":'清除',
				"currentText":'planBeginDate'//仅作为“清除”按钮的判断条件
			});
			$('#planEndDate').datepicker({
				//inline: true
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',
				"currentText":'planEndDate'//仅作为“清除”按钮的判断条件						
			});
			
			$('#inspectDate').datepicker({
				//inline: true								
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',
				
				"currentText":'inspectDate'//仅作为“清除”按钮的判断条件						
			});
			$('#tractDate').datepicker({
				//inline: true								
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',
				
				"currentText":'tractDate'//仅作为“清除”按钮的判断条件						
			});
			//datepicker的“清除”功能
            $(".ui-datepicker-close").live("click", function (){              
              if($(this).parent("div").children("button:eq(0)").text()=="planBeginDate") $("#planBeginDate").val("");
              if($(this).parent("div").children("button:eq(0)").text()=="planEndDate") $("#planEndDate").val("");
              if($(this).parent("div").children("button:eq(0)").text()=="inspectDate") $("#inspectDate").val("");   
              if($(this).parent("div").children("button:eq(0)").text()=="tractDate") $("#tractDate").val("");   
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
				$("#category").attr("readonly","readonly");
				$("#category").css("border-color","white");
				$("#department").attr("readonly","readonly");
				$("#department").css("border-color","white");
				$("#important").attr("readonly","readonly");
				$("#important").css("border-color","white");
				$("#sysLevel").attr("readonly","readonly");
				$("#sysLevel").css("border-color","white");
				$("#excuteQuantity").attr("readonly","readonly");
				$("#excuteQuantity").css("border-color","white");
				$("#planBeginDate").attr("readonly","readonly");
				$("#planBeginDate").css("border-color","white");
				$("#planEndDate").attr("readonly","readonly");
				$("#planEndDate").css("border-color","white");
			}
			
			//严重程度degree
			var  dlength=$("#degree").children("option").length;
			for(var i=0;i<dlength;i++){
				if($("#degree").children("option:eq("+i+")").val()==$("[name=degree]").val())
					$("#degree").children("option:eq("+i+")").attr("selected",true);
			}
			//业务类别category
			var  clength=$("#category").children("option").length;
			for(var i=0;i<clength;i++){
				if($("#category").children("option:eq("+i+")").val()==$("[name=category]").val())
					$("#category").children("option:eq("+i+")").attr("selected",true);
			}
			//科目subject
			var  slength=$("#subject").children("option").length;
			for(var i=0;i<slength;i++){
				if($("#subject").children("option:eq("+i+")").val()==$("[name=subject]").val())
					$("#subject").children("option:eq("+i+")").attr("selected",true);
			}
			//整改方案plan
			var  plength=$("#plan").children("option").length;
			for(var i=0;i<plength;i++){
				if($("#plan").children("option:eq("+i+")").val()==$("[name=plan]").val())
					$("#plan").children("option:eq("+i+")").attr("selected",true);
			}
			//落实情况workable
			var  wlength=$("#workable").children("option").length;
			for(var i=0;i<wlength;i++){
				if($("#workable").children("option:eq("+i+")").val()==$("[name=workable]").val())
					$("#workable").children("option:eq("+i+")").attr("selected",true);
			}
			//复核情况review
			var  rlength=$("#review").children("option").length;
			for(var i=0;i<rlength;i++){
				if($("#review").children("option:eq("+i+")").val()==$("[name=review]").val())
					$("#review").children("option:eq("+i+")").attr("selected",true);
			}
		});
		
		function shut(){
		  window.opener=null;
		  window.open("","_self");
		  window.close();
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
            		<li class="fin">内控检查问题整改跟踪保存</li>
				</ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <!--Filter--><!--Filter End-->
        <!--Table-->
        
        <div class="mb10 pt45">
        <form id="add" method="post" action="/portal/project/inspect/save.action" >
        <input  type="hidden" id="workInspectId" name="workInspectId" value="<s:property value='inspect.workInspectId'/>"/>
        
          <table width="100%"  class="table_1">
           <thead>
            <th colspan="4" class="t_r">
       	      &nbsp;</th>
                                  </thead>
                              <tbody>
                               <tr>
                                <td class="t_r lableTd">单位名称：</td>
                                <td>
                                <input type="text" id="department" name="inspect.department" class="input_xxlarge" value="<s:property value='inspect.department'/>"/>
                                </td>
                                <td class="t_r lableTd">检查日期：</td>
                                <td>
                                <input type="text" id="inspectDate" name="inspect.inspectDate" class="input_xxlarge" value="<s:date name='inspect.inspectDate' format='yyyy-MM-dd'/>"/>
                                </td>
                                </tr>
                                <tr>
                                <td class="t_r lableTd">严重程度：</td>
                                <td>
                                <input type="hidden" id="degrees" name="degree" class="input_xxlarge" value="<s:property value='inspect.degree'/>"/>
                                <select id="degree" name="inspect.degree" class="input_xxlarge">
                                	<option value="">--请选择--</option>
                                	<option value="高">--高--</option>
                                	<option value="中">--中--</option>
                                	<option value="低">--低--</option>
                                </select>
                                </td>
                                <td class="t_r lableTd">业务类型：</td>
                                <td>
                                <input type="hidden" id="categorys" name="category" class="input_xxlarge" value="<s:property value='inspect.category'/>"/>
                                <select id="category" name="inspect.category" class="input_xxlarge">
                                <option value="">--请选择--</option>
                                <option value="企业">--企业--</option>
                                <option value="生产">--生产--</option>
                                <option value="企业/生产">--企业/生产--</option>
                                </select>
                                </td>
                                </tr><tr>
                                <td class="t_r lableTd">科目：</td>
                                <td>
                                <input type="hidden" id="subjects" name="subject" class="input_xxlarge" value="<s:property value='inspect.subject'/>"/>
                                <select id="subject" name="inspect.subject" class="input_xxlarge">
                                <option value="">--请选择--</option>
                                <option value="物理安全">--物理安全--</option>
                                <option value="网络安全">--网络安全--</option>
                                <option value="系统安全">--系统安全--</option>
                                <option value="应用安全">--应用安全--</option>
                                <option value="数据安全">--数据安全--</option>
                                <option value="终端安全">--终端安全--</option>
                                <option value="安全管理制度">--安全管理制度--</option>
                                <option value="组织机构管理">--组织机构管理--</option>
                                <option value="人员安全管理">--人员安全管理--</option>
                                <option value="系统建设管理">--系统建设管理--</option>
                                <option value="系统运维管理">--系统运维管理--</option>
                                </select>
                                </td>
                                <td class="t_r lableTd">科目子类：</td>
                                <td>
                                <input type="text" id="subSubject" name="inspect.subSubject" class="input_xxlarge" value="<s:property value='inspect.subSubject'/>"/>
                                </td>
                                </tr>
                                <tr>
                                <td class="t_r lableTd">计划开始时间：</td>
                                <td>
                                <input readonly="readonly" type="text" id="planBeginDate"  name="inspect.planBeginDate" class="input_xxlarge" value="<s:date name='inspect.planBeginDate' format='yyyy-MM-dd'/>"/>
                                </td>
                                <td class="t_r lableTd">计划结束时间：</td>
                                <td>
                                <input readonly="readonly" type="text" id="planEndDate" name="inspect.planEndDate" class="input_xxlarge" value="<s:date name='inspect.planEndDate' format='yyyy-MM-dd'/>"/> 
                                </td>
                                </tr>
                                <tr>
                                <td class="t_r lableTd">跟踪日期：</td>
                                <td>
                                <input type="text" id="tractDate" name="inspect.tractDate" class="input_xxlarge" value="<s:date name='inspect.tractDate' format='yyyy-MM-dd'/>"/>
                                </td>
								<td class="t_r lableTd">整改方案：</td>
                                <td>
                                <input type="hidden" id="plans" name="plan" value="<s:property value='inspect.plan'/>"/>
                                <select id="plan" name="inspect.plan" class="input_xxlarge">
                                <option value="">--请选择--</option>
                                <option value="已制定">--已制定--</option>
                                <option value="制定中">--制定中--</option>
                                <option value="未制定">--未制定--</option>
                                <option value="无需制定">--无需制定--</option>
                                </select>
                                </td>
                                </tr>
                                <tr>
                                <td class="t_r lableTd">落实情况：</td>
                                <td>
                                <input type="hidden" id="workables" name="workable" value="<s:property value='inspect.workable'/>"/>
                                <select id="workable" name="inspect.workable" class="input_xxlarge">
                                <option value="">--请选择--</option>
                                <option value="已整改">--已整改--</option>
                                <option value="整改中">--整改中--</option>
                                <option value="未整改">--未整改--</option>
                                </select>
                                </td>
                                <td class="t_r lableTd">复核情况：</td>
                                <td>
                                <input type="hidden" id="reviews" name="review" value="<s:property value='inspect.review'/>"/>
                                <select id="review" name="inspect.review" class="input_xxlarge">
                                <option value="">--请选择--</option>
                                <option value="通过">--通过--</option>
                                <option value="未通过">--未通过--</option>
                                </select>
                                </td>
                                </tr>
                                <tr>
                                <td class="t_r lableTd">检查问题描述：</td>
                                <td colspan="3">
                                <textarea cols="10" rows="5" id="inspectInfo" name="inspect.inspectInfo" value=""><s:property value='inspect.inspectInfo'/></textarea>
                                </td>
                                </tr>
                                <tr>
                                <td class="t_r lableTd">整改备注：</td>
                                <td colspan="3">
                                <textarea cols="10" rows="5" id="reformMemo" name="inspect.reformMemo" value=""><s:property value='inspect.reformMemo'/></textarea>
                                </td>
                                </tr>
                                <tr>
                                <td class="t_r lableTd">跟踪备注：</td>
                                <td colspan="3">
                                <textarea cols="10" rows="5" id="tractMemo" name="inspect.tractMemo" value=""><s:property value='inspect.tractMemo'/></textarea>
                                </td>
                                </tr>
                               <tr>
                                <td class="t_r lableTd">备注：</td>
                                <td colspan="3">
                                <textarea cols="10" rows="5" id="memo" name="inspect.memo" value=""><s:property value='inspect.memo'/></textarea>
                                </td>
                                </tr>
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="4" class="t_r"><input id="saves" onclick="return checkout();" type="submit" value="保存"/>&nbsp;
                                <s:if test="workSecurity.confirm==0">
                                <input id="release" type="button" value="发布"/>&nbsp;
                                </s:if>
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
