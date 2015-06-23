<?xml version="1.0" encoding="utf-8"?>
  <!-- Edited with XML Spy v2007 (http://www.altova.com) -->
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method='html' version='1.0' encoding='UTF-8' indent='yes' />

  <xsl:template match="/">

<html lang="en">
<head >
<meta http-equiv="X-UA-Compatible" content="IE=edge" charset="utf-8" /><!-- 解决IE出现杂项问题-->
<title>发文单</title>

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
		table.print td, table.print2 td{
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
		.t_r, .w15p{
			width: 15%;
		}
		.bg1{
			background-color: #e1e1e1;
		}
		.bg2{
			background-color: #eee;
		}
		table.print2{
			width: 100%;
			border: #000 1px solid;
			font-size: 12px;
			border-bottom: none;
		}
		

	</style>
</head>
<body>
	<div class="main">
		<div class="p8 t_c">
			<h1>上海申通地铁集团有限责任公司</h1>
			<h3>发文单</h3>
		</div>
		<table class="print2">
			<tr class="bg1">
				<td colspan="4">
					<h5 class="fl">发文字号：</h5>
					<span class="fl"><xsl:value-of select="Datas/BasicData/sendId"/></span>
					<div class="fr">
						<span class="mr5 fl">文别：<xsl:value-of select="Datas/BasicData/docClass"/></span>
						<span class="mr5 fl">密别：<xsl:value-of select="Datas/BasicData/secretClass"/></span>
						<span class="mr5 fl">缓急：<xsl:value-of select="Datas/BasicData/hj"/></span>
					</div>
				</td>
			</tr>
			<tr>
				<td class="w15p bg2">文件类型</td>
				<td><xsl:value-of select="Datas/BasicData/fileType"/></td>
				<td class="w15p bg2">保密期限</td>
				<td>
					<xsl:value-of select="Datas/BasicData/secretLimit"/>
					<xsl:if test="Datas/BasicData/secretLimit">
					年
					</xsl:if>
				</td>
			</tr>
		</table>
		<table class="print2">
			<tr>
				<td width="33.3%">
					<div>
						<div>签发：</div>
						<div><xsl:value-of select="Datas/TApprovedinfoList/TApprovedinfo[stepname='签发领导']/remark"/></div>
						<div class="fr"><xsl:value-of select="Datas/TApprovedinfoList/TApprovedinfo[stepname='签发领导']/userfullname"/></div>
						<div class="clear"></div>
						<div class="fr"><xsl:value-of select="Datas/TApprovedinfoList/TApprovedinfo[stepname='签发领导']/upddateStr"/></div>
					</div>
				</td>
				<td width="33.3%">
					<div>
						<div>审阅：</div>
						<div></div>
						<div class="fr"></div>
						<div class="clear"></div>
						<div class="fr"></div>
					</div>
				</td>
				<td>
					<div>
						<div>会签：</div>
						<div><xsl:value-of select="Datas/TApprovedinfoList/TApprovedinfo[stepname='领导']/remark"/></div>
						<div class="fr"><xsl:value-of select="Datas/TApprovedinfoList/TApprovedinfo[stepname='领导']/userfullname"/></div>
						<div class="clear"></div>
						<div class="fr"><xsl:value-of select="Datas/TApprovedinfoList/TApprovedinfo[stepname='领导']/upddateStr"/></div>
					</div>
				</td>
			</tr>
			<tr class="bg2">
				<td class="t_c">拟稿部门</td>
				<td class="t_c">会签部门</td>
				<td class="t_c">核稿部门</td>
			</tr>
			<tr>
				<td>
					
					<div>
						<div>拟稿人：</div>
						<div><xsl:value-of select="Datas/TApprovedinfoList/TApprovedinfo[stepname='起草']/remark"/></div>
						<div class="clearfix">
							<div class="fr mr5">
								<xsl:value-of select="Datas/TApprovedinfoList/TApprovedinfo[stepname='起草' or stepname='拟稿人修改']/upddateStr"/>
							</div>
							<!--拟稿人修改 -->
							<div class="fr mr5">
								<xsl:value-of select="Datas/TApprovedinfoList/TApprovedinfo[stepname='起草' or stepname='拟稿人修改']/userfullname"/>
							</div>
						</div>
					</div>
				</td>
				<td></td>
				<td>
					<div>
						<div>核稿人：</div>
						<div><xsl:value-of select="Datas/TApprovedinfoList/TApprovedinfo[stepname='核稿']/remark"/></div>
						<div class="clearfix">
							<div class="fr mr5"><xsl:value-of select="Datas/TApprovedinfoList/TApprovedinfo[stepname='核稿']/upddateStr"/></div>
							<div class="fr mr5"><xsl:value-of select="Datas/TApprovedinfoList/TApprovedinfo[stepname='核稿']/userfullname"/></div>
						</div>
					</div>
					
				</td>
			</tr>
		</table>
		<table class="print2">
			<tr>
				<td class="w15p bg2">标题：</td>
				<td><xsl:value-of select="Datas/BasicData/docTitle"/></td>
			</tr>
			<tr>
				<td class="w15p bg2">主送单位：</td>
				<td><xsl:if test="Datas/BasicData/sendMainW"> 
					<xsl:value-of select="Datas/BasicData/sendMainW"/><br></br> 
					</xsl:if>
					<xsl:value-of select="Datas/BasicData/sendMain"/>
				</td>
			</tr>
			<tr>
				<td class="w15p bg2">抄送单位：</td>
				<td>
					<xsl:if test="Datas/BasicData/sendCopy"> 
					<xsl:value-of select="Datas/BasicData/sendCopy"/> <br></br>
					</xsl:if>
					<xsl:value-of select="Datas/BasicData/sendInside"/>
				</td>
			</tr>
			<tr>
				<td class="w15p bg2">内发：</td>
				<td><xsl:value-of select="Datas/BasicData/sendReportW"/></td>
			</tr>
			<!-- <tr>
				<td colspan="2" class="bg2">相关附件：</td>
			</tr>
			<tr>
				<td colspan="2">
					<table width="100%" cellpadding="2" cellspacing="0" style="font-size:12px">
						<xsl:if test="Datas/AttachFileList/AttachFile/fileName ">
						
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
					 </xsl:if>
					</table>
				</td>
			</tr >-->
			<tr>
				<td class="w15p bg2">校对：</td>
				<td><xsl:value-of select="Datas/TApprovedinfoList/TApprovedinfo[stepname='校稿']/remark"/></td>
			</tr>
			<tr>
				<xsl:variable name="attachMainPath"
				select="Datas/BasicData/AttMain/AttachFileList/AttachFile[1]/path" />
				
				<td class="w15p bg2">正式文件：</td>
				<td>
					<xsl:variable name="AttachMainFileId"
							select="Datas/BasicData/AttMain/AttachFileList/AttachFile[1]/id" />
					<a href="http://10.1.44.18/downloadFile.action?fileId={$AttachMainFileId}">
						<xsl:value-of select="Datas/BasicData/AttMain/AttachFileList/AttachFile[1]/fileName"/>
					</a>
				</td>
			</tr>
		</table>
		<table class="print2">
			<tr>
				<td width="50%">印制份数：<xsl:value-of select="Datas/BasicData/docCount"/></td>
				<td>印发日期:<xsl:value-of select="Datas/BasicData/sendDate"/></td>
			</tr>
		</table>
		<table class="print2">
			<tr>
				<td class="w15p bg2">办理情况：</td>
				<td></td>
			</tr>
			<tr>
				<td class="w15p bg2">套头意见：</td>
				<td><xsl:value-of select="Datas/TApprovedinfoList/TApprovedinfo[stepname='套头']/remark"/></td>
			</tr>
			<tr>
				<td class="w15p bg2">办结人意见：</td>
				<td><xsl:value-of select="Datas/TApprovedinfoList/TApprovedinfo[stepname='办结']/remark"/></td>
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