<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>在线测评</title>
<link rel="stylesheet" href="../exam/ui/css/formalize.css" />
<link rel="stylesheet" href="../exam/ui/css/page.css" />
<link rel="stylesheet" href="../exam/ui/css/default/imgs.css" />
<link rel="stylesheet" href="../exam/ui/css/reset.css" />
<link type="text/css" href="../exam/ui/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
<!--[if IE 6.0]>
         <script src="ui/js/iepng.js" type="text/javascript"></script>
         <script type="text/javascript">
              EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
         </script>
     <![endif]-->
<script src="../exam/ui/js/html5.js"></script>
<script src="../exam/ui/js/jquery-1.7.1.min.js"></script>
<script src="../exam/ui/js/jquery-ui-1.8.18.custom.min.js"></script>
<script src="../exam/ui/js/jquery.formalize.js"></script>
<script src="../exam/ui/js/jquery.form.js"></script>
<script  type="text/javascript">
var cansubmit = true;

	$(document).ready(function(){		
		
		var vt = $(".questionCon").eq(0).offset().top;  // 获取 第一个问题的位置  初始化右侧序号模块提供位置参数
		$(window).scroll(function() { 		
			var wt = $(window).scrollTop();
			var st = wt+150;
			$("#rightDiv").css("top",st+"px");		// 操作模块位置初始化
			$("#littleMsg").css("top",st+"px");		// 弹出小提示模块初始化
			
			if(wt>vt){// 滚动条滚动时设置右侧序号模块位置
				$(".questionNum").css("top",wt+"px");	
			}else{
				$(".questionNum").css("top",vt+"px");	
			}
		});
		//$("#littleMsg").show();// 弹出小提示模块初始化  默认显示
		$(".questionNum").css("top",vt+"px");	// 右侧序号模块初始位置
		
		
		// 右侧序号模块点击事件
		$(".questionNum .topname").mouseover(function(){
			$(this).html("回顶部");
		}).mouseout(function(){
			$(this).html("导航栏");
		}).click(function(){
			$(window).scrollTop(0);
		}).siblings().click(function(){
			var txt = $(this).text();
			txt = txt.split('.')[0]+"_"+txt.split('.')[1];
			var otop = $("#tit_"+txt).offset().top-40;
			$(window).scrollTop(otop);
		});
		
		//js title 换行
		var tit = $("#e_tit").html();
		var regExp = new RegExp(" ","g");
		tit = tit.replace(regExp , "<br>"); 
		$("#e_tit").html(tit);
		
		setData();//初始化数据
		disOper();
		//saveDataDefault();
	});
	//初始化数据
	function setData(){
		$("#question_all tr").each(function(){
			var groupId = $(this).attr("groupid");
			var q_quesrtid = $(this).attr("id");
			$(this).appendTo($("#group_"+groupId));
			$("#option_all ."+q_quesrtid).appendTo($("#o"+q_quesrtid));
		});
		
		/* $("label").click(function(){
			$(this).prev("input").change();
			$(this).prev("input").attr("checked","checked");
		}); */
		
		showQuestionNum();
		
	}
	
	function disOper(){
	cansubmit = false;
		$(".con input").attr("disabled","disabled");
		$(".con textarea").attr("disabled","disabled");
		$("input[type='button']").attr("disabled","disabled");
	}
	function undisOper(){
	cansubmit = true;
		$(".con input").removeAttr("disabled");
		$(".con textarea").removeAttr("disabled");
		$("input[type='button']").removeAttr("disabled");
	}

	// 小提示  显示隐藏
	function toggleMsg(){
		$("#littleMsg").toggle();
	}
	
	//暂存功能
	function saveWait(){
		var otop = $("#tit_2_7").offset().top-40;
		//var st = $(window).scrollTop();
		$(window).scrollTop(otop);
		//alert(st);
	}
	
	function gotoQ(qid){
	//alert(qid);
		var otop =$("#q_"+qid).offset().top-40;
		$(window).scrollTop(otop);
	}
	// 隐藏序号模块 中已经填写过的序号
	function hideQuestionNum(questid){
		
		$("#Qnum_"+questid).hide();
	}
	// 显示序号模块 中未填写过的序号
	function showQuestionNum(){
		$(".questionNum .rem").remove();
		$(".questionCon .num").each(function(){
			if(!$(this).hasClass("numhid")){
				
				var hclass ="";
				if($(this).hasClass("numhid")){
					hclass="numhid";
				}
				
				var qnum = $(this).text();
				var qid = $(this).attr("id").split("_")[1];
				
				var istextarea =  $(".con textarea[questid='"+qid+"']").length;
				
				if(istextarea<=0){
					var groupnum = $(this).parents("table").eq(0).attr("groupnum");  
					var qhtml = "<ul id='Qnum"+qid+"' class='rem "+hclass+" ' onclick=gotoQ('"+qid+"') >"+qnum+"</ul>";
					
					$(".questionNum").append(qhtml);
				}
				
				//var html();
			}
		});
		//$("#QNum_"+groupNum+"_"+questionNum).show();
	}
	
	function custom_close(){
if 
(confirm("您确定要关闭本页吗？")){
window.opener=null;
window.open('','_self');
window.close();
}
else{}
}
	function custom_save(){
	if(!cansubmit) return false;
	
	//保存数据后  textarea  保存后  如果为空  则视为未填写
		
		var tit = "";
		$(".con textarea").each(function(){
			var val = $(this).val();
			if(val.length>450){
				var qid = $(this).attr("questid");
				tit = $("#qtit_"+qid).html();
				$(this).focus();
			}
		});
		$(".con input[type='text']").each(function(){
			var val = $(this).val();
			if(val.length>240){
				var qid = $(this).attr("questid");
				tit = $("#qtit_"+qid).html();
				$(this).focus();
			}
		});
	if(tit!=""){
		alert(tit+"\r\n文字过长！");
		return false;
	}
	
	var unnum = $(".questionNum .rem").length;
	if(unnum>0){
		var  qid = $(".questionNum .rem").eq(0).attr("id");
		qid=qid.substring(4,qid.length);
		var tit = $("#qtit_"+qid).text();
		alert("您还有:\r\n\""+tit+"\"\r\n等题目未答，请回答完后提交保存！");
		return false;
	}
	
	if 
(confirm("您确定要提交吗？\r\n提交后将不可以修改！")){
		var uMainid = $("#uMainid").val();
		$.post('/portal/exam/saveUserMain.action?uMainid='+uMainid+'&random='+Math.random(),
				{
					
				},
				function(obj, textStatus, jqXHR){
					alert("保存成功！");
					window.location.reload();
				},
				"json"
			).error(function() { alert("服务器连接失败，请稍后再试!"); });

}
else{}	
	}
	function viewQuestionLeft(qid,obj){
		var offset = $(obj).parent().offset();
		var the_top = offset.top;
		
		$("#question_detail_left").attr("style","top:" + the_top + "px");
		$("#question_detail_left").show();
		$("#question_detail_left").html("...........");
		viewQuestion("question_detail_left",qid);
	}	
	
	function viewQuestion(obj,qid){
		if(obj==null | ""==obj){
			obj = "question_detail";
		}
		$("#"+obj).html("Loading....");		
		$.ajax({
			type : 'post',
			url : 'viewQ.action?random='+Math.random(),
			dataType:'json',
			cache : false,
			data:{
				id : qid
			},
			error:function(){
				alert("系统连接失败，请稍后再试！")
			},
			success:function(data){
				var qtype = data.q.questType;
				var html = '';
				html += '<div><b>题干：</b>' + data.q.title + '</div>';
				if(qtype=="0" || qtype=="1"){//选择
					html += '<div><b>选项：</b></div>';
					$(data.options).each(function(){
						html += this.opCode + '：' + this.opValue + '<br/>';
					});
					html += '<div><b>答案：</b>' + data.q.rightAnswer + '</div>';
					
				}else if(qtype=="2"  || qtype=="3"){//问答
					html += '<div><b>答案：</b>' + data.q.rightAnswer + '</div>';
					
				}
				html += '<div><b>备注：</b>' + data.q.remark + '</div>';
				$("#"+obj).html(html);
				
			}
		});
	}	
	function hideDetail(){
		$('#question_detail_left').hide();
	}
	function editQ(pid,qid){
		window.location.href='addQ.action?pid='+pid+'&qid='+qid;
	}
	function deleteQ(pid,qid){
		if(window.confirm('确定要删除吗？\n这将影响到已完成的答卷，且不可恢复。\n请谨慎操作。')){
			window.location.href='deleteQ.action?pid='+pid+'&qid='+qid;
		}
	}	
