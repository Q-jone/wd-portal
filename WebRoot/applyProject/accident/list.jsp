<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>事件管理</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/formalize.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/page.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/default/imgs.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/reset.css" />
<!--[if IE 6.0]>
           <script src="../js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
        <script src="../../js/html5.js"></script>
        <script src="../js/jquery-1.10.2.min.js"></script>
        <script src="../js/jquery-migrate-1.2.1.min.js"></script>
        <script src="../js/jquery.blockUI.js"></script>
        <script src="../js/jquery.form.js"></script>
		<script src="../../js/jquery.formalize.js"></script>
		<link type="text/css" href="../css/flick/jquery-ui-1.10.3.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="../js/jquery-ui-1.10.3.custom.min.js"></script>
		<script type="text/javascript" src="../../js/flick/jquery.ui.datepicker-zh-CN.js"></script>
       
	<script type="text/javascript">
	jQuery.notNull = function(options){
			if (typeof(options) == "undefined" || options== null){
				return "";
			}else {
				return options;
			}
		}
	

		var listOptions = {
			cache:false,
			dataType:'json',
			type:'post',
			callback:null,
			url:'/portal/applyProjectList/accidentList.action?random='+Math.random(),
		    success:function(data){
		    	$("#checkboxAll").prop("checked",false);
			    	var p = data.pageInfo;
					var temp = "";
					if(data!=null && data.list.length >0){
						var l = data.list;
						
						for(var i=0;i<l.length;i++){
							temp += "<tr>";
							temp += "<td class='t_c'><input class='clk' type='checkbox' value='"+$.notNull(l[i].ID)+"'></td>";
							temp += "<td class='t_c'>"+(i+1) +"</td>";
							temp += "<td class='t_l'>"+$.notNull(l[i].ACCIDENTCONTENT).replace(/\n/g,"<br>") +"</td>";
							temp += "<td class='t_c'>"+$.notNull(l[i].OPERATEPERSON) +"</td>";
							temp += "<td class='t_c'>"+$.notNull(l[i].OPERATETIME) +"</td>";
							
							<s:if test='#session.userName=="胡波" || 
					        	#session.userName=="李名敏" ||
					        	#session.userName=="金涛" ||
					        	#session.userName=="黄天印"'>
					    	
							temp += "<td class='t_c'><a class='editA' href='javascript:void(0);' accidentId='"+l[i].ID+"'>编辑</a></td>";
							</s:if>
							temp += "<td class='t_c'><a target='_blank' href='/portal/applyProject/accidentView.action?id="+l[i].ID+"'>查看</a></td>";
							temp += "</tr>";
						}
						$("#totalPage_out").val(p.totalPage);
						$("#totalPage").html("当前显示"+(((p.currentPage-1)*p.pageSize)+1)+"-"+(((p.currentPage-1)*p.pageSize)+l.length)+"条记录，"+"总记录："+p.totalRow+"条");	
						var totalOption = "";
						for(var i=1;i<=p.totalPage;i++){
							totalOption += "<option value='"+i+"'>"+i+"</option>";						
							$("#page_out").html(totalOption);
						}
						$("#page_out").val(p.currentPage);
						$("#pageSize_out option:last").val(p.totalRow);
					}else{
						$("#totalPage_out").val(0);
						$("#totalPage").html("当前显示0条记录，总记录：0条");
						$("#page_out").html("<option value='0'>0</option>");
					}
					
					$("#mytable>tbody").eq(0).html(temp);
					//var t = $("#show>tbody").eq(0).find("tr:first").html();
					//console.log($("#show>tbody").eq(0).html());
					//alert(index);
					
					$.unblockUI();
				return false;
		    }
		};
		
		function list(){
			$.blockUI({ message: $('#domMessage') }); 
			 $("#list").ajaxSubmit(listOptions);
		}
		
		var intHandn=null;
		var rtnn=null;	
		function checkWinn(){
				if(rtnn!=null && rtnn.closed){
					clearInterval(intHandn);
					intHandn=null;
					rtnn=null;
					list()
				}
		}
		function openWinn(url,i){
			rtnn = window.open(url,i);
			intHandn=setInterval("checkWinn()",3000);
			return false;
		}
		
		
		
	 	$(function(){
	 		$("#deleteButton").click(function(){
				var chks = "";
				$(".clk:checked").each(function(){
					chks += "'"+$(this).val()+"',";
				})
				if(chks == ""){
					alert("请选择！");
				}else{
					chks = chks.substr(0,(chks.length)-1);
					$.post(
							'/portal/applyProject/delete.action?random='+Math.random(),
							{
								"id": 	chks
							},
							function(obj, textStatus, jqXHR){
								if(obj=="1"){
									alert("操作成功!");
									list();
								}
							},
							"text"
						).error(function() { alert("服务器连接失败，请稍后再试!"); })
				}
			});
	 		
	 		$(document).on("click","#checkboxAll",function(){
				//alert($(this).prop("checked"));
				$("tbody").find("input[type=checkbox]").prop("checked",$(this).prop("checked"));
			});
			
			$(document).on("click",".clk",function(){
				 if($(".clk:checked").length != $(".clk").length){
					 $("#checkboxAll").prop("checked",false);
				 }else{
					 $("#checkboxAll").prop("checked",true);
				 }
			});
			
	 		$("#addButton").click(function(){
	 			openWinn("/portal/applyProject/accident/add.jsp",new Date().getTime());
	 		})
	 		
	 		$(document).on("click",".editA",function(){
	 			openWinn("/portal/applyProject/accidentEdit.action?id="+$(this).attr("accidentId"),$(this).attr("accidentId"));
	 			return false;
	 		})
	 		
	 		$("#prePage").click(function(){
	 			if($("#page_out").val() == 1 || $("#page_out").val() == 0){}
	 			else{
	 				$("#page_out").val(parseInt($("#page_out").val()-1));
	 				$("#page").val($("#page_out").val());
	 	        	list();
	 			}
	 			
	 		})
	 		
	 		$("#nextPage").click(function(){
	 			if(($("#page_out").val() == $("#totalPage_out").val()) || $("#page_out").val() ==0){}
	 			else{
	 				$("#page_out").val(parseInt($("#page_out").val())+1);
	 				$("#page").val($("#page_out").val());
	 	        	list();
	 			}
	 			
	 		})
	 	
	 		
	 	$("#submit").click(function(){
       		 list();
       	 })
       	
        $(document).on("change","#page_out",function(){
        	$("#page").val($(this).val());
        	list();
        })
        
        $(document).on("change","#pageSize_out",function(){
        	if($("#page_out").val()!=0){
	        	$("#pageSize").val($(this).val());
	        	list();
         	}
        })
        	
      
           list();
           
		})	
    </script>

