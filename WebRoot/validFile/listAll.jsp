<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta charset="utf-8"/>
    <title>行政发文管理</title>
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
    <script src="../js/html5.js"></script>
    <script src="js/jquery-1.10.2.min.js"></script>
    <script src="js/jquery-migrate-1.2.1.min.js"></script>
    <script src="js/jquery.tablesorter.js"></script>
    <script src="js/jquery.blockUI.js"></script>
    <script src="js/jquery.form.js"></script>
    <script src="../js/jquery.formalize.js"></script>
    <!--<script src="../js/switchDept.js"></script>-->
    <script src="../js/show.js"></script>
    <link type="text/css" href="css/flick/jquery-ui-1.10.3.custom.css" rel="stylesheet"/>
    <script type="text/javascript" src="js/jquery-ui-1.10.3.custom.min.js"></script>
    <script type="text/javascript" src="../js/flick/jquery.ui.datepicker-zh-CN.js"></script>
    <script type="text/javascript" src="js/page.js"></script>
    <script type="text/javascript" src="js/print.js"></script>
    <style type="text/css">
        .ui-datepicker-title span {
            display: inline;
        }

        button.ui-datepicker-current {
            display: none;
        }
    </style>
    <script type="text/javascript">
        var listCallback = function (data) {
            $("#checkboxAll").prop("checked", false);
            var p = data.pageInfo;
            var temp = "";
            if (data != null && data.list.length > 0) {
                var l = data.list;

                for (var i = 0; i < l.length; i++) {
                    temp += "<tr>";
                    temp += "<td class='t_c'><input class='clk' type='checkbox' value='" + $.notNull(l[i].ID) + "'></td>";
                    temp += "<td class='t_c'>" + (i + 1) + "</td>";
                    temp += "<td class='t_c'>" + $.notNull(l[i].SENDCODE) + "</td>";
                    temp += "<td class='t_c'>" + $.notNull(l[i].SENDDATE) + "</td>";
                    temp += "<td class='t_l'>" + $.notNull(l[i].TITLE) + "</td>";
                    temp += "<td class='t_c'>" + $.notNull(l[i].ADDPERSON) + "</td>";
                    temp += "<td class='t_c'>" + $.notNull(l[i].SENDDEPT) + "</td>";
                    temp += "<td class='t_c'>" + $.notNull(l[i].NORMATIVE) + "</td>";
                    temp += "<td class='t_c'>" + $.notNull(l[i].STATUS) + "</td>";
                    temp += "<td class='t_c'>" +
                            "<a class='showForm' href='javascript:void(0);' " +
                            "mainId='" + $.notNull(l[i].MAINID) + "' attach='" + $.notNull(l[i].ATTACH) + "'>下载</a></td>";
                    temp += "</tr>";
                }
                $("#totalPage_out").val(p.totalPage);
                $("#totalPage").html("当前显示" + (((p.currentPage - 1) * p.pageSize) + 1) + "-" + (((p.currentPage - 1) * p.pageSize) + l.length) + "条记录，" + "总记录：" + p.totalRow + "条");
                var totalOption = "";
                $("#pageInfo").html(p.currentPage + "/" + p.totalPage);
                $("#page_out").val(p.currentPage);
                $("#current").val(p.currentPage);
                $("#pageSize_out option:last").val(p.totalRow);
            } else {
                $("#current").val("0");
                $("#pageInfo").html("0/0");
                $("#totalPage_out").val(0);
                $("#totalPage").html("当前显示0条记录，总记录：0条");
                $("#page_out").val("0");
            }

            $("#mytable>tbody").eq(0).html(temp);
            //var t = $("#show>tbody").eq(0).find("tr:first").html();
            //console.log($("#show>tbody").eq(0).html());
            //alert(index);
            if (index == 0) {
                $('#mytable').tablesorter({headers: { 0: { sorter: false}}});
                index++;
            } else {
                $('#mytable').trigger("update");
            }
            $.unblockUI();
            return false;
        }


        var index = 0;
        var listOptions = {
            cache: false,
            dataType: 'json',
            type: 'post',
            callback: null,
            url: '/portal/validFileList/listAll.action?random=' + Math.random(),
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
            },
            success: listCallback
        };


        $(function () {
            $(document).on("click", "#checkboxAll", function () {
                //alert($(this).prop("checked"));
                $("tbody").find("input[type=checkbox]").prop("checked", $(this).prop("checked"));
            });

            $(document).on("click", ".clk", function () {
                if ($(".clk:checked").length != $(".clk").length) {
                    $("#checkboxAll").prop("checked", false);
                } else {
                    $("#checkboxAll").prop("checked", true);
                }
            });

            $(document).on("click", ".showForm", function () {
                window.open("/portal/attachOld/loadFileOldList.action?fileGroup=attach&fileGroupName=attachGroup&fileGroupId=" + $(this).attr("attach") + "&userName=&loginName=&procType=view&targetType=frame&type=1");
            })

        })
    </script>

    <style>
        .headerSortDown {
            background: url("js/desc.gif") no-repeat scroll center right transparent;
            cursor: pointer;
        }

        .headerSortUp {
            background: url("js/asc.gif") no-repeat scroll center right transparent;
            cursor: pointer;
        }

    </style>
</head>

<body>
<div id="domMessage" style="display:none;">
    <h1>请稍后</h1>
