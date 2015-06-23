<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>用户账户添加</title>
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
       	<script src="../js/jquery-1.7.1.js"></script>
        <script src="../js/html5.js"></script>
		<script src="../js/jquery.formalize.js"></script>
		<script src="../js/loading.js"></script>
		<script src="../js/show.js"></script>
		<script src="js/validate.js"></script>
		<link type="text/css" href="../css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		<script src="../js/flick/jquery-ui-1.8.18.custom.min.js"></script>
		<script src="../js/flick/jquery.ui.datepicker-zh-CN.js"></script>
		<style type="text/css">
			/*demo page css*/
			.demoHeaders { margin-top: 2em; }
			#dialog_link {padding: .4em 1em .4em 20px;text-decoration: none;position: relative;}
			#dialog_link span.ui-icon {margin: 0 5px 0 0;position: absolute;left: .2em;top: 50%;margin-top: -8px;}
			ul#icons {margin: 0; padding: 0;}
			ul#icons li {margin: 2px; position: relative; padding: 4px 0; cursor: pointer; float: left;  list-style: none;}
			ul#icons span.ui-icon {float: left; margin: 0 4px;}
			.ui-datepicker-title span {display:inline;}
		</style>	
		<script type="text/javascript">
         $(function(){
         	$('#birthday').datepicker({
				"inline": true,
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',	
				"yearRange":'1900:+nn' ,
				"changeMonth":true,
				"maxDate":'0D'
			});
			$("#birthday").datepicker('option', $.datepicker.regional['zh-CN']); 
				
			$(".ui-datepicker-close").live("click", function (){              
              $("#birthday").val("");              
            });
            
			$(".odd tr:odd").css("background","#fafafa");	
			loadShow();		
		});
		
		function loginNameExist(loginName){
			$.ajax({
			type: 'POST',
			url: 'stptUserExist.action?random='+Math.random(),
			dataType:'json',
			data:{
					"loginName" : loginName
				},
			cache : false,
			error:function(){alert('系统连接失败，请稍后再试！')},
			success: function(obj){		
				if(obj.result==1){
					alert("该工号已存在，请重新录入！")
					$("#loginName").val("");
					$("#loginName").focus();
					return false;
				}else{
					if($("#name").val()==""){
					 	alert("请输入姓名！")
					 	$("#name").focus();
					 	return false;
					 }
					
					//验证身份证号	
					  if($("#idcard").val()!="")  {
					    if(isIdCardNo($("#idcard").val())==false) return false;		
					  }
					  //验证居住地邮编
					  if($("#postcode").val()!="")  {	  
					    if(isPostCode($("#postcode"))==false) return false;
					  }
					  
					  //验证居住地电话
					  if($("#phone").val()!=null&&$("#phone").val()!=""&&!$("#phone").val().match(/^[0-9-]*$/)){		   
					      alert("居住地电话仅能输入数字和中划线");
					      $("#phone").focus();
					      return false;		   
					  }
					  
					  //验证手机号码
					  if($("#mobile1").val()!="")  {	 
					    if(isMobileNO($("#mobile1"))==false) return false;
					  }
					  
					   //验证手机号码
					  if($("#mobile2").val()!="")  {	 
					    if(isMobileNO($("#mobile2"))==false) return false;
					  }
					  
					  //验证单位邮编
					  if($("#cpostcode").val()!="")  {	  
					    if(isPostCode($("#cpostcode"))==false) return false;
					  }
					  
					  //验证单位电话
					  if($("#cphone").val()!=null&&$("#cphone").val()!=""&&!$("#cphone").val().match(/^[0-9-]*$/)){		   
					      alert("单位电话仅能输入数字和中划线");
					      $("#cphone").focus();
					      return false;		   
					  }
					  
					  //验证备注
					  if($("#remark").val().length > 500) {
					    alert("备注最多输入500字！");
					    $("#remark").focus();
					    return false;
					  }
					  
					  if($("#birthday").val()!=null && $("#birthday").val()!="" && !$("#birthday").val().match(/^[0-9]{4}[-]{1}[0-9]{2}[-]{1}[0-9]{2}$/)){
			        		alert("请选择输入正确的日期");       		
			        		$("#birthday").focus();
			        		return false;
			        	}
					 $("form").submit();
					 showLoading();
				}
			}	
		});		
		}
		
		
		function shut(){
		  window.opener=null;
		  window.open("","_self");
		  window.close();
		}
		
		function check(){
		
		$("#loginName").val($("#loginName").val().replace(/(^\s*)|(\s*$)/g,''));
		$("#name").val($("#name").val().replace(/(^\s*)|(\s*$)/g,''));
		
		
		if($("#loginName").val()==""){
		 	alert("请输入工号！")
		 	$("#loginName").focus();
		 	return false;
		 }
		 
		 var patrn=/^[A-Z]{1}[0-9]{11}$/;
		if (!patrn.exec($("#loginName").val())){
			alert("请输入正确的工号！")
			$("#loginName").val("");
			$("#loginName").focus();
			return false;
		}
		
		loginNameExist($("#loginName").val());
						 
		 
		}
		
		
        </script>
       </head>

