<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>申通地铁集团综合业务协同平台</title>
<link href="css/reset.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/my.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
    <script src="js/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="js/highcharts.js"></script>
<script type="text/javascript" src="js/highcharts.between-points.js"></script>
<script type="text/javascript" src="js/mscp.js"></script>

<link href="/portal/js/facyBox-master/facybox.css" media="screen" rel="stylesheet" type="text/css" />
<link href="/portal/js/facyBox-master/facybox_urls.css" media="screen" rel="stylesheet" type="text/css" />
<link href="/portal/js/facyBox-master/demo/faceplant.css" media="screen" rel="stylesheet" type="text/css" />
<script src="/portal/js/facyBox-master/facybox.js" type="text/javascript"></script>

<style>
.Num_01,.Num_02{ width:128px;}
.Num_01 em,.Num_02 em{ font-size:12px;}
.Num_01 h3,.Num_02 h3{ font-size:18px;}

.expert{ width:328px;background:url(../images/L_box_bg1.png) no-repeat; padding:7px 0 0 8px}
.expert_body{ width:318px;  background:#f7fbff; border:1px solid #b9b9b9; border-radius:5px; text-align:center;}
.expert_body h3 ,.gys_body h3{ font:bold 14px/42px 'Microsoft Yahei'; color:#494949;}
</style>
<script type="text/javascript">
var todate = new Date();
var toyear = todate.getFullYear();
var tomonth = todate.getMonth();
function showYear(){
	var addHtml = "";
	var startYear = 2012;
	for(var i=0;i<toyear-startYear+1;i++){
		addHtml += "<option value='"+(startYear+i)+"'>"+(startYear+i)+"年</option>";
	}
	$("#moreOrganizationTraderData").find("#year").append(addHtml);
	$("#moreOrganizationTraderData").find("#year").children("option").each(function(){
		if($(this).val()==toyear){
			$(this).attr("selected",true);
		}
	});
	$("#moreOrganizationTraderDataMonth").children("option").each(function(i){
		if(i==tomonth){
			$(this).attr("selected",true);
		}
	});

	$("#moreSupplyBidData").find("#year").append(addHtml);
	$("#moreSupplyBidData").find("#year").children("option").each(function(){
		if($(this).val()==toyear){
			$(this).attr("selected",true);
		}
	});
	$("#moreSupplyBidDataMonth").children("option").each(function(i){
		if(i==tomonth){
			$(this).attr("selected",true);
		}
	});
}

function setShortTitle(shortTitle,Title){
	if(shortTitle=="null"){
		return shortTitle;
	}else{
		return Title;
	}
}
//侧边新闻
function getLatestNewsAside(channelID){
	
	var url = "<%=basePath%>jeecms/getJeecmsInfo.action?random="+Math.random();
	
	$.ajax({
		async:false, 
		url: url, // 跨域URL 
		type: 'post', 
		dataType: 'json', 
		data:{
			method:"JEECMS.GET_CONTENT_LIST",
			channelId:channelID,
			hasTitleImg:"0,1",
			typeId:'1,2,3,4',
			rownum:'3'
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			//alert(XMLHttpRequest);
			//alert(textStatus);
			//alert(errorThrown);
			//alert("系统连接失败，请稍后再试！");
		},
		success: function(obj){			
			var newsLi = "";
			if(obj){
				if(obj.length==0){
					$("#ggb").html("&nbsp;&nbsp;无相关信息。");
				}else{
					for(var i=0;i<obj.length;i++){
						newsLi += "<li><a target='_blank' title='"+setShortTitle(obj[i].shortTitle,obj[i].title)+"'";
						newsLi += " href='<%=basePath%>jeecms/contentDetail.jsp?content="+obj[i].contentId+"'>"+obj[i].title+"</a></li>";
					}
					
					$("#ggb").html(newsLi);
				}
				
			}else{
				$("#ggb").html("&nbsp;&nbsp;无相关信息。");
			}
		}	  
	});	
	

}

	$(function(){
		showYear();
		$(document).on("change","#moreOrganizationTraderDataMonth",function(){
			showOrganizationTrader('<%=basePath%>mscp/findOrganizationTrade.action','switchMonth',$(this).siblings("#year").val(),$(this).val(),$(this));
		})
		$(document).on("change","#moreSupplyBidDataMonth",function(){
			showSupplyBid('<%=basePath%>mscp/findSupplyBid.action','switchMonth',$(this).siblings("#year").val(),$(this).val(),$(this));
		})
		
		showMscpInfo('<%=basePath%>mscp/findMscpInfo.action');

		showOrganizationTrader('<%=basePath%>mscp/findOrganizationTrade.action','thisMonth','','',null);

		showSupplyBid('<%=basePath%>mscp/findSupplyBid.action','thisMonth','','',null);
		findMscpExpertStat('<%=basePath%>mscp/findMscpExpertStat.action');	

		showPortalVisit('<%=basePath%>mscp/findPortalVisit.action');
		showPlatformVisit('<%=basePath%>mscp/findMonthVisit.action');
		
		showMonthNotice('<%=basePath%>mscp/findMonthNotice.action');

		showMonthBid('<%=basePath%>mscp/findMonthBid.action');
		
		showTotalBid('<%=basePath%>mscp/findTotalBid.action');

		getLatestNewsAside("1872");	

		findMonthBidByMonth('<%=basePath%>mscp/findMonthBidByMonth.action');

		showSupplierNum('<%=basePath%>mscp/showSupplierNum.action');

		showSupplierStatPig('<%=basePath%>mscp/findMscpMonthSupplierStatSumByMonth.action');

		findMscpAlarmStat('<%=basePath%>mscp/findMscpAlarmStat.action');

		
	});
</script>
</head>
<body>
<div class="Main clearfix">
	<!-- 
    <div class="header"> 
    	<a href="#"><img src="images/top.jpg" /></a>
    </div>
     -->
	<div id="Contents" class="Content">
		<div class="C_main clearfix">
		<div class="gys2 fL" style="width:335px;height:223px;padding:0px;margin:0px;">
			<div class="ggb">
	        	<h3><a href="<%=basePath%>jeecms/findStfbNewsByPage.action?channelId=1872" target="_blank">公告板</a></h3>
	            <ul id="ggb">
	            </ul>
	        </div>
        </div>
        
    	<div class="Num_list fR" style="width:910px;background-color:transparent;padding-bottom:0px;">
           <div class="Num_01">
               <em>门户访问量</em>
               <h3 id="data1"></h3>
               <i id="data1i"></i>
           </div>
           <div class="Num_02">
               <em>平台访问量</em>
               <h3 id="data2"></h3>
               <i id="data2i"></i>
           </div>
           <div class="Num_02">
               <em>注册供应商数</em>
               <h3 id="data3"></h3>
           </div>
           <div class="Num_02">
               <em>合格供应商数</em>
               <h3 id="data4"></h3>
           </div> 
           <div class="Num_02">
               <em>专家数量</em>
               <h3 id="data6"></h3>
           </div>
           <div class="Num_02">
               <em>累计投诉数</em>
               <h3 id="data5"></h3>
           </div>
           <div class="Num_02">
               <em>定向采购数量</em>
               <h3 id="data13"></h3>
           </div>
           
           <div class="Num_01">
               <em style="font-size:12px;">单一来源公告数量</em>
               <h3 style="font-size:18px;" id="data7"></h3>
               <i id="data7i"></i>
           </div> 
           <div class="Num_02">
               <em>招标公告数量</em>
               <h3 id="data8"></h3>
               <i id="data8i"></i>
           </div>
           <div class="Num_02">
               <em>中标公告数量</em>
               <h3 id="data9"></h3>
               <i id="data9i"></i>
           </div>
           <div class="Num_02">
               <em>中标公告金额(万元)</em>
               <h3 id="data14"></h3>
           </div>
           <div class="Num_02">
               <em>竞标公告数量</em>
               <h3 id="data10"></h3>
               <i id="data10i"></i>
           </div>
           <div class="Num_02">
               <em>竞价结果数量</em>
               <h3 id="data11"></h3>
               <i id="data11i"></i>
           </div>
           <div class="Num_02">
               <em>网上交易金额(万元)</em>
               <h3 id="data12"></h3>
           </div>
           </div>
           </div>
           <div class="C_main clearfix" style="padding-bottom:10px;">
                <div class="C_left fL" >
                	<div class="gys2" style="margin-top:0px; ">
                    	<div class="gys_body2">
                        	<h3>组织机构交易金额排行榜前10名</h3>
                            <table width="301" border="0" cellspacing="0" cellpadding="0" class="table_body">
                            <thead>
                              <tr>
                                <th width="50">名次</th>
                                <th width="85">名称</th>
                                <th>交易次数</th>
                                <th width="105">交易金额（万元）</th>
                              </tr>
                              </thead>
                              <tbody id="organizationVisit"> 
                              </tbody>
                            </table>       
                        </div>
                    </div>
                    <div class="clearfix" style="height:10px;"></div>
                    <div class="zzjg">
                    	<div class="zzjg_body">
                        	<h3>供应商中标金额排行榜前10名</h3>
                            <table width="301" border="0" cellspacing="0" cellpadding="0" class="table_body">
                            <thead>
                              <tr>
                                <th width="40">名次</th>
                                <th width="85">名称</th>
                                <th>中标次数</th>
                                <th width="105">中标金额（万元）</th>
                              </tr>
                              </thead>
                              <tbody id="supplyBid">
                              </tbody>
                             
                            </table>  
                        </div>
                    </div>
                    
                    <div class="clearfix" style="height:10px;"></div>
                    <div class="expert">
                    	<div class="expert_body">
                        	<h3>专家总数分类统计</h3>
                            <table width="301" border="0" cellspacing="0" cellpadding="0" class="table_body">
                            <thead>
                              <tr>
                                <th width="200" style="text-align:left;padding-left: 20px;">分类</th>
                                <th>专家数(个)</th>
                              </tr>
                              </thead>
                              <tbody id="expertStat">
                              </tbody>
                             
                            </table>  
                        </div>
                    </div>
                    <div class="clearfix" style="height:10px;"></div>
                </div>
                <div class="C_right fR">
                	<!-- <img src="images/R_img.gif" class="r_img" /> -->
                	
                	<div class="R_body" >
                		<div style="width: 50%;height:250px;float: left;margin-top: 5px;" id="portalVisit"></div>
                		<div style="width: 49%;height:250px;float: right;margin-top: 5px;" id="platformVisit"></div>
                		<div style="width: 100%; height: 250px;float: left;" id="monthNotice"></div>
                		<div style="width: 100%; height: 250px;float: left;padding:0 0 10px 0" id="monthBidStat"></div>
                		<div style="width: 19%;height:250px;float: left;margin-top: 5px;" id="totalCountPie"></div>
                		<div style="width: 19%;height:250px;float: left;margin-top: 5px;" id="totalMoneyPie"></div>
                		<div style="width: 60%;height:250px;float: right;margin-top: 5px;" id="totalColumn"></div>
                	</div>
                	
                	
                    <div class="R_body" >	
                	<table width="887" border="0" cellspacing="0" cellpadding="0" class="R_c_body">
                    <thead id="thead">
                      <tr>
                        <th>竞价类型</th>
                        <th>总单数</th>
                        <th>已完成</th>
                        <th>已终止</th>
                        <th>执行中</th>
                        <th>中标金额（元）</th>
                      </tr>
                      </thead>
                    </table>
                    </div>
                    <div class="R_body" style="margin-top:20px;border: 0;">	
                    	<div style="width: 50%;height:260px;float: left;margin-top: 5px;">
                    		<table border="0" cellspacing="0" cellpadding="0" class="R_c_body" style="width:430px;">
			                    <thead id="thead">
			                      <tr style="height:15px;">
			                        <th style="background-color:#5CACEE;color:white;border:1px solid #d0d0d0">类型</th>
			                        <th style="background-color:#5CACEE;color:white;border:1px solid #d0d0d0">供应商数量</th>
			                        <th style="background-color:#5CACEE;color:white;border:1px solid #d0d0d0">单月新增数量</th>
			                      </tr>
			                      </thead>
			                      <tbody id="supplierTbody"></tbody>
                    		</table>
                    	</div>
                		<div style="width: 49%;height:250px;float: right;margin-top: 5px;" id="supplierStatPig"></div>
                    	<div class="Num_list" style="width:894px;height:100px;background-color:transparent;padding-bottom:0px;float:left;" id="alarmDiv">
				         </div>  
                    </div>
                </div>
                
                   <div class="clearfix"></div> 
                 <div class="C_right fL"  style="width:1250px;height:30px;">
                 	<ul>
                 	<li style="float:left;line-height:50px;padding-left:30px;font-family:Microsoft Yahei;font-size:16px;"><strong>友情链接：</strong>
                 	</li>
                 	<li style="float:left;line-height:50px;padding-left:30px;font-family:Microsoft Yahei;font-size:16px;">
                        <a href="http://10.35.90.106:8001/ieps/login.jsp" target="_blank"><strong>阳光采购平台</strong></a>
                 	</li>
                 	</ul>
                 	</div>
                 	
           </div>

        </div>
    </div>
    <div>
    <div id="moreOrganizationTraderData" style="display:none;">
		<div class="gys2" style="margin-top:0px; ">
          	<div class="gys_body2">
              	<h3>组织机构交易金额排行榜前10名</h3>
              	<select id="year"></select>
              	<select id="moreOrganizationTraderDataMonth">
              		<option value="01">1月</option>
              		<option value="02">2月</option>
              		<option value="03">3月</option>
              		<option value="04">4月</option>
              		<option value="05">5月</option>
              		<option value="06">6月</option>
              		<option value="07">7月</option>
              		<option value="08">8月</option>
              		<option value="09">9月</option>
              		<option value="10">10月</option>
              		<option value="11">11月</option>
              		<option value="12">12月</option>
              	</select>
                  <table width="301" border="0" cellspacing="0" cellpadding="0" class="table_body">
                  <thead>
                    <tr>
                      <th width="50">名次</th>
                      <th width="85">名称</th>
                      <th>交易次数</th>
                      <th width="105">交易金额（万元）</th>
                    </tr>
                    </thead>
                    <tbody id="moreOrganizationTraderDataTbody"> 
                    	
                    </tbody>
                  </table>       
              </div>
          </div>
          <div class="clearfix" style="height:10px;"></div>
	</div>
	
	<div id="moreSupplyBidData" style="display:none;">
		<div class="gys2" style="margin-top:0px; ">
          	<div class="gys_body2">
              	<h3>供应商中标金额排行榜前10名</h3>
              	<select id="year"></select>
              	<select id="moreSupplyBidDataMonth">
              		<option value="01">1月</option>
              		<option value="02">2月</option>
              		<option value="03">3月</option>
              		<option value="04">4月</option>
              		<option value="05">5月</option>
              		<option value="06">6月</option>
              		<option value="07">7月</option>
              		<option value="08">8月</option>
              		<option value="09">9月</option>
              		<option value="10">10月</option>
              		<option value="11">11月</option>
              		<option value="12">12月</option>
              	</select>
                  <table width="301" border="0" cellspacing="0" cellpadding="0" class="table_body">
                  <thead>
                    <tr>
                      <th width="50">名次</th>
                      <th width="85">名称</th>
                      <th>中标次数</th>
                      <th width="105">中标金额（万元）</th>
                    </tr>
                    </thead>
                    <tbody id="moreSupplyBidDataTbody"> 
                    	
                    </tbody>
                  </table>       
              </div>
          </div>
          <div class="clearfix" style="height:10px;"></div>
	</div>
</div>
</body>
</html>