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
    <title>集团本部安全隔离工作进度情况</title>
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
                            <h5 class="fl stats">集团有限公司信息安全内控检查问题整改跟踪台帐</h5>

                        </div>
                    </div>
                </header>
                <div class="con clearfix">
                    <div><h1 style="color: black;padding: 5px;text-align: center">集团有限公司信息安全内控检查问题整改跟踪台帐</h1></div>
                    <div>
                        <div style="">
                            <div class="stateStyle">

                                <div class="filter">
                                    <div class="query">
                                        <div class="p8">
                                            <s:form action="report" method="post" namespace="/project/inspect">
                                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                                    <tr>
                                                        <td class="t_r">单位名称</td>
                                                        <td>
                                                            <s:textfield name="inspect.department" cssClass="input_xlarge"/>
                                                        </td>
                                                        <td class="t_r">检查问题描述</td>
                                                        <td>
                                                            <s:textfield name="inspect.inspectInfo" cssClass="input_xlarge"/>
                                                        </td>
                                                        <td class="t_r">严重程度</td>
                                                        <td>
                                                            <s:select cssStyle="float:left" name="inspect.degree"
                                                                      list="#{'':'全部','高':'高','中':'中','低':'低'}"></s:select>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="t_r">检查日期</td>
                                                        <td colspan="7">
                                                            <s:textfield cssStyle="width:110px" name="inspect.beginInspectDate" cssClass="input_xlarge" readonly="true"
                                                                         id="beginDate"/> 至
                                                            <s:textfield cssStyle="width:110px" name="inspect.endInspectDate" cssClass="input_xlarge" readonly="true"
                                                                         id="endDate"/>
                                                        </td>

                                                    </tr>
                                                    <tr>
                                                        <td colspan="6" class="t_c">
                                                            <input type="submit" value="检 索"/>

                                                            <s:if test="#session.loginName=='G001000001702549' || #session.loginName=='ADMIN2549' ||  #session.loginName=='G020001000492549'">
                                                                &nbsp;<input type="button" value="导入数据" id="importBtn"/>
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
                                        <td rowspan="2">检查日期</td>
                                        <td rowspan="2">单位名</td>
                                        <td rowspan="2" width="200">检查问题描述</td>
                                        <td rowspan="2">涉及系统</td>
                                        <td rowspan="2">严重程度</td>
                                        <td colspan="3">分类</td>
                                        <td colspan="5">整改</td>
                                        <td colspan="3">跟踪</td>
                                    </tr>
                                    <tr>
                                        <td>业务类型</td>
                                        <td>科目</td>
                                        <td>科目子类</td>
                                        <td>整改方案</td>
                                        <td>计划开始时间</td>
                                        <td>计划完成时间</td>
                                        <td>落实情况</td>
                                        <td>备注</td>
                                        <td>跟踪日期</td>
                                        <td>复核情况</td>
                                        <td>备注</td>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <s:iterator value="pageResultSet.list" status="s">
                                    <tr>
                                        <td><s:property value="#s.index+1"/></td>
                                        <td><s:date format="yyyy/MM/dd" name="inspectDate"/></td>
                                        <td><s:property value="department"/></td>
                                        <td style="text-align:left;word-break:break-all;white-space: normal; "><s:property value="inspectInfo"/></td>
                                        <td><s:property value="sysName"/></td>
                                        <td><s:property value="degree"/></td>
                                        <td><s:property value="category"/></td>
                                        <td><s:property value="subject"/></td>
                                        <td><s:property value="subSubject"/></td>
                                        <td><s:property value="plan"/></td>
                                        <td><s:date format="yyyy/MM/dd" name="planBeginDate"/></td>
                                        <td><s:date format="yyyy/MM/dd" name="planEndDate"/></td>
                                        <td><s:property value="workable"/></td>
                                        <td><s:property value="reformMemo"/></td>
                                        <td><s:date format="yyyy/MM/dd" name="tractDate"/></td>
                                        <td><s:property value="review"/></td>
                                        <td><s:property value="tractMemo"/></td>
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

        $("#importBtn").click(function () {
            location.href = "<s:url value="/project/import.jsp"/>?type=2";
        })

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