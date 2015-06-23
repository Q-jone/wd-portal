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
<meta http-equiv="x-ua-compatible" content="IE=9">
<title>工会立功竞赛</title>
<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/default/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />

<script src="<%=path%>/js/jquery-1.7.1.js"></script>
<script src="<%=path%>/deptDoc/js/ztree/jquery.ztree.all-3.4.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/flick/jquery-ui-1.8.18.custom.min.js"></script>
<script src="<%=path%>/js/jquery.form.js"></script>
<script src="<%=path%>/js/jquery.formalize.js"></script>

	<script type="text/javascript">
        $(document).ready(function () {
        	if('${theme.year}' != ''){
        		$('[name=year]').val('${theme.year}');	
        	}else{
        		$('[name=year]').val('${thisYear}');
        	}
        });
        
      	var handleOptions = {
    			cache:false,
    			type:'post',
    			callback:null,
    			beforeSubmit:function(){$('#saveBtn').attr('disabled','true');},
    			url:'<%=path%>/unionTheme/save.action',
    		    success:function(data){
    				if(data=="1"){
    					alert("操作成功");
						shut();
    				}
    				$('#saveBtn').removeAttr('disabled');
    				return false;
    		    },error: function() { alert("服务器连接失败，请稍后再试!"); $('#saveBtn').removeAttr('disabled');}	
    		};
          
        function doSubmit(){
      		var checkResult = checkForm();
    		if(checkResult){
    			alert(checkResult);
    			return false;
    		}        	  
        	if(confirm('确认要提交吗？')){$('#form').ajaxSubmit(handleOptions);}
          }
          
          function checkForm(){
				if($('[name=year]').val()==''){
					$('[name=year]').focus();
					return '请选择年份！';
				}
				
				if($('[name=themeName]').val()==''){
					$('[name=themeName]').focus();
					return '请输入竞赛名称！';
				}				
          }  
          
  		function shut(){
  		  window.opener=null;
  		  window.open("","_self");
  		  window.close();
  		}          
    </script>

	<style>
	
	</style>

</head>

<body>
<div class="main">
    <!--Ctrl-->
    <div class="ctrl clearfix">
        <div class="posi fl">
            <ul>
                <li class="fin">竞赛主题表单</li>
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
    <!--Filter--><!--Filter End-->
    <!--Table-->

    <div class="mb10 pt45">
        <form id="form" method="post">
            <input type="hidden" name="id" value="${theme.id}"/>
			<input type="hidden" name="cUser" value="${theme.cUser}"/>
            <input type="hidden" name="cTime" value="${theme.cTime}"/>     
            <table width="100%"  class="table_1">
                <thead>
                <th colspan="4" class="t_r">
                    &nbsp;</th>
                </thead>
                <tbody>
                <tr>
                    <td class="t_r lableTd">年份：</td>
                    <td>
                        <select id="select" name="year">
                            <option value="">--请选择--</option>
							<%int thisYear = (Integer)request.getAttribute("thisYear");
								for(int i=2014;i<thisYear+5;i++){
							%>
							<option value="<%=i%>"><%=i%></option>
							<%
								}
							%>
                        </select>
                    </td>
                    <td class="t_r lableTd">竞赛名称：</td>
                    <td>
                        <input type="text" name="themeName" class="input_xxlarge" maxlength="200" value="${theme.themeName}"/>
                    </td>
                </tr>


                </tbody>
                <tr class="tfoot">
                    <td colspan="4" class="t_r"><input type="button" id="saveBtn" onclick="doSubmit();" value="保 存"/>&nbsp;
                        <input type="reset" value="重 置" />&nbsp;</td>
                </tr>

            </table>
        </form>
    </div>
    <!--Table End-->
</div>
</body>
</html>