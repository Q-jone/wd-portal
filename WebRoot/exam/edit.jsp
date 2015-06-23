<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>在线测评</title>
<link rel="stylesheet" href="ui/css/formalize.css" />
<link rel="stylesheet" href="ui/css/page.css" />
<link rel="stylesheet" href="ui/css/default/imgs.css" />
<link rel="stylesheet" href="ui/css/reset.css" />
<link type="text/css" href="ui/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
<!--[if IE 6.0]>
         <script src="ui/js/iepng.js" type="text/javascript"></script>
         <script type="text/javascript">
              EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
         </script>
     <![endif]-->
<script src="ui/js/html5.js"></script>
<script src="ui/js/jquery-1.7.1.min.js"></script>
<script src="ui/js/jquery-ui-1.8.18.custom.min.js"></script>
<script src="ui/js/jquery.formalize.js"></script>
<script src="ui/js/jquery.form.js"></script>
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
		$("#littleMsg").show();// 弹出小提示模块初始化  默认显示
		$(".questionNum").css("top",vt+"px");	// 右侧序号模块初始位置
		
		
		// 右侧序号模块点击事件
		$(".questionNum .topname").mouseover(function(){
			$(this).html("回顶部");
		}).mouseout(function(){
			$(this).html("未选择");
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
		
		/* //问题中  所有选项点击选择时触发事件
		$(".questionCon input").click(function(){
			//setDefoult(this);
			
		});
		
		$(".questionCon textarea").each(function(i,n){
			if($(n).val()!==''){
				setDefoult(n);
			}
			
		});
		$(".questionCon textarea").blur(function(){
				if($(this).val()!==''){
					setDefoult(this);
				}else{
					$(this).parent("td").parent("tr").prev(".tit").find(".num").removeClass("numok");
					var num = $(this).parent("td").parent("tr").prev(".tit").find(".num").eq(0).text();
					//alert(num);
					showQuestionNum(num.split('.')[0],num.split('.')[1]);
					
				}
		});
		$(".questionCon input[type='text']").blur(function(){
				if($(this).val()!==''){
					setDefoult(this);
				}else{
					$(this).parent("td").parent("tr").prev(".tit").find(".num").removeClass("numok");
					var num = $(this).parent("td").parent("tr").prev(".tit").find(".num").eq(0).text();
					//alert(num);
					showQuestionNum(num.split('.')[0],num.split('.')[1]);
					
				}
		}); */
		
		setData();//初始化数据
		
		saveDataDefault();
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
	//点击后保存数据
	function saveDataDefault(){
		
		
		
		
		$(".con textarea").on("change",function(){
			setValue(this);
		});
		$(".con input").on("change",function(){
			
			if($(this).attr("type")=='radio'){
				var opname= $(this).attr("name");
				var uOption = "";
				$(".con input[name='"+opname+"']:radio").each(function(i,n){
					if($(n).attr("uoptionid")!=''){
						uOption = $(n).attr("uoptionid");
					}
					$(n).attr("uoptionid","");
				});
				$(this).attr("uoptionid",uOption);
			}
			//console.log($(this).val());
			//console.log($(this).attr("uoptionid"));
			setValue(this);
		});
		
		var isUserOk = $("#isUserOk").val();
		
		if(isUserOk=='false'){
			disOper();
		}
		
	}
			var formOptions = {
				cache:false,
				type:'post',
				callback:null,
				dataType :'text',
				url:"/portal/exam/saveUserOption.action",
			    success:function(data){
					if(data){
						var d = data.split(",");
						//console.log(d.length);
						if(d.length>2){
							var questid = d[0];
							var uoptionid = d[1];
							var optionid = d[2];
							$("#num_"+questid).addClass("numok");
							
							$("#o_"+optionid).attr("uoptionid",uoptionid);
							
							//保存数据后  textarea  保存后  如果为空  则视为未填写
							$(".con textarea").each(function(){
								var val = $(this).val();
								if(val==""){
									var qid = $(this).attr("questid");
									$("#num_"+qid).removeClass("numok");
								}
							});
							$(".con input[type='text']").each(function(){
								var val = $(this).val();
								if(val==""){
									var qid = $(this).attr("questid");
									$("#num_"+qid).removeClass("numok");
								}
							});
							
							
							
							undisOper();
							showQuestionNum();
						}else{
							// 刷新页面
						}
						
					}
			    },error:function() { alert("服务器连接失败，请稍后再试!"); }
		};
	function setValue(obj){
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
	
	
	
		disOper();
		$("#form .cl input").val("");
		$("#answer").val($(obj).val());
		var questid = $(obj).attr("questid");
		$("#questId").val(questid); //
		$("#groupId").val($("#q_"+questid).attr("groupid")); //
		$("#optionId").val($(obj).attr("optionid")); //
		$("#uOptionId").val($(obj).attr("uoptionid")); //
		$("#remark").val(""); //
		

		$("#form").ajaxSubmit(formOptions);  
	}
	
	function setDefoult(obj){
		
		$(obj).parent("td").parent("tr").prev(".tit").find(".num").addClass("numok");
		var num = $(obj).parent("td").parent("tr").prev(".tit").find(".num").eq(0).text();
		//alert(num);
		hideQuestionNum(num.split('.')[0],num.split('.')[1]);
		
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
			if(!$(this).hasClass("numok")){
				
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
</script>
<style type="text/css">
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
<input type="hidden" id="isUserOk" value="<s:property value='#request.isUserOk'/>" />
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
                      <s:if test="#request.isUserOk">
                      <li><a href="javascript:void(0);" onclick="custom_save();" class="imp">提交保存</a></li>
                      </s:if>
                      <li><a href="javascript:toggleMsg();" class="tips">小提示</a></li>
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
                        	<h1 class="t_c" id="e_tit"><s:property value='examMain.title'/></h1>
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
                <div id="num_<s:property value="#question.id" />" class="<s:if test="#request.mapQ.get(#question.id)">numok</s:if> num <s:if test="#question.showNum!=1">numhid</s:if>"><s:property value="#question.questNum" /></div>
                </td>
                <td><div id="qtit_<s:property value="#question.id" />"><s:property value="#question.title" /></div></td>
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
           <%--  <tr class="tit" id="tit_<s:property value="#group.groupNum" />_1">
                <td width="5">
                <div class=" numok num"><s:property value="#group.groupNum" />.1</div>
                </td>
                <td><div><s:property value="#group.groupDescription" /></div></td>
            </tr>
            <tr class="con">
                <td></td>
                <td >
                <input name="m.option" type="radio" checked="checked" value="A" />A、党员
                <input name="m.option" type="radio" value="B" />B、群众
                </td>
            </tr> --%>
        </table>
        </s:iterator>
        <!-- 题目组循环！END -->
        </div>
                          <div class="mb10 t_c">
                          <s:if test="#request.isUserOk">
                          <input type="button" value="提交保存" onclick="custom_save()" />
                                  &nbsp;
<input type="button" value="关闭" onclick="custom_close()" />
&nbsp;
						</s:if>
                          </div>
                            <div class="footer"></div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 隐藏弹出层-->
            <div id="littleMsg" class="mb10 panel_5 littleMsg">
              <div class="tit">
                <h4><s:property value='examMain.targetName'/>：
                <input type="button" value="关闭"  onclick="toggleMsg();" style="float:right; margin:10px;"/></h4>
              </div>
              <div class="con">
                <ul class="list t_ind2">
                  <li><s:property value='examMain.desp'/></li>
					<li class="right_tit"><s:property value='examMain.startGroup'/></li>
                    <li class="right_time"><s:property value='examMain.cTime'/></li>
                </ul>
              </div>
            </div>
            <!-- 隐藏弹出层END-->
             <!--序号组-->
            <div class="questionNum">
            	<ul class="topname">未选择</ul>
            </div>
            <!--序号组end-->
           
        </div>
        
          
         
    </div>
    
</body>
</html>

