<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method='html' version='1.0' encoding='UTF-8' indent='yes' />

  <xsl:template match="/">
  	<xsl:variable name="beginDept" select="Datas/TApprovedinfoList/TApprovedinfo[stepname='Begin']/dept"/>
 
<html lang="en">
<head>
 <meta http-equiv="X-UA-Compatible" content="IE=edge" charset="utf-8"  />
<title>上海申通地铁集团有限公司工作联系单</title>


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
			<h1>上海申通地铁集团有限公司</h1>
			<h3>工作联系单</h3>
		</div>
		<div class="p8">编号：<xsl:value-of select="Datas/BasicData/serial"/></div>
		<table class="print mb10">
			<tr class="bg1">
				<td colspan="4"><b>工作联系单基本信息栏</b></td>
			</tr>
			<tr>
				<td class="t_r bg2">主送部门</td>
				<td colspan="3"><xsl:value-of select="Datas/BasicData/main_unit"/></td>
			</tr>
			<tr>
				<td class="t_r bg2">抄送部门</td>
				<td colspan="3"><xsl:value-of select="Datas/BasicData/copy_unit"/></td>
			</tr>
			<tr>
				<td class="t_r bg2">主题</td>
				<td colspan="3"><xsl:value-of select="Datas/BasicData/theme"/></td>
			</tr>
			<tr>
				<td class="t_r bg2">联系时间</td>
				<td><xsl:value-of select="Datas/BasicData/contact_date"/></td>
				<td class="t_r bg2">要求回复时间</td>
				<td><xsl:value-of select="Datas/BasicData/reply_date"/></td>
			</tr>
			<tr>
				<!-- <td class="t_r bg2">联系内容</td> -->
				<td colspan="4" style="width:100%">
				<div class="subTitle">联系内容:</div>
				<pre><xsl:value-of select="Datas/BasicData/content"/></pre></td>
			</tr>
			
			<tr>
				<!-- <td colspan="4">
					<table width="100%" cellpadding="2" cellspacing="0" style="font-size:12px">
						
						<tr>
						<td align='center' nowrap="nowrap" width='40%'>
							<font><b>文件名</b> </font>
						</td>
						<td align='center' nowrap="nowrap" width='5%'>
							<font><b>大小</b> </font>
						</td>
						<td align='center' nowrap="nowrap" width='20%'>
							<font><b>上传时间</b> </font>
						</td>
						<td align='center' nowrap="nowrap" width='5%'>
							<font><b>上传人</b> </font>
						</td>
						<td align='center' nowrap="nowrap" width='10%'>
							<font><b>版本</b> </font>
						</td>
						<td align='center' nowrap="nowrap" width='15%'>
							<font><b>备注</b> </font>
						</td>
						</tr>
					 <xsl:for-each select="Datas/AttachFileList/AttachFile">
					 	
					 	<tr>
						<td style="line-height: 15px">
							<xsl:variable name="AttachFileId"
							select="id" />
							
							<a href="http://10.1.44.18/downloadFile.action?fileId={$AttachFileId}" target="_blank"
								style="display: inline;">
								<xsl:value-of select="fileName"/>
								
								.
								
								<xsl:value-of select="fileExtName"/></a>
							
						</td>
						<td style="line-height: 15px;">
							<xsl:value-of select="fileSize"/> K</td>
						<td style="line-height: 15px"><xsl:value-of select="uploadDate"/> </td>
						<td style="line-height: 15px"><xsl:value-of select="uploader"/> </td>
						
						
						<td style="line-height: 15px">
							v<xsl:value-of select="version"/></td>
						
						
						<td style="word-break: break-all">
							<xsl:value-of select="memo"/>

						</td>
					</tr>
					
					 </xsl:for-each>
					
					</table>
				</td> -->
			</tr>
			<!-- <tr>
				<td class="t_r bg2">发起人</td>
				<td><xsl:value-of select="Datas/TApprovedinfoList/TApprovedinfo[stepname='Begin']/userfullname"/>
					</td>
				<td class="t_r bg2">发起部门</td>
				<td><xsl:value-of select="Datas/TApprovedinfoList/TApprovedinfo[stepname='Begin']/dept"/></td>
			</tr> -->
			<tr class="bg1">
				<td colspan="4"><b>工作联系单流转意见栏</b></td>
			</tr>
			<tr>
				<td colspan="4">
					<div class="subTitle">签发意见：</div>
					<xsl:for-each select="Datas/TApprovedinfoList/TApprovedinfo[stepname='部门内部签发' or stepname='返回修改']">
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
					<div class="p8 clearfix">
						<div class="fl w33p">经办人：<xsl:value-of select="Datas/BasicData/content_operator"/></div>
						<div class="fl w33p">签发：<xsl:value-of select="Datas/BasicData/content_signer"/></div>
						<div class="fl w33p">签章：<xsl:value-of select="Datas/BasicData/content_inscribe"/></div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div class="subTitle">主送部门内部意见：</div>
					<xsl:for-each select="Datas/TApprovedinfoList/TApprovedinfo[stepname='部门业务人员处理' 
						or stepname='部门接受人工作分发' 
						or stepname='部门领导审核'
						  ]">
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
					<div class="subTitle">返回发起部门内部意见</div>
					 <xsl:for-each select="Datas/TApprovedinfoList/TApprovedinfo[stepname='部门领导审核' and dept= $beginDept]">
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
					<div>备注</div>
					<div>
						<br></br>
						<br></br>
						<br></br>
					</div>
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