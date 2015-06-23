<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>在线测评</title>
<link rel="stylesheet" href="../exam/ui/css/formalize.css" />
<link rel="stylesheet" href="../exam/ui/css/page.css" />
<link rel="stylesheet" href="../exam/ui/css/default/imgs.css" />
<link rel="stylesheet" href="../exam/ui/css/reset.css" />
<link type="text/css" href="../exam/ui/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
<!--[if IE 6.0]>
         <script src="ui/js/iepng.js" type="text/javascript"></script>
         <script type="text/javascript">
              EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
         </script>
     <![endif]-->
<script src="../exam/ui/js/html5.js"></script>
<script src="../exam/ui/js/jquery-1.7.1.min.js"></script>
<script src="../exam/ui/js/jquery-ui-1.8.18.custom.min.js"></script>
<script src="../exam/ui/js/jquery.formalize.js"></script>
<script src="../exam/ui/js/jquery.form.js"></script>
<script  type="text/javascript">

	$(document).ready(function(){		
		var lastqnum="";
		$("#group .con_tr").each(function(i,n){
			var qnum = $(n).attr("qnum");
			if(lastqnum!=qnum){
				var tdn = $("#group .con_num_"+qnum).length;
				
				$("#group .con_num_"+qnum).hide();
				$("#group .con_tit_"+qnum).hide();
				
				$("#group .con_num_"+qnum).eq(0).attr("rowspan",tdn).show();
				$("#group .con_tit_"+qnum).eq(0).attr("rowspan",tdn).show();
				/* for(var i=1;i<tdn;i++){
					$("#group .con_num_"+qnum).eq(i).remove();
					$("#group .con_tit_"+qnum).eq(i).remove();
				} */
				lastqnum=qnum;
			}
			
		});
	});

