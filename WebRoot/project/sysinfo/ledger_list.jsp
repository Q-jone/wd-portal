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
    <title>上海轨道交通信息化系统-台帐</title>
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
        #select {

            width: 100%;
            float: right;
            margin: 5px;
            text-align: right;
        }

        body {
            width: 100%;
            text-align: center;
        }

        li {
            background-image: url(<s:url value="/project/sysinfo/images/icon.gif"/>);
            background-repeat: no-repeat;
            padding-left: 15px;
            background-position: center;
        }

        li.tip2 {
            background-position: 0px 0px;
        }

        li.tip1 {
            background-position: 0 -28px;
        }

        li.tip0 {
            background-position: 0 -56px;
        }
    </style>
    <script>

        $(function () {


            $("select").change(function () {
                $("#form").submit();
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
                <li class="fin">上海轨道交通信息化系统-台帐</li>
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
                                <h5 class="fl stats">上海轨道交通信息化系统台帐</h5>

                            </div>
                        </div>
                    </header>
                    <div><h1 style="color: black;padding: 5px;">上海轨道交通信息化系统台帐</h1></div>
                    <div id="select">
                        <s:if test="#session.loginName == 'G002000000332549' || #session.loginName == 'ADMIN2549'">
                            <div style="float:left;margin: 5px">
                                <input type="button" value="项目管理"
                                       onclick="location.href='<s:url action="list.action"
                                                                      namespace="/project/sysinfo"/>'"/>
                            </div>
                        </s:if>
                        <s:form action="ledger" id="form" method="post" namespace="/project/sysinfo">

                            <s:select name="year" list="#{'2014':'2014年度','2015':'2015年度','2016':'2016年度'}"></s:select>

                        </s:form>
                    </div>
                    <div style="width:100%;">
                        <table width="100%"  class="table_1" id="mytable">
                            <thead>
                            <tr class="tit">
                                <td class="t_c" width="3%">序号</td>
                                <td class="t_c">大类</td>
                                <td class="t_c" >项目编号</td>
                                <td class="t_c" >项目名称</td>
                                <td class="t_c" width="250">项目描述</td>
                                <td class="t_c" >系统建设单位</td>
                                <td class="t_c">等保定级</td>
                                <td class="t_c" >项目状态</td>
                                <td class="t_c">阶段</td>
                                <td class="t_c">进度</td>
                                <td class="t_c" width="200">备注</td>
                            </tr>
                            </thead>
                            <tbody>

                            <s:iterator value="projects" status="s">
                                <tr id="dataTr">
                                    <td class="t_c"><s:property value="#s.index+1"/></td>
                                    <td class="t_c" id="pname"><s:property value="projectType"/></td>
                                    <td class="t_l"><s:property value="projectNumber" /></td>
                                    <td class="t_c"><s:property value="projectName" /></td>
                                    <td  style="text-align:left;word-break:break-all;white-space: normal; "><s:property value="projectDiscription" /></td>
                                    <td class="t_c"><s:property value="department" /></td>
                                    <td class="t_c"><s:property value="level" /></td>
                                    <td class="t_c"><s:property value="projectStatus" /></td>
                                    <td class="t_c"><s:property value="currentPlan.subPlanName" /></td>
                                    <td class="t_c">
                                        <ul style="width: 15px;margin-left: 15px;">
                                            <li class="tip<s:if test="currentPlan.manual == null"><s:property value="currentPlan.planStatus"/></s:if><s:else><s:property value="currentPlan.manual"/></s:else>">&nbsp;</li>
                                        </ul>
                                    </td>
                                    <td class="t_c"><s:property value="memo"/></td>

                                </tr>
                            </s:iterator>
                            </tbody>
                            <s:if test="projects.size<1">
                                <tr class="tfoot"><td colspan="30"><div class="clearfix">无相关数据</div></td>
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