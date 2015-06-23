<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <title></title>
    <link rel="stylesheet" href="../css/formalize.css"/>

    <link rel="stylesheet" href="../css/page.css"/>
    <link rel="stylesheet" href="../css/default/imgs.css"/>
    <link rel="stylesheet" href="../css/reset.css"/>
    <!--[if IE 6.0]>
    <script src="../js/iepng.js" type="text/javascript"></script>
    <script type="text/javascript">
        EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
    </script>
    <![endif]-->
    <script type="text/javascript" src="/portal/js/jquery-1.7.1.js"></script>
    <style>
        #deptTb li {
            float: left; /* 往左浮动 */
             line-height: 30px;
        }
    </style>
</head>
<body>
<div class="main mw1002">
    <!--Ctrl-->
    <div class="ctrl clearfix">
        <div class="fl"><img id="show" onclick="showHide();" src="../css/default/images/sideBar_arrow_right.jpg"
                             width="46" height="30" alt="展开"></div>
        <div class="posi fl">
            <ul>
                <li><a href="javascript:window.location.href='/portal/center/yygl/yg_index.jsp'">超时统计</a></li>
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
                            <h5 class="fl stats">
                                <s:if test="item == \"1\"">
                                    3天以下
                                </s:if><s:elseif test="item == \"2\"">
                                3-10天
                            </s:elseif><s:elseif test="item == \"3\"">
                                10-30天
                            </s:elseif><s:elseif test="item == \"4\"">
                                30-100天
                            </s:elseif><s:elseif test="item == \"5\"">
                                100天以上
                            </s:elseif><s:else>
                                <s:property value="item"/>
                            </s:else> 超时流程统计
                            </h5>

                        </div>
                    </div>
                </header>
                <div class="con clearfix">

                    <div class="gray_border">
                        <div class="block">

                            <div style="border:1px;width:100%;float:left;">

                                <table width="100%" class="table_1" id="mytable">
                                    <thead>
                                    <tr class="tit">

                                        <td class="t_c" width="20%"><s:if
                                                test="#parameters.type[0]==\"dept\"">部门名称</s:if><s:else>超时时长</s:else></td>
                                        <td class="t_c" width="10%">超时事项</td>
                                        <td class="t_c" width="10%">延时申请</td>
                                        <td class="t_c" width="10%">百分比</td>
                                        <td class="t_c" width="20%"><s:if
                                                test="#parameters.type[0]==\"dept\"">部门名称</s:if><s:else>超时时长</s:else></td>
                                        <td class="t_c" width="10%">超时事项</td>
                                        <td class="t_c" width="10%">延时申请</td>
                                        <td class="t_c" width="10%">百分比</td>
                                    </tr>
                                    </thead>
                                </table>
                                <div style="width:100%">
                                    <ul id="deptTb" style="text-align: center;width:100%;float:left">
                                        <s:iterator value="delayItemReportVoList">
                                            <li style="width:20%">
                                                <s:if test="#parameters.type[0]==\"dept\"">
                                                <a
                                                    href="/portal/delayWarn/listProcess.action?type=sub&delayProcess.department=<s:property value="itemName"/>&delayProcess.delayType=<s:property value="#parameters.item"/>"><s:property
                                                    value="itemName"/></a>
                                                </s:if>
                                                <s:if test="#parameters.type[0]==\"day\"">
                                                    <a
                                                            href="/portal/delayWarn/listProcess.action?type=sub&delayProcess.department=<s:property value="#parameters.item"/>&delayProcess.delayType=<s:property value="itemName"/>"><s:property
                                                            value="itemName"/></a>
                                                </s:if>
                                            </li>
                                            <li style="width:10%"><s:property value="itemQuantity"/></li>
                                            <li style="width:10%"><s:property value="applayQuantity"/></li>
                                            <li style="width:10%"><s:property value="percent"/>%</li>
                                        </s:iterator>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="clear"></div>
                </div>
            </div>

        </div>
    </div>
</div>
<script>

</script>
</body>
</html>
