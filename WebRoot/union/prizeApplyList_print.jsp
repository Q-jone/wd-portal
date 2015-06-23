<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.*"%>
<%@ page import="com.wonders.stpt.union.entity.bo.UnionMatch"%>
<%@ page import="com.wonders.stpt.union.entity.bo.prize.UnionPersonalPrize"%>
<%@ page import="com.wonders.stpt.union.entity.bo.prize.UnionTeamPrize"%>
<%@ page import="com.wonders.stpt.union.entity.bo.UnionPrize"%>

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

<script src="<%=path%>/js/jquery-1.7.1.js"></script>
<script src="<%=path%>/deptDoc/js/ztree/jquery.ztree.all-3.4.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/flick/jquery-ui-1.8.18.custom.min.js"></script>
<script src="<%=path%>/js/flick/jquery.ui.datepicker-zh-CN.js"></script>
<script src="<%=path%>/js/jquery.form.js"></script>
<script src="<%=path%>/js/jquery.formalize.js"></script>
	<script type="text/javascript">
	
    function showApplyView(id,type){
    	if(type=='1'){
    		window.open('<%=path%>/unionPersonalPrize/showView.action?id='+id);
    	}else if(type=='2'){
    		window.open('<%=path%>/unionTeamPrize/showView.action?id='+id);
    	}else if(type=='3'){
    		window.open('<%=path%>/unionProjectPrize/showView.action?id='+id);
    	}else if(type=='4'){
    		window.open('<%=path%>/unionAchivementPrize/showView.action?id='+id);
    	}
    }   
          
  		function shut(){
  		  window.opener=null;
  		  window.open("","_self");
  		  window.close();
  		}     
  		
    </script>

	<style>
		div.jqi{ width:770px}
		.ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }	
	</style>
	<style type="text/css" media=print>
	.nprint{display:none;}
	</style>
</head>

<body>
<form action="" id="form" method="post">
	<input type="hidden" name="id" value="${theme.id}"/>
    <input type="hidden" name="status" value="${theme.status}"/>
    <input type="hidden" name="checkRole" value="${checkRole}"/>
