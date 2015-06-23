<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.wonders.stpt.metroExpress.constant.ExpressConstants"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>用户管理</title>
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
       	<script src="../js/jquery-1.7.1.js"></script>
        <script src="../js/html5.js"></script>
		<script src="../js/jquery.formalize.js"></script>
		<script src="../js/My97DatePicker/WdatePicker.js"></script>
		<script src="../js/show.js"></script>
	<script type="text/javascript">
        $(document).ready(function () {
        	$("#button2").click(function(){
        		window.open("stptUserAdd.jsp");
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
			
			loadShow();			
        });
        
        
       
       //跳转到制定页
       function goPage(pageNo,type){
       
       		//type=0,直接跳转到制定页
	       if(type=="0"){
	   	    	var pageCount = $("#totalPageCount").val();
	       		var number = $("#number").val();
	       		if(!number.match(/^[0-9]+$/)){
	       			alert("请输入数字");
	       			$("#number").val("");
	       			$("#number").focus();
	       			return ;
	       		}
	       		if(parseInt(number)>parseInt(pageCount)){
	       			$("#number").val(pageCount);
	       			$("#pageNo").val(pageCount);
	       		}else{
	       			$("#pageNo").val(number);
	       		}
	       }
			//type=1,跳转到上一页	       
	       if(type=="1"){
	       		$("#pageNo").val(parseInt($("#number").val())-1);
	       }
			//type=2,跳转到下一页	       
	       if(type=="2"){
	       		$("#pageNo").val(parseInt($("#number").val())+1);
	       }
	       
	       //type=3,跳转到最后一页,或第一页
	       if(type=="3"){
	   	    	$("#pageNo").val(pageNo);
	       }
       	   $("#form").submit();
       }
      
      
      //点击取消按钮后 清空所有数据
      function clearInput(){
      		//清空时间
      		$("#loginName").val("");
      		$("#name").val("");
      		$("#sex").val("");
      		$("#rank").val("");
      		$("#company").val("");
      		$("#dept").val("");
      }

      
      function deleteStptUser(id){
      		if(confirm("确认删除吗？")){
      		$.ajax({
			type: 'POST',
			url: 'stptUserDelete.action?random='+Math.random(),
			data:{
					"stptUserId" : id
				},
			dataType:'json',
			cache : false,
			error:function(){alert('系统连接失败，请稍后再试！')},
			success: function(obj){	
				if(obj){
					alert("删除成功！")
					$("#form").submit();
				}
			}	  
		});
      }
      }
        
      var rtn = null;
      var intHand = null;  
      function edit(id){
      		var url = "stptUserEdit.action?stptUserId="+id;
      		rtn = window.open(url,'manage'+id);
      		intHand=setInterval("checkWin()",3000);
      }
      
      
      function checkWin()
	{
		
		if(rtn!=null && rtn.closed){
			clearInterval(intHand);
			intHand=null;
			rtn=null;
			//alert(1);
			document.location.reload();
		}
	}
       
    </script>



</head>

<body>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
			<div class="fl"><img id="show" onclick="showHide();" src="../css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li><a href="#">用户管理</a></li>
                	<li class="fin">用户管理</li>
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
        <!--Filter-->
      <div class="filter">
        	<div class="query">
        	<div class="p8 filter_search">
        	<s:form action="stptUserManageByPage" id="form" method="post"  namespace="/userManage">
        	
        	<input type="hidden"  value="" name="number" id="pageNo"/>
        	
        	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
        	    <tr>
        	     <td class="t_r">用户工号</td>
        	      <td>
        	      	<input value="<s:property value="stptUser.loginName"/>" type="text" id="loginName" name="loginName" class="input_large" />
        	      </td>
        	      <td class="t_r">用户姓名</td>
        	      <td><input value="<s:property value="stptUser.name"/>" type="text" id="name" name="name" class="input_large"/></td>
        	      <td class="t_r">性别</td>
        	      <td>
        	      <select name="sex" id="sex" class="input_large" >
        	      		<option value="">---全部---</option>
        	      		<option value="1" <s:if test='stptUser.sex=="1"'> selected </s:if>  >男</option>
        	      		<option value="0" <s:if test='stptUser.sex=="0"'> selected </s:if>  >女</option>
        	      </select>
        	     </td>
        	    
      	        </tr>
      	        
      	         <tr>
        	     <td class="t_r">职位</td>
        	      <td>
        	      	<input value="<s:property value="stptUser.rank"/>" type="text" id="rank" name="rank" class="input_large" />
        	      </td>
        	      <td class="t_r">单位</td>
        	      <td><input value="<s:property value="stptUser.company"/>" type="text" id="company" name="company" class="input_large"/></td>
        	      <td class="t_r">部门</td>
        	      <td><input value="<s:property value="stptUser.dept"/>" type="text" id="dept" name="dept" class="input_large"/></td>
        	    
      	        </tr>
      	        
      	        
      	        
        	    <tr>
        	      <td colspan="6" class="t_c">
                  	<input type="submit" value="检 索" />&nbsp;&nbsp;
                  	<input type="button" value="重 置" onclick="clearInput()"/></td>
				</tr>
      	    </table>
      	    </s:form>
      	    </div>
        	</div>
        
       				<div class="fn clearfix">
		                  <h5 class="fl"><a href="#" class="colSelect fl">用户信息列表</a></h5>
		             <input type="button" name="button2" id="button2" value="新 增" class="fr">
		            </div>
		</div>

        <!--Filter End-->
        <!--Table-->
        <s:set name="lineMap" value="#request.lineMap"/>
        <div class="mb10">
        	<table width="100%"  class="table_1">
                              <tbody>
                              <tr class="tit">
                                <!-- <td><input type="checkbox" id="test_checkbox_1" name="test_checkbox_1" /></td>-->
                                <td class="t_c">用户工号</td>
                                <td class="t_c">用户姓名</td>
                                <td class="t_c">性别</td>
                                <td class="t_c">职位</td>
                                <td class="t_c">单位</td>
                                <td class="t_c">部门</td>
                                <td class="t_c">联系方式</td>
                                <td class="t_c">最后登陆时间</td>
                                <td class="t_c">操作</td>
                                </tr>
                              <s:iterator value="#request.page.result">
                              <tr>
                                <!-- <td class="t_c"><input type="checkbox" id="test_checkbox_1" name="test_checkbox_1" /></td>-->
                               	<td class="t_c"><s:property value="loginName"/></td>
                                <td class="t_c"><s:property value="name"/></td>
                                <td class="t_c"><s:if test='sex=="1"'> 男 </s:if><s:elseif test='sex=="0"'>女</s:elseif><s:else>&nbsp;</s:else></td>
                                <td class="t_c"><s:property value="rank"/></td>
                                <td class="t_c">
                                <s:set name="str" value="company"></s:set>  
								<s:if test="#str.length()>15">  
									<s:property value="#str.substring(0,15)+'...'" />  
								</s:if>  
								<s:else>  
									<s:property value="#str" />  
								</s:else>  
                                </td>
                                <td class="t_c">
                                <s:set name="str2" value="dept"></s:set>  
								<s:if test="#str2.length()>15">  
									<s:property value="#str2.substring(0,15)+'...'" />  
								</s:if>  
								<s:else>  
									<s:property value="#str2" />  
								</s:else>  
                                </td>
                                <td class="t_c"><s:property value="phone"/></td>
                                <td class="t_c"><s:property value="operateTime"/></td>
                                <td class="t_c"><a class="mr5" href="stptUserView.action?stptUserId=<s:property value="id"/>" target="_blank">查看</a> 
                                <a class="mr5" href="javascript:edit('<s:property value="id"/>')">修改</a>
                                <a class="mr5" href="javascript:deleteStptUser('<s:property value="id"/>')">删除</a>
                                </td>
                                </tr>
                                </s:iterator>
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="10"><div class="clearfix"><span class="fl"><s:property value="#request.page.totalSize"/>条记录，当前显示<s:property value="#request.page.start"/>-<s:property value="#request.page.start+#request.page.pageSize-1"/>条</span>
                           		<ul class="fr clearfix pager">
		                             <li>Pages:<s:property value="#request.page.currentPageNo"/>/<s:property value="#request.page.totalPageCount"/>
		                             			<input type="hidden" value="<s:property value='#request.page.totalPageCount'/>" id="totalPageCount">
			                                  <input type="text" id="number" name="pageNumber" min="0" max="999" step="1" class="input_tiny" value="<s:property value='#request.page.currentPageNo'/>"/>
			                                  <input type="button" name="button" id="button" value="Go" onclick="goPage(0,0)">
		                             </li>
                             
		                             <s:if test="#request.page.currentPageNo==#request.page.totalPageCount">
		                             <li><a href="javascript:void(0)">&gt;&gt;</a></li>
		                             </s:if>
		                             <s:else>
		                              <li><a href="javascript:void(0)" onclick="goPage(<s:property value='#request.page.totalPageCount'/>,3)">&gt;&gt;</a></li>
		                             </s:else>
                              
	                             	<li>
	                             	<s:if test="#request.page.currentPageNo==#request.page.totalPageCount">	
	                             		<a href="javascript:void(0)">下一页</a>
	                             	</s:if>
	                             	<s:else>
	                             		<a href="javascript:void(0)" onclick="goPage(<s:property value='#request.page.currentPageNo'/>,2)">下一页</a>
	                             	</s:else>
	                             	</li>
	                             	<li>
	                             	<s:if test="#request.page.currentPageNo==1">
	                             		<a href="javascript:void(0)">上一页</a>
	                             	</s:if>
	                             	<s:else>
	                             		<a href="javascript:void(0)" onclick="goPage(<s:property value='#request.page.currentPageNo'/>,1)">上一页</a>
	                             	</s:else>
	                             	
	                             	</li> 
	                             
	                             	<s:if test="#request.page.currentPageNo==1">
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