</head>

<body>
<div id="domMessage" style="display:none;"> 
    <h1>请稍后</h1> 
</div> 	
<%-- 操作页面--%>
		
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
			<div class="fl"><img id="show" onclick="showHide();" src="../../css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li class="fin">事件管理</li>
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
           		<li><a href="/portal/applyProject/plan/list.jsp"><span>计划管理</span></a></li>
	           	<li class="selected"><a href="/portal/applyProject/accident/list.jsp"><span>事件管理</span></a></li>
	        	<li><a href="/portal/applyProject/project/list.jsp"><span>项目管理</span></a></li>
	           	
	        </ul>
        </div>
        <!--Filter-->
      <div class="filter">
        	<div class="query">
        	<div class="p8 filter_search">
        	<form id="list">
        	<input type="hidden" name="pageSize" id="pageSize" value=""/>
        	
        	<input type="hidden" name="page" id="page" value=""/>
        	 <table width="100%" border="0" cellspacing="0" cellpadding="0">
        	    <tr>
        	     <td class="t_r">事件内容</td>
        	     <td>
        	     <input type="text" id="accidentcontent_like" name="accidentcontent_like" class="input_xxlarge"/>
        	     </td>
        	     <td class="t_r"></td>
        	     <td><input type="hidden" id="type_equal" name="type_equal" value="2"/></td>
        	     <td class="t_r"></td>
        	     <td><input type="hidden" id="order" name="order" value="operatetime"/></td>
      	      	</tr> 
      	      	
        	    <tr>
        	      <td colspan="6" class="t_c">
                  	<input id="submit" type="button" value="检 索" />&nbsp;&nbsp;
                  	<input type="reset" value="重 置"/></td>
				</tr>
      	    </table>
      	    </form>
      	    </div>
        	</div>     
		      <div class="fn clearfix">
		                  <h5 class="fl"><a href="#" class="colSelect fl">事件管理列表</a></h5>
		             <s:if test='#session.userName=="胡波" || 
					        	#session.userName=="李名敏" ||
					        	#session.userName=="金涛" ||
					        	#session.userName=="黄天印"'>
					 <input type="button" name="deleteButton" id="deleteButton" value="删除" class="fl">  	    	
		             &nbsp;<input type="button" name="addButton" id="addButton" value="新 增" class="fr">
		          </s:if>
		          </div>
		      </div>


      
        <!--Filter End-->
        <!--Table-->
        <div class="mb10" id="listDiv">
        	<table width="100%"  class="table_1" id="mytable">
        				<thead>
                              <tr class="tit">
                                <th class="t_c"><input type="checkbox" id="checkboxAll" name="checkboxAll" /></th>
                                <th style="white-space:nowrap;" class="t_c">序号</th>
                                <th style="white-space:nowrap;" class="t_l">事件内容</th>
                                 <th style="white-space:nowrap;" class="t_c">操作人</th>
                                 <th style="white-space:nowrap;" class="t_c">操作时间</th>
                                <th style="white-space:nowrap;" colspan="2" class="t_c">操作</th>
                               </tr>
                              
                              </thead>
                              <tbody>
                             
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="7"><div class="clearfix">
                                <input type="hidden" id="totalPage_out">
                                <span id="totalPage" class="fr">当前显示1-10条记录，总记录：100</span>
                           		<ul class="fl clearfix pager">
		                             <li class="fl" id="nextPage"><a href="javascript:void(0)">下一页</a></li>
		                             <li class="fl" id="prePage"><a href="javascript:void(0)">上一页</a> </li>
		                        </ul>
		                            <span class="fl">
									&nbsp; 跳转至：
	                             	<select id="page_out"></select>
	                             	
	                             	</span>
	                             	<span class="fl">&nbsp;每页显示条数：	</span>
	                             	<select id="pageSize_out">
                                <option value="10">10</option>
                                <option value="15">15</option>
                                <option value="20">20</option>
                                <option value="30">30</option>
                                <option value="-1">显示全部</option>
                                </select>
                            
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