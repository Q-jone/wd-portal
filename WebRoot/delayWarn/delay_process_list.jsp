<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta charset="utf-8"/>
    <title>超时事项流程查询</title>
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
    <script src="../js/jquery-1.7.1.js"></script>
    <script src="../js/jquery.formalize.js"></script>
    <!--<script src="../js/switchDept.js"></script>-->
    <script src="../js/show.js"></script>
    <link type="text/css" href="../css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet"/>
    <script type="text/javascript" src="../js/flick/jquery-ui-1.8.18.custom.min.js"></script>
    <script type="text/javascript" src="../js/flick/jquery.ui.datepicker-zh-CN.js"></script>
    <script src="../processInfo/js/contextPath.js"></script>
    <style type="text/css">
        .ui-datepicker-title span {
            display: inline;
        }

        button.ui-datepicker-current {
            display: none;
        }
    </style>
    <script type="text/javascript">
        $(function () {
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
        });

        function openForm(pname,pincident,cname,cincident){
            var param = {"processname":cname,"incident":cincident};

            if(pname=='多级工作联系单'){
                param.processname = pname;
                param.pincident = pincident;
                param.type = "contract";
            }
            $.post("/portal/delayWarn/monitor.action",param, function (taskid) {
                var url ;
                if(pname=="新收文流程"){
                    url = workflowPath+"/receive-recvMain/forward.action?pincident="+pincident+"&pname="+encodeURI(pname)+"&cname="+encodeURI(cname)+"&cincident="+cincident+"&taskid="+taskid+"&viewType=1";
                }
                else if(pname=="新收呈批件"){
                    url = workflowPath+"/receive-redvMain/forward.action?pincident="+pincident+"&pname="+encodeURI(pname)+"&cname="+encodeURI(cname)+"&cincident="+cincident+"&taskid="+taskid+"&viewType=1";
                }
                else if(pname=="合同后审流程"){
                    url = workflowPath+"/contract-reviewMain/forward.action?pincident="+pincident+"&pname="+encodeURI(pname)+"&cname="+encodeURI(cname)+"&cincident="+cincident+"&taskid="+taskid+"&viewType=1";
                }
                else if(pname=="新发文流程"){
                    //incidentNo=95&modelName=新发文流程&taskId=0619131cb48370acbb60a02a0f9e14
                    url = workflowPath+"/send-tDocSend/toFormPage.action?incidentNo="+pincident+"&modelName="+encodeURI(pname)+"&taskid="+taskid;
                }
                else if(pname=="新党委发文流程"){
                    url = workflowPath+"/send-tDocSend/toFormPage.action?incidentNo="+pincident+"&modelName="+encodeURI(pname)+"&taskid="+taskid;
                }
                else if(pname=="多级工作联系单"){
                    url = workflowPath+"/contact-deptContact/forward.action?id="+taskid;
                }
                else{
                    url = openForm1('',pname,pincident,pname,pincident,taskid);
                }
                window.open(url);
            });
        }

        function openScan(processname,incident) {
            $.post("/portal/delayWarn/monitor.action",{"processname":processname,"incident":incident}, function (data) {

                var url = ultimusPath + "/sLogin/workflow/TaskStatus.aspx?TaskID=" + data;
                window.open(url);
            })

        }


        function openForm1(task_user_name,model_id,instance_id,processName,pinstance_id,task_id){
            var url =stptPath;
            url += '/openflowform.action';
            url +="?task_id="+encodeURI(task_id);
            url +="&task_user_name="+ encodeURI('ST/G00100000161');
            if (model_id == ''){
                url +="&model_id=" + encodeURI(processName);
            }else{
                url +="&model_id=" + encodeURI(model_id);
            }

            if (instance_id == ''){
                url +="&instance_id="+ encodeURI(pinstance_id);
            }else{
                url +="&instance_id="+ encodeURI(instance_id);
            }
            url +="&step_name=aa";
            url +="&pinstance_id=" + encodeURI(pinstance_id);
            url +="&processName=" + encodeURI(processName);
            url +="&task_type=1" ;

            return url;
        }


        //跳转到制定页
        function goPage(pageNo, type) {
            //type=0,直接跳转到制定页
            if (type == "0") {

                var pageCount = $("#totalPageCount").val();
                var number = $("#number").val();
                if (!number.match(/^[0-9]*$/)) {
                    alert("请输入数字");
                    $("#number").val($("#currentNumber").val());
                    $("#number").focus();
                    return;
                }
                if (parseInt(number) > parseInt(pageCount)) {
                    $("#number").val(pageCount);
                    $("#page").val(pageCount);
                } else {
                    $("#page").val(number);
                }
            }
            //type=1,跳转到上一页
            if (type == "1") {
                $("#page").val(parseInt($("#number").val()) - 1);
            }
            //type=2,跳转到下一页
            if (type == "2") {
                //alert($("#number").val());
                $("#page").val(parseInt($("#number").val()) + 1);
                //alert($("#pageNo").val());
            }

            //type=3,跳转到最后一页,或第一页
            if (type == "3") {
                $("#page").val(pageNo);
            }
            $("form").submit();

        }
    </script>


