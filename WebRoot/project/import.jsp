<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>数据导入</title>
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
                <li class="fin">数据导入</li>
            </ul>
        </div>
    </div>
    <!--Ctrl End-->
    <!--Filter--><!--Filter End-->
    <!--Table-->

    <div class="mb10 pt45">
        <s:form action="import.action" namespace="/project" method="post"
                enctype="multipart/form-data">
<input type="hidden" name="type" value="<s:property value="#parameters.type"/>"/>

            <table width="100%"  class="table_1">
                <thead>
                <th colspan="4" class="t_r">
                    &nbsp;</th>
                </thead>
                <tbody>
                <tr>
                    <td class="t_r lableTd">选择文件：<br>只支持97-2003版的excel文件</td>
                    <td colspan="3">
                        <s:file name="upload" cssClass="input_xxlarge"/>
                    </td>
                </tr>


                <tr class="tfoot">
                    <td colspan="4" class="t_r">
                        <input type="submit" value="上 传"/>&nbsp;
                        <s:if test="#parameters.type[0]==\"1\"">
                            <input type="button" value="后 退" onclick="location.href='/portal/project/security/report.action'"/>
                        </s:if>
                        <s:if test="#parameters.type[0]==\"2\"">
                        <input type="button" value="后 退" onclick="location.href='/portal/project/inspect/report.action'"/>
                        </s:if>
                        <s:if test="#parameters.type[0]==\"3\"">
                            <input type="button" value="后 退" onclick="location.href='/portal/project/risk/report.action'"/>
                        </s:if>
                    </td>
                </tr>

                </tbody>

            </table>
        </s:form>
    </div>
    <!--Table End-->
</div>
</body>
</html>