</script>
<style type="text/css">
#question_detail{position:absolute; left:590px; top:100px; width:350px; height:200px; display:none; padding:5px; border:solid 1px #999;
background:#eee;OVERFLOW-y:auto}
#question_detail_left{position:absolute; left:460px; top:100px; width:350px; height:200px; display:none; padding:5px; border:solid 1px #999;
background:#eee;OVERFLOW-y:auto}
.littleMsg{ width:600px; position:absolute; top:150px; left:120px; z-index:9999;

border:#c7c7c7 1px solid;}
.littleMsg .list{ padding:10px; line-height:30px;  } 
.littleMsg .right_tit{ text-align:right; font-size:14px;} 
.littleMsg .right_time{ text-align:right;}   
.t_ind2{text-indent:2em ;}
.exam td,.exam th{font-size:14px; padding-top:5px; padding-bottom:5px; color:#666;}
.exam table input{ margin-left:10px;}
.exam .num{ padding-left:2px;padding-right:2px; background-color:#CCC; color:#000; margin-right:5px; margin-left:20px; text-align:center; }
.exam .numok{ background-color:#390; color:#FFF}
.exam .tit td{font-size:16px; color:#000;}
.exam .con td{ vertical-align:top; height:55px; color:#000;}
.exam .con div{float: left;}
.exam .questionNum{ font-size:12px;position:absolute; top:100px; left:760px; z-index:9999;}
.exam .questionNum ul{background-color:#CCC; margin-top:2px; text-align:center; padding:2px; cursor:pointer;}
.exam .questionNum ul:hover{background-color:#390; color:#FFF}
.exam .questionNum .topname{ background-color:#06C; color:#FFF;}
.t2em{ text-indent:2em;}
.group_bg{color:#0066CC; font-size: 16px; font-weight: bold;}
.numhid{display: none;}
</style>
</head>

<body class="Flow exam">
<input type="hidden" id="uMainid" value="<s:property value='#request.examUserMain.id'/>" />
<div class="f_bg_fw">
    <div class="w850">
    	<div class="logo_2"></div>
    </div>
      <div class="gray_bg">
          <!--Panel_6-->  
        <div class="Divab1" id="rightDiv">
        	
          <!--1st-->
            <div class="panel_6">
              <div class="divT">
                <div class="mb10 icon icon_1"></div>
                <div class="more_4"><a href="#" title="更多">更多</a></div>
              </div>
              <div class="divH">
                <div class="divB">
                  <h5 class="clearfix">相关操作</h5>
                  <div class="con">
                    <ul class="button clearfix">
                      <!--<li><a href="javascript:saveWait();" class="ywbl">暂存</a></li>
                      <li><a href="#" class="print">打印</a></li>
                      <li><a href="#" class="imp">提交保存</a></li>
                      <li><a href="#" class="jk">当前结果</a></li>-->
                      <li><a href="javascript:void(0);" onclick="window.location.href='addQ.action?pid=<s:property value="#request.paper.id" />'" class="imp">添加试题</a></li>
                      <li><a href="javascript:void(0);" onclick="window.location.href='list.action'" class="ywbl">管理试卷</a></li>
                      <!--
                      <li><a href="#" class="exp">公文导出</a></li>-->
                    </ul>
                  </div>
                </div>
                <div class="divF"></div>
              </div>
            </div>
            <!--1st End-->
        </div>
      <!--Panel_6 End-->  
        	<div class="gray_bg2">
            	<div class="w_bg">
                	<div>
                    	<div class="Top_fw">
                        	<h1 class="t_c" id="e_tit"><s:property value='#request.paper.title'/></h1>
	<div class="mb10 Step clearfix">
          <ul class="t2em"><s:property value='examMain.exDescription'/></ul>
        </div>
        
        <div id="option_all"  style="display: none;">
        <s:iterator value="#request.options" id="option" status="s">
        	<s:set name="ischecked" value="" />
        	<s:set name="textval" value="" />
        	<s:set name="uoptionid" value="" />
        	<s:if test="#request.map.get(#option.id)!=null&&#request.map.get(#option.id)!=''">
        		<s:set name="ischecked" value="checked='checked'" />
        		<s:set name="textval" value="#request.map.get(#option.id)[1]" />
        		<s:set name="uoptionid" value="#request.map.get(#option.id)[0]" />
        	</s:if>
        	<div class="q_<s:property value="#option.questId" />">
        		<s:if test="#option.opType==0">
        			<input 	id="o_<s:property value="#option.id" />" 
        					questid="<s:property value="#option.questId" />" 
        					uoptionid="<s:property value="#uoptionid" />" 
        					optionid="<s:property value="#option.id" />" 
        					name="<s:property value="#option.questId" />.opType" 
        					type="radio" <s:property value="#ischecked" /> 
        					value="<s:property value="#option.opCode" />" />
        			<label><s:property value="#option.opCode" />、<s:property value="#option.opValue" /></label>
				</s:if>
				<s:if test="#option.opType==1">
        			<input 	id="o_<s:property value="#option.id" />" 
        					questid="<s:property value="#option.questId" />" 
        					uoptionid="<s:property value="#uoptionid" />" 
        					optionid="<s:property value="#option.id" />" 
        					name="<s:property value="#option.id" />.opType" 
        					type="checkbox" <s:property value="#ischecked" /> 
        					value="<s:property value="#option.opCode" />" />
        			<label><s:property value="#option.opCode" />、<s:property value="#option.opValue" /><label>
				</s:if>
				<s:if test="#option.opType==3">
        			<s:property value="#option.opCode" />
        			<input 	id="o_<s:property value="#option.id" />" 
        					questid="<s:property value="#option.questId" />" 
        					uoptionid="<s:property value="#uoptionid" />" 
        					optionid="<s:property value="#option.id" />" 
        					name="<s:property value="#option.id" />.opType" 
        					type="text"  
        					value="<s:property value="#textval" />" />
				</s:if>
				<s:if test="#option.opType==2">
        			<textarea id="o_<s:property value="#option.id" />" 
        					questid="<s:property value="#option.questId" />" 
       						uoptionid="<s:property value="#uoptionid" />" 
       						optionid="<s:property value="#option.id" />" 
       						name="<s:property value="#option.id" />.opType" style="width:600px; height:80px;" ><s:property value="#textval" /></textarea>
				</s:if>
        	</div>
        </s:iterator>
        </div>        
        
        <table id="question_all" style="display: none;">
         <s:iterator value="#request.questions" id="question" status="s">
         	
         	<tr class="tit " groupid="<s:property value="#question.groupId" />" id="q_<s:property value="#question.id" />">
                <td width="5">
                <div id="num_<s:property value="#question.id" />" class="numok num <s:if test="#question.showNum!=1">numhid</s:if>"><s:property value="#question.questNum" /></div>
                </td>
                <td><div id="qtit_<s:property value="#question.id" />"><s:property value="#question.title" />
                <span style="float:right">
                               		<a class="mr5" style="display:inline" href="javascript:editQ('<s:property value="#request.paper.id" />','<s:property value="#question.id"/>')">修改</a>|
               		<a class="mr5" style="display:inline" href="javascript:deleteQ('<s:property value="#request.paper.id" />','<s:property value="#question.id"/>')">删除</a>|
               		<a class="mr5" style="display:inline" href="javascript:void(0)" onmouseover="javascript:viewQuestionLeft('<s:property value="id"/>',this)" onmouseout="hideDetail();">详情</a>
               	</span>
                </div>
                </td>
            </tr>
            <tr class="con " groupid="<s:property value="#question.groupId" />">
                <td></td>
                <td id="oq_<s:property value="#question.id" />">

                </td>
            </tr>
         </s:iterator>
        </table>
        <div id="form_hid"  style="display: none;">
        <s:form id="form">
        	<input name="mainId" id="mainId" value="<s:property value="examMain.id" />" />
        	<input name="loginName" id="loginName" value="<s:property value="#request.examUserMain.loginName" />" />
        	<input name="deptId" id="deptId" value="<s:property value="#request.examUserMain.deptId" />" />
        	<div class="cl">
        	<input name="groupId" id="groupId" value="" />
        	<input name="questId" id="questId" value="" />
        	<input name="optionId" id="optionId" value="" />
        	<input name="answer" id="answer" value="" />
        	<input name="uOptionId" id="uOptionId" value="" />
        	</div>
        </s:form>
        </div>
        <div class="mb10 questionCon">
        <!-- 题目组循环！ -->
        <s:iterator value="#request.groups" id="group" status="s">
        <table width="90%" border="0" cellspacing="0" cellpadding="0" groupnum="<s:property value="#group.groupNum" />" id="group_<s:property value="#group.id" />">						
            <thead><th colspan="2" >
            	<p class="group_bg"><s:property value="#group.title" /></p>
            	<p class="t_ind2 "><s:property value="#group.groupDescription" /></p>
            </th></thead>
        </table>
        </s:iterator>
        <!-- 题目组循环！END -->
        </div>
<!--                           <div class="mb10 t_c">
                          <input type="button" value="提交保存" onclick="custom_save()" />
                                  &nbsp;
<input type="button" value="关闭" onclick="custom_close()" />
&nbsp;
                          </div> -->
                            <div class="footer"></div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 隐藏弹出层-->
            <!-- 隐藏弹出层END-->
             <!--序号组-->
            <div class="questionNum">
            	<ul class="topname">导航栏</ul>
            </div>
            <!--序号组end-->
           
        </div>
        
          
         
    </div>
    <div id="question_detail_left"></div>
    <div id="question_detail"></div>
</body>
</html>

