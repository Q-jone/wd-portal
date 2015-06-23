<?xml version="1.0" encoding="utf-8"?>
  <!-- Edited with XML Spy v2007 (http://www.altova.com) -->
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method='html' version='1.0' encoding='UTF-8' indent='yes' />

  <xsl:template match="/">
<html lang="en">
<head>
 <meta http-equiv="X-UA-Compatible" content="IE=edge" charset="utf-8" />
<title>上海申通地铁集团有限公司发文单</title>
<link rel="stylesheet" href="css/formalize.css" />
<link rel="stylesheet" href="css/page.css" />
<link rel="stylesheet" href="css/default/imgs.css" />
<link rel="stylesheet" href="css/reset.css" />
		<link type="text/css" href="css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
		<!--[if IE 6.0]>
           <script src="js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
	


</head>

<body class="Flow">
<div class="f_bg_fw">
    <div class="w850">
    	<div class="logo_2"></div>
    </div>
      <div class="gray_bg">
          <!--Panel_6-->  
        <div class="Divab1">
          <!--1st-->
            <div class="panel_6">
              <div class="divT">
                <div class="mb10 icon icon_1"></div>
                <div class="more_4"><a href="#" title="更多">更多</a></div>
              </div>
              <div class="divH">
                <div class="divB">
                  <h5 class="clearfix">业务办理</h5>
                  <div class="con">
                    <ul class="button clearfix">
                      <li><a href="#" class="ywbl">业务办理</a></li>
                      <li>
                        <a href="#" target="blank" class="print">
                          打印</a></li>
                      <li><a href="#" class="jk">业务监控</a></li>
                      <li><a href="#" class="tips">小提示</a></li>
                      <li><a href="#" class="imp">公文导入</a></li>
                      <li><a href="#" class="exp">公文导出</a></li>
                    </ul>
                  </div>
                </div>
                <div class="divF"></div>
              </div>
            </div>
            <!--1st End-->
        </div>
      <!--Divab1 End-->  
        	<div class="gray_bg2">
            	<div class="w_bg">
                	<div>
                    	<div class="Top_fw">
                        	<h1 class="t_c">上海申通地铁集团有限公司<br></br>发文单</h1>
                                                        <div class="mb10 Step clearfix">
                              <ul class="clearfix">
       <li class="fst on">
           <dl>
               <dt></dt>
                 <dd>登记阶段</dd>
              </dl>
         </li>
       <li >
           <dl>
               <dt></dt>
                 <dd>拟办阶段</dd>
              </dl>
         </li>
       <li>
           <dl>
               <dt></dt>
                 <dd>批办阶段</dd>
              </dl>
         </li>
       <li>
           <dl>
               <dt></dt>
                 <dd>处理阶段</dd>
              </dl>
         </li>
         <li class="fin">
           <dl>
               <dt></dt>
                 <dd>办结阶段</dd>
             </dl>
         </li>
     </ul>

                            </div>
                            <div class="mb10">
                           	   <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_4">
                                  <thead>
                                  <th colspan="4">
                                      <h5 class="fl">发文字号：</h5><span class="fl"><xsl:value-of select="root/Datas/BasicData/sendId"/></span>
                                        <span class="fr clearfix">
                                          <h6 class="fl">文别：</h6><span class="fl mr8"><xsl:value-of select="root/Datas/BasicData/docClass"/></span>
                                          <h6 class="fl">密别：</h6><span class="fl mr8"><xsl:value-of select="root/Datas/BasicData/secretClass"/></span>
                                          <h6 class="fl">缓急：</h6><span class="fl mr8"><xsl:value-of select="root/Datas/BasicData/hj"/></span>
                                        </span>
                                    </th>
                                    </thead>
                                  <tr>
                                    <td class="lableTd" width="15%">文件类型</td>
                                    <td ><xsl:value-of select="root/Datas/BasicData/fileType"/></td>
                                    <td class="lableTd" width="15%">保密期限</td>
                                    <td >
                                      <xsl:value-of select="root/Datas/BasicData/secretLimit"/>
                                      <xsl:if test="root/Datas/BasicData/secretLimit">
                                      年
                                      </xsl:if>
                                    </td>
                                  </tr>
                              </table>
                              <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_4">
                                <tr>
                                  <td width="33%" class="r_bor">
                                     <div>签 发：</div>
                                    <div class="clearfix">
                                      <div><xsl:value-of select="root/Datas/TApprovedinfoList/TApprovedinfo[stepname='签发领导']/remark"/></div>
                                      <div class="fr"><xsl:value-of select="root/Datas/TApprovedinfoList/TApprovedinfo[stepname='签发领导']/userfullname"/></div>
                                      <div class="clear"></div>
                                      <div class="fr"><xsl:value-of select="root/Datas/TApprovedinfoList/TApprovedinfo[stepname='签发领导']/upddateStr"/></div>
                                    </div>
                                  </td>
                                  <td width="33%" class="r_bor">
                                    <div>审 阅：</div>
                                    <div class="clearfix">
                                      <div></div>
                                      <div class="fr"></div>
                                      <div class="clear"></div>
                                      <div class="fr"></div>
                                    </div>
                                  </td>
                                  <td>
                                    <div>会 签：</div>
                                    <div class="clearfix">
                                      <div><xsl:value-of select="root/Datas/TApprovedinfoList/TApprovedinfo[stepname='领导']/remark"/></div>
                                      <div class="fr"><xsl:value-of select="root/Datas/TApprovedinfoList/TApprovedinfo[stepname='领导']/userfullname"/></div>
                                      <div class="clear"></div>
                                      <div class="fr"><xsl:value-of select="root/Datas/TApprovedinfoList/TApprovedinfo[stepname='领导']/upddateStr"/></div>
                                    </div>
                                  </td>
                                </tr>
                                <tr>
                                  <td width="33%" class="lableTd t_c r_bor">拟稿部门</td>
                                  <td width="33%" class="lableTd t_c r_bor">会签部门</td>
                                  <td class="lableTd t_c">核稿部门</td>
                                </tr>
                                <tr>
                                  <td width="33%" class="r_bor">
                                    <div>拟稿人：</div>
                                    <div class="clearfix">
                                      <div><xsl:value-of select="root/Datas/TApprovedinfoList/TApprovedinfo[stepname='起草']/remark"/></div>
                                      <div class="fr"><xsl:value-of select="root/Datas/TApprovedinfoList/TApprovedinfo[stepname='起草' or stepname='拟稿人修改']/upddateStr"/></div>
                                      <div class="fr mr8"><xsl:value-of select="root/Datas/TApprovedinfoList/TApprovedinfo[stepname='起草' or stepname='拟稿人修改']/userfullname"/></div>
                                    </div>
                                    
                                  </td>
                                  <td width="33%" class="r_bor"></td>
                                  <td>
                                    <div>
                                          <div>核稿人：</div>
                                          <div><xsl:value-of select="root/Datas/TApprovedinfoList/TApprovedinfo[stepname='核稿']/remark"/></div>
                                          <div class="clearfix">
                                            <div class="fr mr5"><xsl:value-of select="root/Datas/TApprovedinfoList/TApprovedinfo[stepname='核稿']/upddateStr"/></div>
                                            <div class="fr mr5"><xsl:value-of select="root/Datas/TApprovedinfoList/TApprovedinfo[stepname='核稿']/userfullname"/></div>
                                          </div>
                                    </div>
                                  </td>
                                </tr>
                              </table>
                              <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_4">
                                <tr>
                                  <td class="lableTd" width="15%">标题：</td>
                                  <td ><xsl:value-of select="root/Datas/BasicData/docTitle"/></td>
                                </tr>
                                <tr>
                                  <td class="lableTd" width="15%">主送单位：</td>
                                  <td ><xsl:if test="root/Datas/BasicData/sendMainW"> 
                                      <xsl:value-of select="root/Datas/BasicData/sendMainW"/><br></br> 
                                      </xsl:if>
                                      <xsl:value-of select="root/Datas/BasicData/sendMain"/>
                                  </td>
                                </tr>
                                <tr>
                                  <td class="lableTd" width="15%">抄送单位：</td>
                                  <td ><xsl:if test="root/Datas/BasicData/sendCopy"> 
                                    <xsl:value-of select="root/Datas/BasicData/sendCopy"/> <br></br>
                                    </xsl:if>
                                    <xsl:value-of select="root/Datas/BasicData/sendInside"/>
                                  </td>
                                </tr>
                                <tr>
                                  <td class="lableTd" width="15%">内发：</td>
                                  <td ><xsl:value-of select="root/Datas/BasicData/sendReportW"/></td>
                                </tr>
                                <tr>
                                  <td class="lableTd" width="15%" colspan="2">相关附件：</td>
                                </tr>
                                <tr>
                                  <td colspan="2">
                                    
                                    <table width="100%" cellpadding="2" cellspacing="0" style="font-size:12px;border-top:#bbb 1px solid;border-right:#bbb 1px solid">
                                      <xsl:if test="root/Datas/AttachFileList/AttachFile/fileName ">
                                      <!--  附件属性名-->
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
                                      <xsl:for-each select="root/Datas/AttachFileList/AttachFile">
            
            <tr>
            <td style="line-height: 15px">
              <xsl:variable name="AttachFileId"
              select="id" />
              <!--img src="/workflowNew/images/files/doc.gif" style="display: inline;" /-->
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
                                </tr>
                                <tr>
                                  <td class="lableTd" width="15%">校对：</td>
                                  <td ><xsl:value-of select="root/Datas/TApprovedinfoList/TApprovedinfo[stepname='校稿']/remark"/>
                                  </td>
                                </tr>
                                <tr>
                                  <xsl:variable name="attachMainPath"
                                  select="root/Datas/BasicData/AttMain/AttachFileList/AttachFile[1]/path" />
                                  <td class="lableTd" width="15%">正式文件：</td>
                                  <td ><xsl:variable name="AttachMainFileId"
                                        select="root/Datas/BasicData/AttMain/AttachFileList/AttachFile[1]/id" />
                                    <a href="http://10.1.44.18/downloadFile.action?fileId={$AttachMainFileId}">
                                      <xsl:value-of select="root/Datas/BasicData/AttMain/AttachFileList/AttachFile[1]/fileName"/>
                                    </a>
                                  </td>
                                </tr>
                              </table>
                              <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_4">
                                <tr>
                                  <td class="r_bor" width="50%">印制份数：<xsl:value-of select="root/Datas/BasicData/docCount"/></td>
                                  <td>印发日期:<xsl:value-of select="root/Datas/BasicData/sendDate"/></td>
                                </tr>
                              </table>
                              <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_4">
                                <tr>
                                  <td class="lableTd" width="15%">办理情况：</td>
                                  <td ></td>
                                </tr>
                                <tr>
                                  <td class="lableTd" width="15%">套头意见：</td>
                                  <td ><xsl:value-of select="root/Datas/TApprovedinfoList/TApprovedinfo[stepname='套头']/remark"/></td>
                                </tr>
                                <tr>
                                  <td class="lableTd" width="15%">办结人意见：</td>
                                  <td ><xsl:value-of select="root/Datas/TApprovedinfoList/TApprovedinfo[stepname='办结']/remark"/></td>
                                </tr>
                              </table>
                          </div>
                          <div class="mb10 t_c">
                          <!--  input type="submit" value="初审通过" />
                                 
<input type="button" value="返 回" />

<input type="reset" value="取 消" /-->
                          </div>
                            <div class="footer"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>

  	 <!--html结束 -->
</xsl:template>
</xsl:stylesheet>