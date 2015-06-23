<%@ page import="com.wonders.stpt.todoItem.util.ConfigUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String contextPath = request.getContextPath();
    String workflowUrl = ConfigUtil.getValueByKey("config.properties", "wokflowUrl")+"/workflow";
    String oldOaUrl = ConfigUtil.getValueByKey("config.properties", "stptPath");
    String caUrl = ConfigUtil.getValueByKey("config.properties", "urlCa");
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>
    <c:choose>
        <c:when test="${requestScope.processStatus == 1}">
            未跟踪办结事项
        </c:when>
        <c:when test="${requestScope.processStatus == 0}">
            未跟踪进行事项
        </c:when>
    </c:choose>
</title>
<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script
	src="<%=contextPath%>/center/wdsw/newVersion/js/jquery-1.10.2.min.js"></script>
<script
	src="<%=contextPath%>/center/wdsw/newVersion/js/jquery-migrate-1.2.1.min.js"></script>
<script
	src="<%=contextPath%>/center/wdsw/newVersion/js/bootstrap.min.js"></script>
<script
        src="<%=contextPath%>/center/wdsw/newVersion/js/bootstrap-paginator.js"></script>
<script src="<%=contextPath%>/center/wdsw/newVersion/js/wdsw.js"></script>
<!-- Bootstrap -->
<link
	href="<%=contextPath%>/center/wdsw/newVersion/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="<%=contextPath%>/center/wdsw/newVersion/css/bootstrap-theme.min.css"
	rel="stylesheet">
<link href="<%=contextPath%>/center/wdsw/newVersion/css/custom.css"
	rel="stylesheet">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="js/html5shiv.min.js"></script>
  <script src="js/respond.min.js"></script>
  <![endif]-->
<style>
</style>
<script>
	$(function() {
        oldOaUrl = "<%=oldOaUrl%>";
        workflowUrl = "<%=workflowUrl%>";
        caUrl = "<%=caUrl%>";
		$('#myTab li:eq(0) a').tab('show');
        //分页功能
        /*var options = {
            bootstrapMajorVersion:3,
            currentPage: 1,
            numberOfPages: 5,
            totalPages:11
        }
        $('#page1').bootstrapPaginator(options);*/
	})
</script>
</head>
<body>
<div class="container-fluid">
		<!-- <ol class="breadcrumb">
			<li><a href="#" class="glyphicon glyphicon-chevron-right"></a></li>
			<li><a href="#"> <strong>我的事务</strong>
			</a></li>
			<li class="active">首页</li>
		</ol> -->

        <div class="main mw1002">
            <div class="ctrl clearfix">
                <div class="fl"><img id="show" onclick="showHide();" src="<%=contextPath%>/css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="展开"></div>
                <div class="posi fl">
                    <ul>
                        <li><a href="javascript:void(0);">我的事务</a></li>
                        <li class="fin">
                            <c:choose>
                                <c:when test="${requestScope.processStatus == 1}">
                                    未跟踪办结事项
                                </c:when>
                                <c:when test="${requestScope.processStatus == 0}">
                                    未跟踪进行事项
                                </c:when>
                            </c:choose>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="clearfix pt45 fl w75p">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title todo">
                            <c:choose>
                                <c:when test="${requestScope.processStatus == 1}">
                                    未跟踪办结事项
                                </c:when>
                                <c:when test="${requestScope.processStatus == 0}">
                                    未跟踪进行事项
                                </c:when>
                            </c:choose>
                        <!--${fn:length(result)}
                            <a class="fr mr defaultSet" href="javascript:void(0);">
                                <strong class="text-warning">设置</strong>
                            </a>-->
                        </h3>
					</div>
					<div class="clearfix">
						<ul id="myTab" class="nav nav-tabs">
							<c:choose>
								<c:when test="${type!=null && fn:length(type) > 0}">
									<c:forEach items="${type}" var="items" varStatus="s">
										<li><a href="#t${s.index+1}" data-toggle="tab"> <strong>${items}</strong><span title="跟踪" class="notShow rv glyphicon glyphicon-remove"></span>
										</a></li>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<li><a href="javascript:void(0);" data-toggle="tab"> <strong>无相关信息。</strong></a></li>
								</c:otherwise>
							</c:choose>
						</ul>
						<div class="tab-content" id="tTabContent">
							<c:choose>
								<c:when test="${result!=null && fn:length(result) > 0}">
									<c:forEach items="${result}" var="items" varStatus="s">
										<div class="clearfix tab-pane fade in" id="t${s.index+1}">
											<table class="table table-hover">
												<c:forEach items="${items.value}" var="roww"
													varStatus="rows">
													<tr class="hand" taskid="${roww.taskid}"
														pname="${roww.pname}" pincident="${roww.pincident}">
														<td style="width: 5%;">${rows.index+1 }</td>
														<td style="width: 15%;">[${roww.pname}]</td>
														<td style="width: 45%;">${roww.summary}</td>
														<td style="width: 5%;">
															<button mainId="${roww.id}" type="button"
																class="trackButton btn btn-xs btn-success">跟踪</button>
														</td>
														<td style="width: 15%;">
                                                            <c:choose>
                                                                <c:when test="${requestScope.processStatus == 1}">
                                                                    ${roww.donetime}
                                                                </c:when>
                                                                <c:when test="${requestScope.processStatus == 0}">
                                                                    ${roww.operatetime}
                                                                </c:when>
                                                            </c:choose>
                                                        </td>
														<td style="width: 15%;">${roww.stepname}</td>
													</tr>
												</c:forEach>
											</table>
										</div>
									</c:forEach>
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>

							<span class="pull-right">&nbsp;</span>
						</div>
                       <nav class="pull-right pagination">
                            <ul id="page1"></ul>
                       </nav>
					</div>

				</div>
			</div>
		</div>
		<!-- row 2-->
	</div>
	<!--container-->
</body>
</html>