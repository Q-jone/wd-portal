<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.wonders.stpt.exam.entity.ExamGroup"  %>    
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>试卷管理</title>
<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/default/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
<link rel="stylesheet" href="../exam/ui/js/validation/validationEngine.css"/> 
<script src="../js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="../exam/ui/js/validation/validationEngine.js"></script> 
<!--[if IE 6.0]>
           <script src="../js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
	<script type="text/javascript">
        $(document).ready(function () {
        	$("#button2").click(function(){
        		window.open("add.action");
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
       
    </script>



</head>
<% 
String mainId = (String) request.getParameter("mainId");

ExamGroup examGroup = (ExamGroup) request.getAttribute("examGroup");
%>
<body style="background:#fff">
	<font color="red"><b><%=examGroup==null?"新增分组":"修改分组"%></b></font><br>
	<s:form action="showGroup.action" id="form" method="post" namespace="/exam">
	    	<input type="hidden" name="op" value="<%=examGroup!=null?"update":"add"%>" />
	    	<input type="hidden" name="id" value="<%=examGroup!=null?examGroup.getId():""%>" />
	    	<input type="hidden" name="mainId" value="<%=mainId%>" />
		    <table class="table_1" width="100%" align="center">
		    	<tr>
		    		<td class="thx">分组名称</td>
		    		<td><input type="text" maxlength="50" name="title" value="<%=examGroup!=null&&examGroup.getTitle()!=null?examGroup.getTitle():""%>" />  </td>
		    		<td class="thx">次序号</td>
		    		<td><input type="text" maxlength="2" class="validate[custom[onlyNumber]]" name="groupNum" value="<%=examGroup!=null?examGroup.getGroupNum():""%>" />  </td>
		    	</tr>
		    	<tr>
		    		<td colspan="4" style="text-align:center">
		    			<input type="submit" value="<%=examGroup!=null?"修 改":"新 增"%>" class="btn" />
		    			<input type="reset" value=" 重 置 " class="btn" />
		    		</td>
		    	</tr>
		    </table>
	</s:form>
    <br/>
	<table class="table_1" width="100%">
	<tbody>
		<tr class="tit">
			<td class="t_c" width="60%">分组名称</td>
			<td class="t_c" width="20%">次序</td>
			<td class="t_c" width="20%">操作</td>
		</tr>
		<% 
		@SuppressWarnings("unchecked")
		List<ExamGroup> list = (List<ExamGroup>)request.getAttribute("list");
		if(list!=null && list.size()>0){
			for(ExamGroup group : list){
		%>
			<tr>
				<td><%=group.getTitle()!=null?group.getTitle():""%></td>
				<td style="text-align:center"><%=group.getGroupNum()%></td>
				<td style="text-align:center">
					<a style="display:inline;" href="showGroup.action?op=load&id=<%=group.getId()%>&mainId=<%=mainId%>">修改</a>
					-
					<a style="display:inline;" href="showGroup.action?op=delete&id=<%=group.getId()%>&mainId=<%=mainId%>" onclick="return window.confirm('确定要删除吗？\n这将影响到分组下的试题，且不可恢复。\n请谨慎操作。');">删除</a>
				</td>
			</tr>
		<% 
			}
		}
		%>
		</tbody>
	</table>	  
</body>
</html>