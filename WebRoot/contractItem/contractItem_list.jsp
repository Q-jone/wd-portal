<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta charset="utf-8" />
    <title>合同变更事项信息列表</title>
    <link rel="stylesheet" href="<%=basePath %>css/formalize.css" />
    <link rel="stylesheet" href="<%=basePath %>css/page.css" />
    <link rel="stylesheet" href="<%=basePath %>css/default/imgs.css" />
    <link rel="stylesheet" href="<%=basePath %>css/reset.css" />
    <!--[if IE 6.0]>
    <script src="../js/iepng.js" type="text/javascript"></script>
    <script type="text/javascript">
        EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
    </script>
    <![endif]-->
    <script src="<%=basePath %>js/html5.js"></script>
    <script src="<%=basePath %>js/jquery-1.7.1.js"></script>
    <script src="<%=basePath %>js/jquery.formalize.js"></script>
    <!--<script src="../js/switchDept.js"></script>-->
    <script src="<%=basePath %>js/show.js"></script>
    <link type="text/css" href="<%=basePath %>css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
    <script type="text/javascript" src="<%=basePath %>js/flick/jquery-ui-1.8.18.custom.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>js/flick/jquery.ui.datepicker-zh-CN.js"></script>
    <script src="<%=basePath %>processInfo/js/contextPath.js"></script>
    <style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
    </style>
    <script type="text/javascript">
        function goPage(pageNo,type){
            if(type=="0"){
                var totalPage = $("#totalPageCount").val();//总页数
                var pageNumber = $("#pageNumber").val();//当前页码
                if(!pageNumber.match(/^[0-9]*$/)){//输入不是数字时提示
                    alert("请输入数字");
                    $("#pageNumber").val("");
                    $("#pageNumber").focus();
                    return ;
                }
                if(parseInt(pageNumber)>parseInt(totalPage)){
                    $("#pageNumber").val(totalPage);
                    $("#page").val(pageCount);
                }else{
                    $("#page").val(pageNumber);
                }
            }
            if(type=="1"){
                $("#page").val(parseInt($("#page").val())-1);
            }
            if(type=="2"){
                $("#page").val(parseInt($("#page").val())+1);
            }
            if(type=="3"){
                $("#page").val(pageNo);
            }
            $("#form").submit();
        }


        $(function(){
            $("input[name*=Time]").datepicker({
                "changeYear":true
            });
            $("[id=status_show]").each(function(){
                $(this).html($(this).html().replace("0","过程中").replace("1","预归档").replace("3","已取消"));
            });
            $("#flag").val($("#flag_no").val());

            $("[id=changePutForword_show]").each(function(){
                $(this).html($(this).html().replace("1","合同甲方单位").replace("2","合同乙方单位"));
            });
        });

        function clearData(){
            $("#contractName").val("");
            $("#changeItemName").val("");
            $("#changeSerialNo").val("");
            $("#regTimeStart").val("");
            $("#regTimeEnd").val("");
            $("#regPerson").val("");
            $("#flag").val("");
            $("flag_no").val("");
        }

        function searchData(){
            $("#page").val("1");
        }

        function toDetail(instant_id,process_name,taskid){
            //http://10.1.44.18/contractInfoDetail.action?id=25007&modelName=%E5%90%88%E5%90%8C%E5%AE%A1%E6%89%B9%E6%B5%81%E7%A8%8B&incidentNo=2454&stepName=Begin&taskId=060909743aed2a969758e1a79785db&taskUserName=ST/G00200000059
            //var url = 'http://10.1.44.18/stoa/publicConn.jsp?urlPath=/htsp/detail.do?b_query=true&type=2&pinstanceid='+encodeURI(instant_id)+'&modelId='+encodeURI(process_name);
            var url = "http://10.1.48.16:8080/workflow/contractsxChange-reviewMain/forward.action?"
                    +"pname="+encodeURI(process_name)+"&pincident="+instant_id
                    +"&cname="+encodeURI(process_name)+"&cincident="+instant_id
                    +"&taskid="+taskid+"&viewType=1";;
            //window.location.href=url;
            window.open(url);
            return false;
        }

        function toSee(taskid){
            var url = "http://10.1.48.17/sLogin/workflow/TaskStatus.aspx?TaskID="+taskid;
            //window.location.href=url;
            window.open(url);
            return false;
        }
    </script>
