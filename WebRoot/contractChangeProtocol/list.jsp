<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
  <meta charset="utf-8" />
  <title>合同变更协议</title>
  <link rel="stylesheet" href="../css/formalize.css" />
  <link rel="stylesheet" href="../css/page.css" />
  <link rel="stylesheet" href="../css/default/imgs.css" />
  <link rel="stylesheet" href="../css/reset.css" />
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
  <link type="text/css" href="../css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
  <script type="text/javascript" src="../js/flick/jquery-ui-1.8.18.custom.min.js"></script>
  <script type="text/javascript" src="../js/flick/jquery.ui.datepicker-zh-CN.js"></script>
  <style type="text/css">
    .ui-datepicker-title span {display:inline;}
    button.ui-datepicker-current { display: none; }
  </style>
  <script type="text/javascript">



    $(function(){
      $(".status_show").each(function(){
        $(this).html($(this).html().replace("0","过程中").replace("1","预归档").replace("3","已取消"));
      });
      $('#reg_time_start').datepicker({
        //inline: true
        "changeYear":true,
        "showButtonPanel":true,
        "closeText":'清除',
        "currentText":'reg_time_start'//仅作为“清除”按钮的判断条件
      });
      $('#reg_time_end').datepicker({
        //inline: true
        "changeYear":true,
        "showButtonPanel":true,
        "closeText":'清除',
        "currentText":'reg_time_end'//仅作为“清除”按钮的判断条件
      });

      $('#date_start').datepicker({
        //inline: true
        "changeYear":true,
        "showButtonPanel":true,
        "closeText":'清除',
        "currentText":'date_start'//仅作为“清除”按钮的判断条件
      });

      //$("#indicatorDate").datepicker('option', $.datepicker.regional['zh-CN']);
      $('#date_end').datepicker({
        //inline: true
        "changeYear":true,
        "showButtonPanel":true,
        "closeText":'清除',
        "currentText":'date_end'//仅作为“清除”按钮的判断条件
      });
      $('#date_start1').datepicker({
        //inline: true
        "changeYear":true,
        "showButtonPanel":true,
        "closeText":'清除',
        "currentText":'date_start1'//仅作为“清除”按钮的判断条件
      });

      //$("#indicatorDate").datepicker('option', $.datepicker.regional['zh-CN']);
      $('#date_end1').datepicker({
        //inline: true
        "changeYear":true,
        "showButtonPanel":true,
        "closeText":'清除',
        "currentText":'date_end1'//仅作为“清除”按钮的判断条件
      });



      //datepicker的“清除”功能
      $(".ui-datepicker-close").live("click", function (){
        if($(this).parent("div").children("button:eq(0)").text()=="date_start") $("#date_start").val("");
        if($(this).parent("div").children("button:eq(0)").text()=="date_end") $("#date_end").val("");
        if($(this).parent("div").children("button:eq(0)").text()=="date_start1") $("#date_start1").val("");
        if($(this).parent("div").children("button:eq(0)").text()=="date_end1") $("#date_end1").val("");
        if($(this).parent("div").children("button:eq(0)").text()=="reg_time_start")
        {$("#reg_time_start").val("");}
        if($(this).parent("div").children("button:eq(0)").text()=="reg_time_end") $("#reg_time_end").val("");

      });

      $(".t_c a").css("display","inline");
      var $tbInfo = $(".filter .query input:text");
      $tbInfo.each(function () {
        $(this).focus(function () {
          $(this).attr("placeholder", "");
        });
      });

      var $tblAlterRow = $(".table_1 tbody tr:even");
      if ($tblAlterRow.length > 0)
        $tblAlterRow.css("background","#fafafa");
      var h_flag = $("#h_flag").val();
      $("#flag").val(h_flag);
    });
    function goPage(pageNo,type){
      //type=0,直接跳转到制定页
      if(type=="0"){

        var pageCount = $("#totalPageCount").val();
        var number = $("#number").val();
        if(!number.match(/^[0-9]*$/)){
          alert("请输入数字");
          $("#number").val($("#currentNumber").val());
          $("#number").focus();
          return ;
        }
        if(parseInt(number)>parseInt(pageCount)){
          $("#number").val(pageCount);
          $("#page").val(pageCount);
        }else{
          $("#page").val(number);
        }
      }
      //type=1,跳转到上一页
      if(type=="1"){
        $("#page").val(parseInt($("#number").val())-1);
      }
      //type=2,跳转到下一页
      if(type=="2"){
        //alert($("#number").val());
        $("#page").val(parseInt($("#number").val())+1);
        //alert($("#pageNo").val());
      }

      //type=3,跳转到最后一页,或第一页
      if(type=="3"){
        $("#page").val(pageNo);
      }
      $("#form").submit();

    }
    function setpage1(){
      $("#page").val("1");
    }
    function clearInput(){
      $("#contract_name").val("");
      $("#self_no").val("");
      $("#change_price_start").val("");
      $("#change_price_end").val("");
      $("#reg_person").val("");
      $("#reg_time_start").val("");
      $("#reg_time_end").val("");
      $("#flag").children("option:eq(0)").attr("selected",true);
      $("#date_start").val("");
      $("#date_end").val("");
      $("#date_start1").val("");
      $("#date_end1").val("");
    }

    function toForm( process,instant,taskid){
      var url =             "http://10.1.48.16:8080/workflow/contracthsChange-reviewMain/forward.action?" +
                // "http://10.1.48.16:8080/workflow/contractChange-contractMain/forward.action?"+
              "pname="+encodeURI(process)+"&pincident="+encodeURI(instant)+
              "&cname="+encodeURI(process)+"&cincident="+encodeURI(instant)+"&taskid="+encodeURI(taskid);
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
<%-- 操作页面--%>
<div class="main">
  <!--Ctrl-->
  <div class="ctrl clearfix">
    <div class="fl"><img id="show" onclick="showHide();" src="../css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
    <div class="posi fl">
      <ul>
        <li><a href="javascript:window.location.href='/portal/center/wdsw/wd_index.jsp'">我的事务</a></li>
        <li class="fin">合同变更协议</li>
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
    <%--<div class="tabs_2">
      <ul>
        <li class="selected"><a href="/portal/contractManage/list.action"><span>合同流程查询</span></a></li>
        <li><a href="/portal/contractReview/list.action"><span>后审流程查询</span></a></li>
      </ul>
    </div>--%>
    <!--Filter-->
    <div class="filter">
      <div class="query">
        <div class="p8 filter_search">
          <s:form action="list" id="form" method="post"  namespace="/contractProcotol">

            <input type="hidden" name="page" id="page" value="<s:property value="page"/>"/>

            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td class="t_r">合同名称</td>
                <td>
                  <input type="text" id="contract_name" name="contract_name" class="input_xlarge" value="<s:property value='#request.contract_name'/>"/>
                </td>
                <td class="t_r">变更协议自有编号</td>
                <td>
                  <input type="text" id="self_no" name="self_no" class="input_xlarge"  value="<s:property value='#request.self_no'/>"/>
                </td>
                <td class="t_r">拟变金额</td>
                <td>
                  <input type="text" id="change_price_start" name="change_price_start" style="width:117px"  value="<s:property value='#request.change_price_start'/>"/>
                 至 <input type="text" id="change_price_end" name="change_price_end" style="width:117px"  value="<s:property value='#request.change_price_end'/>"/>

                </td>
              </tr>

              <tr>
                <td class="t_r">登记人</td>
                <td>
                  <input type="text" id="reg_person" name="reg_person" class="input_xlarge"  value="<s:property value='#request.reg_person'/>"/>
                </td>
                <td class="t_r">登记日期</td>
                <td>
                  <input type="text" id="reg_time_start" name="reg_time_start" style="width:117px"  value="<s:property value='#request.reg_time_start'/>"/>至<input type="text" id="reg_time_end" name="reg_time_end" style="width:117px"  value="<s:property value='#request.reg_time_end'/>"/>
                </td>
                <td class="t_r">状态</td>
                <td>
                  <input type="hidden" id="h_flag" value="<s:property value='#request.flag'/>"/>
                  <select style="width:117px" id="flag" name="flag" >
                    <option value="">---请选择---</option>
                    <option value="0">过程中</option>
                    <option value="1">预归档</option>
                    <option value="3">已取消</option>
                  </select>
                </td>

              </tr>

              <tr>
                <td class="t_r">合同开始时间</td>
                <td>
                  <input type="text" id="date_start" name="date_start" style="width:117px"  value="<s:property value='#request.date_start'/>"/>至
                  <input type="text" id="date_end" name="date_end" style="width:117px"  value="<s:property value='#request.date_end'/>"/>
                </td>
                <td class="t_r">合同结束时间</td>
                <td>
                  <input type="text" id="date_start1" name="date_start1" style="width:117px"  value="<s:property value='#request.date_start1'/>"/>至
                  <input type="text" id="date_end1" name="date_end1" style="width:117px"  value="<s:property value='#request.date_end1'/>"/>
                </td>
                <td class="t_r"></td>
                <td>
                </td>
              </tr>
              <tr>
                <td class="t_r"></td>
                <td>
                </td>
              </tr>
              <tr>
                <td colspan="6" class="t_c">
                  <input type="submit" value="检 索" onclick="setpage1();"/>&nbsp;&nbsp;
                  <input type="button" value="重 置" onclick="clearInput();"/></td>
              </tr>
            </table>
          </s:form>
        </div>
      </div>
      <div class="fn clearfix">
        <h5 class="fl"><a href="#" class="colSelect fl">合同变更协议列表</a></h5>
        <!-- <input type="submit" name="button2" id="button2" value="新 增" class="fr"> -->
      </div>
    </div>



    <!--Filter End-->
    <!--Table-->
    <s:set name="r" value="#request.result"></s:set>
    <div class="mb10">
      <table width="100%"  class="table_1">
        <tbody>
        <tr class="tit">
          <!-- <td><input type="checkbox" id="test_checkbox_1" name="test_checkbox_1" /></td>-->
          <td class="t_c" width="3%">序号</td>
          <td class="t_c" width="17%">合同名称</td>
          <td class="t_c" width="15%">变更协议自有编号</td>
          <td class="t_c" width="8%">拟变金额(万元)</td>
          <td class="t_c" width="10%">变更事项</td>
          <td class="t_c" width="10%">合同开始时间</td>
          <td class="t_c" width="10%">合同结束时间</td>
          <td class="t_c" width="10%">登记人</td>
          <td class="t_c" width="10%">登记日期</td>

          <td class="t_c" width="5%">状态</td>
          <td class="t_c" width="3%">表单</td>
          <td class="t_c" width="3%">监控</td>
          <%--<td class="t_c" width="3%">终止</td>--%>

        </tr>
        <s:iterator value="#r.list" id="items" status="s">
          <tr>

            <td class="t_c"><s:property value="#s.index+1+(#r.pageInfo.currentPage-1)*10"/></td>
            <td class="t_l"><s:property value="#items[0]"/></td>
            <td class="t_l"><s:property value="#items[1]" /></td>
            <td class="t_r"><s:property value="#items[2]" /></td>
            <td class="t_c"><s:property value="#items[3]" /></td>
            <td class="t_c"><s:property value="#items[6]" /></td>
            <td class="t_c"><s:property value="#items[7]" /></td>
            <td class="t_c"><s:property value="#items[4]" /></td>
            <td class="t_c"><s:property value="#items[5]" /></td>

            <td class="t_c status_show" ><s:property value="#items[8]" /></td>
            <td>
              <center>
                <input type="hidden" name="process" id="process" value="<s:property value="#items[9]" />"/>
                <input type="hidden" name="incidect" id="incidect" value="<s:property value="#items[10]" />"/>
                <a href="javascript:void(0)" onclick="toForm('<s:property value="#items[9]" />','<s:property value="#items[10]" />','<s:property value="#items[11]" />');"><img src="../css/default/images/p_open.gif"/></a>
              </center>
            </td>
            <td>
              <center>
                <a href="javascript:void(0)" onclick="toSee('<s:property value="#items[11]" />');"><img src="../css/default/images/p_but9.gif"/></a>
              </center>
            </td>
            <%--<td>--%>
              <%--<a href="javascript:void(0);" class="stop"><img src="../css/default/images/delete.gif"/></a>--%>
            <%--</td>--%>

          </tr>
        </s:iterator>
        </tbody>
        <tr class="tfoot">
          <td colspan="15"><div class="clearfix">
            <span class="fl"><s:property value="#r.pageInfo.totalRow"/>条记录</span>
            <ul class="fr clearfix pager">
              <li>Pages:<s:property value="#r.pageInfo.currentPage"/>/<s:property value="#r.pageInfo.totalPage"/>
                <input type="hidden" value="<s:property value='#r.pageInfo.totalPage'/>" id="totalPageCount">
                <input type="hidden" value="<s:property value='#r.pageInfo.currentPage'/>" id="currentNumber">
                <input type="text" id="number" name="pageNumber" min="0" max="999" step="1" class="input_tiny" value="<s:property value='#r.pageInfo.currentPage'/>"/>
                <input type="button" name="button" id="button" value="Go" onclick="goPage(0,0)">
              </li>

              <s:if test="#r.pageInfo.currentPage==#r.pageInfo.totalPage">
                <li><a href="javascript:void(0)">&gt;&gt;</a></li>
              </s:if>
              <s:else>
                <li><a href="javascript:void(0)" onclick="goPage(<s:property value='#r.pageInfo.totalPage'/>,3)">&gt;&gt;</a></li>
              </s:else>

              <li>
                <s:if test="#r.pageInfo.currentPage==#r.pageInfo.totalPage">
                  <a href="javascript:void(0)">下一页</a>
                </s:if>
                <s:else>
                  <a href="javascript:void(0)" onclick="goPage(<s:property value='#r.pageInfo.currentPage'/>,2)">下一页</a>
                </s:else>
              </li>
              <li>
                <s:if test="#r.pageInfo.currentPage==1">
                  <a href="javascript:void(0)">上一页</a>
                </s:if>
                <s:else>
                  <a href="javascript:void(0)" onclick="goPage(<s:property value='#r.pageInfo.currentPage'/>,1)">上一页</a>
                </s:else>

              </li>

              <s:if test="#r.pageInfo.currentPage==1">
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
    <!--Table End-->
  </div>
</div>
</body>
</html>