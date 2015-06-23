<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head lang="zh">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script type="text/javascript" src="<s:url value="/js/jquery-1.7.1.js"/>"></script>
    <script src="<s:url value="/js/html5.js"/>"></script>
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
            width: 100%;
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
            background-position: 0 -56px
        }

        .tip li.tipB {
            background-position: 0 -28px
        }

        .tip li.tipC {
            background-position: 0px 0px
        }

        .tip li.tip {
            background-position: -100px -100px
        }

        .tip li.tip0 {
            background-position: 0px -5px
        }

        .tip li.tip2 {
            background-position: 0 -33px
        }

        .tip li.tip1 {
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
                            <h5 class="fl stats">集团本部安全隔离工作进度情况</h5>

                        </div>
                    </div>
                </header>
                <div class="con clearfix">
                    <div><h1 style="color: black;padding: 5px;text-align: center">集团本部安全隔离工作进度情况</h1></div>
                    <div>
                        <div style="">
                            <div class="stateStyle">
                                <ul class="state">
                                    <s:if test="#session.loginName == 'G002000000332549' || #session.loginName == 'ADMIN2549'">
                                        <%--<li>--%>
                                        <%--<input type="button" value="项目管理"--%>
                                        <%--onclick="location.href='<s:url action="list.action"--%>
                                        <%--namespace="/project/sysinfo"/>'"/>--%>
                                        <%--</li>--%>
                                    </s:if>
                                    <ul class="tip">
                                        <li class="tipA">已完成</li>
                                        <li class="tipB">进行中</li>
                                        <li class="tipC">延时</li>
                                    </ul>
                                </ul>
                                <%--<form action="/portal/project/sysinfo/report.action" method="GET">--%>
                                <%--<s:select name="year"--%>
                                <%--list="#{'2014':'2014年度','2015':'2015年度','2016':'2016年度'}"></s:select>--%>

                                <%--</form>--%>
                            </div>
                            <div class="scroll" style="margin:5px;">
                                <table border="0" cellspacing="0" cellpadding="0" width="99%">
                                    <thead>
                                    <tr>
                                        <th rowspan="2">公司部门</th>
                                        <th rowspan="2">后台服务部署<br>
                                            （6月6日-6月27日）
                                        </th>
                                        <th rowspan="2">个人无线终端入网<br>
                                            （6月24日-6月27日）
                                        </th>
                                        <th>管理终端入网<br>
                                            （7月14日-7月22日）
                                        </th>
                                        <th>互联网终端入网<br>
                                            （7月17日-7月28日）
                                        </th>
                                        <th>管理网终端入网<br>
                                            （7月17日-7月28日）
                                        </th>
                                        <th rowspan="2">终端隔离业务上线<br>
                                         （7月17日-7月29日）
                                        </th>
                                    </tr>
                                    <tr><td>利旧</td>
                                        <td>利旧</td>
                                        <td>新增</td>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <s:iterator value="pageResultSet.list">
                                    <tr>
                                        <td><s:property value="department"/></td>
                                        <td class="bg">

                                            <ul class="tip">
                                                <li class="tip<s:property value="webServiceStatus"/>"><s:if
                                                        test="webServicePlanBeginDate != null"><s:date
                                                        name="webServicePlanBeginDate"
                                                        format="MM月dd日"/>-</s:if><s:date
                                                        name="webServicePlanEndDate" format="MM月dd日"/></li>
                                            </ul>

                                        </td>
                                        <td>

                                            <ul class="tip">
                                                <li class="tip<s:property value="wifiStatus"/>"><s:if
                                                        test="wifiPlanBeginDate != null"><s:date
                                                        name="wifiPlanBeginDate"
                                                        format="MM月dd日"/>-</s:if><s:date
                                                        name="wifiPlanEndDate" format="MM月dd日"/></li>
                                            </ul>

                                        </td>
                                        <td>
                                            <ul class="tip">
                                                <li class="tip<s:property value="mgrStatus"/>"><s:if
                                                        test="mgrPlanBeginDate != null"><s:date name="mgrPlanBeginDate"
                                                                                                format="MM月dd日"/>-</s:if><s:date
                                                        name="mgrPlanEndDate" format="MM月dd日"/></li>
                                            </ul>
                                        </td>
                                        <td>
                                            <ul class="tip">
                                                <li class="tip<s:property value="netOldStatus"/>"><s:if
                                                        test="netOldPlanBeginDate != null"><s:date
                                                        name="netOldPlanBeginDate"
                                                        format="MM月dd日"/>-</s:if><s:date
                                                        name="netOldPlanEndDate" format="MM月dd日"/></li>
                                            </ul>

                                        </td>
                                        <td>
                                            <ul class="tip">
                                                <li class="tip<s:property value="netNewStatus"/>"><s:if
                                                        test="netNewPlanBeginDate != null"><s:date
                                                        name="netNewPlanBeginDate"
                                                        format="MM月dd日"/>-</s:if><s:date
                                                        name="netNewPlanEndDate" format="MM月dd日"/></li>
                                            </ul>


                                        </td>
                                        <td>

                                            <ul class="tip">
                                                <li class="tip<s:property value="isolationStatus"/>"><s:if
                                                        test="isolationPlanBeginDate != null"><s:date
                                                        name="isolationPlanBeginDate" format="MM月dd日"/>-</s:if><s:date
                                                        name="isolationPlanEndDate" format="MM月dd日"/></li>
                                            </ul>

                                        </td>
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

        $("select").change(function () {
            $("form").submit();
        })
    })
</script>
</html>