<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method='html' version='1.0' encoding='UTF-8' indent='yes' />

  <xsl:template match="/">
  	<xsl:variable name="beginDept" select="Datas/TApprovedinfoList/TApprovedinfo[stepname='Begin']/dept"/>
 
<html lang="en">
<head>
 <meta http-equiv="X-UA-Compatible" content="IE=edge" charset="utf-8"  />
<title>上海申通地铁集团有限公司合同审批单</title>


	<link rel="stylesheet" href="css/reset.css" />
	<script>
	function doPrint(){
		document.getElementById("printBtn").style.display="none";
		window.print();
		document.getElementById("printBtn").style.display="inline";		
	}
	</script>	
	<style>
		body{
			background-color: #fff;
			line-height: 150%;
			margin: 0 auto;
		}
		.main{
			width: 920px;
			margin: 0 auto;
		}
		table.print{
			width: 100%;
			border: #000 1px solid;
			font-size: 12px;
		}
		table.print td{
			padding: 5px;
			line-height: 24px;
			border: #000 1px solid;
		}
		h1, h3{
			color: #cc1d01;
			line-height: 150%;
		}
		h3{
			font-weight: normal;
		}
		.dash_black{
			border-bottom: #090909 1px dashed;
		}
		.t_r{
			width: 15%;
		}
		.bg1{
			background-color: #e1e1e1;
		}
		.bg2{
			background-color: #eee;
		}
		pre{
			white-space: pre-wrap;       
			white-space: -moz-pre-wrap;  
			white-space: -pre-wrap;      
			white-space: -o-pre-wrap;    
			word-wrap: break-word;       
		}
		.subTitle{
			line-height:1.5;
			font-weight:bold;
		}
 

	</style>