</script>
<style type="text/css">
.littleMsg{ width:600px; position:absolute; top:150px; left:120px; z-index:9999;

border:#c7c7c7 1px solid;}
.littleMsg .list{ padding:10px; line-height:30px;  } 
.littleMsg .right_tit{ text-align:right; font-size:14px;} 
.littleMsg .right_time{ text-align:right;}   
.t_ind2{text-indent:2em ;}
.exam td,.exam th{font-size:14px; padding-top:5px; padding-bottom:5px; color:#666;}

.exam table input{ margin-left:10px;}
.exam .num{ padding-left:1px;padding-right:1px; background-color:#CCC; color:#000;  text-align:center; }
.exam .numok{ background-color:#390; color:#FFF}
.exam .tit td{font-size:16px; color:#000;}
.exam .con td{ vertical-align:top; height:55px; color:#000;}
.exam .con div{float: left;}
.exam .questionNum{ font-size:12px;position:absolute; top:100px; left:760px; z-index:9999;}
.exam .questionNum ul{background-color:#CCC; margin-top:2px; text-align:center; padding:2px; cursor:pointer;}
.exam .questionNum ul:hover{background-color:#390; color:#FFF}
.exam .questionNum .topname{ background-color:#06C; color:#FFF;}
.t2em{ text-indent:2em;}
.group_bg{color:#0066CC; font-size: 16px; font-weight: bold;}
.numhid{display: none;}
.questionCon td,.questionCon th{border-bottom: 1px solid #ccc;border-right: 1px solid #ccc;}
.questionCon tr{border-top: 1px solid #ccc;border-left: 1px solid #ccc;}
#group .count_con{width: 50px; text-align: center;}
</style>
</head>

<body class="Flow exam">
<input type="hidden" id="isUserOk" value="<s:property value='#request.isUserOk'/>" />
<input type="hidden" id="uMainid" value="<s:property value='#request.examUserMain.id'/>" />
<div class="f_bg_fw">
    <div class="w850">
    	<div class="logo_2"></div>
    </div>
      <div class="gray_bg">
          <!--Panel_6-->  
        <div class="Divab1" id="rightDiv">
        	
          <!--1st-->
            <div class="panel_6">
              <div class="divT">
                <div class="mb10 icon icon_1"></div>
                <div class="more_4"><a href="#" title="更多">更多</a></div>
              </div>
              <div class="divH">
                <div class="divB">
                  <h5 class="clearfix">相关操作</h5>
                  <div class="con">
                    <ul class="button clearfix">
                      <!--<li><a href="javascript:saveWait();" class="ywbl">暂存</a></li>
                      <li><a href="#" class="print">打印</a></li>
                      <li><a href="#" class="imp">提交保存</a></li>
                      <li><a href="#" class="jk">当前结果</a></li>-->
                      <s:if test="#request.isUserOk">
                     <!--  <li><a href="javascript:void(0);" onclick="custom_save();" class="imp">提交保存</a></li> -->
                      </s:if>
                      <li><a href="javascript:toggleMsg();" class="tips">小提示</a></li>
                      <!-- <li><a href="edit.action?examId=EXAMMAIN" class="tips">正常排序</a></li>
                      <li><a href="edit.action?examId=EXAMMAIN&otype=random" class="tips">随机排序</a></li> -->
                      <!--
                      <li><a href="#" class="exp">公文导出</a></li>-->
                    </ul>
                  </div>
                </div>
                <div class="divF"></div>
              </div>
            </div>
            <!--1st End-->
        </div>
      <!--Panel_6 End-->  
        	<div class="gray_bg2">
            	<div class="w_bg">
                	<div>
                    	<div class="Top_fw">
                        	<h1 class="t_c" id="e_tit"><s:property value='examMain.title'/></h1>
	<div class="mb10 Step clearfix">
          <ul class="t2em"><s:property value='examMain.exDescription'/></ul>
        </div>

        <div class="mb10 questionCon" >
        <!-- 题目组循环！ -->
        <table  border="0" cellspacing="0" cellpadding="0" id="group">						
            <thead>
            <tr>
            <th class="count_con" align="right">序号 </th>
            <th>题目 </th>
            <th class="count_con">选项 </th>
            <th class="count_con" style="width: 120px;">值 </th>
            <th class="count_con">集团 </th>
            <th class="count_con">第一运营公司 </th>
            <th class="count_con">第三运营公司 </th>
            <th class="count_con">第四运营公司 </th>
            <th class="count_con">运营中心 </th>
            <th class="count_con">维保公司 </th>
            <th class="count_con">技术中心 </th>
            <th class="count_con">资产公司 </th>
            <th class="count_con">股份公司</th>
            <th class="count_con">磁浮公司 </th>
            <th class="count_con">大桥公司 </th>
            <th class="count_con">项目公司 </th>
            <th class="count_con">其他 </th>
            <th class="count_con">合计</th>
            </tr>
            </thead>
                
           <s:iterator value="#request.report" id="r" status="s">
       
	           <tr class="tit con_tr" qnum="<s:property value="#r[0]" />-<s:property value="#r[1]" />" >
	                <td width="5" class="count_con con_num_<s:property value="#r[0]" />-<s:property value="#r[1]" />" rowspan="1">
	                	<div class=" numok num"><s:property value="#r[0]" />-<s:property value="#r[1]" /></div>
	                </td>
	                <td width="200"  class="con_tit_<s:property value="#r[0]" />-<s:property value="#r[1]" />"><s:property value="#r[2]" /></td>
	                <td class="count_con"><s:property value="#r[3]" /></td>
	                <td class="count_con"><s:property value="#r[4]" /></td>
	                <td class="count_con"><s:property value="#r[5]" /></td>
	                <td class="count_con"><s:property value="#r[6]" /></td>
	                <td class="count_con"><s:property value="#r[7]" /></td>
	                <td class="count_con"><s:property value="#r[8]" /></td>
	                <td class="count_con"><s:property value="#r[9]" /></td>
	                <td class="count_con"><s:property value="#r[10]" /></td>
	                <td class="count_con"><s:property value="#r[11]" /></td>
	                <td class="count_con"><s:property value="#r[12]" /></td>
	                <td class="count_con"><s:property value="#r[13]" /></td>
	                <td class="count_con"><s:property value="#r[14]" /></td>
	                <td class="count_con"><s:property value="#r[15]" /></td>
	                <td class="count_con"><s:property value="#r[16]" /></td>
	                <td class="count_con"><s:property value="#r[17]" /></td>
	                <td class="count_con"><s:property value="#r[18]" /></td>
	            </tr>
            </s:iterator>
        </table>
        <!-- 题目组循环！END -->
        </div>
                          <div class="mb10 t_c">
                          <s:if test="#request.isUserOk">
                          <input type="button" value="提交保存" onclick="custom_save()" />
                                  &nbsp;
<input type="button" value="关闭" onclick="custom_close()" />
&nbsp;
						</s:if>
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
