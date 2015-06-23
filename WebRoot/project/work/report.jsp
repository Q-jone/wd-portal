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
    <title>集团信息化重点工作推进计划</title>
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
    <link type="text/css" href="<%=basePath %>css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
    <script type="text/javascript" src="<%=basePath %>js/flick/jquery-ui-1.8.18.custom.min.js"></script>
    <script>

        $(function () {
            $( "#dialog-message" ).dialog({
                autoOpen: false,
                modal: false,
                width:500,
                close:function(){
                    $("#hisTb").html("");
                }
            });

            $("select").change(function () {
                $("#form").submit();
            });

            $("#manageBtn").click(function(){
                location.href="<s:url value="/project/work/workSecludes.action"/>";
            });
        });

        function listHistory(id) {
            $.post("<s:url value="/project/progress/progresses.action"/>",{"workSecludeId":id,"format":"json"},function(data){
                var html = '';
                $.each(data,function(i,n){
                    var time = n.createTime;
                    html += '<tr><td class="t_c">'+(i+1)+'</td>';
                    html += '<td class="t_c">'+ n.progress +'</td>';
                    html += '<td class="t_c">'+ (time.year+1900)+'年'+(time.month+1)+'月'+time.day+'日'+'</td></tr>';

                });
                $("#hisTb").html(html);
                $( "#dialog-message").dialog( "open" );

            },"json");
        }

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
                                <h5 class="fl stats">集团信息化重点工作推进计划</h5>

                            </div>
                        </div>
                    </header>
                    <div><h1 style="color: black;padding: 5px;text-align: center"><s:if test="year == null">2014</s:if><s:else><s:property value="year"/></s:else>年度集团信息化重点工作推进计划</h1></div>
                    <div style="text-align: right;margin: 5px;">
                        <s:if test="#session.deptId==2549">
                            <div style="float:left;"><input type="button" value="管理" id="manageBtn"/></div>
                        </s:if>
                        <s:form action="report.action" id="form" method="post" namespace="/project/work">
                            <s:hidden name="workSeclude.confirm" value="1"/>
                            <s:select name="workSeclude.year"
                                      list="#{'2014':'2014年度','2015':'2015年度','2016':'2016年度'}"></s:select>

                        </s:form>
                    </div>
                    <div style="width:100%;">
                        <table width="100%" class="table_1" id="mytable">
                            <thead>
                            <tr class="tit">
                                <td class="t_c" width="10">序号</td>
                                <td class="t_c" width="120">重点工作</td>
                                <td class="t_c" width="100">目标及要求</td>
                                <td class="t_c" width="50">责任单位</td>
                                <td class="t_c" width="30">配合单位</td>
                                <td class="t_c" width="100">推进情况</td>
                                <td class="t_c" width="50">补充说明</td>
                                <td class="t_c" width="100">对应绩效考核项</td>
                                <td class="t_c" width="100">信息中心对口联系人</td>
                            </tr>
                            </thead>
                            <tbody>

                            <s:iterator value="pageResultSet.list" status="s">
                                <tr id="dataTr">
                                    <td class="t_c"><s:property value="#s.index+1"/></td>
                                    <td class="t_c"><s:property value="workName" /></td>
                                    <td class="t_c"><s:property value="objective" /></td>
                                    <td class="t_c"><s:property value="responsible" /></td>
                                    <td class="t_c"><s:property value="cooperate" /></td>
                                    <td class="t_c" ><s:property value="progress" /><span style="float:right;cursor:pointer;color:blue;" onclick="listHistory('<s:property value="workSecludeId" />')">more>></span></td>
                                    <td class="t_c"><s:property value="memo" /></td>
                                    <td class="t_c"><s:property value="kpi" /></td>
                                    <td class="t_c"><s:property value="contactName"/>联系电话:<s:property value="contactTel"/></td>

                                </tr>
                            </s:iterator>
                            </tbody>
                            <s:if test="pageResultSet.list.size<1">
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

<div id="dialog-message" title="历史推进情况">
    <table width="100%" class="table_1">
        <thead>
        <tr class="tit">
            <td class="t_c" width="10">序号</td>
            <td class="t_c" width="200">推进情况</td>
            <td class="t_c" width="100">时间</td>
        </tr>
        </thead>
        <tbody id="hisTb">

        </tbody>
    </table>
</div>
</body>
</html>