</div>
<div class="main">
    <!--Ctrl-->
    <div class="ctrl clearfix">
        <div class="fl"><img id="show" onclick="showHide();" src="../css/default/images/sideBar_arrow_right.jpg"
                             width="46" height="30" alt="收起"></div>
        <div class="posi fl">
            <ul>
                <li class="fin">文件列表</li>
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
    <div class="pt45">
        <div class="clearfix"></div>
        <!--Filter-->
        <div class="filter">
            <div class="query">
                <div class="p8 filter_search">
                    <form id="list">
                        <input type="hidden" name="pageSize" id="pageSize" value=""/>

                        <input type="hidden" name="page" id="page" value=""/>
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td class="t_r">文件字号</td>
                                <td>
                                    <input type="text" id="sendcode_like" name="sendcode_like" class="input_xlarge"/>
                                </td>
                                <td class="t_r">文件标题</td>
                                <td>
                                    <input type="text" id="title_like" name="title_like" class="input_xlarge"/>
                                </td>
                                <td class="t_r">文件属性</td>
                                <td>
                                    <select name="normative_equal" id="normative_equal" class="input_large">
                                        <option value="">全部</option>
                                        <option value="非规范性文件">非规范性文件</option>
                                        <option value="规范性文件">规范性文件</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td class="t_r">制定部门</td>
                                <td>
                                    <input type="text" id="addperson_like" name="addperson_like" class="input_xlarge"/>
                                </td>
                                <td class="t_r">发送部门</td>
                                <td>
                                    <input type="text" id="senddept_like" name="senddept_like" class="input_xlarge"/>
                                </td>
                                <td class="t_r">文件状态</td>
                                <td>
                                    <select name="status_in" id="status_in" class="input_large">
                                        <option value="">全部</option>
                                        <option value="有效,部分有效">有效+部分有效</option>
                                        <option value="有效">有效</option>
                                        <option value="部分有效">部分有效</option>
                                        <option value="失效">失效</option>
                                        <option value="废止">废止</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td class="t_r">发文日期</td>
                                <td>
                                    <input readonly="readonly" type="date" id="senddate_startDate"
                                           name="senddate_startDate" value=""/>
                                    至
                                    <input readonly="readonly" type="date" id="senddate_endDate" name="senddate_endDate"
                                           value=""/>

                                </td>
                                <td class="t_r">字号类别</td>
                                <td>
                                    <select name="code2_equal" id="code2_equal" class="input_middle">
                                        <option value="">全部</option>
                                        <option value="2009">2009</option>
                                        <option value="2010">2010</option>
                                        <option value="2011">2011</option>
                                        <option value="2012">2012</option>
                                        <option value="2013">2013</option>
                                        <option value="2014">2014</option>
                                        <option value="2015">2015</option>
                                        <option value="2016">2016</option>
                                    </select>
                                    <select name="code1_equal" id="code1_equal" class="input_middle">
                                        <option value="">全部</option>
                                        <option value="沪地铁">沪地铁</option>
                                        <option value="沪地铁办发">沪地铁办发</option>
                                        <option value="会议">会议</option>
                                    </select>
                                </td>
                                <td><input type="hidden" name="order" id="order" value="senddate desc"></td>
                                <td></td>

                            </tr>

                            <tr>
                                <td colspan="6" class="t_c">
                                    <input id="submit" type="button" value="检 索"/>&nbsp;&nbsp;
                                    <input type="reset" value="重 置"/></td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
            <div class="fn clearfix">
                <h5 class="fl"><a href="#" class="colSelect fl">行政发文文件列表</a></h5>
                <input type="button" name="printButton" id="printButton" value="打印本页" class="fl">
                <input type="text" name="printTitle" id="printTitle" value="" class="fl">

            </div>
        </div>


        <!--Filter End-->
        <!--Table-->
        <div class="mb10" id="listDiv">
            <table width="100%" class="table_1" id="mytable">
                <thead>
                <tr class="tit">
                    <th class="t_c"><input type="checkbox" id="checkboxAll" name="checkboxAll"/></th>
                    <th style="white-space:nowrap;" class="t_c">序号</th>
                    <th style="white-space:nowrap;" class="t_c">文件字号</th>
                    <th style="white-space:nowrap;" class="t_c">发文日期</th>
                    <th style="white-space:nowrap;" class="t_c">文件标题</th>
                    <th style="white-space:nowrap;" class="t_c">制定部门/人</th>
                    <th style="white-space:nowrap;" class="t_c">发送部门</th>
                    <th style="white-space:nowrap;" class="t_c">文件属性</th>
                    <th style="white-space:nowrap;" class="t_c">文件状态</th>
                    <th style="white-space:nowrap;" class="t_c">正式文件</th>
                </tr>

                </thead>
                <tbody>

                </tbody>
                <tr class="tfoot">
                    <td colspan="12">
                        <div class="clearfix">
                            <input type="hidden" id="totalPage_out">
                                <span class="fl">&nbsp;每页显示条数：  
                                   <select id="pageSize_out">
                                       <option value="10">10</option>
                                       <option value="15">15</option>
                                       <option value="20">20</option>
                                       <option value="30">30</option>
                                       <option value="-1">显示全部</option>
                                   </select>
                                </span>
                                <span class="fl">
                                    &nbsp; Pages：<span style="display:inline;" id="pageInfo">0/0</span>&nbsp;
                                    <input id="current" type="hidden"/>
                                   <input id="page_out" type="text" class="input_tiny"/>
                                   <input id="redirect" type="button" value="Go" class="btn"/>
                                 </span>
                            <span id="totalPage" class="fr">当前显示1-10条记录，总记录：100</span>
                                
                                <span class="fr" style="margin-right:10px;">
                           		<ul class="clearfix pager">
                                    <li id="prePage" style="float:left;"><a href="javascript:void(0)">上一页</a></li>
                                    <li id="nextPage" style="float:left;"><a href="javascript:void(0)">下一页</a></li>
                                </ul>
		                        </span>


                        </div>
                    </td>
                </tr>
            </table>

        </div>
        <!--Table End-->
    </div>
</div>
</body>
</html>