<div class="main f_bg">
    <div class="ctrl clearfix">
        <div class="fl"><img id="show" onclick="showHide();" src="<%=path%>/css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
        <div class="posi fl">
            <ul>
                <li class="fin">专项汇总表</li>
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
    <div class="pt45">

        <table width="100%"  class="table_1">
            <tr>
                <td style="text-align:center;">
                <h2>${theme.themeName}-${match.matchName}</h2>
                </td>
            </tr>       
        </table>

    </div>    
    
	<s:if test="null!=#request.appliedTeamPrizes && !#request.appliedTeamPrizes.isEmpty()"> 
    <div>

        <table width="100%"  class="table_1">
            <thead>
            	<th colspan="5" style="text-align:center;"><strong>先进集体</strong></th>
            </thead>
            <tbody>
            <tr class="tit">
                <td class="t_c" style="width:40px">序号</td>
                <td class="t_c" width="25%">奖项名称</td>
                <td class="t_c" width="25%">集体全称</td>
                <td class="t_c" width="25%">人数</td>
                <td class="t_c" width="20%">所属单位</td>
            </tr>            
            <s:iterator value="#request.appliedTeamPrizes" id="data" status="s">
            <tr>
                <td class="t_c" width="30"><s:property value="#s.index+1"/></td>
                <td class="t_c">${data.prize.prizeName}</td>
                <td class="t_c">${data.name}</td>
                <td class="t_c">${data.persons}</td>
                <td class="t_c">${data.deptName}</td>

            </tr>
            </s:iterator>
            </tbody>
        </table>

    </div>        
	</s:if>
	
	<s:if test="null!=#request.appliedPersonalPrizes && !#request.appliedPersonalPrizes.isEmpty()"> 
    <div>

        <table width="100%"  class="table_1">
            <thead>
            <th colspan="7" style="text-align:center;"><strong>先进个人</strong></th>
            </thead>
            <tbody>
            <tr class="tit">
                <td class="t_c" width="30">序号</td>
                <td class="t_c" width="20%">奖项名称</td>
                <td class="t_c" width="20%">姓名</td>
                <td class="t_c" width="10%">性别</td>
                <td class="t_c" width="20%">职位</td>
                <td class="t_c" width="10%">出生年月</td>
                <td class="t_c" width="15%">所属单位</td>
            </tr>            
            <s:iterator value="#request.appliedPersonalPrizes" id="data" status="s">
            <tr>
                <td class="t_c" width="30"><s:property value="#s.index+1"/></td>
                <td class="t_c">${data.prize.prizeName}</td>
                <td class="t_c">${data.name}</td>
                <td class="t_c">${data.gender}</td>
                <td class="t_c">${data.position}</td>
                <td class="t_c">${data.brithday}</td>
                <td class="t_c">${data.deptName}</td>

            </tr>
            </s:iterator>
            </tbody>
        </table>
        
    </div>
    </s:if>
    
    <s:if test="null!=#request.appliedProjectPrizes && !#request.appliedProjectPrizes.isEmpty()"> 
    <div>

        <table width="100%"  class="table_1">
            <thead>
            <th colspan="5" style="text-align:center;"><strong>优秀创新项目</strong></th>
            </thead>
            <tbody>
            <tr class="tit">
                <td class="t_c" width="30">序号</td>
                <td class="t_c" width="25%">奖项名称</td>
                <td class="t_c" width="25%">项目名称</td>
                <td class="t_c" width="25%">承担单位</td>
                <td class="t_c" width="20%">负责人</td>
            </tr>            
            <s:iterator value="#request.appliedProjectPrizes" id="data" status="s">
            <tr>
                <td class="t_c" width="30"><s:property value="#s.index+1"/></td>
                <td class="t_c">${data.prize.prizeName}</td>
                <td class="t_c">${data.prjectName}</td>
                <td class="t_c">${data.deptName}</td>
                <td class="t_c">${data.responsibilePerson}</td>

            </tr>
            </s:iterator>
            </tbody>
        </table>
        
    </div>    
    </s:if>
    
    <s:if test="null!=#request.appliedAchivementPrizes && !#request.appliedAchivementPrizes.isEmpty()">
    <div>

        <table width="100%"  class="table_1">
            <thead>
            <th colspan="6" style="text-align:center;"><strong>品牌成果</strong></th>
            </thead>
            <tbody>
            <tr class="tit">
                <td class="t_c" width="30">序号</td>
                <td class="t_c" width="20%">奖项名称</td>
                <td class="t_c" width="20%">项目名称</td>
                <td class="t_c" width="20%">参赛板块</td>
                <td class="t_c" width="20%">申报集体全称</td>
                <td class="t_c" width="15%">负责人</td>
            </tr>            
            <s:iterator value="#request.appliedAchivementPrizes" id="data" status="s">
            <tr>
                <td class="t_c" width="30"><s:property value="#s.index+1"/></td>
                <td class="t_c">${data.prize.prizeName}</td>
                <td class="t_c">${data.prjectName}</td>
                <td class="t_c">${data.module}</td>
                <td class="t_c">${data.groupName}</td>
                <td class="t_c">${data.responsibilePerson}</td>

            </tr>
            </s:iterator>
            </tbody>
        </table>
        
    </div>        
	</s:if>
	
    <div>
       <table width="100%"  class="table_1">
                <tr class="tfoot nprint">
                   <td colspan="7" style="text-align:center"><input type="button" onclick="window.print();" value="打 印"/>&nbsp;
                       <input type="button" onclick="shut();" value="关 闭"/>&nbsp;</td>
               </tr> 
		</table>
	</div>
</div>
</form>
<div style="display:none;" id="applyDepartmentDialog" title="分配名额">
</div>
</body>
</html>