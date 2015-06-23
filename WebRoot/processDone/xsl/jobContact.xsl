<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method='html' version='1.0' encoding='UTF-8' indent='yes' />

  <xsl:template match="/">
    <!--发起部门 -->
    <xsl:variable name="beginDept" select="Datas/TApprovedinfoList/TApprovedinfo[stepname='Begin']/dept"/>
    <xsl:variable name="pid" select="Datas/processDoneId"/>
  	<html lang="en">
<head>
 <meta http-equiv="X-UA-Compatible" content="IE=edge" charset="utf-8"  />
<title>上海申通地铁集团有限公司工作联系单</title>
<link rel="stylesheet" href="css/formalize.css" />
<link rel="stylesheet" href="css/page.css" />
<link rel="stylesheet" href="css/default/imgs.css" />
<link rel="stylesheet" href="css/reset.css" />
		<link type="text/css" href="css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
    <style>
      pre{
      white-space: pre-wrap;       
      white-space: -moz-pre-wrap;  
      white-space: -pre-wrap;      
      white-space: -o-pre-wrap;    
      word-wrap: break-word;       
    }
    </style>
		<!--[if IE 6.0]>
           <script src="js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
		<script src="js/html5.js"></script>
		<script src="js/jquery-1.7.1.min.js"></script>
		<script src="js/jquery-ui-1.8.18.custom.min.js"></script>
		<script src="js/jquery.formalize.js"></script>


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
                      <li><a href="printProcessInfo.action?id={$pid}" target="blank" class="print">打印</a></li>
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
                        	<h1 class="t_c">上海申通地铁集团有限公司<br></br>工作联系单</h1>
                                                        <!-- <div class="mb10 Step clearfix">
                              <ul class="clearfix">
                                  <li class="fst pass">
                                      <dl>
                                          <dt></dt>
                                            <dd>立项</dd>
                                         </dl>
                                     </li>
                                  <li class="on">
                                      <dl>
                                          <dt></dt>
                                            <dd>立项立项立项立项立项</dd>
                                         </dl>
                                    </li>
                                  <li>
                                      <dl>
                                          <dt></dt>
                                            <dd>立项</dd>
                                         </dl>
                                    </li>
                                  <li>
                                      <dl>
                                          <dt></dt>
                                            <dd>立项</dd>
                                         </dl>
                                    </li>
                                  <li>
                                      <dl>
                                          <dt></dt>
                                            <dd>立项</dd>
                                         </dl>
                                    </li>
                                  <li>
                                      <dl>
                                          <dt></dt>
                                            <dd>立项</dd>
                                         </dl>
                                    </li>
                                  <li>
                                      <dl>
                                          <dt></dt>
                                            <dd>立项</dd>
                                         </dl>
                                    </li>
                                    <li class="fin">
                                      <dl>
                                          <dt></dt>
                                            <dd>立项立项</dd>
                                        </dl>
                                    </li>
                                </ul>
                            </div> -->
                            <div class="mb10">
                              <table class="table_4 mb10">
                                <tr>
                                  <th colspan="4"><h5 class="fl">工作联系单基本信息栏</h5></th>
                                </tr>
                                <tr>
                                  <td class="t_r lableTd" width="15%">主送部门</td>
                                  <td colspan="3"><xsl:value-of select="Datas/BasicData/main_unit"/></td>
                                </tr>
                                <tr>
                                  <td class="t_r lableTd">抄送部门</td>
                                  <td colspan="3"><xsl:value-of select="Datas/BasicData/copy_unit"/></td>
                                </tr>
                                <tr>
                                  <td class="t_r lableTd">主题</td>
                                  <td colspan="3"><xsl:value-of select="Datas/BasicData/theme"/></td>
                                </tr>
                                <tr>
                                  <td class="t_r lableTd">联系时间</td>
                                  <td><xsl:value-of select="Datas/BasicData/contact_date"/></td>
                                  <td class="t_r lableTd" width="15%">要求回复时间</td>
                                  <td><xsl:value-of select="Datas/BasicData/reply_date"/></td>
                                </tr>
                                <tr>
                                  <td class="t_r lableTd">联系内容</td>
                                  <td colspan="3"><pre><xsl:value-of select="Datas/BasicData/content"/></pre></td>
                                </tr>
                                <tr>
                                  <td class="t_r lableTd">相关资料</td>
                                  <td colspan="3">
                                    <table width="100%" cellpadding="2" cellspacing="0" style="font-size:12px;border-top:#bbb 1px solid;border-right:#bbb 1px solid">
             <xsl:if test="Datas/AttachFileList/AttachFile/fileName ">
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
           <xsl:for-each select="Datas/AttachFileList/AttachFile">
            
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
				
			</tr><!-- 
                                <tr>
                                  <td class="t_r lableTd">发起人</td>
                                  <td><xsl:value-of select="Datas/TApprovedinfoList/TApprovedinfo[stepname='Begin']
                                  	/userfullname"/>
                                  </td>
                                  <td class="t_r lableTd">发起部门</td>
                                  <td><xsl:value-of select="Datas/TApprovedinfoList/TApprovedinfo[stepname='Begin']/dept"/></td>
                                </tr> -->

                                <tr>
                                  <th colspan="4"><h5 class="fl">工作联系单流转意见栏</h5></th>
                                </tr>
                                <tr>
                                  <td colspan="4">
                                    <div class="node">
                                    <b class="fl">签发意见</b>
                                    <div class="clear"></div>
                                    <xsl:for-each select="Datas/TApprovedinfoList/TApprovedinfo[stepname='部门内部签发' or stepname='返回修改']">
                                    <div class="con">
                                      <i><xsl:value-of select="dept"/></i>
                                      <!-- <div class="p8"> -->
                                      <p><xsl:value-of select="remark"/></p>
                                      <!-- </div> -->
                                      <div class="p8 clearfix">
                                        <div class="fr mr5"><xsl:value-of select="upddateStr"/>
                                        </div>
                                        <div class="fr mr5"><xsl:value-of select="userfullname"/>
                                        </div>
                                        <!-- <div class="fr mr5"><xsl:value-of select="dept"/> -->
                                        <!-- </div> -->
                                      </div>
                                    </div>
                                    </xsl:for-each>
                                    </div>
                                    <!-- node end -->
                                    <div class="p8 clearfix">
                                      <div class="fl w33p">经办人：<xsl:value-of select="Datas/BasicData/content_operator"/></div>
                                      <div class="fl w33p">签发：<xsl:value-of select="Datas/BasicData/content_signer"/></div>
                                      <div class="fl w33p">签章：<xsl:value-of select="Datas/BasicData/content_inscribe"/></div>
                                    </div>
                                   

                                  </td>

                                </tr>
                                <tr>
                                  <td colspan="4">
                                    <div class="node">
                                    <b class="fl">主送部门内部意见</b>
                                    <div class="clear"></div>
                                    <xsl:for-each select="Datas/TApprovedinfoList/TApprovedinfo[stepname='部门业务人员处理' 
                    										or stepname='部门接受人工作分发' 
                    										or stepname='部门领导审核'
                                        and dept != $beginDept
                    										  ]">
