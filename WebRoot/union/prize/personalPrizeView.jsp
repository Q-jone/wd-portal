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
<link rel="stylesheet" href="../css/reset.css" />

<script src="<%=path%>/js/jquery-1.7.1.js"></script>

	<script type="text/javascript">
        $(document).ready(function () {
            
        });
        
  		function shut(){
  		  window.opener=null;
  		  window.open("","_self");
  		  window.close();
  		}          
    </script>

<style type="text/css">
body {
	font-size: 14px;
}

.l-table-edit {
	
}

.l-table-edit-td {
	padding: 4px;
}

.l-button-submit,.l-button-test {
	width: 80px;
	float: left;
	margin-left: 10px;
	padding-bottom: 2px;
}

.l-verify-tip {
	left: 230px;
	top: 120px;
}
.l-table-edit-td input{width:180px}
.a_title{font-size:20px; font-weight:bold;text-align:center;}
.a_applyman{width:80%; text-align:right;padding:5px;margin-bottom:5px;}
.a_table{border-top:1px solid #000000;border-left:1px solid #000000;}
.a_table td{border-bottom:1px solid #000000;border-right:1px solid #000000;padding:5px;}
.inTable td{border:none;padding:5px;padding-left:25px;}
.inTable .intitle{font-size:20px; font-weight:bold;text-align:center;margin:10px;}
.a_bottom{font-size:12px; text-align:left;margin:10px;width:829px;}
.a_label{float:left;}
.a_label_r{float:right;}
.textareacon{padding-left:30px;margin-top:5px;margin_bottom:5px;}
.textlinecon{padding-left:30px;margin-top:5px;margin_bottom:5px;height:20px;line-height:20px;}
.textareacon textarea{width:86%; margin-top:5px;margin_bottom:5px;}
.padding10{padding-left:10px;}
.changePre{color: #999999;}
</style>

<style type="text/css" media=print>
.nprint{display:none;}
</style>
</head>

<body>
<center>
	<form name="form" method="post" id="form">
		<div class="a_title">${prize.match.theme.year}年上海轨道交通${prize.match.matchName}</div>
		<div class="a_title">先进个人审批表</div>
		<table width="829" border="0" class="a_table">
			<tr>
				<td style="text-align:center;" width="10%">姓名</td>
				<td align="left" width="10%">
					${personalPrize.name}
				</td>
				<td style="text-align:center;" width="10%">性别</td>
				<td align="left" width="10%">
					${personalPrize.gender}
				</td>
				<td style="text-align:center;" width="15%">出生年月</td>
				<td align="left" width="15%">
					${personalPrize.brithday}
				</td>
				<td style="text-align:center;" width="15%">政治面貌</td>
				<td align="left" width="15%">
					${personalPrize.political}
				</td>				
			</tr>	
			<tr>
				<td colspan="2" style="text-align:center;">工作单位</td>
				<td colspan="6" align="left">
					${personalPrize.deptName}
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align:center;">职务</td>
				<td colspan="6" align="left">
					${personalPrize.position}
				</td>
			</tr>			
			<tr style="height:60px;">
				<td style="text-align:center;vertical-align:middle;">曾获<br>荣誉</td>
				<td colspan="7" align="left">
					${personalPrize.prizeInfo}
				</td>
			</tr>			
			<tr style="height:180px;">
				<td style="text-align:center;vertical-align:middle;">主要<br>事迹</td>
				<td colspan="7" align="left">
					${personalPrize.introduct}
				</td>
			</tr>						
			<s:if test="#request.approvals!=null">
			<tr style="height:100px;">
				<td colspan="8" style="padding:0px;">
				<table style="border-collapse: collapse;border: 0px solid #000;width:100%;height:150px;">
					<tr>
						<td style="border-top: 0;border-right: 1px solid #000;border-bottom: 0;border-left: 0;width:50%;position:relative;padding:2px;padding-left:4px;">
							<div>
								所属单位意见<br>
								${approvals.deptSuggest}
							</div>
							<div style="position:absolute; right:34px; bottom:18px;">
								盖&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;章
							</div>
							<div style="position:absolute; right:4px; bottom:0">
								<s:property value="#request.approvals.deptPassDate.substring(0,4)"/>&nbsp;年&nbsp;<s:property value="#request.approvals.deptPassDate.substring(5,7).replaceAll('0','')"/>&nbsp;月&nbsp;<s:property value="#request.approvals.deptPassDate.substring(8,10)"/>&nbsp;日
							</div>						
						</td>
						<td style="border:0px;position:relative;padding:2px;padding-left:4px;">
							<div>
								${prize.match.matchName}考评小组意见<br>
								${approvals.assessSuggest}
							</div>
							<div style="position:absolute; right:34px; bottom:18px;">
								盖&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;章
							</div>
							<div style="position:absolute; right:4px; bottom:0">
								<s:property value="#request.approvals.assessPassDate.substring(0,4)"/>&nbsp;年&nbsp;<s:property value="#request.approvals.assessPassDate.substring(5,7).replaceAll('0','')"/>&nbsp;月&nbsp;<s:property value="#request.approvals.assessPassDate.substring(8,10)"/>&nbsp;日
							</div>					
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td colspan="8" style="height:150px;position:relative;padding:2px;padding-left:4px;">
					<div>
						${prize.match.matchName}领导小组意见<br>
						${approvals.leadSuggest}
					</div>
					<div style="position:absolute; right:34px; bottom:18px;">
						盖&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;章
					</div>
					<div style="position:absolute; right:4px; bottom:0">
						<s:property value="#request.approvals.leadPassDate.substring(0,4)"/>&nbsp;年&nbsp;<s:property value="#request.approvals.leadPassDate.substring(5,7).replaceAll('0','')"/>&nbsp;月&nbsp;<s:property value="#request.approvals.leadPassDate.substring(8,10)"/>&nbsp;日
					</div>						
				</td>
			</tr>	
			</s:if>		
			<s:else>
			<tr style="height:100px;">
				<td colspan="8" style="padding:0px;">
				<table style="border-collapse: collapse;border: 0px solid #000;width:100%;height:150px;">
					<tr>
						<td style="border-top: 0;border-right: 1px solid #000;border-bottom: 0;border-left: 0;width:50%;position:relative;padding:2px;padding-left:4px;">
							<div>
								所属单位意见<br>
								${approvals.deptSuggest}
							</div>
							<div style="position:absolute; right:24px; bottom:18px;">
								盖&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;章
							</div>
							<div style="position:absolute; right:4px; bottom:0">
								年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
							</div>						
						</td>
						<td style="border:0px;position:relative;padding:2px;padding-left:4px;">
							<div>
								${prize.match.matchName}考评小组意见<br>
								${approvals.assessSuggest}
							</div>
							<div style="position:absolute; right:24px; bottom:18px;">
								盖&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;章
							</div>
							<div style="position:absolute; right:4px; bottom:0">
								年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
							</div>					
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td colspan="8" style="height:150px;position:relative;padding:2px;padding-left:4px;">
					<div>
						${prize.match.matchName}领导小组意见<br>
						${approvals.leadSuggest}
					</div>
					<div style="position:absolute; right:24px; bottom:18px;">
						盖&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;章
					</div>
					<div style="position:absolute; right:4px; bottom:0">
						年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
					</div>						
				</td>
			</tr>			
			</s:else>						
		</table>
		<!-- 注：1、非招标类项目由责任部门负责；合同总价金额在100万元以上需填写此表，由责任部门分管领导审签并盖部门章。
    2、涉及法律问题需征求企管部意见；涉及公司开发票的建议征求财务部意见；在关联部门栏内填写。
    3、合同签订完成后，责任部门统一报集团备案、存档。
		 -->
<!-- 		<div class="a_bottom">
			<p>1、用word。</p>
	      <p style="padding-left:24px;">2、涉及法律问题需征求企管部意见；涉及公司开发票的建议征求财务部意见；在关联部门栏内填写。</p>
	      <p style="padding-left:24px;">3、合同签订完成后，责任部门统一报集团备案、存档。</p>
		</div> -->
		<div class="a_bottom nprint" style="text-align:center;">
			<a href="javascript:window.print();">打印</a>
		</div>
	</form>
</center>
</body>
</html>