<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head lang="zh">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script type="text/javascript" src="<s:url value="/js/jquery-1.7.1.js"/>"></script>
    <script src="<s:url value="/js/html5.js"/>"></script>
    <title>信息化项目实施推进计划表1</title>
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
            /*width: 100px*/
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
            background-color: pink
        }

        .bg2 {
            background-color: lightgreen
        }

        .bg3 {
            background-color: orange
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
                <li><a href="javascript:window.location.href='/portal/project/sysinfo/report.action'">信息化项目实施推进计划表</a>
                </li>
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
                            <h5 class="fl stats">信息化项目实施推进计划表</h5>

                        </div>
                    </div>
                </header>
                <div class="con clearfix">
                    <div><h1 style="color: black;padding: 5px;text-align: center"><s:if test="year == null">2014</s:if><s:else><s:property value="year"/></s:else>年度信息化项目实施推进计划</h1></div>
                    <div>
                        <div style="">
                            <div class="stateStyle">
                                <ul class="state">
                                    <s:if test="#session.loginName == 'G002000000332549' || #session.loginName == 'ADMIN2549'">
                                        <li>
                                            <input type="button" value="项目管理"
                                                   onclick="location.href='<s:url action="list.action"
                                                                                  namespace="/project/sysinfo"/>'"/>
                                        </li>
                                    </s:if>
                                    <li class="bg1">前期策划</li>
                                    <li class="bg2">立项采购</li>
                                    <li class="bg3">实施推进</li>
                                </ul>
                                <ul class="tip">
                                    <li class="tipC">计划正常</li>
                                    <li class="tipB">计划超期</li>
                                    <li class="tipA">阶段超期</li>
                                </ul>
                                <form action="/portal/project/sysinfo/report.action" method="GET">
                                    <s:select name="year"
                                              list="#{'2014':'2014年度','2015':'2015年度','2016':'2016年度'}"></s:select>

                                </form>
                            </div>
                            <div class="scroll">
                                <table width="800" border="0" cellspacing="0" cellpadding="0">
                                    <thead>
                                    <tr>
                                        <td colspan="44" style="text-align:center;">2014年度</td>
                                    </tr>
                                    <tr>
                                        <td rowspan="2">序号</td>
                                        <td rowspan="2" width="100px">项目名称</td>
                                        <td rowspan="2">责任单位</td>
                                        <%--<th rowspan="2">分管领导</th>--%>
                                        <%--<th rowspan="2">责任人</th>--%>
                                        <%--<th rowspan="2">项目目标</th>--%>
                                        <td rowspan="2" style="width:300px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 年度推进目标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </td>
                                        <%--<th rowspan="2">项目状态</th>--%>
                                        <td colspan="3">1</td>
                                        <td colspan="3">2</td>
                                        <td colspan="3">3</td>
                                        <td colspan="3">4</td>
                                        <td colspan="3">5</td>
                                        <td colspan="3">6</td>
                                        <td colspan="3">7</td>
                                        <td colspan="3">8</td>
                                        <td colspan="3">9</td>
                                        <td colspan="3">10</td>
                                        <td colspan="3">11</td>
                                        <td colspan="3">12</td>
                                    </tr>
                                    <tr>
                                        <td>上旬</td>
                                        <td>中旬</td>
                                        <td>下旬</td>
                                        <td>上旬</td>
                                        <td>中旬</td>
                                        <td>下旬</td>
                                        <td>上旬</td>
                                        <td>中旬</td>
                                        <td>下旬</td>
                                        <td>上旬</td>
                                        <td>中旬</td>
                                        <td>下旬</td>
                                        <td>上旬</td>
                                        <td>中旬</td>
                                        <td>下旬</td>
                                        <td>上旬</td>
                                        <td>中旬</td>
                                        <td>下旬</td>
                                        <td>上旬</td>
                                        <td>中旬</td>
                                        <td>下旬</td>
                                        <td>上旬</td>
                                        <td>中旬</td>
                                        <td>下旬</td>
                                        <td>上旬</td>
                                        <td>中旬</td>
                                        <td>下旬</td>
                                        <td>上旬</td>
                                        <td>中旬</td>
                                        <td>下旬</td>
                                        <td>上旬</td>
                                        <td>中旬</td>
                                        <td>下旬</td>
                                        <td>上旬</td>
                                        <td>中旬</td>
                                        <td>下旬</td>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <s:iterator value="projects" status="s">
                                    <tr>
                                        <td><s:property value="#s.index+1"/></td>
                                        <td>
                                            <ul class="tip" style="padding-left: 0;">
                                                <li class="tip<s:if test="currentPlan.manual == null"><s:property value="currentPlan.planStatus"/></s:if><s:else><s:property value="currentPlan.manual"/></s:else>"><s:property
                                                        value="projectName"/></li>
                                            </ul>

                                        </td>
                                        <td><s:property value="department"/></td>
                                        <%--<td><s:property value="leader"/></td>--%>
                                        <%--<td><s:property value="responsible"/></td>--%>
                                        <%--<td width="150"><s:property value="projectGoal"/></td>--%>
                                        <td style="text-align:left;word-break:break-all;white-space: normal; width:300px"><s:property value="projectForwardGoals" escape="false"/></td>
                                        
                                        <!-- style="text-align:left;white-space: normal;width:200px" -->
                                        <%--<td><s:property value="projectStatus" escape="false"/></td>--%>
                                        <s:iterator value="projectPlans" status="x"><!-- 12个月36旬数据 -->
                                        <s:if test="startPosition>0">
                                            <td colspan="<s:property value="startPosition"/>">&nbsp;</td>
                                        </s:if>
                                        <td colspan="<s:property value="period"/>"
                                            class="bg<s:property value="planName"/>">
                                            <ul class="tip" style="padding-left: 0;">
                                                <s:if test="projectPlanId==currentPlan.projectPlanId">
                                                    <li class="tip<s:if test="currentPlan.manual == null"><s:property value="currentPlan.planStatus"/></s:if><s:else><s:property value="currentPlan.manual"/></s:else>">
                                                        <s:property value="subPlanName"/></li>
                                                </s:if>
                                                <s:else>
                                                    <s:property value="subPlanName"/>
                                                </s:else>
                                            </ul>

                                        </td>
                                        </s:iterator>
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