<!--                                           <xsl:sort select="upddateStr" order="descending"/> -->
                    									<div class="con">
                                        <i><xsl:value-of select="dept"/></i>
                    										<p><xsl:value-of select="remark"/></p>
                    										<div class="p8 clearfix">
                    											<div class="fr mr5"><xsl:value-of select="upddateStr"/>-07-15</div>
                    											<div class="fr mr5"><xsl:value-of select="userfullname"/></div>
                    											<!--<div class="fr mr5">运管中心</div>-->
                    										</div>
                    									</div>
                    									</xsl:for-each>
                                      </div>
                                      <!-- node end -->
                                  </td>
                                </tr>
                                <tr>
									<td colspan="4">
										<div class="node">
                      <b class="fl">返回发起部门内部意见</b>
                       <div class="clear"></div>
                     <xsl:for-each select="Datas/TApprovedinfoList/TApprovedinfo[stepname='部门领导审核' and dept= $beginDept]">
										<div class="con">
                     

                      <i ><xsl:value-of select="dept"/></i>
											<p><xsl:value-of select="remark"/></p>
											<div class="p8 clearfix">
												<div class="fr mr5"><xsl:value-of select="upddateStr"/></div>
												<div class="fr mr5"><xsl:value-of select="userfullname"/></div>
												<!-- <div class="fr mr5"><xsl:value-of select="Datas/TApprovedinfoList/TApprovedinfo[stepname='部门领导审核' and dept= $beginDept]/dept"/></div> -->
											</div>
										</div>
                      </xsl:for-each>
                      </div>
									</td>
								</tr>
                                
                              </table>
                          </div>
                          <div class="mb10 t_c">
                          <input type="submit" value="初审通过" />
                                  
<input type="button" value="返 回" />

<input type="reset" value="取 消" />
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