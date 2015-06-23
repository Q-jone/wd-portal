<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.wonders.stpt.exam.entity.ExamGroup"  %>      
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>试题创建</title>
<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/default/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
<link rel="stylesheet" href="../exam/ui/js/validation/validationEngine.css"/> 
<!--[if IE 6.0]>
           <script src="../js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
	<script src="../exam/ui/js/jquery-1.2.6.js"></script>
	<script type="text/javascript" src="../exam/ui/js/validation/validationEngine.js"></script> 
	<script type="text/javascript">
        $(document).ready(function () {
    		$("#qtype").change(function(){
    			
    			var qtype = $(this).val();
    			//清空当前的选项
    			$("#key_setting").empty();
    			$("#btn_xuan_addrow").hide();
    			
    			TOTAL_BLANKS = 0;
    			
    			//选择题
    			if(0==qtype || 1==qtype){
    				xuan_init(qtype);
    			}else if(2==qtype || 3==qtype){
    				wenda(qtype);
    			}
    			
    		});
    		
    		if($('#qid').val()==''){
    			xuan_init(0);    			
    		}else{
    			load_init();
    		}
    		
        	$("#button2").click(function(){
        		window.location.href = "listQ.action?pid="+$('#mainId').val();
        	})
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
			
        });
        
        function checkForm(){
        	var value = $('#title').val();
        	if(value==null || value==""){
        		alert('请输入试卷名称!');
        		$('#title').focus();
        		return false;
        	}

        	$("#form").submit();
        }      
        xuan_init = function(qtype){
        	$("#btn_xuan_addrow").show();
        	var options = ['A','B','C','D'];
        	$("#key_setting").append('<table class="stable" width="455" align="left" id="key_setting_table">');
        	$(options).each(function(i){
        		var html = '<tr>';
        		html += '<td>选项' + this + '<input type="hidden" name="sid" value=""/></td>';
        		if(0==qtype){
        			html += '<td><input type="radio" class="validate[required]" name="skey" value="'+this+'" /></td>';
        		}else if(1==qtype){
        			html += '<td><input type="checkbox" class="validate[required,minCheckbox[4]]" name="skey" value="'+this+'" /></td>';
        		}
        		
        		var aremove = '<a href="javascript:;" onclick="$(this).parent().parent().remove()">移除</a>';
        		html += '<td><textarea rows="2" cols="40" name="soption" class="validate[required]" id="toption_' + this + '"></textarea> '+aremove+'</td>';
        		html += '</tr>';
        		
        		$("#key_setting_table").append(html);
        	}); 
        	$("#key_setting").append('</table>');
        }        
      //选择题增加选项
        xuan_addrow = function(qtype){
        	$("#btn_xuan_addrow").show();
        	var options = ['A','B','C','D','E','F','G','H'];
        	for(var i=0;i<options.length;i=i+1){
        		var this_value = options[i];
        		if(!document.getElementById("toption_"+this_value)){
        			var html = '<tr>';
        			html += '<td>选项' + this_value + '<input type="hidden" name="sid" value=""/></td>';
        			if(0==qtype){
        				html += '<td><input type="radio" class="validate[required]" name="skey" value="'+this_value+'" /></td>';
        			}else if(1==qtype){
        				html += '<td><input type="checkbox" class="validate[required,minCheckbox[4]]" name="skey" value="'+this_value+'" /></td>';
        			}
        			var aremove = '<a href="javascript:;" onclick="$(this).parent().parent().remove()">移除</a>';
        			html += '<td><textarea rows="2" cols="40" name="soption" class="validate[required]" id="toption_' + this_value + '"></textarea> '+aremove+'</td>';
        			html += '</tr>';
        			$("#key_setting_table").append(html);
        			break;
        		}
        	};
        }        
      //修改时_选择题初始化选项
        xuan_init_load = function(qtype){
        	$("#btn_xuan_addrow").show();
        	$("#key_setting").append('<table class="stable" width="455" align="left" id="key_setting_table">');
        	$(OPTION_LIST).each(function(i){
        		var html = '<tr>';
        		html += '<td>选项' + this.opCode + '<input type="hidden" name="sid" value="'+this.id+'"/></td>';
        		if(0==qtype){
        			html += '<td><input type="radio" class="validate[required]" name="skey" value="'+this.opCode+'" /></td>';
        		}else if(1==qtype){
        			html += '<td><input type="checkbox" class="validate[required,minCheckbox[4]]" name="skey" value="'+this.opCode+'" /></td>';
        		}
        		
        		var aremove = '<a href="javascript:;" onclick="removeOption(this,&quot;'+this.id+'&quot;);">移除</a>';
        		html += '<td><textarea rows="2" cols="40" name="soption" class="validate[required]" id="toption_' + this.opCode + '">'+this.opValue+'</textarea> '+aremove+'</td>';
        		html += '</tr>';
        		
        		$("#key_setting_table").append(html);
        	}); 
        	$("#key_setting").append('</table>');
        }      
      //问答题
        wenda = function(qtype){
        	var html = '';
        	if(2==qtype){
        		html += '<textarea name="skey" rows="5" cols="55" style="width:450px"></textarea>';        		
        	}else if(3==qtype){
        		html += '<input type="text" name="skey"></textarea>';
        	}
        	if(OPTION_LIST != null && $(OPTION_LIST).length == 1){
        		html += '<input type="hidden" name="sid" value="'+$(OPTION_LIST)[0].id+'"/>';
        	}
        	$("#key_setting").append(html);
        }      
        load_init = function(){
        	var qtype = $("#_qtype_").val();
        	$("#qtype").val(qtype);
        	$("#qtype").attr("disabled","disabled");
        	$("#groupId").val($("#_groupid_").val());
        	$("#showNum").val($("#_showNum_").val());
        	//清空当前的选项
        	$("#key_setting").empty();
        	$("#btn_xuan_addrow").hide();
        	
        	var _skey_ = $("#_skey_").val();
        	
        	//选择题
        	if(0==qtype || 1==qtype){
        		xuan_init_load(qtype);
        		$("input[name=skey]").val(_skey_.split(''));
        	//问答题
        	}else if(2==qtype || 3==qtype){
        		wenda(qtype);
        		$("textarea[name=skey],input[name=skey]").val(unescape(_skey_));
        	}
        	
        	
        }      
        
        removeOption = function(a,opId){
        	$(a).parent().parent().remove();
        	
        	$.ajax({
        		type: 'POST',
        		url: 'deleteOp.action',
        		dataType:'json',
        		data: "id="+opId+"&random="+Math.random(),
        		cache : false,
        		error:function(){alert('系统连接失败，请稍后再试！')},
        		success: function(obj){			
        		}	  
        	});	
        }
        
        var OPTION_LIST = <%=request.getAttribute("OPTION_LIST") %>;
    </script>



