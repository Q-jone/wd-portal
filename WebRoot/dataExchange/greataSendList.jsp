<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>数据交换平台--数据查询</title>
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
		<script src="../js/flick/jquery-ui-1.8.18.custom.min.js"></script>
		<script src="../js/flick/jquery.ui.datepicker-zh-CN.js"></script>
		<script src="/portal/todoItem/js/jobcontact.js"></script>
		<style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
        a img{margin:0 auto;}
        #jobContactInfo {display:none;position:absolute;width:380px;height:auto;}
        #jobContactCuiban {display:none;position:absolute;width:150px;height:auto;}
		.hoverTable {border:0px solid #DADCD3;background:#FBFACC;width:100%;cellpadding:0px; cellspacing:0px;text-align:center;border-collapse:collapse;}
		.hoverTable tr td {width:0%;border:1px solid #DADCD3;text-align:middle;padding:2px;}
		.title{ font-size:12px; color:#931602;  font-weight:bold;text-align:center;display:inline;}
		.summary{display:block;cursor:pointer;}
		.table_1 font,.table_1 b {display:inline;}
		</style>
	<script type="text/javascript"><!--
	 //跳转到制定页
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
      
		function viewXML(id){
			sonWindow = window.open('viewXML.action?id='+id+'&rand='+Math.random());
		}


        $(document).ready(function () {
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
					
	        var valid = '<%=request.getAttribute("valid")%>';
			if(valid!=""&& valid!="null"){
				$("form:first").find("select>option[value='"+valid+"']").attr("selected",true);
			}
  
	        $('#dataDates').datepicker({
	    		inline: true,  
                changeYear: true,  
                changeMonth: true , 
                showButtonPanel:true,     
                closeText:'清除',   
                onSelect:
                function(selectedDate){
					$("#dataDatee").datepicker("option","minDate",selectedDate);
				},
                
                currentText:'ds'//仅作为“清除”按钮的判断条件  
	    	});
	    	$('#dataDatee').datepicker({
	    		inline: true,  
                changeYear: true,  
                changeMonth: true  ,  
                showButtonPanel:true,     
                closeText:'清除',
                onSelect:  
                function(selectedDate){
					$("#dataDates").datepicker("option","maxDate",selectedDate);
				}, 
                currentText:'de'//仅作为“清除”按钮的判断条件  
	    	});
	    	
			$(".ui-datepicker-close").live("click", function (){          
              if($(this).parent("div").children("button:eq(0)").text()=="ds") $("#dataDates").val("");
              if($(this).parent("div").children("button:eq(0)").text()=="de") $("#dataDatee").val("");       
              });
						                
		/*
	        var valid = '<%=request.getAttribute("valid")%>';
			if(valid!=""&& status!="null"){
				$("form:first").find("select>option[value='"+valid+"']").attr("selected",true);
			}
			*/				
			$("#clearInput").click(function(){
	      		$("#dataDates").val("");
	      		$("#dataDatee").val("");
	      		//$("form:first").find("select>option[value='']").attr("selected",true);
	      		$("#page").val("1");
			})
             
      })
        
       
    --></script>



</head>

<body>
	<div id="jobContactInfo" style="z-index:2;"></div>
	<div id="jobContactCuiban" style="z-index:2;"></div>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
			<div class="fl"><img id="show" onclick="showHide();" src="../css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li><a href="javascript:window.location.href='/portal/center/wdsw/wd_index.jsp'">数据交换平台</a></li>
                	<li class="fin"><s:property value="#request.dataTypes[#request.dataType]"/>信息列表</li>
                </ul>
            </div>
   		</div>
        <!--Ctrl End-->
      <div class="pt45">
      <div class="filter">
        	<div class="query">
        	<div class="p8 filter_search">
        	<s:form action="findGreataSendByPage" id="form" method="post" namespace="/dataExchange" theme="simple">
        	<input type="hidden"  value="<s:property value="#request.dataType"/>" name="dataType" id="dataType"/>
        	<input type="hidden" name="page" id="page" value="<s:property value="#request.page.currentPage"/>"/>
        	
	       	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
	       	    <tr>
	       	      <td class="t_r">数据日期</td>
	       	      <td>
	       	      	<input type="text" id="dataDates" name="dataDates" class="input_large date" value="<s:property value="#request.dataDates"/>"  readonly="readonly"/>
	       	       -
                   <input type="text" id="dataDatee" name="dataDatee" class="input_large date"  value="<s:property value='#request.dataDatee'/>"  readonly="readonly"/>
                  </td>
                  <!-- 
	       	      <td class="t_r" style="display:none">状态</td>
	              <td style="display:none">
	              	<select name="valid" id="valid" class="input_large">
	              				<option value="">--请选择--</option>	
	                      		<option value="1">未办理</option>
	                      		<option value="0">已办理</option>						
	                </select>
	              </td>   
	               -->     	      
	     	      <td class="t_c">
	                <input type="submit" value="检 索" />&nbsp;&nbsp;
					<input id="clearInput" type="button" value="重 置"/>
				  </td>
      	      </tr>
      	    </table>
      	    </s:form>
      	    </div>
        	</div>     
		      <div class="fn clearfix">
		                  <h5 class="fl"><a href="#" class="fl">信息列表</a></h5>
		      </div>
		</div>
      
        <!--Filter End-->
      <!--Table-->
        <div class="mb10">
        	<table width="100%"  class="table_1">
                              <tbody>
                              	<tr class="tit">
			                      <td class="t_c" width="5%">序号</td>
			                      <td class="t_c" width="35%">文件标题</td>
			                      <!-- td class="t_c" width="20%" style="display:none">数据类型</td-->
			                      <td class="t_c" width="10%">集团文件编号</td>
			                      <td class="t_c" width="15%">原文件编号</td>
			                      <td class="t_c" width="15%">办理时间</td>
			                      <td class="t_c" width="10%">办理状态</td>
			                      <td class="t_c" width="10%">操作</td>                                
                                </tr>
                              <s:iterator value="#request.page.result" id="items" status="st">
                              <tr>
                              		<td class="t_c"><s:property value="#st.index+#request.page.startRow+1"/></td>
                               		<td class="t_c"><s:property value="title"/></td>
									<!-- td class="t_c" style="display:none"><s:property value="dataType"/></td-->
				                   	<td class="t_c"><s:property value="swid"/></td>
				                   	<td class="t_c"><s:property value="filezh"/></td>
				                   	<td class="t_c"><s:date name="operateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
				                   	<td class="t_c">
				                   		<s:if test="valid==0">已办理</s:if>
										<s:elseif test="valid==1">未办理</s:elseif>
				                   	</td>
				                   	<td class="t_c"><a class="fl mr5" href="javascript:void(0);" onclick="viewXML( '<s:property value='id'/>');">预览/办理</a></td>
				                </tr>
                                </s:iterator>
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="11"><div class="clearfix"><span class="fl">共<s:property value="#request.page.totalRows"/>条记录</span>
                           		<ul class="fr clearfix pager">
		                             <li>Pages:<s:property value="#request.page.currentPage"/>/<s:property value="#request.page.totalPages"/>
		                             		<input type="hidden" value="<s:property value='#request.page.totalPages'/>" id="totalPageCount">
		                             		<input type="hidden" value="<s:property value='#request.page.currentPage'/>" id="currentNumber">
			                                  <input type="text" id="number" name="pageNumber" min="0" max="999" step="1" class="input_tiny" value="<s:property value='#request.page.currentPage'/>"/>
			                                  <input type="button" name="button" id="button" value="Go" onclick="goPage(0,0)">
		                             </li>
                             
		                             <s:if test="#request.page.currentPage==#request.page.totalPages">
		                             <li><a href="javascript:void(0)">&gt;&gt;</a></li>
		                             </s:if>
		                             <s:else>
		                              <li><a href="javascript:void(0)" onclick="goPage(<s:property value='#request.page.totalPages'/>,3)">&gt;&gt;</a></li>
		                             </s:else>
                              
	                             	<li>
	                             	<s:if test="#request.page.currentPage==#request.page.totalPages">	
	                             		<a href="javascript:void(0)">下一页</a>
	                             	</s:if>
	                             	<s:else>
	                             		<a href="javascript:void(0)" onclick="goPage(<s:property value='#request.page.currentPage'/>,2)">下一页</a>
	                             	</s:else>
	                             	</li>
	                             	<li>
	                             	<s:if test="#request.page.currentPage==1">
	                             		<a href="javascript:void(0)">上一页</a>
	                             	</s:if>
	                             	<s:else>
	                             		<a href="javascript:void(0)" onclick="goPage(<s:property value='#request.page.currentPage'/>,1)">上一页</a>
	                             	</s:else>
	                             	
	                             	</li> 
	                             
	                             	<s:if test="#request.page.currentPage==1">
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