<body>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img id="show" onclick="showHide();" src="../css/default/images/sideBar_arrow_right.jpg" width="46" height="30" title="收起"></div>
            <div class="posi fl">
            	<ul>
            		<li><a href="#">用户管理</a></li>
                	<li class="fin">用户添加</li>
                </ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <!--Filter--><!--Filter End-->
        <!--Table-->
        <div class="mb10 pt45">
        <s:form action="stptUserAdd" method="post"  namespace="/userManage">
        
       	  <table width="100%"  class="table_1">
           <thead>
            <th colspan="4" class="t_r"><input type="button" value="添 加" onclick="check();"/>
        	      &nbsp;
        	      <input type="button" value="关 闭" onclick="shut();"/>
        	      &nbsp;
        	      <input type="reset" value="重 置" />
       	      &nbsp;</th>
                                  </thead>
                              <tbody>
                               <tr>
                                <td class="t_r lableTd">工号：</td>
                                <td><input type="text" id="loginName" name="loginName" class="input_large"/></td>
                               <td class="t_r lableTd">姓名：</td>
                                <td><input type="text" id="name" name="name" class="input_large"/></td>
                                </tr>
                              <tr>
                               <td class="t_r lableTd">身份证号</td>
                                <td><input type="text" id="idcard" name="idcard" maxlength="18" class="input_large"/></td>
                                <td class="t_r lableTd">性别：</td>
                                <td>
			        	        	<input type="radio" id="sex" name="sex" value="1" checked="checked"/>男
			        	        	<input type="radio" id="sex" name="sex" value="0"/>女
				        	    </td> 
                               </tr>
                               <tr>
                                <td class="t_r lableTd">出生日期</td>
                                <td><input id="birthday" name="birthday" type="date"  class="input_large" readonly="readonly"/></td>
                                <td class="t_r lableTd">民族</td>
                                <td><input type="text" id="nation" name="nation" maxlength="20" class="input_large"/></td>
                               </tr>
                               <tr>
                                <td class="t_r lableTd">籍贯</td>
                                <td><input type="text" id="birthplace" name="birthplace" maxlength="20" class="input_large" /></td>
                                <td class="t_r lableTd">政治面貌</td>
                                <td><select name="political" id="political" class="input_large">
                                    <option value="">---请选择---</option>
                                    <option value="中共党员">中共党员</option>
                                    <option value="民主党派">民主党派</option>
                                    <option value="共青团员">共青团员</option>
                                    <option value="群众">群众</option>                                    
                                </select></td>
                              </tr> 
                              <tr>
                                <td class="t_r lableTd">最高学历</td>
                                <td>
                                  <select name="degree" id="degree" class="input_large">
                                    <option value="">---请选择---</option>
                                    <option value="博士">博士</option>
                                    <option value="硕士">硕士</option>
                                    <option value="本科">本科</option>
                                    <option value="大专">大专</option>
                                    <option value="中专">中专</option>
                                    <option value="高中">高中</option>
                                    <option value="初中">初中</option>
                                    <option value="小学">小学</option>
                                </select></td>
                                <td class="t_r lableTd">居住地址</td>
                                <td><input type="text" id="address" name="address" class="input_xlarge" style="width:80%" maxlength="100"/></td>
                              </tr>
                              
                              <tr>
                                <td class="t_r lableTd">邮政编码</td>
                                <td><input type="text" id="postcode" name="postcode" maxlength="6" class="input_large"/></td>
                                <td class="t_r lableTd">居住地电话</td>
                                <td><input type="text" id="phone" name="phone" maxlength="20" class="input_large"/></td>
                              </tr>
                              
                              <tr>
                                <td class="t_r lableTd">手机号码1</td>
                                <td><input type="text" id="mobile1" name="mobile1" maxlength="11" class="input_large" /></td>
                                <td class="t_r lableTd">手机号码2</td>
                                <td><input type="text" id="mobile2" name="mobile2" maxlength="11" class="input_xlarge"/></td>
                              </tr>  
                              
                               <tr>
                               <td class="t_r lableTd">目前工作单位</td>
                                  <td><input type="text" id="company" name="company" class="input_xlarge"/></td>
                             <td class="t_r lableTd">目前工作部门</td>
                                  <td><input type="text" id="dept" name="dept" class="input_xlarge"/></td>
                              </tr>  
                              
                              <tr>
                                <td class="t_r lableTd">目前职务</td>
                                <td><input type="text" id="rank" name="rank" maxlength="20" class="input_large"/></td>
                                <td class="t_r lableTd">技术等级</td>
                                <td><select name="grade" id="grade" class="input_large">
                                    <option value="">---请选择---</option>   
                                    <option value="工人">工人</option>                                    
                                    <option value="技术人员">技术人员</option>                                    
                                    <option value="高级工">高级工</option>
                                    <option value="干部">干部</option>                                                               
                                </select></td>
                              </tr>
                              <tr>
                                <td class="t_r lableTd">最高职称</td>
                                <td><select name="title" id="title" class="input_large">
                                    <option value="">---请选择---</option>   
                                    <option value="初级职称">初级职称</option>                                    
                                    <option value="中级职称">中级职称</option> 
                                    <option value="高级职称">高级职称</option>                                   
                                    <option value="副高职称">副高职称</option>
                                    <option value="正高职称">正高职称</option>                                                              
                                </select></td>
                                <td class="t_r lableTd">技术专业</td>
                                <td>
                                <select name="major" id="major" class="input_large">
                                    <option value="">---请选择---</option>   
                                    <option value="经济师">经济师</option>                                    
                                    <option value="政工师">政工师</option>
                                    <option value="会计师">会计师</option>                                    
                                    <option value="工程师">工程师</option>
                                </select>
                                </td>
                                </tr>
                             
                              <tr>
                                <td class="t_r lableTd">支内或农口</td>
                                <td><select name="household" id="household" class="input_large">
                                  <option value="">---请选择---</option>
                                  <s:if test="stptUser.household=='支内'">
                                    <option value="支内" selected="selected">支内</option>
                                  </s:if>
                                  <s:else>
                                    <option value="支内">支内</option>
                                  </s:else>
                                   <s:if test="stptUser.household=='农口'">
                                    <option value="农口" selected="selected">农口</option>
                                  </s:if>
                                  <s:else>
                                    <option value="农口">农口</option>
                                  </s:else>
                                </select></td>
                                <td class="t_r lableTd">在职或退休</td>
                                <td><select name="retire" id="retire" class="input_large">
                                  <option value="">---请选择---</option>
                                  <s:if test="stptUser.retire=='在职'">
                                    <option value="在职" selected="selected">在职</option>
                                  </s:if>
                                  <s:else>
                                    <option value="在职">在职</option>
                                  </s:else>
                                   <s:if test="stptUser.retire=='退休'">
                                    <option value="退休" selected="selected">退休</option>
                                  </s:if>
                                  <s:else>
                                    <option value="退休">退休</option>
                                  </s:else>
                                </select></td>
                              </tr>
                              <tr>
                                <td class="t_r lableTd">工作单位地址</td>
                                <td colspan="3"><input type="text" id="caddress" name="caddress" maxlength="50" class="input_xlarge" style="width:64%"/></td>
                              </tr>
                               <tr>
                                <td class="t_r lableTd">单位邮编</td>
                                <td><input type="text" id="cpostcode" name="cpostcode" maxlength="6" class="input_large" /></td>
                                <td class="t_r lableTd">单位电话</td>
                                <td><input type="text" id="cphone" name="cphone" maxlength="20" class="input_large" /></td>
                              </tr>
                              <tr>
                                <td class="t_r lableTd">备注说明</td>
                                <td colspan="3"><textarea id="remark" name="remark" rows="5" ></textarea></td>
                             </tr>

                              </tbody>
                              <tr class="tfoot">
                                <td colspan="4" class="t_r"><input type="button" value="添 加" onclick="check();"/>&nbsp;
                                <input type="button" value="关 闭" onclick="shut();"/>&nbsp;
                                <input type="reset" value="重 置" />&nbsp;</td>
                              </tr>
                             
                              
                            </table>
             </s:form>
      </div>
        <!--Table End-->
</div>
</body>
</html>
