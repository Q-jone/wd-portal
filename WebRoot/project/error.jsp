<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>数据导入失败</title>
    <link rel="stylesheet" href="<s:url value="/css/formalize.css"/>" />
    <link rel="stylesheet" href="<s:url value="/css/page.css"/>" />
    <link rel="stylesheet" href="<s:url value="/css/default/imgs.css"/>" />
    <link rel="stylesheet" href="<s:url value="/css/reset.css"/>" />
    <link type="text/css" href="<s:url value="/css/flick/jquery-ui-1.8.18.custom.css"/>" rel="stylesheet" />
    <!--[if IE 6.0]>
    <script src="js/iepng.js" type="text/javascript"></script>
    <script type="text/javascript">
        EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
    </script>
    <![endif]-->
    <script src="<s:url value="/js/html5.js"/>"></script>
    <script src="<s:url value="/js/jquery-1.7.1.js"/>"></script>
    <script src="<s:url value="/js/show.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/js/flick/jquery-ui-1.8.18.custom.min.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/js/flick/jquery.ui.datepicker-zh-CN.js"/>"></script>
    <style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
    </style>

</head>

<body>
<div class="main">
    <!--Ctrl-->
    <div class="ctrl clearfix">
        <div class="fl"><img id="show" onclick="showHide();" src="<s:url value="/css/default/images/sideBar_arrow_right.jpg"/>" width="46" height="30" title="收起"></div>
        <div class="posi fl">
            <ul>
                <li class="fin">数据导入失败</li>
            </ul>
        </div>
    </div>
    <!--Ctrl End-->
    <!--Filter--><!--Filter End-->
    <!--Table-->

    <div class="mb10">
        <div class="panel_4 mb10 panel_8" style="background-color: white;">
            <header>
                <div class="tit">
                    <div class="bg clearfix">

                    </div>
                </div>
            </header>

            <div class="stateStyle">


            </div>
            <div style="width:100%;">
                <table width="100%" class="table_1" id="mytable">
                    <thead>
                    <tr class="tit">
                        <td class="t_c" width="10">序号</td>
                        <td width="120">日志详情</td>
                    </tr>
                    </thead>
                    <tbody>

                    <s:iterator value="result" status="s">
                        <tr id="dataTr">
                            <td class="t_c"><s:property value="#s.index+1"/></td>
                            <td><s:property value="result[#s.index]"/></td>
                        </tr>
                    </s:iterator>
                    </tbody>
                    <s:if test="result.size<1">
                        <tr class="tfoot">
                            <td colspan="30">
                                <div class="clearfix">无相关数据</div>
                            </td>
                        </tr>
                    </s:if>
                    <tr id="dataTr">
                        <td class="t_c" colspan="2"><input type="button" value="返回" onclick="location.href='/portal/project/import.jsp?type=<s:property value="type"/>'"/></td>

                    </tr>
                </table>

            </div>
        </div>
    </div>
    <!--Table End-->
</div>
</body>
</html>
