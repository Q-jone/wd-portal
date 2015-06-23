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
<title>上海申通地铁集团有限公司合同审批单</title>
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
                      <li><a href="printProcessInfo.action?id={$pid}" class="print">打印</a></li>
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
                        	<h1 class="t_c">上海申通地铁集团有限公司<br></br>合同审批单</h1>
                            <div class="mb10">
                              <table class="table_4 mb10">
                                <tr>
                                  <th colspan="4"><h5 class="fl">合同审批单基本信息栏</h5></th>
                                </tr>
                                <tr>
                                  <td class="t_r lableTd" width="15%">计划编号：</td>
                                  <td ><xsl:value-of select="Datas/BasicData/Htxx/PlanNum"/></td>
                                  <td class="t_r lableTd" width="15%">项目公司：</td>
                                  <td ><xsl:value-of select="Datas/BasicData/Htxx/ProjectCOName"/></td>
                                </tr>
                                <tr>
                                  <td class="t_r lableTd">工程编号：</td>
                                  <td ><xsl:value-of select="Datas/BasicData/Htxx/ProjectNum"/></td>
                                  <td class="t_r lableTd">合同名称：</td>
                                  <td ><xsl:value-of select="Datas/BasicData/Htxx/ContractName"/></td>
                                </tr>
                                <tr>
                                  <td class="t_r lableTd">合同编号：</td>
                                  <td colspan="3"><xsl:value-of select="Datas/BasicData/Htxx/ContractNum"/></td>
                                </tr>
                                <tr>
                                  <td class="t_r lableTd">合同金额分类：</td>
                                  <td><xsl:value-of select="Datas/BasicData/Htxx/ContractMoneyType"/></td>
                                  <td class="t_r lableTd" width="15%">合同金额(万元)：</td>
                                  <td><xsl:value-of select="Datas/BasicData/Htxx/ContractMoney"/></td>
                                </tr>
                                <tr>
                                  <td class="t_r lableTd">经办部门意见：</td>
                                  <td colspan="3"><pre><xsl:value-of select="Datas/BasicData/Htxx/DealDeptSuggest"/></pre></td>
                                </tr>
                                <tr>
                                  <td class="t_r lableTd">经办人：  </td>
                                  <td><xsl:value-of select="Datas/BasicData/Htxx/DealPerson"/></td>
                                  <td class="t_r lableTd" width="15%">项目公司法人<br></br>或委托代理人  </td>
                                  <td><xsl:value-of select="Datas/BasicData/Htxx/DelegatePerson"/></td>
                                </tr>
                                <tr>
                                  <td class="t_r lableTd">登记人：   </td>
                                  <td><xsl:value-of select="Datas/BasicData/Htxx/AddPerson"/></td>
                                  <td class="t_r lableTd" width="15%">登记日期： </td>
                                  <td><xsl:value-of select="Datas/BasicData/Htxx/AddDate"/></td>
                                </tr>
                                <tr>
                                  <td class="t_r lableTd"> 备注：</td>
                                  <td colspan="3"> <xsl:value-of select="Datas/BasicData/Htxx/Remark"/></td>
                                </tr>
                                <tr>
                                  <td class="t_r lableTd"> 自用编号：</td>
                                  <td><xsl:value-of select="Datas/BasicData/Htxx/SelfNum"/></td>
                                  <td class="t_r lableTd"> 批文号：</td>
                                  <td><xsl:value-of select="Datas/BasicData/Htxx/FileNum"/></td>
                                </tr>
                                <tr>
                                  <td class="t_r lableTd"> 对方公司：  </td>
                                  <td><xsl:value-of select="Datas/BasicData/Htxx/SignCop"/></td>
                                  <td class="t_r lableTd"> 预算（万元）：  </td>
                                  <td><xsl:value-of select="Datas/BasicData/Htxx/Budget"/></td>
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
                                  <th colspan="4"><h5 class="fl">合同审批单流转意见栏</h5></th>
                                </tr>
                                <tr>
                                  <td colspan="4">
                                    <div class="node">
                                    <b class="fl">内部审核</b>
                                    <div class="clear"></div>
                                    <xsl:for-each select="Datas/TApprovedinfoList/TApprovedinfo[stepname='申报部门领导审批']">
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
                                        
                                      </div>
                                    </div>
                                    </xsl:for-each>
                                    </div>
                                    

                                  </td>

                                </tr>
                                <tr>
                                  <td colspan="4">
                                    <div class="node">
                                    <b class="fl">预审</b>
                                    <div class="clear"></div>
                                    <xsl:for-each select="Datas/TApprovedinfoList/TApprovedinfo[stepname='预审']">

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
                                      <b class="fl">审核及处置</b>
                                       <div class="clear"></div>
                                     <xsl:for-each select="Datas/TApprovedinfoList/TApprovedinfo[stepname='合约审核'
                                      or stepname='合约领导审批'
                                      or stepname='部门领导审核'
                                      or stepname='部门业务人员处理'
                                      or stepname='法律顾问审核'
                                      or stepname='部门业务经办人']">
                										<div class="con">
                                     

                                      <i ><xsl:value-of select="dept"/></i>
                											<p>
                                        <xsl:choose>

                                                <xsl:when test="remark != ''"> 
                                                  <xsl:value-of select="remark"/>
                                                </xsl:when>

                                               <xsl:otherwise> 
                                                   <xsl:text>本人无异议 </xsl:text>
                                               </xsl:otherwise>
                                        </xsl:choose>
                                        
                                      </p>
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
                                <tr>
                                  <td colspan="4">
                                    <div class="node">
                                      <b class="fl">审批</b>
                                       <div class="clear"></div>
                                     <xsl:for-each select="Datas/TApprovedinfoList/TApprovedinfo[stepname='集团领导']">
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
                                <tr>
                                  
                                </tr>                                
                                <tr>
                                  <td colspan="4">
                                    <div class="node">
                                      <b class="fl">办理情况</b>
                                       <div class="clear"></div>
                                     <xsl:for-each select="Datas/TApprovedinfoList/TApprovedinfo[stepname='办结']">
                                    <div class="con">
                                     

                                      <i ><xsl:value-of select="dept"/></i>
                                      <p><xsl:value-of select="remark"/></p>
                                      <div class="p8 clearfix">
                                        <div class="fr mr5"><xsl:value-of select="upddateStr"/></div>
                                        <div class="fr mr5"><p></p>xsl:value-of select="userfullname"/></div>
                                        
                                      </div>
                                    </div>
                                      </xsl:for-each>
                                      </div>
                                  </td>
                                </tr>
                                
                             
                                <tr>
                                <th colspan="4">
                                <h5 class="fl">备案信息</h5>
                                <span class="fr pt5 mr5"><a href="#">收起</a></span>
                                </th>
                                </tr>
                                <tr>
                                <td class="lableTd" width="15%"> 审批通过日期:</td>
                                <td width="35%"><xsl:value-of select="Datas/BasicData/Htba/examinePassTime"/></td>
                                <td class="lableTd" width="15%"> 正式签约日期:</td>
                                <td width="35%"><xsl:value-of select="Datas/BasicData/Htba/contractSignTime"/></td>
                                </tr>
                                <tr>
                                <td class="lableTd"> 合同履行期限:</td>
                                <td colspan="3"><xsl:value-of select="Datas/BasicData/Htba/performTimelimit"/></td>
                                </tr>
                                <tr>
                                <td class="lableTd"> 备注:</td>
                                <td colspan="3"><xsl:value-of select="Datas/BasicData/Htba/remark"/></td>
                                </tr>
                                <tr>
                                <td class="lableTd" colspan="2"> 审批通过的合同与备案的合同条款是否一致:</td>
                                <td colspan="2"><xsl:value-of select="Datas/BasicData/Htba/ifSame"/></td>
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