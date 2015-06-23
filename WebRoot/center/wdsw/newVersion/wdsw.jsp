<%@ page import="com.wonders.stpt.todoItem.util.ConfigUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>我的事务</title>
<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="js/jquery-1.10.2.min.js"></script>
<script src="js/jquery-migrate-1.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/wdsw.js"></script>
<script src="<%=contextPath%>/js/show.js"></script>
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-theme.min.css" rel="stylesheet">
<link href="css/custom.css" rel="stylesheet">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="js/html5shiv.min.js"></script>
  <script src="js/respond.min.js"></script>
  <![endif]-->
<script>
  $(function(){
	  getStfbNews("40");
	  getWeather("<%=contextPath%>/center/wdsw/tqyb.jsp");
	  isLog("${sessionScope.trackStatus}");
      oldOaUrl = "<%=oldOaUrl%>";
      workflowUrl = "<%=workflowUrl%>";
      caUrl = "<%=caUrl%>";
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
                            <li class="fin">首页</li>
                        </ul>
                    </div>
                </div>
                <div class="clearfix pt45">
                    <div class="fl w75p">
                        <div class="clearfix mg10 w95p">
                            <!--待办事项-->
                            <div class="panel panel-info">
                                <div class="panel-heading">
                                    <h3 class="panel-title todo" id="todoH3">个人待办事项：当前个人待办事项0项，超期事项0项，请关注。
                                    </h3>

                                </div>
                                <div class="clearfix">
                                    <div class="todoUl clearfix">
                                        <table class="table table-hover" id="todoTable">
                                        <tr><td>读取中，请稍候</td></tr>
                                        </table>
                                    </div>
                                    <span class="pull-right" id="todoSpan"> </span>
                                </div>
                            </div>
                            <!--待办事项-->
                            <!--后续跟踪事项-->
                            <div class="panel panel-info" id="unDoneDiv">
                                <div class="panel-heading">
                                    <h3 class="panel-title todo" id="unDoneH3">
                                        已办事项跟踪：2天后仍在进行中的已办事项0项，其中未跟踪事项0项，详情请 <a href="javascript:void(0);">
                                        <strong class="text-warning">点击</strong>
                                    </a>
                                    </h3>

                                </div>
                                <div class="clearfix">
                                    <ul id="unDoneTab" class="nav nav-tabs">
                                    </ul>
                                    <div id="unDoneTabContent" class="tab-content">
                                        <div class="todoUl clearfix">
                                            <table class="table table-hover">
                                                <tr><td>读取中，请稍候</td></tr>
                                            </table>
                                        </div>
                                    </div>
                                    <span class="pull-right" id="unDoneSpan"></span>

                                </div>
                            </div>
                            <!--后续跟踪事项-->
                            <!--近期办结事项-->
                            <div class="panel panel-info" id="doneDiv">
                                <div class="panel-heading">
                                    <h3 class="panel-title todo" id="doneH3">
                                        个人办结事项：一周内已完成的事项0项，其中未跟踪事项0项，详情请 <a href="javascript:void(0);">
                                        <strong class="text-warning">点击</strong>
                                    </a>
                                    </h3>

                                </div>
                                <div class="clearfix">
                                    <ul id="doneTab" class="nav nav-tabs">
                                    </ul>
                                    <div id="doneTabContent" class="tab-content">
                                        <div class="todoUl clearfix">
                                            <table class="table table-hover">
                                                <tr><td>读取中，请稍候</td></tr>
                                            </table>
                                        </div>
                                    </div>
                                    <span class="pull-right" id="doneSpan"></span>
                                </div>
                            </div>
                            <!--近期办结事项-->
                            <!--通知通告-->
                            <div class="panel panel-info">
                                <div class="panel-heading">
                                    <h3 class="panel-title notice">通知通告</h3>
                                </div>
                                <div class="clearfix">
                                    <ul id="noticeUl" class="list-group clearfix noticeUl">
                                    </ul>
                                    <span class="pull-right" id="noticeSpan"> </span>
                                </div>

                            </div>
                        </div>
                        <!--通知通告-->
                    </div>
                    <div class="fr w25p">
                        <div class="clearfix mg10">
                            <!--天气预报-->
                            <div class="panel panel-info">
                                <div class="panel-heading">
                                    <h3 class="panel-title weather">天气预报</h3>
                                </div>
                                <div class="weatherUl panel-body text-center">
                                   <iframe width="230" id="weatherFrame" border="0" frameborder="0"
                                        framespacing="0" marginheight="0" marginwidth="0" scrolling="no"
                                        allowtransparency="yes"></iframe>
                                </div>
                            </div>
                            <!--天气预报-->
                            <!--日程安排-->
                            <div class="panel panel-info">
                                <div class="panel-heading">
                                    <h3 class="panel-title plan">日程安排</h3>
                                </div>
                                <div class="clearfix">
                                    <div class="otherUl clearfix">
                                        <table class="table table-hover" id="dateTable">
                                            <tr><td>读取中，请稍候</td></tr>
                                        </table>
                                    </div>
                                    <span class="pull-right" id="dateSpan"> </span>
                                </div>
                            </div>
                            <!--日程安排-->
                            <!--会议安排-->
                            <div class="panel panel-info">
                                <div class="panel-heading">
                                    <h3 class="panel-title meet">会议安排</h3>
                                </div>
                                <div class="clearfix">
                                    <div class="otherUl clearfix">
                                        <table class="table table-hover" id="meetTable">
                                            <tr><td>读取中，请稍候</td></tr>
                                        </table>
                                    </div>
                                    <span class="pull-right" id="meetSpan"> </span>
                                </div>
                            </div>
                            <!--会议安排-->
                        </div>
                    </div>
                </div>
            </div>
            <!-- ROW 1-->




        </div>
	<!--container-->
</body>
</html>