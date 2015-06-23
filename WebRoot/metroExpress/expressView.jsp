<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>运营速报查看</title>
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
                	<li><a href="javascript:window.location.href='/portal/center/yygl/yg_index.jsp'">运营管理</a></li>
                	<li class="fin">运营速报查看</li>
                </ul>
            </div>
           
   		</div>
        <!--Ctrl End-->
		<s:set name="lineMap" value="#request.lineMap"/>
        <!--Table-->
        <div class="mb10 pt45">
       	  <table width="100%"  class="table_1">
             <thead>
            <th colspan="4" class="t_r">
        	      <input type="button" value="关 闭" onclick="shut();"/>
        	      &nbsp;
        	      </th>
                                  </thead>
                              <tbody>
                                <tr>
                                <td class="t_r lableTd">事件标题：</td>
                                <td colspan="3"><s:property value='metroExpress.accidentTitle'/></td>
                                </tr>
                              <tr>
                                 <td class="t_r lableTd">事件类别：</td>
                                <td>
                               <s:property value='metroExpress.accidentType'/>
                                </td>
                                <td class="t_r lableTd">信息来源：</td>
                                <td><s:property value='metroExpress.accidentSource'/></td>
                                </tr>
                              <tr>
                              
                                <td class="t_r lableTd">发生日期：</td>
                                <td><s:property value='metroExpress.accidentDate'/></td>
                                <td class="t_r lableTd">发生时间：</td>
                                <td><s:property value='metroExpress.accidentTime'/></td>
                                </tr>
                              <tr>
                                <td class="t_r lableTd">所在线路：</td>
                                <td><s:property value="#lineMap.get(metroExpress.accidentLine)"/></td>
                                <td class="t_r lableTd">紧急程度：</td>
                                <td><s:property value='metroExpress.accidentEmergency'/></td>
                              </tr>
                                <tr>
                                <td class="t_r lableTd">事件地点：</td>
                                <td><s:property value='metroExpress.accidentLocation'/></td>
                                <td class="t_r lableTd">填报人：</td>
                                <td><s:property value='metroExpress.operatePerson'/></td>                                 
                                </tr>
                              <tr>
                                <td class="t_r lableTd">事件概述：</td>
                                <td colspan="3">
                                <textarea readonly="readonly" id="accidentDetail" name="accidentDetail" rows="5"><s:property value='metroExpress.accidentDetail'/></textarea>
                                </td> 
                              </tr>
                              
                             <s:iterator value="#request.resultMap" id="map" status="st">
                             	<tr>
	                                <td class="t_r lableTd">续报<s:property value="#st.index+1"/>：</td>
	                                <td colspan="3">
	                                <textarea id="accidentRemarkParam" rows="4"><s:property value='#map.value'/></textarea>
	                                </td>
                              	</tr>
                             </s:iterator>
                              <!-- 
                               <tr>
                                <td class="t_r lableTd">续报：</td>
                                <td colspan="3">
                                <textarea readonly="readonly" id="accidentRemark" name="accidentRemark" rows="15"><s:property value='metroExpress.accidentRemark'/></textarea>
                                </td> 
                              </tr>
                               -->
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
