<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head lang="zh">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script type="text/javascript" src="<s:url value="/js/jquery-1.7.1.js"/>"></script>
    <link type="text/css" href="<s:url value="/css/flick/jquery-ui-1.8.18.custom.css"/>" rel="stylesheet"/>
    <script type="text/javascript" src="<s:url value="/js/flick/jquery-ui-1.8.18.custom.min.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/js/flick/jquery.ui.datepicker-zh-CN.js"/>"></script>
    <script src="<s:url value="/js/html5.js"/>"></script>
    <title>信息安全风险管理台帐</title>


    <link rel="stylesheet" href="/portal/css/formalize.css"/>
    <link rel="stylesheet" href="/portal/css/page.css"/>
    <link rel="stylesheet" href="/portal/css/default/imgs.css"/>
    <link rel="stylesheet" href="/portal/css/reset.css"/>
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

    </style>
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
            <div class="panel_4 mb10">
                <header>
                    <div class="tit">
                        <div class="bg clearfix">
                            <h5 class="fl stats">信息安全风险管理台帐</h5>

                        </div>
                    </div>
                </header>
                <div class="con clearfix">
                    <div><h1 style="color: black;padding: 5px;text-align: center">信息安全风险管理台帐</h1></div>
                    <div>
                        <div style="">
                            <div class="stateStyle">

                                <div class="filter">
                                    <div class="query">
                                        <div class="p8">
                                            <s:form action="report" method="post" namespace="/project/risk">
                                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                                    <tr>
                                                        <td class="t_r">单位名称</td>
                                                        <td>
                                                            <s:textfield name="risk.department" cssClass="input_xlarge"/>
                                                        </td>
                                                        <td class="t_r">风险来源</td>
                                                        <td>
                                                            <s:textfield name="risk.riskFrom" cssClass="input_xlarge"/>
                                                        </td>
                                                        <td class="t_r">风险描述</td>
                                                        <td>
                                                            <s:textfield name="risk.riskInfo" cssClass="input_xlarge"/>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="t_r">处置情况跟踪</td>
                                                        <td>
                                                            <s:select cssStyle="float:left" name="risk.trackInfo"
                                                                      list="#{'':'全部','未完成':'未完成','已验证':'已验证'}"></s:select>
                                                        </td>
                                                        <td class="t_r">风险等级</td>
                                                        <td>
                                                            <s:select cssStyle="float:left" name="risk.riskLevel"
                                                                      list="#{'':'全部','高':'高','中':'中','低':'低'}"></s:select>
                                                        </td>
                                                        <td class="t_r">类别</td>
                                                        <td>
                                                            <s:textfield name="risk.category" cssClass="input_xlarge"/>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="t_r">发现时间</td>
                                                        <td colspan="7">
                                                            <s:textfield cssStyle="width:110px" name="risk.discoveryBeginDate" cssClass="input_xlarge" readonly="true"
                                                                         id="beginDate"/> 至
                                                            <s:textfield cssStyle="width:110px" name="risk.discoveryEndDate" cssClass="input_xlarge" readonly="true"
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
                            </div>
                            <div class="scroll" style="margin:5px;">
                                <table border="0" cellspacing="0" cellpadding="0" width="99%">
                                    <thead>
                                    <tr>
                                        <td width="8%">单位名</td>
                                        <td width="8%">风险来源</td>
                                        <td width="8%">发现时间</td>
                                        <td width="200px">风险描述</td>
                                        <td width="200px">涉及系统</td>
                                        <td width="8%">类别</td>
                                        <td width="8%">风险等级</td>
                                        <td width="8%">风险处置方案</td>
                                        <td width="8%">风险处置时限</td>
                                        <td width="8%">处置跟踪情况</td>
                                        <td width="8%">跟踪日期</td>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <s:iterator value="pageResultSet.list">
                                    <tr>
                                        <td><s:property value="department"/></td>
                                        <td><s:property value="riskFrom"/></td>
                                        <td><s:date name="discovery" format="yyyy/MM/dd"/></td>
                                        <td  style="text-align:left;word-break:break-all;white-space: normal; "><s:property value="riskInfo"/></td>
                                        <td style="text-align:left;word-break:break-all;white-space: normal; "><s:property value="sysName"/></td>
                                        <td><s:property value="category"/></td>
                                        <td><s:property value="riskLevel"/></td>
                                        <td><s:property value="plan"/></td>
                                        <td><s:property value="dateLimit"/></td>
                                        <td><s:property value="trackInfo"/></td>
                                        <td><s:date name="trackDate" format="yyyy/MM/dd"/></td>
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
            location.href = "<s:url value="/project/import.jsp"/>?type=3";
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