</head>

<body>

<div class="main">
    <!--Ctrl-->
    <div class="ctrl clearfix">
        <div class="fl"><img id="show" onclick="showHide();" src="<%=basePath %>css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
        <div class="posi fl">
            <ul>
                <li class="fin">合同变更事项信息列表</li>
            </ul>
        </div>

    </div>
    <!--Ctrl End-->
    <div class="pt45">
        <!--Filter-->
        <div class="filter">
            <div class="query">
                <div class="p8 filter_search">
                    <s:form action="list" id="form" method="post"  namespace="/contractItem">

                        <input type="hidden" id="pageSize" name="pageSize" value="<s:property value="pageResultSet.pageInfo.pageSize"/>" />
                        <input type="hidden" id="page" name="page" value="<s:property value="pageResultSet.pageInfo.currentPage"/>" />

                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td class="t_r">变更的原合同名称</td>
                                <td>
                                    <input type="text" id="contractName" name="contractItem.contractName" class="input_xlarge" value="<s:property value='contractItem.contractName'/>"/>
                                </td>
                                <td class="t_r">变更事项名称</td>
                                <td>
                                    <input type="text" id="changeItemName" name="contractItem.changeItemName" class="input_xlarge" value="<s:property value='contractItem.changeItemName'/>"/>
                                </td>
                                <td class="t_r">变更流水号</td>
                                <td>
                                    <input type="text" id="changeSerialNo" name="contractItem.changeSerialNo" class="input_xlarge" value="<s:property value='contractItem.changeSerialNo'/>"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="t_r">登记日期</td>
                                <td style="white-space: nowrap">
                                    <input type="text" id="regTimeStart" name="contractItem.regTime" style="width: 118px;" value="<s:property value='contractItem.regTime'/>">
                                    -
                                    <input type="text" id="regTimeEnd" name="contractItem.regTimeEnd" style="width: 118px;"  value="<s:property value='contractItem.regTimeEnd'/>">
                                </td>
                                <td class="t_r">登记人</td>
                                <td>
                                    <input type="text" id="regPerson" name="contractItem.regPerson" class="input_xlarge" value="<s:property value='contractItem.regPerson'/>"/>
                                </td>
                                <td class="t_r">状态</td>
                                <td>
                                    <input type="hidden" id="flag_no" value="<s:property value='contractItem.flag'/>">
                                    <select style="width:118px" id="flag" name="contractItem.flag" class="input_xlarge"/>">
                                        <option value="">---请选择---</option>
                                        <option value="0">过程中</option>
                                        <option value="1">预归档</option>
                                        <option value="3">已取消</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="6" class="t_c">
                                    <input type="submit" value="检 索" style="width:50px;" onclick="searchData();">
                                    <input type="button" value="清 除" style="width:50px;" onclick="clearData();">
                                </td>
                            </tr>
                        </table>
                    </s:form>
                </div>
            </div>
        </div>
        <div style="background-color: ;">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<h5 class="fl"><a href="#" class="colSelect fl">合同变更事项息列表</a></h5>
        </div>
    </div>
    <!--Filter End-->
    <!--Table-->
    <!--  <s:set name="r" value="#request.pageResultSet.list"></s:set> -->
    <div class="mb10">
        <table width="100%"  class="table_1" id="mytable">
            <thead>
            <tr class="tit">
                <td class="t_c" width="3%">序号</td>
                <td class="t_c" width="8%">变更事项名称</td>
                <td class="t_c" width="12%">变更的原合同编号</td>
                <td class="t_c" width="16%">变更流水号</td>
                <td class="t_c" width="5%">变更提出方</td>
                <td class="t_c" width="7%">拟变金额（万元）</td>
                <td class="t_c" width="22%">变更的原合同名称</td>
                <td class="t_c" width="6%">登记时间</td>
                <td class="t_c" width="4%">登记人</td>
                <td class="t_c" width="5%">状态</td>
                <td class="t_c" width="4%">表单</td>
                <td class="t_c" width="4%">监控</td>
                <%--<td class="t_c" width="4%">终止</td>--%>
            </tr>
            </thead>
            <tbody>
            <s:set name="r" value="pageResultSet.list"></s:set>
            <s:iterator value="r" id="items" status="s">
                <tr id="dataTr">
                    <td class="t_c"><s:property value="#s.index+(pageResultSet.pageInfo.currentPage-1)*pageResultSet.pageInfo.pageSize+1"/></td>
                    <td class="t_c"><s:property value="changeItemName" /></td>
                    <td class="t_l"><s:property value="changeContractNo"/></td>
                    <td class="t_l"><s:property value="changeSerialNo" /></td>
                    <td class="t_c" id="changePutForword_show"><s:property value="changePutForword" /></td>
                    <td class="t_r"><s:property value="changePrice" /></td>
                    <td class="t_l"><s:property value="contractName" /></td>
                    <td class="t_c"><s:property value="regTime.substring(0,10)" /></td>
                    <td class="t_c"><s:property value="regPerson" /></td>
                    <td class="t_c" id="status_show"><s:property value="flag" /></td>
                    <td>
                        <a href="javascript:void(0)" onclick="toDetail('<s:property value="incidect" />','<s:property value="process" />','<s:property value="taskId" />');"><img src="../css/default/images/p_open.gif"/></a>
                    </td>
                    <td>
                        <a href="javascript:void(0)" onclick="toSee('<s:property value="taskId" />');"><img src="../css/default/images/p_but9.gif"/></a>
                    </td>

                </tr>
            </s:iterator>
            </tbody>
            <s:if test="pageResultSet.pageInfo.totalRow!=0">
                <tr class="tfoot">
                    <td colspan="30"><div class="clearfix"><span class="fl">共<s:property value="pageResultSet.pageInfo.totalRow"/>条记录，当前显示<s:property value="pageResultSet.pageInfo.startRow"/>-
        	      <s:if test="pageResultSet.pageInfo.totalRow<(pageResultSet.pageInfo.startRow+pageResultSet.pageInfo.pageSize-1)">
                      <s:property value="pageResultSet.pageInfo.totalRow"/>
                  </s:if>
        	      <s:else>
                      <s:property value="pageResultSet.pageInfo.startRow+pageResultSet.pageInfo.pageSize-1"/>
                  </s:else>
        	      条</span>

                        <ul class="fr clearfix pager">
                            <li>Pages:<s:property value="pageResultSet.pageInfo.currentPage"/>/<s:property value="pageResultSet.pageInfo.totalPage"/>
                                <input type="hidden" value="<s:property value='pageResultSet.pageInfo.totalPage'/>" id="totalPageCount">
                                <input type="text" id="pageNumber" name="pageNumber" min="0" max="999" step="1" class="input_tiny" value="<s:property value='pageResultSet.pageInfo.currentPage'/>"/>
                                <input type="button" name="button" id="button" value="Go" onclick="goPage(0,0)">
                            </li>

                            <s:if test="pageResultSet.pageInfo.currentPage==pageResultSet.pageInfo.totalPage">
                                <li>&gt;&gt;</li>
                            </s:if>
                            <s:else>
                                <li><a href="javascript:void(0)" onclick="goPage(<s:property value='pageResultSet.pageInfo.totalPage'/>,3)">&gt;&gt;</a></li>
                            </s:else>
                            <li>
                                <s:if test="pageResultSet.pageInfo.currentPage==pageResultSet.pageInfo.totalPage">
                                    下一页
                                </s:if>
                                <s:else>
                                    <a href="javascript:void(0)" onclick="goPage(<s:property value='pageResultSet.pageInfo.currentPage'/>,2)">下一页</a>
                                </s:else>
                            </li>
                            <li>
                                <s:if test="pageResultSet.pageInfo.currentPage==1">
                                    上一页
                                </s:if>
                                <s:else>
                                    <a href="javascript:void(0)" onclick="goPage(<s:property value='pageResultSet.pageInfo.currentPage'/>,1)">上一页</a>
                                </s:else>
                            </li>
                            <s:if test="pageResultSet.pageInfo.currentPage==1">
                                <li>&lt;&lt;</li>
                            </s:if>
                            <s:else>
                                <li><a href="javascript:void(0)" onclick="goPage(1,3)">&lt;&lt;</a></li>
                            </s:else>
                        </ul>
                    </div></td>
                </tr></s:if><s:else>
            <tr class="tfoot"><td colspan="30"><div class="clearfix">无相关数据</div></td>
            </tr>
        </s:else>
        </table>

    </div>


</div>
</div>
</body>
</html>