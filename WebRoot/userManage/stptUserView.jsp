<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>用户信息查看</title>
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
		<script src="../js/My97DatePicker/WdatePicker.js"></script>
		<script src="../js/show.js"></script>
		<script type="text/javascript">
        $(function(){
			$(".odd tr:odd").css("background","#fafafa");		
			loadShow();	
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
        	<div class="fl"><img id="show" onclick="showHide();" src="../css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li><a href="#">用户管理</a></li>
                	<li class="fin">用户信息查看</li>
                </ul>
            </div>
           
   		</div>
        <!--Ctrl End-->
        <!--Table-->
        <div class="mb10 pt45">
        
          <div class="filter">
           	 <div class="fn"><h5>用户相关信息</h5></div>            	
           </div>
           <table width="100%"  class="table_1 mb10">
           	<tr class="tit">
           		<td class="t_c">账号</td>
           		<td class="t_c">姓名</td>
           		<td class="t_c">昵称</td>
           		<td class="t_c">所属部门</td>
           		<td class="t_c">职务</td>
           		<td class="t_c">创建时间</td>
           		<td class="t_c">角色</td>
              </tr>
           		<s:iterator value="#request.voList" id="vo">
           		<tr>
           			<td class="t_c"><s:property value='#vo.loginName'/></td>
           			<td class="t_c"><s:property value='#vo.name'/></td>
           			<td class="t_c"><s:property value='#vo.nickName'/></td>
           			<td class="t_c"><s:property value='#vo.dept'/></td>
           			<td class="t_c"><s:property value='#vo.rank'/></td>
           			<td class="t_c"><s:property value='#vo.createDate'/></td>
           			<td class="t_c"><s:property value='#vo.role'/></td>
           		</tr>
           		</s:iterator>
              <s:iterator value="#request.agentList" id="agent">
             		<tr>
             			<td class="t_c"><s:property value='#agent.loginName'/></td>
             			<td class="t_c"><s:property value='#agent.name'/></td>
             			<td class="t_c"><s:property value='#agent.nickName'/></td>
             			<td class="t_c"><s:property value='#agent.dept'/></td>
             			<td class="t_c"><s:property value='#agent.rank'/></td>
             			<td class="t_c"><s:property value='#agent.createDate'/></td>
             			<td class="t_c"><s:property value='#agent.role'/></td>
             		</tr>
             		</s:iterator>
           </table>
        
       	  <table width="100%"  class="table_1">
             <thead>
            <th colspan="4" class="t_r">
        	      <input type="button" value="关 闭" onclick="shut();"/>
        	      &nbsp;
        	      </th>
                                  </thead>
                               <tbody>
                               <tr>
                                <td class="t_r lableTd">工号：</td>
                                <td><s:property value="stptUser.loginName"/></td>
                               	<td class="t_r lableTd">姓名：</td>
                                <td><s:property value="stptUser.name"/></td>
                               </tr>
                                
                              <tr>
                               <td class="t_r lableTd">身份证号</td>
                                <td><s:property value='stptUser.idcard'/></td>
                                <td class="t_r lableTd">性别：</td>
                                <td>
                                 <s:if test='stptUser.sex=="1"'>男</s:if>
                                <s:elseif test='stptUser.sex=="0"'>女</s:elseif>
                               	<s:else>&nbsp;</s:else>
                                </td>
                               </tr>
                               <tr>
                                <td class="t_r lableTd">出生日期</td>
                                <td><s:property value='stptUser.birthday'/></td>
                                <td class="t_r lableTd">民族</td>
                                <td><s:property value='stptUser.nation'/></td>
                               </tr>
                               <tr>
                                <td class="t_r lableTd">籍贯</td>
                                <td><s:property value='stptUser.birthplace'/></td>
                                <td class="t_r lableTd">政治面貌</td>
                                <td><s:property value='stptUser.political'/></td>
                              </tr> 
                              <tr>
                                <td class="t_r lableTd">最高学历</td>
                                <td><s:property value='stptUser.degree'/></td>
                                <td class="t_r lableTd">居住地址</td>
                                <td><s:property value='stptUser.address'/></td>
                              </tr>
                              
                              <tr>
                                <td class="t_r lableTd">邮政编码</td>
                                <td><s:property value='stptUser.postcode'/></td>
                                <td class="t_r lableTd">居住地电话</td>
                                <td><s:property value='stptUser.phone'/></td>
                              </tr>
                              
                              <tr>
                                <td class="t_r lableTd">手机号码1</td>
                                <td><s:property value='stptUser.mobile1'/></td>
                                <td class="t_r lableTd">手机号码2</td>
                                <td><s:property value="stptUser.mobile2"/></td>
                              </tr>  
                              
                               <tr>
                               <td class="t_r lableTd">目前工作单位</td>
                                  <td><s:property value="stptUser.company"/></td>
                             <td class="t_r lableTd">目前工作部门</td>
                                  <td><s:property value="stptUser.dept"/></td>
                              </tr>  
                              
                              <tr>
                                <td class="t_r lableTd">目前职务</td>
                                <td><s:property value='stptUser.rank'/></td>
                                <td class="t_r lableTd">技术等级</td>
                                <td><s:property value='stptUser.grade'/></td>
                              
                              </tr>
                              <tr>
                                <td class="t_r lableTd">最高职称</td>
                                <td><s:property value='stptUser.title'/></td>
                               
                                <td class="t_r lableTd">技术专业</td>
                                <td><s:property value='stptUser.major'/></td>
                                </tr>
                             
                              <tr>
                                <td class="t_r lableTd">支内或农口</td>
                                <td><s:property value='stptUser.household'/></td>
                                
                                <td class="t_r lableTd">在职或退休</td>
                                <td><s:property value='stptUser.retire'/></td>
                                
                              </tr>
                              <tr>
                                <td class="t_r lableTd">工作单位地址</td>
                                <td colspan="3"><s:property value='stptUser.caddress'/></td>
                              </tr>
                               <tr>
                                <td class="t_r lableTd">单位邮编</td>
                                <td><s:property value='stptUser.cpostcode'/></td>
                                <td class="t_r lableTd">单位电话</td>
                                <td><s:property value='stptUser.cphone'/></td>
                              </tr>
                              <tr>
                                <td class="t_r lableTd">备注说明</td>
                                <td colspan="3"><s:property value='stptUser.remark'/></td>
                             </tr>
                              
                              
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="4" class="t_r">
                                <input type="button" value="关 闭" onclick="shut();"/>
                                </td>
                              </tr>
                            </table>  
                            
                           
             
      </div>                             
        <!--Table End-->
</div>
</body>
</html>
