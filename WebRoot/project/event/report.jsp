<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head lang="zh">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script type="text/javascript" src="<s:url value="/js/jquery-1.7.1.js"/>"></script>
    <script src="<s:url value="/js/html5.js"/>"></script>
    <link type="text/css" href="<s:url value="/css/flick/jquery-ui-1.8.18.custom.css"/>" rel="stylesheet"/>
    <script type="text/javascript" src="<s:url value="/js/flick/jquery-ui-1.8.18.custom.min.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/js/flick/jquery.ui.datepicker-zh-CN.js"/>"></script>
    <title>信息安全事件管理台帐</title>
    <style>
        body, html, .scroll {
            width: 100%;
        }

        ul {
            list-style: none;
        }

        li {
            font-size: 12px
        }

        .ui-datepicker-title span {
            display: inline;
        }

        button.ui-datepicker-current {
            display: none;
        }

        .scroll {
            overflow-x: scroll
        }

        .scroll table, .scroll table td, .scroll table th {
            border-color: #d5d5d5;
            border-style: solid;
        }

        .scroll table {
            border-width: 1px 1px 0px 0px;
            font-size: 12px;
            color: #303030
        }

        .scroll table thead td, .scroll table thead th {
            background-color: #fafafa;
            text-align: center
        }

        .scroll table td, .scroll table th {
            border-width: 0px 0px 1px 1px;
            padding: 5px 10px;
            white-space: nowrap;

            text-align: center;
        }

        .scroll table th {
            width: 100px
        }

        .stateStyle {
            margin: 5px;
            width: 99%;
            float: left;
        }

        .stateStyle li {
            float: left;
            padding: 5px 10px;
            margin-right: 20px;
        }

        .bg1 {
            background-color: lightgreen
        }

        .bg3 {
            background-color: red
        }

        .bg2 {
            background-color: yellow
        }

        .stateStyle .tip li, .scroll .tip li {
            background-image: url(<s:url value="/project/sysinfo/images/icon.gif"/>);
            background-repeat: no-repeat;
            padding-left: 15px
        }

        .tip li.tipA {
            background-position: 0px 0px
        }

        .tip li.tipB {
            background-position: 0 -28px
        }

        .tip li.tipC {
            background-position: 0 -56px
        }

        .tip li.tip2 {
            background-position: 0px -5px
        }

        .tip li.tip1 {
            background-position: 0 -33px
        }

        .tip li.tip0 {
            background-position: 0 -61px
        }

        .stateStyle select {
            float: right;
            margin-right: 10px
        }

    </style>

    <link rel="stylesheet" href="/portal/css/formalize.css"/>
    <link rel="stylesheet" href="/portal/css/page.css"/>
    <link rel="stylesheet" href="/portal/css/default/imgs.css"/>
    <link rel="stylesheet" href="/portal/css/reset.css"/>