</head>

<body>
<div class="main">
    <!--Ctrl-->
    <div class="ctrl clearfix">
        <div class="fl"><img id="show" onclick="showHide();" src="../css/default/images/sideBar_arrow_right.jpg"
                             width="46" height="30" alt="收起"></div>
        <div class="posi fl">
            <ul>
                <li class="fin">超时事项流程查询</li>
            </ul>
        </div>
        <div style="display:none;" class="fr lit_nav nwarp">
            <%--<ul>--%>
            <%--<li class="selected"><a class="print" href="#">打印</a></li>--%>
            <%--<li><a class="express" href="#">导出数据</a></li>--%>
            <%--<li class="selected"><a class="table" href="#">表格模式</a></li>--%>
            <%--<li><a class="treeOpen" href="#">打开树</a></li>--%>
            <%--<li><a class="filterClose" href="#">关闭过滤</a></li>--%>
            <%--</ul>--%>
        </div>
    </div>
    <!--Ctrl End-->
    <div class="pt45">
        <!--Filter-->
        <div class="filter">
            <div class="query">
                <div class="p8 filter_search">
                    <s:form action="listProcess" id="form" method="post" namespace="/delayWarn">
                        <input type="hidden" name="page" id="page" value="<s:property value='#request.page'/>"/>
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td class="t_r">流程名称</td>
                                <td>
                                    <s:textfield name="delayProcess.processName" cssClass="input_xlarge"/>
                                </td>
                                <td class="t_r">摘要</td>
                                <td>
                                    <s:textfield name="delayProcess.summary" cssClass="input_xlarge"/>
                                </td>
                                <td class="t_r">开始时间</td>
                                <td>
                                    <s:textfield name="delayProcess.beginDate" cssClass="input_large" readonly="true"
                                                 id="beginDate"/>至
                                    <s:textfield name="delayProcess.endDate" cssClass="input_large" readonly="true"
                                                 id="endDate"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="t_r">申请状态</td>
                                <td>
                                    <s:select name="delayProcess.apply"
                                              list="#{'':'全部','已申请':'已申请','未申请':'未申请'}"></s:select>
                                </td>
                                <td class="t_r">状态</td>
                                <td>
                                    <s:select name="delayProcess.status" list="#{'进行中':'进行中','已完成':'已完成'}"></s:select>
                                </td>
                                <td class="t_r">催办次数</td>
                                <td>
                                    <s:textfield name="delayProcess.minWarn" cssClass="input_large"/>至
                                    <s:textfield name="delayProcess.maxWarn" cssClass="input_large"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="t_r">超时时长</td>
                                <td>
                                    <s:select name="delayProcess.delayType"
                                              list="#{'':'全部','1':'3天以下','2':'3-10天','3':'10-30天','4':'30-100天','5':'100天以上'}"></s:select>

                                </td>
                                <s:if test="showDept == \"0\"">
                                    <td class="t_r">所属部门</td>
                                    <td>
                                        <s:textfield name="delayProcess.department" cssClass="input_large"/>
                                    </td>
                                    <td class="t_r"></td>
                                    <td>
                                    </td>
                                </s:if>
                            </tr>
                            <tr>
                                <td colspan="6" class="t_c">
                                    <input type="submit" value="检 索"/></td>
                            </tr>
                        </table>
                    </s:form>
                </div>
            </div>
        </div>

        <!--Filter End-->
        <!--Table-->
        <div class="mb10">
            <table width="100%" class="table_1" id="mytable">
                <thead>
                <tr class="tit">
                    <td class="t_c" width="4%">序号</td>
                    <td class="t_c" width="10%">流程名称</td>
                    <td class="t_c" width="15%">所属部门</td>
                    <td class="t_c" width="30%">摘要</td>
                    <td class="t_c" width="8%">开始时间</td>
                    <td class="t_c" width="8%">结束时间</td>
                    <td class="t_c" width="5%">状态</td>
                    <td class="t_c" width="3%">超时</td>
                    <td class="t_c" width="3%">催办</td>
                    <td class="t_c" width="3%">表单</td>
                    <td class="t_c" width="3%">监控</td>
                    <td class="t_c" width="8%">申请</td>
                </tr>
                </thead>
                <tbody>
                <s:iterator value="resultSet.list" status="s">
                    <tr id="dataTr">
                        <td class="t_c"><s:property value="#s.index+1"/></td>
                        <td class="t_c"><s:property value="processName"/></td>
                        <td class="t_c"><s:property value="department"/></td>
                        <td class="t_c"><s:property value="summary"/></td>
                        <td class="t_c"><s:property value="beginDate"/></td>
                        <td class="t_c"><s:property value="endDate"/></td>
                        <td class="t_c"><s:property value="status"/></td>
                        <td class="t_c"><s:property value="delay"/></td>
                        <td class="t_c"><s:property value="warn"/></td>
                        <td class="t_c">
                            <center><a href="javascript:void(0);" onclick="openForm('<s:property value="processName"/>','<s:property value="incident"/>','<s:property value="cname"/>','<s:property value="cincident"/>');">
                                <img src="/portal/css/default/images/p_open.gif"/>
                            </a></center>
                        </td>
                        <td class="t_c">
                            <center><a href="javascript:void(0);"
                                       onclick="openScan('<s:property value="cname"/>','<s:property value="cincident"/>');"><img
                                    src="/portal/css/default/images/p_but9.gif"/></a></center>
                        </td>
                        <td class="t_c"><s:property value="apply"/></td>
                    </tr>
                </s:iterator>
                </tbody>
                <tr class="tfoot">
                    <td colspan="15">
                        <div class="clearfix"><span class="fl"><s:property
                                value="resultSet.pageInfo.totalRow"/>条记录</span>
                            <ul class="fr clearfix pager">
                                <li>Pages:<s:property value="resultSet.pageInfo.currentPage"/>/<s:property
                                        value="resultSet.pageInfo.totalPage"/>
                                    <input type="hidden" value="<s:property value='resultSet.pageInfo.totalPage'/>"
                                           id="totalPageCount">
                                    <input type="hidden" value="<s:property value='resultSet.pageInfo.currentPage'/>"
                                           id="currentNumber">
                                    <input type="text" id="number" name="pageNumber" min="0" max="999" step="1"
                                           class="input_tiny"
                                           value="<s:property value='resultSet.pageInfo.currentPage'/>"/>
                                    <input type="button" name="button" id="button" value="Go" onclick="goPage(0,0)">
                                </li>

                                <s:if test="resultSet.pageInfo.currentPage==resultSet.pageInfo.totalPage">
                                    <li><a href="javascript:void(0)">&gt;&gt;</a></li>
                                </s:if>
                                <s:else>
                                    <li><a href="javascript:void(0)"
                                           onclick="goPage(<s:property value='resultSet.pageInfo.totalPage'/>,3)">&gt;&gt;</a>
                                    </li>
                                </s:else>

                                <li>
                                    <s:if test="resultSet.pageInfo.currentPage==resultSet.pageInfo.totalPage">
                                        <a href="javascript:void(0)">下一页</a>
                                    </s:if>
                                    <s:else>
                                        <a href="javascript:void(0)" onclick="goPage(<s:property
                                                value='resultSet.pageInfo.currentPage'/>,2)">下一页</a>
                                    </s:else>
                                </li>
                                <li>
                                    <s:if test="resultSet.pageInfo.currentPage==1">
                                        <a href="javascript:void(0)">上一页</a>
                                    </s:if>
                                    <s:else>
                                        <a href="javascript:void(0)" onclick="goPage(<s:property
                                                value='resultSet.pageInfo.currentPage'/>,1)">上一页</a>
                                    </s:else>

                                </li>

                                <s:if test="resultSet.pageInfo.currentPage==1">
                                    <li><a href="javascript:void(0)">&lt;&lt;</a></li>
                                </s:if>
                                <s:else>
                                    <li><a href="javascript:void(0)" onclick="goPage(1,3)">&lt;&lt;</a></li>
                                </s:else>


                            </ul>
                        </div>
                    </td>
                </tr>
            </table>

        </div>


    </div>
</div>
</body>
</html>