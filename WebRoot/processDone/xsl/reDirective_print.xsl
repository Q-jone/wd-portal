<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method='html' version='1.0' encoding='UTF-8' indent='yes' />

  <xsl:template match="/">
  	<xsl:variable name="beginDept" select="Datas/TApprovedinfoList/TApprovedinfo[stepname='Begin']/dept"/>
  
<html lang="en">
<head>
 <meta http-equiv="X-UA-Compatible" content="IE=edge" charset="utf-8"  />
<title>上海申通地铁集团有限公司呈批文件单</title>


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
			<h3>呈批文件单</h3>
		</div>
		<ul style="text-align:center;line-height:250%">
			<ui class="mr5 fl">呈报部门：<xsl:value-of select="Datas/BasicData/submitdept"/></ui>
			<ui >呈报日期：<xsl:value-of select="Datas/BasicData/submitdate"/></ui>
			<ui class="mr5 fr">部门编号：<xsl:value-of select="Datas/BasicData/deptid"/></ui>
		</ul>
		
		<table class="print mb10">
			<tr class="bg1">
				<td colspan="4"><b>呈批文件单基本信息栏</b></td>
			</tr>
			<tr>
				<td class="t_r bg2">标题</td>
				<td colspan="3"><xsl:value-of select="Datas/BasicData/title"/></td>
				
			</tr>
			<tr>
				<td class="t_r bg2">关键词</td>
				<td ><xsl:value-of select="Datas/BasicData/drSecretClass"/></td>
				
				<td class="t_r bg2">缓急</td>
				<td ><xsl:value-of select="Datas/BasicData/doclevel"/></td>
			</tr>
			<tr>
				<td colspan="4" >主要内容:<br></br>
				<xsl:if test="Datas/BasicData/content">
				
				<pre>
				<xsl:value-of select="Datas/BasicData/content"/>
				</pre>
				</xsl:if>
				

				<div class="p8 clearfix fr">
						<div >经办人：<xsl:value-of select="Datas/BasicData/operator"/>
						<xsl:variable name="a">  </xsl:variable><!-- <xsl:text>  </xsl:text> -->
						<!-- &#0x20; -->
						 部门负责人：<xsl:value-of select="Datas/BasicData/deptmaster"/></div>
						
						<!-- <div class="fl w33p">签发：<xsl:value-of select="Datas/BasicData/content_signer"/></div> -->
						<div > </div>
				</div>
				</td>
			</tr>
			
			
			
			<tr class="bg1">
				<td colspan="4"><b>呈批文件单流转意见栏</b></td>
			</tr>
			<tr>
				<td colspan="4">
					<div class="subTitle">拟办意见：</div>
					<xsl:for-each select="Datas/TApprovedinfoList/TApprovedinfo[stepname='拟办人']">
						<xsl:sort select="upddate" order="descending"/>
					<div>
						<i class="i-red"><xsl:value-of select="dept"/></i>
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
					
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div class="subTitle">会稿部门意见:</div>
					<xsl:for-each select="Datas/TApprovedinfoList/TApprovedinfo[stepname='部门领导审核'
						or stepname='部门业务人员处理']">
					<xsl:sort select="upddate" />
					<div>
						<i><xsl:value-of select="dept"/></i>
						<div class="p8"><xsl:value-of select="remark"/></div>
						<div class="p8 clearfix">
							<div class="fr mr5"><xsl:value-of select="upddateStr"/></div>
							<div class="fr mr5"><xsl:value-of select="userfullname"/></div>
							<!--<div class="fr mr5">运管中心</div>-->
						</div>
					</div>
					</xsl:for-each>
					
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div class="subTitle">公司分管领导批示：</div>
					<xsl:for-each select="Datas/TApprovedinfoList/TApprovedinfo[stepname='领导' ]">
					<xsl:sort select="upddate" order="descending"/>
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
				<td colspan="2">
					<div class="subTitle">总裁批示：</div>
					 <xsl:for-each select="Datas/TApprovedinfoList/TApprovedinfo[stepname='']">
	                      <xsl:sort select="upddate" order="descending"/>
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
				<td colspan="2">
					<div class="subTitle">董事长批示：</div>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div class="subTitle">办理情况：</div>
					 <xsl:for-each select="Datas/TApprovedinfoList/TApprovedinfo[stepname='办结' ]">
					 	<xsl:sort select="upddate" order="descending"/>
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