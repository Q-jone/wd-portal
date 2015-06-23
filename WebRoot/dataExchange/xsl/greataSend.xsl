<?xml version="1.0" encoding="utf-8"?>
  <!-- Edited with XML Spy v2007 (http://www.altova.com) -->
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method='html' version='1.0' encoding='UTF-8' indent='yes' />

  <xsl:template match="/">
  <xsl:variable name="recValid" select="root/@valid"/>  

<html lang="en">
<head>
 <meta http-equiv="X-UA-Compatible" content="IE=edge" charset="utf-8"  />
<title>上海申通地铁集团有限公司收文处理单</title>
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
<script src="/portal/js/html5.js"></script>
<script src="/portal/js/jquery-1.7.1.js"></script>
<script src="/portal/js/jquery-ui-1.8.18.custom.min.js"></script>
<script src="/portal/js/jquery.formalize.js"></script>  
<script type="text/javascript" src="js/common.js"></script>  
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
	                  	<xsl:if test="root[@valid='1']">
	                    	<li id="ywbl"><a href="javascript:ywbl('{$recValid}');" class="ywbl">业务办理</a></li>
	                    </xsl:if>
					  <li><a href="#" target="blank" class="print">打印</a></li>
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
       <form action="http://10.1.44.18/docRe/registerRe.action" id="formAdd" name="formAdd" method="post" target="theOldUrl">   
       <input type="hidden" name="typeId"><xsl:attribute name="value"><xsl:value-of select="root/Datas/BasicData/ChiefPerson"/></xsl:attribute></input>
       <input type="hidden" name="header"><xsl:attribute name="value"><xsl:value-of select="root/Datas/BasicData/ordinartyPerson"/></xsl:attribute></input>
       <input type="hidden" name="drSwtype"><xsl:attribute name="value"><xsl:value-of select="root/Datas/BasicData/chiefDep"/></xsl:attribute></input>
       <input type="hidden" name="attachFj" value=""/>
             
          <div class="gray_bg2">
              <div class="w_bg">
                  <div>
                      <div class="Top_fw">
                          <h1 class="t_c">上海申通地铁集团有限公司<br></br>收文单</h1>
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
                                            <dd>跟踪阶段</dd>
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
                                      <h5 class="fl">收文编号：</h5><span class="fl"><xsl:value-of select="root/Datas/BasicData/drSwid"/></span>
                                        <span class="fr clearfix">
                                          <h6 class="fl">密级：</h6><span class="fl mr8">
                                          <xsl:value-of select="root/Datas/BasicData/drSecretClass"/></span>
                                          <h6 class="fl">缓急：</h6><span class="fl mr8">
                                          <xsl:value-of select="root/Datas/BasicData/priorities"/></span>
                                        </span>
                                    </th>
                                  </thead>
                                  <tr>
                                    <td class="lableTd" width="15%">来文单位</td>
                                    <td width="35%"><input name="drSwunit" type="text" size="42"><xsl:attribute name="value"><xsl:value-of select="root/Datas/BasicData/drSwunit"/></xsl:attribute></input></td>
                                    <td class="lableTd" width="15%">文件日期</td>
                                    <td ><input name="drFiledate" type="text" size="42"><xsl:attribute name="value"><xsl:value-of select="root/Datas/BasicData/drFiledate"/></xsl:attribute></input></td>
                                  </tr>
                                  <tr>
                                  <td class="lableTd" width="15%">份数</td>
                                  <td ><input name="drNum" type="text" size="42"><xsl:attribute name="value"><xsl:value-of select="root/Datas/BasicData/drNum"/></xsl:attribute></input></td>
                                  <td class="lableTd" width="15%">文件字号</td>
                                  <td >
                                      <input name="drFilezh" type="text" size="42"><xsl:attribute name="value"><xsl:value-of select="root/Datas/BasicData/drFilezh"/></xsl:attribute></input>
                                  </td>
                                </tr>
                                <tr>
                                  <td class="lableTd" width="15%">文件标题</td>
                                  <td colspan='3'><textarea name="drTitle"><xsl:value-of select="root/Datas/BasicData/drTitle"/></textarea></td>
                                  
                                </tr>
                                <tr>
                                 <td class="lableTd" width="15%">文件内容</td>
                                  <td colspan='3'>                                  
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
                                              <xsl:variable name="AttachFilePath"
                                              select="path" />
                                              <input type="hidden" name="filePath" value="{$AttachFilePath}"/>
                         					  <input type="hidden" name="fileName"><xsl:attribute name="value"><xsl:value-of select="fileName"/></xsl:attribute></input>
                                              <input type="hidden" name="fileExtName" ><xsl:attribute name="value"><xsl:value-of select="fileExtName"/></xsl:attribute></input>                                              
                                              <input type="hidden" name="fileSize"><xsl:attribute name="value"><xsl:value-of select="fileSize"/></xsl:attribute></input>
                                              <input type="hidden" name="version" ><xsl:attribute name="value"><xsl:value-of select="version"/></xsl:attribute></input>
                                              <input type="hidden" name="memo" ><xsl:attribute name="value"><xsl:value-of select="memo"/></xsl:attribute></input>
                                              <!--img src="/workflowNew/images/files/doc.gif" style="display: inline;" /-->
                                              <a href="{$AttachFilePath}" target="_blank"
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
                              <!-- </table>
                              
                              <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_4"> -->

                                <tr>
                                  <td class="lableTd" width="15%">备注</td>
                                  <td colspan="3">
                                    <textarea name="remark"><xsl:value-of select="root/Datas/BasicData/remark"/></textarea>
                                  </td>
                                </tr>
                                <tr>
                                  <th colspan="4"><h5 class="fl">收文意见栏</h5></th>
                                </tr>
                                <!-- 拟办意见 -->
                                <tr>
                                  <td colspan="4">
                                    <div class="node">
                                    <b class="fl">拟办意见</b>
                                    <div class="clear"></div>
                                    <xsl:for-each select="root/Datas/TApprovedinfoList/TApprovedinfo[stepname='拟办人']">
                                          <xsl:sort select="upddate" order="descending"/>
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
                                      <div class="node">
                                        <b class="fl">领导批示</b>
                                        <div class="clear"></div>
                                        <xsl:for-each select="root/Datas/TApprovedinfoList/TApprovedinfo[stepname='领导'
                                          or stepname='批示领导']">
                                              <xsl:sort select="upddate" order="descending"/>
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
                                      <div class="node">
                                        <b class="fl">部门意见</b>
                                        <div class="clear"></div>
                                        <xsl:for-each select="root/Datas/TApprovedinfoList/TApprovedinfo[stepname='部门业务人员处理'
                                          or stepname='部门领导审核'
                                          or stepname='部门接受人工作分发']">
                                              <xsl:sort select="upddate" order="descending"/>
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
                                      <div class="node">
                                        <b class="fl">办结人意见</b>
                                        <div class="clear"></div>
                                        <xsl:for-each select="root/Datas/TApprovedinfoList/TApprovedinfo[stepname='办结']">
                                              <xsl:sort select="upddate" order="descending"/>
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
                                      <div class="node">
                                        <b class="fl">备案情况</b>
                                        <div class="clear"></div>
                                        <xsl:for-each select="root/Datas/TApprovedinfoList/TApprovedinfo[stepname='备案']">
                                              <xsl:sort select="upddate" order="descending"/>
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
                                       <div class="node">
                                        <b class="fl">办理结果</b>
                                        <div class="clear"></div>
                                        <xsl:for-each select="root/Datas/TApprovedinfoList/TApprovedinfo[stepname='办结']">
                                              <xsl:sort select="upddate" order="descending"/>
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
                                  </td>
                                </tr>
                                <!-- 拟办end -->
                                <!-- <tr>
                                  <td class="lableTd" width="15%">拟办意见</td>
                                  <xsl:for-each select="">
                                    <td colspan="3"><xsl:value-of select="root/Datas/BasicData/sendReportW"/></td>

                                  </xsl:for-each>
                                  
                                </tr> -->
                                <!-- <tr>
                                  <td class="lableTd" width="15%" >领导批示</td>
                                  <td colspan="3"><xsl:value-of select="root/Datas/BasicData/sendReportW"/></td>
                                </tr>
                                
                               <tr>
                                  <td class="lableTd" width="15%" >部门意见</td>
                                  <td colspan="3"><xsl:value-of select="root/Datas/BasicData/sendReportW"/></td>
                                </tr>
                                
                                  
                                
                               
                              </table>
                              
                              <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_4">
                                <tr>
                                  <td class="lableTd" width="15%">办结人意见</td>
                                  <td ><xsl:value-of select="root/Datas/TApprovedinfoList/TApprovedinfo[stepname='办结']/remark"/></td>
                                </tr>
                                <tr>
                                  <td class="lableTd" width="15%">备案情况</td>
                                  <td ><xsl:value-of select="root/Datas/TApprovedinfoList/TApprovedinfo[stepname='备案']/remark"/></td>
                                </tr> -->
                               <!--  <tr>
                                   
                                         <i class="i-red"><xsl:value-of select="dept"/></i>
                                        <div class="p8"><xsl:value-of select="remark"/></div>
                                        <div class="p8 clearfix">
                                          <div class="fr mr5"><xsl:value-of select="upddateStr"/></div>
                                          <div class="fr mr5">
                                            <xsl:value-of select="userfullname"/>
                                          </div>
                                          
                                        </div>
                                      </div>
                                        </xsl:for-each>
                                    </td>
                               </tr> -->
                              </table>
                          </div>
                          <div class="mb10 t_c">
                          <!-- input type="submit" value="初审通过" />
                                 
                          <input type="button" value="返 回" />

                          <input type="reset" value="取 消" / -->
                          </div>
                            <div class="footer"></div>
                        </div>
                    </div>
                </div>
            </div>
       </form>
        <form action="confirmValid.action" id="recUpdate" name="recUpdate" method="post" target="theUpdateUrl">  
        <input type="hidden" name="id"><xsl:attribute name="value"><xsl:value-of select="root/@id"/></xsl:attribute></input> 
        </form>
       <iframe name="theOldUrl" style="display: none;"></iframe>
        <iframe name="theUpdateUrl" style="display: none;"></iframe>
      </div>       
    </div>
</body>
</html>

     <!--html结束 -->
</xsl:template>
</xsl:stylesheet>