</head>

<body>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
			<div class="fl"><img id="show" onclick="showHide();" src="../css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li><a href="#">试卷管理</a></li>
                	<li class="fin">创建试卷</li>
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
      <div style="padding-top: 35px;">
        <!--Filter-->
      <div class="filter">
       				<div class="fn clearfix">
		                  <h5 class="fl"><a href="#" class="colSelect fl">创建试卷</a></h5>
		             <input type="button" name="button2" id="button2" value="管理试题" class="fr">
		            </div>
		</div>
    <div >
    	<s:form action="saveQ.action" id="form" method="post"  namespace="/exam">
    <table class="table_1" width="100%" align="center">
    	<tr>
			<td class="lableTd">试题类型</td>
			<td>
				<select name="questType" id="qtype">
					<option value="0">单选题</option>
					<option value="1">多选题</option>
					<option value="2">问答题</option>
					<option value="3">填写题</option>
				</select>
			</td>
			<td class="lableTd">题号</td>
			<td>
				<input type="text" size="10" maxlength="3" name="questNum" class="validate[custom[onlyNumber]]" value="<s:property value="#request.q.questNum"/>"/>
				是否显示题号
				<select name="showNum" id="showNum">
					<option value="1">显示</option>
					<option value="0">不显示</option>
				</select>
			</td>			
		</tr>
    	<tr>
			<td width="10%" class="lableTd">所属试卷</td>
    		<td width="40%">
				<s:property value="#request.paper.title"/>
			</td>    	
    		<td width="10%" class="lableTd">所属分组</td>
    		<td width="40%">
    			<select name="groupId" class="validate[required]" id="groupId">
    				<option value="">--请选择所属分组--</option>
    				<% 
    				@SuppressWarnings("unchecked")
    				List<ExamGroup> groups = (List<ExamGroup>)request.getAttribute("groups");
    				if(groups!=null && groups.size()>0){
    					for(ExamGroup group : groups){
    				%>
					<option value="<%=group.getId()%>"><%=group.getTitle()!=null?group.getTitle() : ""%></option>
					<% 
						}
					}
					%>
				</select>
			</td>
    	</tr>
		<tr>
			<td class="thx">题干内容</td>
			<td colspan="3">
				<textarea rows="3" cols="55" style="width:450px" name="title" class="validate[required]"><s:property value="#request.q.title"/></textarea>
			</td>
		</tr>
		
		<tr>
			<td class="thx">答案设置<br/>
				<input type="button" id="btn_xuan_addrow" style="display:none" value="增加选项" class="btn" onclick="xuan_addrow($('#qtype').val());" />
			</td>
			<td colspan="3" id="key_setting"></td>
		</tr>
		
		<tr>
			<td class="thx">备注</td>
			<td colspan="3">
				<textarea rows="3" cols="55" style="width:450px" name="remark"><s:property value="#request.q.remark"/></textarea>
			</td>
		</tr>
		<tr>
			<td class="thx"></td>
			<td colspan="3">
				<input type="submit" value=" 提 交 " class="btn" />
				<input type="reset" value=" 重 置 " class="btn" />
				<input type="hidden" name="id" value="<s:property value="#request.q.id"/>" id="qid" />
				<input type="hidden" name="mainId" value="<s:property value="#request.paper.id"/>" id="mainId"/>
				
				<input type="hidden" value="<s:property value="#request.q.rightAnswer"/>" id="_skey_" />
				<s:if test="#request.q.id!=null">
				<input type="hidden" name="questType" value="<s:property value="#request.q.questType"/>" id="_qtype_" />
				</s:if>
				<input type="hidden" value="<s:property value="#request.q.groupId"/>" id="_groupid_" />
				<input type="hidden" value="<s:property value="#request.q.showNum"/>" id="_showNum_" />
			</td>
		</tr>
    </table>
      </s:form>    
    </div>
        <!--Table End-->
</div>
</div>
</body>
</html>