</head>
<body>
<div class="main mw1002" style="text-align: center;">
    <!--Ctrl-->
    <div class="ctrl clearfix">
        <div class="fl"><img id="show" onclick="showHide();" src="/portal/css/default/images/sideBar_arrow_right.jpg"
                             width="46" height="30" alt="展开"></div>
        <div class="posi fl">
            <ul>
                <li class="fin">首页</li>
            </ul>
        </div>

    </div>

    <!--Ctrl End-->
    <div class="clearfix pt45">
        <div class="mb10">

            <!--Panel_8-->
            <div class="panel_4 mb10 panel_8">
                <header>
                    <div class="tit">
                        <div class="bg clearfix">
                            <h5 class="fl stats">信息安全事件管理台帐</h5>

                        </div>
                    </div>
                </header>
                <div class="con clearfix">
                    <div><h1 style="color: black;padding: 5px;text-align: center">信息安全事件管理台帐</h1></div>
                    
                    <div>
                        <div style="">
                            <div class="stateStyle">

                                <div class="filter">
                                    <div class="query">
                                        <div class="p8">
                                            <s:form action="report" method="post" namespace="/project/event">
                                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                                    <tr>
                                                        <td class="t_r">单位名称</td>
                                                        <td>
                                                            <s:textfield name="event.companyName" cssClass="input_xlarge"/>
                                                        </td>
                                                        <td class="t_r">信息来源</td>
                                                        <td>
                                                            <s:select cssStyle="float:left" name="event.messageSource"
                                                                      list="#{'':'选择','内部':'内部','外部':'外部'}" cssClass="input_xlarge"></s:select>
                                                        </td>
                                                        <td class="t_r">业务分类</td>
                                                        <td>
                                                            <s:select cssStyle="float:left" name="event.classification"
                                                                      list="#{'':'选择','企业':'企业','生产':'生产','企业/生产':'企业/生产'}" cssClass="input_xlarge"></s:select>
                                                        </td>
                                                    </tr>
                                                    <%-- <tr>
                                                        <td class="t_r">检查日期</td>
                                                        <td colspan="7">
                                                            <s:textfield cssStyle="width:110px" name="inspect.beginInspectDate" cssClass="input_xlarge" readonly="true"
                                                                         id="beginDate"/> 至
                                                            <s:textfield cssStyle="width:110px" name="inspect.endInspectDate" cssClass="input_xlarge" readonly="true"
                                                                         id="endDate"/>
                                                        </td>

                                                    </tr> --%>
                                                    <tr>
                                                        <td colspan="6" class="t_c">
                                                            <input type="submit" value="检 索"/>
																<%-- &nbsp;<input type="button" value="导入数据" id="importBtn"/><input type="button" value="管理" id="manage" />
                                                            --%><s:if test="#session.loginName=='G001000001702549' || #session.loginName=='ADMIN2549' ||  #session.loginName=='G020001000492549'">
                                                                &nbsp;<input type="button" value="导入数据" id="importBtn"/><input type="button" value="管理" id="manage" />
                                                            </s:if> 
                                                        </td>
                                                    </tr>
                                                </table>
                                            </s:form>
                                        </div>
                                    </div>
                                </div>
                                <%--<ul class="state">--%>
                                <%--<s:if test="#session.loginName == 'G002000000332549' || #session.loginName == 'ADMIN2549'">--%>
                                <%--<li>--%>
                                <%--<input type="button" value="数据导入"--%>
                                <%--onclick="location.href='<s:url action="list.action"--%>
                                <%--namespace="/project/sysinfo"/>'"/>--%>
                                <%--</li>--%>
                                <%--</s:if>--%>
                                <%--<li class="bg1">已完成</li>--%>
                                <%--<li class="bg2">进行中</li>--%>
                                <%--<li class="bg3">延时</li>--%>
                                <%--</ul>--%>
                                <%--<form action="/portal/project/sysinfo/report.action" method="GET">--%>
                                <%--<s:select name="year"--%>
                                <%--list="#{'2014':'2014年度','2015':'2015年度','2016':'2016年度'}"></s:select>--%>

                                <%--</form>--%>
                            </div>
                            <div class="scroll">
                                <table border="0" cellspacing="0" cellpadding="0" width="99%">
                                    <thead>
                                    <tr>
                                        <td rowspan="2">序号</td>
                                        <td rowspan="2">单位名称</td>
                                        <td colspan="14">事件管理信息</td>
                                    </tr>
                                    <tr>
                                        <td>报告时间</td>
                                        <td>事件发生时间</td>
                                        <td>信息来源</td>
                                        <td>业务分类</td>
                                        <td>事件类型</td>
                                        <td>涉及网络或系统</td>
                                        <td>事件描述</td>
                                        <td>事件等级</td>
                                        <td>事件原因分析</td>
                                        <td>处理过程</td>
                                        <td>处理结果</td>
                                        <td>事件报告人</td>
                                        <td>联系方式</td>
                                        <td>备注</td>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <s:iterator value="pageResultSet.list" status="s">
                                    <tr>
                                        <td><s:property value="#s.index+1"/></td>
                                        <td><s:property value="companyName" /> </td>
                                        <td><s:date name="telTime" format="yyyy/MM/dd"/> </td>
                                        <%-- <td style="text-align:left;word-break:break-all;white-space: normal; "><s:property value="inspectInfo"/></td> --%>
                                        <td><s:date name="beginTime" format="yyyy/MM/dd"/></td>
                                        <td><s:property value="messageSource"/></td>
                                        <td><s:property value="classification"/></td>
                                        <td><s:property value="eventType"/></td>
                                        <td><s:property value="networkSystem"/></td>
                                        <td><s:property value="descriptions"/></td>
                                        <td><s:property value="ranks"/></td>
                                        <td><s:property value="reasons" /> </td>
                                        <td><s:property value="process" /></td>
                                        <td><s:property value="results"/></td>
                                        <td><s:property value="reporter"/></td>
                                        <td><s:property value="telphone"/>  </td>
                                        <td><s:property value="memo"/></td>
                                    </tr>
                                    </s:iterator>
                                    <tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="clear"></div>
                
                </div>
            </div>

        </div>
    </div>
</div>
</body>

<script>
    $(document).ready(function () {

        $("#importBtn").click(function () {//导入数据
            location.href = "<s:url value="/project/import.jsp"/>?type=4";
        });
		$("#manage").click(function(){
			location.href="<s:url value="/project/event/events.action"/>";
			
		});
        $('#beginDate').datepicker({
            //inline: true
            "changeYear": true,
            "showButtonPanel": true,
            "closeText": '清除',
            "currentText": 'beginDate'//仅作为“清除”按钮的判断条件
        });

        //$("#indicatorDate").datepicker('option', $.datepicker.regional['zh-CN']);
        $('#endDate').datepicker({
            //inline: true
            "changeYear": true,
            "showButtonPanel": true,
            "closeText": '清除',
            "currentText": 'endDate'//仅作为“清除”按钮的判断条件
        });


        //datepicker的“清除”功能
        $(".ui-datepicker-close").live("click", function () {
            if ($(this).parent("div").children("button:eq(0)").text() == "beginDate") $("#beginDate").val("");
            if ($(this).parent("div").children("button:eq(0)").text() == "endDate") $("#endDate").val("");
        });
    })
</script>
</html>