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
<link type="text/css" href="<%=path%>/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
<link rel="stylesheet" href="<%=path%>/union/css/tree/style.css" type="text/css">

<script src="<%=path%>/js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="<%=path%>/union/js/tree/jquery.tree.core.min.js"></script>
<script type="text/javascript" src="<%=path%>/union/js/tree/jquery.tree.excheck.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/flick/jquery-ui-1.8.18.custom.min.js"></script>
<script src="../js/flick/jquery.ui.datepicker-zh-CN.js"></script>
<script src="<%=path%>/js/jquery.form.js"></script>
<script src="<%=path%>/js/jquery.formalize.js"></script>
<script src="<%=path%>/union/js/custom_widgets.js"></script>

	<script type="text/javascript">
        $(document).ready(function () {

        });
          
  		function shut(){
  		  window.opener=null;
  		  window.open("","_self");
  		  window.close();
  		}          
  		
    </script>

	<style>
	span{display: inline;}
	</style>
	<style type="text/css" media=print>
	.nprint{display:none;}
	</style>
</head>

<body>
<div class="main">
    <!--Ctrl-->
    <div class="ctrl clearfix">
        <div class="fl"><img id="show" onclick="showHide();" src="<%=path%>/css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
        <div class="posi fl">
            <ul>
                <li class="fin">竞赛奖项表单</li>
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
        <form id="form" action="" method="post">
            <div id="applicantDeptDiv"></div>
            <input type="hidden" name="matchId" value="${param.matchId}"/>
            <input type="hidden" name="id" value="${prize.id}"/>
            <table width="100%"  class="table_1">
                <thead>
                <th colspan="4" class="t_r">
                    &nbsp;</th>
                </thead>
                <tbody>
                <tr>
                    <td class="t_r lableTd" width="20%">奖项类型：</td>
                    <td width="30%">
	                    <s:if test="#request.prize.prizeType==1">
	                        	个人类
	                    </s:if>
	                    <s:if test="#request.prize.prizeType==2">
	                        	集体类
	                    </s:if>
	                    <s:if test="#request.prize.prizeType==3">
	                        	 项目类
	                    </s:if>
	                    <s:if test="#request.prize.prizeType==4">
	                        	项目成果类
	                    </s:if>                            
	                    &nbsp;
	                    <s:if test="#request.prize.prizeSubType==1">
	                        	分等次竞赛
	                    </s:if>
	                    <s:if test="#request.prize.prizeSubType==2">
	                        	不分等次竞赛
	                    </s:if>	                    
                    </td>
                    <td class="t_r lableTd" width="20%">参与人数：</td>
                    <td id="personRange_setting" width="30%">
                    	<s:if test="#request.prize.prizeType!=1">
		                    <s:if test="#request.prize.personRange==1">
		                        	单位
		                    </s:if>
		                    <s:if test="#request.prize.personRange==2">
		                        	集体人数<=20人
		                    </s:if>
		                    <s:if test="#request.prize.personRange==3">
		                        	 20人< 集体人数 < 50人
		                    </s:if>
		                    <s:if test="#request.prize.personRange==4">
		                        	集体人数 >= 50人
		                    </s:if>                     	
                        </s:if>
                    </td>                    
                </tr>

                <tr>
                    <td class="t_r lableTd">奖项名称：</td>
                    <td>
						${prize.prizeName}
                    </td>

                    <td class="t_r lableTd">奖金：</td>
                    <td>
						${prize.bonus}
                    </td>
                </tr>

                <tr>

                    <td class="t_r lableTd">数量：</td>
                    <td colspan="3">
						${prize.quantity}
                    </td>
                </tr>
                

                </tbody>
                <tr class="tfoot nprint">
                    <td colspan="4" class="t_r"><input type="button" onclick="window.print();" value="打 印"/>&nbsp;
                        <input type="button" onclick="shut();" value="关 闭"/>&nbsp;</td>
                </tr>

            </table>
        </form>
    </div>
    <!--Table End-->
</div>
<div style="display:none;" id="departmentDialog" title="选择考评部门">
    <ul id="departmentTree" class="ztree"></ul>
</div>
</body>
</html>