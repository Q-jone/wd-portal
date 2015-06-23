<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
  <meta charset="utf-8" />
  <title>合同流程管理</title>
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
  <script src="js/jquery.form.js"></script>
  <script src="../js/jquery.formalize.js"></script>
  <!--<script src="../js/switchDept.js"></script>-->
  <script src="../js/show.js"></script>
  <script src="js/common.js"></script>
  <link type="text/css" href="../css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
  <script type="text/javascript" src="../js/flick/jquery-ui-1.8.18.custom.min.js"></script>
  <script type="text/javascript" src="../js/flick/jquery.ui.datepicker-zh-CN.js"></script>
  <style type="text/css">
    .ui-datepicker-title span {display:inline;}
    button.ui-datepicker-current { display: none; }
  </style>
  <script type="text/javascript">

    var handleOptions = {
      cache:false,
      type:'post',
      callback:null,
      url:'/portal/contractManage/addReason.action',
      success:function(data){
        if(data=="1"){
          alert("终止成功");
          $('#handleZone').dialog("close");
          $("#form").submit();
        }else{
          alert("终止失败！");
          $('#handleZone').dialog("close");
        }
        return false;
      }
    };

    $(function(){
      $("[id=status_show]").each(function(){
        $(this).html($(this).html().replace("0","过程中").replace("1","已审核").replace("3","已取消"));
      });

      $(".stop").click(function(){
        $( "#handleZone" ).dialog( "open" );
        $("#handleZone").find("#process").val($("#processName",$(this).parents("td")).val());
        $("#handleZone").find("#incident").val($("#pinstance_id",$(this).parents("td")).val());
        $("#handleZone").find("#mainId").val($("#mainId",$(this).parents("td")).val());
      })

      $(".showReason").click(function(){
        var mainId = $("#mainId",$(this).parents("td")).val();
        var temp = "";
        $.post(
                '/portal/contractManage/getReason.action?random='+Math.random(),
                {
                  "mainId": 	mainId
                },
                function(obj, textStatus, jqXHR){
                  if(obj !=null && obj.length>0){
                    for(var i=0;i<obj.length;i++){
                      $("#reasonZone").find("#reason").val($("#processName",$(this).parents("td")).val());
                      temp +="<tr><td style='border:1px solid black;'>"+obj[i].reason+"</td>"+
                      "<td style='border:1px solid black;'><a target='_blank' href='/portal/attach/loadFileList.action?fileGroup=attach&fileGroupName=attachGroup&fileGroupId="+obj[i].attach+"&procType=view'>查看</a></td>"+
                      "<td style='border:1px solid black;'>"+obj[i].operator+"</td>"+
                      "<td style='border:1px solid black;'>"+obj[i].operateTime+"</td></tr>";
                    }
                    $("#reasonTable").append(temp);
                  }
                  $( "#reasonZone" ).dialog( "open" );
                },
                "json"
        ).error(function() { alert("服务器连接失败，请稍后再试!"); })
      })


      $("#handleZone").dialog({
        modal: true,
        autoOpen: false,
        width: 630,
        height: 510,
        zIndex: 9999,
        buttons: [
          {
            text: "确认",
            click: function() {
              if($("#reason").val()!=""){
                $("#handleForm").ajaxSubmit(handleOptions);
              }else{
                alert("请输入原因!");
              }

            }
          },
          {
            text: "取消",
            click: function() {
              //$("#reason").val("");
              //$("#attach").val("");
              //$("#attachFrame").attr("src","/portal/attach/loadFileList.action?fileGroup=attach&fileGroupName=attachGroup&fileGroupId=&userName="+"<s:property value='#session.userName'/>"+"&loginName="+"<s:property value='#session.loginName'/>"+"&procType=edit&targetType=frame&type=1");
              //$("#form").submit();
              $( this ).dialog( "close" );
            }
          }
        ],
        close: function(event, ui) {$("#form").submit();}
      });

      $("#reasonZone").dialog({
        modal: true,
        autoOpen: false,
        width: 630,
        height: 510,
        zIndex: 9999,
        buttons: [
          {
            text: "取消",
            click: function() {
              //$("#form").submit();
              $( this ).dialog( "close" );
            }
          }
        ],
        close: function(event, ui) {$("#form").submit();}
      });
    })
    function setpage1(){
      $("#page").val("1");
    }
    function clearInput(){
      $("#status").children("option:eq(0)").attr("selected",true);
      $("#contract_name").val("");
      $("#contract_num").val("");

      $("#plan_num").val("");
      $("#sign_cop").val("");
      $("#self_num").val("");
      $("#project_num").val("");

      $("#date_start").val("");
      $("#date_end").val("");
      $("#add_person").val("");
      $("#contract_money").val("");
      $("#contract_money_end").val("");

    }

    function toForm( process,instant,taskid){
      var url =     "http://10.1.48.16:8080/workflow/contractApproval-contractMain/forward.action?"+
      // "http://10.1.48.16:8080/workflow/contracthsChange-reviewMain/forward.action?" +
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

    function toReason(
            mainId){
      var url = "/portal/contractManage/getReason.action?mainId="+mainId;
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
        <li class="fin">合同流程管理</li>
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
    <div class="tabs_2">
      <ul>
        <li class="selected"><a href="/portal/contractManage/newList.action"><span>新合同查询</span></a></li>
        <li ><a href="/portal/contractManage/list.action"><span>合同流程查询</span></a></li>
        <li><a href="/portal/contractReview/list.action"><span>后审流程查询</span></a></li>
      </ul>
    </div>
    <!--Filter-->
    <div class="filter">
      <div class="query">
        <div class="p8 filter_search">
          <s:form action="newList" id="form" method="post"  namespace="/contractManage">

            <input type="hidden" name="page" id="page" value="<s:property value="page"/>"/>

            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td class="t_r">合同名称</td>
                <td>
                  <input type="text" id="contract_name" name="contract_name" class="input_xlarge" value="<s:property value='#request.contract_name'/>"/>
                </td>
                <td class="t_r">合同编号</td>
                <td>
                  <input type="text" id="contract_num" name="contract_num" class="input_xlarge"  value="<s:property value='#request.contract_num'/>"/>
                </td>
                <td class="t_r">计划编号</td>
                <td>
                  <input type="text" id="plan_num" name="plan_num" class="input_xlarge"  value="<s:property value='#request.plan_num'/>"/>
                </td>
              </tr>

              <tr>
                <td class="t_r">对方单位</td>
                <td>
                  <input type="text" id="sign_cop" name="sign_cop" class="input_xlarge"  value="<s:property value='#request.sign_cop'/>"/>
                </td>
                <td class="t_r">自用编号</td>
                <td>
                  <input type="text" id="self_num" name="self_num" class="input_xlarge"  value="<s:property value='#request.self_num'/>"/>
                </td>
                <td class="t_r">工程编号</td>
                <td>
                  <input type="text" id="project_num" name="project_num" class="input_xlarge"  value="<s:property value='#request.project_num'/>"/>
                </td>

              </tr>

              <tr>
                <td class="t_r">登记日期</td>
                <td>
                  <input type="text" id="date_start" name="add_time_start" style="width:117px"  value="<s:property value='#request.add_time_start'/>"/>至
                  <input type="text" id="date_end" name="add_time_end" style="width:117px"  value="<s:property value='#request.add_time_end'/>"/>
                </td>
                <td class="t_r">登记人</td>
                <td>
                  <input type="text" id="add_person" name="add_person" class="input_xlarge"  value="<s:property value='#request.add_person'/>"/>
                </td>
                <td class="t_r">合同价(万元)</td>
                <td>
                  <input type="text" id="contract_money" name="contract_money" class="input_xlarge"  value="<s:property value='#request.contract_money'/>"/>
                </td>
              </tr>
              <tr>
                <td class="t_r">合同状态</td>
                <td>
                  <input type="hidden" id="h_status" value="<s:property value='#request.pstatus'/>"/>
                  <select style="width:117px" id="status" name="pstatus" >
                    <option value="">---请选择---</option>
                    <option value="0">过程中</option>
                    <option value="1">已审核</option>
                    <option value="3">已取消</option>
                  </select>
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
        <h5 class="fl"><a href="#" class="colSelect fl">合同列表</a></h5>
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
          <td class="t_c" width="10%">计划编号</td>
          <td class="t_c" width="10%">合同编号</td>
          <td class="t_c" width="10%">自用编号</td>
          <td class="t_c" width="20%">合同名称</td>
          <td class="t_c" width="7%">工程编号</td>
          <td class="t_c" width="8%">对方单位</td>
          <td class="t_c" width="7%">登记日期</td>
          <td class="t_c" width="5%">登记人</td>
          <td class="t_c" width="7%">合同价(万元)</td>
          <td class="t_c" width="5%">合同状态</td>
          <td class="t_c" width="3%">表单</td>
          <td class="t_c" width="3%">监控</td>
          <td class="t_c" width="3%">终止</td>
        </tr>
        <s:iterator value="#r.list" id="items" status="s">
          <tr>

            <td class="t_c"><s:property value="#s.index+1+(#r.pageInfo.currentPage-1)*10"/></td>
            <td class="t_c"><s:property value="#items[0]"/></td>
            <td class="t_l"><s:property value="#items[1]" /></td>
            <td class="t_l"><s:property value="#items[2]" /></td>
            <td class="t_l"><s:property value="#items[3]" escape="0"/></td>
            <td class="t_c"><s:property value="#items[4]" /></td>
            <td class="t_l"><s:property value="#items[5]" /></td>
            <td class="t_c"><s:property value="#items[6]" /></td>
            <td class="t_c"><s:property value="#items[7]" /></td>
            <td class="t_r"><s:property value="#items[8]" /></td>
            <td class="t_c" id="status_show"><s:property value="#items[9]" /></td>
            <td>
              <center>
                <a href="javascript:void(0)" onclick="toForm('<s:property value="#items[11]" />','<s:property value="#items[10]" />','<s:property value="#items[13]" />');"><img src="../css/default/images/p_open.gif"/></a>
              </center>
            </td>
            <td>
              <center>
                <a href="javascript:void(0)" onclick="toSee('<s:property value="#items[13]" />');"><img src="../css/default/images/p_but9.gif"/></a>
              </center>
            </td>

            <td>
              <input type="hidden" value="<s:property value='#items[11]'/>" id="pinstance_id"/>
              <input type="hidden" value="<s:property value='#items[12]'/>" id="processName"/>
              <input type="hidden" value="<s:property value='#items[13]'/>" id="mainId"/>
              <center>
                <s:if test='#items[10]=="99"'>
                  <a href="javascript:void(0);" class="showReason"><img src="../css/default/images/p_find.gif"/></a>
                </s:if>
                <s:elseif test='#session.userName=="胡炎生" || #session.userName=="李晴阳" '>
                  <a href="javascript:void(0);" class="stop"><img src="../css/default/images/delete.gif"/></a>
                </s:elseif>
                <s:else></s:else>
              </center>
            </td>
          </tr>
        </s:iterator>
        </tbody>
        <tr class="tfoot">
          <td colspan="15"><div class="clearfix"><span class="fl"><s:property value="#r.pageInfo.totalRow"/>条记录</span>
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