</head>
<body>
	<div class="main">
		<div class="dash_black p8 t_c">
			<h1>上海申通地铁集团有限责任公司</h1>
			<h3>合同审批单</h3>
		</div>
		<div class="p8"></div>
		<table class="print mb10">
			<tr class="bg1">
				<td colspan="4"><b>合同审批单基本信息栏</b></td>
			</tr>
			<tr>
				<td class="t_r bg2">计划编号：</td>
				<td ><xsl:value-of select="Datas/BasicData/Htxx/PlanNum"/></td>
				<td class="t_r bg2">项目公司：</td>
				<td ><xsl:value-of select="Datas/BasicData/Htxx/ProjectCOName"/></td>
					
			</tr>
			<tr>
				<td class="t_r bg2">工程编号：	</td>
				<td ><xsl:value-of select="Datas/BasicData/Htxx/ProjectNum"/></td>
				<td class="t_r bg2">合同名称：	</td>
				<td ><xsl:value-of select="Datas/BasicData/Htxx/ContractName"/></td>
			</tr>
			<tr>
				<td class="t_r bg2">合同编号：	</td>
				<td colspan="3"><xsl:value-of select="Datas/BasicData/Htxx/ContractNum"/></td>
			</tr>
			<tr>
				<td class="t_r bg2">合同金额分类：	</td>
				<td ><xsl:value-of select="Datas/BasicData/Htxx/ContractMoneyType"/></td>
				<td class="t_r bg2">合同金额(万元)：	</td>
				<td ><xsl:value-of select="Datas/BasicData/Htxx/ContractMoney"/></td>
			</tr>
			<tr>
				<td class="t_r bg2">经办部门意见：	</td>
				<td colspan="3"><xsl:value-of select="Datas/BasicData/Htxx/DealDeptSuggest"/></td>
			</tr>
			<tr>
				<td class="t_r bg2">经办人：	</td>
				<td ><xsl:value-of select="Datas/BasicData/Htxx/DealPerson"/></td>
				<td class="t_r bg2">项目公司法人或<br></br>委托代理人:	</td>
				<td ><xsl:value-of select="Datas/BasicData/Htxx/DelegatePerson"/></td>
			</tr>
			<tr>
				<td class="t_r bg2">登记人：		</td>
				<td ><xsl:value-of select="Datas/BasicData/Htxx/AddPerson"/></td>
				<td class="t_r bg2">登记日期：	</td>
				<td ><xsl:value-of select="Datas/BasicData/Htxx/AddDate"/></td>
			</tr>
			<tr>
				<td class="t_r bg2">备注：	</td>
				<td colspan="3"><xsl:value-of select="Datas/BasicData/Htxx/Remark"/></td>
			</tr>
			<tr>
				<td class="t_r bg2">自用编号：</td>
				<td ><xsl:value-of select="Datas/BasicData/Htxx/SelfNum"/></td>
				<td class="t_r bg2">批文号：</td>
				<td ><xsl:value-of select="Datas/BasicData/Htxx/FileNum"/></td>
			</tr>
			<tr>
				<td class="t_r bg2">对方公司：</td>
				<td ><xsl:value-of select="Datas/BasicData/Htxx/SignCop"/></td>
				<td class="t_r bg2">预算(万元)：	</td>
				<td ><xsl:value-of select="Datas/BasicData/Htxx/Budget"/></td>
			</tr>
			<!-- <tr>
				
				<td colspan="4" style="width:100%">
				<div class="subTitle">经办部门意见：	</div>
				<pre><xsl:value-of select="Datas/BasicData/content"/></pre></td>
			</tr> -->
			
			<tr>
				
			</tr>
			<!-- <tr>
				<td class="t_r bg2">发起人</td>
				<td><xsl:value-of select="Datas/TApprovedinfoList/TApprovedinfo[stepname='Begin']/userfullname"/>
					</td>
				<td class="t_r bg2">发起部门</td>
				<td><xsl:value-of select="Datas/TApprovedinfoList/TApprovedinfo[stepname='Begin']/dept"/></td>
			</tr> -->
			<tr class="bg1">
				<td colspan="4"><b>合同审批单流转意见栏</b></td>
			</tr>
			<tr>
				<td colspan="4">
					<div class="subTitle">内部审核:</div>
					<xsl:for-each select="Datas/TApprovedinfoList/TApprovedinfo[stepname='申报部门领导审批']">
					<div>
						<i ><xsl:value-of select="dept"/></i>
						<div class="p8">
							<xsl:value-of select="remark"/>
						</div>
						<div class="p8 clearfix">
							
							<div class="fr mr5">
							<xsl:value-of select="upddateStr"/></div>	
							<div class="fr mr5"><xsl:value-of select="userfullname"/></div>
							<!-- <div class="fr mr5"><xsl:value-of select="dept"/></div> -->
							
						</div>
					</div>
					</xsl:for-each>
					<!-- <div class="p8 clearfix">
						<div class="fl w33p">经办人：<xsl:value-of select="Datas/BasicData/content_operator"/></div>
						<div class="fl w33p">签发：<xsl:value-of select="Datas/BasicData/content_signer"/></div>
						<div class="fl w33p">签章：<xsl:value-of select="Datas/BasicData/content_inscribe"/></div>
					</div> -->
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div class="subTitle">预审:</div>
					<xsl:for-each select="Datas/TApprovedinfoList/TApprovedinfo[stepname='预审']">
					<!-- <xsl:sort select="upddateStr" order="descending"/> -->
					<div>
						<i><xsl:value-of select="dept"/></i>
						<div class="p8"><xsl:value-of select="remark"/></div>
						<div class="p8 clearfix">
							<div class="fr mr5"><xsl:value-of select="upddateStr"/>-07-15</div>
							<div class="fr mr5"><xsl:value-of select="userfullname"/></div>
							<!--<div class="fr mr5">运管中心</div>-->
						</div>
					</div>
					</xsl:for-each>
					
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div class="subTitle">审核及处置:</div>
					<xsl:for-each select="Datas/TApprovedinfoList/TApprovedinfo[stepname='合约审核'
                                      or stepname='合约领导审批'
                                      or stepname='部门领导审核'
                                      or stepname='部门业务人员处理'
                                      or stepname='法律顾问审核'
                                      or stepname='部门业务经办人']">
					<div><!-- <xsl:variable name="beginDept"
							select="Datas/TApprovedinfoList/TApprovedinfo[stepname='Begin']/dept"/> -->
						 <i class="i-red"><xsl:value-of select="dept"/></i>
						<div class="p8"><xsl:value-of select="remark"/></div>
						<div class="p8 clearfix">
							<div class="fr mr5"><xsl:value-of select="upddateStr"/></div>
							<div class="fr mr5">
								<xsl:value-of select="userfullname"/>
							</div>
							<!-- <div class="fr mr5"><xsl:value-of select="dept"/></div> -->
						</div>
					</div>
					  </xsl:for-each>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div class="subTitle">审批:</div>
					<xsl:for-each select="Datas/TApprovedinfoList/TApprovedinfo[stepname='集团领导']">
					<!-- <xsl:sort select="upddateStr" order="descending"/> -->
					<div>
						<i><xsl:value-of select="dept"/></i>
						<div class="p8"><xsl:value-of select="remark"/></div>
						<div class="p8 clearfix">
							<div class="fr mr5"><xsl:value-of select="upddateStr"/>-07-15</div>
							<div class="fr mr5"><xsl:value-of select="userfullname"/></div>
							<!--<div class="fr mr5">运管中心</div>-->
						</div>
					</div>
					</xsl:for-each>
					
				</td>
			</tr>
			<tr class="bg1">
				<td colspan="4"><b>备案信息</b></td>
			</tr>
			<tr>
				<td class="t_r bg2">审批通过日期：	</td>
				<td><xsl:value-of select="Datas/BasicData/Htba/examinePassTime"/>	</td>
				<td class="t_r bg2">正式签约日期：	</td>
				<td><xsl:value-of select="Datas/BasicData/Htba/contractSignTime"/></td>

			</tr>
			<tr>
				<td class="t_r bg2">合同履行期限：		</td>
				<td colspan="3" ><xsl:value-of select="Datas/BasicData/Htba/performTimelimit"/>天	</td>

			</tr>
			<tr>
				<td class="t_r bg2">备注：		</td>
				<td colspan="3" ><xsl:value-of select="Datas/BasicData/Htba/remark"/>	</td>

			</tr>
			<tr>
				<td class="t_r bg2" >审批通过的合同与备案合同条款是否一致：		</td>
				<td colspan="3" ><xsl:value-of select="Datas/BasicData/Htba/ifSame"/>	</td>

			</tr>
			<tr>
				<td colspan="4">
					<div class="subTitle">办理情况:</div>
					<xsl:for-each select="Datas/TApprovedinfoList/TApprovedinfo[stepname='办结']">
					<!-- <xsl:sort select="upddateStr" order="descending"/> -->
					<div>
						<i><xsl:value-of select="dept"/></i>
						<div class="p8"><xsl:value-of select="remark"/></div>
						<div class="p8 clearfix">
							<div class="fr mr5"><xsl:value-of select="upddateStr"/>-07-15</div>
							<div class="fr mr5"><xsl:value-of select="userfullname"/></div>
							<!--<div class="fr mr5">运管中心</div>-->
						</div>
					</div>
					</xsl:for-each>
					
				</td>
			</tr>
			
			
			<!-- <tr>
				<td colspan="4">
					<div>备注</div>
					<div>
						<br></br>
						<br></br>
						<br></br>
					</div>
				</td>
			</tr> -->
		</table>
		<div>
			注：<br></br>
			1、纸质影印件仅供参考，实际内容以综合业务协同平台网上为准；<br></br>
			2、纸质归档件需加盖本单位电子文件打印专用章。
		</div>
	</div>
	<div class="mb10 t_c">
       <input type="button" value="打印"  id="printBtn" onclick="javascript:doPrint();"/>
    </div>
</body>
</html>
    <!--html结束 -->
</xsl:template>
</xsl:stylesheet>