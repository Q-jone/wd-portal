<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>试题创建</title>
<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/default/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
<link rel="stylesheet" href="../exam/ui/js/validation/validationEngine.css"/> 
	<script src="../js/jquery-1.7.1.js"></script>
	<script type="text/javascript" src="../exam/ui/js/validation/validationEngine.js"></script>        	
	<script type="text/javascript">
        $(document).ready(function () {
        	$("#button2").click(function(){
        		window.location.href = "list.action";
        	})
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
			
        });
        
        function checkForm(){
        	if($('#desp').val().length > 1000){
        		$('#desp').val($('#desp').val().substr(0,1000));
        	}
        	
        	$("#form").submit();
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
                	<li><a href="#">试卷管理</a></li>
                	<li class="fin">创建试卷</li>
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
        <!--Filter-->
      <div class="filter">
       				<div class="fn clearfix">
		                  <h5 class="fl"><a href="#" class="colSelect fl">创建试卷</a></h5>
		             <input type="button" name="button2" id="button2" value="管理试卷" class="fr">
		            </div>
		</div>
    <div >
    <s:form action="save.action" id="form" method="post"  namespace="/exam">
      <table width="100%"  class="table_1">
        <tbody>
          <tr>
          	<td class="t_r lableTd">试卷名称</td>
			<td><input type="text" id="title" name="title" maxlength="200" class="validate[required] input_large" value="<s:property value='#request.paper.title'/>"/></td>
	        <td class="t_r lableTd">是否开放</td>
	           <td>
	           		<select name="state" id="state" class="input_large">
	           		<s:if test='#request.paper.state=="1"'>
		                <option value="0">开放</option>
		                <option value="1" selected="selected">不开放</option>
		            </s:if>
		            <s:else>
		                <option value="0" selected="selected">开放</option>
		                <option value="1">不开放</option>		            	
		            </s:else>
	           		</select>
	           </td>
	           
          </tr>
           <tr>
           	 <td class="t_r lableTd">背景描述</td>
            <td colspan="3">
				<textarea id="desp" name="desp" cols="40" rows="5"><s:property value='#request.paper.desp'/></textarea>
           </td>
          </tr>
        </tbody>
        <tr class="tfoot">
          <td colspan="4" class="t_r">
          	<input type="button" onclick="checkForm()" value="提 交"/>
          </td>
        </tr>
      </table>
      <input type="hidden" name="id" value="<s:property value='#request.paper.id'/>" />
      </s:form>
    </div>
        <!--Table End-->
</div>
</div>
</body>
</html>