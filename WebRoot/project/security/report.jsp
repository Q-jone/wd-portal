<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta charset="utf-8"/>
    <title>集团信息安全等保测评工作进度完成情况</title>
    <link rel="stylesheet" href="<%=basePath %>css/page.css"/>
    <link rel="stylesheet" href="<%=basePath %>css/default/imgs.css"/>
    <link rel="stylesheet" href="<%=basePath %>css/reset.css"/>
    <!--[if IE 6.0]>
    <script src="../js/iepng.js" type="text/javascript"></script>
    <script type="text/javascript">
        EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
    </script>
    <![endif]-->
    <script src="<%=basePath %>js/html5.js"></script>
    <script src="<%=basePath %>js/jquery-1.7.1.js"></script>
    <!--<script src="../js/switchDept.js"></script>-->
    <script src="<%=basePath %>js/show.js"></script>
    <style type="text/css">

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
            background-color: pink
        }

        .bg2 {
            background-color: lightgreen
        }

        .bg3 {
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
    <script>

        $(function () {

            $("#importBtn").click(function(){
                location.href="<s:url value="/project/import.jsp"/>?type=1";
            });

            $("select").change(function () {
                $("#form").submit();
            });
            $("#manageBtn").click(function(){
            	
            	location.href="<s:url value="/project/security/securities.action"/>";
            });
        });

       
    </script>

</head>
<body>
<div class="main">
    <!--Ctrl-->
    <div class="ctrl clearfix">
        <div class="fl"><img id="show" onclick="showHide();"
                             src="<%=basePath %>css/default/images/sideBar_arrow_right.jpg" width="46" height="30"
                             alt="收起"></div>
        <div class="posi fl">
            <ul>
                <li class="fin"></li>
                <li class="fin">首页</li>
            </ul>
        </div>

        <div style="display:none;" class="fr lit_nav nwarp">
            <ul>
                <li class="selected"><a class="print" href="#">打印</a></li>
                <li><a class="express" href="#">导出数据</a></li>
                <li class="selected"><a class="table" href="#">表格模式</a></li>
                <li><a class="treeOpen" href="#">打开树</a></li>
                <li><a class="filterClose" href="#">关闭过滤</a></li>
            </ul>
        </div>
    </div>
    <!--Ctrl End-->

    <div class="con clearfix">
        <div class="clearfix pt45">
            <div class="mb10">

                <!--Panel_8-->
                <div class="panel_4 mb10 panel_8" style="background-color: white;">
                    <header>
                        <div class="tit">
                            <div class="bg clearfix">
                                <h5 class="fl stats">集团信息安全等保测评工作进度完成情况</h5>

                            </div>
                        </div>
                    </header>
                    <div><h1 style="color: black;padding: 5px;text-align: center"><s:if test="year == null">2014</s:if><s:else><s:property value="year"/></s:else>年度集团信息安全等保测评工作进度完成情况</h1></div>
                    <div class="stateStyle">

                        <s:if test="#session.loginName=='G001000001702549' || #session.loginName=='ADMIN2549'">
                            <div style="float:left;"><input type="button" value="管理" id="manageBtn"/></div>
                            <div style="float:left;"><input type="button" value="导入数据" id="importBtn"/></div>
                        </s:if>
                        <ul class="tip">
                            <li class="tipC">计划正常</li>
                            <li class="tipA">计划超期</li>
                        </ul>
                        <s:form action="report.action" id="form" method="post" namespace="/project/security">
                            <s:select name="year"
                                      list="#{'2014':'2014年度','2015':'2015年度','2016':'2016年度'}"></s:select>

                        </s:form>
                    </div>
                    <div style="width:100%;">
                        <table width="100%" class="table_1" id="mytable">
                            <thead>
                            <tr class="tit">
                                <td class="t_c" width="10">序号</td>
                                <td class="t_c" width="120">系统名称</td>
                                <td class="t_c" width="100">类别</td>
                                <td class="t_c" width="50">系统重要性</td>
                                <td class="t_c" width="30">系统定级</td>
                                <td class="t_c" width="100">归属单位</td>
                                <td class="t_c" width="50">实施系统数量</td>
                                <td class="t_c" width="100">统筹计划开始时间</td>
                                <td class="t_c" width="100">统筹计划完成时间</td>
                                <td class="t_c" width="30">状态</td>
                                <td class="t_c" width="200">备注</td>
                            </tr>
                            </thead>
                            <tbody>

                            <s:iterator value="pageResultSet.list" status="s">
                                <tr id="dataTr">
                                    <td class="t_c"><s:property value="#s.index+1"/></td>
                                    <td class="t_c"><s:property value="sysName"/></td>
                                    <td class="t_l"><s:property value="category"/></td>
                                    <td class="t_c"><s:property value="important"/></td>
                                    <td class="t_c"><s:property value="sysLevel"/></td>
                                    <td class="t_c"><s:property value="department"/></td>
                                    <td class="t_c"><s:property value="excuteQuantity"/></td>
                                    <td class="t_c"><s:date name="planBeginDate" format="yyyy年MM月dd日"/></td>
                                    <td class="t_c"><s:date name="planEndDate" format="yyyy年MM月dd日"/></td>
                                    <td class="t_c">
                                        <div class="stateStyle">
                                        <ul class="tip">
                                            <s:if test="delayed == 1"><li class="tipA">超<s:property value="delay"/>天</li></s:if>
                                            <s:if test="delayed == 0 || delayed == -1"><li class="tipC">&nbsp;</li></s:if>
                                            <s:if test="delayed == null">&nbsp;</s:if>
                                        </ul>
                                        </div>
                                    </td>
                                    <td style="text-align: left"><s:property value="memo"/></td>

                                </tr>
                            </s:iterator>
                            </tbody>
                            <s:if test="projects.size<1">
                                <tr class="tfoot">
                                    <td colspan="30">
                                        <div class="clearfix">无相关数据</div>
                                    </td>
                                </tr>
                            </s:if>
                        </table>

                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--Filter End-->


</div>
</div>
</body>
</html>